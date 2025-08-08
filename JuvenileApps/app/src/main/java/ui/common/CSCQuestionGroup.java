/*
 * Created on Feb 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;

import java.util.Collection;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCQuestionGroup implements Comparable
{	
	private String groupId="0";
	private String groupText="";
	private boolean isGroupTextDetailHeader=false;
	private String groupTextAlign="";
//	 the order within the group the question is sequenced
	private int groupSequence=0;
	private Collection questions=null;
	private boolean isValidateEachQuesGroup = true;
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * return -1 if this object is less than the incoming object
	 * returns 1 if this object is greater than the incoming object
	 * returns 0 if this object is equal to the incoming object
	 */
	public int compareTo(Object aIncomingObj)
	{
		if(aIncomingObj==null)
			return 1;
		CSCQuestionGroup incomingGroup=(CSCQuestionGroup)aIncomingObj;
		if(this.getGroupSequence()==incomingGroup.getGroupSequence())
			return 0;
		else if(this.getGroupSequence() < incomingGroup.getGroupSequence())
			return -1;
		else
			return 1;
	}

		

	/**
	 * @return Returns the isValidateEachQuesGroup.
	 */
	public boolean isValidateEachQuesGroup() {
		return isValidateEachQuesGroup;
	}
	/**
	 * @param isValidateEachQuesGroup The isValidateEachQuesGroup to set.
	 */
	public void setValidateEachQuesGroup(boolean isValidateEachQuesGroup) {
		this.isValidateEachQuesGroup = isValidateEachQuesGroup;
	}
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}	
/**
 * @return Returns the groupSequence.
 */
public int getGroupSequence() {
	return groupSequence;
}
/**
 * @param groupSequence The groupSequence to set.
 */
public void setGroupSequence(int groupSequence) {
	this.groupSequence = groupSequence;
}
	/**
	 * @return Returns the questions.
	 */
	public Collection getQuestions() {
		return questions;
	}
	/**
	 * @param questions The questions to set.
	 */
	public void setQuestions(Collection questions) {
		this.questions = questions;
	}

	/**
	 * @return Returns the groupText.
	 */
	public String getGroupText() {
		return groupText;
	}
	/**
	 * @param groupText The groupText to set.
	 */
	public void setGroupText(String groupText) {
		this.groupText = groupText;
	}
	/**
	 * @return Returns the groupTextAlign.
	 */
	public String getGroupTextAlign() {
		return groupTextAlign;
	}
	/**
	 * @param groupTextAlign The groupTextAlign to set.
	 */
	public void setGroupTextAlign(String groupTextAlign) {
		this.groupTextAlign = groupTextAlign;
	}
	/**
	 * @return Returns the isGroupTextDetailHeader.
	 */
	public boolean isGroupTextDetailHeader() {
		return isGroupTextDetailHeader;
	}
	/**
	 * @param isGroupTextDetailHeader The isGroupTextDetailHeader to set.
	 */
	public void setGroupTextDetailHeader(boolean isGroupTextDetailHeader) {
		this.isGroupTextDetailHeader = isGroupTextDetailHeader;
	}
}
