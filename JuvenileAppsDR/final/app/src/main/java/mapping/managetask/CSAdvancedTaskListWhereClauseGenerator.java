/*
 * Created on Sep 29, 2005
 *
 */
package mapping.managetask;


import java.util.List;

import messaging.managetask.GetCSTaskListAdvancedSearchEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author Richard Young
 *  
 */
public class CSAdvancedTaskListWhereClauseGenerator
{
    
    private static final String STAFFPOSITION_ID = "STAFFPOSITION_ID";
    
    private static final String WG_ID = "WORKGROUP_ID";

    private static final String SINGLE_QUOTE = "'";

    private static final String STATUSCD = "STATUSCD";

    private static final String CREATE_DATE = "CREATEDATE";
    
    private static final String DEFENDANT_ID = "DEFENDANT_ID";

    private static final String DUE_DATE = "DUEDATE";

    private static final String BETWEEN = "BETWEEN";

    private static final String COURT_NUM = "COURTCD";
    
    private static final String AND = " AND ";
   
    private static final String EQUALS = "=";
        
    private static final String SPACE = " ";
    
    private GetCSTaskListAdvancedSearchEvent queryEvent;
    
    private static final String FETCH_FIRST_LIMIT = " FETCH FIRST 2000 ROWS ONLY";

    private StringBuffer buffer;

    public CSAdvancedTaskListWhereClauseGenerator()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see pd.pattern.IAssembler#assemble()
     */
    public String getClause(IEvent event)
    {
        this.buffer = new StringBuffer();
        this.queryEvent = (GetCSTaskListAdvancedSearchEvent) event;

        this.assembleMainClause();
      
        return this.buffer.toString();
    }
    
    private void assembleMainClause()
	{
	   	boolean isANDReqd = false;
	   	
	   	if (this.hasStaffPositionId())
		{
	   	   	this.assembleStaffPositionId();
	   	   	isANDReqd = true;

		}
		if (this.hasWorkGroupIds())
		{
			if (isANDReqd)
			{
				buffer.append(AND);
			}
			this.assembleWorkgroupIdsClause();
			isANDReqd = true;
		}
		
		if (this.hasTaskStatusIds())
		{
			if(isANDReqd)
			{
				buffer.append(AND);
			}
			this.assembleTaskStatusIdsClause();
			isANDReqd = true;
		}
		
		
		if (this.hasCourtCd())
		{
			if (isANDReqd)
			{
				buffer.append(AND);
			}
			this.assembleCourtClause();
			isANDReqd = true;
		}
		
		if (this.hasDefendantId())
		{
			if (isANDReqd)
			{
				buffer.append(AND);
			}
			this.assembleDefendantIdClause();
			isANDReqd = true;
		}
		
		if (this.hasCreateDateRange())
		{
			if (isANDReqd)
			{
				buffer.append(AND);
			}
			this.assembleCreateDateClause();
			isANDReqd = true;
		}
		
		if (this.hasDueDateRange())
		{
			if (isANDReqd)
			{
				buffer.append(AND);
			}
			this.assembleDueDateClause();
			isANDReqd = true;
		}
//		addFetchLimit();
	}

    /**
     * 
     */
    private void assembleDueDateClause()
    {

        // Set end date range
        String beginDueDate = DateUtil.dateToString(queryEvent.getBeginDueDate(),"yyyy-MM-dd-HH.mm.ss.SSSSSS");
        String endDueDate = DateUtil.dateToString(queryEvent.getEndDueDate(),"yyyy-MM-dd-HH.mm.ss.SSSSSS");
        if (beginDueDate != null && endDueDate != null)
        {
            
            buffer.append(DUE_DATE);
            buffer.append(SPACE );
            buffer.append(BETWEEN);
            buffer.append(SINGLE_QUOTE);
            buffer.append(beginDueDate);
            buffer.append(SINGLE_QUOTE);
            
            buffer.append(AND);
            buffer.append(SPACE );
            buffer.append(SINGLE_QUOTE);
            buffer.append(endDueDate);
            buffer.append(SINGLE_QUOTE);
            
        }
        
    }

    /**
     * 
     */
    private void assembleCreateDateClause()
    {

//      Set begin date
        String beginCreateDate = DateUtil.dateToString(queryEvent.getBeginCreateDate(),"yyyy-MM-dd-HH.mm.ss.SSSSSS");
        String endCreateDate = DateUtil.dateToString(queryEvent.getEndCreateDate(),"yyyy-MM-dd-HH.mm.ss.SSSSSS");
        if (beginCreateDate != null && endCreateDate != null)
        {
            buffer.append(CREATE_DATE);
            buffer.append(SPACE );
            buffer.append(BETWEEN);
            buffer.append(SPACE );
            buffer.append(SINGLE_QUOTE);
            buffer.append(beginCreateDate);
            buffer.append(SINGLE_QUOTE);
            
            buffer.append(AND);
            buffer.append(SINGLE_QUOTE);
            buffer.append(endCreateDate);
            buffer.append(SINGLE_QUOTE);
       }
        
    }

    /**
     * 
     */
    private void assembleCourtClause()
    {
        // Set court
        String court = queryEvent.getCourtId();
        if (court != null && court.length()> 0)
        {
            buffer.append(COURT_NUM);
            buffer.append(EQUALS);
            buffer.append(SINGLE_QUOTE);
            buffer.append(court);
            buffer.append(SINGLE_QUOTE);
        }
        
    }

    /**
     * 
     */
    private void assembleDefendantIdClause()
    {
        // Set severity
        String defendantId = padSpn( queryEvent.getDefendantId());
        if (defendantId != null && defendantId.length() > 0)
        {
            buffer.append(DEFENDANT_ID);
            buffer.append(EQUALS);
            buffer.append(SINGLE_QUOTE);
            buffer.append(defendantId);
            buffer.append(SINGLE_QUOTE);
        }
        
    }

    /**
     * 
     */
    private void assembleWorkgroupIdsClause()
    {
        // Set severity
        List wgList = queryEvent.getWorkgroupIds();
        buffer.append( WG_ID ) ;
        buffer.append( " IN") ;
        buffer.append("(") ;
        for( int x = 0; x< wgList.size(); x++ )
 		{
			buffer.append( wgList.get(x) );
			if ( x != ( wgList.size() - 1 ) )
			{
				buffer.append( ",");  
			}
		}
		buffer.append(" )") ; 
    }
   
    /**
     * 
     */
    private void assembleTaskStatusIdsClause()
    {
        // Set statusIds
        List statusIdsList = queryEvent.getTaskStatusIds();
        buffer.append(STATUSCD + " IN");
        buffer.append("(") ;
        for( int x = 0; x< statusIdsList.size(); x++ )
 		{
			buffer.append("'");
        	buffer.append( statusIdsList.get(x) );
        	buffer.append("'");
			if ( x != ( statusIdsList.size() - 1 ) )
			{
				buffer.append( ",");  
			}
		}
		buffer.append(" )") ; 
    }
    /**
     * 
     */
    private void assembleStaffPositionId()
    {
//      Set Owner
        String positionId = queryEvent.getStaffPositionId();
        if (positionId != null && positionId.length() > 0)
        {
            buffer.append(STAFFPOSITION_ID);
            buffer.append(EQUALS);
            buffer.append(SPACE);
            buffer.append(positionId);
        }
        
    }

    
//    private void assembleUserDepartmentDesc()
//    {
//        // Set departmentDesc
//        String deptDesc = queryEvent.getUserDepartmentDesc();
//        if (deptDesc != null && deptDesc.length()> 0)
//        {
//            buffer.append(DEPT_DESC);
//            buffer.append(EQUALS);
//            buffer.append(SINGLE_QUOTE);
//            buffer.append(deptDesc);
//            buffer.append(SINGLE_QUOTE);
//        }
//        
//    }
    
    /**
     * 
     */
    private void addFetchLimit()
    {
        // Limit records fetched    
        buffer.append(FETCH_FIRST_LIMIT);
        
    }
    
    
    /**
     * 
     * @return
     */   
    private boolean hasDefendantId()
	{
		return queryEvent.getDefendantId() != null && !queryEvent.getDefendantId().equals("");
	}
    /**
     * 
     * @return
     */
    private boolean hasTaskStatusIds()
	{
		return queryEvent.getTaskStatusIds().size() > 0;
	}
       
    /**
     * @return
     */
    private boolean hasCourtCd()
    {
        
        return queryEvent.getCourtId()!= null && !queryEvent.getCourtId().equals("");
    }
    
    /**
     * @return
     */
    private boolean hasCreateDateRange()
    {
        return queryEvent.getBeginCreateDate()!=null && queryEvent.getEndCreateDate() !=null ;
    }
      
    /**
     * @return
     */
    private boolean hasDueDateRange()
    {
        return queryEvent.getBeginDueDate()!=null && queryEvent.getEndDueDate() !=null ;
    }
    
//    private boolean hasUserDepartmentDesc()
//    {
//        return queryEvent.getUserDepartmentDesc()!=null && !queryEvent.getUserDepartmentDesc().equals("");
//    }
    /**
     * @return
     */
    private boolean hasWorkGroupIds()
	{
		return queryEvent.getWorkgroupIds().size() > 0;
	}
    
    private boolean hasStaffPositionId()
    {
        return queryEvent.getStaffPositionId()!=null && !queryEvent.getStaffPositionId().equals("");
    }
    
    private String padSpn(String spn){
    	
        if (spn != null && spn.length() < 8) {
            StringBuffer sb = new StringBuffer(spn);
            for (int i = 0; i < 8 - spn.length(); i++) {
                sb.insert(0, "0");
            }
            spn = sb.toString();
        }
        return spn;
    }    

}
