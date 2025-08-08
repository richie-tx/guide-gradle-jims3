<!-- JavaScript - Button Check -->

function validateCreateFields(){
    var thisForm = document.forms[0];
	Trim(thisForm["dsmRec.testDate"].value);
    Trim(thisForm["dsmRec.axisIPrimaryScore"].value);
  	Trim(thisForm["dsmRec.axisISecondaryScore"].value);
  	Trim(thisForm["dsmRec.axisITertiaryScore"].value);
  	Trim(thisForm["dsmRec.axisIFourth"].value);
  	Trim(thisForm["dsmRec.axisIFifth"].value);
  	Trim(thisForm["dsmRec.axisIIPrimaryScore"].value);
  	Trim(thisForm["dsmRec.axisIISecondaryScore"].value);
  	Trim(thisForm["dsmRec.axisIIIPrimaryScore"].value);
  	Trim(thisForm["dsmRec.axisIIISecondaryScore"].value);
  	Trim(thisForm["dsmRec.diagnosis10"].value);  	
	return(validateDsmTestForm(thisForm));	
}






