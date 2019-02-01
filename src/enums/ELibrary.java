package enums;
/**
 * massage of Library
 * @author Amin Rafiei
 *
 */
public enum ELibrary {

	SUCCESSFUL_MSG("successfully!!"),
	
	UNKNOWN_MSG("invalid input!"),
	
	UNKNOWN_MEMBER("unknown member!"),
	
	UNKNOWN_BOOK("unknow book!"),
	
	DELETED_MSG("deleted!!"),
	
	ID_MEMBER("id memeber:"),
	
	ID_BOOK("id book:"),

	DELETE_WARNING("you cant delete!!"),
	
	MAX_BORROW_WARNING("you already have 3 borrowed book!"),
	
	LINE("--------------------------"),
	
	SHARP("#####"),
	
	USERNAME("username:"),
	
	PASSWORD("password(num only):"),
	
	CANT_BORROW("cant borrow!!"),
	
	AVALIBLE_WARNING("it isnt avaliable!!"),
	
	EXPIRED_ALART("\n********you have expired book********"),
	
	EMPTY_MSG("Nothing found!");
	
	
	
	
	
	private final String text;

	private ELibrary(final String text) {
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return text;
	}

}
