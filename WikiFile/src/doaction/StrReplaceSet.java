package doaction;

public class StrReplaceSet {
              String keypre;   String cnkeypre;
             String  valuepre;   String  cnvaluepre;
	   public void replace (String[] strArray){
		   // 英文原文去掉多余部分的键值对匹配方法
		  keypre=strArray[0].replace(" ", "").replace("\"", "");
		   valuepre=strArray[1].replace("\"", "").replace("],","]") .replace(",", " ").replace("\\", "");
	//	System.out.println("strpreplaceset: keypre="+keypre+" valuepre="+valuepre);
		   
	   }
	   
	   public void cnreplace (String[] strArray){
		   // 中文mod去掉多余部分的键值对匹配方法
		  cnkeypre=strArray[2].replace(" ", "").replace("\"", "").replace(",", "").replace("value", "").replace("/", "");
		   cnvaluepre=strArray[3].replace(" ", "").replace(",", " ").replace("}", "").replace("]", "").replace("\"", "");
	   }
}
