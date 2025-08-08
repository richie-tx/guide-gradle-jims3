/*
 * Created on March 26, 2018, NM
 * District Court Conversion
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author nemathew
 *
 */
public class JPOofRecSupervisionTypeCategoryResponseEvent extends ResponseEvent 
{
	private String jpo;
	private String jpoId;
	private String supervisionTypeId;
	private String supervisionType;
	private String supervisionCategoryId;
	private String supervisionCategory;
	private String supervisionNum;
	
	/**
	 * @return
	 */
	public String getJpo() {
		return jpo;
	}
	/**
	 * @param jpo
	 */
	public void setJpo(String jpo) {
		this.jpo = jpo;
	}
	/**
	 * @return
	 */
	public String getJpoId() {
		return jpoId;
	}
	/**
	 * @param jpoId
	 */
	public void setJpoId(String jpoId) {
		this.jpoId = jpoId;
	}
	/**
	 * @return
	 */
	public String getSupervisionTypeId() {
		return supervisionTypeId;
	}
	/**
	 * @param supervisionTypeId
	 */
	public void setSupervisionTypeId(String supervisionTypeId) {
		this.supervisionTypeId = supervisionTypeId;
	}
	/**
	 * @return
	 */
	public String getSupervisionType() {
		return supervisionType;
	}
	/**
	 * @param supervisionType
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}
	/**
	 * @return
	 */
	public String getSupervisionCategoryId() {
		return supervisionCategoryId;
	}
	/**
	 * @param supervisionCategoryId
	 */
	public void setSupervisionCategoryId(String supervisionCategoryId) {
		this.supervisionCategoryId = supervisionCategoryId;
	}
	/**
	 * @return
	 */
	public String getSupervisionCategory() {
		return supervisionCategory;
	}
	/**
	 * @param supervisionCategory
	 */
	public void setSupervisionCategory(String supervisionCategory) {
		this.supervisionCategory = supervisionCategory;
	}
	/**
	 * @return the supervisionNum
	 */
	public String getSupervisionNum()
	{
	    return supervisionNum;
	}
	/**
	 * @param supervisionNum the supervisionNum to set
	 */
	public void setSupervisionNum(String supervisionNum)
	{
	    this.supervisionNum = supervisionNum;
	}
	
	


}