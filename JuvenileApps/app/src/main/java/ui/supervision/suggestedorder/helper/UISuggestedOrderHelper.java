/*
 * Created on Oct 10, 2005
 *
 */
package ui.supervision.suggestedorder.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

import naming.PDCodeTableConstants;
import naming.SuggestedOrderControllerServiceNames;
import naming.SupervisionConstants;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.suggestedorder.GetSuggestedOrderDataEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderCourtResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public final class UISuggestedOrderHelper
{
	/**
	 * 
	 */
	private UISuggestedOrderHelper()
	{
		super();
	}

	/**
	 * Adds conditionId as topic
	 * @param soConditionResponseEvents
	 * @return
	 */
	public static Collection addTopicsToConditions(Collection soConditionResponseEvents)
	{
		if (soConditionResponseEvents != null)
		{
			SuggestedOrderConditionResponseEvent socre = null;
			Iterator iter = soConditionResponseEvents.iterator();
			while (iter.hasNext())
			{
				socre = (SuggestedOrderConditionResponseEvent) iter.next();
				socre.setTopic(socre.getConditionId());
			}
		}
		return soConditionResponseEvents;
	}
	/**
	 * Adds offense code as topic
	 * @param offenseCodeResponseEvents
	 * @return
	 */
	public static Collection addTopicsToOffenses(Collection offenseCodeResponseEvents)
	{
		if (offenseCodeResponseEvents != null)
		{
			OffenseCodeResponseEvent ocre = null;
			Iterator iter = offenseCodeResponseEvents.iterator();
			while (iter.hasNext())
			{
				ocre = (OffenseCodeResponseEvent) iter.next();
				ocre.setTopic(ocre.getOffenseCodeId());
			}
		}

		return offenseCodeResponseEvents;
	}
	/**
	 * Builds a collection of court numbers with a postpended comma.
	 * @param courtBeans
	 * @return
	 */
	public static Collection buildCourtList(Collection courtBeans)
	{
		Collection courtsList = new ArrayList();
		if (courtBeans != null)
		{
			CourtBean courtBean = null;
			Collection courts = null;
			Iterator courtIter = null;
			CourtResponseEvent cre = null;
			Iterator beanIter = courtBeans.iterator();

			while (beanIter.hasNext())
			{
				courtBean = (CourtBean) beanIter.next();
				courts = courtBean.getCourts();
				if (courts != null && courts.size() > 0)
				{
					courtIter = courts.iterator();
					while (courtIter.hasNext())
					{
						cre = (CourtResponseEvent) courtIter.next();
						courtsList.add(cre.getCourtId());
					}
				}
			}
		}
		return courtsList;
	}
	/**
	 * Creates a new collection from an existing collection.
	 * @param coll1
	 * @return
	 */
	public static Collection buildNewCollection(Collection coll1)
	{

		Collection coll2 = new ArrayList();
		Object obj = null;
		Iterator iter = coll1.iterator();
		while (iter.hasNext())
		{
			obj = (Object) iter.next();
			coll2.add(obj);
		}
		return coll2;

	}

	/**
	 * Builds map of response events setting key as topic.
	 * @param responseEvents
	 * @return
	 */
	public static Map buildResponseEventMap(Collection responseEvents)
	{
		Map map = new HashMap();

		if (responseEvents != null)
		{
			ResponseEvent re = null;
			Iterator iter = responseEvents.iterator();
			while (iter.hasNext())
			{
				re = (ResponseEvent) iter.next();
				map.put(re.getTopic(), re);
			}
		}
		return map;
	}

	/**
	 * Creates CourtBeans from SuggestedOrderCourtResponseEvents.
	 * @param allCourts
	 * @param soCourts
	 * @return
	 */
	public static Collection createCourtBeans(Collection allCourts, Collection soCourts) throws GeneralFeedbackMessageException
	{

		Collection allCourtBeans = new ArrayList();
		if (allCourts == null || allCourts.size() == 0)
		{
			Collection courtBeans = getCourtBeans();
			allCourtBeans = courtBeans;
		}
		else
		{
			allCourtBeans = allCourts;
		}

		Map allCourtsMap = UISuggestedOrderHelper.createCourtMap(allCourtBeans);
		Map selectedCourtsMap = new HashMap();

		SuggestedOrderCourtResponseEvent socre = null;
		CourtResponseEvent cre = null;
		CourtResponseEvent newCre = null;
		CourtBean selectedCourtBean = null;
		CourtBean newCourtBean = null;
		Collection cres = null;

		Iterator iterator = soCourts.iterator();
		while (iterator.hasNext())
		{
			socre = (SuggestedOrderCourtResponseEvent) iterator.next();
			cre = (CourtResponseEvent) allCourtsMap.get(socre.getCourtId());
			if(cre==null){
				throw new GeneralFeedbackMessageException("Court " + socre.getCourtId()+ " on suggested order cannot be found. Call System Admin to correct this problem");
			}
			newCre = new CourtResponseEvent();
			newCre.setCourtCategory(cre.getCourtCategory());
			newCre.setCourtCategoryDesc(cre.getCourtCategoryDesc());
			newCre.setCourtNumber(cre.getCourtNumber());
			newCre.setDescription(cre.getDescription());
			newCre.setExceptionCourt(cre.isExceptionCourt());
			newCre.setJudgeFirstName(cre.getJudgeFirstName());
			newCre.setJudgeLastName(cre.getJudgeLastName());
			newCre.setCourtId(cre.getCourtId());

			selectedCourtBean = (CourtBean) selectedCourtsMap.get(newCre.getCourtCategory());

			if (selectedCourtBean != null)
			{
				selectedCourtBean.insertCourt(newCre);
			}
			else
			{
				newCourtBean = new CourtBean();
				newCourtBean.setCategory(newCre.getCourtCategory());
				newCourtBean.setCategoryDesc(newCre.getCourtCategoryDesc());
				cres = new ArrayList();
				cres.add(newCre);
				newCourtBean.setCourts(cres);

				selectedCourtsMap.put(newCourtBean.getCategory(), newCourtBean);
			}
		}
		return selectedCourtsMap.values();
	}
	
	public static Collection getCourtBeans() {
        Map courtBeanMap = new HashMap();
        Collection courtBeans = new ArrayList();
        Collection courts = UISupervisionOptionHelper.fetchCSCDFilteredCourts();
        // If you want to sort alphabetically by description add that either
        // here or
        // change the CourtResponseEvent compare method.
        if (courts != null) {
            Iterator it = courts.iterator();
            CourtBean courtBean = null;
            while (it.hasNext()) {
                CourtResponseEvent cre = (CourtResponseEvent) it.next();
                // get the court category first
                String category = cre.getCourtCategory();
                String agencyId = SecurityUIHelper.getUserAgencyId();
                
                courtBean = (CourtBean) courtBeanMap.get(category);
                if (courtBean == null) {
                    courtBean = new CourtBean();
                    courtBean.setCategory(category);
                    courtBean.setCategoryDesc(cre.getCourtCategoryDesc());
                    
                    if ((agencyId.equals(PDCodeTableConstants.CSCD_AGENCY)) &&
                    		( (courtBean.getCategory().equals("CC")) ||
                    		  (courtBean.getCategory().equals("CR")) ||	
                    		  (courtBean.getCategory().equals("OC"))
                    		) ) {
                    	courtBeans.add(courtBean);
                        courtBeanMap.put(category, courtBean);
                    } else if (!agencyId.equals(PDCodeTableConstants.CSCD_AGENCY)) {
                    	courtBeans.add(courtBean);
                    	courtBeanMap.put(category, courtBean);
                    }
                
                }

                if (!cre.getCode().equals("HCT")) {
                	courtBean.insertCourt(cre);
                }
                
            }
        }
        return courtBeans;
    }

	/**
	 * Filters courts based on offense.
	 * @param offenseType
	 * @param courtBeans
	 * @return
	 */
	public static Collection filterCourtsByOffenses(String filterByOffenseType, Collection courtBeans)
	{
		Collection filteredCourtBeans = new ArrayList();
		CourtBean courtBean = null;
		CourtBean filteredCourtBean = null;
		String categoryOffenseType = null;
		Collection courts = null;
		Collection filteredCourts = null;
		CourtResponseEvent court = null;
		String courtOffenseType = null;
		String courtNum = null;
		Iterator courtsIterator = null;

		Iterator iter = courtBeans.iterator();
		while (iter.hasNext())
		{
			courtBean = (CourtBean) iter.next();
			categoryOffenseType = getCourtCategoryOffenseType(courtBean.getCategory());
			if (categoryOffenseType.equals(filterByOffenseType))
			{
				filteredCourtBeans.add(courtBean);
			}
			else
				if (filterByOffenseType.equals(SupervisionConstants.BOTH)
					&& (categoryOffenseType.equals(SupervisionConstants.FELONY)
						|| categoryOffenseType.equals(SupervisionConstants.MISDEMEANOR)))
				{
					filteredCourtBeans.add(courtBean);
				}
				else
					if (courtBean.getCategory().equals(SupervisionConstants.OUT_OF_COUNTY_COURT))
					{
						courts = courtBean.getCourts();
						filteredCourts = new ArrayList();
						courtsIterator = courts.iterator();
						while (courtsIterator.hasNext())
						{
							court = (CourtResponseEvent) courtsIterator.next();
							courtNum = court.getCourtNumber();
							courtOffenseType =
								courtOffenseType = courtNum.substring(courtNum.length() - 1).toUpperCase();
							if (courtOffenseType.equals(filterByOffenseType) || courtNum.equals("CSR"))
							{
								filteredCourts.add(court);
							}
						}
						if (filteredCourts.size() > 0)
						{
							filteredCourtBean = new CourtBean();
							filteredCourtBean.setCategory(courtBean.getCategory());
							filteredCourtBean.setCategoryDesc(courtBean.getCategoryDesc());
							filteredCourtBean.setIsSelected(courtBean.getIsSelected());
							filteredCourtBean.setCourts(filteredCourts);
							filteredCourtBeans.add(filteredCourtBean);
						}

					}
					else
					{
						courtOffenseType = UISuggestedOrderHelper.getCourtCategoryOffenseType(courtBean.getCategory());
						if ((filterByOffenseType.equals(SupervisionConstants.MISDEMEANOR)
							|| filterByOffenseType.equals(SupervisionConstants.FELONY))
							&& courtOffenseType.equals(SupervisionConstants.BOTH))
						{
							filteredCourtBeans.add(courtBean);
						}
					}
		}
		return filteredCourtBeans;
	}
	/**
	 * Determines whether a court handles felony or misdemeanor offenses based on courtCategory
	 * @param courtCategory
	 * @return
	 */
	public static String getCourtCategoryOffenseType(String courtCategory)
	{

		String courtCategoryOffenseType = "";
		if (courtCategory != null)
		{
			if (courtCategory.equalsIgnoreCase(SupervisionConstants.COUNTY_CRIMINAL_COURT))
			{
				courtCategoryOffenseType = SupervisionConstants.MISDEMEANOR;
			}
			else
				if (courtCategory.equalsIgnoreCase(SupervisionConstants.CRIMINAL_DISTRICT_COURT))
				{
					courtCategoryOffenseType = SupervisionConstants.FELONY;
				}
				else
					if (courtCategory.equalsIgnoreCase(SupervisionConstants.FAMILY_COURT)
						|| courtCategory.equals(SupervisionConstants.CIVIL_DISTRICT_COURT))
					{
						courtCategoryOffenseType = "";
					}
					else
						if (courtCategory.equalsIgnoreCase(SupervisionConstants.JUSTICE_OF_PEACE_COURT))
						{
							courtCategoryOffenseType = SupervisionConstants.MISDEMEANOR;
						}
						else
							if (courtCategory.equalsIgnoreCase(SupervisionConstants.JUVENILE_COURT))
							{
								courtCategoryOffenseType = SupervisionConstants.BOTH;
							}
							else
								if (courtCategory.equalsIgnoreCase(SupervisionConstants.OUT_OF_COUNTY_COURT))
								{
									courtCategoryOffenseType = courtCategoryOffenseType = SupervisionConstants.BOTH;
								}
		}
		return courtCategoryOffenseType;
	}
	/**
	 * Determines whether a court handles felony or misdemeanor offenses based on courtCategory and court number (for out-of-county cases only);
	 * @param courtNum
	 * @param courtCategory
	 * @return
	 */
	public static String getCourtOffenseType(String courtNum, String courtCategory)
	{

		String courtOffenseType = "";
		if (courtCategory != null && !courtCategory.equals(""))
		{
			if (courtCategory.equalsIgnoreCase(SupervisionConstants.COUNTY_CRIMINAL_COURT))
			{
				courtOffenseType = SupervisionConstants.MISDEMEANOR;
			}
			else
				if (courtCategory.equalsIgnoreCase(SupervisionConstants.CRIMINAL_DISTRICT_COURT))
				{
					courtOffenseType = SupervisionConstants.FELONY;
				}
				else
					if (courtCategory.equalsIgnoreCase(SupervisionConstants.FAMILY_COURT)
						|| courtCategory.equals(SupervisionConstants.CIVIL_DISTRICT_COURT))
					{
						courtOffenseType = "";
					}
					else
						if (courtCategory.equalsIgnoreCase(SupervisionConstants.JUSTICE_OF_PEACE_COURT))
						{
							courtOffenseType = SupervisionConstants.MISDEMEANOR;
						}
						else
							if (courtCategory.equalsIgnoreCase(SupervisionConstants.JUVENILE_COURT))
							{
								courtOffenseType = SupervisionConstants.BOTH;
							}
							else
								if (courtCategory.equalsIgnoreCase(SupervisionConstants.OUT_OF_COUNTY_COURT)
									&& courtNum != null
									&& !courtNum.equals(""))
								{
									courtOffenseType = courtNum.substring(courtNum.length() - 1).toUpperCase();
								}
		}
		return courtOffenseType;
	}

	/**
	 * Retreives offenses matching event criteria.
	 * @param event
	 * @return
	 */
	public static Collection retrieveOffenses(IEvent requestEvent)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection offenseCodes = MessageUtil.compositeToCollection(compositeResponse, OffenseCodeResponseEvent.class);
		return offenseCodes;
	}
	/**
	 * @param form
	 * @param aRequest
	 * @return
	 */
	public static Collection retrieveSelectedCourtsFromRequest(SuggestedOrderForm form, HttpServletRequest aRequest)
	{
		Map courtMap = UISuggestedOrderHelper.createCourtMap(form.getCourts());
		Collection selectedCourts = new ArrayList();
		Collection courtBeans = form.getCourts();
		String[] selCourts = null;
		CourtBean selCourtBean = null;
		CourtResponseEvent cre = null;
		if (courtBeans != null)
		{
			CourtBean courtBean = null;
			Iterator it = courtBeans.iterator();
			while (it.hasNext())
			{
				courtBean = (CourtBean) it.next();
				selCourts = aRequest.getParameterValues(courtBean.getCategory());
				if (selCourts != null)
				{
					selCourtBean = new CourtBean();
					selCourtBean.setCategory(courtBean.getCategory());
					selCourtBean.setCategoryDesc(courtBean.getCategoryDesc());
					for (int i = 0; i < selCourts.length; i++)
					{
						cre = (CourtResponseEvent) courtMap.get(selCourts[i]);
						selCourtBean.insertCourt(cre);
					}
					selectedCourts.add(selCourtBean);
				}
			}
		}
		return selectedCourts;
	}
	/**
	 * @param courts
	 * @return
	 */
	public static Map createCourtMap(Collection courts)
	{
		Map map = new HashMap();
		Iterator it = courts.iterator();
		while (it.hasNext())
		{
			CourtBean cb = (CourtBean) it.next();
			Iterator cres = cb.getCourts().iterator();
			while (cres.hasNext())
			{
				CourtResponseEvent cre = (CourtResponseEvent) cres.next();
				map.put(cre.getCourtId(), cre);
			}
		}
		return map;
	}
	/**
	 * @param cre
	 * @return
	 */
	public static String prependWithZeros(String theField, int newFieldLength)
	{

		StringBuffer sb = new StringBuffer(theField);
		if (theField.length() < newFieldLength)
		{
			for (int i = 0; i < newFieldLength - theField.length(); i++)
			{
				sb.insert(i, "0");
			}
		}
		return sb.toString();
	}
	/**
	 * @param collection
	 * @return
	 */
	public static void createConditionSequence(Collection conditions)
	{
		Collection newConditions = new ArrayList();
		int counter = 1;
		Iterator iter = conditions.iterator();
		while (iter.hasNext())
		{
			SuggestedOrderConditionResponseEvent socre = (SuggestedOrderConditionResponseEvent) iter.next();
			if (socre.getSeqNum() == null || socre.getSeqNum().equals(""))
			{ // add any new object into a collection to assign sequence number in the end
				newConditions.add(socre);
			}
			else
			{
				socre.setSeqNum(String.valueOf(counter++));
			}
		} // traverse through the new collection
		for (Iterator iterator = newConditions.iterator(); iterator.hasNext();)
		{
			SuggestedOrderConditionResponseEvent socre = (SuggestedOrderConditionResponseEvent) iterator.next();
			socre.setSeqNum(String.valueOf(counter++));
		}
	}

	/**
	 * @param suggestedOrderId
	 * @return
	 */
	public static CompositeResponse fetchSuggestedOrder(String suggestedOrderId)
	{
		GetSuggestedOrderDataEvent requestEvent =
			(GetSuggestedOrderDataEvent) EventFactory.getInstance(
				SuggestedOrderControllerServiceNames.GETSUGGESTEDORDERDATA);
		requestEvent.setSuggestedOrderId(suggestedOrderId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		return compositeResponse;
	}
	
	public static Collection setResequenceCondition(Collection aOriginalList, String aSequence, boolean aIsPreview) {

        if (aOriginalList != null) {
            if (aSequence == null || aSequence.trim().equals("")) {
                return aOriginalList;
            }
            ArrayList newPreviewList = new ArrayList(aOriginalList.size());
            Iterator originalListIter = aOriginalList.iterator();
            HashMap originalMap = new HashMap();
            while (originalListIter.hasNext()) {
                SuggestedOrderConditionResponseEvent response = (SuggestedOrderConditionResponseEvent) originalListIter.next();
                String newStr = (response.getSeqNum());
                originalMap.put(newStr, response);
                newPreviewList.add(new SuggestedOrderConditionResponseEvent());
                newStr = null;
                response = null;
            }
            StringTokenizer myTokenizer = new StringTokenizer(aSequence, ",");
            originalListIter = null;
            originalListIter = aOriginalList.iterator();
            int counter = 0;
            while (originalListIter.hasNext()) {
                SuggestedOrderConditionResponseEvent response = (SuggestedOrderConditionResponseEvent) originalListIter.next();
                SuggestedOrderConditionResponseEvent newResponse = new SuggestedOrderConditionResponseEvent();
                String myNewSequenceNum = myTokenizer.nextToken();
                int mySequenceNum = (Integer.parseInt(myNewSequenceNum));
                if (aIsPreview) {
                    SuggestedOrderConditionResponseEvent mappedResponse = (SuggestedOrderConditionResponseEvent) originalMap.get(Integer.toString(mySequenceNum));
                    if(mappedResponse!=null){
newResponse.setSeqNum(Integer.toString(counter + 1));
                    
                    newResponse.setStandardId(mappedResponse.getStandardId());
                    newResponse.setConditionLiteralPreview(mappedResponse.getConditionLiteralPreview());
                    newPreviewList.set(counter, newResponse);
                    }

                } else {
                    SuggestedOrderConditionResponseEvent mappedResponse = (SuggestedOrderConditionResponseEvent) originalMap.get(Integer.toString(mySequenceNum));
                    if(mappedResponse!=null){
                    mappedResponse.setSeqNum(Integer.toString(counter + 1));
                    newPreviewList.set(counter, mappedResponse);
                    }
                }
                counter++;
            }
            return newPreviewList;
        } else
            return null;

    }

}
