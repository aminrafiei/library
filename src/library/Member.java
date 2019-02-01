package library;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import enums.ELibrary;
import main.Accessable;
import main.CheckInput;
import main.Console_Member;
import main.Profile;

/**
 * This class represents a member. it extends of profile abstractClass
 * and Serializable to write in objectList
 * @author Amin Rafiei
 *
 */
public class Member extends Profile implements Accessable, Serializable {

	private static int localId = 0;
	private int numBorrow = 0;
	private boolean expiredAlert;
	public int[] borrowBook;
	public String[] borrowTime;
	private long fine;
	private long payment;

	/**
	 * one of the Constructor that set value of property
	 * 
	 * @param id
	 *            member id
	 * @param name
	 *            member first name
	 * @param last
	 *            member last name
	 * @param age
	 *            member age
	 * @param gender
	 *            member gender
	 * @param numBorrow
	 *            The number of borrowd books
	 * @param borrowBook
	 *            The id of books that members borrowed
	 * @param borrowTime
	 *            The time of borrowd books
	 * @param expiredAlert
	 *            The alert of expired book
	 */
	public Member(int id, int nationalId, int pass, String name, String last, int age, char gender, int numBorrow,
			int[] borrowBook, String[] borrowTime, boolean expiredAlert, long fine,long payment) {

		setName(name);
		setLast(last);
		setAge(age);
		setGender(gender);
		setId(id);
		setNationalId(nationalId);
		setPass(pass);
		setExpiredAlert(expiredAlert);
		setFine(fine);
		setPayment(payment);
		this.borrowBook = borrowBook;
		this.numBorrow = numBorrow;
		this.borrowTime = borrowTime;
	}

	/**
	 * another Constructor
	 */
	public Member() {
	}

	public static boolean access(String id, int pass) {

		boolean find = false;
		Node p = Library.listMember.firstMem;
		while (p != null) {
			if (id.equals(String.valueOf(p.getMember().getNationalId())) && p.getMember().getPass() == pass) {
				Console_Member mem = new Console_Member(p.getMember());
				mem.run();
				find = true;
				break;
			}
			p = p.getNextMemmber();
		}
		return find;
	}

	/**
	 * with this method admin can creat new member
	 * @return object of new member with all information
	 */
	public static Member create() {

		localId = Library.memberCounter;
		int numBorrow = 0;
		int[] borrowBook = new int[Library.MAX_BORROW + 1];
		String[] borrowTime = new String[Library.MAX_BORROW + 1];
		boolean expiredAlert = false;
		System.out.print("New member:\n1:National Id:");
		int nationalId = CheckInput.checkNum();
		System.out.print("2:First name:");
		String first = CheckInput.checkString();
		System.out.print("3:last name:");
		String last = CheckInput.checkString();
		System.out.print("4:age(number):");
		int age = CheckInput.checkNum();
		System.out.print("5:gender(m/f):");
		char gender = CheckInput.checkChar();
		localId++;
		long fine = 0;
		long payment = 0;
		int pass = ThreadLocalRandom.current().nextInt(1000, 9998 + 1);
		//int pass = CheckInput.checkNum();
		
		System.out.println("new member with membership number:" + nationalId + " & password : " + pass
				+ " has been created.");

		return new Member(localId, nationalId, pass, first, last, age, gender, numBorrow, borrowBook, borrowTime,
				expiredAlert, fine,payment);
	}

	/**
	 * with this method Admin can Update information of member
	 */
	public void update() {

		System.out.println("which part do you want to be change?\n1:First name(" + getName() + ")\n2:Last name("
				+ getLast() + ")\n3:Age(" + getAge() + ")\n4:gender(" + getGender() + ")\n5:End");

		int[] save = CheckInput.saveInput();
		for (int i = 1; i <= 5; i++)
			if (i == save[i]) {
				switch (i) {
				case 1:
					System.out.println("First name(" + getName() + "):");
					setName(CheckInput.checkString());
					break;
				case 2:
					System.out.println("Last name(" + getLast() + "):");
					setLast(CheckInput.checkString());
					break;
				case 3:
					System.out.println("Age(" + getAge() + "):");
					setAge(CheckInput.checkNum());
					break;
				case 4:
					System.out.println("jensiyat(" + getGender() + "):");
					setGender(CheckInput.checkChar());
					break;
				case 5:
					break;
				default:
					System.out.println(ELibrary.UNKNOWN_MSG.toString());
				}

			}

	}

	public int getNumBorrow() {
		return numBorrow;
	}

	public void setNumBorrow(int numBorrow) {
		this.numBorrow = numBorrow;
	}

	public boolean isExpiredAlert() {
		return expiredAlert;
	}

	public void setExpiredAlert(boolean expiredAlert) {
		this.expiredAlert = expiredAlert;
	}

	public long getFine() {
		return fine;
	}

	public void setFine(long fine) {
		this.fine = fine;
	}


	public long getPayment() {
		return payment;
	}

	public void setPayment(long payment) {
		this.payment = payment;
	}

}
