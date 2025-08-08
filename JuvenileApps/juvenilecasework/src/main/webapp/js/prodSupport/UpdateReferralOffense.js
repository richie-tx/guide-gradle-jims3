$(document)
		.ready(
				function() {
										
					if (typeof $("#offDate") != "undefined") 
					{
						datePickerSingle($("#offDate"), "Date", false);
					}
					if (typeof $("#arrestDate") != "undefined") 
					{
						datePickerSingle($("#arrestDate"), "Date", false);
					}
					
					var updatevalidate = function() 
					{						
						var juvNum = $("#juvNum").val();
						if ($("#juvNum").val() != "") 
						{
							if ($("#juvNum").val().trim().length < 6) 
							{
								alert("Invalid Juvenile Number.  Please modify entry.");
								$("#juvNum").focus();
								return false;
							}

							// juvenile Number
							if (Math.floor(juvNum) != juvNum
									|| $.isNumeric(juvNum) == false) 
							{
								alert("Invalid Juvenile Number.  Please modify entry.");
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
						var refNum = $("#refNum").val();
						if ($("#refNum").val() != "") 
						{
							if ($("#refNum").val().trim().length !=4) 
							{
								alert("Invalid Referral Number.  Please modify entry.");
								$("#refNum").focus();
								return false;
							}

							// juvenile Number
							if (Math.floor(refNum) != refNum
									|| $.isNumeric(refNum) == false) 
							{
								alert("Invalid Referral Number.  Please modify entry.");
								$("#refNum").focus();
								return false;
							}
						} 
						else 
						{
							alert("Referral Number is required.");
							$("#refNum").focus();
							return false;
						}
						if ($("#offCode").val() == "") 
						{
							alert("Offense Code is required.");
							$("#offCode").focus();
							return false;
						}						
						
						var onCampOffense = $('#onCampOffense').val();
						if(onCampOffense && onCampOffense.length > 4){
							alert("On Campus Offense must be 4 characters or less");
							$('#onCampOffense').focus();
							return false;
						}
						
						var onCampDistrict = $('#onCampDistrict').val();
						if(onCampDistrict && onCampDistrict.length > 5){
							alert("On Campus District must be 5 characters or less");
							$('#onCampDistrict').focus();
							return false;
						}
						
						var onCampSchool = $('#onCampSchool').val();
						if(onCampSchool && onCampSchool.length > 3){
							alert("On Campus School must be 3 characters or less");
							$('#onCampSchool').focus();
							return false;
						}
						
						
						if ($("#offDate").val() == "") {
							alert("Offense Date is required.");
							$("#offDate").focus();
							return false;
						}
						else
						{
							 //alert($("#hdnrefDate").val());
							var offdt = $("#offDate").val();
							var today = formatDate(new Date());
							var refoffDateFormatted = formatDate(offdt);
							var refdt = $("#hdnrefDate").val();
							var refDateFormatted = formatDate(refdt);
							if (refoffDateFormatted > today) 
							{
								alert('Offense Date cannot be in the future.');
								$("#offDate").focus();
								return false;
							}		
							
//							if (refoffDateFormatted > refDateFormatted) 
//							{
//								alert('Offense Date cannot be after referral date.  Please verify entry.');
//								$("#offDate").focus();
//								return false;
//							}	
//							var refDate = new Date($("#hdnrefDate").val());
//							var offDate = new Date($("#offDate").val());
//							
							console.log('referral date: ', $("#hdnrefDate").val());
							console.log('offense date: ', $("#offDate").val());
//							
//							if(!refDate){
//								alert('referral date is undefined or null. confirm that referral date exists. see browser console.');
//								console.log('referral date: ', refDate);
//								return false;
//							}
//							
//							if(!offDate){
//								alert('offense date is undefined or null. confirm that offense date exists. see browser console.');
//								console.log('offense date: ', offDate);
//								$("#offDate").focus();
//								return false;
//							}					
													
//							if ( offDate > refDate ) 
//							{
//								alert('Offense Date cannot be after referral date.  Please verify entry.');
//								$("#offDate").focus();
//								return false;
//							}	
							
						}
						
//						if ($("#offInvestNumber").val() != "")
//						{
//							var offInvstNum = $("#offInvestNumber").val();
//							if( /[^a-zA-Z0-9\-]/.test( offInvstNum ) )
//							{
//								alert("only numbers, letters and dashes are allowed for offense investigation.");
//								$("#offInvestNumber").focus();
//								return false;
//							}
//							
//						}
						if ($("#seqNum").val() == "") 
						{
							alert("Sequence Number is required.");
							$("#seqNum").focus();
							return false;
						}
						else
						{
							var seqNum = $("#seqNum").val();
							if (Math.floor(seqNum) != seqNum
										|| $.isNumeric(seqNum) == false) 
							{
								alert("Sequence Number must be numeric.");
								$("#seqNum").focus();
								return false;
							}
						}
						
						if ($("#chargeSeqNum").val() != "") 
						{
							var chseqNum = $("#chargeSeqNum").val();
							if (Math.floor(chseqNum) != chseqNum
										|| $.isNumeric(chseqNum) == false) 
							{
								alert("Charge Sequence Number must be numeric.");
								$("#chargeSeqNum").focus();
								return false;
							}
						}
						if(true)
							spinner();
						
					}
					function formatDate(date) {

						var newStr = '';
						if (date > '') {

							var tempDate = new Date(date).toISOString().substr(0, 10);
							newStr = tempDate.replace(/-/g, "");
						}
						return newStr;
					}
					$("#submitBtn").click(updatevalidate);				
					
					// enter key
					$(document).bind("keydown", function(event) {
						if (event.which == 13) {
							validate();
						}
					});

				});
