package enums;
/**
 * all menu for console
 * @author Amin Rafiei
 *
 */
public enum EMenu {

	MAIN_MENU("Menu:"
			+ "\n1:Add new member\n"
			+ "2:Update\n3:Search\n"
			+ "4:Delete\n"
			+ "5:add Book\n"
			+ "6:borrow menu\n"
			+ "7:show profile\n"
			+ "8:give fine\n"
			+ "9:Log out"),
	
	BORROW_MENU("1:Borrow book\n"
			+ "2:refund\n"
			+ "3:show ALL borrowed book"),
	
	DELETE_MENU("1:delete member\n2:delete book"),
	
	UPDATE_MENU("1:update member\n2:update book"),
	
	SEARCH_MENU("1:search by id\n2:advanced search"),
	
	MEMBER_MENU("Menu:\n"
			+ "1:borrow status\n"
			+ "2:profile\n"
			+ "3:LogOut"),
	
	ADVANCED_SEARCH_MEM("Search By:\n"
			+ "1:First name\n"
			+ "2:Last name\n"
			+ "3:Age\n"
			+ "4:jensiyat\n"
			+ "5:End!"),
	
	ADVANCED_SEARCH_BOOK("Search By:\n"
			+ "1:Name\n"
			+ "2:Author name\n"
			+ "3:ISBN\n"
			+ "4:Topic\n"
			+ "5:End!"),
	
	TOPIC_MENU("4:Topic)"
			+ "1:Drama,"
			+ "2:Horror,"
			+ "3:History,"
			+ "4:Scientific:");
	
	private final String text;

	private EMenu(final String text) {
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return text;
	}
}
