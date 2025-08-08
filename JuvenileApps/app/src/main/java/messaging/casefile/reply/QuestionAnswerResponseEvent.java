/*
 * Created on July 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.casefile.reply; 

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 * Class holding data for each individual jsp.
 */
 
public class QuestionAnswerResponseEvent extends ResponseEvent{
	private String name;
	private String type;
	private String id;
	
	private Collection questionGroupResponseEvents;
	
	/**
	 * Consturctor
	 */
	public QuestionAnswerResponseEvent(){
	}
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string)
	{
		type = string;
	}

	/**
	 * @return
	 */
	public Collection getQuestionGroupResponseEvents()
	{
		return questionGroupResponseEvents;
	}

	/**
	 * @param collection
	 */
	public void setQuestionGroupResponseEvents(Collection collection)
	{
		questionGroupResponseEvents = collection;
	}

	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		id = string;
	}

	
}
