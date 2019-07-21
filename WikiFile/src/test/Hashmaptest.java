package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import doaction.Fileread;
import doaction.Filewrite;
import samplewikipage.BiomesPage;

public class Hashmaptest {
        public static void main(String[] args) throws IOException {
        	Map<String, String> map = new HashMap<String, String>();
        	Map<String, String> mapcn = new HashMap<String, String>();
        /*	map.put("shortdescription", "Eye Shield");
        	String s=map.get("shortdescription").toString();
        	System.out.println(s);*/
        	      	
        	String path="FilesIN/sb/biomes/surface_detached/flesh/dripblood1/dripblood1.object";
        	StringBuilder sb=new StringBuilder("");
    		FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            
            BufferedReader br = new BufferedReader(isr);
           
            String str = null;
            int line = 1;
            while((str = br.readLine()) != null) {// 一次读入一行，直到读入null为文件结束
                  sb.append(str+"\r\n");
                      // 显示行号
                      System.out.println("line " + line + ": " + str);
                      if (str.contains(":")==true) {   //如果存在冒号则分割该字符串
                          String[] strArray = str.split(":");
                          String keypre=strArray[0].replace(" ", "").replace("\"", "");
                          String valuepre=strArray[1].replace("\"", "").replace(",", "");
                          map.put(keypre, valuepre);      //分别将键值对存入map中
                      }
                      line++;
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////
        	String pathcn="FilesIN/cnmod/biomes/surface_detached/flesh/dripblood1/dripblood1.object.patch";
        	StringBuilder sb2=new StringBuilder("");
    		FileInputStream fis2 = new FileInputStream(pathcn);
            InputStreamReader isr2 = new InputStreamReader(fis2, "UTF-8");
            BufferedReader br2 = new BufferedReader(isr2);
           
            String str2 = null;
            int line2 = 1;
            while((str2 = br2.readLine()) != null) {// 一次读入一行，直到读入null为文件结束
                  sb2.append(str2+"\r\n");
                      // 显示行号
             //         System.out.println("line " + line + ": " + str);
                      if (str2.contains(":")==true) {   //如果存在冒号则分割该字符串
                          String[] strArray2 = str2.split(":");
                          String keypre2=strArray2[2].replace(" ", "").replace("\"", "").replace(",", "").replace("value", "").replace("/", "");
                          String valuepre2=strArray2[3].replace(" ", "").replace(",", "").replace("}", "").replace("]", "").replace("\"", "");
                              //分别将键值对存入map中
                          mapcn.put(keypre2, valuepre2);
                          
                      }
                      line2++;
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            for (Map.Entry<String, String> entry : map.entrySet()) {
            	   String key = entry.getKey().toString();
            	   String value = entry.getValue().toString();
            	   System.out.println("key=" + key + " value=" + value);
            	  }
           BiomesPage bp=new BiomesPage();
           
           System.out.println(bp.getPage(map,mapcn));
            Filewrite fw =new Filewrite();   fw.Writefile(bp.getPage(map,mapcn), "src/test/tttttest.txt");
            fis.close();
            br.close();
            isr.close();

		}
}
