package mojo.util.spellcheck;

public class AdvancedTextBoundary extends SimpleTextBoundary
{

    public AdvancedTextBoundary()
    {
        ignoreXML = false;
    }

    public int following(int i)
    {
        if(ignoreXML)
        {
            int i1 = super.following(i);
            int j;
            int j1;
            if((j1 = i + super.theText.substring(i, i1).indexOf('<')) > i - 1 && (j = super.theText.indexOf("/script>", j1)) > -1 && super.theText.substring(j1, j1 + 7).indexOf("script") > -1)
            {
                j += 7;
                if(isAtNonWhiteSpace(i))
                {
                    if(j < super.theText.length() - 1 && isAtNonWhiteSpace(j + 1))
                        if(j < super.theText.length())
                            return j + 1;
                        else
                            return j;
                    if(j < super.theText.length())
                        return j + 1;
                    else
                        return j;
                } else
                {
                    return following(j);
                }
            }
            if((j1 = i + super.theText.substring(i, i1).indexOf('<')) > i - 1)
                if((j = super.theText.indexOf('>', j1)) > -1)
                {
                    if(isAtNonWhiteSpace(i))
                    {
                        if(j < super.theText.length() - 1 && isAtNonWhiteSpace(j + 1))
                        {
                            if(super.theText.substring(j1, j + 1).toLowerCase().indexOf("font") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("strong") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("superfont") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<big>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</big>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<small>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</small>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<tt>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</tt>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<em>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</em>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<pre>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</pre>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<b>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</b>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<s>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</s>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<i>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</i>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("<u>") > -1 || super.theText.substring(j1, j + 1).toLowerCase().indexOf("</u>") > -1)
                                return following(j + 1);
                            if(j < super.theText.length())
                                return j + 1;
                            else
                                return j;
                        }
                        if(j < super.theText.length())
                            return j + 1;
                        else
                            return j;
                    } else
                    {
                        return following(j);
                    }
                } else
                {
                    return i1;
                }
            if((j1 = i + super.theText.substring(i, i1).indexOf('&')) > i - 1)
                if((j = super.theText.indexOf(';', j1)) > -1)
                {
                    if(isAtNonWhiteSpace(i))
                    {
                        if(j < super.theText.length() - 1 && isAtNonWhiteSpace(j + 1))
                        {
                            if(super.theText.substring(j1, j).toLowerCase().indexOf("amp") > -1)
                                return following(j + 1);
                            if(j < super.theText.length())
                                return j + 1;
                            else
                                return j;
                        }
                        if(j < super.theText.length())
                            return j + 1;
                        else
                            return j;
                    } else
                    {
                        return following(j);
                    }
                } else
                {
                    return i1;
                }
            if(i1 < super.theText.length() && isAtNonWhiteSpace(i) && super.theText.charAt(i1) == '<')
            {
                int k1 = i1;
                int k;
                if(k1 > -1)
                    k = super.theText.indexOf('>', k1);
                else
                    k = -1;
                if(k > -1 && (k >= super.theText.length() - 1 || !isAtNonWhiteSpace(k + 1)))
                    return i1;
                if(k1 > -1 && k > -1 && (super.theText.substring(k1, k + 1).toLowerCase().indexOf("font") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("strong") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("superfont") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<big>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</big>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<small>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</small>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<tt>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</tt>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<em>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</em>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<pre>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</pre>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<b>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</b>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<s>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</s>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<i>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</i>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("<u>") > -1 || super.theText.substring(k1, k + 1).toLowerCase().indexOf("</u>") > -1))
                {
                    if(k < super.theText.length() - 1 && isAtNonWhiteSpace(k + 1))
                        return following(k + 1);
                    if(k < super.theText.length())
                        return k + 1;
                    else
                        return k;
                }
                if(i1 < super.theText.length() && isAtNonWhiteSpace(i) && super.theText.charAt(i1) == '&')
                {
                    int l1 = i1;
                    int l;
                    if(l1 > -1)
                        l = super.theText.indexOf(';', l1);
                    else
                        l = -1;
                    if(l1 > -1 && l > -1 && super.theText.substring(l1, (l - l1) + 1).toLowerCase().indexOf("amp") > -1)
                        return following(l + 1);
                    if(l < super.theText.length())
                        return l + 1;
                    else
                        return l;
                } else
                {
                    return i1;
                }
            } else
            {
                return i1;
            }
        } else
        {
            return super.following(i);
        }
    }

    protected boolean isAtNonWhiteSpace(int i)
    {
        return (super.theText.charAt(i) != '&' && ignoreXML || !ignoreXML) && super.isAtNonWhiteSpace(i);
    }

    boolean isInsideXMLTag(int i)
    {
        if(i <= 0 || i >= super.theText.length())
        {
            return false;
        } else
        {
            int j = super.theText.lastIndexOf('<', i);
            int k = super.theText.lastIndexOf('>', i);
            int l = super.theText.indexOf('<', i);
            int i1 = super.theText.indexOf('>', i);
            return j > -1 && i1 > -1 && k < j && l > i1;
        }
    }

    public int preceding(int i)
    {
        if(ignoreXML)
        {
            int l = super.preceding(i);
            int j;
            int i1;
            if(l > 0 && (i1 = l + super.theText.substring(l, i).indexOf('>')) > l - 1)
                if((j = super.theText.lastIndexOf('<', i1)) > -1)
                {
                    if(isAtNonWhiteSpace(i))
                    {
                        if(j < super.theText.length() - 1 && isAtNonWhiteSpace(j + 1))
                        {
                            if(super.theText.substring(j, i1 + 1).toLowerCase().indexOf("font") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("strong") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("superfont") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<big>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</big>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<small>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</small>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<tt>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</tt>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<em>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</em>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<pre>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</pre>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<b>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</b>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<s>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</s>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<i>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</i>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("<u>") > -1 || super.theText.substring(j, i1 + 1).toLowerCase().indexOf("</u>") > -1)
                                return preceding(j);
                            if(j > 0)
                                return j - 1;
                            else
                                return j;
                        }
                        if(j > 0)
                            return j - 1;
                        else
                            return j;
                    } else
                    {
                        return preceding(j);
                    }
                } else
                {
                    return l;
                }
            if(isInsideXMLTag(i))
                return super.preceding(super.theText.lastIndexOf('<', i));
            if(l > 0 && isAtNonWhiteSpace(i) && super.theText.charAt(l - 1) == '>')
            {
                int j1 = l;
                int k;
                if(j1 > -1)
                    k = super.theText.lastIndexOf('<', j1);
                else
                    k = -1;
                if(k > -1 && j1 > -1 && (super.theText.substring(k, j1 + 1).toLowerCase().indexOf("font") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("strong") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("superfont") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<big>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</big>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<small>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</small>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<tt>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</tt>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<em>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</em>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<pre>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</pre>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<b>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</b>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<s>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</s>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<i>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</i>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("<u>") > -1 || super.theText.substring(k, j1 + 1).toLowerCase().indexOf("</u>") > -1))
                {
                    if(k > 0 && isAtNonWhiteSpace(k - 1))
                        return preceding(k);
                    if(k > 0)
                        return k - 1;
                    else
                        return k;
                } else
                {
                    return l;
                }
            } else
            {
                return l;
            }
        } else
        {
            return super.preceding(i);
        }
    }

    public boolean ignoreXML;
}
