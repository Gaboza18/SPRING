package com.green.biz.view;

public class BookVO {

	private int item_id;
	private String category;
	private String name;
	private String author;
	private int price;
	private String publisher;
	private String image_url;
	private String notes;
	private String is_recommended;
	private int rating;

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getIs_recommended() {
		return is_recommended;
	}

	public void setIs_recommended(String is_recommended) {
		this.is_recommended = is_recommended;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "BookVO [category=" + category + ", name=" + name + ", author=" + author + ", price=" + price
				+ ", publisher=" + publisher + ", image_url=" + image_url + ", notes=" + notes + ", is_recommended="
				+ is_recommended + ", rating=" + rating + "]";
	}

}
