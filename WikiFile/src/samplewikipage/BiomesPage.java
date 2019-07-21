package samplewikipage;

import java.util.Map;

public class BiomesPage extends Keys {
      
	/*
	 * 设定页面主体，拼接字符串和变量，变量内容继承自Keys.java类
	 * ！！图片大小每种类型的设置都不一样，需要测试再设定
	 */
	   public  StringBuilder getPage(Map<String, String> map,Map<String, String> mapcn){//map：英文键值对   mapcn：中文键值对
	    	  //设定itemid
		      itemid=map.get("objectName").replace(" ", "");
		      //设定文件名中文部分
		      cnname=mapcn.get("shortdescription");
		      StringBuilder sb =new StringBuilder();
	    	  TranslationReplace(map);   //进行翻译处理
	    	  System.out.println("raritycn "+raritycn+" racecn"+racecn+" categorycn"+categorycn);
	    	  //页面标题处理
	    	  StringBuilder cnnametitle=new StringBuilder("");
	    	  if (mapcn.get("shortdescription")!=null){
	    		  cnnametitle.append(mapcn.get("shortdescription")+"："); 
	    	  }
			sb.append("====== "+cnnametitle+""+map.get("shortdescription")+" ======"+"\r\n");
			//↑此处若存在中文名则需要在最后加上冒号
			sb.append("^{{::starbound:"+map.get("inventoryIcon").replace(" ", "")+"?100|}}|^"+"\r\n");  //这里的数字为图片大小，需要测试之后再设定
			sb.append("^  物品ID：|**"+map.get("objectName")+"**|"+"\r\n");
			sb.append("^  物品名称：|"+mapcn.get("shortdescription")+" "+map.get("shortdescription")+"|"+"\r\n");
			sb.append("^  价格：|"+map.get("price")+"|"+"\r\n");
			sb.append("^  物品描述：|"+mapcn.get("description")+""+map.get("description")+"|"+"\r\n");
			//以下是可能存在的属性--------------------------------------------
			sb.append("|  稀有程度：|"+raritycn+"|"+"\r\n");
			sb.append("|  殖民地标签：|"+map.get("colonyTags")+"|"+"\r\n");
			sb.append("|  种族：|"+racecn+"|"+"\r\n");
			sb.append("|  物品种类：|"+categorycn+"|"+"\r\n");
			sb.append("|  种族描述  ||"+"\r\n");
			sb.append("|  猿人：|\""+mapcn.get("apexDescription")+"\"|"+"\r\n");
			sb.append("|  翼族人：|\""+mapcn.get("avianDescription")+"\"|"+"\r\n");
			sb.append("|  叶族人：|\""+mapcn.get("floranDescription")+"\"|"+"\r\n");
			sb.append("|  电子人：|\""+mapcn.get("glitchDescription")+"\"|"+"\r\n");
			sb.append("|  人类：|\""+mapcn.get("humanDescription")+"\"|"+"\r\n");
			sb.append("|  鲛人：|\""+mapcn.get("hylotlDescription")+"\"|"+"\r\n");
			sb.append("|  星之子：|\""+mapcn.get("novakidDescription")+"\"|"+"\r\n");
			

			
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
