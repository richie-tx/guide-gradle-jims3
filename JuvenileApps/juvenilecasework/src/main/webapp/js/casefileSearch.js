/**
 * Javascript for IE11 changes.
 */

function SearchValidator(thisForm){
	   var focusSet = false;
	   var msg = ""; 
	
	   return true;
}
	
function Trim( s )
{
		// Remove leading spaces and carriage returns
	while( (s.substring( 0,1 ) == ' ') || (s.substring( 0,1 ) == '\n') || (s.substring( 0,1 ) == '\r') )
	{ 
		s = s.substring( 1, s.length ); 
	}
		
	// Remove trailing spaces and carriage returns
	while(( s.substring( s.length -1, s.length ) == ' ' ) || 
				(s.substring( s.length -1, s.length ) == '\n') ||
				(s.substring( s.length -1, s.length ) == '\r') )
	{ 
		s = s.substring( 0, s.length -1 ); 
	}
		
	return s;
}


//JQuery on Dom ONLOAD
$(document).ready(function() {
	
	//case status search
	$(document).on('click mouseover', '#fromExpDate',  function(){
		var x = $('#fromExpDate');
		var y = $('#toExpDate');
		datePickerRange(x,y);
	});
	//Task #50044 changes
	$(document).on('click mouseover', '#fromDispDate',  function(){
		var x = $('#fromDispDate');
		var y = $('#toDispDate');
		datePickerRange(x,y);
	});
	
	//probation officer search
	$(document).on('click mouseover', '#casefileActivationStDate',  function(){
		var x = $('#casefileActivationStDate');
		var y = $('#casefileActivationEndDate');
		datePickerRange(x,y);
	});
	
	//probation officer search
	$(document).on('click mouseover', '#caseloadExpectedEndDateFrom',  function(){
		var x = $('#caseloadExpectedEndDateFrom');
		var y = $('#caseloadExpectedEndDateTo');
		datePickerRange(x,y);
	});


	//From and To should be JQuery object.
	function datePickerRange( from,to ) 
	{
	      from.datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        to.datepicker( "option", "minDate", selectedDate );
	      }
	    });

	    to.datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        from.datepicker( "option", "maxDate", selectedDate );
	      }
	    });
	}

	// Task 50044 changes
	setInterval(function(){ 
	    //code goes here that will be run every 2 seconds.   
			// get String and check length	
			var fromDateString = $("#fromDispDate").val();
			var endDateString = $("#toDispDate").val();
			if(fromDateString!=null && endDateString!=null && fromDateString.length == 10 && endDateString.length == 0){
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
					$('#toDispDate').val(changedDate);
				}
			}
		}, 2000);

}); // jquery ends here