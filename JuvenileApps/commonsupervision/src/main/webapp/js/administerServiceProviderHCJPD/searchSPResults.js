
function validateRadioFields()
{
	var thisForm = document.forms[0];	
	
	var mychoice=0;
	for(var i=0; i<thisForm.length;i++)
    {
    	if(thisForm.elements[i].type == 'radio')
     	{
     	  if(thisForm.elements[i].checked)
	      {        
	      	mychoice=1;
	      }   
     	}     
    }
     if(mychoice==0)
    {
    	alert("At least one Service Provider record has to be selected.");    	
    	return false;
    }
	return true;	
}
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}

