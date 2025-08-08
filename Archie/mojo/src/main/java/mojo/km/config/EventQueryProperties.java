package mojo.km.config;

import java.util.Collections;

import java.util.List;

import mojo.km.naming.MappingConstants;
import mojo.tools.code.KeyWord;

/**
 * Responsible for defining mapping to IEvents that will hold properties to be used for queries.
 * 
 * @author Eric A Amundson, Jim Fisher
 */
public class EventQueryProperties extends CallbackProperties
{
    public static final String EVENTNAME = "eventName";

    public static final String WHERECLAUSE = "whereClause";

    public static final String WHERECLAUSEGENERATOR = "whereClauseGenerator";

    public static final String WHERECLAUSEGENERATORMETHOD = "whereClauseGeneratorMethod";

    private String eventName;

    private FieldMappingProperties fProps;

    private boolean optimized;

    private String queryKey;

    private String queryString;

    private int queryType;

    private boolean retrieveDynamicMethod;

    private boolean retrieveMethod;

    private String whereClauseGenerator;

    protected boolean whereClauseGeneratorExists;

    private String whereClauseGeneratorMethod;

    private void buildJDBCQuery()
    {
    	StringBuilder buffer = new StringBuilder();
        buffer.append("SELECT ");

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

        buffer.append(" FROM ");
        
        // TODO Use the following once Spring is introduced for Connection Properties
        //buffer.append(cProps.getDb2Schema());
        
        if("JDBC".equalsIgnoreCase(this.connectionName))
        {
        	buffer.append("JIMS2.");
        }
        else if("JDBCMSSQL".equalsIgnoreCase(this.connectionName))
        {
        	buffer.append("JIMS2.");
        }
        else if("JDBCStatic".equalsIgnoreCase(this.connectionName))
        {
        	buffer.append("HCDB2E2.JIMS2.");
        }
        
        buffer.append(this.source);        
        
        if (this.queryType == MappingConstants.ATTRIBUTE_QUERY)
        {
            buffer.append(MappingConstants.WHERE);
        }
        else if (this.whereClause != null && !this.whereClause.equals("")
                && !this.whereClause.equalsIgnoreCase(MappingConstants.NONE))
        {
            buffer.append(MappingConstants.WHERE);
            buffer.append(this.whereClause);
           // buffer.append(MappingConstants.FOR_READ_ONLY);
        }
        else
        {
            //buffer.append(MappingConstants.FOR_READ_ONLY);
        }

        this.queryString = buffer.toString();
    }

    public FieldMappingProperties getErrorFieldMappingProperties()
    {
        return fProps;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FB02BB
     */
    public String getEventName()
    {
        return this.eventName;
    }

    /**
     * @return Returns the queryKey.
     */
    public String getQueryKey()
    {
        return queryKey;
    }

    /**
     * @return Returns the queryString.
     */
    public String getQueryString()
    {
        return queryString;
    }

    /**
     * @return Returns the queryType.
     */
    public int getQueryType()
    {
        return queryType;
    }

    /**
     * @return
     */
    public String getWhereClauseGenerator()
    {
        return this.whereClauseGenerator;
    }

    /**
     * @return
     */
    public String getWhereClauseGeneratorMethod()
    {
        return this.whereClauseGeneratorMethod;
    }

    public boolean hasWhereClauseGenerator()
    {
        return this.whereClauseGeneratorExists;
    }

    public boolean isFileIOMapping()
    {
        return MappingConstants.FILE_IO_MAPPING.equals(this.getMappingClassName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.config.CallbackProperties#isOptimized()
     */
    public boolean isOptimized()
    {
        return optimized;
    }

    /**
     * @return Returns the retrieveDynamicMethod.
     */
    public boolean isRetrieveDynamicMethod()
    {
        return retrieveDynamicMethod;
    }

    /**
     * @return Returns the retrieveMethod.
     */
    public boolean isRetrieveMethod()
    {
        return retrieveMethod;
    }

    public void optimize()
    {
        // TODO utilize fieldProperties array when available
        EntityMappingProperties eProps = super.getParent();
        boolean noValidation = eProps.isBufferMapping() || eProps.isReportMapping() || this.isFileIOMapping();

        if (noValidation == false)
        {
            // set queryType constant
            this.optimizeQueryType();
            this.optimizeRetrieveMethod();

            try
            {

                // *** fields optimization
                Class clazz = Class.forName(this.parent.getEntity());

                List fields = this.getFieldsList();
                int len = fields.size();
                for (int i = 0; i < len; i++)
                {
                    this.fProps = (FieldMappingProperties) fields.get(i);

                    // convert property type to constant found in naming.MappingConstants
                    this.fProps.optimizeDataType(); // convert propertyType to int
                    this.fProps.optimizeDataItem(); // trim whitespace
                    this.fProps.optimizeReflection(clazz); // cache property methods on Field Props
                    this.fProps.optimizeFormatting();
                }

                // sort fields by index (parmIndex or position)
                Collections.sort(fields, new FieldMappingProperties.Sorter());

                // fields array optimization
                super.fieldsArray = new FieldMappingProperties[fields.size()];
                super.computeArray(fields, fieldsArray);

                // *** parms optimization
                clazz = Class.forName(this.eventName);
                List parms = this.getParms();
                Collections.sort(parms, new FieldMappingProperties.Sorter());
                len = parms.size();
                for (int i = 0; i < len; i++)
                {
                    this.fProps = (FieldMappingProperties) parms.get(i);

                    // convert property type to constant found in naming.MappingConstants
                    this.fProps.optimizeDataType(); // convert propertyType to int
                    this.fProps.optimizeDataItem(); // trim whitespace
                    this.fProps.optimizeReflection(clazz); // cache property methods on Parm Props
                    this.fProps.optimizeFormatting();

                    // index is optimized when fieldMapping is added to the callback
                }
            }
            catch (ClassNotFoundException e)
            {
                throw new LoadMappingPropertyException(e.getMessage(), e);
            }

            //if (super.connectionName.startsWith(MappingConstants.JDBC))
            //{
            // sort JDBC parms by index (parmIndex)
            Collections.sort(parms, new FieldMappingProperties.Sorter());
            //}

            // parms array optimization
            super.parmsArray = new ParmMappingProperties[parms.size()];
            super.computeArray(parms, parmsArray);                       

            this.queryKey = this.createKey(this.eventName);
        }

        // optimize whereClause
        super.optimizeWhereClause(); // trim whitespace, set none when appropriate

        this.optimizeWhereClauseGenerator(); // trim whitespace, set none when appropriate

        if (this.connectionName.startsWith("JDBC"))
        {
            this.buildJDBCQuery();
        }

        // only optimze if no exceptions are thrown
        this.optimized = true;
    }

    private void optimizeQueryType()
    {
        if ("mojo.km.persistence.OIDEvent".equals(this.eventName))
        {
            this.queryType = MappingConstants.OID_QUERY;
        }
        else if ("mojo.km.persistence.AttributeEvent".equals(this.eventName))
        {
            this.queryType = MappingConstants.ATTRIBUTE_QUERY;
        }
        else if ("mojo.km.persistence.AllQueryEvent".equals(this.eventName))
        {
            this.queryType = MappingConstants.ALL_QUERY;
        }
        else
        {
            this.queryType = MappingConstants.EVENT_QUERY;
        }
    }

    /**
     *  
     */
    private void optimizeRetrieveMethod()
    {
        if (MappingConstants.RETRIEVE_METHOD_NAME.equals(this.mappingMethodName))
        {
            this.retrieveMethod = true;
        }
        else if (MappingConstants.RETRIEVE_DYNAMIC_METHOD_NAME.equals(this.mappingMethodName))
        {
            this.retrieveDynamicMethod = true;
        }
    }

    public void optimizeWhereClauseGenerator()
    {
        if (whereClauseGenerator != null && whereClauseGeneratorMethod != null)
        {
            this.whereClauseGenerator = this.whereClauseGenerator.trim();
            this.whereClauseGeneratorMethod = this.whereClauseGeneratorMethod.trim();

            this.whereClauseGeneratorExists = (whereClauseGenerator.equals("") == false && whereClauseGeneratorMethod
                    .equals("") == false);
        }

    }

    /**
     * @param eventName
     * @roseuid 4057237300FF
     */
    public void refreshMapping(String eventName)
    {

    }

    /**
     * @param eventName
     * @modelguid {941793C5-C4D5-4865-99C4-2354385886A6}
     * @roseuid 404DA1FB02B1
     */
    public void setEventName(String anEventName)
    {
        this.eventName = anEventName;
    }

    /**
     * @param string
     */
    public void setWhereClauseGenerator(String aWhereClauseGenerator)
    {
        this.whereClauseGenerator = aWhereClauseGenerator;
    }

    /**
     * @param string
     */
    public void setWhereClauseGeneratorMethod(String aWhereClauseGeneratorMethod)
    {
        this.whereClauseGeneratorMethod = aWhereClauseGeneratorMethod;
    }

    public String toString()
    {
        return super.toString() + "\n" + this.getEventName();
    }
}
