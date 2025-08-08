//used with Gang Referral, JQuery

$(document).ready(function(){
	
	//General casework.js functionality	
	$("#updateBtn,#gangFinish,#gangInfoFinish").on('click',function(){
		return disableSubmitButtonCasework($(this));
		
	});
	
	if(typeof $("#referralDate")!= "undefined"){
		datePickerSingle( $("#referralDate"),"Referral Date",false);
	}
	
	if(typeof $("#entryDte")!= "undefined"){
		datePickerSingle( $("#entryDte"),"Entry Date",false);
	}
	
	$("#descHybridId,#otherReasonForReferralTxt,#rejecReasonId,#descHybrid,[name='aliasMoniker'],[name='rank']").on('keyup mouseout',function(){
		textLimit($(this),255);
		return false;
	});
	
	$("#comments,#conclusionId").on('keyup mouseout',function(){
		textLimit($(this),3500);
		return false;
	});
	
	$("#gangRefListBack,#gangCreateBack,#infoGangBack").on('click',function(){
		goNav('back');
	});
	
	//gangAssessmentReferralList.jsp
	//------------------------------------------------
	
	if(document.title == "JIMS2&nbsp;Juvenile Casework&nbsp;- gangAssessmentReferralList.jsp")
	{
		enableUpdateAssessment();
	}

	$("[name='updateAssessment']").on('click',function(){
		enableUpdateAssessment();
	});
	
	function enableUpdateAssessment()
	{
		if($("[name='updateAssessment']").is(':checked'))
		{
			$("#updateBtn").prop('disabled',false);
		}
	}
	
	//gangAssessmentReferralListCreate.jsp
	//------------------------------------------------
	
	//formerly function refreshPage(theForm) //on click of refresh button 
	$("[name='reset']").on('click',function(){
		var fldx = $("[name='conclusion']")[0];
		var acceptedStatus = $("#acceptedStatusId");
		var rejectedStatus = $("#rejectedStatusId");
		var recommendation = $("[name='recommendationsId']")[0];
		var rejectionReason = $("#rejectionReasonIdTxt");
		if(fldx!=null)
		{
			$(fldx).val("");
			$(rejectionReason).val("");
			$(acceptedStatus).prop("checked",false);
			$(rejectedStatus).prop("checked",false);
			$(recommendation).val("");
			if(rejectionReason!=null){
				$("#rejecReasonId").val("");
				$("#rejectionReasonId").hide();
				$("#rejectionReasonIdTxt").hide();
			}
		}
		$("[name='referralDate']").val(formatDate(new Date(),'MM/dd/yyyy'));
		$("[name='placementFacilityId']").val("");
		$("[name='gangNameId']").val("");
		$("[name='cliqueSetId']").val("");
		$("#othrGangName").val("");
		$("#otherGangTxt").hide();
		$("#descHybridId").val("");
		$("#descHybrid").hide();
		$("#othrCliqueName").val("");
		$("#otherCliqueTxt").hide();
		
		
		$("[name='selectedReasonForReferrals']").prop("checked",false);
		$("[name='lvlOfGangInvolvementId']").prop("checked",false);
		$("[name='otherReasonForReferral']").prop('checked',false);
		$("[name='otherReasonForReferralTxt']").val("");
		$("#otherReasonForReferralId").hide();
		
		$("[name='comments']").val("");
		
		$("#parentNotifiedGangAssReqYes").prop("checked",false);
		$("#parentNotifiedGangAssReqNo").prop("checked",false);
		$("#parentNotifiedGangAssReqId").val("");
		
		$("#parentNotifiedYes").prop("checked",false);
		$("#parentNotifiedNo").prop("checked",false);
		$("#parentNotifiedId").val("");
		
	});
	$("input[id^='parentNotified']").on('change', function (e) {	
		if(this.checked)
		{
			$("#parentNotifiedId").val("Yes");
		} 
		else 
		{
			$("#parentNotifiedId").val("No");
		}		
	});
	
	$("input[id^='parentNotifiedGangAssReq']").on('change', function (e) {	
		if(this.checked)
		{
			$("#parentNotifiedGangAssReqId").val("Yes");
		} 
		else 
		{
			$("#parentNotifiedGangAssReqId").val("No");
		}		
	});
	
	$("#gangListCreateNext").on('click',function(){
		var msg="";
		var fldx = "";
		var inputDate=""
		var today = new Date();
		var attDate = $("[name='referralDate']");
		if(attDate!=null)
		{
			inputDate = new Date($(attDate).val());
		}
		if(inputDate!=null && inputDate > today)
		{
			if (msg == "") {
				attDate.focus()
			}	
			msg += "Referral Date cannot be a future date.\n";
		}
		
		var fld1 = $("#gangSelId");
		var fld2 = $("#cliqueSelId");
		if(fld1!=null)
		{
			if ($(fld1).val() == "")
			{
				msg += "Gang Name selection is required.\n";
				fld1.focus();
			}
			if ($(fld1).val() > "")
			{	
				if ($(fld1).val() == "OTHR")
			  	{
					fldx = $("#othrGangName");
					if (fldx!=null && $(fldx).val() == "")
					{
						if (msg == "")  {
							fldx.focus();
						}
				  		msg += "Other Gang Name required for Gang Name 'Other' selection.\n";				
					} 
			  	} 
		  	}
			if ($(fld1).val() > "")
			{	
				if ($(fld1).val() == "HYB")
			  	{
					fldx = $("#descHybridId");
					if (fldx!=null && $(fldx).val() == "")
					{
						if (msg == "")  
						{
							fldx.focus();
						}
				  		msg += "Describe Hybrid required for Gang Name 'Hybrid' selection.\n";				
					} 
					else 
					{
						$("#descHybridTxt").show();
					}
			  	}
		  	}
			/*if ($(fld2).val() > "")
			{
				if($(fld2).val() == "OTHR")
				{
					fldx = $("#othrCliqueName");
					if (fldx!=null && $(fldx).val() == "")
					{
						if (msg == "")
						{
							fldx.focus();
						}
						msg += "Other Clique/Set required for Gang Name 'Other' selection.\n";
					}
				}
			}*/
		 }	
		fld1 = $("[name='selectedReasonForReferrals']");
		var selectedCount=0;
		if($(fld1).length)
		{
			for (var i = 0; i < fld1.length; i++) 
			{ 
				if (fld1[i].checked == "")
				{
					selectedCount++;
				} 
			}
		}
		
		var fld2 = $("[name='otherReasonForReferral']");	
		if($(fld2).length && $(fld2).prop('checked') == true)
	  	{
			var otherTxt=$("[name='otherReasonForReferralTxt']");
			if (otherTxt!=null && $(otherTxt).val()== "")
			{
				if (msg == "")  
				{
					otherTxt[0].focus();
				}
		  		msg += "Other Reason for Referral required for 'Other' selection.\n";				
			} 
	  	} 
		if($(fld1).length && $(fld2).length)
		{
			if(selectedCount==fld1.length && $(fld2).prop('checked')!=true )
			{ 
				msg += "Reason For Referral selection is required.\n";		
			}	
		}
		
		fldx = $("[name='lvlOfGangInvolvementId']");
		if(fldx!=null && selectedCount!=0)
		{
			selectedCount=0;
			for (var i = 0; i < fldx.length; i++) 
			{ 
				if (fldx[i].checked == "")
				{
					selectedCount++;
				} 
			}
			
			if (selectedCount==fldx.length)
			{
		  		msg += "Level of Gang Involvement selection is required.\n";				
			}	
		}
		
		//=========== parent notified gang assessment ====================
		var gangAssessmentAction = $("#gangAssessmentAction").val();
		console.log("gangAssessmentAction: " + gangAssessmentAction);
		
		if(gangAssessmentAction && gangAssessmentAction == "create"){
			
			var parentNotifiedGangAssReqYes = null;
			if ($("#parentNotifiedGangAssReqYes").prop("checked")) {
				parentNotifiedGangAssReqYes = true;
			}
			
			var parentNotifiedGangAssReqNo = null;
			if ($("#parentNotifiedGangAssReqNo").prop("checked")) {
				parentNotifiedGangAssReqNo = true;
			}
			
			if(!parentNotifiedGangAssReqYes && !parentNotifiedGangAssReqNo){
				alert('parent(s) was notified that a gang assessment was requested for the youth requires a selection');
				return false;
			}
			
		}
			
		//Update Assessment validation starts.
		
		//=========== parent notified gang assessment ====================
		if(gangAssessmentAction && gangAssessmentAction == "update"){
			
			var parentNotifiedYes = null;
			if ($("#parentNotifiedYes").prop("checked")) {
				parentNotifiedYes = true;
			}
			
			var parentNotifiedNo = null;
			if ($("#parentNotifiedNo").prop("checked")) {
				parentNotifiedNo = true;
			}
			
			if(!parentNotifiedYes && !parentNotifiedNo){
				alert('parent(s) was notified that a gang assessment will be completed with the youth requires a selection');
				return false;
			}
		}
		
		//end gang assessment
		fldx = $("#acceptedStatusId");
		fld1= $("#rejectedStatusId");
		var fldu= $("#unableStatusId");
		if (fldx!=null)
		{
			if(($(fldx).prop('checked')=="") && ($(fld1).prop('checked')=="")&& ($(fldu).prop('checked')=="")){
				msg += "Status is required.\n";
			}else {
				if($(fld1).prop('checked')){
					var fldx = $("[name='rejectionReason']");
					if (fldx!=null && $(fldx).val() == "")
					{
						if (msg == "")  
						{
							fldx.focus();
						}
						msg += "If Status rejected , Please explain is required.\n";				
					}	
				}
			}
		} 
		
		var fld2 = $("#recommendationId");
	 	if(fld2!=null)
	 	{
			if ($(fld2).val() == "")
		  	{
		  		msg += "Recommendations selection is required.\n";
		  		fld2.focus();
		  	}
	 	}
	 	fldx = $("[name='conclusion']");
		if (fldx!=null && $(fldx).val() == "")
		{
			if (msg == "")  
			{
				fldx.focus();
			}
			msg += "Conclusion is required.";				
		} 
		if (msg == "")
	  	{	
			return true;
	  	}
		alert(msg);
		return false;
	});
	
	//formerly function checkGangOther(el)
	$("#gangSelId").on('change',function(){
		var showOther = 0;
		var otherGangName=$("#othrGangName");
		var descHybrid =$("#descHybridId");
		
		if ($(this).val() == "OTHR")
		{
			showOther = 1;
		}else{
			if(otherGangName!=null){
				$(otherGangName).val("");
			}
		}
		if(showOther == 0){$("#otherGangTxt").hide();}
		else {$("#otherGangTxt").show();}
		
		//To show describe hybrid on selection of hybrid.
		var descHybrid = 0;
		if ($(this).val() == "HYB")
		{
			descHybrid = 1;
		}else{
			if(descHybrid!=null){
				$(descHybrid).val("");
			}
		}
		if(descHybrid == 0){$("#descHybrid").hide();}
		else {$("#descHybrid").show();}
	});
	
	//function checkCliqueOther(el) 
	$("#cliqueSelId").on('change',function(){
		var showOther = 0;
		var otherCliqueName= $("#othrCliqueName");
		if ($(this).val() == "OTHR")
		{
			showOther = 1;
		}else{
			if(otherCliqueName!=null){
			$(otherCliqueName).val("");
			}
		}
		if(showOther == 0){$("#otherCliqueTxt").hide();}
		else {$("#otherCliqueTxt").show();}
	});
	
	//used with function checkReasonForReferralOther(el) 
	$("#othrReasForRefId").on('click',function(){
		checkReasonForReferralOther($(this)); 
	});
	
	function checkReasonForReferralOther(el) 
	{
		var otherReasonForRef= $("[name='otherReasonForReferral']");
		var otherReasonForRefTxt = $("[name='otherReasonForReferralTxt']");
		if(el.prop('checked') == true)
		{
			$("#otherReasonForReferralId").show();
		}
		else
		{
			if(otherReasonForRef!=null && otherReasonForRefTxt!=null){
				$(otherReasonForRef).prop('checked',false);
				$(otherReasonForRefTxt).val("");
			}
			$("#otherReasonForReferralId").hide();
		}
	}
	
	//used with function checkedRejectionReason
	$("[name='acceptedStatus']").on('click',function(){
		var el = ($(this).val() == "Rejected") ? "N":"Y";
		checkRejectionReason(el);
	});
	
	function checkRejectionReason(el){
		var rejectionReason = $("#rejecReasonId");
		if (el == "N")
		{
			$("#rejectionReasonId").show();
			$("#rejectionReasonIdTxt").show();
		}
		else
		{
			if(rejectionReason!=null){
				$(rejectionReason).val("");
			}
			$("#rejectionReasonId").hide();
			$("#rejectionReasonIdTxt").hide();
		}
	}
	
	//formerly function enableControls() //called on load so using if to call
	if(document.title == "JIMS2 Juvenile Casework - gangAssessmentReferralListCreate.jsp"){
		var other = $('#othrReasForRefId');
		var otherReasonForRef= $("[name='otherReasonForReferral']");
		var otherReasonForRefTxt = $("[name='otherReasonForReferralTxt']");
		if(other!=null && $(other).prop('checked')){
			checkReasonForReferralOther(other);
		}else{
			if(otherReasonForRef!=null && otherReasonForRefTxt!=null){
				$(otherReasonForRef).prop('checked',false);
				$(otherReasonForRefTxt).val("");
			}
		}
		var gangSel = $('#gangSelId');
		
		if($(gangSel).length){
			$(gangSel).trigger('change');
		}
		
		/*var cliqueSel = $('#cliqueSelId');
		if($(cliqueSel).length){
			$(cliqueSel).trigger('change');
		}*/

		var rejectionRadio = $("#rejectedStatusId");
		if(rejectionRadio!=null && $(rejectionRadio).prop('checked')){
			checkRejectionReason('N');
		}
	}
	
	//interviewInfoGangCreate.jsp
	//------------------------------------------------
	
	//formerly function checkGangOther(el) 
	$("#gangSelId").on('change',function(){
		var showOther = 0;
		if ($(this).val() == "OTHR")
		{
			showOther = 1;
		}
		if(showOther == 0){$("#otherGangTxt").hide();}
		else {$("#otherGangTxt").show();}
		
		//To show describe hybrid on selection of hybrid.
		var descHybrid = 0;
		if ($(this).val() == "HYB")
		{
			descHybrid = 1;
		}
		if(descHybrid == 0){
			$("#descHybridTxtAreaLbl").hide();
			$("#descHybridTxtArea").hide();
		}
		else {
			$("#descHybridTxtAreaLbl").show();
			$("#descHybridTxtArea").show();
		}
	});
	
	//formerly function checkCliqueOther(el) 
	/*$("#cliqueSelId").on('change',function(){
		var showOther = 0;
		if ($(this).val() == "OTHR")
		{
			showOther = 1;
		}
		if(showOther == 0){$("#otherCliqueTxt").hide();}
		else {$("#otherCliqueTxt").show();}
	});
	*/
	//formerly function validateFields(theForm)
	$("#gangAddValidate").on('click',function(){
	  	var msg = "";
	  	var fld1 = $("#gangSelId");
	  	var fld2 = $("#cliqueSelId");
	  	var fldx = "";
	  	if ($(fld1).val() == "")
	  	{
	  		msg = "Gang Name is required.\n";
	  		fld1.focus();
	  	}
	  	
		if ($(fld1).val() > "")
		{	
			if ($(fld1).val() == "OTHR")
		  	{
				fldx = $("#otherGangNameId");
				if ($(fldx).val() == "")
				{
					if (msg == "")  {
						fldx.focus();
					}
			  		msg += "Other Gang Name required for Gang Name 'Other' selection.\n";				
				} 
		  	} 
			if ($(fld1).val() == "HYB")
		  	{
				fldx = $("#descHybrid");
				if ($(fldx).val() == "")
				{
					if (msg == "")  {
						fldx.focus();
					}
			  		msg += "Describe Hybrid required for Gang Name 'Hybrid' selection.\n";				
				} else {
					$("#descHybridTxt").show();
				}
		  	}
	  	}
		
		/*if ($(fld2).val() > "")
		{	
			if($(fld2).val() == "OTHR")
		  	{
				fldx = $("#otherCliqueSetId");
				if ($(fldx).val() == "")
				{
					if (msg == "")  {
						fldx.focus();
					}
			  		msg += "Other Clique/Set required for Gang Name 'Other' selection.\n";				
				} 
		  	} 
		}	*/
		fldx = $("#currentStatusId");
		if ($(fldx).val() == "")
		{
			if (msg == "")  {
				fldx.focus();
			}
	  		msg += "Status selection is required.\n";				
		} 
		
		fldx = $("#assocTypeSel");
		if ($(fldx).val() == "")
		{
			if (msg == "")  {
				fldx.focus();
			}
	  		msg += "Association Type selection is required.\n";				
		} 
	  	if (msg == "")
	  	{	
			return true;
	  	}
	  	alert(msg);
	  	return false;
	});
	
	//formerly function enableControls()
	if(document.title=="JIMS2 - interviewInfoGangCreate.jsp"){
		if($("#entryDte").val() == "")
		{
			var mydate = new Date();	
			var year = mydate.getFullYear();
			var month = mydate.getMonth()+1;
			if(month < 10)
			{
				month = "0" + month;
			}
			var daym = mydate.getDate();
			if (daym < 10)
			{
				 daym = "0" + daym ;
			}	 
			var currentDateStr = month +"/" +daym +"/" +year ;
			$("#entryDte").val(currentDateStr);
		}
		var fld1 = $("#gangSelId");
	  	var fld2 = $("#cliqueSelId");
	  	if ($(fld1).val() == "OTHR" || $(fld1).val() == "HYB" )
	  	{
	  		 $(fld1).trigger('change');
	  	}
	  	/*if ($(fld2).val() == "OTHR")
	  	{
	  		$(fld2).trigger('change');
	  	}*/
	}
});