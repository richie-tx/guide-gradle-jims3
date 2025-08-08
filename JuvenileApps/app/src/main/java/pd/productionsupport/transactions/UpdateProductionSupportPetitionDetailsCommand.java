package pd.productionsupport.transactions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.UpdateProductionSupportPetitionDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.juvenilewarrant.JJSPetition;
import pd.security.PDSecurityHelper;

public class UpdateProductionSupportPetitionDetailsCommand implements ICommand
{
    public UpdateProductionSupportPetitionDetailsCommand()
    {
    }

    public void execute(IEvent event) throws Exception
    {

	UpdateProductionSupportPetitionDetailsEvent updateEvent = (UpdateProductionSupportPetitionDetailsEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (updateEvent.getOID() != null && StringUtils.isNotEmpty(updateEvent.getOID()))
	{
	    Iterator<JJSPetition> petitionIter = JJSPetition.findAll("OID", updateEvent.getOID());
	    if (petitionIter.hasNext())
	    {
		JJSPetition petitionDetail = (JJSPetition) petitionIter.next();

		petitionDetail.setStatus(nvl(updateEvent.getStatus()));
		petitionDetail.setType(nvl(updateEvent.getType()));
		petitionDetail.setPetitionDate(updateEvent.getPetitionDate());
		petitionDetail.setPetitionNum(updateEvent.getPetitionNum());
		petitionDetail.setReferralNum(updateEvent.getReferralNum());
		petitionDetail.setAmend(nvl(updateEvent.getAmend()));
		petitionDetail.setOffenseCodeId(nvl(updateEvent.getOffenseCodeId()));
		petitionDetail.setSeverity(nvl(updateEvent.getSeverity()));
		petitionDetail.setSequenceNum(updateEvent.getSequenceNum());
		petitionDetail.setSequenceNumber(updateEvent.getSequenceNumber());
		petitionDetail.setTerminationDate(updateEvent.getTerminationDate());
		petitionDetail.setTerminationCreateDate(updateEvent.getTerminationCreateDate());
		petitionDetail.setCJISNumber(nvl(updateEvent.getCJISNum()));
		petitionDetail.setDPSCode(nvl(updateEvent.getDPSCode()));
		petitionDetail.setLcUser(PDSecurityHelper.getLogonId());
		petitionDetail.setLcDate(DateUtil.getCurrentDate());
		petitionDetail.setLcTime(new SimpleDateFormat("HH.mm.ss").parse(toTime(Calendar.getInstance().getTime())));
		petitionDetail.setJuvenileNum(updateEvent.getJuvenileNum());
		IHome home = new Home();
		home.bind(petitionDetail);
		
		/*PetitionResponseEvent response = new PetitionResponseEvent();
		response.setPetitionStatus( petitionDetail.getStatus());
		dispatch.postEvent(response);*/
	    }

	}
	else
	{
	    ErrorResponseEvent error = new ErrorResponseEvent();
	    error.setMessage("Petition detail record is not found. Failed to update the petition detail record.");
	    dispatch.postEvent(error);
	}

    }

    public String nvl(String value)
    {
	return nvl(value, null);
    }

    public String nvl(String value, String alter)
    {
	return (value != null && value.length() > 0) ? value : alter;
    }

    public String toTime(Date time) throws Exception
    {
	return new SimpleDateFormat("HH.mm.ss").format(time);
    }

}
