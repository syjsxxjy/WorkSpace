package dto;

public class Topic extends IdEntity {
	private String title;
	private String body;
	private long uid;
	private long bid;
	private long replycount;
	private long readcount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public long getReplycount() {
		return replycount;
	}

	public void setReplycount(long replycount) {
		this.replycount = replycount;
	}

	public long getReadcount() {
		return readcount;
	}

	public void setReadcount(long readcount) {
		this.readcount = readcount;
	}

	private int replyCount;
}
