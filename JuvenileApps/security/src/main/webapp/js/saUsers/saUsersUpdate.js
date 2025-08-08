<!-- JavaScript for saUsersUpdate.jsp only -->
<!-- 05/18/2005  sprakash - this validates the fields with condition in addition to Struts validation -->

function uncheckOther(el)
{
	var theForm = el.form;
	var theName = el.name;
	var theValue = el.value;
	var objCheckBoxes = theForm.elements[theName];
	var countCheckBoxes = objCheckBoxes.length;

	if (el.value==theValue && el.checked)
	{
		for(var i = 0; i < countCheckBoxes; i++)
		{
			if (objCheckBoxes[i].checked && objCheckBoxes[i].value!=theValue)
			{
				objCheckBoxes[i].checked = false;
			}
		}
	}

}
