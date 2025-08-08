/**
 * Javascript for IE11 changes.
 */

//JQuery on Dom ONLOAD
$(document).ready(function() {
	
	//onchange of search Type
	$('#searchTypeId').change(function(){
		var searchType = $('#searchTypeId').val();
		toggleFields(searchType);
	});
	
	//onchange of search Type
	$(document).on('click mouseover', '#from',  function(){
		var x = $('#from');
		var y = $('#to');
		datePickerRange(x,y);
	});
	
	//hide or show element based on the searchType selection.
	function toggleFields(searchType){
		if(typeof searchType != "undefined"){
			//on selection of warrant type show warrant number and hide rest.
			if(searchType=='warrantNumber'){
				$('#warrantNumber').show();
				$('#juvName').hide();
				$('#warrantTypes').hide();
				$('#adultName').hide();
				$('#warrantOriginatorId').hide();
				$('#officerId').hide();
				$("#warrantStatus").hide();
			 	$("#warrantStatusReqd").hide();
			 	$("#warrantStatusNotReqd").hide();
				$("input#warrantNum").focus(); //Focus on field
			}
			
			if(searchType=='juvenileName'){
				$('#juvName').show();
				$('#warrantNumber').hide();
				$('#warrantTypes').hide();
				$('#adultName').hide();
				$('#warrantOriginatorId').hide();
				$('#officerId').hide();
				$('#adultName').hide();
				$("#warrantStatus").hide();
			 	$("#warrantStatusReqd").hide();
			 	$("#warrantStatusNotReqd").hide();
				$("input#lastName").focus(); //Focus on field
			}
			
			if(searchType=='warrantType'){
				$('#warrantTypes').show();
				$('#warrantNumber').hide();
				$('#juvName').hide();
				$('#adultName').hide();
				$('#warrantOriginatorId').hide();
				$('#officerId').hide();
				$('#adultName').hide();
				$('#warrantTypeCd').val(''); //Focus on field
				$('#from').val(''); //Clear Date
				$('#to').val(''); //Clear Date
			}
			if(typeof $('#warrantTypeCd').val()!= "undefined"){
				$("#warrantTypeCd").on('change',function(){
					//alert($('#warrantTypeCd').val());
					if ($('#warrantTypeCd').val() == 'OIC'){
						  $("#warrantStatus").show();
					 	  $("#warrantStatusReqd").show();
					 	  $("#warrantStatusNotReqd").hide();
				    }
					else
					{
					   $("#warrantStatus").show();
					   $("#warrantStatusNotReqd").show();
					   $("#warrantStatusReqd").hide();	
					}
				});
			}
			
			if(searchType=='officerId'){
				$('#officerId').show();
				$('#warrantTypes').hide();
				$('#warrantNumber').hide();
				$('#juvName').hide();
				$('#adultName').hide();
				$('#warrantOriginatorId').hide();
				$("#warrantStatus").hide();
			 	$("#warrantStatusReqd").hide();
			 	$("#warrantStatusNotReqd").hide();
			}
			
			if(searchType=='adultName'){
				$('#adultName').show();
				$('#officerId').hide();
				$('#warrantTypes').hide();
				$('#warrantNumber').hide();
				$('#juvName').hide();
				$('#warrantOriginatorId').hide();
				$("#warrantStatus").hide();
			 	$("#warrantStatusReqd").hide();
			 	$("#warrantStatusNotReqd").hide();
			}
			
			if(searchType=='warrantOriginatorId'){
				$('#warrantOriginatorId').show();
				$('#adultName').hide();
				$('#officerId').hide();
				$('#warrantTypes').hide();
				$('#warrantNumber').hide();
				$('#juvName').hide();
				$("#warrantStatus").hide();
			 	$("#warrantStatusReqd").hide();
			 	$("#warrantStatusNotReqd").hide();
			}
		}
	}
	
	function ValidateDate(dtValue)
	{
		var dtRegex = new RegExp(/\b\d{1,2}[\/]\d{1,2}[\/]\d{4}\b/);
		return dtRegex.test(dtValue);
	}
 

	//From and To should be JQuery object.
	function datePickerRange( from,to ) 
	{
	      from.datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        to.datepicker( "option", "minDate", selectedDate );
	      }
	    });

	    to.datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        from.datepicker( "option", "maxDate", selectedDate );
	      }
	    });
	}

	//validation for required fields: on click of next
    $("#submit").click(function () {
    	var searchBy =$("#searchTypeId").val();
    	var msg = true;
    	
    	if (searchBy == "warrantNumber")
    	{
    		var warrantNum = $("input#warrantNum").val();    		
    		   if ( warrantNum !== "" && $.isNumeric( warrantNum )) {
    		   
    		}else{
    				alert("Warrant number is non numeric.");
    				document.getElementById("warrantNum").value = "";    			   			
    				$("input#warrantNum").focus(); //Focus on field    				
     		   		msg = false;
    		}
    	}
    	
    	/* begin name searches edits */
    	if (searchBy == "juvenileName")
    	{
    		var lastName = $("input#lastName").val();
    		var firstName = $("input#firstName").val();
    		if ( firstName == "" && lastName == "" ) 
        	    //&& theForm.warrantTypeId.selectedIndex == 0)
        	{
            	alert("At least 1 search field is is required for search.");
             	msg = false;    	      	   
        	}   
    		if (firstName != "" && lastName == "")
    		{
    			alert("Juvenile Last Name is required for search when First Name is entered.");
    			$("input#lastName").focus();
        		msg = false;
    	    }
    	    if(lastName != "")
    	    {
    	    	customValRequired("juvenileName.lastName", "Last Name is required for search.");
    			customValMask("juvenileName.lastName", "Juvenile Last Name must be at least 2 valid characters or 2 valid characters before an *.","/^([a-zA-Z']{2})([a-zA-Z '.-]*)([*]?)([a-zA-Z '.-]*)$/");
    			msg = validateCustomStrutsBasedJS(theForm);
    	  	}
    	}	
    	
    	if (searchBy == "warrantType")
    	{
    		var iSelects = $("#warrantTypeCd").val();
    		var iWarrantStatus = $("#warrantStatusCd").val();
    		if ( iSelects == "" )
    		{
    			alert("A Warrant Type option needs to be selected.");
    			$('#warrantTypeCd').val(''); //Focus on field
    			msg = false;
    		}
    		if ( iSelects == "OIC" ) 
    		{
    			
    			var warrantStatus = $('#warrantStatusCd').val();
    			if(typeof  warrantStatus!= "undefined"){
    				if(warrantStatus==""){
    					alert("A Warrant Status option needs to be selected for Order of Immediate Custody warrants.");
    					$('#warrantStatusCd').focus();
    					msg = false;
    				}
    			}
    		}
    	
    		
    		var fromDate = $('#from').val();
    		if( fromDate != ""){
    			
    			var validDate1 = new Date( fromDate );
        		if( validDate1 == 'Invalid Date'){
        			alert('Please enter a valid from date.');
        			msg=false;
        		}
    		}
    		
    		/*
    		 * 
    		 if(!ValidateDate(fromDate))
	    	 {
	    	   alert("Please enter date in a valid format - mm/dd/yyyy")
	    	   msg= false;
	    	 }*/
    	}
    	
    	if ( msg ){
			spinner();
		}
    	
    	return msg;
    	
    	
    });
    
    function spinner(){
    	$('body').append( '<div class="overlay">'
    			+ '<div class="overlay-background"></div>'
    			+ '<div class="se-pre-con"></div>' 
    			+ '</div' );
    	$(".overlay").css("display", "block");
    }
    
 }); // jquery ends here
 