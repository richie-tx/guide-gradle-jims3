// BEGIN FUNCTIONS FOR CHECKBOXES
function checkLikeConditions(id,el)
{
	var idStr = 'LG' + id;
	if (!el.checked)
	{
		document.getElementById(idStr).checked = false;
	}
}

function checkAllConditions(id,cnt,el)
{
	var idStr = "";
	var setVal = true;
	if (!el.checked)
	{
		setVal = false
	}
	for(var x = 1; x <= cnt; x++){
		idStr = "LG" + id + "C" + x;
		document.getElementById(idStr).checked = setVal;
	}
}

function checkConditionSelects(compType)
{
	var compValue = "";
	var compValue2 = "";	
	var condType = "";
	var flds = document.getElementsByName("selectedConditionIds");
	var idStr = "";	
	var idxValue = 0;
	var matchFound = false;
	var str = "";	
// space at end of literal needed for display purposes
//	if (compType == "C")
//	{
//		condType = "compliant ";
//	}
	if (compType == "N")
	{
		condType = "Noncompliant ";
	}		
	if (flds.length == 0)
	{
		alert("At least 1 condition must be selected.");
		return msg;
	}

	for(var x = 0; x < flds.length; x++){
		if (flds[x].type == "checkbox")
		{
			if (flds[x].checked == true)
			{
				str = flds[x].id;
				compValue = document.getElementsByName(str);
				if ((compType == "C") || (compValue[1].value == "false" && compType == "N") || compType == "")
				{
					matchFound = true;
					break;
				}	
			}	
		} 
	}
	if (!matchFound)
	{
		alert("At least 1 " + condType + "condition must be selected.");
		return false;
	}
// should only be true for create casenotes	
	if (compType == "")
	{
		return true;
	}
// uncheck checkboxes so selected items only contains correct condition types
	if (compType == "N")
	{
		for(var x = 0; x < flds.length; x++){
			if (flds[x].type == "checkbox")
			{
				if (flds[x].checked == true)
				{
					str = flds[x].id;
					compValue = document.getElementsByName(str);
	//				if ((compValue[1].value == "true" && compType == "N") || (compValue[1].value == "false" && compType == "C"))
					if (compValue[1].value == "true")
					{
						compValue[0].checked = false;
	// uncheck single checkbox for like conditions if condition is not of correct type					
						indxValue = str.indexOf("C");
						if (indxValue > 0)
						{
							idStr = str.substring(0,indxValue);
							compValue2 = document.getElementsByName(idStr);	
							if (compValue2[1].value == "true")
					//		if ((compValue2[1].value == "true" && compType == "N") || (compValue2[1].value == "false" && compType == "C"))
							{
								document.getElementById(idStr).checked = false;
							}
						}
					}	
				}	
			} 
		}	
	}
	return true;	
}

function resetPagination(){              
    document.getElementsByName("pager.offset")[0].value=0; 
}