package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.ListProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.JuvenileCaseHelper;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rYoung
 */

public class UpdateMsReferralQueryAction extends Action
{

    private Logger log = Logger.getLogger("UpdateMsReferralQueryAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;
	boolean listSearch = false;
	String forward = "success";

	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    regform.setReferralId("");
	    return mapping.findForward("error");
	}

	String juvenileNum = regform.getJuvenileId();
	String referralNum = regform.getReferralId();
	String referralOID = regform.getReferralOID();
	
	if (juvenileNum == null || juvenileNum.equals(""))
	{
	    regform.setMsg("Please enter a Juvenile ID.");
	    return (mapping.findForward("error"));
	}
	if (StringUtils.isEmpty(referralNum) 
		&& StringUtils.isEmpty(referralOID) )
	{
	    listSearch = true;
	}
	//Log the query attempt
	log.info("[" + new Date() + "] ProdSupport (" + SecurityUIHelper.getLogonId().toUpperCase() + ") - Juvenile ID: " + juvenileNum + "- Referral Query ID: " + referralNum);

	//Verify valid JuvenileID		
	JJSJuvenileResponseEvent juvenile = retrieveJuvenile(juvenileNum);
	if (juvenile == null || juvenile.getJuvenileNum() == null || juvenile.getJuvenileNum().equals(""))
	{
	    regform.setToJuvenileId("");
	    regform.setMsg("Juvenile not found. You must enter a valid JuvenileID.");
	    return mapping.findForward("error");
	}

	regform.clear();
	// Set juvenile 
	Name name = new Name(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName());

	regform.setJuvenileName(name.getFormattedName());
	regform.setJuvenileSsn(juvenile.getJuvenileSsn());
	regform.setBirthDate(juvenile.getBirthDate());
	
	// master status from jjs_ms_juvenile
	String statusDesc = JuvenileBuilder.getMasterStatusDesc(juvenile.getStatusId());
	regform.setStatusId(statusDesc);
	//
	regform.setRectype(juvenile.getRectype());

	if (!listSearch)
	{

	    ArrayList juvprogrefs = getReferralsById( juvenileNum, referralNum, referralOID );

	    if (juvprogrefs != null)
	    {
		System.out.println("Juvenile size: " + juvprogrefs.size() );
		regform.setJuvprogrefCount(juvprogrefs.size());
		regform.setJuvprogrefs(juvprogrefs);
		
		if ( juvprogrefs.size() > 1 ){
		    forward = "listSuccess";
		} 
		
		if (juvprogrefs.size() == 1 ){
		    regform.setJuvprogref( buildJuvenileReferralResp ( (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(0) ) );
		    regform.setOriginalJuvprogref( buildJuvenileReferralResp ( (ProductionSupportJuvenileReferralResponseEvent) juvprogrefs.get(0) )  );
		}
	    }
	    else
	    {
		regform.setMsg("No referral records found for JuvenileID " + juvenileNum + "AND ReferralNum " + referralNum);
		return mapping.findForward("error");
	    }

	}
	else
	{

	    ArrayList juvprogrefs = getReferralList(juvenileNum);

	    if (juvprogrefs != null)
	    {
		regform.setJuvprogrefCount(juvprogrefs.size());
		
		//sorts in descending order by seq num.
		    Collections.sort((List<ProductionSupportJuvenileReferralResponseEvent>) juvprogrefs, Collections.reverseOrder(new Comparator<ProductionSupportJuvenileReferralResponseEvent>() {
			@Override
			public int compare(ProductionSupportJuvenileReferralResponseEvent evt1, ProductionSupportJuvenileReferralResponseEvent evt2)
			{
			    return Integer.valueOf(evt2.getReferralNum()).compareTo(Integer.valueOf(evt1.getReferralNum()));
			}
		    }));
		regform.setJuvprogrefs(juvprogrefs);
		forward = "listSuccess";
	    }
	    else
	    {
		regform.setMsg("No referral records found for JuvenileID " + juvenileNum);
		return mapping.findForward("error");
	    }
	}

	/** Populate Drop-downs **/
	ArrayList refSourceCodes   = (ArrayList) JuvenileReferralHelper.getAllReferralSources();
	ArrayList refDecisionCodes = (ArrayList) JuvenileCaseHelper.getAllReferralDecisions();
	ArrayList crtDecisionCodes = (ArrayList) JuvenileDistrictCourtHelper.getCourtDecisions();

	regform.setReferralSrcCodes(refSourceCodes);
	regform.setOutcomeCodes(refDecisionCodes);
	regform.setStatusCodes(crtDecisionCodes);

	regform.setMsg("");
	return mapping.findForward(forward);

    }

    /**
     * returns juvenile number if it finds it
     * 
     * @param juvenileNumber
     * @return
     */
    private JJSJuvenileResponseEvent retrieveJuvenile(String juvenileNumber)
    {

	/**
	 * Search for jcjuvenile.
	 */
	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(juvenileNumber);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	return juvenileResp;
    }

    /**
     * @param referralNum
     * @return
     */
    private ArrayList getReferralsById(String juvenileID, String referralNum, String referralOID)
    {
	ArrayList juvReferrals = null;

	// Get and set Associated JuvProgRefs
	GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(juvenileID);
	getJuvenileRerralsEvent.setReferralNum(referralNum);
	getJuvenileRerralsEvent.setReferralOID(referralOID);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	juvReferrals = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);

	return juvReferrals;
    }

    /**
     * @param referralNum
     * @return
     */
    private ArrayList getReferralList(String juvenileId)
    {
	ArrayList juvReferrals = null;

	// Get and set Associated JuvProgRefs
	ListProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (ListProductionSupportJuvenileReferralsEvent) 
						    EventFactory.getInstance(ProductionSupportControllerServiceNames.LISTPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(juvenileId);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	juvReferrals = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);

	return juvReferrals;
    }
    
    private ProductionSupportJuvenileReferralResponseEvent buildJuvenileReferralResp( ProductionSupportJuvenileReferralResponseEvent juvenileReferralResp) {
	
	ProductionSupportJuvenileReferralResponseEvent response = new ProductionSupportJuvenileReferralResponseEvent();
	response.setReferralOID( juvenileReferralResp.getReferralOID());
	response.setJuvenileNum( juvenileReferralResp.getJuvenileNum());
	response.setReferralNum( juvenileReferralResp.getReferralNum());
	response.setReferralDate( juvenileReferralResp.getReferralDate());
	response.setReferralSource( juvenileReferralResp.getReferralSource() );
	response.setReferralOfficer( juvenileReferralResp.getReferralOfficer() );
	response.setIntakeDecision( juvenileReferralResp.getIntakeDecision() );
	response.setCloseDate( juvenileReferralResp.getCloseDate() );
	response.setCourtResult( juvenileReferralResp.getCourtResult() );
	response.setCourtDisposition( juvenileReferralResp.getCourtDisposition() );
	response.setDispositionDate( juvenileReferralResp.getDispositionDate() );
	response.setCourtId( juvenileReferralResp.getCourtId() );
	response.setPiaStatus( juvenileReferralResp.getPiaStatus() );
	response.setViolationProbation( juvenileReferralResp.getViolationProbation() );
	response.setDaLogNum( juvenileReferralResp.getDaLogNum() );
	response.setTransNum( juvenileReferralResp.getTransNum() );
	response.setOffenseCode( juvenileReferralResp.getOffenseCode() );
	response.setLcuser( juvenileReferralResp.getLcuser() );
	response.setLcdate( juvenileReferralResp.getLcdate() );
	response.setLcTime( juvenileReferralResp.getLcTime() );
	response.setIntakeDate( juvenileReferralResp.getIntakeDate() );
	response.setRectype( juvenileReferralResp.getRectype() );
	response.setReferralTypeInd( juvenileReferralResp.getReferralTypeInd() );
	response.setCtAssignJPOId( juvenileReferralResp.getCtAssignJPOId() );
	response.setProbationStartDate( juvenileReferralResp.getProbationStartDate() );
	response.setProbationEndDate( juvenileReferralResp.getProbationEndDate() );
	response.setPdaDate( juvenileReferralResp.getPdaDate() );
	/// task 172857
	response.setCountyREFD( juvenileReferralResp.getCountyREFD());
	response.setTJJDreferralDate( juvenileReferralResp.getTJJDreferralDate() );
	response.setJpoId( juvenileReferralResp.getJpoId()  );
	response.setOffenseTotal( juvenileReferralResp.getOffenseTotal()  );
	response.setProbationJpoId( juvenileReferralResp.getProbationJpoId() );
	response.setDecisionType( juvenileReferralResp.getDecisionType() );
	//
	return response;
	
    }

}
