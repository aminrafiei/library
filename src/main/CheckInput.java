package main;

import java.util.Scanner;

import enums.ELibrary;

/**
 * Check input value
 * 
 * @author Amin Rafiei
 *
 */
public class CheckInput {

	static Scanner scan = new Scanner(System.in);

	/**
	 * check the input is "f" or "m"
	 * 
	 * @return char "m" or "f"
	 */
	public static String checkString() {
		return scan.next();
	}

	public static final void findMSG(boolean find) {
		if (!find)
			System.out.println(ELibrary.UNKNOWN_MSG.toString());
		else
			System.out.println(ELibrary.SUCCESSFUL_MSG.toString());
	}

	public static char checkChar() {

		while (!scan.hasNext("m") && !scan.hasNext("f")) {
			System.out.println(ELibrary.UNKNOWN_MSG.toString());
			scan.next();
		}
		char output = scan.next().charAt(0);
		return output;
	}

	/**
	 * check input is number
	 * 
	 * @return number(int)
	 */
	public static int checkNum() {

		while (!scan.hasNextInt()) {
			System.out.println(ELibrary.UNKNOWN_MSG.toString());
			scan.next();
		}
		int output = scan.nextInt();
		return output;
	}

	/**
	 * save the input choises
	 * 
	 * @return array of choise
	 */
	public static int[] saveInput() {

		int part = 0;
		int[] save = new int[6];
		while (part != 5) {
			part = checkNum();
			if (part <= 5 && part > 0)
				save[part] = part;
			else
				System.out.println(ELibrary.UNKNOWN_MSG.toString());
		}
		return save;
	}

}
