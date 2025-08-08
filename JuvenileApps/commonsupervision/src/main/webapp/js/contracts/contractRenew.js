/* script for contractRenew.jsp */
function checkForInput(theForm){
	var endDate  = Trim(theForm.endDate.value);
	var orgEndDate = theForm.originalEndDate.value;
	var startDate = Trim(theForm.startDate.value);
	theForm.endDate.value = endDate;
	if (endDate == "" ){
		alert("End Date is required.");
		theForm.endDate.focus();
		return false;
	}
	result = validateDate(endDate);
	if (result == false){
		alert("End Date is invalid date or wrong date format.");
		theForm.endDate.focus();
		return false;
	}	
    var endDateValues = endDate.split('/');
    var orgEndDateValues = orgEndDate.split('/');
//array elements 0=month 1=day 2=year	
    var endDateYMD = endDateValues[2] + endDateValues[0] + endDateValues[1];
    var orgEndDateYMD = orgEndDateValues[2] + orgEndDateValues[0] + orgEndDateValues[1];
	if (orgEndDateYMD > endDateYMD){
		alert("End date cannot be previous to original end date - " + orgEndDate + ".");
		theForm.endDate.focus();
		return false;
	}
	if (startDate > ""){
	    var startDateValues = startDate.split('/');
	    var endDateValues = endDate.split('/');
// array elements 0=month 1=day 2=year	
	    startDate = startDateValues[2] + startDateValues[0] + startDateValues[1];
	    endDate = endDateValues[2] + endDateValues[0] + endDateValues[1];
		if (startDate > endDate){
			alert("End Date cannot be previous to Start date.");
			theForm.endDate.focus();
			return false;			
		}
	}
	var now=new Date();
	var year="" + now.getYear();
	if(year.length < 3){
	   year = "20" + year;
	}
	var month= now.getMonth() + 1;
	month = "" + month;
	if(month.length < 2){
	   month = "0" + month;
	}
	var day = "" + now.getDate();
	if(day.length < 2){
	   day = "0" + day;
	}
	var formattedDate= year + month + day;
	
	if(parseInt(endDate) < parseInt(formattedDate)){
		alert("End Date cannot be previous to today.");
		theForm.endDate.focus();
		return false;		
	}
	return true;
}
	
function validateDate(fldValue){
	var regDate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
	var result = regDate.test(fldValue,regDate);
	if (result == false){
		return false;
	}  
	var dValues = fldValue.split('/');
	var mon = dValues[0];
	var day = dValues[1];
	var leapYear = 0;
	if (mon > 12 || mon == 0){
		return false;
	}
	if (day > 31 || day == 0){
		return false;
	}	
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11){
		if (day > 30){
			return false;
		}	
	}
	if (mon == 2){
		leapYear = dValues[2] %4;
		if (leapYear == 0 && day > 29){
			return false;
		}
		if (leapYear > 0  && day > 28){
			return false;
		}	
	}
	return true;
}