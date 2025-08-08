package mojo.km.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import mojo.km.naming.MappingConstants;
import mojo.tools.code.KeyWord;

/**
 * Responsible for handling properties related to persistent events.
 * 
 * @author Eric A Amundson, Jim Fisher
 */
public abstract class CallbackProperties extends GenericProperties
{
    public static final String CICSPROGRAMNAME = "cicsProgramName";

    public static final String CONNECTIONNAME = "connectionName";

    public static final String FILENAME = "fileName";

    private static String[] INVALID_ACCESSORS = { "Function", "hashCode", "getContextKey", "getEXP", "getLockedByUser",
            "getRules", "getSID", "getSTP", "getUID", "getUserID", "getWorkflowID", "isComposed", "isDeleted", "isDirty", "isNew" };

    private static String[] INVALID_MUTATORS = { "Function", "setDeleted", "setDirty", "setEXP", "setIsComposed", "setModified",
            "setNotNew", "setSID", "setSTP", "setTransient", "setUID", "setUserID", "setWorkflowID" };

    public static final String MAPPINGCLASSNAME = "mappingClassName";

    public static final String MAPPINGCOMPLETE = "mappingComplete";

    public static final String MAPPINGMETHODNAME = "mappingMethodName";

    public static final String NAME = "name";

    public static final String SEQUENCENUMBER = "sequenceNumber";

    public static final String SOURCE = "source";

    public static final String STOREDPROCEDURENAME = "storedProcedureName";

    public static final String SUBCLASSRECORD = "subclassRecord";

    public static final String SUBCLASSSEQUENCE = "subclassSequence";

    public static final String TRANID = "tranId";

    static
    {
        Arrays.sort(INVALID_ACCESSORS);
        Arrays.sort(INVALID_MUTATORS);
    }

    protected String cicsProgramName;

    protected String connectionName;

    protected HashMap fieldMaps;

    protected HashMap fieldParmIndexMaps;

    protected HashMap fieldPositionMaps;

    protected List fields;

    protected FieldMappingProperties[] fieldsArray;

    protected String fileName;

    protected String mappingClassName;

    protected String mappingComplete;

    protected String mappingMethodName;

    protected String name;

    protected EntityMappingProperties parent;

    protected HashMap parmMaps;

    protected List parms;

    protected ParmMappingProperties[] parmsArray;

    protected String sequenceNumber;

    protected String source;

    protected String storedProcedureName;

    protected String subclassRecord;

    protected String subclassSequence;

    protected String tranId;

    protected String whereClause;

    private boolean whereExists;

    public CallbackProperties()
    {
        this.fieldMaps = new HashMap();
        this.parmMaps = new HashMap();
        this.fieldParmIndexMaps = new HashMap();
        this.fieldPositionMaps = new HashMap();
        this.parms = new ArrayList();
        this.fields = new ArrayList();
    }

    /**
     * @param fProps
     * @roseuid 404DA1FA00CE
     */
    public void addFieldMap(FieldMappingProperties fProps)
    {
        fProps.optimizeIndex(this.connectionName);

        if (fProps.getIndex() != 0 || this.connectionName.startsWith("JDBC") == false)
        {
            this.fieldMaps.put(fProps.getPropertyName(), fProps);
            this.fieldPositionMaps.put(fProps.getPosition(), fProps);
            this.fieldParmIndexMaps.put(fProps.getParmIndex(), fProps);
            this.fields.add(fProps);
        }
    }

    /**
     * @param parmProps
     * @roseuid 404DA1FA00B0
     */
    public void addParmMapping(ParmMappingProperties pProps)
    {
        parmMaps.put(pProps.getPropertyName(), pProps);
        pProps.optimizeIndex(this.connectionName);
        this.parms.add(pProps);
    }

    /**
     * 
     * @param aMethodName
     * @return true if the methodName is not valid
     */
    @SuppressWarnings("unused")
	private boolean checkInvalidAccessor(String aMethodName)
    {
        boolean invalid = false;
        try
        {
            invalid = Arrays.binarySearch(INVALID_ACCESSORS, aMethodName) > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return invalid;
    }

    /**
     * @param methodName
     * @return
     */
    @SuppressWarnings("unused")
	private boolean checkInvalidMutator(String aMethodName)
    {
        return Arrays.binarySearch(INVALID_MUTATORS, aMethodName) < 0;
    }

    /**
     * @param aFields
     * @param aFieldsArray
     */
    public void computeArray(List aFields, FieldMappingProperties[] aFieldsArray)
    {
        int len = aFields.size();
        if (len != aFieldsArray.length)
        {
            throw new LoadMappingPropertyException("Field indicies are not valid: fields.size(): " + aFields.size()
                    + " fieldsArray: " + aFieldsArray);
        }
        for (int i = 0; i < len; i++)
        {
            aFieldsArray[i] = (FieldMappingProperties) aFields.get(i);
        }
    }

    /**
     * @param methodName
     * @return
     */
    private String extractPropertyName(String methodName)
    {
        String propertyName = null;
        if (methodName.equals("getOID") || methodName.equals("setOID"))
        {
            propertyName = "OID";
        }
        else if (methodName.startsWith("get") || methodName.startsWith("has") || methodName.startsWith("set"))
        {
        	StringBuilder buffer = new StringBuilder(methodName.substring(3));
            char ch = Character.toLowerCase(buffer.charAt(0));
            buffer.setCharAt(0, ch);
            propertyName = buffer.toString();
        }
        else if (methodName.startsWith("is"))
        {
        	StringBuilder buffer = new StringBuilder(methodName.substring(2));
            char ch = Character.toLowerCase(buffer.charAt(0));
            buffer.setCharAt(0, ch);
            propertyName = buffer.toString();
        }

        return propertyName;
    }

    public String getCicsProgramName()
    {
        return this.cicsProgramName;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA00A8
     */
    public String getConnectionName()
    {
        return this.connectionName;
    }

    abstract public FieldMappingProperties getErrorFieldMappingProperties();

    public FieldMappingProperties getFieldByParmIndexMap(String parmIndex)
    {
        return (FieldMappingProperties) fieldParmIndexMaps.get(parmIndex);
    }

    public FieldMappingProperties getFieldByPositionMap(String position)
    {
        return (FieldMappingProperties) fieldPositionMaps.get(position);
    }

    public int getFieldCount()
    {
        int size = 0;
        if (fieldMaps != null)
        {
            size = fieldMaps.size();
        }
        return size;
    }

    /**
     * @param pName
     * @return mojo.km.config.FieldMappingProperties
     * @roseuid 404DA1FA00D9
     */
    public FieldMappingProperties getFieldMap(String pName)
    {
        return (FieldMappingProperties) fieldMaps.get(pName);
    }

    public List getFields()
    {
        return this.fields;
    }

    /**
     * @return Returns the fieldsArray.
     */
    public FieldMappingProperties[] getFieldsArray()
    {
        return fieldsArray;
    }

    /**
     * @return Iterator
     * @roseuid 404DA1FA0115
     */
    public Iterator getFieldsIterator()
    {
        return fieldMaps.values().iterator();
    }

    public List getFieldsList()
    {
        return this.fields;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA00ED
     */
    public String getMappingClassName()
    {
        return this.mappingClassName;
    }

    public String getMappingComplete()
    {
        return this.mappingComplete;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA0100
     */
    public String getMappingMethodName()
    {
        return this.mappingMethodName;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * @return Returns the parentClass.
     */
    public EntityMappingProperties getParent()
    {
        return parent;
    }

    public List getParmList()
    {
        return this.parms;
    }

    /**
     * @param pName
     * @return mojo.km.config.ParmMappingProperties
     * @roseuid 404DA1FA00C4
     */
    public ParmMappingProperties getParmMapping(String pName)
    {
        return (ParmMappingProperties) parmMaps.get(pName);
    }

    public List getParms()
    {
        return this.parms;
    }

    /**
     * @return Returns the parmsArray.
     */
    public ParmMappingProperties[] getParmsArray()
    {
        return parmsArray;
    }

    /**
     * @return Iterator
     * @roseuid 404DA1FA0114
     */
    public Iterator getParmsIterator()
    {
        return parmMaps.values().iterator();
    }

    /**
     * @return
     */
    public String getSequenceNumber()
    {
        return this.sequenceNumber;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA010B
     */
    public String getSource()
    {
        return this.source;
    }

    public String getStoredProcedureName()
    {
        return this.storedProcedureName;
    }

    public String getSubclassRecord()
    {
        return this.subclassRecord;
    }

    public String getSubclassSequence()
    {
        return this.subclassSequence;
    }

    public String getTranId()
    {
        return this.tranId;
    }

    /**
     * @return Returns the whereClause.
     */
    public String getWhereClause()
    {
        return whereClause;
    }

    public boolean hasWhere()
    {
        return whereExists;
    }

    protected abstract boolean isOptimized();

    protected abstract void optimize() throws LoadMappingPropertyException;

    /**
     *  
     */
    public void optimizeReflectionBackup(HashMap fPropsMap, Class aClass)
    {
        StringBuilder missingAccessors = new StringBuilder();
        StringBuilder missingMutators = new StringBuilder();

        Method[] methods = aClass.getMethods();

        for (int i = 0; i < methods.length; i++)
        {
            Method method = methods[i];
            String methodName = method.getName();

            // find accessors
            int accessorIndex = Arrays.binarySearch(INVALID_ACCESSORS, methodName);
            int mutatorIndex = Arrays.binarySearch(INVALID_MUTATORS, methodName);
            if (accessorIndex < 0
                    && (methodName.startsWith("get") || methodName.startsWith("is") || methodName.startsWith("has")))
            {
                String propertyName = this.extractPropertyName(methodName);
                FieldMappingProperties fProps = (FieldMappingProperties) fPropsMap.get(propertyName);
                if (fProps != null)
                {
                    fProps.setAccessor(method);
                }
            }
            else if (mutatorIndex < 0 && methodName.startsWith("set"))
            {
                String propertyName = this.extractPropertyName(methodName);
                FieldMappingProperties fProps = (FieldMappingProperties) fPropsMap.get(propertyName);
                if (fProps != null)
                {
                    fProps.setMutator(method);
                }
            }
        }

        // check properties
        Iterator i = fPropsMap.values().iterator();
        while (i.hasNext())
        {
            FieldMappingProperties fProps = (FieldMappingProperties) i.next();
            if (fProps.getAccessor() == null)
            {
                missingAccessors.append("missing accessor: ");
                missingAccessors.append(fProps.toString());
                missingAccessors.append("\n");
            }

            if (fProps.getMutator() == null)
            {
                missingMutators.append("missing mutator: ");
                missingMutators.append(fProps.toString());
                missingMutators.append("\n");
            }
        }

        String reflectionError = missingAccessors.toString() + missingMutators.toString();

        if (reflectionError.length() > 0)
        {
            // TODO throw exception
            // throw new LoadMappingPropertyException(reflectionError.toString());
            System.err.println(this.toString() + "\n" + reflectionError);
        }
    }

    /**
     *  
     */
    public void optimizeWhereClause()
    {
        if (whereClause == null || whereClause.equals("") || whereClause.equalsIgnoreCase(MappingConstants.NONE))
        {
            this.whereClause = MappingConstants.NONE;
            this.whereExists = false;
        }
        else
        {
            this.whereClause = this.whereClause.trim();
            this.whereExists = true;
        }
    }

    public void setCicsProgramName(String aCicsProgramName)
    {
        this.cicsProgramName = aCicsProgramName;
    }

    /**
     * @param connectionName
     * @roseuid 404DA1FA00A6
     */
    public void setConnectionName(String aConnectionName)
    {
        this.connectionName = aConnectionName;
    }

    public void setFileName(String aFileName)
    {
        this.fileName = aFileName;
    }

    /**
     * @param aMappingClassName
     * @roseuid 404DA1FA00E3
     */
    public void setMappingClassName(String aMappingClassName)
    {
        this.mappingClassName = aMappingClassName;
    }

    public void setMappingComplete(String aMappingComplete)
    {
        this.mappingComplete = aMappingComplete;
    }

    /**
     * @param name
     * @roseuid 404DA1FA00F6
     */
    public void setMappingMethodName(String aMappingMethodName)
    {
        this.mappingMethodName = aMappingMethodName;
    }

    public void setName(String aName)
    {
        this.name = aName;
    }

    public void setParent(EntityMappingProperties aParent)
    {
        this.parent = aParent;
    }

    public void setSequenceNumber(String aSequenceNumber)
    {
        this.sequenceNumber = aSequenceNumber;
    }

    /**
     * query key format: "operation::entityClass::contextKey"
     * 
     * @param operation
     * @return
     */
    protected String createKey(String operation)
    {
    	StringBuilder buffer = new StringBuilder();
        buffer.append(operation);
        buffer.append(KeyWord.DOUBLE_COLON);
        buffer.append(this.parent.getEntity());
        buffer.append(KeyWord.DOUBLE_COLON);
        buffer.append(this.parent.getContextKey());
        return buffer.toString();
    }

    /**
     * @param sourceName
     * @roseuid 404DA1FA0101
     */
    public void setSource(String aSource)
    {
        this.source = aSource;
    }

    public void setStoredProcedureName(String aStoredProcedureName)
    {
        this.storedProcedureName = aStoredProcedureName;
    }

    public void setSubclassRecord(String aSubclassRecord)
    {
        this.subclassRecord = aSubclassRecord;
    }

    public void setSubclassSequence(String aSubclassSequence)
    {
        this.subclassSequence = aSubclassSequence;
    }

    public void setTranId(String aTranId)
    {
        this.tranId = aTranId;
    }

    /**
     * @param whereClause
     *            The whereClause to set.
     */
    public void setWhereClause(String whereClause)
    {
        this.whereClause = whereClause;
    }

    public String toString()
    {
    	StringBuilder buffer = new StringBuilder();
    	buffer.append("callbackName: ");
        buffer.append(this.name);
        buffer.append(" entity: ");
        buffer.append(this.parent.getEntity());
        buffer.append(" source: ");
        buffer.append(this.source);
        return buffer.toString();
    }
}
