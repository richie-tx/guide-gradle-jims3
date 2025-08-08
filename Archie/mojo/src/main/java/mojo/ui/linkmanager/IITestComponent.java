package mojo.ui.linkmanager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.*;
import javax.swing.*;

import mojo.ui.common.ValueLinkable;
//import PresentationLayer.AppShell.OESMain;

/** @modelguid {DBB152A4-6758-41BC-BF18-8DC3BE2EC315} */
class IITestComponent extends JPanel implements ValueLinkable
{
	/** @modelguid {2F831C6C-29A1-4F73-A2E1-EA8F810EAD5A} */
  IITestComponent(String group) { mGroup = group; }
	/** @modelguid {F9114E3D-8896-4B64-A0C7-6DC3DAFE44E7} */
  public void joinValueChangeGroup(String group){}
	/** @modelguid {430EED62-3585-493A-BE3C-C2427026DEF1} */
  public void quitValueChangeGroup(String group) {}
	/** @modelguid {7EFCF2CF-CDF5-436E-97C3-24B632B38DEE} */
  public String[] getValueChangeGroups()
  { 
    String[] groups = {mGroup};
    return groups;
  }
	/** @modelguid {8EE4186B-FFAF-4A4F-9C7E-ACA778892CA5} */
  public void changeValue(String symbol) {}
	/** @modelguid {3CA5080A-20A5-486F-9A97-1B3DC5B907DB} */
  private String mGroup;
}