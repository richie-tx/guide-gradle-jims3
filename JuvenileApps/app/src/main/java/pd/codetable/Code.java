package pd.codetable;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;

public class Code extends PersistentObject implements ICode
{
    /**
     * @return pd.codetable.Code
     * @param event
     * @roseuid 4112973102E5
     */
    static public Code find(String codeTableName, String codeId)
    {
        Reference reference = new Reference(codeId, Code.class, codeTableName);
        Code code = (Code) reference.getObject();
        return code;
    }

    static public Collection findAll(String codeTableName)
    {
        Collection codes = new mojo.km.persistence.ArrayList(Code.class, "codeTableName", codeTableName,
                codeTableName);
        return codes;
    }

    public static Map getCodeMap(String codeTableName)
    {
        Collection codes = findAll(codeTableName);
        Map codeMap = new HashMap();
        if (codes != null)
        {
            Iterator i = codes.iterator();
            while (i.hasNext())
            {
                ICode code = (ICode) i.next();
                codeMap.put(code.getCode(), code);
            }
        }
        return codeMap;
    }

    public CodeResponseEvent valueObject(boolean thin)
    {
        CodeResponseEvent response = new CodeResponseEvent();
        if (thin)
        {
            response.setCodeId(this.getCodeId());
            response.setCode(this.getCode());
            response.setDescription(this.getDescription());
        }
        else
        {
            response.setCodeId(this.getCodeId());
            response.setActiveDate(this.getActiveDate());
            response.setCode(this.getCode());
            response.setCodeTableName(this.getCodeTableName());
            response.setDescription(this.getDescription());
            response.setInactiveDate(this.getInactiveDate());
            response.setInactiveEffectiveDate(this.getInactiveEffectiveDate());
            response.setStatus(this.getStatus());
        }
        return response;
    }

    public CodeResponseEvent valueObject(String topic, boolean thin)
    {
        CodeResponseEvent response = this.valueObject(thin);
        response.setTopic(topic);
        return response;
    }

    /**
     * @return pd.codetable.Code
     * @param codeId
     * @roseuid 410FA34B0329
     */
    static public Code find(String codeId)
    {
        Code code = null;
        IHome home = new Home();
        code = (Code) home.find(codeId, Code.class);
        return code;
    }

    private Date activeDate;

    private String code;

    /**
     * Properties for codeHistories
     * 
     * @referencedType pd.codetable.CodeHistory
     */
    private Collection codeHistories = null;

    /**
     * Properties for codeTable
     */
    private CodeTable codeTable = null;

    private String codeTableName;

    private String description;

    private Date inactiveDate;

    private Date inactiveEffectiveDate;

    private String status;

    /**
     * @roseuid 410FB81203D2
     */
    public Code()
    {
    }

    /**
     * Clears all pd.codetable.CodeHistory from class relationship collection.
     * 
     * @roseuid 410FB8AD0013
     */
    public void clearCodeHistories()
    {
        initCodeHistories();
        codeHistories.clear();
    }

    /**
     * Access method for the activeDate property.
     * 
     * @return the current value of the activeDate property
     */
    public Date getActiveDate()
    {
        fetch();
        return activeDate;
    }

    /**
     * Access method for the code property.
     * 
     * @return the current value of the code property
     */
    public String getCode()
    {
        fetch();
        return code;
    }

    /**
     * returns a collection of pd.codetable.CodeHistory
     * 
     * @return java.util.Collection
     * @roseuid 410FB8AC0383
     */
    public Collection getCodeHistories()
    {
        fetch();
        initCodeHistories();
        return codeHistories;
    }

    /**
     * Access method for the codeId property.
     * 
     * @return the current value of the codeId property
     */
    public String getCodeId()
    {
        return (String) super.getOID();
    }

    /**
     * Gets referenced type pd.codetable.CodeTable
     * 
     * @return CodeTable
     */
    public CodeTable getCodeTable()
    {
        initCodeTable();
        return codeTable;
    }

    /**
     * Access method for the codeTableName property.
     * 
     * @return the current value of the codeTableName property
     */
    public String getCodeTableName()
    {
        fetch();
        return codeTableName;
    }

    /**
     * Access method for the description property.
     * 
     * @return the current value of the description property
     */
    public String getDescription()
    {
        fetch();
        return description;
    }

    /**
     * Access method for the inactiveDate property.
     * 
     * @return the current value of the inactiveDate property
     */
    public Date getInactiveDate()
    {
        fetch();
        return inactiveDate;
    }

    /**
     * Access method for the inactiveEffectiveDate property.
     * 
     * @return the current value of the inactiveEffectiveDate property
     */
    public Date getInactiveEffectiveDate()
    {
        fetch();
        return inactiveEffectiveDate;
    }

    /**
     * Access method for the status property.
     * 
     * @return the current value of the status property
     */
    public String getStatus()
    {
        fetch();
        return status;
    }

    /**
     * Initialize class relationship implementation for pd.codetable.CodeHistory
     * 
     * @roseuid 410FB8AC036F
     */
    private void initCodeHistories()
    {
        if (codeHistories == null)
        {
            try
            {
                if (this.getOID() == null)
                {
                    new mojo.km.persistence.Home().bind(this);
                }
                codeHistories = new mojo.km.persistence.ArrayList(CodeHistory.class, "codeId", "" + getOID());
            } catch (Throwable t)
            {
                codeHistories = new java.util.ArrayList();
            }
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.CodeTable
     */
    private void initCodeTable()
    {
        if (codeTable == null)
        {
            if (codeTable.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(codeTable);
            }
            try
            {
                codeTable = (CodeTable) new mojo.km.persistence.Reference(codeTableName,
                        CodeTable.class).getObject();
            } catch (Throwable t)
            {
                codeTable = null;
            }
        }
    }

    /**
     * insert a pd.codetable.CodeHistory into class relationship collection.
     * 
     * @param anObject
     * @roseuid 410FB8AC0397
     */
    public void insertCodeHistories(CodeHistory anObject)
    {
        initCodeHistories();
        codeHistories.add(anObject);
    }

    /**
     * Removes a pd.codetable.CodeHistory from class relationship collection.
     * 
     * @param anObject
     * @roseuid 410FB8AC03C9
     */
    public void removeCodeHistories(CodeHistory anObject)
    {
        initCodeHistories();
        codeHistories.remove(anObject);
    }

    /**
     * Sets the value of the activeDate property.
     * 
     * @param aActiveDate
     *            the new value of the activeDate property
     */
    public void setActiveDate(Date aActiveDate)
    {
        if (this.activeDate == null || !this.activeDate.equals(aActiveDate))
        {
            markModified();
        }
        activeDate = aActiveDate;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param aCode
     *            the new value of the code property
     */
    public void setCode(String aCode)
    {
        if (this.code == null || !this.code.equals(aCode))
        {
            markModified();
        }
        code = aCode;
    }

    /**
     * Sets the value of the codeId property.
     * 
     * @param aCodeId
     *            the new value of the codeId property
     */
    public void setCodeId(String aCodeId)
    {
        setOID(aCodeId);
    }

    /**
     * set the type reference for class member codeTable
     */
    public void setCodeTable(CodeTable aCodeTable)
    {
        if (this.codeTable == null || !this.codeTable.equals(aCodeTable))
        {
            markModified();
        }
        if (aCodeTable.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(aCodeTable);
        }
        setCodeTableName("" + aCodeTable.getOID());
        this.codeTable = (CodeTable) new mojo.km.persistence.Reference((PersistentObject) aCodeTable).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.CodeTable
     */
    public void setCodeTableName(String aCodeTableName)
    {
        if (this.codeTableName == null || !this.codeTableName.equals(aCodeTableName))
        {
            markModified();
        }
        codeTable = null;
        this.codeTableName = aCodeTableName;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param aDescription
     *            the new value of the description property
     */
    public void setDescription(String aDescription)
    {
        if (this.description == null || !this.description.equals(aDescription))
        {
            markModified();
        }
        description = aDescription;
    }

    /**
     * Sets the value of the inactiveDate property.
     * 
     * @param aInactiveDate
     *            the new value of the inactiveDate property
     */
    public void setInactiveDate(Date aInactiveDate)
    {
        if (this.inactiveDate == null || !this.inactiveDate.equals(aInactiveDate))
        {
            markModified();
        }
        inactiveDate = aInactiveDate;
    }

    /**
     * Sets the value of the inactiveEffectiveDate property.
     * 
     * @param aInactiveEffectiveDate
     *            the new value of the inactiveEffectiveDate property
     */
    public void setInactiveEffectiveDate(Date aInactiveEffectiveDate)
    {
        if (this.inactiveEffectiveDate == null || !this.inactiveEffectiveDate.equals(aInactiveEffectiveDate))
        {
            markModified();
        }
        inactiveEffectiveDate = aInactiveEffectiveDate;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param aStatus
     *            the new value of the status property
     */
    public void setStatus(String aStatus)
    {
        if (this.status == null || !this.status.equals(aStatus))
        {
            markModified();
        }
        status = aStatus;
    }
}