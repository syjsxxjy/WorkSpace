package dto;

public class Board extends IdEntity{
          private String boradname;
          private String pid;
          
          
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getBoradname() {
			return boradname;
		}
		public void setBoradname(String boradname) {
			this.boradname = boradname;
		}
}
