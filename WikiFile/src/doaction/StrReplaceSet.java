package doaction;

public class StrReplaceSet {
              String keypre;   String cnkeypre;
             String  valuepre;   String  cnvaluepre;
	   public void replace (String[] strArray){
		   // Ӣ��ԭ��ȥ�����ಿ�ֵļ�ֵ��ƥ�䷽��
		  keypre=strArray[0].replace(" ", "").replace("\"", "");
		   valuepre=strArray[1].replace("\"", "").replace("],","]") .replace(",", " ").replace("\\", "");
	//	System.out.println("strpreplaceset: keypre="+keypre+" valuepre="+valuepre);
		   
	   }
	   
	   public void cnreplace (String[] strArray){
		   // ����modȥ�����ಿ�ֵļ�ֵ��ƥ�䷽��
		  cnkeypre=strArray[2].replace(" ", "").replace("\"", "").replace(",", "").replace("value", "").replace("/", "");
		   cnvaluepre=strArray[3].replace(" ", "").replace(",", " ").replace("}", "").replace("]", "").replace("\"", "");
	   }
}
