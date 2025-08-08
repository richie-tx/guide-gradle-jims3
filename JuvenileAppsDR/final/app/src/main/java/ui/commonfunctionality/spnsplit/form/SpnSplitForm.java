/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.commonfunctionality.spnsplit.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.supervision.CaseInfo;
import ui.supervision.SupervisionPeriodInfo;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpnSplitForm extends ActionForm {
	
	// Default Elements in all forms
//	private static Collection emptyColl = new ArrayList();
	private boolean listsSet = false;
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	private String selectedTopic = "";
	
	private SpnInfo erroneousSpn;
	private SpnInfo validSpn;
	private SupervisionPeriodInfo currentSupPeriod;
	private String spnSplitExceptionId;
	
	public String getSpnSplitExceptionId() {
		return spnSplitExceptionId;
	}

	public void setSpnSplitExceptionId(String spnSplitExceptionId) {
		this.spnSplitExceptionId = spnSplitExceptionId;
	}

	private Collection supPeriods; // collection of SupervisionPeriodInfo object
	private String confirmationMessage = "";
	private List currentAssessments;
	private List currentAssociates;
	private List currentSupervisionPlans;
	private List selectedAssessments;
	private List selectedAssociates;
	private List selectedLOSes;
	private List selectedSupervisionPlans;
	private List spnSplitErrors;
	
	public List getSpnSplitErrors() {
		return spnSplitErrors;
	}

	public void setSpnSplitErrors(List spnSplitErrors) {
		this.spnSplitErrors = spnSplitErrors;
	}

	private String[] selectedAssessmentIds;
	private String[] selectedAssociateIds;
	private String[] selectedLOSIds;
	private String[] selectedSupervisionPlanIds;
	private String showBackButton;
	
	public SpnSplitForm(){
		clear();
	}
	
	public void clearErrSpn(){
		erroneousSpn=new SpnInfo();
	}
	
	public void clearValidSpn(){
		validSpn=new SpnInfo();
	}
	
	public static class SpnInfo{
		private String spn="";
		private IName name=new Name();
		private Date dob=null;
		private String sexId="";
		private String sex="";
		private String raceId="";
		private String race="";
		private String jailIndId="";
		private String jailInd="";
		/**
		 * @return Returns the dob.
		 */
		public Date getDob() {
			return dob;
		}
		/**
		 * @param dob The dob to set.
		 */
		public void setDob(Date dob) {
			this.dob = dob;
		}
		
		public String getDobAsStr(){
			if(dob==null)
				return "";
			else{
				try{
					return DateUtil.dateToString(dob,UIConstants.DATE_FMT_1);
				}
				catch(Exception e){
					return "";
				}
			}
		}
		/**
		 * @return Returns the jailInd.
		 */
		public String getJailInd() {
			jailInd = "";
			if (jailIndId != null){
				if (getJailIndId().equalsIgnoreCase("Y")){
					jailInd = "IN JAIL";
				} else {
					if (getJailIndId().equalsIgnoreCase("H")){
						jailInd = "HAS BEEN IN JAIL";
					} else {
						if (getJailIndId().equalsIgnoreCase("R")){
							jailInd = "IN RECEIVING";
						} else {
							if (getJailIndId().equalsIgnoreCase("I")){
								jailInd = "IN TRANSIT";
							} 
						}
					}
				}
			}
			return jailInd;
		}
		/**
		 * @param jailInd The jailInd to set.
		 */
		public void setJailInd(String jailInd) {
			this.jailInd = jailInd;
		}
		/**
		 * @return Returns the jailIndId.
		 */
		public String getJailIndId() {
			return jailIndId;
		}
		/**
		 * @param jailIndId The jailIndId to set.
		 */
		public void setJailIndId(String jailIndId) {
			this.jailIndId = jailIndId;
		}
		/**
		 * @return Returns the name.
		 */
		public IName getName() {
			return name;
		}
		/**
		 * @param name The name to set.
		 */
		public void setName(IName name) {
			this.name = name;
		}
		/**
		 * @return Returns the race.
		 */
		public String getRace() {
			return race;
		}
		/**
		 * @param race The race to set.
		 */
		public void setRace(String race) {
			this.race = race;
		}
		/**
		 * @return Returns the raceId.
		 */
		public String getRaceId() {
			return raceId;
		}
		/**
		 * @param raceId The raceId to set.
		 */
		public void setRaceId(String raceId) {
			this.raceId = raceId;
			if (raceId == null || raceId.equals(""))
			{
				race = "";
				return;
			}
			Collection myCodes=CodeHelper.getRaceCodes(false);
			if (myCodes != null && myCodes.size() > 0)
			{
				race = CodeHelper.getCodeDescriptionByCode(myCodes, raceId);
			}
		}
		/**
		 * @return Returns the sex.
		 */
		public String getSex() {
			return sex;
		}
		/**
		 * @param sex The sex to set.
		 */
		public void setSex(String sex) {
			this.sex = sex;
		}
		/**
		 * @return Returns the sexId.
		 */
		public String getSexId() {
			return sexId;
		}
		/**
		 * @param sexId The sexId to set.
		 */
		public void setSexId(String sexId) {
			this.sexId = sexId;
			if (sexId == null || sexId.equals(""))
			{
				sex = "";
				return;
			}
			Collection myCodes=CodeHelper.getSexCodes();
			if (myCodes != null && myCodes.size() > 0)
			{
				sex = CodeHelper.getCodeDescriptionByCode(myCodes, sexId);
			}
		}
		/**
		 * @return Returns the spn.
		 */
		public String getSpn() {
			return spn;
		}
		/**
		 * @param spn The spn to set.
		 */
		public void setSpn(String spn) {
			this.spn = spn;
		}
	}
	
	
	public void reset(ActionMapping mapping,
	  javax.servlet.http.HttpServletRequest request){
	
		Object obj=request.getAttribute("clearSupPeriodSelection");
		if(obj!=null){
			clearSupPeriodSelection(false);	
		}
		obj=null;
		obj=request.getParameter("clearSupPeriodSelection");
		if(obj!=null){
			clearSupPeriodSelection(false);	
		}
		obj=null;
		obj=request.getAttribute("clearOrdersSelection");
		if(obj!=null){
			clearOrdersSelection(false);	
		}
		obj=null;
		obj=request.getParameter("clearOrdersSelection");
		if(obj!=null){
			clearOrdersSelection(false);	
		}
	}
	
	public void clearOrdersSelection(boolean aDefaultValue){
		if(currentSupPeriod!=null && currentSupPeriod.getCases()!=null && currentSupPeriod.getCases().size()>0){
			Iterator iter=currentSupPeriod.getCases().iterator();
			while(iter.hasNext()){
				CaseInfo myCase=(CaseInfo)iter.next();
				myCase.setSelected(aDefaultValue);
			}
		}
	}
	
	public void clearSupPeriodSelection(boolean aDefaultValue){
		if(getSupPeriods()!=null && getSupPeriods().size()>0){
			Iterator iter=getSupPeriods().iterator();
			while(iter.hasNext()){
				SupervisionPeriodInfo mySupPeriod=(SupervisionPeriodInfo)iter.next();
				mySupPeriod.setSelected(aDefaultValue);
			}
		}
	}
	
	public void clear(){
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
		erroneousSpn=new SpnInfo();
		validSpn=new SpnInfo();
		currentSupPeriod=new SupervisionPeriodInfo();
		supPeriods=new ArrayList();
		spnSplitErrors= new ArrayList();
		clearOrdersSelection(false);
		clearSupPeriodSelection(false);
		spnSplitExceptionId="";
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the currentSupPeriod.
	 */
	public SupervisionPeriodInfo getCurrentSupPeriod() {
		return currentSupPeriod;
	}
	/**
	 * @param currentSupPeriod The currentSupPeriod to set.
	 */
	public void setCurrentSupPeriod(SupervisionPeriodInfo currentSupPeriod) {
		this.currentSupPeriod = currentSupPeriod;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the erroneousSpn.
	 */
	public SpnInfo getErroneousSpn() {
		return erroneousSpn;
	}
	/**
	 * @param erroneousSpn The erroneousSpn to set.
	 */
	public void setErroneousSpn(SpnInfo erroneousSpn) {
		this.erroneousSpn = erroneousSpn;
	}
	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}
	/**
	 * @param listsSet The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}

	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the supPeriods.
	 */
	public Collection getSupPeriods() {
		return supPeriods;
	}
	/**
	 * @param supPeriods The supPeriods to set.
	 */
	public void setSupPeriods(Collection supPeriods) {
		this.supPeriods = supPeriods;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
	 * @return Returns the validSpn.
	 */
	public SpnInfo getValidSpn() {
		return validSpn;
	}
	/**
	 * @param validSpn The validSpn to set.
	 */
	public void setValidSpn(SpnInfo validSpn) {
		this.validSpn = validSpn;
	}

	public String getSelectedTopic() {
		return selectedTopic;
	}

	public void setSelectedTopic(String selectedTopic) {
		this.selectedTopic = selectedTopic;
	}

	public List getCurrentAssessments() {
		return currentAssessments;
	}

	public void setCurrentAssessments(List currentAssessments) {
		this.currentAssessments = currentAssessments;
	}

	public List getCurrentAssociates() {
		return currentAssociates;
	}

	public void setCurrentAssociates(List currentAssociates) {
		this.currentAssociates = currentAssociates;
	}

	public List getCurrentSupervisionPlans() {
		return currentSupervisionPlans;
	}

	public void setCurrentSupervisionPlans(List currentSupervisionPlans) {
		this.currentSupervisionPlans = currentSupervisionPlans;
	}

	public String getConfirmationMessage() {
		return confirmationMessage;
	}

	public void setConfirmationMessage(String confirmationMessage) {
		this.confirmationMessage = confirmationMessage;
	}

	public List getSelectedAssessments() {
		return selectedAssessments;
	}

	public void setSelectedAssessments(List selectedAssessments) {
		this.selectedAssessments = selectedAssessments;
	}

	public List getSelectedAssociates() {
		return selectedAssociates;
	}

	public void setSelectedAssociates(List selectedAssociates) {
		this.selectedAssociates = selectedAssociates;
	}

	public List getSelectedLOSes() {
		return selectedLOSes;
	}

	public void setSelectedLOSes(List selectedLOSes) {
		this.selectedLOSes = selectedLOSes;
	}

	public List getSelectedSupervisionPlans() {
		return selectedSupervisionPlans;
	}

	public void setSelectedSupervisionPlans(List selectedSupervisionPlans) {
		this.selectedSupervisionPlans = selectedSupervisionPlans;
	}

	public String[] getSelectedAssessmentIds() {
		return selectedAssessmentIds;
	}

	public void setSelectedAssessmentIds(String[] selectedAssessmentIds) {
		this.selectedAssessmentIds = selectedAssessmentIds;
	}

	public String[] getSelectedAssociateIds() {
		return selectedAssociateIds;
	}

	public void setSelectedAssociateIds(String[] selectedAssociateIds) {
		this.selectedAssociateIds = selectedAssociateIds;
	}

	public String[] getSelectedLOSIds() {
		return selectedLOSIds;
	}

	public void setSelectedLOSIds(String[] selectedLOSIds) {
		this.selectedLOSIds = selectedLOSIds;
	}

	public String[] getSelectedSupervisionPlanIds() {
		return selectedSupervisionPlanIds;
	}

	public void setSelectedSupervisionPlanIds(String[] selectedSupervisionPlanIds) {
		this.selectedSupervisionPlanIds = selectedSupervisionPlanIds;
	}

	public String getShowBackButton() {
		return showBackButton;
	}

	public void setShowBackButton(String showBackButton) {
		this.showBackButton = showBackButton;
	}

}