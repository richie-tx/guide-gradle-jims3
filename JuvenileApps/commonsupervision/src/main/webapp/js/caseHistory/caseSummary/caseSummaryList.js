// Order status      VR status        		allowable actoins
// active			draft				view, print, update, delete
// active			prefiled,not draft  view, print, update(via task), delete
// active			filed				view, print, create
// active			none				create
// inactive			pre filed			view, print, delete
// incomplete		none				none
// draft			none				none		
// pending			none				none
// withdrawn		none				none
// only active orders on this page

function checkSelection(theButton)
{
	var selectMade = false;
	var selectedVRStatus = "";
	var rbs = document.getElementsByName("csStatus");
	if (rbs.length == 0 || typeof(rbs.length)=="undefined"){
		alert("Report must be selected to " + theButton.value + ".");
		return false; 
	}
	for (x = 0; x < rbs.length; x++) {
		if (rbs[x].checked == true)	{
			selectMade = true;
			selectedVRstatus = rbs[x].value;
			break;
		}	
	}
	if (!selectMade) {
		alert("Report must be selected to " + theButton.value + ".");
		return false;	
	}	
// deselect all so if user selects back button on next page they will be forced to select again
// otherwise update, delete and maintain buttons can be active on filed status selection
	for (x = 0; x < rbs.length; x++) {
		rbs[x].checked = false;
	}	
	return true;
}

function disableButtons(vrStatus)
{
	var statusVal = document.getElementsByName("orderStatusVal");
	var btns = document.getElementsByName("SubmitAction");
	for (x=0; x < btns.length; x++)
	{
		btns[x].disabled = false;
		if (vrStatus == "FL"){
			if (btns[x].value == "Update" || btns[x].value == "Delete"){
				btns[x].disabled = true;
			}
		}	
		if (vrStatus != "DR"){
			if (btns[x].value == "Update" ){
				btns[x].disabled = true;
			}	
		}
		if (vrStatus != "FL" && statusVal[0].value == "INACTIVE"){
			if (btns[x].value == "Update") {
				btns[x].disabled = true;
			}
		}
	}
}
function setCSvalue(idValue){
	document.forms[0].violationReportId.value = idValue;
}

function selectSingleResult(){
	var rbs = document.getElementsByName("csStatus");
	if (rbs.length == 1){
		rbs[0].checked = true;
		disableButtons(rbs[0].value);
		var respId = document.getElementsByName("ncResponseId");
		setCSvalue(respId[0].value);
	}	
	return true;
}