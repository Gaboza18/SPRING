package com.green.biz.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate = null;

	private static final String BOOK_INSERT = "INSERT INTO books(item_id,category,name,author,price,publiser,image_url,notes,is_recommended,rating)"
			+ "VALUES(seq_item_id.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	private static final String BOOK_GET = "SELECT * FROM books WHERE item_id=?";

	private static final String BOOK_LIST = "SELECT * FROM books";

	public void insertBook(BookVO vo) {

		jdbcTemplate.update(BOOK_INSERT, vo.getName(), vo.getAuthor(), vo.getCategory(), vo.getPrice(),
				vo.getPublisher(), vo.getImage_url(), vo.getNotes(), vo.getIs_recommended());
	}

	public BookVO getBook(BookVO vo) {

		Object[] args = { vo.getItem_id() };

		return jdbcTemplate.queryForObject(BOOK_GET, args, new BookRowMapper());
	}

	public List<BookVO> getBookList() {

		return jdbcTemplate.query(BOOK_LIST, new BookRowMapper());

	}

	class BookRowMapper implements RowMapper<BookVO> {

		@Override
		public BookVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			BookVO book = new BookVO();

			book.setItem_id(rs.getInt("item_id")); // ��Ϲ�ȣ
			book.setName(rs.getString("name")); // ��������
			book.setAuthor(rs.getString("author")); // ����
			book.setCategory("category"); // ī�װ�
			book.setPrice(rs.getInt("price")); // ����
			book.setPublisher("publisher"); // ���ǻ�
			book.setImage_url(rs.getString("image_url")); // �̹��� URL
			book.setNotes(rs.getString("notes")); // �������
			book.setRating(rs.getInt("rating")); // ��õ

			return book;
		}
	}

}
