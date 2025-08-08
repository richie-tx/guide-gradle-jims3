package mojo.km.security;

import java.util.Set;

import mojo.km.security.role.ldap.IUserContext;

public interface ISecurityManager /*extends java.io.Serializable*/ //87191
{

	/**
	 * @param user
	 * @roseuid 42497207025F
	 */
	public void buildAcl(SecurityUser user); //#87188

	/**
	 * @return mojo.km.security.ACL
	 * @roseuid 42246ABB01AC
	 */
	public ACL getACL();

	/**
	 * @param feature
	 * @return java.util.Collection
	 * @roseuid 42246D550055
	 */
	//public List getConstraintsForWhichUserCanDoFeature(String feature); //87191

	/**
	 * @return java.util.Collection
	 * @roseuid 4225BC86027A
	 */
	public Set getFeatures();

	/**
	 * @return mojo.km.security.UserBean
	 * @roseuid 42247C780000
	 */
	public IUserInfo getIUserInfo();

	/**
	 * @return mojo.km.security.UserBean
	 * @roseuid 42247C780000
	 */
	public IUserContext getUser();
	/**
	 * @param feature
	 * @return boolean
	 * @roseuid 42246A080104
	 */
	public boolean isAllowed(String feature);

	/**
	 * @param feature
	 * @param constraint
	 * @return Boolean
	 * @roseuid 42246DD00283
	 */
	public boolean isAllowed(String feature, Constraint constraint);

	/**
	 * @param features
	 * @return boolean
	 * @roseuid 424994C6000D
	 */
	public boolean isAllowed(String[] features);

	/**
	 * @param type
	 * @return boolean
	 * @roseuid 42306B8F034D
	 */
	public boolean isUserOfType(String type);

	/**
	 * @param types
	 * @return boolean
	 */
	public boolean isUserOfType(String[] types);

	/**
	 * @param user
	 * @roseuid 42497207025F
	 */
	public void setIUserInfo(IUserInfo iUserInfo);
}
