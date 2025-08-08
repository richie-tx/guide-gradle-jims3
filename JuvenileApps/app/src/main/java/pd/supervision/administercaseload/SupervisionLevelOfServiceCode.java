/*
 * Created on Oct 26, 2007
 */
package pd.supervision.administercaseload;

import java.util.Iterator;
import java.util.List;

import pd.codetable.ICodetable;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;


/**
 * @author cc_rbhat
 */
public class SupervisionLevelOfServiceCode extends PersistentObject implements ICodetable  {
	
	private String levelOfServiceCode;
	
	private String levelOfServiceLegacyCode;
	
	private String description;
	
	private String cstsCode;

	/**
	 * 
	 */
	public SupervisionLevelOfServiceCode() {
	}
	
	public String getSupervisionLevelOfServiceCodeId() {
		return this.getOID().toString();
	}

	/**
	 * @return Returns the cstsCode.
	 */
	public String getCstsCode() {
        fetch();
		return cstsCode;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
        fetch();
		return description;
	}
	/**
	 * @return Returns the levelOfServiceCode.
	 */
	public String getLevelOfServiceCode() {
        fetch();
		return levelOfServiceCode;
	}
	/**
	 * @return Returns the levelOfServiceLegacyCode.
	 */
	public String getLevelOfServiceLegacyCode() {
        fetch();
		return levelOfServiceLegacyCode;
	}
	/**
	 * @param cstsCode The cstsCode to set.
	 */
	public void setCstsCode(String cstsCode) {
        if (this.cstsCode == null || !this.cstsCode.equals(cstsCode)) {
            markModified();
        }
		this.cstsCode = cstsCode;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
        if (this.description == null || !this.description.equals(description)) {
            markModified();
        }
		this.description = description;
	}
	/**
	 * @param levelOfServiceCode The levelOfServiceCode to set.
	 */
	public void setLevelOfServiceCode(String levelOfServiceCode) {
        if (this.levelOfServiceCode == null || !this.levelOfServiceCode.equals(levelOfServiceCode)) {
            markModified();
        }
		this.levelOfServiceCode = levelOfServiceCode;
	}
	/**
	 * @param levelOfServiceLegacyCode The levelOfServiceLegacyCode to set.
	 */
	public void setLevelOfServiceLegacyCode(String levelOfServiceLegacyCode) {
        if (this.levelOfServiceLegacyCode == null || !this.levelOfServiceLegacyCode.equals(levelOfServiceLegacyCode)) {
            markModified();
        }
		this.levelOfServiceLegacyCode = levelOfServiceLegacyCode;
	}
	
	static public SupervisionLevelOfServiceCode find(String oid) {
		IHome home = new Home();
		SupervisionLevelOfServiceCode code = (SupervisionLevelOfServiceCode) home.find(oid, SupervisionLevelOfServiceCode.class);
		return code;
	}

	static public SupervisionLevelOfServiceCode findByLevelOfService(String levelOfServiceCode) {
        IHome home = new Home();
        SupervisionLevelOfServiceCode code = (SupervisionLevelOfServiceCode) home.find("levelOfServiceCode", 
        		levelOfServiceCode, SupervisionLevelOfServiceCode.class);
		return code;
	}
	
	static public SupervisionLevelOfServiceCode findByLegacyLevelOfService(String legacyLOSCd) {
		List aList = CollectionUtil.iteratorToList( SupervisionLevelOfServiceCode.findAll("levelOfServiceLegacyCode", legacyLOSCd) );
		SupervisionLevelOfServiceCode code = (SupervisionLevelOfServiceCode) aList.get(0);
		return code;
	}
	
	/**
     * @return java.util.Iterator
     * @param attrName, attrValue
     * @roseuid removed findAll returning List rry
     */
    static public Iterator findAll(String attrName, String attrValue) {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, SupervisionLevelOfServiceCode.class);
    }
    
    /**
     * 
     */
	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(SupervisionLevelOfServiceCode.class);
		
	}

	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
}
