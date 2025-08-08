/*
 * Created on Dec 4, 2007
 */
package pd.supervision.administercaseload;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author mchowdhury
 */
public class SuperviseeZipCode extends PersistentObject {

    private String defendantId;
    private String zipCode;
    private int organizationId;
    private String programUnitDesction;
    private int parentId;
	
	/**
	 * @param defendantId
	 * @return
	 */
	public static Iterator findAll(String attrName, String attrVal) {
		return new Home().findAll(attrName, attrVal, SuperviseeZipCode.class);
	}

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getProgramUnitDesction() {
		return programUnitDesction;
	}

	public void setProgramUnitDesction(String programUnitDesction) {
		this.programUnitDesction = programUnitDesction;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
