package Control;

import java.util.ArrayList;

import Common.Expert;
import Entity.ExpertList;

public class ExpertManager {
	
	public void requestAddExpert(Expert expert)
	{
		ExpertList expertList = ExpertList.getInstance();
		expertList.addExpert(expert);
	}
	
	public ArrayList<Expert> requestSearchExpert(int expertNumber)
	{
		ArrayList<Expert> retList = new ArrayList<Expert>();
		
		ExpertList expertList = ExpertList.getInstance();
		Expert x = expertList.getExpert(expertNumber);
		retList.add(x);
		
		return retList;
	}
	
	public ArrayList<Expert> requestSearchExpertsByName(String name)
	{
		ExpertList expertList = ExpertList.getInstance();
		return expertList.getExpertsByName(name);
	}
	
	//수정완료
	public ArrayList<Expert> requestSearchExpertsByOrgan(String organName)
	{
		ExpertList expertList = ExpertList.getInstance();
		UserManage manager = new UserManage();
		
		int organNumber = manager.reqgetorgannum(organName);
		return expertList.getExpertsByOrgan(organNumber);
	}
	
	public ArrayList<Expert> requestRandomExperts(String field)
	{
		ExpertList expertList = ExpertList.getInstance();
		return expertList.getRandomExperts(field);
	}
}
