/*
 * Created on Feb 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;

import java.util.Comparator;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
	public class SummaryComparator implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			if(o1==null || o2==null)
			{
				return 1;
			}
			
			CSCQuestion question1 = (CSCQuestion) o1;
			CSCQuestion question2 = (CSCQuestion) o2;
			
			if(question1.getSummaryRowSeq()== question2.getSummaryRowSeq())
			{
				if(question1.getSummaryColSeq() < question2.getSummaryColSeq())
					return -1;
				else if(question1.getSummaryColSeq()> question2.getSummaryColSeq())
					return 1;
				else
					return 0;
			}
			else if(question1.getSummaryRowSeq() < question2.getSummaryRowSeq())
				return -1;
			else			
				return 1;
		}
	}