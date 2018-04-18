package transferobj;

import java.sql.Date;
import java.util.ArrayList;

import databaseaccess.DAOEmployee;

public class Employee implements TransferObject<Employee> {
	private int empNumber;
	private String empFName, empLName, empGender;
	private Date empBDate, empHDate;

	public int getEmpNumber() {
		return empNumber;
	}

	public String getEmpFName() {
		return empFName;
	}

	public String getEmpLName() {
		return empLName;
	}

	public String getEmpName() {
		return getEmpFName() + " " + getEmpLName();
	}

	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}

	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}

	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public void setEmpBDate(Date empBDate) {
		this.empBDate = empBDate;
	}

	public void setEmpHDate(Date empHDate) {
		this.empHDate = empHDate;
	}

	public String getEmpGender() {
		return empGender;
	}

	public Date getEmpBDate() {
		return empBDate;
	}

	public Date getEmpHDate() {
		return empHDate;
	}

	public void printAll() {
		System.out.println(getEmpNumber() + " " + getEmpFName() + " " + getEmpLName() + " " + getEmpGender() + " "
				+ getEmpBDate() + " " + getEmpHDate());
	}
	@Override
	public String[] getValues() {
		return new String[] { "" + getEmpNumber(), getEmpBDate().toString(), getEmpFName(), getEmpLName(),
				getEmpGender(), getEmpHDate().toString() };
	}

}
