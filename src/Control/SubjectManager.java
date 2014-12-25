package Control;

import java.util.ArrayList;

import Common.PreProject;
import Common.Subject;
import Entity.PreProjectList;
import Entity.ProjectList;
import Entity.SubjectList;
import Entity.UserList;

public class SubjectManager {
	
	public SubjectManager(){}
	
	public ArrayList<Subject> request_Type_SubjectList(String type){ 
		// 분야별 신청대상과제목록
		SubjectList s = SubjectList.getInstance();
		return s.getTypeSubjectList(type);
	}
	
	public int request_Application_Availability(String id, String subjectName, String type, String area){ 
		// 신청가능여부확인
		
		UserManage um = new UserManage();
		int organId = um.requserOrganId(id); // 사용자 기관정보 확인
		
		if(organId == 0 || organId == -999) // 사용자 기관등록정보가 없을 시
			return 1;
		
		PreProjectList pl = PreProjectList.getInstance();
		
		if(pl.getDuplicationCheck(subjectName, organId, type, area)) // 중복 신청 확인
			return 2; 		
		
		return 0; // 신청 가능 
	}
	
	// 과제 상태 변경 (과제번호 / 상태)
	public boolean setChangePreProjectStatus(int num, String state)
	{
		PreProjectList preprojectList = PreProjectList.getInstance();
		if (preprojectList.setPreProjectStatu(num, state))
			return true;
		else
			return false;
	}
		
	public void request_AddpreProject(int organId, String fileName, String id, String type, String area, String subjectName){ 
		// 신청과제등록
		PreProjectList p = PreProjectList.getInstance();
		
		p.savePreProject(organId, fileName, id, type, area, subjectName);
	}
	
	public ArrayList<PreProject> request_PreProjectList(String id){ 
		// 신청과제조회
		PreProjectList pl = PreProjectList.getInstance();
		return pl.getIdpreProjectList(id);
	}
	
	public PreProject request_PreProject(String id, String status){ 
		// 신청과제
		PreProjectList f = PreProjectList.getInstance();
		return f.getStatuspreProjectList(id, status);
	}
	
	public int request_organId(String id){
		
		UserManage um = new UserManage();
		int organId = um.requserOrganId(id); // 사용자 기관정보 확인
		
		if(organId == 0 || organId == -999) // 사용자 기관등록정보가 없을 시
			return 1;
		
		return organId;
	}
	
	public PreProject request_PreProjectList(String id, String status){ 
	      // 신청과제조회
	      PreProjectList pl = PreProjectList.getInstance();
	      return pl.getStatuspreProjectList(id, status);
	}
}
