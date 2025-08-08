package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.juvenilewarrant.ChargeRequestEvent;
import messaging.juvenilewarrant.UpdateJuvenileWarrantEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.SocialSecurity;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

public class DisplayVOPUpdateSummaryAction extends LookupDispatchAction
{

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", UIConstants.BACK);
		buttonMap.put("button.finish", UIConstants.FINISH);
		buttonMap.put("button.next", UIConstants.NEXT);
		buttonMap.put("button.go", UIConstants.GO);
		buttonMap.put("button.mainPage","mainPage");
		return buttonMap;
	}
	
	public ActionForward mainPage(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws Exception
		{
			ActionForward forward = aMapping.findForward(UIConstants.MAIN_PAGE);
			return forward;
		}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return forward
	 * @throws Exception
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.NEXT);

		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
		form.setWarrantTypeUI(UIConstants.UPDATE_VOP_SUCCESS);

		this.setCharge(form);

		boolean otherCautionSelected = false;
		String[] cautionCodes = form.getSelectedCautions();		
		if (cautionCodes != null)
		{			
			for (int i = 0; i < cautionCodes.length; i++)
			{
				if(PDCodeTableConstants.CAUTION_CODE_OTHER.equals(cautionCodes[i]))
				{
					otherCautionSelected = true;
				}
			}
		}
		
		if(otherCautionSelected == false)
		{
			form.setCautionComments(null);
		}

		form.setSsn(new SocialSecurity(form.getSSN1(), form.getSSN2(), form.getSSN3()));

		form.refreshSchoolDescriptions();
		
		form.setBackToWarrantUrl(forward.getPath());

		return forward;
	}

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return forward
	 * @throws Exception
	 */
	public ActionForward go(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;

		form.refreshSchool();

		return aMapping.findForward(UIConstants.GO);
	}

	public String getCourtDescription(Collection courts, String courtId)
	{
		String description = null;
		if (courts != null)
		{
			Iterator i = courts.iterator();
			while (i.hasNext())
			{
				Object codeObj = i.next();
				if (codeObj instanceof JuvenileCourtResponseEvent)
				{
					JuvenileCourtResponseEvent court = (JuvenileCourtResponseEvent) codeObj;
					if (court.getCode().equals(courtId))
					{
						description = court.getDescription();
					}
				}
			}
		}
		return description;
	}

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return forward
	 * @throws Exception
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);

		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
		form.setWarrantTypeUI(UIConstants.UPDATE_VOP_SUCCESS);

		form.setBackToWarrantUrl(forward.getPath());

		return forward;
	}

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return forward
	 * @throws Exception
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;

		String typeUI = form.getWarrantTypeUI();

		boolean update = UIConstants.UPDATE_VOP_SUCCESS.equals(typeUI);

		if (update)
		{
			this.updateWarrant(form);
			form.setWarrantTypeUI(UIConstants.UPDATE_VOP_CONFIRM);
		}
		else
		{
			form.setWarrantTypeUI(UIConstants.UPDATE_VOP_SUCCESS);
		}

		aRequest.getSession().removeAttribute("schoolDistricts");
		
		form.setBackToWarrantUrl(aMapping.findForward(UIConstants.FINISH).getPath());

		return aMapping.findForward(UIConstants.FINISH);
	}

	/**
	 * 
	 * @param aForm
	 */
	private void setCharge(JuvenileWarrantForm form)
	{
		List chargesSelected = new ArrayList();
		Collection charges = form.getCharges();
		if (charges != null)
		{
			Iterator i = charges.iterator();
			while (i.hasNext())
			{
				PetitionResponseEvent petitionEvt = (PetitionResponseEvent) i.next();
				if (petitionEvt.getSequenceNum().equals(form.getSelectedCharge()))
				{
					ChargeRequestEvent chargeEvent = new ChargeRequestEvent();
					chargeEvent.setTopic(PDJuvenileWarrantConstants.CHARGE_EVENT_TOPIC);
					chargeEvent.setCourtId(petitionEvt.getCourtId());
					chargeEvent.setOffenseCodeId(petitionEvt.getOffenseCodeId());
					chargeEvent.setPetitionNum(petitionEvt.getPetitionNum());
					chargeEvent.setSequenceNum(petitionEvt.getSequenceNum());
					chargesSelected.add(chargeEvent);

					form.setPetitionNum(petitionEvt.getPetitionNum());
					form.setCourtId(petitionEvt.getCourtId());
					form.setChargeCodeId(petitionEvt.getOffenseCodeId());
					form.setChargeDescription(petitionEvt.getOffense());
				}
			}
		}

		form.setChargesSelected(chargesSelected);
	}
	/**
	 * 
	 * @param form
	 * @return returnException
	 */
	private void updateWarrant(JuvenileWarrantForm form)
	{
		UpdateJuvenileWarrantEvent event =
			(UpdateJuvenileWarrantEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.UPDATEJUVENILEWARRANT);

		this.setEvent(form, event);

		MessageUtil.postRequest(event);
	}

	//	TODO Generalize this when doing the ER for charges and sequenceNum
	private ChargeResponseEvent getSelectedCharges(Collection charges, String selectedCharge)
	{
		ChargeResponseEvent charge = null;
		Iterator i = charges.iterator();
		while (i.hasNext())
		{
			charge = (ChargeResponseEvent) i.next();
			if (charge.getSequenceNum().equals(selectedCharge))
			{
				break;
			}
		}

		return charge;
	}

	/**
	 * 
	 * @param form
	 * @param event
	 */
	private void setEvent(JuvenileWarrantForm form, UpdateJuvenileWarrantEvent event)
	{
		// TODO Move to an assembler
		event.setWarrantNum(form.getWarrantNum());
		event.setJuvenileNum(form.getJuvenileNumInteger());
		event.setFirstName(form.getFirstName());
		event.setMiddleName(form.getMiddleName());
		event.setLastName(form.getLastName());
		event.setNameSuffix(form.getNameSuffix());
		event.setStatement(form.getAffidavitStatement());
		event.setTransactionNum(form.getTransactionNum());
		if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_UNSEND.equals(form.getWarrantActivationStatusId()))
		{
			event.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
		}
		else
		{
			event.setWarrantActivationStatus(form.getWarrantActivationStatusId());
		}
		event.setWarrantAcknowledgeStatus(form.getWarrantAcknowledgeStatusId());
		event.setWarrantSignedStatus(form.getWarrantSignedStatusId());
		event.setProbationOfficerOfRecordName(form.getProbationOfficerOfRecordName());
		event.setStatus(form.getWarrantStatusId());
		event.setBuild(form.getBuildId());
		event.setFbiNum(form.getFbiNum());
		event.setHeight(form.getHeight());
		event.setWeight(form.getWeight());
		event.setRace(form.getRaceId());
		event.setSex(form.getSexId());
		event.setComplexion(form.getComplexionId());
		event.setEyeColor(form.getEyeColorId());
		event.setHairColor(form.getHairColorId());
		event.setSchoolDistrict(form.getSchoolDistrictId());
		event.setSchoolName(form.getSchoolCodeId());
		event.setWarrantType(form.getWarrantTypeId());
		event.setComments(form.getCautionComments());
		event.setOfficerId(form.getOfficerId());
		event.setPhone(form.getPhoneNum().getPhoneNumber());
		String joinedSSNStr = form.getSSN1() + form.getSSN2() + form.getSSN3();
		event.setSsn(joinedSSNStr);
		event.setStatement(form.getAffidavitStatement());
		event.setSidNum(form.getSid());
		event.setOfficerId(form.getOfficerOID());
		event.setWarrantOriginatorId(form.getWarrantOriginatorId());
		event.setAliasName(form.getAliasName());
		event.setWarrantOriginatorCourt(form.getCourtId());
		event.setDateOfBirth(form.getDateOfBirth());
		event.setReferralNum(form.getReferralNumInteger());

		this.setCodes(form, event);
	}

	/**
	 * 
	 * @param form
	 * @param event
	 */
	private void setCodes(JuvenileWarrantForm form, UpdateJuvenileWarrantEvent event)
	{
	    String[] inserted = new String[1];
        inserted[0] = form.getSelectedCharge();
        if (form.getSelectedCharge().equals(form.getOriginalCharge()) == false)
        {
            event.setCharges(inserted);
            ChargeRequestEvent chargeRequest = (ChargeRequestEvent) form.getChargesSelected().get(0);
            event.addRequest(chargeRequest);
        }

        inserted = this.getChangeSet(form.getOriginalCautions(), form.getSelectedCautions());
        event.setCautions(inserted);
        String[] deleted = this.getChangeSet(form.getSelectedCautions(), form.getOriginalCautions());
        event.setDeletedCautions(deleted);

        inserted = this.getChangeSet(form.getOriginalScars(), form.getSelectedScars());
        event.setScarsMarks(inserted);
        deleted = this.getChangeSet(form.getSelectedScars(), form.getOriginalScars());
        event.setDeletedScarsMarks(deleted);

        inserted = this.getChangeSet(form.getOriginalTattoos(), form.getSelectedTattoos());
        event.setTattoos(inserted);
        deleted = this.getChangeSet(form.getSelectedTattoos(), form.getOriginalTattoos());
        event.setDeletedTattoos(deleted);
	}
	
	private String[] getChangeSet(String[] set1, String[] set2)
    {
        String[] changeSet = null;

        if (set1 != null)
        {
            Arrays.sort(set1);

            List changeSetList = new ArrayList();

            if (set2 != null)
            {
                for (int i = 0; i < set2.length; i++)
                {
                    int foundIndex = Arrays.binarySearch(set1, set2[i]);
                    if (foundIndex < 0)
                    {
                        changeSetList.add(set2[i]);
                    }
                }
            }

            int insertCount = changeSetList.size();

            changeSet = new String[insertCount];

            Iterator i = changeSetList.iterator();
            int index = 0;
            while (i.hasNext())
            {
                changeSet[index] = (String) i.next();
                index++;
            }
        }
        else
        {
            // when set1 does not contain any values
            changeSet = set2;
        }

        return changeSet;
    }
}
