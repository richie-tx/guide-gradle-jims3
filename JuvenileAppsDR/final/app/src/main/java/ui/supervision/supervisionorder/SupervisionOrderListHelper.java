/*
 * Created on Feb 9, 2006
 *
 */
package ui.supervision.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import naming.PDConstants;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import messaging.supervisionorder.reply.MagistrateResponseEvent;
import messaging.supervisionorder.reply.PrintTemplatesResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;

import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;

/**
 * @author dgibler
 *
 */
public final class SupervisionOrderListHelper
{
	/**
	 * 
	 */
	private SupervisionOrderListHelper()
	{
		super();
	}

	/**
	 * @param judges
	 * @param courtId
	 * @return
	 */
	public static String getJudge(Collection judges, String courtId)
	{
		String judgeName = null;
		if (judges != null)
		{
			Iterator iter = judges.iterator();
			JudgeResponseEvent jre = null;

			while (iter.hasNext())
			{
				jre = (JudgeResponseEvent) iter.next();
				if (jre.getCourtId().equals(courtId))
				{
					judgeName = jre.getFormattedName();
				}
			}
		}
		return judgeName;
	}
	/**
	 * @param offenses
	 * @param offenseId
	 * @return
	 */
	public static String getOffense(Collection offenses, String offenseId)
	{
		String offense = null;
		if (offenses != null)
		{
			Iterator iter = offenses.iterator();
			OffenseCodeResponseEvent ocre = null;

			while (iter.hasNext())
			{
				ocre = (OffenseCodeResponseEvent) iter.next();
				if (ocre.getOffenseCodeId().equals(offenseId))
				{
					offense = ocre.getDescription();
				}
			}
		}
		return offense;
	}
	/**
	 * @param magistrates
	 * @param magistrateId
	 * @return
	 */
	public static String getMagistrate(Collection magistrates, String magistrateId)
	{
		String magistrateName = null;
		if (magistrates != null)
		{
			Iterator iter = magistrates.iterator();
			MagistrateResponseEvent mre = null;

			while (iter.hasNext())
			{
				mre = (MagistrateResponseEvent) iter.next();
				if (mre.getMagistrateId().equals(magistrateId))
				{
					magistrateName = mre.getFormattedName();
				}
			}
		}
		return magistrateName;
	}
	/**
	 * @param staff
	 * @param supervisionStaffId
	 * @return
	 */
	public static String getSupervisionStaff(Collection staff, String supervisionStaffId)
	{
		String theName = null;
		if (staff != null)
		{
			Iterator iter = staff.iterator();
			SupervisionStaffResponseEvent mre = null;

			while (iter.hasNext())
			{
				mre = (SupervisionStaffResponseEvent) iter.next();
				if (mre.getSupervisionStaffId().equals(supervisionStaffId))
				{
					theName = mre.getFormattedName();
				}
			}
		}
		return theName;
	}
	/**
	 * Builds JudgeResponseEvents from CourtResponseEvents and sorts by name
	 * @return
	 */
	public static Collection getJudgeList()
	{
		Collection courts = UISupervisionOptionHelper.fetchCSCDFilteredCourts();
		JudgeResponseEvent judge = null;
		StringBuffer judgeName = null;
		Collection judges = new ArrayList();
		if (courts != null)
		{
			Iterator iter = courts.iterator();
			while (iter.hasNext())
			{
				CourtResponseEvent cre = (CourtResponseEvent) iter.next();
				judge = new JudgeResponseEvent();
				judge.setCourtId(cre.getCourtId());
				if (cre.getJudgeLastName() == null || cre.getJudgeLastName().equals(""))
				{
					judgeName = new StringBuffer("Judge name not on file for court ");
					judgeName.append(cre.getCourtNumber());
					judge.setLastName(judgeName.toString());
				}
				else
				{
					judge.setFirstName(cre.getJudgeFirstName());
					judge.setLastName(cre.getJudgeLastName());
				}
				judge.setTopic(judge.getCourtId());
				judges.add(judge);
			}
		}
		if (judges.size() > 1)
		{
			Collections.sort((List) judges);
		}

		return judges;
	}
	/**
	 * @param codes
	 * @param codeId
	 * @return
	 */
	public static String getCodeDescription(Collection codes, String codeId)
	{
		Iterator iter = codes.iterator();
		CodeResponseEvent cre = null;
		String description = null;
		while (iter.hasNext())
		{
			cre = (CodeResponseEvent) iter.next();
			if (cre.getCode().equals(codeId))
			{
				description = cre.getDescription();
				break;
			}
		}
		return description;
	}
	
	/**
	 * @param codes
	 * @param codeId
	 * @return
	 */
	public static String getOrderTitleName(Collection orderTitleList, String orderTitleId, String versionTypeId)
	{
		String description = null;
		if ("M".equals(versionTypeId)){
			description = "MODIFIED";
		} else {
			Iterator iter = orderTitleList.iterator();
			PrintTemplatesResponseEvent cre = null;
	
			while (iter.hasNext())
			{
				cre = (PrintTemplatesResponseEvent) iter.next();
				if (cre.getPrintTemplateId().equals(orderTitleId))
				{
					description = cre.getOrderTitle();
					break;
				}
			}
		}	
		return description;
	}	

	/**
	 * @param codes
	 * @param codeId
	 * @return
	 */
	public static String getOrderTitleName(Collection orderTitleList, String orderTitleId)
	{
		String description = null;
		Iterator iter = orderTitleList.iterator();
		PrintTemplatesResponseEvent cre = null;
		while (iter.hasNext())
		{
			cre = (PrintTemplatesResponseEvent) iter.next();
			if (cre.getPrintTemplateId().equals(orderTitleId))
			{
				description = cre.getOrderTitle();
				break;
			}
		}
		return description;
	}	
	/**
	 * @param codes
	 * @param codeId
	 * @return
	 */
	public static void setOrderTitleNames(Collection orderDetailResponseEvents, Collection orderTitleList){
		// set order titles
		for(Iterator iter = orderDetailResponseEvents.iterator(); iter.hasNext(); ){
			SupervisionOrderDetailResponseEvent orderEvent = (SupervisionOrderDetailResponseEvent)iter.next();
			orderEvent.setOrderTitle(getOrderTitleName(orderTitleList, orderEvent.getOrderTitleId()));
		}
	}
    private static final String ALL = "ALL";
	public static Collection groupOrderTitleList(Collection orderTitles, String courtCategory, String courtNum, String versionType)
    {
        if(versionType == null || versionType.equals(PDConstants.BLANK)){
        	versionType="Original";
        }
        if (courtNum == null || courtNum.equals(PDConstants.BLANK)){
        	courtNum = ALL;
        }
        Iterator iter = orderTitles.iterator();
        List orderTitlesCC = new ArrayList();
        while (iter.hasNext())
        {
            PrintTemplatesResponseEvent event = (PrintTemplatesResponseEvent) iter.next();
            if (event.getCourtCategory().equals(courtCategory)) {
            	if (event.getCourtId().equals(ALL) || event.getCourtId().equals(courtNum)){
            		orderTitlesCC.add(event);
            	}
            }
        }
        Collections.sort(orderTitlesCC);
        return orderTitlesCC;

    }

	public static Collection getJuvCourts() {
	       Collection courtBeans = new ArrayList();
	        SortedMap map = new TreeMap();
	        Collection courts = UISupervisionOptionHelper.fetchCourts();
	        CodeResponseEvent codeRE = null;
	        if (courts != null) {
	            Iterator it = courts.iterator();
				
	            while (it.hasNext()) {
	                CourtResponseEvent cre = (CourtResponseEvent) it.next();

					if ("JUV".equals(cre.getCourtCategory())){
						//Show all JUV courts so that app code will not have to change
						//if a new court is added and description is worded differently.
						//if (cre.getDescription().indexOf("JUDICIAL DISTRICT") > 0 ){				
							codeRE = new CodeResponseEvent();
							codeRE.setCode(cre.getCourtNumber());
							codeRE.setCodeId(cre.getCourtId());
							codeRE.setDescription(cre.getDescription());
							courtBeans.add(codeRE);
							map.put(codeRE.getDescription(), codeRE);
						//}
					}

	            }
	        }
			if (courtBeans.size() > 0){
				courtBeans = new ArrayList(map.values());
			}
	        return courtBeans;
    }
}
