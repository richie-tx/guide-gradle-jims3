/*
 * Created on Feb 13, 2008
 *
 */
package messaging.administerassessments.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class CSCQuestionGroupResponseEvent extends ResponseEvent 
{
	private String id;
	private String name;
	private String displayText;
	private boolean isDisplayTextDetailHeader;
	private String displayTextAlign;
	private int sequence;	
	private Collection questionResponseEvents;

	
	/**
	 * Consturctor
	 */
	public CSCQuestionGroupResponseEvent(){
	}


    /**
     * @return Returns the displayText.
     */
    public String getDisplayText() {
        return displayText;
    }
    /**
     * @return Returns the displayTextAlign.
     */
    public String getDisplayTextAlign() {
        return displayTextAlign;
    }
	
	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

	/**
	 * @return
	 */
	public Collection getQuestionResponseEvents()
	{
		return questionResponseEvents;
	}
	

	/**
	 * @return
	 */
	public int getSequence()
	{
		return sequence;
	}
    /**
     * @return Returns the isDisplayTextDetailHeader.
     */
    public boolean isDisplayTextDetailHeader() {
        return isDisplayTextDetailHeader;
    }
    /**
     * @param displayText The displayText to set.
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
    /**
     * @param displayTextAlign The displayTextAlign to set.
     */
    public void setDisplayTextAlign(String displayTextAlign) {
        this.displayTextAlign = displayTextAlign;
    }
    /**
     * @param isDisplayTextDetailHeader The isDisplayTextDetailHeader to set.
     */
    public void setDisplayTextDetailHeader(boolean isDisplayTextDetailHeader) {
        this.isDisplayTextDetailHeader = isDisplayTextDetailHeader;
    }

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		id = string;
	}
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * @param collection
	 */
	public void setQuestionResponseEvents(Collection collection)
	{
		questionResponseEvents = collection;
	}
	
	/**
	 * @param i
	 */
	public void setSequence(int i)
	{
		sequence = i;
	}
}
