package mojo.ui.common;

import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** @modelguid {DEB1D43F-5CE7-464B-BE3D-8BA43C3D4582} */
public class FontSelectorPanel extends JPanel 
{
	/** @modelguid {48F2B0BC-1DBA-4FEA-AB3F-2570328D94C0} */
    private ActionListener actionListener;
    
	/** @modelguid {4A770141-C5D0-4365-95D1-D81C109248AF} */
    private String mName;
	/** @modelguid {DC5D6AF9-6229-4722-8A1C-15F1DD726679} */
    private int    mStyle;
	/** @modelguid {EF887823-67B3-4AB7-8FCB-F0AC3F56C952} */
    private int    mSize;
    
	/** @modelguid {4F6AD9E0-1C68-4CB9-9010-CBD6D782B8ED} */
	private DefaultComboBoxModel mFontSizeComboBoxModel  = new DefaultComboBoxModel();
	/** @modelguid {AC3D4C6F-1D7A-496C-86FB-F9462BFE1760} */
	private DefaultComboBoxModel mFontStyleComboBoxModel = new DefaultComboBoxModel();
	
	//{{DECLARE_CONTROLS
	/** @modelguid {FFD06048-3AB2-4AE3-A293-8F9AA308A3F8} */
	JComboBox mFontNameComboBox = new JComboBox();
	/** @modelguid {4922B242-4B4D-4921-8F40-790E88B42DE3} */
	JComboBox mFontStyleComboBox = new JComboBox();
	/** @modelguid {A14BE4C9-6606-461B-A1AD-79F836EED012} */
	JComboBox mFontSizeComboBox = new JComboBox();
	/** @modelguid {B7881C91-FE6C-4D75-A094-9BE51D8D116E} */
	DefaultComboBoxModel mFontNameComboBoxModel = new DefaultComboBoxModel();
	//}}

	/** @modelguid {13DF3070-8BB1-4116-B058-3DD93E1271C6} */
	private class FontStyle
	{
		/** @modelguid {3EFEF44A-593D-4ADA-AEC6-EE6764E118D1} */
	    private int style;
	    
		/** @modelguid {6512CD48-1EEF-46B0-A9F0-DEE0BC0B8060} */
	    FontStyle( int p_style )  { style = p_style; }
	    
		/** @modelguid {21914850-66CA-4D4A-80C1-1DF4D97244FF} */
	    int getStyle()  { return style; }
	    
		/** @modelguid {BBCCEF4C-CD3A-448E-8D9E-ED87772770B0} */
	    public String toString()
	    {
	        if (style == Font.PLAIN)  return "Regular";
	        if (style == Font.BOLD)   return "Bold";
	        if (style == Font.ITALIC) return "Italic";
	        return "Bold Italic";
	    }
	    
		/** @modelguid {72E18AB9-EE17-4374-8F6D-2D1B9AE9935C} */
	    public boolean equals( Object obj )
	    {
	        return style == ((FontStyle) obj).style;
	    }
	}
	
	/** @modelguid {82F76288-A46C-4EBE-A6EF-37AA9FC3FEE4} */
	public FontSelectorPanel()
	{
		super.setLayout(new GridBagLayout());

		//{{INIT_CONTROLS
		setLayout(new GridBagLayout());
		setSize(430,63);
		mFontNameComboBox.setModel(mFontNameComboBoxModel);
		add(mFontNameComboBox,new GridBagConstraints(0,0,1,1,0.6,1.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,2));
		mFontNameComboBox.setBackground(Color.white);
		mFontNameComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		mFontNameComboBox.setBounds(5,17,135,29);
		add(mFontStyleComboBox,new GridBagConstraints(1,0,1,1,0.4,1.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,2));
		mFontStyleComboBox.setBackground(Color.white);
		mFontStyleComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		mFontStyleComboBox.setBounds(150,17,133,29);
		add(mFontSizeComboBox,new GridBagConstraints(2,0,1,1,0.2,1.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,2));
		mFontSizeComboBox.setBackground(Color.white);
		mFontSizeComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		mFontSizeComboBox.setBounds(293,17,131,29);
		//$$ mFontitledBorder.move(0,271);
		//$$ mFontNameComboBoxModel.move(96,271);
		//$$ stringComboBoxModel1.move(96,271);
		//$$ mFontStyleComboBoxModel.move(48,271);
		//$$ mFontSizeComboBoxModel.move(72,271);
		//}}

		//{{REGISTER_LISTENERS
		//}}
		
		//***** listenes
		ActionListener goFire = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) { fireActionEvent(); }
		};
		
        mFontNameComboBox.addActionListener(goFire);
        mFontStyleComboBox.addActionListener(goFire);
        mFontSizeComboBox.addActionListener(goFire);
        
		//***** get system fonts
		GraphicsEnvironment ge        = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font                fonts[]   = ge.getAllFonts();
		TreeSet             fontNames = new TreeSet();
		
		for (int i = 0; i < fonts.length; i++)  
		{
		    String name = fonts[i].getFamily();
		    
		    if (!Character.isUpperCase(name.charAt(0)))  break;
		    
		    fontNames.add(name);
		}
		
		fontNames.add( "Helvetica" );
		fontNames.add( "Courier" );
		fontNames.add( "Dialog" );
		
		mName = "Helvetica";
		
		String fontList[] = new String[fontNames.size()];
		System.arraycopy( fontNames.toArray(), 0, fontList, 0, fontList.length );
		
		for (int i = 0; i < fontList.length; i++)
		{
		  mFontNameComboBoxModel.addElement(fontList[i]);
		}
		mFontNameComboBox.setSelectedItem(mName);		
		
		//***** font size combo box
		mFontSizeComboBoxModel.addElement(new Integer("10"));
		mFontSizeComboBoxModel.addElement(new Integer("12"));
		mFontSizeComboBoxModel.addElement(new Integer("14"));
		mFontSizeComboBoxModel.addElement(new Integer("16"));
		mFontSizeComboBoxModel.addElement(new Integer("18"));
		mFontSizeComboBoxModel.addElement(new Integer("20"));
		mFontSizeComboBox.setModel(mFontSizeComboBoxModel);
		
		mSize = 12;
		
		mFontSizeComboBox.setSelectedItem( new Integer(mSize) );
		
		//***** font style combo box
		mFontStyleComboBoxModel.addElement(new FontStyle( Font.PLAIN ));
		mFontStyleComboBoxModel.addElement(new FontStyle( Font.BOLD ));
		mFontStyleComboBoxModel.addElement(new FontStyle( Font.ITALIC ));
		mFontStyleComboBoxModel.addElement(new FontStyle( Font.BOLD | Font.ITALIC ));
		mFontStyleComboBox.setModel(mFontStyleComboBoxModel);
		
		mStyle = Font.PLAIN;
		
		mFontStyleComboBox.setSelectedIndex(0);
	}

	/** @modelguid {5959D0AC-3FC8-41D7-B53D-7F161CD35965} */
    public void setLayout(LayoutManager mgr)
    {
    }
    
    //***** state accessors
	/** @modelguid {1A61A759-A391-48AB-A31F-0C1C89198CBD} */
    public String getFontName()
    {
        return mFontNameComboBoxModel.getSelectedItem().toString();
    }
    
	/** @modelguid {C310B756-9412-4A9A-833F-0F23E2160820} */
    public int getFontSize()
    {
        return ((Integer) mFontSizeComboBoxModel.getSelectedItem()).intValue();
    }
    
	/** @modelguid {AD22A801-E7E1-496B-9B31-042134E7AA06} */
    public int getFontStyle()
    {
        return ((FontStyle) mFontStyleComboBoxModel.getSelectedItem()).getStyle();
    }
    
    //***** state mutators
	/** @modelguid {70B38AD8-EDC3-4016-9969-F7C2D47BDDCE} */
    public void setFontName( String name )
    {
        mFontNameComboBoxModel.setSelectedItem(name);
    }
    
	/** @modelguid {41565DA2-4D37-4C10-90A8-9C7BAC0E3A04} */
    public void setFontSize( int size )
    {
        mFontSizeComboBoxModel.setSelectedItem( new Integer(size) );
    }
    
	/** @modelguid {EDCC2FD6-C823-4E8C-A60F-C922B9B8F277} */
    public void setFontStyle( int style )
    {
        mFontStyleComboBoxModel.setSelectedItem( new FontStyle(style) );
    }
    
	//***** query/clear state change
	/** @modelguid {5ABCD91A-48A2-491D-B7C2-88842D6A2AC1} */
	public boolean stateChanged()
	{
	    return (!mName.equals(getFontName())) || 
	           (mSize != getFontSize()) ||
	           (mStyle != getFontStyle());
	}

	/** @modelguid {37780B61-EBFB-40C0-8612-21A704F70235} */
	public void clearStateChanged()
	{
	    mName  = getFontName();
	    mSize  = getFontSize();
	    mStyle = getFontStyle();
	}
	
	//***** handle listeners
	/** @modelguid {C1CBBE50-5C8F-4BE5-86E6-B98520775E5A} */
    public synchronized void addActionListener(ActionListener l) 
    {
        actionListener = AWTEventMulticaster.add(actionListener, l);
    }
	/** @modelguid {752F2267-DDFE-48F6-8D1E-7DABBA2366E2} */
    public synchronized void removeActionListener(ActionListener l) 
    {
        actionListener = AWTEventMulticaster.remove(actionListener, l);
    }
	/** @modelguid {116D3359-BC81-47D6-B10B-2C36C31B066D} */
    public void fireActionEvent()
    {
        if (actionListener != null) 
        {
            actionListener.actionPerformed(new ActionEvent(this,0,"FontChanged"));
        }         
    }
}