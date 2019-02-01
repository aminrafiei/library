package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;

import library.Book;
import library.Library;
import library.List;
import library.ListBook;
import library.ListMember;
import library.Member;
import library.Node;

/**
 * this class load files
 * and set them in ram
 * @author Amin Rafiei
 *
 */
public class Load {

	public static FileReader fr;
	public static BufferedReader read;

	/**
	 * Buffered file backup
	 * @param path location of backup path
	 * @throws FileNotFoundException
	 */
	public static void readBackUp(String path) throws FileNotFoundException {

		File load = new File(path);
		fr = new FileReader(load);
		read = new BufferedReader(fr);
	}

	/**
	 * read from file list and set it ram list 
	 * @throws Exception
	 */
	public static void readList() throws Exception {

		Library.listMember = (ListMember) Load.loadList(Write.LIST_Mem_PATH);
		Library.listBook = (ListBook) Load.loadList(Write.LIST_Book_PATH);
	}

	/**
	 * read member information from backup file
	 * @param line line of member information
	 * @return new member obj
	 * @throws FileNotFoundException
	 */
	public static Member loadMem(String line) throws FileNotFoundException {

		Member member = null;
		int max = (Library.MAX_BORROW * 2);
		String[] members = line.split(",");

		int[] tempB = new int[4];
		String[] tempT = new String[4];
		for (int i = 0; i < Library.MAX_BORROW; i++)
			tempB[i] = Integer.valueOf(members[i + 8]);

		for (int i = 0; i < Library.MAX_BORROW; i++)
			tempT[i] = members[i + 11];

		member = new Member(Integer.parseInt(members[1]),Integer.parseInt(members[2]), Integer.parseInt(members[3]), members[4], members[5],
				Integer.parseInt(members[6]), members[7].charAt(0), Integer.parseInt(members[max + 2]), tempB, tempT,
				Boolean.parseBoolean(members[max + 3]),Integer.parseInt(members[16]),Integer.parseInt(members[17]));

		return member;

	}

	/**
	 * read book information from backup file
	 * @param line line of book information
	 * @return new book obj
	 */
	public static Book loadBooK(String line) {

		String[] books = line.split(",");
		Book book = new Book(Integer.parseInt(books[1]), books[2], books[3], Integer.parseInt(books[4]), books[5],
				Boolean.parseBoolean(books[6]));
		return book;
	}

	/**
	 * read and load list from file
	 * @param path of list files
	 * @return memberlist or booklist
	 * @throws Exception
	 */
	public static List loadList(String path) throws Exception {

		List list = null;
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		list = (List) in.readObject();
		in.close();
		fileIn.close();
		return list;

	}

	/**
	 * counting number of member or book from 
	 * backup list
	 */
	public static void countNumMemberBook() {

		Node node = Library.listMember.firstMem;
		while (node != null) {
			Library.memberCounter++;
			node = node.getNextMemmber();
		}
		node = Library.listBook.firstBook;
		while (node != null) {
			Library.bookCounter++;
			node = node.getNextBook();
		}

	}

}
