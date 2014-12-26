package Entity;

import java.util.ArrayList;

import Common.User;

public class UserList
{
	private ArrayList<User> userList;

	public ArrayList<User> getUserList()
	{
		return userList;
	}

	public void setUserList(ArrayList<User> userList)
	{
		this.userList = userList;
	}

	private static class UserListHolder
	{
		static final UserList uniqueInstance = new UserList();
	}

	private UserList()
	{
	}
	
	public ArrayList<User> getUserListFromOrganId(int organId) // 
	{
		ArrayList<User> retUserList = new ArrayList<User>();
		
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getOrganId() == organId)
				retUserList.add(userList.get(i));
		}
		
		if(retUserList.size()==0) return null;
		
		return retUserList;
	}
	
	public static UserList getInstance()
	{
		return UserListHolder.uniqueInstance;
	}

	public boolean join(User user) // 회원가입
	{
		userList.add(user);
		return true;
	}

	public boolean login(String id, String password) // 로그인
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getId().equals(id))
			{
				if (userList.get(i).getPassword().equals(password))
				{
					return true;// 로그인 성공
				}
				return false;// 비밀번호 틀림
			}
		}
		return false; // 아이디 틀림
	}

	public User getUser(String id) // get사용자 목록
	{

		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getId().equals(id))
			{
				return userList.get(i);
			}
		}

		return null; // 일치하는 사용자가 없을 때 null로 반환
	}

	public User getUserList(String id, String userName) // get사용자 목록
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getId().equals(id) && userList.get(i).getName().equals(userName))
				return userList.get(i);
		}

		return null; // 일치하는 사용자가 없을 때 null로 반환
	}

	public ArrayList<User> getUserListFromName(String userName)// get사용자 목록
	{
		ArrayList<User> arr = new ArrayList<User>();
		// System.out.println(userName+"씨발");
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getName().equals(userName))
			{
				arr.add(userList.get(i));
			}
			// System.out.println(userList.get(i).getName());
		}
		return arr;
	}

	public boolean checkId(String id) // 아이디 조회
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getId().equals(id))
			{
				return false;
			}
		}
		return true;
	}

	public boolean checksocialNumber(String socialNumber) // 주민번호 조회
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getSocialNumber().equals(socialNumber))
			{
				return false;
			}
		}
		return true;
	}

	public String getProjectLeaderEmail(String leaderId) // get과제책임자이메일
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getId().equals(leaderId))
			{
				return userList.get(i).getEmail();
			}
		}

		return null;
	}

	public String getUserId(String userName, String socialNumber) // get사용자아이디
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getName().equals(userName) && userList.get(i).getSocialNumber().equals(socialNumber))
			{
				return userList.get(i).getId();
			}
		}

		return null;
	}

	public ArrayList<User> getuserImformation(ArrayList<String> workerid) // get사용자정보
	{
		System.out.println(workerid.get(0) + " 김성욱");
		ArrayList<User> arr = new ArrayList<User>();
		for (int i = 0; i < workerid.size(); i++)
		{
			for (int j = 0; j < userList.size(); j++)
			{
				if (userList.get(j).getId().equals(workerid.get(i)))
					arr.add(userList.get(j));
			}
		}
		System.out.println(arr.get(0).getId() + " 김성욱");
		System.out.println(arr.get(1).getId() + " 김성욱");
		
		return arr;
	}

	public int getuserOrganId(String id) // get사용자사업자번호
	{
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getId().equals(id))
				{
					int x = userList.get(i).getOrganId();
					return x;
				}
		}
		return -999;
	}
	
	public ArrayList<User> getuserorid(int orid) // get사용자정보
	{
		ArrayList<User> arr = new ArrayList<User>();
		
		for (int i = 0; i < userList.size(); i++)
		{
			if(userList.get(i).getOrganId() == orid)
			{
				arr.add(userList.get(i));
			}
		}
		return arr;
	}
}
