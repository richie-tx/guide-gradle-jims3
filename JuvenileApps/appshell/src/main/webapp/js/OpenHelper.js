<!--
	//**********************************************
	//  Open Helper
	// Author: Chris Johnston
	//**********************************************
	

	var HelperWindow;

  
			
  
  function openTheWindow(windowName)
	{
		// this function grabs the names of the elements in forms[0]
		// it then creates a comma seperated string.
		// then opens a spell Check window and send the info to it
		if(windowName=="Directory")
		HelperWindow = window.showModelessDialog("/jims2/html/countryDir.html" ,"","dialogHeight: 300px; dialogWidth: 280px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
		else if(windowName=="Help"){
		//HelperWindow = window.showModelessDialog("/jims2/WebHelp/HelpIndex.html","","dialogHeight: 550px; dialogWidth: 650px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: Yes; status: Yes;");
		var HelpWindow;
		HelpWindow = window.open("/jims2/WebHelp/HelpIndex.html","HelpWindow","height=550,width=600,resizable=y,toolbar=no",true);
		}
		else if(windowName=="Helplogin")
		HelperWindow = window.showModelessDialog("/jims2/WebHelp/Overview/user_guide_logon.htm","","dialogHeight: 550px; dialogWidth: 650px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: Yes; status: Yes;");
		
		
		else if(windowName=="editSpecimen")
		HelperWindow = window.showModelessDialog("/jims2/jsp/TSS/editSpecimenTypeBody.jsp","","dialogHeight: 500px; dialogWidth: 450px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: Yes; status: Yes;");
		else if(windowName=="editProcedure")
		HelperWindow = window.showModelessDialog("/jims2/jsp/TSS/editProcedureBody.jsp","","dialogHeight: 500px; dialogWidth: 450px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: Yes; status: Yes;");
		else if(windowName=="editLocation")
		HelperWindow = window.showModelessDialog("/jims2/jsp/TSS/editjims2LocationBody","","dialogHeight: 500px; dialogWidth: 450px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: Yes; status: Yes;");
	}

function selectDataFromWindow(windowName, argObj){	
	if(windowName=="Directory")
		HelperWindow = window.showModelessDialog(
			"/jims2/html/countryDir.html" ,
			argObj,
			"dialogHeight: 300px; dialogWidth: 280px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
}

		
		