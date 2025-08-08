package mojo.ui.common;

import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.JDialog;

/**
 * This package private class is used to decouple the unique behaviors of
 * the various possible titleable components by providing a common interface.
 * Concrete implementations of this class will provide the appropriate type
 * casting and method invocation for accessing titles.
 * @modelguid {0B9BA9C8-9886-47EB-A297-DA853FDB95D6}
 */
abstract class TitledComponent
{
    /** The component for which we're managing titles 
     * @modelguid {8F363726-7850-4C67-9763-7F3895EA4E36}
     */
    protected final Component mComponent;
    
    /** Method for setting the title text on 'mComponent' 
     * @modelguid {226EF23A-ADC4-4CEF-B87F-B8438B20B830}
     */
    protected abstract void setTitleText( String title );
    
    /**
     * Instantiate a new title manager.
     * @param component component to manage a title for
     * @modelguid {62C6E26F-DF8A-4E97-B55F-24E5BA967838}
     */
    public TitledComponent( Component component )
    {
        mComponent = component;
    }
    
    /** Set the title on the component, then make sure it gets
     *  repainted.
     * @modelguid {5CEB752B-0E53-4E51-88EB-7C192683434E}
     */
    public void setTitle( String title )
    {
        setTitleText(title);
        mComponent.validate();
        mComponent.repaint();
    }
    
    /** Mehtod for getting the title text from 'mComponent' 
     * @modelguid {34154F5C-71E7-44B2-9B9F-7F84C38C9F81}
     */
    public abstract String getTitle();
};
