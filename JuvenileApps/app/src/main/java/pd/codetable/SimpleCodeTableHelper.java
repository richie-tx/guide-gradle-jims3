/*
 * Created on Apr 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import messaging.code.CodeRespEvtCodeIdComparator;
import messaging.codetable.GetCodeEvent;
import messaging.codetable.GetCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SimpleCodeTableHelper extends CodeTableHelper 
{
	 
    /**
     * Will return a List of UNSORTED CodeResponseEvents for a given particular code table. Will
     * return an empty list if no codes exist for that code table.
     * 
     * @param codeTableName
     * @return Collection
     */
    public static List getUnsortedCodeRespEvts(String codeTableName)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODES);
        List codes=null;
        if(codeTableName!=null && !codeTableName.trim().equals("")){
	        codesRequestEvent.setCodeTableName(codeTableName);
	        dispatch.postEvent(codesRequestEvent);
	        CompositeResponse response = MessageUtil.postRequest(codesRequestEvent);
	        codes = MessageUtil.compositeToList(response, CodeResponseEvent.class);
        }
        if(codes==null){
        	codes=new ArrayList();
        }
        return codes;
    }

    /**
     * Returs a list of CodeResponseEvents sorted alphabetically by description. Will
     * return an empty list if no codes exist for that code table.
     * @param codeTableName
     * @return
     */
    public static List getCodesSortedByDesc(String codeTableName)
    {
        List codes = getUnsortedCodeRespEvts(codeTableName);
        sortICodesByDescr(codes);
        return codes;
    }
    
    /**
     * Returns a list of CodeResponseEvents sorted by the code. Will
     * return an empty list if no codes exist for that code table.
     * @param codeTableName
     * @return
     */
    public static List getCodesSortedByCode(String codeTableName)
    {
        List codes = getUnsortedCodeRespEvts(codeTableName);
        sortICodesByCode(codes);
        return codes;
    }
    
    /**
     * Returns a list of CodeResponseEvents sorted by the CodeId. Will
     * return an empty list if no codes exist for that code table.
     * @param codeTableName
     * @return
     */
    public static List getCodesSortedByCodeId(String codeTableName)
    {
        List codes = getUnsortedCodeRespEvts(codeTableName);
        sortCodeRespEvtsByCodeId(codes);
        return codes;
    }
    	 
    /**
	  * Given an incoming code table name  and  code, returns the selected ICode
	  * @param codeTableName
	  * @param aCode -- a code
	  * @return
	  */
	 public static ICode getICodeObjFromCode(String aCodeTableName, String aCode)
	 {
	 	ICode retVal=null;
	 	if(aCodeTableName==null || aCode==null || aCodeTableName.trim().equals("") || aCode.trim().equals("")) {
	 		
	 	}
	 	else{
	 		List myList=getUnsortedCodeRespEvts(aCodeTableName);
	 		retVal= getICodeObjFromCode(myList,aCode);
	 	}
	 	return retVal;
	 }
    
    
 /**
  * Given an incoming code table name  and  code Id, returns the selected ICode
  * @param codeTableName
  * @param aCodeId -- a code ID
  * @return
  */
 public static ICode getICodeObjFromCodeId(String aCodeTableName, String aCodeId)
 {
 	ICode retVal=null;
 	if(aCodeTableName==null || aCodeId==null || aCodeTableName.trim().equals("") || aCodeId.trim().equals("")) {
 		
 	}
 	else{
 		List myList=getUnsortedCodeRespEvts(aCodeTableName);
 		retVal= getICodeObjFromCodeId(myList,aCodeId);
 	}
 	return retVal;
 }
 
 /**
  * Find the given ICode Object by searching to see if the incoming code or codeId is found within the 
  * specified collection of codeRespObjs passed in
  * @param aICodeObjs -- a List containig ICode Objects
  * @param aCode
  * @return
  */
 public static ICode getICodeObjFromCodeId(Collection aCodeRespEvtObjs, String aCodeId)
 {
 	CodeResponseEvent retValue = null;
     if (aCodeRespEvtObjs != null && aCodeRespEvtObjs.size()>0 && aCodeId!=null &&  !aCodeId.trim().equals(""))
     {
         Iterator i = aCodeRespEvtObjs.iterator();
         while (i.hasNext())
         {
         	Object myObj=i.next();
        	if(myObj!=null && (myObj instanceof CodeResponseEvent)){
        		CodeResponseEvent tempCode = (CodeResponseEvent)myObj;
	             if (tempCode.getCodeId()!=null && tempCode.getCodeId().equals(aCodeId) )
	             {
	                 retValue = tempCode;
	             }
        	}
         }
     }
     return retValue;
 }
 
 
 /**
  * Given an incoming code table name  and a code , returns the description 
  * @param codeTableName
  * @param aCode -- a code used to decide what is used to determine which description to get 
  * @return
  */
 public static String getDescrByCode(String aCodeTableName, String aCode)
 {
     String description = "";
     ICode retVal=getICodeObjFromCode(aCodeTableName,aCode);
     if (retVal!=null && retVal.getDescription()!=null){
     	description=retVal.getDescription();
     }
     return description;
 }
 
 /**
  * Given an incoming code table name  and a codeId , returns the description 
  * @param codeTableName
  * @param aCodeId -- a codeId used to decide what is used to determine which description to get 
  * @return
  */
 public static String getDescrByCodeId(String aCodeTableName, String aCodeId)
 {
     String description = "";
     ICode retVal=getICodeObjFromCodeId(aCodeTableName,aCodeId);
     if (retVal!=null && retVal.getDescription()!=null){
     	description=retVal.getDescription();
     }
     return description;
 }
 
 /**
  * Given an incoming code table name  and a codeId , returns the description 
  * @param aCodeRespEvtObjs -- the code resp event objects to search through
  * @param aCodeId -- a codeId used to decide what is used to determine which description to get
  * @return
  */
 public static String getDescrByCodeId(Collection aCodeRespEvtObjs, String aCodeId)
 {
     String description = "";
     ICode retVal=getICodeObjFromCodeId(aCodeRespEvtObjs,aCodeId);
     if (retVal!=null && retVal.getDescription()!=null){
     	description=retVal.getDescription();
     }
     return description;
 }
 
 
 
 /**
  * Used to get a list of String description objects back that match the incoming set of selectedCodeIdValues 
  * @param codeTableName
  * @param aSelCodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
  *  has codes in them instead.
  * @return 
  */
 public static List getDescsFromSelCodeIds(String aCodeTableName, String[] aSelCodeIdValues)
 {
     List myCodeTableValues=getUnsortedCodeRespEvts(aCodeTableName);
     return getDescsFromSelCodeIds(myCodeTableValues,aSelCodeIdValues);
 }
 
 
 
 /**
  * Used to get a list of String description objects back that match the incoming set of selectedCodeValues 
  * @param codeTableName
  * @param aSelCodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
	  * has codes in them instead.
  * @return 
  */
 public static List getDescsFromSelCodeIds(List aCodeRespEvents, String[] aSelCodeIdValues)
 {
     List selected = new ArrayList();
     List mySelectedCodeRespEvnts=aCodeRespEvents;
     if(aSelCodeIdValues!=null && aSelCodeIdValues.length>0){
     	for (int loopX = 0; loopX < aSelCodeIdValues.length; loopX++)
         {
	     	String findVal=aSelCodeIdValues[loopX];
		     if(mySelectedCodeRespEvnts!=null && mySelectedCodeRespEvnts.size()>0){
		     	 for (int i = 0; i < mySelectedCodeRespEvnts.size(); i++)
		         {
		     	 	Object myObj=mySelectedCodeRespEvnts.get(i);
		     	 	if(myObj!=null && (myObj instanceof CodeResponseEvent)){
		     	 		CodeResponseEvent myRetVal= (CodeResponseEvent)myObj;
			         	if(findVal!=null && myRetVal.getCodeId()!=null && myRetVal!=null && myRetVal.getDescription()!=null && myRetVal.getCodeId().equals(findVal)){
			                 selected.add(myRetVal.getDescription());
			                 break;
			         	}
		     	 	}
		         }
		     }
         }
     }
     return selected;
 }
 
 /**
  * Used to get a list of String description objects back that match the incoming set of selectedCodeIdValues 
  * @param codeTableName
  * @param aSelCodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
  *  has codes in them instead.
  * @return 
  */
 public static List getDescsFromSelCodes(String aCodeTableName, String[] aSelCodeValues)
 {
     List myCodeTableValues=getUnsortedCodeRespEvts(aCodeTableName);
     return getDescsFromSelCodes(myCodeTableValues,aSelCodeValues);
 }
 
 public static List getDescsFromSelCodes(String aCodeTableName, List aSelCodeValues)
 {
     List myCodeTableValues=getUnsortedCodeRespEvts(aCodeTableName);
     return getDescsFromSelCodes(myCodeTableValues,aSelCodeValues);
 }

 
     /**
	  * Used to get a list of CodeResponseEvent objects back that match the incoming set of selectedCodeValues 
	  * @param codeTableName
	  * @param aSelCode_CodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
	  * @param aIsCodeIds -- if true that indicates the aSelCode_CodeIdValues have codeIds in them and if false indicates that the aSelCode_CodeIdValues
	  * has codes in them instead.
	  * @return 
	  */
	 public static List getICodeObjsFromSelCodes(String aCodeTableName, String[] aSelCodeValues)
	 {
	     List myCodeTableValues=getUnsortedCodeRespEvts(aCodeTableName);
	     return getICodeObjsFromSelCodes(myCodeTableValues,aSelCodeValues);
	 }

	 
	 /**
	  * Used to get a list of CodeResponseEvent objects back that match the incoming set of selectedCodeValues 
	  * @param codeTableName
	  * @param aSelCode_CodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
	  * @param aIsCodeIds -- if true that indicates the aSelCode_CodeIdValues have codeIds in them and if false indicates that the aSelCode_CodeIdValues
	  * has codes in them instead.
	  * @return 
	  */
	 public static List getICodeObjsFromSelCodeIds(String aCodeTableName, String[] aSelCodeIdValues)
	 {
	     List myCodeTableValues=getUnsortedCodeRespEvts(aCodeTableName);
	     return getICodeObjsFromSelCodeIds(myCodeTableValues,aSelCodeIdValues);
	 }
	 		 
	 /**
	  * Used to get a list of CodeResponseEvent objects back that match the incoming set of selectedCodeValues 
	  * @param codeTableName
	  * @param aSelCode_CodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
	  * @param aIsCodeIds -- if true that indicates the aSelCode_CodeIdValues have codeIds in them and if false indicates that the aSelCode_CodeIdValues
	  * has codes in them instead.
	  * @return 
	  */
	 public static List getICodeObjsFromSelCodeIds(List aCodeRespEvtObjs, String[] aSelCodeIdValues)
	 {
	     List selected = new ArrayList();
	     List myCodeTableValues=aCodeRespEvtObjs;
	     if (myCodeTableValues!=null && myCodeTableValues.size()>0 && aSelCodeIdValues != null && aSelCodeIdValues.length > 0)
	     {
	         for (int i = 0; i < aSelCodeIdValues.length; i++)
	         {
	         	ICode myRetVal= getICodeObjFromCodeId(myCodeTableValues,aSelCodeIdValues[i]);
	         	if(myRetVal!=null){
	                 selected.add(myRetVal);
	         	}
	         }
	     }
	     return selected;
	 }
	 
	 /**
	  * Intended typically to be used to sort an already retrieved list into a different format.
	  * @param aCodeResponseEvent_Codes -- List that must contain CodeResponseEvent objects
	  */
	 public static void sortCodeRespEvtsByCodeId(List aCodeResponseEvent_Codes){
	 	if(aCodeResponseEvent_Codes!=null && aCodeResponseEvent_Codes.size()>0)
	 		Collections.sort(aCodeResponseEvent_Codes,new CodeRespEvtCodeIdComparator());
	 }
    
	 
	 /**
	  * Gets the code directly by quering the database.
	  * @param codeTableName
	  * @param code
	  * @return
	  */
	 public static ICode getICodeByCode_Backend(String codeTableName, String code)
	 {
	     GetCodeEvent codeEvent = (GetCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODE);
	     codeEvent.setCodeTableName(codeTableName);
	     codeEvent.setCode(code);
	     IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	     dispatch.postEvent(codeEvent);
	     CompositeResponse response = (CompositeResponse) dispatch.getReply();
	     ICode codeResponse = (ICode) MessageUtil.filterComposite(response, ICode.class);
	     return codeResponse;
	 }

}