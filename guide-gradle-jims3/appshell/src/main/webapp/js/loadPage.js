function loadPage() {
   gotoFirstInput();
}

function loadPageWithTimeOut(timeout) {
   gotoFirstInputWithTimeOut(timeout);
}

function gotoFirstInput() {

   for (i=0; i<=document.forms[0].elements.length; i++) {
      if(document.forms[0].elements[i].type=="textarea"||document.forms[0].elements[i].type=="text"){
	 document.forms[0].elements[i].focus();      
         i=document.forms[0].elements.length+1;
      }
   }
}

function gotoFirstInputWithTimeOut(timeout) {

   for (i=0; i<=document.forms[0].elements.length; i++) {
      if(document.forms[0].elements[i].type=="textarea"||document.forms[0].elements[i].type=="text"){
	 setTimeout("focusOn("+i+")", timeout)
         i=document.forms[0].elements.length+1;
      }
   }
}


// this function turn off any submit button in the form
function disableSubmitButton(){
var frm = document.forms[0];
for(x=0;x<=frm.length;x++){
if(frm.elements[x].type=='submit'){
		frm.elements[x].disabled = true;
		x=frm.length+1
		}	
	}
}



function focusOn(i){
document.forms[0].elements[i].focus();
}

function colorField(errorField){
whiteBGFields(errorField)
if(errorField.type=="textarea"||errorField.type=="text")
errorField.style.background='#FDF0B1'; 
}

function whiteBGFields() {
   for (i=0; i<=document.forms[0].elements.length-1; i++) {
      if(document.forms[0].elements[i].type=="textarea"||document.forms[0].elements[i].type=="text"){
	 		document.forms[0].elements[i].style.background='WHITE'; 
      }
   }
}

function theTrimmer(textbox, which) {
  if (textbox) {
    //trim leading spaces
	if(which != 'trailing'){
		while (textbox.value.substring(0,1) == " ") {
      		textbox.value = textbox.value.substring(1);
    	}
	}
	//trim trailing spaces
	if(which != 'leading'){
		while(textbox.value.substring(textbox.value.length - 1, textbox.value.length) == " ") {
    	  textbox.value = textbox.value.substring(0, textbox.value.length - 1);
    	}
  	}
	
  }
}


function selectEntireBlock(field) {
   // select all items in a multiple select list before submitting
   for (var i=0; i<field.length; i++) {
	  field[i].selected = true;
   }
}