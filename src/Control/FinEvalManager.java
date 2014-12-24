package Control;

import java.util.ArrayList;
import java.util.Date;

import Common.Expert;
import Common.FinEvalPlan;
import Common.FinEvalResult;
import Common.Notice;
import Common.Project;
import Entity.FinEvalPlanList;
import Entity.FinEvalResultList;
import Control.UserManage;

public class FinEvalManager
{

	// 최종평가 결과 조회
	public FinEvalResult requestFinEvalResult(int projectNumber)
	{
		FinEvalResultList finEvalResult = FinEvalResultList.getInstance();
		return finEvalResult.getFinEvalResult(projectNumber);
	}

	// 정산완료된 과제목록 받아오기
	public ArrayList<Project> requestProjectsForCharger()
	{
		ArrayList<Project> result = new ArrayList<Project>();
		ProjectManager proManager = new ProjectManager();

		result = proManager.reqProjectList("상태", "정산");
		return result;
	}

	// 임의선정된 전문가목록 받아오기
	public ArrayList<Expert> requestExpertsForCharger(int projectNum)
	{
		ArrayList<Expert> result = new ArrayList<Expert>();
		ProjectManager proManager = new ProjectManager();
		ExpertManager exManager = new ExpertManager();
		String field = proManager.reqProjectSearchNumber(projectNum).getType();
		result = exManager.requestRandomExperts(field);

		return result;
	}

	// 전문가가 평가할 과제목록 받아오기
	public ArrayList<FinEvalPlan> requestFinEvalPlanForExpert(String id)
	{
		ArrayList<FinEvalPlan> result = new ArrayList<FinEvalPlan>();
		FinEvalPlanList finEvalPlan = FinEvalPlanList.getInstance();

		result = finEvalPlan.getFinEvalPlanListForEval(id);

		return result;
	}

	// 과제책임자에게 줄 최종평가결과목록 받아오기
	public ArrayList<FinEvalResult> requestFinEvalResultForPl(String id)
	{
		ArrayList<FinEvalResult> result = new ArrayList<FinEvalResult>();
		FinEvalResultList finEvalResult = FinEvalResultList.getInstance();

		result = finEvalResult.getFinEvalResultForPl(id);

		return result;
	}

	// 최종평가계획 추가

	public boolean requestAddFinEvalPlan(int projectNum, String expertLeaderId, String expertLeaderEmail,
			ArrayList<String> expertsEmail, String fileName, Date noticeDate)
	{
		// 통보엑티브컨트롤.추가();
		// 통보엑티브에 추가

		NoticeManager notManager = NoticeManager.getInstance();
		UserManage userManager = new UserManage();
		ProjectManager proManager = new ProjectManager();

		Project project = proManager.reqProjectSearchNumber(projectNum);
		String plEmail = userManager.reqProjectLeaderEmail(project.getLeaderId());

		notManager.add(new Notice(noticeDate, "최종평가계획", "최종평가계획안", expertLeaderEmail, fileName));
		notManager.add(new Notice(noticeDate, "최종평가계획", "최종평가계획안", plEmail, fileName));

		for (int i = 0; i < expertsEmail.size(); i++)
		{
			notManager.add(new Notice(noticeDate, "최종평가계획", "최종평가계획안", expertsEmail.get(i), fileName));
		}

		FinEvalPlanList planList = FinEvalPlanList.getInstance();
		FinEvalPlan addPlan = new FinEvalPlan(projectNum, expertLeaderId, expertLeaderEmail, expertsEmail, plEmail,
				noticeDate, fileName);

		proManager.setChangeProjectStatus(projectNum, "최종평가계획");

		return planList.addFinEvalPlan(addPlan);
	}

	// 최종평가결과 추가
	public boolean requestAddFinEvalResult(int projectNum, String result, String fileName)
	{
		ProjectManager proManager = new ProjectManager();

		Project project = proManager.reqProjectSearchNumber(projectNum);

		// 최종평가결과Entity.추가();
		FinEvalResultList resultList = FinEvalResultList.getInstance();
		FinEvalResult addResult = new FinEvalResult(project.getProjectNumber(), project.getLeaderId(), result, fileName);

		// 과제 상태 변경
		proManager.setChangeProjectStatus(project.getProjectNumber(), "최종평가완료");

		resultList.addFinEvalResult(addResult);

		return true;
	}
}