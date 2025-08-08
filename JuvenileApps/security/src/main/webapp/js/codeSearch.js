<script language=javascript>
var adminData = new Array();
var civilData = new Array();
var crimData  = new Array();
var tcncData  = new Array();

adminData[0] = 'Employee Time Management'
adminData[1] = 'Employee Time Scheduling'
adminData[2] = 'Human Resources'
adminData[3] = 'Training Scheduling'

civilData[0] = 'Activity Codes'
civilData[1] = 'Attorney Vacation'
civilData[2] = 'Constable Tracking Codes'
civilData[3] = 'Docketing Codes'
civilData[4] = 'Fee Codes'
civilData[5] = 'Post Trial Codes'
civilData[6] = 'Service Codes'

crimData[0] = 'Case Tracking'
crimData[1] = 'Court Coordinator Scheduling'
crimData[2] = 'Community Supervision'
crimData[3] = 'D.A. Intake'
crimData[4] = 'G.R.I.T.S'
crimData[5] = 'Offense Tracking'
crimData[6] = 'Offense 13'
crimData[7] = 'Persons'
crimData[8] = 'Receiving'
crimData[9] = 'Sherrifs Property Room'
crimData[10] = 'Time Management'
crimData[11] = 'Warrants'

tcncData[0] = "NCIC Codes"

function populateData( name ) {
	select = document.forms[0].subType;
	var count = 0;

    select.options.length = count;
//  select.options[count++] = new Option("Select Subtype");

// Place all matching subtypes into Options.
   if (name == "ad"){
      for( var i = 0; i < adminData.length; i++ ) {
   		select.options[count++] = new Option(adminData[i] );
      }
   }
   if (name == "cv"){
      for( var i = 0; i < civilData.length; i++ ) {
   		select.options[count++] = new Option(civilData[i] );
      }
   }
   if (name == "cr"){
      for( var i = 0; i < crimData.length; i++ ) {
     	select.options[count++] = new Option(crimData[i] );
      }
   }
   if (name == "tn"){
      for( var i = 0; i < tcncData.length; i++ ) {
     	select.options[count++] = new Option(tcncData[i] );
      }
   }
   if (name == "0" || document.forms[0].type.selectedIndex == 0){
      select.options[count++] = new Option("  ");
      document.forms[0].type.focus();
      return;
   }

   if (count == 0){
   	  select.options[count++] = new Option("No Subtypes available")
   }
// Set which option from subtype is to be selected

//	select.options.selectedIndex = 2;
// Give subcategory focus and select it
      select.focus();
}

function buttonCheck(button){
  if (button == 'S'){
    this.location = "codeSearchResults.htm";
    return true;
  }
  if (button == 'C'){
    this.location = "codeTableMenu.htm";
    return true;
  }  
}

</script>

