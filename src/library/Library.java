package library;

import enums.ELibrary;
import main.Accessable;
import main.CheckInput;
import main.Search;
import main.Time;

/**
 * @author Amin Rafiei
 */
public class Library {

	public static final int MAX_BORROW = 3;
	private static final int MAX_TIME_BORROW = 10;
	private static final int MAX_FINE = 1000;
	public static Book book;
	public static Member member;
	public static int memberCounter = 0;
	public static int bookCounter = 0;
	private static Time time;
	public static ListMember listMember = new ListMember();
	public static ListBook listBook= new ListBook();

	/**
	 * The only constructor of the class
	 */
	public Library() {
		time = new Time();
	}

	/**
	 * with this method member can borrow a book <br>
	 * if he hasnt any expired book and he has less than 3 books
	 */
	public boolean borrowManage() {

		boolean find = false;
		boolean available = false;
		boolean success = false;
		System.out.println(ELibrary.ID_BOOK.toString());
		int input = CheckInput.checkNum();
		if (Search.findBook(input)) {
			if (book.isAvailable() == true) {
				find = true;
				available = true;
				if (member.getNumBorrow() < MAX_BORROW) {
					if (member.isExpiredAlert() == false) {
						success = true;
						member.borrowBook[member.getNumBorrow()] = book.getId();
						book.setAvailable(false);
						String startTime = Time.currectTime();
						member.borrowTime[member.getNumBorrow()] = startTime;
						member.setNumBorrow(member.getNumBorrow() + 1);
					} else
						System.out.println(ELibrary.CANT_BORROW.toString());
				} else
					System.out.println(ELibrary.MAX_BORROW_WARNING.toString());
			} else
				available = false;
			find = true;
		}
		if (!find)
			System.out.println(ELibrary.UNKNOWN_BOOK);

		if (!available && find)
			System.out.println(ELibrary.AVALIBLE_WARNING.toString());
		
		return success;
	}

	/**
	 * with this method member can refund a book
	 */
	public boolean refundManage() {

		boolean find = false;
		System.out.println(ELibrary.ID_BOOK.toString());
		int input = CheckInput.checkNum();
		for (int j = 0; j < MAX_BORROW; j++) {
			if (input == member.borrowBook[j]) {
				for (int i = j; i < member.getNumBorrow(); i++) {
					member.borrowBook[i] = member.borrowBook[i + 1];
					member.borrowTime[i] = member.borrowTime[i + 1];
				}
				member.setNumBorrow(member.getNumBorrow() - 1);
				member.setExpiredAlert(false);
				member.setPayment(0);
				if (Search.findBook(input))
					book.setAvailable(true);
				find = true;
			}

		}

		if (!find)
			System.out.println(ELibrary.UNKNOWN_BOOK.toString());
		
		return find;
	}

	/**
	 * check accsse for admin or member
	 * @param acc interface between admin or member
	 * @return if admin true else false
	 */
	public static boolean checkAcss(Accessable acc) {

		if (acc instanceof Admin)
			return true;
		else if (acc instanceof Member)
			return false;

		return true;

	}

	/**
	 * This method show and find all of borrowed books with id and name member and book
	 * @param acc interface between admin or member
	 */
	public static void showALLBorrowedBook(Accessable acc) {

		boolean admin = checkAcss(acc);
		boolean find = false;
		Node j = listMember.firstMem;
		while (j != null) {
			
			if (admin)
				member = j.getMember();
			else
				member = (Member) acc;
			
			Node p = listBook.firstBook;
			while (p != null) {
				book = p.getBook();
				if (!book.isAvailable()) {
					find = true;
					for (int h = 0; h < MAX_BORROW; h++) {
						if (member.borrowBook[h] == book.getId()) {
							time.differeceTime(member.borrowTime[h]);
							fullInfo(member, book, h);
							System.out.println(ELibrary.SHARP.toString());
						}
					}
				}
				p = p.getNextBook();
			}
			if(member.getNumBorrow()>0){
				System.out.println("total fine:" + (checkFine(member)-member.getPayment() + member.getFine()));
				System.out.println(ELibrary.LINE.toString());
			}
			if (!admin)
				break;
			j = j.getNextMemmber();
		}
		if (!find)
			System.out.println(ELibrary.EMPTY_MSG.toString());
	}

	/**
	 * show in console borrowed information
	 * @param member member info
	 * @param book book info
	 * @param i time of borrow
	 */
	public static void fullInfo(Member member, Book book, int i) {

		System.out.println("Book Name:" + book.getName() + " id:" + book.getId() + " By:" + member.getName() + " id:"
				+ member.getNationalId());
		System.out.println("Date of borrow:" + member.borrowTime[i]);
		time.printTimeConvert();

	}

	/**
	 * check for days fine
	 * @param acc interface between admin or member
	 * @return total fine of dats of borrow
	 */
	public static long checkFine(Accessable acc) {

		long total = 0;
		time = new Time();
		boolean admin = checkAcss(acc);
		Node j = listMember.firstMem;
		while (j != null) {
			total = 0;
			if (admin)
				member = j.getMember();
			else
				member = (Member) acc;

			for (int h = 0; h < getMaxBorrow(); h++) {
				if (member.borrowBook[h] != 0) {
					time.differeceTime(member.borrowTime[h]);
					if (time.diffSeconds() <= 0) {
						long min = time.diffDays();
						total += MAX_FINE * (-min);
					}
				}
			}
			if (!admin)
				break;
			j = j.getNextMemmber();
		}
		
		return total;
	}

	/**
	 * this method checked if any book is expired show a expired alart massage
	 * acc interface between admin or member
	 */
	public static void showExpiredAlart(Accessable acc) {

		time = new Time();
		boolean admin = checkAcss(acc);
		boolean find = false;
		Node j = listMember.firstMem;
		while (j != null) {
			if (admin)
				member = j.getMember();
			else
				member = (Member) acc;
			for (int h = 0; h < getMaxBorrow(); h++) {
				if (member.borrowBook[h] != 0) {

					time.differeceTime(member.borrowTime[h]);
					if (time.diffSeconds() < 0) {
						member.setExpiredAlert(true);
						find = true;
					}
				}
				if (!admin)
					break;
			}
			j = j.getNextMemmber();
		}
		if (find)
			System.out.println(ELibrary.EXPIRED_ALART.toString());

	}

	/**
	 * @return The Maximum of Time Borrow(default 1 min)
	 */
	public static int getMaxTimeBorrow() {
		return MAX_TIME_BORROW;
	}

	/**
	 * @return The Maximum of Borrowed books(default 3 books)
	 */
	public static int getMaxBorrow() {
		return MAX_BORROW;
	}

	/**
	 * @return The Maximum of fine for each day(default 1000 Toman)
	 */
	public static int getMaxFine() {
		return MAX_FINE;
	}

}
