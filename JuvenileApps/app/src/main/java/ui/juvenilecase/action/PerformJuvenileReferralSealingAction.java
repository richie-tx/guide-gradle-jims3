/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileReferralSealingSearchResultsAction
 *
 * Date:    2019-09-27
 *
 * Author:  Anju Pillai
 
 */

package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformJuvenileReferralSealingAction extends JIMSBaseAction
{   
   protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "submitSearch");
	//keyMap.put("button.back", "back");
	
    }
    public PerformJuvenileReferralSealingAction()
    {

    }
    
    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A46D8E0234
     */
 
    
   public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {
       ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	String reffNum="";
	//= regform.getJuvenileId();
	if (reffNum == null || reffNum.equals(""))
	{	   
	    reffNum = aRequest.getParameter("reffNum");
	    //regform.setJuvenileId(juvenileNum);
	}
	
	/*GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(juvenileNum);

	JJSJuvenileResponseEvent juvenile = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);
	if (juvenile == null || juvenile.getJuvenileNum() == null || juvenile.getJuvenileNum().equals(""))
	{
	    regform.setToJuvenileId("");
	    //regform.setMsg("Juvenile not found. You must enter a valid JuvenileID.");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.generic", "Juvenile record not found.  Please retry search"));
            saveErrors(aRequest, errors);		
	    return aMapping.findForward("error");	   
	}
	Name name = new Name(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName());

	regform.setJuvenileName(name.getFormattedName());
	regform.setJuvenileSsn(juvenile.getJuvenileSsn());
	regform.setBirthDate(juvenile.getBirthDate());
	regform.setSex(juvenile.getSexId());
	regform.setRace(juvenile.getRaceId());
	//JuvenileProfileDetailResponseEvent aRespEvnt = regform.getProfileDetail( ) ;
	GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	requestEvent.setJuvenileNum(juvenileNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
	JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);

	if (detail.getHispanic() != null)
	{
	    if (detail.getHispanic().equalsIgnoreCase("Y"))
	    {
		regform.setHispanic(UIConstants.YES_FULL_TEXT);
	    }
	    else
	    {
		regform.setHispanic(UIConstants.NO_FULL_TEXT);
	    }
	}
	
	List<String> juvNums = new ArrayList();
	juvNums.add(juvenile.getJuvenileNum());
	GetJuvenileStatusEvent statusReq = new GetJuvenileStatusEvent();
	statusReq.setJuvenileNums(juvNums);
	
	Iterator statIter = JuvenileStatus.findAll(statusReq);
	if( statIter.hasNext() ){
	    
	    JuvenileStatus status = (JuvenileStatus) statIter.next();
	    if(status != null){
		 String statusDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_PROFILE_STATUS, status.getStatusId());
		    regform.setStatusId( statusDesc ); 
	    }
	}
	regform.setRectype(juvenile.getRectype());
	if (juvenile.getRectype().equals("S.JUVENILE"))
	{
	    regform.setToJuvenileId("");
	    //regform.setMsg("Juvenile not found. You must enter a valid JuvenileID.");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.generic", "Juvenile Master Record is Sealed.  This record does not qualify for Juvenile Referral Seal"));
            saveErrors(aRequest, errors);		
	    return aMapping.findForward("error");	   
	}
	ArrayList juvprogrefs = null;

	// Get and set Associated JuvProgRefs
	ListProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (ListProductionSupportJuvenileReferralsEvent) 
						    EventFactory.getInstance(ProductionSupportControllerServiceNames.LISTPRODUCTIONSUPPORTJUVENILEREFERRALS);
	getJuvenileRerralsEvent.setJuvenileId(juvenileNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
	juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
	
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
		//regform.setMsg();
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.generic", "Juvenile referral record not found.  Please retry search."));
		saveErrors(aRequest, errors);		
		return aMapping.findForward("error");
	    }*/
	    return aMapping.findForward(forward);
    }
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
   {
	ProdSupportForm form = (ProdSupportForm) aForm;
	return aMapping.findForward("back");
   }

}
