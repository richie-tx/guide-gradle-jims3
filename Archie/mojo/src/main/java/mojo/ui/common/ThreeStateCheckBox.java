package mojo.ui.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

/** @modelguid {38DA2B82-1671-44BF-8BC5-9408A26508CE} */
public class ThreeStateCheckBox extends JPanel
{
	//{{DECLARE_CONTROLS
	/** @modelguid {E87D0A79-A7A5-4C39-A8D2-BF91E01FE55F} */
	JCheckBox mCheckBox = new JCheckBox();
	/** @modelguid {292C4E72-FE57-4567-9200-E1912982BEAA} */
	JLabel mLabel = new JLabel();
	//}}
	
    /** State. 0 = unchecked, 1 = half checked, 2 = checked 
     * @modelguid {8AD6FCD3-41BA-4D7F-9AF6-9E7E266C3FC7}
     */
    private int mState;
    
	/** Our event listener 
	 * @modelguid {23401938-27D0-4901-9450-F4EE8E6C70A1}
	 */
    private ActionListener mListener;
    
    /** Last click time 
     * @modelguid {48354097-F22D-481A-A01F-C4FB00C312AA}
     */
    private long mLastClick;

    /** Constructor 
     * @modelguid {88BB5997-F4D5-4132-8431-BD0CABD99031}
     */
	public ThreeStateCheckBox()
	{
		super.setLayout(new GridBagLayout());
		
		//{{INIT_CONTROLS
		setLayout(new GridBagLayout());
		setSize(295,162);
		add(mCheckBox, new GridBagConstraints(0,0,1,1,0.0,1.0,GridBagConstraints.WEST,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
		mCheckBox.setBounds(0,0,21,162);
		mLabel.setText("jlabel");
		add(mLabel, new GridBagConstraints(1,0,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
		mLabel.setBounds(21,0,274,162);
		setLayout(new GridBagLayout());
		setSize(295,162);
		add(mCheckBox, new GridBagConstraints(0,0,1,1,0.0,1.0,GridBagConstraints.WEST,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
		mCheckBox.setBounds(0,0,21,162);
		mLabel.setText("threestate");
		add(mLabel, new GridBagConstraints(1,0,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
		mLabel.setBounds(21,0,274,162);
		//}}
		
		addMouseListener( new MouseAdapter() {
		    public void mouseClicked( MouseEvent e ) {
		        onMouseClick();
		        e.consume();
		    }
		});
		
		mCheckBox.addMouseListener( new MouseAdapter() {
		    public void mouseClicked( MouseEvent e ) {
		        onMouseClick();
		        e.consume();
		    }
		});
		
		mCheckBox.addActionListener( new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
		        onMouseClick();
		    }
		});
		
    }
    
	/** on mouse click 
	 * @modelguid {12296556-C311-4151-A41A-6C8E7BDE8621}
	 */
	private void onMouseClick()
	{
	    if (isEnabled())
	    {
	        long time = System.currentTimeMillis();
	        if ((time - mLastClick) > 100)
	        {
	            mLastClick = time;
	            mState--;
	            if (mState < 0)  mState = 2;
	            onStateChanged();
    	        fireActionEvent();
    	    }
	    }
	}
	
	/** draw check box 
	 * @modelguid {6601D57B-29E9-4C39-B256-47498D2BFAEE}
	 */
	private void onStateChanged()
	{
        if (isEnabled())
        {
            mCheckBox.setSelected(mState != 0);
            mCheckBox.setEnabled(mState != 1);
        }
        else
        {
            mCheckBox.setSelected(mState != 0);
            mCheckBox.setEnabled(false);
        }
	}
	
	/** notify listeners 
	 * @modelguid {1D049035-1394-404A-8DFB-B9CE83EFB3A8}
	 */
	private void fireActionEvent()
	{
	    if (mListener != null)
	    {
	        ActionEvent event = new ActionEvent(this, 0, "stateChanged");
	        mListener.actionPerformed(event);
	    }
	}
	
    /** Disallow layout changes 
     * @modelguid {98E5BB3E-1C0E-44C8-8FEA-71984EB5609F}
     */
    public void setLayout(LayoutManager mgr)
    {
    }

    /** Enabled/disable 
     * @modelguid {48AB961D-F5C5-46DC-BBCC-BD3EDD0A0B8B}
     */
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        mLabel.setEnabled(enabled);
        onStateChanged();
    }

    /** Set text 
     * @modelguid {72C620F2-A913-4E99-8B4C-24D5E5F1B308}
     */
    public void setText( String text )
    {
        mLabel.setText(text);
    }
    
    /** Set state 
     * @modelguid {12A28DA3-ADFE-4E0E-9D9F-8F142E819665}
     */
    public void setState( int state )
    {
        state = Math.max(state,0);
        state = Math.min(state,2);
        mState = state;
        onStateChanged();
    }
    
    /** Get the state 
     * @modelguid {47D7CCC3-D06C-4C61-B490-AC269112571F}
     */
    public int getState()
    {
        return mState;
    }
    
    /** Add a listener 
     * @modelguid {694AC0C8-4534-4F87-878A-09132797A0A1}
     */
    public synchronized void addActionListener(ActionListener l) 
    {
        mListener = AWTEventMulticaster.add(mListener, l);
    }
    
    /** Remove a listener 
     * @modelguid {B1920106-74C3-4341-B585-66651DC88996}
     */
    public synchronized void removeActionListener(ActionListener l) 
    {
        mListener = AWTEventMulticaster.remove(mListener, l);
    }
}
