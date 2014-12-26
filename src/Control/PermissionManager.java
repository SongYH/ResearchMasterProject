package Control;

import java.util.ArrayList;

import Common.Permission;
import Common.User;
import Common.UserPermission;
import Entity.BudgetList;
import Entity.PermissionList;
import Entity.UserList;
import Entity.UserPermissionList;

public class PermissionManager
{
	public PermissionManager(){}
	public static class ManageInfo
	{
		User userInfo;
		String permissionName;
		
		public ManageInfo(User userInfo, String permissionName)
		{
			this.userInfo = userInfo;
			this.permissionName = permissionName;
		}
		public User getUserInfo(){return userInfo;}
		public String getPermissionName(){return permissionName;}
	}
	public void addUserPermission(UserPermission up)
	{ 
		UserPermissionList userPermissionList = UserPermissionList.getInstance();
		
		userPermissionList.addUserPermission(up);
	}
	//사용자 권한확인
	public String confirmUserPermission(String id)
	{
		UserPermissionList userPermissionList = UserPermissionList.getInstance();
		return userPermissionList.getPermissionName(id);
	}
	
	//쓰지마삼
	public ArrayList<ManageInfo> reqPermissionInfo(String searchType, String searchInput)
	{
		final String ID = "id";
		final String PERMISSION_NAME = "permissionName";
		
		UserManage userManager = new UserManage();
		UserPermissionList userPermissionList = UserPermissionList.getInstance();

		ArrayList<UserPermission> tempPermissionList = new ArrayList<UserPermission>();
		
		ArrayList<ManageInfo> retManageInfoList = new ArrayList<ManageInfo>();

		try
		{
			switch(searchType)
			{
			   case ID :
				   
			      User user = userManager.reqUser(searchInput);
			      String permissionName = userPermissionList.getPermissionListFromId(searchInput).getPermissionName();
			      retManageInfoList.add(new ManageInfo(user, permissionName));
			      break;
			
			   case PERMISSION_NAME : 
				  tempPermissionList.addAll(userPermissionList.getPermissionList(searchInput));
			      for(UserPermission x : tempPermissionList)
			      {
			         String id = x.getUserId();
			         retManageInfoList.add(new ManageInfo(userManager.reqUser(id), x.getPermissionName()));
			      }
			      break;
			}
		}
		catch(NullPointerException e)
		{
			return null;
		}

		return retManageInfoList;
	}
	
	public ArrayList<Permission> getPermissionList()
	{
		PermissionList plist = PermissionList.getInstance();
		return plist.getEntirePermissionList();
	}
	
	//이것도 쓰지마삼
	public void updateUserPermission(String[] userId, String[] permissionName)
	{
		UserPermissionList list = UserPermissionList.getInstance();
		ArrayList<UserPermission> entireList = list.getEntireUserPermissionList();
		
		for(int i=0;i<userId.length;i++)
		{
			UserPermission x = new UserPermission(userId[i], permissionName[i]);
			
			if(entireList.contains(x))
			{
				list.setUserPermission(x.getUserId(), x);
			}
			else
			{
				list.addUserPermission(x);
			}
		}
	}
}
