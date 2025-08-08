/*
 * Created on Oct 11, 2005
 *
 */
package ui.supervision.suggestedorder.helper;

import java.util.Collection;

import ui.common.CodeHelper;

/**
 * @author dgibler
 *
 */
public class SuggestedOrderListHelper
{
	/**
	 * @return Collection
	 */
	public Collection getCourtDivisions()
	{
		return CodeHelper.getSupervisionCourtDivisionCodes(true);
	}
	/**
		 * @return Collection
		 */
	public Collection getJurisdictions()
	{
		return CodeHelper.getJurisdictionCodes(true);
	}
	
	/**
	 * @return Collection
	 */
	public Collection getConditionTypes()
	{
		return CodeHelper.getConditionTypeCodes(true);
	}
}
