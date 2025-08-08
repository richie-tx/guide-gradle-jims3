/*
 * Created on Sept 18, 2013
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import ui.common.SimpleCodeTableHelper;

public final class UIJuvenileTraitsHelper
{

	/**
     *  
     */
	private UIJuvenileTraitsHelper()
	{
		super();
	}

	/*
	 * @param juvenileNum
	 * @return
	 */
	public static boolean findActiveCasefile( String juvenileNum )
	{
		boolean result = false;
		if (juvenileNum != null && !"".equals(juvenileNum))
		{
			SearchJuvenileCasefilesEvent searchEvent = 
				(SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

            searchEvent.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);
            searchEvent.setJuvenileNum(juvenileNum);

            List casefiles = MessageUtil.postRequestListFilter(searchEvent, JuvenileCasefileSearchResponseEvent.class);
            
            Iterator iter = casefiles.iterator();
    		while (iter.hasNext())
    		{
    			JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) iter.next();
    			if (!casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_CLOSED_DESCRIPTION) &&
    				!casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_CLOSED_APPROVED_DESCRIPTION))
    			{
    				 result = true;
    				 break;
    			}
    		}	
		}

		return result;
	}
	
	public static List loadUpdateTraitStatuses()
	{
		List temp1 = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.FAMILY_TRAIT_STATUS);
		List temp2 = new ArrayList();
    	for (int y=0; y<temp1.size(); y++)
    	{
    		CodeResponseEvent cre = (CodeResponseEvent) temp1.get(y);
    		if ("FO".equals(cre.getCode() ) || "LC".equals(cre.getCode()) ) {
    			temp2.add(cre);
    		}
    	}
		temp1 = null;
		return temp2;
	}

}