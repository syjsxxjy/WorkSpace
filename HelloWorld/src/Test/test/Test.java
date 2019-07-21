package Test.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.lang.Integer;

public class Test {
     public static void main(String[] args) throws IOException {
    	 int l =1;
//    	 System.out.println("主方法");
    	 File f = new File("number1.txt");
    	 if ( f.isFile()){
//    		 System.out.println("判断文件");
    		 BufferedReader br = new BufferedReader(new FileReader("number1.txt"));
    		 HashSet<String> hs = new HashSet<String>();
    		 String line;
    		 while((line = br.readLine()) != null) {
    			 if (line.equals("")==false) {
    				 hs.add(line);
    			 }
    				
    			}
			 System.out.println(hs);
    		 List <Integer>list = new ArrayList<Integer>();
    		 for (String s : hs) {
//    			 System.out.println(s);
    			 int i=Integer.parseInt( s );
//    			 System.out.println(i);
    			 list.add(i);
    		 }
    		 Collections.sort(list);
    		 BufferedWriter bw = new BufferedWriter(new FileWriter("number2.txt"));
    		 for(int i : list){
//    			 System.out.println("List遍历");
    			     System.out.println(i);
    			      bw.write(String.valueOf(i));
    				 bw.newLine();

    		 }
    		 
    		 bw.close();
    			br.close();

    		 
    	 }
	}
} 
