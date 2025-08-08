package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.SaveProdSupportLastAttorneyEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.PACTRiskNeedLevel;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;


public class PerformUpdateLastAttorneyAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	
	keyMap.put("button.validateBarNumber", "validateBarNumber");
	keyMap.put("button.searchAttorney", "searchAttorney");
	keyMap.put("button.validateGalBarNumber", "validateGalBarNumber");
	keyMap.put("button.searchGalAttorney", "searchGalAttorney");
	keyMap.put("button.findAttorney", "findAttorney");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.select", "returnSelect");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.back", "back");
	keyMap.put("button.updateRecord", "updateLastAttorney");
    }

    private Logger log = Logger.getLogger("PerformUpdateLastAttorneyAction");

    public ActionForward updateLastAttorney (ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String juvNum = regform.getJuvenileId();
	JJSLastAttorney record = new JJSLastAttorney();
	boolean isChange = false;
	
	Iterator it = JJSLastAttorney.findAll("juvenileNum",juvNum);
	
	while(it.hasNext()){
	    
	    JJSLastAttorney rec = (JJSLastAttorney) it.next();
	    record=rec;    
	    
	}
	
	
	SaveProdSupportLastAttorneyEvent updateEvent =(SaveProdSupportLastAttorneyEvent)
	EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEPRODSUPPORTLASTATTORNEY );
	
	updateEvent.setJuvenileNum(juvNum);
	updateEvent.setLastAttorneyID(record.getLastAttorneyID());
	String atyBar = regform.getAttRecord().getAtyBarNum();
	String atyName =regform.getAttRecord().getAtyName();
	String galBar = regform.getAttRecord().getGalBarNum();
	String galName = regform.getAttRecord().getGalName();
	String newAtyBar = regform.getAtyBarNum();
	String newAtyName = regform.getAtyName();
	String newGalBar = regform.getGalBarNum();
	String newGalName = regform.getGalAttorneyName();
	String attConVal = regform.getAttRecord().getAttConnect();
	String newAttConVal = regform.getAttorneyConnection();
	String courtId 	= regform.getOriginalCourtId();
	String newCourtId = regform.getCourtId();
	String detentionId = regform.getOriginalDetentionId();
	String newDetentionId = regform.getDetentionId();
	
	//Referral update
	boolean isAtyBarChanged = checkIfTwoValuesChanged(newAtyBar, atyBar);
	if (isAtyBarChanged)
	{	
		updateEvent.setAtyBarNum(newAtyBar);
		isChange = true;
	}
	else if(atyBar != null){
	    updateEvent.setAtyBarNum(atyBar);
	    regform.setAtyBarNum(null);
	}
	
	//CasefileID update
	boolean isAtyNameChanged = checkIfTwoValuesChanged(newAtyName, atyName);
	if (isAtyNameChanged)
	{	
	updateEvent.setAtyName(newAtyName);
	isChange = true;
	}
	else if(atyName != null){
	updateEvent.setAtyName(atyName);
	regform.setAtyName(null);
	}		


	
	boolean isGalBarChanged = checkIfTwoValuesChanged(newGalBar, galBar);
	if (isGalBarChanged)
	{	
		updateEvent.setGalBarNum(newGalBar);
		isChange = true;
	}
	else if(galBar != null){
	    updateEvent.setGalBarNum(galBar);
	    regform.setGalBarNum(null);
	}
	boolean isGalNameChanged = checkIfTwoValuesChanged(newGalName,galName);
	if (isGalNameChanged)
	{	
		updateEvent.setGalName(newGalName);
		isChange = true;
	}
	else if(galName != null){
	    
	    updateEvent.setGalName(galName);
	    regform.setGalAttorneyName(null);
	}
	boolean isAttConValChanged = checkIfTwoValuesChanged(newAttConVal, attConVal);
	if (isAttConValChanged)
	{	
		updateEvent.setAttConnect(newAttConVal);
		isChange = true;
	}
	else if(attConVal != null){
	    updateEvent.setAttConnect(attConVal);
	    regform.setAttorneyConnection(null);
	}
	
	
	    boolean isCourtIdChanged = checkIfCourtIdChanged( nvl(newCourtId), nvl(courtId) );
	    if ( isCourtIdChanged ){
		isChange = true;
	    }
	    updateEvent.setJjclcourtId(newCourtId);
	 
	
	
	    boolean isDetentionIdChanged = checkIfCourtIdChanged( nvl(newDetentionId), nvl(detentionId) );
	    if ( isDetentionIdChanged  ) {
		isChange = true;
	    }
	    updateEvent.setJjcldetentionId(newDetentionId);

	
	// check that a value changed before updating
	if(!isChange){
		regform.setMsg("At least one value needs to be changed.");
		return (aMapping.findForward("error"));
	}

	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
	dispatch.postEvent( updateEvent );
	CompositeResponse composite = (CompositeResponse)dispatch.getReply();
	
	MessageUtil.processReturnException( composite );
	regform.setMsg("");

	return aMapping.findForward(UIConstants.SUCCESS);
    }
    public ActionForward validateBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setBarNum(aRequest.getParameter("barNumberId"));
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    form.setAtyName("");
	    form.setAtyBarNum("");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	if (attorneyDataList != null & !attorneyDataList.isEmpty())
	{
	    for (int x = 0; x < attorneyDataList.size();)
	    {
		AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
		attorneyRespEvt.setAttyName(respEvent.getAttyName());
		attorneyRespEvt.setBarNum(respEvent.getBarNum());
		attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
		break;
	    }
	}
	if (attorneyRespEvt != null)
	{
	    
	    //reset back on the object 
	    ActionMessages messageHolder = new ActionMessages();
	    form.setAtyName(attorneyRespEvt.getAttyName());
	    form.setAtyBarNum(attorneyRespEvt.getBarNum());
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	return aMapping.findForward("lastAttorneyUpdate");
    }


       public ActionForward validateGalBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setBarNum(aRequest.getParameter("galBarNumberId"));
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    form.setGalAttorneyName("");
	    form.setGalBarNum("");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	if (attorneyDataList != null & !attorneyDataList.isEmpty())
	{
	    for (int x = 0; x < attorneyDataList.size();)
	    {
		AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
		attorneyRespEvt.setAttyName(respEvent.getAttyName());
		attorneyRespEvt.setBarNum(respEvent.getBarNum());
		attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
		break;
	    }
	}
	if (attorneyRespEvt != null)
	{
	    
	    //reset back on the object 
	    ActionMessages messageHolder = new ActionMessages();
	    form.setGalAttorneyName(attorneyRespEvt.getAttyName());
	    form.setGalBarNum(attorneyRespEvt.getBarNum());
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	return aMapping.findForward("lastAttorneyUpdate");
    }

    public ActionForward searchAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setAttorneyName("");
	form.setAction("searchAttorney");
	HttpSession session = aRequest.getSession();
	//form.setAttorneyName(form.getAttRecord().getAtyName());
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward("attorneySuccess");
    }

    public ActionForward searchGalAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setAttorneyName("");
	form.setAction("searchGalAttorney");
	HttpSession session = aRequest.getSession();
	//form.setGalAttorneyName(form.getAttRecord().getGalName());
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward("attorneySuccess");
    }

    public ActionForward findAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String attorneyName = aRequest.getParameter("attorneyName");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setAttorneyName(attorneyName);
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null)
	{
	    sendToErrorPage(aRequest, error.getMessage());
	    return aMapping.findForward("failure");
	}

	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);

	if (attorneyDataList == null || attorneyDataList.isEmpty())
	{
	    attorneyDataList = new ArrayList<AttorneyNameAndAddressResponseEvent>();
	}
	Collections.sort((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList);
	form.setAttorneyDataList(attorneyDataList);
	if (attorneyDataList.size() == 0)
	{
	    form.setErrMessage("No records found for supplied search criteria");
	}

	return aMapping.findForward("attorneySuccess");
    }

    public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	ProdSupportForm form = (ProdSupportForm) aForm;
	String attyName = "";
	String barNum = "";
	String attyNameHist = "";

	form.setValidateMsg("");
	List<AttorneyNameAndAddressResponseEvent> wrkList = new ArrayList<AttorneyNameAndAddressResponseEvent>(form.getAttorneyDataList());
	String selVal = form.getSelectedValue();
	for (int x = 0; x < wrkList.size(); x++)
	{
	    AttorneyNameAndAddressResponseEvent jcoEvent = (AttorneyNameAndAddressResponseEvent) wrkList.get(x);
	    if (selVal.equals(jcoEvent.getBarNum()))
	    {
		barNum = jcoEvent.getBarNum();
		attyName = jcoEvent.getAttyName();
		attyNameHist = jcoEvent.getAttyNameHistory();
		break;
	    }
	}
	if(form.getAction().equalsIgnoreCase("searchAttorney")){
		form.setAtyName(attyName);
		form.setAtyBarNum(barNum);
		
	}
	else if(form.getAction().equalsIgnoreCase("searchGalAttorney")){
	    	form.setGalAttorneyName(attyName);
		form.setGalBarNum(barNum);
	}
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAction("");
	selVal = null;
	wrkList = null;
	form.setAttorneyDataList(null);
	return aMapping.findForward("lastAttorneyUpdate");
    }

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward("attorneySuccess");
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	return aMapping.findForward("lastAttorneyUpdate");
    }
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	return aMapping.findForward("lastAttorneyUpdate");
    }


    /**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null && OlderValue != null && !newValue.equals("")){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null && OlderValue == null){
				result = true;
		}
		
		return result;
	}
	
	private boolean checkIfCourtIdChanged(String newValue, String oldValue){
	    	boolean result = false;
	    	
	    	if ( !newValue.equals(oldValue) ){
	    	    result = true;
	    	}
		
		return result;
	}
	
	private String nvl(String value){
	    return ( value != null && value.length() > 0) ? value: "";
	}


}
