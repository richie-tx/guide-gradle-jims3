$(document).ready(function(){
	$("#selectRefBtn").prop("disabled", true);
	
	$("input:radio").change(function () {
	    $("#selectRefBtn").attr("disabled", false);
	});
	
	if (typeof $("#courtDate") != "undefined") {
		datePickerSingle($("#courtDate"), "Date", false);
	}
	
	if (typeof $("#filingDate") != "undefined") {
		datePickerSingle($("#filingDate"), "Date", false);
	}
	
	
	var validate = function() {
		var petitionNum = $("#petitionNum").val();
		var crtDt = $("#courtDate").val();
		console.log
		if ($("#petitionNum").val() == "") {
			alert("Petition Number is required.");
			$("#petitionNum").focus();
			return false;
		} else {
			var petitionNum = $("#petitionNum").val();
			if (petitionNum.toLowerCase().match("^j")) {
				if ((petitionNum.length < 8 || petitionNum.length >= 9)) {
					alert("If petition number entry begins with ‘J’ (using a juvenile number), must be followed by 7 digits.");
					$("#petitionNumber").focus();
					return false;
				} else {
					if ($
							.isNumeric(petitionNum.substring(1,
									8)) == false) {
						alert("If petition number entry begins with ‘J’ (using a juvenile number), must be followed by 7 digits.");
						$("#petitionNum").focus();
						return false;
					}
				}
			} else {
				if ($.isNumeric(petitionNum.substring(0, 9)) == false) {
					alert("Petition Number is invalid - First 9 Digits Wrong. Please verify petition number");
					$("#petitionNum").focus();
					return false;
				} else if (!petitionNum.substring(
						petitionNum.length - 1).toLowerCase()
						.match("j$")) {
					alert("Petition Number is invalid - Must End With J. Please verify petition number");
					$("#petitionNum").focus();
					return false;
				}

			}// end of else(2)
		} // end of
		
		
		
		return true;
	}
	
	$("#submitBtn").click(function(event){
		event.preventDefault();
		if ( validate() ){
			spinner();
			$('#ancillaryCalendar').submit();
		}
	});
	
	$("#selectRefBtn").click(function(event){
		var docketEventId = $(
		'input:radio[name=docketEventId]:checked')
		.val();
		console.log("Docket if: " + docketEventId);
		if ( docketEventId != null ) {
			$('form')
			.attr(
					'action',
					'/JuvenileCasework/UpdateAncillaryCalendarQuery.do?&docketId='+ docketEventId);
			spinner();
			$('form').submit();
		}
	});
	
	$("#searchAttorneyBtn")
	.on(
			'click',
			function(e) {
				localStorage.setItem(
						"CalendarRecordWin", "open");
				$('#updateAncillaryRecord')
						.attr(
								'action',
								"/JuvenileCasework/PerformUpdateDistrictCourtCalendarRecord.do?submitAction=Search Attorney&from=updateAncillaryCalendar");
				spinner();
				$('#updateAncillaryRecord').submit();
			});
	$("#validateBarNumBtn")
	.on(
			'click',
			function(e) {
				$('#updateAncillaryRecord')
						.attr(
								'action',
								"/JuvenileCasework/PerformUpdateDistrictCourtCalendarRecord.do?submitAction=Validate Bar Number&from=updateAncillaryCalendar");
				spinner();
				$('#updateAncillaryRecord').submit();
			});

});

