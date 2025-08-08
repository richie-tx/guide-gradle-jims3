/*
 * Created on Oct 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.commonfunctionality.spnconsolidation.form;

import java.util.Date;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import ui.common.CodeHelper;

/**
 * @author ryoung
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SpnConsolidationForm extends ActionForm {

	private String action = "";

	private String aliasSpn;

	private String baseSpn;

	private Date fromDateOfBirth;

	private String fromJailInd;

	private String fromJailIndicator;

	private String fromPartyName;

	private String fromRace;

	private String fromRaceId;

	private String fromSex;

	private String fromSexId;

	private Date toDateOfBirth;

	private String toJailInd;

	private String toJailIndicator;

	private String toPartyName;

	private String toRace;

	private String toRaceId;

	private String toSex;

	private String toSexId;

	/**
	 * @return Returns the aliasSpn.
	 */
	public String getAliasSpn() {
		return aliasSpn;
	}

	/**
	 * @param aliasSpn
	 *            The aliasSpn to set.
	 */
	public void setAliasSpn(String aliasSpn) {
		this.aliasSpn = aliasSpn;
	}

	/**
	 * @return Returns the baseSpn.
	 */
	public String getBaseSpn() {
		return baseSpn;
	}

	/**
	 * @param baseSpn
	 *            The baseSpn to set.
	 */
	public void setBaseSpn(String baseSpn) {
		this.baseSpn = baseSpn;
	}

	public void clear() {
		// Never clear the action
		// action = "";
		aliasSpn = "";
		baseSpn = "";
		fromDateOfBirth = null;
		fromJailInd = "";
		fromJailIndicator = "";
		fromPartyName = "";
		fromRace = "";
		fromRaceId = "";
		fromSex = "";
		fromSexId = "";
		toDateOfBirth = null;
		toJailInd = "";
		toJailIndicator = "";
		toPartyName = "";
		toRace = "";
		toRaceId = "";
		toSex = "";
		toSexId = "";
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @return Returns the fromJailIndicator.
	 */
	public String getFromJailIndicator() {
		return fromJailIndicator;
	}

	/**
	 * @param fromJailIndicator
	 *            The fromJailIndicator to set.
	 */
	public void setFromJailIndicator(String fromJailIndicator) {
		this.fromJailIndicator = fromJailIndicator;
	}

	/**
	 * @return Returns the fromPartyName.
	 */
	public String getFromPartyName() {
		return fromPartyName;
	}

	/**
	 * @param fromPartyName
	 *            The fromPartyName to set.
	 */
	public void setFromPartyName(String fromPartyName) {
		this.fromPartyName = fromPartyName;
	}

	/**
	 * @return Returns the fromRaceId.
	 */
	public String getFromRaceId() {
		return fromRaceId;
	}

	/**
	 * @param fromRace
	 *            The fromRaceId to set.
	 */
	public void setFromRaceId(String fromRaceId) {
		this.fromRaceId = fromRaceId;
		if (fromRaceId != null && !fromRaceId.equals("")) {
			this.fromRace = CodeHelper.getCodeDescriptionByCode(CodeHelper.getRaces(), fromRaceId);

		} else {
			this.fromRace = "";
		}
	}

	/**
	 * @return
	 */
	public String getFromRace() {
		return fromRace;
	}

	/**
	 * @return Returns the fromSexId.
	 */
	public String getFromSexId() {
		return fromSexId;
	}

	/**
	 * @return
	 */
	public String getFromSex() {
		return fromSex;
	}

	/**
	 * @param fromSex
	 *            The fromSexId to set.
	 */
	public void setFromSexId(String fromSexId) {
		this.fromSexId = fromSexId;
		if (fromSexId != null && !fromSexId.equals("")) {
			this.fromSex = CodeHelper.getCodeDescriptionByCode(CodeHelper.getSexCodes(), fromSexId);

		} else {
			this.fromSex = "";
		}
	}

	/**
	 * @return Returns the toJailIndicator.
	 */
	public String getToJailIndicator() {
		return toJailIndicator;
	}

	/**
	 * @param toJailIndicator
	 *            The toJailIndicator to set.
	 */
	public void setToJailIndicator(String toJailIndicator) {
		this.toJailIndicator = toJailIndicator;
	}

	/**
	 * @return Returns the toPartyName.
	 */
	public String getToPartyName() {
		return toPartyName;
	}

	/**
	 * @param toPartyName
	 *            The toPartyName to set.
	 */
	public void setToPartyName(String toPartyName) {
		this.toPartyName = toPartyName;
	}

	/**
	 * @return Returns the toRaceId.
	 */
	public String getToRaceId() {
		return toRaceId;
	}

	/**
	 * @return
	 */
	public String getToRace() {
		return toRace;
	}

	/**
	 * @param toRace
	 *            The toRaceId to set.
	 */
	public void setToRaceId(String toRaceId) {
		this.toRaceId = toRaceId;
		if (toRaceId != null && !toRaceId.equals("")) {
			this.toRace = CodeHelper.getCodeDescriptionByCode(CodeHelper.getRaces(), toRaceId);

		} else {
			this.fromRace = "";
		}
	}

	/**
	 * @return Returns the toSexId.
	 */
	public String getToSexId() {
		return toSexId;
	}

	/**
	 * @return
	 */
	public String getSex() {
		return toSex;
	}

	/**
	 * @param toSex
	 *            The toSexId to set.
	 */
	public void setToSexId(String toSexId) {
		this.toSexId = toSexId;
		if (toSexId != null && !toSexId.equals("")) {
			this.toSex = CodeHelper.getCodeDescriptionByCode(CodeHelper.getSexCodes(), toSexId);

		} else {
			this.toSex = "";
		}
	}

	/**
	 * @return Returns the fromDateOfBirth.
	 */
	public Date getFromDateOfBirth() {
		return fromDateOfBirth;
	}

	public String getFromDateOfBirthAsString() {
		if (fromDateOfBirth == null) {
			return "";
		} else {
			return DateUtil.dateToString(fromDateOfBirth, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param fromDateOfBirth
	 *            The fromDateOfBirth to set.
	 */
	public void setFromDateOfBirth(Date date) {
		fromDateOfBirth = date;
	}

	/**
	 * @return Returns the toDateOfBirth.
	 */
	public Date getToDateOfBirth() {
		return toDateOfBirth;
	}

	public String getToDateOfBirthAsString() {
		if (toDateOfBirth == null) {
			return "";
		} else {
			return DateUtil.dateToString(toDateOfBirth, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param toDateOfBirth
	 *            The toDateOfBirth to set.
	 */
	public void setToDateOfBirth(Date date) {
		toDateOfBirth = date;
	}

	/**
	 * @return Returns the toSex.
	 */
	public String getToSex() {
		return toSex;
	}

	/**
	 * @param toSex
	 *            The toSex to set.
	 */
	public void setToSex(String toSex) {
		this.toSex = toSex;
	}

	/**
	 * @param fromRace
	 *            The fromRace to set.
	 */
	public void setFromRace(String fromRace) {
		this.fromRace = fromRace;
	}

	/**
	 * @param fromSex
	 *            The fromSex to set.
	 */
	public void setFromSex(String fromSex) {
		this.fromSex = fromSex;
	}

	/**
	 * @param toRace
	 *            The toRace to set.
	 */
	public void setToRace(String toRace) {
		this.toRace = toRace;
	}

	/**
	 * @return Returns the toJailInd.
	 */
	public String getToJailInd() {
		return toJailInd;
	}

	/**
	 * @param toJailInd
	 *  The toJailInd to set.
	 */
	public void setToJailInd(String toJailInd) {
		this.toJailInd = toJailInd;

		if (toJailInd.equals("N")) {
			this.toJailIndicator = "NOT IN JAIL";

		} else if (toJailInd.equals("Y")) {
			this.toJailIndicator = "IN JAIL";

		} else if (toJailInd.equals("H")) {
			this.toJailIndicator = "PREVIOUSLY IN JAIL";
			
		} else if (toJailInd.equals("R")) {
			this.toJailIndicator = "RECEIVED";
			
		} else if (toJailInd.equals("I")) {
			this.toJailIndicator = "IN TRANSIT";

		} else {
			this.toJailIndicator = "";
		}
	}

	/**
	 * @return Returns the fromJailInd.
	 */
	public String getFromJailInd() {
		return fromJailInd;
	}

	/**
	 * @param fromJailInd
	 *  The fromJailInd to set.
	 */
	public void setFromJailInd(String fromJailInd) {
		this.fromJailInd = fromJailInd;
		
		if (toJailInd.equals("N")) {
			this.toJailIndicator = "NOT IN JAIL";

		} else if (toJailInd.equals("Y")) {
			this.toJailIndicator = "IN JAIL";

		} else if (toJailInd.equals("H")) {
			this.toJailIndicator = "PREVIOUSLY IN JAIL";
			
		} else if (toJailInd.equals("R")) {
			this.toJailIndicator = "RECEIVED";
			
		} else if (toJailInd.equals("I")) {
			this.toJailIndicator = "IN TRANSIT";

		} else {
			this.toJailIndicator = "";
		}

	}
}
