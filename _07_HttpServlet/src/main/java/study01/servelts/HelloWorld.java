package study01.servelts;

import javax.servlet.http.HttpServlet;

/* Servlet 인터페이스(서블릿 생태계 최상위)
 * - 나머지도 모두 구현해야 한다.
 * - service() 
 * GenericServer 추상 클래스 implemented Servlet
 * - service()만 구현한다
 * - service()로 get/post 모두 전달된다.
 * HttpServlet 추상 클래스 extends GenericServlet
 * - doGet()은 get요청 처리 
 * - doPost()는 post요청 처리*/
@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet {

}
