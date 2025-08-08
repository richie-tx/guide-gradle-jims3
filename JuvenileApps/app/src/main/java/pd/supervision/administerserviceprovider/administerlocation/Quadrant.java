package pd.supervision.administerserviceprovider.administerlocation;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

/**
* @roseuid 447357C70049
*/
public class Quadrant extends PersistentObject {
	private String zipCode;
	private String quadrantDesc;
	private String region;
	private int organizationId;
	private String defendantId;	
	private String quadrantCd;

    
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getQuadrantDesc() {
		return quadrantDesc;
	}

	public void setQuadrantDesc(String quadrantDesc) {
		this.quadrantDesc = quadrantDesc;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}	

	public String getQuadrantCd() {
		return quadrantCd;
	}

	public void setQuadrantCd(String quadrantCd) {
		this.quadrantCd = quadrantCd;
	}

	/**
	 * @param anEvent
	 * @return
	 */
	static public List findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, Quadrant.class);
		return CollectionUtil.iteratorToList(iter);
	}
	
	/**
	 * @param anEvent
	 * @return
	 */
	static public List findAll(String attrName, String attrVal){
		IHome home = new Home();
		Iterator iter = home.findAll(attrName, attrVal, Quadrant.class);
		return CollectionUtil.iteratorToList(iter);
	}	
}