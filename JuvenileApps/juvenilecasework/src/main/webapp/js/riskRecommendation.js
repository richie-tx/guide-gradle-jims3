function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
  		alert("Your input has been truncated to " + maxlen +" characters!");
	}
}
// These validations are repeated for Add and Update Recommendation 
function validateInput()
{
	var msg = "";
	var minScoreGood = false;
	var maxScoreGood = false;
//	var negativeNumericRegExp = /^-?[1-9][0-9]{0,2}$/;
	var negativeNumericRegExp = /^-?[0-9]{0,2}$/;
	var numericRegExp = /^[0-9]*$/;
	var textRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2f\-]*$/;
	var fld = document.getElementById("rNameId");
	fld.value = Trim(fld.value);
	if (fld.value == "") {
		msg = "Recommendation Name is required.\n";
		fld.focus();
	} 
	if (fld.value != "" && fld.value.length < 4) {
		msg = "Recommendation Name must be at least 4 characters.\n";
		fld.focus();
	}
	if (fld.value.length > 4){
		if (textRegExp.test(fld.value,textRegExp) == false) {
			msg = "Recommendation Name contains one or more invalid characters.\n";
			fld.focus();
		}
	}

	fld = document.getElementById("rTextId");
	fld.value = Trim(fld.value);
	if (fld.value == "") {
		if (msg == "") {
			fld.focus();
		}
		msg += "Recommendation Text is required.\n";
	} else {
		if (textRegExp.test(fld.value,textRegExp) == false) {
			if (msg == "") {
				fld.focus();
			}
			msg += "Recommendation Text contains one or more invalid characters.\n";
		}
	}
	
	fld = document.getElementById("minId");
	var minScore = Trim(fld.value);
	if (minScore == "") {
		if (msg == "") {
			fld.focus();
		}
		msg += "Min Score is required.\n";
	} else {
		if (negativeNumericRegExp.test(minScore,negativeNumericRegExp) == false || validNegative(minScore) == false) {
			if (msg == "") {
				fld.focus();
			}
			msg += "Min Score must be a number from -99 to 999.\n";
		} else {
			minScore = TrimZero(minScore,"min");
			fld.value = minScore;
			minScoreGood = true;
		} 
	}
	fld = document.getElementById("maxId");
	var maxScore = Trim(fld.value);
	if (maxScore == "") {
		if (msg == "") {
			fld.focus();
		}
		msg += "Max Score is required.\n";
	} else {
		if (numericRegExp.test(maxScore,numericRegExp) == false) {
			if (msg == "") {
				fld.focus();
			}
			msg += "Max Score must be a number from 0 to 999.\n";			
		} else {
			maxScore = TrimZero(maxScore,"max");
			fld.value = maxScore;
			maxScoreGood = true;			
		} 
	}
	if (minScoreGood && maxScoreGood) {
		if (parseInt(minScore) >= parseInt(maxScore)) {
			if (msg == "") {
				document.getElementById("minId").focus();
			}
			msg += "Min Score must be less than Max Score.\n";
		}
	}
	fld = document.getElementById("grpId");
	if (fld.selectedIndex == 0) {
		if (msg == "") {
			fld.focus();
		}
		msg += "Risk Result Group selection is required.";
	}
	if (msg == "") {
		return true;
	}
	alert(msg);
	return false;
}
function TrimZero(numStr, type)
{
	var numLen = numStr.length;
	if (numLen > 1) {
		var str = "";
		var char1 = numStr.substring(0,1);
		var char2 = numStr.substring(1,2);
		var char3 = "";
		if (numLen == 3) {
			char3 = numStr.substring(2,3); 
		}
		if (type == "min"){
			if (char1 != '0') {
				str = char1;
			}
			if (char1 == '-' && char2 != '0'){
				str += char2
			}
			if (char1 == '0') {
				str = char2;
			}
			if (char1 != '0' && char1 != '-') {
				str += char2;
			}
			if (char1 == "-"  && char3 != ""){
				str += char3;
			}
			if (char1 != '0' && char1 != '-') {
				str += char3;
			}
		}		
		if (type == "max") {
			if (char1 != "0") {
				str = numStr;
			} else {
				if (char2 != 0){
					str += char2;
				}
				if (numLen == 3) {
					str += char3;	
				}				
			}	
		}
		numStr = str;    
	}
	return numStr;
}
function validNegative(minScore)
{
	if (minScore == "-0" || minScore == "-00") {
		return false;
	}	
	return true;
}