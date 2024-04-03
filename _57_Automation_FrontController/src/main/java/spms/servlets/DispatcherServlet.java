package spms.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.controls.Controller;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	
	private void prepareRequestData(HttpServletRequest req, 		// 브라우저가 보내온 Parameter
									Map<String, Object> model, 		// 자동 생성된 객체를 저장할 model 객체
									DataBinding dataBinding)		// Controller에서 자동 생성 정보 추출 위한 인터페이스 
									throws Exception{
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;			// 생성할 객체 이름
		Class<?> dataType = null;		// 데이터 클래스 타입
		Object dataObj = null;			// 생성된 객체
		
		for(int i=0;i<dataBinders.length;i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i+1];
			
			// 데이터 객체 생성
			dataObj = ServletRequestDataBinder.bind(req, dataType, dataName);
			
			// 생성 데이터 model에 저장
			model.put(dataName, dataObj);
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
				resp.setContentType("text/html; charset=UTF-8");
				String servletPath = req.getServletPath();
				System.out.println("DispatchServlet::service() - servletPath=" + servletPath);
		
				Map<String, Object> model = new ConcurrentHashMap<>();
				// model.put("memberDao", this.getServletContext().getAttribute("memberDao"));
				model.put("session", req.getSession());
		
				// 해당 주소와 일치하는 클래스 객체를 꺼내온다.
				Controller pageController = (Controller) this.getServletContext().getAttribute(servletPath);
					
				// DataBinding 인터페이스를 상속받은 pageController인 경우
				if(pageController instanceof DataBinding) {
					prepareRequestData(req, model, (DataBinding)pageController);
				}
			
				/* 아래 대신 Reflection을 사용해서 자동화 처리할 예정
				if ("/member/list.do".equals(servletPath)) {
					// pageController = new MemberListController();
				} else if ("/member/add.do".equals(servletPath)) {
					// pageController = new MemberAddController();
					if (req.getParameter("email") != null) {
						model.put("member", new Member().setEmail(req.getParameter("email"))
								.setPassword(req.getParameter("password")).setName(req.getParameter("name")));
					}
	
				} else if ("/member/update.do".equals(servletPath)) {
					// pageController = new MemberUpdateController();
					if (req.getParameter("email") != null) {
						model.put("member", new Member().setNo(Integer.parseInt(req.getParameter("no")))
								.setEmail(req.getParameter("email")).setName(req.getParameter("name")));
					} else {
						model.put("no", Integer.parseInt(req.getParameter("no")));
					}
				} else if ("/member/delete.do".equals(servletPath)) {
					// pageController = new MemberDeleteController();
					model.put("no", Integer.parseInt(req.getParameter("no")));
				} else if ("/auth/login.do".equals(servletPath)) {
					// pageController = new LogInController();
					if (req.getParameter("email") != null) {
						model.put("loginInfo",
								new Member().setEmail(req.getParameter("email")).setPassword(req.getParameter("password")));
					}
				} else if ("/auth/logout.do".equals(servletPath)) {
					// pageController = new LogOutController();
				}
				*/

				System.out.println("DispatchServlet::service() - pageController=" + pageController.getClass().getName());
				String viewUrl = pageController.execute(model);
	
				for (String key : model.keySet()) {
					req.setAttribute(key, model.get(key));
				}
	
				System.out.println("DispatchServlet::service() - viewUrl=" + viewUrl);
				System.out.println("");
	
				if (viewUrl.startsWith("redirect:")) {
					resp.sendRedirect(viewUrl.substring("redirect:".length()));
					return;
				} else {
					RequestDispatcher rd = req.getRequestDispatcher(viewUrl);
					rd.include(req, resp);
				}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}
