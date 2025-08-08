/*
 * Created on Feb 22, 2008
 *
 */
package messaging.administerassessments.reply;

import java.util.Map;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_bjangay
 *
 */
public class SCSTotalsResponseEvent extends ResponseEvent
{
	Map classificationTypeIdTotalMap; // map of classification IDs (String)and totals (Integer)
	
	
	/**
	 * @return Returns the classificationTypeIdTotalMap.
	 */
	public Map getClassificationTypeIdTotalMap() {
		return classificationTypeIdTotalMap;
	}
	/**
	 * @param classificationTypeIdTotalMap The classificationTypeIdTotalMap to set.
	 */
	public void setClassificationTypeIdTotalMap(Map classificationTypeIdTotalMap) {
		this.classificationTypeIdTotalMap = classificationTypeIdTotalMap;
	}
}
