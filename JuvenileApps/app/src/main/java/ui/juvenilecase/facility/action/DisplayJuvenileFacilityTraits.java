package ui.juvenilecase.facility.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvenileFacilityTraits extends LookupDispatchAction{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.populateTraits","populateTraits");
		keyMap.put("button.find","find");
		return keyMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B350171
	 */
	public ActionForward populateTraits(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);
		traitsForm.clearAll();
		traitsForm.setAction(UIConstants.VIEW);
		traitsForm.setUIFacility(true);
		JuvenileProfileForm jpForm = UIJuvenileHelper.getHeaderForm(aRequest,true);
		jpForm.clear();
		
		String juvNumSearch = aRequest.getParameter("juvNum");
		//String juvNum = jpForm.getJuvenileNum();
		// variable ifModify, for US 14452, to disable Adding traits in Modify flow
		String isModifyAdmit = aRequest.getParameter("modifyAdmit");
		traitsForm.setIsModifyAdmitView(isModifyAdmit); //bug #50640
		
		String isReleaseView = aRequest.getParameter("releaseView");
		traitsForm.setIsReleaseView(isReleaseView); //bug #50640
		
		String isAdmitView = aRequest.getParameter("admitView");
		traitsForm.setIsAdmitView(isAdmitView); //bug #50640
		
		String supervisionNum = aRequest.getParameter("supervisionNum");
		if(traitsForm.getSupervisionNum() != null && supervisionNum != null && !supervisionNum.equals("")){
		    traitsForm.setSupervisionNum(supervisionNum); 
		}		
		 
		// if statement true, jpForm probably empty
		//if (juvNum == null || "".equalsIgnoreCase(juvNum) ){
			//juvNum = UIJuvenileHelper.getJuvenileNumber(aRequest,false,true);
			// just in case juvenile number not found
			if (juvNumSearch != null && !"".equalsIgnoreCase( juvNumSearch ) ){
				GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
				getJuvProfileEvent.setJuvenileNum(juvNumSearch);
				CompositeResponse response = MessageUtil.postRequest(getJuvProfileEvent);
				JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent)
						MessageUtil.filterComposite( response, JuvenileProfileDetailResponseEvent.class);
				if (juvProfileRE != null){
					UIJuvenileHelper.populateHeaderFormEvent( jpForm, juvProfileRE);
				}
			} /*else {
				jpForm.clear();
			}	*/
		//}

		return link(aMapping,aForm,aRequest,aResponse);	
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest,true);
		
		traitsForm.setAction( UIConstants.FIND );

		return link(aMapping,aForm,aRequest,aResponse);	
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward link(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_FACILITY_TRAITS);
        traitsForm.setTraitTypeId("26");
        traitsForm.clear();
        //Added for U.S. #42660
        HttpSession session = aRequest.getSession();
        JuvenileDetentionFacilitiesResponseEvent detentionRespEvent = (JuvenileDetentionFacilitiesResponseEvent) session.getAttribute("FACILITY_DETENTION_RESP");
        if(detentionRespEvent!=null){
        	//traitsForm.setCurrentFacilityOID(detentionRespEvent.getOriginalAdmitOID());
        	//consider the current detentionid even if original present in case of pre transfer add another property
        	if(detentionRespEvent.getOriginalAdmitOID()==null){ // original Admit OID is null get the detentionId.
        		traitsForm.setCurrentFacilityOID(detentionRespEvent.getDetentionId());
        	}
        	else
        	{
        	    traitsForm.setCurrentFacilityOID(detentionRespEvent.getOriginalAdmitOID());
        	    traitsForm.setTransferAdmitOID(detentionRespEvent.getDetentionId());
        	}
        }
        //Added for U.S. #42660
        String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        traitsForm.setJuvenileNumber(juvenileNum);
        traitsForm.setUIFacility(true);
        traitsForm.setUICasefile(true); // single casefile for facility so make it true.
        traitsForm.initializeTraitForm(true);
        traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));
        return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
}
