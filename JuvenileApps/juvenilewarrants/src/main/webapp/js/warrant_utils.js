 $(document).ready(function() {
	 
	var selectedCheckBoxValue = false;
	
	 
	 $('input[type=radio][name=selectedAssociateAddressId]').change(function(evt) {
		 
		 var addressId = this.value;
		 console.log('selectedAssociateAddressId: ', addressId);
		 console.log('=============== end address Id ===========');
		 
		 selectedRadioBtn = evt.currentTarget;
		 //console.log(evt);
		 
		 $('input[name="selectedBadAddress"]').prop('checked', false);
	 
	 });
	 
	 $(":checkbox").change(function(evt) {
		 
		 var selectedCheckbox = evt.currentTarget;
		 
		 if(selectedCheckbox.checked){
			 
			 //console.log('checkbox name', selectedCheckbox.name);
			 //console.log('checked value', selectedCheckbox.value);
			 
			 selectedCheckBoxValue = selectedCheckbox.value;
			 
			 if(selectedCheckbox.name == 'selectedBadAddress'){
				 
				 $('#addressId').prop('checked', true);
				 
				 //alert('please enter the correct address below');
				 
				 $('#associateDropDown').focus();			 
				
			 } 
						 
			//mark the radio button as checked	 
			$('input[name="selectedAssociateAddressId"][value="' + selectedCheckbox.value + '"]').prop('checked', true);
			 
		 }
		 
		 if(!selectedCheckbox.checked){
			 
			 if(selectedCheckbox.name == 'selectedBadAddress'){
				 
				 $('#addressId').prop('checked', false);		 
				
			 } 
			 
		 }
	 
	 });
	 
	 
	 var associatesDropDown = $('#associateIdDropDown');  //document.getElementById("associateIdDropDown");
	 var associateIdCheckbox = document.getElementById("addressId");
	 
	 $('#associateIdDropDown').change(function() {
		 
		 var associateIdValue = $('#associateIdDropDown').val();
		 
		    if(associateIdValue === "1111111")
		    {
		    	document.getElementById("addressId").checked = true;
		    }
		    
		  //validate bad address checkbox
			 if(!isBadAddressChecked() && associateIdValue !== '1111111'){
				 
				 alert('A Bad Address checkbox must also be selected');
				 return false;;
			 }
			 
//			 if(isBadAddressChecked() && associateIdValue === '1111111'){
//				 
//				 alert('If associateId 1111111 is selected the bad address checkbox must not be selected');
//				 result = false;;
//			 }	
	 }); 

});
 
function isBadAddressChecked(){
	var badAddresschecked = false;
	
	var badAddressCheckboxes = document.getElementsByName("selectedBadAddress")
	
	 
	 for(var i=0; i < badAddressCheckboxes.length; i++) {
		 
		 if(badAddressCheckboxes[i].type == 'checkbox'){
			 
			 if(badAddressCheckboxes[i].checked){
				 
				 badAddresschecked = true;
				 break;
			 }
			 
		 }
		 
	 }
	
	return badAddresschecked;
	
}
 
 
function validateNewAddress(form){
	
	var result = true;
	
	var streetNum = $('#streetNum').val();
	var streetName =  $('#streetName').val();
	//var aptNum =  $('#aptNumber').val();
	var city =  $('#city').val();
	var state =  $('#state').val();
	var zipCode =  $('#zipCode').val();
	//var zipCode2 =  $('#zipCode2').val();
	var associateId = $('#associateIdDropDown').val();
	
	var checked = false;
	var isOtherRelative = false;
	 
	var badAddressCheckbox = document.getElementById("chkBadAddress");
	 var addressIdInputs = document.getElementsByName("addressId");
	 var selectedAssociateIdInputs = document.getElementsByName("selectedAssociateId");
	 
	 for(var i=0; i < addressIdInputs.length; i++) {
		 
		 if(addressIdInputs[i].type == 'checkbox'){
			 
			 var isBadAddressCheckedResult = isBadAddressChecked();
			 
			 
			 if(isBadAddressCheckedResult && addressIdInputs[i].name === 'addressId' && addressIdInputs[i].value === 'newAddress'){
				 
				 checked = true;
			 }	
			 
			 if(!isBadAddressCheckedResult && addressIdInputs[i].name === 'addressId' && addressIdInputs[i].value === 'newAddress' && form.associateId.value === '1111111'){
				 
				 isOtherRelative = true;
				 checked = true;
			 }	
			 
		 }
	 }
	 
	 for(var i=0; i < selectedAssociateIdInputs.length; i++) {
		 
		 if(selectedAssociateIdInputs[i].name === 'selectedAssociateId' && checked && isOtherRelative){
			 selectedAssociateIdInputs[i].value = form.associateId.value;
		 }
		 
	 }
	 
	 
	if(checked){
		
		if(!form.associateId.value){
			alert('associateId number is required');		
			result = false;		
			form.associateId.focus();	
		}		
		else if(!form.streetNum.value)
		{
			alert('street number is required');		
			result = false;		
			form.streetNum.focus();
		}		
		else if(!form.streetName.value){
			alert('street name is required');
			result = false;
			form.streetName.focus();		
		}		
		else if(!form.city.value){
			alert('city is required');
			result = false;
			form.city.focus();		
		}		
		else if(!form.state.value){
			alert('state is required');
			result = false;
			form.state.focus();		
		}		
		else if(!form.zipCode.value){
			alert('zipCode is required');
			result = false;
			form.zipCode.focus();		
		}
	} 
	
	
	return result;
}

 function initializeAssociateAddress(){
	 
	 var addressId = document.getElementById("addressId").value;
	 var streetNumber = document.getElementById("streetNum").value;
	 var streetName = document.getElementById("streetName").value;
	 var aptNumber = document.getElementById("apartmentNum").value;
	 var city = document.getElementById("city").value;
	 var stateId = document.getElementById("state").value;
	 var zipCode = document.getElementById("zipCode").value;
	 var addressType = document.getElementById("addressType").value;
	 
	 console.log("street #: ", streetNumber)
	 
	 var associateAddress = {
			 "addressId": addressId,
			 "streetNumber": streetNumber,
			 "streetName": streetName,
			 "aptNumber": aptNumber,
			 "city": city,
			 "stateId": stateId,
			 "zipCode": zipCode,
			 "addressType": addressType
	 };
	 
	 
	 document.getElementById("associateAddress").value = associateAddress;
	 
 }

function OpenOutlook()
{        
	var juvNum = document.getElementById("juvenileNum").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var juvName = firstName + " " + lastName;
	var warrantNumber = document.getElementById("warrantNumber").value;
	var jpOfficerEmail = document.getElementById("jpOfficerEmailAddress").value;
	var dataCorrectionsEmail = "Data.Corrections@hcjpd.hctx.net";
	
    var body = escape("Warrant Number: " + warrantNumber + "\nJuvenile Number: " + juvNum + "\nName: " + juvName);
    var subject = "Correction/Update to Family for " + juvName + "(" + juvNum + ")";
    var emailList = jpOfficerEmail +  ";" + dataCorrectionsEmail

                    window.location.href = "mailto:" + emailList + "?body="+body+"&subject="+subject; 

}