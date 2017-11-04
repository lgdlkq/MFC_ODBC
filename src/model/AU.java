package model;

public class AU {
	private String Uname;
	private String Upassword;
	
	public AU(){
		
	}
	
	public AU(String Uname, String Upassword){
		this.Uname = Uname;
		this.Upassword = Upassword;
	}

	public String getUname() {
		return Uname;
	}

	public void setUname(String uname) {
		Uname = uname;
	}

	public String getUpassword() {
		return Upassword;
	}

	public void setUpassword(String upassword) {
		Upassword = upassword;
	}

	@Override
	public String toString() {
		return "AU [Uname=" + Uname + ", Upassword=" + Upassword + "]";
	}
	
}
