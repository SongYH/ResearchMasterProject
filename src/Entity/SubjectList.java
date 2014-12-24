package Entity;

import java.util.ArrayList;

import Common.Subject;
import DAO.DAOsubject;

public class SubjectList {
	private ArrayList<Subject> subjectList;
	
	private static class SubjectListHolder
	{
		static final SubjectList uniqueInstance = new SubjectList();
	}
	public static SubjectList getInstance()
	{
		return SubjectListHolder.uniqueInstance;
	}
	
	public ArrayList<Subject> getTypeSubjectList(String type){ // 해당분야신청대상과제목록
		
		if(type.equals("전체")){
			return subjectList;
		}
		ArrayList<Subject> resultList = new ArrayList<Subject>();
		
		for(int i = 0; i < subjectList.size(); i++){
			if(subjectList.get(i).getType().equals(type)){
				resultList.add(subjectList.get(i));
			}
		}
		
		return resultList;
	}

	public ArrayList<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(ArrayList<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	
}
