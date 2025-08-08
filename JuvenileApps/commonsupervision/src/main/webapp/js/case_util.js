var asp='';
var action='';
var agency='';
var workflowFrom='';
var misc='';
var superviseeLName='';
var superviseeFName='';
var period='';
var officerName='';

r = argItems('asp');
if (r.length > 0) {
	asp=r[0];
}
r = argItems('action');
if (r.length > 0) {
	action=r[0];
}
r = argItems('agency');
if (r.length > 0) {
	agency=r[0];
}
r = argItems('workflowFrom');
if (r.length > 0) {
	workflowFrom=r[0];
}

r = argItems('misc');
if (r.length > 0) {
	misc=r[0];
}

r = argItems('superviseeLName');
if (r.length > 0) {
	superviseeLName=r[0];
}
r = argItems('superviseeFName');
if (r.length > 0) {
	superviseeFName=r[0];
}
r = argItems('period');
if (r.length > 0) {
	period=r[0];
}
r = argItems('officerName');
if (r.length > 0) {
	officerName=r[0];
}


//imagePath - to deal with folder structure
var imagePath = "../../"
var path = "../"
function determinePath(level)
{
	if (level == 1){
		imagePath = "../../../";
		path = "../../"
	}
}



// Description: parses name pairs out of the url, given theArgName parses out the value pairs that match
	// Usuage: r = argItems('caseType');
   	//  if (r.length > 0) {
	//	caseType=r[0];
	//        document.writeln('<LI>'+r[0]+'</LI><br>')
    	//   }
function argItems (theArgName) {
	sArgs = location.search.slice(1).split('&');
    r = '';
    for (var i = 0; i < sArgs.length; i++) {
        if (sArgs[i].slice(0,sArgs[i].indexOf('=')) == theArgName) {
            r = sArgs[i].slice(sArgs[i].indexOf('=')+1);
            break;
        }
    }
    return (r.length > 0 ? unescape(r).split(',') : '')
}

function setOfficerName(aOfficerName){
 officerName=aOfficerName;
}

function setSuperviseeLName(aSuperviseeLName){
 superviseeLName=aSuperviseeLName;
}

function setSuperviseeFName(aSuperviseeFName){
 superviseeFName=aSuperviseeFName;
}


function setASP(aASP){
 asp=aASP;
}

function setAgency(aAgency){
	agency=aAgency;
}

function setAction(aAction){
 	action=aAction;
}

/* this function will dynamically change the text for a cell in a table
		 pass the ID value of the TD and the new text
*/
function setCellText( idOfTD, textToSet )
{
    document.getElementById( idOfTD ).innerHTML = textToSet ;
}

	//this function alerts a message and then redirects to another page
	/*params: msg - message to be alerted
					  location - new page to be redirected to
	*/
	function alertAndGo(msg, location){
		alert(msg);
		window.location.href=location;
	}

	//this function returns the current date in String format mm/dd/yyyy
	/*params:
	*/

	function getCurrentDate(){
		var theDate=new Date();
		var theMonth = theDate.getMonth()+1;
		var theDay = theDate.getDate();
		var theYear = theDate.getFullYear();

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

	function setAmPm(el){
		var currentTime = new Date()
		var hours = currentTime.getHours()
		var minutes = currentTime.getMinutes()

		if(hours > 11){
		el.options[1].selected = true;
		} else {
		el.options[0].selected = true;
		}
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

	//this function checksall checkboxes given the name
	/*params: el - the checkAll checkbox form element
						checkboxName - the name of the checkboxes that will automatically be checked or unchecked
	*/
	function checkAll(el, checkboxName)
	{
		var theForm = el.form;
		var checkAllName = el.name;
		var objCheckBoxes = document.getElementsByName(checkboxName);
		var countCheckBoxes = objCheckBoxes.length;


		if(el.checked)
		{
			// set the check value for all check boxes
			for(var i = 0; i < countCheckBoxes; i++)
				if (!objCheckBoxes[i].disabled){
					objCheckBoxes[i].checked = true;
				}
		}else {
				for(var i = 0; i < countCheckBoxes; i++)
				if (!objCheckBoxes[i].disabled){
					objCheckBoxes[i].checked = false;
				}
			}
	}
	//this function unchecks the checkAll checkbox
	/*params: el - the checkbox form element
						checkAllName - the name of the checkall checkbox that will automatically be unchecked
	*/
	function uncheckCheckAll(el, checkAllName)
	{
		var theForm = el.form;
		if (!el.checked){
			theForm.elements[checkAllName].checked = false;
		}
	}

	//this function checksall checkboxes given the name - assumes name format is checkboxName[i].checkboxNameEnd
	/*params: el - the checkAll checkbox form element
						checkboxName - beginning part of the name
						checkboxNameEnd - ending part of the name
						numOfCheckboxes - how many checkboxes to be checked
	*/
	function checkAllByNameBgColor(el, checkboxName, checkboxNameEnd, numOfCheckboxes)
	{
		var theForm = el.form;
		var checkAllName = el.name;

		if(el.checked)
		{
			// set the check value for all check boxes
			for(var i = 0; i < numOfCheckboxes; i++){
				var checkboxNameStr = checkboxName + "[" + i + "]." + checkboxNameEnd;
				document.getElementsByName(checkboxNameStr)[0].checked = true;
				chooseRowByName(document.getElementsByName(checkboxNameStr)[0])
			}
		}else {
			for(var i = 0; i < numOfCheckboxes; i++){
				var checkboxNameStr = checkboxName + "[" + i + "]." + checkboxNameEnd;
				document.getElementsByName(checkboxNameStr)[0].checked = false;
				chooseRowByName(document.getElementsByName(checkboxNameStr)[0])

			}
		}
	}

	//this function checksall checkboxs and changes the bgcolor
	/*params: el - the checkAll checkbox form element
						checkboxName - the name of the checkboxes that will automatically be checked or unchecked
	*/
	function checkAllBgColor(el, checkboxName)
	{
		var theForm = el.form;
		var objCheckBoxes = document.getElementsByName(checkboxName);
		var countCheckBoxes = objCheckBoxes.length;


		if(el.checked)
		{
			// set the check value for all check boxes
			for(var i = 0; i < countCheckBoxes; i++)
			{
				objCheckBoxes[i].checked = true;
				chooseRow(objCheckBoxes[i]);
			}
		}else {
				for(var i = 0; i < countCheckBoxes; i++)
				{
					objCheckBoxes[i].checked = false;
					chooseRow(objCheckBoxes[i]);
				}
			}
	}

	//this function prints the common section of the SO
	/*params:
	*/
	function SOHeaderInfo(){
		document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr>");
		document.write("<td class=headerLabel>Last Name</td>");
		document.write("<td class=headerData>Smith</td>");
		document.write("<td class=headerLabel>First Name</td>");
		document.write("<td class=headerData>Joe</td>");
		document.write("<td class=headerLabel>DOB</td>");
		document.write("<td class=headerData>7/7/1980</td>");
		document.write("<td class=headerLabel>SPN</td>");
		document.write("<td class=headerData>23132454 - 999</td>");
		document.write("</tr>");
		document.write("</table>");
	}

	//this function prints the header section of the ooc
	/*params:
	*/
	function adultSupHeader(){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Name</td>");
			document.write("<td colspan=5 class=headerData>Smith, John Joe</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>SPN</td>");
			document.write("<td class=headerData>12345678</td>");
			document.write("<td class=headerLabel>SSN</td>");
			document.write("<td class=headerData>111-111-1111</td>");
			document.write("<td class=headerLabel>DOB</td>");
			document.write("<td class=headerData>12/28/1976</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Race</td>");
			document.write("<td class=headerData>White</td>");
			document.write("<td class=headerLabel>Sex</td>");
			document.write("<td class=headerData>M</td>");
			document.write("<td class=headerLabel>SID</td>");
			document.write("<td class=headerData>TX12322425</td>");
			document.write("</tr>");
			document.write("</table>");
	}

	//this function prints the header section of the ooc - pts specific
	/*params:
	*/
	function adultSupHeaderPTS(){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Name</td>");
			document.write("<td colspan=5 class=headerData>Fife, Barney</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>SPN</td>");
			document.write("<td class=headerData>01410655</td>");
			document.write("<td class=headerLabel>SSN</td>");
			document.write("<td class=headerData>121-11-5451</td>");
			document.write("<td class=headerLabel>DOB</td>");
			document.write("<td class=headerData>12/28/1976</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Race</td>");
			document.write("<td class=headerData>White</td>");
			document.write("<td class=headerLabel>Sex</td>");
			document.write("<td class=headerData>M</td>");
			document.write("<td class=headerLabel>SID</td>");
			document.write("<td class=headerData>TX12322425</td>");
			document.write("</tr>");
			document.write("</table>");
	}

	//this function prints the header section of the casenotes area
	/*params:
	*/
	function casenotesHeaderPTS(){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Name</td>");
			document.write("<td colspan=5 class=headerData>");
			if (superviseeLName != ""){
				document.write(superviseeLName + ", " + superviseeFName);
			}else {document.write("Smith, John M");}
			document.write(" <img src=" + imagePath + "common/images/redLight.gif name=\"redLight\" title=\"Out of Compliance\" border=0></td></tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Officer</td>");
			document.write("<td colspan=5 class=headerData>Lyons, James Gregory</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>SPN</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("22334455");
			  break

				case "Simmons":
				document.write("11223344");
			  break

			  case "Flinstone":
				document.write("33445566");
			  break

				default:
				document.write("12345678");
			}
			document.write("</td><td class=headerLabel>CON</td>");
			document.write("<td class=headerData>DEF</td>");
			document.write("<td class=headerLabel>DOB</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("01/25/1970");
			  break

				case "Simmons":
				document.write("07/01/1949");
			  break

				default:
				document.write("12/28/1976");
			}
			document.write("</td></tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Race</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("Hispanic");
			  break

				case "Simmons":
				document.write("Black");
			  break

				default:
				document.write("White");
			}
			document.write("</td><td class=headerLabel>Sex</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("M");
			  break

				case "Simmons":
				document.write("F");
			  break

				default:
				document.write("M");
			}
			document.write("</td><td class=headerLabel>Unit</td>");
			document.write("<td class=headerData></td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel nowrap width=17%>Next Contact Date</td>");
			document.write("<td class=headerData width=17%>05/05/2006</td>");
			document.write("<td class=headerLabel width=17%>Contact Method</td>");
			document.write("<td class=headerData width=17%>Office Visit</td>");
			document.write("<td class=headerLabel width=17%>Contact Reason</td>");
			document.write("<td class=headerData width=15%></td></tr>");
			document.write("<tr class=hidden id=addressRow>");
			document.write("<td class=headerLabel>Address</td>");
			document.write("<td colspan=5 class=headerData>12345 Main<br>Houston, TX 77002 <br>Home: 713-222-4111 <a href=#>Update Address</a></td></tr>");
			document.write("<tr class=hidden id=employmentRow>");
			document.write("<td class=headerLabel>Employment</td>");
			document.write("<td colspan=5 class=headerData>Harris County, 713-111-1111, Developer <a href=#>Update Employment</a></td></tr>");
			document.write("<tr class=hidden id=casesRow>");
			document.write("<td class=headerLabel>Case</td>");
			document.write("<td class=headerData>9811324 01010<br>1247577 01010</td>");
			document.write("<td class=headerLabel>Court</td>");
			document.write("<td class=headerData>214<br>124</td>");
			document.write("<td class=headerLabel>Next Court Date</td>");
			document.write("<td class=headerData>10/10/2006<br>05/06/2006</td>");
			document.write("</tr></table><div class=hr></div>");
	}

	//this function prints the header section of the casenotes area
	/*params:
	*/
	function casenotesHeaderCSCD(){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Name</td>");
			document.write("<td colspan=5 class=headerData>");
			if (superviseeLName != ""){
				document.write(superviseeLName + ", " + superviseeFName);
			}else {document.write("Smith, John M");}
			document.write(" <img src=" + imagePath + "common/images/redLight.gif name=\"redLight\" title=\"Out of Compliance\" border=0></td></tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Officer</td>");
			document.write("<td colspan=5 class=headerData>Lyons, James Gregory</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>SPN</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("22334455");
			  break

				case "Simmons":
				document.write("11223344");
			  break

			  case "Flinstone":
				document.write("33445566");
			  break

				default:
				document.write("12345678");
			}
			document.write("</td><td class=headerLabel>CON</td>");
			document.write("<td class=headerData>DEF</td>");
			document.write("<td class=headerLabel>DOB</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("01/25/1970");
			  break

				case "Simmons":
				document.write("07/01/1949");
			  break

				default:
				document.write("12/28/1976");
			}
			document.write("</td></tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Race</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("Hispanic");
			  break

				case "Simmons":
				document.write("Black");
			  break

				default:
				document.write("White");
			}
			document.write("</td><td class=headerLabel>Sex</td>");
			document.write("<td class=headerData>");
			switch (superviseeLName)
			{
				case "Gonzalez":
				document.write("M");
			  break

				case "Simmons":
				document.write("F");
			  break

				default:
				document.write("M");
			}
			document.write("</td><td class=headerLabel>Unit</td>");
			document.write("<td class=headerData></td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel width=17%>Next Contact Date</td>");
			document.write("<td class=headerData width=17%>05/05/2006 7:30 AM</td>");
			document.write("<td class=headerLabel width=17%>Contact Method</td>");
			document.write("<td class=headerData width=17%>Office Visit</td>");
			document.write("<td class=headerLabel width=17%>Contact Reason</td>");
			document.write("<td class=headerData width=25%></td></tr>");
			document.write("<tr class=hidden id=addressRow>");
			document.write("<td class=headerLabel>Address</td>");
			document.write("<td colspan=5 class=headerData>12345 Main<br>Houston, TX 77002 <br>Home: 713-222-4111 <a href=#>Update Address</a></td></tr>");
			document.write("<tr class=hidden id=employmentRow>");
			document.write("<td class=headerLabel>Employment</td>");
			document.write("<td colspan=5 class=headerData>Harris County, 713-111-111, Developer <a href=#>Update Employment</a></td></tr>");
			document.write("<tr class=hidden id=casesRow>");
			document.write("<td class=headerLabel>Case</td>");
			document.write("<td class=headerData>9811324 01010<br>1247577 01010</td>");
			document.write("<td class=headerLabel>Court</td>");
			document.write("<td class=headerData>214<br>124</td>");
			document.write("<td class=headerLabel>Supervision Period</td>");
			document.write("<td class=headerData>10/10/2004 - 05/06/2006<br>10/10/2004 - 05/06/2006<br></td>");
			document.write("</tr></table><div class=hr></div>");
	}

	//this function prints the header section of the ooc
	/*params:
	*/
	function adultSupHeaderModify(){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Name</td>");
			document.write("<td colspan=5 align=left bgcolor=#ffffff class=headerData><select><option>Smith, John Joe<option>Smith, Johnny<option>Smith, J</select></td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>SPN</td>");
			document.write("<td class=headerData>00011001</td>");
			document.write("<td class=headerLabel>SSN</td>");
			document.write("<td class=headerData>111-111-1111</td>");
			document.write("<td class=headerLabel>DOB</td>");
			document.write("<td class=headerData>12/28/1976</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Race</td>");
			document.write("<td class=headerData>White</td>");
			document.write("<td class=headerLabel>Sex</td>");
			document.write("<td class=headerData>M</td>");
			document.write("<td class=headerLabel>SID</td>");
			document.write("<td class=headerData>TX12322425</td>");
			document.write("</tr>");
			document.write("</table>");
	}

	//this function prints the header section of Admin Position
	/*params:
	*/
	function positionHeader(noa){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Current User</td>");
			document.write("<td colspan=5 align=left bgcolor=#ffffff class=headerData>");
			if (noa){
				document.write("No Officer Assigned");
			}else document.write("Smith, AJ");
			document.write("</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel width=1% nowrap>Position Name</td>");
			document.write("<td class=headerData>Supervisor NM Name</td>");
			document.write("<td class=headerLabel>POI</td>");
			document.write("<td class=headerData>NM</td>");
			document.write("</tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Program Unit</td>");
			document.write("<td class=headerData>North Region</td>");
			document.write("<td class=headerLabel width=1% nowrap>Program Section</td>");
			document.write("<td class=headerData></td>");
			document.write("</tr>");
			document.write("</table>");
	}

	//this function prints the common section of the DC no log#
	/*params:
	*/
	function assignmentHeader(){
			document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Name</td>");
			document.write("<td colspan=3 class=headerData>");
			if (superviseeLName != ""){
				document.write(superviseeLName + ", " + superviseeFName);
			}else	document.write("Smith, John M");
			document.write("<td class=headerLabel>SPN</td>");
			document.write("<td class=headerData>");
			switch (superviseeFName)
			{
				case "Steven":
				document.write("1222222");
			  break

				case "Mike":
				document.write("15121212");
			  break

			  case "John":
				document.write("11111111");
			  break

			  case "Chris":
				document.write("13121212");
			  break

			  case "Larry":
				document.write("16121212");
			  break

				default:
				document.write("12345678");
			}
			document.write("</td>");
			document.write("</td></tr>");
			document.write("<tr>");
			document.write("<td class=headerLabel>Case</td>");
			document.write("<td class=headerData>12487564 01010</td>");
			document.write("<td class=headerLabel>CRT</td>");
			document.write("<td class=headerData>204</td>");
			document.write("<td class=headerLabel width=1% nowrap>Supervision Period</td>");
			document.write("<td class=headerData>12/12/2004 - 12/26/2006</td>");
			document.write("</tr>");
			document.write("</table><div class=hr></div>");
	}

	//this function prints the common section of the DC no log#
	/*params:
	*/
	function DCHeaderInfo(){
		document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr>");
		document.write("<td class=headerLabel>Last Name</td>");
		document.write("<td class=headerData>Smith</td>");
		document.write("<td class=headerLabel>First Name</td>");
		document.write("<td class=headerData>Joe</td>");
		document.write("<td class=headerLabel>SPN</td>");
		document.write("<td class=headerData>23132454</td>");
		document.write("</tr>");
		document.write("</table>");
	}

	//this function prints the common section of the DC no log#
	/*params:
	*/
	function caseloadHeader(){
		document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr>");
		document.write("<td class=headerLabel>Name</td>");
		document.write("<td class=headerData>Smith, John <img src=" + imagePath + "common/images/redLight.gif name=\"redLight\" title=\"Out of Compliance\" border=0></td>");
		document.write("<td class=headerLabel>SPN</td>");
		document.write("<td class=headerData>23132454</td>");
		document.write("<tr><td class=headerLabel>Officer</td>");
		document.write("<td class=headerData>Fife, Barney</td>");
		document.write("<td class=headerLabel>Fee</td>");
		document.write("<td class=headerData>100.00</td>");
		document.write("</tr>");
		document.write("</table>");
	}

	//this function prints the common section of the DC no log#
	/*params:
	*/
	function searchHeader(which){
		document.write("<table width=98% border=0 cellpadding=4 cellspacing=0 class=borderTableGrey bgColor=#f0f0f0>");
		document.write("<tr>");
		if (which == "name"){
		document.write("<td class=subHead align=center><a href=searchSPN.htm class=topNav>Name</a> > <a href=searchSSN.htm >SSN</a> > <a href=searchDL.htm >Driver's License</a> > <a href=searchID.htm >ID's</a></td>");
		}else if (which == "ssn"){
				document.write("<td class=subHead align=center><a href=searchSPN.htm>Name</a> > <a href=searchSSN.htm class=topNav>SSN</a> > <a href=searchDL.htm >Driver's License</a> > <a href=searchID.htm >ID's</a></td>");
			}else if (which == "dl"){
				document.write("<td class=subHead align=center><a href=searchSPN.htm>Name</a> > <a href=searchSSN.htm >SSN</a> > <a href=searchDL.htm class=topNav>Driver's License</a> > <a href=searchID.htm>ID's</a></td>");
				}else if (which == "id"){
					document.write("<td class=subHead align=center><a href=searchSPN.htm>Name</a> > <a href=searchSSN.htm >SSN</a> > <a href=searchDL.htm>Driver's License</a> > <a href=searchID.htm class=topNav>ID's</a></td>");
				}
		document.write("</tr>");
		document.write("</table>");
	}

	//called onload to check if checkboxes are checked and set color
	//pass in which form
	function maintainChks(formNum){

		//get form which checkboxes are in
	  elem = document.forms[formNum];

	  for(i=0; i<elem.length; i++) {
	    if (elem[i].type == "radio"){
	    		if(elem[i].checked){
		    		var	id = elem[i].value;
		    		document.getElementById(id).className = "selectedRow";
		    	}
	    	}
	  }
	}

	//changes the background color of the row where the checkbox is
	//row has incrementing number Id, and checkbox has same value
	//param: el - the form object radio/checkbox
	function chooseRowByName(el, theName) {
		theName = el.name;
		var theForm = el.form;
		//create row id
		id = theName;
		var thisRow = document.getElementsByName(id)[0];
		//checkbox is checked - change background color	- check if alternating row
		if (el.checked){
				if (el.type != "checkbox"){
						thisRow.className = "selectedRow";
				}else thisRow.className = "selectedRowGreen";
		}else thisRow.className = "";

		removeSelectedRow(theForm, el.name);

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


	//this function renders top level tabs for common supervision
	/*params: activeTab - sets the active tab
	*/
	function renderTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Calendar"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=# class=topNav>Calendar</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=# class=topNav>Calendar</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Compliance"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "adminCompliance/search.htm class=topNav>Compliance</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "adminCompliance/search.htm class=topNav>Compliance</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Tasks"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "tasks/taskList.htm class=topNav>Tasks</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "tasks/taskList.htm class=topNav>Tasks</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "ProcessOrder"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "processSupOrder/search.htm class=topNav>Process Order</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "processSupOrder/search.htm class=topNav>Process Order</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Caseload"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "caseload/caseloadList.htm class=topNav>Caseload</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "caseload/caseloadList.htm class=topNav>Caseload</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}		

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "ManageFeatures"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "csCommon/manageFeaturesIndex.htm class=topNav>Setup</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "csCommon/manageFeaturesIndex.htm class=topNav>Setup</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Casenotes"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "casenotes/search.htm class=topNav>Casenotes</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else if (activeTab == "CasenotesPTS"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "casenotesPTS/search.htm class=topNav>Casenotes</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "casenotes/search.htm class=topNav>Casenotes</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Checkin"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "checkinPTS/dailySchedule.htm class=topNav>Checkin</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else if (activeTab == "Checkin"){
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=" + path + "checkinPTS/dailySchedule.htm class=topNav>Checkin</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=" + imagePath + "common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=" + path + "checkinPTS/dailySchedule.htm class=topNav>Checkin</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=" + imagePath + "common/images/right_inactive_tab.gif></td>");
		}

		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders sub tabs under manage features
	/*params: activeTab - sets the active tab
	*/
	function renderSubTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		/*if (activeTab == "Conditions"){
			document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
			document.write("<td bgcolor=#33cc66 align=center><a href=../manageSupervisionOptions/conditionCreate.htm class=topNav title=''>Conditions</a></td>");
			document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../manageSupervisionOptions/conditionCreate.htm class=topNav title=''>Conditions</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Court"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center>");
		document.write("<a href=../manageSupervisionOptions/courtPolicyCreate.htm class=topNav title='Pretrial Label:Compliance Standards'>Court Policies</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../manageSupervisionOptions/courtPolicyCreate.htm class=topNav title='Pretrial Label:Compliance Standards \n HCJPD Label:Consequence'>Court Policies</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Dept"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../manageSupervisionOptions/deptPolicyCreate.htm class=topNav title='Pretrial Label:Dept Standards'>Dept Policies</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../manageSupervisionOptions/deptPolicyCreate.htm class=topNav title='Pretrial Label:Dept Standards'>Dept Policies</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Vendors"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../csCommon/adminServiceProviderIndex.htm class=topNav>Service Provider</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../csCommon/adminServiceProviderIndex.htm class=topNav>Service Provider</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");*/

		if (activeTab == "Programs"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=# class=topNav>Programs</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=# class=topNav>Programs</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Reports"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../csCommon/reportsMain.htm class=topNav>Reports</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../csCommon/reportsMain.htm class=topNav>Reports</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "ooc"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../manageCaseInitiationPrePostTrial/partySearch.htm class=topNav>OOC</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../manageCaseInitiationPrePostTrial/partySearch.htm class=topNav>OOC</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		/*document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Suggested Order"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../csCommon/suggestedOrderIndex.htm class=topNav>Suggested Order</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../csCommon/suggestedOrderIndex.htm class=topNav>Suggested Order</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}*/

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Staff"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../csCommon/staffIndex.htm class=topNav>Staff</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../csCommon/staffIndex.htm class=topNav>Staff</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Positions"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../positionsCSCD/positionSearch.htm class=topNav>Positions</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../positionsCSCD/positionSearch.htm class=topNav>Positions</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Workgroups"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../workgroupCSCD/workgroupSearch.htm class=topNav>&nbsp;Workgroups</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../workgroupCSCD/workgroupSearch.htm class=topNav>&nbsp;Workgroups</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}

		/*document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Location"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../location/search.htm class=topNav>Location</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../location/search.htm class=topNav>Location</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../common/images/right_green_inactive_tab.gif></td>");
		}*/

		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders caseload sub tabs under Caseload Tab
	/*params: activeTab - sets the active tab
	*/
	function renderCaseloadSubTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Profile"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../profile/casefileProfileTab.htm class=topNav>Profile</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../profile/casefileProfileTab.htm class=topNav>Profile</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Associates"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../manageAssociate/associatesList.htm class=topNav>Associates</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../manageAssociate/associatesList.htm class=topNav>Associates</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Programs"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=# class=topNav>Programs</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=# class=topNav>Programs</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Assessments"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=# class=topNav>Assessments</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=# class=topNav>Assessments</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}		

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Cases"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../orders/ordersList.htm class=topNav>Cases</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../orders/ordersList.htm class=topNav>Cases</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Fees"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=# class=topNav>Fees</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=# class=topNav>Fees</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=" + imagePath + "common/images/spacer.gif width=2></td>");

		if (activeTab == "Calendar"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../calendar/calendar.htm class=topNav>Calendar</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=" + imagePath + "common/images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../calendar/calendar.htm class=topNav>Calendar</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=" + imagePath + "common/images/right_green_inactive_tab.gif></td>");
		}
		document.write("</tr>");
		document.write("</table>");
	}

//this function renders sub navs for some sections under manage features
	/*params: active - sets the active tab
	*/
	function manageFeaturesSubNav(active){
		if (active == "ooc"){
		document.write("<table border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr>");
		document.write("<td>&nbsp;<a href=search.htm>Create</a> <b>|</b> <a href=caseSearch.htm>Modify</a></td>");
		document.write("</tr>");
		document.write("</table>");
		}
		if (active == "Dept"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=deptPolicyCreate.htm>Create</a> <b>|</b> <a href=deptPolicySearch.htm>Search</a></td>");
			document.write("</tr>");
			document.write("</table>");
		}
		if (active == "Court"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=courtPolicyCreate.htm>Create</a> <b>|</b> <a href=courtPolicySearch.htm>Search</a></td>");
			document.write("</tr>");
			document.write("</table>");
		}
		if (active == "Conditions"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=conditionCreate.htm>Create</a> <b>|</b> <a href=conditionSearch.htm>Search</a></td>");
			document.write("</tr>");
			document.write("</table>");
		}
		if (active == "Process Order"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=search.htm>Create</a> <b>|</b> <a href=search.htm?mod>Modify</a> <b></td>");
			document.write("</tr>");
			document.write("</table>");
		}
		if (active == "Vendors"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=../adminServiceProvider/serviceProviderCreate.htm>Create CSCD/PTS</a> <a href=../adminServiceProviderHCJPD/serviceProviderCreate.htm>Create HCJPD</a> <b>|</b> <a href=#>Modify</a> <b></td>");
			document.write("</tr>");
			document.write("</table>");
		}
		if (active == "Staff"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=../supervisionStaff/search.htm>Manage Supervision Staff</a> <b>|</b> <a href=../supervisionStaff/caseloadAssignmentsList.htm>Administer Court Assignment</a> <b>|</b>  <a href=../supervisionStaff/reassignSuperviseeSearch.htm>Reassign Supervisee</a></td>");
			document.write("</tr>");
			document.write("</table>");
		}
		if (active == "Reports"){
			document.write("<table border=0 cellpadding=2 cellspacing=1>");
			document.write("<tr>");
			document.write("<td>&nbsp;<a href=../reports/assignmentsSearch.htm>View Assignments</a> <b>|</b> </td>");
			document.write("</tr>");
			document.write("</table>");
		}
	}

	function suggestedOrderSubNav(){
		document.write("<table border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr>");
		document.write("<td>&nbsp;<a href=../adminSugOrder/sugOrderCreate.htm class=blackSubNav>Create</a> <b>|</b> <a href=../adminSugOrder/sugOrderSearch.htm class=blackSubNav>Search</a></td>");
		document.write("</tr>");
		document.write("</table>");
		}

	function caseloadSubNav(){
		document.write("<table border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr>");
		document.write("<td>&nbsp;<a href=../caseloadPTS/assignmentList.htm?clerk class=blackSubNav>Assign Clerk</a> <b>|</b> <a href=../caseloadPTS/assignmentList.htm?officer class=blackSubNav>Assign Officer</a> <b>|</b> <a href=../caseloadPTS/reassignSuperviseeSearch.htm class=blackSubNav>Reassign Supervisee</a></td>");
		document.write("</tr>");
		document.write("</table>");
		}

	//show hide where there is an expand (plus sign)
	/*params: spanName - name of the span to hide or show - image has same id as span -"Span"
	*/
	function showHide(spanName, format){
		var appendName = spanName + "Span";
		var theClassName = document.getElementById(appendName).className;
		alert("path " + spanName);
		if (theClassName=='visible' || theClassName=='visibleTR' || theClassName=='visibleTable'){
			window.document.images[spanName].src=imagePath + "common/images/expand.gif";
			document.getElementById(appendName).className='hidden';
		}else {
				window.document.images[spanName].src=imagePath + "common/images/contract.gif";
				if(format=="row"){
						document.getElementById(appendName).className='visibleTR';
					}else if(format=="table"){
							document.getElementById(appendName).className='visibleTable';
						}else if(format=="inline"){
							document.getElementById(appendName).className='visibleInline';
							}else {
								document.getElementById(appendName).className='visible';
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

 	//path is hardcoded for prototype
 	//path = "../../common"; Richard Young commented out

  var appendName = rowPrepend + rowNums;

  if (imgName != ""){
  	var currentImage = window.document.images[imgName].src;

	  if (currentImage.indexOf("contract") < 0)
	  {
	   window.document.images[imgName].src=path+"/images/contract.gif";
	  } else{
	   window.document.images[imgName].src=path+"/images/expand.gif";
	  }
	}

  for (var intI=0; intI<rowNums; intI++) {
   appendName = rowPrepend + intI;
   if (document.getElementById(appendName).className=='visibleTR')
   {
    document.getElementById(appendName).className='hidden';
   } else{
    document.getElementById(appendName).className='visibleTR';
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

				if (el.checked==true && ob_t2in[i].disabled==false) {
						ob_t2in[i].checked=true;
					}
				else {
					if (ob_t2in[i].disabled==false) {
						ob_t2in[i].checked=false;
					}
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
		if(el.options[el.selectedIndex].value == "I"){
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

//for group drop downs - imitating configurator
	/*params: activateID - what ID to enable
		el - the select itself (this)
		lastLevelID - if there is a 3rd level what is it's ID?
	*/
function enableSelectByID(activateID, el, lastLevelID)
	{
		if (el){
			if (el.options[el.selectedIndex].value != ""){				
				document.getElementById(activateID).disabled=false;
			}else{
					document.getElementById(activateID).selectedIndex = 0;
				 document.getElementById(activateID).disabled=true;
				 if (lastLevelID){
				 	document.getElementById(lastLevelID).selectedIndex = 0;
				 	document.getElementById(lastLevelID).disabled=true;	
					}
				}
		}
	}

//shows the cases that share the same like condition
	/*params: theId - the Id of the link that is clicked
						rowName - the name of the row(s) that need to be rendered
						howMany - the number of cases that share the like condition
	*/
	function showOtherCases(theId, rowName, howMany){
		var rowString;

		if (document.getElementById(theId).innerHTML == "View Other Cases"){
			for(i=0;i<howMany;i++){
				rowString = rowName + i;
				show(rowString, 1, "row");
			}
			//switch the text of the link
			document.getElementById(theId).innerHTML = "Hide Other Cases";
		}else{
			for(i=0;i<howMany;i++){
				rowString = rowName + i;
				show(rowString, 0);
			}
			//switch the text of the link
			document.getElementById(theId).innerHTML = "View Other Cases";
		}

	}

//shows the associated standards/policies
	/*params: theId - the Id of the link that is clicked
						conditionName - the condition name - the row id is the conditionName + "AssocRow"
	*/
	function showAssociations(theId, conditionName){
		var rowString = conditionName + "AssocRow";

		if (document.getElementById(theId).innerHTML == "View Standards"){
				show(rowString, 1, "row");
			document.getElementById(theId).innerHTML = "Hide Standards";
		}else{
			show(rowString, 0);
			document.getElementById(theId).innerHTML = "View Standards";
		}

	}

//selects all like conditions when the grouped condition is selected
	/*params: el - the checkbox form element
						checkboxName - the checkbox name that automatically get checked
						theId - theId of the link that renders the other like conditions
						rowName - the name of the row(s) that need to be rendered
						howMany - the number of cases that share the like condition
	*/
	function selectOtherConditionsAndExpand(el, checkboxName, theId, rowName, howMany){
		if(el.checked){
			if (document.getElementById(theId).innerHTML == "View Other Cases"){
				checkAll(el, checkboxName);
				showOtherCases(theId, rowName, howMany);
			}
			hideViewOther(theId)
		}
	}

	//hides the View Other Cases link
	/*params: theId - the Id of the link that is clicked
	*/
	function hideViewOther(theId){
		document.getElementById(theId).innerHTML = "";
	}

//this function adds rows to the Task List section of MSO (2 colums - 2 dropdowns)
	/*params: tableId - which table to add row to
	*/
function addTaskListRowToTable(tableId)
{
  var tbl = document.getElementById(tableId);
  var lastRow = tbl.rows.length;
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);

  // right cell
  var cellRight = row.insertCell(0);

  var el = document.createElement('select');
	el.setAttribute('name','recipient' + iteration);
	el.setAttribute('id','recipient' + iteration);
	el.setAttribute('size','1');
	cellRight.appendChild(el);
	var el1 = document.createElement('option');
	el1.innerText = 'Please Select';
	el1.setAttribute('value','');
	el.appendChild(el1);
	var el1 = document.createElement('option');
	el1.innerText = 'Officer';
	el1.setAttribute('value','Officer' + iteration);
	el.appendChild(el1);
	var el1 = document.createElement('option');
	el1.innerText = 'Docket Clerk';
	el1.setAttribute('value','Docket Clerk' + iteration);
	el.appendChild(el1);
	var el1 = document.createElement('option');
	el1.innerText = 'Manager';
	el1.setAttribute('value','Manager' + iteration);
	el.appendChild(el1);
	var el1 = document.createElement('option');
	el1.innerText = 'Other';
	el1.setAttribute('value','Other' + iteration);
	el.appendChild(el1);


   // 3rd cell
  var cellRight = row.insertCell(1);

  var el = document.createElement('select');
	el.setAttribute('name','recipient' + iteration);
	el.setAttribute('id','recipient' + iteration);
	el.setAttribute('size','1');
	cellRight.appendChild(el);
	var el2 = document.createElement('option');
	el2.innerText = 'Please Select';
	el2.setAttribute('value','');
	el.appendChild(el2);
	var el2 = document.createElement('option');
	el2.innerText = 'ACTION LIST';
	el2.setAttribute('value','ACTION LIST' + iteration);
	el.appendChild(el2);
	var el2 = document.createElement('option');
	el2.innerText = 'OFFICE VISIT';
	el2.setAttribute('value','OFFICE VISIT' + iteration);
	el.appendChild(el2);

	}

//renders the detail dictionary for court policies and conditions
	/*params: howTall - work in progress- this would determine how tall to render the scrolling div
	*/
function renderAllegationDetailDictionary(){

document.write("<table width=100% border=0 cellspacing=0 cellpadding=2 class=borderTableBlue>");
document.write("<tr>");
document.write("<td class=detailHead>Detail Dictionary</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td>");
document.write("<table width=100% border=0 cellspacing=1 cellpadding=2>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Name");
document.write("</td>");
document.write("<td class=formDe>");
document.write("<input type=text>");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Description");
document.write("</td>");
document.write("<td class=formDe>");
document.write("<input type=text style=width:100%>");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Type");
document.write("</td>");
document.write("<td class=formDe><select><option>Please Select</option><option>Reference</option><option>Variable</option></select>");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=formDe>");
document.write("</td>");
document.write("<td class=formDe>");
document.write("<input type=button value=Filter> <input type=button value='Show All'>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
document.write("<div class=scrollingDiv400>");
document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Name");
document.write("</td>");
document.write("<td class=formDeLabel>");
document.write("Description");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td><u>{{RevokedToLocation}}</u> </td>");
document.write("<td>");
 document.write("Revoked To Location");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{RevokePlea}}</u> </td>");
document.write("<td>");
 document.write("Revoke Plea By Supervisee");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{ModifyCOCS}}</u> </td>");
document.write("<td>");
 document.write("Modify COCS");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{ModifyCOCSDate}}</u> </td>");
document.write("<td>");
 document.write("Modify COCS Date");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{ModifyCOCSSupervisionStaff}}</u> </td>");
 document.write("<td>");
document.write("Modify COCS SupervisionStaff");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{ViolationDrugDate}}</u> </td>");
 document.write("<td>");
document.write("Date of Drug Violation");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{ViolationDrugName}}</u> </td>");
 document.write("<td>");
document.write("Drug name in Violation");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{LocationOfDrugCollection}}</u> </td>");
 document.write("<td>");
document.write("Location of Drug Collection");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{ViolationDrugSubstance}}</u> </td>");
document.write("<td>");
 document.write("Violation Drug Substance");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{OccuranceDate}}</u> </td>");
document.write("<td>");
 document.write("Occurance Date");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{OccuranceTime}}</u> </td>");
document.write("<td>");
 document.write("Occurance Time");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{UAMonitorStaff}}</u> </td>");
 document.write("<td>");
document.write("UA Monitor Staff");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{Alc-DrugEvalCompByDate}}</u> </td>");
document.write("<td>");
 document.write("Alcohol-Drug Evaluation Complete By Date");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{ANONMeetingsAttend}}</u> </td>");
 document.write("<td>");
document.write("AnnonMeetingsAttend");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#ffffff>");
document.write("<td><u>{{ANONMeetingComplete}}</u> </td>");
 document.write("<td>");
document.write("AnnonMeetingComplete");
document.write("</td>");

document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");


document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("</table>");
document.write("</div>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
}


//renders the detail dictionary for court policies and conditions
	/*params: howTall - work in progress- this would determine how tall to render the scrolling div
	*/
function renderDetailDictionary(howTall, special){

document.write("<table width=100% border=0 cellspacing=0 cellpadding=2 class=borderTableBlue>");
document.write("<tr>");
document.write("<td class=detailHead>Detail Dictionary</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td>");
document.write("<table width=100% border=0 cellspacing=1 cellpadding=2>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Name");
document.write("</td>");
document.write("<td class=formDe>");
document.write("<input type=text>");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Description");
document.write("</td>");
document.write("<td class=formDe>");
document.write("<input type=text style=width:100%>");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Type");
document.write("</td>");
document.write("<td class=formDe>");
if (special){
	document.write("Variable");
	}else{
		document.write("<select><option>All</option><option>Reference</option><option>Variable</option></select>");
	}
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=formDe>");
document.write("</td>");
document.write("<td class=formDe>");
document.write("<input type=button value=Filter> <input type=button value='Show All'>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
document.write("<div class=scrollingDiv400>");
document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
document.write("<tr>");
document.write("<td class=formDeLabel>");
document.write("Name");
document.write("</td>");
document.write("<td class=formDeLabel>");
document.write("Description");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td><u>{{ Much longer name example}}</u> </td>");
document.write("<td>");
document.write("2 Consecutive Months");
document.write("</td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td>");
document.write("<u>{{Event Count}}</u> ");
document.write("</td>");
document.write("<td>");
document.write("2");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td><u>{{DOPDay}}</u> </td>");
document.write("<td>");
document.write("Date Of Probation Day");
document.write("</td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{DOPMnth}}</u> </td>");
document.write("<td>");
document.write("Date Of Probation Month");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td><u>{{DOPYr}}</u> </td>");
document.write("<td>");
document.write("Date Of Probation Year");
document.write("</td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td><u>{{Value1}}</u> </td>");
document.write("<td>");
document.write("Text text text");
document.write("</td>");
document.write("</tr>");
if (!special){
document.write("<tr bgcolor=#ccffcc>");
document.write("<td class=boldText>");
document.write("<u>[Judge Name]</u> ");
document.write("</td>");
document.write("<td>");
document.write("This is the judge's name");
document.write("</td>");
document.write("</tr>");
}
document.write("<tr>");
document.write("<td><u>{{NumOfDays}}</u> </td>");
document.write("<td>");
document.write("Number of Days");
document.write("</td>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td>");
document.write("<u><u>Name Value</u> </u>");
document.write("</td>");
document.write("<td>");
document.write("Text text text");
document.write("</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("<tr bgcolor=#f0f0f0>");
document.write("<td> <u>Name Value</u>  </td>");
document.write("<td> Text text text </td>");
document.write("</tr>");
document.write("</table>");
document.write("</div>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
}


function renderProcessOrderCaseHeader(CDI){
document.write("<table width=98% border=0 cellpadding=2 cellspacing=1 class=borderTableBlue>");


switch (CDI)
{
	case "001":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td width=1%>Pretrial Interview</td>");
	document.write("<td width=1%>Case Filed</td>");
	document.write("<td width=1%>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td width=1%>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='001'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>001</td>");
	document.write("<td>9816323 01010</td>");
	document.write("<td>021</td>");
	document.write("<td>POSSESS MARIJUANA</td>");
	document.write("<td></td>");
	document.write("<td></td>");
	document.write("<td>");
	if (location.search=="?confirm"){
		document.write("Draft");
	}
	document.write("</td>");
	document.write("<td>");
	if (location.search=="?confirm" || location.search=="?print"){
		document.write("Original");
	}
	document.write("</td>");
	document.write("<td></td>");
	document.write("</tr>");
  break

  case "002inc":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td width=1%>Pretrial Interview</td>");
	document.write("<td width=1%>Case Filed</td>");
	document.write("<td width=1%>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td width=1%>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='001'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>001</td>");
	document.write("<td>9816323 01010</td>");
	document.write("<td>021</td>");
	document.write("<td>POSSESS MARIJUANA</td>");
	document.write("<td></td>");
	document.write("<td></td>");
	document.write("<td>");
	document.write("Incomplete");
	document.write("</td>");
	document.write("<td>");

	document.write("</td>");
	document.write("<td></td>");
	document.write("</tr>");
  break

  case "001a":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='001'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>001</td>");
	document.write("<td>9816323 01010</td>");
	document.write("<td>021</td>");
	document.write("<td>POSSESS MARIJUANA</td>");
	document.write("<td></td>");
	document.write("<td></td>");
	document.write("<td>");
	document.write("Draft");
	document.write("</td>");
	document.write("<td>Original</td>");
	document.write("</tr>");
  break

  case "001b":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='001'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>001</td>");
	document.write("<td>9816323 01010</td>");
	document.write("<td>021</td>");
	document.write("<td>POSSESS MARIJUANA</td>");
	document.write("<td></td>");
	document.write("<td>11/01/2004</td>");
	document.write("<td>");
	document.write("Pending");
	document.write("</td>");
	document.write("<td>Original</td>");
	document.write("<td>" + getCurrentDate() + "</td>");
	document.write("</tr>");
  break

	case "002":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='002'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>002</td>");
	document.write("<td>9816324 01010</td>");
	document.write("<td>022</td>");
	document.write("<td>ATTEMPT TO POSS CONTROL SUBS</td>");
	document.write("<td><a href=\"javascript:alert('This will pop up the pretrial interview details')\">#12345678</a> 01/10/2004</td>");
	document.write("<td>01/10/2004</td>");
	document.write("<td>");
	if (location.search=="?confirmWdraw"){
		document.write("Inactive");
	}else if (location.search=="?filedConfirm"){
		document.write("Active");
	}else document.write("Pending");
	document.write("</td>");
	document.write("<td>");
	document.write("Amended");
	document.write("</td>");
	document.write("<td>");
	if (location.search.indexOf("confirm")>0 || location.search=="?filed"){
		document.write(getCurrentDate());
	}else document.write("10/10/2004");
	document.write("</td>");
	document.write("</tr>");
  break

	case "003":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='002'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>003</td>");
	document.write("<td>9816326 01010</td>");
	document.write("<td>022</td>");
	document.write("<td>NARC PARAPHERNALIA</td>");
	document.write("<td><a href=\"javascript:alert('This will pop up the pretrial interview details')\">#12345688</a> 01/10/2004</td>");
	document.write("<td>11/05/2002</td>");
	document.write("<td>");
	if (location.search=="?confirmReinstate"){
		document.write("Active");
	}else document.write("Inactive");
	document.write("</td>");
	document.write("<td>Original</td>");
	document.write("<td>");
	if (location.search.indexOf("confirm")>0){
		document.write(getCurrentDate());
	}else document.write("");
	document.write("</td>");
	document.write("</tr>");
  break
	case "025":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='002'>");
	document.write("<td>Wells, Chris</td>");
	document.write("<td>13121212</td>");
	document.write("<td>Def</td>");
	document.write("<td>025</td>");
	document.write("<td>9816327 01010</td>");
	document.write("<td>022</td>");
	document.write("<td>POSS METHAMPHETAMINE</td>");
	document.write("<td><a href=\"javascript:alert('This will pop up the pretrial interview details')\">#12345679</a> 01/10/2004</td>");
	document.write("<td>01/05/2003</td>");
	document.write("<td>");
	if (location.search=="?confirmmodimp"){
		document.write("Draft");
	}else document.write("Active");
	document.write("</td>");
	document.write("<td>");
	if (location.search=="?confirmmodimp"){
		document.write("Modified");
	}else document.write("3rd Amended");
	document.write("</td>");
	document.write("<td>01/08/2005</td>");
	document.write("</tr>");
  break
	case "035":

  break
	case "002a":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='002'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>002</td>");
	document.write("<td>9816329 01010</td>");
	document.write("<td>022</td>");
	document.write("<td>POSS METHAMPHETAMINE</td>");
	document.write("<td></td>");
	document.write("<td>01/05/2004</td>");
	document.write("<td>Draft</td>");
	document.write("<td>2nd Amended</td>");
	document.write("<td>01/28/2005</td>");
	document.write("</tr>");

  break
	case "020":
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='002'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>020</td>");
	document.write("<td>9816330 01010</td>");
	document.write("<td>022</td>");
	document.write("<td>POSSESS MARIJUANA</td>");
	document.write("<td><a href=\"javascript:alert('This will pop up the pretrial interview details')\">#12345000</a> 01/10/2004</td>");
	document.write("<td>01/05/2004</td>");
	document.write("<td>Active</td>");
	document.write("<td>Original</td>");
	document.write("<td></td>");
	document.write("</tr>");
  break
	default:
	document.write("<tr class=detailHead>");
	document.write("<td width=15%>Name</td>");
	document.write("<td>SPN</td>");
	document.write("<td title='Connection Code'>CON</td>");
	document.write("<td title='Court Division Indicator'>CDI</td>");
	document.write("<td>Case #</td>");
	document.write("<td title='Court'>CRT</td>");
	document.write("<td>Offense</td>");
	document.write("<td>Pretrial Interview</td>");
	document.write("<td>Case Filed</td>");
	document.write("<td>Order Status</td>");
	document.write("<td title='Version'>Version</td>");
	document.write("<td>Order Filed</td>");
	document.write("</tr>");
	document.write("<tr id='001'>");
	document.write("<td>Smith, John, M</td>");
	document.write("<td>12345678</td>");
	document.write("<td>Def</td>");
	document.write("<td>001</td>");
	document.write("<td>9816323 01010</td>");
	document.write("<td>021</td>");
	document.write("<td>POSSESS MARIJUANA</td>");
	document.write("<td></td>");
	document.write("<td></td>");
	document.write("<td></td>");
	document.write("<td></td>");
	document.write("</tr>");
	}

document.write("</table>");

}

function renderASPHeader(outSource){
document.write("<table cellpadding=1 cellspacing=0 border=0 width=98%>");
document.write("<tr>");
document.write("<td bgcolor=#cccccc>");
document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
document.write("<tr>");
document.write("<td class=headerLabel width=1% nowrap>Provider Name</td>");
document.write("<td colspan=3 class=headerData>Travis & Associates</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=headerLabel>Start Date</td>");
document.write("<td class=headerData>12/12/2000</td>");
document.write("<td class=headerLabel width=1%>In House</td>");
document.write("<td class=headerData>");
if (outSource){
	document.write("No");
}else document.write("Yes");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
}

function renderASPHeaderProgram(){
document.write("<table cellpadding=1 cellspacing=0 border=0 width=98%>");
document.write("<tr>");
document.write("<td bgcolor=#cccccc>");
document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
document.write("<tr>");
document.write("<td class=headerLabel width=1% nowrap>Provider Name</td>");
document.write("<td colspan=3 class=headerData>Travis & Associates</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=headerLabel>Start Date</td>");
document.write("<td class=headerData>12/12/2000</td>");
document.write("<td class=headerLabel>In House</td>");
document.write("<td class=headerData>Yes</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=headerLabel>Program&nbsp;Name</td>");
document.write("<td class=headerData colspan=3>Alcoholics Counseling</td>");
document.write("</tr>");
document.write("<tr>");
document.write("<td class=headerLabel>Target&nbsp;Intervention</td>");
document.write("<td class=headerData colspan=3>Substance Abuse Prevention/Intervention </td>");
document.write("</tr>");
document.write("</table>");
document.write("</td>");
document.write("</tr>");
document.write("</table>");
}

function confirmDeleteLink(msg, forwardURL){
	if (confirm(msg)){
		if (forwardURL != ""){
			goNav(forwardURL);
			return true;
		}
	}
}
	/* this function will dynamically change the text for a cell in a table
		 pass the ID value of the TD and the new text 
*/ 
function setCellText( theID, textToSet )
{ 
    document.getElementById( theID ).innerHTML = textToSet ;
}

function reloadPage(){
  window.location.reload();
}
