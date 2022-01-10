package com.green.view.controller;

import java.util.HashMap;
import java.util.Map;

	/*
	 * 모든 Controller 객체들을 저장하고 있다가, 클라이언트의 요청이 들어오면
	   요청을 처리할 특정 Controller를 검색하는 기능을 제공.
	 HandlerMapping 객체는 DispatcherServlet이 사용하는 객체로서 Dispatcher가
	   생성되고 init() 메소드가 호출될 때 한번만 생성됨.
	 */

public class HandlerMapping {

	// Controller 객체 등록
	private Map<String, Controller> mappings;

	public HandlerMapping() {

		mappings = new HashMap<String, Controller>();

		// Controller
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}
	
	/*
	 *  요청 URL에 대해, 적절한 업무처리 Controller를 전달한다.
	 */
	public Controller getController(String path) {
		
		return mappings.get(path); // login.do 매핑
		
	}
}
