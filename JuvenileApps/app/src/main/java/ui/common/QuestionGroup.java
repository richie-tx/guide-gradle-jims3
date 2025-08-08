/*
 * Created on Dec 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import java.util.Collection;

import java.lang.Comparable;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuestionGroup implements Comparable
{

	private String groupName="";
	private String groupId="0";
	// the order within the group the question is sequenced
	private int groupSequence=0;
	private Collection questions=null;
	private String dependsOnQuestionId="";
	private String dependsOnResponseId="";

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
		QuestionGroup incomingGroup=(QuestionGroup)aIncomingObj;
		if(this.getGroupSequence()==incomingGroup.getGroupSequence())
			return 0;
		else if(this.getGroupSequence() < incomingGroup.getGroupSequence())
			return -1;
		else
			return 1;
	}

	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * @return
	 */
	public String getGroupName()
	{
		return groupName;
	}

	/**
	 * @return
	 */
	public int getGroupSequence()
	{
		return groupSequence;
	}

	/**
	 * @param i
	 */
	public void setGroupId(String i)
	{
		groupId = i;
	}

	/**
	 * @param string
	 */
	public void setGroupName(String string)
	{
		groupName = string;
	}

	/**
	 * @param i
	 */
	public void setGroupSequence(int i)
	{
		groupSequence = i;
	}

	/**
	 * @return
	 */
	public Collection getQuestions()
	{
		return questions;
	}

	/**
	 * @param collection
	 */
	public void setQuestions(Collection collection)
	{
		questions = collection;
	}

	/**
	 * @return
	 */
	public String getDependsOnQuestionId()
	{
		return dependsOnQuestionId;
	}

	/**
	 * @return
	 */
	public String getDependsOnResponseId()
	{
		return dependsOnResponseId;
	}

	/**
	 * @param string
	 */
	public void setDependsOnQuestionId(String string)
	{
		dependsOnQuestionId = string;
	}

	/**
	 * @param string
	 */
	public void setDependsOnResponseId(String string)
	{
		dependsOnResponseId = string;
	}

}
