$(document)
		.ready(
				function() {
					var action = $("#action").val();
					var actionError = $("#actionError").val();
					var cursorPosition = $("#cursorPosition").val();
					var currDate = new Date(); // current Date

					$("#juvNum").focus();
					if (typeof $("#courtDate") != "undefined") {
						datePickerSingle($("#courtDate"), "Date", false);
					}
					
					var validate = function() {
						var juvNum = $("#juvNum").val();
						//var crt = $("#courtNum").val();
						var crtDt = $("#courtDate").val();						
						if (juvNum != "") 
						{
							if (juvNum.trim().length < 6) {
								alert("Invalid Juvenile Number.");
								$("#juvNum").focus();
								return false;
							}

							// juvenile Number
							if (Math.floor(juvNum) != juvNum
									|| $.isNumeric(juvNum) == false) {
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
						/*$('form').attr(
								'action',
								'/JuvenileCasework/UpdateDistrictCourtCalendarRecordQuery.do?&juvenileNum='
										+ juvNum + '&courtDt=' + crtDt
										+ '&courtId=' + crt);
						$('form').submit();*/
						return true;
					}

					$("#submitBtn").click(function(){
					if ( validate() ) {
							spinner();
						}
					else
						return false;
					});
					
					$("#deleteBtn").click(function(){
						var isOk = window.confirm('Are you sure you want to delete the District Court Calendar?');
						if (isOk) {
							spinner();	
						}
						else
							return false;
					});
					
					
					var selectedVal = $("form input:radio");
					$.each(selectedVal, function(index) { // loops through all
						// the radio
						// buttons.
						if (typeof radio !== 'undefined'
								&& $(this).val() !== '') { // 
							$(this).prop("checked", 'checked');
						}
					});				
					
					
					$("#selectRefBtn")
							.on(
									"click",
									function() {
										var docketEventId = $(
												'input:radio[name=docketEventId]:checked')
												.val();										
										if (docketEventId != "") {
											$("#tempNum").val(docketEventId); // set
											// it
											// in
											// the
											// form
											$('form')
													.attr(
															'action',
															'/JuvenileCasework/DeleteDistrictCourtCalendarRecordQuery.do?&docketId='+ docketEventId);
											spinner();
											$('form').submit();
											return true;
										}

									});

					$("#BtnRefresh").on("click", function() {
						// Clear fields on the form
						$("#juvNum").val("");
						$("#reffNum").val("");
						$("#courtDate").val("");
					});
					$("#backToQryBtn")
							.on(
									"click",
									function() {
										// Clear fields on the form
										$('form')
												.attr('action',
														'/JuvenileCasework/DeleteDistrictCourtCalendarRecordQuery.do?clr=Y');
										$('form').submit();
										return true;

									});

					// enter key
					$(document).bind("keydown", function(event) {
						if (event.which == 13) {
							validate();
						}
					});

				});
