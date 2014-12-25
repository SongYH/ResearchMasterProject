package Entity;

import java.util.ArrayList;

import Common.PreProject;
import Common.Project;
import DAO.DAOpreproject;

public class PreProjectList {
	private ArrayList<PreProject> preProjectList;
	
	private static class PreProjectListHolder
	{
		static final PreProjectList uniqueInstance = new PreProjectList();
	}
	
	public static PreProjectList getInstance()
	{
		return PreProjectListHolder.uniqueInstance;
	}
	
	public ArrayList<PreProject> getIdpreProjectList(String id){ 
		// 해당id 신청과제목록
		ArrayList<PreProject> resultList = new ArrayList<PreProject>();
		
		for(int i = 0; i < preProjectList.size(); i++){
			if(preProjectList.get(i).getId().equals(id)){
				resultList.add(preProjectList.get(i));
			}
		}
		return resultList;
	}
	
	public PreProject getStatuspreProjectList(String id, String status){
		// 상태가 선정된 과제
		PreProject result = null;
		
		for(int i = 0; i < preProjectList.size(); i++){
			if(preProjectList.get(i).getId().equals(id) && preProjectList.get(i).getStatus().equals(status)){
				result = preProjectList.get(i);
			}
		}
		return result;
	}
	
	public ArrayList<PreProject> getStatuspreProjectList(String status){
		// 상태가 선정된 과제
		ArrayList<PreProject> result = new ArrayList<PreProject>();
		
		for(int i = 0; i < preProjectList.size(); i++){
			if(preProjectList.get(i).getStatus().equals(status)){
				result.add(preProjectList.get(i));
			}
		}
		return result;
	}
	public boolean setPreProjectStatu(int num, String status)
	{
		System.out.println(num + "  " + status + " 리스트");
		for (PreProject p : preProjectList)
		{
			if (p.getProjectNumber() == num)
			{
				p.setStatus(status);
				return true;
			}
		}
		return false;
	}
	public boolean getDuplicationCheck(String subjectName, int organId, String type, String area){ 
		// 과제신청 중복여부확인
		boolean result = false;
		
		for(int i = 0; i < preProjectList.size(); i++){
			if(preProjectList.get(i).getOrganId() == organId && 
				preProjectList.get(i).getSubjectName().equals(subjectName) &&
				preProjectList.get(i).getType().equals(type) &&
				preProjectList.get(i).getArea().equals(area)){
				result = true;
				System.out.println("중복 신청");
				break;
			}
		}
		return result;
	}
	
	public void savePreProject(int organId, String fileName, String id, String type, String area, String subjectName){ 
		// 신청과제정보저장
		PreProject savePreProject = new PreProject(preProjectList.size()+1, organId, subjectName, "신청", type, area, id, fileName);

		preProjectList.add(savePreProject);
	}

	public ArrayList<PreProject> getPreProjectList() {
		return preProjectList;
	}

	public void setPreProjectList(ArrayList<PreProject> preProjectList) {
		this.preProjectList = preProjectList;
	}
	
	
}
