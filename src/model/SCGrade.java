package model;

public class SCGrade {
	private String Sno;
	private String Sname;
	private int Cno;
	private String Cname;
	private float Cgrade;
	private String Sclass;
	private float avgClass;
	private float avgPrivate;
	private int allCredit;
	private String Tno;

	public SCGrade() {

	}

	public SCGrade(String Sno, float avgPrivate, int allCredit) {
		this.Sno = Sno;
		this.avgPrivate = avgPrivate;
		this.allCredit = allCredit;
	}

	public SCGrade(String Sclass, String Cname, float avgClass) {
		this.Sclass = Sclass;
		this.Cname = Cname;
		this.avgClass = avgClass;
	}

	public SCGrade(String Sno,int Cno,String Cname, String Tno, float Cgrade) {
		this.Sno = Sno;
		this.Cno = Cno;
		this.Cname = Cname;
		this.Tno = Tno;
		this.Cgrade = Cgrade;
	}

	public SCGrade(String Sno, String Sname, String Cname, float Cgrade, String Sclass) {
		this.Sno = Sno;
		this.Sname = Sname;
		this.Cname = Cname;
		this.Cgrade = Cgrade;
		this.Sclass = Sclass;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getTno() {
		return Tno;
	}

	public void setTno(String tno) {
		Tno = tno;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
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

	public float getCgrade() {
		return Cgrade;
	}

	public void setCgrade(float cgrade) {
		Cgrade = cgrade;
	}

	public String getSclass() {
		return Sclass;
	}

	public void setSclass(String sclass) {
		Sclass = sclass;
	}

	public float getAvgClass() {
		return avgClass;
	}

	public void setAvgClass(float avgClass) {
		this.avgClass = avgClass;
	}

	public float getAvgPrivate() {
		return avgPrivate;
	}

	public void setAvgPrivate(float avgPrivate) {
		this.avgPrivate = avgPrivate;
	}

	public int getAllCredit() {
		return allCredit;
	}

	public void setAllCredit(int allCredit) {
		this.allCredit = allCredit;
	}

}
