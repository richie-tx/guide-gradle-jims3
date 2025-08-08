/*
 */
package mapping.juvenilecase;

import java.util.List;

import messaging.juvenilecase.SearchCasefileByJuvenileAndOfficerEvent;
import mojo.km.messaging.IEvent;

public class SearchCasefilesByJuvenileAndOfficerWhereClause
{

    public String getSearchCasefilesClause(IEvent anEvent)
    {
        SearchCasefileByJuvenileAndOfficerEvent mev = (SearchCasefileByJuvenileAndOfficerEvent) anEvent;
        StringBuffer buf = new StringBuffer(200);
        boolean isFlag = false;
        String beginWhereClause = " ";                

        List juvNumList = mev.getJuvNumList();
        if (juvNumList != null && juvNumList.size() > 0)
        {
            buf.append("JUVENILE_ID IN ( ");
            
            int len = juvNumList.size();
            
            for(int i=0;i<len-1;i++)
            {                
                buf.append(" '");
                buf.append((String) juvNumList.get(i));
                buf.append("', ");
            }
            buf.append(" '");
            buf.append((String) juvNumList.get(len-1));
            buf.append("' ");
            
            buf.append(") ");
            beginWhereClause = " AND ";
            isFlag = true;
        }

        if (mev.isCaseLoadManager())
        {
            if (mev.getOfficerLastName() != null && !"".equals(mev.getOfficerLastName()))
            {
                buf.append(beginWhereClause);
                buf.append("MGR_USERID IN (SELECT USERID FROM JIMS2.OFFICER WHERE LASTNAME LIKE UPPER('");
                buf.append(mev.getOfficerLastName().replace("'", "''") + "%') AND COALESCE(MIDDLENAME,'') LIKE UPPER('");
                buf.append(mev.getOfficerMiddleName().replace("'", "''") + "%') AND COALESCE(FIRSTNAME,'') LIKE UPPER('");
                buf.append(mev.getOfficerFirstName().replace("'", "''") + "%') AND OFFICER_SUBTYPE LIKE 'CLM' AND OFFICER_TYPE LIKE 'J')");
                isFlag = false;
            }

        }
        else
        {
            if (mev.getOfficerLastName() != null && !"".equals(mev.getOfficerLastName()))
            {
                buf.append(beginWhereClause);
                buf.append("OFFICERLNAME LIKE UPPER('" + mev.getOfficerLastName().replace("'", "''") + "%') AND ");
                buf.append("COALESCE(OFFICERMNAME,'') LIKE UPPER('" + mev.getOfficerMiddleName().replace("'", "''") + "%') AND ");
                buf.append("COALESCE(OFFICERFNAME,'') LIKE UPPER('" + mev.getOfficerFirstName().replace("'", "''")
                        + "%') AND OFFICER_TYPE LIKE 'J'");
                buf.append("");
                isFlag = false;
            }

            if (mev.getOfficerUserId() != null && !"".equals(mev.getOfficerUserId()))
            {
                buf.append(beginWhereClause);
                buf.append("USERID LIKE UPPER('" + mev.getOfficerUserId() + "')");
                isFlag = false;
            }
        }
//Bug fix for #22129:SQL doesn't need OPTIMIZE. 
//        if (isFlag == true)
//        {
//            buf.append(" OPTIMIZE FOR 1 ROW ");
//        }
        return buf.toString();
    }

}
