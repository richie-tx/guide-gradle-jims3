package mojo.util.spellcheck.web;


class JSProv
{

    JSProv()
    {
    }

    public static String getAddButtonInformJS(String s)
    {
        return "<script>function inform(){parent.document.forms[0].addButton.value='" + s + "';parent.document.forms[0].addButton.disabled=false;}window.onload=inform;</script>";
    }

    public static String getDocumentTextBoxStyleJS()
    {
        return " documentTextBoxStyle = \"    \\";
    }

    public static String getFinishFunctionJS(String s, String s1, boolean flag, boolean flag1)
    {
        String s2 = "function finish(closeWindow){if(mode == \"popup\"){if(callBack==\"\"){if (" + s + "." + s1 + ") " + s + "." + s1 + ".setText(text);" + "} else {" + "if(document.all){" + "eval('" + s + ".'+callBack+'.value= text');" + "} else{ eval('" + s + ".document.'+callBack+'.value= text') ;}" + "}";
        if(flag)
            s2 = s2 + "this.close();";
        else
        if(flag1)
            s2 = s2 + "if(closeWindow==true){this.close();} else { window.onunload=null;postFinishedEventToOpener();}";
        else
            s2 = s2 + "window.onunload=null;postFinishedEventToOpener();";
        s2 = s2 + "} else { document.forms[0].CorrectedText.value = text;document.forms[0].submit();}}";
        return s2;
    }

    public static String getFinishedMessageJS(String s)
    {
        return "alert(\"" + s + "\");";
    }

    public static String getFormOnSubmitJS()
    {
        return "document.forms[0].onsubmit=RSWonSubmit;function RSWonSubmit(){change(); return false;}";
    }

    public static String getGlobalFuncsJS(boolean flag, boolean flag1, String s, String s1, String s2, boolean flag2)
    {
        String s3 = "";
        if(!flag)
            s3 = s3 + "window.onload=refresh;";
        s3 = s3 + "window.onunload=windowClosing;";
        s3 = s3 + "function windowClosing(){";
        if(flag1)
            s3 = s3 + "if (" + s + "._rsPostBack" + s1 + ") " + s + "._rsPostBack" + s1 + "();";
        if(!s2.equals(""))
            s3 = s3 + "postFinishedEventToOpener();";
        s3 = s3 + "}\r\n";
        s3 = s3 + "    function postFinishedEventToOpener(){ ";
        if(!s2.equals(""))
            if(flag2)
                s3 = s3 + "if (" + s + "." + s2 + ") " + s + "." + s2 + "(spellCheckFinished);";
            else
                s3 = s3 + "if (" + s + "." + s2 + ") " + s + "." + s2 + "();";
        s3 = s3 + " }";
        return s3;
    }

    public static String getGlobalVarsJS(String s, boolean flag, String s1, String s2)
    {
        return "    \";mode='" + s + "';showXMLTags=" + (flag ? "false" : "true") + ";callBack='" + s1 + "';userDicFile='" + s2 + "'";
    }

    public static String getMainBodyJS(String s, boolean flag, boolean flag1, int i, String s1, String s2, String s3, String s4, 
            String s5, int j)
    {
        return "//Copyright 2002-2004 Keyoti Inc. - This code may not be redistributed without a purchased license.\nvar spellCheckFinished=false;var duplicateWord=false;var showXMLTags=true;var userDicFile='';var mode='popup';var callBack='';var currentWordIndex=0;var ignoreWordIndexes=new Object();var documentTextBoxStyle=\"<style></style>\";function change(){if(currentWordIndex<badWords.length){changeWord(currentWordIndex);nextWord();}}function changeAll() { if (currentWordIndex < badWords.length) { var currentWord = badWords[currentWordIndex].text; changeWord(currentWordIndex); for (var i = currentWordIndex + 1; i < badWords.length; i++) { if (!ignoreWordIndexes[i] && badWords[i].text == currentWord) { changeWord(i); ignoreWordIndexes[i] = true; } } nextWord(); }}function ignoreCurrent(){if(currentWordIndex<badWords.length){nextWord();}}function ignoreAll(){ if (currentWordIndex < badWords.length) { var currentWord = badWords[currentWordIndex].text; for (var i = currentWordIndex + 1; i < badWords.length; i++) {  if (!ignoreWordIndexes[i] && badWords[i].text == currentWord) {  ignoreWordIndexes[i] = true;  } } nextWord(); }}function changeSuggestions(){var suggestion=document.forms[0].suggestions.options[document.forms[0].suggestions.selectedIndex].text;if(suggestion!=\"" + s + "\"){document.forms[0].word.value=suggestion;}}" + "function nextWord(){while(currentWordIndex++<badWords.length&&ignoreWordIndexes[currentWordIndex]);if(currentWordIndex>=badWords.length){if(document.forms[0].changeButton!=null)document.forms[0].changeButton.disabled=true;if(document.forms[0].changeAllButton!=null)document.forms[0].changeAllButton.disabled=true;if(document.forms[0].ignoreButton!=null)document.forms[0].ignoreButton.disabled=true;if(document.forms[0].ignoreAllButton!=null)document.forms[0].ignoreAllButton.disabled=true;}refresh();}" + "function textToHtml(t){ if(showXMLTags){ var ltexp = new RegExp(\"<\"); while(ltexp.test(t))t = t.replace(ltexp, \"&lt;\"); \tvar gtexp = new RegExp(\">\");\twhile(gtexp.test(t)) t = t.replace(gtexp, \"&gt;\"); } else {} var newlineexp = new RegExp(\"" + (flag || flag1 ? "" : "\\n") + (flag ? "\\r" : "") + (flag1 ? "\\n" : "") + "\");while(newlineexp.test(t))t = t.replace(newlineexp, \"<br>\");return t; }\n" + "function refresh(){if(currentWordIndex<badWords.length){var html=\"<html>\";html+='<head>';html+=documentTextBoxStyle;html+=\"<\"+\"script TYPE='text/javascript'\"+\">\\n\";html+=\"function getPageCoords (el) {\\n\";html+=\"  var coords = {x: 0, y: 0};\\n\";html+=\"  do {\\n\";html+=\"    coords.x += el.offsetLeft;\\n\";html+=\"    coords.y += el.offsetTop;\\n\";html+=\"  }\\n\";" + "html+=\"  while ((el = el.offsetParent));\\n\";html+=\"  return coords;\\n\";html+=\"}\\n\";html+=\"function scrollIntoView(el) {\\n\";html+=\"  var coords = getPageCoords(el);\\n\";html+=\"  window.scrollTo (coords.x, coords.y);\\n\";html+=\"}\\n\";html+=\"<\"+\"/script\"+\">\";" + "html+='</head>';html+='<body marginwidth=4 marginheight=4 topmargin=4 leftmargin=4>';html+=ftr(textToHtml(text.substring(0,badWords[currentWordIndex].start))," + i + ");html+='<span id=\"highlight\" class=\"badWordHighlight\">';html+=ftr(text.substring(badWords[currentWordIndex].start,badWords[currentWordIndex].end)," + i + ");html+='</span>';html+=ftr(textToHtml(text.substring(badWords[currentWordIndex].end,text.length))," + i + ");html+='</body></html>';" + "documentTextPanel.document.open();documentTextPanel.document.write(html);documentTextPanel.document.close();documentTextPanel.scrollIntoView(documentTextPanel.document.getElementById(\"highlight\"));document.forms[0].suggestions.options.length=0;var n=badWords[currentWordIndex].suggestions.length;" + "if(n==0){document.forms[0].word.value=badWords[currentWordIndex].text;" + "document.forms[0].suggestions.options[0]=new Option(\"" + s + "\");" + "}else if (badWords[currentWordIndex].suggestions[0]==\"" + s1 + "\"){" + "\tdocument.forms[0].suggestions.options[0]=new Option(badWords[currentWordIndex].suggestions[0]);document.forms[0].suggestions.selectedIndex=0;document.forms[0].word.value='';duplicateWord=true;" + "}else{" + "\tdocument.forms[0].word.value=badWords[currentWordIndex].suggestions[0];" + "\tfor(var i=0;i<n;i++){" + "\t\tdocument.forms[0].suggestions.options[i]=new Option(badWords[currentWordIndex].suggestions[i]);" + "\t}" + "\tdocument.forms[0].suggestions.selectedIndex=0;duplicateWord=false;" + "}" + "document.forms[0].word.select();" + "}else{var html=\"\";html+='<head>';" + "html+=documentTextBoxStyle;html+='</head>';html+='<body marginwidth=4 marginheight=4 topmargin=4 leftmargin=4>';html+=textToHtml(text);html+='</body>';documentTextPanel.document.open();documentTextPanel.document.write(html);documentTextPanel.document.close();document.forms[0].word.value=\"\";document.forms[0].suggestions.options.length=0;document.forms[0].suggestions.options[0]=new Option(\"" + s + "\");if(badWords.length>0){" + s2 + "} else {" + s3 + "} \r\n spellCheckFinished=true;finish();\t}}" + "function changeWord(index){" + s4 + " var newText=\"\";if(duplicateWord && document.forms[0].word.value==''){badWords[index].start--;} newText+=text.substring(0,badWords[index].start);newText+=document.forms[0].word.value;newText+=text.substring(badWords[index].end,text.length);moveWordOffsets(document.forms[0].word.value.length-text.substring(badWords[index].start,badWords[index].end).length,index+1);text=newText;}function moveWordOffsets(delta,start){for(i=start;i<badWords.length;i++){badWords[i].start+=delta;badWords[i].end+=delta;}}" + "\nfunction addCurrent(){var currentWord = badWords[currentWordIndex].text;document.forms[0].addButton.value=\"" + s5 + "\";document.forms[0].addButton.disabled=true; addWordFrame.location.href=document.location.href + \"?fMessage=addWord&UserDictionaryFile=\" + escape(userDicFile) + \"&word=\" + badWords[currentWordIndex].e + \"&GuiLanguage=" + j + "\";ignoreAll();}\n" + " function ftr(tt, level){ switch(level){ case 1: return fsb( tt ); break; case 2: return feh( tt ); break; case 3: return ftr(ftr(tt,2),1); break; case 4: return fu( tt ); break; case 5: return ftr(ftr(tt,4),1); break; case 6: return ftr(ftr(tt,4),2); break; case 7: return ftr(ftr(tt,6),1); break; case 8: return fb(tt); break; case 9: return ftr(ftr(tt,8),1); break; case 10: return ftr(ftr(tt,8),2); break; case 11: return ftr(ftr(tt,10),1); break; case 12: return ftr(ftr(tt,8),4); break; case 13: return ftr(ftr(tt,12),1); break; case 14: return ftr(ftr(tt,12),2); " + "break; case 15: return fsb( feh( fu( fb(tt) ) ) ); break; default: return tt; break; } }  function fsb(tt){ var exp = new RegExp(\"<script([^<])*<([ ])*/([ ])*script([ ])*\"+\">\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"\"); var exp = new RegExp(\"<script([^<])*/([ ])*>\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"\");  var exp = new RegExp(\"<script([^<])*>\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"\"); return tt; }  function feh(tt){ var exp = new RegExp(\"(<([^>])*)on([^ |^=)*([ |=])*=([ |=])*([^ |^>])*(([^>])*>)\", \"i\"); " + "while(exp.test(tt))tt = tt.replace(exp, \"$1$6\"); return tt; }  function fu(tt){ var exp = new RegExp(\"(<([^>])*)([ |=])*=([ |=])*([^ |^>])*javascript:([^ |^>])*(([^>])*>)\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"$1$7\"); return tt; }  function fb(tt){ var exp = new RegExp(\"<PUBLIC([ ])*:([^<])*<([ ])*/PUBLIC([ ])*\"+\">\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"\"); var exp = new RegExp(\"<PUBLIC([ ])*:([^<])*/([ ])*>\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"\");  var exp = new RegExp(\"<PUBLIC([ ])*:([^<])*>\", \"i\"); while(exp.test(tt))tt = tt.replace(exp, \"\"); return tt } ";
    }

    public static String getNoErrorsMessageJS(String s)
    {
        return "alert(\"" + s + "\");";
    }
}
