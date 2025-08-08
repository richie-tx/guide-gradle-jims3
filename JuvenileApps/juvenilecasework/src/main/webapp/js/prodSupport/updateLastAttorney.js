/**
 * 
 */
$(document).ready(function () {	

	$("#searchAttorneyBtn")
	.on(
			'click',
			function(e) {
				localStorage.setItem(
						"CalendarRecordWin", "open");
				$('form')
						.attr(
								'action',
								"/JuvenileCasework/PerformUpdateLastAttorney.do?submitAction=Search Attorney");
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
								"/JuvenileCasework/PerformUpdateLastAttorney.do?submitAction=Validate Bar Number&barNumberId="+$("#barNumber").val());
				spinner();
				$('form').submit();
			});
	
$("#searchGalAttorneyBtn")
.on(
		'click',
		function(e) {
			localStorage.setItem(
					"CalendarRecordWin", "open");
			$('form')
					.attr(
							'action',
							"/JuvenileCasework/PerformUpdateLastAttorney.do?submitAction=Search Gal Attorney");
			spinner();
			$('form').submit();
		});
$("#validateGalBarNumBtn")
.on(
		'click',
		function(e) {
			$('form')
					.attr(
							'action',
							"/JuvenileCasework/PerformUpdateLastAttorney.do?submitAction=Validate Gal Bar Number&galBarNumberId="+$("#galBarNumber").val());
			spinner();
			$('form').submit();
		});
$("#findBtn")
.on(
		'click',
		function(e) {
			$('form')
					.attr(
							'action',
							"/JuvenileCasework/PerformUpdateLastAttorney.do?submitAction=Find Attorney&&attorneyName="+$("#attorneyName").val());
			spinner();
			$('form').submit();
		});
		



});