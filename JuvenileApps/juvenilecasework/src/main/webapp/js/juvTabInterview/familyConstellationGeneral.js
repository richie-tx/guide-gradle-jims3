//Uses Jquery

$(document).ready(function (){
	
	if(typeof $("#dateOfBirth")!= "undefined"){
		datePickerSingle( $("#dateOfBirth"),"Date of Birth",true);
	}
	
	//all calls for goNav on all family Tab pages
	$("a#updateMemberLink,#updateMemberButton,#updateActiveCons,#dynamics,#addNewMember,#addNewMemberOther,#addNewMemberUpdate,#addNewMemberCreate").on("click", function(){
		goNav($(this).data("href"));
	});
	//all calls for goNav on family Tab for "back"
	$("#conListBack").on("click", function(){
		goNav("back");
	});
	$("input:radio[name$=detentionVisitationAsStr]").click(function(){		
		
		
		if($(this).val()=='false' && $(this).attr('title')=='true')
		{				
			var detVisits = parseInt($("#totalDetentionVisits").val());
			detVisits--;
			$("#totalDetentionVisits").val(""+detVisits);	
			var elemName = $(this).attr('name');
			$('[name="' +elemName + '"]').each(function(index){
				if(index==1)  //keeping track of previous selection
					$(this).attr('title', 'false');	
				if(index==0)
					$(this).attr('title', 'false');	
			});		
		}
		if($(this).val()=='true' && $(this).attr('title')!='true')
		{
			var detVisits 			= parseInt($("#totalDetentionVisits").val());
			var daVisit  			=  $("#daVisit").val();
			var visitorCapRemoved 	= $("#visitorCapRemoved").val();
			if ( detVisits >= 5
					&& visitorCapRemoved != "true" ){
				alert("Juvenile has a total of four contacts/family members plus one Defense Attorney with detention visit privileges. An edit of detention visit privileges is required to add a new detention visit.");
				var elemName = $(this).attr('name');
				$('[name="' +elemName + '"]').each(function(index){
					if(index==1)
						$(this).prop('checked', true);					
				});
				return false;
			}
			else if(detVisits == 4 
					&& daVisit == "false"
					&& visitorCapRemoved != "true")
			{
				alert("Maximum four members/contacts allowed detention visits at a time. Change detention visit for other members/contacts to proceed.");
				var elemName = $(this).attr('name');
				$('[name="' +elemName + '"]').each(function(index){
					if(index==1)
						$(this).prop('checked', true);					
				});
				return false;
			}
			else
			{
				detVisits++;
				$("#totalDetentionVisits").val(""+detVisits);
				var elemName = $(this).attr('name');
				$('[name="' +elemName + '"]').each(function(index){
					if(index==0)
						$(this).attr('title', 'true');
					if(index==1)
						$(this).attr('title', 'true');
				});				
			}
				
		}
	});
	
	
	//all calls for textLimit in family tab
	$("[name='currentTrait.traitComments'],[name='currentGuardian.notes']").on('keyup mouseout',function(){
		textLimit($(this),255);
		return false;
	});
	
	//calls casework.js's disableSubmitButtonCasework
	$("#traitCreateSaveCont,#guardianSummaryFinish,#constellationSummaryFinish,#conCreateSummaryFinish").click(function(){
		disableSubmitButtonCasework($(this));
		
	});
	
	//used in familyConstellationList.jsp
	//---------------------------------------------------------------------------------
	//formerly function confirmUpdateGuardians()
	$("#confirmUpdateGuardians").on("click",function(){
		return confirm("Proceeding to Update to change guardians will cause a new constellation to be created. Do you wish to continue?");
	});
	
	//formerly function showHideMultiRow(spanName, format, path){
	$("#showHideMultyRow").on("click", function(){
		var spanName = $(this).data("spanname");
		var format = $(this).data("format");
		var path = $(this).data("path");
		var appendName = "#" + spanName + "Span";
		var theClassName = $(appendName).attr("class");
		if (theClassName=='visible' || theClassName=='visibleTR' || theClassName=='visibleTable'){
			//window.document.images[spanName].src=path+"images/expand.gif";
			$("img[name='"+spanName+"']").attr("src",path+"images/expand.gif");
			$(appendName).attr("class",'hidden');
		}else {
				//window.document.images[spanName].src=path+"images/contract.gif";
				$("img[name='"+spanName+"']").attr("src",path+"images/contract.gif");
				if(format=="row"){
					$(appendName).attr("class",'visibleTR');
					}else if(format=="table"){
						$(appendName).attr("class",'visibleTable');
						}else {
							$(appendName).attr("class",'visible');
							}
			}
	});
	
	//used in familyMemberSearch.jsp
	//---------------------------------------------------------------------------------
	// formerly function validateThisPage(myRealForm){
	$("#submitMemberSearch").on("click", function(){
		var myRealForm = document.juvenileMemberSearchForm;
		if(validateJuvenileMemberSearchForm(myRealForm)){
			return validateSSNFamily( "ssn.SSN1","ssn.SSN2","ssn.SSN3", "SSN ", false );
		}
		else{
			return false;
		}
	});
	
	//taken from app.js for above function
	function validateSSNFamily( elem1Name,elem2Name,elem3Name, msgSSN, isRequired )
	{
		var elem1Val = Trim( $("[name='"+elem1Name+"']").val() );
		var elem2Val = Trim( $("[name='"+elem2Name+"']").val() );
		var elem3Val = Trim( $("[name='"+elem3Name+"']").val() );

		if( isRequired && elem1Val == "" && elem2Val == "" && elem3Val == "" )
		{
	    alert( msgSSN + " is required" );
	    $("[name='"+elem1Name+"']").focus( );

	    return false;
	   }

		if( elem1Val > "" && elem2Val == "" ) 
		{
	    alert( msgSSN + " must be entered if there is partial entry." );
	    $("[name='"+elem2Name+"']").val().focus( );

	    return false;
	  }

	  if( elem1Val > "" && elem3Val == "" ) 
	  {
	    alert( msgSSN + " must be entered if there is partial entry." );
	    $("[name='"+elem3Name+"']").val().focus( );

	    return false;
	  }

	  if( elem2Val > "" && elem1Val == "" ) 
	  {
	    alert( msgSSN + " must be entered if there is partial entry." );
	    $("[name='"+elem1Name+"']").focus( );

	    return false;
	  }

	  if( elem2Val > "" && elem3Val == "" ) 
	  {
	    alert( msgSSN + " must be entered if there is partial entry." );
	    $("[name='"+elem3Name+"']").val().focus( );

	    return false;
	  }

	  if( elem3Val > "" && elem1Val == "" ) 
	  {
	    alert( msgSSN + " must be entered if there is partial entry." );
	    $("[name='"+elem1Name+"']").focus( );

	    return false;
	  }

	  if( elem3Val > "" && elem2Val == "" ) 
	  {
	    alert( msgSSN + " must be entered if there is partial entry." );
	    $("[name='"+elem2Name+"']").val().focus( );

	    return false;
	  }  

		return true;
	}
	
	
	//used in familyConstellationUpdate.jsp
	//---------------------------------------------------------------------------------
	var checkboxselected = 0;
	var guardianChecked = 0;
	var radioChecked;
	
	//formerly reset() function
	if(document.title == "JIMS2 - familyConstellationUpdate.jsp")
	{
		checkboxselected = 0;
		guardianChecked = 0;
		if($("#famConUpdateStatus").val() == "update")
		{
			changeFormActionURL($("#tURL").val() , false);
		}
	}
	
	//formerly function setClicked(){
	if(document.title == "JIMS2 - familyConstellationUpdate.jsp")
	{
		$("input[type='radio'][name^='currentConstellation.memberList']").on('click', function(){
			radioChecked = true;
		});
	}
	
	//formerly function removeConfirm()
	$("[name='removeConLink']").on("click",function(){
		return confirm("You are about to remove a member from this constellation. Are you sure you want to continue?");
	});
	
	$("#validateUpdate,#validateCreate").on('click',function() {
		var ctr =$("[name='nestedMemberRow']").length;
		for(x=0; x<ctr; x++)
		{
			if (!validateRadiosCasework("currentConstellation.memberList["+x+"].inHomeStatusAsStr",'In Home Status is Required.'))
		   { 
			   return false;
		   }
		   if (!validateRadiosCasework("currentConstellation.memberList["+x+"].detentionHearingAsStr",'Detention/Hearing is Required.'))
		   { 
			   return false;
		   }
		   if (!validateRadiosCasework("currentConstellation.memberList["+x+"].detentionVisitationAsStr",'Visit is Required.'))
		   { 
			   return false;
		   }
		}
		var fldName = "";
	 	var msg = "";
		var primaryCareGiverCtr=0;
		var primaryCareGiverChecked;
		for (x=0; x<ctr; x++)
		{
			fldName = 'currentConstellation.memberList[' + x + '].inHomeStatusAsStr';
			if ($("[name='"+fldName+"']")[0].checked == true)
			{
				fldName = 'currentConstellation.memberList[' + x + '].incarcerated';
			 	if ($("[name='"+fldName+"']")[0].value == 'true')
			 	{
					fldName = 'currentConstellation.memberList[' + x + '].memberName.formattedName';
			 		msg += $("[name='"+fldName+"']")[0].value + " is incarcetated, 'In House Status' can not be Yes.\n";
			 	}	
		 	}
			
			//primary caregiver validation check.
			primaryCareGiver = 'currentConstellation.memberList[' + x + '].primaryCareGiverAsStr';
			if($("[name='"+primaryCareGiver+"']")[0].checked){
				primaryCareGiverCtr++;
			}
			//primary caregiver and guardian check
			fldName = 'currentConstellation.memberList[' + x + '].guardian';
			if ($("[name='"+fldName+"']")[0].value == 'true' && $("[name='"+primaryCareGiver+"']")[0].checked){
				primaryCareGiverChecked=true;
			}
		}
		if(primaryCareGiverCtr>2){
			msg +='Please select max of two primary care givers.\n';
		}
		if(primaryCareGiverChecked){
			msg +='Primary caregiver(s) would be someone other than the juvenile\'s guardian.Please select primary caregiver other than juvenile\'s guardian';
			primaryCareGiverChecked=false;
		}
		
		if (msg == "")
		{
			return true;
		}
		alert(msg);
		return false;
	});
	
	//Extracted from app.js to be used with familyConstillationUpdate.jsp
	function validateRadiosCasework( name, msg )
	{
		var myOption = (-1);

		if($("[name='"+name+"']").length > 1 )
		{
			for( i = 0; i < $("[name='"+name+"']").length; i++ )
			{
				if($("[name='"+name+"']")[ i ].checked )
				{
					myOption = i;
				}
			}

			if( myOption == (-1) )
			{
				alert( msg );

				return false;
			}
		}

		return true;
	}
	
	
	//used in familyConstellationGuardianSelection.jsp
	//---------------------------------------------------------------------------------
	
	//formerly checkTotalMembers() function
	if(document.title == 'JIMS2 - familyConstellationGuardianSelection.jsp')
	{
		var el = $("[name='memberNum']");
		if (el != null && el.length > 0){
			var radioCtr = 0;
			var radioIdx = 0;
			var fldName = "";
			var elName = "";
			for (x=0; x<el.length; x++) {
				elName = "currentConstellation.memberList[" + x + "].primaryContact";
				fldName = $("[name='"+elName+"']");
				if ( fldName.length > 0 || typeof($(fldName).value) != 'undefined') {
					radioCtr++;
					radioIdx = x;
				}
			};
			if (radioCtr == 1) {	
				fldName  = "currentConstellation.memberList[" + radioIdx + "].primaryContact"
				$("[name='"+fldName+"']").prop("checked",true);
				fldName  = "currentConstellation.memberList[" + radioIdx + "].guardian"
				$("[name='"+fldName+"']").prop("checked",true);
				$("#pcMemNum").val(el[radioIdx].value);
			}	
		};
		
		//formerly function resetPrimaryGuardianSelects(el){
		$("input[type='radio'][name^='currentConstellation.memberList']").on('click',function(){
			var el = $(this);
			$("#pcMemNum").val($(el).val());
			var flds = $("[name='memberNum']");
			if (flds != null){
				for (x=0; x<flds.length; x++){
					elName = "currentConstellation.memberList[" + x + "].primaryContact";
					fldName = $("[name='"+elName+"']");
					if ( fldName.length > 0 || typeof($(fldName).val()) != 'undefined') {
						$("[name='"+elName+"']")[0].checked = false;
						if (elName == $(el).attr("name")){
							$("#pcMemNum").val(flds[x].value);
						}
					}
				}
				$("[name='"+$(el).attr("name")+"']")[0].checked = true;
				$("[name='"+$(el).attr("name")+"']")[0].value = true;
			}
		});
	}
	
	
	//formerly function validateInputs(){
	$("#validateSelectionInputs").on("click",function(){
		var el = $("[name='memberNum']");
		var elChecked = 0;
		var elName = "";
		var msg = "";
		var primaryCareGiver;
		var primaryCareGiverChecked;
		if (el != null){
			for (x=0; x<el.length; x++) {
				elName = "currentConstellation.memberList[" + x + "].primaryContact";
				primaryCareGiver = 'currentConstellation.memberList[' + x + '].primaryCareGiver';
				fldName = $("[name='"+elName+"']");
				if ( fldName.length > 0 || typeof($(fldName).val()) != 'undefined') {
					if (fldName[0].checked == true)   // primary Guardian selected
					{
						elChecked = 1;
				    	elName =  "currentConstellation.memberList[" + x + "].guardian";
				    	break;
					}
				}
			}
			if (elChecked == 0){
				msg = "Primary Guardian selection is required.\n";
			} else if ($("[name='"+elName+"']")[0].checked == false){
				msg = "Primary Guardian selection must correspond to a selected Guardian.\n";
			}else {
				if ($("[name='"+primaryCareGiver+"']")[0].value == 'true'){
					primaryCareGiverChecked=true;
				}
			}	
			elChecked = 0;
			for (x=0; x<el.length; x++) {
				elName = "currentConstellation.memberList[" + x + "].guardian";
				primaryCareGiver = 'currentConstellation.memberList[' + x + '].primaryCareGiver';
				fldName = $("[name='"+elName+"']");
				if ( fldName.length > 0 || typeof($(fldName).val()) != 'undefined') {
					if (fldName[0].checked == true) // guardian selected
					{
						if ($("[name='"+primaryCareGiver+"']")[0].value == 'true'){
							primaryCareGiverChecked=true;
						}
						elChecked++;
						
					}
				}
			}	
			if(primaryCareGiverChecked){
				msg +='Primary caregiver(s) would be someone other than the juvenile\'s guardian.Please select primary caregiver other than juvenile\'s guardian\n';
			}
			if (elChecked == 0){
				msg += "At least 1 Guardian selection is required.";
			}
			if (elChecked > 2){
				msg += "No more than 2 Guardian selections allowed.";
			}
		}
		if (msg == ""){
			return true;
		}
		alert(msg);
		return false;
	});
			
	//used in familyMemberSelectionList.jsp
	//---------------------------------------------------------------------------------
	var webApp = /JuvenileCasework/;

	//formerly function checkSelect()
	$("#checkSelectedMember").on('click',function(){
		var sels = $("[name='twoLane']");
		var selMemberNum = "";
		for (x=0; x<sels.length; x++)
		{
			if (sels[x].checked == true)
			{
				selMemberNum = sels[x].value;
				break;		
			}
		}
		if (selMemberNum  != "")
		{
			file = webApp + "displayCreateFamilyMember.do?submitAction=Select";
			loadMember(file, selMemberNum);
			return true;
		} else {
			alert("Member selection required.");
			return false;
		} 	
	});
	
	function loadMember(file, memberNum){
		var myURL=file + "&selectedValue=" + memberNum;
		load(myURL,top.opener);
		window.close();
	}
	
	function load(file,target) {
	    if (target != '')
	        target.window.location.href = file;
	    else
	        window.location.href = file;
	}
	
	//formerly function setSelected(){
	$("#setSelectedBypass").on('click',function(){
		$("#hiddenVal").val("Bypass");
		window.close();
	});
	
	//family financial info update
	$("#updateButton").on('click', function(){	
		goNav($(this).data("href"));
	});
	
	$("#dynamicsAddToList").on('click',function(){
		var dynamic=$("#dynamic").val();
		var dynamicLevel=$("#dynamicLevel").val();
		var dynamicStatus=$("#dynamicStatus").val();
		
		return isDynamicsRequired(dynamic,dynamicLevel,dynamicStatus);
	});
	function isDynamicsRequired(dynamic,dynamicLevel,dynamicStatus){		
		if(dynamic=="" && dynamicLevel=="" && dynamicStatus==""){
			alert("Dynamic is required\n"+
				  "Dynamic Level is required\n"+
				  "Dynamic Status is required.");
			return false;
		}
		if(dynamic!="" && dynamicLevel=="" && dynamicStatus==""){
			alert("Dynamic Level is required\n"+
				  "Dynamic Status is required.");
			return false;
		}
		if(dynamic=="" && dynamicLevel!="" && dynamicStatus==""){
			alert("Dynamic is required\n"+
				  "Dynamic Status is required.");
			return false;
		}
		if(dynamic=="" && dynamicLevel=="" && dynamicStatus!=""){
			alert("Dynamic is required\n"+
				  "Dynamic Level is required.");
			return false;
		}
		return isFieldNotEmpty(dynamic, "Dynamic is required.", "#dynamic") && isFieldNotEmpty(dynamicLevel, "Dynamic Level is required.", "#dynamicLevel") && isFieldNotEmpty(dynamicStatus, "Dynamic Status is required.", "#dynamicStatus");
	}


	function isFieldNotEmpty(field, fieldMsg, domElementId){
		if(field == ""){
			alert(fieldMsg);
			$(domElementId).focus();
			return false;
		}
		return true;
	}
	
	var isClosedJuvStatus =  $("#juvStatus").val();
	console.log('IsClosedJuvStatus - family Constellation: ', isClosedJuvStatus);
	
	if(isClosedJuvStatus === "true"){
		$("#updateActiveCons").attr('disabled', true);
		$("#confirmUpdateGuardians").attr('disabled', true);
		$("#updateMemberButton").attr('disabled', true);
		$("#createNewConstellation").attr('disabled', true);
		$("#dynamics").attr('disabled', true);
	} 
	
	if(isClosedJuvStatus === "false") {
		$("#updateActiveCons").attr('disabled', false);
		$("#confirmUpdateGuardians").attr('disabled', false);
		$("#updateMemberButton").attr('disabled', false);
		$("#createNewConstellation").attr('disabled', false);
		$("#dynamics").attr('disabled', false);
	}
	
	//disable the create constellation button if the youth already belongs to a constellation
	var youthHasConstellation = $('#juvHasConstellation').val();
	
	if(youthHasConstellation === "true")
	{
		$("#createNewConstellation").attr('disabled', true);
	}
	
});

//var isJuvStatusClosed = document.getElementById("juvStatus");
//console.log('isJuvStatusClosed : ', isJuvStatusClosed);
//if(isJuvStatusClosed){
//	document.getElementById("updateActiveCons").disabled = true;
//	document.getElementById("confirmUpdateGuardians").disabled = true;
//	document.getElementById("updateMemberButton").disabled = true;
//}

//if(!isJuvStatusClosed){
//	document.getElementById("updateActiveCons").disabled = false;
//	document.getElementById("confirmUpdateGuardians").disabled = false;
//	document.getElementById("updateMemberButton").disabled = false;
//}
