$(document).ready(function () {
	
	//added for  U.S #23442
	$("#traitTypeIdAdd option[value='26']").remove(); // remove the facility traits option from the shopping cart
	if($("#traitTypeId").val()=='26' || $("[styleId^=26]") ){
		$("[styleId^=26]").hide();
	}
	//added for  U.S #23442
	
	$("#viewTraits").click(function(){
		var parentId = $("#traitTypeId").val();
		if(parentId == "" || parentId == "Please Select")
		{
			alert("Please Select a Trait Type");
			return false;
		}
		spinner();
		$("#traitTypeId").attr("disabled",false);
	});
	
	$("#utsbtn2,styleId,addMoreTraitsId").click(function(){
		spinner();
		disableSubmitButtonCasework($(this));
	});
	
	$("#utsbtn1").click(function(){
		button = $(this);
		var hasURLExtensions = (-1);
		var form = $(this).closest('form');
		var action = $(form).attr('action');
		var btnVal = button.val();
		var btnName = button.attr('name');
		var hasAmpersendInValue;
		
		hasAmpersendInValue = btnVal.indexOf( "&" );
		if( hasAmpersendInValue != (-1) )
		{
		     btnVal = btnVal.replace( /&/,"%26" );
		} 
		
		hasURLExtensions = action.indexOf( "?" );
		if( hasURLExtensions != (-1) )
		{
			action+="&"+btnName+"="+btnVal;
		}else{
			action+="?"+btnName+"="+btnVal;
		}
		$(form).attr('action',action);
		$(form).submit();
		
		button.prop('disabled',true);
		spinner();
		return false;
	});
	
	function enableTraitsUpdateButton(button){
		$("#utsbtn2,#utsbtn1").prop('disabled',false);
		return button;
	}
	
	//ends with trait id call update button
	$("[styleId$=traitId]").click(function(){
		enableTraitsUpdateButton($(this));
	});
});