package control;

/**
 * @author YangYZ
 *
 * 2011-11-30
 */
public class PageVO {

	/**
	 * 每页显示的行数,默认为10
	 */
	private int rownumperpage=25;   

	/**
	 * 目前是哪一页 默认为第一页
	 */
	private int currentPage=1;

	/**
	 * 总行数
	 */
	private int totalRownum;

	/**
	 * 总页数
	 */
	private int pageNums;

	/**
	 * 页面显示的第一页(新的分页外观需求) 页面需要显示多个页码数
	 */
	private int startPage = 1;

	/**
	 * 页面显示的翻页的最后个页码
	 */
	private int endPage = 1;
	
	/**
	 * startPage - endPage 的距离 计算翻页时候用
	 */
	private int pageStep = 5;
	
	/**
	 * 获取总页数
	 * 
	 */
	public int getPageNums() {
		return pageNums;
	}

	public int getTotalRownum() {
		return totalRownum;

	}

	public void setTotalRownum(int totalRownum) {
		
		this.totalRownum = totalRownum;

		calcPages();
	}

	public int getCurrentPage() {
		
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	/**
	 * 计算总页数
	 * 
	 */
	//TODO 参数设置有顺序，不好！
	private void calcPages() {
		if (totalRownum <= 0) {
			totalRownum = 0;
			pageNums = 0;
			currentPage = 1;
			return;
		}
		
		if (totalRownum % rownumperpage == 0) {
			pageNums = totalRownum / rownumperpage;  //pageNums 总页数
		} else {
			pageNums = totalRownum / rownumperpage + 1;
		}
		
		if (currentPage > pageNums) {
			currentPage = 1;
		}
		if (pageNums <= pageStep) {
			startPage = 1;
		} else {
			startPage = currentPage - (pageStep / 2);      // 7-(5/2)=2
			if (startPage<=0) {
				startPage = 1;
			}
		}
		endPage = startPage + pageStep - 1;
		if (endPage > pageNums) {
			endPage = pageNums;
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public int getRownumperpage() {
		return rownumperpage;
	}

	public PageVO setRownumperpage(int rownumperpage) {
		this.rownumperpage = rownumperpage;
		
		return this;
	}

	/**
	 * 获取当前页的第一条记录的位置
	 * 
	 */
	public int getFirstResult() {
		return (currentPage - 1) * rownumperpage;    // (1-1)x10=0【数组第一个下标0】  (2-1)x10=10【数组第11个下标10】   
	}
	
	public void setCurrentResult(int cur) {
		int k = cur / rownumperpage + 1;
		this.setCurrentPage(k);
	}

	public int getEndPage() {
		return endPage;
	}

	public void setPageStep(int pageStep) {
		if ((pageStep < 10) && (pageStep > 1)) {
			this.pageStep = pageStep;
		}
	}

	
	
}
