package model;

public class Course {
	private int Cno;
	private String Cname;
	private int Cpno;
	private float Ccredit;
	
	public Course() {
		super();
	}
	
	public Course(int cno) {
		super();
		Cno = cno;
	}

	public Course(int cno, int cpno) {
		super();
		Cno = cno;
		Cpno = cpno;
	}

	public Course(String cname, float ccredit) {
		super();
		Cname = cname;
		Ccredit = ccredit;
	}

	public Course(int cno, String cname, int cpno, float ccredit) {
		super();
		Cno = cno;
		Cname = cname;
		Cpno = cpno;
		Ccredit = ccredit;
	}


	public int getCno() {
		return Cno;
	}
	public void setCno(int cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public int getCpno() {
		return Cpno;
	}
	public void setCpno(int cpno) {
		Cpno = cpno;
	}
	public float getCcredit() {
		return Ccredit;
	}
	public void setCcredit(float ccredit) {
		Ccredit = ccredit;
	}
}
