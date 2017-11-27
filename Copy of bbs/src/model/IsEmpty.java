package model;

import java.util.regex.Matcher;
import java.util.regex.*;

public class IsEmpty {
            public static boolean Checkisempty(String string){
            	System.out.println("class:IsEmpty 传入参数string="+string); 
            	Pattern p = Pattern.compile("^\\s+");
            	 Matcher m = p.matcher(string);
            	 boolean b = m.matches();
            	if(b||string.equals("")) {
            		return true;
            		}else{	return false;
            	}
            }
}
