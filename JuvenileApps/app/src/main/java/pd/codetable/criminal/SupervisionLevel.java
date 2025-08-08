package pd.codetable.criminal;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @autor mchowdhury
 */
public class SupervisionLevel extends PersistentObject {
	private String name;
	private String parentId;
	private String riskPoints;
	private int groupId;
	

	/**
	 * @return Returns the groupId.
	 */
	public int getGroupId() {
		fetch();
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(int groupId) {
		if (this.groupId != groupId) {
			markModified();
		}
		this.groupId = groupId;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		fetch();
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		if (this.name != null && !this.name.equals(name)) {
			markModified();
		}
		this.name = name;
	}
	/**
	 * @return Returns the parentId.
	 */
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(String parentId) {
		if (this.parentId != null && !this.parentId.equals(parentId)) {
			markModified();
		}
		this.parentId = parentId;
	}
	/**
	 * @return Returns the riskPoints.
	 */
	public String getRiskPoints() {
		fetch();
		return riskPoints;
	}
	/**
	 * @param riskPoints The riskPoints to set.
	 */
	public void setRiskPoints(String riskPoints) {
		if (this.riskPoints != null && !this.riskPoints.equals(riskPoints)) {
			markModified();
		}
		this.riskPoints = riskPoints;
	}

	/**
	   * Finds all supervisionLevels by an attribute value
	   * @param attributeName
	   * @param attributeValue
	   * @return 
	   */
	   static public Iterator findAll(String attributeName, String attributeValue) {
		   IHome home = new Home();
		   return home.findAll(attributeName, attributeValue, SupervisionLevel.class);
	   }
	   
	   /**
	   * Finds all supervisionLevels
	   * @return 
	   */
	   static public Iterator findAll() {
		   return new Home().findAll(SupervisionLevel.class);
	   }
	   
	  /**
	   * Finds all supervisionLevels by an attribute value
	   * @param attributeName
	   * @param attributeValue
	   * @return 
	   */
	   static public Iterator findAll(IEvent event) {
		   IHome home = new Home();
		   return home.findAll(event, SupervisionLevel.class);
	   }
	   
	   /**
		* @return pd.codetable.criminal.SupervisionLevel
		* @param supervisionLevelId
		* @roseuid 4236ED9502A8
		*/
		static public SupervisionLevel find(String supervisionLevelId) {
			return (SupervisionLevel) new Home().find(supervisionLevelId, SupervisionLevel.class);
		}
		
		/**
		* Access method for the supervisionLevelId property.
		* @return the current value of the supervisionLevelId property
		*/
		public String getsupervisionLevelId()
		{
			return "" + getOID();
		}
}
