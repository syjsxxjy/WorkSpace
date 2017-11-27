package samplewikipage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectsPage extends Keys{
	/*
	 * 设定页面主体，拼接字符串和变量，变量内容继承自Keys.java类
	 * ！！图片大小每种类型的设置都不一样，需要测试再设定
	 */
	   public  StringBuilder getPage(Map<String, String> map,Map<String, String> mapcn){//map：英文键值对   mapcn：中文键值对
	    	  //设定itemid
		      itemid=map.get("objectName").replace(" ", "");   System.out.println(itemid);  
		      //设定文件名中文部分
		       if( mapcn.get("shortdescription")!=null){
		    	   cnname=mapcn.get("shortdescription");   //System.out.println(cnname);
	       }else{
	    	   cnname=" ";}
		       //设定中文物品描述，可能为空字符
		       if(mapcn.get("description")!=null){
		    	   cndescription=mapcn.get("description");
		       }else{
		    	   cndescription=""; }
		      //原游戏图片路径参数预处理
		       if(map.get("inventoryIcon")!=null){
		    	   String sReg="(.+.png)";
			       Pattern p = Pattern.compile(sReg);
			       Matcher m = p.matcher(map.get("inventoryIcon"));  
			       while(m.find()){
			    	   System.out.println("图片路径 "+ m.group());
			    	   inventoryIconpath=m.group().replace(" ", "");
			       }
			     //wiki图片存放路径,只有文件名，不带路径，路径在filecopy时创建 为每一个物品ID创建一个单独的路径存放文件  
			       String sReg2="(\\w+.png)";
			       Pattern p2 = Pattern.compile(sReg2);
			       Matcher m2 = p2.matcher(map.get("inventoryIcon"));  
			       while(m2.find()){
			    	   System.out.println("wiki图片路径 "+ m2.group());
			    	   wikiinventoryIconpath=m2.group().replace(" ", "");
			       }	       
		       }else if(map.get("image")!=null){   //inventoryicon=null且image属性不为null时，用image作为图片路径
		    	   String sReg="(.+.png)";
			       Pattern p = Pattern.compile(sReg);
			       Matcher m = p.matcher(map.get("image"));  
			       while(m.find()){
			    	   System.out.println("图片路径 "+ m.group());
			    	   inventoryIconpath=m.group().replace(" ", "");
			       }
			     //wiki图片存放路径,只有文件名，不带路径，路径在filecopy时创建 为每一个物品ID创建一个单独的路径存放文件  
			       String sReg2="(\\w+.png)";
			       Pattern p2 = Pattern.compile(sReg2);
			       Matcher m2 = p2.matcher(map.get("image"));  
			       while(m2.find()){
			    	   System.out.println("wiki图片路径 "+ m2.group());
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
	    	  TranslationReplace(map);   //进行翻译处理
	    	  //页面标题处理
	    	  StringBuilder cnnametitle=new StringBuilder("");
	    	  if (mapcn.get("shortdescription")!=null){
	    		  cnnametitle.append(mapcn.get("shortdescription")+"："); 
	    	  }else{ cnnametitle.append(" ");}
			sb.append("====== "+cnnametitle+""+map.get("shortdescription")+" ======"+"\r\n");
			//↑此处若存在中文名则需要在最后加上冒号
			sb.append("^{{::starbound:"+itemid+":"+wikiinventoryIconpath+"?100|}}|^"+"\r\n");  //这里的数字为图片大小，需要测试之后再设定
			sb.append("^  物品ID：|**"+itemid+"**|"+"\r\n");
			sb.append("^  物品名称：|"+cnname+" "+map.get("shortdescription")+"|"+"\r\n");
			sb.append("^  价格：|"+map.get("price")+"|"+"\r\n");
			sb.append("^  物品描述：|"+cndescription+""+map.get("description")+"|"+"\r\n");
			//以下是可能存在的属性--------------------------------------------
			sb.append("|  稀有程度：|"+raritycn+"|"+"\r\n");
			if (map.containsKey("tooltipKind")){  sb.append("|  工具提示种类：|"+tooltipKindcn+"|"+"\r\n");	}
			if (map.containsKey("colonyTags")){  sb.append("|  殖民地标签：|"+map.get("colonyTags")+"|"+"\r\n");}
			if (map.containsKey("maxStack")){  sb.append("|  最大叠加数量：|"+map.get("maxStack")+"|"+"\r\n");}
			if (map.containsKey("category")){  sb.append("|  物品分类：|"+map.get("category")+"|"+"\r\n");}
			if (map.containsKey("level")){  sb.append("|  等级：|"+map.get("level")+"|"+"\r\n");}
            if (map.containsKey("itemTags")){  sb.append("|  物品标签：|"+map.get("itemTags")+"|"+"\r\n");}
            if (map.containsKey("race")){  sb.append("|  种类：|"+map.get("race")+"|"+"\r\n");}
            if (map.containsKey("weaponType")){  sb.append("|  武器类型：|"+map.get("weaponType")+"|"+"\r\n");}
            if (map.containsKey("power")){  sb.append("|  力量：|"+map.get("power")+"|"+"\r\n");}
            if (map.containsKey("walkWhileFiring")){  sb.append("|  射击时行走：|"+map.get("value")+"|"+"\r\n");}
            if (map.containsKey("speed")){  sb.append("|  速度：|"+map.get("speed")+"|"+"\r\n");}
            if (map.containsKey("life")){  sb.append("|  生命值：|"+map.get("life")+"|"+"\r\n");}
            if (map.containsKey("liquid")){  sb.append("|  液♂体：|"+map.get("liquid")+"|"+"\r\n");}
            if (map.containsKey("kind")){  sb.append("|  类型：|"+map.get("kind")+"|"+"\r\n");}
            if (map.containsKey("shipUpgrade")){  sb.append("|  飞船等级：|"+map.get("shipUpgrade")+"|"+"\r\n");}
            if (map.containsKey("materialId")){  sb.append("|  材质ID：|"+map.get("materialId")+"|"+"\r\n");}
            if (map.containsKey("printable")){  sb.append("|  能否打印：|"+printablecn+"|"+"\r\n");}
            
            
            
            if (map.containsKey(" apexDescription")){sb.append("|  种族描述  ||"+"\r\n");}
            if (map.containsKey(" apexDescription")){sb.append("|  猿人：|\""+mapcn.get("apexDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" avianDescription")){sb.append("|  翼族人：|\""+mapcn.get("avianDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" floranDescription")){sb.append("|  叶族人：|\""+mapcn.get("floranDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" glitchDescription")){sb.append("|  电子人：|\""+mapcn.get("glitchDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" humanDescription")){sb.append("|  人类：|\""+mapcn.get("humanDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" hylotlDescription")){sb.append("|  鲛人：|\""+mapcn.get("hylotlDescription")+"\"|"+"\r\n");}
            if (map.containsKey(" novakidDescription")){sb.append("|  星之子：|\""+mapcn.get("novakidDescription")+"\"|"+"\r\n");}
            
            
            
                	 
                 
			
			//以下插入原文文本
			sb.append("\r\n");
			sb.append("----");
			sb.append("\r\n");sb.append("\r\n");
			sb.append("  "+originaltxt);
			sb.append("  \r\n");
			sb.append("~~DISQUS~~");
	    	  return sb;
	      }
}
