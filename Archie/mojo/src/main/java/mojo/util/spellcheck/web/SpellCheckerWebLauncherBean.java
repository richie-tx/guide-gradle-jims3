package mojo.util.spellcheck.web;

import mojo.util.spellcheck.CultureText;
import mojo.util.spellcheck.LanguageType;
import java.io.IOException;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Random;

public class SpellCheckerWebLauncherBean
{

    public SpellCheckerWebLauncherBean()
    {
        _mode = "popup";
        _callBack = "";
        _textComponentName = null;
        _buttonText = "Check Spelling";
        _rapidSpellWebPage = "";
        separateHyphenWords = false;
        _ignoreWordsWithDigits = true;
        _windowWidth = 370;
        _windowHeight = 400;
        _windowX = 100;
        _windowY = 100;
        _userDictionaryFile = "";
        _suggestionsMethod = "HASHING_SUGGESTIONS";
        _includeUserDictionaryInSuggestions = false;
        _ignoreCapitalizedWords = false;
        _ignoreXML = false;
        _showXMLTags = false;
        _sslFriendly = false;
        _modal = false;
        _considerationRange = 80;
        _tabIndex = 0;
        uniqueJSInterfaceObjectSuffix = "";
        _finishedListener = "";
        _dictFile = "";
        _popUpWindowName = "";
        _rsMultipleID = null;
        _createPopUpWindow = true;
        _showFinishedMessage = true;
        _showNoErrorsMessage = true;
        _allowMixedCase = false;
        _allowAnyCase = false;
        _showButton = true;
        _ButtonImages = new String[3];
        _warnDuplicates = true;
        _guiLanguage = 10001;
        _languageParser = 10001;
        textComponentInterface = 10001;
        _textToCheckField = new HtmlInputHidden();
        _callBackField = new HtmlInputHidden();
        _modeField = new HtmlInputHidden();
        _richTextComponentInterfaceJS = "function RSRTBInterface(rtbElementName){this.rtbName = rtbElementName;this.getText = getText;this.setText = setText;function getText(){\treturn eval(this.rtbName).document.body.innerHTML;}function setText(text){\teval(this.rtbName).document.body.innerHTML = text;}}";
// RDS 09122006
//        _standardInterfaceJS = "function RSStandardInterface(tbElementName){this.tbName = tbElementName;this.getText = getText;this.setText = setText;function getText(){\treturn eval('document.'+this.tbName+'.value');}function setText(text){\teval('document.'+this.tbName+'.value = text') ;}}";
//
        _standardInterfaceJS = "function RSStandardInterface(tbElementName){this.tbName = tbElementName;this.getText = getText;this.setText = setText;function getText(){\tvar finalVal=null;if(window.tinyMCE){var editId = tinyMCE.selectedInstance.editorId;var inst = tinyMCE.getInstanceById(editId);if(inst){finalVal=inst.getBody().innerHTML;}}if(finalVal==null){var myFoundElem=document.getElementsByName(tbElementName)[0];finalVal=myFoundElem.value;}return finalVal;}function setText(text){\tif(window.tinyMCE){var editId = tinyMCE.selectedInstance.editorId;var inst = tinyMCE.getInstanceById(editId);if(inst){inst.getBody().innerHTML=text;}}else{var myFoundElem=document.getElementsByName(tbElementName)[0];myFoundElem.value=text;}}}";
        _button = new RSButton("button");
        _button.value = CultureText.get("Check Spelling", _guiLanguage);
        Random random = new Random();
        _id = "r" + random.nextInt(9) + "a" + random.nextInt(9) + "z" + random.nextInt(9);
    }

    String escText(String s)
    {
        for(int i = -2; (i = s.indexOf("\\", i + 2)) > -1;)
            s = s.substring(0, i) + "\\" + s.substring(i);

        for(int j = -2; (j = s.indexOf("\"", j + 2)) > -1;)
            s = s.substring(0, j) + "\\" + s.substring(j);

        return s;
    }

    public boolean getAllowAnyCase()
    {
        return _allowAnyCase;
    }

    public boolean getAllowMixedCase()
    {
        return _allowMixedCase;
    }

    public String getButtonImageMouseDown()
    {
        return _ButtonImages[2];
    }

    public String getButtonImageMouseOut()
    {
        return _ButtonImages[0];
    }

    public String getButtonImageMouseOver()
    {
        return _ButtonImages[1];
    }

    public String getButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_button)).attributes.get("onMouseOut");
    }

    public String getButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_button)).attributes.get("onMouseOver");
    }

    public String getButtonStyle()
    {
        return (String)((HtmlElement) (_button)).attributes.get("style");
    }

    public String getButtonText()
    {
        return _buttonText;
    }

    public String getCallBack()
    {
        return _callBack;
    }

    public int getConsiderationRange()
    {
        return _considerationRange;
    }

    public boolean getCreatePopUpWindow()
    {
        return _createPopUpWindow;
    }

    public String getDictFile()
    {
        return _dictFile;
    }

    public String getFinishedListener()
    {
        return _finishedListener;
    }

    public int getGuiLanguage()
    {
        return _guiLanguage;
    }

    public String getId()
    {
        return _id;
    }

    public boolean getIgnoreCapitalizedWords()
    {
        return _ignoreCapitalizedWords;
    }

    public boolean getIgnoreWordsWithDigits()
    {
        return _ignoreWordsWithDigits;
    }

    public boolean getIgnoreXML()
    {
        return _ignoreXML;
    }

    public boolean getIncludeUserDictionaryInSuggestions()
    {
        return _includeUserDictionaryInSuggestions;
    }

    public int getLanguageParser()
    {
        return _languageParser;
    }

    public boolean getModal()
    {
        return _modal;
    }

    public String getMode()
    {
        return _mode;
    }

    public String getPopUpWindowName()
    {
        return _popUpWindowName;
    }

    public String getRSMultipleID()
    {
        return _rsMultipleID;
    }

    public String getRapidSpellWebPage()
    {
        return _rapidSpellWebPage;
    }

    public boolean getSSLFriendly()
    {
        return _sslFriendly;
    }

    public boolean getSeparateHyphenWords()
    {
        return separateHyphenWords;
    }

    public boolean getShowButton()
    {
        return _showButton;
    }

    public boolean getShowFinishedMessage()
    {
        return _showFinishedMessage;
    }

    public boolean getShowNoErrorsMessage()
    {
        return _showNoErrorsMessage;
    }

    public boolean getShowXMLTags()
    {
        return _showXMLTags;
    }

    public String getSuggestionsMethod()
    {
        return _suggestionsMethod;
    }

    public int getTabIndex()
    {
        return _tabIndex;
    }

    public String getTextComponentInterface()
    {
        return TextComponentInterfaceType.getNameFor(textComponentInterface);
    }

    public String getTextComponentName()
    {
        return _textComponentName;
    }

    public String getUserDictionaryFile()
    {
        return _userDictionaryFile;
    }

    public boolean getWarnDuplicates()
    {
        return _warnDuplicates;
    }

    public int getWindowHeight()
    {
        return _windowHeight;
    }

    public int getWindowWidth()
    {
        return _windowWidth;
    }

    public int getWindowX()
    {
        return _windowX;
    }

    public int getWindowY()
    {
        return _windowY;
    }

    public void setAllowAnyCase(boolean flag)
    {
        _allowAnyCase = flag;
    }

    public void setAllowMixedCase(boolean flag)
    {
        _allowMixedCase = flag;
    }

    public void setButtonImageMouseDown(String s)
    {
        _ButtonImages[2] = s;
    }

    public void setButtonImageMouseOut(String s)
    {
        _ButtonImages[0] = s;
    }

    public void setButtonImageMouseOver(String s)
    {
        _ButtonImages[1] = s;
    }

    public void setButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_button)).attributes.put("onMouseOut", s);
    }

    public void setButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_button)).attributes.put("onMouseOver", s);
    }

    public void setButtonStyle(String s)
    {
        ((HtmlElement) (_button)).attributes.put("style", s);
    }

    public void setButtonText(String s)
    {
        _buttonText = s;
    }

    public void setCallBack(String s)
    {
        _callBack = s;
    }

    public void setConsiderationRange(int i)
    {
        _considerationRange = i;
    }

    public void setCreatePopUpWindow(boolean flag)
    {
        _createPopUpWindow = flag;
    }

    public void setDictFile(String s)
    {
        _dictFile = s;
    }

    public void setFinishedListener(String s)
    {
        _finishedListener = s;
    }

    public void setGuiLanguage(int i)
    {
        _guiLanguage = i;
    }

    public void setId(String s)
    {
        if(s != null)
            _id = s;
    }

    public void setIgnoreCapitalizedWords(boolean flag)
    {
        _ignoreCapitalizedWords = flag;
    }

    public void setIgnoreWordsWithDigits(boolean flag)
    {
        _ignoreWordsWithDigits = flag;
    }

    public void setIgnoreXML(boolean flag)
    {
        _ignoreXML = flag;
    }

    public void setIncludeUserDictionaryInSuggestions(boolean flag)
    {
        _includeUserDictionaryInSuggestions = flag;
    }

    public void setLanguageParser(int i)
    {
        _languageParser = i;
    }

    public void setModal(boolean flag)
    {
        _modal = flag;
    }

    public void setMode(String s)
    {
        _mode = s;
    }

    public void setPopUpWindowName(String s)
    {
        _popUpWindowName = s;
    }

    public void setRSMultipleID(String s)
    {
        if(s != null)
        {
            _createPopUpWindow = false;
            _popUpWindowName = s + "_PopUpWin";
            _showFinishedMessage = false;
            _showNoErrorsMessage = false;
            _finishedListener = s + "_NotifyDone";
            _showButton = false;
        }
        _rsMultipleID = s;
    }

    public void setRapidSpellWebPage(String s)
    {
        _rapidSpellWebPage = s;
    }

    public void setSSLFriendly(boolean flag)
    {
        _sslFriendly = flag;
    }

    public void setSeparateHyphenWords(boolean flag)
    {
        separateHyphenWords = flag;
    }

    public void setShowButton(boolean flag)
    {
        _showButton = flag;
    }

    public void setShowFinishedMessage(boolean flag)
    {
        _showFinishedMessage = flag;
    }

    public void setShowNoErrorsMessage(boolean flag)
    {
        _showNoErrorsMessage = flag;
    }

    public void setShowXMLTags(boolean flag)
    {
        _showXMLTags = flag;
    }

    public void setSuggestionsMethod(String s)
    {
        _suggestionsMethod = s;
    }

    public void setTabIndex(int i)
    {
        _tabIndex = i;
        _button.tabIndex = i;
    }

    public void setTextComponentInterface(String s)
    {
        textComponentInterface = TextComponentInterfaceType.getIntFor(s);
    }

    public void setTextComponentName(String s)
    {
        _textComponentName = s;
    }

    public void setUserDictionaryFile(String s)
    {
        _userDictionaryFile = s;
    }

    public void setWarnDuplicates(boolean flag)
    {
        _warnDuplicates = flag;
    }

    public void setWindowHeight(int i)
    {
        _windowHeight = i;
    }

    public void setWindowWidth(int i)
    {
        _windowWidth = i;
    }

    public void setWindowX(int i)
    {
        _windowX = i;
    }

    public void setWindowY(int i)
    {
        _windowY = i;
    }

    public void writeHtml(Writer writer)
        throws IOException
    {
        _button.value = CultureText.get(_buttonText, _guiLanguage);
        uniqueJSInterfaceObjectSuffix = _id;
        if(textComponentInterface == 10001)
            writer.write("<script language='JavaScript'>" + _standardInterfaceJS + "</script>");
        else
        if(textComponentInterface == 10002)
            writer.write("<script language='JavaScript'>" + _richTextComponentInterfaceJS + "</script>");
        writer.write("<script language='JavaScript' type='text/Javascript'>function escQuotes(text){  var rx = new RegExp(\"\\\"\", \"g\"); return text.replace(rx,\"&#34;\");}");
        writer.write("function escEntities(text){");
        writer.write("\tvar rx = new RegExp(\"&\", \"g\"); ");
        writer.write("\ttext = text.replace(rx,\"&amp;\");");
        writer.write("\tfor(var i=161; i<=255; i++){");
        writer.write("\t\trx = new RegExp(String.fromCharCode(i), \"g\");");
        writer.write("\t\ttext = text.replace(rx, \"&#\"+i+\";\");");
        writer.write("\t}return text;}");
        writer.write("</script>");
        if(_mode.equals("popup"))
        {
            ((HtmlElement) (_button)).attributes.put("onClick", "popUpCheckSpelling" + uniqueJSInterfaceObjectSuffix + "('rsTCInt" + uniqueJSInterfaceObjectSuffix + "')");
            writer.write("<script language='JavaScript'>function popUpCheckSpelling" + uniqueJSInterfaceObjectSuffix + "(interfaceObjectName){" + "  var spellBoot = \"<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body onLoad='document.forms[0].submit();'><font face='arial, helvetica' size=2>" + CultureText.get("Spell checking document...", _guiLanguage) + "</font><form action='" + _rapidSpellWebPage + "' method='post' ACCEPT-CHARSET='UTF-8'>\";" + "  spellBoot += \"<input type='hidden' name='textToCheck' value=\\\"\"+escQuotes(escEntities(eval(interfaceObjectName+'.getText()'))" + ")+\"\\\"><input type='hidden' name='InterfaceObject' value='\"+interfaceObjectName+\"'><input type='hidden' name='mode' value='popup'><input type='hidden' name='UserDictionaryFile' value='" + escText(_userDictionaryFile) + "'><input type='hidden' name='SuggestionsMethod' value='" + _suggestionsMethod + "'><input type='hidden' name='SeparateHyphenWords' value='" + separateHyphenWords + "'>\";" + " spellBoot += \"<input type='hidden' name='IncludeUserDictionaryInSuggestions' value='" + _includeUserDictionaryInSuggestions + "'><input type='hidden' name='FinishedListener' value='" + _finishedListener + "'><input type='hidden' name='callBack' value='" + _callBack + "'><input type='hidden' name='IgnoreXML' value='" + _ignoreXML + "'><input type='hidden' name='IgnoreCapitalizedWords' value='" + _ignoreCapitalizedWords + "'>\";" + " spellBoot += \"<input type='hidden' name='GuiLanguage' value='" + LanguageType.getLanguageNameFromType(_guiLanguage) + "'><input type='hidden' name='LanguageParser' value='" + LanguageType.getLanguageNameFromType(_languageParser) + "'><input type='hidden' name='Modal' value='" + _modal + "'><input type='hidden' name='AllowAnyCase' value='" + _allowAnyCase + "'><input type='hidden' name='IgnoreWordsWithDigits' value='" + _ignoreWordsWithDigits + "'>\";" + " spellBoot += \"<input type='hidden' name='ShowFinishedMessage' value='" + _showFinishedMessage + "'><input type='hidden' name='ShowNoErrorsMessage' value='" + _showNoErrorsMessage + "'><input type='hidden' name='ShowXMLTags' value='" + _showXMLTags + "'><input type='hidden' name='AllowMixedCase' value='" + _allowMixedCase + "'><input type='hidden' name='RswlClientID' value='" + _id + "'><input type='hidden' name='WarnDuplicates' value='" + _warnDuplicates + "'>\";" + " spellBoot += \"<input type='hidden' name='DictFile' value='" + _dictFile + "'><input type='hidden' name='PopUpWindowName' value='" + _popUpWindowName + "'><input type='hidden' name='CreatePopUpWindow' value='" + _createPopUpWindow + "'><input type='hidden' name='ConsiderationRange' value='" + _considerationRange + "'></form></body></html>\";");
            if(!_modal)
            {
                String s = "sc";
                String s2 = _sslFriendly ? "blank.html" : "";
                if(_createPopUpWindow)
                    writer.write(" var " + s + " = window.open('" + s2 + "', 'rspellwin', 'resizable=yes,scrollbars=auto,dependent=yes,toolbar=no," + (_windowX < 0 ? "" : "left=" + _windowX + ",") + (_windowY < 0 ? "" : "top=" + _windowY + ",") + "status=no,location=no,menubar=no,width=" + _windowWidth + ",height=" + _windowHeight + "');");
                else
                if(s != null)
                    s = _popUpWindowName;
                writer.write(" " + s + ".focus(); " + s + ".document.open();  " + s + ".document.write(spellBoot); " + s + ".document.close();");
            } else
            {
                writer.write("var pair = [this.window,spellBoot];showModalDialog(\"RapidSpellModalHelper.html\",pair,\"dialogHeight: " + (_windowHeight + 10) + "px; dialogWidth: " + (_windowWidth + 5) + "px; " + (_windowX < 0 ? "" : "dialogLeft:" + _windowX + "; ") + (_windowY < 0 ? "" : "dialogTop:" + _windowY + "; ") + "edge: Sunken; center: Yes; help: No; resizable: No; status: No;\");");
            }
            writer.write("}");
            if(textComponentInterface == 10001)
                writer.write("var rsTCInt" + uniqueJSInterfaceObjectSuffix + " = new RSStandardInterface(\"" + _textComponentName + "\");");
            else
            if(textComponentInterface == 10002)
                writer.write("var rsTCInt" + uniqueJSInterfaceObjectSuffix + " = new RSRTBInterface(\"" + _textComponentName + "\");");
            else
            if(textComponentInterface == 10003)
                writer.write("var rsTCInt" + uniqueJSInterfaceObjectSuffix + " = new RSCustomInterface(\"" + _textComponentName + "\");");
            writer.write("</script>");
        } else
        {
            ((HtmlElement) (_button)).attributes.put("onClick", "separateCheckSpelling(this.form, 'rsTCInt" + uniqueJSInterfaceObjectSuffix + "')");
            writer.write("<script language='Javascript'>");
            writer.write("function separateCheckSpelling(thisForm, interfaceObjectName){  thisForm.textToCheck.value=escQuotes(escEntities(eval(interfaceObjectName+'.getText()'))); thisForm.mode.value='separate';  thisForm.callBack.value='" + _callBack + "';thisForm.action='" + _rapidSpellWebPage + "';thisForm.submit();" + " } ");
            if(textComponentInterface == 10001)
            {
                writer.write(_standardInterfaceJS);
                writer.write("var rsTCInt" + uniqueJSInterfaceObjectSuffix + " = new RSStandardInterface(\"" + _textComponentName + "\");");
            } else
            if(textComponentInterface == 10002)
            {
                writer.write(_richTextComponentInterfaceJS);
                writer.write("var rsTCInt" + uniqueJSInterfaceObjectSuffix + " = new RSRTBInterface(\"" + _textComponentName + "\");");
            } else
            if(textComponentInterface == 10003)
                writer.write("var rsTCInt" + uniqueJSInterfaceObjectSuffix + " = new RSCustomInterface(\"" + _textComponentName + "\");");
            writer.write("</script>");
            _textToCheckField.name = "textToCheck";
            _textToCheckField.id = "textToCheck";
            _textToCheckField.renderControl(writer);
            _callBackField.name = "callBack";
            _callBackField.id = "callBack";
            _callBackField.renderControl(writer);
            _modeField.name = "mode";
            _modeField.id = "mode";
            _modeField.renderControl(writer);
            writer.write("<input type='hidden' name='UserDictionaryFile' value='" + _userDictionaryFile + "'><input type='hidden' name='SuggestionsMethod' value='" + _suggestionsMethod + "'><input type='hidden' name='DictFile' value='" + _dictFile + "'><input type='hidden' name='AllowMixedCase' value='" + _allowMixedCase + "'><input type='hidden' name='IgnoreWordsWithDigits' value='" + _ignoreWordsWithDigits + "'>" + "<input type='hidden' name='GuiLanguage' value='" + LanguageType.getLanguageNameFromType(_guiLanguage) + "'><input type='hidden' name='LanguageParser' value='" + LanguageType.getLanguageNameFromType(_languageParser) + "'><input type='hidden' name='ShowXMLTags' value='" + _showXMLTags + "'><input type='hidden' name='RswlClientID' value='" + _id + "'>" + "<input type='hidden' name='IncludeUserDictionaryInSuggestions' value='" + _includeUserDictionaryInSuggestions + "'><input type='hidden' name='SeparateHyphenWords' value='" + separateHyphenWords + "'><input type='hidden' name='ShowFinishedMessage' value='" + _showFinishedMessage + "'><input type='hidden' name='ShowNoErrorsMessage' value='" + _showNoErrorsMessage + "'>" + "<input type='hidden' name='IgnoreCapitalizedWords' value='" + _ignoreCapitalizedWords + "'><input type='hidden' name='IgnoreXML' value='" + _ignoreXML + "'><input type='hidden' name='ConsiderationRange' value='" + _considerationRange + "'><input type='hidden' name='AllowAnyCase' value='" + _allowAnyCase + "'><input type='hidden' name='WarnDuplicates' value='" + _warnDuplicates + "'>");
        }
        String s1 = ((HtmlElement) (_button)).attributes.get("onClick").toString();
        if(_showButton)
            if(_ButtonImages[0] == null || _ButtonImages[0].equals(""))
            {
                _button.renderControl(writer);
            } else
            {
                String s3 = "";
                if(((HtmlInput) (_button)).tabIndex > 0)
                    s3 = "TabIndex=" + ((HtmlInput) (_button)).tabIndex;
                writer.write("<script>" + getId() + "Out=new Image();" + getId() + "Out.src=\"" + _ButtonImages[0] + "\";" + getId() + "Over=new Image();" + getId() + "Over.src=\"" + (_ButtonImages[1] == null ? _ButtonImages[0] : _ButtonImages[1]) + "\";" + getId() + "Down=new Image();" + getId() + "Down.src=\"" + (_ButtonImages[2] == null ? _ButtonImages[0] : _ButtonImages[2]) + "\";</script>");
                writer.write("<a href=\"#stayhere\"  onClick=\"" + s1 + "\" " + s3 + "><img border='0' src=\"" + _ButtonImages[0] + "\" onMouseOver=\"this.src=" + getId() + "Over.src\" onMouseOut=\"this.src=" + getId() + "Out.src\" onMouseDown=\"this.src=" + getId() + "Down.src\" onMouseUp=\"this.src=" + getId() + "Over.src\"></a>");
            }
    }

    String _mode;
    String _callBack;
    String _textComponentName;
    String _buttonText;
    String _rapidSpellWebPage;
    boolean separateHyphenWords;
    boolean _ignoreWordsWithDigits;
    int _windowWidth;
    int _windowHeight;
    int _windowX;
    int _windowY;
    String _userDictionaryFile;
    RSButton _button;
    String _id;
    String _suggestionsMethod;
    boolean _includeUserDictionaryInSuggestions;
    boolean _ignoreCapitalizedWords;
    boolean _ignoreXML;
    boolean _showXMLTags;
    boolean _sslFriendly;
    boolean _modal;
    int _considerationRange;
    int _tabIndex;
    String uniqueJSInterfaceObjectSuffix;
    String _finishedListener;
    String _dictFile;
    String _popUpWindowName;
    String _rsMultipleID;
    boolean _createPopUpWindow;
    boolean _showFinishedMessage;
    boolean _showNoErrorsMessage;
    boolean _allowMixedCase;
    boolean _allowAnyCase;
    boolean _showButton;
    String _ButtonImages[];
    boolean _warnDuplicates;
    int _guiLanguage;
    int _languageParser;
    int textComponentInterface;
    HtmlInputHidden _textToCheckField;
    HtmlInputHidden _callBackField;
    HtmlInputHidden _modeField;
    String _richTextComponentInterfaceJS;
    String _standardInterfaceJS;
}
