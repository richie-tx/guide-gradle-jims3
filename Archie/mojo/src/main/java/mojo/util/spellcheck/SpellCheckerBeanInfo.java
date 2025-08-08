package mojo.util.spellcheck;

import java.awt.Image;
import java.beans.*;
import javax.swing.ImageIcon;

public class SpellCheckerBeanInfo extends SimpleBeanInfo
{

    public SpellCheckerBeanInfo()
    {
        beanClass = SpellChecker.class;
    }

    public BeanInfo[] getAdditionalBeanInfo()
    {
        try
        {
            BeanInfo abeaninfo[] = new BeanInfo[1];
            abeaninfo[0] = Introspector.getBeanInfo(beanClass.getSuperclass());
            return abeaninfo;
        }
        catch(IntrospectionException introspectionexception)
        {
            throw new Error(introspectionexception.toString());
        }
    }

    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor beandescriptor = new BeanDescriptor(beanClass);
        return beandescriptor;
    }

    public Image getIcon(int i)
    {
        String s = "/mono-icon-16.gif";
        if(i == 3)
            s = "/mono-icon-16.gif";
        if(i == 4)
            s = "/mono-icon-32.gif";
        if(i == 1)
            s = "/icon-16.gif";
        if(i == 2)
            s = "/icon-32.gif";
        Image image = (new ImageIcon((SpellCheckerBeanInfo.class).getResource(s))).getImage();
        return image;
    }

    private final Class beanClass;
}
