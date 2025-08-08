//<!-- JavaScript - Emloyment Status Check -->

$(document).ready(function () {
	$("#addEmployValidate").on('click',function(){
		
	   if ($("[name='currentEmployment.employmentStatusId']").val()=="") {
	      alert("Employment Status selection is required.");
	      $("[name='currentEmployment.employmentStatusId']").focus();
	      return false;
	   }
	   if ( $("[name='currentEmployment.salary']").val() > "" &&  $("[name='currentEmployment.salaryRateId']").val() == "") {
	      alert("Salary Rate is required if Salary is entered.");
	      $("[name='currentEmployment.salaryRateId']").focus();
	      return false;
	   }
	   if (!( $("[name='currentEmployment.salaryRateId']").val() == "") && !( $("[name='currentEmployment.salary']").val() > "")) {
	      alert("Salary is required if Salary Rate is entered.");
	      $("[name='currentEmployment.salary']").focus();
	      return false;
	   }
	   if ( $("[name='currentEmployment.salaryRateId']").val()=="HR" &&  $("[name='currentEmployment.workHours']").val() <= "") {
	      alert("Number of Hours Worked (per week) is required if Salary Rate is Hourly.");
	       $("[name='currentEmployment.workHours']").focus();
	      return false;
	   }
	   
	    if ( $("[name='currentEmployment.workHours']").val()!="") {
	    	if ( $("[name='currentEmployment.salaryRateId']").val()!="HR")
	    	{
			    alert("Hours Worked per Week is required only if Salary Rate is Hourly.");
			    $("[name='currentEmployment.salaryRateId']").focus();
			    return false;
			}		
	   }
	    
	    var currentEmployer = $("#currentEmployer").val();
	    var currentEmployerFormat = /^[a-zA-Z0-9 '.,/\!+?@\x26\\\-]*$/;
	    var jobTitle = $("#jobTitle").val();
	    var jobTitleFormat = /^[A-Za-z0-9 ,]*$/;
	    var salary = $("#salary").val();
	    var salaryFormat =	/^(?:[1-9][0-9]{0,9}(?:\.\d{1,2})?|100000000|100000000.00)$/; // /^([1-9]{1}([0-9]{0,5})(\.[0-9]{2})?|0(\.[0-9]{2})?)$/;
	    var workHours = $("#workHours").val();
	    var workHoursFormat = /^[0-9]*$/;
	    var lengthOfEmployment = $("#lengthOfEmployment").val();
	    var lengthOfEmploymentFormat = /^[A-Za-z0-9 ,]*$/;
	    console.log(currentEmployerFormat.test(currentEmployer));
	    if ( !currentEmployerFormat.test(currentEmployer) ){
	    	alert("Employer must be alphanumeric.");
	    	return false;
	    }
	    
	    if ( !jobTitleFormat.test(jobTitle) ) {
	    	alert("Job Title must be alphanumeric.");
	    	return false;
	    }
	    
	    if ( !salaryFormat.test(salary) && salary.length > 0  ){
	    	alert("Salary is not a valid. Please note no commas or dollar signs are allowed.  Example: for $1,000 enter 1000.");
	    	return false;
	    }
	    
	    if ( !workHoursFormat.test(workHours) ){
	    	alert("Number of Hours Worked must be numeric.");
	    	return false;
	    	
	    }
	    
	    if( !lengthOfEmploymentFormat.test(lengthOfEmployment) ){
	    	alert("Length of Employment must be alphanumeric.");
	    	return false;
	    }
	   //var theForm = document.juvenileMemberForm;
	   //return validateJuvenileMemberFormEmploy(theForm);
	    if ( true ) {
	    	spinner();
	    }
	    return true;
	 });
	
	
});


