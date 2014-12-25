package Entity;
import java.util.ArrayList;

import Common.Expert;
import DAO.DAOexpert;

/**
 * 우리가 그린 Class 다이어그램에서
 * 목록 Entity에 해당하는 클래스
 * UML에서 나타낸 함수들을 여기서 만들면된다.
 * @author JM
 *
 */
public class ExpertList
{
	private ArrayList<Expert> expertList;
	
	private static class ExpertListHolder
	{
		static final ExpertList uniqueInstance = new ExpertList();
	}

	public static ExpertList getInstance()
	{
		return ExpertListHolder.uniqueInstance;
	}
///////////////////////////////////////////////////////////////////////
	
	///전문가추가
	public void addExpert(Expert expert)
	{
		expertList.add(expert);
	}
	
	
	///전문가번호로 전문가 얻어오기
	public Expert getExpert(int expertNumber)
	{
		Expert expert = null;
		for(int i=0; i<expertList.size(); i++)
		{
			if(expertList.get(i).getExpertNumber() == expertNumber)
				expert = expertList.get(i);
		}
		return expert;
	}
	
	///전문가 이름으로 전문가목록 얻어오기
	public ArrayList<Expert> getExpertsByName(String name)
	{
		ArrayList<Expert> experts = new ArrayList<Expert>();
		for(int i=0; i<expertList.size(); i++)
		{
			if(expertList.get(i).getName().equals(name))
			{
				experts.add(expertList.get(i));
			}
		}
		return experts;
	}
	
	///기관번호로 전문가목록 얻어오기
	public ArrayList<Expert> getExpertsByOrgan(int OrganId)
	{
		ArrayList<Expert> experts = new ArrayList<Expert>();
		for(int i=0; i<expertList.size(); i++)
		{
			if(expertList.get(i).getOrganId() == OrganId)
			{
				experts.add(expertList.get(i));
			}
		}
		return experts;
	}
	
	///전문가 정보 변경
	public void setExpertInfo(Expert expert)
	{
		for(int i=0; i<expertList.size(); i++)
		{
			if(expertList.get(i).getExpertNumber() == expert.getExpertNumber())
			{ 
				expertList.set(i, expert);
			}
		}
	}
	
	//전문가 임의 선정
	public ArrayList<Expert> getRandomExperts(String field)
	{
		ArrayList<Expert> selectedExperts = new ArrayList<Expert>();
		ArrayList<Expert> randomExperts = new ArrayList<Expert>();
		
		int random=0;
		boolean check = true;
		
		
		for(int i=0; i<expertList.size(); i++)
		{
			if(expertList.get(i).getField().equals(field))
			{
				selectedExperts.add(expertList.get(i));
			}
		}
		
		while(randomExperts.size()<6)
		{
			random = (int)(Math.random()*selectedExperts.size());
			
			
			for(int i=0; i<randomExperts.size(); i++)
			{
				if(randomExperts.get(i).getId().equals(selectedExperts.get(random).getId()))
					check = false;	//중복되는게 이미 뽑혀있음
			}
			
			if(check==true)
			{
				randomExperts.add(selectedExperts.get(random));
			}
			else
			{
				check = true;
			}
		}
		
		return randomExperts;
	}
	public ArrayList<Expert> getExpertList()
	{
		return expertList;
	}
	public void setExpertList(ArrayList<Expert> expertList)
	{
		this.expertList = expertList;
	}
	
	
}
