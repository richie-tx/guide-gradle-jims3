//JQuery File for Juv Tab Program Referral

$(document).ready(function(){
	
	if(document.title == "JIMS2 Juvenile Casework - programReferralList.jsp")
	{
		var ls = location.search;
		var sortInd = ls.indexOf("sortId");
		var amberInd = ls.indexOf("&");
		var idNumb = ls.substring(sortInd+7, amberInd);
		
		if (ls.indexOf("pager") > 0 || (idNumb > "20" && idNumb < "29"))
		{	
			$("[name='closedreferral']").attr('src',path + "/images/contract.gif");
			$("#cprInfo0").attr('class','visible');
		}
	}
	
	$("#progRefCommentUpdate").on('keydown mouseout', function(){
		var maxlimit = 1000;
		if( $(this).val().length > maxlimit )
		  {
		    alert( 'Maximum text length of ' + maxlimit + ' reached for this field - input has been truncated.' );
		    $(this).val($(this).val().substring( 0, maxlimit -1 ));
		  }
		
	});
	
	$("#gotoServiceProvider").on('click', function(event){
		event.preventDefault();
		
		var serviceProviderId = $('#juvServiceProviderId').val();
		var url = "/CommonSupervision/handleJuvServiceProviderSearchResults.do?submitAction=View&serviceProviderId=" + serviceProviderId + "&source=programReferralTab";
		spinner();
		window.location.href = url;

	});

});