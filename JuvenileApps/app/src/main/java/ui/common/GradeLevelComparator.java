/*
 * Created on Jun 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import java.util.Comparator;

import messaging.codetable.reply.CodeResponseEvent;
import naming.PDCodeTableConstants;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GradeLevelComparator implements Comparator
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1)
	{
		int result = 0;

		String gradeA = ((CodeResponseEvent) arg0).getCode();
		String gradeB = ((CodeResponseEvent) arg1).getCode();

		int gradeAValue = this.parseGrade(gradeA);
		int gradeBValue = this.parseGrade(gradeB);

		if (gradeAValue < gradeBValue)
		{
			result = -1;
		}
		else if (gradeAValue > gradeBValue)
		{
			result = 1;
		}

		return result;
	}

	// 10/08/2012 revised coding in getGradeLevelsCodes() to only pass numeric values
	// this allows for future additions of values that are not numeric which "C" for college 
	// was added to code table by Carla that caused parsing error.	
	private int parseGrade(String grade)
	{
		int returnGrade = 0;
		try
		{
			returnGrade = Integer.parseInt(grade);
		} 
		catch (Exception e)
		{
			returnGrade = 0;
		}
	
//		if (PDCodeTableConstants.KINDERGARTEN.equals(grade) == false)
//					
//		{
//			returnGrade = Integer.parseInt(grade);
//		}
		return returnGrade;
	}

}
