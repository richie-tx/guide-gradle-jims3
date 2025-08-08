/*
 * Created on Nov 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.Question;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ResidentialExitPlanForm extends ActionForm
{
	// Default Form variables
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	private String closingInfoId="";
	private String expectedReleaseDate="";
	private String facility="";
	private String facilityReleaseReason="";
	private String levelOfCare="";
	private String permanencyPlan="";
	private String specialNotes="";
	private Collection exitPlanQuestions=new ArrayList();
	private String reportType="REPR";
		
	//Drop down Ids 
	private String facilityId="";
	private String facilityReleaseReasonId="";
	private String levelOfCareId="";
	private String permanencyPlanId="";
	
	// Drop Down Lists
	private static Collection facilityList=emptyColl;
	private static Collection facilityReleaseReasonList=emptyColl;
	private static Collection levelOfCareList=emptyColl;
	private static Collection permanencyPlanList=emptyColl;
	
	
	public ResidentialExitPlanForm(){
		//		Default Initialization
		  emptyColl = new ArrayList();
		  listsSet=false;
		  action="";
		  secondaryAction="";
		  update=false;
		  delete=false;
		  selectedValue="";
		  UIJuvenileLoadCodeTables.getInstance().setResidentialExitPlanForm(this);
		  // Form specific initialization
		//testForm();
	}
	
	public void clearAll(){
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
	
		expectedReleaseDate="";
		facility="";
		facilityReleaseReason="";
		levelOfCare="";
        permanencyPlan="";
		specialNotes="";
		//exitPlanQuestions=new ArrayList();
		
			//Drop down Ids 
		facilityId="";
		facilityReleaseReasonId="";
		levelOfCareId="";
		permanencyPlanId="";
	}
	
	private void testForm(){
		for(int loopX=0; loopX<10; loopX++)
			exitPlanQuestions.add(new Question());
		Collections.sort((List)exitPlanQuestions);
	}
	
	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @return
	 */
	public String getExpectedReleaseDate()
	{
		return expectedReleaseDate;
	}

	/**
	 * @return
	 */
	public String getFacility()
	{
		return facility;
	}

	/**
	 * @return
	 */
	public String getFacilityId()
	{
		return facilityId;
	}

	/**
	 * @return
	 */
	public Collection getFacilityList()
	{
		return facilityList;
	}

	/**
	 * @return
	 */
	public String getFacilityReleaseReason()
	{
		return facilityReleaseReason;
	}

	/**
	 * @return
	 */
	public String getFacilityReleaseReasonId()
	{
		return facilityReleaseReasonId;
	}

	/**
	 * @return
	 */
	public Collection getFacilityReleaseReasonList()
	{
		return facilityReleaseReasonList;
	}

	

	/**
	 * @return
	 */
	public String getLevelOfCare()
	{
		return levelOfCare;
	}

	/**
	 * @return
	 */
	public String getLevelOfCareId()
	{
		return levelOfCareId;
	}

	/**
	 * @return
	 */
	public Collection getLevelOfCareList()
	{
		return levelOfCareList;
	}

	/**
	 * @return
	 */
	public boolean isListsSet()
	{
		return listsSet;
	}

	

	/**
	 * @return
	 */
	public String getPermanencyPlan()
	{
		return permanencyPlan;
	}

	/**
	 * @return
	 */
	public String getPermanencyPlanId()
	{
		return permanencyPlanId;
	}

	/**
	 * @return
	 */
	public Collection getPermanencyPlanList()
	{
		return permanencyPlanList;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public String getSpecialNotes()
	{
		return specialNotes;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
		delete = b;
	}

	/**
	 * @param string
	 */
	public void setExpectedReleaseDate(String string)
	{
		expectedReleaseDate = string;
	}

	/**
	 * @param string
	 */
	public void setFacility(String string)
	{
		facility = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityId(String string)
	{
		facilityId = string;
		if(facilityId==null || facilityId.equals("")){
			facility = "";
			return;
		}
		if(ResidentialExitPlanForm.facilityList !=null &&  ResidentialExitPlanForm.facilityList.size()>0){
			facility=CodeHelper.getCodeDescriptionByCode(ResidentialExitPlanForm.facilityList,facilityId);
		}
	}

	/**
	 * @param collection
	 */
	public void setFacilityList(Collection collection)
	{
		facilityList = collection;
	}

	/**
	 * @param string
	 */
	public void setFacilityReleaseReason(String string)
	{
		facilityReleaseReason = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityReleaseReasonId(String string)
	{
		facilityReleaseReasonId = string;
		if(facilityReleaseReasonId==null || facilityReleaseReasonId.equals(""))
			return;
		if(ResidentialExitPlanForm.facilityReleaseReasonList !=null &&  ResidentialExitPlanForm.facilityReleaseReasonList.size()>0){
			facilityReleaseReason=CodeHelper.getCodeDescriptionByCode(ResidentialExitPlanForm.facilityReleaseReasonList,facilityReleaseReasonId);
		}
	}

	/**
	 * @param collection
	 */
	public void setFacilityReleaseReasonList(Collection collection)
	{
		facilityReleaseReasonList = collection;
	}


	/**
	 * @param string
	 */
	public void setLevelOfCare(String string)
	{
		levelOfCare = string;
	}

	/**
	 * @param string
	 */
	public void setLevelOfCareId(String string)
	{
		levelOfCareId = string;
		if(levelOfCareId==null || levelOfCareId.equals(""))
			return;
		if(ResidentialExitPlanForm.levelOfCareList !=null &&  ResidentialExitPlanForm.levelOfCareList.size()>0){
			levelOfCare=CodeHelper.getCodeDescriptionByCode(ResidentialExitPlanForm.levelOfCareList,levelOfCareId);
		}
	}

	/**
	 * @param collection
	 */
	public void setLevelOfCareList(Collection collection)
	{
		levelOfCareList = collection;
	}

	/**
	 * @param b
	 */
	public void setListsSet(boolean b)
	{
		listsSet = b;
	}

	/**
	 * @param string
	 */
	public void setPermanencyPlan(String string)
	{
		permanencyPlan = string;
	}

	/**
	 * @param string
	 */
	public void setPermanencyPlanId(String string)
	{
		permanencyPlanId = string;
		if(permanencyPlanId==null || permanencyPlanId.equals(""))
			return;
		if(ResidentialExitPlanForm.permanencyPlanList !=null &&  ResidentialExitPlanForm.permanencyPlanList.size()>0){
			permanencyPlan=CodeHelper.getCodeDescriptionByCode(ResidentialExitPlanForm.permanencyPlanList,permanencyPlanId);
		}
	}

	/**
	 * @param collection
	 */
	public void setPermanencyPlanList(Collection collection)
	{
		permanencyPlanList = collection;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

	/**
	 * @param string
	 */
	public void setSpecialNotes(String string)
	{
		specialNotes = string;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
		update = b;
	}

	/**
	 * @return
	 */
	public Collection getExitPlanQuestions()
	{
		return exitPlanQuestions;
	}

	/**
	 * @param collection
	 */
	public void setExitPlanQuestions(Collection collection)
	{
		exitPlanQuestions = collection;
	}

	/**
	 * @return
	 */
	public String getClosingInfoId()
	{
		return closingInfoId;
	}

	/**
	 * @param string
	 */
	public void setClosingInfoId(String string)
	{
		closingInfoId = string;
	}

	/**
	 * @return
	 */
	public String getReportType()
	{
		return reportType;
	}

	/**
	 * @param string
	 */
	public void setReportType(String string)
	{
		reportType = string;
	}

}
