package ui.juvenilecase.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import messaging.juvenile.UpdateJuvenileNumControlEvent;
import messaging.juvenile.reply.JuvenileNumControlResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenile.Juvenile;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.referral.form.JuvenileReferralForm;

/**
 * @author sthyagarajan
 */
public class DisplayCreateJuvenileSummaryAction extends LookupDispatchAction
{
    /**
     * Key Method Map
     */
    protected Map getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.back", "back");
	keyMap.put("button.finish", "submitJuvenile");
	return keyMap;
    }

    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward submitJuvenile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	 //89766 User-story
	if (!form.getAction().equalsIgnoreCase("updateJuvenile"))
	{
	    //generate juvenile number
	    UpdateJuvenileNumControlEvent event = (UpdateJuvenileNumControlEvent) EventFactory.getInstance(JuvenileControllerServiceNames.UPDATEJUVENILENUMCONTROL);
	    CompositeResponse compositeResponse = MessageUtil.postRequest(event);
	    JuvenileNumControlResponseEvent juvNumCtrlEvt = (JuvenileNumControlResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileNumControlResponseEvent.class);
	    if (juvNumCtrlEvt != null)
	    {
		form.setJuvenileNum(juvNumCtrlEvt.getLastJuvenileNum());
	    }
	    Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (errorResponse != null)
	    {
		ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		try
		{
		    throw new GeneralFeedbackMessageException(myEvt.getMessage());
		}
		catch (GeneralFeedbackMessageException e)
		{
		    e.printStackTrace();
		}
	    }
	}
	 //89766 User-story
	if (form.getJuvenileNum() != null && !form.getJuvenileNum().isEmpty())
	{
	    // SAVE juvenile Name information.
	    SaveJuvenileProfileMainEvent requestEvent = (SaveJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEMAIN);

	    requestEvent.setJuvenileNum(form.getJuvenileNum());
	    if(form.getAction().equalsIgnoreCase("updateJuvenile")){
		requestEvent.setAction("updateJuvenileFinish"); 
		form.setAction("updateJuvenileFinish");
		}else {
	    requestEvent.setAction("createJuvenile");
		}
	    requestEvent.setLastName(form.getLastName());
	    requestEvent.setFirstName(form.getFirstName());
	    requestEvent.setMiddleName(form.getMiddleName());
	    requestEvent.setNameSuffix(form.getNameSuffix());
	    requestEvent.setRaceId( nvl(form.getOriginalRaceId(),form.getRaceId()) );

	    requestEvent.setSexId(form.getSexId());
	    requestEvent.setDateOfBirth(DateUtil.stringToDate(form.getDateOfBirth(), DateUtil.DATE_FMT_1));
	    requestEvent.setRealDOB(DateUtil.stringToDate(form.getRealDOB(), DateUtil.DATE_FMT_1));
	    requestEvent.setSSN(form.getSSN().getFormattedSSN());
	    
	    if (requestEvent.getAction().equals("createJuvenile")){
		requestEvent.setJuvExcludedReporting(form.getJuvExcludedReporting());
	    }
	    
	    requestEvent.setStatusId("N"); //no casefile
	    //Bug #88529 Hispanic Indicator will no longer be modified from Create/Update. It will done through Profile
	  //  requestEvent.setHispanic(form.isHispanic());
	  //  requestEvent.setHispanicStr(form.getHispanicStr());
	    requestEvent.setStudentId(form.getJuvenileNum());
	    requestEvent.setComments(form.getComments());
	    requestEvent.setCheckedOutTo(form.getCheckedOutTo());
	    requestEvent.setCheckedOutDate(form.getDateOut());
	    requestEvent.setLastActionBy(form.getLastActionBy());
	    requestEvent.setLastUpdate(form.getLastUpdate());
	    requestEvent.setOperator(form.getOperator());
	    //create Juvenile address
	    requestEvent.setJuvAddress(form.getJuvAddress());
	    //create Juvenile School
	    //Remove for bug 120991
	   requestEvent.setSchoolId(form.getSchoolId());
	   requestEvent.setSchoolDistId(form.getSchoolDistrictId());
	   requestEvent.setPgmAttdngId(form.getProgramAttendingId());
	   //requestEvent.setExitTypeId(form.getExitTypeId()); BUG 76348
	   requestEvent.setSchoolAttendanceStatusId(form.getAttendanceStatusId());
	   requestEvent.setGradeLvlId(form.getGradeLevelId());
	   requestEvent.setSchoolteaCode(form.getTeacode());
	   requestEvent.setJuvFromM204Flag(form.getJuvFromM204Flag());
	    //create family.
	    if(form.getMemberDetailsBeanList()!=null && !form.getMemberDetailsBeanList().isEmpty())
		requestEvent.setMemberDetailsBeanList(form.getMemberDetailsBeanList());
	    CompositeResponse response = MessageUtil.postRequest(requestEvent);
	  //bug fix for 99640
	  //check for the oid and reset the m204 flag to N.
	  Juvenile newjuv = Juvenile.findJCJuvenile(form.getJuvenileNum());
	  if (newjuv != null && newjuv.getOID()!=null &&newjuv.getOID()!="")
	  {
	   	form.setJuvFromM204Flag("N");
	  }
	  //
	    ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	    if (errorEvt != null)
	    {
		sendToErrorPage(aRequest, "error.generic", "An error has occurred: " + errorEvt.getMessage());
		errorEvt.getMessage();
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	if (form.getAction().equalsIgnoreCase("updateJuvenileFinish"))
	{
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.updateJuvSuccess"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	    form.setAction("updateJuvenileSummary");
	}
	else
	{
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.createJuvSuccess"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	    form.setAction("createJuvenileSummary");
	}
	
	
	//add a trait record to T_JCTRAITS table if RealDOB is entered - US 173684
	if(form.getRealDOB() != null && !"".equals(form.getRealDOB())){
	    
	    String supervisionNum = JuvenileReferralHelper.getJuvCasefileNum(form.getJuvenileNum());
	    if(supervisionNum != null && !"".equals(supervisionNum) 
		    		&& form.getJuvenileNum() != null && !"".equals(form.getJuvenileNum()) 
		    			&& form.getRealDOB() != null && !"".equals(form.getRealDOB())){
		
		JuvenileReferralHelper.SaveRealDobTraitInfo(form.getJuvenileNum(), form.getRealDOB(), supervisionNum);
	    }
	    
	}
	
	

	return aMapping.findForward(UIConstants.SUCCESS);
    }

  //bug #85456  
 /*   *//**
     * incrementTrackingNumber
     * @param form
     * @return
     *//*
    public static String incrementJuvenileNum(JuvenileReferralForm form)
    {
	if(form.getLastJuvenileNum()!=null && !form.getLastJuvenileNum().isEmpty()){
	    int newJuvenileNum = Integer.parseInt(form.getLastJuvenileNum())+1;
	  return(String.valueOf(newJuvenileNum));
	}
	return "";
    }
    */
    /**
     * Cancel
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
	return forward;
    }

    /**
     * Back
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	if(form.getAction().equalsIgnoreCase("updateJuvenile")){
	    ActionForward forward = aMapping.findForward("backToUpdate");
		return forward;
	}ActionForward forward = aMapping.findForward(UIConstants.BACK);
	return forward;
    }

    /*
     * 
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.length() > 0));
    }

    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey, param));
	saveErrors(aRequest, errors);
    }
    
    
    private String nvl(String value1, String value2 ){
	return (value1 != null && value1.length() > 0 ) ? value1 : value2;
    }    
}
