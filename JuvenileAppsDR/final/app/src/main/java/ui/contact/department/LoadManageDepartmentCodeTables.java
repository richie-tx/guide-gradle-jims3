/*
 * Created on August 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact.department;

import java.util.Collection;

import ui.common.CodeHelper;
import ui.contact.department.form.DepartmentForm;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadManageDepartmentCodeTables
{
	static private LoadManageDepartmentCodeTables instance = null;
    static private Collection accessTypes;
    static private Collection departmentStatus;
    static private Collection states;
	static private Collection streetTypes;
	static private Collection setcisAccesses;
	static private Collection counties;
	
	/**
	 * <p>name: getInstance description: Other classes call this method to get the LoadManageDepartmentCodeTables
	 * @return Instance
	 */
	public synchronized static LoadManageDepartmentCodeTables getInstance(){
	   if (instance == null) {
		  instance = new LoadManageDepartmentCodeTables();
	   }
	   return instance;
	}


	/** <p>name: LoadManageDepartmentCodeTables description: constructor 
	 */
	private LoadManageDepartmentCodeTables(){
	   this.load();
	}

	/** 
	 * @description: loads the codeTables
	 */
	private void load(){
		accessTypes =  CodeHelper.getAgencyAccessTypeCodes();
		departmentStatus = CodeHelper.getAgencyStatusCodes();
		states = CodeHelper.getStateCodes();
		streetTypes = CodeHelper.getStreetTypeCodes();
		counties = CodeHelper.getCountyCodes();
		setcisAccesses = CodeHelper.getSetcicAccessCodes();
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param departmentForm 
	 */
	public void setDepartmentForm(DepartmentForm departmentForm){
		departmentForm.setAccessTypes(accessTypes);
		departmentForm.setStatusTypes(departmentStatus);
		departmentForm.setCountyList(counties); 
		departmentForm.setStreetTypes(streetTypes); 
		departmentForm.setStateList(states);
		departmentForm.setSetcicAccessTypes(setcisAccesses);
	}
}
