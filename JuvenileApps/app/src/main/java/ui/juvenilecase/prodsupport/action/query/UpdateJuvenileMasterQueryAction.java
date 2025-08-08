package ui.juvenilecase.prodsupport.action.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.productionsupport.GetProdSupportJJSJuvenileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.codetable.criminal.JuvenileMasterStatus;

import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and displays the record
 */

public class UpdateJuvenileMasterQueryAction extends Action {
	private Logger log = Logger.getLogger("UpdateJuvenileMasterQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.setReferralNum("");
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String juvenileId = regform.getFromJuvenileId();

		if (juvenileId == null || juvenileId.equals("")) {
			regform.setMsg("Juvenile number is required.");
			return mapping.findForward("error");
		}


		regform.clearAllResults();

		regform.setFromJuvenileId(juvenileId);

		// Log the query attempt
		log.info("Juvenile Master Update Query - JuvID: " + juvenileId + " LogonId: " + SecurityUIHelper.getLogonId());

		ArrayList juveniledetails = retrieveJuvenileDetails(juvenileId);

		if (juveniledetails == null || juveniledetails.size() == 0) {
			
			regform.setMsg("Juvenile Number is invalid.  Please retry search "
					+ juvenileId + ".");
			return mapping.findForward("error");
		}
		else if(juveniledetails.iterator().hasNext())
		{
		    setUpdateValues(regform, (JuvenileProfileDetailResponseEvent)juveniledetails.get(0));
		}

		regform.setJuveniles(juveniledetails);
		regform.setRaceList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.RACE));
		regform.setOriginalRaceList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.RACE));
		regform.setSexList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SEX));
		regform.setFacilityStatusList((ArrayList)SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.FACILITY_STATUS));
		regform.setMsg("");
		ArrayList<JuvenileFacilityResponseEvent> activeFacilitiesList = (ArrayList<JuvenileFacilityResponseEvent>)JuvenileFacilityHelper.loadActiveFacilities();
		regform.setActiveFacilitiesList(activeFacilitiesList);
		regform.setMasterStatusCodes( getMasterStatusCodes() );
		//regform.setCausesOfDeath((ArrayList)CodeHelper.getCauseOfDeathCodes());
		regform.setCausesOfDeath(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CAUSE_OF_DEATH));
		regform.setDeathVerficationCodes(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.DEATH_VERIFICATION));
		return mapping.findForward("success");

	}
	private void setUpdateValues(ProdSupportForm regform, JuvenileProfileDetailResponseEvent resp)
	{
	    regform.setPurgeBoxNum(resp.getPurgeBoxNum());
	    regform.setPurgeFlag(resp.getPurgeFlag());
	    regform.setPurgeDate(resp.getPurgeDate());
	    regform.setPurgeSerNum(resp.getPurgeSerNum());
	    regform.setNewSSN(new SocialSecurity(resp.getCompleteSSN()));
	    regform.setRace(resp.getRace());
	    regform.setOriginalRace(resp.getOriginalRace());
	    regform.setRaceId(resp.getRaceId());
	    regform.setOriginalRaceId(resp.getOriginalRaceId());
	    regform.setSex(resp.getSex());
	    regform.setSexId(resp.getSexId());
	    regform.setCaseNotePart1(resp.getCaseNotePart1());
	    regform.setCheckedOutTo(resp.getCheckedOutTo());
	    regform.setCheckedOutDate(DateUtil.dateToString(resp.getCheckedOutDate(), DateUtil.DATE_FMT_1));
	    regform.setDetentionFacilityId(resp.getDetentionFacilityId());
	    regform.setDetentionStatusId(resp.getDetentionStatusId());
	    regform.setStatusId(resp.getStatusId());
	    regform.setLastReferral(resp.getLastReferral());
	    regform.setMasterDOB(DateUtil.dateToString(resp.getDateOfBirth(), DateUtil.DATE_FMT_1));
	    regform.setRealDOB(DateUtil.dateToString(resp.getRealDOB(), DateUtil.DATE_FMT_1));
	    regform.setOldStatusId(resp.getOldStatusId());
	    regform.setJuvenileFName(resp.getFirstName());
	    regform.setJuvenileMName(resp.getMiddleName());
	    regform.setJuvenileLName(resp.getLastName());
	    regform.setJuvenileNameSuffix(resp.getNameSuffix());
	    regform.setJuvenileId(resp.getJuvenileNum());
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    if (resp.getLcTime() != null)
	    {
		String lctime = dateFormat.format(resp.getLcTime());
		regform.setLctime(lctime);
	    }
	    //US 190449 
	    regform.setRectype(resp.getRecType());
	    regform.setSealComments(resp.getSealComments());
	    regform.setSealedDate(resp.getSealedDate());
	    regform.setLivewith(resp.getLiveWithTjjd());
	    regform.setCauseOfDeath(resp.getYouthDeathReason());
	    regform.setDeathVerification(resp.getYouthDeathVerification());
	    regform.setDateOfDeath(DateUtil.dateToString(resp.getDeathDate(), DateUtil.DATE_FMT_1));
	    regform.setAgeAtDeath(resp.getDeathAge());
	    regform.setJuvExcluded(resp.getJuvExcluded());
	    //US 190449 
	}
	
	
	private ArrayList retrieveJuvenileDetails( String juvenileId ){

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProdSupportJJSJuvenileEvent juvProfileEvent = (GetProdSupportJJSJuvenileEvent)
			EventFactory.getInstance( ProductionSupportControllerServiceNames.GETPRODSUPPORTJJSJUVENILE ) ;
		juvProfileEvent.setJuvenileId(juvenileId);

		ArrayList juveniles = (ArrayList) MessageUtil.postRequestListFilter(juvProfileEvent, JuvenileProfileDetailResponseEvent.class);

		return juveniles;	
	}
	
	private ArrayList<JuvenileMasterStatus> getMasterStatusCodes(){
	    ArrayList<JuvenileMasterStatus> masterStatusCodes = new ArrayList<JuvenileMasterStatus>();
	    
	    Iterator codes = JuvenileMasterStatus.findAll();
	    while ( codes.hasNext() ) {
		masterStatusCodes.add( (JuvenileMasterStatus)codes.next() );
	    }
	    
	    return masterStatusCodes;
	}
}
