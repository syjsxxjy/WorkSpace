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
 * ��ȡ�ļ������ҽ��м�ֵ�Դ���
 */
public class Fileread {
	StringBuilder sb;   StringBuilder sbcn;
    public  Map<String, String> map;//������Ŵ�ԭ�ļ��ж�ȡ�ļ�ֵ��
    public	Map<String, String> mapcn;//������Ŵ�����mod��ȡ�ļ�ֵ��
	     
        public  StringBuilder  Readfile(String path) throws IOException{
            sb=new StringBuilder(); map=new HashMap<String,String>();
    		FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");  
            BufferedReader br = new BufferedReader(isr);
           StrReplaceSet srs =new StrReplaceSet();
            String str = null;int line = 1;
           
            while((str = br.readLine()) != null) {//��ʽ1���滻�����ַ���
                  sb.append(str+"\r\n");
                 // System.out.println(str);
                  // ��ʾ�к�
       //           System.out.println("line " + line + ": " + str);
                  if (str.contains(":")==true) {   //�������ð����ָ���ַ���
                      String[] strArray = str.split(":");   srs.replace(strArray);  //  System.out.println(srs.keypre+"   "+srs.valuepre    );

                    /*  String keypre=strArray[0].replace(" ", "").replace("\"", "");   //��1�ֹ����滻��ȡ��ÿ���ı�
                      String valuepre=strArray[1].replace("\"", "").replace(",", ""); ////��1�ֹ����滻��ȡ��ÿ���ı�
*/                   map.put(srs.keypre, srs.valuepre);      //�ֱ𽫼�ֵ�Դ���map��
                       
                  }
                  line++;
            }
           

            fis.close();
            br.close();
            isr.close();
			return sb; 
        }
        /*
         * ��ȡ����2���ڶ���ƥ�䷽ʽ�滻��ȡ��ÿ�����ݣ�������ȡ����mod
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
                  // ��ʾ�к�
         //         System.out.println("line " + line + ": " + str);
                  if (str.contains(":")==true) {   //�������ð����ָ���ַ���
                      String[] strArray = str.split(":");  srs.cnreplace(strArray);
                      String keypre=srs.cnkeypre;  String valuepre=srs.cnvaluepre;
            /*          String keypre=strArray[0].replace(" ", "").replace("\"", ""); //�ڶ��ֹ����滻��ȡ��ÿ���ı�
                      String valuepre=strArray[1].replace("\"", "").replace(",", "");  //�ڶ��ֹ����滻��ȡ��ÿ���ı�
*/                      mapcn.put(keypre, valuepre);      //�ֱ𽫼�ֵ�Դ���map��
                  }
                  line++;
            }
           

            fis.close();
            br.close();
            isr.close();
			return sbcn; 
        }
}
