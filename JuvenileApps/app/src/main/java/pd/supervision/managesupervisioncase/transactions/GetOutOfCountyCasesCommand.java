//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\GetOutOfCountyCasesCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.managesupervisioncase.GetOutOfCountyCasesEvent;
import messaging.managesupervisioncase.reply.CaseListResponseEvent;
import messaging.managesupervisioncase.reply.CaseNotFoundEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseTO;
import messaging.managesupervisioncase.reply.PartyEvent;
import messaging.party.GetPartyDataEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import pd.contact.party.Party;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.managesupervisioncase.OutOfCountyCase;

public class GetOutOfCountyCasesCommand implements ICommand
{
	/**
	 * @roseuid 4447C7330174
	 */
	public GetOutOfCountyCasesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4447C3660393
	 */
	public void execute(IEvent event)
	{
		GetOutOfCountyCasesEvent getCasesEvent = (GetOutOfCountyCasesEvent) event;
		Iterator oocCases = null;
		PartyEvent defendant = null;

		String caseNum = getCasesEvent.getCaseNum();
		// Check to see if the lookup is for a specific case (by number)
		if (caseNum != null && !caseNum.trim().equals(""))
		{
			Collection cases = new ArrayList();
			String courtDivisionId = PDCodeTableConstants.PTS;
			if (getCasesEvent.getUserAgencyId().equals(PDCodeTableConstants.CSCD_AGENCY))
			{
				courtDivisionId = PDCodeTableConstants.CSCD;
			}
			// lookup the OOC case.  
			OutOfCountyCase oocCase =
				(OutOfCountyCase) OutOfCountyCaseFactory.find(getCasesEvent.getCaseNum(), courtDivisionId);
			// check if case was found
			if (oocCase != null)
			{
				cases.add(oocCase);
				defendant = new PartyEvent();
				oocCase.getPartyInfo(defendant);
				oocCases = cases.iterator();
			}
			else
			{
				// post a case not found event
				EventManager.getSharedInstance(EventManager.REPLY).postEvent(new CaseNotFoundEvent());
				return;
			}
		}
		else
		// Lookup by defendant; either SPN or OID
		{
			String defendantId = getCasesEvent.getDefendantId();
			String spn = getCasesEvent.getSpn();
			if (defendantId != null && !defendantId.trim().equals(""))
			{
				// get the defendant (party) info
				defendant = getDefendantByOID(defendantId);
			}
			else
			if (spn != null && !spn.trim().equals(""))
			{
				// get the defendant (party) info
				defendant = getDefendantBySPN(spn);
			}
			// no need to continue if we have no defendant info 
			if (defendant != null)
			{
				oocCases = OutOfCountyCaseFactory.findAll(getCasesEvent);
			}
			else
			{
				// post an empty ResponseEvent to indicate that the Defendant was not found 
				EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ResponseEvent());
				return;
			}
		}

		// create and populate the response event
		CaseListResponseEvent caseEvent = new CaseListResponseEvent();

		caseEvent.setParty(defendant);

		while (oocCases.hasNext())
		{
			OutOfCountyCase oocCase = (OutOfCountyCase) oocCases.next();

			OutOfCountyCaseTO caseTO = new OutOfCountyCaseTO();
			// add the data to the response
			caseTO.setCaseNum(oocCase.getCaseNum());
			caseTO.setCdi(oocCase.getCourtDivisionId());
			if (oocCase.getCaseStatus() != null)
			{
				caseTO.setCaseStatus(oocCase.getCaseStatus().getDescription());
			}
			caseTO.setCourtNum(oocCase.getOutOfCountyCaseTypeId());
			if (oocCase.getDefendantStatus() != null)
			{
				caseTO.setDefendantStatus(oocCase.getDefendantStatus().getDescription());
			}
			caseTO.setDispositionDate(oocCase.getDispositionDate());
			caseTO.setDispositionTypeId(oocCase.getDispositionTypeId());
			//caseTO.setFilingDate(DateUtil.convertYYYYMMDD(oocCase.getFilingDate()));
			caseTO.setFilingDate(DateUtil.stringToDate(oocCase.getFilingDate(), "yyyyMMdd"));
			caseTO.setInstrumentTypeId(oocCase.getInstrumentTypeId());
			caseTO.setOffense(oocCase.getOffenseCodeId());
			caseTO.setReactivateInd(oocCase.canBeReactivated());
			caseTO.setConnectionId(oocCase.getConnectionId());

			// add the case to the response event
			caseEvent.addCase(caseTO);
		}
		// sort the list (ascending by case number) before returning
		caseEvent.sortCases();
		
		// post the response
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(caseEvent);
	}

	/**
	 * @param event
	 * @roseuid 4447C366039C
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4447C366039E
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 4447C36603A5
	 */
	public void update(Object updateObject)
	{

	}

	private PartyEvent getDefendantByOID(String anOID)
	{		
		GetPartyDataEvent getEvent = new GetPartyDataEvent();

		getEvent.setOID(anOID);
		
		return getDefendant(getEvent);
	}
	
	private PartyEvent getDefendantBySPN(String spn)
	{
		
		GetPartyDataEvent getEvent = new GetPartyDataEvent();

		getEvent.setSpn(spn);
		getEvent.setCurrentNameInd("Y");
		
		return getDefendant(getEvent);
	}
	
	private PartyEvent getDefendant(GetPartyDataEvent getEvent)
	{
		
		Party party = Party.find(getEvent);
		if(party != null){
			
			PartyEvent partyEvent = new PartyEvent();
			partyEvent.setPartyOid(party.getOID());
			partyEvent.setPartyDateOfBirth(party.getDateOfBirth());
			partyEvent.setPartyFirstName(party.getFirstName());
			partyEvent.setPartyMiddleName(party.getMiddleName());
			partyEvent.setPartyLastName(party.getLastName());
			partyEvent.setPartyRaceId(party.getRaceId());
			partyEvent.setPartySexId(party.getSexId());
			partyEvent.setPartySID(party.getSidNum());
			partyEvent.setPartySpn(party.getSpn());
			partyEvent.setPartySSN(party.getSsn());
			partyEvent.setPartyNamePtr(party.getNamePtr());
			partyEvent.setPartyNameSeqNum(party.getNameSeq());
			return partyEvent;
		} 	
		return null;
	}
}
