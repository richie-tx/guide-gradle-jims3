package mapping.juvenile;

import messaging.juvenile.SearchJuvenileProfilesEvent;
import mojo.km.messaging.IEvent;

public class SearchJuvenileProfilesWhereClause
{

    public String searchJuvenileProfilesClause(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(200);
	if (anEvent instanceof SearchJuvenileProfilesEvent)
	{
	    SearchJuvenileProfilesEvent searchProfileEvt = (SearchJuvenileProfilesEvent) anEvent;
	    if (searchProfileEvt.getLastName() != null && !"".equals(searchProfileEvt.getLastName()))
	    {
		    buf.append("JUVENILELNAME LIKE '");
		    buf.append(searchProfileEvt.getLastName().trim());
		    buf.append("%'");
		
	    }
	    if (searchProfileEvt.getMiddleName() != null && !"".equals(searchProfileEvt.getMiddleName()))
	    {
		buf.append("AND JUVENILEMNAME LIKE '");
		buf.append(searchProfileEvt.getMiddleName().trim());
		buf.append("%'");
	    }
	   
	    if (searchProfileEvt.getFirstName() != null && !"".equals(searchProfileEvt.getFirstName()))
	    {
		buf.append("AND JUVENILEFNAME LIKE '");
		buf.append(searchProfileEvt.getFirstName().trim());
		buf.append("%'");
	    }
	    if (searchProfileEvt.getRaceId() != null && !"".equals(searchProfileEvt.getRaceId()))
	    {
		buf.append("AND RACE = '");
		buf.append(searchProfileEvt.getRaceId().trim());
		buf.append("'");
	    }
	    if (searchProfileEvt.getSexId() != null && !"".equals(searchProfileEvt.getSexId()))
	    {
		buf.append("AND SEX = '");
		buf.append(searchProfileEvt.getSexId().trim());
		buf.append("'");
	    }
	    if (searchProfileEvt.getDateOfBirth() != null && !"".equals(searchProfileEvt.getDateOfBirth()))
	    {
		 if (searchProfileEvt.getLastName() != null && !"".equals(searchProfileEvt.getLastName())){
		     
		     buf.append(" AND BIRTHDATE = '");
		     buf.append(searchProfileEvt.getDateOfBirth().trim());
		     buf.append("'");
			
		 } else{
		     buf.append("BIRTHDATE = '");
		     buf.append(searchProfileEvt.getDateOfBirth().trim());
		     buf.append("'");
		 }
		 
	    }
	    if (searchProfileEvt.getJuvenileNum() != null && !"".equals(searchProfileEvt.getJuvenileNum()))
	    {
		buf.append("JUVENILENUM = '");
		buf.append(searchProfileEvt.getJuvenileNum().trim());
		buf.append("'");
	    }
	    if (searchProfileEvt.getStatusId() != null && !"".equals(searchProfileEvt.getStatusId()))
	    {
		buf.append(" AND STATUS = '");
		buf.append(searchProfileEvt.getStatusId().trim());
		buf.append("'");
	    }
	    if (searchProfileEvt.getSsn() != null && !"".equals(searchProfileEvt.getSsn()))
	    {
		buf.append("JUVENILESSN = '");
		buf.append(searchProfileEvt.getSsn().trim());
		buf.append("'");
	    }
	    //add for master status
	    if (searchProfileEvt.getFrom() != null && "SP".equals(searchProfileEvt.getFrom()))
	    {
		buf.append(" AND STATUS <> 'C' AND  STATUS <> 'N'");
	    }
	}
	return buf.toString();
    }
}
