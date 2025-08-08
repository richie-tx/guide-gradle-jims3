/*
 * Created on Apr 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.code.ICodeComparator;
import messaging.codetable.reply.ICode;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodeTableHelper {

	 
	 /**
	  * Intended typically to be used to sort an already retrieved list into a different format.
	  * @param aICodes -- List that must contain ICode objects
	  */
  public static void sortICodesByCode(List aICodes){
	 	if(aICodes!=null && aICodes.size()>0)
	 		Collections.sort(aICodes,new ICodeComparator());
	 }
	 
	 /**
	  * Intended typically to be used to sort an already retrieved list into a different format.
	  * @param aICodes -- List that must contain ICode objects
	  */
	 public static void sortICodesByDescr(List aICodes){
	 	if(aICodes!=null && aICodes.size()>0)
	 		Collections.sort(aICodes);
	 }
	 
	 /**
		 * delim separated String representation of all descriptions for a given set of passed in ICodeObjects
		 * @param ICodeObjects -- a colleciton of ICodeObjects
		 * @param delim -- the delimiter aka separator between each description
		 * @return
		 */
		 public static String getStringDescrWDelim(List ICodeObjects, String delim)
		    {
		        StringBuffer buffer = new StringBuffer();
		        if (ICodeObjects != null && ICodeObjects.size()>0)
		        {
		        	int ICodeObjectsSize = ICodeObjects.size();
		            for (int loopX=0;loopX<ICodeObjectsSize;loopX++)
		            {
		            	Object myObj=ICodeObjects.get(loopX);
		            	if(myObj!=null && (myObj instanceof ICode)){
			                ICode code = (ICode)myObj;
			                buffer.append(code.getDescription());
			                if (delim != null && loopX < ICodeObjectsSize-1)
			                {
			                    buffer.append(delim);
			                }
		            	}
		            }
		        }
		        return buffer.toString();
		    }
		 /**
		     * Takes an imcoming collection of ICode objects and converts them to a map with code as the key and the ICode as the object.
		     * Will return an empty map if no codes exist for that code table.
		     * @param aICodes
		     * @return
		     */
		    public static Map getICodeObjMap(List aICodeObjects)
		    {
		        Map codeMap = new HashMap();
		        if (aICodeObjects != null)
		        {
		        	int ICodeObjectsSize = aICodeObjects.size();
		            for (int loopX=0;loopX<ICodeObjectsSize;loopX++)
		            {
		            	Object myObj=aICodeObjects.get(loopX);
		            	if(myObj!=null && myObj instanceof ICode){
		            		ICode codeRespEvt = (ICode) myObj;
			                if(codeRespEvt!=null && codeRespEvt.getCode()!=null){
			                		codeMap.put(codeRespEvt.getCode(), codeRespEvt);
			                	
			                }
		            	}
		            }
		        }
		        return codeMap;
		    } 
		    
		    /**
			  * Given an incoming Collection of ICodes  and a code , returns the description 
			  * @param aICodeObjs -- collection of ICodeObjects
			  * @param aCode -- a code used to decide what is used to determine which description to get 
			  * @return
			  */
			 public static String getDescrByCode(List aICodeObjs, String aCode)
			 {
			     String description = "";
			     ICode retVal=getICodeObjFromCode(aICodeObjs,aCode);
			     if (retVal!=null && retVal.getDescription()!=null){
			     	description=retVal.getDescription();
			     }
			     return description;
			 }
		    
		   
			 
			  /**
			  * Find the given ICode Object by searching to see if the incoming code or codeId is found within the 
			  * specified collection of ICode Objects passed in
			  * @param aICodeObjs -- a List containig ICode Objects
			  * @param aCode
			  * @return
			  */
	 public static ICode getICodeObjFromCode(List aICode_Codes, String aCode)
	 {
	     ICode retValue = null;
	     if (aICode_Codes != null && aCode!=null &&  !aCode.trim().equals(""))
	     {
	     	boolean found=false;
	     	int ICodeObjectsSize = aICode_Codes.size();
           for (int loopX=0;loopX<ICodeObjectsSize && !found;loopX++)
           {
           	Object myObj=aICode_Codes.get(loopX);
	        	if(myObj!=null && (myObj instanceof ICode)){
		             ICode tempCode = (ICode)myObj;
		             if (aCode.equals(tempCode.getCode()))
		             {
		                 retValue = tempCode;
		                 found=true;
		             }
	        	}
	         }
	     }
	     return retValue;
	 }
	 
	 /**
	  * Used to get a list of String description objects back that match the incoming set of selectedCodeValues 
	  * @param aICodeObjects
	  * @param aSelCodeValues -- a String array comprised of the selected code values
	  * @return 
	  */
	 public static List getDescsFromSelCodes(List aICodeObjects, String[] aSelCodeValues)
	 {
	     List selected = new ArrayList();
	     if(aSelCodeValues!=null && aSelCodeValues.length>0){
	     	for (int loopX = 0; loopX < aSelCodeValues.length; loopX++)
	         {
		     	String findVal=aSelCodeValues[loopX];
		     	boolean found=false;
			     if(aICodeObjects!=null && aICodeObjects.size()>0){
			     	 for (int i = 0, maxSize=aICodeObjects.size(); i < maxSize  && !found; i++)
			         {
			     	 	Object myObj=aICodeObjects.get(i);
			     	 	if(myObj!=null && (myObj instanceof ICode)){
			     	 		ICode myRetVal= (ICode)myObj;
				         	if(myRetVal.getCode()!=null && myRetVal.getDescription()!=null && myRetVal.getCode().equals(findVal)){
				                 selected.add(myRetVal.getDescription());
				                 found=true;
				         	}
			     	 	}
			         }
			     }
	         }
	     }
	     return selected;
	 }
	 
	 
	 /**
	  * Used to get a list of ICode objects back that match the incoming set of selectedCodeValues 
	  * @param aICodeObjs -- incoming list of aICodeObjs to search through
	  * @param aSelCode_CodeIdValues -- a String array comprised of the selected code values of a CodeResponseEvent
	  * @return 
	  */
	 public static List getICodeObjsFromSelCodes(List aICodeObjs, String[] aSelCodeValues)
	 {
	     List selected = new ArrayList();
	     if (aICodeObjs!=null && aICodeObjs.size()>0 && aSelCodeValues != null && aSelCodeValues.length > 0)
	     {
	         for (int i = 0; i < aSelCodeValues.length; i++)
	         {
	         	ICode myRetVal= getICodeObjFromCode(aICodeObjs,aSelCodeValues[i]);
	         	if(myRetVal!=null){
	                 selected.add(myRetVal);
	         	}
	         }
	     }
	     return selected;
	 }
	 
	 public static List getDescsFromSelCodes(List aICodeObjects, List aSelCodeValues)
	 {
	     List selected = new ArrayList();
	     if(aSelCodeValues!=null && aSelCodeValues.size()>0){
	     	for (int loopX = 0; loopX < aSelCodeValues.size();loopX++)
	         {
		     	String findVal= ((Code) aSelCodeValues.get(loopX)).getCode();
		     	boolean found=false;
			     if(aICodeObjects!=null && aICodeObjects.size()>0){
			     	 for (int i = 0, maxSize=aICodeObjects.size(); i < maxSize  && !found; i++)
			         {
			     	 	Object myObj=aICodeObjects.get(i);
			     	 	if(myObj!=null && (myObj instanceof ICode)){
			     	 		ICode myRetVal= (ICode)myObj;
				         	if(myRetVal.getCode()!=null && myRetVal.getDescription()!=null && myRetVal.getCode().equals(findVal)){
				                 selected.add(myRetVal.getDescription());
				                 found=true;
				         	}
			     	 	}
			         }
			     }
	         }
	     }
	     return selected;
	 }
	
}// END CLASS