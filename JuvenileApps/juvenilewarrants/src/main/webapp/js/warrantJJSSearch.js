<!-- Javascript for emulated validation -->

function SearchValidator(thisForm)
{
   var msg = "";
   var VopSearch = false;
   var juvenileRegex = /^\d+$/;
   var referralRegex = /^\d{4}$/;
   var petitionRegex = /^\d+[jJ]$/;
   
	for (var x=0; x <thisForm.length; x++)
	{
    	if (thisForm.elements[x].name == "juvenileNum")
     	{
        	VopSearch = true;
     	}
	}	

	// VOP search   
	if (VopSearch == true)
	{
		var juvenileNum = Trim(thisForm.juvenileNum.value);
    	if (juvenileNum == "")
		{
        	msg = "Juvenile Number is required.\n";
			thisForm.juvenileNum.focus();
		} 
		else if(juvenileRegex.test(juvenileNum) == false)
		{
			msg = "Juvenile Number should be an integer.";
			thisForm.juvenileNum.focus();			
		}
	}   
	// OIC search     
	else if (VopSearch == false)
	{
		var petitionNum = Trim(thisForm.petitionNum.value);
      	if (petitionNum == "")
		{
			msg = "Petition Number is required.\n";
			thisForm.petitionNum.focus();
		}
		else if(petitionRegex.test(petitionNum) == false)
		{
			msg = "Petition Number is invalid.";
			thisForm.petitionNum.focus();			
		}
      
      
      /*else if (thisForm.petitionNum.value > "")
      {
         var str = thisForm.petitionNum.value;
         var jIndex = str.toUpperCase().indexOf("J");
         var petNum = str.substring(0,str.length - 1);
         if (isNaN(petNum) == true ||jIndex == -1)
         {
       	    msg = "Petition number is invalid.\n";
            thisForm.petitionNum.focus(); 	        
 	     }	          
      }*/
	}
	
	// Validate referral
	var referralNum = Trim(thisForm.referralNum.value);
	if (msg == "")
	{
		if(referralNum == "")
		{
	    	msg = "Referral Number is required.\n";
			thisForm.referralNum.focus();
		} 
		else if(referralRegex.test(referralNum) == false)
		{
			msg = "Referral Number should be a four digit integer.";
			thisForm.referralNum.focus();			
		}
	}
	
	if (msg == "")
	{
		return true;
	}
	else
	{	
		alert(msg);
		return false;
	}
}
