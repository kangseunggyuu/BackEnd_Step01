package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

public class DBConnectionPool {
	
	final int PRE_CREATED = 5;

	String url;
	String username;
	String password;
	
	// ArrayList대신 Vector를 변경하다.
	// ArrayList와 Vector는 동일한 구조이지만, Vector 멀티스레드 동기화 처리가 되어 있다.
	List<Connection> connList = new Vector<Connection>();
	
	public DBConnectionPool(String driver, String url,
			String username, String password) throws Exception{
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
		
		preCreatedConnection();
	}
	
	private void preCreatedConnection() throws Exception {
		for(int i=0;i<PRE_CREATED;i++) {
			Connection conn = DriverManager.getConnection(url, username, password);
			connList.add(conn);
		}
	}
	
	public Connection getConnection() throws Exception{
		// 현재 사용하지 않고 있는 Connection 객체가 존재한다면
		if(connList.size() > 0) {			
			// 첫번째 인덱스에 위치한 Connection 객체를 꺼낸다.
			Connection conn = connList.remove(0);
			// 서버와 통신이 연결되어 있다면
			if(conn.isValid(10)) {
				return conn;
			}
		}
		
		// 위의 return conn;이 되지 않았다는 것은 Connection객체가 없거나, 정상적이지 않다는
		// 뜻이므로 새롭게 서버와 연결된 Connection 객체를 생성해서 return한다.
		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws Exception{
		// 사용을 마친 정상적인 Connection 객체를 다시 connList에 저장한다.
		if(conn != null && conn.isValid(10))
			connList.add(conn);
	}
	
	// 모든 Connection 객체의 연결을 종료한다.
	public void closeAll() {
		for(Connection conn : connList)
			try {conn.close();} catch(Exception e) {}
	}
}






















