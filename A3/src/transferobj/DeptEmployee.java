package transferobj;

import java.sql.Date;
import java.util.ArrayList;

import databaseaccess.DAODeptEmployee;

public class DeptEmployee implements TransferObject<DeptEmployee> {
	private int empID;
	private String deptID;
	private Date fDate, tDate;

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
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
		return new String[] { "" + getEmpID(), getDeptID(), getfDate().toString(), gettDate().toString() };

	}

	@Override
	public void printAll() {
		// TODO decide if needed

	}

}
