/*
 * Created on Apr 19, 2006
 *
 */
package pd.supervision.Factory;

import java.util.Iterator;

import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.managesupervisioncase.GetOutOfCountyCasesEvent;
import mojo.km.persistence.Home;
import naming.PDCodeTableConstants;
import pd.supervision.managesupervisioncase.OutOfCountyCase;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
import pd.supervision.managesupervisioncase.PreTrialSupervisionCase;

/**
 * @author jmcnabb
 *
 * Factory class for Out Of County Supervision Cases.
 * Currently, there are only 2 types.
 */
public class OutOfCountyCaseFactory
{
	public static OutOfCountyCase createCase(String courtDivisionId)
	{
		OutOfCountyCase newCase = null;
		if (courtDivisionId.equals(PDCodeTableConstants.CSCD))
		{
			newCase = new OutOfCountyProbationCase();
		}
		else
		if (courtDivisionId.equals(PDCodeTableConstants.PTS))
		{
			newCase = new PreTrialSupervisionCase();
		}
		else
		{
			// Invalid Court Division for Out of County case
			throw new RuntimeException("Invalid Court Division <" + courtDivisionId + "> for Out of County case.");
		}
		
		newCase.setCourtDivisionId(courtDivisionId);
		return newCase;
	}

	public static OutOfCountyCase find(String caseNum, String courtDivisionId)
	{
		GetOutOfCountyCaseEvent findEvent = new GetOutOfCountyCaseEvent();
		findEvent.setCaseNum(caseNum);
		findEvent.setCourtDivisionId(courtDivisionId);
		
		Iterator cases = null;
		if (courtDivisionId.equals(PDCodeTableConstants.CSCD))
		{
			cases = new Home().findAll(findEvent, OutOfCountyProbationCase.class);
		}
		else
		if (courtDivisionId.equals(PDCodeTableConstants.PTS))
		{
			cases = new Home().findAll(findEvent, PreTrialSupervisionCase.class);
		}
		else
		{
			// Invalid Court Division for Out of County case
			System.out.println("Invalid Court Division <" + courtDivisionId + "> for Out of County case.");
		}
		
		OutOfCountyCase oocCase = null;
		if (cases.hasNext())
		{
			oocCase = (OutOfCountyCase)cases.next();
		}
		return oocCase;
	}

	/**
	 * @return 
	 * @param event  Contains all necessary criteria for lookup.
	 */
	public static Iterator findAll(GetOutOfCountyCasesEvent event) {
		if (event.getUserAgencyId().equals(PDCodeTableConstants.CSCD_AGENCY))
		{
			event.setCourtDivisionId(PDCodeTableConstants.CSCD);
			return new Home().findAll(event, OutOfCountyProbationCase.class);
		}
		event.setCourtDivisionId(PDCodeTableConstants.PTS);
		return new Home().findAll(event, PreTrialSupervisionCase.class);
	}
	
	/**
	 * @return Class  
	 * @param courtDivisionId
	 */
	public static Class getClass(String courtDivisionId)
	{
		if (courtDivisionId.equals(PDCodeTableConstants.CSCD))
		{
			return OutOfCountyProbationCase.class;
		}
		else
		if (courtDivisionId.equals(PDCodeTableConstants.PTS))
		{
			return PreTrialSupervisionCase.class;
		}
		else
		{
			// Invalid Court Division for Out of County case
			throw new RuntimeException("Invalid Court Division <" + courtDivisionId + "> for Out of County case.");
		}
	}
}
