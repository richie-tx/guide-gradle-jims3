//Source file: C:\\VIEWS\\ARCHPRODUCTION\\FRAMEWORK\\MOJO-JIMS2\\MOJO.JAVA\\src\\mojo\\km\\security\\IConstraintMapper.java

package mojo.km.security;


public interface IConstraintMapper 
{
   
   /**
    * @param oid
    * @param type
    * @return Object
    * @roseuid 422DEA8603C3
    */
   public Object getConstraint(Object oid, String type);
   
   /**
    * @return String[]
    * @roseuid 422DED21030B
    */
   public String[] getTypes();
}
/**
 * IConstraintMapper.IConstraintMapper()
 */
