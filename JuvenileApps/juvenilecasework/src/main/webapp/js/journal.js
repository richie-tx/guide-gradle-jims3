// **** NON-JQUERY FUNCTIONS



// **** JQUERY FUNCTIONS
//Text limit validations.
function textLimit(field, maxlen) {
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
		alert("Your input has been truncated to " + maxlen +" characters!");	
	}
}


// on Page Dom load load up any events
$(document).ready(function () {
 
	//From and end DATE CALENDAR.
	if(typeof  $("#fromDate") != "undefined" && typeof  $("#endDate") != "undefined"){
		datePickerRange($("#fromDate"),$("#endDate"));
	}
	
	
	setInterval(function(){ 
    //code goes here that will be run every 2 seconds.   
		// get String and check length	
		var fromDateString = $("#fromDate").val();
		var endDateString = $("#endDate").val();
		if(fromDateString.length == 10 && endDateString.length == 0){
			// get timestamp from string
			var fromDateTime = getDateFromFormat(fromDateString,'MM/dd/yyyy');
			// convert timestamp to Date
			var fromDateDate = new Date(fromDateTime);
			// Add 30 days
			fromDateDate.setDate(fromDateDate.getDate() + 30); 
			// convert back to formatted date string
			var changedDate = formatDate(fromDateDate,'MM/dd/yyyy');
			// set EndDate field to new value
			if(changedDate != null && changedDate != '01/30/1970'){ // value if date input invalid
				$('#endDate').val(changedDate);
			}
		}
	}, 2000);
	
});

	