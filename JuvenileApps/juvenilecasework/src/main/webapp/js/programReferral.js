//functionality used by the program referral

$(document).ready(function(){
	
	var outComeId 		= sessionStorage.getItem("outComeId");
	var ocSubOptionalId = sessionStorage.getItem("ocSubOptionalId");
	var ocSubRequiredId = sessionStorage.getItem("ocSubRequiredId");
	
	if ( outComeId != null ) {
		$("#outComeId").val( outComeId );
	}
	
	if ( ocSubOptionalId != null ){
		$("#ocSubOptionalId").val( ocSubOptionalId );
	}
	
	if ( ocSubRequiredId != null ){
		$("#ocSubRequiredId").val( ocSubRequiredId );
	}
	
	$("#outComeId").change(function(){
		//console.log( $("#outComeId").val() );
		sessionStorage.setItem("outComeId", $("#outComeId").val() );
		sessionStorage.setItem("ocSubOptionalId", $("#ocSubOptionalId").val() );
		sessionStorage.setItem("ocSubRequiredId", $("#ocSubRequiredId").val() );
		//$("#ocSubOptionalId").val("");
		//$("#ocSubRequiredId").val("");
		
	})
	
	$("#ocSubOptionalId").change(function(){
		sessionStorage.setItem("ocSubOptionalId", $("#ocSubOptionalId").val() );
	})
	
	$("#ocSubRequiredId").change(function(){
		sessionStorage.setItem("ocSubRequiredId", $("#ocSubRequiredId").val() );
	})
	
	var pgms = $("#providerProgramId");
	if(typeof $("#endDate") != "undefined")
	{	
		datePickerSingle( $("#endDate"), "End Date", false);		
	}
	if(typeof $("#entryDate") != "undefined")
	{		
		datePickerSingle( $("#entryDate"), "Begin Date", false);		
	}
	if (typeof $("#outComeId") != "undefined") {		
		if($("#outComeId").val() == 'S')
		{
			$("#ocSubBlank").hide();
			$("#ocSubOptional").show();
			$("#ocSubRequired").hide();
			setOutComeDescriptionSelect("ocSubOptionalId");
		}
		if($("#outComeId").val() == 'X')
		{
			$("#ocSubBlank").hide();
			$("#ocSubOptional").hide();
			$("#ocSubRequired").show();
			setOutComeDescriptionSelect("ocSubRequiredId");
		}
		if($("#outComeId").val() == 'D')
		{
			$("#ocSubBlank").show();
			$("#ocSubOptional").hide();
			$("#ocSubRequired").hide();
			alert("Is the juvenile deceased?  If so, continue.  If not, correct the outcome.");
			$("#outComeId").focus();
		}
	}
	$("#submitId").on("click",function(){
		var msg="";	
		var fld1 = document.getElementById("outComeId");
		if ( fld1.selectedIndex == 0 ) {				
    		if (msg == "") {
    			fld1.focus();
    		}
    		msg += "Outcome selection is required.\n";
    		alert(msg);
    		return false;
    	}
		
		/*if (fld1.selectedIndex == 0
				&& $("#closingEndDate").val() == "" ) {				
    		if (msg == "") {
    			fld1.focus();
    		}
    		msg += "Outcome selection is required.\n";
    		alert(msg);
    		return false;
    	}*/
		
		if (fld1.selectedIndex > 0) {
	    	
			  if ($("#ocSubBlankId").is(":visible")) {   			
	    			
	    			document.getElementById("selectedOutcomeSubcategoryId").value = "";
	    			document.getElementById("selectedOutcomeSubcategoryDesc").value = "";
	    		}
    	 
    		if ($("#ocSubOptionalId").is(":visible")) {
    			
    			fld2 = document.getElementById("ocSubOptionalId");
    			if (fld2.selectedIndex == 0){    				
    				document.getElementById("selectedOutcomeSubcategoryId").value = "";
    				document.getElementById("selectedOutcomeSubcategoryDesc").value = "";
    			} else {
    				document.getElementById("selectedOutcomeSubcategoryId").value = fld2.options[fld2.selectedIndex].value;
    				document.getElementById("selectedOutcomeSubcategoryDesc").value = fld2.options[fld2.selectedIndex].text;
    			}
    		}
    		if ($("#ocSubRequiredId").is(":visible")) {    		
    			fld2 = document.getElementById("ocSubRequiredId");
    			
    			if (fld2.selectedIndex == 0){
    				if (msg == "") {
    					fld2.focus();
    				}
    				msg += "Outcome Description selection is required for selected Outcome value.\n";
    				alert(msg);
    				return false;
    			} else {    			
    				document.getElementById("selectedOutcomeSubcategoryId").value = fld2.options[fld2.selectedIndex].value;
    				document.getElementById("selectedOutcomeSubcategoryDesc").value = fld2.options[fld2.selectedIndex].text;
    			}
    		}
		}
	    	
	});
	$("#outComeId").on("change", function(){
	
		if($("#outComeId").val() == 'S')
		{
			$("#ocSubBlank").hide();
			$("#ocSubOptional").show();
			$("#ocSubRequired").hide();
			setOutComeDescriptionSelect("ocSubOptionalId");
		}
		else if($("#outComeId").val() == 'X')
		{
			$("#ocSubBlank").hide();
			$("#ocSubOptional").hide();
			$("#ocSubRequired").show();
			setOutComeDescriptionSelect("ocSubRequiredId");
		}
		else if($("#outComeId").val() == 'D')
		{
			$("#ocSubBlank").show();
			$("#ocSubOptional").hide();
			$("#ocSubRequired").hide();
			alert("Is the juvenile deceased?  If so, continue.  If not, correct the outcome.");
			$("#outComeId").focus();
		}
		else
		{
			$("#ocSubBlank").show();
			$("#ocSubOptional").hide();
			$("#ocSubRequired").hide();
			$("#outComeId").focus();
		}
	});
	function setOutComeDescriptionSelect(optId){
		var val = document.getElementById("selectedOutcomeSubcategoryId").value;
		var ocSub = document.getElementById(optId);
		for (x=0; x< ocSub.length; x++){
			if (ocSub.options[x].value == val) {
				document.getElementById(optId).selectedIndex == x;
				break;
			}	
		}
	}
	if(typeof $("#juvServiceProviderId") != "undefined")
	{		
		
		if(typeof courtordered != "undefined")
		{
			if( courtordered == "true")
				$("#courtOrderedId1").prop("checked",true);
			else 
				$("#courtOrderedId1").prop("checked",false);
		}
		if(typeof $("#juvServiceProviderId") != "undefined")
		{
			if($("#juvServiceProviderId option:selected").index() == "0"){
				/*added for bug 45785*/
				if($("#refStatus").val()=="ACCEPTED"){
					$("#acp").show();
					$("#blk").hide();
					$("#ten").hide();
				}
				else if($("#refStatus").val()=="TENTATIVE"){
					$("#ten").show();
					$("#blk").hide();
					$("#acp").hide();
				}
				else{
					if($("#refStatus").val()==""){
						$("#blk").show();
						$("#acp").hide();
						$("#ten").hide();		
					}
				}
				/*added for bug 45785*/
				$("#juvServiceProviderId").focus();	
				$("#providerProgramId").attr("disabled","disabled");
			}
			else{
				var selId = $("#juvServiceProviderId").val();	
				if(typeof pgms[0] != "undefined")
				{					
					pgms[0].options.length = 0;					
					pgms[0].options[0] = new Option( "Please Select", "", false, false );
					/*added for bug 45785*/
					if($("#refStatus").val()=="ACCEPTED"){
						$("#acp").show();
						$("#blk").hide();
						$("#ten").hide();
					}
					else if($("#refStatus").val()=="TENTATIVE"){
						$("#ten").show();
						$("#blk").hide();
						$("#acp").hide();
					}
					else{
						if($("#refStatus").val()==""){
							$("#blk").show();
							$("#acp").hide();
							$("#ten").hide();		
						}
					}
					/*added for bug 45785*/
					for(i in providers)
					{
						//alert("providers[i].id"+providers[i].id);
						if(providers[i].id == selId)
						{					
							for(j in providers[i].serviceResponseEvent)
							{
								pgms[0].options[pgms[0].options.length] = new Option( providers[i].serviceResponseEvent[j].name, providers[i].serviceResponseEvent[j].id);
								break;	
							}
						}
					}
				}
			}
			if(typeof pgms[0] != "undefined")
			{
				var pgmLen = pgms[0].options.length;
				
				if(pgmLen >1)
				{
					
					pgms[0].disabled = false;
					var pn = document.getElementsByName("programReferral.providerProgramName");
					if (pn[0].value != null && pn[0].value > "")
					{					
						for (x=0; x<pgmLen; x++)
						{					
							if (pgms[0].options[x].text == pn[0].value)
							{
								pgms[0].selectedIndex = x;
								break;
							}			
						}
					}	
	//				pgms[0].value="";
				} else{
					pgms[0].selectedIndex = 0; //reset choice back to default
					pgms[0].value="";
					pgms[0].disabled = true;
				}
			}
			
		}
	}
	
	$("#progRefCommentsId").on('keyup mouseout', function(){
		textLimit($(this),1000);
		return false;
	});
	
	
	$("[name='programReferral.juvServiceProviderId']").on("change",function(){		
		pgms[0].options.length = 0;
		pgms[0].options[0] = new Option( "Please Select", "", false, false );	
		if($("#juvServiceProviderId option:selected").index() == "0"){	
			/*added for bug 45785*/
			if($("#refStatus").val()=="ACCEPTED"){
				$("#acp").show();
				$("#blk").hide();
				$("#ten").hide();
			}
			else if($("#refStatus").val()=="TENTATIVE"){
				$("#ten").show();
				$("#blk").hide();
				$("#acp").hide();
			}
			else{
				if($("#refStatus").val()==""){
					$("#blk").show();
					$("#acp").hide();
					$("#ten").hide();		
				}
			}
			/*added for bug 45785*/
			$("#providerProgramId").propr('selectedIndex', 0);
			$("#providerProgramId").attr("disabled","disabled");			
			return;
		}	
		
		for(i in providers)
		{
			if(providers[i].id == $("#juvServiceProviderId").val())
			{				
				var inHouse = providersType[i].inHouse;			

				if (inHouse == 'true'){
					$("#acp").show();
					$("#blk").hide();
					$("#ten").hide(); 
				} else {
					$("#ten").show(); 		
					$("#blk").hide();
					$("#acp").hide();
				}
				
				for(j in providers[i].serviceResponseEvent)
				{
					pgms[0].options[pgms[0].options.length] = new Option( providers[i].serviceResponseEvent[j].name, providers[i].serviceResponseEvent[j].id);
				}
				break;	
			}
		}
		
		if(pgms[0].options.length>1)
		{
			pgms[0].disabled = false;
			pgms[0].value="";
		} else{
			pgms[0].selectedIndex = 0; //reset choice back to default
			pgms[0].value="";
			pgms[0].disabled = true;
		}
	});

	
	$("#gotoServiceProvider").on('click', function(event){
		event.preventDefault();
		
		var serviceProviderId = $('#juvServiceProviderId').val();
		
		if(!serviceProviderId){
			alert("service provider Id is missing. Please check the data and then try again.");
			return false;
		}		
		
		var url = "/CommonSupervision/handleJuvServiceProviderSearchResults.do?submitAction=View&serviceProviderId=" + serviceProviderId + "&source=programReferralTab";
		spinner();
		window.location.href = url;

	});
	
});

