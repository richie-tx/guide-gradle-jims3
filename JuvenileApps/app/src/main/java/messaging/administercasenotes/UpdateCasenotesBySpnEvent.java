/*
 * Created on Nov 15, 2006
 *
 */
package messaging.administercasenotes;

import java.util.List;

import mojo.km.messaging.PersistentEvent;

/**
 * @author ryoung
 *  
 */
public class UpdateCasenotesBySpnEvent extends PersistentEvent {
    
    private String casenoteId;
    private String superviseeId;
    private List casenotes;
    /**
     * @return Returns the casenoteId.
     */
    public String getCasenoteId() {
        return casenoteId;
    }

    /**
     * @param casenoteId
     *            The casenoteId to set.
     */
    public void setCasenoteId(String casenoteId) {
        this.casenoteId = casenoteId;
    }
    /**
     * @return Returns the casenotes.
     */
    public List getCasenotes()
    {
        return casenotes;
    }
    /**
     * @param casenotes The casenotes to set.
     */
    public void setCasenotes(List casenotes)
    {
        this.casenotes = casenotes;
    }
    /**
     * @return Returns the superviseeId.
     */
    public String getSuperviseeId()
    {
        return superviseeId;
    }
    /**
     * @param superviseeId The superviseeId to set.
     */
    public void setSuperviseeId(String superviseeId)
    {
        this.superviseeId = superviseeId;
    }
}
