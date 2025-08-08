package pd.juvenile.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.SearchJuvenileCasefileListEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.juvenilecase.JuvenileCasefile;

/**
 * Class SearchJuvenileProfileCasefileListCommand.
 * 
 * @author Anand Thorat
 */
public class SearchJuvenileCasefileListCommand implements mojo.km.context.ICommand
{
    /**
     * @param event
     * @roseuid 42B888920234
     */
    public void execute(IEvent event)
    {
	SearchJuvenileCasefileListEvent searchEvent = (SearchJuvenileCasefileListEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	String juvenileId = searchEvent.getJuvenileId();
	Iterator iter = JuvenileCasefile.findAll("juvenileId", juvenileId);
	while (iter.hasNext())
	{
	    JuvenileCasefile juvenileCasefile = (JuvenileCasefile) iter.next();
	    JuvenileProfileCasefileListResponseEvent response = getResponseEvent(juvenileCasefile);
	    dispatch.postEvent(response);
	}

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.execute

    /**
     * @param event
     * @roseuid 42B888920242
     */
    public void onRegister(IEvent event)
    {

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.onRegister

    /**
     * @param event
     * @roseuid 42B888920244
     */
    public void onUnregister(IEvent event)
    {

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.onUnregister

    /**
     * @param updateObject
     * @roseuid 42B88AD40261
     */
    public void update(Object updateObject)
    {

    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.update

    /**
     * @param juvenileCasefile
     *            The juvenile casefile.
     * @return The response event.
     */
    private JuvenileProfileCasefileListResponseEvent getResponseEvent(JuvenileCasefile juvenileCasefile)
    {
	JuvenileProfileCasefileListResponseEvent event = new JuvenileProfileCasefileListResponseEvent();
	event.setSupervisionNum(juvenileCasefile.getOID());
	event.setSequenceNum(juvenileCasefile.getSequenceNumber());
	event.setActivationDate(getFormatedDate(juvenileCasefile.getActivationDate()));
	event.setActivationDateDt(juvenileCasefile.getActivationDate());
	//event.setSupervisionEndDate(getFormatedDate(juvenileCasefile.getSupervisionEndDate()));
	event.setProbationOfficer(juvenileCasefile.getProbationOfficerFullName());
	event.setProbationOfficerId(juvenileCasefile.getProbationOfficerId());
	Code supervisionType = juvenileCasefile.getSupervisionType();
	event.setSupervisionType(supervisionType.getDescription());
	event.setSupervisionCategory(juvenileCasefile.getSupervisionCategoryId());
	event.setSupervisionTypeId(juvenileCasefile.getSupervisionTypeId()); //Added for Task 37996; User story 11077
	Code status = juvenileCasefile.getCaseStatus();
	String controllingRef = juvenileCasefile.getControllingReferral();
	if (controllingRef != null)
	{
	    event.setControllingReferralId(juvenileCasefile.getControllingReferral());
	}
	String controllingRefId = juvenileCasefile.getCasefileControllingReferralId();
	if (controllingRefId != null)
	{
	    event.setControllingReferralId(juvenileCasefile.getCasefileControllingReferralId());
	}
	event.setSupervisionOutcomeDescriptionId(juvenileCasefile.getSupervisionOutcomeDescriptionId());
	event.setSupervisionOutcome(juvenileCasefile.getSupervisionOutcome());
	if (status != null)
	{
	    event.setCaseStatus(status.getDescription());
	}

	return event;
    }

    /**
     * @param date
     *            The date.
     * @return The formated date.
     */
    private String getFormatedDate(Date date)
    {
	String strDate = "";
	if (date != null)
	{
	    strDate = DateUtil.dateToString(date, "MM/dd/yyyy");
	}
	return strDate;
    } //end of pd.juvenile.transactions.SearchJuvenileProfileCasefileListCommand.getFormatedDate	

    /*
     * given a string, return true it it's not null and not empty
     * @param str
     * @return
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.trim().length() > 0));
    }

} // end SearchJuvenileProfileCasefileListCommand
