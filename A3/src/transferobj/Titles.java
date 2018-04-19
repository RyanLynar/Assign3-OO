package transferobj;

import java.sql.Date;

public class Titles implements TransferObject<Titles> {
	private int empNo;
	private String title;
	private Date tDate,fDate;

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	@Override
	public String[] getValues() {
		return new String[] {""+getEmpNo(),getTitle(),getfDate().toString(),gettDate().toString()};
	}

	@Override
	public void printAll() {
		// TODO Auto-generated method stub
		
	}

}