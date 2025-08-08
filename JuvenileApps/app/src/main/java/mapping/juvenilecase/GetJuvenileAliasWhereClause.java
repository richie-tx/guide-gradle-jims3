package mapping.juvenilecase;

import messaging.juvenilecase.GetJuvenileAliasEvent;
import mojo.km.messaging.IEvent;

public class GetJuvenileAliasWhereClause
{
    public String getJuvenileAliasClause(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(200);
	if (anEvent instanceof GetJuvenileAliasEvent)
	{
	    GetJuvenileAliasEvent juvenileAliasEvt = (GetJuvenileAliasEvent) anEvent;
	    if (juvenileAliasEvt.getLastName() != null && !"".equals(juvenileAliasEvt.getLastName()))
	    {
		buf.append("LASTNAME LIKE '");
		buf.append(juvenileAliasEvt.getLastName().trim());
		buf.append("%'");
	    }
	    if (juvenileAliasEvt.getMiddleName() != null && !"".equals(juvenileAliasEvt.getMiddleName()))
	    {
		buf.append("AND MIDDLENAME LIKE '");
		buf.append(juvenileAliasEvt.getMiddleName().trim());
		buf.append("%'");
	    }
	    if (juvenileAliasEvt.getFirstName() != null && !"".equals(juvenileAliasEvt.getFirstName()))
	    {
		buf.append("AND FIRSTNAME LIKE '");
		buf.append(juvenileAliasEvt.getFirstName().trim());
		buf.append("%'");
	    }
	   
	    if (juvenileAliasEvt.getRaceId() != null && !"".equals(juvenileAliasEvt.getRaceId()))
	    {
		buf.append("AND RACE = '");
		buf.append(juvenileAliasEvt.getRaceId().trim());
		buf.append("'");
	    }
	    if (juvenileAliasEvt.getSexId() != null && !"".equals(juvenileAliasEvt.getSexId()))
	    {
		buf.append("AND SEX = '");
		buf.append(juvenileAliasEvt.getSexId().trim());
		buf.append("'");
	    }
	    if ( juvenileAliasEvt.getJuvenileType() != null )
	    {
		buf.append("AND NAMETYPE IN ");
		buf.append("('A'");
		buf.append(",'M'");
		buf.append(",'N'");
		buf.append(",'S'");
		buf.append(",'G'");
		buf.append(",'F'");
		buf.append(",'P')");
		//buf.append("'");
	    }
	    
	    
	    if (juvenileAliasEvt.getDateOfBirth() != null && !"".equals(juvenileAliasEvt.getDateOfBirth()))
	    {
		if (juvenileAliasEvt.getLastName() != null && !"".equals(juvenileAliasEvt.getLastName())){
		    buf.append(" AND BIRTHDATE = '");
		    buf.append(juvenileAliasEvt.getDateOfBirth().trim());
		    buf.append("'");		    
		}else{
			buf.append("BIRTHDATE = '");
			buf.append(juvenileAliasEvt.getDateOfBirth().trim());
			buf.append("'"); 
		}
	    }
	    if (juvenileAliasEvt.getJuvenileNum() != null && !"".equals(juvenileAliasEvt.getJuvenileNum()))
	    {
		buf.append("JUVENILENUM = '");
		buf.append(juvenileAliasEvt.getJuvenileNum().trim());
		buf.append("'");
	    }
	   
	}
	return buf.toString();
    }
}
