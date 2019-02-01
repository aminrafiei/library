package file;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import library.Book;
import library.Library;
import library.List;
import library.Member;
/**
 * this class write files
 * @author Amin Rafiei
 *
 */
public class Write {

	private static FileWriter fileWriter;
	private static BufferedWriter writer;
	public static final String BACKUP_Member_PATH = "memberTMP.csv";
	public static final String BACKUP_Book_PATH = "bookTMP.csv";
	public static final String LIST_Mem_PATH = "listMem.txt";
	public static final String LIST_Book_PATH = "listBook.txt";

	/**
	 * rewrite member list
	 */
	public static final void reWriteListMem() {

		writeList(Library.listMember, LIST_Mem_PATH);
		activeWrite(BACKUP_Member_PATH, false);
	}
	
	/**
	 * rewrite book list
	 */
	public static final void reWriteListBook(){
		
		writeList(Library.listBook, LIST_Book_PATH);
		activeWrite(BACKUP_Book_PATH, false);
	}

	/**
	 * set member and title subject for backup file
	 * @param sub title subject
	 * @param member the member to write
	 */
	public static final void backUpMemberSubject(String sub, Member member) {

		activeWrite(BACKUP_Member_PATH, true);
		writeMemberBack(sub, member);
	}

	/**
	 * set book and title subject for backup file
	 * @param sub title subject
	 * @param book the book to write
	 */
	public static final void backUpBookSubject(String sub, Book book) {

		activeWrite(BACKUP_Book_PATH, true);
		writeBookBack(sub, book);
	}

	
	/**
	 *  ready file to write
	 * @param path location file
	 * @param active set write after end or renew file
	 */
	public static void activeWrite(String path, boolean active) {

		try {
			fileWriter = new FileWriter(path, active);

		} catch (IOException e) {
			e.printStackTrace();
		}
		writer = new BufferedWriter(fileWriter);

	}

	/**
	 * write list in file
	 * @param list member list or book list
	 * @param path loction file
	 */
	public static void writeList(List list, String path) {

		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(list);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * write information of member in backup file
	 * @param type title subject
	 * @param member member information
	 */
	public static void writeMemberBack(String type, Member member) {

		try {
			writer.write(type + ",");
			writer.write(String.valueOf(member.getId() + ","));
			writer.write(String.valueOf(member.getNationalId() + ","));
			writer.write(String.valueOf(member.getPass() + ","));
			writer.write(member.getName() + ",");
			writer.write(member.getLast() + ",");
			writer.write(String.valueOf(member.getAge() + ","));
			writer.write(member.getGender() + ",");
			for (int i = 0; i < 3; i++)
				writer.write(String.valueOf(member.borrowBook[i] + ","));

			for (int i = 0; i < 3; i++)
				writer.write(member.borrowTime[i] + ",");
			writer.write(String.valueOf(member.getNumBorrow()) + ",");
			writer.write(String.valueOf(member.isExpiredAlert())+ ",");
			writer.write(String.valueOf(member.getFine())+ ",");
			writer.write(String.valueOf(member.getPayment()));
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * write information of book in backup file
	 * @param type type title subject
	 * @param book book information
	 */
	public static void writeBookBack(String type, Book book) {
		try {
			writer.write(type + ",");
			writer.write(String.valueOf(book.getId() + ","));
			writer.write(book.getName() + ",");
			writer.write(book.getAuthor() + ",");
			writer.write(String.valueOf(book.getIsbn() + ","));
			writer.write(book.getTopic() + ",");
			writer.write(String.valueOf(book.isAvailable()));
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
