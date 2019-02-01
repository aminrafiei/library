package library;


import enums.ELibrary;
import file.Write;
import main.Accessable;
import main.CheckInput;
import main.Console_Admin;
import main.Profile;
import main.Search;

/**
 * @author Amin Rafiei
 */
public class Admin extends Profile implements Accessable {

	
	Library lib;
	private static final String user = "admin"; 
	private static final int pass = 1234;
	
	public Admin() {
		lib = new Library();
	}

	/**
	 * set admin profie default information 
	 */
	public void profileDefault() {
		setName("amin");
		setLast("rafiei");
		setGender('m');
		setAge(18);
		setId(00);
		setPass(pass);
		setNationalId(0);
	}

	/**
	 * admin can get and reciver member fine
	 * @param add in refund method return true to save differnce payment and find
	 */
	public void getingFine(boolean add) {

			long dayFine = Library.checkFine(Library.member);
			long payment = Library.member.getPayment();
			long fine = Library.member.getFine();
			if (dayFine+fine != 0) {
				System.out.println("member must pay:" + (dayFine-payment+fine));
				System.out.println("member payed:"+ payment);
				System.out.print("member payment:");
				int pay = CheckInput.checkNum();
				Library.member.setPayment(pay + payment);
				if(add)
					Library.member.setFine(dayFine-Library.member.getPayment());
				Write.backUpMemberSubject("UPDATE", Library.member);
				CheckInput.findMSG(true);
			}
			else
				System.out.println("nothing to pay :) ");
			
	}
	
	/**
	 * manage fine accsesbilty
	 * @param finding boolean for refund or admin
	 */
	public void fine(boolean finding){
		
		if(finding){
			if(Search.getMember())
				getingFine(false);
			else
				CheckInput.findMSG(false);
		}else
			getingFine(true);
			
	}

	/**
	 * admin can create member
	 * and this method also save member in backup file
	 */
	public static void manageCreateMember() {

		Member member = Member.create();
		Library.listMember.addListMem(member);
		Write.backUpMemberSubject("CREATE", member);
	}

	/**
	 * admin can create book
	 * and this method also save book in backup file
	 */
	public static void mangerAddBook() {

		Book book = Book.add();
		Library.listBook.addListBook(book);
		Write.backUpBookSubject("CREATE", book);
	}
	
	/**
	 * admin can update member
	 * and this method also save member updete in backup file
	 */
	public static void updateMember() {

		if (Search.getMember()) {
			Library.member.update();
			Write.backUpMemberSubject("UPDATE", Library.member);
			CheckInput.findMSG(true);
		} else
			CheckInput.findMSG(false);

	}
	
	/**
	 * admin can update book
	 * and this method also save book update in backup file
	 */
	public static void updateBook() {

		if (Search.getBook()) {
			Library.book.update();
			Write.backUpBookSubject("UPDATE", Library.book);
			CheckInput.findMSG(true);
		} else
			CheckInput.findMSG(false);

	}

	/**
	 * admin can delete member
	 * and this method also save member delete info in backup file
	 */
	public static void deleteMember() {

		if (Search.getMember()) {
			boolean[] find = Library.listMember.deleteMember(Library.member);
			if (find[1]) {
				Write.backUpMemberSubject("DELETE", Library.member);
				System.out.println(ELibrary.DELETED_MSG.toString());
			} else
				System.out.println(ELibrary.DELETE_WARNING.toString());
			if (!find[0])
				CheckInput.findMSG(find[0]);
		}
	}

	/**
	 * admin can delete book
	 * and this method also save book delete info in backup file
	 */
	public static void deleteBook() {

		if (Search.getBook()) {
			boolean[] find = Library.listBook.deleteBook(Library.book);
			if (find[1]) {
				Write.backUpBookSubject("DELETE", Library.book);
				System.out.println(ELibrary.DELETED_MSG.toString());
			} else
				System.out.println(ELibrary.DELETE_WARNING.toString());
			if (!find[0])
				CheckInput.findMSG(find[0]);
		}
	}

	/**
	 * search member
	 * @param input chose for search method 
	 */
	public static void readMember(int input) {
		if (input == 1)
			Search.searchByIdMember();
		else if (input == 2)
			Search.advancedSearch();
	}

	/**
	 * search book
	 * @param input chose for search method 
	 */
	public static void readBook(int input) {
		if (input == 1)
			Search.searchByIdBook();
		else if (input == 2)
			Search.advancedSearchBook();
	}

	/**
	 * refund a book with id member and book
	 * and if member has any find first he have to
	 * pay it and save in backup
	 */
	public void refund() {

		if (Search.getMember()) {
			Library.showALLBorrowedBook(Library.member);
			fine(false);
			boolean success = lib.refundManage();
			Write.backUpMemberSubject("UPDATE", Library.member);
			Write.backUpBookSubject("UPDATE", Library.book);
			if(success)
				CheckInput.findMSG(success);
		} else
			CheckInput.findMSG(false);

	}

	/**
	 * borrow a book with id member and book
	 * and save in backup
	 */
	public void borrow() {

		if (Search.getMember()) {
			boolean success = lib.borrowManage();
			Write.backUpMemberSubject("UPDATE", Library.member);
			Write.backUpBookSubject("UPDATE", Library.book);
			if(success)
				CheckInput.findMSG(success);
		} else
			CheckInput.findMSG(false);
	}

	/**
	 * check admin
	 * 
	 * @param id
	 *            username of admin
	 * @param pass
	 *            password of admin
	 */
	public static boolean access(String id, int pass) {
		boolean find = false;
		if (id.equals(user) && pass == Admin.pass) {
			Console_Admin adm = new Console_Admin(new Admin());
			adm.run();
			find = true;
		}
		return find;
	}

}
