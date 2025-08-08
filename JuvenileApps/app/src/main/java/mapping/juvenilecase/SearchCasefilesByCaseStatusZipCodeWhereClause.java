/*
 */
package mapping.juvenilecase;

import messaging.juvenilecase.SearchJuvenileCasefileByZipCodeEvent;
import mojo.km.messaging.IEvent;

public class SearchCasefilesByCaseStatusZipCodeWhereClause
{

    public String getSearchCasefilesClause(IEvent anEvent)
    {

    	SearchJuvenileCasefileByZipCodeEvent mev = (SearchJuvenileCasefileByZipCodeEvent) anEvent;
        StringBuffer buf = new StringBuffer(300);

        //buf.append("CASESTATUSCD = '");
        //buf.append(mev.getCaseStatusId());
        //buf.append("' "); //commented for US 156271
        
        //US 156271 changes STARTS (also commented the above three lines of code)
	if (mev.getCaseStatusId() != null && !"".equals(mev.getCaseStatusId()) && !mev.getCaseStatusId().equalsIgnoreCase("CIP")) {
	        buf.append("CASESTATUSCD = '");
	        buf.append(mev.getCaseStatusId()); 
	        buf.append("' ");
	}
	else if(mev.getCaseStatusId() != null && !"".equals(mev.getCaseStatusId()) && mev.getCaseStatusId().equalsIgnoreCase("CIP")){
	    buf.append("CASESTATUSCD in ('CS', 'CA', 'CEPC', 'CP' )");
	}
	//US 156271 changes ENDS
	
       	if (mev.getSupervisionTypeId() != null && !"".equals(mev.getSupervisionTypeId())) {
       		buf.append(" and SPRVSIONTYPECD = '");
            buf.append(mev.getSupervisionTypeId());
            buf.append("' ");
       	}

       	if (mev.getOfficerLastName() != null && !"".equals(mev.getOfficerLastName())) {
       		buf.append("and LASTNAME like upper('");
            buf.append(mev.getOfficerLastName().replace("*", ""));
            buf.append("%') ");
       	}
       	
       	if (mev.getOfficerFirstName() != null && !"".equals(mev.getOfficerFirstName())) {
       		buf.append("and FIRSTNAME like upper('");
            buf.append(mev.getOfficerFirstName().replace("*", ""));
            buf.append("%') ");
       	}
       	
       	if (mev.getOfficerMiddleName() != null && !"".equals(mev.getOfficerMiddleName())) {
       		buf.append("and MIDDLENAME like upper('");
            buf.append(mev.getOfficerMiddleName().replace("*", ""));
            buf.append("%') ");
       	}

       	if (mev.getLocation() != null && !"".equals(mev.getLocation())) {
       		buf.append("and LOCATION_ID = ");
            buf.append(mev.getLocation());
            buf.append(" ");
       	} 	
       	//#32659 changes
    	if (mev.getZipCode() != null && !"".equals(mev.getZipCode())) {
       		buf.append("and ZIPCODE = '");
            buf.append(mev.getZipCode());
            buf.append("' ");
       	}
    	
    	if (mev.getExpectedEndDateFrom() != null && !"".equals(mev.getExpectedEndDateFrom() ) ) {
    		if (mev.getExpectedEndDateTo() != null && !"".equals(mev.getExpectedEndDateTo() )) {
        		buf.append( " and SUPRVSIONENDDATE between '" ); 
				buf.append(convertDate(mev.getExpectedEndDateFrom() ) ); 
				buf.append( "' and '" ); 
				buf.append(convertDate(mev.getExpectedEndDateTo() ) ); 
        	} else {
           		buf.append( " and SUPRVSIONENDDATE = '" ); 
           		buf.append(convertDate(mev.getExpectedEndDateFrom() ) );  
        	}
			buf.append( "'" );
       	}
    	//added for #32659 changes # hot fix changes.
    	if(mev.getCountOfJuv().equalsIgnoreCase("false")){
    		buf.append("order by CASEFILE_ID,FMCREATEDATE");
    	}
       	
         return buf.toString();
    }
 
// quick date conversion coding     
    private String convertDate( String inDate)
    {
    	String outDate = "";
    	String[] flds = inDate.split("/");
    	outDate = flds[2] + "-" + flds[0] + "-" + flds[1] + " 00:00:00.0";
    	return outDate;
    }

}