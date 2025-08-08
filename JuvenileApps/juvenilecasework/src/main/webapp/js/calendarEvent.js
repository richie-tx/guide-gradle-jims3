/**
 *  //added for #36737
 */

$(document).ready(function () {
	var tentativeRefFlag = $("#tentativeRefFlag").val();
	if(tentativeRefFlag=="true"){
		alert("One or more scheduled juveniles have a TENTATIVE REFERRED program referral.\nUpdate the program referral status to ACCEPTED to document attendance.");
	}
	
	var attendanceIds=$('input[id=attendance_id]');
	var referralListAttendanceIds = $('input[id=referralLstAttendanceId]');
	$.each(referralListAttendanceIds,function(idx,element){
	 $.each(attendanceIds,function(index){ 
		 var attendanceId=$(this).val();
		 if(attendanceId==$(element).val())
		 {
			$("#" + attendanceId).find("#attended,#absent,#progressNotes,#addAttendees,addlAttendees,a").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
		 }
	 });
	});
});