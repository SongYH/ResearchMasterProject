package Entity;
import java.util.ArrayList;

import Common.UserPermission;
import DAO.DAOuserPermission;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class UserPermissionList
{
	private ArrayList<UserPermission> list;
	
	private static class UserPermissionListHolder
	{
		static final UserPermissionList uniqueInstance = new UserPermissionList();
	}

	public static UserPermissionList getInstance()
	{
		return UserPermissionListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////

	public String getPermissionName(String id)
	{
		for(UserPermission x : list)
		{
			if(x.getUserId().equals(id))
			{
				return x.getPermissionName();
			}
		}
		
		return null;
	}
	
	public ArrayList<UserPermission> getEntireUserPermissionList()
	{
		return list;
	}
	
	public ArrayList<UserPermission> getPermissionList(String permissionName)
	{
		ArrayList<UserPermission> retList = new ArrayList<UserPermission>();
		for(UserPermission x : list)
		{
			if(x.getPermissionName().equals(permissionName))
			{
				retList.add(x);
			}
		}
		
		return retList;
	}
	
	public UserPermission getPermissionListFromId(String id)
	{
		for(UserPermission x : list)
		{
			if(x.getUserId().equals(id))
			{
				return x;
			}
		}
		
		return null;
	}
	
	public void addUserPermission(UserPermission inputData)
	{
		list.add(inputData);
	}
	
	public void addAll(ArrayList<UserPermission> inputData)
	{
		list.addAll(inputData);
	}
	
	public void setUserPermission(int index, UserPermission inputData)
	{
		list.set(index, inputData);
	}
	
	public void setUserPermission(String id, UserPermission inputData)
	{
		int index = list.indexOf(new UserPermission(id, ""));
		list.set(index, inputData);
	}

	public void setList(ArrayList<UserPermission> list)
	{
		this.list = list;
	}
}
