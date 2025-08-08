$(document).ready(function () {	
	
	var action = $("#action").val();
	var actionError = $("#actionError").val();
	var cursorPosition = $("#cursorPosition").val();
	var currDate= new Date(); //current Date	
	
	$("#juvNum").focus();
	var validate = function(){
		
			
   	  var juvNum = $("#juvNum").val();
   	  if($("#juvNum").val()!="" ){
	  	if($("#juvNum").val().trim().length<6){
			alert("Invalid Juvenile Number.");
			$("#juvNum").focus();
			return false;
		}
		
		//juvenile Number
		if(Math.floor(juvNum) != juvNum || $.isNumeric(juvNum)==false) {
			alert("Invalid Juvenile Number.");
			$("#juvNum").focus();
			return false;
		}
   	  }
   	  else
   	  {
   		alert("Juvenile Number is required.");
   		$("#juvNum").focus();
   		return false;
   	  }
   	
   	  if ( true ){
   		  spinner();
   	  }
	  	//if no errors, submit the petition changes.
   	   $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtJuvenileSetting.do?submitAction=Submit');
   	   $('form').submit();
	}
    
  $("#submitBtn").click(validate);
    
//enter key		 
  $(document).bind("keydown", function(event) {
      if (event.which == 13){
      	validate();
      }
  });
 
});