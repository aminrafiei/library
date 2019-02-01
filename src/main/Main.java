package main;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import enums.ELibrary;
import file.BackUp;
import file.Load;
import library.Admin;
import library.Member;

/**
 * The library project is simulation for real library management that can use
 * for borrow and refund book manage
 * @author Amin Rafiei
 *
 */
public class Main {

	private static boolean find;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Load.readList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			Load.readList();
		} catch (FileNotFoundException i) {
			System.out.println("cant find file!!!");
		} catch (EOFException e) {
			System.out.println("cant load any data!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			boolean m = BackUp.checkMem();
			boolean n = BackUp.checkBook();
			if(m || n)
				System.out.println("information successfully restore!!");
		}catch (FileNotFoundException i) {
			System.out.println("cant find backup files file!!!");
		}catch (IOException e) {
			e.printStackTrace();
		}
		Load.countNumMemberBook();
		
		
		while (true) {
			logIn();
		}
	}

	/**
	 * this method show the massage for wrong password or user
	 * the login menu and check the access
	 */
	public static void logIn() {

		System.out.print(ELibrary.USERNAME);
		String id = CheckInput.checkString();
		System.out.print(ELibrary.PASSWORD);
		int pass = CheckInput.checkNum();
		find = Admin.access(id, pass);
		if (!find)
			find = Member.access(id, pass);
		find();
	}

	/**
	 *
	 */
	protected static void find() {

		if (!find)
			System.out.println("User or password is incorrect");
		else if (find)
			find = false;
	}
}
