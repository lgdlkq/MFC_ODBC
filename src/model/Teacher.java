package model;

public class Teacher {
	private String Tno;
	private String Tname;
	private String Tsex;
	private String Ttel;
	private String Tpass;
	private int Cno;
	private String Cname;
	
	public Teacher(){
		
	}
	
	public Teacher(String tno) {
		super();
		Tno = tno;
	}

	public Teacher(String Tno,int Cno,String Cname){
		this.Tno = Tno;
		this.Cno = Cno;
		this.Cname = Cname;
	}
	
	public Teacher(String tno, String tname, int cno, String cname) {
		super();
		Tno = tno;
		Tname = tname;
		Cno = cno;
		Cname = cname;
	}

	public Teacher(String Tno,String Tname,String Tsex,String Ttel,String Tpass){
		this.Tno = Tno;
		this.Tname = Tname;
		this.Tsex = Tsex;
		this.Ttel = Ttel;
		this.Tpass = Tpass;
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
	
	public String getTno() {
		return Tno;
	}

	public void setTno(String tno) {
		Tno = tno;
	}

	public String getTname() {
		return Tname;
	}

	public void setTname(String tname) {
		Tname = tname;
	}

	public String getTsex() {
		return Tsex;
	}

	public void setTsex(String tsex) {
		Tsex = tsex;
	}

	public String getTtel() {
		return Ttel;
	}

	public void setTtel(String ttel) {
		Ttel = ttel;
	}

	public String getTpass() {
		return Tpass;
	}

	public void setTpass(String tpass) {
		Tpass = tpass;
	}

	@Override
	public String toString() {
		return "Teacher [Tno=" + Tno + ", Tname=" + Tname + ", Tsex=" + Tsex + ", Ttel=" + Ttel + ", Tpass=" + Tpass
				+ ", Cno=" + Cno + ", Cname=" + Cname + "]";
	}

}
