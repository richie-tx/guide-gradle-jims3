package mojo.util.spellcheck.web;

import mojo.util.spellcheck.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.ServletRequest;

// Referenced classes of package com.keyoti.rapidSpell.web:
//            HtmlElement, HtmlInput, HtmlSelect, JSProv, 
//            K, Key, Label, RSButton, 
//            RSText

public class SpellCheckerWebBean
{

    public SpellCheckerWebBean()
    {
        _ignoreAllButton = new RSButton("button");
        _ignoreButton = new RSButton("button");
        _changeButton = new RSButton("button");
        _changeAllButton = new RSButton("button");
        _finishButton = new RSButton("button");
        _addButton = new RSButton("button");
        _layout = null;
        _previewPaneStyle = "font-family:'Sans-Serif, Arial, Helvetica';\tfont-size:10pt;\tcolor:black; background-color:white;";
        _previewPaneWidth = 318;
        _previewPaneHeight = 150;
        _scriptFilterLevel = 15;
        _badWordHighlightStyle = "border-bottom: 1px solid red; background-color: #ddddff";
        _changeToLabel = new Label();
        _suggestionsLabel = new Label();
        _suggestionsBox = new HtmlSelect();
        sslFriendly = false;
        _allowMixedCase = false;
        developerMode = false;
        _allowAnyCase = false;
        rswlClientID = "";
        removeDuplicateWordSuggestion = new Vector(1);
        mode = null;
        callBack = null;
        userDictionaryFile = null;
        suggestionsMethod = "HASHING_SUGGESTIONS";
        finishedListener = "";
        dictFile = "";
        includeUserDictionaryInSuggestions = false;
        ignoreCapitalizedWords = false;
        ignoreWordsWithDigits = true;
        ignoreXML = false;
        showXMLTags = false;
        considerationRange = -1;
        showFinishedMessage = true;
        showNoErrorsMessage = true;
        createdPopUpWindow = true;
        finishClosesWindow = true;
        separateHyphenWords = false;
        warnDuplicates = true;
        interfaceObject = "notSpecified";
        correctionNotifyListener = null;
        licenseKey = "";
        modal = false;
        openerWindow = "window.opener";
        _finishedText = "The Spelling Check Is Complete.";
        _noErrorsText = "No Spelling Errors In Text.";
        userDictionary = null;
        rapidSpellChecker = null;
        guiLanguage = 10001;
        languageParser = 10001;
        k = new K();
        javascript = "";
        init();
    }

    void addPreviewStylesToRender(Writer writer, String s, String s1)
        throws IOException
    {
        String s2 = " <style>body{" + s + "} .badWordHighlight{" + s1 + "}</style>";
        writer.write(s2);
    }

    private void doRender(Writer writer, ServletRequest servletrequest)
        throws IOException
    {
        request = servletrequest;
        rapidSpellChecker = getCheckerEngine();
        rapidSpellChecker.setMaximumAnagramLength(5);
        if(getPageAttribute("IncludeUserDictionaryInSuggestions") != null)
            includeUserDictionaryInSuggestions = getPageAttribute("IncludeUserDictionaryInSuggestions").toLowerCase().equals("true");
        rapidSpellChecker.setIncludeUserDictionaryInSuggestions(includeUserDictionaryInSuggestions);
        if(getPageAttribute("fMessage") != null && getPageAttribute("fMessage").equals("addWord"))
        {
            if(request.getParameter("GuiLanguage") != null && !request.getParameter("GuiLanguage").equals(""))
            {
                guiLanguage = LanguageType.getLanguageTypeFromString(request.getParameter("GuiLanguage"));
                updateUIText();
            }
            userDictionaryFile = request.getParameter("UserDictionaryFile");
            if(userDictionaryFile == null)
                userDictionaryFile = "";
            if(userDictionary != null && userDictionary.isValid())
            {
                rapidSpellChecker.setUserDictionary(userDictionary);
                rapidSpellChecker.addWord(request.getParameter("word"));
                userDictionaryFile = "";
            }
            if(!userDictionaryFile.equals(""))
            {
                rapidSpellChecker.setUserDictionary(new File(userDictionaryFile));
                rapidSpellChecker.addWord(request.getParameter("word"));
            }
            writer.write(JSProv.getAddButtonInformJS(((HtmlInput) (_addButton)).value));
        } else
        {
            text = getPageAttribute("textToCheck");
            if(text == null)
                text = "NULL";
            mode = getPageAttribute("mode");
            if(mode == null)
                mode = "separate";
            callBack = getPageAttribute("callBack");
            if(callBack == null)
                callBack = "";
            userDictionaryFile = getPageAttribute("UserDictionaryFile");
            if(userDictionaryFile == null)
                userDictionaryFile = "";
            if(!userDictionaryFile.equals(""))
                rapidSpellChecker.setUserDictionary(new File(userDictionaryFile));
            if(userDictionary != null && userDictionary.isValid())
                rapidSpellChecker.setUserDictionary(userDictionary);
            suggestionsMethod = getPageAttribute("SuggestionsMethod");
            if(suggestionsMethod != null && suggestionsMethod.equals("PHONETIC_SUGGESTIONS"))
                rapidSpellChecker.setSuggestionsMethod(SpellChecker.PHONETIC_SUGGESTIONS);
            else
                rapidSpellChecker.setSuggestionsMethod(SpellChecker.HASHING_SUGGESTIONS);
            if(getPageAttribute("IgnoreCapitalizedWords") != null)
                ignoreCapitalizedWords = getPageAttribute("IgnoreCapitalizedWords").toLowerCase().equals("true");
            rapidSpellChecker.setIgnoreCapitalizedWords(ignoreCapitalizedWords);
            if(getPageAttribute("IgnoreXML") != null)
                ignoreXML = getPageAttribute("IgnoreXML").toLowerCase().equals("true");
            rapidSpellChecker.setIgnoreXML(ignoreXML);
            if(getPageAttribute("ShowXMLTags") != null)
                showXMLTags = getPageAttribute("ShowXMLTags").toLowerCase().equals("true");
            if(getPageAttribute("InterfaceObject") != null)
                interfaceObject = getPageAttribute("InterfaceObject");
            if(getPageAttribute("FinishedListener") != null)
                finishedListener = getPageAttribute("FinishedListener");
            if(getPageAttribute("DictFile") != null)
                dictFile = getPageAttribute("DictFile");
            if(dictFile != null && !dictFile.equals(""))
                rapidSpellChecker.setDictFilePath(dictFile);
            if(getPageAttribute("Modal") != null)
                modal = getPageAttribute("Modal").toLowerCase().equals("true");
            if(modal)
                openerWindow = "parent.o";
            if(getPageAttribute("AllowMixedCase") != null)
                _allowMixedCase = getPageAttribute("AllowMixedCase").toLowerCase().equals("true");
            rapidSpellChecker.setAllowMixedCase(_allowMixedCase);
            if(getPageAttribute("AllowAnyCase") != null)
                _allowAnyCase = getPageAttribute("AllowAnyCase").toLowerCase().equals("true");
            rapidSpellChecker.setAllowAnyCase(_allowAnyCase);
            if(getPageAttribute("IgnoreWordsWithDigits") != null)
                ignoreWordsWithDigits = getPageAttribute("IgnoreWordsWithDigits").toLowerCase().equals("true");
            rapidSpellChecker.setIgnoreWordsWithDigits(ignoreWordsWithDigits);
            try
            {
                if(getPageAttribute("ConsiderationRange") != null)
                    considerationRange = Integer.parseInt(getPageAttribute("ConsiderationRange"));
                if(considerationRange > 1)
                    rapidSpellChecker.setConsiderationRange(considerationRange);
            }
            catch(NumberFormatException _ex) { }
            if(getPageAttribute("SeparateHyphenWords") != null)
                separateHyphenWords = getPageAttribute("SeparateHyphenWords").toLowerCase().equals("true");
            rapidSpellChecker.setSeparateHyphenWords(separateHyphenWords);
            if(getPageAttribute("CreatePopUpWindow") != null)
                createdPopUpWindow = getPageAttribute("CreatePopUpWindow").toLowerCase().equals("true");
            if(getPageAttribute("ShowFinishedMessage") != null)
                showFinishedMessage = getPageAttribute("ShowFinishedMessage").toLowerCase().equals("true");
            if(getPageAttribute("ShowNoErrorsMessage") != null)
                showNoErrorsMessage = getPageAttribute("ShowNoErrorsMessage").toLowerCase().equals("true");
            if(getPageAttribute("GuiLanguage") != null)
                guiLanguage = LanguageType.getLanguageTypeFromString(getPageAttribute("GuiLanguage"));
            if(getPageAttribute("FinishClosesWindow") != null)
                finishClosesWindow = getPageAttribute("FinishClosesWindow").toLowerCase().equals("true");
            if(getPageAttribute("WarnDuplicates") != null)
                warnDuplicates = getPageAttribute("WarnDuplicates").toLowerCase().equals("true");
            rapidSpellChecker.setWarnDuplicates(warnDuplicates);
            if(getPageAttribute("LanguageParser") != null)
                languageParser = LanguageType.getLanguageTypeFromString(getPageAttribute("LanguageParser"));
            rapidSpellChecker.setLanguageParser(languageParser);
            if(getPageAttribute("RswlClientID") != null && !getPageAttribute("RswlClientID").equals(""))
                rswlClientID = getPageAttribute("RswlClientID");
            updateUIText();
            javascript += JSProv.getFinishFunctionJS(openerWindow, interfaceObject, createdPopUpWindow, finishClosesWindow);
            writer.write("<!-- This page contains code that is the copyright of Keyoti Inc. 2002-2004 -->\n");
            writer.write("<!-- RapidSpell Web, licensed for domain:" + k.LSN + " servers:" + join(", ", k.LIPS) + " -->\n");
            writer.write("<script>" + javascript + "</script>");
            writer.write("    <script language=\"JavaScript\">");
            writer.write(JSProv.getDocumentTextBoxStyleJS());
            addPreviewStylesToRender(writer, _previewPaneStyle, _badWordHighlightStyle);
            writer.write(JSProv.getGlobalVarsJS(mode, ignoreXML, callBack, escText(userDictionaryFile)));
            writer.write("    </script>");
            writer.write("    <script language=\"JavaScript\">");
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = true;
            writer.write(JSProv.getGlobalFuncsJS(flag, flag1, openerWindow, rswlClientID, finishedListener, flag2));
            writer.write("    var text = \"" + lineEnd(escText(text), text.indexOf('\r') > -1) + "\";");
            writer.write("    var badWords = [");
            rapidSpellChecker.check(text);
            int i = 0;
            BadWord badword;
            try
            {
                while((badword = rapidSpellChecker.nextBadWord()) != null) 
                {
                    if(i > 0)
                        writer.write(",");
                    i++;
                    writer.write("{text:\"" + badword.getWord() + "\",e:\"" + URLEncoder.encode(badword.getWord()) + "\",start:" + badword.getStartPosition() + ",end:" + badword.getEndPosition() + ",suggestions:[");
                    Vector vector;
                    if(badword.getReason() == BadWord.REASON_DUPLICATE)
                        vector = removeDuplicateWordSuggestion;
                    else
                        vector = rapidSpellChecker.findSuggestions();
                    for(int j = 0; j < vector.size(); j++)
                    {
                        writer.write("\"" + (String)vector.get(j) + "\"");
                        if(j < vector.size() - 1)
                            writer.write(",");
                    }

                    writer.write("]}");
                }
            }
            catch(Exception exception)
            {
                writer.write(exception.toString());
            }
            writer.write("    ]; ");
            writer.write("    </script>");
            if(mode.equals("separate"))
                writer.write("    <form name='RapidSpellCheckerForm' method='POST' action='" + callBack + "'>");
            else
                writer.write("    <form name='RapidSpellCheckerForm' method='POST' action='' onsubmit='change();return false;'>");
            writer.write("    <input type='hidden' name='RSCallBack' value='true'>");
            writer.write("    <input type='hidden' name='CorrectedText' value=''>");
            if(_layout == null || _layout.equals(""))
            {
                writer.write("    <table cellSpacing=0 cellPadding=1 width=330 border=0>");
                if(Key.ver.equals("pinspelleval"))
                    writer.write("<script>alert(\"This is an unlicensed evaluation version of PinSpell, the dictionary has no 'z' words - the full version does not show this message.\");</script><Tr><Td style='border:1px solid red;background-color:white;width:330px;font-family:arial,sans-serif;'>Unlicensed, for evaluation only!</td></tr>");
                writer.write("            <tr>");
                writer.write("            <td vAlign=top height=170 width=330 colspan=2 align=center>");
                writer.write("                    <table border=0 width=330 height=170 cellpadding=1>");
                writer.write("                            <tr><td width=318 align=center>");
                writer.write("    <iframe " + (sslFriendly ? "src='blank.html' " : "") + " name='documentTextPanel' width=" + _previewPaneWidth + " height=" + _previewPaneHeight + "></iframe>");
                writer.write("                            </td>");
                writer.write("                    </table>");
                writer.write("            </td>");
                writer.write("            <tr>");
                writer.write("            <td>");
                _changeToLabel.renderControl(writer);
                writer.write("            </td><td rowspan=4 align=center>");
                writer.write("            <table border=0 cellpadding=2 width=100% height=170>");
                writer.write("                    <tr><td>");
                _ignoreButton.renderControl(writer);
                writer.write("                    </td></tr>");
                writer.write("                    <tr><td>");
                _ignoreAllButton.renderControl(writer);
                writer.write("                    </td></tr>");
                writer.write("                    <tr><td>");
                if(rapidSpellChecker.getUserDictionary() != null && rapidSpellChecker.getUserDictionary().isValid())
                {
                    _addButton.renderControl(writer);
                    writer.write("                    </td></tr>");
                    writer.write("                    <tr><td>");
                }
                _changeButton.renderControl(writer);
                writer.write("                    </td></tr>");
                writer.write("                    <tr><td>");
                _changeAllButton.renderControl(writer);
                writer.write("                    </td></tr>    ");
                writer.write("                    <tr><td>");
                _finishButton.renderControl(writer);
                writer.write("                    </td></tr>");
                writer.write("            </table>");
                writer.write("            </td>");
                writer.write("            </tr>");
                writer.write("                    <tr><td>");
                _changeToBox.renderControl(writer);
                writer.write("                    </td></tr><tr><td>");
                _suggestionsLabel.renderControl(writer);
                writer.write("                    </td></tr><tr><td>");
                _suggestionsBox.renderControl(writer);
                writer.write("            </td></tr>");
                writer.write("    </table>");
            } else
            {
                StringTokenizer stringtokenizer = new StringTokenizer(_layout, "<", true);
                boolean flag3 = false;
                while(stringtokenizer.hasMoreTokens()) 
                {
                    String s = stringtokenizer.nextToken();
                    if(s.indexOf("<") > -1)
                        flag3 = true;
                    if(s.indexOf("PreviewPane/>") > -1)
                        writer.write("<iframe " + (sslFriendly ? "src='blank.html' " : "") + " name='documentTextPanel' width=" + _previewPaneWidth + " height=" + _previewPaneHeight + "></iframe>");
                    else
                    if(s.indexOf("IgnoreButton/>") > -1)
                        _ignoreButton.renderControl(writer);
                    else
                    if(s.indexOf("IgnoreAllButton/>") > -1)
                        _ignoreAllButton.renderControl(writer);
                    else
                    if(s.indexOf("ChangeButton/>") > -1)
                        _changeButton.renderControl(writer);
                    else
                    if(s.indexOf("ChangeAllButton/>") > -1)
                        _changeAllButton.renderControl(writer);
                    else
                    if(s.indexOf("FinishButton/>") > -1)
                        _finishButton.renderControl(writer);
                    else
                    if(s.indexOf("ChangeToLabel/>") > -1)
                        _changeToLabel.renderControl(writer);
                    else
                    if(s.indexOf("ChangeToBox/>") > -1)
                        _changeToBox.renderControl(writer);
                    else
                    if(s.indexOf("SuggestionsLabel/>") > -1)
                        _suggestionsLabel.renderControl(writer);
                    else
                    if(s.indexOf("SuggestionsBox/>") > -1)
                        _suggestionsBox.renderControl(writer);
                    else
                    if(s.indexOf("AddButton/>") > -1 && rapidSpellChecker.getUserDictionary() != null && rapidSpellChecker.getUserDictionary().isValid())
                        _addButton.renderControl(writer);
                    else
                    if(!s.equals("<"))
                        writer.write((flag3 ? "<" : "") + s);
                }
            }
            writer.write("    </form>");
            writer.write("     <iframe " + (sslFriendly ? "src='blank.html' " : "") + " width=0 height=0 name='addWordFrame' style='border: none;'></iframe>");
        }
    }

    String escText(String s)
    {
        for(int i = -2; (i = s.indexOf("\\", i + 2)) > -1;)
            s = s.substring(0, i) + "\\" + s.substring(i);

        for(int j = -2; (j = s.indexOf("\"", j + 2)) > -1;)
            s = s.substring(0, j) + "\\" + s.substring(j);

        for(int l = 0; (l = s.toLowerCase().indexOf("<script", l)) > -1;)
            s = s.substring(0, l) + "<scr\"+\"ipt" + s.substring(l + 7);

        for(int i1 = 0; (i1 = s.toLowerCase().indexOf("</script", i1)) > -1;)
            s = s.substring(0, i1) + "</scr\"+\"ipt" + s.substring(i1 + 8);

        return s;
    }

    public void freeResources()
    {
        rapidSpellChecker = null;
    }

    public String getAddButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_addButton)).attributes.get("onMouseOut");
    }

    public String getAddButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_addButton)).attributes.get("onMouseOver");
    }

    public String getAddButtonStyle()
    {
        return (String)((HtmlElement) (_addButton)).attributes.get("style");
    }

    public String getAddButtonStyleClass()
    {
        return (String)((HtmlElement) (_addButton)).attributes.get("class");
    }

    public String getAddButtonText()
    {
        return (String)((HtmlElement) (_addButton)).attributes.get("text");
    }

    public String getBadWordHighlightStyle()
    {
        return _badWordHighlightStyle;
    }

    public String getChangeAllButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_changeAllButton)).attributes.get("onMouseOut");
    }

    public String getChangeAllButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_changeAllButton)).attributes.get("onMouseOver");
    }

    public String getChangeAllButtonStyle()
    {
        return (String)((HtmlElement) (_changeAllButton)).attributes.get("style");
    }

    public String getChangeAllButtonStyleClass()
    {
        return (String)((HtmlElement) (_changeAllButton)).attributes.get("class");
    }

    public String getChangeAllButtonText()
    {
        return (String)((HtmlElement) (_changeAllButton)).attributes.get("text");
    }

    public String getChangeButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_changeButton)).attributes.get("onMouseOut");
    }

    public String getChangeButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_changeButton)).attributes.get("onMouseOver");
    }

    public String getChangeButtonStyle()
    {
        return (String)((HtmlElement) (_changeButton)).attributes.get("style");
    }

    public String getChangeButtonStyleClass()
    {
        return (String)((HtmlElement) (_changeButton)).attributes.get("class");
    }

    public String getChangeButtonText()
    {
        return (String)((HtmlElement) (_changeButton)).attributes.get("text");
    }

    public String getChangeToBoxStyle()
    {
        return (String)((HtmlElement) (_changeToBox)).attributes.get("style");
    }

    public String getChangeToBoxStyleClass()
    {
        return (String)((HtmlElement) (_changeToBox)).attributes.get("class");
    }

    public String getChangeToLabel()
    {
        return _changeToLabel.text;
    }

    public String getChangeToLabelStyle()
    {
        return (String)((HtmlElement) (_changeToLabel)).attributes.get("style");
    }

    public String getChangeToLabelStyleClass()
    {
        return (String)((HtmlElement) (_changeToLabel)).attributes.get("class");
    }

    public ICheckerEngine getCheckerEngine()
    {
        if(rapidSpellChecker == null)
            if(!developerMode)
                rapidSpellChecker = new SpellChecker(licenseKey);
            else
                rapidSpellChecker = new SpellChecker("RSWEBJAVADEVMODE");
        return rapidSpellChecker;
    }

    public String getFinishButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_finishButton)).attributes.get("onMouseOut");
    }

    public String getFinishButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_finishButton)).attributes.get("onMouseOver");
    }

    public String getFinishButtonStyle()
    {
        return (String)((HtmlElement) (_finishButton)).attributes.get("style");
    }

    public String getFinishButtonStyleClass()
    {
        return (String)((HtmlElement) (_finishButton)).attributes.get("class");
    }

    public String getFinishButtonText()
    {
        return (String)((HtmlElement) (_finishButton)).attributes.get("text");
    }

    public String getIgnoreAllButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_ignoreAllButton)).attributes.get("onMouseOut");
    }

    public String getIgnoreAllButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_ignoreAllButton)).attributes.get("onMouseOver");
    }

    public String getIgnoreAllButtonStyle()
    {
        return (String)((HtmlElement) (_ignoreAllButton)).attributes.get("style");
    }

    public String getIgnoreAllButtonStyleClass()
    {
        return (String)((HtmlElement) (_ignoreAllButton)).attributes.get("class");
    }

    public String getIgnoreAllButtonText()
    {
        return ((HtmlInput) (_ignoreAllButton)).value;
    }

    public String getIgnoreButtonOnMouseOut()
    {
        return (String)((HtmlElement) (_ignoreButton)).attributes.get("onMouseOut");
    }

    public String getIgnoreButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_ignoreButton)).attributes.get("onMouseOver");
    }

    public String getIgnoreButtonStyle()
    {
        return (String)((HtmlElement) (_ignoreButton)).attributes.get("style");
    }

    public String getIgnoreButtonStyleClass()
    {
        return (String)((HtmlElement) (_ignoreButton)).attributes.get("class");
    }

    public String getIgnoreButtonText()
    {
        return (String)((HtmlElement) (_ignoreButton)).attributes.get("text");
    }

    public String getLayout()
    {
        return _layout;
    }

    public String getLicenseKey()
    {
        return licenseKey;
    }

    public String getPageAttribute(String s)
    {
        String s1 = request.getParameter(s);
        try
        {
            if(s1 == null)
                return null;
            else
                return new String(s1.getBytes("8859_1"), "UTF8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            System.err.println(unsupportedencodingexception.getMessage());
        }
        return s1;
    }

    public int getPreviewPaneHeight()
    {
        return _previewPaneHeight;
    }

    public String getPreviewPaneStyle()
    {
        return _previewPaneStyle;
    }

    public int getPreviewPaneWidth()
    {
        return _previewPaneWidth;
    }

    public boolean getSSLFriendly()
    {
        return sslFriendly;
    }

    public int getScriptFilterLevel()
    {
        return _scriptFilterLevel;
    }

    public String getSuggestionsBoxStyle()
    {
        return (String)((HtmlElement) (_suggestionsBox)).attributes.get("style");
    }

    public String getSuggestionsBoxStyleClass()
    {
        return (String)((HtmlElement) (_suggestionsBox)).attributes.get("class");
    }

    public String getSuggestionsLabel()
    {
        return _suggestionsLabel.text;
    }

    public String getSuggestionsLabelStyle()
    {
        return (String)((HtmlElement) (_suggestionsLabel)).attributes.get("style");
    }

    public String getSuggestionsLabelStyleClass()
    {
        return (String)((HtmlElement) (_suggestionsLabel)).attributes.get("class");
    }

    public UserDictionary getUserDictionary()
    {
        return userDictionary;
    }

    void init()
    {
        _addButton.value = "Add";
        _ignoreButton.value = "Ignore";
        _ignoreAllButton.value = "Ignore All";
        _changeButton.value = "Change";
        _changeAllButton.value = "Change All";
        _finishButton.value = "Finish";
        _changeToLabel.text = "Change To:";
        _suggestionsLabel.text = "Suggestions:";
        _changeToBox = new RSText();
        _changeToBox.size = 16;
        ((HtmlElement) (_changeToBox)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:10pt;width:200px;");
        ((HtmlElement) (_suggestionsBox)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:10pt;width:200px;");
        ((HtmlElement) (_changeToLabel)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:10pt;");
        ((HtmlElement) (_suggestionsLabel)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:10pt;");
        ((HtmlElement) (_ignoreButton)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:9pt;width:90px;");
        ((HtmlElement) (_changeButton)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:9pt;width:90px;");
        ((HtmlElement) (_ignoreAllButton)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:9pt;width:90px;");
        ((HtmlElement) (_changeAllButton)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:9pt;width:90px;");
        ((HtmlElement) (_finishButton)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:9pt;width:90px;");
        ((HtmlElement) (_addButton)).attributes.put("style", "font-family:'sans-serif, arial, helvetica';font-size:9pt;width:90px;");
        _changeToBox.name = "word";
        _changeToBox.id = "word";
        _suggestionsBox.name = "suggestions";
        _suggestionsBox.id = "suggestions";
        _suggestionsBox.items.add("No suggestions");
        _suggestionsBox.size = 6;
        _suggestionsBox.multiple = false;
        ((HtmlElement) (_suggestionsBox)).attributes.put("onChange", "changeSuggestions()");
        ((HtmlElement) (_addButton)).attributes.put("onclick", "addCurrent()");
        _addButton.name = "addButton";
        _addButton.id = "addButton";
        ((HtmlElement) (_ignoreButton)).attributes.put("onclick", "ignoreCurrent()");
        _ignoreButton.name = "ignoreButton";
        _ignoreButton.id = "ignoreButton";
        ((HtmlElement) (_ignoreAllButton)).attributes.put("onclick", "ignoreAll()");
        _ignoreAllButton.name = "ignoreAllButton";
        _ignoreAllButton.id = "ignoreAllButton";
        ((HtmlElement) (_changeButton)).attributes.put("onclick", "change()");
        _changeButton.name = "changeButton";
        _changeButton.id = "changeButton";
        ((HtmlElement) (_changeAllButton)).attributes.put("onclick", "changeAll()");
        _changeAllButton.name = "changeAllButton";
        _changeAllButton.id = "changeAllButton";
        ((HtmlElement) (_finishButton)).attributes.put("onclick", "finish()");
        _finishButton.name = "cancelButton";
        _finishButton.id = "cancelButton";
    }

    String join(String s, String as[])
    {
        String s1 = "";
        for(int i = 0; i < as.length; i++)
        {
            s1 = s1 + as[i];
            if(i < as.length - 1)
                s1 = s1 + s;
        }

        return s1;
    }

    String lineEnd(String s, boolean flag)
    {
        byte byte0 = 4;
        if(flag)
            byte0 = 5;
        for(int i = -byte0; (i = s.indexOf("\n", i + byte0)) > -1;)
            s = s.substring(0, (i - byte0) + 4) + (flag ? "\\r" : "") + "\\n\\" + s.substring(i);

        return s;
    }

    private void renderBadLicense(Writer writer, ServletRequest servletrequest)
        throws IOException
    {
        String s = "";
        if(k.LSN == "Evaluation (time limit)")
        {
            String s1 = " This key has expired.  Key details:" + k.LIPS[0];
            writer.write("<br><Br>" + s1 + "<br><br>");
        }
        writer.write("<span style='font-size:14pt; color: red;'>Sorry, RapidSpell Web is either not licensed to run on this server or this web site.</span><br>Please double check your license key and ensure it is set in the <b>RapidSpellWeb</b> tag.");
        writer.write("<br><br>This server name:" + servletrequest.getServerName());
    }

    public void setAddButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_addButton)).attributes.put("onMouseOut", s);
    }

    public void setAddButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_addButton)).attributes.put("onMouseOver", s);
    }

    public void setAddButtonStyle(String s)
    {
        ((HtmlElement) (_addButton)).attributes.put("style", s);
    }

    public void setAddButtonStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_addButton)).attributes.put("class", s);
    }

    public void setAddButtonText(String s)
    {
        _addButton.value = s;
    }

    public void setBadWordHighlightStyle(String s)
    {
        _badWordHighlightStyle = s;
    }

    public void setChangeAllButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_changeAllButton)).attributes.put("onMouseOut", s);
    }

    public void setChangeAllButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_changeAllButton)).attributes.put("onMouseOver", s);
    }

    public void setChangeAllButtonStyle(String s)
    {
        ((HtmlElement) (_changeAllButton)).attributes.put("style", s);
    }

    public void setChangeAllButtonStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_changeAllButton)).attributes.put("class", s);
    }

    public void setChangeAllButtonText(String s)
    {
        _changeAllButton.value = s;
    }

    public void setChangeButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_changeButton)).attributes.put("onMouseOut", s);
    }

    public void setChangeButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_changeButton)).attributes.put("onMouseOver", s);
    }

    public void setChangeButtonStyle(String s)
    {
        ((HtmlElement) (_changeButton)).attributes.put("style", s);
    }

    public void setChangeButtonStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_changeButton)).attributes.put("class", s);
    }

    public void setChangeButtonText(String s)
    {
        _changeButton.value = s;
    }

    public void setChangeToBoxStyle(String s)
    {
        ((HtmlElement) (_changeToBox)).attributes.put("style", s);
    }

    public void setChangeToBoxStyleClass(String s)
    {
        ((HtmlElement) (_changeToBox)).attributes.put("class", s);
    }

    public void setChangeToLabel(String s)
    {
        _changeToLabel.text = s;
    }

    public void setChangeToLabelStyle(String s)
    {
        ((HtmlElement) (_changeToLabel)).attributes.put("style", s);
    }

    public void setChangeToLabelStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_changeToLabel)).attributes.put("class", s);
    }

    public void setCheckerEngine(ICheckerEngine icheckerengine)
    {
        rapidSpellChecker = icheckerengine;
    }

    public void setFinishButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_finishButton)).attributes.put("onMouseOut", s);
    }

    public void setFinishButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_finishButton)).attributes.put("onMouseOver", s);
    }

    public void setFinishButtonStyle(String s)
    {
        ((HtmlElement) (_finishButton)).attributes.put("style", s);
    }

    public void setFinishButtonStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_finishButton)).attributes.put("class", s);
    }

    public void setFinishButtonText(String s)
    {
        _finishButton.value = s;
    }

    public void setIgnoreAllButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_ignoreAllButton)).attributes.put("onMouseOut", s);
    }

    public void setIgnoreAllButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_ignoreAllButton)).attributes.put("onMouseOver", s);
    }

    public void setIgnoreAllButtonStyle(String s)
    {
        ((HtmlElement) (_ignoreAllButton)).attributes.put("style", s);
    }

    public void setIgnoreAllButtonStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_ignoreAllButton)).attributes.put("class", s);
    }

    public void setIgnoreAllButtonText(String s)
    {
        _ignoreAllButton.value = s;
    }

    public void setIgnoreButtonOnMouseOut(String s)
    {
        ((HtmlElement) (_ignoreButton)).attributes.put("onMouseOut", s);
    }

    public void setIgnoreButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_ignoreButton)).attributes.put("onMouseOver", s);
    }

    public void setIgnoreButtonStyle(String s)
    {
        ((HtmlElement) (_ignoreButton)).attributes.put("style", s);
    }

    public void setIgnoreButtonStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_ignoreButton)).attributes.put("class", s);
    }

    public void setIgnoreButtonText(String s)
    {
        _ignoreButton.value = s;
    }

    public void setLayout(String s)
    {
        _layout = s;
    }

    public void setLicenseKey(String s)
    {
        licenseKey = s;
    }

    public void setPreviewPaneHeight(int i)
    {
        _previewPaneHeight = i;
    }

    public void setPreviewPaneStyle(String s)
    {
        _previewPaneStyle = s;
    }

    public void setPreviewPaneWidth(int i)
    {
        _previewPaneWidth = i;
    }

    public void setSSLFriendly(boolean flag)
    {
        sslFriendly = flag;
    }

    public void setScriptFilterLevel(int i)
    {
        _scriptFilterLevel = i;
    }

    public void setSuggestionsBoxStyle(String s)
    {
        ((HtmlElement) (_suggestionsBox)).attributes.put("style", s);
    }

    public void setSuggestionsBoxStyleClass(String s)
    {
        ((HtmlElement) (_suggestionsBox)).attributes.put("class", s);
    }

    public void setSuggestionsLabel(String s)
    {
        _suggestionsLabel.text = s;
    }

    public void setSuggestionsLabelStyle(String s)
    {
        ((HtmlElement) (_suggestionsLabel)).attributes.put("style", s);
    }

    public void setSuggestionsLabelStyleClass(String s)
    {
        if(!s.equals(""))
            ((HtmlElement) (_suggestionsLabel)).attributes.put("class", s);
    }

    public void setUserDictionary(UserDictionary userdictionary)
    {
        userDictionary = userdictionary;
    }

    void updateUIText()
    {
        String s = "";
        if(correctionNotifyListener != null)
            s = "opener." + correctionNotifyListener + "(badWords[index].start, text.substring(badWords[index].start,badWords[index].end), document.forms[0].word.value);";
        String s1 = JSProv.getFinishedMessageJS(CultureText.get(_finishedText, guiLanguage));
        String s2 = JSProv.getNoErrorsMessageJS(CultureText.get(_noErrorsText, guiLanguage));
        removeDuplicateWordSuggestion.clear();
        removeDuplicateWordSuggestion.add(CultureText.get("Remove duplicate word", guiLanguage));
        if(!showFinishedMessage)
            s1 = "";
        if(!showNoErrorsMessage)
            s2 = "";
        boolean flag = false;
        boolean flag1 = true;
        javascript = JSProv.getMainBodyJS(CultureText.get("No Suggestions.", guiLanguage), flag, flag1, getScriptFilterLevel(), CultureText.get("Remove duplicate word", guiLanguage), s1, s2, s, CultureText.get("Adding...", guiLanguage), guiLanguage);
        _suggestionsBox.items.add(CultureText.get("No Suggestions.", guiLanguage));
        _changeToLabel.text = CultureText.get(_changeToLabel.text, guiLanguage);
        _suggestionsLabel.text = CultureText.get(_suggestionsLabel.text, guiLanguage);
        _addButton.value = CultureText.get(((HtmlInput) (_addButton)).value, guiLanguage);
        _ignoreButton.value = CultureText.get(((HtmlInput) (_ignoreButton)).value, guiLanguage);
        _ignoreAllButton.value = CultureText.get(((HtmlInput) (_ignoreAllButton)).value, guiLanguage);
        _changeButton.value = CultureText.get(((HtmlInput) (_changeButton)).value, guiLanguage);
        _changeAllButton.value = CultureText.get(((HtmlInput) (_changeAllButton)).value, guiLanguage);
        _finishButton.value = CultureText.get(((HtmlInput) (_finishButton)).value, guiLanguage);
    }

    public void writeHtml(Writer writer, ServletRequest servletrequest)
        throws IOException
    {
//        if(k.cLic(servletrequest, licenseKey))
//        {
//            if(licenseKey.equals(""))
//                developerMode = true;
            doRender(writer, servletrequest);
//        } else
//        {
//            renderBadLicense(writer, servletrequest);
//        }
    }

    RSButton _ignoreAllButton;
    RSButton _ignoreButton;
    RSButton _changeButton;
    RSButton _changeAllButton;
    RSButton _finishButton;
    RSButton _addButton;
    String _layout;
    String _previewPaneStyle;
    int _previewPaneWidth;
    int _previewPaneHeight;
    int _scriptFilterLevel;
    String _badWordHighlightStyle;
    Label _changeToLabel;
    Label _suggestionsLabel;
    RSText _changeToBox;
    HtmlSelect _suggestionsBox;
    boolean sslFriendly;
    boolean _allowMixedCase;
    boolean developerMode;
    boolean _allowAnyCase;
    String rswlClientID;
    Vector removeDuplicateWordSuggestion;
    ServletRequest request;
    String text;
    String mode;
    String callBack;
    String userDictionaryFile;
    String suggestionsMethod;
    String finishedListener;
    String dictFile;
    boolean includeUserDictionaryInSuggestions;
    boolean ignoreCapitalizedWords;
    boolean ignoreWordsWithDigits;
    boolean ignoreXML;
    boolean showXMLTags;
    int considerationRange;
    boolean showFinishedMessage;
    boolean showNoErrorsMessage;
    boolean createdPopUpWindow;
    boolean finishClosesWindow;
    boolean separateHyphenWords;
    boolean warnDuplicates;
    String interfaceObject;
    String correctionNotifyListener;
    String licenseKey;
    boolean modal;
    String openerWindow;
    String _finishedText;
    String _noErrorsText;
    UserDictionary userDictionary;
    ICheckerEngine rapidSpellChecker;
    int guiLanguage;
    int languageParser;
    K k;
    String javascript;
}
