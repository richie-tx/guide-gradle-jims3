/*
 * HandleObjectSortingAction.java
 *
 * Created on June 9, 2005, 11:25 AM
*/

package ui.common.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.config.ISortingConstants;
import mojo.km.config.SortingEntity;
import mojo.km.config.xml.XMLSortingPropertyAdapter;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.SortItemList;

/**
 *
 * @author  mchowdhury
 * description: This class handles the sorting request for many different items
 */
public class HandleObjectListSortingAction extends Action{
	/** public Constructor
	*/
	public HandleObjectListSortingAction() {
	}

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse){
		
		Collection items = null;
		String returnPath = null;

		try {
			String order = aRequest.getParameter(ISortingConstants.ORDER);
			String id = aRequest.getParameter(ISortingConstants.ID);
			String characterSelected = aRequest.getParameter("selectedChar");

            if(id == null || id.equals("")){
				this.dispatchToErrorPage("error.common",aMapping,aRequest);
			}
			XMLSortingPropertyAdapter xmpAdapter = XMLSortingPropertyAdapter.getInstance();
		    SortingEntity sEntity = xmpAdapter.getSortingEntity(id);
		
		    if(sEntity == null){
				this.dispatchToErrorPage("error.common",aMapping,aRequest);
		    }
		    
		    String primarySortBy = sEntity.getPrimarySortBy();
		    String secondarySortBy = sEntity.getSecondarySortBy();
		    returnPath = sEntity.getReturnPath();
			String responseEvent = sEntity.getResponseEvent();
	        String responseEventId = sEntity.getResponseEventId();
	        String actionForm = sEntity.getActionForm();
	        String data = sEntity.getCollectionData();
	        String actionFormIdentifier = sEntity.getActionFormIdentifier();
	        String sortingDataType = sEntity.getSortingDataType();

			Object formObj = Class.forName(actionForm).newInstance();
			// if form is in session scope
			formObj = (Object) aRequest.getSession().getAttribute(actionFormIdentifier);
			if(formObj == null){
			   // if form is in request scope
			   formObj = (Object) aRequest.getParameter(actionFormIdentifier);
			}
			items = this.getItemsList(formObj,data);
	       
			if((order == null || order.equals("")) && characterSelected != null && !(characterSelected.equals("")) && !(characterSelected.equalsIgnoreCase("0"))){
			   items = SortItemList.getSortedData(responseEvent,responseEventId,primarySortBy,secondarySortBy,items,Integer.parseInt(characterSelected));
			}else if(order.equals(ISortingConstants.ASCENDING_ORDER)){
			   if(sortingDataType != null && sortingDataType.equalsIgnoreCase("Date")){
			   	   items = SortItemList.getAscendingSortedDataByDate(responseEvent,responseEventId,primarySortBy,secondarySortBy,items);
			   }else if(sortingDataType != null && sortingDataType.equalsIgnoreCase("Integer")){
				   items = SortItemList.getAscendingSortedDataByInteger(responseEvent,responseEventId,primarySortBy,secondarySortBy,items);
			   }else{
				   items = SortItemList.getAscendingSortedData(responseEvent,responseEventId,primarySortBy,secondarySortBy,items);
			   }
			}else if(order.equals(ISortingConstants.DESCENDING_ORDER)){   
				items = SortItemList.getDescendingSortedData(responseEvent,responseEventId,primarySortBy,secondarySortBy,sortingDataType,items);
		    }
		   // set the forms with sorted Collection
		   this.setFormWithSortedCollection(formObj,items,data);
     	} catch(Exception e) {
			e.printStackTrace();
			this.dispatchToErrorPage("error.common",aMapping,aRequest);
		}
		return dispatch(returnPath);
    }
    
	/**
	 * @param errorkey
	 * @return ActionForward 
	 */
	private ActionForward dispatchToErrorPage(String errorkey,ActionMapping aMapping,HttpServletRequest aRequest){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorkey));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);    
	}

	/**
	 * @param ActionForward
	 */
	private ActionForward dispatch(String returnPath){
		ActionForward newForward = new ActionForward(returnPath);
		newForward.setRedirect(true);
		return newForward;
	}

	/**
	 * @param item Object
	 * @param items Collection
	 * @param methodCollectionName
	 */
	private void setFormWithSortedCollection(Object item, Collection items, String methodCollectionName) throws InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
	   String setter = "set" + methodCollectionName.substring(3);
	   Method method = item.getClass().getMethod(setter, new Class[]{Collection.class});
	   Object obj = method.invoke(item, new Object[]{items}); 
	}

	/**
	* @param item Object
	* @param methodName String
	* @return Collection
	*/	
	private Collection getItemsList(Object item, String methodName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException{
	   Method method = item.getClass().getMethod(methodName, new Class[]{});
	   Object obj = method.invoke(item, new Class[]{}); 
	   return (Collection) obj; 
	}
}