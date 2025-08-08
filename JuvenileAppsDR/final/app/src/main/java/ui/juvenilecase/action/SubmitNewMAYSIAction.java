package ui.juvenilecase.action;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.GetMaysiRequestsEvent;
import messaging.mentalhealth.SaveMAYSIMetadataEvent;
import messaging.mentalhealth.reply.MAYSIAssessResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.MentalHealthForm;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper;

/**
 * @author dgibler
 *  
 */
public class SubmitNewMAYSIAction extends JIMSBaseAction
{

    /**
     * @roseuid 42791FD100BB
     */
    public SubmitNewMAYSIAction()
    {

    }

    protected void addButtonMapping(Map buttonMap)
    {
        buttonMap.put("button.finish", "finish");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.cancel", "cancel");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D5702D0
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        MentalHealthForm mhForm = (MentalHealthForm) aForm;
        JuvenileProfileForm pFrom = UIJuvenileHelper.getHeaderForm(aRequest);
        GetMaysiRequestsEvent requestMaysi =
        	(GetMaysiRequestsEvent) EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMAYSIREQUESTS);
	
	     requestMaysi.setJuvenileNumber(mhForm.getJuvenileNum());
	     requestMaysi.setReferralNumber(mhForm.getReferralNum());
	     GregorianCalendar myCal = new GregorianCalendar();
	     myCal.add(Calendar.DATE, -1);
	     requestMaysi.setDateForward(myCal.getTime());
	     List myColl = MessageUtil.postRequestListFilter(requestMaysi, MAYSISearchResultResponseEvent.class);
	
	     if (myColl != null && myColl.size() > 0)
	     {
	         sendToErrorPage(aRequest, "error.newMaysiRequest");
	         return aMapping.findForward(UIConstants.FAILURE);
	     }
       
        SaveMAYSIMetadataEvent request = 
        	(SaveMAYSIMetadataEvent) EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.SAVEMAYSIMETADATA);

        request.setRequestingOfficerId(UIUtil.getCurrentUserID());
        request.setFacilityType(mhForm.getFacilityTypeId());
        request.setHasPreviousMAYSI(mhForm.isHasPreviousMAYSI());
        request.setJuvenileNumber(mhForm.getJuvenileNum());
        request.setLengthOfStay(mhForm.getLengthOfStayId());
        request.setLocationId(mhForm.getLocationUnitId());
        request.setReferralNumber(mhForm.getReferralNum());
        request.setRequestDate(new Date());
        request.setAdministered(mhForm.isAdminister());
        request.setReasonNotDone(mhForm.getReasonNotDone());
        //request.setReasonNotDoneId(mhForm.getReasonNotDoneId());
        request.setOthReasonNotDone(mhForm.getOtherReasonNotDone());
        request.setScheduledOffIntDate(DateUtil.stringToDate(mhForm.getScheduledOffIntDateStr(), DateUtil.DATE_FMT_1));
        request.setRaceId(mhForm.getRaceId());
        request.setSexId(mhForm.getSexId());
        request.setMaysiId(mhForm.getMaysiId());
        if(mhForm.getTestAge()==null || mhForm.getTestAge().equals("")){
        	request.setTestAge(0);
        }
        else{
        	request.setTestAge(Integer.parseInt(mhForm.getTestAge()));
        }

        MAYSIAssessResponseEvent detail = (MAYSIAssessResponseEvent) MessageUtil.postRequest(request,
                MAYSIAssessResponseEvent.class);
        if (detail != null)
        {
            mhForm.setAssessmentOfficerName(UIUtil.getCurrentUserName());
            Collection assessments = UIJuvenileCaseworkHelper.fetchPreviousMAYSIAssessments(mhForm.getJuvenileNum());
            if (assessments != null)
            {
                Iterator iterAssessments = assessments.iterator();
                while (iterAssessments.hasNext())
                {
                    MAYSISearchResultResponseEvent respAssessments = (MAYSISearchResultResponseEvent) iterAssessments
                            .next();
                    String strMaysiId = respAssessments.getMaysiFullAssessId();
                    String[] ids = strMaysiId.split("-");
                    int i = strMaysiId.indexOf("-");
                    strMaysiId = strMaysiId.substring(0, i);
                    if (mhForm.isAdminister() && (mhForm.isTask() == false))
                    { // only create alert if maysi was administered
                        if (strMaysiId.equals(detail.getAssessmentId()) && ids[2].equals("0"))
                        {
                            JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper();
                            helper.sendMAYSIRequestorActionAlert(detail, mhForm.getJuvenileNum(), pFrom
                                    .getJuvenileName(), respAssessments.getMaysiFullAssessId());
                        }
                    }
                }
            }
        }
        else
        {
            return aMapping.findForward(UIConstants.FAILURE);
        }
        if(mhForm.getReasonNotDone()!=null && !mhForm.getReasonNotDone().equals(""))
        	mhForm.setAssessmentOption(UIConstants.SUBSEQUENT_NOT_NEEDED_ASSESSMENT_OPTION);
        else
        	mhForm.setAssessmentOption(UIConstants.NEW_MAYSI_ASSESSMENT_OPTION);		
			
       if(mhForm.getMaysiId()!=null && !mhForm.getMaysiId().equals(""))
    	   mhForm.setScreenDate(mhForm.getAssessmentDate());
       else
       		mhForm.setScreenDate(DateUtil.getCurrentDate());
       
       	
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }
}
