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
					if (typeof $("#hrgDate") != "undefined") {
						datePickerSingle($("#hrgDate"), "Date", false);
					}
					
					var validate = function() {
						var juvNum = $("#juvNum").val();
						var refNum = $("#refNum").val();
						var chainNum = $("#chainNum").val();
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
						} 
						else {
							alert("Juvenile Number is required.");
							$("#juvNum").focus();
							return false;
						}
						
						if ( true ) {
							spinner();
						}
					}

					$("#submitButton").click(validate);
					var updatevalidate = function() 
					{
						var newJuvNum	   = $("#juvNum").val();						
						var oldJuvNum	   = $("#oldJuvenileNum").val();						
						var oldAttorneyConnection	   = $("#oldAttorneyConnection").val();
						var newattorneyConnection	   = $("#attorneyConnection").val();						
						var newhrgDate      =  $("#hrgDate").val();
						var oldhrgDate      =  $("#oldCourtDate").val();	
						var newcrtResult	   = $("#crtResult").val();
						var oldCourtResult	   = $("#oldCourtResult").val();
						var oldHearingType  = $("#oldHearingType").val();
						var newhearingType  = $("#hearingType").val();
						var newbookingRefNum= $("#bookingRefNum").val();
						var oldReferralNum= $("#oldReferralNum").val();
						var oldBarNum     = $("#oldbarNum").val();
						var newBarNum     = $("#barNumber").val();	
						var oldattorneyName	   = $("#oldattorneyName").val();
						var newattorneyName	   = $("#newattorneyName").val();
						var oldPetitionNumber  = $("#oldPetitionNumber").val();
						var newPetitionNumber  = $("#petitionNumber").val();
						if( (newJuvNum == oldJuvNum)
								&& ( oldAttorneyConnection == newattorneyConnection ) 
								&& ( newhrgDate == oldhrgDate ) 
								&& ( newcrtResult == oldCourtResult )
								&& ( oldBarNum == newBarNum)&&(oldattorneyName == newattorneyName)
								&& ( oldHearingType == newhearingType )
								&& ( newbookingRefNum == oldReferralNum )
								&& ( oldPetitionNumber == newPetitionNumber )
							)
						{								
								alert('No Changes detected');
								return false;
						}					
						
						var juvNum = $("#juvNum").val();						
						var refNum = $("#refNum").val();
						var chainNum = $("#chainNum").val();
						var crtDt = $("#courtDate").val();

						if ($("#juvNum").val() != "") {
							if ($("#juvNum").val().trim().length < 6) {
								alert("Invalid juvenile number.  Please verify entry");
								$("#juvNum").focus();
								return false;
							}

							// juvenile Number
							if (Math.floor(juvNum) != juvNum
									|| $.isNumeric(juvNum) == false) {
								alert("Invalid juvenile number.  Please verify entry");
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
						
						
						if ($("#hrgDate").val() == "") {
							alert("Hearing Date is required.");
							$("#hrgDate").focus();
							return false;
						}
						if ($("#refNum").val() == "") {
							alert("Referral Number is required.");
							$("#refNum").focus();
							return false;
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
						
						
						return true;					

					}
					$("#updateBtn").click(function(event){
						
						var courtId = $("#courtId").val();
						
						if ($("#courtId").val() != "") {
							if ($("#courtId").val().trim().length > 3) {
								alert("Invalid court.  Please verify entry");
								$("#courtId").focus();
								 event.preventDefault(); 
								return false;
							}

							// juvenile Number
							if (Math.floor(courtId) != courtId
									|| $.isNumeric(courtId) == false) {
								alert("Invalid court.  Please verify entry");
								$("#courtId").focus();
								event.preventDefault(); 
								return false;
							}
						} 
						else 
						{
							alert("Court is required.");
							$("#courtId").focus();
							event.preventDefault(); 
							return false;
						}
						
						if ( updatevalidate ) {
							spinner();
						}
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
												"DetRecordWin", "open");										
										$('form')
												.attr(
														'action',
														"/JuvenileCasework/PerformUpdateDetentionCourtRecord.do?submitAction=Search Attorney");
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
														"/JuvenileCasework/PerformUpdateDetentionCourtRecord.do?submitAction=Validate Bar Number");
										spinner();
										$('form').submit();
									});

					/*$("#validateOffenseBtn")
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
															"/JuvenileCasework/PerformUpdateDetentionCourtRecord.do?submitAction=Validate Offense");
											$('form').submit();
										}
									});*/
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
															'/JuvenileCasework/UpdateDetentionCourtRecordQuery.do?&docketId='+ docketEventId);
											spinner();
											$('form').submit();
											return true;
										}

									});

					$("#BtnRefresh").on("click", function() {
						// Clear fields on the form
						$("#juvNum").val("");
						$("#courtNum").val("");
						$("#courtDate").val("");
					});
					$("#backToQryBtn")
							.on(
									"click",
									function() {
										// Clear fields on the form
										$('form')
												.attr('action',
														'/JuvenileCasework/UpdateDetentionCourtRecordQuery.do?clr=Y');
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
