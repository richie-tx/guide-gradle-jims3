     // This class holds custom validation based on the struts validation to help evaluate fields with regex masks, etc...
     // Several masks have been predefined and can be utilized by calling for the methods that start with add ex: addCurrencyValidation
     // If not using the predefined masks any validation such as required fields, masks, max lengths, etc..
     // should use methods that start with customVal ex: customValEmail
     // Example usage is: customValEmail(<nest:writeNesting property="email"/>,<bean:message key="error.emailAddressInvalid"/>,"");
    // Finally tied to the button that will trigger off all the validation should be an onclick that calls
    // validateCustomStrutsBasedJS(form) such as <input type="button" onclick="validateCustomStrutsBasedJS(this.form)"/>
    // this file can be used in conjunction with the real struts validations and all other validations
     var customBCancel = false; 
	 
	 // Arrays for holding validations to be done
	 var valRequiredArray=new Array();
	 var valCondRequiredArray=new Array();
	 var valMaskArray=new Array();
	 var valMaxLengthArray=new Array(); 
	 var valMinLengthArray=new Array();
	 var valDateArray=new Array();
	 var valByteArray=new Array();
	 var valIntegerArray=new Array();
	 var valShortArray=new Array();
	 var valFloatArray=new Array();
	 var valEmailArray=new Array();
	 var valCreditCardArray=new Array();
	 var valIntegerRangeArray=new Array();
	 
	 var definedTinyMCEFieldMask="/^[^%_]*$/";
//	 var definedCurrencyWithNWithoutCommasMask="/^[$]?[0-9]{1,3}(?:,?[0-9]{3})*\.[0-9]{2}$/";
	 var definedCurrencyWithNWithoutCommasMask="/^[0-9]{0,3}(?:,?[0-9]{3})*(?:\.[0-9]{2})?$/";	 
	 var definedCurrencyMask="/^([1-9]{1}([0-9]{0,12})(\\.[0-9]{2})|0(\\.[0-9]{2}))$/";
	 var defined24HrTimeMask="/^(20|21|22|23|[01][0-9])(([:][0-5]\\d){1,2})$/";
	 var defined12HrTimeMask="/^(12|11|10|[0][1-9])(([:][0-5]\\d){1,2})$/";
	 var definedNameMask="/^[a-zA-Z0-9][a-zA-Z0-9 \/_\.\\'\-]*$/";
	 var definedAlphaNumericnSpaceMaskWSymbols="/^[a-zA-Z0-9 ,.#\-]*$/";
	 var definedAlphaNumericNoFirstSpaceMaskWSymbols="/^[a-zA-Z0-9\.\\\\';,()\x26\x2f\-][a-zA-Z0-9 \.\\\\';,()\x26\x2f\-]*$/";
	 var definedAlphaNumericnSpaceMask="/^[a-zA-Z0-9 ]*$/";
	 var definedAlphaNumericMask="/^[a-zA-Z0-9]*$/";
	 var definedAlphaMask="/^[a-zA-Z]*$/";
	 var definedNumericMask="/^[0-9]*$/";
	 var definedFreeTextDB2Mask="/^[a-zA-Z0-9\.\\\\',:;()\x26\x2f\x5b\\x5d\-][a-zA-Z0-9 \.\\\\',:;()\x26\x2f\x5b\\x5d\\s\-]*$/";
	 var definedMMDDYYYYMask="MM/dd/yyyy";
	 var definedSearchFieldMask="/^([a-zA-Z0-9]{1})([a-zA-Z0-9']{1})([a-zA-Z0-9 '.\-]*)([*]?)$/";
	 var definedM204LNameSearchFieldMask="/^([a-zA-Z0-9]{1})([a-zA-Z0-9']{1})([a-zA-Z0-9 \/\.\\'\()\x26\-]*)([*]?)$/";
	 var definedM204FMNameSearchFieldMask="/^([a-zA-Z0-9]{1})([a-zA-Z0-9 \/\.\\'\()\x26\-]*)([*]?)$/";
	 var definedIntegerMask="/^[-+]?[0-9]\\d*\\.?[0]*$/";
	 var definedFloatMask="/^[-+]?\\d*\\.?\\d*$/";
	 var definedAlphaNumericDashPeriodMask="/^[a-zA-Z0-9][a-zA-Z0-9 .\-]*$/";
	 var definedDB2Mask="/^[^%_]*$/";
	 	
	 function validateCustomStrutsBasedJS(form, notRequired) {                                                                   
        if (customBCancel) 
      return true; 
        else {
			return  validateCustomRequired(form, notRequired) &&
				 validateCustomCondRequired(form) &&
				 validateCustomMaxLength(form) &&
				 validateCustomMinLength(form) &&
				 validateCustomMask(form) &&
				 validateCustomDate(form) &&
				 validateCustomCreditCard(form) &&
				 validateCustomEmail(form) &&
			     validateCustomByte(form) && 
				 validateCustomInteger(form) &&
				 validateCustomShort(form) &&
				 validateCustomFloat(form) &&
				 validateCustomRange(form);					 				
	    }
    } 
	
	function clearAllValArrays(){
	 valRequiredArray=new Array();
	 valCondRequiredArray=new Array();
	 valMaskArray=new Array();
	 valMaxLengthArray=new Array();
	 valMinLengthArray=new Array();
	 valDateArray=new Array();
	 valByteArray=new Array();
	 valIntegerArray=new Array();
	 valShortArray=new Array();
	 valFloatArray=new Array();
	 valEmailArray=new Array();
	 valCreditCardArray=new Array();
	 valIntegerRangeArray=new Array();
   }
   
   function addDefinedTinyMCEFieldMask(formElemName,errorMsg){
	   return true;
   		//return customValMask (formElemName,errorMsg,"");
   }
   function addDefinedDB2Mask(formElemName,errorMsg){
	   	return true;
   		//return customValMask (formElemName,errorMsg,"");
   }
   function addCurrencyValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedCurrencyMask);
   }
   function addCurrencyWithNWithoutCommasValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedCurrencyWithNWithoutCommasMask);
  }
   function add24HrTimeValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,defined24HrTimeMask);
   }
   function add12HrTimeValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,defined12HrTimeMask);
   }
    function addAlphaNumericnSpacewSymbolsValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedAlphaNumericnSpaceMaskWSymbols);
   }
    function addAlphaNumericNoFirstSpacewSymbolsValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedAlphaNumericNoFirstSpaceMaskWSymbols);
   }
   function addAlphaNumericDashPeriodValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedAlphaNumericDashPeriodMask);
   }
   function addAlphaNumericnSpaceValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedAlphaNumericnSpaceMask);
   }
   function addAlphaNumericValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedAlphaNumericMask);
   }
   function addAlphaValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedAlphaMask);
   }
   function addNumericValidation(formElemName,errorMsg){
		return customValMask (formElemName,errorMsg,definedNumericMask);
   }
   function addNameValidation(formElemName,errorMsg){
   		return customValMask (formElemName,errorMsg,definedNameMask);
   }
   function addDB2FreeTextValidation(formElemName,errorMsg){
	   return true;
		//return customValMask (formElemName,errorMsg,"");
   }
   function addSearchFieldValidation(formElemName,errorMsg){
   		return customValMask (formElemName,errorMsg,definedSearchFieldMask);
   }
   function addM204LNameSearchFieldValidation(formElemName,errorMsg){
   		return customValMask (formElemName,errorMsg,definedM204LNameSearchFieldMask);
   }
   function addM204FMNameSearchFieldValidation(formElemName,errorMsg){
   		return customValMask (formElemName,errorMsg,definedM204FMNameSearchFieldMask);
   }
   function addMMDDYYYYDateValidation(formElemName,errorMsg){
		return customValDateValidations (formElemName,errorMsg,definedMMDDYYYYMask);
   }
   
   function customValEmail(formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
    lastParam="fake";
		valEmailArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.fake=" + lastParam+";  return this[varName];"))));
    } 
   
    function customValFloat(formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
    //lastParam="fake";
		//valFloatArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.mask=" + definedFloatMask+";  return this[varName];"))));
		valMaskArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.mask=" + definedFloatMask+";  return this[varName];"))));
    } 
   
    function customValCreditCard(formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
    lastParam="fake";
		valCreditCardArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.fake=" + lastParam+";  return this[varName];"))));
    } 
   
   function customValInteger(formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
    //lastParam="fake";
		//valIntegerArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.mask=" + definedIntegerMask+";  return this[varName];"))));
		valMaskArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.mask=" + definedIntegerMask+";  return this[varName];"))));
  
    } 
   
    function customValShort(formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
    lastParam="fake";
		valShortArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.fake=" + lastParam+";  return this[varName];"))));
    } 
   
    function customValByte(formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
    lastParam="fake";
   valByteArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.fake=" + lastParam+";  return this[varName];"))));
    } 
   
    function customValRequired (formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testRequired", "Type is required.", new Function ("varName", " return this[varName];"));
   lastParam="fake";
   valRequiredArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.fake=" + lastParam+";  return this[varName];"))));
    } 
    
    // formElemName is the element to be checked  ex: firstName
    // formElemNameCondRequired is te element that is required if the formElemName has been entered ex: lastname
    // last Param is the variable that indicates the null or blank value of the formElemName i.e. if the formElemName
    // equal null or the lastParam value then it is assumed that the formElemName has not been entered and that
    // the formElemNameCondRequired element is not required
     function customValCondRequired(formElemName, formElemNameCondRequired,errorMsg,lastParam) { 
   		valCondRequiredArray.push(new Array(formElemName,formElemNameCondRequired ,errorMsg,(new Function ("varName", "this.blankValue=" + lastParam+";  return this[varName];"))));
    } 

    function customValMask (formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testAlphaNumeric", "Name must be alphanumeric.", new Function ("varName", "this.mask=/^[a-zA-Z0-9 ]*$/;  return this[varName];"));
   //  this.ab = new Array("testNumeric", "Name must be numeric.", new Function ("varName", "this.mask=/^[0-9 ]*$/;  return this[varName];"));
   //  this.ac = new Array("testCurrency", "Cost is not a valid currency entry. No commas or dollar signs are allowed. Example: for $1,000 enter 1000.", new Function ("varName", "this.mask=/^([1-9]{1}([0-9]{0,12})(\\.[0-9]{2})?|0(\\.[0-9]{2})?)$/;  return this[varName];"));
   //  this.ad = new Array("testCaseNumber", "Mailing City must be alphanumeric.", new Function ("varName", "this.mask=/^[a-zA-Z0-9][\\-][a-zA-Z0-9]*$/;  return this[varName];"));
   //  this.ae = new Array("testFreeText", "Notes contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).", new Function ("varName", "this.mask=/^[^%_]*$/;  return this[varName];"));
   //  this.af = new Array("testAlphaNumericWithSymbols", "Name must be alphanumeric.", new Function ("varName", "this.mask=/^[a-zA-Z0-9.;:\\-]*$/;  return this[varName];"));
   //  this.ag = new Array("test24HourTime", "Name must be alphanumeric.", new Function ("varName", "this.mask=/^(20|21|22|23|[01][0-9])(([:][0-5]\\d){1,2})$/;  return this[varName];"));
	valMaskArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.mask=" + lastParam+";  return this[varName];"))));
  
  } 

    function customValMaxLength (formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testMaxLength", "Max Enrollment can not be greater than 4 characters.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
   valMaxLengthArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.maxlength=" + lastParam+";  return this[varName];"))));
	} 
	
	function customValMinLength (formElemName,errorMsg,lastParam) { 
  //this.aa = new Array("testMaxLength", "Max Enrollment can not be greater than 4 characters.", new Function ("varName", "this.minlength='4';  return this[varName];"));
      valMinLengthArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.minlength=" + lastParam+";  return this[varName];"))));
	} 

    function customValDateValidations (formElemName,errorMsg,lastParam) { 
   //  this.aa = new Array("testDate", "Inactive Date is invalid.  Valid format is mm/dd/yyyy.", new Function ("varName", "this.datePattern='MM/dd/yyyy';  return this[varName];"));
    
     valDateArray.push(new Array(formElemName, errorMsg,(new Function ("varName", "this.datePatternFormat='" + lastParam+"';  return this[varName];"))));
	} 
	
	function customValIntegerRange (formElemName,errorMsg,minRange,maxRange) { 
  		 valIntegerRangeArray.push(new Array(formElemName, errorMsg,minRange,maxRange));
	} 
	

 // the methods that follow ideally should not be invoked directly by a user
function validateCustomByte(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                //oByte = new ByteValidations();
				oByte=valByteArray;
                for (x in oByte) {
                if (form[oByte[x][0]].type == 'text' ||
                        form[oByte[x][0]].type == 'textarea' ||
                        form[oByte[x][0]].type == 'password') {
						form[oByte[x][0]].value=customTrimAll(form[oByte[x][0]].value,form[oByte[x][0]].type);
					}
                
                    if ((form[oByte[x][0]].type == 'text' ||
                         form[oByte[x][0]].type == 'textarea' ||
                         form[oByte[x][0]].type == 'select-one' ||
                         form[oByte[x][0]].type == 'radio') &&
                        (form[oByte[x][0]].value.length > 0)) {
                        var iValue = parseInt(form[oByte[x][0]].value);
                        if (isNaN(iValue) || !(iValue >= -128 && iValue <= 127)) {
                            if (i == 0)
                                focusField = form[oByte[x][0]];
                            fields[i++] = oByte[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateCustomMaxLength(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
               // oMaxLength = new maxlength();
			  oMaxLength = valMaxLengthArray;
                for (x in oMaxLength) {
                	 if (form[oMaxLength[x][0]].type == 'text' ||
                        form[oMaxLength[x][0]].type == 'textarea' ||
                        form[oMaxLength[x][0]].type == 'password') {
						form[oMaxLength[x][0]].value=customTrimAll(form[oMaxLength[x][0]].value,form[oMaxLength[x][0]].type);
					}
                
                    if (form[oMaxLength[x][0]].type == 'text' ||
                        form[oMaxLength[x][0]].type == 'textarea' ||
                        form[oMaxLength[x][0]].type == 'password') {
                        var iMax = parseInt(oMaxLength[x][2]("maxlength"));
                        if (!(form[oMaxLength[x][0]].value.length <= iMax)) {
                            if (i == 0) {
                                focusField = form[oMaxLength[x][0]];
                            }
                            fields[i++] = oMaxLength[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateCustomRequired(form, notRequired) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
             //   oRequired = new required();
			 oRequired=valRequiredArray;
                for (x in oRequired) 
				{
					  if (form[oRequired[x][0]].type == 'text' ||
                        form[oRequired[x][0]].type == 'textarea' ||
                        form[oRequired[x][0]].type == 'password') {
						form[oRequired[x][0]].value=customTrimAll(form[oRequired[x][0]].value,form[oRequired[x][0]].type);
					}
		                    if ((form[oRequired[x][0]].type == 'text' ||
		                         form[oRequired[x][0]].type == 'textarea' ||
		                         form[oRequired[x][0]].type == 'select-one' ||
		                         form[oRequired[x][0]].type == 'radio' ||
		                         form[oRequired[x][0]].type == 'password' ||
		                         form[oRequired[x][0]].type == 'select-multiple') &&
		                        (customTrimAll(form[oRequired[x][0]].value,form[oRequired[x][0]].type) == '')) {
		                        if (i == 0) {
		                            focusField = form[oRequired[x][0]];
		                        }
		                        fields[i++] = oRequired[x][1];
		                        bValid = false;
		                    }
                    
                   
                    
                }
                if (notRequired != "notRequired"){
                if (fields.length > 0) {
                	
                	
                	
                	focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
                }else {
                return true;	                
                }
            }
function validateCustomInteger(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
               // oInteger = new IntegerValidations();
				oInteger = valIntegerArray;
                for (x in oInteger) {
                 if (form[oInteger[x][0]].type == 'text' ||
                        form[oInteger[x][0]].type == 'textarea' ||
                        form[oInteger[x][0]].type == 'password') {
						form[oInteger[x][0]].value=customTrimAll(form[oInteger[x][0]].value,form[oInteger[x][0]].type);
					}
                    if ((form[oInteger[x][0]].type == 'text' ||
                         form[oInteger[x][0]].type == 'textarea' ||
                         form[oInteger[x][0]].type == 'select-one' ||
                         form[oInteger[x][0]].type == 'radio') &&
                        (form[oInteger[x][0]].value.length > 0)) {
                        var iValue = parseInt(form[oInteger[x][0]].value);
                        if (isNaN(iValue) || !(iValue >= -2147483648 && iValue <= 2147483647)) {
                            if (i == 0) {
                                focusField = form[oInteger[x][0]];
                            }
                            fields[i++] = oInteger[x][1];
                            bValid = false;
                       }
                    }
                }
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateCustomRange(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRange = valIntegerRangeArray;
                for (x in oRange) {
                 if (form[oRange[x][0]].type == 'text' ||
                        form[oRange[x][0]].type == 'textarea' ||
                        form[oRange[x][0]].type == 'password') {
						form[oRange[x][0]].value=customTrimAll(form[oRange[x][0]].value,form[oRange[x][0]].type);
					}
                    if ((form[oRange[x][0]].type == 'text' ||
                         form[oRange[x][0]].type == 'textarea') &&
                        (form[oRange[x][0]].value.length > 0)) {
                        var iMin = parseInt(oRange[x][2]);
                        var iMax = parseInt(oRange[x][3]);
                        var iValue = parseInt(form[oRange[x][0]].value);
                        if (!(iValue >= iMin && iValue <= iMax)) {
                            if (i == 0) {
                                focusField = form[oRange[x][0]];
                            }
                            fields[i++] = oRange[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusOnField(focusField);
                    alert(fields.join('\n'));
                }
                return bValid;
            }
function validateCustomDate(form) {
               var bValid = true;
               var focusField = null;
               var i = 0;
               var fields = new Array();
            //   oDate = new DateValidations();
			   oDate = valDateArray;
               for (x in oDate) {
               if (form[oDate[x][0]].type == 'text' ||
                        form[oDate[x][0]].type == 'textarea' ||
                        form[oDate[x][0]].type == 'password') {
						form[oDate[x][0]].value=customTrimAll(form[oDate[x][0]].value,form[oDate[x][0]].type);
					}
                   var value = form[oDate[x][0]].value;
                   var datePattern = oDate[x][2]("datePatternFormat");
                   if ((form[oDate[x][0]].type == 'text' ||
                        form[oDate[x][0]].type == 'textarea') &&
                       (value.length > 0) &&
                       (datePattern.length > 0)) {
                     var MONTH = "MM";
                     var DAY = "dd";
                     var YEAR = "yyyy";
                     var orderMonth = datePattern.indexOf(MONTH);
                     var orderDay = datePattern.indexOf(DAY);
                     var orderYear = datePattern.indexOf(YEAR);
                     if ((orderDay < orderYear && orderDay > orderMonth)) {
                         var iDelim1 = orderMonth + MONTH.length;
                         var iDelim2 = orderDay + DAY.length;
                         var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                         var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                         if (iDelim1 == orderDay && iDelim2 == orderYear) {
                            dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                         } else if (iDelim1 == orderDay) {
                            dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
                         } else if (iDelim2 == orderYear) {
                            dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
                         } else {
                            dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
                         }
                         var matched = dateRegexp.exec(value);
                         if(matched != null) {
                            if (!isValidCustomDate(matched[2], matched[1], matched[3])) {
                               if (i == 0) {
                                   focusField = form[oDate[x][0]];
                               }
                               fields[i++] = oDate[x][1];
                               bValid =  false;
                            }
                         } else {
                            if (i == 0) {
                                focusField = form[oDate[x][0]];
                            }
                            fields[i++] = oDate[x][1];
                            bValid =  false;
                         }
                     } else if ((orderMonth < orderYear && orderMonth > orderDay)) {
                         var iDelim1 = orderDay + DAY.length;
                         var iDelim2 = orderMonth + MONTH.length;
                         var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                         var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                         if (iDelim1 == orderMonth && iDelim2 == orderYear) {
                             dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                         } else if (iDelim1 == orderMonth) {
                             dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
                         } else if (iDelim2 == orderYear) {
                             dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
                         } else {
                             dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
                         }
                         var matched = dateRegexp.exec(value);
                         if(matched != null) {
                             if (!isValidCustomDate(matched[1], matched[2], matched[3])) {
                                 if (i == 0) {
                                     focusField = form[oDate[x][0]];
                                 }
                                 fields[i++] = oDate[x][1];
                                 bValid =  false;
                              }
                         } else {
                             if (i == 0) {
                                 focusField = form[oDate[x][0]];
                             }
                             fields[i++] = oDate[x][1];
                             bValid =  false;
                         }
                     } else if ((orderMonth > orderYear && orderMonth < orderDay)) {
                         var iDelim1 = orderYear + YEAR.length;
                         var iDelim2 = orderMonth + MONTH.length;
                         var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                         var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                         if (iDelim1 == orderMonth && iDelim2 == orderDay) {
                             dateRegexp = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                         } else if (iDelim1 == orderMonth) {
                             dateRegexp = new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$");
                         } else if (iDelim2 == orderDay) {
                             dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$");
                         } else {
                             dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$");
                         }
                         var matched = dateRegexp.exec(value);
                         if(matched != null) {
                             if (!isValidCustomDate(matched[3], matched[2], matched[1])) {
                                 if (i == 0) {
                                     focusField = form[oDate[x][0]];
                                  }
                                  fields[i++] = oDate[x][1];
                                  bValid =  false;
                              }
                          } else {
                              if (i == 0) {
                                  focusField = form[oDate[x][0]];
                              }
                              fields[i++] = oDate[x][1];
                              bValid =  false;
                          }
                     } else {
                         if (i == 0) {
                             focusField = form[oDate[x][0]];
                         }
                         fields[i++] = oDate[x][1];
                         bValid =  false;
                     }
                  }
               }
               if (fields.length > 0) {
                  focusOnField(focusField);
                  alert(fields.join('\n'));
               }
               return bValid;
            }

	    function isValidCustomDate(day, month, year) {
	        if (month < 1 || month > 12) {
                    return false;
                }
                if (day < 1 || day > 31) {
                    return false;
                }
                if ((month == 4 || month == 6 || month == 9 || month == 11) &&
                    (day == 31)) {
                    return false;
                }
                if (month == 2) {
                    var leap = (year % 4 == 0 &&
                               (year % 100 != 0 || year % 400 == 0));
                    if (day>29 || (day == 29 && !leap)) {
                        return false;
                    }
                }
                return true;
            }
function validateCustomCreditCard(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
               // oCreditCard = new creditCard();
				 oCreditCard = valCreditCardArray;
                for (x in oCreditCard) {
                 if (form[oCreditCard[x][0]].type == 'text' ||
                        form[oCreditCard[x][0]].type == 'textarea' ||
                        form[oCreditCard[x][0]].type == 'password') {
						form[oCreditCard[x][0]].value=customTrimAll(form[oCreditCard[x][0]].value,form[oCreditCard[x][0]].type);
					}
                    if ((form[oCreditCard[x][0]].type == 'text' ||
                         form[oCreditCard[x][0]].type == 'textarea') &&
                        (form[oCreditCard[x][0]].value.length > 0)) {
                        if (!luhnCheck(form[oCreditCard[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oCreditCard[x][0]];
                            }
                            fields[i++] = oCreditCard[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusOnField(focusField);
                    alert(fields.join('\n'));
                }
                return bValid;
            }

            /**
             * Reference: http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
             */
            function luhnCheck(cardNumber) {
                if (isLuhnNum(cardNumber)) {
                    var no_digit = cardNumber.length;
                    var oddoeven = no_digit & 1;
                    var sum = 0;
                    for (var count = 0; count < no_digit; count++) {
                        var digit = parseInt(cardNumber.charAt(count));
                        if (!((count & 1) ^ oddoeven)) {
                            digit *= 2;
                            if (digit > 9) digit -= 9;
                        };
                        sum += digit;
                    };
                    if (sum == 0) return false;
                    if (sum % 10 == 0) return true;
                };
                return false;
            }

            function isLuhnNum(argvalue) {
                argvalue = argvalue.toString();
                if (argvalue.length == 0) {
                    return false;
                }
                for (var n = 0; n < argvalue.length; n++) {
                    if ((argvalue.substring(n, n+1) < "0") ||
                        (argvalue.substring(n,n+1) > "9")) {
                        return false;
                    }
                }
                return true;
            }
function validateCustomShort(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
            //    oShort = new ShortValidations();
				oShort=valShortArray;
                for (x in oShort) {
                 if (form[oShort[x][0]].type == 'text' ||
                        form[oShort[x][0]].type == 'textarea' ||
                        form[oShort[x][0]].type == 'password') {
						form[oShort[x][0]].value=customTrimAll(form[oShort[x][0]].value,form[oShort[x][0]].type);
					}
                    if ((form[oShort[x][0]].type == 'text' ||
                         form[oShort[x][0]].type == 'textarea' ||
                         form[oShort[x][0]].type == 'select-one' ||
                         form[oShort[x][0]].type == 'radio') &&
                        (form[oShort[x][0]].value.length > 0)) {
                        var iValue = parseInt(form[oShort[x][0]].value);
                        if (isNaN(iValue) || !(iValue >= -32768 && iValue <= 32767)) {
                            if (i == 0) {
                                focusField = form[oShort[x][0]];
                            }
                            fields[i++] = oShort[x][1];
                            bValid = false;
                       }
                    }
                }
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateCustomFloat(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
            //    oFloat = new FloatValidations();
				oFloat=valFloatArray;
                for (x in oFloat) {
                 if (form[oFloat[x][0]].type == 'text' ||
                        form[oFloat[x][0]].type == 'textarea' ||
                        form[oFloat[x][0]].type == 'password') {
						form[oFloat[x][0]].value=customTrimAll(form[oFloat[x][0]].value,form[oFloat[x][0]].type);
					}
                    if ((form[oFloat[x][0]].type == 'text' ||
                         form[oFloat[x][0]].type == 'textarea' ||
                         form[oFloat[x][0]].type == 'select-one' ||
                         form[oFloat[x][0]].type == 'radio') &&
                        (form[oFloat[x][0]].value.length > 0)) {
                        var iValue = parseFloat(form[oFloat[x][0]].value);
                        if (isNaN(iValue)) {
                            if (i == 0) {
                                focusField = form[oFloat[x][0]];
                            }
                            fields[i++] = oFloat[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateCustomEmail(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
              //  oEmail = new email();
				oEmail=valEmailArray;
                for (x in oEmail) {
                if (form[oEmail[x][0]].type == 'text' ||
                        form[oEmail[x][0]].type == 'textarea' ||
                        form[oEmail[x][0]].type == 'password') {
						form[oEmail[x][0]].value=customTrimAll(form[oEmail[x][0]].value,form[oEmail[x][0]].type);
					}
                    if ((form[oEmail[x][0]].type == 'text' ||
                         form[oEmail[x][0]].type == 'textarea') &&
                        (form[oEmail[x][0]].value.length > 0)) {
                        if (!checkCustomEmail(form[oEmail[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oEmail[x][0]];
                            }
                            fields[i++] = oEmail[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusOnField(focusField);
                    alert(fields.join('\n'));
                }
                return bValid;
            }

            /**
             * Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
             * http://javascript.internet.com
             */
            function checkCustomEmail(emailStr) {
               if (emailStr.length == 0) {
                   return true;
               }
               var emailPat=/^(.+)@(.+)$/;
               var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
               var validChars="\[^\\s" + specialChars + "\]";
               var quotedUser="(\"[^\"]*\")";
               var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
               var atom=validChars + '+';
               var word="(" + atom + "|" + quotedUser + ")";
               var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
               var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
               var matchArray=emailStr.match(emailPat);
               if (matchArray == null) {
                   return false;
               }
               var user=matchArray[1];
               var domain=matchArray[2];
               if (user.match(userPat) == null) {
                   return false;
               }
               var IPArray = domain.match(ipDomainPat);
               if (IPArray != null) {
                   for (var i = 1; i <= 4; i++) {
                      if (IPArray[i] > 255) {
                         return false;
                      }
                   }
                   return true;
               }
               var domainArray=domain.match(domainPat);
               if (domainArray == null) {
                   return false;
               }
               var atomPat=new RegExp(atom,"g");
               var domArr=domain.match(atomPat);
               var len=domArr.length;
               if ((domArr[domArr.length-1].length < 2) ||
                   (domArr[domArr.length-1].length > 3)) {
                   return false;
               }
               if (len < 2) {
                   return false;
               }
               return true;
            }
function validateCustomMask(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMasked = valMaskArray;
                for (x in oMasked) {
                if (form[oMasked[x][0]].type == 'text' ||
                        form[oMasked[x][0]].type == 'textarea' ||
                        form[oMasked[x][0]].type == 'password') {
						form[oMasked[x][0]].value=customTrimAll(form[oMasked[x][0]].value,form[oMasked[x][0]].type);
					}
                    if ((form[oMasked[x][0]].type == 'text' ||
                         form[oMasked[x][0]].type == 'textarea' ||
                         form[oMasked[x][0]].type == 'password') &&
                        (form[oMasked[x][0]].value.length > 0)) {
                        if (!customMatchPattern(form[oMasked[x][0]].value, oMasked[x][2]("mask"))) {
                            if (i == 0) {
                                focusField = form[oMasked[x][0]];
                            }
                            fields[i++] = oMasked[x][1];
                            bValid = false;
                        }
                    }
                }
                 
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }

            function customMatchPattern(value, mask) {
               var bMatched = mask.exec(value);
               if (!bMatched) {
                   return false;
               }
			   
               return true;
            }
function validateCustomMinLength(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
             //   oMinLength = new minlength();
			 oMinLength=valMinLengthArray;
                for (x in oMinLength) {
                 if (form[oMinLength[x][0]].type == 'text' ||
                        form[oMinLength[x][0]].type == 'textarea' ||
                        form[oMinLength[x][0]].type == 'password') {
						form[oMinLength[x][0]].value=customTrimAll(form[oMinLength[x][0]].value,form[oMinLength[x][0]].type);
					}
 
                    if (form[oMinLength[x][0]].type == 'text' ||
                        form[oMinLength[x][0]].type == 'textarea' ||
                        form[oMinLength[x][0]].type == 'password') {
                        var iMin = parseInt(oMinLength[x][2]("minlength"));
                        if (form[oMinLength[x][0]].value != "")
                        {
	                        if (!(form[oMinLength[x][0]].value.length >= iMin)) {
	                            if (i == 0) {
	                                focusField = form[oMinLength[x][0]];
	                            }
	                            fields[i++] = oMinLength[x][1];
	                            bValid = false;
	                        }
	                    }
                    }
                }
                if (fields.length > 0) {
                   focusOnField(focusField);
                   alert(fields.join('\n'));
                }
                return bValid;
            }
			
function customTrimAll(sString, type)
{
		while (sString.substring(0,1) == ' ')
		{
			sString = sString.substring(1, sString.length);
		}
		while (sString.substring(sString.length-1, sString.length) == ' ')
		{
			sString = sString.substring(0,sString.length-1);
		}
		if(type!=null && type!='textarea'){
			var finalStr="";
			while (sString.indexOf('<br>')!=-1){
				var startPos=sString.indexOf('<br>');
				finalStr=sString.substring(0,startPos);
				finalStr=finalStr + sString.substring(startPos+4,sString.length);
				sString=finalStr;
			}
			
			while (sString.indexOf('<p>')!=-1){
				var startPos=sString.indexOf('<p>');
				finalStr=sString.substring(0,startPos);
				finalStr=finalStr + sString.substring(startPos+3,sString.length);
				sString=finalStr;
			}
			while (sString.indexOf('</p>')!=-1){
				var startPos=sString.indexOf('</p>');
				finalStr=sString.substring(0,startPos);
				finalStr=finalStr + sString.substring(startPos+4,sString.length);
				sString=finalStr;
			}
			while (sString.indexOf('</br>')!=-1){
				var startPos=sString.indexOf('</br>');
				finalStr=sString.substring(0,startPos);
				finalStr=finalStr + sString.substring(startPos+5,sString.length);
				sString=finalStr;
			}
		}else if(type!=null){
			while (sString.indexOf('<br>')==0){
				finalStr=sString.substring(4,sString.length);
				sString=finalStr;
			}
			while (sString.indexOf('</br>')==0){
				finalStr=sString.substring(5,sString.length);
				sString=finalStr;
			}
		}
	return sString;
}

function validateCustomCondRequired(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
 	oRequired=valCondRequiredArray;
    for (x in oRequired) 
	{
		var theBlankValue=oRequired[x][3]("blankValue");
		if(theBlankValue==null || theBlankValue=="null"){
			theBlankValue="";
		}
		var theErrorMessage=oRequired[x][2];
		var theCondRequiredElemName=oRequired[x][1];
		var theElmenentName=oRequired[x][0];
        if ((form[oRequired[x][0]]!= null) &&
        	(customTrimAll(form[oRequired[x][0]].value,form[oRequired[x][0]].type) != '') &&
        	(customTrimAll(form[oRequired[x][0]].value,form[oRequired[x][0]].type) != theBlankValue)) 
         {
         	if(oRequired[x][1]==null || (customTrimAll(form[oRequired[x][1]].value,form[oRequired[x][0]].type)=='')){
                if (i == 0) {
                    focusField = form[oRequired[x][1]];
                }
                fields[i++] = oRequired[x][2];
                bValid = false;
             }
        }
    }
    if (fields.length > 0) {
       focusOnField(focusField);
       alert(fields.join('\n'));
    }
    return bValid;
}

function focusOnField(aFormElem){
if(aFormElem.className=='mceEditor'){
;
	}
	else{
  	 aFormElem.focus();
  	 }
  	 return true;
}