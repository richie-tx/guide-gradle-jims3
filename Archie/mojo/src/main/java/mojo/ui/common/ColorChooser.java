package mojo.ui.common;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is a simple extension of JColorChooser which clears up some
 * buggy behavior.  Specifically, the buggy "HSB" AbstractColorChooserPanel
 * is remove during the constructor.  In order to make this possible,
 * however, we need to rewrite removeChooserPanel() since that, too, has
 * a bug.
 * @modelguid {57D74318-7ECE-493B-8442-15A99CE459C6}
 */
public class ColorChooser extends JColorChooser
{
	/** @modelguid {7278661E-20D9-437C-8178-6F83653A58BA} */
    static DialogManager mDialogManager;
    
    /**
     * Find the HSB panel and remove it.
     * @modelguid {3056AB14-F6CD-44EF-A602-20FB57C4E424}
     */
    protected void removeBuggyHSBPanel()
    {
        AbstractColorChooserPanel panels[] = getChooserPanels();
        
        for (int i = 0; i < panels.length; i++)
        {
            if (panels[i].getDisplayName().equals("HSB"))
            {
                removeChooserPanel(panels[i]);
            }
        }
    }

    /**
     * Creates a color chooser pane with an initial color of white.
     * @modelguid {D9266A06-4246-4A80-BF2B-4A505DC36720}
     */
	public ColorChooser()
	{
	    super();
	    removeBuggyHSBPanel();
	}

    /**
     * Creates a color chooser pane with the specified initial color.
     *
     * @param initialColor the initial color set in the chooser
     * @modelguid {7283B6B3-E063-4A5E-995F-057C14062507}
     */
	public ColorChooser( Color initialColor )
	{
	    super(initialColor);
	    removeBuggyHSBPanel();
	}

    /**
     * Creates a color chooser pane with the specified ColorSelectionModel.
     *
     * @param initialColor the initial color set in the chooser
     * @modelguid {39DB6AF1-AB67-4362-A456-6C4EB000B447}
     */
	public ColorChooser( ColorSelectionModel model )
	{
	    super(model);
	    removeBuggyHSBPanel();
	}

    /**
     * Removes the AbstractColorChooserPanel specified.  This overrides the
     * buggy superclass behavior where incorrect array copying arithmethic
     * causes an exception to be thrown.
     *
     * @param panel the panel object to remove
     * @modelguid {15308D24-920E-4358-A402-0845159A15AF}
     */
    public AbstractColorChooserPanel removeChooserPanel( AbstractColorChooserPanel panel )
    {
        // get old array and allocate new one
        AbstractColorChooserPanel oldPanels[];
        AbstractColorChooserPanel newPanels[];
        
        oldPanels = getChooserPanels();
        if (oldPanels.length == 0)
        {
    	    throw new IllegalArgumentException("chooser panel not in this chooser");
        }
        
        newPanels = new AbstractColorChooserPanel[oldPanels.length-1];
        
        // copy panels we'll keep
        int oldIndex;
        int newIndex;
        
        for (oldIndex = 0, newIndex = 0; oldIndex < oldPanels.length; oldIndex++)
        {
            if (oldPanels[oldIndex] != panel)
            {
                if (newIndex >= newPanels.length)
                {
            	    throw new IllegalArgumentException("chooser panel not in this chooser");
                }
                
                newPanels[newIndex] = oldPanels[oldIndex];
                newIndex++;
            }
        }
        
        // set the new panels
        setChooserPanels(newPanels);

        return panel;
    }

    /**
     * Shows a modal color-chooser dialog and blocks until the
     * dialog is hidden.  If the user presses the "OK" button, then
     * this method hides/disposes the dialog and returns the selected color.
     * If the user presses the "Cancel" button or closes the dialog without
     * pressing "OK", then this method hides/disposes the dialog and returns
     * null.
     *
     * Here, we've largely plagiarized the superclass code expect for
     * instantiating a different panel type.
     *
     * @param component    the parent Component for the dialog
     * @param title        the String containing the dialog's title
     * @param initialColor the initial Color set when the color-chooser is shown
     * @modelguid {521503DB-5ADC-455B-A5E0-8ECCCB5057E1}
     */
    public static Color showDialog( Component component,
                                    String    title, 
                                    Color     initialColor ) 
    {
        final ColorChooser pane = new ColorChooser(initialColor != null ?
                                               initialColor : Color.white);

        ColorTracker ok = new ColorTracker(pane);
        JDialog dialog = createDialog(component, title, true, pane, ok, null);
      
        dialog.addComponentListener( new ComponentAdapter()
        {
            public void componentHidden( ComponentEvent e ) 
            {
                Window w = (Window)e.getComponent();
                w.hide();
                w.dispose();
            }
        });

//	    mDialogManager = DialogManager.getInstance();
//        mDialogManager.setAsTopWindow(dialog);
        dialog.show(); // blocks until user brings dialog down...

        return ok.getColor();
    }
}


