/*
 * Created on Jan 18, 2007
 *
 */
package ui.juvenilecase.schedulecalendarevent;


/**
 * @author C_NRaveendran
 *
 */
public class  CalendarRetrieverFactory {

	private CalendarRetrieverFactory() {

	}
	public static final String PRESCHEDULED_RETRIEVER = "PRESCHEDULED";
	public static final String LOCATION_RETRIEVER = "LOCATION_RETRIEVER";
	public static final String ACTIVEEVENTS_RETRIEVER = "ACTIVEEVENTS_RETRIEVER";
	public static final String PROVIDER_EVENTS_RETRIEVER = "PROVIDEREVENTS_RETRIEVER";
	
	public static String getRetrieverName(String retrieverType){
		String defaultRetriever = "pd.supervision.calendar.ServiceEventRetriever";
		String retriever = defaultRetriever;
		if (retrieverType.equals(CalendarRetrieverFactory.PRESCHEDULED_RETRIEVER)){
			retriever = "pd.supervision.calendar.PrescheduledServiceEventRetriever";
		}else if (retrieverType.equals(CalendarRetrieverFactory.LOCATION_RETRIEVER)){
			retriever = "pd.supervision.calendar.LocationServiceEventRetriever";
		}else if (retrieverType.equals(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER)){
			retriever = "pd.supervision.calendar.ActiveServiceEventRetriever";
		}else if (retrieverType.equals(CalendarRetrieverFactory.PROVIDER_EVENTS_RETRIEVER)){
			retriever = "pd.supervision.calendar.ProviderServiceEventRetriever";
		}
		return retriever;
	}
}
