//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\GetJournalEntriesCommand.java

package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.PDCasefileJournalHelper;
import naming.PDJuvenileCaseConstants;
import messaging.casefile.GetJournalEntriesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class GetJournalEntriesCommand implements ICommand 
{
   /**
    * @roseuid 47E25729027E
    */
   public GetJournalEntriesCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 47E163730249
    */
   public void execute(IEvent event) 
   {
	   GetJournalEntriesEvent entries = (GetJournalEntriesEvent)event;
	   String journalCategoryCd = entries.getJournalCategoryCd();
	   
	   if( journalCategoryCd != null )
	   {
		   if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_ACTIVITIES))
		   {
			   PDCasefileJournalHelper.fetchActivityJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_RISKANAL))
		   {
			   PDCasefileJournalHelper.fetchRiskAnalJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_CLOSINGINFO))
		   {
			   PDCasefileJournalHelper.fetchClosingJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_GOALS))
		   {
			   PDCasefileJournalHelper.fetchGoalJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_TRAITS))
		   {
			   PDCasefileJournalHelper.fetchTraitJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_PROGREF))
		   {
			   PDCasefileJournalHelper.fetchProgReferralJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CATEGORY_CALENDAREVENTS))
		   {
			   PDCasefileJournalHelper.fetchCalEventsJournalEntries(entries);
		   }
		   else if(journalCategoryCd.equalsIgnoreCase(PDJuvenileCaseConstants.JOURNAL_CASE_REVIEW_SUMMARY))
		   {
			   PDCasefileJournalHelper.getCaseReviewJournalSummaryEntries(entries);
		   }
		   else
		   {
			   PDCasefileJournalHelper.getAllJournalEntries(entries);
		   }
	   }
   }
   
   /**
    * @param event
    * @roseuid 47E16373024B
    */
   public void onRegister(IEvent event) 
   {
   }
   
   /**
    * @param event
    * @roseuid 47E163730259
    */
   public void onUnregister(IEvent event) 
   {
   }
   
   /**
    * @param anObject
    * @roseuid 47E16373025B
    */
   public void update(Object anObject) 
   {
   }
}
