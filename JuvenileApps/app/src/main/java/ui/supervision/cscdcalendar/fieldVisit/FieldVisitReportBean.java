package ui.supervision.cscdcalendar.fieldVisit;

import java.util.Collection;

public class FieldVisitReportBean {
	private Collection events = null;
	private String startMileage = null;
	private String endMileage = null;
	private String fieldVisitDate = "";
	private String cellPhone = "";

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFieldVisitDate() {
		return fieldVisitDate;
	}

	public void setFieldVisitDate(String fieldVisitDate) {
		this.fieldVisitDate = fieldVisitDate;
	}

	public Collection getEvents() {
		return events;
	}

	public void setEvents(Collection events) {
		this.events = events;
	}

	public String getStartMileage() {
		return startMileage;
	}

	public void setStartMileage(String startMileage) {
		this.startMileage = startMileage;
	}

	public String getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(String endMileage) {
		this.endMileage = endMileage;
	}

}
