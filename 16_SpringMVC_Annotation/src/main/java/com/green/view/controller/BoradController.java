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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.board.BoardService;
import com.green.biz.dto.BoardVO;

/*
 *  �Խ��� ���� ��Ʈ�ѷ�
 */

@Controller
@SessionAttributes("board")
public class BoradController {

	@Autowired
	private BoardService boardService;

	/* �Խñ� ��� */
	@RequestMapping(value = "/insertBoard.do") // client ȭ�鿡�� "/insertBoard.do"��� ��û�� ������ �Ʒ��� �޼ҵ� ���༳��
	// ������ �����̳ʰ� insertBoard() �޼ҵ带 �����Ҷ� Command ��ü�� �����Ͽ� ����ڰ� �Է��� ���� �����Ͽ� �Ѱ��ش�
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException { // BoardVO command ��ü ���

		System.out.println("�Խñ� ���ó��");

		// ���ε� ���� ó��
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("C:/Users/ssych/SPRING-workspace/upload/" + fileName));
		}

		// 2. DB ���� ó��
		boardService.insertBoard(vo);

		return "getBoardList.do";

	}

	/* �Խñ� ���� */

	/*
	 * @SessionAttribute�� �����ϸ� GetBoard ���� "board" �Ӽ����� ����� ���� ����ǰ�
	 * 
	 * @ModelAttribute�� �̿��Ͽ� BoardVO ��ü�� ���� ���� �����. ȭ�鿡�� �о�� �������� BoardVO ��ü�� �ݿ���
	 */

	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("�Խñ� ����ó��");
		System.out.println("�ۼ��� �̸�: " + vo.getWriter());

		boardService.updateBoard(vo);

		// �����۾��� �Խù� ����Ʈ ȭ�� ���
		return "getBoardList.do";
	}

	/* �Խñ� ���� */
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {

		System.out.println("�Խñ� ����ó��");

		boardService.deleteBoard(vo);

		return "getBoardList.do";
	}

	/* �Խñ� �� ��ȸ */
	@RequestMapping(value = "/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {

		System.out.println("�Խñ� ����ȸ ó��");

		// �Խñ� ��ȣ �Է°� ����
		BoardVO board = boardService.getBoard(vo);

		// ����ȭ�� ����
		model.addAttribute("board", board);

		return "getBoard.jsp";
	}

	/* �˻� ���� ��� ���� */
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {

		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("����", "TITLE");
		conditionMap.put("����", "CONTENT");

		return conditionMap;
	}

	/* �Խñ� ��� ��ȸ */

	@RequestMapping(value = "/getBoardList.do")
	public String getBoardList(

			// �Խñ� �˻�(name = value, defaultValue="ȭ�����κ��� ���޵Ǵ� ������ ������ ������ �⺻��",
			// required="�Ķ���� ��������")
			// String Ÿ���� ������ ������

			// @RequestParam(value = "searchCondition", defaultValue = "TITLE", required =
			// false) String condition,
			// @RequestParam(value = "searchKeyword", defaultValue = "", required = false)
			// String keyword,

			BoardVO vo, Model model) {

		System.out.println("�Խñ� �����ȸ ó��");

		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");

		System.out.println("�˻� ����: " + vo.getSearchCondition());
		System.out.println("�˻� �ܾ�: " + vo.getSearchKeyword());

		List<BoardVO> boardList = boardService.getBoardList(vo);

		// �˻� ����� ���ǿ� �����ϰ� ���ȭ�� ȣ��
		model.addAttribute("boardList", boardList);

		return "getBoardList.jsp";
	}
}