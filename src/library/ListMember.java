package library;

import java.io.Serializable;

public class ListMember implements Serializable, List {

	public Node firstMem = null;

	/**
	 * This method add object of member to list of Node
	 * 
	 * @param member
	 *            the object of member
	 */
	public void addListMem(Member member) {

		Library.memberCounter++;
		Node n = new Node(member);
		if (firstMem == null) {
			firstMem = n;
		} else {
			Node p = firstMem;

			while (p.getNextMemmber() != null)
				p = p.getNextMemmber();

			p.setNextMemmber(n);
		}

	}

	/**
	 * this method delete a member
	 * 
	 * @param member
	 *            is membership id
	 */
	public boolean[] deleteMember(Member member) {

		boolean isDel = false;
		boolean find = false;
		Node p = firstMem;
		while (p != null) {

			if (firstMem.equals(member)) {
				find = true;
				if (firstMem.getMember().getNumBorrow() == 0) {
					firstMem = p.getNextMemmber();
					isDel = true;
				}
			} else {
				if (p.getNextMemmber() != null && p.getNextMemmber().equals(member)) {
					find = true;
					if (p.getNextMemmber().getMember().getNumBorrow() == 0) {
						p.setNextMemmber(p.getNextMemmber().getNextMemmber());
						isDel = true;
					}
				}

			}

			p = p.getNextMemmber();
		}

		return new boolean[] {find, isDel};

	}
	
	

}
