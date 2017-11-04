package util;

import java.awt.Image;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class Constant {
	public static final String dbUserName = "root";
	public static final String dbPassword = "lgd5875452";
	public static final String jdbcName = "com.mysql.jdbc.Driver";
	public static final String dbUrl = "jdbc:mysql://localhost:3306/sams?useSSL=true&useUnicode=true&characterEncoding=utf-8";
	
	public static final int Done = 1;
	public static final int Dall = 2;
	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return false;
		}else {
			return true;
		}
	}
	
	//ÅÐ¶Ï×Ö·û´®ÊÇ·ñÈ«ÎªÊý×Ö
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
	
}
