package utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 *  ȭ�鿡 ǥ���� ������ ��ư ���� ���� Ŭ����
 */

public class PageMaker {

	private Criteria criteria; // ���� ��������ȣ, ������ �� �׸� ��
	private int totalCount; // ��ü �Խñ��� ��
	private int startPage; // ���� ������ ��ȣ
	private int endPage; // �� ������ ��ȣ
	private boolean prev; // ���� ������ ��ư ����
	private boolean next; // ���� ������ ��ư ����
	private int cntPageNum = 10; // ȭ�� �ϴܿ� ǥ���� ������ ��ư�� ��
	private int realEndPage; // ��ü �׸���� ���� ���� �������� ��ȣ

	// ��ü �Խñ��� �� ���� �� ������� �ʱ�ȭ ����
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		// �� ������� �ʱ�ȭ ȣ��
		fieldInit();
	}

	// ��� ���� �ʱ�ȭ
	public void fieldInit() {

		// (1) ǥ���� �� ��������ȣ ���(Math.ceil = �ø�ó��)
		endPage = (int) (Math.ceil(criteria.getPageNum() / (double) cntPageNum) * cntPageNum);

		// (2) ���� ��������ȣ ���
		startPage = endPage - cntPageNum + 1;

		// (3) ���� �� ������ ��ȣ ���
		// ���� �� ������ ��ȣ = Math.ceil(�Խñ��� �� �׸� �� / �������� �׸� ��)
		realEndPage = (int) (Math.ceil(totalCount / (double) criteria.getRowsPerPage()));

		// ���� �� �������� ��ȣ�� endPage ����
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		// ������ư, ������ư ǥ�� ���� ����
		prev = startPage == 1 ? false : true; // ������������ 1������ �� ��� ������ư �ʿ� ����
		next = endPage * criteria.getRowsPerPage() < totalCount ? true : false;
	}

	/*
	 * ȭ�鿡�� ������ ��ȣ�� ����ϸ�, ������ ��ȣ�� �������� �׸� ���� �̿��Ͽ� URL�� QueryString�� ����� �ִ� �޼ҵ� ex)
	 * ?pageNum=3&rowPerPage=10
	 */
	public String makeQuery(int page) {

		UriComponents uriComp = UriComponentsBuilder.newInstance().queryParam("pageNum", page)
				.queryParam("rowsPerPage", criteria.getRowsPerPage()).build();

		return uriComp.toString();
	}

	// �������, Getter / Setter
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCntPageNum() {
		return cntPageNum;
	}

	public void setCntPageNum(int cntPageNum) {
		this.cntPageNum = cntPageNum;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "PageMaker [criteria=" + criteria + ", totalCount=" + totalCount + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", cntPageNum=" + cntPageNum
				+ ", realEndPage=" + realEndPage + "]";
	}

}
