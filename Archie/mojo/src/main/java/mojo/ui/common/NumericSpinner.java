package mojo.ui.common;

import java.beans.*;
import java.awt.*;
import java.text.*;

/** @modelguid {13711C95-E520-4882-A747-16D1358DFC12} */
public class NumericSpinner extends AbstractSpinner
{
    //***** private attributes
	//{{DECLARE_CONTROLS
	//}}
	/** @modelguid {B83C3932-1F51-4166-AD93-5C5252F3D26B} */
	private NumberFormat mFormat;
	/** @modelguid {8E8DC03E-191F-4404-BE1F-FF38FA1AA19D} */
	private Number       mValue;
	/** @modelguid {2AF04F05-E83C-4381-A506-ABC8832F79BF} */
	private Number       mMin;
	/** @modelguid {57A4CA76-355E-4C72-857D-F60D1DC3780A} */
	private Number       mMax;
	/** @modelguid {A40B555A-BDF4-4BC7-BE72-30C9F3DA8B31} */
	private Number       mInc;
	/** @modelguid {BA20EB31-23C1-44B2-AC88-BFBAFEF1CFDA} */
	private boolean      mRollover;

    //***** constructor
	/** @modelguid {EDB5886A-2E4A-4BD5-B317-A289C7D7A169} */
	public NumericSpinner()
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
		mFormat = new DecimalFormat("########");
		setNewValue(mMin.doubleValue());
	}
	
	//***** set new value
	/** @modelguid {9C22352E-7FDD-4B41-92FC-086B0F35EBE0} */
	protected void setNewValue( double value )
	{
	  if (value < mMin.doubleValue())
	  {
	    value = mMin.doubleValue();
	  }
	  if (value > mMax.doubleValue())
	  {
	    value = mMax.doubleValue();
	  }
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
	/** @modelguid {36194C1D-D5DA-4EC1-8857-44F282CAE93B} */
    protected void scrollDown()
    {
        try
        {
            setValue(getFormat().parse(getNewText()));
        }
        catch( ParseException e )
        {
        }
        
        double value    = getValue().doubleValue();
        double newValue = value - mInc.doubleValue();
//        newValue = Math.round( newValue / mInc.doubleValue() ) * mInc.doubleValue();
        
        if (newValue < mMin.doubleValue())
        {
            newValue = mRollover ? mMax.doubleValue() : mMin.doubleValue();
        }
        
        setNewValue(newValue);
    }

    //***** on scroll up
	/** @modelguid {BE2B4814-9E93-4299-9F73-75B6372E5D7B} */
    protected void scrollUp()
    {
        try
        {
            setValue(getFormat().parse(getNewText()));
        }
        catch( ParseException e )
        {
        }
        
        double value    = getValue().doubleValue();
        double newValue = value + mInc.doubleValue();
 //       newValue = Math.round( newValue / mInc.doubleValue() ) * mInc.doubleValue();
        
        if (newValue > mMax.doubleValue())
        {
            newValue = mRollover ? mMin.doubleValue() : mMax.doubleValue();
        }
        
        setNewValue(newValue);
    }

    //***** validate what the user typed in
	/** @modelguid {44DD6E94-24A5-4092-9C7D-4C6DAAE50EBD} */
    protected boolean validateText()
    {
        boolean isValid = false;
        
        try
        {
            double newValue = mFormat.parse(getText()).doubleValue();
            
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
	/** @modelguid {ABBB5449-1B49-455E-82DC-65F84A64B366} */
    public void setFormat( NumberFormat format )
    {
        mFormat = format;
    }
    
    //***** get text format object
	/** @modelguid {A415A9DD-28BA-435C-8114-92B122A72B27} */
    public NumberFormat getFormat()
    {
        return mFormat;
    }
    

    //***** set spin parameters
	/** @modelguid {ECD14B81-0371-4AA9-873A-37567D0BD195} */
    public void setMin( Number n )
    {
        mMin = n;
    }
    
	/** @modelguid {7B0D06DC-2535-40C9-B4EC-B8A8E2C2AD8A} */
    public Number getMin()
    {
        return mMin;
    }
    
	/** @modelguid {0D6B8362-69DA-4C88-AA2B-3D648EC8683D} */
    public void setMax( Number n )
    {
        mMax = n;
    }
    
	/** @modelguid {B71187EB-1EE6-45D8-A534-621A568D9AF0} */
    public Number getMax()
    {
        return mMax;
    }
    
	/** @modelguid {AF754A6E-2EDC-45A0-BAC4-B3BD07B55843} */
    public void setInc( Number n )
    {
        mInc = n;
    }
    
	/** @modelguid {31150610-74F6-4F2E-AF4A-E8306F501592} */
    public Number getInc()
    {
        return mInc;
    }
    
    //***** get/set spin value
	/** @modelguid {90CA3B37-BC2B-4DBF-8AA9-C6A149016170} */
    public void setValue( Number n )
    {
        Number current = mValue;
        setNewValue(n.doubleValue());
        if (!validateText())
        {
            mValue = current;
        }
    }
    
	/** @modelguid {D7E5768D-8EBD-45C4-B762-DC74CEDAA720} */
    public Number getValue()
    {
        return mValue;
    }
    
	/** @modelguid {A7D92E3F-0003-4F72-AE1B-CF95A29A3074} */
    public boolean getRollover()
    {
        return mRollover;
    }
    
	/** @modelguid {D464F7CC-B650-407E-945F-E91107413427} */
    public void setRollover( boolean rollover )
    {
        mRollover = rollover;
    }
}
