package samplewikipage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectsPage extends Keys{
	/*
	 * �趨ҳ�����壬ƴ���ַ����ͱ������������ݼ̳���Keys.java��
	 * ����ͼƬ��Сÿ�����͵����ö���һ������Ҫ�������趨
	 */
	   public  StringBuilder getPage(Map<String, String> map,Map<String, String> mapcn){//map��Ӣ�ļ�ֵ��   mapcn�����ļ�ֵ��
	    	  //�趨itemid
		      itemid=map.get("objectName").replace(" ", "");   System.out.println(itemid);  
		      //�趨�ļ������Ĳ���
		       if( mapcn.get("shortdescription")!=null){
		    	   cnname=mapcn.get("shortdescription");   //System.out.println(cnname);
	       }else{
	    	   cnname=" ";}
		       //�趨������Ʒ����������Ϊ���ַ�
		       if(mapcn.get("description")!=null){
		    	   cndescription=mapcn.get("description");
		       }else{
		    	   cndescription=""; }
		      //ԭ��ϷͼƬ·������Ԥ����
		       if(map.get("inventoryIcon")!=null){
		    	   String sReg="(.+.png)";
			       Pattern p = Pattern.compile(sReg);
			       Matcher m = p.matcher(map.get("inventoryIcon"));  
			       while(m.find()){
			    	   System.out.println("ͼƬ·�� "+ m.group());
			    	   inventoryIconpath=m.group().replace(" ", "");
			       }
			     //wikiͼƬ���·��,ֻ���ļ���������·����·����filecopyʱ���� Ϊÿһ����ƷID����һ��������·������ļ�  
			       String sReg2="(\\w+.png)";
			       Pattern p2 = Pattern.compile(sReg2);
			       Matcher m2 = p2.matcher(map.get("inventoryIcon"));  
			       while(m2.find()){
			    	   System.out.println("wikiͼƬ·�� "+ m2.group());
			    	   wikiinventoryIconpath=m2.group().replace(" ", "");
			       }	       
		       }else if(map.get("image")!=null){   //inventoryicon=null��image���Բ�Ϊnullʱ����image��ΪͼƬ·��
		    	   String sReg="(.+.png)";
			       Pattern p = Pattern.compile(sReg);
			       Matcher m = p.matcher(map.get("image"));  
			       while(m.find()){
			    	   System.out.println("ͼƬ·�� "+ m.group());
			    	   inventoryIconpath=m.group().replace(" ", "");
			       }
			     //wikiͼƬ���·��,ֻ���ļ���������·����·����filecopyʱ���� Ϊÿһ����ƷID����һ��������·������ļ�  
			       String sReg2="(\\w+.png)";
			       Pattern p2 = Pattern.compile(sReg2);
			       Matcher m2 = p2.matcher(map.get("image"));  
			       while(m2.find()){
			    	   System.out.println("wikiͼƬ·�� "+ m2.group());
			    	   wikiinventoryIconpath=m2.group().replace(" ", "");
			       }
		       }
		       else{
		    	   inventoryIconpath=" ";
		    	   wikiinventoryIconpath=" ";
		       }
		       if(inventoryIconpath==null){
		    	   inventoryIconpath=" ";
		       }
		       if(wikiinventoryIconpath==null){
		    	   wikiinventoryIconpath=" ";
		       }
		       
		      StringBuilder sb =new StringBuilder();
	    	  TranslationReplace(map);   //���з��봦��
	    	  //ҳ����⴦��
	    	  StringBuilder cnnametitle=new StringBuilder("");
	    	  if (mapcn.get("shortdescription")!=null){
	    		  cnnametitle.append(mapcn.get("shortdescription")+"��"); 
	    	  }else{ cnnametitle.append(" ");}
			sb.append("====== "+cnnametitle+""+map.get("shortdescription")+" ======"+"\r\n");
			//���˴�����������������Ҫ��������ð��
			sb.append("^{{::starbound:"+itemid+":"+wikiinventoryIconpath+"?100|}}|^"+"\r\n");  //���������ΪͼƬ��С����Ҫ����֮�����趨
			sb.append("^  ��ƷID��|**"+itemid+"**|"+"\r\n");
			sb.append("^  ��Ʒ���ƣ�|"+cnname+" "+map.get("shortdescription")+"|"+"\r\n");
			sb.append("^  �۸�|"+map.get("price")+"|"+"\r\n");
			sb.append("^  ��Ʒ������|"+cndescription+""+map.get("description")+"|"+"\r\n");
			//�����ǿ��ܴ��ڵ�����--------------------------------------------
			sb.append("|  ϡ�г̶ȣ�|"+raritycn+"|"+"\r\n");
			if (map.containsKey("tooltipKind")){  sb.append("|  ������ʾ���ࣺ|"+tooltipKindcn+"|"+"\r\n");	}
			if (map.containsKey("colonyTags")){  sb.append("|  ֳ��ر�ǩ��|"+map.get("colonyTags")+"|"+"\r\n");}
			if (map.containsKey("maxStack")){  sb.append("|  ������������|"+map.get("maxStack")+"|"+"\r\n");}
			if (map.containsKey("category")){  sb.append("|  ��Ʒ���ࣺ|"+map.get("category")+"|"+"\r\n");}
			if (map.containsKey("level")){  sb.append("|  �ȼ���|"+map.get("level")+"|"+"\r\n");}
            if (map.containsKey("itemTags")){  sb.append("|  ��Ʒ��ǩ��|"+map.get("itemTags")+"|"+"\r\n");}
            if (map.containsKey("race")){  sb.append("|  ���ࣺ|"+map.get("race")+"|"+"\r\n");}
            if (map.containsKey("weaponType")){  sb.append("|  �������ͣ�|"+map.get("weaponType")+"|"+"\r\n");}
            if (map.containsKey("power")){  sb.append("|  ������|"+map.get("power")+"|"+"\r\n");}
            if (map.containsKey("walkWhileFiring")){  sb.append("|  ���ʱ���ߣ�|"+map.get("value")+"|"+"\r\n");}
            if (map.containsKey("speed")){  sb.append("|  �ٶȣ�|"+map.get("speed")+"|"+"\r\n");}
            if (map.containsKey("life")){  sb.append("|  ����ֵ��|"+map.get("life")+"|"+"\r\n");}
            if (map.containsKey("liquid")){  sb.append("|  Һ���壺|"+map.get("liquid")+"|"+"\r\n");}
            if (map.containsKey("kind")){  sb.append("|  ���ͣ�|"+map.get("kind")+"|"+"\r\n");}
            if (map.containsKey("shipUpgrade")){  sb.append("|  �ɴ��ȼ���|"+map.get("shipUpgrade")+"|"+"\r\n");}
            if (map.containsKey("materialId")){  sb.append("|  ����ID��|"+map.get("materialId")+"|"+"\r\n");}
            if (map.containsKey("printable")){  sb.append("|  �ܷ��ӡ��|"+printablecn+"|"+"\r\n");}
            
            
            
            if (map.containsKey(" apexDescription")){sb.append("|  ��������  ||"+"\r\n");}
            if (map.containsKey(" apexDescription")){sb.append("|  Գ�ˣ�|\""+mapcn.get("apexDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" avianDescription")){sb.append("|  �����ˣ�|\""+mapcn.get("avianDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" floranDescription")){sb.append("|  Ҷ���ˣ�|\""+mapcn.get("floranDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" glitchDescription")){sb.append("|  �����ˣ�|\""+mapcn.get("glitchDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" humanDescription")){sb.append("|  ���ࣺ|\""+mapcn.get("humanDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" hylotlDescription")){sb.append("|  ���ˣ�|\""+mapcn.get("hylotlDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" novakidDescription")){sb.append("|  ��֮�ӣ�|\""+mapcn.get("novakidDescription")+"\"|"+"\r\n");}
            
            
            
                	 
                 
			
			//���²���ԭ���ı�
			sb.append("\r\n");
			sb.append("----");
			sb.append("\r\n");sb.append("\r\n");
			sb.append("  "+originaltxt);
			sb.append("  \r\n");
			sb.append("~~DISQUS~~");
	    	  return sb;
	      }
}
