/* modified version of script found in dheading.jsp */
function helpPopUp(){
	var z = document.getElementsByName("helpFile");
	if (z.length != 1) 
	{
		alert("No Help file reference found for this page.");
		return false;
	}
	var strHelpFile = z[0].value;
	if (strHelpFile.length == 0)
	{
		alert("No Help file reference found for this page.");
		return false;	
	}
	var indx = strHelpFile.indexOf('|');
	var useCase = strHelpFile;
	var mapId = 0;
	if (indx > -1)
	{
		useCase = strHelpFile.substring(0,indx);
        mapId = strHelpFile.substring(indx + 1,indx + 4);
		if (isNaN(mapId) == true)
		{  
			mapId = 0;
		}
	}
	var docURL = document.URL.toUpperCase();
/* these test region check values made need refining */	
	var localIndx = docURL.indexOf('LOCAL');
	var qaIndx = docURL.indexOf('QA');
	var indx9080 = docURL.indexOf('9080');
/*  5/14/10 LDeen added additional region indexes */	
	var testIndx = docURL.indexOf('TEST');
	var trainIndx = docURL.indexOf('TR');
	var perfIndx = docURL.indexOf('PERF');
/*	alert(docURL + "\nlocalIndx=" + localIndx + "\nqaIndx=" + qaIndx + "\nindx9080=" + indx9080); */
/* Use this url for production  region */
   var url  = "https://help.jims2.hctx.net/" + useCase; 
/* Use this url for test regions */
	if (localIndx > 0 || qaIndx > 0 || indx9080 > 0 || testIndx > 0 || trainIndx > 0 || perfIndx > 0){
		var url  = "http://webtest2/" + useCase; 
	}
/*  RH_ShowHelp does not work to Firefox browser, use window.open() instead */	
	if (mapId == 0){ 
		var	wt = screen.width - 100;
		var	ht = screen.height - 180;
		var windowSettings = "toolbar=no,location=no,directories=no,minimize=no," +
		           "status=no,menubar=no,scrollbars=yes,resizable=yes,titlebar=no," +
       		       "width=" + wt + ",height=" + ht + ",left=50,top=20"; 
		window.open(url,'',windowSettings);
		return false; 
	}	
	url = url + "<id=" + mapId;	
	RH_ShowHelp(0, url, HH_HELP_CONTEXT, mapId);
	return false;
 }
