package pd.supervision.administercaseload.transactions;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.administercaseload.GetRecentDisposedCasesEvent;
import messaging.administercaseload.reply.DisposedCaseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import pd.codetable.Code;
import pd.codetable.criminal.OffenseCode;
import pd.common.util.StringUtil;
import pd.supervision.administercaseload.DisposedCase;

public class GetRecentDisposedCasesCommand implements ICommand
{

    /**
     * @roseuid 464360260252
     */
    public GetRecentDisposedCasesCommand()
    {

    }
    /**
     * @param event
     * @roseuid 46433E1C016C
     */
    public void execute(IEvent anEvent) {
		GetRecentDisposedCasesEvent cEvent = (GetRecentDisposedCasesEvent) anEvent;

		Iterator recentDisposedCasesIter = DisposedCase.findAll(cEvent);

		while (recentDisposedCasesIter != null && recentDisposedCasesIter.hasNext()) {
			
			DisposedCase disposedCase = (DisposedCase) recentDisposedCasesIter.next();
			if (disposedCase != null) 
			{
				DisposedCaseResponseEvent respEvent = new DisposedCaseResponseEvent();
				
				if (!StringUtil.isEmpty(disposedCase.getDispositionCd())) {
					String disCode = disposedCase.getDispositionCd().trim();
					Code code = Code.find( PDCodeTableConstants.DISP_TYPE, disCode );
					
					if ( code != null ){
						
						String disDesc = code.getDescription();
						respEvent.setDispositionDesc( disDesc );
						
					}
				}
				if (!StringUtil.isEmpty(disposedCase.getOffenseCd())) 
				{
					respEvent.setOffenseCd(disposedCase.getOffenseCd());
					OffenseCode offCode = disposedCase.getOffense();
					
					if ( offCode != null )
					{	
						respEvent.setOffenseDesc(offCode.getDescription());
					}
					
				}
				if (!StringUtil.isEmpty(disposedCase.getOffenseDate())) 
				{
					respEvent.setOffenseDate(disposedCase.getOffenseDate().trim());
					respEvent.setPriorOffenseDate(DateUtil.stringToDate(formatOffenseDate(disposedCase.getOffenseDate().trim()), DateUtil.DATE_FMT_1));
				}
				if (!StringUtil.isEmpty(disposedCase.getCriminalCaseId()))
				{
					respEvent.setCriminalCaseId( disposedCase.getCriminalCaseId());
				}
				if (!StringUtil.isEmpty(disposedCase.getCdi()))
				{
					respEvent.setCdi(disposedCase.getCdi());
				}
				if (!StringUtil.isEmpty(disposedCase.getDispositionCd()))
				{
					respEvent.setDispositionCd(disposedCase.getDispositionCd());
				}
				MessageUtil.postReply(respEvent);
			}
		}

	}

    private String formatOffenseDate(String offenseDate){
		StringBuffer formattedOffenseDate = new StringBuffer();
		if(offenseDate != null && !offenseDate.equals("")){
			String year = offenseDate.substring(4,6);
			String month = offenseDate.substring(0,2);
			String day = offenseDate.substring(2,4);
			
			if(offenseDate.length() == 6){        				
				if(60 < Integer.parseInt(year)){  
					year = "19" + year;
				}else{
					year = "20" + year;
				}
			}
			
			formattedOffenseDate.append(month);
			formattedOffenseDate.append("/");	        				
			formattedOffenseDate.append(day);
			formattedOffenseDate.append("/");
			formattedOffenseDate.append(year);			
		}
		return formattedOffenseDate.toString();
	}
   
    /**
     * @param event
     * @roseuid 46433E1C016E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C0170
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 46433E1C017C
     */
    public void update(Object anObject)
    {

    }
}
