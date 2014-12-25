package Control;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import DAO.DAOCalculate;
import DAO.DAObudget;
import DAO.DAObudgetCareer;
import DAO.DAOexpert;
import DAO.DAOfinEval;
import DAO.DAOorgan;
import DAO.DAOpermission;
import DAO.DAOproject;
import DAO.DAOprojectCareer;
import DAO.DAOuser;
import DAO.*;

public class MainControl implements ServletContextListener
{
	private ServletContext ctx = null;
	DAObudget daoBudget = new DAObudget();
	DAOCalculate daoCal = new DAOCalculate();
	DAOexpert daoExp = new DAOexpert();
	DAOfinEval daoFin = new DAOfinEval();
	DAOorgan daoOrg = new DAOorgan();
	DAOpermission daoPerm = new DAOpermission();
	DAOproject daoProj = new DAOproject();
	DAOuser daoUser = new DAOuser();
	DAOuserPermission daoUserPerm = new DAOuserPermission();
	DAOprojectCareer daoProCar=new DAOprojectCareer();
	DAObudgetCareer daoBudCar= new DAObudgetCareer();
	DAOsubject daOsubject = new DAOsubject();
	DAOpreproject daoPrepro = new DAOpreproject();
	DAOevidence daoEvidence = new DAOevidence();
	DAOclawback daoClawback = new DAOclawback();
	
	
	// Tomcat시작시 아래 메시지 출력
	public void contextInitialized(ServletContextEvent event)
	{
		System.out.println(">>> 과제관리시스템 시작!!");
		daoBudget.loadList();
		daoCal.loadList();
		daoExp.loadList();
		daoFin.loadPlanList();
		daoFin.loadResultList();
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
		daoCal.saveList();
		daoExp.saveList();
		daoFin.savePlanList();
		daoFin.saveResultList();
		daoOrg.saveList();
		daoPerm.saveList();
		daoProj.saveList();
		daoBudget.saveList();
		daoUser.saveList();
		daoUserPerm.saveList();
		daoProCar.saveList();
		daoBudCar.saveList();
		daOsubject.saveList();
		daoPrepro.saveList();
		daoClawback.saveList();
		daoEvidence.saveList();
		this.ctx = null;
	}
}
