package ui.juvenilecase.riskanalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import naming.UIConstants;

import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;

public class RiskAnalysisUIHelper 
{

	public RiskAnalysisUIHelper() {}
	
	/**
	 * @param categoriesList
	 * @return sorted List
	 */
	public static List sortCategories(Collection categoriesList){
		Iterator iter = categoriesList.iterator();
		SortedMap map = new TreeMap();
		String catName = UIConstants.EMPTY_STRING;
		while(iter.hasNext()){
			CategoryResponseEvent catRe = (CategoryResponseEvent) iter.next();	
			catName = catRe.getCategoryName().toUpperCase();
			map.put(catName + catRe.getCategoryId(), catRe);
		}
		return new ArrayList(map.values());
	} 

	/**
	 * @param controlCodesList
	 * @return sorted List
	 */
	public static List sortControlCodes(List codesList){
		Iterator iter = codesList.iterator();
		SortedMap map = new TreeMap();
		String dName = "";
		while(iter.hasNext()){
			RiskAnalysisControlCodeResponseEvent raccre = (RiskAnalysisControlCodeResponseEvent) iter.next();	
			dName = raccre.getName().toUpperCase(); 
			map.put(dName, raccre);
		}
		return new ArrayList(map.values());
	}
}
