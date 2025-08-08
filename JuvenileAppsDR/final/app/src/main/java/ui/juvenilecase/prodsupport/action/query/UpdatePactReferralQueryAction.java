package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.GetJuvenileCasefileReferralsEvent;
import messaging.juvenilecase.GetJuvenileCasefileRiskNeedLevelEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.productionsupport.GetJuvenileReferralOffensesQueryEvent;
import messaging.referral.reply.ReferralPACTResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import net.minidev.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileBuilder;
import pd.juvenilecase.PACTRiskNeedLevel;
import ui.common.Name;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdatePactReferralQueryAction extends Action
{

    private Logger log = Logger.getLogger("UpdatePactReferralQueryAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm prodform = (ProdSupportForm) form;
	String forward = "successResult";
	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");

	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    prodform.clearAllResults();
	    prodform.setMsg("");
	    return mapping.findForward("error");
	}

	String riskneedID = prodform.getRiskneedID();
	String juvNum = prodform.getJuvenileId().trim();
	String referralNum = prodform.getReferralId().trim();
	if (clrChk != null && clrChk.equalsIgnoreCase("U"))
	{
	    prodform.clearAllResults();
	}

	String casefileID = "";
	String tempStatus = "";
	ArrayList<String> casefiles = new ArrayList<String>();

	if (juvNum == null || juvNum.equals(""))
	{
	    prodform.setMsg("Please enter a Juvenile ID");
	    return (mapping.findForward("error"));
	}
	/*if(referralNum == null || referralNum.equals(""))
	{
	    prodform.setMsg("Please enter a Referral number");
	    return(mapping.findForward("error"));
	}*/

	//Verify valid Juvenile

	JJSJuvenileResponseEvent juv = retrieveJuvenile(juvNum);

	if (juv == null || juv.getJuvenileNum() == null || juv.getJuvenileNum().equals(""))
	{
	    prodform.setToJuvenileId("");
	    prodform.setMsg("Juvenile not found. You must enter a valid Juvenile Number");
	    return mapping.findForward("error");
	}
	prodform.clear();

	Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
	prodform.setJuvenileName(name.getFormattedName());
	prodform.setJuvenileSsn(juv.getJuvenileSsn());
	prodform.setBirthDate(juv.getBirthDate());
	prodform.setRectype(juv.getRectype());

	// master status from jjs_ms_juvenile
	String statusDesc = JuvenileBuilder.getMasterStatusDesc(juv.getStatusId());
	prodform.setStatusId(statusDesc);
	//

	GetJuvenileCasefileReferralsEvent event = (GetJuvenileCasefileReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEREFERRALS);
	event.setJuvenileNum(juvNum);
	
	ArrayList<ReferralPACTResponseEvent> pactReferrals = new ArrayList<ReferralPACTResponseEvent>();
	ArrayList<PACTRiskNeedLevel> pactReferralsTotal = new ArrayList<PACTRiskNeedLevel>();
	List jsonList = new ArrayList();
	
	if (referralNum == null || referralNum.equals(""))
	{
	    int numberOfCurrent = 0;
	    Iterator riskNeedItert = PACTRiskNeedLevel.findAll("juvenileNumber", juvNum);
	    while (riskNeedItert.hasNext())
	    {

		PACTRiskNeedLevel pactVal = (PACTRiskNeedLevel) riskNeedItert.next();
		ReferralPACTResponseEvent resp = new ReferralPACTResponseEvent();
		JSONObject json = new JSONObject();		
		
		resp.setCaseFileId(pactVal.getCaseFileId());
		resp.setReferralNumber(pactVal.getReferralNumber());
		resp.setJuvenileNumber(pactVal.getJuvenileNumber());
		resp.setRiskLvl(pactVal.getRiskLvl());
		resp.setNeedsLvl(pactVal.getNeedsLvl());
		resp.setLastPactDate(pactVal.getLastPactDate());
		resp.setRiskNeedLvlId(pactVal.getRiskNeedLvlId());
		resp.setStatus(pactVal.getStatus());
		if( pactVal.getPactId() > 1 ){
		    
		    resp.setPactId(String.valueOf(pactVal.getPactId()));
		}else{
		    
		    resp.setPactId("");
		}
		
		if("CURRENT".equalsIgnoreCase( pactVal.getStatus())){
		    
		    json.put("STATUS", pactVal.getStatus());
		    json.put("casefileID", pactVal.getCaseFileId());
		    jsonList.add(json);
		    
		}
		
		JJSOffenseResponseEvent juvrefoffenses = getReferralOffense(juvNum, pactVal.getReferralNumber());
		if (juvrefoffenses != null && juvrefoffenses.getOffenseCode() != null)
		{
		    resp.setOffenseCode(juvrefoffenses.getOffenseCodeId());
		}
		else
		{
		    resp.setOffenseCode(null);
		}
				
		pactReferrals.add(resp);
		pactReferralsTotal.add(pactVal);
	    }
	    //sort pactReferrals on referral number
	       Collections.sort((ArrayList<ReferralPACTResponseEvent>) pactReferrals, Collections.reverseOrder(new Comparator<ReferralPACTResponseEvent>() {
			@Override
			public int compare(ReferralPACTResponseEvent evt1, ReferralPACTResponseEvent evt2)
			{
			    if (evt1.getReferralNumber()!= null && evt2.getReferralNumber() != null)
				return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
			    else
				return -1;
			}
		    }));

	}
	else
	{
	    Iterator riskNeedIter = PACTRiskNeedLevel.findAll("juvenileNumber", juvNum);
	    int numberOfCurrent = 0;
	    while (riskNeedIter.hasNext())
	    {
		PACTRiskNeedLevel pactVal = (PACTRiskNeedLevel) riskNeedIter.next();		
		ReferralPACTResponseEvent resp = new ReferralPACTResponseEvent();
		JSONObject json = new JSONObject();
		
		resp.setCaseFileId(pactVal.getCaseFileId());
		resp.setReferralNumber(pactVal.getReferralNumber());
		resp.setJuvenileNumber(pactVal.getJuvenileNumber());
		resp.setRiskLvl(pactVal.getRiskLvl());
		resp.setNeedsLvl(pactVal.getNeedsLvl());
		resp.setLastPactDate(pactVal.getLastPactDate());
		resp.setRiskNeedLvlId(pactVal.getRiskNeedLvlId());
		resp.setStatus(pactVal.getStatus());
		if( pactVal.getPactId() > 1 ){
		    
		    resp.setPactId(String.valueOf(pactVal.getPactId()));
		}else{
		    
		    resp.setPactId("");
		}
		if("CURRENT".equalsIgnoreCase( pactVal.getStatus())){
		    
		    json.put("STATUS", pactVal.getStatus());
		    json.put("casefileID", pactVal.getCaseFileId());
		    jsonList.add(json);
		    
		}
		JJSOffenseResponseEvent juvrefoffenses = getReferralOffense(juvNum, pactVal.getReferralNumber());
		if (juvrefoffenses != null && juvrefoffenses.getOffenseCode() != null)
		{
		    resp.setOffenseCode(juvrefoffenses.getOffenseCodeId());
		}
		else
		{
		    resp.setOffenseCode(null);
		}
		pactReferralsTotal.add(pactVal);
		String referral = pactVal.getReferralNumber();
		if(referral.equals(referralNum)){
		    pactReferrals.add(resp);
		} 
	    }
	    //pactReferrals.addAll((Collection<? extends PACTRiskNeedLevel>) IteratorUtils.toList(riskNeedIter));
	    riskNeedIter = null;

	}
	prodform.setPactReferralsJson(jsonList);

	if (riskneedID == null || riskneedID.equals(""))
	{
	    if (!pactReferrals.isEmpty())
	    {
		prodform.setPactReferrals((ArrayList) pactReferrals);
		prodform.setPactReferralsTotal((ArrayList) pactReferralsTotal);
		forward = "listsuccess";
	    }
	    else
	    {
		prodform.setMsg("No PACT referral records found for JuvenileID " + juvNum);
		return mapping.findForward("error");
	    }

	}
	else
	{
	    PACTRiskNeedLevel pactIter = PACTRiskNeedLevel.find(riskneedID);
	    int numberOfCurrentStatus = 0;
	    if (pactIter != null)
	    {
		prodform.setPactDate(DateUtil.dateToString(pactIter.getLastPactDate(), DateUtil.DATE_FMT_1));
		prodform.setRiskValue(pactIter.getRiskLvl());
		prodform.setNeedValue(pactIter.getNeedsLvl());
		prodform.setNewPactDate(DateUtil.dateToString(pactIter.getLastPactDate(), DateUtil.DATE_FMT_1));
		prodform.setPactStatus(pactIter.getStatus());
		prodform.setPactRecord(pactIter);
		prodform.setNewCaseID(pactIter.getCaseFileId());
		prodform.setNewReferralID(pactIter.getReferralNumber());
		prodform.setRiskneedID(riskneedID);
		prodform.setJuvenileId(juvNum);
		if( pactIter.getPactId() > 0 ){
		    
		    prodform.setPactId(String.valueOf(pactIter.getPactId()));
		    prodform.setNewPactId(String.valueOf(pactIter.getPactId()));
		}else{
		    
		    prodform.setPactId("");
		}
	
		JJSOffenseResponseEvent juvrefoffenses = getReferralOffense(juvNum, pactIter.getReferralNumber());
		if (juvrefoffenses != null && juvrefoffenses.getOffenseCode() != null){
		    prodform.setOffCode(juvrefoffenses.getOffenseCodeId());
		}
		else{
		    prodform.setOffCode(null);
		}
		for (PACTRiskNeedLevel pact : pactReferralsTotal)
		{
		    if( pact.getCaseFileId() != null ) {
			
			 System.out.println("caseFileId " + pact.getCaseFileId() + "Status " + pact.getStatus());
			    if (pact.getCaseFileId().equals(pactIter.getCaseFileId()) && "CURRENT".equals(pact.getStatus()))
			    {
				numberOfCurrentStatus++;
			    }
		    }
		   
		}

		prodform.setCurrentPactReferrals(numberOfCurrentStatus);
		if (numberOfCurrentStatus > 1)
		{
		    prodform.setMoreThanOneCurrent(true);
		}
		else
		{
		    prodform.setMoreThanOneCurrent(false);
		}

		GetJuvenileCasefileRiskNeedLevelEvent Evt = new GetJuvenileCasefileRiskNeedLevelEvent();
		Evt.setCaseFileId(pactIter.getCaseFileId());
		Evt.setJuvenileNum(juvNum);
		Evt.setReferralNumber(pactIter.getReferralNumber());
		Iterator it = PACTRiskNeedLevel.findAll(Evt);
		while (it.hasNext())
		{
		    PACTRiskNeedLevel record = (PACTRiskNeedLevel) it.next();
		    tempStatus = record.getStatus();
		    if (tempStatus != null && tempStatus.equalsIgnoreCase("current") && (!record.getRiskNeedLvlId().equals(riskneedID)))
		    {
			prodform.setStatusFlag("CURRENT");
			break;
		    }
		    else
		    {
			prodform.setStatusFlag("NOTCURRENT");
		    }
		}
	    }
	    forward = "successResult";
	}
	return mapping.findForward(forward);

    }

    /**
     * returns juvenile number
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
    private JJSOffenseResponseEvent getReferralOffense(String juvenileNum, String referralNum)
    {
	JJSOffenseResponseEvent juvReferrals = null;
	GetJuvenileReferralOffensesQueryEvent off = (GetJuvenileReferralOffensesQueryEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJUVENILEREFERRALOFFENSESQUERY);
	off.setJuvenileNum(juvenileNum);
	off.setReferralNum(referralNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(off);
	juvReferrals = (JJSOffenseResponseEvent) MessageUtil.filterComposite(juvenileReferralsResp, JJSOffenseResponseEvent.class);

	return juvReferrals;
    }

}
