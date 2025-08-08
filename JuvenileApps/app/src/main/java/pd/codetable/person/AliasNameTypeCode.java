package pd.codetable.person;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class AliasNameTypeCode extends PersistentObject 
{
    private String code;

    private String description;

    private String inactiveInd;

    private String rectype;
    

    /**
     * @roseuid 4276954B005D
     */
    public AliasNameTypeCode()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.codetable.ICodetable#findAll()
     */
    static public Iterator findAll()
    {
        return new Home().findAll(AliasNameTypeCode.class);
    }
    
    /* (non-Javadoc)
	 * @see pd.codetable.ICodetable#find(java.lang.String)
	 */
	static public AliasNameTypeCode find(String oid) {
		return (AliasNameTypeCode) new Home().find(oid, AliasNameTypeCode.class);
	}
	
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, AliasNameTypeCode.class);
	}
	
	public static AliasNameTypeCode find(String attributeName, String attributeValue)
	{
		AliasNameTypeCode juvLU = null;
		IHome home = new Home();
		juvLU = (AliasNameTypeCode) home.find(attributeName, attributeValue, AliasNameTypeCode.class);
		return juvLU;
	}

    /**
     * @return
     */
    public String getCode()
    {
        fetch();
        return code;
    }

    /**
     * @return
     */
    public String getDescription()
    {
        fetch();
        return description;
    }

    /**
     * @return
     */
    public String getInactiveInd()
    {
        fetch();
        return inactiveInd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.codetable.ICodetable#inActivate()
     */
    public void inActivate()
    {
        // TODO Auto-generated method stub

    }

    /**
     * @param theLevel
     */
    public void setCode(String theCode)
    {
        if (this.code == null || !this.code.equals(theCode))
        {
            markModified();
        }
        code = theCode;
    }

    /**
     * @param theLevelDescription
     */
    public void setDescription(String theLevelDescription)
    {
        if (this.description == null || !this.description.equals(theLevelDescription))
        {
            markModified();
        }
        description = theLevelDescription;
    }

    /**
     * @param theUnit
     */
    public void setInactiveInd(String theUnit)
    {
        if (this.inactiveInd == null || !this.inactiveInd.equals(theUnit))
        {
            markModified();
        }
        inactiveInd = theUnit;
    }

    public String getRectype()
    {
	fetch();
	return rectype;
    }

    public void setRectype(String rectype)
    {
	if (this.rectype == null || !this.rectype.equals(rectype))
        {
            markModified();
        }
	this.rectype = rectype;
    }
    
    
}
