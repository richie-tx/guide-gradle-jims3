/** script for contractCreateUpdate.jsp */
function checkInputs(theForm){
	var cnum  = Trim(theForm.contractNum.value);
	var cname = Trim(theForm.contractName.value);
	var pgmFundingDesc = Trim(theForm.programFundingDesc.value);
	var startDate = Trim(theForm.startDate.value);
	var endDate = Trim(theForm.endDate.value);
	var orgEndDate = theForm.originalEndDate.value;	
	var tracerNumFrom = "";
	var tracerNumTo = "";	
	var tracers = document.getElementsByName("tracerNumberRangeFrom");
	var tracersPresent = false;
	if (tracers.length > 0){
		tracerNumFrom = Trim(theForm.tracerNumberRangeFrom.value);
		tracerNumTo = Trim(theForm.tracerNumberRangeTo.value);	
		theForm.tracerNumberRangeFrom.value = tracerNumFrom;
		theForm.tracerNumberRangeTo.value = tracerNumTo;	
		tracersPresent = true;
	}
	var totalValue = Trim(theForm.totalValue.value);
	var totalValueValid = true;
	var spValues = document.getElementsByName("serviceProviderValue");
	var spValue = "";
	var spValuePresent = false;
	var spValueValid = false;	
	if (spValues.length > 0){
		spValue = Trim(theForm.serviceProviderValue.value);
		theForm.serviceProviderValue.value = spValue;
		spValuePresent = true;
	}
	
	var result = false;
	var msg = "";
	var contractNumRegExp = /^[a-zA-Z0-9]*$/;
	var startDateValid = true;
	var endDateValid = true;
	var contractNumRegExp = /^[a-zA-Z0-9]*$/;
	var currencyRegExp = /^([1-9]{1}([0-9]{0,12})(.[0-9]{2})|0(.[0-9]{2}))$/;
	var nameRegExp = /^[a-zA-Z0-9 \x2D\x27\x26]*$/;
	var zeroCurrencyRegExp = /^0{1,12}.00$/;	
	var tracerNumRegExp = /^[0-9]*$/;
	theForm.contractName.value = cname;
	theForm.contractNum.value = cnum;
	theForm.programFundingDesc.value = pgmFundingDesc;
	theForm.startDate.value = startDate;
	theForm.endDate.value = endDate;
	theForm.originalEndDate.value = orgEndDate;	

	theForm.totalValue.value = totalValue;	
	if (cnum == ""){
		msg = "Contract Number is required.\n";
		theForm.contractNum.focus();
	} else {
		result = contractNumRegExp.test(cnum);
       	if (result == false){
   			msg += "Contract Name must be alphanumeric.\n";
   			theForm.contractNum.focus();
       	}	
	}
	if (cname == ""){
		if (msg == ""){
			theForm.contractName.focus();
		}
		msg += "Contract Name is required.\n";
	} else {
		result = nameRegExp.test(cname);
       	if (result == false){
   			if (msg == ""){
   				theForm.contractName.focus();
    		}
   	       	msg += "Contract Name contains invalid characters.\n";
       	}	
	}
	if (startDate == ""){
		if (msg == ""){
			theForm.startDate.focus();
		}
		msg += "Start Date is required.\n";
		startDateValid = false;
	}
	if (startDate > ""){
		result = validateDate(startDate);
		if (result == false){
			if (msg == ""){
				theForm.startDate.focus();
			}			
			msg += "Start Date is invalid date or wrong date format.\n";
			startDateValid = false;
		}			
	}
	if (endDate > ""){
		result = validateDate(endDate);
		if (result == false){
			if (msg == ""){
				theForm.endDate.focus();
			}			
			msg += "End Date is invalid date or wrong date format.\n";
			endDateValid = false;
		}			
	}
	if (startDateValid && endDateValid){
    	var startDateValues = startDate.split('/');
    	var endDateValues = endDate.split('/');
//array elements 0=month 1=day 2=year	
    	var endDateYMD = endDateValues[2] + endDateValues[0] + endDateValues[1];
    	var startDateYMD = startDateValues[2] + startDateValues[0] + startDateValues[1];
		if (startDateYMD > endDateYMD){
			if (msg == ""){
				theForm.endDate.focus();
			}	
			msg += "End date cannot be previous to start date.\n";
	}	
	}	
	if (theForm.contractTypeId.selectedIndex == 0){
		if (msg == ""){
			theForm.contractTypeId.focus();
		}
		msg += "Funding Type selection is required.\n";		
	}
/*  edit Tracer numbers if present */
	if (tracersPresent){	
		if (tracerNumFrom > ""){
			result = tracerNumRegExp.test(tracerNumFrom);
        	if (result == false){
    			if (msg == ""){
    				theForm.tracerNumberRangeFrom.focus();
	    		}
    	       	msg += "Beginning Tracer Number must be a number.\n";
        	}
	        if (tracerNumTo == ""){
    	   		if (msg == ""){
    				theForm.tracerNumberRangeTo.focus();
    			}
	           	msg += "Ending Tracer Number is required.\n";        	
    	    }
		}
		if (tracerNumTo > ""){
			result = tracerNumRegExp.test(tracerNumTo);
	        if (result == false){
    			if (msg == ""){
    				theForm.tracerNumberRangeTo.focus();
    		}
           	msg += "Ending Tracer Number must be a number.\n";
	        }
    	    if (tracerNumFrom == ""){
       			if (msg == ""){
    				theForm.tracerNumberRangeFrom.focus();
	    		}
    	       	msg += "Begiinning Tracer Number is required.\n";        	
        	}
        }	        
	}
/* edit total and service dollar values */
	if (totalValue == ""){
			if (msg == ""){
				theForm.totalValue.focus();
			}
			msg += "Total Value is required.\n";
		}		
	if (totalValue > ""){
        result = currencyRegExp.test(totalValue);
        if (result == false){
    		if (msg == ""){
    			theForm.totalValue.focus();
    		}
           	msg += "Total Value must be in valid currency format and not equal zero. Example: for $1,000 enter 1000.\n";
           	totalValueValid = false;
        }
        result = zeroCurrencyRegExp.test(totalValue);
        if (result == true){
    		if (msg == ""){
    			theForm.totalValue.focus();
    		}
           	msg += "Total Value can not equal zero.\n";
           	totalValueValid = false;
        }
        if (spValuePresent){
	        if (spValue == ""){
    	   		if (msg == ""){
    				theForm.serviceProviderValue.focus();
    			}
	           	msg += "Service Provider Value is required for Total Value.\n";        	
    	    }
        }    
	}
	if (spValue > ""){
		spValueValid = true;
        result = currencyRegExp.test(spValue);
        if (result == false){
    		if (msg == ""){
    			theForm.serviceProviderValue.focus();
    		}
           	msg += "Service Provider Value must be in currency format and not equal zero.\n";
    		spValueValid = false;           	
        }
        result = zeroCurrencyRegExp.test(spValue);
        if (result == true){
    		if (msg == ""){
    			theForm.serviceProviderValue.focus();
    		}
           	msg += "Service Provider Value can not equal zero.\n";
    		spValueValid = false;           	
        } 
        if (totalValueValid && spValueValid){
        	var spValuef = parseFloat(spValue);
        	var totalValuef = parseFloat(totalValue);        	
        	if (spValuef > totalValuef){
           		if (msg == ""){
        			theForm.serviceProviderValue.focus();
        		}
               	msg += "Service Provider Value can not more than Total Value.\n";       		
        	}
        }
	}

	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;
}

function validateDate(fldValue){
	var regDate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
	var result = regDate.test(fldValue,regDate);
	if (result == false){
		return false;
	}  
	var dValues = fldValue.split('/');
	var mon = dValues[0];
	var day = dValues[1];
	var leapYear = 0;
	if (mon > 12 || mon == 0){
		return false;
	}
	if (day > 31 || day == 0){
		return false;
	}	
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11){
		if (day > 30){
			return false;
		}	
	}
	if (mon == 2){
		leapYear = dValues[2] %4;
		if (leapYear == 0 && day > 29){
			return false;
		}
		if (leapYear > 0  && day > 28){
			return false;
		}	
	}
	return true;
}