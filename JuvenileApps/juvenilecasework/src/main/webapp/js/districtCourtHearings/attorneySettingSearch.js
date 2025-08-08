$(document)
		.ready(
				function() {

					var action = $("#action").val();
					var actionError = $("#actionError").val();
					var cursorPosition = $("#cursorPosition").val();
					var currDate = new Date(); // current Date
					var backDate = new Date("01/01/1960");
					var fmtBackDate = (backDate.getMonth() + 1) + '/'
							+ backDate.getDate() + '/' + backDate.getFullYear();
					var fmtBackDateStr = fmtBackDate;
					document.getElementById('assignedAttorney1').checked=false;
					document.getElementById('assignedAttorney2').checked=false;
					$("#assignedAttorneyId").val("0");
					$("#barNumber").focus();
					// holiday check calendar.
					var countyHolidayList = $("#holidaysList").val();
					if (typeof $("#courtDate").val() != "undefined") {
						datePickerSingle($("#courtDate"), "Hearing Date", false);
					}

					if (action == "validateBarNumber"
							|| action == "validateBarNumber") {
						$("#petitionTbl").find("input,select").attr("disabled",
								"disabled");
						$("#subpoenaTbl").find("input,select").attr("disabled",
								"disabled");
						$("#barNumber").focus();
					}

					$("#validateBarNumber")
							.on(
									'click',
									function(e) {
										if ($("#barNumber").val().trim() == "") {
											alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
											$("#barNumber").focus();
											return false;
										}

										if ($("#barNumber").val() != ""
												&& $.isNumeric($("#barNumber")
														.val()) == false) {
											alert("Bar Number Is Numeric.Please Correct And Retry.");
											$("#barNumber").focus();
											return false;
										}

										if (true) {
											spinner();
										}
										$('form')
												.attr(
														'action',
														'/JuvenileCasework/submitJuvenileDistrictCourtAttorneySetting.do?submitAction=Validate Bar Number');
										$('form').submit();
									});

					var validate = function() {
						if ($("#barNumber").val() == ""
								&& $("#juvNum").val() == ""
								&& $("#attorneyName").val() == "") {
							alert("Bar Number And/Or Attorney Name ''Or'' Juvenile Number Is Required.");
							$("#barNumber").focus();
							return false;
						}
						if($("#assignedAttorneyId").val()=="1" ||  $("#assignedAttorneyId").val()=="2" )
						{
							if($("#barNumber").val() == "")
							{
								alert("Bar Number And/Or Attorney Name Is Required.");
								return false;
							}
							
						}
						if ($("#barNumber").val() != ""
								&& $.isNumeric($("#barNumber").val()) == false) {
							alert("Bar Number Is Numeric.Please Correct And Retry.");
							$("#barNumber").focus();
							return false;
						}

						var juvNum = $("#juvNum").val();
						if ($("#juvNum").val() != "") {
							if ($("#juvNum").val().trim().length < 6) {
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
						var courtDt = $("#courtDate").val() + " 23:59";
						var courtDtTime = new Date(courtDt);
						if (courtDtTime < currDate) {
							alert("Court Date cannot be in the past.");
							courtDt.focus();
							return false;
						}
						
						if (true) {
							spinner();
						}
						// if no errors, submit the petition changes.
						$('form')
								.attr(
										'action',
										'/JuvenileCasework/submitJuvenileDistrictCourtAttorneySetting.do?submitAction=Submit');
						$('form').submit();
					}

					$("#submitBtn").click(validate);
					$("input[id^='assignedAttorney1']").on('change', function (e) {	
						if(this.checked)
						{
							$("#assignedAttorneyId").val("1");
							/*if($("#barNumber").val() == "")
								alert("Bar Number And/Or Attorney Name Is Required.");*/
						}		
					});
					$("input[id^='assignedAttorney2']").on('change', function (e) {	
						if(this.checked)
						{
							$("#assignedAttorneyId").val("2");
							/*if($("#barNumber").val() == "")
								alert("Bar Number And/Or Attorney Name Is Required.");*/
						}		
					});
					// enter key
					$(document).bind("keydown", function(event) {
						if (event.which == 13) {
							validate();
						}
					});

					$("#searchAttorney")
							.on(
									'click',
									function(e) {
										spinner();
										$('form')
												.attr(
														'action',
														'/JuvenileCasework/submitJuvenileDistrictCourtAttorneySetting.do?submitAction=Search Attorney');
										$('form').submit();
									});

				});