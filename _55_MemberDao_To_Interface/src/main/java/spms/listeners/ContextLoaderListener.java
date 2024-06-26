package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberListController;
import spms.dao.MySqlMemberDao;


//@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextInitialized() 호출");
		
		try {
			ServletContext sc = sce.getServletContext();
			

			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
						"java:comp/env/jdbc/studydb");
			
			MySqlMemberDao memberDao = new MySqlMemberDao();
			memberDao.setDataSource(ds);
			
			sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());			
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));			
			sc.setAttribute("/member/add.do", new MemberListController().setMemberDao(memberDao));				
			sc.setAttribute("/member/update.do", new MemberListController().setMemberDao(memberDao));				
			sc.setAttribute("/member/delete.do", new MemberListController().setMemberDao(memberDao));		
			
			// sc.setAttribute("memberDao", memberDao);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextDestroyed() 호출");

	}
}







