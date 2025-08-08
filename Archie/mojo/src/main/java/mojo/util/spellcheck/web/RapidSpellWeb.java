package mojo.util.spellcheck.web;

import mojo.util.spellcheck.ICheckerEngine;
import mojo.util.spellcheck.UserDictionary;
import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class RapidSpellWeb extends TagSupport
{

    public RapidSpellWeb()
    {
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
            rswBean.writeHtml(jspwriter, super.pageContext.getRequest());
            rswBean.freeResources();
        }
        catch(IOException _ex)
        {
            throw new JspTagException("JspWriter not there!!");
        }
        return 0;
    }

    public String getAddButtonOnMouseOut()
    {
        return rswBean.getAddButtonOnMouseOut();
    }

    public String getAddButtonOnMouseOver()
    {
        return rswBean.getAddButtonOnMouseOver();
    }

    public String getAddButtonStyle()
    {
        return rswBean.getAddButtonStyle();
    }

    public String getAddButtonStyleClass()
    {
        return rswBean.getAddButtonStyleClass();
    }

    public String getAddButtonText()
    {
        return rswBean.getAddButtonText();
    }

    public String getBadWordHighlightStyle()
    {
        return rswBean.getBadWordHighlightStyle();
    }

    public String getChangeAllButtonOnMouseOut()
    {
        return rswBean.getChangeAllButtonOnMouseOut();
    }

    public String getChangeAllButtonOnMouseOver()
    {
        return rswBean.getChangeAllButtonOnMouseOver();
    }

    public String getChangeAllButtonStyle()
    {
        return rswBean.getChangeAllButtonStyle();
    }

    public String getChangeAllButtonStyleClass()
    {
        return rswBean.getChangeAllButtonStyleClass();
    }

    public String getChangeAllButtonText()
    {
        return rswBean.getChangeAllButtonText();
    }

    public String getChangeButtonOnMouseOut()
    {
        return rswBean.getChangeButtonOnMouseOut();
    }

    public String getChangeButtonOnMouseOver()
    {
        return rswBean.getChangeButtonOnMouseOver();
    }

    public String getChangeButtonStyle()
    {
        return rswBean.getChangeButtonStyle();
    }

    public String getChangeButtonStyleClass()
    {
        return rswBean.getChangeButtonStyleClass();
    }

    public String getChangeButtonText()
    {
        return rswBean.getChangeButtonText();
    }

    public String getChangeToBoxStyle()
    {
        return rswBean.getChangeToBoxStyle();
    }

    public String getChangeToBoxStyleClass()
    {
        return rswBean.getChangeToBoxStyleClass();
    }

    public String getChangeToLabel()
    {
        return rswBean.getChangeToLabel();
    }

    public String getChangeToLabelStyle()
    {
        return rswBean.getChangeToLabelStyle();
    }

    public String getChangeToLabelStyleClass()
    {
        return rswBean.getChangeToLabelStyleClass();
    }

    public ICheckerEngine getCheckerEngine()
    {
        return rswBean.getCheckerEngine();
    }

    public String getFinishButtonOnMouseOut()
    {
        return rswBean.getFinishButtonOnMouseOut();
    }

    public String getFinishButtonOnMouseOver()
    {
        return rswBean.getFinishButtonOnMouseOver();
    }

    public String getFinishButtonStyle()
    {
        return rswBean.getFinishButtonStyle();
    }

    public String getFinishButtonStyleClass()
    {
        return rswBean.getFinishButtonStyleClass();
    }

    public String getFinishButtonText()
    {
        return rswBean.getFinishButtonText();
    }

    public String getIgnoreAllButtonOnMouseOut()
    {
        return rswBean.getIgnoreAllButtonOnMouseOut();
    }

    public String getIgnoreAllButtonOnMouseOver()
    {
        return rswBean.getIgnoreAllButtonOnMouseOver();
    }

    public String getIgnoreAllButtonStyle()
    {
        return rswBean.getIgnoreAllButtonStyle();
    }

    public String getIgnoreAllButtonStyleClass()
    {
        return rswBean.getIgnoreAllButtonStyleClass();
    }

    public String getIgnoreAllButtonText()
    {
        return rswBean.getIgnoreAllButtonText();
    }

    public String getIgnoreButtonOnMouseOut()
    {
        return rswBean.getIgnoreButtonOnMouseOut();
    }

    public String getIgnoreButtonOnMouseOver()
    {
        return rswBean.getIgnoreButtonOnMouseOver();
    }

    public String getIgnoreButtonStyle()
    {
        return rswBean.getIgnoreButtonStyle();
    }

    public String getIgnoreButtonStyleClass()
    {
        return rswBean.getIgnoreButtonStyleClass();
    }

    public String getIgnoreButtonText()
    {
        return rswBean.getIgnoreButtonText();
    }

    public String getLayout()
    {
        return rswBean.getLayout();
    }

    public String getLicenseKey()
    {
        return rswBean.getLicenseKey();
    }

    public int getPreviewPaneHeight()
    {
        return rswBean.getPreviewPaneHeight();
    }

    public String getPreviewPaneStyle()
    {
        return rswBean.getPreviewPaneStyle();
    }

    public int getPreviewPaneWidth()
    {
        return rswBean.getPreviewPaneWidth();
    }

    public boolean getSSLFriendly()
    {
        return rswBean.getSSLFriendly();
    }

    public int getScriptFilterLevel()
    {
        return rswBean.getScriptFilterLevel();
    }

    public String getSuggestionsBoxStyle()
    {
        return rswBean.getSuggestionsBoxStyle();
    }

    public String getSuggestionsBoxStyleClass()
    {
        return rswBean.getSuggestionsBoxStyleClass();
    }

    public String getSuggestionsLabel()
    {
        return rswBean.getSuggestionsLabel();
    }

    public String getSuggestionsLabelStyle()
    {
        return rswBean.getSuggestionsLabelStyle();
    }

    public String getSuggestionsLabelStyleClass()
    {
        return rswBean.getSuggestionsLabelStyleClass();
    }

    public UserDictionary getUserDictionary()
    {
        return rswBean.getUserDictionary();
    }

    public void setAddButtonOnMouseOut(String s)
    {
        rswBean.setAddButtonOnMouseOut(s);
    }

    public void setAddButtonOnMouseOver(String s)
    {
        rswBean.setAddButtonOnMouseOver(s);
    }

    public void setAddButtonStyle(String s)
    {
        rswBean.setAddButtonStyle(s);
    }

    public void setAddButtonStyleClass(String s)
    {
        rswBean.setAddButtonStyleClass(s);
    }

    public void setAddButtonText(String s)
    {
        rswBean.setAddButtonText(s);
    }

    public void setBadWordHighlightStyle(String s)
    {
        rswBean.setBadWordHighlightStyle(s);
    }

    public void setChangeAllButtonOnMouseOut(String s)
    {
        rswBean.setChangeAllButtonOnMouseOut(s);
    }

    public void setChangeAllButtonOnMouseOver(String s)
    {
        rswBean.setChangeAllButtonOnMouseOver(s);
    }

    public void setChangeAllButtonStyle(String s)
    {
        rswBean.setChangeAllButtonStyle(s);
    }

    public void setChangeAllButtonStyleClass(String s)
    {
        rswBean.setChangeAllButtonStyleClass(s);
    }

    public void setChangeAllButtonText(String s)
    {
        rswBean.setChangeAllButtonText(s);
    }

    public void setChangeButtonOnMouseOut(String s)
    {
        rswBean.setChangeButtonOnMouseOut(s);
    }

    public void setChangeButtonOnMouseOver(String s)
    {
        rswBean.setChangeButtonOnMouseOver(s);
    }

    public void setChangeButtonStyle(String s)
    {
        rswBean.setChangeButtonStyle(s);
    }

    public void setChangeButtonStyleClass(String s)
    {
        rswBean.setChangeButtonStyleClass(s);
    }

    public void setChangeButtonText(String s)
    {
        rswBean.setChangeButtonText(s);
    }

    public void setChangeToBoxStyle(String s)
    {
        rswBean.setChangeToBoxStyle(s);
    }

    public void setChangeToBoxStyleClass(String s)
    {
        rswBean.setChangeToBoxStyleClass(s);
    }

    public void setChangeToLabel(String s)
    {
        rswBean.setChangeToLabel(s);
    }

    public void setChangeToLabelStyle(String s)
    {
        rswBean.setChangeToLabelStyle(s);
    }

    public void setChangeToLabelStyleClass(String s)
    {
        rswBean.setChangeToLabelStyleClass(s);
    }

    public void setCheckerEngine(ICheckerEngine icheckerengine)
    {
        rswBean.setCheckerEngine(icheckerengine);
    }

    public void setFinishButtonOnMouseOut(String s)
    {
        rswBean.setFinishButtonOnMouseOut(s);
    }

    public void setFinishButtonOnMouseOver(String s)
    {
        rswBean.setFinishButtonOnMouseOver(s);
    }

    public void setFinishButtonStyle(String s)
    {
        rswBean.setFinishButtonStyle(s);
    }

    public void setFinishButtonStyleClass(String s)
    {
        rswBean.setFinishButtonStyleClass(s);
    }

    public void setFinishButtonText(String s)
    {
        rswBean.setFinishButtonText(s);
    }

    public void setIgnoreAllButtonOnMouseOut(String s)
    {
        rswBean.setIgnoreAllButtonOnMouseOut(s);
    }

    public void setIgnoreAllButtonOnMouseOver(String s)
    {
        rswBean.setIgnoreAllButtonOnMouseOver(s);
    }

    public void setIgnoreAllButtonStyle(String s)
    {
        rswBean.setIgnoreAllButtonStyle(s);
    }

    public void setIgnoreAllButtonStyleClass(String s)
    {
        rswBean.setIgnoreAllButtonStyleClass(s);
    }

    public void setIgnoreAllButtonText(String s)
    {
        rswBean.setIgnoreAllButtonText(s);
    }

    public void setIgnoreButtonOnMouseOut(String s)
    {
        rswBean.setIgnoreButtonOnMouseOut(s);
    }

    public void setIgnoreButtonOnMouseOver(String s)
    {
        rswBean.setIgnoreButtonOnMouseOver(s);
    }

    public void setIgnoreButtonStyle(String s)
    {
        rswBean.setIgnoreButtonStyle(s);
    }

    public void setIgnoreButtonStyleClass(String s)
    {
        rswBean.setIgnoreButtonStyleClass(s);
    }

    public void setIgnoreButtonText(String s)
    {
        rswBean.setIgnoreButtonText(s);
    }

    public void setLayout(String s)
    {
        rswBean.setLayout(s);
    }

    public void setLicenseKey(String s)
    {
        rswBean.setLicenseKey(s);
    }

    public void setPageContext(PageContext pagecontext)
    {
        rswBean = new SpellCheckerWebBean();
        super.setPageContext(pagecontext);
    }

    public void setPreviewPaneHeight(int i)
    {
        rswBean.setPreviewPaneHeight(i);
    }

    public void setPreviewPaneStyle(String s)
    {
        rswBean.setPreviewPaneStyle(s);
    }

    public void setPreviewPaneWidth(int i)
    {
        rswBean.setPreviewPaneWidth(i);
    }

    public void setSSLFriendly(boolean flag)
    {
        rswBean.setSSLFriendly(flag);
    }

    public void setScriptFilterLevel(int i)
    {
        rswBean.setScriptFilterLevel(i);
    }

    public void setSuggestionsBoxStyle(String s)
    {
        rswBean.setSuggestionsBoxStyle(s);
    }

    public void setSuggestionsBoxStyleClass(String s)
    {
        rswBean.setSuggestionsBoxStyleClass(s);
    }

    public void setSuggestionsLabel(String s)
    {
        rswBean.setSuggestionsLabel(s);
    }

    public void setSuggestionsLabelStyle(String s)
    {
        rswBean.setSuggestionsLabelStyle(s);
    }

    public void setSuggestionsLabelStyleClass(String s)
    {
        rswBean.setSuggestionsLabelStyleClass(s);
    }

    public void setUserDictionary(UserDictionary userdictionary)
    {
        rswBean.setUserDictionary(userdictionary);
    }

    SpellCheckerWebBean rswBean;
}
