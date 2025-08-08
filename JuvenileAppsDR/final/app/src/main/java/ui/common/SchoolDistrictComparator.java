/*
 * Created on Jul 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import java.util.Comparator;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SchoolDistrictComparator implements Comparator
{

	public int compare(Object arg0, Object arg1)
	{		
		JuvenileSchoolDistrictCodeResponseEvent eventA = (JuvenileSchoolDistrictCodeResponseEvent) arg0;
		JuvenileSchoolDistrictCodeResponseEvent eventB = (JuvenileSchoolDistrictCodeResponseEvent) arg1;
		if (eventA.getDistrictDescription()==null){
			return -1;
		}
		if (eventB.getDistrictDescription()==null){
			return 1;
		}
		return eventA.getDistrictDescription().compareTo(eventB.getDistrictDescription());
		
	}
}
