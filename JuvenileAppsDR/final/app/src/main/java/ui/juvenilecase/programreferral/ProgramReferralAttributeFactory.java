/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import messaging.programreferral.ProgramReferralRetrieverAttribute;
import messaging.programreferral.ProgramReferralRetrieverAttributeImpl;

/**
 */
public class ProgramReferralAttributeFactory {
	
	public static ProgramReferralRetrieverAttribute getCasefileAttribute() {
			return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.CASEFILE);					
	}
	
	public static ProgramReferralRetrieverAttribute getProgramAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.PROGRAM);					
	}
	
	public static ProgramReferralRetrieverAttribute getJuvenileAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.JUVENILE);					
	}

	public static ProgramReferralRetrieverAttribute getStateAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.STATUSCD);					
	}
	
	public static ProgramReferralRetrieverAttribute getSubStateAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.SUBSTATUSCD);					
	}

	public static ProgramReferralRetrieverAttribute getServiceProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.SERVICEPROVIDER);					
	}
	
	public static ProgramReferralRetrieverAttribute getBeginDateProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.BEGINDATE);					
	}
	
	public static ProgramReferralRetrieverAttribute getEndDateProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.ENDDATE);					
	}
	
	public static ProgramReferralRetrieverAttribute getClosedBeginDateProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.BCLOSEDATE);					
	}
	
	public static ProgramReferralRetrieverAttribute getClosedEndDateProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.ECLOSEDATE);					
	}
	
	public static ProgramReferralRetrieverAttribute getOfficerLastNameProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.OFFICERLASTNAME);					
	}
	
	public static ProgramReferralRetrieverAttribute getOfficerFirstNameProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.OFFICERFIRSTNAME);					
	}
	
	public static ProgramReferralRetrieverAttribute getOfficerMiddleNameProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.OFFICERMIDDLENAME);					
	}
	
	public static ProgramReferralRetrieverAttribute getJuvenileLastNameProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.JUVLASTNAME);					
	}
	
	public static ProgramReferralRetrieverAttribute getJuvenileFirstNameProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.JUVFIRSTNAME);					
	}
	
	public static ProgramReferralRetrieverAttribute getJuvenileMiddleNameProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.JUVMIDDLENAME);					
	}
	
	public static ProgramReferralRetrieverAttribute getJuvenileLocationProviderAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.JUVLOCUNIT);					
	}
	
	public static ProgramReferralRetrieverAttribute getJuvenileSupervisionTypeAttribute() {
		return new ProgramReferralRetrieverAttributeImpl(ProgramReferralRetrieverAttribute.SPRVISIONTYPE);					
	}
	
	private ProgramReferralAttributeFactory() {
				
	}
}
