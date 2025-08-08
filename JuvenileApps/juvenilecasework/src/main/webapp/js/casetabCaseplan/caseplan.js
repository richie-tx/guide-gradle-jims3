//functionality used by the caseplan tab
$(document).ready(function(){
	
	if(typeof $("#childDtNotified") != "undefined")
		datePickerSingle( $("#childDtNotified"),  false);	
	
	if(typeof $("#familyDtNotified") != "undefined")
		datePickerSingle( $("#familyDtNotified"), false);	
	
	if(typeof $("#caregiverDtNotified") != "undefined")
		datePickerSingle( $("#caregiverDtNotified"), false);	
	
	if(typeof $("#otherDtNotified") != "undefined")
		datePickerSingle( $("#otherDtNotified"));	
	
	if(typeof $("#childDtOfParticipation") != "undefined")
		datePickerSingle( $("#childDtOfParticipation"));	
	
	if(typeof $("#familyDtOfParticipation") != "undefined")
		datePickerSingle( $("#familyDtOfParticipation"));	
	
	if(typeof $("#caregiverDtOfParticipation") != "undefined")
		datePickerSingle( $("#caregiverDtOfParticipation"));	
	
	if(typeof $("#otherNameDtOfParticipation") != "undefined")
		datePickerSingle( $("#otherNameDtOfParticipation"));	
	
	if(typeof $("#childMailedDt") != "undefined")
		datePickerSingle( $("#childMailedDt"));	
	
	if(typeof $("#familyMailedDt") != "undefined")
		datePickerSingle( $("#familyMailedDt"),  false);	
	
	if(typeof $("#caregiverMailedDt") != "undefined")
		datePickerSingle( $("#caregiverMailedDt"),  false);	
	
	
	if(typeof $("#otherNameMailedDt") != "undefined")
		datePickerSingle( $("#otherNameMailedDt"),  false);	
	
	if(typeof $("#socialHistDated") != "undefined")
		datePickerSingle( $("#socialHistDated"),  false);	
	
	if(typeof $("#psychologicalRepDated") != "undefined")
		datePickerSingle( $("#psychologicalRepDated"),  false);	
	
	if(typeof $("#riskAssesmentDated") != "undefined")
		datePickerSingle( $("#riskAssesmentDated"),  false);	
	
	if(typeof $("#otherDated") != "undefined")
		datePickerSingle( $("#otherDated"),  false);	
		
	if(typeof $("#dtDeterminationMade") != "undefined")
		datePickerSingle( $("#dtDeterminationMade"),  false);	
	
	if(typeof $("#entryDate1") != "undefined")
		datePickerSingle($("#entryDate1") ,  false);
	
	if(typeof $("#entryDate2") != "undefined")
		datePickerSingle($("#entryDate2"),  false);
});

