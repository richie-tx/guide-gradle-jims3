package ui.common;

import java.util.Comparator;

import ui.supervision.programReferral.ReferralFormField;

public class RefFormFieldComparator implements Comparator
{

	public int compare(Object o1, Object o2)
	{
		if(o1==null || o2==null || !(o1 instanceof ReferralFormField) || !(o2 instanceof ReferralFormField))
		{
			return 1;
		}
		
		ReferralFormField refFormField1 = (ReferralFormField) o1;
		ReferralFormField refFormField2 = (ReferralFormField) o2;
		
		if(refFormField1.getRowSequence()== refFormField2.getRowSequence())
		{
			if(refFormField1.getColumnSequence() < refFormField2.getColumnSequence())
				return -1;
			else if(refFormField1.getColumnSequence()> refFormField2.getColumnSequence())
				return 1;
			else
				return 0;
		}
		else if(refFormField1.getRowSequence() < refFormField2.getRowSequence())
			return -1;
		else			
			return 1;
	}

}
