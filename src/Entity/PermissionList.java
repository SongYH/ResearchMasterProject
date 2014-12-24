package Entity;
import java.util.ArrayList;

import Common.Permission;
import Common.UserPermission;
import DAO.DAOpermission;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class PermissionList
{
	private ArrayList<Permission> list;
	
	private static class PermissionListHolder
	{
		static final PermissionList uniqueInstance = new PermissionList();
	}

	public static PermissionList getInstance()
	{
		return PermissionListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////

	
	public ArrayList<Permission> getEntirePermissionList()
	{
		return list;
	}
	public ArrayList<Permission> getList()
	{
		return list;
	}
	public void setList(ArrayList<Permission> list)
	{
		this.list = list;
	}
}
