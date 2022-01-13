package com.green.view.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.green.biz.board.BoardService;
import com.green.biz.dao.BoardDAO;
import com.green.biz.dto.BoardVO;

/*
 * 게시판 관련 콘트롤러
 */
@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	// 글 등록
	// 사용자 화면에서 "/insertBoard.do"라는 요청이 들어오면
	// 아래의 메소드를 수행한다는 설정
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException  {  // BoardVO command객체 등록
		System.out.println("게시글 등록 처리");
		
		// 업로드 파일 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/spring-workspace/upload/"+fileName));
		}
		
		// 2. DB 연동 처리
		boardService.insertBoard(vo);
		
		// 3. 화면 네비게이션
		return "getBoardList.do";
	}
	
	// 게시글 수정
	/*
	 * @SessionAttributes로 설정하면 GetBoard에서 "board" 속성으로 값이 저장되고
	 * @ModelAttribute를 이용하여 BoardVO 객체에 기존 값이 저장됨. 
	 * 화면에서 읽어온 수정값이 BoardVO 객체에 반영됨.
	 */
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("게시글 수정 처리");
		System.out.println("작성자 이름: " + vo.getWriter());
		
		boardService.updateBoard(vo);
		
		return "getBoardList.do";
	}
	
	// 게시글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("게시글 삭제 처리");
		
		boardService.deleteBoard(vo);
		
		return "getBoardList.do";
	}
	
	// 게시글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {

		System.out.println("게시글 상세조회 처리");
		
		BoardVO board = boardService.getBoard(vo);
		
		// 응답화면 구성
		model.addAttribute("board", board);
		
		return "getBoard.jsp";
	}
	
	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();
		
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
	
	
	// 게시글 목록 조회
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(
//			@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition,
//			@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
			BoardVO vo,
			Model model) {
		System.out.println("게시글 목록조회 처리");

		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		
		System.out.println("검색 조건: " + vo.getSearchCondition());
		System.out.println("검색 단어: " + vo.getSearchKeyword());
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		// 검색결과를 세션에 저장하고 목록화면을 호출한다.
		model.addAttribute("boardList", boardList);
		
		return "getBoardList.jsp";
	}
}
