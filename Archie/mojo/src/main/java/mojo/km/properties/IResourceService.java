package mojo.km.properties;

import java.util.MissingResourceException;

//----------------------------------------------------------------------------
// INTERFACE:  IResourceService
/**
** Implementors of this interface are ones that load ResourceBundle files
** and maintain the ResourceBundle attribute privately.
* @modelguid {277A57A7-E1C3-4D37-AECD-8238CF43DFAB}
*/
//----------------------------------------------------------------------------
public interface IResourceService
{
    /** Method that returns the value associated with the provided property
    *   name. If the propertyName is not found, a MissingPropertyException
    *   will be thrown.
    *   @param propertyName Key to a stored value.
    *   @return Value associated with the passed-in property.
    *   @throws MissingResourceException
    * @modelguid {7CE4C71F-3C1F-4DAB-8B62-18A15C5B7F27}
    */
    public String getResourceString( String propertyName )
        throws MissingResourceException;

    /** Method that returns the value associated with the provided property
    *   name. If the propertyName is not found, a MissingResourceException
    *   will be thrown if and only if the required argument is set to true.
    *  @param propertyName Key to a stored value.
    *  @param required Whether or not the property must be defined.
    *  @return Value associated with the passed-in property.
    *  @throws MissingResourceException
    * @modelguid {A795D990-36BB-4C2F-93AB-F6496B40BF37}
    */
    public String getResourceString( String propertyName, boolean required )
        throws MissingResourceException;

	/** @modelguid {3AE33FFC-EA1E-4DF5-89D8-68A83C7AD2A5} */
    public void putProperty( String propertyName, String value )
        throws Exception;
}
