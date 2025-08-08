package mojo.util.spellcheck.web;

import mojo.util.spellcheck.LanguageType;
import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class SpellCheckerWebLauncher extends TagSupport
{

    public SpellCheckerWebLauncher()
    {
        guiLang = "English";
        langParser = "English";
        rswlBean = new SpellCheckerWebLauncherBean();
    }

    public int doEndTag()
        throws JspTagException
    {
        return 6;
    }

    public int doStartTag()
        throws JspTagException
    {
        try
        {
            javax.servlet.jsp.JspWriter jspwriter = super.pageContext.getOut();
            rswlBean.writeHtml(jspwriter);
        }
        catch(IOException _ex)
        {
            throw new JspTagException("JspWriter not there!!");
        }
        return 0;
    }

    public boolean getAllowAnyCase()
    {
        return rswlBean.getAllowAnyCase();
    }

    public boolean getAllowMixedCase()
    {
        return rswlBean.getAllowMixedCase();
    }

    public String getButtonImageMouseDown()
    {
        return rswlBean.getButtonImageMouseDown();
    }

    public String getButtonImageMouseOut()
    {
        return rswlBean.getButtonImageMouseOut();
    }

    public String getButtonImageMouseOver()
    {
        return rswlBean.getButtonImageMouseOver();
    }

    public String getButtonOnMouseOut()
    {
        return rswlBean.getButtonOnMouseOut();
    }

    public String getButtonOnMouseOver()
    {
        return rswlBean.getButtonOnMouseOver();
    }

    public String getButtonStyle()
    {
        return rswlBean.getButtonStyle();
    }

    public String getButtonText()
    {
        return rswlBean.getButtonText();
    }

    public String getCallBack()
    {
        return rswlBean.getCallBack();
    }

    public int getConsiderationRange()
    {
        return rswlBean.getConsiderationRange();
    }

    public boolean getCreatePopUpWindow()
    {
        return rswlBean.getCreatePopUpWindow();
    }

    public String getDictFile()
    {
        return rswlBean.getDictFile();
    }

    public String getFinishedListener()
    {
        return rswlBean.getFinishedListener();
    }

    public String getGuiLanguage()
    {
        return guiLang;
    }

    public String getId()
    {
        return rswlBean.getId();
    }

    public boolean getIgnoreCapitalizedWords()
    {
        return rswlBean.getIgnoreCapitalizedWords();
    }

    public boolean getIgnoreWordsWithDigits()
    {
        return rswlBean.getIgnoreWordsWithDigits();
    }

    public boolean getIgnoreXML()
    {
        return rswlBean.getIgnoreXML();
    }

    public boolean getIncludeUserDictionaryInSuggestions()
    {
        return rswlBean.getIncludeUserDictionaryInSuggestions();
    }

    public String getLanguageParser()
    {
        return langParser;
    }

    public boolean getModal()
    {
        return rswlBean.getModal();
    }

    public String getMode()
    {
        return rswlBean.getMode();
    }

    public String getPopUpWindowName()
    {
        return rswlBean.getPopUpWindowName();
    }

    public String getRSMultipleID()
    {
        return rswlBean.getRSMultipleID();
    }

    public String getRapidSpellWebPage()
    {
        return rswlBean.getRapidSpellWebPage();
    }

    public boolean getSSLFriendly()
    {
        return rswlBean.getSSLFriendly();
    }

    public boolean getSeparateHyphenWords()
    {
        return rswlBean.getSeparateHyphenWords();
    }

    public boolean getShowButton()
    {
        return rswlBean.getShowButton();
    }

    public boolean getShowFinishedMessage()
    {
        return rswlBean.getShowFinishedMessage();
    }

    public boolean getShowNoErrorsMessage()
    {
        return rswlBean.getShowNoErrorsMessage();
    }

    public boolean getShowXMLTags()
    {
        return rswlBean.getShowXMLTags();
    }

    public String getSuggestionsMethod()
    {
        return rswlBean.getSuggestionsMethod();
    }

    public int getTabIndex()
    {
        return rswlBean.getTabIndex();
    }

    public String getTextComponentInterface()
    {
        return rswlBean.getTextComponentInterface();
    }

    public String getTextComponentName()
    {
        return rswlBean.getTextComponentName();
    }

    public String getUserDictionaryFile()
    {
        return rswlBean.getUserDictionaryFile();
    }

    public boolean getWarnDuplicates()
    {
        return rswlBean.getWarnDuplicates();
    }

    public int getWindowHeight()
    {
        return rswlBean.getWindowHeight();
    }

    public int getWindowWidth()
    {
        return rswlBean.getWindowWidth();
    }

    public int getWindowX()
    {
        return rswlBean.getWindowX();
    }

    public int getWindowY()
    {
        return rswlBean.getWindowY();
    }

    public void setAllowAnyCase(boolean flag)
    {
        rswlBean.setAllowAnyCase(flag);
    }

    public void setAllowMixedCase(boolean flag)
    {
        rswlBean.setAllowMixedCase(flag);
    }

    public void setButtonImageMouseDown(String s)
    {
        rswlBean.setButtonImageMouseDown(s);
    }

    public void setButtonImageMouseOut(String s)
    {
        rswlBean.setButtonImageMouseOut(s);
    }

    public void setButtonImageMouseOver(String s)
    {
        rswlBean.setButtonImageMouseOver(s);
    }

    public void setButtonOnMouseOut(String s)
    {
        rswlBean.setButtonOnMouseOut(s);
    }

    public void setButtonOnMouseOver(String s)
    {
        rswlBean.setButtonOnMouseOver(s);
    }

    public void setButtonStyle(String s)
    {
        rswlBean.setButtonStyle(s);
    }

    public void setButtonText(String s)
    {
        rswlBean.setButtonText(s);
    }

    public void setCallBack(String s)
    {
        rswlBean.setCallBack(s);
    }

    public void setConsiderationRange(int i)
    {
        rswlBean.setConsiderationRange(i);
    }

    public void setCreatePopUpWindow(boolean flag)
    {
        rswlBean.setCreatePopUpWindow(flag);
    }

    public void setDictFile(String s)
    {
        rswlBean.setDictFile(s);
    }

    public void setFinishedListener(String s)
    {
        rswlBean.setFinishedListener(s);
    }

    public void setGuiLanguage(String s)
    {
        rswlBean.setGuiLanguage(LanguageType.getLanguageTypeFromString(s));
        guiLang = s;
    }

    public void setId(String s)
    {
        if(s != null)
            rswlBean.setId(s);
    }

    public void setIgnoreCapitalizedWords(boolean flag)
    {
        rswlBean.setIgnoreCapitalizedWords(flag);
    }

    public void setIgnoreWordsWithDigits(boolean flag)
    {
        rswlBean.setIgnoreWordsWithDigits(flag);
    }

    public void setIgnoreXML(boolean flag)
    {
        rswlBean.setIgnoreXML(flag);
    }

    public void setIncludeUserDictionaryInSuggestions(boolean flag)
    {
        rswlBean.setIncludeUserDictionaryInSuggestions(flag);
    }

    public void setLanguageParser(String s)
    {
        rswlBean.setLanguageParser(LanguageType.getLanguageTypeFromString(s));
        langParser = s;
    }

    public void setModal(boolean flag)
    {
        rswlBean.setModal(flag);
    }

    public void setMode(String s)
    {
        rswlBean.setMode(s);
    }

    public void setPopUpWindowName(String s)
    {
        rswlBean.setPopUpWindowName(s);
    }

    public void setRSMultipleID(String s)
    {
        rswlBean.setRSMultipleID(s);
    }

    public void setRapidSpellWebPage(String s)
    {
        rswlBean.setRapidSpellWebPage(s);
    }

    public void setSSLFriendly(boolean flag)
    {
        rswlBean.setSSLFriendly(flag);
    }

    public void setSeparateHyphenWords(boolean flag)
    {
        rswlBean.setSeparateHyphenWords(flag);
    }

    public void setShowButton(boolean flag)
    {
        rswlBean.setShowButton(flag);
    }

    public void setShowFinishedMessage(boolean flag)
    {
        rswlBean.setShowFinishedMessage(flag);
    }

    public void setShowNoErrorsMessage(boolean flag)
    {
        rswlBean.setShowNoErrorsMessage(flag);
    }

    public void setShowXMLTags(boolean flag)
    {
        rswlBean.setShowXMLTags(flag);
    }

    public void setSuggestionsMethod(String s)
    {
        rswlBean.setSuggestionsMethod(s);
    }

    public void setTabIndex(int i)
    {
        rswlBean.setTabIndex(i);
    }

    public void setTextComponentInterface(String s)
    {
        rswlBean.setTextComponentInterface(s);
    }

    public void setTextComponentName(String s)
    {
        rswlBean.setTextComponentName(s);
    }

    public void setUserDictionaryFile(String s)
    {
        rswlBean.setUserDictionaryFile(s);
    }

    public void setWarnDuplicates(boolean flag)
    {
        rswlBean.setWarnDuplicates(flag);
    }

    public void setWindowHeight(int i)
    {
        rswlBean.setWindowHeight(i);
    }

    public void setWindowWidth(int i)
    {
        rswlBean.setWindowWidth(i);
    }

    public void setWindowX(int i)
    {
        rswlBean.setWindowX(i);
    }

    public void setWindowY(int i)
    {
        rswlBean.setWindowY(i);
    }

    SpellCheckerWebLauncherBean rswlBean;
    String guiLang;
    String langParser;
}
