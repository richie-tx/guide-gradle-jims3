function RSStandardInterface(tbElementName){
	this.tbName = tbElementName;
	this.getText = getText;
	this.setText = setText;
	function getText(){	
		var finalVal=null;
		if(window.tinyMCE){
			var editId = tinyMCE.selectedInstance.editorId;
			var inst = tinyMCE.getInstanceById(editId);
			if(inst){
				finalVal=inst.getBody().innerHTML;
			}
		}
		if(finalVal==null){
			var myFoundElem=document.getElementsByName(tbElementName)[0];
			finalVal=myFoundElem.value;
		}
		return finalVal;
	}
	function setText(text){	
		if(window.tinyMCE){
			var editId = tinyMCE.selectedInstance.editorId;
			var inst = tinyMCE.getInstanceById(editId);
			if(inst){
				inst.getBody().innerHTML=text;
			}
		} else {
			var myFoundElem=document.getElementsByName(tbElementName)[0];
			myFoundElem.value=text;
		}
	}
}
function escQuotes(text){  
	var rx = new RegExp("\"", "g"); 
	return text.replace(rx,"&#34;");
}

function escEntities(text){	
	var rx = new RegExp("&", "g"); 	
	text = text.replace(rx,"&amp;");	
	for(var i=161; i<=255; i++){		
		rx = new RegExp(String.fromCharCode(i), "g");		
		text = text.replace(rx, "&#"+i+";");	
	}
	return text;
}

function textLimit(field, maxlen) {
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
		alert("Your input has been truncated to " + maxlen +" characters!");	
	}
}

function popUpCheckSpellingspellCheckerButtonId(interfaceObjectName){  
	var spellBoot = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body onLoad='document.forms[0].submit();'><font face='arial, helvetica' size=2>Spell checking document...</font><form action='jsp/caseworkCommon/spellCheckPopUp.jsp' method='post' ACCEPT-CHARSET='UTF-8'>";  
	spellBoot += "<input type='hidden' name='textToCheck' value=\""+escQuotes(escEntities(eval(interfaceObjectName+'.getText()')))+"\"><input type='hidden' name='InterfaceObject' value='"+interfaceObjectName+"'><input type='hidden' name='mode' value='popup'><input type='hidden' name='SuggestionsMethod' value='HASHING_SUGGESTIONS'><input type='hidden' name='SeparateHyphenWords' value='false'>"; 
	spellBoot += "<input type='hidden' name='IncludeUserDictionaryInSuggestions' value='true'><input type='hidden' name='FinishedListener' value=''><input type='hidden' name='callBack' value=''><input type='hidden' name='IgnoreXML' value='true'><input type='hidden' name='IgnoreCapitalizedWords' value='false'>"; 
	spellBoot += "<input type='hidden' name='GuiLanguage' value='ENGLISH'><input type='hidden' name='LanguageParser' value='ENGLISH'><input type='hidden' name='Modal' value='false'><input type='hidden' name='AllowAnyCase' value='false'><input type='hidden' name='IgnoreWordsWithDigits' value='true'>"; 
	spellBoot += "<input type='hidden' name='ShowFinishedMessage' value='true'><input type='hidden' name='ShowNoErrorsMessage' value='true'><input type='hidden' name='ShowXMLTags' value='false'><input type='hidden' name='AllowMixedCase' value='false'><input type='hidden' name='RswlClientID' value='spellCheckerButtonId'><input type='hidden' name='WarnDuplicates' value='true'>"; 
	spellBoot += "<input type='hidden' name='DictFile' value=''><input type='hidden' name='PopUpWindowName' value=''><input type='hidden' name='CreatePopUpWindow' value='true'><input type='hidden' name='ConsiderationRange' value='80'></form></body></html>"; 

	var sc = window.open('', 'rspellwin', 'resizable=yes,scrollbars=auto,dependent=yes,toolbar=no,left=100,top=100,status=no,location=no,menubar=no,width=500,height=400'); 
	sc.focus(); 
	sc.document.open();  
	sc.document.write(spellBoot); 
	sc.document.close();
}