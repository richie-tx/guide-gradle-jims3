package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.productionsupport.GetJuvenileReferralOffensesQueryEvent;
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

public class UpdateReferralOffenseRecordQueryAction extends Action
{

    private Logger log = Logger.getLogger("UpdateReferralOffenseRecordQueryAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;
	//boolean listSearch = false;
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

	String juvenileNum = regform.getJuvenileId().trim();
	String referralNum = regform.getReferralId().trim();

	/*if (juvenileNum == null || juvenileNum.equals(""))
	{
	    regform.setMsg("Please enter a Juvenile ID.");
	    return (mapping.findForward("error"));
	}
	if (StringUtils.isEmpty(referralNum))
	{
	    listSearch = true;
	}*/

	//Log the query attempt
	log.info("[" + new Date() + "] ProdSupport (" + SecurityUIHelper.getLogonId().toUpperCase() + ") - Juvenile ID: " + juvenileNum + "- Referral Query ID: " + referralNum);

	//Verify valid JuvenileID		
	JJSJuvenileResponseEvent juvenile = retrieveJuvenile(juvenileNum);
	if (juvenile == null || juvenile.getJuvenileNum() == null || juvenile.getJuvenileNum().equals(""))
	{
	    regform.setToJuvenileId("");
	    regform.setMsg("Juvenile Number is invalid.  Please retry search.");
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
	
	GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(juvenileNum);
	getJuvenileRerralsEvent.setReferralNum(referralNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	Collection<ProductionSupportJuvenileReferralResponseEvent> juvReffDetails = MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
	Iterator refIter = juvReffDetails.iterator();
	    if (juvReffDetails != null)
	    {
		ProductionSupportJuvenileReferralResponseEvent reff = new ProductionSupportJuvenileReferralResponseEvent();
		if (refIter.hasNext())
		{
		    reff = (ProductionSupportJuvenileReferralResponseEvent) refIter.next();
		    regform.setReferralDate(reff.getReferralDate());		    
		}
	    }
	
	
	ArrayList juvrefoffenses = getReferralOffense(juvenileNum, referralNum);
	//ArrayList juvreffs=getReferralsById(juvenileNum,referralNum);
	//regform.setReferralDate(juvreffs.);

	if (juvrefoffenses != null && juvrefoffenses.size() != 0)
	{
	    regform.setJuvRefOffensesCount(juvrefoffenses.size());
	    if (juvrefoffenses.size() > 1)
	    {
		Collections.sort((List<JJSOffenseResponseEvent>) juvrefoffenses, Collections.reverseOrder(new Comparator<JJSOffenseResponseEvent>() {
		    @Override
		    public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2)
		    {
			return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		    }
		}));
		ArrayList temp = new ArrayList();
		JJSOffenseResponseEvent t =  (JJSOffenseResponseEvent) juvrefoffenses.get(0);
		temp.add(t);
		regform.setOriginalReferralOffense(t);
		regform.setReferralOffense( buildOffenseResponse(t));
		//regform.setDocumentId(reff.getReferralOID());//setting it to OID to use for update
	    } else{
		regform.setReferralOffense( buildOffenseResponse( (JJSOffenseResponseEvent)juvrefoffenses.get(0) ) );
		regform.setOriginalReferralOffense( (JJSOffenseResponseEvent)juvrefoffenses.get(0) );
	    }
	    forward = "success";
	}
	else
	{
	    regform.setMsg("No matching record found.  Please retry search");
	    return mapping.findForward("error");
	}

	
	/** Populate Drop-downs **/
	/*ArrayList refSourceCodes = (ArrayList) JuvenileReferralHelper.getAllReferralSources();
	ArrayList refDecisionCodes = (ArrayList) JuvenileCaseHelper.getReferralDecisions();
	ArrayList crtDecisionCodes = (ArrayList) JuvenileDistrictCourtHelper.getCourtDecisions();

	regform.setReferralSrcCodes(refSourceCodes);
	regform.setOutcomeCodes(refDecisionCodes);
	regform.setStatusCodes(crtDecisionCodes);*/

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
    private ArrayList getReferralOffense(String juvenileNum, String referralNum)
    {
	ArrayList juvReferrals = null;	
	GetJuvenileReferralOffensesQueryEvent off = (GetJuvenileReferralOffensesQueryEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJUVENILEREFERRALOFFENSESQUERY);
	off.setJuvenileNum(juvenileNum);
	off.setReferralNum(referralNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(off);
	juvReferrals = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, JJSOffenseResponseEvent.class);
	
	return juvReferrals;
    }

    /**
     * @param referralNum
     * @return
     */
    
	private ArrayList getReferralsById(String juvenileID, String referralNum)
	    {
		ArrayList juvReferrals = null;

		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (GetProductionSupportJuvenileReferralsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEREFERRALS);
		getJuvenileRerralsEvent.setJuvenileId(juvenileID);
		getJuvenileRerralsEvent.setReferralNum(referralNum);
		CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
		juvReferrals = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
		return juvReferrals;
    }


    private JJSOffenseResponseEvent buildOffenseResponse( JJSOffenseResponseEvent offenseResp ){
	JJSOffenseResponseEvent response = new JJSOffenseResponseEvent();
	response.setOID( offenseResp.getOID() );
	response.setJuvenileNum( offenseResp.getJuvenileNum() );
	response.setReferralNum( offenseResp.getReferralNum() );
	response.setOffDate( offenseResp.getOffDate() );
	response.setOffenseCode( offenseResp.getOffenseCode() );
	response.setOldoffenseCode( offenseResp.getOldoffenseCode() );
	response.setKeyMapLocation( offenseResp.getKeyMapLocation() );
	response.setInvestigationNum( offenseResp.getInvestigationNum() );
	response.setOffenseStreetNum( offenseResp.getOffenseStreetNum());
	response.setOffenseStreetName( offenseResp.getOffenseStreetName());
	response.setOffenseAptNum( offenseResp.getOffenseAptNum() );
	response.setOffenseCity( offenseResp.getOffenseCity() );
	response.setOffenseState( offenseResp.getOffenseState() );
	response.setOffenseZip( offenseResp.getOffenseZip()  );
	response.setWeaponType( offenseResp.getWeaponType() );
	response.setCjisNum( offenseResp.getCjisNum() );
	response.setArrestDate( offenseResp.getArrestDate() );
	response.setArrestTime( offenseResp.getArrestTime() );
	response.setSequenceNum( offenseResp.getSequenceNum() );
	response.setChargeSequenceNum( offenseResp.getChargeSequenceNum() );
	response.setLcUser( offenseResp.getLcUser());
	response.setLcTime( offenseResp.getLcTime());
	response.setLcDate( offenseResp.getLcDate());	
	response.setOnCampOffense(offenseResp.getOnCampOffense());
	response.setOnCampDistrict(offenseResp.getOnCampDistrict());
	response.setOnCampSchool(offenseResp.getOnCampSchool());
	
	return response;
    }

}
