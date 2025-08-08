<!-- officerSearch.js -->
function validateSearchFields(){
	clearAllValArrays();
    var thisForm = document.forms[0];
    var streetSize = thisForm["locationAddress.streetName"].value
    
//    trim(thisForm["locationName"]);
//    trim(thisForm["locationAddress.streetNumber"]);
//    trim(thisForm["locationAddress.streetName"]);
//    trim(thisForm["locationAddress.city"]);
    var mychoice=0;
	for(var i=0; i<thisForm.length;i++)
   {
    	if(thisForm.elements[i].type == "text")
    	{
    		  if(thisForm.elements[i].value !="")
    		    mychoice=1;
    	}
    	else if(thisForm.elements[i].selectedIndex>0)
    	{
    		
    		mychoice=1;
    	}
    }
    if(mychoice==0)
    {
    	alert("At least one field has to be selected or entered for search.");
    	thisForm["locationName"].focus();
    	return false;
    }
	
    if(thisForm["locationAddress.streetNumber"].value != "" )
		if(thisForm["locationAddress.streetName"].value == "" )
		{
			alert("Street name has to be provided for using street number as search field.");
			thisForm["locationAddress.streetName"].focus();		    
		    return false;
		}
		
	if(thisForm["locationAddress.streetName"].value != "" )
		if(thisForm["locationAddress.streetName"].value.length < 2 )
		{
			alert("Street name must have at least 2 alphanumeric characters to search.");
			thisForm["locationAddress.streetName"].focus();
			return false;
		}
    
	var locationName = thisForm["locationName"].value;
	if(locationName.value != "") {
		
		customValMask("locationName", "Location Name must be at least 2 valid characters or 2 valid characters before the *.","/^[a-zA-Z0-9]([a-zA-Z0-9 \/_\.\\'\-]+)([*]?)$/");
		return validateCustomStrutsBasedJS(thisForm);
	}
	
   return true;
}
