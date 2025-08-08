$(document).ready(function() {
					var action = $("#action").val();
					var actionError = $("#actionError").val();
					var cursorPosition = $("#cursorPosition").val();
					var currDate = new Date(); // current Date

					$("#petitionNum").focus();
					if (typeof $("#courtDate") != "undefined") {
						datePickerSingle($("#courtDate"), "Date", false);
					}					
					if (typeof $("#hrgDate") != "undefined") {
						datePickerSingle($("#hrgDate"), "Date", false);
					}
					
					var validate = function() {
						var petitionNum = $("#petitionNum").val();
						var crtDt = $("#courtDate").val();

						if (!petitionNum) {
							alert("Petition Number is required.");
							$("#petitionNum").focus();
							return false;
						}
						

//						if (!crtDt) {
//							alert("Court Date is required.");
//							$("#courtDate").focus();
//							return false;
//						}
						
						if ( true ) {
							spinner();
						}
					}

					$("#submitBtn").click(validate);					
					
					
					var selectedVal = $("form input:radio");
					$.each(selectedVal, function(index) { // loops through all
						// the radio
						// buttons.
						if (typeof radio !== 'undefined'
								&& $(this).val() !== '') { // 
							$(this).prop("checked", 'checked');
						}
					});
					
					

					$("#BtnRefresh").on("click", function() {
						// Clear fields on the form
						$("#petitionNum").val("");
						$("#courtDate").val("");
						
						//clear error message - if any
						document.getElementById("error-alert").innerHTML = "";
					});
					$("#backToQryBtn")
							.on(
									"click",
									function() {
										// Clear fields on the form
										$('form')
												.attr('action',
														'/JuvenileCasework/DeleteAncillaryCourtCalendarRecordQuery.do?clr=Y');
										$('form').submit();
										return true;

									});
					
					$("#selectRefBtn").on("click",	function() {
						
						if($('input:radio[name="docketEventId"]:checked').length == 0){
							alert('please select a radio button');
							return false;
						}
								var docketEventId = $('input:radio[name=docketEventId]:checked').val();										
								if (docketEventId != "") {
									$("#docketId").val(docketEventId); // set
									// it
									// in
									// the
									// form											
									$('form')
											.attr(
													'action',
													'/JuvenileCasework/DeleteAncillaryCourtCalendarRecordQuery.do?&docketId='+ docketEventId);
									spinner();
									$('form').submit();
									return true;
								}

						});
					
					
						$("#deleteBtn").on("click",	function(){
							
							var isOk = window.confirm('Are you sure  you want to delete the Ancillary Calendar Record?');
							
							if (isOk) {								 
										
									$('form')
											.attr(
													'action',
													'/JuvenileCasework/DeleteAncillaryCourtCalendarRecord.do');
									spinner();
									$('form').submit();
									
									return true;
								  
							} else if(!isOk) {
								//return to the search screen
								
								$('form')
								.attr('action',
										'/JuvenileCasework/DeleteAncillaryCourtCalendarRecordQuery.do?clr=Y');
								$('form').submit();
								
								return true;
							} else {
								
								return false;
							}
							
						});
						
						var confirmSubmit = function(){
							
							var answer = window.confirm('Are you sure  you want to delete the Ancillary Calendar Record?');
							if (answer) {
//								 
//								var docketEventId = $("#docketId").val();								
//								if (docketEventId) {
//										
//									$('form')
//											.attr(
//													'action',
//													'/JuvenileCasework/DeleteAncillaryCourtCalendarRecord.do?&docketId='+ docketEventId);
//									spinner();
//									$('form').submit();
//									
//									return true;
//								}
								
								alert('yay! delete');
								return true;
								  
								} else {
									
									alert('cancelled!');
									
								  // Do nothing!
								  console.log('ancillary delete cancelled.');
								  
								  return false;
								}
						}

					// enter key
					$(document).bind("keydown", function(event) {
						if (event.which == 13) {
							validate();
						}
					});

				});
