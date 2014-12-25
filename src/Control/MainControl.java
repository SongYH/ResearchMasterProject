package Control;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import DAO.DAOCalculate;
import DAO.DAObudget;
import DAO.DAObudgetCareer;
import DAO.DAOclawback;
import DAO.DAOevidence;
import DAO.DAOexpert;
import DAO.DAOfinEval;
import DAO.DAOorgan;
import DAO.DAOpermission;
import DAO.DAOpreproject;
import DAO.DAOproject;
import DAO.DAOprojectCareer;
import DAO.DAOsubject;
import DAO.DAOuser;
import DAO.DAOuserPermission;
import DAO.*;

public class MainControl implements ServletContextListener
{
	private ServletContext ctx = null;
	DAObudget daoBudget = new DAObudget();
	DAObudgetCareer daoBudCar= new DAObudgetCareer();
	DAOCalculate daoCal = new DAOCalculate();
	DAOclawback daoClawback = new DAOclawback();
	DAOevidence daoEvidence = new DAOevidence();
	DAOexpert daoExp = new DAOexpert();
	DAOfinEval daoFin = new DAOfinEval();
	DAOmidEval daoMid = new DAOmidEval();
	DAOorgan daoOrg = new DAOorgan();
	DAOpermission daoPerm = new DAOpermission();
	DAOpreproject daoPrepro = new DAOpreproject();
	DAOproject daoProj = new DAOproject();
	DAOprojectCareer daoProCar=new DAOprojectCareer();
	DAOsubject daOsubject = new DAOsubject();
	DAOuser daoUser = new DAOuser();
	DAOuserPermission daoUserPerm = new DAOuserPermission();
	
	
	// Tomcat시작시 아래 메시지 출력
	public void contextInitialized(ServletContextEvent event)
	{
		System.out.println(">>> 과제관리시스템 시작!!");
		daoBudget.loadList();
		daoCal.loadList();
		daoExp.loadList();
		daoFin.loadPlanList();
		daoFin.loadResultList();
		daoMid.loadPlanList();
		daoMid.loadResultList();
		daoOrg.loadList();
		daoPerm.loadList();
		daoProj.loadList();
		daoUser.loadList();
		daoUserPerm.loadList();
		daoProCar.loadList();
		daoBudCar.loadList();
		daOsubject.loadList();
		daoPrepro.loadList();
		daoClawback.loadList();
		daoEvidence.loadList();
		this.ctx = event.getServletContext();
	}
	
	// Tomcat Shutdown 시키면 아래 메시지 확인
	public void contextDestroyed(ServletContextEvent event)
	{
		System.out.println(">>> 과제관리시스템 종료!!");
		daoOrg.saveList();	//
		daoUser.saveList();//
		daOsubject.saveList();
		daoPrepro.saveList();
		daoPerm.saveList();//
		
		daoUserPerm.saveList();//
		
		daoProj.saveList();//
		daoBudget.saveList();//
		daoProCar.saveList();
		daoBudCar.saveList();
		daoClawback.saveList();
		daoEvidence.saveList();
		daoCal.saveList();	//
		daoExp.saveList();
		
		daoFin.savePlanList();	//
		daoFin.saveResultList();//
		daoMid.savePlanList();	//
		daoMid.saveResultList();//
		this.ctx = null;
	}
}
