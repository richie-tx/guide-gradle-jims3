$(document).ready(function () {	
	
	if($("#action").val()=="DOBSearch"){
		$("#juvenileName").hide();
		$("#searchType").val("JDOB");
		$("#juvDOB").show();
	}
	var myForm = document.forms[0];
	
	//Date picker for DOB field for Name search
	if(typeof $("#dateOfBirth") != "undefined"){
		$("#dateOfBirth").on("mousedown",function(){
			datePickerSingle($("#dateOfBirth"),"Date of Birth",true);
		}); 
	}
	
	//Date picker for DOB field for Date search
	if(typeof $("#jDOB") != "undefined"){
		$("#jDOB").on("mousedown",function(){
			datePickerSingle($("#jDOB"),"Date of Birth",true);
		}); 
	}
	
	//on change of search type
	$("#searchType").on("change",function(){
		evalSearch(myForm)
	}); //on change of search type.
	
	function evalSearch(myForm){
		var searchType = $("#searchType").val();
				
		if(searchType=="JNAM"){
			$("#juvenileName").show();
			$("#juvDOB").hide();
			$("#lastName").focus();
			$("#searchResults").hide();
	   		clearJuvDOBFields(); 
	   		
		} else if (searchType=="JDOB")
		{
			$("#juvDOB").show();
			$("#juvenileName").hide();
	    	//$("#jDOB").focus(); //commented due to date picker issues
    		$("#searchResults").hide();
	    	clearJuvNameFields();
		}
	}
	
	$("#submitBtn").click(function () {

		var searchType = $("#searchType").val();
		if(typeof searchType !="undefined"){
			var searchType = $("#searchType").val();
			   if(searchType=="JNAM"){
				   var lastName = $("#lastName").val();
				   if(typeof lastName!="undefined"){
					   if( $("#lastName").val()==""){
						  alert("Juvenile Last Name is required for the entered search criteria.");
						   $("#lastName").focus();
						   return false;
					   }else if(lastName.length<2)
						   {
						   alert(" Juvenile Last Name Cannot Be Less Than 2 Characters.");
						   $("#lastName").focus();
						   return false;
						   }
				   }
			   }
			   else if(searchType=="JDOB")
			   {
				   var dateOfBirth = $("#dateOfBirth").val();
				   if(typeof dateOfBirth!="undefined"){
					   if($("#jDOB").val() ==""){
						   alert("Juvenile Date of Birth is required.");
						   $("#jDOB").focus();
						   return false;	
					   }
				   }
			   }
			}
		
		if ( true ) {
			spinner();
		}
	});

	/**
	 * clearJuvNameFields
	 */
	function clearJuvNameFields()
	{
	  $("#lastName").val("");
	  $("#firstName").val("");
	  $("#middleName").val("");
	  $("#raceId").val("");
	  $("#sexId").val("");
	  $("#dateOfBirth").val("");
	  
	}
	
	
	/**
	 * clearJuvDOBFields
	 */
	function clearJuvDOBFields()
	{
 	   if(typeof $("#jDOB")!="undefined"){
		 $("#jDOB").val("");
		}
	}
});
		
