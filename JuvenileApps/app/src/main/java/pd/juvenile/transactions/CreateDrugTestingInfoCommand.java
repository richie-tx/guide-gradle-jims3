package pd.juvenile.transactions;

import naming.UIConstants;
import pd.juvenile.DrugTesting;
import pd.juvenilecase.casefile.Activity;
import messaging.juvenile.CreateDrugTestingInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.DateUtil;

public class CreateDrugTestingInfoCommand implements ICommand
{
    public CreateDrugTestingInfoCommand(){}
    
    public void execute(IEvent event) {
	Home home = new Home();
	
	CreateDrugTestingInfoEvent createEvent = (CreateDrugTestingInfoEvent)event;
	DrugTesting drugTesting = new DrugTesting();
	drugTesting.setAssociateCasefile(createEvent.getAssociateCasefile());
	drugTesting.setJuvenileId(createEvent.getJuvenileNumber());
	drugTesting.setTestAdministered(createEvent.getTestAdministered());
	if( createEvent.getTestDate() != null 
		&& createEvent.getTestDate().length() > 0 ) {
	    drugTesting.setTestDate(DateUtil.stringToDate(createEvent.getTestDate(),DateUtil.DATE_FMT_1 )) ;
	}
	 
	if ( createEvent.getTestTime() != null
		&& createEvent.getTestTime().length() > 0 ) {
	    drugTesting.setTestTime(DateUtil.stringToDate(createEvent.getTestTime(),UIConstants.TIME_FMT_1AMPM));
	}
	drugTesting.setSubstanceTested(createEvent.getSubstanceTested());
	drugTesting.setDrugTestResults(createEvent.getDrugTestResult());
	drugTesting.setTestLocation(createEvent.getTestLocation());
	drugTesting.setAdministeredBy(createEvent.getAdministeredBy());
	drugTesting.setComments(createEvent.getComments());
	
	
	Activity activity = new Activity();
	activity.setSupervisionNumber( createEvent.getAssociateCasefile() );
	if( createEvent.getTestDate() != null 
		&& createEvent.getTestDate().length() > 0 ) {
	    activity.setActivityDate(DateUtil.stringToDate(createEvent.getTestDate(),DateUtil.DATE_FMT_1 )) ;
	}
	if ( createEvent.getTestTime() != null
		&& createEvent.getTestTime().length() > 0 ) {
	    activity.setActivityTime(createEvent.getTestTime());
	}
	
	if ( !"YES".equals( createEvent.getTestAdministered() ) ){
	    activity.setActivityCodeId( createEvent.getTestAdministered() );
	} else {
	    if ("UIV".equals( createEvent.getDrugTestResult() )){
		activity.setActivityCodeId("UTI");
	    } else {
		activity.setActivityCodeId( createEvent.getDrugTestResult() );
	    }
	}

	String comments = createEvent.getSubstanceTested() 
				+ " : " + createEvent.getTestLocation()
				+ " : " + createEvent.getAdministeredBy()
				+ " : " + createEvent.getComments() ;
	activity.setComments( comments  );
	
	
	try {
	    home.bind(activity);
	    String activityId = activity.getOID();
	    drugTesting.setActivityId(activityId);
	    home.bind(drugTesting);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	
    }

}
