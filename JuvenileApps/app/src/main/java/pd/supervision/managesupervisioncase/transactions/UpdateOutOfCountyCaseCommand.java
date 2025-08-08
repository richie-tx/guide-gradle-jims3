//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\UpdateOutOfCountyCaseCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.managesupervisioncase.UpdateOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.security.PDSecurityHelper;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.managesupervisioncase.OutOfCountyCase;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.task.helper.TaskHelper;

public class UpdateOutOfCountyCaseCommand implements ICommand
{

	/**
	 * @roseuid 444525EE0346
	 */
	public UpdateOutOfCountyCaseCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 443D14B200A2
	 */
	public void execute(IEvent event)
	{
		UpdateOutOfCountyCaseEvent updateEvent = (UpdateOutOfCountyCaseEvent) event;
		
		OutOfCountyCase aCase = null;
		
		OutOfCountyCaseEvent response = new OutOfCountyCaseEvent();
		String courtDivisionId = updateEvent.getCdi();
		response.setCdi(courtDivisionId);
		
		if (OutOfCountyCase.newCase(updateEvent))
		{
			aCase = OutOfCountyCaseFactory.createCase(courtDivisionId);

			// set the values
			aCase.updateOutOfCountyCase(updateEvent, false);
			if (aCase instanceof OutOfCountyProbationCase){
				OutOfCountyProbationCase probCase = (OutOfCountyProbationCase) aCase;
				probCase.setDispositionDate(updateEvent.getDispositionDate());
				probCase.setDispositionTypeId(updateEvent.getDispositionTypeId());
				probCase.setArrestDate(updateEvent.getArrestDate());
				probCase.setCJISNum(updateEvent.getCjisNum());
				probCase.setSentenceDate(updateEvent.getSentenceDate());
				probCase.setPersonId(updateEvent.getPersonId());
				probCase.setFilingDate(updateEvent.getDispositionDate());
				probCase.setConfinementDays(updateEvent.getConfinementDays());
				probCase.setConfinementMonths(updateEvent.getConfinementMonths());
				probCase.setConfinementYears(updateEvent.getConfinementYears());
				probCase.setSupervisionDays(updateEvent.getSupervisionDays());
				probCase.setSupervisionMonths(updateEvent.getSupervisionMonths());
				probCase.setSupervisionYears(updateEvent.getSupervisionYears());
				probCase.setCaseAndDefendantStatus(updateEvent.getDispositionTypeId());
				probCase.setNameSeqNum(updateEvent.getNameSeqNum());
				probCase.setNamePtr(updateEvent.getNamePtr());
				probCase.setDefendantId(updateEvent.getSpn());//removes name.seq.num from party.oid.derived.

				if (updateEvent.getDispositionTypeId().equals(PDCodeTableConstants.PRETRIAL_INTERVENTION))
				{
					probCase.setInstrumentTypeId(PDCodeTableConstants.CSCD_PTIN_INSTR_TYPE);
				}
				else
				// If Disposition Type = 'DADJ' or 'PROB',  then 
				//	   If Court = 'INM' or 'OTM', then set Instrument Type to MIN
				//	   If Court = 'INF' or 'OTF', then set Instrument Type to FIN
				//	   If Court = JP court, then set Instrument Type to MIN
				{
					String court = probCase.getOutOfCountyCaseTypeId();
					if (court.equals(PDCodeTableConstants.IN_STATE_MISD) || court.equals(PDCodeTableConstants.OUT_OF_STATE_MISD) ||
						court.equals(PDCodeTableConstants.HARRIS_COUNTY_TREASURER_OFFICE_MISD) || court.equals(PDCodeTableConstants.CSCD_CSR_JP_CRTS_IN_LIEU_OF_MISD) ||
						court.startsWith("J"))
					{
						probCase.setInstrumentTypeId(PDCodeTableConstants.CSCD_MISD_INSTR_TYPE);
					}
					else
					if (court.equals(PDCodeTableConstants.IN_STATE_FELONY) || court.equals(PDCodeTableConstants.OUT_OF_STATE_FELONY))
					{
						probCase.setInstrumentTypeId(PDCodeTableConstants.CSCD_FELONY_INSTR_TYPE);
					}

				}

			}
			
			// bind to the database
			try
			{
				new Home().bind(aCase);
			} catch (Exception e)
			{
				/* // now what ?? look for a specific message in the exception
				String errMsg = m204Exception.getMessage();
				if (errMsg.indexOf("M204 & VSAM CASE RECORDS BUILT") > 0)
				{
					throw new RuntimeException(errMsg.substring(errMsg.indexOf("("), errMsg.indexOf(")")));
				} */
				TaskHelper.createCsTaskToJIMSWorkgroup("CREATE OOC CASE", aCase.getSpn(), PDConstants.BLANK, e);
				throw new RuntimeException(e.getMessage());
			} 
			
			// set the new OID (caseNum)
			response.setCaseNum(aCase.getCaseNum());
			response.setFilingDate(DateUtil.stringToDate(aCase.getFilingDate(), PDConstants.DATE_FORMAT_YYYYMMDD));

		}
		else
		{
			aCase = (OutOfCountyCase)OutOfCountyCaseFactory.find(updateEvent.getCaseNum(), courtDivisionId);
			Date currTransferInDate = aCase.getTransferInDate();
			// set the values
			aCase.updateOutOfCountyCase(updateEvent, true);
			if (aCase instanceof OutOfCountyProbationCase){
				OutOfCountyProbationCase probCase = (OutOfCountyProbationCase) aCase;
				probCase.setDispositionDate(updateEvent.getDispositionDate());
				probCase.setDispositionTypeId(updateEvent.getDispositionTypeId());
				probCase.setArrestDate(updateEvent.getArrestDate());
				probCase.setCJISNum(updateEvent.getCjisNum());
				probCase.setSentenceDate(updateEvent.getSentenceDate());
				probCase.setPersonId(updateEvent.getPersonId());
				probCase.setFilingDate(updateEvent.getDispositionDate());
				probCase.setConfinementDays(updateEvent.getConfinementDays());
				probCase.setConfinementMonths(updateEvent.getConfinementMonths());
				probCase.setConfinementYears(updateEvent.getConfinementYears());
				probCase.setSupervisionDays(updateEvent.getSupervisionDays());
				probCase.setSupervisionMonths(updateEvent.getSupervisionMonths());
				probCase.setSupervisionYears(updateEvent.getSupervisionYears());
				probCase.setCaseAndDefendantStatus(updateEvent.getDispositionTypeId());
				probCase.setNameSeqNum(updateEvent.getNameSeqNum());
				probCase.setNamePtr(updateEvent.getNamePtr());
				probCase.setDefendantId(updateEvent.getSpn());//removes name.seq.num from party.oid.derived.
				
				if (updateEvent.getDispositionTypeId().equals(PDCodeTableConstants.PRETRIAL_INTERVENTION))
				{
					probCase.setInstrumentTypeId(PDCodeTableConstants.CSCD_PTIN_INSTR_TYPE);
				}
				else
				// If Disposition Type = 'DADJ' or 'PROB',  then 
				//	   If Court = 'INM' or 'OTM', then set Instrument Type to MIN
				//	   If Court = 'INF' or 'OTF', then set Instrument Type to FIN
				//	   If Court = JP court, then set Instrument Type to MIN
				{
					String court = probCase.getOutOfCountyCaseTypeId();
					if (court.equals(PDCodeTableConstants.IN_STATE_MISD) || court.equals(PDCodeTableConstants.OUT_OF_STATE_MISD) ||
						court.equals(PDCodeTableConstants.HARRIS_COUNTY_TREASURER_OFFICE_MISD) || court.equals(PDCodeTableConstants.CSCD_CSR_JP_CRTS_IN_LIEU_OF_MISD) ||
						court.startsWith("J"))
					{
						probCase.setInstrumentTypeId(PDCodeTableConstants.CSCD_MISD_INSTR_TYPE);
					}
					else
					if (court.equals(PDCodeTableConstants.IN_STATE_FELONY) || court.equals(PDCodeTableConstants.OUT_OF_STATE_FELONY))
					{
						probCase.setInstrumentTypeId(PDCodeTableConstants.CSCD_FELONY_INSTR_TYPE);
					}

				}
			}
			try {
				StringBuffer caseId = new StringBuffer();
				caseId.append(updateEvent.getCdi());
				caseId.append(updateEvent.getCaseNum());
				Iterator supIter = SupervisionOrder.findAll(CaseloadConstants.CRIMINAL_CASE_ID,caseId.toString());
				while(supIter.hasNext()){
					SupervisionOrder sup = (SupervisionOrder) supIter.next();
		    		   if(PDCodeTableConstants.STATUS_ACTIVE_ID.equalsIgnoreCase(sup.getOrderStatusId())){
		    			   StringBuffer currCourtId = new StringBuffer();
		    			   currCourtId.append("OC ");
       					   currCourtId.append(updateEvent.getOutOfCountyCaseTypeId());
		    			   if ( StringUtils.isNotEmpty(sup.getCurrentCourtId()) && sup.getCurrentCourtId().equals(currCourtId.toString()) ) {
		    				   break;
		    			   }else {
			    			   sup.setCurrentCourtId(currCourtId.toString());
		    			   }
		    		   }
		    	   }				
				TransactionManager.getInstance().commitClear();
			} catch (Exception e) {
				TaskHelper.createCsTaskToJIMSWorkgroup("UPDATE OOC CASE", aCase.getSpn(), aCase.getCriminalCaseId(), e);
				throw new RuntimeException(e.getMessage());
			}
			if (updateEvent.getTransferInDate() != null 
					&& !updateEvent.getTransferInDate().equals(currTransferInDate)){
				modifySupervisionPeriodBeginDate(updateEvent.getSpn(), 
						aCase.getCriminalCaseId(),
						updateEvent.getTransferInDate());
			}

			
		}

		// return the results of the validation or new case number
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(response);	
	}
	int THOUSAND = 1000;
	private static final String OOC_CDI = "010";

	private void modifySupervisionPeriodBeginDate(String spn, String criminalCaseId, Date newTransferInDate) {
		if (spn.length() < 8){
			StringBuffer sb = new StringBuffer(spn);
			while (sb.length() < 8){
				sb.insert(0, 0);
			}
			spn=sb.toString();
		}
		String agencyId = PDSecurityHelper.getUserAgencyId();
		SupervisionPeriod sp = SupervisionPeriod.findActiveSupervisionPeriod(spn, agencyId);

		if (sp != null && newTransferInDate.before(sp.getSupervisionBeginDate())){
			sp.setSupervisionBeginDate(newTransferInDate);
		} else if (sp != null) {
			//read for active cases
			List orderList = SupervisionOrderHelper.getActiveSupervisionOrders(spn, agencyId);
			Date oldestDate = newTransferInDate;
			SupervisionOrder order = null;

			for (int i = 0; i < orderList.size(); i++) {
				order = (SupervisionOrder) orderList.get(i);
				//Skip the order of the case being updated.
				if (order.getCriminalCaseId().startsWith(OOC_CDI)){
						if (!order.getCriminalCaseId().equals(criminalCaseId)){
							if (order.getTransferInDate() != null
							&& order.getTransferInDate().before(oldestDate)){
								oldestDate = order.getTransferInDate();
							} 
						} else {
							continue;
						}
				} else 	if (order.getCaseSupervisionBeginDate().before(oldestDate)){
						oldestDate = order.getCaseSupervisionBeginDate();
				}
			}
			sp.setSupervisionBeginDate(oldestDate);
		}
	}

	/**
	 * @param event
	 * @roseuid 443D14B200AB
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 443D14B200AD
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 444525EE035A
	 */
	public void update(Object updateObject)
	{

	}
}
