/*
 * Created on May 15, 2007
 *
 */
package messaging.programreferral;



/**
 */
public interface ProgramReferralRetrieverAttribute {
	
		public static final String CASEFILE = "CASEFILE_ID";
		public static final String PROGRAM = "PROVPROGRAM_ID";
		public static final String JUVENILE= "JUVENILE_ID";
		public static final String STATUSCD= "STATUSCD";
		public static final String SUBSTATUSCD= "SUBSTATUSCD";
		public static final String SERVICEPROVIDER= "JUVSERVPROV_ID";
		public static final String JUVLOCUNIT= "JUVLOCUNIT_ID";
		public static final String SPRVISIONTYPE= "SPRVSIONTYPECD";
		public static final String BEGINDATE= "BEGINDATE";
		public static final String ENDDATE= "ENDDATE";
		public static final String BCLOSEDATE= "BCLOSEDATE";
		public static final String ECLOSEDATE= "ECLOSEDATE";
		public static final String OFFICERLASTNAME= "OFFICERLNAME";
		public static final String OFFICERFIRSTNAME= "OFFICERFNAME";
		public static final String OFFICERMIDDLENAME= "OFFICERMNAME";
		
		//TODO - not the final constants, find out what they need to be against M204
		public static final String JUVLASTNAME= "JUVENILELNAME";
		public static final String JUVFIRSTNAME= "JUVENILEFNAME";
		public static final String JUVMIDDLENAME= "JUVENILEMNAME";
		
		public String getAttributeName();
		public String getAttributeValue();		
		public void setAttributeValue(String attributeValue);	
}
