package study01.servelts;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* GenericServlet은 1개의 추상메소드를 가진 추상 클래스이다.
 * servlet 인터페이스가 상속시켜준 나머지 메소드는 default로 구현되어 있고
 * 반드시 서비스에 필요한 service만 개발자가 구현하도록 한다. */
@SuppressWarnings("serial")
public class HelloWorld extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service() 호출");
		
	}
	
}
