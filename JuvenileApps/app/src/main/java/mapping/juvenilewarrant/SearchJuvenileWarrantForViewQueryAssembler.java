/*
 * Created on Sep 4, 2007
 *
 */
package mapping.juvenilewarrant;

import org.apache.commons.lang.StringUtils;

import naming.PDJuvenileWarrantConstants;
import messaging.juvenilewarrant.SearchJuvenileWarrantForViewEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author Richard Capestani
 *
 */
public class SearchJuvenileWarrantForViewQueryAssembler
{
	
	private static final String DATE_OF_ISSUE = "DATEOFISSUE";
	private static final String JUV_FIRST_NAME = "JUVFIRSTNAME";
	private static final String JUV_LAST_NAME = "JUVLASTNAME";
	private static final String OFFICER_ID = "OFFICER_ID";
	private static final String WARRANT_ID = "WARRANT_ID";
	private static final String WARRANT_ORIG_USER_ID = "WORIGUSER_ID";
	private static final String WARRANT_STATUS_CD = "WARRANTSTATUSCD";
	private static final String WARRANT_TYPE_CD = "WARRANTTYPECD";

	private static final String SINGLE_QUOTE = "'";
	private static final String AND = " AND ";
	private static final String EQUALS = " = ";
	private static final String LIKE = " LIKE ";
	private static final String BETWEEN = " BETWEEN ";

	private SearchJuvenileWarrantForViewEvent queryEvent;

	private StringBuffer buffer;

	public SearchJuvenileWarrantForViewQueryAssembler()
	{
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IAssembler#assemble()
	 */
	public String getClause(IEvent event)
	{
		this.buffer = new StringBuffer();
		this.queryEvent = (SearchJuvenileWarrantForViewEvent) event;
		this.assembleMainClause();
		return this.buffer.toString();
	}

	/**
	 * @return
	 */
	
	private void assembleMainClause()
	{
	   	if ( StringUtils.isNotEmpty(queryEvent.getWarrantNum()) ) {
			this.assembleWarrantNum();
		}
		if ( StringUtils.isNotEmpty(queryEvent.getLastName()) ) {
	   	   	this.assembleJuvenileName();
		}
		if ( StringUtils.isNotEmpty(queryEvent.getWarrantTypeId()) ) {
			this.assembleWarrantTypeCode();
		}
		if ( StringUtils.isNotEmpty(queryEvent.getOfficerId()) ) {
	   	    this.assembleOfficerId();
	   	}
		if ( StringUtils.isNotEmpty(queryEvent.getOriginatorId()) ) {
			this.assembleWarrantOriginatorUserId();
		}
		
	}

	/**
	 * 
	 *
	 */
	private void assembleWarrantNum()
	{
        buffer.append(WARRANT_ID);
        buffer.append(EQUALS);
        buffer.append(queryEvent.getWarrantNum());
	}
	
	/**
	 * 
	 */
	private void assembleJuvenileName()
	{
		boolean isANDReq = false;
		int beginIndex = 0;
		String endLetter = "";
		String lastName = queryEvent.getLastName();
		buffer.append(JUV_LAST_NAME);
		buffer.append(LIKE);
		buffer.append(SINGLE_QUOTE);
		lastName = lastName.replace('*', '%');
		buffer.append(lastName.toUpperCase());
		beginIndex = lastName.length() - 1;
		endLetter = lastName.substring(beginIndex);
		if( endLetter.equals("'") )
		{
		    buffer.append("'");
		}
		if( !endLetter.equals("%") )
		{
		    buffer.append("%");
		}
		buffer.append(SINGLE_QUOTE);			
		isANDReq = true;
		
		if ( StringUtils.isNotEmpty(queryEvent.getFirstName()) )
		{
			if ( isANDReq )
			{
				buffer.append(AND);
			}
			String firstName = queryEvent.getFirstName();
		    buffer.append(JUV_FIRST_NAME);
			buffer.append(LIKE);
			buffer.append(SINGLE_QUOTE);
			firstName = firstName.replace('*', '%');
			buffer.append(firstName.toUpperCase());
			beginIndex = firstName.length() - 1;
			endLetter = firstName.substring(beginIndex);
			if(!endLetter.equals("%"))
			{
			    buffer.append("%");
			}
			buffer.append(SINGLE_QUOTE);
		}
	}
	
	/**
	 * 
	 */
	private void assembleWarrantTypeCode()
	{
		String warrantTypeCode = queryEvent.getWarrantTypeId();
		buffer.append(WARRANT_TYPE_CD);
		buffer.append(EQUALS);
		buffer.append(SINGLE_QUOTE);
		if(PDJuvenileWarrantConstants.WARRANT_TYPE_ARR.equalsIgnoreCase(warrantTypeCode))
		{
		    buffer.append(PDJuvenileWarrantConstants.WARRANT_TYPE_ARR);
		}else if(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equalsIgnoreCase(warrantTypeCode))
		{
		    buffer.append(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA);
		}else if(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equalsIgnoreCase(warrantTypeCode))
		{
		    buffer.append(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC);
		}else if(PDJuvenileWarrantConstants.WARRANT_TYPE_PCW.equalsIgnoreCase(warrantTypeCode))
		{
		    buffer.append(PDJuvenileWarrantConstants.WARRANT_TYPE_PCW);
		}else if(PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equalsIgnoreCase(warrantTypeCode))
		{
		    buffer.append(PDJuvenileWarrantConstants.WARRANT_TYPE_VOP);
		}
		buffer.append(SINGLE_QUOTE);
		if (StringUtils.isNotEmpty(queryEvent.getWarrantStatusId())) {
			buffer.append(AND);
			buffer.append(WARRANT_STATUS_CD);
			buffer.append(EQUALS);
			buffer.append(SINGLE_QUOTE);
			buffer.append(queryEvent.getWarrantStatusId());
			buffer.append(SINGLE_QUOTE);
		}
		if ( queryEvent.getSearchDate1() != null && queryEvent.getSearchDate2() != null ) {
			String beginDate = DateUtil.dateToString(queryEvent.getSearchDate1(),"yyyy-MM-dd HH:mm:ss.SSS");
	        String endDate = DateUtil.dateToString(queryEvent.getSearchDate2(),"yyyy-MM-dd HH:mm:ss.SSS");
			buffer.append(AND);
			buffer.append(DATE_OF_ISSUE);
			buffer.append(BETWEEN);
			buffer.append(SINGLE_QUOTE);
    		buffer.append(beginDate);
    		buffer.append(SINGLE_QUOTE);
    		buffer.append(AND);
    		buffer.append(SINGLE_QUOTE);
    		buffer.append(endDate);
    		buffer.append(SINGLE_QUOTE);
		}
	}
	
	/**
	 * 
	 *
	 */
	private void assembleOfficerId()
	{
	    String officer_Id = queryEvent.getOfficerId();
	    if (officer_Id != null && officer_Id.length() > 0)
	    {
	        buffer.append(OFFICER_ID);
	        buffer.append(EQUALS);
	        buffer.append(officer_Id);
	    }
	}
	
	/**
	 * 
	 *
	 */
	private void assembleWarrantOriginatorUserId()
	{
		String warrantOriginatorUserId = queryEvent.getOriginatorId();
		if (warrantOriginatorUserId != null)
		{
			buffer.append(WARRANT_ORIG_USER_ID);
			buffer.append(LIKE);
			buffer.append(SINGLE_QUOTE);
			warrantOriginatorUserId = warrantOriginatorUserId.replace('*', '%');
			buffer.append(warrantOriginatorUserId.toUpperCase());
			buffer.append(SINGLE_QUOTE);
		}
	}

}
