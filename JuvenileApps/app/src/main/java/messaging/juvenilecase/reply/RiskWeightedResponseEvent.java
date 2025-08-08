/*
 * Created on Oct 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Comparator;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskWeightedResponseEvent extends ResponseEvent implements Comparable
{
	private int weightedResponseID;
	private String answerText;
	private int weight;
	private int sortOrder;
	private String riskQuestionsId;
	private String subordinateQuestionId;
	private String action;
	
	/**
	 * @return
	 */
	public String getAnswerText()
	{
		return answerText;
	}

	/**
	 * @return
	 */
	public String getRiskQuestionsId()
	{
		return riskQuestionsId;
	}

	/**
	 * @return
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * @return
	 */
	public int getSortOrder()
	{
		return sortOrder;
	}

	/**
	 * @return
	 */
	public int getWeightedResponseID()
	{
		return weightedResponseID;
	}

	/**
	 * @param string
	 */
	public void setAnswerText(final String string)
	{
		answerText = string;
	}

	/**
	 * @param string
	 */
	public void setRiskQuestionsId(final String string)
	{
		riskQuestionsId = string;
	}

	/**
	 * @param i
	 */
	public void setWeight(final int i)
	{
		weight = i;
	}
	
	/**
	 * @param i
	 */
	public void setSortOrder(final int i)
	{
		sortOrder = i;
	}

	/**
	 * @param i
	 */
	public void setWeightedResponseID(final int i)
	{
		weightedResponseID = i;
	}
	
	/**
	 * @param subordinateQuestionId
	 */
	public void setSubordinateQuestionId(String subordinateQuestionId) {
		this.subordinateQuestionId = subordinateQuestionId;
	}

	/**
	 * @return
	 */
	public String getSubordinateQuestionId() {
		return subordinateQuestionId;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/*
	 * 
	 */
	public static Comparator RiskQuestionComparator = new Comparator() 
	{
		public int compare(Object riskQuestion, Object otherRiskQuestion) 
		{
		  int riskOrder = ((RiskWeightedResponseEvent)riskQuestion).getSortOrder();
		  int otherRiskOrder = ((RiskWeightedResponseEvent)otherRiskQuestion).getSortOrder();  
		 
		  return( riskOrder - otherRiskOrder );    
		}	
	};

	/*
	 * 
	 */
	public int compareTo(Object respEvent) throws ClassCastException 
	{
    int otherSortOrder = ((RiskWeightedResponseEvent)respEvent).getSortOrder();
    int rtn = this.sortOrder - otherSortOrder ;

    /* if the sortOrder #'s for both objects are of equal value, 
     * then let's assume the sort order has not been properly applied 
     * in the table, so we will sort based on the text of the question.
     */
    if( rtn == 0 )
    {
    	rtn = compareToUsingQuestionText( this, respEvent ) ;
    }
    
    return( rtn ) ;    
	 }


	/** Sort in order of ascending questionNbr
	 * @see Comparable#compareTo(Object)
	 */
	
	public int compareToUsingQuestionText( Object questionEvent, Object otherQuestionEvent )
	{
		int result = 0;
		String answerText = ((RiskWeightedResponseEvent)questionEvent).getAnswerText() ;
		String otherAnswerText = ((RiskWeightedResponseEvent)otherQuestionEvent).getAnswerText() ;
		
		/* if (this > other) return > 0
		* if (this == other) return 0
		* if (this < other) return < 0
		*/
		
		if( answerText.indexOf("+") > 0 )
	  { // found a + sign ... example: 15+
			answerText = answerText.substring(0, answerText.indexOf("+"));
//		   result = -1;
	  }
		if(answerText.indexOf("+") < 0)
		{ // + sign not found
			try
			{ // see if the answers are numbers
				int thisNum = Integer.parseInt( answerText );
				int otherNum = Integer.parseInt( otherAnswerText );
				result = thisNum - otherNum ;
			}
			catch( NumberFormatException nfe )
			{ // regular strings
				result = answerText.compareTo( otherAnswerText );
				if( answerText.equalsIgnoreCase("no") || 
						answerText.equalsIgnoreCase("yes") )
				{
					result *= (-1);
				}
			} // catch
		}
		else
		{
			result = answerText.compareTo( otherAnswerText );	
		}
		
		return result;
	}
	
}
