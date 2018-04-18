package transferobj;

public class Departments implements TransferObject<Departments>{
	private String deptNumber, deptName;
	

	public String getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String[] getValues() {
		return new String[] {getDeptName(), getDeptNumber()};
	}

	@Override
	public void printAll() {
		System.out.println(deptName +", "+deptNumber);
		
	}

}
