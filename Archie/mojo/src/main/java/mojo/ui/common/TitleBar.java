package mojo.ui.common;

import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.JDialog;

/**
 * A class for accessing the title bar of a frame without knowing the frame's
 * concrete type.  An instance is typically passed to a panel through
 * its constructor.
 * @modelguid {ED0CFE4D-41ED-4F1D-B6D5-29CE6B8A1539}
 */
public class TitleBar 
{
    /** 
     * An object containg a reference to the frame and encapsulating the
     * specific behavior required for modifying the title bar.
     * @modelguid {21A84985-F645-4B3A-85CC-97108F07A9C0}
     */
    private final TitledComponent mTitledComponent;
    
    /** 
     * Instantiate a TitleBar object for a frame.
     * @param component component to manage a title for
     * @exception RuntimeException thrown if 'component' is not a JInternalFrame,
     *            JFrame, or JDialog.
     * @modelguid {EE10C46C-7B7B-42DA-B79C-D28A48FEEB35}
     */
	public TitleBar( Component windowtype ) 
	{
	    // if component is a JInternalFrame
	    if (windowtype instanceof JInternalFrame)
	    {
	        mTitledComponent = new TitledComponent(windowtype) {
                protected void setTitleText( String title ) {
	                ((JInternalFrame) mComponent).setTitle(title);
                }
                public String getTitle() {
    	           return ((JInternalFrame) mComponent).getTitle();
                }
	        };
	    }
	    
	    // if component is a JFrame
	    else if (windowtype instanceof JFrame)
	    {
	        mTitledComponent = new TitledComponent(windowtype) {
                protected void setTitleText( String title ) {
	                ((JFrame) mComponent).setTitle(title);
                }
                public String getTitle() {
    	           return ((JFrame) mComponent).getTitle();
                }
	        };
	    }
	    
	    // if component is a JDIalog
	    else if(windowtype instanceof JDialog)
	    {
	        mTitledComponent = new TitledComponent(windowtype) {
                protected void setTitleText( String title ) {
	                ((JDialog) mComponent).setTitle(title);
                }
                public String getTitle() {
    	           return ((JDialog) mComponent).getTitle();
                }
	        };
	    }
	    
	    // otherwise, panic
	    else
	    {
	        throw new RuntimeException("Unable to handle component of type " + windowtype.getName().getClass() );
	    }
	}
	
	/** Get the title of the underlying component 
	 * @modelguid {8DFC68F2-E94D-480B-9D11-5FA537551EA8}
	 */
	public String getTitle()
	{
	    return mTitledComponent.getTitle();
	}
	
	/** Set the title of the underlying component 
	 * @modelguid {79886B28-9DE7-4AE1-BE22-35EF2B3D3771}
	 */
	public void setTitle(String title)
	{
	    mTitledComponent.setTitle(title);
	}
}


