package mojo.ui.common;

import java.awt.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 * <B>FrameManager</B> implements a single static method that takes a component
 * and recurses up the hierachy tree to find the UT2 Frame and then returns that
 * Frame.
 *
 * @author R. Goode
 * @modelguid {371E06DC-51C2-42C2-B08A-D6F3C37F72EB}
 */
public class FrameManager
{
  /**
   * This stores the UTFrame instance for anyone to retrieve
   * @modelguid {EDCBA751-CC26-4C44-88DF-59A5A107EB6C}
   */
  private static Frame frame = null;
  /**
   * This method is suppose to take a component and recurses up the hierachy tree to find the UT2 Frame 
   * parent ( if it can) and then returns that Frame.  This does not work for components of JInternalFrame jdk1.3  
   * @param searchComp - component node to start search regressively up the hierarachy tree (Z order)
   * NOTE: this works for children DIRECTLY constructed by the Appshell - OESMain
   *       However, it doesn't work for children constructed by JInternalFrames.  They are unique - the reason
   *       is that JInternalFrames creates a hidden awt.Frame and then adds the panels, buttons,etc.. to it.
   *       Therefore, performing the method below will only get you this hidden awt.Frame of the JInternalFrame
   *       and not progress back up the program to the "main" awt.Frame of OESMain that you want and need.
   * @modelguid {8C8B1765-FE5C-4902-8FB9-A42371E5246B}
   */
  public static Frame getFramefromComp(Object searchComp)
  {
    boolean btest = true;
    Frame mFrame = null;
    System.out.println(" Start Found Frame Loop");
    Component testComp = (Component)searchComp; 
    while(btest && (testComp != null) && (testComp.getParent() != null))
    {
        System.out.println("      Another loop " + testComp.getParent().getClass().getName());
        testComp = testComp.getParent();
        if (testComp instanceof Frame)
        {
            btest = false;
            mFrame = (Frame)testComp;
            System.out.println("   Found Frame");
        }
    
    }
    System.out.println(" End Found Frame Loop");
    return mFrame;
  
//    JInternalFrame testComp1 = (JInternalFrame)getRootPane().getParent();
//    Container testComp1 = SwingUtilities.getRootPane(testComp);
//    if(testComp1 == null)
//       System.out.println(" Start Found Frame Loop 1 - " + testComp1.getClass().getName());

  }
  
  /**
   * This method sets the "global" UTFrame that everyone can access. 
   *  NOTE:  This should really only be called  and set by the Appshell - Oesmain.java upon 
   *         the main construction of the UT2 program. 
   * @modelguid {5D43DB9E-AB75-4A71-A0EF-C4758EDF3447}
   */
  public static void setFrame(Frame aFrame)
  {
    frame = aFrame;
  }
  
  /**
   * This method gets the stored UTFrame from this class an sends it to the calling method
   *   returns back the OESMain - Frame for UT2.
   * @modelguid {7FB1E9B9-A553-4662-8541-2F4EAC2193DE}
   */
  public static Frame getFrame()
  {
    return frame;
  }
  
}