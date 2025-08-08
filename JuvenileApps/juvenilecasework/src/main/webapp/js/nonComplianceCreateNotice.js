$(document).ready(function () {
	
	if(typeof $('#nonComplianceDateStrId') != 'undefined')
		datePickerSingle( $("#nonComplianceDateStrId"), "Non Compliance Date", true);	
	
	if(typeof $("#sanctionAssignedDateStrId") != "undefined")
		datePickerSingle( $("#sanctionAssignedDateStrId"), "Sanction Assigned Date", true);			
	
	if(typeof $("#sanctionCompleteByDateStrId") != "undefined")
		datePickerSingle( $("#sanctionCompleteByDateStrId"), "Sanction Complete By Date", false);
	
	if(typeof $("#completionDateId") != "undefined")
		datePickerSingle( $("#completionDateId"), "Completion Date", false);
	
	if(typeof $("#signatureSignedDateStrId") != "undefined")
		datePickerSingle( $("#signatureSignedDateStrId"), "Signature Signed Date", false);
		
});