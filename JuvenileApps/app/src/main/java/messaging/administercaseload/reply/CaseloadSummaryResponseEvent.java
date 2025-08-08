package messaging.administercaseload.reply;

import java.util.ArrayList;
import java.util.List;

import messaging.administercaseload.domintf.ICaseloadSummary;
import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 */
public class CaseloadSummaryResponseEvent extends ResponseEvent
{
    private List caseloads;
    
    public CaseloadSummaryResponseEvent()
    {
        this.caseloads = new ArrayList();
    }
    
    /**
     * @param summary
     */
    public void addCaseload(ICaseloadSummary aCaseload)
    {
        this.caseloads.add(aCaseload);
    }
    
    public List getCaseloads()
    {
        return this.caseloads;
    }

}
