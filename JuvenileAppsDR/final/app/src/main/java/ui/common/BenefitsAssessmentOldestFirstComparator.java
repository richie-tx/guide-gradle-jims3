/*
 * Created on Oct 10, 2007
 * 
 */
package ui.common;

import java.util.Comparator;

import messaging.juvenilecase.reply.RequestedBenefitsAssessmentResponseEvent;

/**
 * @author awidjaja
 *
 */
public class BenefitsAssessmentOldestFirstComparator implements Comparator
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1)
	{
		int result = 0;

		RequestedBenefitsAssessmentResponseEvent obj1 = (RequestedBenefitsAssessmentResponseEvent)arg0;
		RequestedBenefitsAssessmentResponseEvent obj2 = (RequestedBenefitsAssessmentResponseEvent)arg1;
		
		if(obj2 == null || obj2.getRequestDate() == null) {
			return -1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		}
		if(obj1 == null || obj1.getRequestDate() == null) {
			return 1;// this makes any null objects go to the bottom change this to 1 if you want the top of the list
		}
		
		return obj1.getRequestDate().compareTo(obj2.getRequestDate());
	}
}
