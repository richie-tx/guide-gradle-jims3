/*
 * Created on August 15, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security;

import java.util.Collection;

import ui.common.CodeHelper;
import ui.security.form.RoleForm;
import ui.security.form.RoleSAForm;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadSecurityCodeTables
{
	static private LoadSecurityCodeTables instance = null;
    static private Collection agencyTypes;
	static private Collection jmcReps;
	static private Collection jims2Subsystems;
	static private Collection secirityInquires;
	static private Collection passwordQuestions;
	
	/**
	 * <p>name: getInstance description: Other classes call this method to get the LoadSecurityCodeTables
	 * @return Instance
	 */
	public synchronized static LoadSecurityCodeTables getInstance(){
	   if (instance == null) {
		  instance = new LoadSecurityCodeTables();
	   }
	   return instance;
	}


	/** <p>name: LoadSecurityCodeTables description: constructor 
	 */
	private LoadSecurityCodeTables(){
	   this.load();
	}

	/** 
	 * @description: loads the codeTables
	 */
	private void load(){
		agencyTypes =  CodeHelper.getAgencyTypeCodes();
		jmcReps = null;
		jims2Subsystems = CodeHelper.getJIMS2Subsystems();
		secirityInquires = CodeHelper.getSecurityInquires();
		passwordQuestions = CodeHelper.getPasswordQuestionCodes();
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param RoleForm 
	 */
	public void setRoleForm(RoleForm roleForm){
		roleForm.setAgencyTypes(agencyTypes);
		roleForm.setJmcReps(jmcReps);
		roleForm.setJims2Subsystems(jims2Subsystems);
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param roleSAForm 
	 */
	public void setRoleSAForm(RoleSAForm roleSAForm){
		roleSAForm.setJims2Subsystems(jims2Subsystems);
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param securityInquiriesForm 
	 */
	public void setSecurityInquiriesForm(ui.security.inquiries.form.SecurityInquiriesForm securityInquiriesForm){
		securityInquiriesForm.setSecurityInquires(secirityInquires);
		securityInquiriesForm.setJims2Subsystems(jims2Subsystems);
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param securityInquiriesForm 
	 */
	public void setJIMS2AccountForm(ui.security.authentication.jims2Account.form.JIMS2AccountForm jims2AccountForm){
		jims2AccountForm.setPasswordQuestions(passwordQuestions);

	}
}
