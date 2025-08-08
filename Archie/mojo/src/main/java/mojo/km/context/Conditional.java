package mojo.km.context;

/**
Defines interface for checking if condition is meet.
@author Eric A Amundson
 * @modelguid {7C4488EA-D960-4EC8-80D2-CD3FE61E74CD}
*/
public interface Conditional {
    /**
    Evaluate conditional logic.
    @return true if condition is meet.
	 * @modelguid {31170AC8-804E-42C0-93EC-A805CC384B9A}
    */
    public boolean evaluate();
}
