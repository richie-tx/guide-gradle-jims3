
package mojo.km.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Locale;
import java.util.Hashtable;
import java.io.*;
import mojo.km.properties.IResourceService;

/**
 *    Responsible to provide RresourceBundle related functionality
 *    that manages resources for a locale using a set of static strings.
 *	@author Anurag Srivastava
 * @modelguid {E5FB48A2-EAF4-453E-A284-71B07F4A37FB}
 */
public class ResourceService implements IResourceService
{
	/** @modelguid {833DBDEE-8EBD-454C-87E5-326936640819} */
    private ResourceBundle resourceBundle = null;
	/** @modelguid {76C9D2D4-E2C6-41F5-9BB0-531E9189174C} */
    private PropertyResourceBundle overrideResourceBundle = null;
	/** @modelguid {DAEEA36B-7E5F-4923-A118-7FE81DB08C5D} */
    private Hashtable putProperties = null;
	/** @modelguid {922EE461-1D1C-4CC9-B3AC-0029F4BCD8FA} */
    private String resourceName = null;

    //----------------------------------------------------------------------
    //                  PUBLIC METHODS
    //----------------------------------------------------------------------

	/** @modelguid {9CE19157-751E-4FE5-A573-5DDB0FC03775} */
    public ResourceService(String resourceName) {
        this.resourceName = resourceName;
    }

    /** Method that returns the value associated with the provided property
    *   name. If the property is not found, a MissingPropertyException
    *   will be thrown.
    *   @param propertyName Key to a stored value.
    *   @return Value associated with the passed-in property.
    *   @throws MissingResourceException
    * @modelguid {60DA1CE2-D46B-4A0C-A333-48EA4A9E08C3}
    */
    public String getResourceString( String propertyName )
        throws MissingResourceException
    {
        return getResourceString( propertyName, true );
    }

    /** Method that returns the value associated with the provided property
    *   name. If the propertyName is not found, a MissingResourceException
    *   will be thrown if and only if the required argument is set to true.
    *  @param propertyName Key to a stored value.
    *  @param required Whether or not the property must be defined.
    *  @return Value associated with the passed-in property.
    *  @throws MissingResourceException
    * @modelguid {A4170D99-52F0-4B4D-B30A-4F652ED25732}
    */
    public String getResourceString( String propertyName, boolean required  )
        throws MissingResourceException
    {
        if( resourceBundle == null ) {
           loadResources();
        }

        String str = "";
        boolean isFound = false;

        if( putProperties != null ) {
            str = (String)putProperties.get( propertyName );
            if( str != null ) return str;
        }

        if( overrideResourceBundle != null )
        {
            try {
                str = overrideResourceBundle.getString( propertyName );
                isFound = true;
            } 
            catch( MissingResourceException mre ) {
            }
        }

        if(!isFound && (resourceBundle != null)){
             try {
                str = resourceBundle.getString( propertyName );
            } 
            catch( MissingResourceException mre ) {
                System.err.println( "Failed to find " + propertyName );
                if( required ) {
                    throw mre;  // rethrow if required
                }
            }
        }
        return str;
    }

    /**
     * Unlike a ResourceBundle, this method allows the user to both modify
     * properties defined within the ResourceBundle and add new new
     * properties.
     * @param aResourceString a key for the value stored in the resource bundle.
     * @modelguid {060A14A8-5671-41FC-A84D-94087D8215D2}
     */
     public void putProperty( String propertyName, String value )
        throws Exception
     {
         if( putProperties == null ) putProperties = new Hashtable();
         putProperties.put( propertyName, value );
     }

     //----------------------------------------------------------------------
     //                   PRIVATE METHODS
     //----------------------------------------------------------------------

    /**
     * Responsible for loading the ResourceBundle and override file, if any.
     * @modelguid {663AECD3-8C28-4AB8-A37E-4A4A293890BB}
     */
    private void loadResources()
    {
        try { 
            resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
            /* PLEASE DO NOT DELETE THIS CODE!
            checkForOverrides();
            */
        }
        catch( MissingResourceException mre ) {
            mre.printStackTrace();
            System.err.println( "Unable to locate " + resourceName );
            throw mre;
        }
    }

    /**
     * Responsible for loading an optional ResourceBundle that overrides one
     * to many values within the primary ResourceBundle.
     * @param overrideFileName Path to a resource file, not necessarily within
     * the CLASSPATH to this class, that contains additional property-value
     * associations which, if found, will be used in place of the property-value
     * associations located within the primary ResourceBundle.
     * @modelguid {F13A10FB-45C3-45FD-B229-E522DE0A0421}
     */
    private void loadResourceOverrides( String overrideFileName )
        throws IOException
    {
        FileInputStream istream = null;
        try {
            File file = new File(overrideFileName);
            if( file.exists() ) {
                istream = new FileInputStream(resourceName);
                overrideResourceBundle = new PropertyResourceBundle( istream );
            }
        } 
        catch( IOException ioe ) {
            System.err.println( "Error accessing override file: " + resourceName
                                + ": " + ioe );
            throw ioe;
        }
        finally {
            if( istream != null ) {
                istream.close();
            }
        }
    }

	/** @modelguid {55D626D9-A707-4F77-9DDE-7D508AA9239B} */
    private void checkForOverrides() throws IOException
    {
        //---------------------- Overrides? ---------------------//
        if( overrideResourceBundle == null ) {
            String overrideFileName = getResourceString( "overrideFileName" ).trim();
            if( overrideFileName.length() > 0 ) {
                loadResourceOverrides( overrideFileName );
            }
        }
    }

}
