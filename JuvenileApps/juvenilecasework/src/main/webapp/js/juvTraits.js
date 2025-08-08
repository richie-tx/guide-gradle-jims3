//Jquery file used with the traits pages in juvTabTraits
$(document).ready(function(){
	
	//juvenileTraitsCreateNewSelectCasefile.jsp
	//----------------------------------------------------------
	if(document.title == "JIMS2 Juvenile Casework - juvenileTraitsCreateNewSelectCasefile.jsp"){
		var rd = $("[name='supervisionNum']");
		if (rd != null) {
			if (rd.length == 1){
				$(rd).first().prop('checked',true);
			}	
		}
	}
	$("#selectCasefileNext").on('click',function(){
		var status = validateRadios(this, 'supervisionNum', 'Casefile selection is required.')
		if ( status ) {
			spinner();
		}
		return status ;
	});
	
	//juvenileTraitsCreateNew.jsp
	//----------------------------------------------------------
	$("#createTraitFinish").on('click',function(){
		spinner();
		return disableSubmitButtonCasework($(this));
		
	});
	
	$("#btnGangsTabBack").on('click',function() {
		
		//$('form').attr('action','/JuvenileCasework/displayJuvenileGangs.do?submitAction=Tab&action=View');
		//spinner();
		//$('form').submit();
		window.history.back();
		
	});
	
});