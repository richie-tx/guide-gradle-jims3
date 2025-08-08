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

/**
 * <B>LinkManagerDialog</B> is the top level component created to display and
 * modify value link groups. A value link group is a collection of
 * components that notify each other if their symbol is changed, so all 
 * components in a group can maintain the same symbol.
 *
 * @author R. Ratliff
 * @modelguid {2CB0A41D-AEA8-4372-A9B0-C8028DB4BA19}
 */
public class LinkManagerDialog extends JDialog
{
  /**
   * This is the only defined constructor for this class.
   * @param pane contains all the current frames in UTII. The frames
   * are used to obtain the UTII windows.
   * @modelguid {3066D5D6-5572-4CBE-8EBC-B68FF745C30C}
   */
  public LinkManagerDialog(Frame parent,JDesktopPane pane)
  {
    super(parent/*JOptionPane.getFrameForComponent(pane)*/, TITLE, true);
    initializeDataModel(parent,pane);
    addComponents();
    setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
  }
  
  /**
   * This method is used to initialize the data model used by this dialog to 
   * store user changes.
   * @param pane contains all the current frames in UTII. The frames
   * are used to obtain the UTII windows.
   * @modelguid {98DC7E79-88AE-4B1C-86CC-60F45C9F3A45}
   */
  private void initializeDataModel(Frame parent,JDesktopPane pane)
  {
    JInternalFrame[] frames = pane.getAllFrames();
    mWindowElements = new Vector();
    Object window = null;
    String title = null;
    for (int i = 0; i < frames.length; i++)
    {
      window = frames[i].getContentPane().getComponent(0);
      if (window instanceof ValueLinkable)
      {
        String mnameString =  (parent).getName();
//        title = mnameString + " - " + frames[i].getTitle();
        title = makeTitle(mnameString, frames[i].getTitle());
        mWindowElements.add(new WindowElement(title,
                                              (ValueLinkable)window));
      }
    }
    mGroupList = new JList();
    mGroupListModel = buildGroupListModel();
    mCurrentGroup = new JTextField();
    mWindowsInGroupList = new JList();
    mWindowsOutOfGroupList = new JList();
  }
  
	/** @modelguid {C3FA2449-9D5B-4895-8DCD-0A55DBF70414} */
  private String makeTitle(/*Object window,*/ String mName, String frameTitle)
  {
    String className = mName;
    // Just in case !!!!!
    if(frameTitle == null || frameTitle == "")
       frameTitle = "unTitled";
    int a = frameTitle.lastIndexOf(":");
    // Doesn't have a ":"
    if(a < 0)
        return className + " - " + frameTitle;
    else
    {
         String beginTitle = frameTitle.substring(0,a - 1);
         if(beginTitle != null)
            className = beginTitle;
         String endTitle = frameTitle.substring(a + 1);
         if(endTitle != null)
            return className + " - " + endTitle;
         else
            return mName + " - " + frameTitle;
    }
    
//     return className + " - " + frameTitle;
    
  }

 /**
   * This method is used to create a list model that contains all the
   * group names.
   * @return a list model containing all the group names.
   * @modelguid {EC65D1D8-E58E-41C9-88F0-59648AC703F6}
   */
  private GroupListModel buildGroupListModel()
  {
    mGroupListModel = new GroupListModel();
    Iterator windows = mWindowElements.iterator();
    while (windows.hasNext())
    {
      mGroupListModel.addGroup(((WindowElement)windows.next()).getGroup());
    }
    
    return mGroupListModel;
  }
  
  /**
   * This method is used to add the swing components used to display and 
   * modify the symbol link groups.
   * @modelguid {CEF80D21-3ADE-4184-ACE6-E3256CA00B60}
   */
  private void addComponents()
  {
    getContentPane().add(createLinkControls(), BorderLayout.CENTER);
    getContentPane().add(createDialogControlButtons(), BorderLayout.SOUTH);
    if (mGroupList.getModel().getSize() > 0)
    {
      mGroupList.setSelectedIndex(0);
    }
    else
    {
      adjustWindowLists(null);
    }
  }
  
  /**
   * This method is used to create a container of components used to display
   * and modify symbol link groups.
   * @return a link controls component.
   * @modelguid {E97F2386-D056-4E0C-853E-B152AF6CAC44}
   */
  private JComponent createLinkControls()
  {
    JPanel panel = new JPanel(new GridLayout(2, 1));
    panel.add(createLinkGroupControl());
    panel.add(createWindowListsControl());
    
    return panel;
  }
  
  /**
   * This method is used to create a control that add and remove link groups.
   * @return link group control component.
   * @modelguid {22A75EDA-23A1-4348-82F2-F8D2D78DA290}
   */
  private JComponent createLinkGroupControl()
  {
    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(new TitledBorder(LINK_GROUPS_TITLE));
    panel.add(createLinkGroupList(), BorderLayout.CENTER);
    panel.add(createLinkGroupButtons(), BorderLayout.SOUTH);
    addListSelectionListenerToPopulateCurrentGroup();
   
    return panel;
  }
  
  /**
   * This method is used to create a component that contains the list of 
   * available link groups.
   * @return the link group list component.
   * @modelguid {CA399E50-0FB7-467D-A6B3-3500DA26E1C5}
   */
   
  private JComponent createLinkGroupList()
  {
    mGroupList.setModel(mGroupListModel);
    mGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    return new JScrollPane(mGroupList);
  }
  
  /**
   * This method is used to create a set of buttons used to modify the list of
   * link groups.
   * @return the button container component.
   * @modelguid {5BB3DC00-60BE-461D-8507-4CB780572EF3}
   */
  private JComponent createLinkGroupButtons()
  {
    JPanel container = new JPanel(new GridLayout(1, 2, 10, 0));
    mCurrentGroup = new JTextField();
    container.add(mCurrentGroup);
    JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
    JButton button = new JButton(ADD_GROUP_TEXT);
    panel.add(button);
    addActionListenerToAddGroup(button);
    removegroupbutton = new JButton(REMOVE_GROUP_TEXT);
    if(mGroupListModel.getSize() < 1)
       removegroupbutton.setEnabled(false);
    panel.add(removegroupbutton);
    addActionListenerToRemoveGroup(removegroupbutton);
    container.add(panel);
    
    return container;
  }
  
  /**
   * This method is used to add an action listener to the specified
   * button to add a group to the current list of groups.
   * @param button is the button to which the action listener is added.
   * @modelguid {0CEB0A29-A39D-4C41-8EC9-5436F9381DCD}
   */
  private void addActionListenerToAddGroup(JButton button)
  {
    button.addActionListener(new ActionListener()
    {
      /**
       * This method is invoke when the button is selected.
       * @param e is the event generated when the button is selected.
       */
      public void actionPerformed(ActionEvent e)
      {
        mGroupListModel.addGroup(mCurrentGroup.getText());
        if(mGroupListModel.getSize() < 1)
           removegroupbutton.setEnabled(false);
        else
           removegroupbutton.setEnabled(true);
           
        clearGroupTextField();
      }
    });
  }
  
  /**
   * This method is used to add an action listener to the specified
   * button to remove a group from the current list of groups.
   * @param button is the button to which the action listener is added.
   * @modelguid {6EA803DA-E79D-40EB-8C00-CC4B4DC52FFA}
   */
  private void addActionListenerToRemoveGroup(JButton button)
  {
    button.addActionListener(new ActionListener()
    {
      /**
       * This method is invoke when the button is selected.
       * @param e is the event generated when the button is selected.
       */
      public void actionPerformed(ActionEvent e)
      {
        if(mGroupListModel.getSize() > 0 )
        {
            String group = mCurrentGroup.getText();
            mGroupListModel.removeGroup(group);
            removeGroupFromWindows(group);
            if(mGroupList.getLastVisibleIndex() > -1)
               mGroupList.setSelectedIndex(0);
            adjustWindowLists(group);
            if(mGroupListModel.getSize() < 1)
                removegroupbutton.setEnabled(false);
            else
                removegroupbutton.setEnabled(true);
            clearGroupTextField();
        }
      }
    });
  }
  
  /**
   * This method is used to remove all windows from the specified group.
   * @param group is the group from which all windows are removed.
   * @modelguid {224F3488-4393-4A51-922C-0A2E9B980656}
   */
  private void removeGroupFromWindows(String group)
  {
    Iterator windows = mWindowElements.iterator();
    WindowElement window = null;
    while (windows.hasNext())
    {
      window = (WindowElement)windows.next();
      if (group.equals(window.getGroup()))
      {
        window.setGroup(null);
      }
    }
  }
  
  /**
   * This method is used to add a list selection listener used to
   * populate the current group field when a group is selected.
   * @modelguid {DD1AB225-C026-43DB-82E0-47F956377554}
   */
  private void addListSelectionListenerToPopulateCurrentGroup()
  {
    mGroupList.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent e)
      {
        JList list = (JList)e.getSource();
        String group = (String)list.getSelectedValue();
        mCurrentGroup.setText(group);
        adjustWindowLists(group);
      }
    });
  }
  
  /**
   * This method is used to create a control to add and remove windows from a
   * selected link group.
   * @return window lists control component.
   * @modelguid {514CB5FE-3A8B-4B13-825A-A38B149E46E2}
   */
  private JComponent createWindowListsControl()
  {
    JPanel panel = new JPanel(new GridLayout(1, 3));
    panel.setBorder(new TitledBorder(WINDOWS_TITLE));
    panel.add(createCurrentWindowsList());
    panel.add(createWindowsButtons());
    panel.add(createWindowsForGroupList());
    
    return panel;
  }
  
  /**
   * This method is used to create the component containing a list of all 
   * available windows.
   * @return the current windows list component.
   * @modelguid {E4A2E530-F0AA-44F2-B184-1618F0B0B5C0}
   */
  private JComponent createCurrentWindowsList()
  {
    JPanel panel = new JPanel(new BorderLayout());
    mWindowsOutOfGroupList.setCellRenderer(new WindowElementCellRenderer());
    JScrollPane scrollPane = new JScrollPane(mWindowsOutOfGroupList);
    panel.add(scrollPane, BorderLayout.CENTER);
    JPanel textPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    textPane.add(new JLabel(AVAILABLE_WINDOWS_TEXT));
    panel.add(textPane, BorderLayout.SOUTH);
    
    return panel;
  }
  
  /**
   * This method is used to create the container of buttons used to move 
   * windows to and from link groups.
   * @return windows buttons components.
   * @modelguid {CD625088-4401-494D-A939-45BFA3463B95}
   */
  private JComponent createWindowsButtons()
  {
    JPanel container = new JPanel();
    JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));
    JButton button = new JButton(ADD_WINDOW_TEXT);
    addActionListenerToAddWindowToGroup(button);
    panel.add(button);
    button = new JButton(REMOVE_WINDOW_TEXT);
    addActionListenerToRemoveWindowFromGroup(button);
    panel.add(button);
    container.add(panel);
    
    return container;
  }
  
  /**
   * This method is used to add an action listener to a button to add
   * the selected window(s) to the current group.
   * @param button is the button to which the action listener is added.
   * @modelguid {27F687E6-2002-4D94-9552-EE992BF4E453}
   */
  private void addActionListenerToAddWindowToGroup(JButton button)
  {
    button.addActionListener(new ActionListener()
    {
      /**
       * This method is invoke when the button is selected.
       * @param e is the event generated when the button is selected.
       */
      public void actionPerformed(ActionEvent e)
      {
        String group = mCurrentGroup.getText();
        if (group != null)
        {
          Object[] selectedValues 
                                 = mWindowsOutOfGroupList.getSelectedValues();
          for (int i = 0; i < selectedValues.length; i++)
          {
            ((WindowElement)selectedValues[i]).setGroup(group);
          }
          adjustWindowLists(group);
        }
      }
    });
  }
  
  /**
   * This method is used to add an action listener to a button to remove
   * the selected window(s) from the current group.
   * @param button is the button to which the action listener is added.
   * @modelguid {213346C7-748B-4285-876D-698C77DF8CF9}
   */
  private void addActionListenerToRemoveWindowFromGroup(JButton button)
  {
    button.addActionListener(new ActionListener()
    {
      /**
       * This method is invoke when the button is selected.
       * @param e is the event generated when the button is selected.
       */
      public void actionPerformed(ActionEvent e)
      {
        Object[] selectedValues = mWindowsInGroupList.getSelectedValues();
        for (int i = 0; i < selectedValues.length; i++)
        {
          ((WindowElement)selectedValues[i]).setGroup(null);
        }
        adjustWindowLists(mCurrentGroup.getText());
      }
    });
  }
  
  /**
   * This method is used to create the component containing a list of all 
   * windows belonging to the current selected link group.
   * @return the windows for group list component.
   * @modelguid {91AFE786-075A-4514-BCB3-F0AF0031D803}
   */
  private JComponent createWindowsForGroupList()
  {
    JPanel panel = new JPanel(new BorderLayout());
    mWindowsInGroupList.setCellRenderer(new WindowElementCellRenderer());
    JScrollPane scrollPane = new JScrollPane(mWindowsInGroupList);
    panel.add(scrollPane, BorderLayout.CENTER);
    JPanel textPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    textPane.add(new JLabel(GROUP_WINDOWS_TEXT));
    panel.add(textPane, BorderLayout.SOUTH);
    
    return panel;
  }
  
  /**
   * This method is invoked to create the component that contains the dialog 
   * control buttons. The buttons allow the user to accept or reject changes 
   * made to the symbol link groups.
   * @return the dialog control buttons container.
   * @modelguid {3419565B-6E83-4478-90EF-3C64EB3337E1}
   */
  private JComponent createDialogControlButtons()
  {
    JPanel container = new JPanel();
    JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
    JButton button = new JButton(OK_TEXT);
    panel.add(button);
    addActionListenerToAcceptChanges(button);
    
    button = new JButton(CANCEL_TEXT);
    panel.add(button);
    addActionListenerToCancelChanges(button);
    container.add(panel);
    
    return container;
  }
  
  /**
   * This method is used to add an action listener to a button to accept the 
   * changes made by the user to the symbol link groups. The action listener 
   * will also dispose of the dialog.
   * @param button is the button to which the action listener is added.
   * @modelguid {973A2D0B-2DF5-4AA7-9442-BDD1E47C3ED2}
   */
  private void addActionListenerToAcceptChanges(JButton button)
  {
    button.addActionListener(new ActionListener()
    {
      /**
       * This method is invoke when the button is selected.
       * @param e is the event generated when the button is selected.
       */
      public void actionPerformed(ActionEvent e)
      {
        Iterator windows = mWindowElements.iterator();
        while (windows.hasNext())
        {
          ((WindowElement)windows.next()).maybeUpdateWindowWithGroup();
        }
        LinkManagerDialog.this.dispose();
      }
    });
  }
  
  /**
   * This method is used to acc an action listener to a button to cancel the 
   * changes made by the user to the symbol link groups. The action listener 
   * will also dispose of the dialog.
   * @param button is the button to which the action listener is added.
   * @modelguid {F971B053-5AFD-4061-93BB-F3988C7B738A}
   */
  private void addActionListenerToCancelChanges(JButton button)
  {
    button.addActionListener(new ActionListener()
    {
      /**
       * This method is invoke when the button is selected.
       * @param e is the event generated when the button is selected.
       */
      public void actionPerformed(ActionEvent e)
      {
        LinkManagerDialog.this.dispose();
      }
    });
  }
  
  /**
   * This method is used to adjust the window lists because of a change
   * to groups or group membership.
   * @param group is the group to which windows either belong or don't belong.
   * @modelguid {7C1E959B-0E0C-4994-93F7-5A890424B03E}
   */
  private void adjustWindowLists(String group)
  {
    Vector windowsInGroup = new Vector();
    Vector windowsOutOfGroup = new Vector();
    WindowElement window = null;
    Iterator windows = mWindowElements.iterator();
    while (windows.hasNext())
    {
      window = (WindowElement)windows.next();
      if (group != null && group.equals(window.getGroup()))
      {
        windowsInGroup.add(window);
      }
      else
      {
        windowsOutOfGroup.add(window);
      }
    }
    mWindowsInGroupList.setListData(windowsInGroup);
    mWindowsOutOfGroupList.setListData(windowsOutOfGroup);
  }
  
  /**
   * This method is used to adjust the window lists because of a change
   * to groups or group membership.
   * @param group is the group to which windows either belong or don't belong.
   * @modelguid {FF7EE292-999D-4BFB-82C2-D7C47334923A}
   */
  private void clearGroupTextField()
  {
    mCurrentGroup.setText("");
  }
  
  /**
   * This holds the list of windows that can be members of link groups.
   * @modelguid {70952131-9DDB-46D5-B3B4-369B0104F36E}
   */
  private Vector mWindowElements;
  
  /**
   * This holds the list component that displays the current list of groups.
   * @modelguid {5474629C-CF07-4E40-A2BD-380F23C4B176}
   */
  private JList mGroupList;
  
  /**
   * This holds the list model containing group names.
   * @modelguid {A6C33EC8-CDE5-4B51-8B8B-1FC95372FE37}
   */
  private GroupListModel mGroupListModel;
  
  /**
   * This holds the text field that displays the current selected group.
   * @modelguid {DE682879-4300-4E5E-900D-3178B81CE69D}
   */
  private JTextField mCurrentGroup;
  
  /**
   * This holds the list containing all windows that belong to the current
   * group.
   * @modelguid {018E91FC-D2A8-472C-94AD-0E6CD47070D5}
   */
  private JList mWindowsInGroupList;
  
  /**
   * This holds the list containing all windows that don't belong to the
   * current group.
   * @modelguid {E0F49382-1A0A-4831-919E-88AA112BBC4C}
   */
  private JList mWindowsOutOfGroupList;
  /**
   * This button for the remove group button
   * @modelguid {F9A3E885-7365-40B9-86DD-DFF54DCEE59B}
   */
  private JButton removegroupbutton;

  // internal class constants.
  //
	/** @modelguid {6AF84403-C30D-406E-811A-B94C39B96CA3} */
  private static final String TITLE = "Symbol Link Groups Manager";
	/** @modelguid {77088803-FBDD-4260-AAB6-103B534C38CA} */
  private static final int DIALOG_WIDTH = 500;
	/** @modelguid {6EEE4E60-11BB-4B2B-B087-426C3CA1CB50} */
  private static final int DIALOG_HEIGHT = 325;
	/** @modelguid {8D0DE9A4-D085-4F91-9315-6F3F585C9E05} */
  private static final String LINK_GROUPS_TITLE = "Groups";
	/** @modelguid {A22AE5B2-ABAB-4AA3-81FF-C17D8B6E75AF} */
  private static final String ADD_GROUP_TEXT = "Add";
	/** @modelguid {14A74027-F157-4986-B30E-7F71DBFFF75D} */
  private static final String REMOVE_GROUP_TEXT = "Remove";
	/** @modelguid {F8E04A53-E3E6-41D3-A5B3-B375DE9DC820} */
  private static final String WINDOWS_TITLE = "Windows";
	/** @modelguid {93678C59-751F-4D20-B9E8-EF3A570E1398} */
  private static final String AVAILABLE_WINDOWS_TEXT = "Available";
	/** @modelguid {EC4641AB-C797-4FCC-8B38-F4DC4CA6FB9C} */
  private static final String GROUP_WINDOWS_TEXT = "In selected group";
	/** @modelguid {9CEDB49E-933F-47D0-A60F-75AE2EDB386A} */
  private static final String ADD_WINDOW_TEXT = "Add >>";
	/** @modelguid {A2BA0779-DB8C-4CDB-8563-2DDD3CE7C209} */
  private static final String REMOVE_WINDOW_TEXT = "<< Remove";
	/** @modelguid {EC358CB0-2CC2-4A4E-80F6-5C9EAA8493EC} */
  private static final String OK_TEXT = "OK";
	/** @modelguid {27C11D85-4807-4537-9792-3D4E643129E7} */
  private static final String CANCEL_TEXT = "Cancel";
  //
  //------------------------------end implementation-------------------------
  //
	/** @modelguid {7C340011-2708-4B33-8512-E6BB3E96F0CA} */
  static void main(String[] args)
  {
    try
    {
 	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	  }
	  catch (Exception e) {}
	  JDesktopPane desktop = new JDesktopPane();
	  
	  JInternalFrame frame = new JInternalFrame();
	  frame.getContentPane().add(new IITestComponent(null));
	  frame.setTitle("DELL");
	  desktop.add(frame);
	  
	  frame = new JInternalFrame();
	  frame.getContentPane().add(new IITestComponent("Alt"));
	  frame.setTitle("AMZN");
	  desktop.add(frame);
	  
	  frame = new JInternalFrame();
	  frame.getContentPane().add(new IITestComponent("Fast Look"));
	  frame.setTitle("INTC");
	  desktop.add(frame);
	  
	  frame = new JInternalFrame();
	  frame.getContentPane().add(new IITestComponent("Fast Look"));
	  frame.setTitle("NASDAQ 100");
	  desktop.add(frame);
	  
    JDialog dialog = new LinkManagerDialog(new Frame(),desktop);
    dialog.show();
    System.exit(0);
  }
}


