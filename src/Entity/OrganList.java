package Entity;

import java.util.ArrayList;

import Common.Organ;

public class OrganList
{
   private ArrayList<Organ> organList;

   private static class OrganListHolder
   {
      static final OrganList uniqueInstance = new OrganList();
   }

   private OrganList()
   {
   }

   public static OrganList getInstance()
   {
      return OrganListHolder.uniqueInstance;
   }

   public ArrayList<Organ> getOrganList()
   {
      return organList;
   }
   
   public int getorgannum(String orname)
   {
      for(int i=0;i<organList.size();i++)
      {
         if(organList.get(i).getOrganName().equals(orname))
            return organList.get(i).getOrganId();
      }
      return 0;
   }

   public void setOrganList(ArrayList<Organ> organList)
   {
      this.organList = organList;
   }

   public boolean join(Organ organ) // 회원가입
   {
      organList.add(organ);
      return true;
   }
   public Organ getidorgans(int orid)
   {
	   
	   Organ or = new Organ();
	   
	   for(int i=0;i<organList.size();i++)
	   {
		   if(organList.get(i).getOrganId()==orid)
		   {
			   or = organList.get(i);
			   return or;
		   }
	   }
	   
	   return null;
   }
}