package pd.productionsupport.transactions;

import java.util.Calendar;

import pd.juvenilecase.JJSJuvenile;
import pd.security.PDSecurityHelper;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileMasterEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.DateUtil;

public class UpdateProductionSupportJuvenileMasterCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	// TODO Auto-generated method stub
	UpdateProductionSupportJuvenileMasterEvent updateEvent = (UpdateProductionSupportJuvenileMasterEvent) event;
	JJSJuvenile juv = JJSJuvenile.find(updateEvent.getJuvenileId());

	if (juv != null)
	{	    
	   juv.setFirstName(updateEvent.getJuvenileFName());
	   juv.setLastName(updateEvent.getJuvenileLName());
	   juv.setMiddleName(updateEvent.getJuvenileMName());
	   juv.setBirthDate(updateEvent.getBirthDate());
	   juv.setRealDOB(updateEvent.getRealDOB());
	   juv.setRaceId(updateEvent.getRaceId());
	   juv.setOriginalRaceId(updateEvent.getOriginalRaceId());
	   juv.setSexId(updateEvent.getSexId());
	   juv.setLastReferralNum(updateEvent.getLastReferral());
	   juv.setJuvenileSsn(updateEvent.getJuvenileSsn());
	   juv.setOldStatusId(updateEvent.getMasterStatusId());
	   juv.setStatusId(updateEvent.getMasterStatusId());
	   juv.setCaseNotePart1(updateEvent.getCaseNotePart1());
	   juv.setCheckedOutDate(updateEvent.getCheckedOutDate());
	   juv.setCheckedOutTo(updateEvent.getCheckedOutTo());
	   juv.setDetentionFacilityId(updateEvent.getDetentionFacilityId());
	   juv.setDetentionStatusId(updateEvent.getDetentionStatusId());
	   juv.setPurgeDate(DateUtil.stringToDate(updateEvent.getPurgeDate(), DateUtil.DATE_FMT_1));
	   juv.setPurgeFlag(updateEvent.getPurgeFlag());
	   juv.setPurgeBoxNum(updateEvent.getPurgeBoxNum());
	   juv.setPurgeSerNum(updateEvent.getPurgeSerNum());
	   juv.setJuvenileTitle(updateEvent.getJuvenileNameSuffix());
	   juv.setLcDate(DateUtil.getCurrentDate());
	   juv.setLcTime(Calendar.getInstance().getTime());
	   juv.setLcuser(PDSecurityHelper.getLogonId());
	   juv.setRectype(updateEvent.getRecType());
	   juv.setLiveWithTjjd(updateEvent.getLivewith());
	   juv.setSealComments(updateEvent.getSealComments());
	   //BUG: 190694
	   if(!updateEvent.getSealedDate().isEmpty()){
	       juv.setSealedDate(updateEvent.getSealedDate());
	   }else {
	       juv.setSealedDate(null);
	   } //BUG: 190694 changes ENDS
	   juv.setCauseOfDeath(updateEvent.getCauseOfDeath());
	   juv.setDeathVerification(updateEvent.getDeathVerification());
	   juv.setDateOfDeath(DateUtil.stringToDate(updateEvent.getDateOfDeath(), DateUtil.DATE_FMT_1));
	   juv.setAgeAtDeath(updateEvent.getAgeAtDeath());
	   juv.setJuvExcluded(updateEvent.getJuvExcluded());
	   new Home().bind(juv);
	}
	else
	{
	    //Post error back	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    ErrorResponseEvent error = new ErrorResponseEvent();
	    error.setMessage("JUVENILE record not SEALED (JUVENILE NUM) with juvenileId: " + updateEvent.getJuvenileId());
	    dispatch.postEvent(error);
	}

    }

}
