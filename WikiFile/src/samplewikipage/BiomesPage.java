package samplewikipage;

import java.util.Map;

public class BiomesPage extends Keys {
      
	/*
	 * �趨ҳ�����壬ƴ���ַ����ͱ������������ݼ̳���Keys.java��
	 * ����ͼƬ��Сÿ�����͵����ö���һ������Ҫ�������趨
	 */
	   public  StringBuilder getPage(Map<String, String> map,Map<String, String> mapcn){//map��Ӣ�ļ�ֵ��   mapcn�����ļ�ֵ��
	    	  //�趨itemid
		      itemid=map.get("objectName").replace(" ", "");
		      //�趨�ļ������Ĳ���
		      cnname=mapcn.get("shortdescription");
		      StringBuilder sb =new StringBuilder();
	    	  TranslationReplace(map);   //���з��봦��
	    	  System.out.println("raritycn "+raritycn+" racecn"+racecn+" categorycn"+categorycn);
	    	  //ҳ����⴦��
	    	  StringBuilder cnnametitle=new StringBuilder("");
	    	  if (mapcn.get("shortdescription")!=null){
	    		  cnnametitle.append(mapcn.get("shortdescription")+"��"); 
	    	  }
			sb.append("====== "+cnnametitle+""+map.get("shortdescription")+" ======"+"\r\n");
			//���˴�����������������Ҫ��������ð��
			sb.append("^{{::starbound:"+map.get("inventoryIcon").replace(" ", "")+"?100|}}|^"+"\r\n");  //���������ΪͼƬ��С����Ҫ����֮�����趨
			sb.append("^  ��ƷID��|**"+map.get("objectName")+"**|"+"\r\n");
			sb.append("^  ��Ʒ���ƣ�|"+mapcn.get("shortdescription")+" "+map.get("shortdescription")+"|"+"\r\n");
			sb.append("^  �۸�|"+map.get("price")+"|"+"\r\n");
			sb.append("^  ��Ʒ������|"+mapcn.get("description")+""+map.get("description")+"|"+"\r\n");
			//�����ǿ��ܴ��ڵ�����--------------------------------------------
			sb.append("|  ϡ�г̶ȣ�|"+raritycn+"|"+"\r\n");
			sb.append("|  ֳ��ر�ǩ��|"+map.get("colonyTags")+"|"+"\r\n");
			sb.append("|  ���壺|"+racecn+"|"+"\r\n");
			sb.append("|  ��Ʒ���ࣺ|"+categorycn+"|"+"\r\n");
			sb.append("|  ��������  ||"+"\r\n");
			sb.append("|  Գ�ˣ�|\""+mapcn.get("apexDescription")+"\"|"+"\r\n");
			sb.append("|  �����ˣ�|\""+mapcn.get("avianDescription")+"\"|"+"\r\n");
			sb.append("|  Ҷ���ˣ�|\""+mapcn.get("floranDescription")+"\"|"+"\r\n");
			sb.append("|  �����ˣ�|\""+mapcn.get("glitchDescription")+"\"|"+"\r\n");
			sb.append("|  ���ࣺ|\""+mapcn.get("humanDescription")+"\"|"+"\r\n");
			sb.append("|  ���ˣ�|\""+mapcn.get("hylotlDescription")+"\"|"+"\r\n");
			sb.append("|  ��֮�ӣ�|\""+mapcn.get("novakidDescription")+"\"|"+"\r\n");
			

			
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
