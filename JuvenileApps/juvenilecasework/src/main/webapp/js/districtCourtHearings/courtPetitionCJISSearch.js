$(document).ready(function () {
   
    $("#submitYouthCrtAct").on('click', function (e) {
    	spinner();
    	var juvNum = $("#origjuvNumId").val();
	 	//alert(juvNum)
	 	$('form').attr('action','/JuvenileCasework/handleJuvenileDistrictCourtPetitionCJISSearch.do?submitAction=Court Activity&juvnum='+juvNum);
		$('form').submit();
		return true;
    });
    
   //add all validations in home page here..
    
	$("#reSubmitbtn").on('click', function (e) {
		var juvNum = $("#juvNum").val();
    	var refNum = $("#typeCd").val();
    	
    	if(refNum!=""&&refNum!="PET"&&refNum!="pet"&&refNum!="CJIS"&&refNum!="cjis")
    	{
    		alert("Invalid Type Code. "+refNum);
    		$("#typeCd").val('');
			$("#typeCd").focus(); 
    		return false;    		
    	}
    	if(juvNum=="" && refNum=="")
    	{ //ref and juvnum
    		alert("Juvenile Number/Petition Number/CJIS Number or type code is required.");    				
    		$("#juvNum").focus();
    		return false;
    	}
    	else if(juvNum=="")
    	{ //juvNum
    		alert("Juvenile Number/Petition Number/CJIS Number is required.");    		
    		$("#juvNum").focus();
    		return false;
    	}
    	else if(juvNum!="" && refNum=="")
    	{
    		if(juvNum.trim().length>6)
    		{
    			alert("Type code is required.");
    			$("#typeCd").focus();        		
        		return false;
    		}
    		else
    			spinner();
    	}
    	else if(juvNum!="" && refNum!="")
    	{
    		if(juvNum.trim().length==6)
    		{
    			alert("Type code not required for Juv num search.");
    			$("#typeCd").val('');
    			$("#typeCd").focus();
    			return false;
    		}
    		else if(juvNum.trim().length<10 && (refNum=="cjis"||refNum=="CJIS"))
    		{
    			alert(" At least first 10 digits of CJIS is required for search");
    			$("#juvNum").focus();
    			return false;
    		}
    		else if(juvNum.trim().length<10 && (refNum=="pet"||refNum=="PET"))
    		{
    			alert(" Petition number entered is not valid.");
    			$("#juvNum").focus();
    			return false;
    		}
    		else
    			spinner();
    	}
    		
    	else 
    	{
		    spinner();
    	}
    	
    	$('form').attr('action','/JuvenileCasework/handleJuvenileDistrictCourtPetitionCJISSearch.do?submitAction=GO&juvnum='+juvNum+'&typeCode='+refNum);
		$('form').submit();		
		return true;
    });
    
});    