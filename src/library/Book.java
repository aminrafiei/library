package library;


import java.io.Serializable;

import enums.ELibrary;
import enums.EMenu;
import file.Write;
import main.CheckInput;

/**
 * this class set all information of books and update it with getter and setter
 * each property . it implements Serializable for write in listObject
 * @author Amin Rafiei
 *
 */
public class Book implements Serializable  {

	private static int localIdBook = 0;
	private String name;
	private String author;
	private int isbn;
	private String topic;
	private int id;
	private boolean available;

	/**
	 * @param id
	 *            id book
	 * @param name
	 *            name book
	 * @param author
	 *            firstName/lastName of author
	 * @param isbn
	 *            ISBN number
	 * @param topic
	 *            Topic of book
	 * @param available
	 *            book is borrowed or not
	 */
	public Book(int id, String name, String author, int isbn, String topic, boolean available) {

		this.id = id;
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.topic = topic;
		this.available = available;
	}

	/**
	 */
	public Book() {
	}

	/**
	 * with this method admin can add new book
	 * 
	 * @return the object new book and set it in node
	 */
	public static Book add() {

		localIdBook = Library.bookCounter;
		boolean available = true;
		System.out.print("New book:\n1:name:");
		String name = CheckInput.checkString();
		System.out.print("2:author:");
		String author = CheckInput.checkString();
		System.out.print("3:ISBN:");
		int isbn = CheckInput.checkNum();
		String topic = topicChoose();
		localIdBook++;
		System.out.println("new book with id number:" + localIdBook + " has been created.");
		return new Book(localIdBook, name, author, isbn, topic, available);
	}

	/**
	 * update information of books
	 */
	public void update() {

		System.out.println("which part do you want to be change?\n1:name(" + getName() + ")\n2:author(" + getAuthor()
				+ ")\n3:ISBN(" + getIsbn() + ")\n4:Topic(" + getTopic() + ")\n5:End");

		int[] save = CheckInput.saveInput();
		for (int i = 1; i <= 5; i++)
			if (i == save[i]) {
				switch (i) {
				case 1:
					System.out.println("Name(" + getName() + "):");
					setName(CheckInput.checkString());
					break;
				case 2:
					System.out.println("Author(" + getAuthor() + "):");
					setAuthor(CheckInput.checkString());
					break;
				case 3:
					System.out.println("ISBN(" + getIsbn() + "):");
					setIsbn(CheckInput.checkNum());
					break;
				case 4:
					System.out.println("Topic(" + getTopic() + "):");
					setTopic(topicChoose());
					break;
				case 5:
					break;
				default:
					System.out.println(ELibrary.UNKNOWN_MSG.toString());
				}

			}

	}
	
	/**
	 * choose topic
	 * @return Sting of topic
	 */
	public static String topicChoose(){
		
		String topic=null;
		int topicNum = 0;
		while (topicNum < 1 || topicNum > 4) {
			System.out.print(EMenu.TOPIC_MENU.toString());
			topicNum = CheckInput.checkNum();
			topic = topicGenre(topicNum);
		}
		return topic;
	}

	/**
	 * @param i
	 * @return
	 */
	public static String topicGenre(int i) {

		String lTopic;
		int input = i;
		switch (input) {
		case 1:
			lTopic = "drama";
			break;
		case 2:
			lTopic = "horror";
			break;
		case 3:
			lTopic = "history";
			break;
		case 4:
			lTopic = "scientific";
			break;
		default:
			lTopic = "Unknown";
			break;
		}
		return lTopic;
	}

	/**
	 * this method show information of book
	 */
	public void toStringBook() {
		System.out.println("1:name:" + getName());
		System.out.println("2:author:" + getAuthor());
		System.out.println("3:ISBN:" + getIsbn());
		System.out.println("4:Topic:" + getTopic());
		System.out.println("5:id number:" + getId());
		System.out.println("6:available status:" + isAvailable());
		System.out.println(ELibrary.LINE.toString());
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

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
