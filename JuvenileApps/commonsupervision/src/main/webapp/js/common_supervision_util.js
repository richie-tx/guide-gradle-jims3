function changeFormSettingsWithConfirm(theForm, theTargetString, button, theActionString, confirmMsg) {
	if(confirm(confirmMsg)){
	 changeFormActionURL(theForm, '/CommonSupervision/'+theActionString,false); 
	 changeFormTarget(theForm,theTargetString) ; 
	 //if the target is not a new window then disable to prevent multiple submissions
	  if(theTargetString != 'new') disableSubmit(button, theForm);
	  return true;
	}else return false;				 
} 

function changeFormSettings(theForm, theTargetString, button, theActionString) {
 changeFormActionURL(theForm, '/CommonSupervision/'+theActionString,false); 
 changeFormTarget(theForm,theTargetString) ; 
 //if the target is not a new window then disable to prevent multiple submissions
  if(theTargetString != 'new') disableSubmit(button, theForm); 
 } 
 
 function changeFormActionURL(formName, URL, doSubmit){
	var myForm=document.getElementById(formName);
	if(myForm == null)
	{
		myForm = document.getElementsByName(formName)[0];
	}

    if(myForm == null)

        myForm = formName;
	
	myForm.action = URL;
	if(doSubmit){
		myForm.submit();
	}
	return true;
}	
	function customCourtReset(theForm){
		theForm.reset();
		var x;
		if(courtCheckBoxArrPar!=null){
			for (x = 0;x < courtCheckBoxArrPar.length;x++)
			{
				reverseToggleCheckAll(courtCheckBoxArrPar[x],courtCheckBoxArrChld[x]);
			}
		}
		else{
			alert("is null");
		}
		return false;
	}
	
	function autoTab(input,len) {
   if(input.value.length >= len){
      input.value = input.value.slice(0, len);
      input.form[(getIndex(input)+1) % input.form.length].focus();
   }
return true;
}

function getIndex(input) {
   var index = -1;
   var i = 0;
   var found = false;
   while (i < input.form.length && index == -1)
   if (input.form[i] == input)index = i;
     else i++;
   return index;
} 
// End AutoTab Script
	
	function atLeastOneReq(arrToValidate){
		var atLeastOneEntered=false;
		if(arrToValidate!=null){
		    var elementEnt=null;
		    var elemName="";
		    
			for(i=0; i<arrToValidate.length; i++)
			{
				elemName=arrToValidate[i];
				elementEnt=document.getElementsByName(elemName)[0]
				if(elementEnt!=null){
					if(elementEnt.value !=null){
						if(trimAll(elementEnt.value)!=null && trimAll(elementEnt.value)!=''){
							return true;
						}
					}
				}
			}   
		}  
		if(atLeastOneEntered){
			return true;
		}
		else{
			alert("At least one entry is required.");
			return false;
		}
	}
	
	//this function alerts a message and then redirects to another page
	/*params: msg - message to be alerted location - new page to be redirected to
	*/
	function alertAndGo(msg, location){
		alert(msg);
		window.location.href=location;
	}
	
	//This function will toggle all of the named checkboxes the same value as the parent checkbox (theElement)
	function toggleCheckAll(theElement, name) 
	{ 
		theForm = theElement.form;
		var list = document.getElementsByName(name);
	 
		for(i=0; i<list.length; i++)
		{
			if(theElement.checked == true)
				list[i].checked="true";
			else
				list[i].checked=null;
		}     
	}
		
	function reverseToggleCheckAll(theElementName, name) 
	{ 
		var theElement=document.getElementById(theElementName);
		theForm = theElement.form;
		var list = document.getElementsByName(name);
	 	var totalChecked=0;
	 	var totalToCheck=0;
		for(i=0; i<list.length; i++)
		{
			
			if(list[i].type=="checkbox"){
				if(list[i].name==theElementName){
				}
				else{
					
					if(list[i].checked){
						totalChecked++;
					}
					else{
						theElement.checked=null;
						return;
					}
				}
			}
		}    
		theElement.checked=true;
	}
	
	//this function returns the current date in String format mm/dd/yyyy
	/*params:
	*/
	function getCurrentDate(){
		var theDate=new Date();
		var theMonth = theDate.getMonth()+1;
		var theDay = theDate.getDate();
		var theYear = theDate.getYear();

		if (theMonth < 10){
			theMonth = "0"+theMonth;
		}
		if (theDay < 10){
			theDay = "0"+theDay;
		}

		var dateStr = (theMonth + "/" + theDay + "/" + theYear);
		return dateStr;
	}
	
	function getCurrentTime(withAMPM){
		var currentTime = new Date()
		var hours = currentTime.getHours()
		var minutes = currentTime.getMinutes()
		var amPM = "";
		if (minutes < 10)
		minutes = "0" + minutes;

		/*if(hours > 11){
			amPM = "PM"
		}else{
			amPM = "AM"
			}
		if (hours > 12){
			hours = hours - 12;
			hours = "0" + hours;
		}*/

		var timeStr = hours + ":" + minutes
		if(withAMPM){
			timeStr = hours + ":" + minutes + " " + amPM;
		}

		return timeStr;
	}
	
	function getCurrentTime12Hour(){
		var currentTime = new Date()
		var hours = currentTime.getHours()
		var minutes = currentTime.getMinutes()
		var amPM = "";
		if (minutes < 10){
			minutes = "0" + minutes;
		}
		if (hours > 12){
			hours = hours - 12;
		}
		if (hours < 10){
			hours = "0" + hours;
		}	
		var timeStr = hours + ":" + minutes
		return timeStr;
	}	
	function convertTimeto2DigitHr(atime){
		if(atime!=null && atime.length==4 || atime.length==7){
			atime="0" + atime;
		}
		return atime;
	}

	//this function redirects to another page
	/*params: location - new page to be redirected to
	*/
	function goNav(location)
	{
		if (location == "back"){
			history.go(-1);
			}else{
				window.location.href=location;
			}
	}
	
	
		
	function checkAllBgColor(el, checkboxName)
	{		
		var theForm = el.form;
		var objCheckBoxes = document.getElementsByTagName("input");
		var countCheckBoxes = objCheckBoxes.length;	
	
		if(el.checked)
		{
			// set the check value for all check boxes
			for(var i = 0; i < countCheckBoxes; i++)
			{
				var curEl=objCheckBoxes[i];
				if (curEl.type=="checkbox"){
					if (curEl.id==checkboxName){
						objCheckBoxes[i].checked = true;
						chooseRow(objCheckBoxes[i]);				
					}
				}
			}		
			
		}else {
				for(var i = 0; i < countCheckBoxes; i++)
				{
					var curEl=objCheckBoxes[i];
				if (curEl.type=="checkbox"){
					if (curEl.id==checkboxName){
						objCheckBoxes[i].checked = false;
						chooseRow(objCheckBoxes[i]);				
					}
				}
				}
			}
	}
	
	//this function checksall checkboxes given the name - assumes name format is checkboxName[i].checkboxNameEnd
	 /*params: el - the checkAll checkbox form element
	      checkboxName - beginning part of the name
	      checkboxNameEnd - ending part of the name
	      numOfCheckboxes - how many checkboxes to be checked
	 */
	 function checkAllByName(el, checkboxName, checkboxNameEnd, numOfCheckboxes)
	 {
	  var theForm = el.form;
	  var checkAllName = el.name;
	 
	  if(el.checked)
	  {
	   // set the check value for all check boxes
	   for(var i = 0; i < numOfCheckboxes; i++){
	    var checkboxNameStr = checkboxName + "[" + i + "]." + checkboxNameEnd;
	    document.getElementsByName(checkboxNameStr)[0].checked = true;
	   }
	  }else {   
	   for(var i = 0; i < numOfCheckboxes; i++){
	    var checkboxNameStr = checkboxName + "[" + i + "]." + checkboxNameEnd;    
	    document.getElementsByName(checkboxNameStr)[0].checked = false;
	   }
	  }
	 }
		
			
	function uncheckCheckAll(el, checkAllName)
	{
		var theForm = el.form;
		if (!el.checked){
			theForm.elements[checkAllName].checked = false;
		}
	}
		
	function validateGroup1IsRequired(theForm)
	{
		if(theForm.group1Id.options[theForm.group1Id.selectedIndex].value == "")
		{
			alert("Please select Group 1");
			return false;
		}
		return true;
	}

	//changes the background color of the row where the checkbox is
	//row has incrementing number Id, and checkbox has same value
	//param: el - the form object radio/checkbox
	function chooseRow(el) {

		var	num = el.value;
		var theForm = el.form;
		//create row id
		id = num;
		var thisRow = document.getElementById(id);
		//checkbox is checked - change background color	- check if alternating row
		if (el.checked){
				if (el.type != "checkbox"){
						thisRow.className = "selectedRow";
				}else thisRow.className = "selectedRowGreen";
		}else thisRow.className = "";

		removeSelectedRow(theForm, el.name);

	}

		//pass in which form
	function removeSelectedRow(theForm, theRadioName){
		//get form which checkboxes are in
	  elem = theForm;

	  for(i=0; i<elem.length; i++) {
	    if(elem[i].type == "radio"){
	    	if(!elem[i].checked && !elem[i].disabled && elem[i].name==theRadioName){
	    		var	num = elem[i].value;
	    		var id = num;
	    		document.getElementById(id).className = "";
	    	}
	    }
	  }
	}
	
//	// Check for required Radio Button  THIS FUNCTION HAS BEEN DEPRECATED IN FAVOR OF THE BETTER ONE BELOW also called the same
	//name
//	function validateRadios(el, name, msg)
////	{
//		alert("3 arg one");
//		myOption = -1;
//		if (document.getElementsByName(name).length > 1)
//		
//		{
//			for (i=0; i<document.getElementsByName(name).length; i++)
///			{
//				if (document.getElementsByName(name)[i].checked)
//				{
//					myOption = i;
//				}
//			}
//	
//			if (myOption == -1)
///			{
//				alert(msg);
///				return false;
//			}
//	
//		} 
//	return true;
//	}
 	// Check for required Radio Button 
 	// el- the form
 	//name- the name of the radio group
 	// msg1- the message to display if they haven't picked a radio
 	// msg2- the message to display if their aren't any elements with that name
	function validateRadios(el, name, msg1, msg2)
	{
	
		myOption = -1;
		if (document.getElementsByName(name).length > 0)
		
		{
			for (i=0; i<document.getElementsByName(name).length; i++)
			{
				if (document.getElementsByName(name)[i].checked)
				{
					myOption = i;
				}
			}
	
			if (myOption == -1)
			{
				alert(msg1);
				return false;
			}
	
		} else {
			alert(msg2);
			return false;
		}
	return true;
	}
	//called to confirm the Cancel button
	//param: msg - passed to verify f(x)
	//param: location - where to send the user if they click OK on the confirm box - passed to goNav f(x)

	function cancelConfirm(msg, location){
		if (verify(msg)){
			goNav(location);
		}else {
			return false;
		}
	}

	//called by cancel confirm
	//returns true/false
	//param: msg - message in the confirm box
	function verify(msg){
		return confirm(msg);
	}
	
	//this function opens a new window	
	//param: url - location address of new window
	function openWindow(url)
	{
		var newWin = window.open(url, "pictureWindow", "height=300,width=500,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
	}
   
	//show hide where there is an expand (plus sign)
	/*params: spanName - name of the span to hide or show - image has same id as span -"Span"
	*/
	function showHide(spanName, format, path){
			
		var appendName = spanName + "Span";
		var theClassName = document.getElementById(appendName).className;
		if (theClassName=='visible' || theClassName=='visibleTR' || theClassName=='visibleTable'){
			window.document.images[spanName].src=path+"images/expand.gif";
			document.getElementById(appendName).className='hidden';
		}else {
				window.document.images[spanName].src=path+"images/contract.gif";
				if(format=="row"){
						document.getElementById(appendName).className='visibleTR';
					}else if(format=="table"){
							document.getElementById(appendName).className='visibleTable';
						}else {
								document.getElementById(appendName).className='visible';
							}
			}
	}
	
	//show hide where there is an expand (plus sign) and group multiple referrals under it
	function showHideMultipleReferrals(spanName, format, path){		
		var appendName = spanName + "Span";
		var theElements = document.getElementsByTagName('TR');
		var groupRows = new Array();
		for(i = 0; i < theElements.length; i++) {          
			currentElement = theElements[i];
			if((currentElement.id.indexOf(appendName)) != -1){
				var theClassName = theElements[i].className;
				groupRows[groupRows.length++] = currentElement;
			}
		}
		// determine whether to mark records as hidden or visible
		for(i in groupRows) {
			var theClassName = groupRows[i].className;
			if (theClassName.indexOf('visibleTR') >= 0){
				document.getElementById(spanName).src=path+"images/expand.gif";
				groupRows[i].className=groupRows[i].className.replace("visibleTR","hidden");
			}else if (theClassName.indexOf('visibleTable') >= 0){
				document.getElementById(spanName).src=path+"images/expand.gif";
				groupRows[i].className=groupRows[i].className.replace("visibleTable","hidden");
			}else if (theClassName.indexOf('visible') >= 0 ){
				document.getElementById(spanName).src=path+"images/expand.gif";
				groupRows[i].className=groupRows[i].className.replace("visible","hidden");
			}else {
					document.getElementById(spanName).src=path+"images/contract.gif";
					if(format=="row"){
						groupRows[i].className=groupRows[i].className.replace("hidden","visibleTR");
					}else if(format=="table"){
						groupRows[i].className=groupRows[i].className.replace("hidden","visibleTable");
					}else {
						groupRows[i].className=groupRows[i].className.replace("hidden","visible");
					}
			}
		}
		
	}
	

	//show hide where there is an expand (plus sign) - and u want to show multiple rows simultaneously
 	/*params:
 		imgName - name of the + or - sign - has to be unique
 		rowPrepend - ID prepend of the row - has to be unique
 		rowNums - number of rows to hide/show - size of the collection
 		path - img path (<msp tag>)
 	*/

 	function showHideMulti(imgName, rowPrepend, rowNums, path){

 	
  var appendName = rowPrepend + rowNums;

  if (imgName != "")
  {
  	var currentImage = window.document.images[imgName].src;

	  if (currentImage.indexOf("contract") < 0)
	  {
	   window.document.images[imgName].src=path+"images/contract.gif";
	  } else{
	   window.document.images[imgName].src=path+"images/expand.gif";
	  }
	}

  for (var intI=0; intI<rowNums; intI++) 
  {
   appendName = rowPrepend + intI;
   
   var element = document.getElementById(appendName);
   if(element != null)
   {
	   if (element.className=='visibleTR')
	   {
		   	element.className='hidden';
	   }	 
	   else
	   {
	    	element.className='visibleTR';
	   }
   }
   
   
  }

 }

//show - basic show/hide functionality
 /*params:
 what - id of what to show or hide
 hide - 0=hide 1=show
 format - table/row - no entry standard Block 
 */

	function show(what, hide, format){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else if(format=="row"){
				document.getElementById(what).className='visibleTR';
			}else if(format=="table"){
					document.getElementById(what).className='visibleTable';
				}else if(format=="inline"){
					document.getElementById(what).className='visibleInline';
					}else {
						document.getElementById(what).className='visible';
					}
	}

// To check/uncheck checkboxes in children.	
	function ob_t2c(el) {			
			var ob_t2in=el.parentNode.parentNode.parentNode.parentNode.nextSibling.getElementsByTagName("input");
			for (var i=0; i<ob_t2in.length; i++) {
				if (el.checked==true) {
						ob_t2in[i].checked=true;
					}
				else {
					ob_t2in[i].checked=false;
				}
			}
		}

function parent(e,id){
	if (e.checked==false) {
		document.getElementById(id).checked=false;
	}
}

//for conditions and court policies - do not render row if event count is immediate
	/*params: el - the dropdown obj
	*/
function checkEventCount(el){
		if(el.options[el.selectedIndex].value == 1 || el.options[el.selectedIndex].value == ""){
			var periodValElem=document.getElementsByName('periodValue');
			if(periodValElem!=null){
				periodValElem[0].value="";
			}
			var periodElem=document.getElementsByName('periodId');
			if(periodElem!=null){
				periodElem[0].options[0].selected=true;
			}
			show("periodRow", 0, "row");
		
		}else show("periodRow", 1, "row");
	}

//for group drop downs - imitating configurator
	/*params: groupNum - which group select u are activating, theForm - the form obj
	*/
function enableSelect(groupNum, theForm)
	{
		if(groupNum == 2)
		theForm.group2.disabled=false;
		else if(groupNum == 3)
		theForm.group3.disabled=false;

	}

// added by Kiran for Preview and Filter Functionality -->
function resetTarget(theForm)
{
	theForm.target = "content";
	return true;
}

function filterDetailDictionary(theForm)
{

		if(theForm.ddName.value== '' && theForm.ddDescription.value == '' )
		{
            theForm.ddName.focus();
            alert('Please enter value for Name or Description');
			return false;
		}
		else
		{
			
			theForm.target = "content";
			return true;
		}
}
<!-- added by Kiran for Preview and Filter Functionality -->

function validateCourtsVariableElements(theForm)
{
	for(i=0;i<1000;i++)
	{
		if(theForm["ECVE["+i+"].variableElements[0].fixed"] == null)
			break;
		
		for(j=0;j<1000;j++)
		{
			var vereValue = theForm["ECVE["+i+"].variableElements["+j+"].value"]
			var vereIsFixed = theForm["ECVE["+i+"].variableElements["+j+"].fixed"]
			var vereDropDown = theForm["ECVE["+i+"].variableElements["+j+"].valueId"]
			
			if(vereIsFixed == null)
				break;
			if(vereValue != null && vereIsFixed.value == "true" && trimAll(vereValue.value) == "")
			{
				alert("Please enter a value for Fixed Detail Element");
				vereValue.value=trimAll(vereValue.value);
				vereValue.focus();
				return false;
			}
			
			else if(vereDropDown != null && vereIsFixed.value == "true")
			{
				if(vereDropDown.value == "")
				{
					alert("Please enter a value for Fixed Detail Element");
					vereDropDown.focus();
					return false;
				}
			}
		}
	}
}

function validateVariableElements(theForm, varRequired)
{
	i=0;
	while(true && i < 5000)
	{
		var vereValue = theForm["variableElementResponseEventsArray["+i+"].value"];
		var vereIsFixed = theForm["variableElementResponseEventsArray["+i+"].fixed"]; 
		var vereDropDown = theForm["variableElementResponseEventsArray["+i+"].valueId"]
		
		if(vereIsFixed == null && vereValue==null && vereDropDown==null)
		{
			break; // nothing to check no elements
		}
		
		if(varRequired){  // used to indicate special condition and variable variables are required as well as fixed
			if(vereValue!=null && trimAll(vereValue.value) == ""){
				alert("All elements required: Please enter a value for the Variable Detail Element");
				vereValue.value=trimAll(vereValue.value);
				vereValue.focus();
				return false;
			}
			if(vereDropDown!=null && vereDropDown.value == ""){
				alert("All elements required: Please enter a value for the Variable Detail Element");
				vereDropDown.focus();
				return false;
			}
		}
		else{
			if(vereValue != null && vereIsFixed.value == "true" && trimAll(vereValue.value) == "")
			{
				alert("Please enter a value for Fixed Detail Element");
				vereValue.value=trimAll(vereValue.value);
				vereValue.focus();
				return false;
			} else if(vereDropDown != null && vereIsFixed.value == "true")
			{
				if(vereDropDown.value == "")
				{
					alert("Please enter a value for Fixed Detail Element");
					vereDropDown.focus();
					return false;
				}
			}
		}
		i++;
	}
	return true;
}

function isAnySelected(form, componentName)
{
	var selected = false;
	
	if(form[componentName].type == 'radio')
	{
		if(form[componentName].checked)
			selected = true;
	}
	else if(form[componentName].length) //if length exist, the object is an array
	{
		for(var i = 0; i < form[componentName].length; i++)
		{
			if(form[componentName][i].checked)
			{
				selected = true;
				break;
			}
		}
	}
	
	if(!selected)
		alert('Please choose one from the search result set in order to proceed');
	return selected;
	
}

//This function will check if the user has selected "Select Court(s) option, then the user need to select at least a court
function validateSelectedCourts(form)
{

	var elem=document.getElementsByName("allCourtsSelected");

	if(elem[0].value="false" || !elem[0].checked)
	{
		
		var isSelected = false;
		
		for(var i = 0; i < form.elements.length; i++)
		{
		
			
			if(form.elements[i].type == 'checkbox' && form.elements[i].checked)
			{
				isSelected = true;
				break;
			}
			else{
				if(form.elements[i].type == 'hidden' && form.elements[i].name=="aCheckBoxIsSelected" && form.elements[i].value!=""){
					isSelected = true;
					break;
				}
			}
		}
		
		if(!isSelected)
			alert('Please select at least one court in order to proceed.');
		return isSelected;
		
	}
		
	return true;
	
}


function validateEventType(theForm)
{
	var elem=theForm['selectedEventTypeIds'];
	if(elem==null)
	{
		return true;
	}
	var eventType = theForm['selectedEventTypeIds'].value;
	if(eventType!=null && eventType != '')
	{

			var eventCount = theForm['eventCount'].value;
			if(eventCount == '')
			{

				alert('Event Count is required when Event Type is specified.');
				return false;
			}

			return validateEventCount(theForm);
	}
	
	return true;
	
}

//function to enfoce that period value is a required field when event count is > 1
function validateEventCount(theForm)
{
	var eventCount = theForm['eventCount'].value;
	if(eventCount > 1)
	{
		if(trimAll(theForm['periodValue'].value) == '')
		{
			alert('Period Value is a required field when event count is more than 1.');
			return false;
		}
		else if(!(isNumeric(theForm['periodValue'].value,false))){
			alert('Period Value must be numeric');
			return false;
		}
	}
	return true;
}

// Check that a string contains only numbers
function isNumeric(string, ignoreWhiteSpace) 
{
   if (string.search) 
   {
      if ((ignoreWhiteSpace && string.search(/[^\d\s]/) != -1) 
      	 || (!ignoreWhiteSpace && string.search(/\D/) != -1)) 
      {
      	 return false;
      }
   }
   return true;
}

function leftTrim(sString)
{
	while (sString.substring(0,1) == ' ')
	{
		sString = sString.substring(1, sString.length);
	}
	return sString;
}

function rightTrim(sString)
{
	while (sString.substring(sString.length-1, sString.length) == ' ')
	{
		sString = sString.substring(0,sString.length-1);	
	}
	return sString;
}

//The allTrim() JavaScript function combines both leftTrim() and rightTrim() functions:
function trimAll(sString)
{
	while (sString.substring(0,1) == ' ')
	{
		sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' ')
	{
		sString = sString.substring(0,sString.length-1);
	}
	return sString;
}

	
	// Passing in a boolean true for disableEnter will disable the Enter Key
// Use in conjection with an event such as : onKeyDown="checkEnterKey(event,true)"
function checkEnterKey(evt,disableEnter){
	var currentElement=evt.srcElement;
	 var key = evt.keyCode;
    if(event.ctrlKey==true){
    	if(key==78){  // the n key
    		alert("Sorry opening a new browser window is not allowed.");
    		disableEvent(evt);
    		return false;
    	}	
    }
    
     if (disableEnter==true && key==13){
     	if(currentElement!=null){
     		if(currentElement.type == 'textarea'){
     			// alert("Have a text area don't do anything with the enter key");
     			return true;
     		}
     	}
		alert("This page has multiple submit buttons. \n\n  Please select appropriate submit button.");
	 	disableEvent(evt);
		return false;
	 }
	 return true;
   } 
   
   // Passing in a boolean true for disableEnter will disable the Enter Key
// Use in conjection with an event such as : onKeyDown="checkEnterKey(event,true)"
function checkEnterKeyAndSubmit(evt,disableEnter){
	var currentElement=evt.srcElement;
	 var key = evt.keyCode;
    if(event.ctrlKey==true){
    	if(key==78){  // the n key
	    		alert("Sorry opening a new browser window is not allowed.");
	    		disableEvent(evt);
	    		return false;
    	}	
    }
     if (disableEnter==true && key==13){
     	if(currentElement!=null){
     		if(currentElement.type == 'textarea'){
     			// alert("Have a text area don't do anything with the enter key");
     			return true;
     		}
     	}
     	var foundBtn=getSubmitBtn();
    	if(foundBtn==null){
			alert("This page has multiple submit buttons. \n\n  Please select appropriate submit button.");
		 	disableEvent(evt);
			return false;
		}
		else{
			foundBtn.click();
			return false;
		}
	 }
	 return true;
   } 
   
   function disableEvent(evt){
	evt.returnValue=false; 
	evt.cancel = true;
	}
   
   function getSubmitBtn(){
   		
   			var subBtnElems=document.getElementsByName("submitAction");
   			var btnToFire=null;
   			var counter=0;
   			if(subBtnElems==null){
   				return null;
   			}
   			else{
   				 for(i=0; i<subBtnElems.length; i++) {
				    if((subBtnElems[i].value.match("back"))==null && 
				    	(subBtnElems[i].value.match("Back"))==null && 
				    	(subBtnElems[i].value.match("BACK"))==null && 
				    	(subBtnElems[i].value.match("cancel"))==null && 
				    	(subBtnElems[i].value.match("Cancel"))==null && 
				    	(subBtnElems[i].value.match("CANCEL"))==null && 
				    	(subBtnElems[i].value.match("reset"))==null && 
				    	(subBtnElems[i].value.match("Reset"))==null && 
				    	(subBtnElems[i].value.match("RESET"))==null && 
				    	(subBtnElems[i].value.match("refresh"))==null && 
				    	(subBtnElems[i].value.match("Refresh"))==null && 
				    	(subBtnElems[i].value.match("REFRESH"))==null 
				    	){
				    	btnToFire=subBtnElems[i];
				    	counter++;
				    }
				  }
				  if(counter!=1){
				    return null;
				  }
				  else{
				  	return btnToFire;
				  }
				    
   			}
   }

//This function should be used whenever a submit button occurs
function disableSubmit(button, form)
{
	var hasURLExtensions=-1;
  	var myAction=form.action;
  	hasURLExtensions=myAction.indexOf("?");
  	var btnVal=button.value;
  	var hasAmpersendInValue;
  	hasAmpersendInValue=btnVal.indexOf("&");
  	if(hasAmpersendInValue!=-1){
  		btnVal=btnVal.replace(/&/,"%26");
  	}
  	if(hasURLExtensions!=-1){
       form.action += "&"+button.name+"="+btnVal;
 	}
  	else{
      form.action += "?"+button.name+"="+btnVal;
  	}
  	form.submit();
 	button.disabled=true;
  	return false;
}

// Compares if Date 1 is greater than or equal to Date 2 returns fals if not true
function compareDate1GreaterEqualDate2(dateValAsString1, dateValAsString2)
{   
	
	if ((dateValAsString1 != null && dateValAsString1 != "") && (dateValAsString2 != null && dateValAsString2 != ""))
	{   
		var date1=new Date(dateValAsString1);
		var date2=new Date(dateValAsString2);
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(0);
		date2.setMinutes(0);
		date2.setSeconds(0);
		if ((date1 >= date2))			
		{   
    		return true;
		}
		else{
			return false;
		}
	}
	else{
		return false;
	}
}

// Compares if Date 1 is greater than Date 2 returns fals if not true
function compareDate1GreaterDate2(dateValAsString1, dateValAsString2)
{   
	
	if ((dateValAsString1 != null && dateValAsString1 != "") && (dateValAsString2 != null && dateValAsString2 != ""))
	{   
		var date1=new Date(dateValAsString1);
		var date2=new Date(dateValAsString2);
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(0);
		date2.setMinutes(0);
		date2.setSeconds(0);
		if ((date1 > date2))			
		{   
    		return true;
		}
		else{
			return false;
		}
	}
	else if (dateValAsString1 != null &&  dateValAsString1 != "" && (dateValAsString2 == null || dateValAsString2 == "")){
		return true;
	} else {
		return false;
	}
}


// 01/30/2006 Manjula Bellare - JIMS200027748 - Compare date fields - effectiveDate and inactiveDate.
// Compare date fields effectiveDate and inactiveDate. 
function compareDate(theForm)
{   
	if (theForm.inactiveDate != null && theForm.inactiveDate.value != "")
	{   
		if ((theForm.effectiveDate.value == theForm.inactiveDate.value) || (new Date(theForm.effectiveDate.value) > (new Date(theForm.inactiveDate.value))))			
		{   
    		alert('Please enter an Inactive Date later than Effective Date');
        	return false;
		}
	}
	else
		return true;
}

// Kiran Krishnamurthy
// For PASO Printing functionality.   

function changeFormTarget(theForm, theTargetString) 
{   
	if(theTargetString==null || theTargetString=="") {
	    theForm.target = "content";
	}
	else {
	    theForm.target=theTargetString;
	}

	return true;
}

function showBtnGrp(btnGroupName, breakerSymbol,isHistorical,orderChainNum,x,versionNum){
	var actualElement;
	var btnGroupId;  // id of the btn group
	var btnUniqueName;  // name of btn
//	var splitStrArr=new Array();
//	splitStrArr=btnGroupName.split(breakerSymbol);
//	btnGroupId=splitStrArr[0];
//	btnUniqueName=splitStrArr[1];
	renderButtonsInGrp(btnGroupName,isHistorical,orderChainNum,"",versionNum);
	
//	if(btnGroupId!=null && splitStrArr.length==2 && btnGroupId!=""){
//		var allElements=document.getElementsByName(btnGroupId);
//		if(allElements!=null && allElements.length>0){
//			for(var loopX=0;loopX<allElements.length;loopX++){
//				actualElement=allElements[loopX];
//				var compareStr="";
//				if(document.all){
//					compareStr=actualElement.name;
//				}
//				else
//					compareStr=actualElement.id;
//				if(compareStr== splitStrArr[1]){
//					actualElement.className='visible';
					// show hide the element
//				}
//				else{
//					actualElement.className='hidden';
					// show hide the element
//				}		
//			}			
//		}
//	}
	return true;
}	

function multiSelectFix(theSelect){
	var theForm = theSelect.form;
	var hasBlank = false;
	var somethingSelected=false;
	var notSelected = 0;
	var selected = new Array();
	for (var i = 0; i < theSelect.options.length; i++) {
		if (theSelect.options[ i ].selected) {
			if(theSelect.options[i].value==""){
				hasBlank=true;
			}
			somethingSelected=true;
			selected.push(i);
		}
	}	
	if(hasBlank){
	 	var loopX=0;
		for (var j = 0; j < selected.length; j++) {
			loopX=selected[j];
			theSelect.options[ loopX ].selected=false;
		}
		theSelect.options[0].selected=true;
	}		
}

	function checkGroupChange(el, groupNum, form){
		if (groupNum == 1){
			if (el.options[el.selectedIndex].value != form.selectedGroup1.value){
				alert("All associations will be lost if there is a change to any group");
			}
		}else if (groupNum == 2){
			if (el.options[el.selectedIndex].value != form.selectedGroup2.value){
				alert("All associations will be lost if there is a change to any group");
			}
		}
		else if (groupNum == 3){
			if (el.options[el.selectedIndex].value != form.selectedGroup3.value){
				alert("All associations will be lost if there is a change to any group");
			}
		}
		return true;
	}	
	
	function checkCategoryChange(el, groupNum, form){
		// As of 04/04/2007 there are not any plans to have Consequesnce or department policies in JUV therefore this code is being commented out as
		// there is a potential for use later.
	//	if (groupNum == 1 && el.options[el.selectedIndex].value != ""){
	//		if (el.options[el.selectedIndex].value != form.selectedGroup1.value){
	//			alert("All associations will be lost if there is a change to Category")
	//		}
	//	}else if (groupNum == 2 && form.group1Id.value == form.selectedGroup1.value && el.options[el.selectedIndex].value != ""){
	//		if (el.options[el.selectedIndex].value != form.selectedGroup1.value){
	//			alert("All associations will be lost if there is a change to Type")
	//		}
	//	}
		return true;
	}	

function isDate(value) {
	// Checks for the following valid date formats:
	// MM/DD/YY   MM/DD/YYYY   MM-DD-YY   MM-DD-YYYY
	// Also separates date into month, day, and year variables	
	//var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	
	// To require a 4 digit year entry, use this line instead:
	// var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
	
	// To require a 2 digits day, 2 digits month, 4 digits year and no dash entry, use this line:
	 var datePat = /^(\d{2})(\/)(\d{2})\2(\d{4})$/;
	 
	
	var matchArray = value.match(datePat); // is the format ok?
	if (value != "")
		{	
			if (matchArray == null) {
				return false;
			}
			month = matchArray[1]; // parse date into variables
			day = matchArray[3];
			year = matchArray[4];
			if (month < 1 || month > 12) { // check month range
				return false;
			}
			if (day < 1 || day > 31) {
				return false;
			}
			if ((month==4 || month==6 || month==9 || month==11) && day==31) {
				return false
			}
			if (month == 2) { // check for february 29th
				var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
				if (day>29 || (day==29 && !isleap)) {
					return false;
				 }
			}
	}
	return true;  // date is valid
	}
	
//this function confirms deletes that are hyperlinks
//pass in the confirm message and the path if the user selects OK
function confirmDeleteLink(msg, forwardURL){
	if (confirm(msg)){
		if (forwardURL != ""){
			goNav(forwardURL);
			return true;
		}
	}
}
//***Begin back to top Script - this renders the back to top link at the bottom of most pages
//this script also lives in app.js
function renderBackToTop(){
	document.write("[<a href=\"javascript:void(0);\" onclick=\"goNav('#top')\">Back to Top</a>]");
}
//**** End Begin back to top Script
 

function trimCasenote(elName){	
	document.getElementsByName(elName)[0].value = trimTextarea(document.getElementsByName(elName)[0].value); 																											
	myReverseTinyMCEFix(document.getElementsByName(elName)[0]);	
}

//replaces all non breaking spaces (&#160; - inserted by tinyMCE) with spaces and then trims them out (trailing and preceding)
function trimTextarea(sString){
	sString = sString.replace(/&#160;/g, " ");
	/* Lookup the LENGTH of string. */
	var LENGTH = sString.length;
	/* Find the first character that is not whitespace. */
	var beginIndex = 0;
	while (beginIndex != LENGTH && sString.charAt(beginIndex) <= " ") {
	beginIndex++;
	}

	/* Find the last character that is not whitespace. */
	var endIndex = LENGTH;
	while (endIndex != beginIndex && sString.charAt(endIndex - 1) <= " ") {
	endIndex = endIndex - 1;
	}
			
	sString = sString.substring(beginIndex, endIndex);
	
	return sString;


}

//checks that tinyMCE text areas do not just contain <br>'s or blank paste from Word
function validateForBROnly(el, errorMsg){
// no text entered	
		if (el == ""){
			alert(errorMsg);
			return false;
		}
// remove most common hidden tags from input		
		var xtext = el.replace(/<br>|<br \/>|<p>|<\/p>|&nbsp;|<strong>|<\/strong>|<em>|<\/em>|<u>|<\/u>|&#160;/gi,'');
// only return and spaces entered		
		if (xtext == "") {
			alert(errorMsg);
			return false;
		}
		var wRegExp = /\w/;  //match any word character(alphanumeric)
// only spaces or blank lines pasted from Word
		if (wRegExp.test(xtext,wRegExp) == false) {
			alert(errorMsg);
			return false;
		}											
	return true;																
}


//renders an appropriately sized scrolling div depending upon the result size
function renderScrollingArea(numberOfItems){
	if (numberOfItems >= 0 && numberOfItems < 8){
		document.write("<div style='border:1px solid black; position:relative;'>")
	}else if (numberOfItems > 7 && numberOfItems < 31){
	 	document.write("<div class=\"scrollingDiv200\">")
	}else if (numberOfItems > 30 && numberOfItems < 51){
	 	document.write("<div class=\"scrollingDiv300\">")
	}else {
	 	document.write("<div class=\"scrollingDiv400\">")
	}
}

function textLimit(field, maxlen) {
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
		alert("Your input has been truncated to " + maxlen +" characters!");	
	}
}
