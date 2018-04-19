package transferobj;

import java.sql.Date;

public class Salaries implements TransferObject<Salaries> {
	int empNo, salary;
	Date fDate, tDate;

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	@Override
	public String[] getValues() {
		return new String[] {""+getEmpNo(),""+getSalary(),getfDate().toString(),gettDate().toString()};
	}

	@Override
	public void printAll() {
		// TODO Auto-generated method stub

	}

}
