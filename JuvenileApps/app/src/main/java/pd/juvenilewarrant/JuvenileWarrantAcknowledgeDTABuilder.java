/*
 * Created on Jul 15, 2005
 *
 */
package pd.juvenilewarrant;

import naming.PDJuvenileWarrantConstants;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import pd.contact.user.UserProfile;
import mojo.pattern.IBuilder;

/**
 * @author Jim Fisher
 *
 */
public class JuvenileWarrantAcknowledgeDTABuilder implements IBuilder
{
	private JuvenileWarrantResponseEvent responseEvent;
	private JuvenileWarrant warrant;

	public JuvenileWarrantAcknowledgeDTABuilder(JuvenileWarrant warrant)
	{
		this.warrant = warrant;
		this.responseEvent = new JuvenileWarrantResponseEvent();
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#build()
	 */
	public void build()
	{
		// Set the responseEvent in the warrantValues first
		this.setWarrantValues();
		this.setFileStamp();
		this.setOfficerValues();
		this.setSignatureValues();
	}

	/**
	 * 
	 */
	private void setWarrantValues()
	{
		responseEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_EVENT_TOPIC);
		responseEvent.setWarrantNum(warrant.getWarrantNum());
		responseEvent.setJuvenileNum(warrant.getJuvenileNum());
		responseEvent.setFirstName(warrant.getFirstName());
		responseEvent.setLastName(warrant.getLastName());
		responseEvent.setMiddleName(warrant.getMiddleName());
		responseEvent.setNameSuffix(warrant.getSuffix());

		responseEvent.setDateOfBirth(warrant.getDateOfBirth());

		// warrant originator
		responseEvent.setWarrantOriginatorId(warrant.getWarrantOriginatorUserId());
		responseEvent.setWarrantOriginatorName(warrant.getWarrantOriginatorName());
		responseEvent.setAffadivitStatement(warrant.getAffidavitStatement());
	}

	/**
	 * 
	 */
	private void setFileStamp()
	{
		UserProfile fileStampUser = warrant.getFileStampUser();

		if (fileStampUser != null)
		{
			String name = fileStampUser.getFirstName();
			this.responseEvent.setFileStampFirstName(name);
			name = fileStampUser.getMiddleName();
			this.responseEvent.setFileStampMiddleName(name);
			name = fileStampUser.getLastName();
			this.responseEvent.setFileStampLastName(name);
		}

		responseEvent.setFileStampDate(warrant.getFileStampDate());
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#getResult()
	 */
	public Object getResult()
	{
		// TODO Auto-generated method stub
		return responseEvent;
	}

	private void setOfficerValues()
	{
	    // The full name is being stored instead of the JPO userID 
	    responseEvent.setProbationOfficerOfRecordName(warrant.getProbationOfficerOfRecordId());	    		
		responseEvent.setOfficerId(warrant.getOfficerId());
	}

	/**
	 * 
	 */
	private void setSignatureValues()
	{
		responseEvent.setWarrantSignedStatusId(warrant.getWarrantSignedStatusId());
	}

}
