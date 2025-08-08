/*
 * Created on Sep 29, 2005
 *
 */
package mapping.juvenilewarrant;

import messaging.juvenilewarrant.SearchJuvenileWarrantEvent;
import mojo.km.messaging.IEvent;
import naming.PDJuvenileWarrantConstants;

/**
 * @author Jim Fisher
 *
 */
public class SearchJuvenileWarrantQueryAssembler
{
	private static final String ACTIVATION_STATUS = "WACTIVATIONSTAT";
	private static final String SINGLE_QUOTE = "'";
	private static final String DALOG_NUMBER = "DALOGNUMBER";
	private static final String REFERRAL_NUMBER = "REFERRALNUMBER";
	private static final String JUV_FIRST_NAME = "JUVFIRSTNAME";
	private static final String JUV_LAST_NAME = "JUVLASTNAME";
	private static final String DATE_OF_BIRTH = "DATEOFBIRTH";
	//private static final String PETITION_NUMBER = "PETITIONNUMBER";
	private static final String JUVENILE_NUMBER = "JUVNUMBER";

	private static final String AND = " AND ";
	private static final String OR = " OR ";
	private static final String EQUALS = "=";
	private static final String LEFT_PAREN = "(";
	private static final String RIGHT_PAREN = ")";

	private SearchJuvenileWarrantEvent queryEvent;

	private StringBuffer buffer;

	public SearchJuvenileWarrantQueryAssembler()
	{
	}

	
	/* (non-Javadoc)
	 * @see pd.pattern.IAssembler#assemble()
	 */
	public String getClause(IEvent event)
	{
		this.buffer = new StringBuffer();
		this.queryEvent = (SearchJuvenileWarrantEvent) event;

		this.assembleStatusClause();
		this.assembleMainClause();

		return this.buffer.toString();
	}

	/**
	 * @return
	 */
	private void assembleMainClause()
	{
		if (this.hasJuvenileNumber())
		{
			buffer.append(AND);
			buffer.append(JUVENILE_NUMBER);
			buffer.append(EQUALS);
			buffer.append(queryEvent.getJuvenileNum());

		}
		else
		{

			if ((this.hasDaLogNumber() || this.hasReferralAndPetition()) || (this.hasNameAndDob()))
			{
				buffer.append(AND);
			}
			buffer.append(LEFT_PAREN);

			boolean isORReqd = false;
			if (this.hasDaLogNumber())
			{
				buffer.append(LEFT_PAREN);
				this.assembleDaLogNumberClause();
				isORReqd = true;
				buffer.append(RIGHT_PAREN);
			}
			
			if (this.hasReferralAndPetition())
			{
				if (isORReqd)
				{
					buffer.append(OR);
				}
				buffer.append(LEFT_PAREN);
				this.assembleReferralAndPetition();
				isORReqd = true;
				buffer.append(RIGHT_PAREN);
			}
			
			if (this.hasNameAndDob())
			{

				if (isORReqd)
				{
					buffer.append(OR);
				}
				buffer.append(LEFT_PAREN);
				this.assembleNameAndDob();
				buffer.append(RIGHT_PAREN);
			}
			buffer.append(RIGHT_PAREN);
		}

	}

	/**
	 * @return
	 */
	private boolean hasJuvenileNumber()
	{
		return (queryEvent.getJuvenileNum() != null);
	}

	/**
	 * 
	 */
	private void assembleNameAndDob()
	{
		boolean isANDReq = false;

		String firstName = queryEvent.getJuvenileFirstName();
		if (firstName != null)
		{
			buffer.append(JUV_FIRST_NAME);
			buffer.append(EQUALS);
			buffer.append(SINGLE_QUOTE);
			buffer.append(firstName.toUpperCase());
			buffer.append(SINGLE_QUOTE);
			isANDReq = true;
		}

		String lastName = queryEvent.getJuvenileLastName();
		if (lastName != null)
		{
			if (isANDReq)
			{
				buffer.append(AND);
			}
			buffer.append(JUV_LAST_NAME);
			buffer.append(EQUALS);
			buffer.append(SINGLE_QUOTE);
			buffer.append(lastName.toUpperCase());
			buffer.append(SINGLE_QUOTE);
			isANDReq = true;
		}

		String dateOfBirth = queryEvent.getDateOfBirth();
		if (dateOfBirth != null)
		{
			if (isANDReq)
			{
				buffer.append(AND);
			}
			buffer.append("coalesce(");
			buffer.append(DATE_OF_BIRTH);
			buffer.append(",'' ) like '");
			buffer.append(queryEvent.getDateOfBirth());
			buffer.append("%'");
		}
	}

	/**
	 * 
	 */
	private void assembleReferralAndPetition()
	{
		buffer.append(REFERRAL_NUMBER);
		buffer.append(EQUALS);
		buffer.append(queryEvent.getReferralNumber());
	}

	/**
	 * 
	 */
	private void assembleDaLogNumberClause()
	{
		buffer.append(DALOG_NUMBER);
		buffer.append(EQUALS);
		buffer.append(queryEvent.getDaLogNumber());
	}

	private void assembleStatusClause()
	{
		buffer.append("(");
		buffer.append(ACTIVATION_STATUS);
		buffer.append(" = '");
		buffer.append(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
		buffer.append("' OR ");
		buffer.append(ACTIVATION_STATUS);
		buffer.append(" = '");
		buffer.append(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
		buffer.append("' OR ");
		buffer.append(ACTIVATION_STATUS);
		buffer.append(" = '");
		buffer.append(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE);
		buffer.append("' OR ");
		buffer.append(ACTIVATION_STATUS);
		buffer.append(" = '");
		buffer.append(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_UNSEND);
		buffer.append("' OR ");
		buffer.append(ACTIVATION_STATUS);
		buffer.append(" = '");
		buffer.append(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_REJECTED);
		buffer.append("')");
	}


	private boolean hasDaLogNumber()
	{
		return queryEvent.getDaLogNumber() != null;
	}

	private boolean hasReferralAndPetition()
	{
		return queryEvent.getReferralNumber() != null;
	}

	private boolean hasNameAndDob()
	{
		return queryEvent.getJuvenileLastName() != null || queryEvent.getDateOfBirth() != null;
	}

	public static void main(String[] args)
	{
		System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

		SearchJuvenileWarrantQueryAssembler queryAssembler = new SearchJuvenileWarrantQueryAssembler();
		SearchJuvenileWarrantEvent event = new SearchJuvenileWarrantEvent();
		event.setDaLogNumber("5");
		event.setDateOfBirth("1/1/2000");
	}

}
