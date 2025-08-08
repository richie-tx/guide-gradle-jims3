//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileCasefileFeeSelectionAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.referral.SaveTransferredOffenseReferralsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.form.TransferredOffenseForm;

/**
 * This action is used in profile and casefile transfer offense referral create
 * pages as both pages perform the same functionality but different tabs
 */
public class HandleJuvenileProfileTransferredOffensesSelectionAction extends JIMSBaseAction
{
    /* (non-Javadoc)
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.add", "addToList");
	keyMap.put("button.remove", "removeFromList");
	keyMap.put("button.validateOffenseCode", "validateOffenseCode");
	keyMap.put("button.searchForOffenseCode", "goToOffenseSearch");
	keyMap.put("button.link", "goToOffenseSearch");
	keyMap.put("button.finish", "saveInfo");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
    }

    /**
     * @roseuid 467FB5C80014
     */
    public HandleJuvenileProfileTransferredOffensesSelectionAction()
    {

    }

    public ActionForward addToList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	// validate offense code just in case user has not 
	JuvenileOffenseCodeResponseEvent jocEvent = validateOffenseCd(form.getOffenseId());
	if (jocEvent != null)
	{
	    SaveTransferredOffenseReferralsEvent stoEvent = new SaveTransferredOffenseReferralsEvent();
	    String refNum = form.getSelectedTransfer(); // value from selected Transfer Offense
	    stoEvent.setRecId(UIConstants.EMPTY_STRING);
	    stoEvent.setJuvenileNum(form.getJuvenileNumber());
	    stoEvent.setReferralNum(refNum);
	    stoEvent.setCountyId(form.getCountyId());
	    String desc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_COUNTY, form.getCountyId());
	    int idx = desc.indexOf(PDCodeTableConstants.JUVENILE_COUNTY);
	    if (idx > 2)
	    {
		desc = desc.substring(0, idx);
	    }
	    stoEvent.setCountyDesc(desc);
	    stoEvent.setPersonId(form.getPersonId()); // added for user story 11081.
	    stoEvent.setOffenseId(form.getOffenseId());
	    stoEvent.setOffenseDesc(jocEvent.getShortDescription());
	    stoEvent.setOffenseCategory(jocEvent.getCategory());
	    stoEvent.setDpsCode(jocEvent.getDpsOffenseCode());
	    stoEvent.setOffenseDateStr(form.getOffenseDateStr());
	    stoEvent.setAdjudicationDateStr(form.getAdjudicationDateStr());
	    stoEvent.setProbationStartDateStr(form.getProbationStartDateStr());
	    stoEvent.setProbationEndDateStr(form.getProbationEndDateStr());
	    List temp = new ArrayList(form.getNewTransferredOffensesList());
	    temp.add(stoEvent);
	    form.setNewTransferredOffensesList(temp);
	    temp = new ArrayList(form.getAvailableTransferredOffenseReferralList());
	    // reset value so radio button does not display on jsp
	    for (int x = 0; x < temp.size(); x++)
	    {
		JuvenileCasefileTransferredOffenseResponseEvent jpEvent = (JuvenileCasefileTransferredOffenseResponseEvent) temp.get(x);
		if (refNum.equals(jpEvent.getReferralNum()))
		{
		    jpEvent.setAvailable("N");
		    break;
		}
	    }
	    form.setSelectedTransfer(UIConstants.EMPTY_STRING);
	    form.setCountyId(UIConstants.EMPTY_STRING);
	    form.setOffenseId(UIConstants.EMPTY_STRING);
	    form.setOffenseDesc(UIConstants.EMPTY_STRING);
	    form.setOffenseDateStr(UIConstants.EMPTY_STRING);
	    form.setAdjudicationDateStr(UIConstants.EMPTY_STRING);
	    form.setProbationStartDateStr(UIConstants.EMPTY_STRING);
	    form.setProbationEndDateStr(UIConstants.EMPTY_STRING);
	    form.setPersonId(UIConstants.EMPTY_STRING);
	}
	else
	{
	    form.setValidateMsg("Offense Code entered is not valid.");
	    form.setOffenseDesc(UIConstants.EMPTY_STRING);
	}
	String forwardStr = "addSuccess" + form.getFromPage();
	return aMapping.findForward(forwardStr);
    }

    public ActionForward removeFromList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	String refNum = UIConstants.EMPTY_STRING;
	String selectedId = aRequest.getParameter("selectedId");
	List inList = new ArrayList(form.getNewTransferredOffensesList());
	int len = form.getNewTransferredOffensesList().size();
	if (len == 1)
	{
	    form.setNewTransferredOffensesList(new ArrayList());
	    List temp = new ArrayList(form.getAvailableTransferredOffenseReferralList());
	    // reset value so radio button displays on jsp
	    for (int y = 0; y < temp.size(); y++)
	    {
		JuvenileCasefileTransferredOffenseResponseEvent jpEvent = (JuvenileCasefileTransferredOffenseResponseEvent) temp.get(y);
		jpEvent.setAvailable("Y");
	    }
	}
	else
	{
	    List outList = new ArrayList();
	    String selNum = "";
	    for (int x = 0; x < len; x++)
	    {
		SaveTransferredOffenseReferralsEvent stoEvent = (SaveTransferredOffenseReferralsEvent) inList.get(x);
		selNum = "" + x;
		if (!selectedId.equalsIgnoreCase(selNum))
		{
		    outList.add(stoEvent);
		}
		else
		{
		    refNum = stoEvent.getReferralNum();
		    // reset value so radio button displays on jsp
		    for (int y = 0; y < form.getAvailableTransferredOffenseReferralList().size(); y++)
		    {
			JuvenileCasefileTransferredOffenseResponseEvent jpEvent = (JuvenileCasefileTransferredOffenseResponseEvent) form.getAvailableTransferredOffenseReferralList().get(y);
			if (refNum.equals(jpEvent.getReferralNum()))
			{
			    jpEvent.setAvailable("Y");
			    break;
			}
		    }
		}
	    }
	    form.setNewTransferredOffensesList(outList);
	    outList = null;
	    inList = null;
	    selNum = null;
	}
	String forwardStr = "removeSuccess" + form.getFromPage();
	return aMapping.findForward(forwardStr);
    }

    public ActionForward saveInfo(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	SaveTransferredOffenseReferralsEvent saveEvent = new SaveTransferredOffenseReferralsEvent();
	List inList = form.getNewTransferredOffensesList();
	for (int x = 0; x < inList.size(); x++)
	{
	    SaveTransferredOffenseReferralsEvent inputEvent = (SaveTransferredOffenseReferralsEvent) inList.get(x);
	    saveEvent = new SaveTransferredOffenseReferralsEvent();
	    saveEvent.setJuvenileNum(inputEvent.getJuvenileNum());
	    saveEvent.setReferralNum(inputEvent.getReferralNum());
	    saveEvent.setCountyId(inputEvent.getCountyId());
	    saveEvent.setOffenseId(inputEvent.getOffenseId());
	    saveEvent.setOffenseCategory(inputEvent.getOffenseCategory());
	    saveEvent.setDpsCode(inputEvent.getDpsCode());
	    saveEvent.setPersonId(inputEvent.getPersonId());// added for user story 11081.
	    Date xDate = DateUtil.stringToDate(inputEvent.getOffenseDateStr(), DateUtil.DATE_FMT_1);
	    saveEvent.setOffenseDate(xDate);
	    xDate = DateUtil.stringToDate(inputEvent.getAdjudicationDateStr(), DateUtil.DATE_FMT_1);
	    saveEvent.setAdjudicationDate(xDate);
	    saveEvent.setProbationStartDate(DateUtil.stringToDate(inputEvent.getProbationStartDateStr(), DateUtil.DATE_FMT_1));
	    saveEvent.setProbationEndDate(DateUtil.stringToDate(inputEvent.getProbationEndDateStr(), DateUtil.DATE_FMT_1));
	    MessageUtil.postRequest(saveEvent);
	}
	// add new values to existing list
	//	   List inList = new ArrayList(form.getNewTransferredOffensesList() );
	List outList = new ArrayList(form.getExistingTransferredOffensesList());
	for (int y = 0; y < inList.size(); y++)
	{
	    SaveTransferredOffenseReferralsEvent stoEvent = (SaveTransferredOffenseReferralsEvent) inList.get(y);
	    JuvenileCasefileTransferredOffenseResponseEvent jcEvent = new JuvenileCasefileTransferredOffenseResponseEvent();
	    jcEvent.setReferralNum(stoEvent.getReferralNum());
	    jcEvent.setCountyId(stoEvent.getCountyId());
	    jcEvent.setCountyDesc(stoEvent.getCountyDesc());
	    jcEvent.setPersonId(stoEvent.getPersonId()); // added for user story 11081.
	    jcEvent.setOffenseCode(stoEvent.getOffenseId());
	    jcEvent.setOffenseShortDesc(stoEvent.getOffenseDesc());
	    jcEvent.setCategory(stoEvent.getOffenseCategory());
	    jcEvent.setDpsCode(stoEvent.getDpsCode());
	    Date xDate = DateUtil.stringToDate(stoEvent.getOffenseDateStr(), UIConstants.DATE_FMT_1);
	    jcEvent.setOffenseDate(xDate);
	    xDate = DateUtil.stringToDate(stoEvent.getAdjudicationDateStr(), UIConstants.DATE_FMT_1);
	    jcEvent.setAdjudicationDate(xDate);
	    outList.add(jcEvent);
	}
	form.setExistingTransferredOffensesList(UIJuvenileTransferredOffenseReferralHelper.sortOffenses(outList));
	form.setNewTransferredOffensesList(new ArrayList());
	form.setConfirmMsg("Transferred Offenses successfully saved.");
	inList = null;
	outList = null;

	String forwardStr = "finishSuccess" + form.getFromPage();
	return aMapping.findForward(forwardStr);
    }

    public ActionForward validateOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	form.setValidateMsg("Offense Code entered is not valid.");
	form.setOffenseDesc(UIConstants.EMPTY_STRING);
	JuvenileOffenseCodeResponseEvent jocEvent = validateOffenseCd(form.getOffenseId());
	if (jocEvent != null)
	{
	    String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    form.setOffenseDesc(lit);
	    form.setErrMessage(UIConstants.EMPTY_STRING);
	    form.setValidateMsg("Offense Code entered is valid.");
	}
	String forwardStr = UIConstants.VALIDATE_SUCCESS + form.getFromPage();
	return aMapping.findForward(forwardStr);
    }

    private JuvenileOffenseCodeResponseEvent validateOffenseCd(String offenseCd)
    {
	JuvenileOffenseCodeResponseEvent jpEvent = null;
	GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
	jocEvent.setAlphaCode(offenseCd);
	List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
	if (events != null & !events.isEmpty())
	{
	    for (int x = 0; x < events.size(); x++)
	    {
		JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
		// Added DPS code blank for bug 37346 
		if (!"Y".equals(respEvent.getInactiveInd()) && !"Y".equals( respEvent.getDiscontCode()) && offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
		{

		    jpEvent = new JuvenileOffenseCodeResponseEvent();
		    jpEvent.setCategory(respEvent.getCategory());
		    jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
		    jpEvent.setShortDescription(respEvent.getShortDescription());
		    break;
		}
	    }
	}
	return jpEvent;
    }

    public ActionForward goToOffenseSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	/*	//  This code to retrieve code table data from JJS code table is based on code
		//  from 'Search Code Table' left Nav option 
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetCodetableRecordEvent request =
				(GetCodetableRecordEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODETABLERECORD);

			request.setCodetableRegId("27");
			request.setType("CX");

			dispatch.postEvent(request);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			ErrorResponseEvent error = 
				(ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse,	ErrorResponseEvent.class);
			if (error != null)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.generic", error.getMessage()));
				saveErrors(aRequest, errors);
				String forwardStr = UIConstants.FAILURE + form.getFromPage(); 
				return aMapping.findForward(forwardStr); 
			}

			Collection codetableDataList = 
				MessageUtil.compositeToCollection(compositeResponse, CodetableDataResponseEvent.class);

			if (codetableDataList == null || codetableDataList.isEmpty())
			{
				codetableDataList = new ArrayList();
			}
			Collections.sort((ArrayList) codetableDataList);
	// load codeTableDataList info into display event
			JuvenileCasefileOffenseCodeResponseEvent ocEvent = new  JuvenileCasefileOffenseCodeResponseEvent();
			List temp1 = (List) codetableDataList;
			List temp2 = new ArrayList();
			List eventValues = new ArrayList();
	    	String inactive = UIConstants.EMPTY_STRING;
			for (int x = 0; x<temp1.size(); x++)
			{
				CodetableDataResponseEvent ctrEvent = (CodetableDataResponseEvent) temp1.get(x);
				ocEvent = new  JuvenileCasefileOffenseCodeResponseEvent();
				eventValues = CollectionUtil.iteratorToList(ctrEvent.getValueMap().values().iterator() );
				inactive = UIConstants.EMPTY_STRING;
				// value of 'y' is based on DISPLAYORDER value in CODETBLREGATTR table for CODETBLREG_ID = 27
				for (int y=0; y<eventValues.size(); y++) {
					if (y == 0){
						 ocEvent.setAlphaCode(eventValues.get(y).toString());
					}
					if (y == 1){
						 ocEvent.setNumericCode(eventValues.get(y).toString());
					}
					if (y == 2){
						 ocEvent.setCategory(eventValues.get(y).toString());
					}
					if (y == 5){
						 ocEvent.setReportGroup(eventValues.get(y).toString());
					}
					if (y == 6){
						 ocEvent.setShortDescription(eventValues.get(y).toString());
					}
					if (y == 7){
						 ocEvent.setLongDescription(eventValues.get(y).toString());
					}
					if (y == 9){
						 ocEvent.setDpsCode(eventValues.get(y).toString());
					}
					if (y == 10){
						 ocEvent.setSeverity(eventValues.get(y).toString());
					}
					if (y == 11){
						 ocEvent.setSeverityType(eventValues.get(y).toString());
					}
					if (y == 12){
						 ocEvent.setSeveritySubType(eventValues.get(y).toString());
					}
					if (y == 13){
						 inactive = eventValues.get(y).toString();
					}
				}
				if (UIConstants.EMPTY_STRING.equals(inactive)) {
					temp2.add(ocEvent);
				}	
			}*/
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	List codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);

	Collections.sort(codes);

	form.setCodetableDataList(codes);

	return aMapping.findForward(UIConstants.OFFENSE_SUCCESS);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	form.clear();
	String forwardStr = UIConstants.BACK + form.getFromPage();
	return aMapping.findForward(forwardStr);

    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	TransferredOffenseForm form = (TransferredOffenseForm) aForm;
	form.clear();
	String forwardStr = UIConstants.CANCEL + form.getFromPage();
	return aMapping.findForward(forwardStr);
    }
}
