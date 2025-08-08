//used with Facility History Tab under Juv Profile
//used for the seperate view history link as well
$(document).ready(function(){
	//viewFacilityHistory.jsp //added for #51734
	$("#juvenileNum").focus();
	$("#submitBtnFacilityHistory").on("click",function(){
		if($("#juvenileNum").val()==""){
			alert("Juvenile Number is Required.");
			$("#juvenileNum").focus();
			return false;
		}
		if($("#juvenileNum").val().trim().length<6){
			alert("Enter a valid Juvenile Number.");
			$("#juvenileNum").focus();
			return false;
		}
		
		if ( true ) {
			spinner();
		}
	});
	//viewFacilityHistory.jsp //added for #51734
	
	//facilityHistoryList.jsp
	$("#facHistListBack").on('click',function(){
		goNav('back');
	});
	$("#facHistListCancel").on('click',function(){
		goNav($(this).data('location'));
	});
	
	
});