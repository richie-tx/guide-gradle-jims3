function loadPageWithTimeout() {
   gotoFirstInput();
}

function gotoFirstInput() {

   for (i=0; i<=document.forms[0].elements.length; i++) {
      if(document.forms[0].elements[i].type=="textarea"||document.forms[0].elements[i].type=="text"){
	  setTimeout("focusOn("+i+")", 100)
         i=document.forms[0].elements.length+1;
      }
   }
}

function focusOn(i){
document.forms[0].elements[i].focus();
}


