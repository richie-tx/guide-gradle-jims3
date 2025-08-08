//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\HandleMentalHealthDSMVCodeSearchAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;

public class HandleMentalHealthDSMVCodeSearchAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.findCodes", "findCodes");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.select", "returnSelect");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}	
	
	/**
	 * @roseuid 45D4AEAD0237
	 */
	public HandleMentalHealthDSMVCodeSearchAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C9003B7
	 */
	   public ActionForward findCodes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		  /* TestingSessionForm sessForm = (TestingSessionForm) aForm;
		   sessForm.setSelectedCode(UIConstants.EMPTY_STRING);
		   sessForm.setErrMessage(UIConstants.EMPTY_STRING);
		   GetJuvenileSimpleCodeTableCodesEvent gsEvent = (GetJuvenileSimpleCodeTableCodesEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILESIMPLECODETABLECODES);
		   gsEvent.setCodeTableName("DSM_CODES");
		   gsEvent.setCode(sessForm.getSearchCode() );
		   gsEvent.setDescription(sessForm.getSearchDesc() );
		   CompositeResponse response = postRequestEvent(gsEvent);
		   List temp1 = MessageUtil.compositeToList(response,JuvenileSimpleCodeTableCodesResponseEvent.class);
		   sessForm.setCodeSearchResultList(temp1);
		   if (temp1.size() == 0){
			   sessForm.setErrMessage("No records found for supplied search criteria");
		   }*/
		   return aMapping.findForward(UIConstants.SUCCESS);  
	   }
	   
	   public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   TestingSessionForm sessForm = (TestingSessionForm) aForm;
/*		   sessForm.setSearchCode(UIConstants.EMPTY_STRING);
		   sessForm.setSearchDesc(UIConstants.EMPTY_STRING);
		   sessForm.setCodeSearchResultList(new ArrayList());
		   sessForm.setErrMessage(UIConstants.EMPTY_STRING);*/
		   return aMapping.findForward(UIConstants.SUCCESS); 
	   }

	   public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   TestingSessionForm sessForm = (TestingSessionForm) aForm;
		   String forwardStr = UIConstants.RETURN_SUCCESS;
/*		   sessForm.setErrMessage(UIConstants.EMPTY_STRING);
		   List wrkList = new ArrayList(sessForm.getCodeSearchResultList());
		   List temp = sessForm.getDiagnosisCodeList();
		   String selVal = sessForm.getSelectedValue();
		   boolean matchFound = false;
		   if (temp.size() > 0 ){
			   for (int y=0; y<temp.size(); y++)
			   {
				   CodeResponseEvent cre = (CodeResponseEvent) temp.get(y);
				   if (selVal.equals(cre.getCode() ) )
				   {
					  matchFound = true;
					  int x = y + 1;
					  String msg = "DSMV Code selected is already entered in Diagnosis " + new Integer(x).toString() + "."; 
					  sessForm.setErrMessage(msg);
					  msg = null;
					  forwardStr = UIConstants.SUCCESS;
 					  break;
				   } 
			   }
		   }	   
	   	   if (!matchFound) {
			   for (int x=0; x<wrkList.size(); x++)
			   {
				   JuvenileSimpleCodeTableCodesResponseEvent jcoEvent = (JuvenileSimpleCodeTableCodesResponseEvent) wrkList.get(x);
				   if (selVal.equals(jcoEvent.getCode() ) ) {
					   int y = temp.size() + 1;
					   CodeResponseEvent cre =  new CodeResponseEvent();
					   cre.setCodeId(new Integer(y).toString());
					   cre.setCode(jcoEvent.getCode());
					   cre.setDescription(jcoEvent.getDescription());
					   temp.add(cre);
					   y++;
					   sessForm.setDiagnosisNum(new Integer(y).toString());
						if (temp.size() > 9 ){
							sessForm.setShowDiagnosisInput("");
						}
					   break;
				   }
			   }
			   sessForm.setSelectedValue(UIConstants.EMPTY_STRING);			  
			   sessForm.setCodeSearchResultList(null);
	   	   }
		   selVal = null;
		   wrkList = null;
		   temp = null;*/
		   return aMapping.findForward(forwardStr); 
	   }
	 
	   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   return aMapping.findForward(UIConstants.BACK); 
	   }
	   
	   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   return aMapping.findForward(UIConstants.CANCEL);
	   }

}
