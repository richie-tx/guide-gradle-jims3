//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayDepartmentSearchResultsAction.java

package ui.juvenilecase.medical.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileMedicationTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileMedicationTypeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.medical.form.MedicalForm;

public class DisplayMedicalMedicationSearchResultsAction extends JIMSBaseAction 
{

	/**
	 * @roseuid 430630EA0355
	 */
	public DisplayMedicalMedicationSearchResultsAction()
	{

	}

	/**
	 * @return Map
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.findMedications", "findMedications");
		keyMap.put("button.refresh", "refresh");		
		keyMap.put("button.select", "select");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E50216
	 */
	public ActionForward findMedications(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{	
		MedicalForm medForm = (MedicalForm) aForm;
		medForm.clear();
		//Collection coll = UIJuvenileProfileMedicalHelper.getMedicationCodes(medForm.getSearchMedication());
		
		//Added
		GetJuvenileMedicationTypeCodesEvent reqEvent = (GetJuvenileMedicationTypeCodesEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEMEDICATIONTYPECODES);
	  	reqEvent.setDosageAdmin(medForm.getSearchMedication().getDelivery());
	  	reqEvent.setStrength(medForm.getSearchMedication().getStrength());
	  	reqEvent.setMedicationTypeId(medForm.getSearchMedication().getMedCode());
	  	reqEvent.setUsage(medForm.getSearchMedication().getUsage());
	  	reqEvent.setMedication(medForm.getSearchMedication().getMedication());
	  	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		//Handle error thrown as ErrorResponseEvent from the command, if there is any
		//Expected error: Number of results matching this criteria is greater than 2000.
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if(error != null) {
			sendToErrorPage(aRequest, error.getMessage());			
		}
		Collection admTypes = MessageUtil.compositeToCollection(compositeResponse, JuvenileMedicationTypeResponseEvent.class);
	  	
	  	
	  	//End
		
		medForm.setMedications(admTypes);
			if(medForm.getMedications().size()==0 && error == null)
			sendToErrorPage(aRequest, "error.noRecords");
	
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward select(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = aMapping.findForward(UIConstants.SELECT_SUCCESS);
			MedicalForm medForm = (MedicalForm) aForm;
			MedicalForm.Medication currentMed = medForm.getMedicRec();
			String medication = medForm.getSelectedMed();
			Collection coll =  medForm.getMedications();
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				JuvenileMedicationTypeResponseEvent newMedic = (JuvenileMedicationTypeResponseEvent)iter.next();
				if(medication.equals(newMedic.getMedicationTypeId()))
				{
					currentMed.setMedication(newMedic.getTradeName()+ " " + newMedic.getDosageAdmin()+ "; " +newMedic.getStrength());
					currentMed.setMedCode(newMedic.getMedicationTypeId());
					currentMed.setDosage(newMedic.getStrength());
					break;
				}
			}
			medForm.setActionType("validateMedCode");
			return forward;
		}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		MedicalForm medForm = (MedicalForm) aForm;
		medForm.getSearchMedication().clearSearchCriteria();
		medForm.setMedications(new ArrayList());
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
}
