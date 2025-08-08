
//constructor
function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.subgroups = new Array();
}

var groups = new Array(); //the array of group1




function updateGroup2(theForm)
{
	var group1Id = theForm.group1Id.options[theForm.group1Id.selectedIndex].value;
	theForm.group2Id.options.length = 0;
	theForm.group2Id.options[0] = new Option( "Please Select", "", false, false );
	if(theForm.group1Id.selectedIndex == 0)
	{
		
		
		theForm.group2Id.selectedIndex = 0; //reset choice back to default
		theForm.group3Id.selectedIndex = 0; //reset choice back to default
		theForm.group2Id.value="";
		theForm.group3Id.value="";
		theForm.group2Id.disabled = true; //disable group2 choice
		theForm.group3Id.disabled = true; //disable group3 choice
		
		return;
	}
	else
	{
		for(i in groups)
		{
			
			if(groups[i].id == group1Id)
			{
				
				for(j in groups[i].subgroups)
				{
					//alert(groups[i].subgroups[j].id+":"+groups[i].subgroups[j].name);
					theForm.group2Id.options[theForm.group2Id.options.length] = new Option( groups[i].subgroups[j].name, groups[i].subgroups[j].id);
				}
			}
		}
		if(theForm.group2Id.options.length>1){
		
			theForm.group2Id.disabled = false;
		
			theForm.group2Id.selectedIndex = 0; //reset choice back to default
			theForm.group3Id.selectedIndex = 0; //reset choice back to default
			theForm.group2Id.value="";
			theForm.group3Id.value="";
			theForm.group3Id.disabled = true;   //disable group3 choice because users should choose group2 before group3
		}
		else{
			theForm.group2Id.selectedIndex = 0; //reset choice back to default
			theForm.group2Id.value="";
			theForm.group2Id.disabled = true;
			theForm.group3Id.selectedIndex = 0; //reset choice back to default
			theForm.group3Id.value="";
			theForm.group3Id.disabled = true;
		}
	}	
	
	
	
	
	
}


function updateGroup3(theForm)
{
	var group1Id = theForm.group1Id.options[theForm.group1Id.selectedIndex].value;
	var group2Id = theForm.group2Id.options[theForm.group2Id.selectedIndex].value;
	
	theForm.group3Id.options.length = 0;
	theForm.group3Id.options[0] = new Option( "Please Select", "", false, false );
	
	if(theForm.group2Id.selectedIndex == 0){
		theForm.group3Id.selectedIndex = 0; //reset choice back to default
			theForm.group3Id.value="";
			theForm.group3Id.disabled = true;
		
	}
	else{
		
		
		
		
		for(i in groups)
		{
			if(groups[i].id == group1Id)
			{
				for(j in groups[i].subgroups)
				{
					if(groups[i].subgroups[j].id == group2Id)
					{
						for(k in groups[i].subgroups[j].subgroups)
						{
							theForm.group3Id.options[theForm.group3Id.options.length] = new Option( groups[i].subgroups[j].subgroups[k].name, groups[i].subgroups[j].subgroups[k].id);
						}
					}
				}
			}
		}
		if(theForm.group3Id.options.length>1){
		
			theForm.group3Id.disabled = false;
		
			theForm.group3Id.selectedIndex = 0; //reset choice back to default
			theForm.group3Id.value="";
		}
		else{
			theForm.group3Id.selectedIndex = 0; //reset choice back to default
			theForm.group3Id.value="";
			theForm.group3Id.disabled = true;
		}
		
	}
}

function reloadGroup(theForm, group2Id, group3Id){
	if (theForm.group1Id.options[theForm.group1Id.selectedIndex].value != ""){
		updateGroup2(theForm);
		for(i=0;i<theForm.group2Id.options.length;i++)
		{
			if(theForm.group2Id.options[i].value == group2Id)
			{
				theForm.group2Id.options.selectedIndex = i;
				break;
			}
		}
		
		if (theForm.group2Id.options[theForm.group2Id.selectedIndex].value != ""){
			updateGroup3(theForm);
			
			for(i=0;i<theForm.group3Id.options.length;i++)
			{
				if(theForm.group3Id.options[i].value == group3Id)
				{
					theForm.group3Id.options.selectedIndex = i;
					break;
				}
			}
		}
	}
}

function unhideGroups(theForm){
	var group1=theForm.group1Id;
	var group2=theForm.group2Id;
	var group3=theForm.group3Id;
	
	if(group1!=null && group1.disabled){
		group1.value="";
		group1.disabled=false;
	}
	if(group2!=null && group2.disabled){
		group2.value="";
		group2.disabled=false;
	}
	if(group3!=null && group3.disabled){
		group3.value="";
		group3.disabled=false;
	}
	return true;
}

function setFocus()
{
	var g1 = document.getElementById("g1");
	var g2 = document.getElementById("g2");
	var g3 = document.getElementById("g3");
	if (g1 != null && g1.className != "hidden")
	{
		var grp1Id = document.getElementById("group1Id");
		if (grp1Id != null)
		{
			grp1Id.focus();
			return;
		}	
	}
	if (g2 == null)
	{
		var grp2Id = document.getElementById("group2Id");
		if (grp2Id != null && grp2Id.options.length > 1)
		{
			grp2Id.focus();
			return;		
		}	
	}		
	if (g3 == null)
	{
		var grp3Id = document.getElementById("group3Id");
		if (grp3Id != null && grp3Id.options.length > 1)
		{
			grp3Id.focus();
			return;			
		}	
	}
	document.forms[0].elements["assocSearchCriteria.objName"].focus();	
	return;
}
