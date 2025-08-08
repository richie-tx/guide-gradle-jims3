//constructor
var activityCodes = new Array();
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
	
	if(theForm.group1Id.selectedIndex == 0)
	{
		theForm.group2Id.disabled = true; //disable group2 choice
		theForm.group3Id.disabled = true; //disable group3 choice
		
		theForm.group2Id.selectedIndex = 0; //reset choice back to default
		theForm.group3Id.selectedIndex = 0; //reset choice back to default
		
		return;
	}
	else
	{
		theForm.group2Id.disabled = false;
		theForm.group3Id.disabled = true;   //disable group3 choice because users should choose group2 before group3
		theForm.group2Id.selectedIndex = 0; //reset choice back to default
		theForm.group3Id.selectedIndex = 0; //reset choice back to default
	}	
	theForm.group2Id.options.length = 0;
	theForm.group2Id.options[0] = new Option( "Please Select", "", false, false );
	
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
	
	
}


function updateGroup3(theForm)
{
	var group1Id = theForm.group1Id.options[theForm.group1Id.selectedIndex].value;
	var group2Id = theForm.group2Id.options[theForm.group2Id.selectedIndex].value;
	
	
	if(theForm.group2Id.selectedIndex == 0)
		theForm.group3Id.disabled = true;
	else
		theForm.group3Id.disabled = false;
		
	theForm.group3Id.options.length = 0;
	theForm.group3Id.options[0] = new Option( "Please Select", "", false, false );
	
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
}

function subTypes(code, description)
{
	this.code = code;
	this.description = description;
	this.subTypes = new Array();
}

function updateType(theForm)
{
	if(typeof theForm!='undefined'){
		var selectedCategoryId;
		if(typeof theForm.selectedCategoryId!='undefined'){
		  selectedCategoryId = theForm.selectedCategoryId.options[theForm.selectedCategoryId.selectedIndex].value;	
		}
		theForm.selectedTypeId.options.length = 0;
		theForm.selectedTypeId.options[0] = new Option( "Please Select", "", false, false );
		if(theForm.selectedCategoryId.selectedIndex == 0)
		{
			theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
			theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
			theForm.selectedTypeId.value="";
			theForm.selectedDescriptionId.value="";
			return;
		}
		else
		{
				//theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
				//theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
		//	}	
			
		//    var k=0;
			for(i in activityCodes)
			{
				
				if(activityCodes[i].code == selectedCategoryId)
				{
					for(j in activityCodes[i].subTypes)
					{
						theForm.selectedTypeId.options[theForm.selectedTypeId.options.length] = new Option( activityCodes[i].subTypes[j].description, activityCodes[i].subTypes[j].code);
						
					}
				}
			}	
					
		//			if(theForm.activityType.value != "")
		//			{
					
		//				while(k<theForm.selectedTypeId.options.length){					
		//					if(theForm.selectedTypeId.options[k].value == theForm.activityType.value)
		//						theForm.selectedTypeId.options[k].selected="selected";	
		//					k++;				
		//				}
						
		//			}
		//		}
		//	}
			if(theForm.selectedTypeId.options.length>1){
				
				theForm.selectedTypeId.disabled = false;
			
				theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
				theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
				theForm.selectedTypeId.value="";
				theForm.selectedDescriptionId.value="";
		//		theForm.selectedTypeId = true;   //disable group3 choice because users should choose group2 before group3
			}
			else{
				theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
				theForm.selectedTypeId.value="";
		//		theForm.selectedTypeId.disabled = true;
				theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
				theForm.selectedDescriptionId.value="";
		//		theForm.selectedDescriptionId.disabled = true;
			}
		}
	}
}

function updateActivity(theForm)
{
	if(typeof theForm!='undefined'){
		var selectedCategoryId;
		if(typeof theForm.selectedCategoryId!='undefined'){
			selectedCategoryId = theForm.selectedCategoryId.options[theForm.selectedCategoryId.selectedIndex].value;
		}
		var selectedTypeId = theForm.selectedTypeId.options[theForm.selectedTypeId.selectedIndex].value;
		
		theForm.selectedDescriptionId.options.length = 0;
		theForm.selectedDescriptionId.options[0] = new Option( "Please Select", "", false, false );
		
		if(theForm.selectedTypeId.selectedIndex == 0){
			theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
			theForm.selectedDescriptionId.value="";
	//		theForm.selectedDescriptionId.disabled = true;
			
		} else 
		{	
		   var b=0;
			for(i in activityCodes)
			{
				if(activityCodes[i].code == selectedCategoryId)
				{
					for(j in activityCodes[i].subTypes)
					{
						if(activityCodes[i].subTypes[j].code == selectedTypeId)
						{
							for(k in activityCodes[i].subTypes[j].subTypes)
							{
								theForm.selectedDescriptionId.options[theForm.selectedDescriptionId.options.length] = new Option( activityCodes[i].subTypes[j].subTypes[k].description, activityCodes[i].subTypes[j].subTypes[k].code);
							}
							if(theForm.activity.value != "")
							{
								while(b<theForm.selectedDescriptionId.options.length){											
									if(theForm.selectedDescriptionId.options[b].value == theForm.activity.value)
										theForm.selectedDescriptionId.options[b].selected="selected";	
									b++;				
								}						
								
							}
						}
					}
				}
			}	
			
			if(theForm.selectedDescriptionId.options.length>1){
				
				theForm.selectedDescriptionId.disabled = false;
			
				theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
				theForm.selectedDescriptionId.value="";
			}
			else{
				theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
				theForm.selectedDescriptionId.value="";
		//		theForm.selectedDescriptionId.disabled = true;
			}
		}
	}
}

function updateTypeVendor(theForm)
{
	if(typeof theForm!='undefined'){
		var selectedCategoryId;
		if(typeof theForm.selectedCategoryId!='undefined'){
		  selectedCategoryId = theForm.selectedCategoryId.options[theForm.selectedCategoryId.selectedIndex].value;	
		}
		for (x = 0; x<document.forms[0].selectedTypeId.length; x++){
			
			if (document.forms[0].selectedTypeId.options[x].value == document.forms[0].selectedGrp2Id.value){
				document.forms[0].selectedTypeId.selectedIndex = x;
				break;
			}
		}
		
		if(theForm.selectedCategoryId.selectedIndex == 0)
		{
			theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
			theForm.selectedDescriptionId.selectedIndex = 0; //reset choice back to default
			theForm.selectedTypeId.value="";
			theForm.selectedDescriptionId.value="";
			return;
		}
		
}
}

function updateTypeForView(theForm)
{
	if(typeof theForm!='undefined'){
		var selectedCategoryId;
		
		if(typeof theForm.selectedCategoryId!='undefined'){
		 selectedCategoryId = theForm.selectedCategoryId.options[theForm.selectedCategoryId.selectedIndex].value;
		}
		theForm.selectedDescriptionId.selectedIndex = 0;
		
		if(theForm.selectedCategoryId.selectedIndex == 0)
		{
			
			theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
			theForm.selectedTypeId.options.length = 0;
			theForm.selectedTypeId.options[0] = new Option( "Please Select", "", false, false );
			theForm.selectedDescriptionId.options.length=0;
			theForm.selectedDescriptionId.selectedIndex=0;
			theForm.selectedDescriptionId.options[0] = new Option( "Please Select", "", false, false )		
			return;
		}
		else
		{
			theForm.selectedTypeId.selectedIndex = 0; //reset choice back to default
			theForm.selectedDescriptionId.selectedIndex=0;
		}	
		theForm.selectedTypeId.options.length = 0;
		theForm.selectedTypeId.options[0] = new Option( "Please Select", "", false, false );
		theForm.selectedDescriptionId.options.length=0;
		theForm.selectedDescriptionId.options[0] = new Option( "Please Select", "", false, false )
		for(i in activityCodes)
		{
			if(activityCodes[i].code == selectedCategoryId)
			{
				for(j in activityCodes[i].subTypes)
				{
					theForm.selectedTypeId.options[theForm.selectedTypeId.options.length] = new Option( activityCodes[i].subTypes[j].description, activityCodes[i].subTypes[j].code);
				}
			}
		}
	}
}