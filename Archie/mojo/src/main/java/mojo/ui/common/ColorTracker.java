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
 * This is another plagiarization of the super class.  Instances of this
 * class become the "OK" listeners passed to createDialog().
 * @modelguid {0FC8B9F8-E93C-415A-86A8-B3BDF53DF8C8}
 */
class ColorTracker implements ActionListener
{
	/** @modelguid {62017626-3AC7-49D7-A63A-B4B9D72A6883} */
    ColorChooser chooser;
	/** @modelguid {327D2EBF-ECF6-4599-AEAA-FAFFA312CFFD} */
    Color        color;

	/** @modelguid {A3528B2F-ECAA-4FA5-9E21-EB7E4AEED81D} */
    public ColorTracker(ColorChooser c) 
    {
        chooser = c;
    }

	/** @modelguid {8B2F02A3-BECE-4862-97EF-400252F8C66F} */
    public void actionPerformed(ActionEvent e) 
    {
        color = chooser.getColor();
    }

	/** @modelguid {A1DCADB9-E42E-4446-B181-5984738D98BC} */
    public Color getColor() 
    {
        return color;
    }
}
