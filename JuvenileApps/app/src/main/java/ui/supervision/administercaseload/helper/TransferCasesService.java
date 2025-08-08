package ui.supervision.administercaseload.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.transfers.UpdateTransferCaseEvent;
import messaging.transfers.reply.TransferResponseEvent;
import messaging.transfers.to.TransferCaseBean;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import naming.SuperviseeControllerServiceNames;
import naming.UIConstants;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;


public class TransferCasesService {
	private static TransferCasesService service;
	private static boolean serviceInitialized = false;
	private static final String HARRIS_COUNTY_CODE = "101";
	private static final String ADD_ACTION_TYPE = "A";
	private static final String CHANGE_ACTION_TYPE = "C";
	private static final String OUT_OF_COUNTY = "010";
	private static final int ZERO = 0;
	private static final int THREE = 3;
	private static final String HISTORICAL = "H";

	/**
	 *  
	 */
	private TransferCasesService() {
	}

	public static TransferCasesService getInstance() {
		if (service == null) {
			synchronized (TransferCasesService.class) {
				if (!serviceInitialized) {
					service = new TransferCasesService();
					serviceInitialized = true;
				}
			}
		}
		return service;
	}
	
	public List getTransferCases(String superviseeId) {
		List transferCases = getTransferCases(superviseeId, HISTORICAL);
		return transferCases;
	}
	public List getTransferCases(String superviseeId, String historyType) {
		List transferCases = null;
		if (superviseeId != null) {
			GetTransfersEvent request = (GetTransfersEvent)EventFactory.getInstance(SuperviseeControllerServiceNames.GETTRANSFERS);
			request.setSuperviseeId(superviseeId);
			request.setSearchType(historyType); 
			transferCases = MessageUtil.postRequestListFilter(request, TransferResponseEvent.class);			
		}
		return transferCases;
	}
	public List getHarrisCountyTransferCases(String superviseeId) {
		List transferCases = getTransferCases(superviseeId);
		List harrisCountyCases = null;
	    if (transferCases != null && transferCases.size() > ZERO) {
	    	harrisCountyCases = new ArrayList();
	    	for (Iterator iterator = transferCases.iterator(); iterator.hasNext();) {
				TransferResponseEvent responseEvent = (TransferResponseEvent)iterator.next();
				if (!responseEvent.getCdi().equals(OUT_OF_COUNTY)) {
					harrisCountyCases.add(responseEvent);
				}
	    	}
		}
		return harrisCountyCases;		
	}
	public List getHarrisCountyTransferCases(String superviseeId, String historyType) {
		List transferCases = getTransferCases(superviseeId, historyType);
		List harrisCountyCases = null;
	    if (transferCases != null && transferCases.size() > ZERO) {
	    	harrisCountyCases = new ArrayList();
	    	for (Iterator iterator = transferCases.iterator(); iterator.hasNext();) {
				TransferResponseEvent responseEvent = (TransferResponseEvent)iterator.next();
				if (!responseEvent.getCdi().equals(OUT_OF_COUNTY)) {
					harrisCountyCases.add(responseEvent);
				}
	    	}
		}
		return harrisCountyCases;		
	}

	public List getOutOfCountyTransferCases(String superviseeId) {
		List transferCases = getTransferCases(superviseeId);		
		List outOfCountyCases = null;		
	    if (transferCases != null && transferCases.size() > ZERO) {
	    	outOfCountyCases = new ArrayList();	
	    	for (Iterator iterator = transferCases.iterator(); iterator.hasNext();) {
				TransferResponseEvent responseEvent = (TransferResponseEvent)iterator.next();
				if (responseEvent.getCdi().equals(OUT_OF_COUNTY)) {
					outOfCountyCases.add(responseEvent);
				}
	    	}
		}
		return outOfCountyCases;		
	}
	
	public List getOutOfCountyTransferCases(String superviseeId, String historyType) {
		List transferCases = getTransferCases(superviseeId, historyType);		
		List outOfCountyCases = null;		
	    if (transferCases != null && transferCases.size() > ZERO) {
	    	outOfCountyCases = new ArrayList();	
	    	for (Iterator iterator = transferCases.iterator(); iterator.hasNext();) {
				TransferResponseEvent responseEvent = (TransferResponseEvent)iterator.next();
				if (responseEvent.getCdi().equals(OUT_OF_COUNTY)) {
					outOfCountyCases.add(responseEvent);
				}
	    	}
		}
		return outOfCountyCases;		
	}
	
	public void transferSuperviseeCases(SuperviseeTransferCasesInfo transferInfo) {		
		String transferType = transferInfo.getTransferType(); 
		String transferOutDate = transferInfo.getTransferOutDate();		
		String transferOutTxCounty = transferInfo.getTransferTxCountyId();
		String transferOutState = transferInfo.getTransferStateId();
		String transferInDate = transferInfo.getTransferInDate();
		boolean rejected = transferInfo.isRejected();
		
		List casesToTransfer = transferInfo.getCasesToTransfer();
		for (Iterator iterator = casesToTransfer.iterator(); iterator.hasNext();) {
			ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
			UpdateTransferCaseEvent requestEvent = new UpdateTransferCaseEvent();
			TransferCaseBean transferCaseBean = requestEvent.getTransferCaseBean();
			transferCaseBean.setDefendantId(caseAssignment.getDefendantId());
			String cdi = caseAssignment.getCdi();
			if (cdi == null || cdi.length() == ZERO) {
				cdi = caseAssignment.getCriminalCaseId().substring(ZERO, THREE);
			}
			String tranferTypeVal = getTransferTypeValue(transferType, cdi);		
			transferCaseBean.setCdi(cdi);
			transferCaseBean.setCriminalCaseId(caseAssignment.getCriminalCaseId().substring(3));

			if (transferType == UIConstants.CS_TRANSFER_OUT_CASES) {
				Date transferDate = DateUtil.stringToDate(transferOutDate, DateUtil.DATE_FMT_1);
				String receivingCountyCode = null;
				if (transferOutTxCounty != null && transferOutTxCounty.length() > ZERO){
					receivingCountyCode = transferOutTxCounty;
				} else {
					receivingCountyCode = transferOutState;
				}
				transferCaseBean.setTransferDate(transferDate);
				if (cdi.equals(OUT_OF_COUNTY)){
					transferCaseBean.setReceivingCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setTransferringCountyCode(receivingCountyCode);
					transferCaseBean.setPersonId(caseAssignment.getDefendantId());
					transferCaseBean.setOtherCountyPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyCaseNum(transferInfo.getOtherCaseNum());
				} else {
					transferCaseBean.setReceivingCountyCode(receivingCountyCode);
					transferCaseBean.setTransferringCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyPersonId(caseAssignment.getDefendantId());
					//transferCaseBean.setOtherCountyCaseNum(caseAssignment.getCriminalCaseId().substring(3));
					transferCaseBean.setOtherCountyCaseNum(PDConstants.BLANK);
				}
				transferCaseBean.setTransferType(tranferTypeVal);
				transferCaseBean.setActionType(ADD_ACTION_TYPE);				
			} else if (transferType == UIConstants.CS_TRANSFER_IN_CASES) {
				Date transferDate = DateUtil.stringToDate(transferInDate, DateUtil.DATE_FMT_1);
				String transferringCountyCode = null;
				if (transferOutTxCounty != null && transferOutTxCounty.length() > ZERO){
					transferringCountyCode = transferOutTxCounty;
				} else {
					transferringCountyCode = transferOutState;
				}
				transferCaseBean.setTransferDate(transferDate);
				transferCaseBean.setRejected(rejected);
				if (cdi.equals(OUT_OF_COUNTY)){
					transferCaseBean.setTransferringCountyCode(transferringCountyCode);
					transferCaseBean.setReceivingCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setPersonId(caseAssignment.getDefendantId());
					transferCaseBean.setOtherCountyPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyCaseNum(transferInfo.getOtherCaseNum());
				} else {
					transferCaseBean.setTransferringCountyCode(transferringCountyCode);
					transferCaseBean.setReceivingCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyPersonId(caseAssignment.getDefendantId());
					//transferCaseBean.setOtherCountyCaseNum(caseAssignment.getCriminalCaseId().substring(THREE));
					transferCaseBean.setOtherCountyCaseNum(PDConstants.BLANK);
				}

				transferCaseBean.setTransferType(tranferTypeVal);
				transferCaseBean.setActionType(ADD_ACTION_TYPE);
			}			
			MessageUtil.postRequest(requestEvent);
		}
	}
	
	private boolean isJurisdictionChangedOnHistoryRecord(TransferCaseBean cBean, boolean transferInExists, boolean transferOutExists){
		boolean isChanged = false;
		List casesBeforeChange = null;
		if (cBean.getCdi().equals(OUT_OF_COUNTY)){
			casesBeforeChange = this.getOutOfCountyTransferCases(cBean.getDefendantId(), "H");
		} else {
			casesBeforeChange = this.getHarrisCountyTransferCases(cBean.getDefendantId(), "H");
		}
		TransferResponseEvent tre = null;
		for (int i = 0; i < casesBeforeChange.size(); i++) {
			tre = (TransferResponseEvent) casesBeforeChange.get(i);
			if (tre.getCriminalCaseId().equals(cBean.getCriminalCaseId())){
					if ((cBean.getTransferType().equals("1") || cBean.getTransferType().equals("3")) //transfer out
						&& tre.getClassificationSeqNumForTransferOut().equals(cBean.getCicsClassificationSequenceNo())
								&& tre.getSubclassificationSeqNumForTransferOut().equals(cBean.getCicsSubclassificationSequenceNo())){
						
					} else if ((cBean.getTransferType().equals("4")  || cBean.getTransferType().equals("2")) //transfer in
							&& tre.getClassificationSeqNumForTransferIn().equals(cBean.getCicsClassificationSequenceNo())
							&& tre.getSubclassificationSeqNumForTransferIn().equals(cBean.getCicsSubclassificationSequenceNo())){
					}
			}
		}
		return isChanged;
	}
	private boolean transferInfoChanged(SuperviseeTransferCasesInfo currTransferInfo, SuperviseeTransferCasesInfo prevTransferInfo, boolean transferInUpdate){
		
		boolean infoChanged = false;
		String prevTransferDestinationCode = PDConstants.BLANK;
		if (prevTransferInfo.getTransferTxCountyId() != null 
				&& prevTransferInfo.getTransferTxCountyId().length() > ZERO) {
			prevTransferDestinationCode = prevTransferInfo.getTransferTxCountyId();
		} else if (prevTransferInfo.getTransferStateId() != null 
				&& prevTransferInfo.getTransferStateId().length() > ZERO) {
			prevTransferDestinationCode = prevTransferInfo.getTransferStateId();
		}
		String currTransferDestinationCode = PDConstants.BLANK;
		if (currTransferInfo.getTransferTxCountyId() != null 
				&& currTransferInfo.getTransferTxCountyId().length() > ZERO) {
			currTransferDestinationCode = currTransferInfo.getTransferTxCountyId();
		} else if (currTransferInfo.getTransferStateId() != null 
				&& currTransferInfo.getTransferStateId().length() > ZERO) {
			currTransferDestinationCode = currTransferInfo.getTransferStateId();
		}
		if (!currTransferDestinationCode.equals(prevTransferDestinationCode)){
			return true;
		}

		if (currTransferInfo.getPersonId() == null){
			currTransferInfo.setPersonId(PDConstants.BLANK);
		}
		if (prevTransferInfo.getPersonId() == null){
			prevTransferInfo.setPersonId(PDConstants.BLANK);
		}
		if (currTransferInfo.getOtherCaseNum() == null){
			currTransferInfo.setOtherCaseNum(PDConstants.BLANK);
		}	
		if (prevTransferInfo.getOtherCaseNum() == null){
			prevTransferInfo.setOtherCaseNum(PDConstants.BLANK);
		}	
		if (currTransferInfo.getPersonId()== null){
			currTransferInfo.setPersonId(PDConstants.BLANK);
		}
		if (prevTransferInfo.getPersonId()== null){
			prevTransferInfo.setPersonId(PDConstants.BLANK);
		}
		if (currTransferInfo.getTransferOutDate() == null){
			currTransferInfo.setTransferOutDate(PDConstants.BLANK);
		}
		if (prevTransferInfo.getTransferOutDate() == null){
			prevTransferInfo.setTransferOutDate(PDConstants.BLANK);
		}
		if (currTransferInfo.getTransferInDate() == null){
			currTransferInfo.setTransferInDate(PDConstants.BLANK);
		}
		if (prevTransferInfo.getTransferInDate() == null){
			prevTransferInfo.setTransferInDate(PDConstants.BLANK);
		}
			
		if (currTransferInfo.isRejected() != prevTransferInfo.isRejected()
				|| !currTransferInfo.getPersonId().equals(prevTransferInfo.getPersonId())
				|| !currTransferInfo.getOtherCaseNum().equals(prevTransferInfo.getOtherCaseNum())
				|| !currTransferInfo.getPersonId().equals(prevTransferInfo.getPersonId())){
			return true;
		}
		if (transferInUpdate){
			if (!currTransferInfo.getTransferInDate().equals(prevTransferInfo.getTransferInDate())){
				return true;
			}
		} else {
			if (!currTransferInfo.getTransferOutDate().equals(prevTransferInfo.getTransferOutDate())){
				return true;
			}
		}

		return infoChanged;
	}


	public void updateCaseHistory(SuperviseeTransferCasesInfo transferInfo, Collection harrisCountyCases, SuperviseeTransferCasesInfo prevTransferInfo) {
		
		List casesToTransfer = transferInfo.getCasesToTransfer();
		
		ICaseAssignment caseAssignment = (ICaseAssignment) casesToTransfer.get(ZERO);
		String cdi = caseAssignment.getCdi();
		if (cdi == null || cdi.length() == ZERO) {
			cdi = caseAssignment.getCriminalCaseId().substring(ZERO, THREE);
		}
			
		UpdateTransferCaseEvent requestEvent = new UpdateTransferCaseEvent();
		TransferCaseBean transferCaseBean = requestEvent.getTransferCaseBean();
		transferCaseBean.setDefendantId(caseAssignment.getDefendantId());
		transferCaseBean.setActionType(CHANGE_ACTION_TYPE);
		transferCaseBean.setCdi(cdi);
		transferCaseBean.setCriminalCaseId(caseAssignment.getCriminalCaseId());
		
		String transferDestinationCode = null;
		if (transferInfo.getTransferTxCountyId() != null 
				&& transferInfo.getTransferTxCountyId().length() > ZERO) {
			transferDestinationCode = transferInfo.getTransferTxCountyId();
		} else if (transferInfo.getTransferStateId() != null 
				&& transferInfo.getTransferStateId().length() > ZERO) {
			transferDestinationCode = transferInfo.getTransferStateId();
		}

		//Update transfer out info
		if (transferInfo.getTransferOutClassificationSeqNum() != null
				&& !transferInfo.getTransferOutClassificationSeqNum().equals(PDConstants.BLANK)) {
			if (transferInfoChanged(transferInfo, prevTransferInfo, false)){
				transferCaseBean.setTransferDate(DateUtil.stringToDate(transferInfo.getTransferOutDate(), DateUtil.DATE_FMT_1));
				transferCaseBean.setTransferType(getTransferTypeValue(UIConstants.CS_TRANSFER_OUT_CASES, cdi));
				if (transferInfo.getTransferTxCountyId() != null 
						&& transferInfo.getTransferTxCountyId().length() > ZERO) {
					transferDestinationCode = transferInfo.getTransferTxCountyId();
				} else if (transferInfo.getTransferStateId() != null 
						&& transferInfo.getTransferStateId().length() > ZERO) {
					transferDestinationCode = transferInfo.getTransferStateId();
				}
				if (cdi.equals(OUT_OF_COUNTY)){
					transferCaseBean.setReceivingCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setTransferringCountyCode(transferDestinationCode);
					transferCaseBean.setPersonId(caseAssignment.getDefendantId());
					transferCaseBean.setOtherCountyPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyCaseNum(transferInfo.getOtherCaseNum());
				} else {
					transferCaseBean.setReceivingCountyCode(transferDestinationCode);
					transferCaseBean.setTransferringCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyPersonId(caseAssignment.getDefendantId());
					//transferCaseBean.setOtherCountyCaseNum(caseAssignment.getCriminalCaseId().substring(THREE));
					transferCaseBean.setOtherCountyCaseNum(PDConstants.BLANK);
				}
				transferCaseBean.setRejected(transferInfo.isRejected());
				transferCaseBean.setCicsClassificationSequenceNo(transferInfo.getTransferOutClassificationSeqNum());
				transferCaseBean.setCicsSubclassificationSequenceNo(transferInfo.getTransferOutSubclassificationSeqNum());
				requestEvent.setTransferCaseBean(transferCaseBean);
				MessageUtil.postRequest(requestEvent);
			}
		}			
		//Update transfer in info
		if (transferInfo.getTransferInClassificationSeqNum() != null
				&& !transferInfo.getTransferInClassificationSeqNum().equals(PDConstants.BLANK)) {
			if (transferInfoChanged(transferInfo, prevTransferInfo, true)){
				transferCaseBean.setTransferDate(DateUtil.stringToDate(transferInfo.getTransferInDate(), DateUtil.DATE_FMT_1));
				transferCaseBean.setTransferType(getTransferTypeValue(UIConstants.CS_TRANSFER_IN_CASES, cdi));
				transferCaseBean.setRejected(transferInfo.isRejected());
				if (transferInfo.getTransferTxCountyId() != null 
						&& transferInfo.getTransferTxCountyId().length() > ZERO) {
					transferDestinationCode = transferInfo.getTransferTxCountyId();
				} else if (transferInfo.getTransferStateId() != null 
						&& transferInfo.getTransferStateId().length() > ZERO) {
					transferDestinationCode = transferInfo.getTransferStateId();
				}
				if (cdi.equals(OUT_OF_COUNTY)){
					transferCaseBean.setTransferringCountyCode(transferDestinationCode);
					transferCaseBean.setReceivingCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setPersonId(caseAssignment.getDefendantId());
					transferCaseBean.setOtherCountyPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyCaseNum(transferInfo.getOtherCaseNum());
				} else {
					transferCaseBean.setTransferringCountyCode(HARRIS_COUNTY_CODE);
					transferCaseBean.setReceivingCountyCode(transferDestinationCode);
					transferCaseBean.setPersonId(transferInfo.getPersonId());
					transferCaseBean.setOtherCountyPersonId(caseAssignment.getDefendantId());
					//transferCaseBean.setOtherCountyCaseNum(caseAssignment.getCriminalCaseId().substring(THREE));
					transferCaseBean.setOtherCountyCaseNum(PDConstants.BLANK);
				}
				transferCaseBean.setCicsClassificationSequenceNo(transferInfo.getTransferInClassificationSeqNum());
				transferCaseBean.setCicsSubclassificationSequenceNo(transferInfo.getTransferInSubclassificationSeqNum());
				requestEvent.setTransferCaseBean(transferCaseBean);
				MessageUtil.postRequest(requestEvent);
			}		
		}

	}
	
	/**
	 * For Harris County cases i.e. cases having cdi = {002, 003} transfer type = 1 is used for transfer out
	 * and transfer type = 4 is used for transfer in. Similarly, for Out Of County cases i.e. cases having 
	 * cdi = {010} transfer type = 3 is used for transfer out and transfer type = 2 is used for transfer in. 
	 * These values are consistent with legacy database.
	 */
	private String getTransferTypeValue(String transferType, String cdi) {
		String val = null; 
		if (transferType == UIConstants.CS_TRANSFER_OUT_CASES) {
			if (cdi.equals(OUT_OF_COUNTY)){
				val = "3";
			} else {
				val = "1";
			}
		} else if (transferType == UIConstants.CS_TRANSFER_IN_CASES) {
			if (cdi.equals(OUT_OF_COUNTY)){
				val = "2";
			} else {
				val = "4";	
			}
		}
		return val;
	}
}
