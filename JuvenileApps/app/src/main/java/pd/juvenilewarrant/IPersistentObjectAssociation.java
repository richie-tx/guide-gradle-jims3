/*
 * Created on Dec 19, 2006
 *
 */
package pd.juvenilewarrant;

/**
 * @author Jim Fisher
 *
 */
public interface IPersistentObjectAssociation
{
    String getChildId();
    void setChildId(String childId);
    String getParentId();
    void setParentId(String parentId);
}
