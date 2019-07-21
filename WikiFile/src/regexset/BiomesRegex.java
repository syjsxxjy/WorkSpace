package regexset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import doaction.Fileread;

public class BiomesRegex {
	//public String P =  ;
		//以下是读取原文所有行内容
			public static  String PobjectNamel="(\"objectName\") : (\\S+),";
			public static String PcolonyTagsl ="(\"colonyTags\") : (\\S+),";
			public static String Prarityl = "(\"rarity\") : (\\S+)," ;
			public static String Pdescriptionl ="(\"description\") : (.+),"  ;
			public static String Pshortdescriptionl = "(\"shortdescription\") : (.+)," ;
			public static String Pracel ="(\"race\") : (\\S+),";
			public static String Pcategoryl = "(\"category\") : (\\S+)," ;
			public static String Ppricel = "(\"price\") : (\\S+)," ;
			public static String PinventoryIconl = "(\"inventoryIcon\") : (\\S+)," ;
	//以下是读取键值对的value值
			public static String PobjectName="(?<=\"objectName\" : \").+(?=\")";
			public static String PcolonyTags ="(?<=\"colonyTags\" : ).+(?=,)";
			public static String Prarity ="(?<=\"rarity\" : \").+(?=\")";
			public static String Pdescription ="(?<=\"description\" : \").+(?=\")";
			public static String Pshortdescription ="(?<=\"shortdescription\" : \").+(?=\")";
			public static String Prace ="(?<=\"race\" : \").+(?=\")";
			public static String Pcategory = "(?<=\"category\" : \").+(?=\")";
			public static String Pprice ="(?<=\"price\" : ).+(?=,)";
			public static  String PinventoryIcon = "(?<=\"inventoryIcon\" : \")\\S+.png" ;
			
			public static ArrayList<String> Pline=new ArrayList<String>();
			public static ArrayList<String> Pvalue=new ArrayList<String>();
			
			public static void addlist(){	
			        	Pline.add(PobjectNamel);
			        	Pline.add(PcolonyTagsl );
			        	Pline.add(Pracel  );
			        	Pline.add( Prarityl );
			        	Pline.add(Pdescriptionl );
			        	Pline.add(Pshortdescriptionl  );
			        	Pline.add( Pcategoryl );
			        	Pline.add( Ppricel);
			        	Pline.add(PinventoryIconl  );
		//Value正则表达式列表增加	        	
			        	Pvalue.add(PobjectName);
			        	Pvalue.add(PcolonyTags );
			        	Pvalue.add(Prace  );
			        	Pvalue.add( Prarity );
			        	Pvalue.add(Pdescription );
			        	Pvalue.add(Pshortdescription  );
			        	Pvalue.add( Pcategory );
			        	Pvalue.add( Pprice);
			        	Pvalue.add(PinventoryIcon  );
			}
			
			public static void main(String[] args) throws IOException {
				addlist();
            System.out.println("=======Regex集输出结果==============");
                for(int i=0;i<Pline.size();i++){
                	System.out.println("Pline "+Pline.get(i));
                	System.out.println("Pvalue "+Pvalue.get(i));
                }
    	    System.out.println("=====================");
    	    String path="FilesIN/sb/biomes/surface_detached/flesh/dripblood1/dripblood1.object";
    	    Fileread fr=new Fileread();
            String filestr= fr.Readfile(path).toString();
            StringBuilder sb1=new StringBuilder();
            StringBuilder sb2=new StringBuilder();
            String Pstring="";
            ArrayList<String>vlist=new ArrayList<String>();
            
            for(int i=0;i<Pline.size();i++){
         	   Pstring=Pline.get(i);
         	   Pattern p1 = Pattern.compile(Pstring);
         	   System.out.println(Pstring);
             	Matcher m1 = p1.matcher(filestr);
                 System.out.println(m1.find());
                 sb1.append("  "+m1.group()+"\r\n");
                 m1.reset(); 
                 
                 Pstring=Pvalue.get(i);
          	   Pattern p2 = Pattern.compile(Pstring);
          	   System.out.println(Pstring);
              	Matcher m2 = p2.matcher(filestr);
                  System.out.println(m2.find());
                  sb2.append(m2.group()+"\r\n");
                  vlist.add(m2.group().toString());
                  m2.reset(); 
            }
    	          System.out.println(sb1); System.out.println("=====================");
    	          System.out.println(sb2);
    	          
			}
	}

