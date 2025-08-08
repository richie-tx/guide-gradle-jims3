package mojo.km.caching;

import java.util.Collection;

import mojo.km.persistence.PersistentObject;


public interface ICacheManager 
{
   
   /**
    * @param entity
    * @param id
    * @roseuid 408018BB00FB
    */
   void add(Object entity, String id);
   
   /**
    * adds a persistent type to the cache
	* @param entity
	*/
   void addEntity(PersistentObject entity);
   
   /**
    * @roseuid 408018BB0153
    */
   void clear();
   
   /**
    * @param id
    * @return boolean
    * @roseuid 408018BB033D
    */
   boolean hasElement(String id);
   
   /**
    * @modelguid {8DAE537D-A996-4E87-A2DA-B3173FBEA6EB}
    * @param id
    * @return java.lang.Object
    * @roseuid 40FFFBEE016E
    */
   Object get(String id);
   
   /**
	* @modelguid {8DAE537D-A996-4E87-A2DA-B3173FBEA6EB}
	* @param classType
	* @return java.lang.Object
	*/
   Collection getInstances(Class classType);
   
   /**
    * @param id
    * @roseuid 415313EA00AB
    */
   void remove(String id);

   /**
	* removes a persistent type to the cache
	* @param entity
	*/
   void removeEntity(PersistentObject entity);
   
}
