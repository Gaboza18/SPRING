package utils;

/*
 * ���� �������� ������ ������ �����ϴ� Ŭ����
 *  - ���� ������ ��ȣ
 *  - ������ �� ��� �׸�
 *  - �� �������� ���� �Խñ� ��ȣ
 */

public class Criteria {

	private int pageNum; // ���� ������ ��ȣ
	private int rowsPerPage; // �������� ������� ����

	// ������
	public Criteria() {
		// �⺻��: ������ ��ȣ: 1, �������� �׸��: 10
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
			this.pageNum = 1; // ������ ���� ������ ���
		} else {
			this.pageNum = pageNum;
		}
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	// �������� �׸��� ���� ����
	public void setRowsPerPage(int rowsPerPage) {

		if (rowsPerPage <= 0 || rowsPerPage > 20) {
			this.rowsPerPage = 20;
		} else {
			this.rowsPerPage = rowsPerPage;
		}
	}
	
	/*
	 *  �� ���������� �����ϴ� �׸��ȣ�� ��ȯ
	 *  ex) 1������ - 1,2,3,4...10
	 *   	2������ - 11,12,13,14....20
	 */
	public int getPageStart() {
		return (pageNum - 1) * rowsPerPage + 1;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}
}
