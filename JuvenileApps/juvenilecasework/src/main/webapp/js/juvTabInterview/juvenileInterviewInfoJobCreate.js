<!-- JavaScript - Job Salary Check -->

function validateJobFields(theForm){

	if ( theForm["salary"].value > "" &&  theForm["salary"].value != "0.0" && theForm["salaryRateId"].selectedIndex < 1) {
		alert("Salary Rate is required if Salary is entered.");
		theForm["salaryRateId"].focus();
		return false;
	}
	if (!( theForm["salaryRateId"].selectedIndex < 1) && !( theForm["salary"].value > "")) {
		alert("Salary is required if Salary Rate is entered.");
		theForm["salary"].focus();
		return false;
	}
	if ( theForm["salaryRateId"].value=="HR" &&  theForm["workHours"].value <="") {
		alert("Number of Hours Worked (per week) is required if Salary Rate is Hourly.");
		theForm["workHours"].focus();
		return false;
	}

	if ( theForm["workHours"].value!="" && theForm["workHours"].value != "0.0" ) {
		if ( theForm["salaryRateId"].value!="HR")
		{
			alert("Hours Worked per Week is required only if Salary Rate is Hourly.");
			theForm["salaryRateId"].focus();
			return false;
		}		
	}

	return validateJuvenileJobForm(theForm);
}


