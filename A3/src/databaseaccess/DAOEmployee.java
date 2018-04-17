package databaseaccess;

import java.sql.Date;

public class DAOEmployee extends DAO {
	private int empNumber;
	private String empFName, empLName, empGender;
	private Date empBDate, empHDate;

	public DAOEmployee() {
		super("employees");
	}

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

	@Override
	public String[] getColumnNames() {
		return new String[] { "emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date" };
	}

	public void printAll() {
		System.out.println(getEmpNumber() + " " + getEmpFName() + " " + getEmpLName() + " " + getEmpGender() + " "
				+ getEmpBDate() +" "+ getEmpHDate());
	}

	@Override
	public void setString(String data, String cName) {
		if (cName.equals("first_name")) {
			setEmpFName(data);
		} else if (cName.equals("last_name")) {
			setEmpLName(data);
		}
	}
	@Override
	public void setChar(String data, String cName) {
		if (cName.equals("gender")) {
			System.out.println("add gender");
			setEmpGender(data);
		}
	}
	@Override
	public void setInt(int data, String cName) {
		if (cName.equals("emp_no")) {
			setEmpNumber(data);
		}

	}

	@Override
	public void setDate(Date data, String cName) {
		if (cName.equals("birth_date")) {
			setEmpBDate(data);
		} else if (cName.equals("hire_date")) {
			setEmpHDate(data);
		}

	}
}
