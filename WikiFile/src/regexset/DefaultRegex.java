package regexset;

import java.util.ArrayList;

public class DefaultRegex {
	//public String P =  ;
	//以下是读取原文所有行内容
		public static  String Pitemnamel="(\"itemName\") : (\\S+),";
		public static String Ppricel ="(\"price\") : (\\S+),";
		public static String Pmaxtackl = "(\"maxStack\") : (\\S+)," ;
		public static String Prarityl = "(\"rarity\") : (\\S+)," ;
		public static String Pdescriptionl ="(\"description\") : (.+),"  ;
		public static String Pshortdescriptionl = "(\"shortdescription\") : (\\S+)," ;
		public static String Ptooltipkindl ="(\"tooltipKind\") : (\\S+),"  ;
		public static String Ptwohandedl = "(\"twoHanded\") : (\\S+)," ;
		public static String Pinventoryiconl = "(\"inventoryIcon\") : (\\S+)," ;
//以下是读取键值对的value值
		public static String Pitemname="(?<=\"itemName\" : \").+(?=\")";
		public static String Pprice ="(?<=\"price\" : ).+(?=,)";
		public static String Pmaxtack ="(?<=\"maxStack\" : ).+(?=,)";
		public static String Prarity ="(?<=\"rarity\" : \").+(?=\")";
		public static String Pdescription ="(?<=\"description\" : \").+(?=\")";
		public static String Pshortdescription ="(?<=\"shortdescription\" : \").+(?=\")";
		public static String Ptooltipkind ="(?<=\"tooltipKind\" : \").+(?=\")";
		public static String Ptwohanded = "(?<=\"twoHanded\" : ).+(?=,)";
		public static  String Pinventoryicon = "(?<=\"inventoryIcon\" : \")\\S+.png" ;
		
		public static ArrayList<String> Pline=new ArrayList<String>();
		public static ArrayList<String> Pvalue=new ArrayList<String>();
		
		public static void addlist(){	
		        	Pline.add(Pitemnamel);
		        	Pline.add(Ppricel );
		        	Pline.add(Pmaxtackl  );
		        	Pline.add( Prarityl );
		        	Pline.add(Pdescriptionl );
		        	Pline.add(Pshortdescriptionl  );
		        	Pline.add( Ptooltipkindl );
		        	Pline.add( Ptwohandedl);
		        	Pline.add(Pinventoryiconl  );
	//Value正则表达式列表增加	        	
		        	Pvalue.add(Pitemname);
		        	Pvalue.add(Pprice );
		        	Pvalue.add(Pmaxtack);
		        	Pvalue.add( Prarity);
		        	Pvalue.add(Pdescription );
		        	Pvalue.add(Pshortdescription  );
		        	Pvalue.add( Ptooltipkind );
		        	Pvalue.add( Ptwohanded);
		        	Pvalue.add(Pinventoryicon  );
//		        	System.out.println("=======RegexSet输出结果==============");
//		                    for(int i=0;i<Pline.size();i++){
//		                    	System.out.println("Pline "+Pline.get(i));
//		                    	System.out.println("Pvalue "+Pvalue.get(i));
//		                    }
//      	
//		        	System.out.println("=====================");
//		        	
		    

		}
     
}
