/*
 * Created on Sep 26, 2006
 *
 */
package messaging.manageassociate.domintf;

/**
 * @author jmcnabb
 *
 * Interface for updating and reading a SuperviseeAssociate.
 */
public interface ISuperviseeAssociate
{
	/**
	 * @return Returns the superviseeAssociateId (unique identifier).
	 */
	String getSuperviseeAssociateId();

	/**
	 * @return Returns the spn.
	 */
	String getSpn();

	/**
	 * @return Returns the associateId.
	 */
	String getAssociateId();

	/**
	 * @return Returns the associateName.
	 */
	String getAssociateName();

	/**
	 * @return Returns the realtionshipTypeId.
	 */
	String getRelationshipTypeId();
	
	/**
	 * @param aSuperviseeAssociateId  The superviseeAssociateId to set.
	 */
	void setSuperviseeAssociateId(String aSuperviseeAssociateId);

	/**
	 * @param aSpn  The spn to set.
	 */
	void setSpn(String aSpn);

	/**
	 * @param anAssociateId  The associateId to set.
	 */
	void setAssociateId(String anAssociateId);

	/**
	 * @param aName   The associateName to set.
	 */
	void setAssociateName(String aName);

	/**
	 * @param aRelationshipTypeId   The relationshipTypeId to set.
	 */
	void setRelationshipTypeId(String aRelationTypeId);
}
