//functionality used by the interview tab

$(document).ready(function () {
	
	$("#attorneyAppt").click(function(){
		
		changeFormActionURL("/JuvenileCasework/handleAttorneyOptions.do?submitAction=Request Attorney Appt",false); 
		disableSubmitButtonCasework($(this));
		spinner();
	});
	
	$("#printParentalStatement").click(function(){
		changeFormActionURL("/JuvenileCasework/handleJuvInterviewSelection.do?submitAction=Print Parental Statement",false); 
		disableSubmitButtonCasework($(this));
		spinner();
	});
	
	$("#viewSocialHistoryData").click(function(){
		spinner();
		//sessionStorage.setItem("isReportGenerated", "false");		
		changeFormActionURL("/JuvenileCasework/handleSocialHistoryData.do?submitAction=View Social History Data", false);
		disableSubmitButtonCasework($(this));		
	});
	
	$("#parentalRights").click(function(){
		changeFormActionURL("/JuvenileCasework/displayParentalRights.do?submitAction=Parental Rights",false);
		disableSubmitButtonCasework($(this));
		spinner();
	});	

	
	
	$("#completeSelectedTasksId").click(function(){		
		disableSubmitButtonCasework($(this));
		spinner();
		return false;		
	});
});