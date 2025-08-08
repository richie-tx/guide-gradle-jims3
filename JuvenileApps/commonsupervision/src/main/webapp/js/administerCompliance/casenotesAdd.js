function showHideCollateralAssociates(collVal, idToShow, format){
	if(collVal=="AS"){
		show(idToShow,1, format);
		document.forms[0].selectedAssociateIds.selectedIndex = 0;
	}else{
		show(idToShow,0);
	}
}
function displayAssociatesDropDown()
{
	var x = document.getElementsByName("collateralId");
	if (x[0].options.value == "AS")
	{
		show("collateralAssociates",1);
	}
}		
