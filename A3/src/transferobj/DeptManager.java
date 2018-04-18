package transferobj;

import java.sql.Date;

public class DeptManager implements TransferObject<DeptManager>{
	private int employeeNumber;
	private String deptNumber;
	private Date fromDate, toDate;
	
	

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String[] getValues() {
		// TODO Auto-generated method stub
		return new String[] {""+ getEmployeeNumber(), getDeptNumber(), getFromDate().toString(), getToDate().toString()};
	}

	@Override
	public void printAll() {
		System.out.println(getEmployeeNumber()+" "+getDeptNumber()+" "+getFromDate().toString()+" "+getToDate().toString());
		
	}

}
