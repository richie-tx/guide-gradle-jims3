/*
 * Created on August 15, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.administercontract;

import java.util.Collection;

import ui.common.CodeHelper;
import ui.security.form.RoleForm;
import ui.security.form.RoleSAForm;
import ui.supervision.administercontract.form.ContractForm;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadSupervisionCodeTables
{
	static private LoadSupervisionCodeTables instance = null;
    static private Collection contractTypes;
    static private Collection glAccountKeys;
	
	/**
	 * <p>name: getInstance description: Other classes call this method to get the LoadSecurityCodeTables
	 * @return Instance
	 */
	public synchronized static LoadSupervisionCodeTables getInstance(){
	   if (instance == null) {
		  instance = new LoadSupervisionCodeTables();
	   }
	   return instance;
	}


	/** <p>name: LoadSecurityCodeTables description: constructor 
	 */
	private LoadSupervisionCodeTables(){
	   this.load();
	}

	/** 
	 * @description: loads the codeTables
	 */
	private void load(){
		contractTypes =  CodeHelper.getContractTypeCodes();
		glAccountKeys = CodeHelper.getGlAccountKeys();
	}
	
	/** 
	 * @description: loads the codeTables
	 * @param RoleForm 
	 */
	public void setContractForm(ContractForm contractForm){
		contractForm.setContractTypes(contractTypes);
		contractForm.setGlAccountKeys(glAccountKeys);
	}
	
	public static void main(String[] args){
		LoadSupervisionCodeTables a = LoadSupervisionCodeTables.getInstance();
		a.load();		
	}
}
