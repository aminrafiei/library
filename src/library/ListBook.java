package library;

import java.io.Serializable;

public class ListBook implements List, Serializable {

	public Node firstBook = null;

	/**
	 * This method add object of book to list of Node
	 * 
	 * @param book
	 *            the object of book
	 */
	public void addListBook(Book book) {

		Library.bookCounter++;  
		Node n = new Node(book);
		if (firstBook == null) {
			firstBook = n;
		} else {
			Node p = firstBook;

			while (p.getNextBook() != null)
				p = p.getNextBook();

			p.setNextBook(n);
		}

	}

	/**
	 * This method delete a book
	 * 
	 * @param book
	 *            the object of book
	 */
	public boolean[] deleteBook(Book book) {

		boolean find = false;
		boolean isDel = false;

		Node p = firstBook;
		while (p != null) {

			if (firstBook.equals(book)) {
				find = true;
				if (firstBook.getBook().isAvailable()) {
					firstBook = p.getNextBook();
					isDel = true;
				}
			} else {
				if (p.getNextBook() != null && p.getNextBook().equals(book)) {
					find = true;
					if (p.getNextBook().getBook().isAvailable()) {
						p.setNextBook(p.getNextBook().getNextBook());
						isDel = true;
					}
				}
			}

			p = p.getNextBook();
		}

		return new boolean[] { find, isDel };

	}
}
