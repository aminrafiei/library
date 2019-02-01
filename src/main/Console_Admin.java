package main;

import enums.EMenu;
import file.Write;
import enums.ELibrary;
import library.Admin;
import library.Library;
/**
 * admin panel
 * @author Amin Rafiei
 *
 */
public class Console_Admin{

	Admin admin;
	
	public Console_Admin(Admin admin){
		this.admin = admin;
	}
	
	/**
	 * run admin menu
	 */
	public void run() {
		
		int input = 0;
		while (input != 9) {
			Library.showExpiredAlart(admin);
			System.out.println(EMenu.MAIN_MENU.toString());
			input = CheckInput.checkNum();
			switch (input) {
			case 1:
				Admin.manageCreateMember();
				break;
			case 2:
				updatePart();
				break;
			case 3:
				System.out.println("1:member\n2:book");
				int n = CheckInput.checkNum();
				if (n == 1)
					searchPart(1);
				else if (n == 2)
					searchPart(2);
				else
					CheckInput.findMSG(false);
				break;
			case 4:
				deletePart();
				break;
			case 5:
				Admin.mangerAddBook();
				break;
			case 6:
				borrowPart();
				break;
			case 7:
				admin.profileDefault();
				System.out.println(admin.toString());
				break;
			case 8:
				admin.fine(true);
				break;
			case 9:
				Write.reWriteListMem();
				Write.reWriteListBook();
				break;
			default:
				CheckInput.findMSG(false);
				break;
			}
		}
	}

	/**
	 *
	 */
	public void borrowPart() {
		System.out.println(EMenu.BORROW_MENU.toString());
		int input = CheckInput.checkNum();
		switch (input) {
		case 1:
			admin.borrow();
			break;
		case 2:
			admin.refund();
			break;
		case 3:
			admin.borrowStatus(admin);
			break;
		default:
			System.out.println(ELibrary.UNKNOWN_MSG.toString());
			break;
		}
	}
	
	/**
	 * delete member or book by id
	 */
	public void deletePart(){
		System.out.println(EMenu.DELETE_MENU.toString());
		int j = CheckInput.checkNum();
		if (j == 1)
			Admin.deleteMember();
		else if (j == 2)
			Admin.deleteBook();
		else
			CheckInput.findMSG(false);
	}

	/**
	 * update member or book by id
	 */
	public void updatePart(){
		System.out.println(EMenu.UPDATE_MENU.toString());
		int i = CheckInput.checkNum();
		if (i == 1)
			Admin.updateMember();
		else if (i == 2)
			Admin.updateBook();
		else
			CheckInput.findMSG(false);
	}

	/**
	 * search member or book
	 * @param input choose for search type
	 */
	public void searchPart(int input){
		int in;
		System.out.println(EMenu.SEARCH_MENU.toString());
		switch (input) {
		case 1:
			in = CheckInput.checkNum();
			if(in==1)
				Admin.readMember(1);
			else if(in==2)
				Admin.readMember(2);
			else
				CheckInput.findMSG(false);
			break;
		case 2:
			 in = CheckInput.checkNum();
			if(in==1)
				Admin.readBook(1);
			else if(in==2)
				Admin.readBook(2);
			else
				CheckInput.findMSG(false);
			break;
		default:
			CheckInput.findMSG(false);
			break;
		}
	}
	
	
}
