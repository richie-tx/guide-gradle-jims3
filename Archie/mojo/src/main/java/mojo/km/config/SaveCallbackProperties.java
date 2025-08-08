package mojo.km.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mojo.km.naming.MappingConstants;
import mojo.tools.code.KeyWord;

/**
 * Responsible for handling the definition to update or insert a persistent object in the persistent
 * data store.
 *  
 */
public class SaveCallbackProperties extends CallbackProperties
{
    public static final String GENOID = "genOID";

    private static final int QUERY_BUFFER_SIZE = 200;

    private String deleteQuery;

    private List dependencies;

    private FieldMappingProperties fProps;
    
    private ParmMappingProperties pProps;

    private String insertQuery;

    private String insertQueryKey;

    private boolean optimized;

    private boolean saveMethod;

    private String updateQueryKey;

    private String updateQuery;

    private String deleteQueryKey;

    public SaveCallbackProperties()
    {
        this.dependencies = new ArrayList();
    }

    public void addDependency(DependencyProperties aProp)
    {
        this.dependencies.add(aProp);
    }

    private void buildJDBCDeleteQuery()
    {
        if (this.source == null || this.source.equals(""))
        {
            throw new LoadMappingPropertyException("SaveCallback source has not been defined for: " + this.parent.getEntity());
        }

        ParmMappingProperties pProps = (ParmMappingProperties) parms.get(0);

        StringBuilder buffer = new StringBuilder(30);
        buffer.append("DELETE FROM JIMS2.");
        buffer.append(this.source);
        buffer.append(MappingConstants.WHERE);
        buffer.append(pProps.getDataItemName());
        buffer.append("= ?");
        this.deleteQuery = buffer.toString();
    }

    private void buildJDBCInsertQuery()
    {
    	StringBuilder buffer = new StringBuilder(QUERY_BUFFER_SIZE);
        buffer.append("INSERT INTO JIMS2.");
        buffer.append(this.source);
        buffer.append(" (");

        int len = this.fields.size();
        for (int i = 0; i < len; i++)
        {
            FieldMappingProperties fieldProps = (FieldMappingProperties) this.fields.get(i);

            buffer.append(fieldProps.getDataItemName());

            if (i + 1 < len)
            {
                buffer.append(KeyWord.COMMA);
            }
        }

        buffer.append(") VALUES (");

        if (len < 1)
        {
            throw new LoadMappingPropertyException("SaveCallback has no field mappings for: " + this.parent.getEntity());
        }

        // offset for final parm
        len--;

        for (int j = 0; j < len; j++)
        {
            buffer.append("?,");
        }

        buffer.append("?)");

        this.insertQuery = buffer.toString();
    }

    private void buildJDBCUpdateQuery()
    {
        FieldMappingProperties fieldProps;

        StringBuilder buffer = new StringBuilder(200);
        buffer.append("UPDATE JIMS2.");
        buffer.append(this.source);
        buffer.append(" SET ");

        int len = this.fields.size();

        for (int i = 0; i < len; i++)
        {
            // append field to the SELECT column list
            fieldProps = (FieldMappingProperties) this.fields.get(i);

            String dataItem;
            //89196 changes.
            if (fieldProps.getDataItemName().equalsIgnoreCase("CREATEUSER"))
            {
                dataItem = "UPDATEUSER";
            }
            else if (fieldProps.getDataItemName().equalsIgnoreCase("CREATEJIMS2USER"))
            {
                dataItem = "UPDATEJIMS2USER";
            }
            else
            {
                dataItem = fieldProps.getDataItemName();
            }

     //       dataItem = fieldProps.getDataItemName(); //89196 changes.
            buffer.append(dataItem);
            buffer.append("=?");

            if (i + 1 < len)
            {
                buffer.append(",");
            }
        }

        buffer.append(" WHERE ");

        ParmMappingProperties pProps = (ParmMappingProperties) parms.get(0);
        buffer.append(pProps.getDataItemName());
        buffer.append("=?");

        this.updateQuery = buffer.toString();
    }

    /**
     * @return Returns the deleteQuery.
     */
    public String getDeleteQuery()
    {
        return deleteQuery;
    }

    public List getDependencies()
    {
        return this.dependencies;
    }

    public FieldMappingProperties getErrorFieldMappingProperties()
    {
        return fProps;
    }

    public String getGenOID()
    {
        return getProperty(GENOID);
    }

    /**
     * @return Returns the insertQuery.
     */
    public String getInsertQuery()
    {
        return insertQuery;
    }

    /**
     * @return Returns the insertQueryKey.
     */
    public String getInsertQueryKey()
    {
        return insertQueryKey;
    }

    /**
     * @return Returns the updateQuery.
     */
    public String getUpdateQuery()
    {
        return updateQuery;
    }

    public String getDeleteQueryKey()
    {
        return this.deleteQueryKey;
    }

    /**
     * @return Returns the updateQueryKey.
     */
    public String getUpdateQueryKey()
    {
        return updateQueryKey;
    }

    protected boolean isOptimized()
    {
        return this.optimized;
    }

    /**
     * @return Returns the saveMethod.
     */
    public boolean isSaveMethod()
    {
        return saveMethod;
    }

    /**
     * This is handled separately from EventQueryProperties because the logic differs slightly.
     * 
     * @throws LoadMappingPropertyException
     */
    public void optimize() throws LoadMappingPropertyException
    {
        this.optimizeSaveMethod();

        if (this.isSaveMethod())
        {
            this.parent.setSaveCallbackProperties(this);
        }
        
        List fields = this.getFieldsList();
        int len = fields.size();
        this.fieldsArray = new FieldMappingProperties[len];

        //*** Fields Optimization
        try
        {
            Class clazz = Class.forName(this.parent.getEntity());
            
            for(int f=0;f<len;f++)
            {
                this.fProps = (FieldMappingProperties) fields.get(f);
                this.fieldsArray[f] = this.fProps;

                // convert property type to constant found in
                // naming.MappingConstants
                this.fProps.optimizeDataItem();
                this.fProps.optimizeDataType();
                this.fProps.optimizeReflection(clazz); // cache property methods
                // on Field Props
                this.fProps.optimizeFormatting();
                // index is optimized when fieldMapping is added to the callback
            }

            // sort JDBC parms by index (parmIndex)
            Collections.sort(this.fields, new FieldMappingProperties.Sorter());

            // optimize parms
            fields = this.getParmList();
            len = fields.size();
            this.parmsArray = new ParmMappingProperties[len];
            for(int f=0;f<len;f++)
            {
                this.pProps = (ParmMappingProperties) fields.get(f);
                this.parmsArray[f] = this.pProps;
                // convert property type to constant found in
                // naming.MappingConstants
                this.pProps.optimizeDataItem();
                this.pProps.optimizeDataType();

                // different from EventQueryProperties, parm property is derived
                // from the Entity
                this.pProps.optimizeReflection(clazz); // cache property methods
                // on Parm Props

                this.pProps.optimizeFormatting();
                
                this.fProps = pProps;

                // index is optimized when fieldMapping is added to the callback
            }

            /*
             * if (super.connectionName.startsWith(MappingConstants.JDBC)) { // sort JDBC parms by
             * index (parmIndex)
             */
            Collections.sort(parms, new FieldMappingProperties.Sorter());
            //}
        }
        catch (ClassNotFoundException e)
        {
            String msg = "ClassNotFoundException: " + e.getMessage();
            throw new LoadMappingPropertyException(msg, e);
        }

        if (this.connectionName.startsWith("JDBC"))
        {
            this.buildJDBCInsertQuery();

            if (parms.size() == 1)
            {
                this.buildJDBCUpdateQuery();
                this.buildJDBCDeleteQuery();
            }
        }

        this.deleteQueryKey = this.createKey(MappingConstants.DELETE_OPERATION);
        this.insertQueryKey = this.createKey(MappingConstants.INSERT_OPERATION);
        this.updateQueryKey = this.createKey(MappingConstants.UPDATE_OPERATION);

        // only optimze if no exceptions are thrown
        this.optimized = true;
    }

    /**
     *  
     */
    private void optimizeSaveMethod()
    {
        if (MappingConstants.SAVE_METHOD.equals(this.mappingMethodName))
        {
            this.saveMethod = true;
        }
    }

    public void setGenOID(String aVal)
    {
        setProperty(GENOID, aVal);
    }
}
