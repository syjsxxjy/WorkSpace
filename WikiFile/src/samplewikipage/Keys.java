package samplewikipage;

import java.util.Map;

public class Keys {
	/*
	 * ������������ķ��룬��������
	 */
	public String inventoryIconpath;//ԭ��ϷͼƬ·����ԭ���п��ܴ���������������Ҫ���н�һ���Ĵ���
	public String wikiinventoryIconpath; //wikiͼƬ���·��,ֻ���ļ���������·����·����filecopyʱ���� Ϊÿһ����ƷID����һ��������·������ļ�  
	public StringBuilder originaltxt;   //ԭ�������������趨����Fileread��ȡ�ļ�֮���趨
	public String itemid;//��ƷID�������ļ����ݹؼ���key��ͬ����wikipage���趨
	public String cnname;//��Ʒ��������������Ϊ���ַ���ͬʱ��Ϊ�ļ���ǰ����ʹ�ã�����key��ͬ����wikipage���趨
	public String cndescription;   //��Ʒ��������������Ϊ���ַ�
	////////mod����Ʒ�����������ƷӢ�����ƣ���Ϊmod����Ʒû��shortdescription���ԣ�������modname������
	public String enname;
	
	/*
	 * �������������ķ���
	 */
	public String tooltipKindcn;
	public String raritycn;
	public String categorycn; 
	public String racecn;
	public String twoHandedcn;
	public String walkWhileFiring;
	public String printablecn;
	public String ceilingcn;
	
   /*
 *  ���봦��ִ�в��ֲ��������滻
     */
	public  void TranslationReplace(Map<String, String> map){
		//ϡ�г̶�rarity
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("Common")){
			    	 raritycn="��ͨ";
			    	 
			     }
		     }
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("legendary")){
			    	 raritycn="����";
			    	 
			     }
		     }
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("Rare")){
			    	 raritycn="�ǳ�ϡ��";
			    	 
			     }
		     }
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("Uncommon")){
			    	 raritycn="ϡ��";
			    	 
			     }
		     }
		 //����race
		     if(map.get("race")!=null){
		    	 if(map.get("race").replace(" ", "").equalsIgnoreCase("human")){
			    	 racecn="����";
			     }
		     }
		   //��Ʒ����category  other trap
		     if(map.get("category")!=null){
		    	 if(map.get("category").replace(" ", "").equalsIgnoreCase("other")){
		    		 categorycn="����";
			     }
		     }
		     if(map.get("category")!=null){
		    	 if(map.get("category").replace(" ", "").equalsIgnoreCase("decorative")){
		    		 categorycn="װ��Ʒ";
			     }
		     }
		     if(map.get("category")!=null){
		    	 if(map.get("category").replace(" ", "").equalsIgnoreCase("trap")){
		    		 categorycn="����";
			     }
		     }
		     //������ʾ����tooltipKind
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("armor")){
		    		 tooltipKindcn="����";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("gun")){
		    		 tooltipKindcn="ǹ֧";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("bow")){
		    		 tooltipKindcn="��";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("tool")){
		    		 tooltipKindcn="����";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("shield")){
		    		 tooltipKindcn="��";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("sword")){
		    		 tooltipKindcn="��";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("container")){
		    		 tooltipKindcn="����";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("fistweapon")){
		    		 tooltipKindcn="��̨";
			     }
		     }
		     //˫��װ��twoHanded
		     if(map.get("twoHanded")!=null){
		    	 if(map.get("twoHanded").replace(" ", "").equalsIgnoreCase("false")){
		    		 twoHandedcn="��";
			     }
		     }
		     if(map.get("twoHanded")!=null){
		    	 if(map.get("twoHanded").replace(" ", "").equalsIgnoreCase("true")){
		    		 twoHandedcn="��";
			     }
		     }
		     //���ʱ����walkWhileFiring
		     if(map.get("walkWhileFiring")!=null){
		    	 if(map.get("walkWhileFiring").replace(" ", "").equalsIgnoreCase("true")){
		    		 walkWhileFiring="��";
			     }
		     }
		     if(map.get("walkWhileFiring")!=null){
		    	 if(map.get("walkWhileFiring").replace(" ", "").equalsIgnoreCase("false")){
		    		 walkWhileFiring="��";
			     }
		     }
		     //�ܷ��ӡprintable
		     if(map.get("printable")!=null){
		    	 if(map.get("printable").replace(" ", "").equalsIgnoreCase("true")){
		    		 printablecn="��";
			     }
		     }
		     if(map.get("printable")!=null){
		    	 if(map.get("printable").replace(" ", "").equalsIgnoreCase("false")){
		    		 printablecn="��";
			     }
		     }
		     //�Ƿ�����ceiling
		     if(map.get("ceiling")!=null){
		    	 if(map.get("ceiling").replace(" ", "").equalsIgnoreCase("true")){
		    		 ceilingcn="��";
			     }
		     }
		     if(map.get("ceiling")!=null){
		    	 if(map.get("ceiling").replace(" ", "").equalsIgnoreCase("false")){
		    		 ceilingcn="��";
			     }
		     }
		     
		     
	}
	

	

}
