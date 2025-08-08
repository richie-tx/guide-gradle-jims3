package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.form.JuvenileDrugForm;
import ui.juvenilecase.form.JuvenileMainForm;

public class DisplaySubstanceAbuseCreateAction extends Action
{
    public DisplaySubstanceAbuseCreateAction(){}
    
    
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
	String clrChk = aRequest.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    drugForm.setDrugTypeCodes(null);
	    drugForm.setTjjdSubstanceAbuseCodes(null);
	}
	
	String juvenileNum = aRequest.getParameter("juvenileNum");
	if ( juvenileNum != null 
		&& juvenileNum.length() > 0 ){
	    drugForm.setJuvenileCasfileResps(null);
	    List<JuvenileCasefileResponseEvent>casefiles = getCasefiles(juvenileNum);
	    drugForm.setJuvenileCasfileResps(casefiles);
	}
	
	if ( drugForm.getDrugTypeCodes() == null
		|| drugForm.getDrugTypeCodes().size() == 0 ){
	    drugForm.setDrugTypeCodes(CodeHelper.geActivetDrugTypes(true));
	}
	
	if ( drugForm.getTjjdSubstanceAbuseCodes() == null
		|| drugForm.getTjjdSubstanceAbuseCodes() .size() == 0 ){
	    drugForm.setTjjdSubstanceAbuseCodes( CodeHelper.getTjjdSubstanceAbuseCodes() );
	}
	
	JuvenileMainForm jmForm = (JuvenileMainForm)aRequest.getSession().getAttribute("juvenileMainForm");
	
	if(jmForm == null )
	{
		jmForm = new JuvenileMainForm();
	}
        if (jmForm.isHasCasefile() == false){
        	jmForm.setHasCasefile(true);  // must be true to display tabs
        }
        aRequest.getSession().setAttribute("juvenileProfileMainForm", jmForm);
	
	 return aMapping.findForward("success");
	
    }
    
    
    private List<JuvenileCasefileResponseEvent> getCasefiles(String juvenileNum){
	Iterator casefileIter = JuvenileCasefile.findAll("juvenileId", juvenileNum);
	List<JuvenileCasefileResponseEvent> casefiles= new ArrayList<>();
	while( casefileIter.hasNext() ){
	    JuvenileCasefile casefile = (JuvenileCasefile)casefileIter.next();
	    JuvenileCasefileResponseEvent casefileRsp = JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
	    if ( "A".equals( casefileRsp.getCaseStatusId() ) ){
		
		String supervisionNumWithSupervisionType = casefileRsp.getSupervisionNum() + "-" + casefileRsp.getSupervisionType();
		casefileRsp.setSupervisionNumWithSupervisionType(supervisionNumWithSupervisionType);	
		
		GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
			EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getAssignments.setCasefileId(casefile.getOID());
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,AssignmentResponseEvent.class);	
		if ( assignmentList != null
			&& assignmentList.size() > 0 ){
		    StringBuffer referrals = new StringBuffer();
		    for (int i = 0; i < assignmentList.size(); i++ ) {
			if ( i == 0 ){
			    referrals.append(assignmentList.get(i).getReferralNum() );
			} else {
			    referrals.append(", " + assignmentList.get(i).getReferralNum() );
			}
		    }
		    
		    casefileRsp.setAssignedReferrals( referrals.toString() );
		}
		casefiles.add(casefileRsp);
	    }
	}
	
	return casefiles;
    }

}
