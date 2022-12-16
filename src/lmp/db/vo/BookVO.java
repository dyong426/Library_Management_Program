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
	private String	   regDate;
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
				  String  book_registrationdate,
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

	public String getRegDate() {
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
	
	// 테이블에 데이터를 쉽게 넣기 위해 getList 메서드 생성
	public Object[] getList() {
		Object[] list = new Object[11];
		
		list[0] = getId();
		list[1] = getTitle();
		list[2] = getAuthor();
		list[3] = getPublisher();
		list[4] = getIsbn();
		list[5] = getBias();
		list[6] = getDuplicates();
		list[7] = getRegDate();
		list[8] = getPrice();
		list[9] = getLocation();
		list[10] = getNote();
		
		return list;
	}

	@Override
	public String toString() {
		
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", this.id,this.title,this.author,this.publisher,this.isbn,this.bias,this.duplicates,this.price,this.location.getLocID(),this.regDate,this.note);
	}
}
