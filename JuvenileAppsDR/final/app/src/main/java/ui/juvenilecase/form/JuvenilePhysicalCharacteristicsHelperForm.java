package ui.juvenilecase.form;

public class JuvenilePhysicalCharacteristicsHelperForm {
	private String code;
	private String descriptions;
	private boolean selectedTattoos = false;
	private boolean selectedScars = false;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public boolean isSelectedTattoos() {
		return selectedTattoos;
	}
	public void setSelectedTattoos(boolean selectedTattoos) {
		this.selectedTattoos = selectedTattoos;
	}
	public boolean isSelectedScars() {
		return selectedScars;
	}
	public void setSelectedScars(boolean selectedScars) {
		this.selectedScars = selectedScars;
	}


}
