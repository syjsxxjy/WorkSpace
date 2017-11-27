package samplewikipage;

public class DefaultPage {
	private String shortdescriptioncn;
	private String shortdescriptionen;
	private String inventoryIconpath;
	private String itemID;
	private String price;
	private String descriptioncn="";
	private String descriptionen;
	private String maxstack;
	private String rarity;
	private String tooltipkind;
	private String twohanded;
	private Object originaltxt;
	//以下是可能存在的属性----------------------------------------
	private String baseshieldhealth;  //盾
	private String itemtags;
	
	
	
      public  StringBuilder getSample(){
    	  StringBuilder sb =new StringBuilder();
    	  
    	  StringBuilder cnnametitle=new StringBuilder("");
    	  if (shortdescriptioncn!=null){
    		  cnnametitle=new StringBuilder(shortdescriptioncn+"："); 
    	  }
    	 
		sb.append("====== "+cnnametitle+""+shortdescriptionen+" ======"+"\r\n");
		//↑此处若存在中文名则需要在最后加上冒号
		sb.append("^{{::starbound:"+inventoryIconpath+"?300|}}|^"+"\r\n");
		sb.append("^  物品ID：|**"+itemID+"**|"+"\r\n");
		sb.append("^  物品名称：|"+shortdescriptioncn+" "+shortdescriptionen+"|"+"\r\n");
		sb.append("^  价格：|"+price+"|"+"\r\n");
		sb.append("^  物品描述：|"+descriptioncn+" "+descriptionen+"|"+"\r\n");
		sb.append("|  最大叠加：|"+maxstack+"|"+"\r\n");
		sb.append("|  稀有程度：|"+rarity+"|"+"\r\n");
		sb.append("|  物品类型：|"+tooltipkind+"|"+"\r\n");
		sb.append("|  双手装备：|"+twohanded+"|"+"\r\n");
		//以下是可能存在的属性--------------------------------------------
		if(itemtags!=null){
			sb.append(" |  物品标签：|["+itemtags+"]|"+"\r\n");
		}
		
		if(baseshieldhealth!=null){
			sb.append(" |  血量：|"+baseshieldhealth+"|"+"\r\n");
		}
		
		//以下插入原文文本
		
		sb.append("----");
		sb.append("\r\n");sb.append("\r\n");
		sb.append(originaltxt);
		sb.append("\r\n");
		sb.append("~~DISQUS~~");
    	  return sb;
      }


	public String getItemID() {
		return itemID;
	}


	public void setItemID(String itemID) {
		this.itemID = itemID;
	}


	public Object getOriginaltxt() {
		return originaltxt;
	}


	public void setOriginaltxt(Object originaltxt) {
		this.originaltxt = originaltxt;
	}


	public String getShortdescriptioncn() {
		return shortdescriptioncn;
	}


	public void setShortdescriptioncn(String shortdescriptioncn) {
		this.shortdescriptioncn = shortdescriptioncn;
	}


	public String getShortdescriptionen() {
		return shortdescriptionen;
	}


	public void setShortdescriptionen(String shortdescriptionen) {
		this.shortdescriptionen = shortdescriptionen;
	}


	public String getInventoryIconpath() {
		return inventoryIconpath;
	}


	public void setInventoryIconpath(String inventoryIconpath) {
		this.inventoryIconpath = inventoryIconpath;
	}



	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getDescriptioncn() {
		return descriptioncn;
	}


	public void setDescriptioncn(String descriptioncn) {
		this.descriptioncn = descriptioncn;
	}


	public String getDescriptionen() {
		return descriptionen;
	}


	public void setDescriptionen(String descriptionen) {
		this.descriptionen = descriptionen;
	}


	public String getMaxstack() {
		return maxstack;
	}


	public void setMaxstack(String maxstack) {
		this.maxstack = maxstack;
	}


	public String getRarity() {
		return rarity;
	}


	public void setRarity(String rarity) {
		this.rarity = rarity;
	}


	public String getTooltipkind() {
		return tooltipkind;
	}


	public void setTooltipkind(String tooltipkind) {
		this.tooltipkind = tooltipkind;
	}


	public String getTwohanded() {
		return twohanded;
	}


	public void setTwohanded(String twohanded) {
		this.twohanded = twohanded;
	}


	public String getItemtags() {
		return itemtags;
	}


	public void setItemtags(String itemtags) {
		this.itemtags = itemtags;
	}


	public String getBaseshieldhealth() {
		return baseshieldhealth;
	}


	public void setBaseshieldhealth(String baseshieldhealth) {
		this.baseshieldhealth = baseshieldhealth;
	}
}
