/*
 * Created on Feb 02, 2007
 *
 */
package ui.codetable.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.codetable.reply.CodetableCompoundListResponseEvent;
import messaging.codetable.reply.CodetableDataResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author mchowdhury
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * @author PRamasamy
 * 
 */
public class CodetableForm extends ActionForm
{
	private String promptCodetableName;

	private String promptCodetableDescription;

	private String searchResultsCount;

	private String codetableRegId;

	private String selCodetableDataId;

	private String codetableType;

	private String codetableEntityName;

	private String codetableContextKey;

	private String codetableName;

	private String filterType;
	
	private String secondFilterType;

	private String filterAttributeId;
	
	private String secondFilterAttributeId;

	private String filterAttributeValue;
	
	private String secondFilterAttributeValue;

	// opType possible values: create, update, view
	// opType, paired with opStatus (in Request) will create Page Title in JSP
	private String opType;

	private Collection codetables;

	private Collection codetableAttributes;

	private Collection codetableDataList;

	private Collection codetableCompoundList;

	private Collection filteredCodetables;

	private CodetableDataResponseEvent currentCode;

	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
	}

	/**
	 * @param aRequest
	 */
	public void clear()
	{
		this.promptCodetableName = "";
		this.promptCodetableDescription = "";
		this.searchResultsCount = "";
		this.codetableRegId = "";
		this.selCodetableDataId = "";
		this.codetableType = "";
		this.filterAttributeId = "";
		this.secondFilterAttributeId = "";
		this.filterAttributeValue = "";
		this.secondFilterAttributeValue = "";
		this.filterType = "";
		this.secondFilterType = "";
		this.codetableEntityName = "";
		this.codetableContextKey = "";
		this.codetableName = "";
		this.opType = "";

		Collection emptyCol = new ArrayList();
		this.filteredCodetables = emptyCol;
		this.codetables = emptyCol;
		this.codetableAttributes = emptyCol;
		this.codetableDataList = emptyCol;
		this.codetableCompoundList = emptyCol;

	}

	/**
	 * @return Returns the promptCodetableName.
	 */
	public String getPromptCodetableName()
	{
		return promptCodetableName;
	}

	/**
	 * @param promptCodetableName
	 *            The promptCodetableName to set.
	 */
	public void setPromptCodetableName(String promptCodetableName)
	{
		this.promptCodetableName = promptCodetableName;
	}

	/**
	 * @return Returns the codetables.
	 */
	public Collection getCodetables()
	{
		return codetables;
	}

	/**
	 * @param codetables
	 *            The codetables to set.
	 */
	public void setCodetables(Collection codetables)
	{
		this.codetables = codetables;
	}

	/**
	 * @return Returns the searchResultsCount.
	 */
	public String getSearchResultsCount()
	{
		return searchResultsCount;
	}

	/**
	 * @param searchResultsCount
	 *            The searchResultsCount to set.
	 */
	public void setSearchResultsCount(String searchResultsCount)
	{
		this.searchResultsCount = searchResultsCount;
	}

	/**
	 * @return Returns the codetableRegId.
	 */
	public String getCodetableRegId()
	{
		return codetableRegId;
	}

	/**
	 * @param codetableRegId
	 *            The codetableRegId to set.
	 */
	public void setCodetableRegId(String codetableRegId)
	{
		this.codetableRegId = codetableRegId;
	}

	/**
	 * @return Returns the codetableAttributes.
	 */
	public Collection getCodetableAttributes()
	{
		return codetableAttributes;
	}

	/**
	 * @param codetableAttributes
	 *            The codetableAttributes to set.
	 */
	public void setCodetableAttributes(Collection codetableAttributes)
	{
		this.codetableAttributes = codetableAttributes;
	}

	/**
	 * @return Returns the codetableDataList.
	 */
	public Collection getCodetableDataList()
	{
		return codetableDataList;
	}

	/**
	 * @param codetableDataList
	 *            The codetableDataList to set.
	 */
	public void setCodetableDataList(Collection codetableDataList)
	{
		this.codetableDataList = codetableDataList;
	}

	/**
	 * @return Returns the selCodetableDataId.
	 */
	public String getSelCodetableDataId()
	{
		return selCodetableDataId;
	}

	/**
	 * @param selCodetableDataId
	 *            The selCodetableDataId to set.
	 */
	public void setSelCodetableDataId(String selCodetableDataId)
	{
		this.selCodetableDataId = selCodetableDataId;
	}

	public CodetableRecordResponseEvent getSelectedCodeTable()
	{
		if (codetableRegId != null && codetableRegId.length() > 0)
		{
			if(codetables != null){
				for (Iterator iter = codetables.iterator(); iter.hasNext();)
				{
					CodetableRecordResponseEvent re = (CodetableRecordResponseEvent) iter.next();
					if (codetableRegId.equals(re.getCodetableRegId()))
					{
						return re;
					}
				}
			}
		}

		return null;

	}

	public Object getNewCodeMap(String key)
	{
		return (Object) currentCode.getValueMap().get(new Integer(key));
	}

	public void setNewCodeMap(String key, Object value)
	{
		currentCode.getValueMap().put(new Integer(key), value);
	}

	/**
	 * @return Returns the codetableType.
	 */
	public String getCodetableType()
	{
		return codetableType;
	}

	/**
	 * @param codetableType
	 *            The codetableType to set.
	 */
	public void setCodetableType(String codetableType)
	{
		this.codetableType = codetableType;
	}

	/**
	 * @return Returns the currentCode.
	 */
	public CodetableDataResponseEvent getCurrentCode()
	{
		return currentCode;
	}

	/**
	 * @param currentCode
	 *            The currentCode to set.
	 */
	public void setCurrentCode(CodetableDataResponseEvent currentCode)
	{
		this.currentCode = currentCode;
	}

	/**
	 * @return Returns the codetableCompoundList.
	 */
	public Collection getCodetableCompoundList()
	{
		return codetableCompoundList;
	}

	/**
	 * @param codetableCompoundList
	 *            The codetableCompoundList to set.
	 */
	public void setCodetableCompoundList(Collection codetableCompoundList)
	{
		this.codetableCompoundList = codetableCompoundList;
	}

	public Collection getCodeTable(String key)
	{
		Object obj = null;
		// this.getCodetableCompoundList().
		return (Collection) obj;
	}

	/**
	 * @param codetableIdentifier.
	 * @return List
	 */
	public List getCompoundList(String codetableIdentifier)
	{
		List res = new ArrayList();

		CodetableCompoundListResponseEvent ccre = getCompoundListResponseEvent(codetableIdentifier);
		if (ccre != null)
		{
			res = getCompoundListResponseEvent(codetableIdentifier).getList();
		}

		return res;
	}

	public CodetableCompoundListResponseEvent getCompoundListResponseEvent(String codetableIdentifier)
	{

		for (Iterator iter = this.codetableCompoundList.iterator(); iter.hasNext();)
		{
			CodetableCompoundListResponseEvent ccre = (CodetableCompoundListResponseEvent) iter.next();
			if (codetableIdentifier.equalsIgnoreCase(ccre.getCodetableIdentifier()))
			{
				return ccre;
			}
		}
		return null;
	}

	/**
	 * @return Returns the codetableContextKey.
	 */
	public String getCodetableContextKey()
	{
		return codetableContextKey;
	}

	/**
	 * @param codetableContextKey
	 *            The codetableContextKey to set.
	 */
	public void setCodetableContextKey(String codetableContextKey)
	{
		this.codetableContextKey = codetableContextKey;
	}

	/**
	 * @return Returns the codetableEntityName.
	 */
	public String getCodetableEntityName()
	{
		return codetableEntityName;
	}

	/**
	 * @param codetableEntityName
	 *            The codetableEntityName to set.
	 */
	public void setCodetableEntityName(String codetableEntityName)
	{
		this.codetableEntityName = codetableEntityName;
	}

	/**
	 * @return Returns the codetableName.
	 */
	public String getCodetableName()
	{
		return codetableName;
	}

	/**
	 * @param codetableName
	 *            The codetableName to set.
	 */
	public void setCodetableName(String codetableName)
	{
		this.codetableName = codetableName;
	}

	/**
	 * @return Returns the filterAttributeId.
	 */
	public String getFilterAttributeId()
	{
		return filterAttributeId;
	}

	/**
	 * @param filterAttributeId
	 *            The filterAttributeId to set.
	 */
	public void setFilterAttributeId(String filterAttributeId)
	{
		this.filterAttributeId = filterAttributeId;
	}

	/**
	 * @return Returns the filterAttributeValue.
	 */
	public String getFilterAttributeValue()
	{
		return filterAttributeValue;
	}

	/**
	 * @param filterAttributeValue
	 *            The filterAttributeValue to set.
	 */
	public void setFilterAttributeValue(String filterAttributeValue)
	{
		this.filterAttributeValue = filterAttributeValue;
	}

	/**
	 * @return Returns the filteredCodetables.
	 */
	public Collection getFilteredCodetables()
	{
		return filteredCodetables;
	}

	/**
	 * @param filteredCodetables
	 *            The filteredCodetables to set.
	 */
	public void setFilteredCodetables(Collection filteredCodetables)
	{
		this.filteredCodetables = filteredCodetables;
	}

	/**
	 * @return Returns the filterType.
	 */
	public String getFilterType()
	{
		return filterType;
	}

	/**
	 * @param filterType
	 *            The filterType to set.
	 */
	public void setFilterType(String filterType)
	{
		this.filterType = filterType;
	}

	/**
	 * @return Returns the opType.
	 */
	public String getOpType()
	{
		return opType;
	}

	/**
	 * @param opType
	 *            The opType to set.
	 */
	public void setOpType(String opType)
	{
		this.opType = opType;
	}

	public void resetFilterCriteria()
	{
		setFilterAttributeId("");
		setFilterAttributeValue("");
		setFilterType("");
		setSecondFilterAttributeId("");
		setSecondFilterType("");
		setSecondFilterAttributeValue("");
	}

	/**
	 * @return Returns the promptCodetableDescription
	 */
	public String getPromptCodetableDescription()
	{
		return promptCodetableDescription;
	}

	/**
	 * @param promptCodetableDescription
	 *            The promptCodetableDescription to set.
	 */
	public void setPromptCodetableDescription(String promptCodetableDescription)
	{
		this.promptCodetableDescription = promptCodetableDescription;
	}

	public String getSecondFilterAttributeId()
	{
	    return secondFilterAttributeId;
	}

	public void setSecondFilterAttributeId(String secondFilterAttributeId)
	{
	    this.secondFilterAttributeId = secondFilterAttributeId;
	}

	public String getSecondFilterType()
	{
	    return secondFilterType;
	}

	public void setSecondFilterType(String secondFilterType)
	{
	    this.secondFilterType = secondFilterType;
	}

	public String getSecondFilterAttributeValue()
	{
	    return secondFilterAttributeValue;
	}

	public void setSecondFilterAttributeValue(String secondFilterAttributeValue)
	{
	    this.secondFilterAttributeValue = secondFilterAttributeValue;
	}
	
	
	
	
	
	

}
