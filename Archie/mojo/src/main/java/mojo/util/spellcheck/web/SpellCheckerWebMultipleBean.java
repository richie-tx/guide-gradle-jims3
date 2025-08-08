package mojo.util.spellcheck.web;

import mojo.util.spellcheck.CultureText;
import mojo.util.spellcheck.LanguageType;
import java.io.IOException;
import java.io.Writer;
import java.util.Hashtable;

public class SpellCheckerWebMultipleBean
{

    public SpellCheckerWebMultipleBean()
    {
        _guiLanguage = 10001;
        _showButton = true;
        _ShowFinishedMessage = true;
        _FinishedListener = "";
        _buttonText = "Check Spelling";
        _tabIndex = 0;
        _RapidSpellWebLaunchers = new SpellCheckerWebLauncherCollection();
        _IgnoreEmptyTextBoxes = true;
        _windowWidth = 370;
        _windowHeight = 400;
        _windowX = 100;
        _windowY = 100;
        _ButtonImages = new String[3];
        _sslFriendly = false;
        _StartedOnTextBoxListener = "";
        _FinishedOnTextBoxListener = "";
        _Button = new RSButton("button");
        updateUIText();
    }

    public RSButton getButton()
    {
        return _Button;
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
        return (String)((HtmlElement) (_Button)).attributes.get("onMouseOut");
    }

    public String getButtonOnMouseOver()
    {
        return (String)((HtmlElement) (_Button)).attributes.get("onMouseOver");
    }

    public String getButtonStyle()
    {
        return (String)((HtmlElement) (_Button)).attributes.get("style");
    }

    public String getButtonText()
    {
        return _buttonText;
    }

    String getControlClientScript()
    {
        String s = "";
        String s1 = getId() + "_PopUpWin";
        String s2 = "";
        if(_FinishedListener != null && !_FinishedListener.equals(""))
            s2 = _FinishedListener + "();";
        String s3 = "";
        if(_ShowFinishedMessage)
            s3 = "if(spellCheckFinished)alert('" + CultureText.get("The spelling check is complete.", _guiLanguage) + "');";
        if(getRapidSpellWebLaunchers() != null)
        {
            for(int i = 0; i < getRapidSpellWebLaunchers().Count(); i++)
            {
                s = s + "\"" + (String)getRapidSpellWebLaunchers().get(i) + "\"";
                if(i < getRapidSpellWebLaunchers().Count() - 1)
                    s = s + ",";
            }

        }
        String s4 = getSSLFriendly() ? "blank.html" : "";
        String s5 = "<script type=text/javascript>var checking = false;var " + s1 + ";" + "var rapidSpellControls = [" + s + "];" + "var currentlyChecking = 0;" + "function " + getId() + "_RunSpellCheck(buttonClick){" + "if (buttonClick && " + s1 + "!=null && !" + s1 + ".closed) {" + s1 + ".focus();" + "} else {" + "if (buttonClick) {checking=true;currentlyChecking=0;}" + "if(!(" + (_IgnoreEmptyTextBoxes ? "true" : "false") + " && eval('rsTCInt'+rapidSpellControls[currentlyChecking]+'.getText()==\"\"'))){\n" + "if (checking && (" + s1 + "==null || " + s1 + ".closed)){ " + s1 + " = " + "window.open('" + s4 + "', 'rspellwin', 'resizable=yes,scrollbars=auto,dependent=yes,toolbar=no," + (_windowX < 0 ? "" : "left=" + _windowX + ",") + (_windowY < 0 ? "" : "top=" + _windowY + ",") + "status=no,location=no,menubar=no,width=" + _windowWidth + ",height=" + _windowHeight + "');" + "}" + "if (" + s1 + "!=null && !" + s1 + ".closed){\n" + (_StartedOnTextBoxListener == "" ? "" : _StartedOnTextBoxListener + "(document.getElementById(eval(\"rsTCInt\"+rapidSpellControls[currentlyChecking]+\".tbName\")));") + "eval(\"popUpCheckSpelling\"+rapidSpellControls[currentlyChecking]+\"('rsTCInt\"+rapidSpellControls[currentlyChecking]+\"')\");\n" + "}" + "} else " + getId() + "_NotifyDone(true);\n" + "}" + "} function " + getId() + "_NotifyDone(spellCheckFinished){currentlyChecking++;" + (_FinishedOnTextBoxListener == "" ? "" : _FinishedOnTextBoxListener + "(document.getElementById(eval(\"rsTCInt\"+rapidSpellControls[currentlyChecking-1]+\".tbName\")));") + ";if(currentlyChecking < rapidSpellControls.length && spellCheckFinished){" + "" + getId() + "_RunSpellCheck(false);} else {checking = false;if(" + s1 + ")" + s1 + ".close();currentlyChecking=0;" + s3 + s2 + "}}" + "</script>";
        return s5;
    }

    public String getFinishedListener()
    {
        return _FinishedListener;
    }

    public String getFinishedOnTextBoxListener()
    {
        return _FinishedOnTextBoxListener;
    }

    public int getGuiLanguage()
    {
        return _guiLanguage;
    }

    public String getId()
    {
        return _id;
    }

    public boolean getIgnoreEmptyTextBoxes()
    {
        return _IgnoreEmptyTextBoxes;
    }

    public SpellCheckerWebLauncherCollection getRapidSpellWebLaunchers()
    {
        return _RapidSpellWebLaunchers;
    }

    public boolean getSSLFriendly()
    {
        return _sslFriendly;
    }

    public boolean getShowButton()
    {
        return _showButton;
    }

    public boolean getShowFinishedMessage()
    {
        return _ShowFinishedMessage;
    }

    public String getStartedOnTextBoxListener()
    {
        return _StartedOnTextBoxListener;
    }

    public int getTabIndex()
    {
        return _tabIndex;
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
        ((HtmlElement) (_Button)).attributes.put("onMouseOut", s);
    }

    public void setButtonOnMouseOver(String s)
    {
        ((HtmlElement) (_Button)).attributes.put("onMouseOver", s);
    }

    public void setButtonStyle(String s)
    {
        ((HtmlElement) (_Button)).attributes.put("style", s);
    }

    public void setButtonText(String s)
    {
        _buttonText = s;
    }

    public void setFinishedListener(String s)
    {
        _FinishedListener = s;
    }

    public void setFinishedOnTextBoxListener(String s)
    {
        _FinishedOnTextBoxListener = s;
    }

    public void setGuiLanguage(int i)
    {
        _guiLanguage = i;
        updateUIText();
    }

    public void setId(String s)
    {
        if(s != null)
            _id = s;
    }

    public void setIgnoreEmptyTextBoxes(boolean flag)
    {
        _IgnoreEmptyTextBoxes = flag;
    }

    public void setRapidSpellWebLaunchers(SpellCheckerWebLauncherCollection rapidspellweblaunchercollection)
    {
        _RapidSpellWebLaunchers = rapidspellweblaunchercollection;
    }

    public void setSSLFriendly(boolean flag)
    {
        _sslFriendly = flag;
    }

    public void setShowButton(boolean flag)
    {
        _showButton = flag;
    }

    public void setShowFinishedMessage(boolean flag)
    {
        _ShowFinishedMessage = flag;
    }

    public void setStartedOnTextBoxListener(String s)
    {
        _StartedOnTextBoxListener = s;
    }

    public void setTabIndex(int i)
    {
        _tabIndex = i;
        _Button.tabIndex = i;
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

    void updateUIText()
    {
        _Button.value = CultureText.get(_buttonText, _guiLanguage);
    }

    public void writeHtml(Writer writer)
        throws IOException
    {
        String s = getId() + "_RunSpellCheck(true)";
        ((HtmlElement) (_Button)).attributes.put("onClick", s);
        writer.write(getControlClientScript());
        if(_showButton)
            if(_ButtonImages[0] == null || _ButtonImages[0].equals(""))
            {
                _Button.renderControl(writer);
            } else
            {
                String s1 = "";
                if(((HtmlInput) (_Button)).tabIndex > 0)
                    s1 = "TabIndex=" + ((HtmlInput) (_Button)).tabIndex;
                writer.write("<script>" + getId() + "Out=new Image();" + getId() + "Out.src=\"" + _ButtonImages[0] + "\";" + getId() + "Over=new Image();" + getId() + "Over.src=\"" + (_ButtonImages[1] == null ? _ButtonImages[0] : _ButtonImages[1]) + "\";" + getId() + "Down=new Image();" + getId() + "Down.src=\"" + (_ButtonImages[2] == null ? _ButtonImages[0] : _ButtonImages[2]) + "\";</script>");
                writer.write("<a href=\"#stayhere\"  onClick=\"" + s + "\" " + s1 + "><img border='0' src=\"" + _ButtonImages[0] + "\" onMouseOver=\"this.src=" + getId() + "Over.src\" onMouseOut=\"this.src=" + getId() + "Out.src\" onMouseDown=\"this.src=" + getId() + "Down.src\" onMouseUp=\"this.src=" + getId() + "Over.src\"></a>");
            }
    }

    int _guiLanguage;
    RSButton _Button;
    boolean _showButton;
    boolean _ShowFinishedMessage;
    String _FinishedListener;
    String _buttonText;
    int _tabIndex;
    SpellCheckerWebLauncherCollection _RapidSpellWebLaunchers;
    boolean _IgnoreEmptyTextBoxes;
    int _windowWidth;
    int _windowHeight;
    int _windowX;
    int _windowY;
    String _ButtonImages[];
    boolean _sslFriendly;
    String _StartedOnTextBoxListener;
    String _FinishedOnTextBoxListener;
    String _id;
}
