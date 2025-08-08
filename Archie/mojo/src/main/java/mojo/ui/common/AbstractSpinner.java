package mojo.ui.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 * The AbstractSpinner is a base class for a lightweight
 * spinner.  It is intended as an alternative to the
 * heavyweight version provided by Symantec.
 * @modelguid {681EFFE5-34D5-4B9A-8F79-4DBA42D1FB7A}
 */
public abstract class AbstractSpinner extends JPanel
{    
    //***** our components
	//{{DECLARE_CONTROLS
	/** @modelguid {777E81A7-6564-4618-A439-174AC5072D72} */
	JTextField mTextField = new JTextField();
	/** @modelguid {E56B1EC8-0188-4A53-955B-84EF6834AB44} */
	JPanel mButtonPanelPanel = new JPanel();
	/** @modelguid {33DE3DD4-C91F-4D25-9415-4B62B62A64A1} */
	JButton mUp = new JButton();
	/** @modelguid {B2908CE5-BC55-4F72-A52D-E6C0A60DBDB5} */
	JButton mDown = new JButton();
	//}}

	/** @modelguid {17ED8144-F51E-4025-AB89-788CD4F4E40B} */
	private ActionListener mActionListener = null;
	/** @modelguid {E66B3316-9318-4F2C-8AA7-5C48BD9A64A9} */
	private String         mLastValidText  = "";
	/** @modelguid {FBE3C970-0E3C-46F1-B2C5-3326F9E6F9A3} */
	private String         mRealText       = "";
	/** @modelguid {89C6D704-5E86-46EE-B673-0450F2FAC322} */
	private String         mLeadingSpaces  = " ";

    /**
     * Dispatch ActionEvent to all registered listeners
     * @modelguid {B2A92717-EFF0-4F23-BDD0-BA653DBBE9C9}
     */
    protected void fireActionPerformed() 
    {
        if (mActionListener != null)
        {
            mActionListener.actionPerformed(new ActionEvent(this, 0, "SpinnerSelection"));
        }
    }
    
    /**
     * Perform behavior associated with clicking the up spinner
     * button.  Default behavior does nothing, so subclasses
     * should override this.
     * @modelguid {38A9CB4B-27C4-4A02-9145-D1E4AF6051D0}
     */
    protected void scrollUp()
    {
    }
         
    /**
     * Perform behavior associated with clicking the down spinner
     * button.  Default behavior does nothing, so subclasses
     * should override this.
     * @modelguid {5C2E8257-BEBA-4E61-8119-0BE56B7C3402}
     */
    protected void scrollDown()
    {
    }
    
    /**
     * Validate text typed in by the user.  Default behavior is
     * to accept everything, so subclasses should override this.
     * @modelguid {6B7B45B7-E35B-41C8-BFB0-F9D09E292364}
     */
    protected boolean validateText()
    {
        return true;
    }
         
    //***** constructor
	/** @modelguid {E1A9102F-7AEF-43B7-8B30-2D14C49DCC68} */
	public AbstractSpinner()
	{
	    //***** setup our components
		super.setLayout(new BorderLayout(0,0));
		
	    mUp   = new BasicArrowButton(SwingConstants.NORTH);
	    mDown = new BasicArrowButton(SwingConstants.SOUTH);
	
		//{{INIT_CONTROLS
		setLayout(new BorderLayout(0,0));
		setSize(430,270);
		add(BorderLayout.CENTER,mTextField);
		mTextField.setBounds(0,0,357,270);
		mButtonPanelPanel.setLayout(new GridLayout(2,1,0,0));
		add(BorderLayout.EAST,mButtonPanelPanel);
		mButtonPanelPanel.setBounds(357,0,73,270);
		mUp.setText("up");
		mButtonPanelPanel.add(mUp);
		mUp.setBounds(0,0,73,135);
		mButtonPanelPanel.add(mDown);
		mDown.setBounds(0,135,73,135);
		//}}

		//{{REGISTER_LISTENERS
		//}}
		//***** listen to buttons
		mUp.addActionListener( new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
                scrollUp();
		        fireActionPerformed();
		    }
		});

		mDown.addActionListener( new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
		        scrollDown();
		        fireActionPerformed();
		    }
		});
		
		//***** listen to text field
		mTextField.addActionListener( new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
		        mRealText = mTextField.getText().trim();
		        if (!mTextField.getText().equals(mLastValidText) && validateText())
		        {
		            fireActionPerformed();
		        }
		        else
		        {
		            mTextField.setText(mLastValidText);
		        }
		    }
		});
		
		mTextField.addFocusListener( new FocusListener() {
		    public void focusGained( FocusEvent e )  
		    {
	        JTextField tf = (JTextField)e.getSource();
          tf.setSelectionStart(0);
          tf.setSelectionEnd(tf.getText().length());
	     }
		    public void focusLost( FocusEvent e )  {
		        mRealText = mTextField.getText().trim();
		        if (!mTextField.getText().equals(mLastValidText) && validateText())
		        {
		            fireActionPerformed();
		        }
		        else
		        {
		            mTextField.setText(mLastValidText);
		        }
		    }
		});
	}


    //***** add an action listener
	/** @modelguid {69C74636-53EE-4FF2-A723-C08F82C9D0D3} */
    public synchronized void addActionListener(ActionListener l)
    {
        mActionListener = AWTEventMulticaster.add(mActionListener, l);
    }
    
	/** @modelguid {7DF3E2D1-E0F6-4484-BC98-8DA41301E622} */
    public synchronized void removeActionListener(ActionListener l) 
    {
        mActionListener = AWTEventMulticaster.remove(mActionListener, l);
    }
    
    //***** we don't use a layout manager
	/** @modelguid {4ED40A08-3C72-4EC2-9976-B4133450F471} */
    public void setLayout(LayoutManager mgr)
    {
    }

    //***** enable/disable component
	/** @modelguid {0DD71C73-735F-4977-8EC9-1B5791F6033A} */
    public void setEnabled(boolean enabled)
    {
        mUp.setEnabled(enabled);
        mDown.setEnabled(enabled);
        mTextField.setEnabled(enabled);
    }

    //***** set text font
	/** @modelguid {370D5C42-DFCC-4DB1-AA15-B5FB313D10CE} */
    public void setFont(Font font)
    {
        super.setFont(font);
        if (mTextField != null)  mTextField.setFont(font);
    }

    //***** get/set text
	/** @modelguid {45A5F6A0-120E-4007-854A-12BA4953787E} */
    public String getText()
    {
        return mRealText;
    }
    
	/** @modelguid {9007A235-3620-44C4-8380-E59EBD2B0FC8} */
    public void setText( String text )
    {
        mRealText = text.trim();
        mTextField.setText(mRealText);
        mLastValidText = mRealText;
    }
    
    //***** get/set horizontal alignment
	/** @modelguid {845BF3C1-1747-47DF-9FAA-821BCE10FF00} */
    public int getHorizontalAlignment()
    {
        return mTextField.getHorizontalAlignment();
    }
    
	/** @modelguid {BF231513-A773-4A65-ACDD-9A520D2BC610} */
    public void setHorizontalAlignment( int align )
    {
        mTextField.setHorizontalAlignment( align );
    }
    
    //***** get/set columns
	/** @modelguid {8CF193BE-D672-4C8B-AE62-1BA7DC2472D0} */
    public int getColumns()
    {
        return mTextField.getColumns();
    }
    
	/** @modelguid {22ADB98F-9E4C-4EB6-A225-BEC2B7BF6C6F} */
    public void setColumns( int align )
    {
        mTextField.setColumns( align );
    }
    
    //***** set number of leading spaces
	/** @modelguid {C95819AC-5AAC-435C-BE3C-5A1E6613CA0C} */
    public void setLeadingSpaces( int n )
    {
        mLeadingSpaces = "";
        for (int i = 0; i < n; i++)  mLeadingSpaces = " " + mLeadingSpaces;
        setText(mRealText);
    }
    
	/** @modelguid {D545EA46-96DC-432B-91F3-65878BDB3547} */
    public String getNewText()
    {
        return mTextField.getText();
    }
    
	/** @modelguid {1F1C2877-D16C-4FB7-BCBC-E66482457530} */
    public JTextField getTextField()
    {
      return mTextField;
    }
}