package Control;

import java.util.ArrayList;
import java.util.Date;

import Common.Expert;
import Common.MidEvalPlan;
import Common.MidEvalResult;
import Common.Notice;
import Common.Project;
import Entity.MidEvalPlanList;
import Entity.MidEvalResultList;
import Control.UserManage;

public class MidEvalManager {
	
	//권한명 획득
	public String reqUserPermission(String loginId)
	{
		PermissionManager perManager = new PermissionManager();		// 권한을 확인하기위한 권한관리객체 생성
		String result = perManager.confirmUserPermission(loginId);			// 권한을 아이디를 통해 가져옴 
		
		return result;
	}
	
	//중간평가 결과 조회
	public MidEvalResult requestMidEvalResult(int projectNumber)
	{
		MidEvalResultList midEvalResult = MidEvalResultList.getInstance();
		return midEvalResult.getMidEvalResult(projectNumber);
	}
	
	//등록된 과제받아오기
	public ArrayList<Project> requestProjectsForCharger()
	{
		ArrayList<Project> result = new ArrayList<Project>();
		ProjectManager proManager = new ProjectManager();
		
		result = proManager.reqProjectList("상태", "등록승인");
		return result;
	}
	
	//임의선정된 전문가목록 받아오기
	public ArrayList<Expert> requestExpertsForCharger(int projectNum)
	{
		ArrayList<Expert> result = new ArrayList<Expert>();
		ProjectManager proManager = new ProjectManager();
		ExpertManager exManager = new ExpertManager();
		String field = proManager.reqProjectSearchNumber(projectNum).getType();
		result = exManager.requestRandomExperts(field);
		
		return result;
	}
	
	//전문가가 평가할 과제목록 받아오기
	public ArrayList<MidEvalPlan> requestMidEvalPlanForExpert(String id)
	{
		ArrayList<MidEvalPlan> result = new ArrayList<MidEvalPlan>();
		MidEvalPlanList midEvalPlan = MidEvalPlanList.getInstance();
		
		result = midEvalPlan.getMidEvalPlanListForEval(id);
		
		return result;
	}
	
	//과제책임자에게 줄 중간평가결과목록 받아오기
	public ArrayList<MidEvalResult> requestMidEvalResultForPl(String id)
	{
		ArrayList<MidEvalResult> result = new ArrayList<MidEvalResult>();
		MidEvalResultList midEvalResult = MidEvalResultList.getInstance();
		
		result = midEvalResult.getMidEvalResultForPl(id);
		
		return result;
	}
	
	//중간평가계획 추가
	
	public boolean requestAddMidEvalPlan(int projectNum, String expertLeaderId, String expertLeaderEmail, ArrayList<String> expertsEmail, String fileName, Date noticeDate)
	{
		//통보엑티브컨트롤.추가();
		//통보엑티브에 추가
		
		NoticeManager notManager = NoticeManager.getInstance();
		UserManage userManager = new UserManage();
		ProjectManager proManager = new ProjectManager();
		
		Project project = proManager.reqProjectSearchNumber(projectNum);
		String plEmail = userManager.reqProjectLeaderEmail(project.getLeaderId());
		
		notManager.add(new Notice(noticeDate, "중간평가계획", "중간평가계획안", expertLeaderEmail, fileName));
		notManager.add(new Notice(noticeDate, "중간평가계획", "중간평가계획안", plEmail, fileName));
		
		for(int i=0; i<expertsEmail.size(); i++)
		{
			notManager.add(new Notice(noticeDate, "중간평가계획", "중간평가계획안", expertsEmail.get(i), fileName));
		}
		
		
		MidEvalPlanList planList = MidEvalPlanList.getInstance();
		MidEvalPlan addPlan = new MidEvalPlan(projectNum, expertLeaderId, expertLeaderEmail, 
				expertsEmail, plEmail, noticeDate, fileName );
		
		proManager.setChangeProjectStatus(projectNum, "중간평가계획");
		
		return planList.addMidEvalPlan(addPlan);
	}
	
	
	//중간평가결과 추가
	public boolean requestAddMidEvalResult(int projectNum, String result, String fileName)
	{
		ProjectManager proManager = new ProjectManager();
		
		Project project = proManager.reqProjectSearchNumber(projectNum);
		
		//중간평가결과Entity.추가();
		MidEvalResultList resultList = MidEvalResultList.getInstance();
		MidEvalResult addResult = new MidEvalResult(project.getProjectNumber(), project.getLeaderId(), result, fileName);
		
		//평가계획Entity에서 삭제();
		MidEvalPlanList planList = MidEvalPlanList.getInstance();
		planList.deleteMidEvalPlan(projectNum);
		
		//과제 상태 변경
		proManager.setChangeProjectStatus(project.getProjectNumber(), "중간평가완료");
		
		resultList.addMidEvalResult(addResult);
		
		return true;
	}
}
