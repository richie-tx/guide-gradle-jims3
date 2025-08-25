		function decodeEmailLists(aString,toSelect,ccSelect,bccSelect) {
			var arrLists = aString.split("&");
			if (toSelect) {
				clearSelect(toSelect);
				if (arrLists[0]) {
				 decodeEmailList(arrLists[0],toSelect);
				}
			}
			if (ccSelect) {
				clearSelect(ccSelect);
				if (arrLists[1]) {
				 decodeEmailList(arrLists[1],ccSelect);
				}
			}
			if (bccSelect) {
			    clearSelect(bccSelect);
				if (arrLists[2]) {
				 decodeEmailList(arrLists[2],bccSelect);
				}
			}
		}
		
		function decodeEmailList(aString,aSelect) {			
			var arrList = aString.split(";");
			for (i=0; i < arrList.length; i++) {
				decodeEmail(arrList[i],aSelect);
			}
		}
		
		function decodeEmail(aString,aSelect) {
		    
		    var arrList = aString.split(":");
			var lOption = document.createElement("OPTION");
			lOption.text = arrList[0];
			lOption.name = arrList[1];
			if (!emailExists(lOption, aSelect)) {
				aSelect.add(lOption,0);
			}
						
		}
		
		function clearSelect(aList) {
			aList.options.length = 0;				
			var newElem = document.createElement("OPTION")
			newElem.text = "                                                            "
			aList.add(newElem,0)						
		}		

		function encodeEmailLists(toSelect,ccSelect,bccSelect) {
			var lString = ""
			if (toSelect) {
				lString = lString + encodeEmailList(toSelect) + "&"
			}
			if (ccSelect) {
				lString = lString + encodeEmailList(ccSelect)+ "&"
			}
			if (bccSelect) {
				lString = lString + encodeEmailList(bccSelect)+ "&"
			}
		

			return lString;
		
		}

	    function encodeEmailList(aList) {
			var lString = ""
			for(k=0; k < aList.options.length - 1; k++) {
			    if (aList.options[k].name!= null && aList.options[k].name != "") {
					lString = lString + aList.options[k].text + ":" + aList.options[k].name + ";" 
				}
			}		
				
			return lString;
		}		

		function emailExists(anElement, aList){
			for(j=0; j < aList.options.length; j++) {
				if(aList.options[j].name==anElement.name) {
					return true
				}
			}
			return false;
		}
