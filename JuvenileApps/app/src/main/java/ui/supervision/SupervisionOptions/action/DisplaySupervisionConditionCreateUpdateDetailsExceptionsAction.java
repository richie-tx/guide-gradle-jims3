//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionCreateUpdateDetailsExceptionsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionCreateUpdateDetailsExceptionsAction extends Action
{
   
   /**
    * @roseuid 42F7C4910138
    */
   public DisplaySupervisionConditionCreateUpdateDetailsExceptionsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3A0244
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	SupervisionConditionForm form = (SupervisionConditionForm)aForm;
	   	
	Collection exCourtNames = new ArrayList(); 
	Map courtVarElemMap = new HashMap();
	
	// map to get CourtNumber from id. courtNumber is shown on jsp while id is sent to db.	
	Map courtEventMap = new HashMap();    	
	Collection selCourts = form.getSelectedCourts();
	if(selCourts != null && selCourts.size() > 0)
	{
		//check if there's any exception courts selected. If there's none, forward to summary
		Iterator iter = selCourts.iterator();
		while(iter.hasNext())
		{
			CourtBean courtBean = (CourtBean)iter.next();
			Iterator courtsIter = courtBean.getCourts().iterator();
			while(courtsIter.hasNext())
			{
				CourtResponseEvent cre = (CourtResponseEvent)courtsIter.next();
				courtEventMap.put(cre.getCourtId(), cre.getCourtNumber());
				if(cre.isExceptionCourt())
				{
					exCourtNames.add(cre.getCourtId());
				}
			}
		}
			
		Collection veres = form.getVariableElementResponseEvents();
		Collection exCourtVeres = form.getExceptionCourtVarElemBeans();
		Map exCourtVeresMap = new HashMap();
			
		//turn exCourtVeres into HashMap for easy lookup
		if(exCourtVeres != null && exCourtVeres.size() > 0)
		{	
			Iterator exCourtVeresIter = exCourtVeres.iterator();
			while(exCourtVeresIter.hasNext())
			{
					
				CourtVariableElementBean cve = 
					(CourtVariableElementBean)exCourtVeresIter.next();
					
				Collection cveVeres = cve.getVariableElements();
				Iterator cveVeresIter = cveVeres.iterator();
					
				while(cveVeresIter.hasNext())
				{
					VariableElementResponseEvent vere = (VariableElementResponseEvent)cveVeresIter.next();
					if(exCourtVeresMap.get(vere.getName()+"^"+vere.getCourtId()) == null)
					{
						exCourtVeresMap.put(vere.getName()+"^"+vere.getCourtId(), vere);			
					}
				}
			}
		}
			
		
		if(veres != null && veres.size() > 0)
		{
			Iterator vereIter = veres.iterator();
				
			while(vereIter.hasNext())
			{
				VariableElementResponseEvent vere = 
						(VariableElementResponseEvent)vereIter.next();
				// set variableelementtypeId as it has not been set as yet				
				setVERTypeId(form.getDetailDictionaryNameIdHashMap(), vere);
											
				Iterator exCourtNamesIter = exCourtNames.iterator();
				VariableElementResponseEvent exVere = null;
					
				while(exCourtNamesIter.hasNext())
				{
					String exCourtName = (String)exCourtNamesIter.next();
						
					if(exCourtVeresMap.size() > 0)
					{
						exVere = (VariableElementResponseEvent)exCourtVeresMap.get(vere.getName()+"^"+exCourtName);
						if(exVere != null)
						{
							addCourtVarRespBean(courtVarElemMap, exVere, vere, courtEventMap);
						}
						else
						{
							exVere = createExCourtVere(vere, form, exCourtName);
							if(exVere != null)
								addCourtVarRespBean(courtVarElemMap, exVere, vere, courtEventMap);
						}
					}
					else
					{
						exVere = createExCourtVere(vere, form, exCourtName);
						if(exVere != null)
							addCourtVarRespBean(courtVarElemMap, exVere, vere, courtEventMap);
																
					}
				}
					
			}
		}
		else
		{
			//no veres to specify value, forward to summary page
		}			
		form.setExceptionCourtVarElemBeans(sortCourtVarElemMap( courtVarElemMap.values()));
	}
	else
	{
		//no selected courts, forward to summary page
	}
		
	return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }

	private void setVERTypeId(Map detailDictionaryNameIdMapping, VariableElementResponseEvent vere){
		//get variable element type id
		String variableElementName = vere.getName();
		VariableElementTypeResponseEvent varElementTypeRE = 
			(VariableElementTypeResponseEvent)detailDictionaryNameIdMapping.get(variableElementName.toUpperCase());

		String varElementId = "";
		if (varElementTypeRE != null)
		{
			varElementId = varElementTypeRE.getVariableElementTypeId();
			vere.setVariableElementTypeId(varElementId);
			if(varElementTypeRE.isEnumration()){
				// set code description if variableElement is of type enumeration
				vere.setValueByValueId();
			}
		}
	}
	
   private VariableElementResponseEvent createExCourtVere(VariableElementResponseEvent vere, SupervisionConditionForm form, String exCourtName)
   {
		VariableElementResponseEvent exVere = new VariableElementResponseEvent();
		exVere.setExceptionCourt(true);
		exVere.setName(vere.getName());
		exVere.setVariableElementTypeId(vere.getVariableElementTypeId());
		exVere.setCourtId(exCourtName);
		exVere.setEnumeration(vere.getEnumeration());
		exVere.setCodeTableName(vere.getCodeTableName());
		exVere.setEnumerationTypeId(vere.getEnumerationTypeId());
		exVere.setReference(vere.isReference()); 
		// get enumerated values
		exVere.setCodeValues(vere.getCodeValues());

		return exVere;
	  
   }
      
   private void addCourtVarRespBean(Map courtVarElemMap, VariableElementResponseEvent exVre, VariableElementResponseEvent vere, Map courtIdNumMap){
   		CourtVariableElementBean cve = (CourtVariableElementBean)courtVarElemMap.get(exVre.getCourtId());
   		if(cve == null){
   			cve = new CourtVariableElementBean();
   			cve.setCourtNumber((String)courtIdNumMap.get(exVre.getCourtId()));
			courtVarElemMap.put(exVre.getCourtId(), cve);
			// get enumerated values
			exVre.setCodeValues(vere.getCodeValues());
   		}
	// get enumerated values
	if(exVre.isEnumeration()){
		exVre.setCodeValues(vere.getCodeValues());
	}
   		cve.insertVariableElement(exVre);
   }
   
 	/**
	 * @param CourtVarElemMap
	 * @return Collection
	*/ 
	public static Collection sortCourtVarElemMap(Collection cveMap){	
		if (cveMap.size() > 1){
			SortedMap map = new TreeMap();
			Iterator Iter = cveMap.iterator();
			String crtNum = "";
			while(Iter.hasNext()){
				CourtVariableElementBean cre = (CourtVariableElementBean) Iter.next();
				crtNum = cre.getCourtNumber();		
				while (crtNum.length() < 4){
					crtNum = " " + crtNum;	
				}
				map.put(crtNum, cre);	
			}
			cveMap = map.values();
		}	
		return cveMap; 
	}
	 
}
