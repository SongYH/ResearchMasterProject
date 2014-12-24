package Common;

public class UserPermission
{
	private String userId;
	private String permissionName;
	
	public UserPermission(String userId, String permissionName)
	{
		super();
		this.userId = userId;
		this.permissionName = permissionName;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getPermissionName()
	{
		return permissionName;
	}
	public void setPermissionName(String permissionName)
	{
		this.permissionName = permissionName;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		UserPermission target = (UserPermission) obj;
		if (userId.equals(target.userId))
			return true;
		else
			return false;
	}
}
