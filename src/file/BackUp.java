package file;

import java.io.IOException;

import library.Book;
import library.Library;
import library.Member;
import library.Node;

/**
 * this class make backup files
 * @author Amin Rafiei
 *
 */
public class BackUp {

	private static boolean find = false;

	/**
	 * read from member backup
	 * and send data to CheckInputMem
	 * @return boolean write or not
	 * @throws IOException
	 */
	public static boolean checkMem() throws IOException {

		Load.readBackUp(Write.BACKUP_Member_PATH);
		String line;
		while ((line = Load.read.readLine()) != null) {
			String[] data = line.split(",");
			checkInputMem(data[0],line);
		}
		if (find) {
			Write.reWriteListMem();
		}
		
		return find;

	}

	/**
	 * read from list for finding title of action
	 * @param action title of action
	 * @param line the line of member information
	 * @throws IOException
	 */
	public static void checkInputMem(String action,String line) throws IOException {

		Member member = Load.loadMem(line);
		switch (action) {
		case "CREATE":
			Library.listMember.addListMem(member);
			find = true;
			break;
		case "UPDATE":
			Node node = Library.listMember.firstMem;
			while (node != null) {
				if (node.getMember().getId() == member.getId()) {
					node.setMember(member);
				}
				node = node.getNextMemmber();
			}
			find = true;
			break;
		case "DELETE":
			Library.listMember.deleteMember(member);
			find = true;
			break;
		default:
			break;
		}
		
	}
	
	
	/**
	 * read from book backup
	 * and send data to CheckInputBook
	 * @return boolean write or not
	 * @throws IOException
	 */
	public static boolean checkBook() throws IOException {

		Load.readBackUp(Write.BACKUP_Book_PATH);
		String line;
		while ((line = Load.read.readLine()) != null) {
			String[] data = line.split(",");
			checkInputBook(data[0],line);
		}
		if (find) {
			Write.reWriteListBook();
		}
		
		return find;

	}

	/**
	 * read from list for finding title of action
	 * @param action title of action
	 * @param line the line of book information
	 * @throws IOException
	 */
	public static void checkInputBook(String action,String line) throws IOException {

		Book book = Load.loadBooK(line);
		
		switch (action) {
		case "CREATE":
			Library.listBook.addListBook(book);
			find = true;
			break;
		case "UPDATE":
			Node node = Library.listBook.firstBook;
			while (node != null) {
				if (node.getBook().getId() == book.getId()) {
					node.setBook(book);
				}
				node = node.getNextBook();
			}
			find = true;
			break;
		case "DELETE":
			Library.listBook.deleteBook(book);
			find = true;
			break;
		default:
			break;
		}
		
	}
}
