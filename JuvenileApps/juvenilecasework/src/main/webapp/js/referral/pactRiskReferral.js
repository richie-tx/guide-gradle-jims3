/**
 * PACT Referral JQuery
 */
$(document).ready(function () {
	if($("#pdaReadOnly").val()=="true")
		document.getElementById('lastPactDate').disabled=true;
	else
		document.getElementById('lastPactDate').disabled=false;
	if($("#pactReadOnly").val()=="true")
		//document.getElementById("riskNeeds").disabled=true;
	 $('#riskNeeds').find('input, select').prop('disabled',true);
	else
		 document.getElementById("riskNeeds").disabled=false;
		 //$('#riskNeeds').find('input, select').prop('disabled',false);
	//On click of the submit button
	/* $("#nextBtn").on('click', function (e) {		 
		 var selectedReferral = $('input[name=selectedReferral]:checked').val();
			//alert(selectedReferral);
			$("#tempRefNum").val(selectedReferral);
	 });*/

});