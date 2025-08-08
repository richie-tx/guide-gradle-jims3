package mojo.ui.common;

import java.beans.*;
import java.awt.*;
import java.text.*;
import mojo.km.utilities.FractionFormat;

/** @modelguid {2127D3A2-B07C-4CDC-849F-3920135DC996} */
public class FractionSpinner extends NumericSpinner
{
    //***** private attributes
	//{{DECLARE_CONTROLS
	//}}
	
	/** @modelguid {7D2D5976-BAE0-421B-ABDA-880F321AB57B} */
	public FractionSpinner()
	{
		setFormat(new FractionFormat((short)256, true, false, false));
		setMin(new Double(0));
		setMax(new Double(999999));
        setInc(new Double(1));
	}
	
	/** @modelguid {F118DD6F-89FB-4CB8-9F2F-B6AF94F0E1F9} */
	public FractionSpinner(String strIncrement)
	{
        this();
        try
        {
            setInc((Double)getFormat().parse(strIncrement));
        }
        catch( ParseException e )
        {
        }
	}
	
	//***** on scroll down
	/** @modelguid {3804E9D6-E212-49B3-A6B1-9728CA891307} */
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
        double inc      = getInc().doubleValue();
        double min      = getMin().doubleValue();
        double max      = getMax().doubleValue();
        
        //decrement by value
        double newValue = value - inc;
        //rounds to increment
        newValue = Math.round( newValue / inc ) * inc;
        if (newValue < min)
        {
            newValue = getRollover() ? max : min;
        }
        setNewValue(newValue);
    }
    
    //***** on scroll up
	/** @modelguid {5D410476-985A-44D6-A038-03F9ED6FEB35} */
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
        double inc      = getInc().doubleValue();
        double min      = getMin().doubleValue();
        double max      = getMax().doubleValue();
        
        //decrement by value
        double newValue = value + inc;
        //rounds to increment
        newValue = Math.round( newValue / inc ) * inc;
        if (newValue > max)
        {
            newValue = getRollover() ? min : max;
        }
        setNewValue(newValue);
    }

    //***** validate what the user typed in
	/** @modelguid {81906C60-C8D6-42EE-8C15-987A1E50A8CC} */
    protected boolean validateText()
    {
        boolean isValid = false;
        
        try
        {
            double newValue = getFormat().parse(getText()).doubleValue();
            
            if ((newValue >= getMin().doubleValue()) && (newValue <= getMax().doubleValue()))
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
    
	/** @modelguid {F4D704EB-985E-4284-832D-2569DE222813} */
    public double getDoubleValue()
    {
        return (getValue()).doubleValue();
    }
}
