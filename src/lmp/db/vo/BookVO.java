package lmp.db.vo;

import java.util.Date;

public class BookVO {
	
	/**
	 * 도서 정보
	 */
	
	private String	   id;
	private String	   title;
	private String	   author;
	private String	   publisher;
	private String	   isbn;
	private Integer	   bias;
	private Integer	   duplicates;
	private Date	   regDate;
	private Integer	   price;
	private LocationVO location;	
	private String	   note;
	
	/**
	 * 도서 등록 / 검색 생성자.
	 * 
	 * @param book_id
	 * @param book_title
	 * @param book_author
	 * @param book_publisher
	 * @param book_isbn
	 * @param book_bias
	 * @param book_duplicates
	 * @param book_registrationdate
	 * @param book_price
	 * @param location
	 * @param book_note
	 */
	public BookVO(
				  String  book_id,
				  String  book_title,
				  String  book_author,
				  String  book_publisher,
				  String  book_isbn,
				  Integer book_bias,
				  Integer book_duplicates,
				  Date	  book_registrationdate,
				  Integer book_price,
				  LocationVO location,
				  String  book_note
				  ) {
		
		this.id			=	book_id;
		this.title		=	book_title;
		this.author		=	book_author;
		this.publisher	=	book_publisher;
		this.isbn		=	book_isbn;
		this.bias		=	book_bias;
		this.duplicates	=	book_duplicates;
		this.regDate	=	book_registrationdate;
		this.price		=	book_price;
		this.location	=	new LocationVO(location);
		this.note		=	book_note;
		
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public Integer getBias() {
		return bias;
	}

	public Integer getDuplicates() {
		return duplicates;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Integer getPrice() {
		return price;
	}

	public LocationVO getLocation() {
		return location;
	}

	public String getNote() {
		return note;
	}

}
