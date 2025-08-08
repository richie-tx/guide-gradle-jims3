package mojo.km.naming;

public class SecurityConstants
{
    // Event Topics
    public static final String ROLE_EVENT_TOPIC = "Role";

    public static final String SYSTEM_ACTIVITY_EVENT_TOPIC = "SystemActivity";

    public static final String ROLE_LISTITEM_EVENT_TOPIC = "Role.ListItem";

    public static final String ROLE_GROUP_EVENT_TOPIC = "RoleGroup";

    public static final String ROLE_GROUP_LISTITEM_EVENT_TOPIC = "RoleGroup.ListItem";

    public static final String ROLEGROUP_ROLE_AVAILABLE_LISTITEM = "RoleGroup.Role.Available.ListItem";

    public static final String ROLEGROUP_ROLE_ASSOCIATED_LISTITEM = "RoleGroup.Role.Associated.ListItem";

    public static final String USER_EVENT_TOPIC = "user";

    public static final String USERGROUP_EVENT_TOPIC = "userGroup";

    // Role Type Constants
    public static final String ROLE_TYPE_MA = "MA";

    //	public static final String PUBLIC = "PUBLIC";
    //	public static final String PRIVATE = "PRIVATE";
    //	public static final String SHARED = "SHARED";
    //
    //	// Status Code Constants
    //	public static final String ACTIVE = "A";
    //	public static final String EXPIRED = "E";
    //	public static final String SUSPENDED = "S";

    // Security Type Constants (not to be confused with Role Type)
    public static final String ROLE = "ROLE";

    public static final String ROLE_GROUP = "ROLEGROUP";

    public static final String USER = "USER";

    public static final String ROLE_ROOT = "ROLEROOT";

    public static final String ROLE_FEATURES_EVENT_TOPIC = "RoleFeature";

    public static final String FEATURE_EVENT_TOPIC = "Feature";

    public static final String ROLE_USER_EVENT_TOPIC = "RoleUser";
    
    public static final String SECURITY_MANAGER = "ISecurityManager";
}
