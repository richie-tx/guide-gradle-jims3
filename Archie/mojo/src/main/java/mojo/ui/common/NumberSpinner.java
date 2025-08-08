package mojo.ui.common;

import java.beans.*;
import java.awt.*;
import java.text.*;

/** @modelguid {ACA4BA4D-4A19-45DA-A196-F09729607D60} */
public class NumberSpinner extends AbstractSpinner
{
    //***** private attributes
	//{{DECLARE_CONTROLS
	//}}
	/** @modelguid {DB5118D1-3D8F-433E-A8CB-8D0BA7F3EDF8} */
	private NumberFormat mFormat;
	/** @modelguid {FB528C75-852B-4EB6-A3BB-26A436552B5A} */
	private Number       mValue;
	/** @modelguid {96255A9A-BDDA-4912-937B-852FD936D14B} */
	private Number       mMin;
	/** @modelguid {1E96B618-C5A3-454E-8C82-A5B2935E57BF} */
	private Number       mMax;
	/** @modelguid {CFA704B8-6A99-49BA-B071-963911F46A99} */
	private Number       mInc;
	/** @modelguid {51DFC2F1-52D2-4491-8570-43B4445097D6} */
	private boolean      mRollover;

    //***** constructor
	/** @modelguid {83C6E105-7DB9-4374-8D8A-DAC28792F787} */
	public NumberSpinner()
	{
		//{{INIT_CONTROLS
		setLayout(null);
		Insets ins = getInsets();
		setSize(ins.left + ins.right + 430,ins.top + ins.bottom + 270);
		//}}

		//{{REGISTER_LISTENERS
		//}}
		
		//***** set some defaults
		mMin    = new Integer(1);
		mMax    = new Integer(10);
		mInc    = new Integer(1);
		mFormat = new DecimalFormat();
		setNewValue(mMin.doubleValue());
	}
	
	//***** set new value
	/** @modelguid {853C2368-9848-4A73-9C32-74F6196D273C} */
	protected void setNewValue( double value )
	{
        try
        {
            String newText = mFormat.format(value);
            mValue = mFormat.parse(newText);
            setText(newText);
        }
        catch( ParseException e )
        {
        }
	}
	
	//***** on scroll down
	/** @modelguid {38965638-6CCC-43CE-ACCA-6B6F72F23E8B} */
    protected void scrollDown()
    {
        try
        {
            setValue(getFormat().parse(getNewText()));
        }
        catch( ParseException e )
        {
        }
        
        double value    = mValue.doubleValue();
        double newValue = value - mInc.doubleValue();
        
        if (newValue < mMin.doubleValue())
        {
            newValue = mRollover ? mMax.doubleValue() : mMin.doubleValue();
        }
        
        setNewValue(newValue);
    }

    //***** on scroll up
	/** @modelguid {E71AF348-0534-41AF-B8A1-D921F8A28917} */
    protected void scrollUp()
    {
        try
        {
            setValue(getFormat().parse(getNewText()));
        }
        catch( ParseException e )
        {
        }
        
        double value    = mValue.doubleValue();
        double newValue = value + mInc.doubleValue();
        
        if (newValue > mMax.doubleValue())
        {
            newValue = mRollover ? mMin.doubleValue() : mMax.doubleValue();
        }
        
        setNewValue(newValue);
    }

    //***** validate what the user typed in
	/** @modelguid {20CFE984-4FA8-4AC0-A1BC-29F6FA91FB44} */
    protected boolean validateText()
    {
        boolean isValid = false;
        
        try
        {
            double newValue = mFormat.parse(getText()).doubleValue();
            double inc      = mInc.doubleValue();
            
            newValue = Math.round( newValue / inc ) * inc;
            
            if ((newValue >= mMin.doubleValue()) && (newValue <= mMax.doubleValue()))
            {
                isValid = true;
                setNewValue(newValue);
            }
        }
        catch( ParseException e )
        {
        }
        
        return isValid;
    }
    
    //***** set text format object
	/** @modelguid {2296BDAD-023E-4E97-A549-A8F8F7AA8238} */
    public void setFormat( NumberFormat format )
    {
        mFormat = format;
    }
    
    //***** get text format object
	/** @modelguid {3212F5A4-3B98-41A1-9BE9-704AC7E122A9} */
    public NumberFormat getFormat()
    {
        return mFormat;
    }
    

    //***** set spin parameters
	/** @modelguid {014751EA-9C57-406F-B23A-E84FBC2C31F9} */
    public void setMin( Number n )
    {
        mMin = n;
    }
    
	/** @modelguid {04F0CA5B-FFDD-4753-84BF-5A65F8458958} */
    public Number getMin()
    {
        return mMin;
    }
    
	/** @modelguid {3B1D9F97-79B3-421D-A721-194D556A7230} */
    public void setMax( Number n )
    {
        mMax = n;
    }
    
	/** @modelguid {CC224DF9-167E-4EB8-8D66-81F528809B99} */
    public Number getMax()
    {
        return mMax;
    }
    
	/** @modelguid {D2E8A6B3-8404-46D9-914E-6416C7A89FC8} */
    public void setInc( Number n )
    {
        mInc = n;
    }
    
	/** @modelguid {70263E13-7E82-4A20-A5D2-EAA772D8C51B} */
    public Number getInc()
    {
        return mInc;
    }
    
    //***** get/set spin value
	/** @modelguid {5EA224C7-F2C5-4F34-B325-DB119E91DC62} */
    public void setValue( Number n )
    {
        Number current = mValue;
        setNewValue(n.doubleValue());
        if (!validateText())
        {
            mValue = current;
        }
    }
    
	/** @modelguid {F975184E-7410-4232-ABF7-E4AB93E148B7} */
    public Number getValue()
    {
        return mValue;
    }
    
	/** @modelguid {D40D6DFA-00A4-4403-A8D0-EFB06FCE6A18} */
    public boolean getRollover()
    {
        return mRollover;
    }
    
	/** @modelguid {7D57BF96-A2A2-440B-92DF-338DB1349A24} */
    public void setRollover( boolean rollover )
    {
        mRollover = rollover;
    }
}