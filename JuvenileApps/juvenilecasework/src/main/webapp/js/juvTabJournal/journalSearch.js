$(document).ready(function () {
	
    	//From and end DATE CALENDAR.
		if(typeof  $("#fromDate") != "undefined" && typeof  $("toDate") != "undefined"){
			datePickerRange($("#fromDate"),$("#toDate"));
		}
	
	
	$("#searchBtn").on("click",function(e){
		
		e.preventDefault();
		var beginDate = $("#fromDate").val();
		var endDate = $("#toDate").val();
		
		var checkedValues = $('input[type="checkbox"]:checked').map(function() {
		  return this.value;
		}).get();          
		           
		 if( checkedValues.length == 0 ){
			
			alert("Please select atleast 1 casefile to search!");
			return false;
		 }
		 
		 if(checkedValues.length > 5 ){
		 		
		 		alert("Please select max of 5 casefiles to search!");
				return false;
		  }            
		
		$("#selectedCasefileIds").val(checkedValues); //set it in the form	
		var category = $('#journalCategory:selected').text();
		$("#selectedCategory").val(category);
		$("#fromDate").val(beginDate);
		$("#toDate").val(endDate);
				
		//alert(" you clicked the submit button" + beginDate);
		spinner();		
		$('form').attr('action',"/JuvenileCasework/displayJuvenileJournalList.do?submitAction=Link");
		$('form').submit();
	});

	
});