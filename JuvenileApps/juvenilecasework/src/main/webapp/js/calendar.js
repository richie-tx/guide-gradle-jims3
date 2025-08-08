$(document).ready(function () {
	//startDate DATE CALENDAR.
	if(typeof $("#startDateStr") != "undefined" && typeof $("#endDateStr") != "undefined"){
		datePickerRange($("#startDateStr"),$("#endDateStr"),"Start Date","End Date");
	}
});