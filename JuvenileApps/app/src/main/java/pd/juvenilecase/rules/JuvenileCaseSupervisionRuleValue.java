package pd.juvenilecase.rules;

import pd.juvenilecase.rules.*;
import pd.supervision.supervisionoptions.VariableElement;
import mojo.km.persistence.PersistentObject;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class JuvenileCaseSupervisionRuleValue extends PersistentObject {
	private String value;
	/**
	* Properties for rule
	* @referencedType pd.juvenilecase.JuvenileCaseSupervisionRule
	* @detailerDoNotGenerate false
	*/
	private JuvenileCaseSupervisionRule rule = null;
	private String juvenileCaseSupervisionRuleId;
	private String variableElementId;
	/**
	* Properties for variableElement
	* @referencedType pd.supervision.supervisionoptions.VariableElement
	* @detailerDoNotGenerate false
	*/
	private VariableElement variableElement = null;
	/**
	* @return 
	*/
	public String getValue() {
		fetch();
		return value;
	}
	/**
	* @param string
	*/
	public void setValue(String string) {
		if (this.value == null || !this.value.equals(string)) {
			markModified();
		}
		value = string;
	}

	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.VariableElement
	*/
	public void setVariableElementId(String variableElementId) {
		if (this.variableElementId == null || !this.variableElementId.equals(variableElementId)) {
			markModified();
		}
		variableElement = null;
		this.variableElementId = variableElementId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.VariableElement
	*/
	public String getVariableElementId() {
		fetch();
		return variableElementId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.VariableElement
	*/
	private void initVariableElement() {
		if (variableElement == null) {
			variableElement =
				(VariableElement) new mojo
					.km
					.persistence
					.Reference(variableElementId, VariableElement.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.VariableElement
	*/
	public VariableElement getVariableElement() {
		initVariableElement();
		return variableElement;
	}
	/**
	* set the type reference for class member variableElement
	*/
	public void setVariableElement(VariableElement variableElement) {
		if (this.variableElement == null || !this.variableElement.equals(variableElement)) {
			markModified();
		}
		if (variableElement.getOID() == null) {
			new mojo.km.persistence.Home().bind(variableElement);
		}
		setVariableElementId("" + variableElement.getOID());
		this.variableElement = (VariableElement) new mojo.km.persistence.Reference(variableElement).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileCaseSupervisionRule
	*/
	public void setJuvenileCaseSupervisionRuleId(String juvenileCaseSupervisionRuleId) {
		this.juvenileCaseSupervisionRuleId = juvenileCaseSupervisionRuleId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileCaseSupervisionRule
	*/
	public String getJuvenileCaseSupervisionRuleId() {
		return juvenileCaseSupervisionRuleId;
	}
}
