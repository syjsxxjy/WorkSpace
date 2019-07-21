package test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import doaction.Fileread;
import doaction.Filewrite;

public class RegexTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String path="FilesIN/grapple.activeitem";
        Fileread fr=new Fileread();
        String string= fr.Readfile(path).toString();
       // System.out.println(string);
		
    	// Pattern p = Pattern.compile("(?<=\"itemName\" : \").+(?=\")");
        String ss="(\"itemName\") : (\\S+),";
        System.out.println(ss);
        Pattern p = Pattern.compile(ss);
    	 Matcher m = p.matcher(string);
    	 System.out.println(m.find());
    	 
         Pattern p2 = Pattern.compile("(\"price\") : (\\S+),");
    	 Matcher m2 = p2.matcher(string);
    	 System.out.println(m2.find());
    	 
    	 StringBuilder sb=new StringBuilder("");
    	 sb.append(m.group());
    	 sb.append("\r\n");
    	 sb.append(m2.group());
    	 System.out.println("      "+m2.group());
    	 sb.append("为什么要放弃治疗♂");
    	 
    	 System.out.println(sb);
    	 String path2="src/test/test3.txt";
    	 Filewrite fw =new Filewrite();
    	 fw.Writefile(sb,path2);

	}

}
