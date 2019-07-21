package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Fileinout {

	public static void main(String[] args) throws IOException,FileNotFoundException {
		// TODO Auto-generated method stub
		StringBuffer sb= new StringBuffer("");
		FileInputStream fis = new FileInputStream("src/Filesin/eyeshield.txt");
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        
        BufferedReader br = new BufferedReader(isr);
       
        String str = null;
       
        while((str = br.readLine()) != null) {
              sb.append(str+"\r\n");
              System.out.println(str);
        }
       
        fis.close();
        br.close();
        isr.close(); 
        
        
        //写出文件操作
        
        FileOutputStream writerStream = new FileOutputStream("src/test/test.txt", true);  
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
        
        bw.write(sb.toString());
       
        bw.close();
        writerStream.close();

	}

}
