//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayCommonAppCourtOrderUpdateSummaryAction.java

package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.casefile.form.CommonAppForm;

public class DisplayCommonAppCourtOrderUpdateSummaryAction extends JIMSBaseAction
{
    /**
     * @roseuid 46D4220D01C6
     */
    public DisplayCommonAppCourtOrderUpdateSummaryAction()
    {
    }

    /*
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /*
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.BACK);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 46D41EAD02BA
     */
    public ActionForward getOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CommonAppForm commonAppForm = (CommonAppForm) aForm;
	CommonAppForm.CourtOrder courtOrder = commonAppForm.getCourtOrder();

	Collection<JJSOffenseResponseEvent> bag = commonAppForm.getMostSeriousOffenses();
	if (notNullNotEmptyCollection(bag))
	{
	    for (JJSOffenseResponseEvent off : bag)
	    {
		if (off.getOffenseLevelId().equals(courtOrder.getMostSeriousOffenseId()))
		{
		    courtOrder.setOffenseCode(off.getCitationCode() + " " + off.getCitationSource());
		    courtOrder.setMostSeriousOffense(off.getOffenseDescription());
		    break;
		}
	    }
	}

	if (courtOrder.getMostSeriousOffenseId() == null || (courtOrder.getMostSeriousOffenseId() != null && courtOrder.getMostSeriousOffenseId().length() == 0))
	{
	    courtOrder.setOffenseCode("");
	    courtOrder.setMostSeriousOffense("");
	}

	courtOrder.setTimeStr(new StringBuffer(commonAppForm.getCourtOrder().getTimeNumericId()).append(" ").append(commonAppForm.getCourtOrder().getDetentionTime()).toString());

	courtOrder.setSelectedFacilities(new ArrayList());
	courtOrder.setSelectedFacilities(getSelectedDetentionFacilities(commonAppForm));

	return aMapping.findForward(UIConstants.FIND_SUCCESS);
    }

    /**
     * getOffenseLevel
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward getOffenseLevel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CommonAppForm commonAppForm = (CommonAppForm) aForm;
	CommonAppForm.CourtOrder courtOrder = commonAppForm.getCourtOrder();
	// modified the code from offense level id to offense code id for ER 11021.
	Collection<JJSOffenseResponseEvent> bag = commonAppForm.getCurrentOffenses();
	if (notNullNotEmptyCollection(bag))
	{
	    for (JJSOffenseResponseEvent off : bag)
	    {
		if (off.getOffenseCodeId().equals(courtOrder.getCurrentOffenseId())) //bug fix 26658
		{
		    courtOrder.setOffenseLevel(mapOffenseLevelDescription(off.getCatagory()));
		    courtOrder.setCurrentOffense(off.getOffenseDescription()); //bug fix 26658
		    break;
		}
	    }
	}

	if (courtOrder.getOffenseCode() == null || (courtOrder.getOffenseCode() != null && courtOrder.getOffenseCode().length() == 0))
	{
	    courtOrder.setOffenseLevel("");
	    courtOrder.setOffenseCode("");
	    courtOrder.setOffenseDescription("");
	}

	return aMapping.findForward(UIConstants.FIND_SUCCESS);
    }

    /**
     * submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CommonAppForm commonAppForm = (CommonAppForm) aForm;
	CommonAppForm.CourtOrder courtOrder = commonAppForm.getCourtOrder();

	if (courtOrder.isDeterminateSentence())
	    courtOrder.setTimeStr(new StringBuffer(commonAppForm.getCourtOrder().getTimeNumericId()).append(" ").append(commonAppForm.getCourtOrder().getDetentionTime()).toString());
	else
	    courtOrder.setTimeStr("");

	courtOrder.setSelectedFacilities(new ArrayList());
	courtOrder.setSelectedFacilities(getSelectedDetentionFacilities(commonAppForm));
	courtOrder.setWeaponType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WEAPON_TYPE, courtOrder.getWeaponTypeId()));

	commonAppForm.setAction("summary");

	return aMapping.findForward("success");
    }

    /**
     * link
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CommonAppForm appForm = (CommonAppForm) aForm;
	String courtName = appForm.getCourtOrder().getCourtNameId();
	Collection courts = appForm.getJuvenileCourts();

	Iterator<JuvenileCourtResponseEvent> iter = courts.iterator();
	while (iter.hasNext())
	{
	    JuvenileCourtResponseEvent resp = iter.next();
	    if (resp.getCode().equals(courtName))
	    {
		appForm.getCourtOrder().setJudgeName(resp.getJudgeName());
		appForm.getCourtOrder().setCourtName(resp.getDescription());
		//appForm.getCourtOrder().setGangRelated("");
		appForm.getCourtOrder().setProsecutingAttorneyName("CHIEF PROS ATTY"); //#37121
		break;
	    }
	    else
	    {
		appForm.getCourtOrder().setJudgeName("");
		//appForm.getCourtOrder().setGangRelated("");
		appForm.getCourtOrder().setProsecutingAttorneyName("CHIEF PROS ATTY"); //#37121
	    }
	}

	return aMapping.findForward("nameSuccess");
    }

    /**
     * buildCurrentOffenses
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward buildCurrentOffenses(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CommonAppForm commonAppForm = (CommonAppForm) aForm;

	Collection currentOffensesBag = new ArrayList();
	Collection<JJSOffenseResponseEvent> offenses = commonAppForm.getMostSeriousOffenses();
	// changes for 25750 get the highest sequence number.
	//Sort by sequenceNum 
	List sortFields = new ArrayList();
	sortFields.add(new ReverseComparator(new BeanComparator("sequenceNum")));
	ComparatorChain multiSort = new ComparatorChain(sortFields);
	Collections.sort((List) offenses, multiSort);
	//Sort by sequenceNum

	if (notNullNotEmptyCollection(offenses))
	{
	    String selectedReferral = commonAppForm.getSelectedControllingReferral();
	    //Changes for ER 11021 starts, Modifying the current offense business logic starts
	    //get the petition for the selected referral
	    //Petition
	    List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsRespForReferral(commonAppForm.getJuvenileNum(), selectedReferral);

	    if (selectedReferral != null && !selectedReferral.isEmpty())
	    {

		//Sort by sequenceNum 
		sortFields = new ArrayList();
		sortFields.add(new ReverseComparator(new BeanComparator("sequenceNum")));
		multiSort = new ComparatorChain(sortFields);
		Collections.sort((List) petitions, multiSort);

		//Sort by sequenceNum
		Iterator<PetitionResponseEvent> petitionIter = petitions.iterator();
		//if no petition for the selected referral, get the offense with seq 1.
		if (!petitionIter.hasNext())
		{
		    for (JJSOffenseResponseEvent anOffense : offenses)
		    {
			if (anOffense.getReferralNum().equalsIgnoreCase(selectedReferral))
			{
			    commonAppForm.getCourtOrder().setCurrentOffenseId(anOffense.getOffenseCodeId()); ////defaulting the current offense code. //bug fix 26658
			    commonAppForm.getCourtOrder().setCauseNumber(anOffense.getPetitionNumber()); //bug fix 26194
			    //ER changes 11449 starts

			    //Pre-Populate Weapon Used && Gang Related from JOT.
			    if (anOffense.getPetitionNumber() != null && !anOffense.getPetitionNumber().isEmpty())
				setGangRelatedAndWeaponUsed(anOffense.getPetitionNumber(), commonAppForm); //bug fix 26194

			    currentOffensesBag.add(anOffense);
			    break;
			}
		    }
		}
		//get the petition allegation w.r.t to the selected referral
		while (petitionIter.hasNext())
		{
		    PetitionResponseEvent petition = petitionIter.next();
		    JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", petition.getOffenseCodeId());
		    JJSOffenseResponseEvent offenseResp = new JJSOffenseResponseEvent();
		    offenseResp.setOffenseCodeId(offenseCode.getOffenseCode());
		    offenseResp.setOffenseDescription(offenseCode.getLongDescription());
		    offenseResp.setCatagory(offenseCode.getCategory());
		    commonAppForm.getCourtOrder().setCurrentOffenseId(offenseCode.getOffenseCode());//defaulting the current description.
		    commonAppForm.getCourtOrder().setCauseNumber(petition.getPetitionNum()); //bug fix 26194
		    //ER changes 11449 starts
		    //Pre-Populate Weapon Used && Gang Related from JOT.
		    if (petition.getPetitionNum() != null && !petition.getPetitionNum().isEmpty())
			setGangRelatedAndWeaponUsed(petition.getPetitionNum(), commonAppForm); //bug fix 26194

		    currentOffensesBag.add(offenseResp);
		    break;
		}
	    }
	    //Changes for ER 11021 starts, Modifying the current offense business logic ends
	    commonAppForm.setCurrentOffenses(currentOffensesBag);
	    getOffenseLevel(aMapping, aForm, aRequest, aResponse); //defaulting offense level as description of Current Offense is defaulted.
	}

	if (commonAppForm.getSelectedControllingReferral() == null || (commonAppForm.getSelectedControllingReferral() != null && commonAppForm.getSelectedControllingReferral().length() == 0))
	{
	    commonAppForm.getCourtOrder().setOffenseLevel("");
	    commonAppForm.getCourtOrder().setOffenseLevelId("");
	    commonAppForm.getCourtOrder().setOffenseDescription("");
	    commonAppForm.getCourtOrder().setCurrentOffenseId(""); //bug fix 26658
	}

	return aMapping.findForward(UIConstants.FIND_SUCCESS);
    }

    /**
     * findOffenseCode
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward findOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CommonAppForm commAppForm = (CommonAppForm) aForm;
	String mostSeriousOffense = commAppForm.getCourtOrder().getMostSeriousOffenseId();
	Collection coll = commAppForm.getMostSeriousOffenses();

	Iterator<JJSOffenseResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    JJSOffenseResponseEvent off = iter.next();
	    if (off.getReferralNum().equals(mostSeriousOffense))
	    {
		commAppForm.getCourtOrder().setOffenseDescription(off.getCitationCode() + " " + off.getCitationSource());
		break;
	    }
	}

	return aMapping.findForward(UIConstants.FIND_SUCCESS);
    }

    /**
     * mapOffenseLevelDescription
     * 
     * @param offID
     * @return
     */
    private String mapOffenseLevelDescription(String offID)
    {
	String desc = "";

	if (offID.equals("FC"))
	{
	    desc = "Capital Felony";
	}
	else
	    if (offID.equals("F1"))
	    {
		desc = "1st degree Felony";
	    }
	    else
		if (offID.equals("F2"))
		{
		    desc = "2nd degree Felony";
		}
		else
		    if (offID.equals("F3"))
		    {
			desc = "3rd degree Felony";
		    }
		    else
			if (offID.equals("JF"))
			{
			    desc = "State Jail Felony";
			}
			else
			    if (offID.equals("MA"))
			    {
				desc = "Class A Misdemeanor";
			    }
			    else
				if (offID.equals("MB"))
				{
				    desc = "Class B Misdemeanor";
				}
				else
				    if (offID.equals("MC"))
				    {
					desc = "Class C Misdemeanor";
				    }
				    else
					if (offID.equals("SO"))
					{
					    desc = "Status Offense";
					}
					else
					    if (offID.equals("CO"))
					    {
						desc = "City Ordinance";
					    }
					    else
						if (offID.equals("AC"))
						{
						    desc = "Administrative Code";
						}

	return (desc);
    }

    /**
     * getSelectedDetentionFacilities
     * 
     * @param cappForm
     * @return
     */
    private Collection getSelectedDetentionFacilities(CommonAppForm cappForm)
    {
	Collection facilities = cappForm.getDetentionFacilities();
	Collection selectedFacilities = new ArrayList();
	long totalDetentionTime = 0L;

	Iterator<CommonAppForm.Placement> iter = facilities.iterator();
	while (iter.hasNext())
	{
	    CommonAppForm.Placement place = iter.next();
	    if (place.isStayed() && (place.getFacilityInfo() != null && place.getFacilityInfo().getJuvTJPCPlacementType() != null && place.getFacilityInfo().getJuvTJPCPlacementType().equals("D"))) //bug fix #58612 Include only D for calculation.
	    {
		totalDetentionTime += place.getDetTime();
		selectedFacilities.add(place);
	    }
	}
	cappForm.getCourtOrder().setDetentionTime(new StringBuffer("").append(totalDetentionTime).append(" Days").toString());

	return selectedFacilities;
    }

    /**
     * notNullNotEmptyCollection
     * 
     * @param bag
     * @return
     */
    private boolean notNullNotEmptyCollection(Collection bag)
    {
	return (bag != null && !bag.isEmpty());
    }

    /**
     * Pre-Populate Weapon Used && Gang Related from JOT on selection of
     * controlling referral no.
     * 
     * @param petitionNum
     * @param commonAppForm
     */
    private void setGangRelatedAndWeaponUsed(String petitionNum, CommonAppForm commonAppForm)
    {
	//Pre-Populate Weapon Used && Gang Related from JOT.
	GetJuvenileCasefilePetitionEvent eve = new GetJuvenileCasefilePetitionEvent();
	eve.setPetitionNum(petitionNum);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(eve);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	Object refObj = MessageUtil.filterComposite(response, JuvenileOffenderTrackingChargeResponseEvent.class);
	if (refObj != null)
	{
	    JuvenileOffenderTrackingChargeResponseEvent resp = (JuvenileOffenderTrackingChargeResponseEvent) refObj;
	    if (resp.getGangIndicator() != null && !resp.getGangIndicator().isEmpty())
	    {
		if (resp.getGangIndicator().equalsIgnoreCase("Y"))
		{ //Pre-populate Gang Indicator.
		    commonAppForm.getCourtOrder().setGangRelated("Yes");
		}
		else
		{
		    commonAppForm.getCourtOrder().setGangRelated("No");
		}
	    }
	    if (resp.isWeaponUsed())
	    {//Pre-Populate Weapon Used.
		commonAppForm.getCourtOrder().setWeaponTypeId(resp.getWeaponTypeId());
		commonAppForm.getCourtOrder().setWeaponType(resp.getWeaponType());
	    }
	}
    }

    /*
     * (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "submit");
	keyMap.put("button.link", "link");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.back", "back");
	keyMap.put("button.find", "buildCurrentOffenses");
	keyMap.put("button.process", "getOffenseCode");
	keyMap.put("button.filter", "getOffenseLevel");
    }
}
