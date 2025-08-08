package pd.codetable.person;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileLevelUnitCode extends PersistentObject 
{
    private String level;

    private String levelDescription;

    private String unit;

    private String unitDescription;
    
    private String levelUnitCD;
    
    private String rectype;
    

    /**
     * @roseuid 4276954B005D
     */
    public JuvenileLevelUnitCode()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.codetable.ICodetable#findAll()
     */
    public Iterator findAll()
    {
        return new Home().findAll(JuvenileLevelUnitCode.class);
    }
    
    /* (non-Javadoc)
	 * @see pd.codetable.ICodetable#find(java.lang.String)
	 */
	static public JuvenileLevelUnitCode find(String oid) {
		return (JuvenileLevelUnitCode) new Home().find(oid, JuvenileLevelUnitCode.class);
	}
	
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileLevelUnitCode.class);
	}
	
	public static JuvenileLevelUnitCode find(String attributeName, String attributeValue)
	{
		JuvenileLevelUnitCode juvLU = null;
		IHome home = new Home();
		juvLU = (JuvenileLevelUnitCode) home.find(attributeName, attributeValue, JuvenileLevelUnitCode.class);
		return juvLU;
	}

    /**
     * @return
     */
    public String getLevel()
    {
        fetch();
        return level;
    }

    /**
     * @return
     */
    public String getLevelDescription()
    {
        fetch();
        return levelDescription;
    }

    /**
     * @return
     */
    public String getUnit()
    {
        fetch();
        return unit;
    }

    /**
     * @return
     */
    public String getUnitDescription()
    {
        fetch();
        return unitDescription;
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
    public void setLevel(String theLevel)
    {
        if (this.level == null || !this.level.equals(theLevel))
        {
            markModified();
        }
        level = theLevel;
    }

    /**
     * @param theLevelDescription
     */
    public void setLevelDescription(String theLevelDescription)
    {
        if (this.levelDescription == null || !this.levelDescription.equals(theLevelDescription))
        {
            markModified();
        }
        levelDescription = theLevelDescription;
    }

    /**
     * @param theUnit
     */
    public void setUnit(String theUnit)
    {
        if (this.unit == null || !this.unit.equals(theUnit))
        {
            markModified();
        }
        unit = theUnit;
    }

    /**
     * @param theUnitDescription
     */
    public void setUnitDescription(String theUnitDescription)
    {
        if (this.unitDescription == null || !this.unitDescription.equals(theUnitDescription))
        {
            markModified();
        }
        unitDescription = theUnitDescription;
    }

    public String getLevelUnitCD()
    {
        fetch();
	return levelUnitCD;
    }

    public void setLevelUnitCD(String levelUnitCD)
    {
	if (this.levelUnitCD == null || !this.levelUnitCD.equals(levelUnitCD))
        {
            markModified();
        }
	this.levelUnitCD = levelUnitCD;
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
