package mojo.km.config;

/**
 * Responsible for handling statement parameter mapping properties.
 * 
 */
public class ParmMappingProperties extends FieldMappingProperties
{
    public String toString()
    {
        String parentClass = this.callback.getParent().getEntity();
        StringBuilder buffer = new StringBuilder(80);
        buffer.append("ParmMappingProperty: ");
        buffer.append(parentClass);
        buffer.append(" propertyName=");
        buffer.append(this.propertyName);
        buffer.append(" propertyType=");
        buffer.append(this.propertyType);
        buffer.append(" index=");
        buffer.append(this.index);
        buffer.append(" dataItemName=");
        buffer.append(this.dataItemName);
        return buffer.toString();
    }
}
