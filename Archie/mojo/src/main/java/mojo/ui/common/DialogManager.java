package mojo.ui.common;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Stack;
import java.util.EmptyStackException;

import javax.swing.JDialog;

/**
 * <B>DialogManager</B> is used manage dialog windows.  The DialogManager
 * will keep the most current dialog in the top most 'Z' position on the
 * desktop.
 *
 * @author Kurt Jacobs
 * @version 1.0
 *
 * @modelguid {C54E6A15-B739-4F74-BDFA-C590534E2665}
 */

public class DialogManager  
{
   /** Our single instance 
    * @modelguid {A5611328-38E5-4A62-9E1E-DAC3A3B36E6B}
    */
   static private DialogManager mInstance = new DialogManager();

	/** @modelguid {7E59D69E-C724-4F9F-8684-09938A5853AC} */
   Stack dialogStack; 
	/** @modelguid {9D24C696-2F6C-4BA6-AEE8-9523608F97E9} */
   ActiveWindowListener mWindowListener;
  
   /** 
     *Keep constructor private to enforce singleton pattern 
     * @modelguid {DBCDA2AA-E387-4D86-837D-32F8784F3CBD}
    */
   private DialogManager()
   {
     dialogStack=new Stack(); 
     mWindowListener= new ActiveWindowListener();
   }
    
   /**
    * This method is used to access the singleton.
    * 
    * @return A reference to the DialogManager
    * @modelguid {DDC890E5-4C5B-4278-B223-095DB2EC8F39}
    */
   public static DialogManager getInstance() 
   {
      return mInstance;
   }
   
   /**
    * This method will set the dialog passed into it as the top
    * level window and will disallow other windows from appearing
    * on top of this window.
    * 
    * @param targetDialog the target dialog to be made the top level
    * 'Z' window
    * @modelguid {EE3803EC-418E-470A-8C24-6FDAACE2FE3A}
    */
   public void setAsTopWindow (JDialog targetDialog) 
   {
     // If there is already a dialog in the stack, remove the window
     // listener from it so that it can be assign to the new window
     // that resides at the top of the stack
     if (dialogStack.empty() == false)
     {
       Object dialogWindow=dialogStack.peek();
       if (dialogWindow instanceof JDialog) {
          ((JDialog)dialogWindow).removeWindowListener(mWindowListener);
       }
     }  
    
     dialogStack.push(targetDialog);
     targetDialog.addWindowListener(mWindowListener); 
   }
   
   /**
    * This inner class watches for the dialog to become an inactive
    * window.  When this happens, it is brought to the front.
    * @modelguid {C388EE2A-BDAC-4015-AFB0-A18A1B47C28C}
   */
   class ActiveWindowListener extends WindowAdapter
   {
      /**
       * This method will bring the window at the top of the stack
       * (most current) to the top most 'Z' position on the desktop
       * @modelguid {47149EE8-0C65-49CA-AD3D-08B280BF5FEE}
       */
      public void bringWindowToFront() {
        if (dialogStack.empty() == false)
        {
          Object dialogWindow=dialogStack.peek();
          if (dialogWindow instanceof JDialog) {
            ((JDialog)dialogWindow).toFront();
          }
        }
      }
      
      /**
       * This method is called when it becomes activated.  
       * 
       * @param e window event
       * @modelguid {FB8B6F3A-E0C1-481E-80A4-48F62022E198}
       */
      public void windowActivated(WindowEvent e)
      {
         bringWindowToFront(); 
      }

      /**
       * This method is called when it becomes deactivated. This occurs
       * when the your places focus on another app.  When this event
       * happens, the target dialog is brought to the top most 'Z'
       * position.
       * 
       * @param e window event
       * @modelguid {F6AA6296-B776-433E-AA28-9B67C4D5B028}
       */
      public void windowDeactivated(WindowEvent e)
      {
         bringWindowToFront(); 
      }
         
      /**
       * This method pops the current dialog off the dialogStack and the
       * listener is removed.  If the dialogStack has a dialog in
       * the stack after removal of the top most dialog, then that
       * dialog is assigned the window listener.
       * 
       * @param e window event
       * @modelguid {07699874-47EA-4128-9478-303B65FE6785}
      */
      public void popCurrentDialog() 
      {
        // pop off the top dialog and remove it's listener
        if (dialogStack.empty() == false)
        {
          Object dialogWindow=dialogStack.pop();
          if (dialogWindow instanceof JDialog)
            ((JDialog)dialogWindow).removeWindowListener(this);
        } 
        
        // if there is another dialog in the stack, assign
        // a window listener and bring it to the front
        if (dialogStack.empty() == false)
         {
          Object dialogWindow=dialogStack.peek();
          if (dialogWindow instanceof JDialog)
            ((JDialog)dialogWindow).addWindowListener(this);
            ((JDialog)dialogWindow).toFront();
         }  
      }
      
      /**
       * This method calls the popCurrentDialog method when the
       * currently opened window receives a windowClosed event.
       * 
       * @param e window event
       * @modelguid {D2C8453C-E83F-40DD-9773-ABF9C3C6EB46}
       */
      public void windowClosed(WindowEvent e) 
      {
        System.out.println("Window Closed");
        popCurrentDialog();
      }
      
       /**
       * This method calls the popCurrentDialog method when the
       * currently opened window receives a windowClosed event.
       * 
       * @param e window event
       * @modelguid {EB5F2AD0-9004-4962-A599-A154C2F19751}
       */
      public void windowClosing(WindowEvent e) 
      {
        System.out.println("Window Closing");
        popCurrentDialog(); 
      }
      
  }
  
  
}