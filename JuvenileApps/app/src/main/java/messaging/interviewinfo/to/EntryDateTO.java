package messaging.interviewinfo.to;

import java.util.Date;

/**
 *
 */
public class EntryDateTO extends ExcludedTO implements Comparable 
{
	private Date entryDate = null;
	
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() 
	{
		return entryDate;
	}
	
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) 
	{
		this.entryDate = entryDate;
	}
	
	public int compareTo( Object obj )
	{
		int result = 0;
		
		if ( obj != null && ! (obj instanceof EntryDateTO) )
		{
			throw new IllegalArgumentException( "'obj' must be of type EntryDateTO." );
		}
		
		Date input = ((EntryDateTO)obj).getEntryDate();
		
		if ( entryDate == null && input == null)
		{
			result = 0;
		}
		else if(entryDate == null)
		{
			result = -1;
		}
		else if(input == null)
		{
			result = 1;
		}
		else
		{
			result = entryDate.compareTo(input);
		}
		
		result = -result;
			
			
		
		return result;  
	}
}
