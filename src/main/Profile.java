package main;

import java.io.Serializable;

import enums.ELibrary;
import file.Write;
import library.Library;

/**
 * this class extends oc CheckInput extended in Admin class,Member class and
 * Search class
 * 
 * @author Amin
 *
 */
abstract public class Profile implements Serializable {

	protected String name;
	protected String last;
	protected int age;
	protected char gender;
	protected int id;
	protected int nationalId;
	protected int pass;

	@Override
	public String toString(){
		return ("1:First name:" + getName()+"\n2:last name:" + getLast()+"\n3:age:" + getAge()+
				"\n4:gender:" + getGender()+"\n5:Local Id:" + getId()+"\n6:National Id:" + getNationalId()+
				"\n7:Password:" + getPass()+"\n"+ELibrary.LINE.toString());
	}

	public void borrowStatus(Accessable acc) {
		
		Library.showALLBorrowedBook(acc);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {

		this.gender = gender;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getNationalId() {
		return nationalId;
	}

	public void setNationalId(int nationalId) {
		this.nationalId = nationalId;
	}
	
	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

}
