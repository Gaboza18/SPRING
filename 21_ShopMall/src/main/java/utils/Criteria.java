package utils;

/*
 * 현재 페이지와 관련한 정보를 저장하는 클래스
 *  - 현재 페이지 번호
 *  - 페이지 당 출력 항목
 *  - 각 페이지의 시작 게시글 번호
 */

public class Criteria {

	private int pageNum; // 현재 페이지 번호
	private int rowsPerPage; // 페이지당 출력행의 갯수

	// 생성자
	public Criteria() {
		// 기본값: 페이지 번호: 1, 페이지당 항목수: 10
		this(1, 10);
	}

	public Criteria(int pageNum, int rowsPerPage) {
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {

		if (pageNum <= 0) {
			this.pageNum = 1; // 페이지 값이 음수일 경우
		} else {
			this.pageNum = pageNum;
		}
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	// 페이지당 항목의 수를 제한
	public void setRowsPerPage(int rowsPerPage) {

		if (rowsPerPage <= 0 || rowsPerPage > 20) {
			this.rowsPerPage = 20;
		} else {
			this.rowsPerPage = rowsPerPage;
		}
	}
	
	/*
	 *  각 페이지에서 시작하는 항목번호를 반환
	 *  ex) 1페이지 - 1,2,3,4...10
	 *   	2페이지 - 11,12,13,14....20
	 */
	public int getPageStart() {
		return (pageNum - 1) * rowsPerPage + 1;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}
}
