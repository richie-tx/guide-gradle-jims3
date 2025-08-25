function validateDate(getinput){

	var input = getinput;
    var index1 = input.indexOf("-");
	var index2 = input.indexOf("-", index1+1);
    var day = input.substring(0,index1);
    var month = input.substring(index1+1, index2);
    var year = input.substring(index2+1);
	var numMonth = 0;
	var numYear = 0;
	var monthBusted = false;
	var yearBusted = false;
	var dayBusted = false;
	
	//for debugging, yo.
	//alert('The day is '+day+'.  The month is '+month+'.  The year is '+year+'.');
	
	//check on year
	if ((year.match(/\D/)) || (year.length != 4)){
		yearBusted = true;		
	} else {
		numYear=year;
	}
		
	//check on month
	if(checkMonth(month) == -1){		
		monthBusted = true;				
	} else {
		numMonth = checkMonth(month);
	}

	//check on day
	if ((day.match(/\D/)) || (day.length > 2) || (day > lastDay(numMonth, numYear)) || (day < 1)){
		dayBusted = true;
	}
	
	//check results and return
	if ((dayBusted) || (yearBusted) || (monthBusted)){
		return true;
	} else {
		return false;
	}

}

function checkMonth(month){
	var monthBusted = true;
	var numMonth;
		
	//allow lowercase first letters, but capitalize before giving to PD.
	if ((month == "Jan") || (month=="jan")){
		if (month=="jan") month = "Jan";
		monthBusted = false;
		numMonth = 1;
	} else if ((month == "Feb") || (month=="feb")){
		if (month=="feb") month = "Feb";
		monthBusted = false;
		numMonth = 2;
	} else if ((month == "Mar") || (month=="mar")){
		if (month=="mar") month = "Mar";
		monthBusted = false;
		numMonth = 3;
	} else if ((month == "Apr") || (month=="apr")){
		if (month=="apr") month = "Apr";
		monthBusted = false;
		numMonth = 4;
	} else if ((month == "May") || (month=="may")){
		if (month=="may") month = "May";
		monthBusted = false;
		numMonth = 5;
	} else if ((month == "Jun") || (month=="jun")){
		if (month=="jun") month = "Jun";
		monthBusted = false;
		numMonth = 6;
	} else if ((month == "Jul") || (month=="jul")){
		if (month=="jul") month = "Jul";
		monthBusted = false;
		numMonth = 7;
	} else if ((month == "Aug") || (month=="aug")){
		if (month=="aug") month = "Aug";
		monthBusted = false;
		numMonth = 8;
	} else if ((month == "Sep") || (month=="sep")){
		if (month=="sep") month = "Sep";
		monthBusted = false;
		numMonth = 9;
	} else if ((month == "Oct") || (month=="oct")){
		if (month=="oct") month = "Oct";
		monthBusted = false;
		numMonth = 10;
	} else if ((month == "Nov") || (month=="nov")){
		if (month=="nov") month = "Nov";
		monthBusted = false;
		numMonth = 11;
	} else if ((month == "Dec") || (month=="dec")){
		if (month=="dec") month = "Dec";
		monthBusted = false;
		numMonth = 12;
	}	

	if(monthBusted == false){
		return numMonth
	} else {
		return -1
	}

}


function lastDay(aMon, aYear) {
        // 31 day months
        if ((aMon == 1) || (aMon == 3) || (aMon == 5) || (aMon == 7) || (aMon == 8) || (aMon == 10) || (aMon == 12))
            return 31;
        // 30 day months
        if ((aMon == 4) || (aMon == 6) || (aMon == 9) || (aMon == 11))
            return 30;
        // leap year situations for february
        if ((aYear % 400 == 0) || (aYear % 4 == 0 && aYear % 100 != 0))
            return 29;
        // non-leap year februaries
        return 28;
    }

function validateDateMsg(){
	var Msg = "You have entered an invalid or improperly formatted date. \n Please enter your date in the following format: \n\n 25-Dec-2002 \n\n You may also use the calendar button."
	return Msg;
}	
	
function todaysDate(){
	today = new Date();
	today.setHours(0);
	today.setMinutes(0);
	today.setSeconds(0);
	today.setMilliseconds(0);
	return today.valueOf();
}

function millDate(entered){
	var input = entered;
    var index1 = input.indexOf("-");
	var index2 = input.indexOf("-", index1+1);
    var day = input.substring(0,index1);
    var monthx = input.substring(index1+1, index2);
	var month = checkMonth(monthx) - 1;
    var year = input.substring(index2+1, index2+5);
	
	var millDateEntered = new Date(year, month, day);
	
	return millDateEntered.valueOf();
}	

