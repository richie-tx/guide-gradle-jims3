package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.productionsupport.GetProductionSupportTraitsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;



/**
 * @author rcarter
 * 
 * 
 * DeleteQueryAction gathers information on what data will be affected by the delete, and 
 * displays this information to the user on the deleteQueryResult.jsp form.
 * 
 * PeformDeleteAction actually executes the delete statement after getting confirmation 
 * from the user, and displays a summary on the deleteSummary.jsp form.
 * 
 */
public class DeleteTraitQueryAction extends Action {

	private Logger log = Logger.getLogger("DeleteTraitQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setMsg("");
			regform.setJuvenileId("");
			regform.setTraitId("");
			return mapping.findForward("error");
		}	
		
		String juvenileId = trimParameter(regform.getJuvenileId());
		String traitId = trimParameter(regform.getTraitId());	
		int searchTraitSize = 0;
		
		String lstChk = request.getParameter("list");
		if (lstChk != null && lstChk.equalsIgnoreCase("Y")) {
		    traitId = "";
		}
		
		regform.setMsg("");
		if ((juvenileId == null || juvenileId.equals("")) && (traitId == null || traitId.equals(""))) {
			regform.setMsg("You must enter a valid Juvenile ID to retrieve all traits for a given juvenile, or a valid Trait ID for a specific trait.");
			return mapping.findForward("error");
		}
		// if Juvenile Id is provided then use this id. If not, use Trait Id. If neither is provided - error. Always clear out trait id if juvenileId is provided.
		String delete = request.getParameter("delete");
		// chose a specific trait from the list of traits
		if (delete != null && delete.equalsIgnoreCase("Y")) {
			if (traitId == null || traitId.equals("")) {
				regform.setMsg("You must choose a valid Trait ID.");
				return mapping.findForward("success"); // send to the collection of traits
			}
			ArrayList traits = new ArrayList();
			if(regform.getTraits() != null && regform.getTraits().size() > 0){
			        searchTraitSize =regform.getTraits().size();
				traits = getTraitByTraitId(traitId,regform.getTraits());
			}else if(traitId != null){
				traits = getTraitByTraitId(traitId);
			}
			regform.clearAllResults();
			regform.setMsg("");
			if (traits == null || traits.size() == 0){
				regform.setMsg("Chosen Trait Record could not be found for traitId: " + traitId);
				regform.setTraits(null);
				regform.setTraitCount(0);
				log.info("Chosen Trait Record could not be found for traitId: " + traitId + " for LogonId: " 
						+ SecurityUIHelper.getLogonId());
				return mapping.findForward("error");
			}else{
				regform.setTraits(traits);
				regform.setTraitCount(traits.size());
				if(searchTraitSize > 0){
				regform.setSearchTraitCount(searchTraitSize);
				}
				
				log.info("Chosen Trait Record found for traitId: " + traitId 
						+ " for LogonId: " + SecurityUIHelper.getLogonId());
				return mapping.findForward("continue");
			}
		}

		// initial search screen
		 if(traitId != null && traitId.equals("") == false){
			regform.clearAllResults();
			regform.setJuvenileId("");
			regform.setTraitId(traitId.trim());
			ArrayList traits = getTraitByTraitId(traitId);
			if (traits == null || traits.size() == 0){
				regform.setMsg("No trait record returned for traitId " + traitId);
				regform.setTraits(null);
				regform.setTraitCount(0);
				log.info("No Trait was found for traitId: " + traitId + " for LogonId: " 
						+ SecurityUIHelper.getLogonId());
				return mapping.findForward("error");
			}else{
				regform.setTraits(traits);
				regform.setTraitCount(traits.size());
				log.info("There were " + traits.size() + " Traits successfully retrieved found for traitId: " + 
						traitId + " for LogonId: " + SecurityUIHelper.getLogonId());
				return mapping.findForward("continue");
			}
		}else if (juvenileId != null && juvenileId.equals("") == false) {
			regform.clearAllResults();
			regform.setJuvenileId(juvenileId.trim());
			regform.setTraitId("");

			// Retrieve the trait collection for this juvenile id
			ArrayList traits = getTraitsByJuvenileNumber(juvenileId);
			if (traits == null || traits.size() == 0){
				regform.setMsg("No trait records returned for juvenileId " + juvenileId);
				regform.setTraits(null);
				regform.setTraitCount(0);
				log.info("No Traits were found for juvenileId: " + juvenileId + " for LogonId: " 
						+ SecurityUIHelper.getLogonId());
				return mapping.findForward("error");
			}else{
				regform.setTraits(traits);
				regform.setTraitCount(traits.size());
				log.info("There were " + traits.size() + " Traits successfully retrieved found for juvenileId: " + 
						juvenileId + " for LogonId: " + SecurityUIHelper.getLogonId());
				return mapping.findForward("success");
			}
			
		} else{
			regform.setMsg("An unknown error occurred - please try again.");
			regform.setTraits(null);
			regform.setTraitCount(0);
			log.info("An unknown error occurred - please try again. TraitId: " + traitId + " JuvenileId: " + juvenileId + " for LogonId: " 
					+ SecurityUIHelper.getLogonId());
			return mapping.findForward("error");
		}
		

	}	
	
	/**
	 * retrieve all the traits for a given juvenile id in a collection
	 * @param juvenileId
	 * @return
	 */
	private ArrayList getTraitsByJuvenileNumber(String juvenileId){

		GetProductionSupportTraitsEvent getTraitsEvent = (GetProductionSupportTraitsEvent)EventFactory.
				getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTTRAITS );
			getTraitsEvent.setJuvenileId( juvenileId );
			CompositeResponse getTraitsResponse = MessageUtil.postRequest(getTraitsEvent);
			ArrayList getTraitsResponsesList = (ArrayList) MessageUtil.compositeToCollection(getTraitsResponse, JuvenileTraitResponseEvent.class);
			Collections.sort(getTraitsResponsesList, JuvenileTraitResponseEvent.JuvenileTraitIdComparator);
		return getTraitsResponsesList;
	}
	
	/**
	 * retrieve a collection with just the chosen single trait from existing collection of traits records
	 * @param traitId
	 * @param traits
	 * @return
	 */
	private ArrayList getTraitByTraitId(String traitId, ArrayList<JuvenileTraitResponseEvent> traits){
		JuvenileTraitResponseEvent matchedTrait = null;
		ArrayList<JuvenileTraitResponseEvent> getTraitsResponsesList = new ArrayList<JuvenileTraitResponseEvent>();
		for(JuvenileTraitResponseEvent trait: traits){
			if(trait.getJuvenileTraitId().equalsIgnoreCase(traitId)){
				matchedTrait = trait;
				getTraitsResponsesList.add(matchedTrait);
				break;				
			}
		}
		return getTraitsResponsesList;
	}
	
	/**
	 * retrieve a collection with from a single trait id
	 * @param traitId
	 * @param traits
	 * @return
	 */
	private ArrayList getTraitByTraitId(String traitId){
		GetProductionSupportTraitsEvent getTraitsEvent = (GetProductionSupportTraitsEvent)EventFactory.
				getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTTRAITS );
			getTraitsEvent.setTraitId(traitId);
			CompositeResponse getTraitsResponse = MessageUtil.postRequest(getTraitsEvent);
			ArrayList getTraitsResponsesList = (ArrayList) MessageUtil.compositeToCollection(getTraitsResponse, JuvenileTraitResponseEvent.class);
		return getTraitsResponsesList;
	}
	
	/**
	 * Return a description for a given code table and code combination
	 * @param codeTableName
	 * @param codeId
	 * @return
	 */
	private String getCodeByCodeId(String codeTableName, String codeId){
		String description = CodeHelper.getCodeDescription(codeTableName, codeId);
		return description;		
	}

	/**
	 * helper method to trim incoming strings
	 * @param parameter
	 */
	private String trimParameter(String parameter){
		if(parameter != null){
			parameter = parameter.trim();
		}
		return parameter;
	}
}
