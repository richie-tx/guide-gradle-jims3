package pd.juvenilecase.family;

import pd.codetable.Code;

/**
* Set the reference value to class :: pd.codetable.Code
*/
public class BenefitsAssessmentSourceCode extends mojo.km.persistence.PersistentObject 
{
	private static String codeTableName = "BENEFITS_ASSESSMENT_SOURCE";
	/**
	* Properties for parent
	*/
	BenefitsAssessment parent = null;
	private String childId;
	Code child;
	private String parentId;
	
	/**
	 * Code description.
	 * 
	 */
	private String description;

	
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		
		this.childId = childId;
		initChild();
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initChild() {
		if (child == null) {
			try {
				child = Code.find( codeTableName, childId );
				description = child.getDescription();
			} catch (Throwable t) {
				child = null;
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getChild() {
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(Code child) {
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent =
					(BenefitsAssessment) new mojo.km.persistence.Reference(getParentId(), BenefitsAssessment.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.Condition
	*/
	public BenefitsAssessment getParent() {
		initParent();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(BenefitsAssessment parent) {
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (BenefitsAssessment) new mojo.km.persistence.Reference(parent).getObject();
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		fetch();
		return description;
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
}
