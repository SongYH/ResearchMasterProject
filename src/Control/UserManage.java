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

   public boolean reqjoin(User user) //회원가입(사용자 :사용자) :boolean
   {
      UserList userlist = UserList.getInstance();

      boolean x = userlist.join(user);
      return x;
   }
   public ArrayList<String> reqOrganName() //req기관이름() :ArrayList<String>
   {
      OrganList organlist = OrganList.getInstance();
     ArrayList<String> arr = new ArrayList<String>();

     for(int i=0;i<organlist.getOrganList().size();i++)
    	 arr.add(organlist.getOrganList().get(i).getOrganName()); 
      
     return arr;
   }
   public boolean reqOrganjoin(Organ organ) //기관등록(기관:기관) : boolean
   {
      OrganList organlist = OrganList.getInstance();

      boolean x = organlist.join(organ);
      return x;
   }

   public boolean reqlogin(String id, String password)  //로그인(아이디 String ,패스워드 String)
   {

      UserList userlist = UserList.getInstance();

      boolean x = userlist.login(id, password);
      return x;
   }

   public User reqUser(String id) //req사용자조회(아이디:String) :User
   {
      UserList userlist = UserList.getInstance();

      User x = userlist.getUser(id);
      return x;
   } 
   public ArrayList<User> reqUserListFromName(String userName) //req사용자목록(이름 :String) :ArrayList<User>
   {
      UserList userlist = UserList.getInstance();

      ArrayList<User> arr = userlist.getUserListFromName(userName);
      return arr;
   }

   public boolean reqcheckId(String id) //req아이디확인(id:String) : boolean
   {
      UserList userlist = UserList.getInstance();

      boolean x = userlist.checkId(id);
      return x;
   }

   public boolean reqchecksocialNumber(String socialNumber) //req주민등록번호확인(주빈등록번호:String) : boolean
   {
      UserList userlist = UserList.getInstance();

      boolean x = userlist.checksocialNumber(socialNumber);
      return x;
   }

   public String reqProjectLeaderEmail(String leaderId) //req과제책임자이메일(아이디:String):String
   {
      UserList userlist = UserList.getInstance();

      String email = userlist.getProjectLeaderEmail(leaderId);
      return email;
   }

   public String reqUserId(String userName, String socialNumber) //req사용자아이디(이름,주민번호):String
   {
      UserList userlist = UserList.getInstance();

      String id = userlist.getUserId(userName, socialNumber);
      return id;
   }

   public ArrayList<User> requserImformation(ArrayList<String> workerid) //req사용자정보(구성원아이디목록:ArrayList<String>):ArrayList<User>
   {
      UserList userlist = UserList.getInstance();

      ArrayList<User> arr = userlist.getuserImformation(workerid);
      return arr;
   }

   public String reqOrganImformation(int workernum) //req기관이름(사업자번호:int):String
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

   public int requserOrganId(String id) //req사용자사업자번호(아이디:String):integer
   {
      UserList userlist = UserList.getInstance();

      int x = userlist.getuserOrganId(id);
      return x;
   }

   public ArrayList<User> reqUserListFromOrganName(String organName) //req사용자목록(기관명:String):ArrayList<User>
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

   public int reqgetorgannum(String orname) //req기관이름사업자번호(기관명:String):Integer
   {
      OrganList organlist = OrganList.getInstance();
      
      int x= organlist.getorgannum(orname);
      
      return x;
   }
   
   public ArrayList<Organ> requserOrganlist(ArrayList<String> userid) //req기관정보(아이디:ArrayList<String>):ArrayList<Organ>
   {
	   ArrayList<Organ> arr = new ArrayList<Organ>();
	   ArrayList<Integer> intarr = new ArrayList<Integer>();
	   OrganList organlist = OrganList.getInstance();
	   UserList userlist = UserList.getInstance();
	   
	   for(int i=0;i<userid.size();i++)
		   intarr.add(userlist.getuserOrganId(userid.get(i)));
	  
	   for(int i=0;i<intarr.size();i++)
		   arr.add(organlist.getidorgans(intarr.get(i)));
	   
	   return arr;
   }
   
   public ArrayList<User> requserorid(int orid) //req사용자목록(사업자번호:int):ArrayList<User>
   {
	   UserList userlist = UserList.getInstance();
	   ArrayList<User> arr = new ArrayList<User>();
	   arr = userlist.getuserorid(orid);
	   return arr;
   }
}