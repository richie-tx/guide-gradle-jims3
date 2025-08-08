package pd.supervision.transfers.transactions;

import naming.PDConstants;
import messaging.transfers.UpdateTransferCaseEvent;
import messaging.transfers.to.TransferCaseBean;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.DateUtil;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.transfers.TransferCase;

public class UpdateTransferCaseCommand implements ICommand {

	public void execute(IEvent event) throws Exception {
		UpdateTransferCaseEvent requestEvent = (UpdateTransferCaseEvent) event;
		TransferCaseBean bean = requestEvent.getTransferCaseBean();		
		
		TransferCase transferCase = new TransferCase();
		transferCase.setCdi(bean.getCdi());
		transferCase.setCriminalCaseId(bean.getCriminalCaseId());
		transferCase.setTransactionDate(DateUtil.dateToString(bean.getTransferDate(), "yyyyMMdd"));
		String aPaddedCode = null;
		Integer anInteger = null;
		if (bean.getReceivingCountyCode() != null 
				&& !bean.getReceivingCountyCode().equals(PDConstants.BLANK)){
			try {
				anInteger = new Integer(bean.getReceivingCountyCode());
				aPaddedCode = this.padWithZeroes(bean.getReceivingCountyCode(), 3);
				transferCase.setReceivingCountyCode(aPaddedCode);
			} catch (Exception e) { //Not numeric. Must be a state code.
				transferCase.setReceivingCountyCode(bean.getReceivingCountyCode());
			}
		}
		if (bean.getTransferringCountyCode() != null 
				&& !bean.getTransferringCountyCode().equals(PDConstants.BLANK)){
			try {
				anInteger = new Integer(bean.getTransferringCountyCode());
				aPaddedCode = this.padWithZeroes(bean.getTransferringCountyCode(), 3);
				transferCase.setTransferringCountyCode(aPaddedCode);
			} catch (Exception e) {//Not numeric.  Must be a state code.
				transferCase.setTransferringCountyCode(bean.getTransferringCountyCode());
			}
		}

		transferCase.setTransferType(bean.getTransferType());
		transferCase.setRejected(bean.isRejected()?"Y":"N");
		transferCase.setCicsRecordClassSeqNumber(bean.getCicsClassificationSequenceNo());
		transferCase.setCicsRecordSubclassSeqNumber(bean.getCicsSubclassificationSequenceNo());
		transferCase.setActionType(bean.getActionType());
		transferCase.setOriginationCountyPersonId(bean.getOtherCountyPersonId());
		transferCase.setOtherCountyCaseId(bean.getOtherCountyCaseNum());
		transferCase.setSupervisingCountyPersonId(bean.getPersonId());
		//Do a hard bind so that T30 record is not created if there is an error returned from CICS.
		
		UserProfile userProfile = UserProfile.find( PDSecurityHelper.getLogonId() );
		String OpId = userProfile.getOperatorId();
		transferCase.setOperatorId( OpId );
		
		new Home().bind(transferCase);

		//CSTSHelper.createCSTSRecordForHarrisCountyCase(transferCase);
	}

	private String padWithZeroes(String aCode, int length) {
		StringBuffer padded = new StringBuffer();
		if (aCode != null && !aCode.equals(PDConstants.BLANK)){
			padded.append(aCode);
			while (padded.length() < length){
				padded.insert(0, "0");
			}
		}
		return padded.toString();
	}

	public void onRegister(IEvent event) {

	}

	public void onUnregister(IEvent event) {

	}

	public void update(Object updateObject) {

	}

}
