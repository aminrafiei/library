package main;

import enums.ELibrary;
import enums.EMenu;
import file.Write;
import library.Library;
import library.Member;
/**
 * member panel
 * @author Amin Rafiei
 *
 */
public class Console_Member{

	
	Member member;
	
	public Console_Member(Member member){
		this.member = member;
	}
	
	/**
	 * run menu
	 */
	public void run() {
		
		int input = 0;
		
		while(input!=3){
			Library.showExpiredAlart(member);
			System.out.println(member.getName()+ " " + member.getLast());
			System.out.println(EMenu.MEMBER_MENU.toString());
			input = CheckInput.checkNum();
			switch (input) {
			case 1:
				member.borrowStatus(member);
				break;
			case 2:
				System.out.println(member.toString());
				break;
			case 3:
				Write.reWriteListMem();
				Write.reWriteListBook();
				break;
			default:
				System.out.println(ELibrary.UNKNOWN_MSG.toString());
				break;
			}
		}
	}

	
}
