$(document).ready(function(){
	$(function(){
		  $('input[type="radio"]').click(function(){
		    if ($(this).is(':checked'))
		    {
		      var radioName = $(this).attr("name");
		      $("input[name= '"+ radioName + "']").css("box-shadow","none");
		    }
		  });
	});
})

function autocheckChronic( selectedObj )  
{ // chronicCheckbox0
	var theID = selectedObj.id.substring( 15 )  ; 
	var newID = "nonChronicCheckbox" + (theID) ;


	if( selectedObj.checked )
	{ 
		document.getElementById(newID).checked = true ;
	}
}

function autoUncheckChronic( selectedObj )
{
	var theID = parseInt( selectedObj.id.substring( 18 ) )  ;
	var newID = "chronicCheckbox" + (theID) ;

	if( selectedObj.checked == false )
	{
		document.getElementById(newID).checked = false ;
	}   
}

function enableFormElement(questionId) 
{
	el = document.getElementById(questionId);
	if (el != null)
	{	
		if (el.type == "checkbox")
		{	
			el.checked = false;
		}
		el.disabled = "" ;
	} 
//	document.getElementById(questionId).disabled = "" ;
}

function disableFormElement(questionId) 
{
	el = document.getElementById(questionId);
	if (el != null)
	{	
		if (el.type == "select-one" || el.type == "select-multiple")
		{
			el.selectedIndex = 0;
			el.value = "";
		} else {	
			el.checked = "";
			el.disabled = "disabled" ;
		}
	} else {
		for (e=0; e < document.forms[0].length; e++)
		{
			if (document.forms[0].elements[e].onclick != null)
			{
				if (document.forms[0].elements[e].onclick.toString().indexOf(questionId) > 0)
				{
					document.forms[0].elements[e + 2].checked = "";
					document.forms[0].elements[e + 3].checked = "true";
					break;
				}	
			}
		}	
	}
//	document.getElementById(questionId).checked = "" ;
//	document.getElementById(questionId).disabled = "disabled" ;
}

function textCounter(field, maxlen) {
	if (field.value.length > maxlen + 1) {
		alert("Your input has been truncated to " +maxlen +" characters.");
	}

	if (field.value.length > maxlen) {
		field.value = field.value.substring(0, maxlen);
	}
} 

function Trim(s)
{
    // Remove leading spaces and carriage returns
    while( (s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r') )
    { 
      s = s.substring( 1,s.length ); 
    }
	  
    // Remove trailing spaces and carriage returns
    while( (s.substring(s.length -1 ,s.length) == ' ') || (s.substring(s.length -1,s.length) == '\n') || (s.substring(s.length -1,s.length) == '\r'))
    { 
      s = s.substring(0,s.length -1); 
    }
	    
    return s;
} 

function isNumeric(value)
{
	var numericRegExp = /^-?\d+$/;
	return numericRegExp.test(Trim(value),numericRegExp);
}

function validateCompletionFields(thisForm) 
{
	
	var collateralVisits = document.getElementById('cVisits');
	var faceToFaceVisits = document.getElementById('f2fVisits');
	var courtDispositionTJPC = document.getElementById('courtDispositionTJPC');

	collateralVisitsNumericMessage = "Collateral Visits must be a numeric value.";
	faceToFaceVisitsNumericMessage = "Face to Face Visits must be a numeric value.";
	
	collateralVisitsRequiredMessage = "Collateral Visits is required.";
	faceToFaceVisitsRequiredMessage = "Face to Face Visits is required.";
	courtDispositionTJPCRequiredMessage = "Court Disposition TJPC is required.";
	
	
	// Collateral Visits 
	collateralVisits.value = Trim(collateralVisits.value);
	if ( collateralVisits.value.length < 1)  {
		alert( collateralVisitsRequiredMessage );
		collateralVisits.focus();
		return false;
	} else if (!isNumeric(collateralVisits.value) ) {
		alert( collateralVisitsNumericMessage );
		collateralVisits.focus();
		return false;
	}
	
	// Face To Face Visits  
	faceToFaceVisits.value = Trim(faceToFaceVisits.value);
	if ( faceToFaceVisits.value.length < 1) {
		alert( faceToFaceVisitsRequiredMessage );
		faceToFaceVisits.focus();
		return false;
	} else if (!isNumeric(faceToFaceVisits.value) ) {
		alert( faceToFaceVisitsNumericMessage);
		faceToFaceVisits.focus();
		return false;
	}
	
	//Court Disposition TJPC 
	if ( courtDispositionTJPC.selectedIndex == 0 ) {
        alert ( courtDispositionTJPCRequiredMessage );
        courtDispositionTJPC.focus();
        return false;
    }
}

function validateFields(thisForm) 
{
	answerMessage1 = "A required question has not been answered - RADIO";

	answerMessage2 = "A required question has not been answered - DROPDOWN or MULTISELECT";
	
	answerMessage3 = "A required question has not been answered - TEXTAREA";
	
	answerMessage4 = "A required question has not been answered - TEXTBOX";
	
	answerMessage5 = "A question requires numerics only - TEXTAREA";
	
	answerMessage6 = "A question requires numerics only - TEXTBOX";
	
	answerMessage7 = "A date question does not allow future dates, date can not be greater than: " + getCurrentDate();

	answerMessage8 = "A required question has not been answered - CHECKBOX";
	
	for( var i = 0; i < thisForm.length; i++ ) 	{
		if( thisForm.elements[i].parentNode.parentNode.className != "hidden" )  {
			var myRegExpReqTrue = /requiredtrue/;
			var myRegExpNumTrue = /numerictrue/;
			var myRegExpFutDTrue = /allowsFutureDatesfalse/;
			var titleStr = thisForm.elements[i].title;
			var matchPosReqTrue = titleStr.search(myRegExpReqTrue);
			var matchPosNumTrue = titleStr.search(myRegExpNumTrue);
			var matchPosFutDTrue = titleStr.search(myRegExpFutDTrue);
			
			if(matchPosFutDTrue != -1) { 
				//Text
			    if(thisForm.elements[i].type == 'text')  {
			    	var componentName = thisForm.elements[i].name;
			        elem = document.getElementsByName(componentName);
			        elemVal = elem[0].value;
					
			        if( Trim(elemVal) != "" )  {
						if(compareDates(elemVal,'MM/dd/yyyy', getCurrentDate(),'MM/dd/yyyy')==1)  {
							alert( answerMessage7 );
				        	elem[0].focus();
							return false;
						}	
			        }
			    }
			} //End - if(matchPosFutDTrue != -1)

			if(matchPosReqTrue != -1)
			{
				//alert("titleStr: " + titleStr + " ,matchPosReqTrue:" + matchPosReqTrue);
				//Radio Button
				if(thisForm.elements[i].type == 'radio') {
					var componentName = thisForm.elements[i].name;
					//alert (componentName);
					var hasOne = false;
			        var radioList = document.getElementsByName(componentName);

			        for( var x = 0; x < radioList.length; x++ ) {
			        	if( radioList[x].checked )  {
			            	hasOne = true;
			            	$('input[type=radio]').css("box-shadow", "none");
			            	break;
			          	}
			      	}

			        if (!hasOne) {
			        	alert( answerMessage1 );
			        	//radioList.focus();
			        	radioList[0].focus();
			        	var radioId = radioList[0].id;
			        	$("#"+radioId).css("box-shadow","1px 1px 2px 2px grey");
						return false;
			        }    
			    }
				//Dropdown
				else if( thisForm.elements[i].tagName == 'SELECT')  {
					var componentName = thisForm.elements[i].name;
					//alert (componentName);
				    elem = document.getElementsByName(componentName)[0];
				    elemVal = elem.value;
				      
				    if( elemVal == "" ) {
				    	alert( answerMessage2 );
				    	elem.focus();
						return false;
				    }
				} 
				//TextArea
				else if( thisForm.elements[i].tagName == 'TEXTAREA' )  {
					var componentName = thisForm.elements[i].name;
			        elem = document.getElementsByName(componentName)[0];
			        elemVal = elem.value;

			        if( Trim(elemVal) == "" ) {
			        	alert( answerMessage3 );
			        	elem.focus();
						return false;
			        }
			        
			      }
				//Text
			    else if(thisForm.elements[i].type == 'text') {
			    	var componentName = thisForm.elements[i].name;
			        elem = document.getElementsByName(componentName);
			        elemVal = elem[0].value;
			        
			        if( Trim(elemVal) == "" ) {
			        	alert( answerMessage4 ); 
			        	elem[0].focus();
						return false;
			        }
			    }//checkbox
			    else if(thisForm.elements[i].type == 'checkbox')  {
					var componentName = thisForm.elements[i].name;
					//alert (componentName);
					var hasOne = false;
			        var radioList = document.getElementsByName(componentName);

			        for( var x = 0; x < radioList.length; x++ ) {
			        	if( radioList[x].checked ) {
			            	hasOne = true;
			            	break;
			          	}
			      	}
 
			        if (!hasOne) {
			        	alert( answerMessage8 );
			        	//radioList.focus();
			        	radioList[0].focus();
						return false;
			        }    
			    }

			} //End - if(matchPosReqTrue != -1)
			
			if(matchPosNumTrue != -1) {
				if( thisForm.elements[i].tagName == 'TEXTAREA' )  {
					var componentName = thisForm.elements[i].name;
			        elem = document.getElementsByName(componentName)[0];
			        elem[0].value = Trim(elem[0].value);
			        elemVal = elem.value;

			        if(  (Trim(elemVal).length > 0) && (isNaN(Trim(elemVal))) )  {
			        	alert( answerMessage5 );
			        	//elem.focus();
						return false;
			        }
			        
			      }
				//Text
			    else if(thisForm.elements[i].type == 'text')  {
			    	var componentName = thisForm.elements[i].name;
			        elem = document.getElementsByName(componentName);
			        elem[0].value = Trim(elem[0].value);
			        elemVal = elem[0].value;
			        
			        if(  (Trim(elemVal).length > 0) && (isNaN(Trim(elemVal))) )  {
			        	alert( answerMessage6 );
			        	elem[0].focus();
						return false;
			        }
			    }

			} //End - if(matchPosNumTrue != -1)
			
		}
	}	
	
	var theMode = document.getElementById('theMode');
	if (theMode != null && theMode.value == 'update') {
		var reasonModMessage = "Modification Reason is required.";
		var reasonMod = document.getElementById('reasonMod');
		if ( Trim(reasonMod.value).length < 1 ) {
			alert( reasonModMessage );
			reasonMod.focus();
			return false;
		}
	}
	
	return true;
}

//Versioning JavaScript

function uiControlTypeChoice(theForm, cDrop)
{	
	
		var offset = cDrop.selectedIndex ;
        var val =  cDrop.options[ offset ].text ;
        var w1 = document.getElementById( 'weight');
        if (val == "QUESTIONHEADER") 
		{ 
    		show( 'collapseHeader', SHOW_ITEM, 'row' );
    		if (w1 != null && w1.value != "") {
    			w1.value = '0';    			
    			w1.disabled = true;
    		}
		} else {
        	document.getElementById( 'collapsibleHeader').selectedIndex = 0;
        	show('collapseHeader', HIDE_ITEM, 'row' );
    		if (w1 != null) {
    			w1.value = '';
    			w1.disabled = false;
    		}
        }

        var inputSubmitAction = document.createElement('input');
        
//        inputSubmitAction.type = 'hidden';
//        inputSubmitAction.name = 'submitAction';
//        inputSubmitAction.value = 'Refresh';
//        theForm.appendChild(inputSubmitAction);

//        theForm.submit();
 		var d1 = document.getElementById( 'defaultSysDate');
 		var d2 = document.getElementById( 'allowFutDate');
        if (val == "DATE") 	{ 
    		d1.disabled = false;
    		d2.disabled = false;
		} else {
			d1.selectedIndex = 0;
			d2.selectedIndex = 0;
    		d1.disabled = true;
    		d2.disabled = true;
        }
}

function checkUIControlType(){
	var ct = document.getElementById("uiCntrlType");
    var w1 = document.getElementById( 'weight');
	if (ct != null){
		if (ct.value = "QUESTIONHEADER") {
    		if (w1 != null && w1.value != "") {
    		
    			w1.disabled = true;
    		} 
		} else {
    		if (w1 != null) {
    			w1.disabled = false;
    		}
		}	
	}
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1) {
		alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen) {
		field.value = field.value.substring(0, maxlen);
	}
}

function validateAnswerFields(theForm)
{
	var answerText = document.getElementById("answerText");
	var weight = document.getElementById("weight");
	var sortOrder = document.getElementById("sortOrder");
	var subordinateQuestionId = document.getElementById("currentAnswer.subordinateQuestionId");
	var answerAction = document.getElementById("currentAnswer.action");
	var textRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2F\-]*$/;

	answerText.value = Trim(answerText.value);
	if ( answerText.value.length < 1) {
		alert("Answer Text is required.");
		answerText.focus();
		return false;
	}
	if (textRegExp.test(answerText.value,textRegExp) == false) {
		alert("Answer Text contains one or more invalid characters.");
		answerText.focus();
		return false;
	} 
	
	weight.value = Trim(weight.value);
	if ( weight.value.length < 1) {
		alert("Weight is required.");
		weight.focus();
		return false;
	}
	else if (isNumeric(weight.value) == false) {
		alert("Weight Order must be numeric.");
		weight.focus();
		return false;
	}

	sortOrder.value = Trim(sortOrder.value);
	if ( sortOrder.value.length < 1) {
		alert("Sort Order is required.");
		sortOrder.focus();
		return false;
	}
	else if (isNumeric(sortOrder.value) == false) {
		alert("Sort Order must be numeric.");
		sortOrder.focus();
		return false;
	}

	if (subordinateQuestionId.selectedIndex > 0) {
	   if (answerAction.selectedIndex == 0) {	 
		      alert("An Action is required if a Subordinate Question is selected.");
		      answerAction.focus();
		      return false;
		 }
   }
	return true;   
}

function validateQuestionFields(theForm)
{
	var questionName = document.getElementById("questionName");
	var questionText = document.getElementById("questionText");
	var uiControlType = document.getElementById("uiControlType");
	var collapsibleHeader = document.getElementById("collapsibleHeader");
	var uiDisplayOrder = document.getElementById("uiDisplayOrder");
	var required = document.getElementById("required");
	var reqPrint = document.getElementById("reqPrint");
	var textRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2F\x3F\-]*$/; 
	
	questionName.value = Trim(questionName.value);
	if ( questionName.value.length < 1) 
	{
		alert("Question Name is required.");
		questionName.focus();
		return false;
	}
	if ( questionName.value.length < 3) 
	{
		alert("Question Name must be at least 3 characters.");
		questionName.focus();
		return false;
	}

	questionText.value = Trim(questionText.value);
	if ( questionText.value.length == 0) 
	{
		alert("Question Text is required.");
		questionText.focus();
		return false;
	}
	if ( questionText.value.length < 3) 
	{
		alert("Question Text must be at least 3 characters.");
		questionText.focus();
		return false;
	}
	if (textRegExp.test(questionText.value,textRegExp) == false) {
		alert("Question Text contains one or more invalid characters or begins with non-alphanumeric character.");
		questionText.focus();
		return false;
	} 

	if (uiControlType.selectedIndex == 0) 
	{	 
	      alert("UI Control Type is required.");
	      uiControlType.focus();
	      return false;
	}
	
	//Find out if Question Header has been chosen, then demand an collapsible header
	var offset = uiControlType.selectedIndex ;
    var val =  uiControlType.options[ offset ].text ;

    if (val == "QUESTIONHEADER") 
	{ 
	   if (collapsibleHeader.selectedIndex == 0) 
	   {	 
	      alert('Collapsible Header selection required.');
	      collapsibleHeader.focus();
	      return false;
	   }
    }

	uiDisplayOrder.value = Trim(uiDisplayOrder.value);
	if ( uiDisplayOrder.value.length < 1) 
	{
		alert("UI Display Order is required.");
		uiDisplayOrder.focus();
		return false;
	} 
	else if (isNumeric(uiDisplayOrder.value) == false) 
	{
		alert("UI Display Order must be numeric.");
		uiDisplayOrder.focus();
		return false;
	}

	if (required.selectedIndex == 0) 
	{	 
	      alert("Required? is required.");
	      required.focus();
	      return false;
	}
	
	if (reqPrint.selectedIndex == 0) 
	{	 
	      alert("Allow Print is required.");
	      reqPrint.focus();
	      return false;
	}
	
	return true;
}

function validateSearchFields(theForm)
{
	var questionId = document.getElementById("questionId");

	questionId.value = Trim(questionId.value);
	if ( questionId.value.length < 1) 
	{
		alert("Question Name selection is required.");
		questionId.focus();
		return false;
	}
	return true;
} 

function validateAnswerIsSelected(theForm)
{
	var hasOne = false;
    var radioList = document.getElementsByName("selectedRiskAnswerId");

    
    for( var x = 0; x < radioList.length; x++ )
		{
    	if( radioList[x].checked )
      	{
        	hasOne = true;
      	}
  	}

    if (!hasOne) 
    {
    	alert( "An Answer is required to be selected to continue this operation." );
		return false;
    }  
} 

function enableTextUpdate(el)
{
	var theMode = document.forms[0].name.toUpperCase();  //  form name should contain either create or update
	if (theMode.indexOf('UPDATE') > 0) 	{	
		var qtxt = document.getElementById("questionText").readOnly = el.checked;
	}
}

function show2(what, hide, format)
{
	var ids = what.split(",");
	for(x=0; x<ids.length; x++)
	{
		if(document.getElementById(ids[x]) != null)
		{
			if (hide == HIDE_ITEM) 
			{
				document.getElementById(ids[x]).className = 'hidden';
			}
			else if(format == "row")
			{
				document.getElementById(ids[x]).className = 'visibleTR';
			}
			else if(format == "table")
			{
				document.getElementById(ids[x]).className = 'visibleTable';
			}
			else if(format == "inline")
			{
				document.getElementById(ids[x]).className = 'visibleInline';
			}
			else 
			{
				document.getElementById(ids[x]).className = 'visible';
			}
		} 
	}	
}

function validateAnswerToBeAdded()
{
	var fld = document.getElementById("tobeAddedList");
	 if (fld == null) {
		 alert("At least 1 answer must be Added to List.");
		 return false;
	 }
	 return true;
}