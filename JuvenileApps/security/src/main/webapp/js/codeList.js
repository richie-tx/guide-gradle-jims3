<script language=javascript>
function actSelect(num){
    var actName = "ACT" + num;
	var actState = false;
    for(var i=0; i<document.forms[0].length; i++){
       if(document.forms[0].elements[i].type == 'checkbox'){
	      fldName = document.forms[0].elements[i].name.toUpperCase();
		  if (fldName == actName){
		     actState = document.forms[0].elements[i].checked;
             setSelect(num,actState);
             return;
          }
       }
    }
}

function setSelect(num, actState){
	var selName = "SEL" + num;
    for(var x=0; x<document.forms[0].length; x++){
       if(document.forms[0].elements[x].type == 'checkbox'){
	      fldName = document.forms[0].elements[x].name.toUpperCase();
		  if (fldName == selName){
		     document.forms[0].elements[x].checked = actState;
             return;
          }
       }
    }
}

function buttonCheck(button){
  var reply = false;
  if (button == 'U'){
    reply = checkSelections();
	if (reply == false){
	   alert("No selection made to Update.");
       document.forms[0].sel1.focus();
	   return false;
	}
    this.location = "codeUpdateSummary.htm";
    return true;
  }
  if (button == 'C'){
    var focusSet = 0;
	var msg = "";
	var newline = ""
    if (document.forms[0].createCode.value == ""){
	   focusSet = 1;
	   document.forms[0].createCode.focus();
	   msg = "Code is required.";
	   newline = "\n";
	}
	if (document.forms[0].createDescription.value == ""){
	   if (focusSet == 0){
	       focusSet = 1;
	       document.forms[0].createDescription.focus();
	   }
	   msg = msg + newline +"Code Description is required.";
	   newline = "\n";
	}
	if (focusSet == 1){
	   alert(msg);
	   return false;
	}
    this.location = "codeCreateSummary.htm";
    return true;
  }

  if (button == 'D'){
    reply = checkSelections();
	if (reply == false){
	   alert("No selection made to Delete.");
       document.forms[0].sel1.focus();
	   return false;
	}
	this.location = "codeDeleteSummary.htm";
    return true;
  }
  if (button == 'F'){
      msg = "A smaller set of records based on the input value(s) for this code table would be listed.\n\n(This function does not work in this prototype.)";
      if (document.forms[0].filterCode.value == "" && document.forms[0].filterDescription.value == ""){
        msg = "All records in code this table would be listed.\n\n(This function does not work in this prototype.)";
	  }
	  alert(msg);
      return false;
  }
  if (button == 'X'){
      this.location = "codeTableMenu.htm";
      return true;
  }
}
function checkSelections(){
    var selName = "";
    for(var i=0; i<document.forms[0].length; i++){
       if(document.forms[0].elements[i].type == 'checkbox'){
	      selName = document.forms[0].elements[i].name.toUpperCase().substring(0,3);
	      if(document.forms[0].elements[i].checked == true && selName == "SEL"){
             return true;
          }
       }
    }
   return false;
}
</script>}
 	

