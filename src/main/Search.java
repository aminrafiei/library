package main;

import java.util.Scanner;

import enums.ELibrary;
import enums.EMenu;
import library.Book;
import library.Library;
import library.Member;
import library.Node;

/**
 * Search in books and members
 * 
 * @author Amin rafiei
 *
 */
public class Search {

	/**
	 * this method find a member in nods by id
	 * 
	 * @param id
	 *            the membership id
	 * @return the object of member and set it to Libaray class
	 */
	public static boolean findMember(int id) {

		Node node = Library.listMember.firstMem;
		while (node != null) {
			if (node.getMember().getNationalId() == id) {
				Library.member = node.getMember();
				return true;
			}
			node = node.getNextMemmber();
		}
		return false;
	}

	/**
	 * this method find a book in nods by id
	 * 
	 * @param id
	 *            the id book
	 * @return the object of book and set it to Libaray class
	 */
	public static boolean findBook(int id) {

		Node node = Library.listBook.firstBook;
		while (node != null) {
			if (node.getBook().getId() == id) {
				Library.book = node.getBook();
				return true;
			}
			node = node.getNextBook();
		}
		return false;
	}

	public static boolean getMember() {

		boolean find = false;
		System.out.println(ELibrary.ID_MEMBER.toString());
		int id = CheckInput.checkNum();
		if (Search.findMember(id)) {
			find = true;
		}
		return find;
	}

	public static boolean getBook() {

		boolean find = false;
		System.out.println(ELibrary.ID_BOOK.toString());
		int id = CheckInput.checkNum();
		if (Search.findBook(id)) {
			find = true;
		}
		return find;
	}

	// search book by id
	public static void searchByIdBook() {

		if (getBook())
			Library.book.toStringBook();
		else
			CheckInput.findMSG(false);
	}

	// search member by id
	public static void searchByIdMember() {

		if (getMember())
			System.out.println(Library.member.toString());
		else
			CheckInput.findMSG(false);
	}

	// advanced search for member
	public static void advancedSearch() {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int max = Library.memberCounter + 1;
		int number;
		int i;
		int num = 0;
		Node p = Library.listMember.firstMem;
		Member member;

		int[][] sav2 = new int[5][max];
		int[] loop = new int[max];
		for (int o = 0; o < 5; o++) {
			for (int a = 0; a < max; a++) {
				sav2[o][a] = -1;
				loop[a] = -1;
			}
		}
		System.out.println(EMenu.ADVANCED_SEARCH_MEM.toString());

		int[] save = CheckInput.saveInput();
		for (i = 1; i <= 5; i++) {
			if (i == save[i]) {
				num++;
				switch (i) {
				case 1:
					System.out.println("First name:");
					String firstname = scan.next();
					number = 0;
					while (p != null) {
						member = p.getMember();
						boolean find = (member.getName()).contains(firstname);
						if (find == true) {
							sav2[0][number] = number;
						}
						number++;
						p = p.getNextMemmber();
					}
					break;
				case 2:
					System.out.println("Last name:");
					String lastname = scan.next();
					p = Library.listMember.firstMem;
					number = 0;
					while (p != null) {
						member = p.getMember();
						boolean find = (member.getLast()).contains(lastname);
						if (find == true) {
							sav2[1][number] = number;
						}
						number++;
						p = p.getNextMemmber();
					}
					break;
				case 3:
					System.out.println("Age:");
					int age = CheckInput.checkNum();
					p = Library.listMember.firstMem;
					number = 0;
					while (p != null) {
						member = p.getMember();
						if (age == member.getAge()) {
							sav2[2][number] = number;
						}
						number++;
						p = p.getNextMemmber();
					}

					break;
				case 4:
					System.out.println("jensiyat(m/f):");
					char jens = CheckInput.checkChar();
					p = Library.listMember.firstMem;
					number = 0;
					while (p != null) {
						member = p.getMember();
						boolean find = (member.getGender() == jens);
						if (find == true) {
							sav2[3][number] = number;
						}
						number++;
						p = p.getNextMemmber();
					}
					break;
				case 5:
					break;
				default:
					System.out.println(ELibrary.UNKNOWN_MSG.toString());

				}
			}

		}
		Node k = Library.listMember.firstMem;
		boolean findnam = false;
		while (k != null) {
			Library.member = k.getMember();
			for (int j = 0; j < 5; j++)
				for (int h = j + 1; h < 5; h++)
					for (int c = 0; c < max; c++) {

						if (sav2[j][c] == sav2[h][c] && sav2[j][c] != -1 && sav2[h][c] != 9 && num != 2) {
							if (Library.member.getId() - 1 == sav2[j][c]) {
								System.out.println(Library.member.toString());
								findnam = true;
							}
						} else if (num == 2 && sav2[j][c] != -1 && loop[c] != Library.member.getId()) {
							if (Library.member.getId() - 1 == sav2[j][c]) {
								System.out.println(Library.member.toString());
								findnam = true;
								loop[c] = Library.member.getId();
							}
						}
					}

			k = k.getNextMemmber();
		}

		if (!findnam)
			System.out.println(ELibrary.EMPTY_MSG.toString());

	}

	public static void advancedSearchBook() {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int max = Library.bookCounter + 1;
		int number;
		int i;
		int num = 0;
		Node p = Library.listBook.firstBook;
		Book book = null;

		int[][] sav2 = new int[5][max];
		int[] loop = new int[max];
		for (int o = 0; o < 5; o++) {
			for (int a = 0; a < max; a++) {
				sav2[o][a] = -1;
				loop[a] = -1;
			}
		}
		System.out.println(EMenu.ADVANCED_SEARCH_BOOK.toString());

		int[] save = CheckInput.saveInput();
		for (i = 1; i <= 5; i++) {
			if (i == save[i]) {
				num++;
				switch (i) {
				case 1:
					System.out.println("Name:");
					String name = scan.next();
					number = 0;
					while (p != null) {
						book = p.getBook();
						boolean find = (book.getName()).contains(name);
						if (find == true) {
							sav2[0][number] = number;
						}
						number++;
						p = p.getNextBook();
					}
					break;
				case 2:
					System.out.println("Author name:");
					String author = scan.next();
					p = Library.listBook.firstBook;
					number = 0;
					while (p != null) {
						book = p.getBook();
						boolean find = (book.getAuthor()).contains(author);
						if (find == true) {
							sav2[1][number] = number;
						}
						number++;
						p = p.getNextBook();
					}
					break;
				case 3:
					System.out.println("ISBN:");
					int isbn = CheckInput.checkNum();
					p = Library.listBook.firstBook;
					number = 0;
					while (p != null) {
						book = p.getBook();
						if (isbn == book.getIsbn()) {
							sav2[2][number] = number;
						}
						number++;
						p = p.getNextBook();
					}

					break;
				case 4:
					System.out.println("Topic:");
					String topic = Book.topicChoose();
					p = Library.listBook.firstBook;
					number = 0;
					while (p != null) {
						book = p.getBook();
						boolean find = (book.getTopic()).equalsIgnoreCase(topic);
						if (find == true) {
							sav2[3][number] = number;
							System.out.println(number);
						}
						number++;
						p = p.getNextBook();
					}
					break;
				case 5:
					break;
				default:
					System.out.println(ELibrary.UNKNOWN_MSG.toString());

				}
			}

		}
		Node k = Library.listBook.firstBook;
		boolean findnam = false;
		while (k != null) {
			Library.book = k.getBook();
			for (int j = 0; j < 5; j++)
				for (int h = j + 1; h < 5; h++)
					for (int c = 0; c < max; c++) {

						if (sav2[j][c] == sav2[h][c] && sav2[j][c] != -1 && sav2[h][c] != 9 && num != 2) {
							if (Library.book.getId() - 1 == sav2[j][c]) {
								Library.book.toStringBook();
								findnam = true;
							}
						} else if (num == 2 && sav2[j][c] != -1 && loop[c] != Library.book.getId()) {
							if (Library.book.getId() - 1 == sav2[j][c]) {
								Library.book.toStringBook();
								findnam = true;
								loop[c] = Library.book.getId();
							}
						}
					}

			k = k.getNextBook();
		}

		if (!findnam)
			System.out.println(ELibrary.EMPTY_MSG.toString());

	}

}
