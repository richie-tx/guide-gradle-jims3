//<!-- JavaScript - Trait Check -->
//modified to use JQuery

$(document).ready(function () {
	
	$("[name='currentTrait.traitComments']").on('keyup mouseout',function(){
		textLimit($(this),255);
		return false;
	});
	
	function validateTraitFields(theForm){
	
	   if (document.getElementById("currentTrait.memberTraitDescId']").val() == "") {
	      alert("Trait Type selection is required.");
	      document.getElementById("currentTrait.memberTraitDescId").focus();
	      return false;
	   }
	   if (document.getElementById("currentTrait.traitLevelId").val() == "") {
	      alert("Trait Level selection is required.");
	      document.getElementById("currentTrait.traitLevelId']").focus();
	      return false;
	   }
	   if (document.getElementById("currentTrait.traitStatusId").val() == "") {
	      alert("Trait Status selection is required.");
	      document.getElementById("currentTrait.traitStatusId']").focus();
	      return false;
	   }
	   return true;
	 }
	 
	 function validateMemberTraitFields(theForm){
	
	   if ($("[name='currentTrait.memberTraitDescId']").val() == "") {
	      alert("Trait Type selection is required.");
	      $("[name='currentTrait.memberTraitDescId']").focus();
	      return false;
	   }
	   if ($("[name='currentTrait.traitLevelId']").val() == "") {
	      alert("Trait Level selection is required.");
	      $("[name='currentTrait.traitLevelId']").focus();
	      return false;
	   }
	   if ($("[name='currentTrait.traitStatusId']").val() == "") {
	      alert("Trait Status selection is required.");
	      $("[name='currentTrait.traitStatusId']").focus();
	      return false;
	   }
	   return true;
	 }
});
