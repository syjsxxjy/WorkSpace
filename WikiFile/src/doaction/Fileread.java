package doaction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import test.Hashmaptest;
/*
 * 读取文件，并且进行键值对处理
 */
public class Fileread {
	StringBuilder sb;   StringBuilder sbcn;
    public  Map<String, String> map;//用来存放从原文件中读取的键值对
    public	Map<String, String> mapcn;//用来存放从中文mod读取的键值对
	     
        public  StringBuilder  Readfile(String path) throws IOException{
            sb=new StringBuilder(); map=new HashMap<String,String>();
    		FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");  
            BufferedReader br = new BufferedReader(isr);
           StrReplaceSet srs =new StrReplaceSet();
            String str = null;int line = 1;
           
            while((str = br.readLine()) != null) {//方式1，替换多余字符串
                  sb.append(str+"\r\n");
                 // System.out.println(str);
                  // 显示行号
       //           System.out.println("line " + line + ": " + str);
                  if (str.contains(":")==true) {   //如果存在冒号则分割该字符串
                      String[] strArray = str.split(":");   srs.replace(strArray);  //  System.out.println(srs.keypre+"   "+srs.valuepre    );

                    /*  String keypre=strArray[0].replace(" ", "").replace("\"", "");   //第1种规则替换读取的每行文本
                      String valuepre=strArray[1].replace("\"", "").replace(",", ""); ////第1种规则替换读取的每行文本
*/                   map.put(srs.keypre, srs.valuepre);      //分别将键值对存入map中
                       
                  }
                  line++;
            }
           

            fis.close();
            br.close();
            isr.close();
			return sb; 
        }
        /*
         * 读取方法2，第二种匹配方式替换读取的每行内容，用来读取中文mod
         */
        
public  StringBuilder  Readfilecn(String path) throws IOException{
	        mapcn=new HashMap<String,String>();
        	sbcn=new StringBuilder("");
    		FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");  
            BufferedReader br = new BufferedReader(isr);
            StrReplaceSet srs =new StrReplaceSet();
            String str = null;int line = 1;
           
            while((str = br.readLine()) != null) {
                  sbcn.append(str+"\r\n");
                 // System.out.println(str);
                  // 显示行号
         //         System.out.println("line " + line + ": " + str);
                  if (str.contains(":")==true) {   //如果存在冒号则分割该字符串
                      String[] strArray = str.split(":");  srs.cnreplace(strArray);
                      String keypre=srs.cnkeypre;  String valuepre=srs.cnvaluepre;
            /*          String keypre=strArray[0].replace(" ", "").replace("\"", ""); //第二种规则替换读取的每行文本
                      String valuepre=strArray[1].replace("\"", "").replace(",", "");  //第二种规则替换读取的每行文本
*/                      mapcn.put(keypre, valuepre);      //分别将键值对存入map中
                  }
                  line++;
            }
           

            fis.close();
            br.close();
            isr.close();
			return sbcn; 
        }
}
