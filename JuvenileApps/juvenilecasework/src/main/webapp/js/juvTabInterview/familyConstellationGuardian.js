//Uses JQuery

$(document).ready(function (){
	
	var globalObjID;	
	// If only 1.default primary and guardian
	var ctr =$("[name='nestedMemberRow']").length;
	if( ctr ===1 ){
		
		 var results=$("input[id^='primaryContact-']");
		  $.each(results,function(index){ //loops through all the radio buttons.
			  var result = $(this).val();
			  var $obj = this.id;
			  var guardianId = $obj.split('-')[1];
			  globalObjID = guardianId;
			  $("#primaryContact-"+guardianId).prop('checked', 'checked');
			  $("#guardian-"+guardianId).prop('checked', 'checked');
		  });
	   }
	
	
	$("#validateSelectionInputs").on("click",function(){
		var el = $("[name='memberNum']");
		var result = $("#youthLivesWith").val();
		var elChecked = 0;
		var guardianChecked = 0;
		var cannotBeBoth = false;
		var elName = "";
		var msg = "";
		var primaryCareGiver;
		var primaryCareGiverChecked;
		var relation = "";
		
	if (el != null){		

		for (x=0; x<el.length; x++) {
				elName = "currentConstellation.memberList[" + x + "].primaryContact";
				fldName = $("[name='"+elName+"']");
				if ( fldName.length > 0 || typeof($(fldName).val()) != 'undefined') {
					if (fldName[0].checked == true)   // primary Guardian selected
					{
						elChecked = 1;
						$("#primaryContact-"+globalObjID).val("true");
						//$("[name='"+elName+"']").attr('value',true);
				    	break;
					}
				}
			}
			if (elChecked == 0){
				msg = "Primary Guardian selection is required.\n";
			} 

			
			var inputs=$("input[id^='primaryCareYES-']");
			  $.each(inputs,function(index){ //loops through all the radio buttons.
				  
				  var radioId = inputs[index].id;
				  var oid = radioId.split('-')[1];
				 	  
				  if( oid === globalObjID && inputs[index].checked ){ //checkBoxes[index].checked && 
					  
					  relation =  $("#relationship-"+oid).val();
					  
					  var primary=$("input[id^='primaryContact-']");
					  $.each(primary,function(index){ //loops through all the radio buttons.
						  
						  var ID = primary[index].id;
						  var memberId = ID.split('-')[1];
						 	  
						  if( oid === memberId && primary[index].checked ){	
							  
							  msg +='The ' + relation + ' was previously indicated as a primary care giver.  Selecting them as guardian means the ' + relation + ' cannot be primary caregiver \n';							  
						  }
						  
					  });
					  
					   inputs[index].checked = false;
					   document.getElementById("primaryCareNO-"+oid).checked = true;
				  }
				  
			  });
			  
			  var guardians=$("input[id^='guardian-']");
			  $.each(guardians,function(index){ //loops through all the radio buttons.
				  	
				  if( guardians[index].checked ){
					  
					  var OID = guardians[index].id;
					  var id = OID.split('-')[1];
					  
					  guardianChecked++;
					  
					  var pcYes=$("input[id^='primaryCareYES-']");
					  $.each(pcYes,function( index ){ //loops through all the radio buttons.
						  var ID = pcYes[index].id;
						  var member = ID.split('-')[1];
						  
						  if( id === member && pcYes[index].checked ){
							  
							  cannotBeBoth = true;
							  
						  }
					  });
				  }
			  });
			  
			if (guardianChecked == 0){
				msg += "At least 1 Guardian selection is required.\n";
			}
			if (guardianChecked > 2){
				msg += "No more than 2 Guardian selections allowed.\n";
			}
			if(cannotBeBoth){
				msg +='Primary caregiver(s) would be someone other than the juvenile\'s guardian.Please select primary caregiver other than juvenile\'s guardian \n';
			}
			if( result === ''){
				msg += "Youth Lives with selection is required.\n";				
			}
			
		}
		if (msg == ""){
			return true;
		}
		alert(msg);
		return false;
	});
	   
		  
  $("input[id^='primaryContact-']").on('click', function (e) {

	  var resultantId = this.id;
      var guardianId = resultantId.split('-')[1];
      
      var checkBoxes=$("input[id^='primaryContact-']");
	  $.each(checkBoxes,function(index){ //loops through all the radio buttons.
		  
		  //console.log(checkBoxes[index]);
		  //console.log(checkBoxes[index].checked);
		  
		  var radioId = checkBoxes[index].id;
		  var oid = radioId.split('-')[1];
		 	  
		  if(oid !== guardianId){ //checkBoxes[index].checked && 
			  checkBoxes[index].checked = false;  
			  document.getElementById("guardian-"+oid).checked = false;
		  }
		  
	  });
	  		
	  document.getElementById("guardian-"+guardianId).checked = true;
	  $("#pcMemNum").val(guardianId);
      globalObjID = guardianId;
     
	});
  
  $("input[id^='guardian-']").on('click', function (e) {
	  var guardianId = this.id;
	  var id = guardianId.split('-')[1];
	  if ( !$("#"+guardianId).is(":checked")
			  && $("#primaryContact-"+id).is(":checked") ) {
		  alert("Primary guardian has to be a guardian.");
		  $("#" + guardianId).prop("checked", true);
	  }
	  
  });
  
  $("input[id^='primaryCareYES-']").on('click', function (e) {
	  
	  var primaryCareGiverCtr=0;
	  var msg = "";
	  
      var checkBoxes=$("input[id^='primaryCareYES-']");
	  $.each(checkBoxes,function(index){ //loops through all the radio buttons.
			 	  
		  if( checkBoxes[index].checked ){ //checkBoxes[index].checked && 
				primaryCareGiverCtr++; 
		  }		  
	 });	  
	  
	  if( primaryCareGiverCtr>2 ){
			msg +='Please select max of two primary care givers.\n';
		}
	  
	  
	  if ( msg == "" ){
			return true;
		}
		alert(msg);
		return false;
     
	});
	
	
});

