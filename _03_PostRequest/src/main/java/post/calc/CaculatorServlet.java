package post.calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/calculator")
public class CaculatorServlet extends HttpServlet {
	
	private Hashtable<String, Operator> operatorTable = new Hashtable<>();
	
	public CaculatorServlet() {
		// 커맨드, 처리 클래스
		operatorTable.put("+", new AddOperator());		
		operatorTable.put("-", new SubOperator());
		operatorTable.put("*", new MulOperator());
		operatorTable.put("/", new DivOperator());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CaculatorServlet - doGet......");
		// 브라우저가 보낸 파라미터를 꺼낸다.
		String op = req.getParameter("op");
		double v1 = Double.parseDouble(req.getParameter("v1"));
		double v2 = Double.parseDouble(req.getParameter("v2"));
		
		// 보내기 전에 utf-8로 알려줘서 한글이 깨지지 않도록 설정
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();		// 객체 내부에 tcp 소켓 스트림이 존재
		
		// 브라우저에 표현될 html
		out.println("<html><body>");
		out.println("<h1>계산 결과</h1>");
		out.println("결과: ");
		
		try {
			// 연산자의 종류에 따라 처리할 클래스를 꺼냄
			Operator operator = operatorTable.get(op);
			if(operator == null)
				out.println("존재하지 않는 연산자입니다.");
			else {
				// 사칙 연산 클래스 모두 Operator의 상속을 받았으므로 해당 클래스의 메서드가 자동으로 호출
				// 다형성
				double result = operator.execute(v1, v2);
				out.println(String.format("%.3f", result));
			}
		}catch(Exception e) {
			out.println("연산 오류가 발생했습니다.");
		}
		
		out.println("</body></html>");
	}
}