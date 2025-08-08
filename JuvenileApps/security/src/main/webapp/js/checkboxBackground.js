<!-- JavaScript - Checkbox Background Color Change -->
<script language=javascript>
	function chooseRow(chk) {
		//alternating row scriptlet - first row (RecordCounter) is 1
		var	num = Number(chk.value);

		//create row id
		id = "row" + num;

		var thisRow = document.getElementById(id);

		//checkbox is checked - change background color	- check if alternating row
		if (chk.checked){
			thisRow.className = "selectedRow";
		}else if(num % 2 == 1){
			thisRow.className = "normalRow";
				}else{
					thisRow.className = "alternateRow";
					}
	}

	//called onload to check if checkboxes are checked and set color
	function maintainChks(){
		//get form which checkboxes are in
	  elem = document.forms[0];

	  for(i=0; i<elem.length; i++) {
	    if(elem[i].type == "checkbox"){
	    	if(elem[i].checked){
	    		var	num = Number(elem[i].value);
	    		var id = "row" + num;
	    		document.getElementById(id).className = "selectedRow";
	    	}
	    }
	  }
	}
</script>