package mojo.util.spellcheck.web;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class SpellCheckerWebMultiple extends TagSupport
{

    public SpellCheckerWebMultiple()
    {
        rswmBean = new SpellCheckerWebMultipleBean();
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
            rswmBean.writeHtml(jspwriter);
        }
        catch(IOException _ex)
        {
            throw new JspTagException("JspWriter not there!!");
        }
        return 0;
    }

    public String getButtonImageMouseDown()
    {
        return rswmBean.getButtonImageMouseDown();
    }

    public String getButtonImageMouseOut()
    {
        return rswmBean.getButtonImageMouseOut();
    }

    public String getButtonImageMouseOver()
    {
        return rswmBean.getButtonImageMouseOver();
    }

    public String getButtonOnMouseOut()
    {
        return rswmBean.getButtonOnMouseOut();
    }

    public String getButtonOnMouseOver()
    {
        return rswmBean.getButtonOnMouseOver();
    }

    public String getButtonStyle()
    {
        return rswmBean.getButtonStyle();
    }

    public String getButtonText()
    {
        return rswmBean.getButtonText();
    }

    public String getFinishedListener()
    {
        return rswmBean.getFinishedListener();
    }

    public String getFinishedOnTextBoxListener()
    {
        return rswmBean.getFinishedOnTextBoxListener();
    }

    public String getId()
    {
        return rswmBean.getId();
    }

    public boolean getIgnoreEmptyTextBoxes()
    {
        return rswmBean.getIgnoreEmptyTextBoxes();
    }

    public String getRapidSpellWebLaunchers()
    {
        return rswmBean.getRapidSpellWebLaunchers().toString();
    }

    public boolean getSSLFriendly()
    {
        return rswmBean.getSSLFriendly();
    }

    public boolean getShowButton()
    {
        return rswmBean.getShowButton();
    }

    public boolean getShowFinishedMessage()
    {
        return rswmBean.getShowFinishedMessage();
    }

    public String getStartedOnTextBoxListener()
    {
        return rswmBean.getStartedOnTextBoxListener();
    }

    public int getTabIndex()
    {
        return rswmBean.getTabIndex();
    }

    public int getWindowHeight()
    {
        return rswmBean.getWindowHeight();
    }

    public int getWindowWidth()
    {
        return rswmBean.getWindowWidth();
    }

    public int getWindowX()
    {
        return rswmBean.getWindowX();
    }

    public int getWindowY()
    {
        return rswmBean.getWindowY();
    }

    public void setButtonImageMouseDown(String s)
    {
        rswmBean.setButtonImageMouseDown(s);
    }

    public void setButtonImageMouseOut(String s)
    {
        rswmBean.setButtonImageMouseOut(s);
    }

    public void setButtonImageMouseOver(String s)
    {
        rswmBean.setButtonImageMouseOver(s);
    }

    public void setButtonOnMouseOut(String s)
    {
        rswmBean.setButtonOnMouseOut(s);
    }

    public void setButtonOnMouseOver(String s)
    {
        rswmBean.setButtonOnMouseOver(s);
    }

    public void setButtonStyle(String s)
    {
        rswmBean.setButtonStyle(s);
    }

    public void setButtonText(String s)
    {
        rswmBean.setButtonText(s);
    }

    public void setFinishedListener(String s)
    {
        rswmBean.setFinishedListener(s);
    }

    public void setFinishedOnTextBoxListener(String s)
    {
        rswmBean.setFinishedOnTextBoxListener(s);
    }

    public void setId(String s)
    {
        if(s != null)
            rswmBean.setId(s);
    }

    public void setIgnoreEmptyTextBoxes(boolean flag)
    {
        rswmBean.setIgnoreEmptyTextBoxes(flag);
    }

    public void setRapidSpellWebLaunchers(String s)
    {
        rswmBean.setRapidSpellWebLaunchers(new SpellCheckerWebLauncherCollection(s));
    }

    public void setSSLFriendly(boolean flag)
    {
        rswmBean.setSSLFriendly(flag);
    }

    public void setShowButton(boolean flag)
    {
        rswmBean.setShowButton(flag);
    }

    public void setShowFinishedMessage(boolean flag)
    {
        rswmBean.setShowFinishedMessage(flag);
    }

    public void setStartedOnTextBoxListener(String s)
    {
        rswmBean.setStartedOnTextBoxListener(s);
    }

    public void setTabIndex(int i)
    {
        rswmBean.setTabIndex(i);
    }

    public void setWindowHeight(int i)
    {
        rswmBean.setWindowHeight(i);
    }

    public void setWindowWidth(int i)
    {
        rswmBean.setWindowWidth(i);
    }

    public void setWindowX(int i)
    {
        rswmBean.setWindowX(i);
    }

    public void setWindowY(int i)
    {
        rswmBean.setWindowY(i);
    }

    SpellCheckerWebMultipleBean rswmBean;
}
