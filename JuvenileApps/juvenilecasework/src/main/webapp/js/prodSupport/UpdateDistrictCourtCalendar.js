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
					if (typeof $("#crtDate") != "undefined") {
						datePickerSingle($("#crtDate"), "Date", false);
					}
					if (typeof $("#filingDate") != "undefined") {
						datePickerSingle($("#filingDate"), "Date", false);
					}
					if (typeof $("#amendmentDate") != "undefined") {
						datePickerSingle($("#amendmentDate"), "Date", false);
					}
					var validate = function() {
						var juvNum = $("#juvNum").val();
						//var crt = $("#courtNum").val();
						var crtDt = $("#courtDate").val();

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
						} else {
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
					var updatevalidate = function() {
						/*
						 * var date=$("#crtDate").val(); alert(date);
						 */
						var juvNum = $("#juvNum").val();	
						if(typeof(juvNum)!="undefined")
						{												
							if (juvNum != "") {
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
							} else {
								alert("Juvenile Number is required.");
								$("#juvNum").focus();
								return false;
							}							
						}
						var refNum = $("#refNum").val();
						if (refNum == "") 
						{
							alert("ReferralNumber is required.");
							$("#refNum").focus();
							return false;
						}
						if ($("#seqNum").val() == "") {
							alert("Sequence Number is required.");
							$("#seqNum").focus();
							return false;
						}
						if ($("#crtDate").val() == "") {
							alert("Court Date is required.");
							$("#crtDate").focus();
							return false;
						}
						if ($("#courtTime").val() == "") {
							alert("Time is required.");
							$("#courtTime").focus();
							return false;
						} else {
							if ($("#courtTime").val().length == 5) {
								var validTime = new String($("#courtTime")
										.val());
								var the_char = validTime.charAt(2);
								if (the_char != ":") {
									alert(" Must separate time with a Colon."
											+ "\n" + " Ex: hh:mm");
									$("#courtTime").focus();
									return false;
								}
								var char_one = validTime.charAt(0);
								var char_two = validTime.charAt(1);
								var hour = char_one + char_two;
								if (hour > 23) {
									alert("Transfer Hour must be a number from 0 to 23.\n");
									$("#courtTime").focus();
									return false;
								}
								var char_three = validTime.charAt(3);
								var char_four = validTime.charAt(4);
								var minutes = char_three + char_four;
								if (minutes > 59) {
									alert("Transfer minutes must be a number from 0 to 59.\n");
									$("#courtTime").focus();
									return false;
								}

								if ($.isNumeric(hour) == false
										|| $.isNumeric(minutes) == false) {
									alert("You must enter a numeric time.");
									$("#courtTime").focus();
									return false;
								}
								if (hour == 0) {
									if (minutes < 1) {
										alert("You must enter a time greater than zero.");
										$("#courtTime").focus();
										return false;
									}
								}
							} else {
								alert(" Enter time in hh:mm format");
								$("#courtTime").focus();
								return false;
							}
						}
						if ($("#petitionNumber").val() == "") {
							alert("Petition Number is required.");
							$("#petitionNumber").focus();
							return false;
						} else {
							var petitionNum = $("#petitionNumber").val();
							var petitionStatus = $("#petitionStatus").val();
							var currentYear = (new Date).getFullYear();
							if (petitionNum.toLowerCase().match("^j")) {
								if ((petitionNum.length < 8 || petitionNum.length >= 9)) {
									alert("If petition number entry begins with �J� (using a juvenile number), must be followed by 7 digits.");
									$("#petitionNumber").focus();
									return false;
								} else {
									if ($
											.isNumeric(petitionNum.substring(1,
													8)) == false) {
										alert("If petition number entry begins with �J� (using a juvenile number), must be followed by 7 digits.");
										$("#petitionNumber").focus();
										return false;
									}
								}
							} else {
								if ($.isNumeric(petitionNum.substring(0, 9)) == false) {
									alert("Petition Number is invalid - First 9 Digits Wrong. Please verify petition number");
									$("#petitionNumber").focus();
									return false;
								} else if (!petitionNum.substring(
										petitionNum.length - 1).toLowerCase()
										.match("j$")) {
									alert("Petition Number is invalid - Must End With J. Please verify petition number");
									$("#petitionNumber").focus();
									return false;
								}

							}// end of else(2)
						} // end of else(1)

						if ($("#petitionStatus").val() == "") {
							alert("Petition Status is required.");
							$("#petitionStatus").focus();
							return false;
						} else {
							var petStatus = $("#petitionStatus").val();
							if ($("#petitionStatus").val().toLowerCase() != "f"
									&& $("#petitionStatus").val().toLowerCase() != "r") {
								alert("Petition Status is invalid - Must be F or R. Please verify.");
								$("#petitionStatus").focus();
								return false;
							} else {
								$("#petitionStatus").val(
										petStatus.toUpperCase());
							}
						}
						if ($("#petitionAllegation").val() == "") {
							alert("Petition Allegation is required.");
							$("#petitionAllegation").focus();
							return false;
						} else {
							var petAllgn = $("#petitionAllegation").val();
							$("#petitionAllegation")
									.val(petAllgn.toUpperCase());
						}
						if ($("#filingDate").val() == "") {
							alert("Filing Date is required.");
							$("#filingDate").focus();
							return false;
						}
						if ($("#petitionAmendment").val() != "") {
							if ($("#amendmentDate").val() == "") {
								alert("Amendment Number and Amendment Date required if either is supplied.");
								$("#amendmentDate").focus();
								return false;
							}
						}
						if ($("#amendmentDate").val() != "") {
							if ($("#petitionAmendment").val() == "") {
								alert("Amendment Number and Amendment Date required if either is supplied.");
								$("#petitionAmendment").focus();
								return false;
							}
						}
						if ($("#attorneyConnection").val() != "") {
							if ($("#barNumber").val() == "") {
								alert("Bar number is required if attorney connection is provided.");
								$("#barNumber").focus();
								return false;
							}
							if ($("#attorneyName").val() == "") {
								alert("Attorney name is required if attorney connection is provided.");
								$("#attorneyName").focus();
								return false;
							}
						}
						/*var chkfile = $("#prevNotes").val();						
						if (chkfile != "") 
						{							
							for ( var i = 0; i < chkfile.length; i++) 
							{							
							if (iChars.indexOf(chkfile.value.charAt(i)) != -1) 
								{
									alert("Notes has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
									return false;							}						}
							return true;
							var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";
							if(!(iChars.match(/\W/g)) == "") {							    
								alert ("Notes has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
							    return false;
							}
							 var regex = /[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g;
								return regex.test(chkfile);
							//return !/[~`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/g.test(chkfile);
						}*/
						/*if ($("#barNumber").val() != "") {							
							if ($("#attorneyName").val() == "") {
								alert("Attorney name is required if bar number is provided.Click on Validate Bar Number to get the attorney name");
								$("#attorneyName").focus();
								return false;
							}
						}*/
						
						/*
						 * $('form').attr('action',"/JuvenileCasework/PerformUpdateDistrictCourtCalendarRecord.do?submitAction=Update
						 * Record"); $('form').submit();
						 */
						
						return true;

					}
					$("#updateBtn").click(function(){
						if ( updatevalidate() ) {
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
					$("#searchAttorneyBtn")
							.on(
									'click',
									function(e) {
										localStorage.setItem(
												"CalendarRecordWin", "open");
										$('form')
												.attr(
														'action',
														"/JuvenileCasework/PerformUpdateDistrictCourtCalendarRecord.do?submitAction=Search Attorney");
										spinner();
										$('form').submit();
									});
					$("#validateBarNumBtn")
							.on(
									'click',
									function(e) {
										$('form')
												.attr(
														'action',
														"/JuvenileCasework/PerformUpdateDistrictCourtCalendarRecord.do?submitAction=Validate Bar Number");
										spinner();
										$('form').submit();
									});

					$("#validateOffenseBtn")
							.on(
									'click',
									function(e) {
										var val = $("#petitionAllegation")
												.val();
										// var val = Trim(fld);
										if (val == "") {
											alert("Allegation Value Missing, Please Correct And Retry.")
											$("#petitionAllegation").focus();
											return false;
										} else {
											$("#petitionAllegation").val(
													val.toUpperCase());
											$('form')
													.attr(
															'action',
															"/JuvenileCasework/PerformUpdateDistrictCourtCalendarRecord.do?submitAction=Validate Offense");
											spinner();
											$('form').submit();
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
															'/JuvenileCasework/UpdateDistrictCourtCalendarRecordQuery.do?&docketId='+ docketEventId);
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
														'/JuvenileCasework/UpdateDistrictCourtCalendarRecordQuery.do?clr=Y');
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
