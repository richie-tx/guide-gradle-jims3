package ui.juvenilecase.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.ICode;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;

public class DisplayJuvenileTattooAndScarsCreateSummaryAction extends LookupDispatchAction {
	/**
	 * @roseuid 42AF409E000F
	 */
	public DisplayJuvenileTattooAndScarsCreateSummaryAction() {

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42AF3EE1007F
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {		
		JuvenilePhysicalCharacteristicsForm jcForm = (JuvenilePhysicalCharacteristicsForm) aForm;
		jcForm.setStatus("");
		String[] tattoos = aRequest.getParameterValues("selectedTattoos");
		String[] scars = aRequest.getParameterValues("selectedScars");
		jcForm.setSelectedScarsArray(scars);
		jcForm.setSelectedTattoosArray(tattoos);
		String otherTattooComments = aRequest.getParameter("newOtherTattooComments");
		String selectedTatoos = "";
		String selectedScars = "";
		
		/*if((tattoos == null || tattoos.length <=0) && (scars == null || scars.length <= 0)){
			jcForm.setStatus(UIConstants.ERROR);
			return aMapping.findForward(UIConstants.ERROR);
		}*/
		
		if (tattoos != null) {
			for(int i=0;i<=tattoos.length-1;i++){
				if(i == 0)
					selectedTatoos = CodeHelper.getCodeDescriptionByCode(CodeHelper.getTattoos( true ), tattoos[i]);
				else
					selectedTatoos = selectedTatoos + "; " + CodeHelper.getCodeDescriptionByCode(CodeHelper.getTattoos( true ), tattoos[i]);
			}
		}
		if (scars != null) {
			for(int i=0;i<=scars.length-1;i++){
				if(i == 0)
					selectedScars = CodeHelper.getCodeDescriptionByCode(CodeHelper.getScarMarks( true ), scars[i]);
				else 
					selectedScars = selectedScars + "; "+ CodeHelper.getCodeDescriptionByCode(CodeHelper.getScarMarks( true ), scars[i]) ;
			}
		}
		
		jcForm.setAllTatoosDesc(selectedTatoos);
		jcForm.setAllScars(selectedScars);		
		
		//Code moved for BUG REPORT #29003
		
		jcForm.processCodeDescriptions();
		jcForm.setEntryDate(DateUtil.dateToString(new Date(),
				"MM/dd/yyyy HH:mm"));
		jcForm.setAction(UIConstants.SUMMARY);
		Object[] prev_Tattoos = new Object[0];
		Object[] prev_Scars = new Object[0];
		
		if(jcForm.getAllTatoosDescWithCode() != null){
			prev_Tattoos = jcForm.getAllTatoosDescWithCode().toArray();
		} 
		if(jcForm.getAllScarsWithCode()!= null){
			prev_Scars = jcForm.getAllScarsWithCode().toArray();
		}
		
		boolean tattooValueFound = true;
		boolean scarsValueFound = true;
		
		if(tattoos == null){			
			tattoos =  new String[0];
		}
		if(scars == null){			
			scars =  new String[0];
		}
		if(tattoos.length != prev_Tattoos.length || scars.length != prev_Scars.length){
			if(!otherTattooComments.equals("")){
				jcForm.setNewOtherTattooComments(jcForm.getAllOtherTattooComments() + " " + otherTattooComments );
			} else {
				jcForm.setNewOtherTattooComments(jcForm.getAllOtherTattooComments());
			}
			return aMapping.findForward(UIConstants.SUCCESS);
		} else {			
				for(int i=tattoos.length-1;i >= 0;i--){					
					tattooValueFound = false;
					for(int j=prev_Tattoos.length-1;j>=0;j--){
						ICode code = (ICode) prev_Tattoos[j];
						if(tattoos[i].equals(code.getCode())){
							tattooValueFound = true;
							break;
						}
					}
					if(!tattooValueFound){
						break;
					}
				}
			
		
				for(int i=scars.length-1;i >= 0;i--){	
					scarsValueFound = false;
						for(int j=prev_Scars.length-1;j>=0;j--){
							ICode code = (ICode) prev_Scars[j];
							if(scars[i].equals(code.getCode())){
								scarsValueFound = true;
								break;						
							}  
						}
						if(!scarsValueFound){
							break;
						}
				}
		
			if(!scarsValueFound || !tattooValueFound){
				if(!otherTattooComments.equals("")){
					jcForm.setNewOtherTattooComments(jcForm.getAllOtherTattooComments() + " " + otherTattooComments );
				} else {
					jcForm.setNewOtherTattooComments(jcForm.getAllOtherTattooComments());
				}
				return aMapping.findForward(UIConstants.SUCCESS);				
			}
			jcForm.setStatus(UIConstants.ERROR);
			return aMapping.findForward(UIConstants.ERROR);
		}
		
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}

}
