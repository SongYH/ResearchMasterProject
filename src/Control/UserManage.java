package Control;

import java.util.ArrayList;

import Common.Organ;
import Common.User;
import Entity.OrganList;
import Entity.UserList;

public class UserManage
{
	public UserManage()
	{

	}

	public boolean join(User user)
	{
		UserList userlist = UserList.getInstance();

		boolean x = userlist.join(user);
		return x;
	}
	public ArrayList<Organ> getOrgans()
	{
		OrganList organlist = OrganList.getInstance();
		
		ArrayList<Organ> arr = new ArrayList<Organ>();
		arr = organlist.getOrganList(); 
		return arr;
	}
	public boolean Organjoin(Organ organ)
	{
		OrganList organlist = OrganList.getInstance();

		boolean x = organlist.join(organ);
		return x;
	}

	public boolean login(String id, String password)
	{

		UserList userlist = UserList.getInstance();

		boolean x = userlist.login(id, password);
		return x;
	}

	public User reqUser(String id)
	{
		UserList userlist = UserList.getInstance();

		User x = userlist.getUser(id);
		return x;
	}

	public User reqUserList(String id, String userName)
	{
		UserList userlist = UserList.getInstance();

		User x = userlist.getUserList(id, userName);
		return x;
	}

	public ArrayList<User> reqUserListFromName(String userName)
	{

		// System.out.println(userName);

		UserList userlist = UserList.getInstance();

		ArrayList<User> arr = userlist.getUserListFromName(userName);
		return arr;
	}

	public boolean reqcheckId(String id)
	{
		UserList userlist = UserList.getInstance();

		boolean x = userlist.checkId(id);
		return x;
	}

	public boolean reqchecksocialNumber(String socialNumber)
	{
		UserList userlist = UserList.getInstance();

		boolean x = userlist.checksocialNumber(socialNumber);
		return x;
	}

	public String reqProjectLeaderEmail(String leaderId)
	{
		UserList userlist = UserList.getInstance();

		String email = userlist.getProjectLeaderEmail(leaderId);
		return email;
	}

	public String reqUserId(String userName, String socialNumber)
	{
		UserList userlist = UserList.getInstance();

		String id = userlist.getUserId(userName, socialNumber);
		return id;
	}

	public ArrayList<User> requserImformation(ArrayList<String> workerid)
	{
		UserList userlist = UserList.getInstance();

		ArrayList<User> arr = userlist.getuserImformation(workerid);
		return arr;
	}

	public ArrayList<User> reqUserListFromOrganId(int organid)
	{
		UserList userlist = UserList.getInstance();

		userlist.getUserListFromOrganId(organid);
		return null;
	}
	
	public int reqgetorgannum(String orname)
	{
		OrganList organlist = OrganList.getInstance();
		
		int x= organlist.getorgannum(orname);
		
		return x;
	}

	public String reqOrganImformation(int workernum)
	{
		OrganList organlist = OrganList.getInstance();
		ArrayList<Organ> ol = organlist.getOrganList();
		for(Organ o : ol)
		{
			if(o.getOrganId() == workernum)
				return o.getOrganName();
		}
		return null;
	}

	public int requserOrganId(String id)
	{
		UserList userlist = UserList.getInstance();

		int x = userlist.getuserOrganId(id);

		System.out.println("컨트롤" +x);
		return x;
	}

	public ArrayList<User> reqUserListFromOrganName(String organName) 
	{
		UserList userlist = UserList.getInstance();
		OrganList organlist = OrganList.getInstance();
		ArrayList<Organ> orgarr = organlist.getOrganList();
		ArrayList<User> userarr = userlist.getUserList();
		ArrayList<User> arr = new ArrayList<User>();
		ArrayList<Integer> x = new ArrayList<Integer>();
		for (int i = 0; i < orgarr.size(); i++)
		{
			if (orgarr.get(i).getOrganName().equals(organName))
			{
				int organid = orgarr.get(i).getOrganId();
				x.add(organid);
			}
		}

		for (int i = 0; i < userarr.size(); i++)
		{
			int organid = userarr.get(i).getOrganId();
			for (int j = 0; j < orgarr.size(); j++)
			{
				if (organid == orgarr.get(j).getOrganId())
				{
					arr.add(userarr.get(i));
				}
			}
		}
		return arr;
	}
}
