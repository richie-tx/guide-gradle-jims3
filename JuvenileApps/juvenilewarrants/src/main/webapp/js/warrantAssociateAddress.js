<!-- warrantAssociateAddress.js -->
function createAddressValidate(theForm)
{
  	var msg = "";
  	var numbers = /^[0-9]*$/;
  	var streetNumRegex = /^[a-zA-Z0-9-\/ ]+$/;
	var strNameRegex = /^[A-Za-z0-9\.\/\- ]*$/;  	
	var aptNumRegex = /^[A-Za-z0-9]*$/;
	var cityRegex = /^[A-Za-z\.\- ]*$/
  
  	var zipre = /^[0-9]{5}$/;	
  	var addzipre = /^[0-9]{4}$/;
    
	var streetNums = document.getElementsByName("newAddress.streetNum");
	var streetNames = document.getElementsByName("newAddress.streetName");
	var aptNums = document.getElementsByName("newAddress.aptNum");
	var cities = document.getElementsByName("newAddress.city");
	var stateIds = document.getElementsByName("newAddress.stateID");
	var zipCodes = document.getElementsByName("newAddress.zipCode");
	var additionalZipCodes = document.getElementsByName("newAddress.additionalZipCode");
		
    var strNum = Trim(streetNums[0].value);
    streetNames[0].value = Trim(streetNames[0].value);
    cities[0].value = Trim(cities[0].value);    	

    if (strNum == "" || strNum == null)
	{
    	if (msg == "")
    	{ 
	    	streetNums[0].focus();
	    }    
    	msg += "Street Number is required to create address.\n";
	}else if((streetNumRegex.test(strNum) == false) || ((isNaN(strNum) == false) && (strNum <= 0)))
    	{
   		    streetNums[0].focus();
		    msg += "Street Number must be non-zero alphanumeric.\n";	
		}
		else if(isNaN(strNum.charAt(0)))
		{
			streetNums[0].focus();
		    msg += "The first character in Street Number must be numeric to create address.\n";
		}
		if (streetNames[0].value == "" || streetNames[0].value == null)
		{
			if (msg == "")
			{
	   			streetNames[0].focus();	    			
    	    }
	    	msg += "Street Name is required to create address.\n";		
		} 
		if (streetNames[0].value > "")
		{
			if(strNameRegex.test(streetNames[0].value) == false)
			{
				if (msg == "")
				{
					streetNames[0].focus();	
				}
				msg += "Street Name must be alphanumeric value.\n";
			}		
		}
		if (aptNums[0].value > "")
		{
			if(aptNumRegex.test(aptNums[0].value) == false)
			{
				if (msg == "")
				{
					aptNums[0].focus();
				}	
				msg += "Apartment Number must be alphanumeric value.\n";
			}
		}		
		if (cities[0].value == "" || cities[0].value == null)
		{
			if (msg == "")
			{
	   			cities[0].focus();	    			
    	    }
	    	msg += "City is required to create address.\n";		
		}
		if (cities[0].value > "")
		{
			if(cityRegex.test(cities[0].value) == false)
			{
				if (msg == ""){
					cities[0].focus();
				}	   
				msg += "City must be alphabetic value.\n";
			}	
		}		
    	if (stateIds[0].value == "" || stateIds[0].value == null )
    	{
       		if (msg == "")
       		{ 
	   		    stateIds[0].focus();   		
	        }    
    		msg += "State selection required to create address.\n";
	  	}
	  	 
	  	var zipCode = Trim(zipCodes[0].value);       	 
       	
       	 if (zipCode == "")
		 {
			if (msg == "")
			{
	   			zipCodes[0].focus();
    	    }
	    	msg += "Zip Code is required to create address.\n";
    	 }
    	 else if(!zipre.exec(zipCode) || zipCode <= 0)
    	 {
    		if (msg == "")
    		{
	   			zipCodes[0].focus();		    		
		    }
		    msg += "Zip Code must be a non-zero 5 digit number.\n";	    
		 }
		 
		 
		 if(additionalZipCodes[0] != null)
		 {
         	var additionalZipCode = Trim(additionalZipCodes[0].value);
	     	if (additionalZipCode != "")
	     	{
				if(!addzipre.exec(additionalZipCode))
				{
    				if (msg == "")
    				{
	   					additionalZipCodes[0].focus();		    			
		    		}
			    	msg += "Zip Code extension must be a 4 digit number.\n";	    
				}
    	 	}
    	 }
	  	 if (msg != "")
	  	 {
    		alert(msg);
  		 	return false;
  	 	 }	
    return true;
}
function addressEntryValidate(theForm)
{
/*  check if any values entered in create address block */ 	
	var streetNums = document.getElementsByName("newAddress.streetNum");
	var streetNames = document.getElementsByName("newAddress.streetName");
	var streetTypeIds = document.getElementsByName("newAddress.streetTypeId");
	var aptNums = document.getElementsByName("newAddress.aptNum");	
	var cities = document.getElementsByName("newAddress.city");
	var stateIds = document.getElementsByName("newAddress.stateId");
	var zipCodes = document.getElementsByName("newAddress.zipCode");
	var addrTypeIds = document.getElementsByName("newAddress.addressTypeId");
	var countyIds = document.getElementsByName("newAddress.countyId");	
	
    var strNum = Trim(streetNums[0].value);
    var strName = Trim(streetNames[0].value);
    var strTypeId = Trim(streetTypeIds[0].value);
    var city = Trim(cities[0].value); 
    var stateId = Trim(stateIds[0].value.toUpperCase());
    var zipCode = Trim(zipCodes[0].value); 
    var aptNum = Trim(aptNums[0].value);
    var addrTypeId = Trim(addrTypeIds[0].value);
    var countyId = Trim(countyIds[0].value); 
    
	if (strNum > "" || strName > "" || strTypeId > "" || aptNum > "" || city > "" || zipCode > "" || aptNum > "" || addrTypeId > "" )
	{
		alert('Create address has values that have not been added.');
		streetNums[0].focus();
		return false		
	}	

/** Create address fields blank, at least 1 update address must be present to continue */
	streetNums = document.getElementsByName('associateAddresses[0].streetNum');
	streetNames = document.getElementsByName('associateAddresses[0].streetName');
	cities = document.getElementsByName('associateAddresses[0].city');
	stateIds = document.getElementsByName('associateAddresses[0].stateId');
	zipCodes = document.getElementsByName('associateAddresses[0].zipCode');
	
    strNum = Trim(streetNums[0].value);
    strName = Trim(streetNames[0].value);
    city = Trim(cities[0].value); 
    stateId = Trim(stateIds[0].value.toUpperCase());
    zipCode = Trim(zipCodes[0].value);   
    
	if ((strNum == "" || strNum == null) || 
		(strName == "" || strName == null) ||
		(city == "" || city == null) ||
		(stateId == "" || stateId == null) ||
		(zipCode == "" || zipCodes == null)) 				
	{
		alert('Address entry required to continue.');
		streetNums[0].focus();		
		return false
	}	
	return true;
}
function setStateCounty(){
// preset Create Address block state and county to Texas and Harris by default
	var stSelect = document.getElementsByName("newAddress.stateId");
	var ctySelect = document.getElementsByName("newAddress.countyId");	
	for (x=0; x<stSelect[0].length; x++){
		if (stSelect[0].options[x].value.toUpperCase() == "TX"){
			stSelect[0].options[x].selected = true;
			break;
		}
	}	
	for (x=0; x<ctySelect[0].length; x++){
		if (ctySelect[0].options[x].value == "101"){
			ctySelect[0].options[x].selected = true;
			break;
		}
	}
// set Update Addressess county based on State selected value	
	var countyName = "";
	var fldName = "";
	var selectedState = "";
	var selectedCounty = "";
	var stSelect = "";		
	for (y=0; y<document.forms[0].length; y++){
		fldName = "associateAddresses[" + y + "].stateId";
	    stSelect = document.getElementsByName(fldName);		
		if (stSelect.length == 0){
			break;
		}
 		countyName="associateAddresses[" + y + "].countyId";
		theCountySelect = document.getElementsByName(countyName);  
 		
	    selectedState = stSelect[0].value;
	    selectedCounty = theCountySelect[0].value;
		if (selectedState == "TX"){  	
		  	theCountySelect[0].disabled = false;
		  	if (selectedCounty == ""){
				for (x=0; x<theCountySelect[0].length; x++){
					if (theCountySelect[0].options[x].value == "101"){
						theCountySelect[0].options[x].selected = true;
						break;
					}
				}	
			}  	
		}else {
  			theCountySelect[0].selectedIndex=0;
		  	theCountySelect[0].disabled = true; 	  	
		}	    
	}	
}
function enableCounty(theSelect, countyId)
{
  var countyName=countyId.replace(".stateId",".countyId");
  
  var selectedState = theSelect.options[theSelect.selectedIndex].value;
  var theForm = theSelect.form;
  var theCountySelect = document.getElementsByName(countyName);  
 
  if (selectedState == "TX"){  	
  	theCountySelect[0].disabled = false;
	for (x=0; x<theCountySelect[0].length; x++){
		if (theCountySelect[0].options[x].value == "101"){
			theCountySelect[0].options[x].selected = true;
			break;
		}
	}  	
  }else {
  	theCountySelect[0].selectedIndex=0;
  	theCountySelect[0].disabled = true; 	  	
  }
}