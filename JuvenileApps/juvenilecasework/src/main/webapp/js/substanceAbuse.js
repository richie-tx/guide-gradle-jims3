$(document).ready(function(){
	
		$("#addSubstanceAbuse").click(function(){
			if ( validateFields() ) {
				saveData();
				spinner();
			} else {
				return false;
			}
		});
		
		$("#finish").click(function(){
			spinner();
			removeData();
		});
		
		$("#substanceAbuse").on('change', function(){
			
			var substanceAbuse  = $("#substanceAbuse").val();
		
			if( substanceAbuse == "T"){
				
				$("#treatmentLoc").show();
				
			}else{
				$("#treatmentLoc").hide();
			}
			
			if ( substanceAbuse != "N"
				&& substanceAbuse != "U") {
				$("#substancesType").prop("disabled", false);
			} else {
				$("#substancesType").val("");
				$("#substancesType").prop("disabled", true);
			}
		});
		
		
	})
	
	
	function validateFields(){
		var associatedCasefile = $("#associatedCasefile").val();
		var substanceAbuse  = $("#substanceAbuse").val();
		var substancesType  = $("#substancesType").val();
		var treatmentLoc 	= $("#loc").val();
		
		
		if ( associatedCasefile == ""
				|| associatedCasefile.length == 0 ){
			alert("Associated Casefile # is required. ");
			return false;
		}
		
		if ( substanceAbuse == ""
				|| substanceAbuse.length == 0 ){
			alert("Substance Abuse is required. ");
			return false;
		}
		
		if ( substanceAbuse != "N"
			&& substanceAbuse != "U") {
			if ( substancesType == ""
					|| substancesType.length == 0 
				){
				alert("Substance Type is required. ");
				return false;
			}
		}
			
		
		
		if( substanceAbuse == "T" && treatmentLoc == "" ){			
			alert("Treatment Location is required when in treatment. ");
			return false;
		}
		
		return true;
	}
	
	
	function saveData(){
		localStorage.setItem("associatedCasefile", $("#associatedCasefile").val() );
		localStorage.setItem("referralNum", $("#referralNumber").html() );
		$("#referralNum").val( $("#referralNumber").html() );
		localStorage.setItem("substanceAbuse", $("#substanceAbuse").val() );
		localStorage.setItem("substancesType", $("#substancesType").val() );
		spinner();
	}
	
	function loadData(){
		if ( localStorage.getItem("associatedCasefile") != "" ) {
			$("#associatedCasefile").val( localStorage.getItem("associatedCasefile") );
		}
		
		if ( localStorage.getItem("referralNum") != "" ) {
			$("#referralNumber").html( localStorage.getItem("referralNum") );
		}
		
		if ( localStorage.getItem("substanceAbuse") != "" ) {
			$("#substanceAbuse").val( localStorage.getItem("substanceAbuse") );
		}
		
		if ( localStorage.getItem("substancesType") != "" ) {
			$("#substancesType").val( localStorage.getItem("substancesType") );
		}
		
		
	}
	
	function removeData(){
		localStorage.removeItem("associatedCasefile");
		localStorage.removeItem("referralNum");
		localStorage.removeItem("substanceAbuse");
		localStorage.removeItem("substancesType");
		
	}