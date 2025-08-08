/*
 * Created on Oct 3rd, 2016
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.form.JuvenileMainForm;


/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayJuvenileJISInformationSummaryAction extends JIMSBaseAction
{
	@Override
	protected void addButtonMapping(Map keyMap){
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
	
	   
	   /**
	    * Set all the selected values in the summary page. 
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return
	    */
	   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse){
		   
		   JuvenileMainForm mainForm = (JuvenileMainForm)aForm;
		   String[] selectJISAgencies = mainForm.getSelectedJISAgencies();
		   
		   if(selectJISAgencies!=null&& selectJISAgencies.length!=0){
			   mainForm.setSelectedJISAgenciesList(SimpleCodeTableHelper.getDescsFromSelCodeIds(PDCodeTableConstants.JUVENILE_JIS_AGENCY, selectJISAgencies));
			   setJISAgencies(mainForm.getSelectedJISAgenciesList(),mainForm);
		   }
		   
		   return aMapping.findForward(UIConstants.NEXT);
	   }


	/**
	 * SetJISAgencies
	 * 
	 * @param setJISAgencies
	 * @param JuvenileMainForm
	 */
	private static void setJISAgencies(List<String> selectJISAgencies,
			JuvenileMainForm mainForm) {	
		
		StringBuffer agency = new StringBuffer();
		if (selectJISAgencies != null) {
			for (int i = 0; i < selectJISAgencies.size(); i++) {
				agency.append(selectJISAgencies.get(i));
				if (i != selectJISAgencies.size() - 1) {
					agency.append(", ");
				}
			}
			mainForm.getCurrentJISInfo().setAgency(agency.toString());
		} else {
			mainForm.getCurrentJISInfo().setAgency("");
		}
		
		mainForm.getCurrentJISInfo().setEntryDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
		return;
	}
	
	   /**
	    * Cancel the jis information create.
	    */
		public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse) {
			ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
			return forward;
		}
}
