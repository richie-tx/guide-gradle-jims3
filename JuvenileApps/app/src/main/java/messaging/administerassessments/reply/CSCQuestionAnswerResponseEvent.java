/*
 * Created on Feb 13, 2008
  */
package messaging.administerassessments.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class CSCQuestionAnswerResponseEvent extends ResponseEvent {
	private String name;
	private String type;
	private String id;
	
	private Collection questionGroupResponseEvents;

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the questionGroupResponseEvents.
     */
    public Collection getQuestionGroupResponseEvents() {
        return questionGroupResponseEvents;
    }
    /**
     * @param questionGroupResponseEvents The questionGroupResponseEvents to set.
     */
    public void setQuestionGroupResponseEvents(
            Collection questionGroupResponseEvents) {
        this.questionGroupResponseEvents = questionGroupResponseEvents;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
}
