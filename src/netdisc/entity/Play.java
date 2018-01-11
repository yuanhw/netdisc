package netdisc.entity;

import java.util.Date;

public class Play {

	private Integer id;
	private String title;
	private Vtype type;
	private String uname;
	private Date altertime;
	private long soucount;
	private long playcount;
	private String md5;
	
	public Play() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Vtype getType() {
		return type;
	}

	public void setType(Vtype type) {
		this.type = type;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Date getAltertime() {
		return altertime;
	}

	public void setAltertime(Date altertime) {
		this.altertime = altertime;
	}

	public long getSoucount() {
		return soucount;
	}

	public void setSoucount(long soucount) {
		this.soucount = soucount;
	}

	public long getPlaycount() {
		return playcount;
	}

	public void setPlaycount(long playcount) {
		this.playcount = playcount;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Play(String title, Vtype type, String uname, Date altertime,
			String md5) {
		super();
		this.title = title;
		this.type = type;
		this.uname = uname;
		this.altertime = altertime;
		this.md5 = md5;
	}

	
}
