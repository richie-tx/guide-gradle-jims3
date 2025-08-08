$(document).ready(function () {
	//Court Date
	var cursorPosition = $("#cursorPosition").val();
	var currDate= new Date(); //current Date
	if(typeof $("#date") != "undefined"){
		datePickerSingle($("#date"),"Date",false);
	}
	//current page offset.
	var startIndex;
	var endIndex;
	var ps = document.getElementsByName("pager.offset");
	var elementIndex = $(ps).val();
	var noOfElements;
	var searchResultSize = $("#searchResultSize").val();
			
	startIndex= elementIndex
	noOfElements =  4
	
	var pageNumber = (parseInt(startIndex) / 4) + 1;
    var lastPageItems = searchResultSize%4;
    var totalPages = parseInt((searchResultSize/4) + 1);
	if(pageNumber == totalPages){
		noOfElements = lastPageItems
		}
	endIndex = parseInt(startIndex) + parseInt(noOfElements); 
	for (var i=startIndex; i <endIndex; i++) 
	{
			var docketEventId=$('[name=\'dockets['+i+'].docketEventId\']');
			var trnValue=$('[name=\'dockets['+i+'].transferFlag\']');
			var prevResult = $("#oldCourtResult-"+docketEventId.val()).val();
			var newResult = $("#courtResult-"+docketEventId.val()).val();			
			var atyConfirm= document.getElementById('atyConfirmation-'+docketEventId.val()).value;
			var hidden=$("#atyBypass-"+docketEventId.val());
			if(typeof (hidden.val())!="undefined")
				var atyBypass= document.getElementById('atyBypass-'+docketEventId.val()).value;	
			else
				var atyBypass='';			
			//alert("atyBypass="+atyBypass)
			var oldattorneyName = $("#oldAttorney-"+docketEventId.val()).val();
			var attorneyName = $("#attorneyName-"+docketEventId.val()).val();
			var oldattorneyConn=document.getElementById('oldattorneyConn-'+docketEventId.val()).value;
			var prevResetTo = $("#prevResetTo-"+docketEventId.val()).val();
			var resetTo = $("#resetToDate-"+docketEventId.val()).val();			
			var transferto = $("#transTo-"+docketEventId.val()).val();
			var newtransferto = $("#oldtransTo-"+docketEventId.val()).val();
			document.getElementById('attnyConfm.'+docketEventId.val()).disabled=true;
			var button=$("#attnyBypass."+docketEventId.val());
			if(typeof (button.val())!="undefined")
				document.getElementById('attnyBypass.'+docketEventId.val()).disabled=true;			
			if(trnValue.val()=="1")
			{
				$("#TRN-"+docketEventId.val()).prop("checked", true);
				/*$("#TRN-"+docketEventId.val()).prop("disabled", false);*/
				$("#transTo-"+docketEventId.val()).prop("disabled", false);
			}
			var interpreter= document.getElementById('interpreter-'+docketEventId.val()).value;  
		    //alert(interpreter)
		    if(interpreter=="1")
		    	document.getElementById('interpreter.'+docketEventId.val()).checked=true;
		    else
		    	document.getElementById('interpreter.'+docketEventId.val()).checked=false;
		    var newattorneyConn;
			if(document.getElementById('attyConn1-'+docketEventId.val()).checked== true)
			{
				newattorneyConn='HAT';
			}
			else if(document.getElementById('attyConn2-'+docketEventId.val()).checked== true)
			{
				newattorneyConn='AAT';
			}
			else if(document.getElementById('attyConn3-'+docketEventId.val()).checked== true)
			{
				newattorneyConn='PDO';
			}	
			else
			{
				newattorneyConn='';
			}
			if(atyBypass!="YES")
			 {
				/*alert(oldattorneyName)
				alert(attorneyName)
				alert(oldattorneyConn)
				alert(newattorneyConn)*/
				if(oldattorneyName!=attorneyName||oldattorneyConn!=newattorneyConn)	  
				{
				  	document.getElementById('attnyConfm.'+docketEventId.val()).disabled=false;
				  	if(document.getElementById('attnyBypass.'+docketEventId.val()))
				  		document.getElementById('attnyBypass.'+docketEventId.val()).disabled=false;
				  	
				}	
				if(atyConfirm!="YES")
			    {	
				 	
					if(prevResult!=newResult||prevResetTo!=resetTo||transferto!=newtransferto)			  
			  		{
			  			document.getElementById('attnyConfm.'+docketEventId.val()).disabled=false;
			  			if(document.getElementById('attnyBypass.'+docketEventId.val()))
			  				document.getElementById('attnyBypass.'+docketEventId.val()).disabled=false;
			  		}	
				 
			    }
			 }
			 if( resetTo== '')
			 {  
				 $("#TRN-"+docketEventId.val()).prop("disabled", false);
			 }
			 else if ( resetTo!= '')
			 {
				 $("#TRN-"+docketEventId.val()).prop("disabled", true);
			 }
		    
	}
	  	
	
	var paginationPageInput = document.getElementsByName('pagerSearch')[0];
	var offset =  $("#pagerOffset").val();		
	var currentPage = localStorage.getItem("currentPageNum");
	/*alert('offset='+offset)
	alert('currentPage='+currentPage)
	if(offset!="0" && currentPage != "0")
	{	
		var currOffset = parseInt(currentPage);
		var newOffSet = (currOffset - 1) * 4;	
		localStorage.setItem("currentPageNum", "0");
		document.getElementsByName('pagerSearch')[0].value = newOffSet;
		document.getElementsByName("pager.offset")[0].value = newOffSet;		
		window.location.href="/JuvenileCasework/jsp/detentionCourtHearings/detentionHearingAction.jsp?pager.offset=" + newOffSet;
		
	}*/
	//bug fix for hot fix 145261 added typeof(offset)!="undefined" && offset!="0"
	if(typeof(offset)!="undefined" && offset!="0"&& currentPage != "0")
	{			
		var currOffset = parseInt(currentPage);
		var newOffSet = (currOffset - 1) * 4;	
		localStorage.setItem("currentPageNum", "0");
		document.getElementsByName('pagerSearch')[0].value = newOffSet;
		document.getElementsByName("pager.offset")[0].value = newOffSet;		
		window.location.href="/JuvenileCasework/jsp/detentionCourtHearings/detentionHearingAction.jsp?pager.offset=" + newOffSet;
		
	}
	else if(ps.length > 0 || typeof( ps.value)!="undefined"){
		localStorage.setItem("currentPageNum", "0");
		document.getElementsByName("pager.offset")[0].value = 0;	
	}	
	
	$('[type=checkbox]:first').focus();
	 
	var scroll= $(window).scroll();
	scroll= scroll+ 100;
	
	//on error position the cursor.
	$(cursorPosition).focus();
	$("[id^='S.']").find("input,button,text,select").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5"); //Bug #76479
	$("[id^='I.']").find("input,button,text,select").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5"); //Bug #76479
	
	
		
	//defaults all the radio buttons.
	var attorneyConnections=$("form input:radio");
	  $.each(attorneyConnections,function(index){ //loops through all the radio buttons.
		  if(typeof radio !== 'undefined' && $(this).val()!==''){ // 
			  $(this).prop("checked",'checked');
		  }
	  });
	  
	  var courtResults=$("select[id^='courtResult']");
	  $.each(courtResults,function(index){ //loops through all the radio buttons.
		  var result = $(this).val();
		  var $obj = this.id;
		  var docketEventId = $obj.split('-')[1];
		  
		  var jsonObj = jQuery.parseJSON($("#courtDecisions").val());
		  $.each(jsonObj, function(i, item) {
			  var code = item.code;
	   		  var resetAllowed = item.allowReset;
		 		if( result == code ){
		 		    if( resetAllowed == 'N'){
		 		    	$("#resetToDate-"+docketEventId).prop("disabled", true);
		 		    	$("#TRN-"+docketEventId).prop("disabled", true);
		 			}
		 		}	 
		  });
		  
		 /*if( result!== 'TRN' ){ // 
			  $("#transTo-"+docketEventId).prop("disabled", true);
		  }*/ 
		 //bug fix for 150953- disabling reset to date to avoid blowout
		 if( result== ''){// && document.getElementById('TRN-'+docketEventId).checked== false
		 $("#resetToDate-"+docketEventId).prop("disabled", true);
		 $("#TRN-"+docketEventId).prop("disabled", true);
		 }
		 
	  });
	  $("input[id^='TRN-']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      var result = $("#courtResult-"+dktEventId).val();
	      //alert(dktEventId)
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 } 
	      if(document.getElementById('TRN-'+dktEventId).checked== true)
		  {
	    	  //alert("checked")
	    	  if( result== '')
	    	  {
	    		  alert("Please select a court result.");
	 				$("#courtResult-"+dktEventId).focus();
	 				$("#TRN-"+dktEventId).prop("checked", false);
	 				return false;
	    	  }
			 $("#transTo-"+dktEventId).prop("disabled", false);
			  $("#transTo-"+dktEventId).focus();
			  //$("#resetToDate-"+dktEventId).prop("disabled", false);
			  $("#TRNValue-"+dktEventId).val('1');
		  }
		 if(document.getElementById('TRN-'+dktEventId).checked== false)
		  {
			 //alert("unchecked")
			 $("#transTo-"+dktEventId).prop("disabled", true);
			 $("#transTo-"+dktEventId).val('');
			 //$("#resetToDate-"+dktEventId).prop("disabled", true);
			 $("#TRNValue-"+dktEventId).val('0');
		  }			 
      });
	  
	
	// Varying field date inputs
	$("input[id^='resetToDate']").on("click", function( e ) {
		//$(this).datepicker().datepicker("show");	
		var input = $(this);
		  var val = input.val();
		  var $obj = this.id;
		  var docketEventId = $obj.split('-')[1];
		  datePickerSingle($("#resetToDate-"+docketEventId),"Date",false);
		  $("#resetToDate-"+docketEventId).focus();
	});
		
	//required fields validation
	$("#btnCrtAction,#btnCrtDocket").on("click",function(){
		//Can be a hearing Date\court date
		if(typeof $("#date") != "undefined"){
			if($("#date").val()==""){
				alert("Date is required. Please select a date.");
				$("#date").focus();
				return false;
			}
		}
		
		if ( true ){
			spinner();
		}
	});
	
	//On click of the submit button
	 $("#goBtn").on('click', function (e) {
		 if ( true ) {
			 spinner();
		 }
	 });
	 
	//holiday check calendar.
	var countyHolidayList = $("#holidaysList").val();
	
		
	//On click of the submit button
	 $("#submitBtn").on('click', function (e) {	
		 	var startIndex;
			var endIndex;
			var noOfElements;
			if( typeof( paginationPageInput )!="undefined"){
	  			localStorage.setItem("currentPageNum", paginationPageInput.value);
	  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
				$("#pagerOffset").val(offset);
  		   }
			var ps = document.getElementsByName("pager.offset");
			var elementIndex = $(ps).val();
			var searchResultSize = $("#searchResultSize").val();
			startIndex= elementIndex
			noOfElements =  4
			
			var pageNumber = (parseInt(startIndex) / 4) + 1;
		    var lastPageItems = searchResultSize%4;
		    var totalPages = parseInt((searchResultSize/4) + 1);
			if(pageNumber == totalPages){
				noOfElements = lastPageItems
				}
			endIndex = parseInt(startIndex) + parseInt(noOfElements); 
			for (var i=startIndex; i <endIndex; i++) 
			{
					var docketEventId=$('[name=\'dockets['+i+'].docketEventId\']');			
					var juvNum=document.getElementById('juvNum-'+docketEventId.val()).value;
					var hidden=$("#atyBypass-"+docketEventId.val());
					if(typeof (hidden.val())!="undefined")
					{
						/*if(document.getElementById('atyBypass-'+docketEventId.val()).value=='YES')
						{
							
						}
						else
						{*/
							if(document.getElementById('attnyBypass.'+docketEventId.val()).disabled==false)
							{
								alert('Please CONFIRM or BYPASS the attorney for JUV - '+juvNum+'.');				
								return false;	
							}
							
						//}
					}
					else
					{
						if(document.getElementById('attnyConfm.'+docketEventId.val()).disabled==false)
						{
							alert('Please confirm the attorney for JUV - '+juvNum+'.');				
							return false;	
						}
					}
			}
		 update();
	 });
	 
	//enter key		 
	 $(document).bind("keydown", function(event) {
	    if (event.which == 13){
        	 event.preventDefault();	
	    	update();
	    }
	 });
	 
	//On click of the submit button
	 $("#deleteSettingBtn").on('click', function (e) {		 
		 deleteRecord();
	 });
	
	//Focus on Bar number
	if(cursorPosition=="barNum"){		
		 var docketEvtId=  $("#tempDocketEventId").val();
		 $("#barNum-"+docketEvtId).focus();
	}
		
	if (cursorPosition == "galBarNum"){
		var docketEvtId=  $("#tempDocketEventId").val();
		$("#galBarNum-"+docketEvtId).focus();
	}
	
	  // on click of validate bar number
	  $("input[id^='validateBarNumber']").on('click', function (e) {
		//  e.preventDefault();		  
		  $("#tempDocketEventId").val('');
		  $("#tempBarNumber").val('');
		  var allowSubmit = true;
		
		  var buttonId = this.id;
		  var docketEventId = buttonId.split('.')[1];
		  var barNum = $("#barNum-"+docketEventId).val();
		  var attName = $("#attorneyName-"+docketEventId);	  
		
		  if(barNum=="")
		  {
	  		 /* alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
	  		  $("#barNum-"+docketEventId).focus();
	  		  return false;*/
			  attName.val("");	
			  if(document.getElementById('attyConn1-'+docketEventId).checked== true)
			  {
				  document.getElementById('attyConn1-'+docketEventId).checked=false;
			  }
			  if(document.getElementById('attyConn2-'+docketEventId).checked== true)
			  {
				  document.getElementById('attyConn2-'+docketEventId).checked=false;
			  }
			  if(document.getElementById('attyConn3-'+docketEventId).checked== true)
			  {
				  document.getElementById('attyConn3-'+docketEventId).checked=false;
			  }
			  allowSubmit = true;			  
	  	  }
		  //alert(allowSubmit);
		  if(barNum.trim()!="")
		  {
			  if( $.isNumeric( barNum.trim() )== false)
			  {
		  			alert("Bar Number Is Numeric. Please Correct And Retry.");
		  			allowSubmit = false;
		  			$("#barNum-"+docketEventId).focus();
		  			return false;
		  	  }
		  }
		  
		  if( allowSubmit ){
			  if( typeof( paginationPageInput )!="undefined"){
		  			localStorage.setItem("currentPageNum", paginationPageInput.value);
		  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
					$("#pagerOffset").val(offset);
	  		   }
			 
			  $("#tempBarNumber").val(barNum);
			  $("#tempDocketEventId").val(docketEventId); //set it in the form
			  spinner();
			  $('form').attr('action',"/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Validate");
			  $('form').submit();
		  } 
		  
	  });
	// on click of atty 2 validate bar number
	  $("input[id^='validate2ndAttorneyBarNum']").on('click', function (e) {
		//  e.preventDefault();		  
		  $("#tempDocketEventId").val('');
		  $("#tempBarNumber").val('');
		  var allowSubmit = true;
		
		  var buttonId = this.id;
		  var docketEventId = buttonId.split('.')[1];
		  var barNum = $("#2ndAttorneyBarnum-"+docketEventId).val();
		  var attName = $("#2ndAttorney-"+docketEventId);	  
		  if(barNum=="")
		  {
	  		 /* alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
	  		  $("#barNum-"+docketEventId).focus();
	  		  return false;*/
			  attName.val("");	
			  if(document.getElementById('2ndAttorneyHATattorneyConnection-'+docketEventId).checked== true)
			  {
				  document.getElementById('2ndAttorneyHATattorneyConnection-'+docketEventId).checked=false;
			  }
			  if(document.getElementById('2ndAttorneyAATattorneyConnection-'+docketEventId).checked== true)
			  {
				  document.getElementById('2ndAttorneyAATattorneyConnection-'+docketEventId).checked=false;
			  }
			  if(document.getElementById('2ndAttorneyPDOattorneyConnection-'+docketEventId).checked== true)
			  {
				  document.getElementById('2ndAttorneyPDOattorneyConnection-'+docketEventId).checked=false;
			  }
			  allowSubmit = true;			  
	  	  }
		  //alert(allowSubmit);
		  if(barNum.trim()!="")
		  {
			  if( $.isNumeric( barNum.trim() )== false)
			  {
		  			alert("Bar Number Is Numeric. Please Correct And Retry.");
		  			allowSubmit = false;
		  			$("#2ndAttorneyBarnum-"+docketEventId).focus();
		  			return false;
		  	  }
		  }
		  
		  if( allowSubmit ){
			  if( typeof( paginationPageInput )!="undefined"){
		  			localStorage.setItem("currentPageNum", paginationPageInput.value);
		  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
					$("#pagerOffset").val(offset);
	  		   }
			 
			  $("#tempBarNumber").val(barNum);
			  $("#tempDocketEventId").val(docketEventId); //set it in the form
			  spinner();
			  $('form').attr('action',"/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Validate Aty2 Barnum");
			  $('form').submit();
		  } 
		  
	  });
	  
	  // on click of validate bar number
	  $("input[id^='validateGalBarNum']").on('click', function (e) {
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  var barNum = $('[name=\'dockets['+elementIndex+'].galBarNum\']');
		  if(typeof barNum!="undefined"){
			  if(barNum.val()==""){
		  		  alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
		  		  barNum.focus();
		  		  return false;
		  	  }
			  
			  if(barNum.val()!="" && $.isNumeric(barNum.val().trim())==false){
		  			alert("Bar Number Is Numeric. Please Correct And Retry.");
		  			barNum.focus();
		  			return false;
		  		}
		  }
		  
		  $("#tempDocketEventId").val('');
		  $("#tempBarNumber").val('');
		  
		  var buttonId = this.id;
		  var docketEventIdKey = buttonId.split('.')[1];
		
		  if( typeof( paginationPageInput )!="undefined"){
	  			localStorage.setItem("currentPageNum", paginationPageInput.value);
	  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
				$("#pagerOffset").val(offset);
		  }
		  
		  $("#tempBarNumber").val(barNum.val());
		  $("#tempDocketEventId").val(docketEventIdKey); //set it in the form
		  
		  if ( true ) {
			  spinner();
		  }
		  
		  $('form').attr('action',"/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=ValidateAdlitem&barNum="+barNum.val());
		  $('form').submit();
	  });	
	  
	
	  //on click of search button
	  $("input[id^='searchAttorney']").on('click', function (e) {
		  
		  if( typeof( paginationPageInput )!="undefined" ){
	  			localStorage.setItem("currentPageNum", paginationPageInput.value);
	  			//offset is calculated based on the pagenum everytime in load so commented out the below
	  			/*
	  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
	  			$("#pagerOffset").val($("input[name='pagerSearch']").val());
	  			alert($("#pagerOffset").val())*/
		   }
		  
		 $("#tempDocketEventId").val('');
		 var buttonId = this.id;
	     var docketEventId = buttonId.split('.')[1];

	     $("#tempAttorneyName").val($("#attorneyName-"+docketEventId).val());
	     $("#tempDocketEventId").val(docketEventId); //set it in the form
	     spinner();
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Search Attorney&mainOffset='+offset);
	  	 $('form').submit();
	  });
	//on click of search attorney2 button
	  $("input[name^='search2ndAttorney']").on('click', function (e) {
		 
		 var elementName = $(this).attr('name')
		 var elementIndex = elementName.split('[')[1].split("]")[0];
		 //var searchAttorney = $('[name=\'allDktSearchResults['+elementIndex+'].galName\']');
		 
		 /*if( typeof( paginationPageInput )!="undefined"){
	  			localStorage.setItem("currentPageNum", paginationPageInput.value);
	  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
				$("#pagerOffset").val(offset);
				
		   }
		 alert(offset)*/
		 $("#action").val("search2ndAttorney");  
  	     $("#tempDocketEventId").val('');
		 var buttonId = this.id;
  	     var docketEventIdKey = buttonId.split('.')[1];
  	     $("#tempDocketEventId").val(docketEventIdKey); //set it in the form
  	     spinner();
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Search Attorney&mainOffset='+offset);
	  	 $('form').submit();
	  	
	  });
	  //on click of search gal button
	  $("input[name^='searchGAL']").on('click', function (e) {
		 
		 var elementName = $(this).attr('name')
		 var elementIndex = elementName.split('[')[1].split("]")[0];
		 //var searchAttorney = $('[name=\'allDktSearchResults['+elementIndex+'].galName\']');
		 
		 if( typeof( paginationPageInput )!="undefined"){
	  			localStorage.setItem("currentPageNum", paginationPageInput.value);
	  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
				$("#pagerOffset").val(offset);
		   }
		 $("#action").val("searchGALCourtAction");  
  	     $("#tempDocketEventId").val('');
		 var buttonId = this.id;
  	     var docketEventIdKey = buttonId.split('.')[1];
  	     $("#tempDocketEventId").val(docketEventIdKey); //set it in the form
  	     spinner();
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Search GAL');
	  	 $('form').submit();
	  	
	  });
	  $("input[id^='attyConn1']").on('click', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];		
	      document.getElementById('attnyConfm.'+dktEventId).disabled=false;
	      if(document.getElementById('attnyBypass.'+dktEventId))
	    	  document.getElementById('attnyBypass.'+dktEventId).disabled=false;
		});
	  $("input[id^='attyConn1']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      
	      var oldattorneyConn=document.getElementById('oldattorneyConn-'+dktEventId).value;
	      var newattorneyConn;
		  if(document.getElementById('attyConn1-'+dktEventId).checked== true)
		  {
			  newattorneyConn='HAT';
		  }
		  if(document.getElementById('attyConn2-'+dktEventId).checked== true)
		  {
			  newattorneyConn='AAT';
		  }
		  if(document.getElementById('attyConn3-'+dktEventId).checked== true)
		  {
			  newattorneyConn='PDO';
		  }		
    	  if(oldattorneyConn!=newattorneyConn)			  
	  	  {
			document.getElementById('attnyConfm.'+dktEventId).disabled=false;
			if(document.getElementById('attnyBypass.'+dktEventId))
				document.getElementById('attnyBypass.'+dktEventId).disabled=false;
	  	  }   
    	  
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 }
	     $("#tempDocketEventId").val(dktEventId); //set it in the form
	     $("#selectedVal").val( "HAT" ); //set it on the form
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney Setting');
	  	 $('form').submit();
      });
	  $("input[id^='attyConn2']").on('click', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];		
	      document.getElementById('attnyConfm.'+dktEventId).disabled=false;
	      if(document.getElementById('attnyBypass.'+dktEventId))
	    	  document.getElementById('attnyBypass.'+dktEventId).disabled=false;
		});
	  $("input[id^='attyConn2']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      var oldattorneyConn=document.getElementById('oldattorneyConn-'+dktEventId).value;
	      
		  var newattorneyConn;
		  if(document.getElementById('attyConn1-'+dktEventId).checked== true)
		  {
			  newattorneyConn='HAT';
		  }
		  if(document.getElementById('attyConn2-'+dktEventId).checked== true)
		  {
			  newattorneyConn='AAT';
		  }
		  if(document.getElementById('attyConn3-'+dktEventId).checked== true)
		  {
			  newattorneyConn='PDO';
		  }		
    	  if(oldattorneyConn!=newattorneyConn)			  
	  	  {
			document.getElementById('attnyConfm.'+dktEventId).disabled=false;
			if(document.getElementById('attnyBypass.'+dktEventId))
				document.getElementById('attnyBypass.'+dktEventId).disabled=false;
	  	  } 
    	  
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
			}
		   $("#tempDocketEventId").val(dktEventId); //set it in the form
		   $("#selectedVal").val( "AAT" ); //set it on the form
		   $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney Setting');
		   $('form').submit();
      });
	  $("input[id^='attyConn3']").on('click', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];		
	      document.getElementById('attnyConfm.'+dktEventId).disabled=false; 
	      if(document.getElementById('attnyBypass.'+dktEventId))
	    	  document.getElementById('attnyBypass.'+dktEventId).disabled=false;
		});
	  $("input[id^='attyConn3']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      var oldattorneyConn=document.getElementById('oldattorneyConn-'+dktEventId).value;
	      
		  var newattorneyConn;
		  if(document.getElementById('attyConn1-'+dktEventId).checked== true)
		  {
			  newattorneyConn='HAT';
		  }
		  if(document.getElementById('attyConn2-'+dktEventId).checked== true)
		  {
			  newattorneyConn='AAT';
		  }
		  if(document.getElementById('attyConn3-'+dktEventId).checked== true)
		  {
			  newattorneyConn='PDO';
		  }		
    	  if(oldattorneyConn!=newattorneyConn)			  
	  	  {
			document.getElementById('attnyConfm.'+dktEventId).disabled=false;
			if(document.getElementById('attnyBypass.'+dktEventId))
				document.getElementById('attnyBypass.'+dktEventId).disabled=false;
	  	  } 
    	  
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 }
	     $("#tempDocketEventId").val(dktEventId); //set it in the form
	     $("#selectedVal").val( "PDO" ); //set it on the form
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney Setting');
	  	 $('form').submit();
      });
	  //attorney 2 connection change
	  $("input[id^='2ndAttorneyHATattorneyConnection']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 } 
	     $("#tempDocketEventId").val(dktEventId); //set it in the form
	     $("#selectedVal").val( "HAT" ); //set it on the form
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney2 Setting');
	  	 $('form').submit();
      });
	  $("input[id^='2ndAttorneyAATattorneyConnection']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 }
	      
	     $("#tempDocketEventId").val(dktEventId); //set it in the form
	     $("#selectedVal").val( "AAT" ); //set it on the form
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney2 Setting');
	  	 $('form').submit();
      });
	  $("input[id^='2ndAttorneyPDOattorneyConnection']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 }
	      
	     $("#tempDocketEventId").val(dktEventId); //set it in the form
	     $("#selectedVal").val( "PDO" ); //set it on the form
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney2 Setting');
	  	 $('form').submit();
      });
	  //
	  $("select[id^='courtResult']").on('click', function (e) {
		  	var $obj = this.id;
		  	var docketEventId = $obj.split('-')[1];
		  	var atyConfirm= document.getElementById('atyConfirmation-'+docketEventId).value;	
			if(atyConfirm!="YES")
		    {		
				document.getElementById('attnyConfm.'+docketEventId).disabled=false;
				if(document.getElementById('attnyBypass.'+docketEventId))
					document.getElementById('attnyBypass.'+docketEventId).disabled=false;
		    }	
		});
	  $("select[id^='courtResult']").on('change', function (e) {

		  var allowSubmit = true;
		  var $obj = this.id;
		  var docketEventId = $obj.split('-')[1];
		  var prevResult = $("#prevCourtResult-"+docketEventId).val();
		  var newResult = $("#courtResult-"+docketEventId).val();
		  var atyConfirm= document.getElementById('atyConfirmation-'+docketEventId).value;	  
		  if(atyConfirm!="YES")
		    {				  
				if(prevResult!=newResult)			  
		  		{
		  			document.getElementById('attnyConfm.'+docketEventId).disabled=false;
		  			if(document.getElementById('attnyBypass.'+docketEventId))
		  				document.getElementById('attnyBypass.'+docketEventId).disabled=false;
		  		}	
		    }
		  var dktEventId = '';
		  $("#tempResult").val(''); //set it on the form
		  $("#tempDocketEventId").val(''); //set it in the form
		  var ArrDT = ['CDO','PCT','CPO','DET','WSA','REL','SRR'];
		  var ArrPC = ['RPC','PCD','PCF','SRA','NPC','PCR','RBJ'];
		  var resultantId = this.id;
		      dktEventId = resultantId.split('-')[1];
		  var hearType = $("#hearingType-"+dktEventId).val();
		  if( typeof dktEventId !== 'undefined'){
			  var resetTo = $("#resetToDate-"+dktEventId).val();
			  var juvenileName = $("#juvName-"+dktEventId).val();
			  
			  if( juvenileName.indexOf( "JUVENILE NOT") != -1){				  
				  allowSubmit = false;
				  alert( "RESULT CHANGE NOT ALLOWED");
				  return false;
			  }
			  
			  if( hearType == '' ){
				  
				  allowSubmit = false;
	    		  alert( "HEARING TYPE MISSING. CONTACT DATA CORRECTIONS");
	    		  return false;
			  }
			  var resultId = $("#courtResult-"+dktEventId).val();
			  var resultIdPrev = $("#prevCourtResult-"+dktEventId).val();
			  
			    //Detention Court Decisions
				var detentionDecisions = $("#courtDecisions").val();

	 		 	$.each(jQuery.parseJSON(detentionDecisions), function(idx, obj) {
	 		 		
	 		 		var code = obj.code;
	 	   		    var hearingType = obj.decision;
	 		 		if(resultId == code){
	 		 		    if( hearingType != 'BOTH' && hearType != hearingType){
	 		 		    	 $("#courtResult-"+dktEventId).val(resultIdPrev);
	 			    		 allowSubmit = false;
	 		 				 alert( "SELECTED RESULT IS NOT ALLOWED ON THE HEARING TYPE< "+hearType+" >");
	 		 			}
	 		 		}	 		 		
	 			});
			  
			  
			  if( resultId == "TRN" ){
				  $("#resetTo-"+dktEventId).prop("enabled", true);
				  $("#resetTo-"+dktEventId).focus();
			  }
			  
			  if( resultId == "REL" ){
				  $("#resetToDate-"+dktEventId).val('')
			  }
			  
		  }
		  if( allowSubmit ){
			  
			  if( typeof( paginationPageInput )!="undefined" ){
					localStorage.setItem("currentPageNum", paginationPageInput.value);
				  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
				  	 $("#pagerOffset").val(offset);
			  }	
			  $("#boolResultOnly").val(true); //set it on the form
			  $("#selectedVal").val( resultId ); //set it on the form
			  $("#tempDocketEventId").val(dktEventId); //set it in the form
			  $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Update Results');
			  $('form').submit();	
		  }
		  
	 });
	  $("input[id^='transTo-']").on('click', function (e) {
		  	var $obj = this.id;
		  	var docketEventId = $obj.split('-')[1];
		  	var trnValue = $("#TRNValue-"+docketEventId).val();
		  	if(trnValue=="1")
		  		$("#TRN-"+docketEventId).prop("checked", true);
		  	var atyConfirm= document.getElementById('atyConfirmation-'+docketEventId).value;	
			if(atyConfirm!="YES")
		    {		
				document.getElementById('attnyConfm.'+docketEventId).disabled=false;	
				if(document.getElementById('attnyBypass.'+docketEventId))
					document.getElementById('attnyBypass.'+docketEventId).disabled=false;
		    }	
		});
	  $("input[id^='transTo-']").on('change', function( e ) {
		  
		      var allowSubmit = true;
		  	  var $obj = this.id;
			  var dktEventId = $obj.split('-')[1];
			  var validCrts = ['','300','313','314','315'];			  
			  var transferCourt = $("#transTo-"+dktEventId).val();
			  var trnValue = $("#TRNValue-"+dktEventId).val();
			  			  
			  if( jQuery.inArray( transferCourt, validCrts ) <= 0 ) {
				  
				  allowSubmit = false;
				  alert("Invalid Transfer Court - CORRECT AND CONTINUE.");
				  $("#transTo-"+dktEventId).focus();
				  return false;
			  }			  
			  if(trnValue=="1")
			  		$("#TRN-"+dktEventId).prop("checked", true);
			  if( allowSubmit ){
				  
				  if( typeof( paginationPageInput )!="undefined" ){
						localStorage.setItem("currentPageNum", paginationPageInput.value);
					  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
					  	 $("#pagerOffset").val(offset);
				  }	
				  $("#selectedVal").val( transferCourt ); //set it on the form
				  $("#tempDocketEventId").val(dktEventId); //set it in the form
				  $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Transfer Out&trn='+trnValue);
				  $('form').submit();
			  }
			  
		});
	  $("input[id^='interpreter']").on('change', function (e) {
		  var allowSubmit = true;
	  	  var $obj = this.id;
		  var dktEventId = $obj.split('.')[1];
		  var newinterpreter;
			if(document.getElementById('interpreter.'+dktEventId).checked== true)
			{
				newinterpreter="1";
			} 
			else 
			{
				newinterpreter="0";
			}
			document.getElementById('interpreter-'+dktEventId).value=newinterpreter;	
			//
			if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
			}
		   $("#tempDocketEventId").val(dktEventId); //set it in the form
		   $("#selectedVal").val( newinterpreter ); //set it on the form
		   $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Update Interpreter');
		   $('form').submit();
		});
	  $("input[id^='attorneyName-']").on("change", function( e ) {
		  var $obj = this.id;
		  var dktEventId = $obj.split('-')[1];
		  var oldattorneyName = $("#oldAttorney-"+dktEventId).val();
		  var attorneyName = $("#attorneyName-"+dktEventId).val();
		  var atyConfirm= document.getElementById('atyConfirmation-'+dktEventId).value;
		  if(oldattorneyName!=attorneyName)			  
		  {
		  	document.getElementById('attnyConfm.'+dktEventId).disabled=false;
		  	if(document.getElementById('attnyBypass.'+dktEventId))
		  		document.getElementById('attnyBypass.'+dktEventId).disabled=false;
		  }			    
	  });
	  $("input[id^='barNum-']").on("click", function( e ) {
		  var $obj = this.id;
		  var dktEventId = $obj.split('-')[1];
		  var atyConfirm= document.getElementById('atyConfirmation-'+dktEventId).value;
		  document.getElementById('attnyConfm.'+dktEventId).disabled=false;
		  if(document.getElementById('attnyBypass.'+dktEventId))
			  document.getElementById('attnyBypass.'+dktEventId).disabled=false;
	  });
	  	  
	  $("input[id^='resetToDate-']").on('click', function (e) {
		  	var $obj = this.id;
		  	var docketEventId = $obj.split('-')[1];
		  	var trnValue = $("#TRNValue-"+docketEventId).val();
		  	if(trnValue=="1")
		  		$("#TRN-"+docketEventId).prop("checked", true);
		  	var atyConfirm= document.getElementById('atyConfirmation-'+docketEventId).value;	
			if(atyConfirm!="YES")
		    {		
				document.getElementById('attnyConfm.'+docketEventId).disabled=false;
				if(document.getElementById('attnyBypass.'+docketEventId))
					document.getElementById('attnyBypass.'+docketEventId).disabled=false;
		    }	
		});
		  
	  $("input[id^='resetToDate-']").on("change", function( e ) {
			  var $obj = this.id;
			  var dktEventId = $obj.split('-')[1];
			  var prevResetTo = $("#prevResetTo-"+dktEventId).val();
			  var resetTo = $("#resetToDate-"+dktEventId).val();
			  var trnValue = $("#TRNValue-"+dktEventId).val();	
			  var atyConfirm= document.getElementById('atyConfirmation-'+dktEventId).value;
			  if(atyConfirm!="YES")
			    {				  
					if(prevResetTo!=resetTo)			  
			  		{
			  			document.getElementById('attnyConfm.'+dktEventId).disabled=false;
			  			if(document.getElementById('attnyBypass.'+dktEventId))
			  				document.getElementById('attnyBypass.'+dktEventId).disabled=false;
			  		}	
			    }
			  if( typeof( resetTo )!= "undefined" && resetTo != ''){
				 
				  var valid = ValidateDate(resetTo);
				  if(!valid){
					 // format date
					  $("#resetToDate-"+dktEventId).val(prevResetTo);
					  alert( "RESET DATE MUST BE IN MM/dd/yyyy FORMAT.");
					  return false;	
					  //resetTo = resetTo.substring(0, 2) + '/' + resetTo.substring(2, 4) + '/' + resetTo.substring(4, 8);
				  }
			  }
			 
			  var transferCourt = $("#transTo-"+dktEventId).val();
			  //if courtId=300, weekends are holidays can be selected
			 if ( transferCourt != "300" && resetTo !== '' ) {
				 validateResetDateByCourtId( resetTo ,countyHolidayList );
			 }
			 
			 var juvenileName = $("#juvName-"+dktEventId).val();
			 if( juvenileName.indexOf( "JUVENILE NOT") != -1){
				  
				  allowSubmit = false;
				  alert( "RESET DATE CHANGE NOT ALLOWED");
				  return false;
			  }
			  
			  var resultId = $("#courtResult-"+dktEventId).val();
			  var courtDate = $("#date").val();
			  
			  if( transferCourt == '' && courtDate == resetTo ){	
				  $("#transTo-"+dktEventId).focus();
				  $("#resetToDate-"+dktEventId).val(prevResetTo);
				  alert( "CANNOT RESET TO CURRENT DATE AND COURT.");
				  return false;				  
			  }

			  if( typeof( paginationPageInput )!="undefined" ){
					localStorage.setItem("currentPageNum", paginationPageInput.value);
				  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
				  	 $("#pagerOffset").val(offset);
			  }			  
			  if(trnValue=="1")
			  		$("#TRN-"+dktEventId).prop("checked", true);
			  $("#selectedVal").val( resetTo ); //set it on the form
			  $("#tempDocketEventId").val(dktEventId); //set it in the form
			  $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Update Link&trn='+trnValue);
			  $('form').submit();
		});
	//on click of attorney Bypass button
	  $("input[name^='attorneyBypass']").on('click', function (e) 
	  {		  
		  var buttonId = this.id;
	 	  var dktEventId = buttonId.split('.')[1];	 	  
	 	  //alert(document.getElementById('atyBypass-'+dktEventId).value)
	 	 
	 	  var count = 0;
		    var ps = document.getElementsByName("pager.offset");
			var elementIndex = $(ps).val();
		 	var allowSub = true;
			$("#submitBtn").prop('disabled', true);
			$("#selectedDockets").val('');
			//var buttonId = this.id;
	 	    //var dktEventId = buttonId.split('.')[1];
	 	    var prevResetTo = $("#prevResetTo-"+dktEventId).val();
	 	    var resetTo = $("#resetToDate-"+dktEventId).val();
			var transferTo = $("#transTo-"+dktEventId).val();
			var result = $("#courtResult-"+dktEventId).val();
			var prevResult = $("#prevCourtResult-"+dktEventId).val();
			var resultId = $("#courtResult-"+dktEventId).val();
			var courtDate = $("#date").val();					 
					  // Create date from input value
			var newResetDate = formatDate(resetTo,0);
			var newCourtDate = formatDate(courtDate,0);
			var prevResetDate = formatDate(prevResetTo, 0);										 
			var courtDate18 = new Date(courtDate);
			courtDate18.setDate( courtDate18.getDate() + 18 );
			courtDate18 = formatDate(courtDate18, 0);
			
					 // check for a change
			if( resetTo != prevResetTo || result != prevResult  )
			{					 
				//Detention Court Decisions				
				var decisionCodes = $("#courtDecisions").val();
				$.each(jQuery.parseJSON(decisionCodes), function(idx, obj) {				 		 		
				var code = obj.code;
				var action = obj.action;
				var decision = obj.decision;
				var resetAllowed = obj.allowReset;
				var trnValue= $("#TRNValue-"+dktEventId).val();				
				
					 		if( resultId == code )
					 		{
					 			if( trnValue == '1' && resetAllowed != "N")
				 				{//change for checkbox
				 					
				 					if( transferTo == ''){
				 						
				 						allowSub = false;
						 				$("#transTo-"+dktEventId).focus();
										alert( "TRANSFER COURT MUST BE POPULATED - CORRECT AND CONTINUE.");
										return false;
				 					}
				 					if( resetTo == ''){
						 				
						 				allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "RESET DATE MUST BE ENTERED - CORRECT AND CONTINUE.");
										return false;
						 			}
				 				}
					 			//alert( code + ":" + resetAllowed);
					 			if( resetTo == '' && resetAllowed == "R")
					 			{
					 				
					 				allowSub = false;
					 				$("#resetToDate-"+dktEventId).focus();
									alert( "RESET DATE MUST BE ENTERED - CORRECT AND CONTINUE.");
									return false;
					 			}
					 			
					 			if( action == 'DETAINED' )
					 			{
					 				var cnt = 0;
					 				cnt=CalculateHolidaysNWeekends( courtDate,resetTo ,countyHolidayList );
					 				//alert("cnt="+cnt)
					 				var courtDate10 = new Date(courtDate);
					 				courtDate10.setDate( courtDate10.getDate() + 10 + cnt );//10+weekend+holidays
					 				courtDate10 = formatDate(courtDate10, 0);
					 				if( resetTo != '' && newResetDate == newCourtDate){
					 					
					 					allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
										return false;
					 				}
					 				
					 				if( newResetDate >= courtDate10 ){
					 					
					 					allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "Reset date cannot be more than 10 business days beyond the current court date.");
										return false;
					 				}
					 				
					 				
					 			}
					 			else if( action == 'OFF DOCKET/RESET')
					 			{
					 				
					 				//if( code == 'TRN' )//change to checkbox
					 				if( trnValue == '1' ) 
					 				{
					 					
					 					if( transferTo == '')
					 					{
					 						
					 						allowSub = false;
							 				$("#transTo-"+dktEventId).focus();
											alert( "TRANSFER COURT MUST BE POPULATED - CORRECT AND CONTINUE.");
											return false;
					 					}
					 					if( newResetDate >= courtDate18 )
					 					{
						 					
						 					allowSub = false;
							 				$("#resetToDate-"+dktEventId).focus();
											alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
											return false;
						 				}
					 					
					 				}
					 				
					 				if( code == 'A/R' )
					 				{					 					
					 					if( newResetDate >= courtDate18 )
					 					{
						 					
						 					allowSub = false;
							 				$("#resetToDate-"+dktEventId).focus();
											alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
											return false;
						 				}					 					
					 				}
					 			}//else if( action == 'OFF DOCKET/RESET')		
					 		}//if( resultId == code )
				 		});//$.each
					 		 		
					 }//if( resetTo != prevResetTo || result != prevResult  )
			 var elementName = $(this).attr('name');		
			 var elementIndex = elementName.split('[')[1].split("]")[0];
			 var attorneyConn;
			 $("#selectedDockets").val(dktEventId);
	  	      if(document.getElementById('attyConn1-'+dktEventId).checked== true)
			  {
				 attorneyConn='HAT';
			  }
			 else if(document.getElementById('attyConn2-'+dktEventId).checked== true)
			  {
				  attorneyConn='AAT';
			  }
			 else if(document.getElementById('attyConn3-'+dktEventId).checked== true)
			  {
				  attorneyConn='PDO';
			  }
			 else
			 {
				 attorneyConn='';
			 }
	  	    var oldattorneyName = $("#oldAttorney-"+dktEventId).val();
			var attorneyName = $("#attorneyName-"+dktEventId).val();
			var oldattorneyConn=document.getElementById('oldattorneyConn-'+dktEventId).value;
			if(oldattorneyName!=attorneyName||attorneyConn!=oldattorneyConn)			  
			{
				allowSub = false;
 				alert( "ATTORNEY DETAILS ARE MODIFIED,PLEASE CONFIRM ATTORNEY.");
				return false;
			}
			if(allowSub)
			 {	
				 if( typeof( paginationPageInput )!="undefined"){
			  			localStorage.setItem("currentPageNum", paginationPageInput.value);
			  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
						$("#pagerOffset").val(offset);
		  		   }
				 if(document.getElementById('attnyBypass.'+dktEventId))
				 {
					 document.getElementById('atyBypass-'+dktEventId).value='YES';
					 document.getElementById('attnyBypass.'+dktEventId).disabled=true;
				 }
			 	  document.getElementById('attnyConfm.'+dktEventId).disabled=true;
			 	  spinner();
				 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney Bypass&docketEventId='+dktEventId);
				 /*$('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney Confirmation&docketEventId='+dktEventId+'&coc='+attorneyConn);
			  	 */
				 $('form').submit();
			 }
	 	 
	  });	  
	  
	//on click of attorney Confirm button
	  $("input[name^='attorneyConfirm']").on('click', function (e) 
	  {
		    var count = 0;
		    /*var ps = document.getElementsByName("pager.offset");
			var elementIndex = $(ps).val();*/
		 	var allowSub = true;
			$("#submitBtn").prop('disabled', true);
			$("#selectedDockets").val('');
			var buttonId = this.id;
	 	    var dktEventId = buttonId.split('.')[1];
	 	    var prevResetTo = $("#prevResetTo-"+dktEventId).val();
	 	    var resetTo = $("#resetToDate-"+dktEventId).val();
			var transferTo = $("#transTo-"+dktEventId).val();
			var result = $("#courtResult-"+dktEventId).val();
			var prevResult = $("#prevCourtResult-"+dktEventId).val();
			var resultId = $("#courtResult-"+dktEventId).val();
			var courtDate = $("#date").val();					 
					  // Create date from input value
			var newResetDate = formatDate(resetTo,0);
			var newCourtDate = formatDate(courtDate,0);
			var prevResetDate = formatDate(prevResetTo, 0);										 
			var courtDate18 = new Date(courtDate);
			courtDate18.setDate( courtDate18.getDate() + 18 );
			courtDate18 = formatDate(courtDate18, 0);	
			
					 // check for a change
			if( resetTo != prevResetTo || result != prevResult  )
			{					 
				//Detention Court Decisions
				var decisionCodes = $("#courtDecisions").val();
				$.each(jQuery.parseJSON(decisionCodes), function(idx, obj) {				 		 		
				var code = obj.code;
				var action = obj.action;
				var decision = obj.decision;
				var resetAllowed = obj.allowReset;
				var trnValue= $("#TRNValue-"+dktEventId).val();
				
					 		if( resultId == code )
					 		{
					 			if( trnValue == '1' && resetAllowed != "N")
				 				{//change for checkbox
				 					
				 					if( transferTo == ''){
				 						
				 						allowSub = false;
						 				$("#transTo-"+dktEventId).focus();
										alert( "TRANSFER COURT MUST BE POPULATED - CORRECT AND CONTINUE.");
										return false;
				 					}
				 					if( resetTo == ''){
						 				
						 				allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "RESET DATE MUST BE ENTERED - CORRECT AND CONTINUE.");
										return false;
						 			}
				 				}
					 			//alert( code + ":" + resetAllowed);
					 			if( resetTo == '' && resetAllowed == "R")
					 			{
					 				
					 				allowSub = false;
					 				$("#resetToDate-"+dktEventId).focus();
									alert( "RESET DATE MUST BE ENTERED - CORRECT AND CONTINUE.");
									return false;
					 			}
					 			
					 			if( action == 'DETAINED' )
					 			{
					 				var cnt = 0;
					 				cnt=CalculateHolidaysNWeekends( courtDate,resetTo ,countyHolidayList );
					 				//alert("cnt="+cnt)
					 				var courtDate10 = new Date(courtDate);
					 				courtDate10.setDate( courtDate10.getDate() + 10 + cnt );//10+weekend+holidays
					 				courtDate10 = formatDate(courtDate10, 0);
					 				if( resetTo != '' && newResetDate == newCourtDate){
					 					
					 					allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
										return false;
					 				}
					 				
					 				if( newResetDate >= courtDate10 ){
					 					
					 					allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
						 				alert( "Reset date cannot be more than 10 business days beyond the current court date.");
										return false;
					 				}
					 				
					 				
					 			}
					 			else if( action == 'OFF DOCKET/RESET')
					 			{
					 				
					 				//if( code == 'TRN' )//change for checkbox
					 				if( trnValue == '1' )
					 				{
					 					
					 					if( transferTo == '')
					 					{
					 						
					 						allowSub = false;
							 				$("#transTo-"+dktEventId).focus();
											alert( "TRANSFER COURT MUST BE POPULATED - CORRECT AND CONTINUE.");
											return false;
					 					}
					 					if( newResetDate >= courtDate18 )
					 					{
						 					
						 					allowSub = false;
							 				$("#resetToDate-"+dktEventId).focus();
											alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
											return false;
						 				}
					 					
					 				}
					 				
					 				if( code == 'A/R' )
					 				{					 					
					 					if( newResetDate >= courtDate18 )
					 					{
						 					
						 					allowSub = false;
							 				$("#resetToDate-"+dktEventId).focus();
											alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
											return false;
						 				}					 					
					 				}
					 			}//else if( action == 'OFF DOCKET/RESET')		
					 		}//if( resultId == code )
				 		});//$.each
					 		 		
					 }//if( resetTo != prevResetTo || result != prevResult  )
			 var elementName = $(this).attr('name');		
			 var elementIndex = elementName.split('[')[1].split("]")[0];
			 var attorneyConn;
			 $("#selectedDockets").val(dktEventId);
	  	      if(document.getElementById('attyConn1-'+dktEventId).checked== true)
			  {
				 attorneyConn='HAT';
			  }
			 else if(document.getElementById('attyConn2-'+dktEventId).checked== true)
			  {
				  attorneyConn='AAT';
			  }
			 else if(document.getElementById('attyConn3-'+dktEventId).checked== true)
			  {
				  attorneyConn='PDO';
			  }
			 else
			 {
				 attorneyConn='';
			 }	
			 if(allowSub)
			 {	
				 if( typeof( paginationPageInput )!="undefined"){
						localStorage.setItem("currentPageNum", paginationPageInput.value);
			  			$("#pagerOffset").val(document.getElementsByName('pagerSearch')[0].value);
						$("#pagerOffset").val(offset);
		  		   }
				 spinner();
				 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Attorney Confirmation&docketEventId='+dktEventId+'&coc='+attorneyConn);
			  	 $('form').submit();
			 }		
	  });
	  
	//call back function.validate on click of submit and press of enter key
	 var update = function(){	 
				 
		 	var count = 0;
		 	var allowSub = true;
			var $docketIds = new Array();
			$("#submitBtn").prop('disabled', true);
			$("#selectedDockets").val('');
			$("input:checkbox").each(function(){//
			   $docketIds.push($(this).val());
			   count++;
			});
			if( count > 0 ){
				$.each( $docketIds, function( index, value ) {
					 var dktEventId = value;
					 var prevResetTo = $("#prevResetTo-"+dktEventId).val();
					 var resetTo = $("#resetToDate-"+dktEventId).val();
					 var transferTo = $("#transTo-"+dktEventId).val();
					 var result = $("#courtResult-"+dktEventId).val();
					 var prevResult = $("#prevCourtResult-"+dktEventId).val();
					 var resultId = $("#courtResult-"+dktEventId).val();
					 var courtDate = $("#date").val();
					 
					  // Create date from input value
					 var newResetDate = formatDate(resetTo,0);
					 var newCourtDate = formatDate(courtDate,0);
					 var prevResetDate = formatDate(prevResetTo, 0);
										 
					 var courtDate18 = new Date(courtDate);
					 courtDate18.setDate( courtDate18.getDate() + 18 );
					 courtDate18 = formatDate(courtDate18, 0);
					 
					 // check for a change
					 if( resetTo != prevResetTo || result != prevResult  ){
						 
						//Detention Court Decisions
						var decisionCodes = $("#courtDecisions").val();
				 		$.each(jQuery.parseJSON(decisionCodes), function(idx, obj) {
				 		 		
					 		var code = obj.code;
					 	   	var action = obj.action;
					 	   	var decision = obj.decision;
					 	   	var resetAllowed = obj.allowReset;
					 	   	var trnValue= $("#TRNValue-"+dktEventId).val();
					 	   	
					 		if( resultId == code ){
					 			
					 			if( trnValue == '1' && resetAllowed != "N")
				 				{//change for checkbox
				 					
				 					if( transferTo == ''){
				 						
				 						allowSub = false;
						 				$("#transTo-"+dktEventId).focus();
										alert( "TRANSFER COURT MUST BE POPULATED - CORRECT AND CONTINUE.");
										return false;
				 					}
				 					if( resetTo == ''){
						 				
						 				allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "RESET DATE MUST BE ENTERED - CORRECT AND CONTINUE.");
										return false;
						 			}
				 				}
					 			//alert( code + ":" + resetAllowed);
					 			if( resetTo == '' && resetAllowed == "R"){
					 				
					 				allowSub = false;
					 				$("#resetToDate-"+dktEventId).focus();
									alert( "RESET DATE MUST BE ENTERED - CORRECT AND CONTINUE.");
									return false;
					 			}
					 			
					 			if( action == 'DETAINED' ){
					 				var cnt = 0;
					 				cnt=CalculateHolidaysNWeekends( courtDate,resetTo ,countyHolidayList );
					 				//alert("cnt="+cnt)
					 				var courtDate10 = new Date(courtDate);
					 				courtDate10.setDate( courtDate10.getDate() + 10 + cnt );//10+weekend+holidays
					 				courtDate10 = formatDate(courtDate10, 0);
					 				if( resetTo != '' && newResetDate == newCourtDate){
					 					
					 					allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
										alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
										return false;
					 				}
					 				
					 				if( newResetDate >= courtDate10 ){
					 					
					 					allowSub = false;
						 				$("#resetToDate-"+dktEventId).focus();
						 				alert( "Reset date cannot be more than 10 business days beyond the current court date.");
										return false;
					 				}
					 				
					 				
					 			}else if( action == 'OFF DOCKET/RESET'){
					 				
					 				//if( code == 'TRN' )
					 				if( trnValue == '1' )
					 				{//change for checkbox
					 					
					 					if( transferTo == ''){
					 						
					 						allowSub = false;
							 				$("#transTo-"+dktEventId).focus();
											alert( "TRANSFER COURT MUST BE POPULATED - CORRECT AND CONTINUE.");
											return false;
					 					}
					 					if( newResetDate >= courtDate18 ){
						 					
						 					allowSub = false;
							 				$("#resetToDate-"+dktEventId).focus();
											alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
											return false;
						 				}
					 					
					 				}
					 				
					 				if( code == 'A/R' ){					 					
					 					if( newResetDate >= courtDate18 ){
						 					
						 					allowSub = false;
							 				$("#resetToDate-"+dktEventId).focus();
											alert( "RESET DATE OUTSIDE OF PERMISSIBLE LIMITS.");
											return false;
						 				}					 					
					 				}
					 			}		
					 		}
				 		});
					 		 		
					 }	
				});
				 
				
				if( allowSub ){
					if( typeof( paginationPageInput )!="undefined" ){
						localStorage.setItem("currentPageNum", paginationPageInput.value);
					  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
					 }
					 $("#selectedDockets").val( $docketIds );
					 spinner();
					 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Submit');
					 $('form').submit();	
				}				
			}				 
			
	}//End of Update function
	 
	 var deleteRecord = function(){

			var count = 0;
			var _docketIds = new Array();
			//var docketEventIds = new Array();
			$("input:checkbox:checked").each(function(){
				_docketIds.push($(this).val());
				count++;
			});
			
			if( count > 0 ){
				//alert("Deleting Future Calendar Record");	
				if( typeof( paginationPageInput )!="undefined" ){
					localStorage.setItem("currentPageNum", paginationPageInput.value);
				  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
				 }
				$("#selectedDockets").val( _docketIds );
				spinner();
				$('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Delete Setting');
				$('form').submit();

			}else{			
				alert("Please Select Checkbox to Delete Record(s)!");
				return( false );
			}    		
		}
	 
	 	function formatToday() {
	 		
	 		var formatTD = new Date().toISOString().substr(0, 10);	 		
	 		var dateToday = formatTD.replace(/-/g, "");
	 		
		    return dateToday;
		}
	 	
	 	function formatDate(date,add) {
		 	
	 		var newStr = '';
	 		if( date > ''){
	 			
	 			var tempDate = new Date(date).toISOString().substr(0, 10);
		 		newStr = tempDate.replace(/-/g, "");
	 		}	 		
		 	return newStr;
	 	}
	 	
	 	function CalculateHolidaysNWeekends(courtDate,resetDate,holidayList) {
		 	
	 		var cnt = 0;
	 		var newdate = new Date(courtDate);
	 		var resettoDate =new Date(resetDate);
	 		while(newdate<=resettoDate)
	 		{
	 			var dayOfWeek = newdate.getDay();
	 		    var isWeekend = (dayOfWeek ==0) || (dayOfWeek == 6);
	 		    if (isWeekend){
	 		    	cnt++;	
	 		    }
	 		   newdate.setDate( newdate.getDate() + 1 );
	 		}
	 		var formattedResetDate = formatDate(resetDate,0);
			var formattedCourtDate = formatDate(courtDate,0);
	 		$.each(jQuery.parseJSON(holidayList), function(idx, obj) {
		 		var holidayYear = (obj.code).substring(0,4);
		 		var holidayMonth = (obj.code).substring(4,6);
		 		var holidayDay = (obj.code).substring(6,8);
		 		var holidayDate = new Date(holidayMonth+"/"+holidayDay+"/"+holidayYear);		 		
		 		var formattedholidayDate = formatDate(holidayDate,0);
		 		if( formattedCourtDate <= formattedholidayDate && formattedResetDate >= formattedholidayDate)
		 			cnt++;
		 		
			});
	 			
		 	return cnt;
	 	}
	 	/**
	 	  * validateResetDateByCourtId
	 	  * @param courtDate
	 	  * added for JUV Detention Hearing Conversion
	 	  */
	 	 function validateResetDateByCourtId( resetDate,holidayList ){
	 		 if ( resetDate!="" ){
	 			 var selectedDate = new Date(resetDate);
	 		 	 var msg = "";
	 		 	 
	 		 	 //holiday check
	 		 	$.each(jQuery.parseJSON(holidayList), function(idx, obj) {
	 		 		var holidayYear = (obj.code).substring(0,4);
	 		 		var holidayMonth = (obj.code).substring(4,6);
	 		 		var holidayDay = (obj.code).substring(6,8);
	 		 		var holidayDate = new Date(holidayMonth+"/"+holidayDay+"/"+holidayYear);
	 		 	
	 		 		if( holidayDate.getTime()== selectedDate.getTime() ){
	 		 			msg = "THIS RESET DATE IS A HOLIDAY.";
	 		 		}
	 			});
	 		 	
	 		    //sat and sun check 
	 		    var dayOfWeek = selectedDate.getDay();
	 		    switch( dayOfWeek ) {
	 		    case 0:
	 		    	msg = "THIS RESET DATE IS A SUNDAY.";
	 		        break;
	 		    case 6:
	 		    	msg = "THIS RESET DATE IS A SATURDAY.";
	 		        break;
	 		    }	
	 		    
	 		 	 if( msg != "" ){
	 		 		if( typeof( paginationPageInput )!="undefined" ){
						localStorage.setItem("currentPageNum", paginationPageInput.value);
					  	$("#pagerOffset").val($("input[name='pagerSearch']").val());
					 }
	 		 		 alert(msg);
	 		 		 //resetDate.value(""); //clear the value to avoid focus issue.
	 		 		 //resetDate.focus();
	 		 		 return false;
	 		 	 }
	 		 } 
	 	 }
	 	 
	 	function ValidateDate(dtValue)
		{
			//var dtRegex = new RegExp(/\b\d{1,2}[\/]\d{1,2}[\/]\d{4}\b/);
	 		var match = /^(\d{2})\/(\d{2})\/(\d{4})$/.exec( dtValue );
			//return dtRegex.test(dtValue);
	 		return match;
		}

});