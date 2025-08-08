$(document).ready(function(){
	if(typeof $("#newDate") != "undefined"){
		datePickerSingle($("#newDate"),"Date",false);
		
		$("#updateRecord").click(function(){
			$("#newAssignmentDate").val( $("#newDate").val() );
			spinner();
			$("#updateMaysiAssessForm").submit();
		})
	}
})