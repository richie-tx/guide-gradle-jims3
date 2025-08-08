/*
 * Created on August 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact;

import java.util.Collection;

import ui.common.CodeHelper;
import ui.contact.officer.form.OfficerForm;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadManageOfficerCodeTables
{
	static private LoadManageOfficerCodeTables instance = null;
    static private Collection officerTypes;
	static private Collection juvLocations;
	static private Collection juvUnits;
	static private Collection ranks;
	static private Collection states;
	static private Collection streetTypes;
	static private Collection workDays;
	static private Collection officerSubTypes;
	static private Collection officerStatuses;
	
	/**
	 * <p>name: getInstance description: Other classes call this method to get the LoadManageOfficerCodeTables
	 * @return Instance
	 */
	public synchronized static LoadManageOfficerCodeTables getInstance(){
	   if (instance == null) {
		  instance = new LoadManageOfficerCodeTables();
	   }
	   return instance;
	}


	/** <p>name: LoadManageOfficerCodeTables description: constructor 
	 */
	private LoadManageOfficerCodeTables(){
	   this.load();
	}

	/** 
	 * @description: loads the codeTables
	 */
	private void load(){
		officerTypes =  CodeHelper.getOfficerTypeCodes();
		juvLocations = CodeHelper.getLocationCodes();
		juvUnits= UIContactHelper.getJuvUnitCodes();
		ranks = CodeHelper.getRankCodes();
		streetTypes = CodeHelper.getStreetTypeCodes();
		states = CodeHelper.getStateCodes();
		workDays = CodeHelper.getWorkDayCodes();
		officerSubTypes = CodeHelper.getOfficerSubTypes();
		officerStatuses = CodeHelper.getOfficerStatuses();
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param OfficerForm 
	 */
	public void setOfficerForm(OfficerForm officerForm){
		officerForm.setOfficerTypes(officerTypes);
		officerForm.setJuvLocations(juvLocations);
		officerForm.setJuvUnits(juvUnits); 
		officerForm.setRanks(ranks); 
		officerForm.setStreetTypes(streetTypes); 
		officerForm.setStates(states);
		officerForm.setWorkDays(workDays); 
		officerForm.setOfficerSubTypes(officerSubTypes);
		officerForm.setOfficerStatuses(officerStatuses);
	}
}
