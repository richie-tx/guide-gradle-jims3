$(document).ready(function(){
	$("#oldRefAssmntType").val( $("#refAssmntType").val() );
	$("#oldRefSeqNum").val( $("#refSeqNum").val() );
	$("#oldAssignmentDate").val( $("#assmntDate").val() );
	
	if(typeof $("#newDate") != "undefined"){
		datePickerSingle($("#newDate"),"Date",false);
	}
	
	$("#resetForm").click(function(){
		$("#newDate").val("");
		$("#assmntType").val("");
		$("#seqNum").val("");
	})
	
	function validateInputs(){
		if ($("#assmntType").val() === ""){
			alert("Assignment Type is required.      ");
			return false;
		}
		if ($("#seqNum").val() === ""){
			alert("Referral Sequence Number is required.        ");
			return false;
		} else {
			if ( isNaN( $("#seqNum").val() ) ) {
				alert("Referral Sequence Number is not valid. Please input a valid Referral Sequence Number.");
				return false;
			} 
		}
		if(true)
			spinner();
		return true;
	}
	
	
	$("#updateRecord").click(function(){
		if ( validateInputs() ) {
			$("#newAssignmentDate") .val( $("#newDate").val() );
			$("#newAssignmentType").val( $("#assmntType").val() );
			$("#newSeqNum").val( $("#seqNum").val() );
			$('#updateAssignmentForm').submit();
		} 
	})
	
	
});

