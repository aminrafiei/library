package library;

import java.io.Serializable;

/**
 * this is Node class that has information of
 * member and book and next Node and implement Serializable
 * to write in object list
 * @author Amin Rafiei
 *
 */
public class Node implements Serializable {

	private Node nextMemmber;
	private Node nextBook;
	private Member member;
	private Book book;
	
	/**
	 * @param member object of Member class
	 */
	public Node(Member member){
		
		this.member = member;
	}

	/**
	 * @param book object of book class
	 */
	public Node(Book book){
		
		this.book = book;
	}
	
	/**
	 * equals member
	 * @param member member to check
	 * @return true for find member else false
	 */
	public boolean equals(Member member){
		if(this.member.getId() == member.getId()){
			return true;
		}
		return false;
	}
	
	/**
	 * equals book
	 * @param book book to check
	 * @return true for find book else false
	 */
	public boolean equals(Book book){
		if(this.book.getId() == book.getId()){
			return true;
		}
		return false;
	}
	
	
	
	public Node getNextMemmber() {
		return nextMemmber;
	}

	public void setNextMemmber(Node nextMemmber) {
		this.nextMemmber = nextMemmber;
	}

	public Node getNextBook() {
		return nextBook;
	}

	public void setNextBook(Node nextBook) {
		this.nextBook = nextBook;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
}
