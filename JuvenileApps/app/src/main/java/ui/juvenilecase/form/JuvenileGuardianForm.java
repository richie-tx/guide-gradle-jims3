/*
 * Created on Jul 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JuvenileGuardianForm extends ActionForm {

	private Collection guardianList = new ArrayList();

	/**
	 * @return Returns the guardianList.
	 */
	public Collection getGuardianList() {
		return guardianList;
	}

	/**
	 * @param guardianList
	 *            The guardianList to set.
	 */
	public void setGuardianList(Collection guardianList) {
		this.guardianList = guardianList;
	}
}
