package pd.juvenilecase.referral.transactions;

import java.sql.Timestamp;
import java.util.Iterator;

import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import messaging.referral.SaveOverrideAssignmentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;

public class SaveOverrideAssignmentCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	SaveOverrideAssignmentEvent saveEvent = (SaveOverrideAssignmentEvent)event;
	  IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	  IHome home = new Home();
	  Iterator<JJSReferral> refIter = JJSReferral.findAll("OID",saveEvent.getReferralId());

	    if (refIter.hasNext())
	    {
		JJSReferral ref = refIter.next();
		if (ref != null)
		{
		    if(saveEvent.getJpoID()!=null && !saveEvent.getJpoID().equalsIgnoreCase("")){
			ref.setJpoId(saveEvent.getJpoID());
		    }
		    if(saveEvent.getCtAssignJPOId()!=null && !saveEvent.getCtAssignJPOId().equalsIgnoreCase("")){
			ref.setCtAssignJPOId(saveEvent.getCtAssignJPOId());
		    }
		    if(saveEvent.getProbJPOId()!=null && !saveEvent.getProbJPOId().equalsIgnoreCase("")){
			ref.setProbJPOId(saveEvent.getProbJPOId());
		    }
		   // home.bind(ref);
		}
	    }
	  //update JJSREFERRAL FOR #89887
	  // Maybe fix Assignment?
    }
    
    private void saveIntakeHistory( SaveOverrideAssignmentEvent evt, IHome home ){
	
	//Create Intake History
	JJSSVIntakeHistory intakeHistory = new JJSSVIntakeHistory();
	
	intakeHistory.setAssignmentType(evt.getAssignmentType());
	//intakeHistory.setAssignmentDate(evt.getAssignmentDate());
	intakeHistory.setIntakeDate(evt.getIntakeDate());
	intakeHistory.setIntakeDecisionId(evt.getIntakeDecisionId());
	intakeHistory.setJpoId(evt.getJpoID());
	intakeHistory.setJuvenileNum(evt.getJuvenileNum());
	intakeHistory.setReferralNumber(evt.getReferralNum());
	intakeHistory.setCreateUserID(UIUtil.getCurrentUserID());
	intakeHistory.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
	intakeHistory.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
	//intakeHistory.setSupervisionTypeId(evt.getSupervisionType());
	home = new Home();
	home.bind(intakeHistory);
    }

}
