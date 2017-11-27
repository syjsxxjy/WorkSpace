package samplewikipage;

import java.util.Map;

public class Keys {
	/*
	 * 特殊参数：中文翻译，中文描述
	 */
	public String inventoryIconpath;//原游戏图片路径，原文中可能带有其它参数，需要进行进一步的处理
	public String wikiinventoryIconpath; //wiki图片存放路径,只有文件名，不带路径，路径在filecopy时创建 为每一个物品ID创建一个单独的路径存放文件  
	public StringBuilder originaltxt;   //原文内容需另外设定，从Fileread读取文件之后设定
	public String itemid;//物品ID，根据文件内容关键字key不同，在wikipage中设定
	public String cnname;//物品的中文名，可以为空字符，同时作为文件名前部分使用，根据key不同，在wikipage中设定
	public String cndescription;   //物品中文描述，可能为空字符
	////////mod类物品特殊参数，物品英文名称，因为mod类物品没有shortdescription属性，所以用modname来代替
	public String enname;
	
	/*
	 * 其它参数的中文翻译
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
 *  翻译处理：执行部分参数中文替换
     */
	public  void TranslationReplace(Map<String, String> map){
		//稀有程度rarity
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("Common")){
			    	 raritycn="普通";
			    	 
			     }
		     }
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("legendary")){
			    	 raritycn="传奇";
			    	 
			     }
		     }
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("Rare")){
			    	 raritycn="非常稀有";
			    	 
			     }
		     }
		     if(map.get("rarity")!=null){
		    	 if(map.get("rarity").replace(" ", "").equalsIgnoreCase("Uncommon")){
			    	 raritycn="稀有";
			    	 
			     }
		     }
		 //种族race
		     if(map.get("race")!=null){
		    	 if(map.get("race").replace(" ", "").equalsIgnoreCase("human")){
			    	 racecn="人族";
			     }
		     }
		   //物品种类category  other trap
		     if(map.get("category")!=null){
		    	 if(map.get("category").replace(" ", "").equalsIgnoreCase("other")){
		    		 categorycn="其它";
			     }
		     }
		     if(map.get("category")!=null){
		    	 if(map.get("category").replace(" ", "").equalsIgnoreCase("decorative")){
		    		 categorycn="装饰品";
			     }
		     }
		     if(map.get("category")!=null){
		    	 if(map.get("category").replace(" ", "").equalsIgnoreCase("trap")){
		    		 categorycn="陷阱";
			     }
		     }
		     //工具提示种类tooltipKind
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("armor")){
		    		 tooltipKindcn="防具";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("gun")){
		    		 tooltipKindcn="枪支";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("bow")){
		    		 tooltipKindcn="弓";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("tool")){
		    		 tooltipKindcn="工具";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("shield")){
		    		 tooltipKindcn="盾";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("sword")){
		    		 tooltipKindcn="剑";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("container")){
		    		 tooltipKindcn="容器";
			     }
		     }
		     if(map.get("tooltipKind")!=null){
		    	 if(map.get("tooltipKind").replace(" ", "").equalsIgnoreCase("fistweapon")){
		    		 tooltipKindcn="炮台";
			     }
		     }
		     //双手装备twoHanded
		     if(map.get("twoHanded")!=null){
		    	 if(map.get("twoHanded").replace(" ", "").equalsIgnoreCase("false")){
		    		 twoHandedcn="否";
			     }
		     }
		     if(map.get("twoHanded")!=null){
		    	 if(map.get("twoHanded").replace(" ", "").equalsIgnoreCase("true")){
		    		 twoHandedcn="是";
			     }
		     }
		     //射击时行走walkWhileFiring
		     if(map.get("walkWhileFiring")!=null){
		    	 if(map.get("walkWhileFiring").replace(" ", "").equalsIgnoreCase("true")){
		    		 walkWhileFiring="是";
			     }
		     }
		     if(map.get("walkWhileFiring")!=null){
		    	 if(map.get("walkWhileFiring").replace(" ", "").equalsIgnoreCase("false")){
		    		 walkWhileFiring="否";
			     }
		     }
		     //能否打印printable
		     if(map.get("printable")!=null){
		    	 if(map.get("printable").replace(" ", "").equalsIgnoreCase("true")){
		    		 printablecn="是";
			     }
		     }
		     if(map.get("printable")!=null){
		    	 if(map.get("printable").replace(" ", "").equalsIgnoreCase("false")){
		    		 printablecn="否";
			     }
		     }
		     //是否悬挂ceiling
		     if(map.get("ceiling")!=null){
		    	 if(map.get("ceiling").replace(" ", "").equalsIgnoreCase("true")){
		    		 ceilingcn="是";
			     }
		     }
		     if(map.get("ceiling")!=null){
		    	 if(map.get("ceiling").replace(" ", "").equalsIgnoreCase("false")){
		    		 ceilingcn="否";
			     }
		     }
		     
		     
	}
	

	

}
