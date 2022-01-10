package com.green.view.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.green.biz.board.BoardService;
import com.green.biz.dto.BoardDAO;
import com.green.biz.dto.BoardVO;

/*
 *  게시판 관련 컨트롤러
 */

@Controller
@SessionAttributes("board")
public class BoradController {
	
	@Autowired
	private BoardService boardService;
	
	/* 게시글 등록 */
	@RequestMapping(value = "/insertBoard.do") // client 화면에서 "/insertBoard.do"라는 요청이 들어오면 아래의 메소드 수행설정
	// 스프링 컨테이너가 insertBoard() 메소드를 실행할때 Command 객체를 생성하여 사용자가 입력한 값을 설정하여 넘겨준다
	public String insertBoard(BoardVO vo) { // BoardVO command 객체 등록

		System.out.println("게시글 등록처리");

		// 2. DB 연동 처리
		boardService.insertBoard(vo);

		return "getBoardList.do";

	}

	/* 게시글 수정 */
	
	/*
	 * @SessionAttribute로 설정하면 GetBoard 에서 "board" 속성으로 저장된 값이 저장되고
	 * @ModelAttribute를 이용하여 BoardVO 객체에 기존 값이 저장됨.
	 * 화면에서 읽어온 수정값이 BoardVO 객체에 반영됨 
	 */
	
	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("게시글 수정처리");
		System.out.println("작성자 이름: " + vo.getWriter());

		boardService.updateBoard(vo);

		// 수정작업후 게시물 리스트 화면 출력
		return "getBoardList.do";
	}

	/* 게시글 삭제 */
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {

		System.out.println("게시글 삭제처리");

		boardService.deleteBoard(vo);

		return "getBoardList.do";
	}

	/* 게시글 상세 조회 */
	@RequestMapping(value = "/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {

		System.out.println("게시글 상세조회 처리");

		// 게시글 번호 입력값 추출
		BoardVO board = boardService.getBoard(vo);

		// 응답화면 구성
		model.addAttribute("board", board);

		return "getBoard.jsp";
	}

	/* 검색 조건 목록 설정 */
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {

		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");

		return conditionMap;
	}

	/* 게시글 목록 조회 */

	@RequestMapping(value = "/getBoardList.do")
	public String getBoardList(

			// 게시글 검색(name = value, defaultValue="화면으로부터 전달되는 데이터 없을때 설정할 기본값",
			// required="파라미터 생략여부")
			// String 타입의 리턴할 변수값

			@RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
			@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword,
			Model model) {

		System.out.println("게시글 목록조회 처리");

		System.out.println("검색 조건: " + condition);
		System.out.println("검색 단어: " + keyword);

		List<BoardVO> boardList = boardService.getBoardList();

		// 검색 결과를 세션에 저장하고 목록화면 호출
		model.addAttribute("boardList", boardList);

		return "getBoardList.jsp";
	}
}