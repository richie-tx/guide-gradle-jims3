$(document).ready(function () {
  
	$("#addToCartBtn").hide();
	
	$("#openCreateBtn").on('click',function(){
		
		$("#trainingSpan").show();
		$("#openCreateBtn").hide();
		$("#addToCartBtn").show();
		
		if(typeof $("#beginDate") != "undefined"){
			datePickerSingle($("#beginDate"),"Date",false);
		}
		
		if(typeof $("#endDate") != "undefined"){
			datePickerSingle($("#endDate"),"Date",false);
		}
		
		
	});
	
	$("#addToCartBtn").on('click', function (e) {
	
		var fld1 = $("#topicId").val();
		var fld2 = $("#beginDate").val();
		var fld3 = $("#endDate").val();
		var fld4 = $("#trainingHrs").val();
		
		//alert(fld1 + "" + fld2 + ""+ fld3+ "" + fld4);
		$("#trainingTopicId").val(fld1);
		$("#begDate").val(fld2);
		$("#endingDate").val(fld3);
		$("#trainHours").val(fld4);
		
	  $('form').attr('action','/security.war/handleOfficerProfileSelection.do?action=AddTraining');
	  $('form').submit();
	});
	
});