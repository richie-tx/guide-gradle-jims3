
//constructor
function suborganization(id, name)
{
	this.id = id;
	this.name = name;
	this.suborganizations = new Array();
}

var organizations = new Array(); //the array of organization1

function sortOrganizations(){
	if(organizations!=null){
		var orgLength1=organizations;
		var a=0;
		for(a in organizations)
		{
			var tempOrg1=null;
			var b=0;
			b=(1*a + 1);
			while(b<orgLength1){
				
				if(organizations[a].name > organizations[b].name){
				  tempOrg1=organizations[b];
				  organizations[b]=organizations[a];
				  organizations[a]=tempOrg1;
				 
				}
				b=(1*b+1);
			}
		}
		a=0;
		for(c in organizations)
		{
			for(a in organizations[c].suborganizations){
				var orgLength1=organizations[c].suborganizations.length;
				var tempOrg1=null;
				var b=0;
				b=(1*a + 1);
				while(b<orgLength1){
					
					if(organizations[c].suborganizations[a].name > organizations[c].suborganizations[b].name){
					  tempOrg1=organizations[c].suborganizations[b];
					  organizations[c].suborganizations[b]=organizations[c].suborganizations[a];
					  organizations[c].suborganizations[a]=tempOrg1;
					 
					}
					b=(1*b+1);
				}
			}
		}
		for(d in organizations)
		{
			for(e in organizations[d].suborganizations)
			{
				for(a in organizations[d].suborganizations[e].suborganizations){
					var orgLength1=organizations[d].suborganizations[e].suborganizations.length;
					var tempOrg1=null;
					var b=0;
					b=(1*a + 1);
					while(b<orgLength1){
						
						if(organizations[d].suborganizations[e].suborganizations[a].name > organizations[d].suborganizations[e].suborganizations[b].name){
						  tempOrg1=organizations[d].suborganizations[e].suborganizations[b];
						  organizations[d].suborganizations[e].suborganizations[b]=organizations[d].suborganizations[e].suborganizations[a];
						  organizations[d].suborganizations[e].suborganizations[a]=tempOrg1;
						 
						}
						b=(1*b+1);
					}
				}
			}
		}
	
		
	}  // END IF Statement
}// END FUNCTION


				


function updateOrganization2(theForm,divisionName, programUnitName, sectionUnitName)
{
	var divisionElem=theForm[divisionName];
	var programUnitElem=theForm[programUnitName];
	var sectionUnitElem=theForm[sectionUnitName];
	var divisionId = divisionElem.options[divisionElem.selectedIndex].value;
	programUnitElem.options.length = 0;
	programUnitElem.options[0] = new Option( "Please Select", "", false, false );
	if(divisionElem.selectedIndex == 0)
	{
		programUnitElem.selectedIndex = 0; //reset choice back to default
		sectionUnitElem.selectedIndex = 0; //reset choice back to default
		programUnitElem.value="";
		sectionUnitElem.value="";
		programUnitElem.disabled = true; //disable organization2 choice
		sectionUnitElem.disabled = true; //disable organization3 choice
		
		return;
	}
	else
	{
		for(i in organizations)
		{
			
			if(organizations[i].id == divisionId)
			{
				
				for(j in organizations[i].suborganizations)
				{
					//alert(organizations[i].suborganizations[j].id+":"+organizations[i].suborganizations[j].name);
					programUnitElem.options[programUnitElem.options.length] = new Option( organizations[i].suborganizations[j].name, organizations[i].suborganizations[j].id);
				}
			}
		}
		if(programUnitElem.options.length>1){
		
			programUnitElem.disabled = false;
		
			programUnitElem.selectedIndex = 0; //reset choice back to default
			sectionUnitElem.selectedIndex = 0; //reset choice back to default
			programUnitElem.value="";
			sectionUnitElem.value="";
			sectionUnitElem.disabled = true;   //disable organization3 choice because users should choose organization2 before organization3
		}
		else{
			programUnitElem.selectedIndex = 0; //reset choice back to default
			programUnitElem.value="";
			programUnitElem.disabled = true;
			sectionUnitElem.selectedIndex = 0; //reset choice back to default
			sectionUnitElem.value="";
			sectionUnitElem.disabled = true;
		}
	}	
}


function updateOrganization3(theForm,divisionName, programUnitName, sectionUnitName)
{
	var divisionElem=theForm[divisionName];
	var programUnitElem=theForm[programUnitName];
	var sectionUnitElem=theForm[sectionUnitName];
	var divisionId = divisionElem.options[divisionElem.selectedIndex].value;
	var programUnitId = programUnitElem.options[programUnitElem.selectedIndex].value;
	
	sectionUnitElem.options.length = 0;
	sectionUnitElem.options[0] = new Option( "Please Select", "", false, false );
	
	if(programUnitElem.selectedIndex == 0){
		sectionUnitElem.selectedIndex = 0; //reset choice back to default
			sectionUnitElem.value="";
			sectionUnitElem.disabled = true;
		
	}
	else{
		
		
		
		
		for(i in organizations)
		{
			if(organizations[i].id == divisionId)
			{
				for(j in organizations[i].suborganizations)
				{
					if(organizations[i].suborganizations[j].id == programUnitId)
					{
						for(k in organizations[i].suborganizations[j].suborganizations)
						{
							sectionUnitElem.options[sectionUnitElem.options.length] = new Option( organizations[i].suborganizations[j].suborganizations[k].name, organizations[i].suborganizations[j].suborganizations[k].id);
						}
					}
				}
			}
		}
		if(sectionUnitElem.options.length>1){
		
			sectionUnitElem.disabled = false;
		
			sectionUnitElem.selectedIndex = 0; //reset choice back to default
			sectionUnitElem.value="";
		}
		else{
			sectionUnitElem.selectedIndex = 0; //reset choice back to default
			sectionUnitElem.value="";
			sectionUnitElem.disabled = true;
		}
		
	}
}

function reloadOrganization(theForm, divisionName, programUnitName, sectionUnitName, programUnitVal, sectionUnitVal){
	var divisionElem=theForm[divisionName];
	var programUnitElem=theForm[programUnitName];
	var sectionUnitElem=theForm[sectionUnitName];
	if (divisionElem.options[divisionElem.selectedIndex].value != ""){
		updateOrganization2(theForm, divisionName, programUnitName, sectionUnitName);
		for(i=0;i<programUnitElem.options.length;i++)
		{
			if(programUnitElem.options[i].value == programUnitVal)
			{
				programUnitElem.options.selectedIndex = i;
				break;
			}
		}
		
		if (programUnitElem.options[programUnitElem.selectedIndex].value != ""){
			updateOrganization3(theForm,divisionName, programUnitName, sectionUnitName);
			
			for(i=0;i<sectionUnitElem.options.length;i++)
			{
				if(sectionUnitElem.options[i].value == sectionUnitVal)
				{
					sectionUnitElem.options.selectedIndex = i;
					break;
				}
			}
		}
	}
}

function unhideOrganizations(theForm,divisionName, programUnitName, sectionUnitName){
	var organization1=theForm[divisionName];
	var organization2=theForm[programUnitName];
	var organization3=theForm[sectionUnitName];
	
	if(organization1!=null && organization1.disabled){
		organization1.value="";
		organization1.disabled=false;
	}
	if(organization2!=null && organization2.disabled){
		organization2.value="";
		organization2.disabled=false;
	}
	if(organization3!=null && organization3.disabled){
		organization3.value="";
		organization3.disabled=false;
	}
	return true;
}

