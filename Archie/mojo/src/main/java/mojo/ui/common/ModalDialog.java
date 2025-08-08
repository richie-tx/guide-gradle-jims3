package mojo.ui.common;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 * <B>ModalDialog</B> is a dialog that encapsulates services that are
 * not available is JDialog. This dialog will maintain a top most Z level
 * position when external application is placed on top of it. This dialog
 * will also load a specified icon for it's window.
 *
 * @author Kurt Jacobs
 * @since 6.29.2000
 *
 * @modelguid {149DBFDC-C9F2-493D-84C1-F3593302940A}
 */

public class ModalDialog extends JDialog
{
     /**
     * Creates a modal or non-modal dialog with the specified title 
     * and the specified owner frame.
     * @param owner the frame from which the dialog is displayed
     * @param title  the String to display in the dialog's title bar
     * @param modal  true for a modal dialog, false for one that allows
     *               others windows to be active at the same time
     * @modelguid {5361EDBE-CD15-45C3-9E3C-BF23D93BB71A}
     */
	public ModalDialog (Frame owner, String title, boolean modal)
	{
		super(owner, title, modal);
	    DialogManager dm = DialogManager.getInstance();
        dm.setAsTopWindow(this);
	}
 
    /**
     * Creates a default modal dialog with the specified title and
     * with the specified owner frame.
     *
     * @param owner the Frame from which the dialog is displayed
     * @param title  the String to display in the dialog's title bar
     * @modelguid {3C868AD3-55D9-4AF2-A211-265BEF32E0EE}
     */
    public ModalDialog (Frame owner, String title)
	{
	  this(owner, title, true);
	}
   
   /**
     * Creates a modal or non-modal dialog with the specified title and window icon
     * and the specified owner frame.
     * @param owner         the frame from which the dialog is displayed
     * @param title         the String to display in the dialog's title bar
     * @param modal         true for a modal dialog, false for one that allows
     *                      others windows to be active at the same time
     * @param iconImagePath path to where the icon that represents the
     *                      module type exists (Marker Maker, Account/BP etc)
     * @modelguid {C2AF533E-6746-4B2D-A5CC-5C5286E60FC1}
     */  
   	public ModalDialog(Frame owner, String title, boolean modal,String iconImagePath)
	{
	  this(owner, title, modal);
      setWindowIcon(iconImagePath); 
	}
           
     /**
     * Creates a modal dialog with the specified title and window icon
     * and the specified owner frame.
     * @param owner         the frame from which the dialog is displayed
     * @param title         the String to display in the dialog's title bar
     * @param iconImagePath path to where the icon that represents the
     *                      module type exists (Marker Maker, Account/BP etc)
     * @modelguid {34AD4D96-DF3C-48F5-B6FA-CB7127F9EFFC}
     */    
    public ModalDialog(Frame owner,String title,String iconImagePath)
	{
	   this(owner, title, true);
	   setWindowIcon(iconImagePath);   
	}
          
 
 
    
	/** @modelguid {5E1CB74E-F97E-42EC-82DB-531DDAACDD49} */
    private Frame getFrame() {
        java.awt.Container w = this;
        while((!(w instanceof Frame)) && (w !=null))
        {
            w = w.getParent();
        }
        return (Frame)w;
    }
    
          
    /**
     * This method will set the icon for the window
     * 
     * @param iconImagePath path to where the image for the icon is found
     * @modelguid {0EFAD248-C0AB-4B34-964B-8190DC1F462B}
     */
    public void setWindowIcon(String iconImagePath) 
    {
        Frame parentFrame=getFrame();
        if (parentFrame==null) { 
           System.out.println("NULL PARENT");
        }
        else {
	     Toolkit tk = Toolkit.getDefaultToolkit();
	     Image img = tk.getImage(iconImagePath);  
	     if (img != null) {
	        System.out.println("ModalDialog:I've got an image");
   	        parentFrame.setIconImage(img);
   	     }
   	     else 
   	       System.out.println("ModalDialog:image is null!");
	    }
	  
    }
}