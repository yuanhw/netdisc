package netdisc.entity;
/**
 * Rfiles entity. @author MyEclipse Persistence Tools
 */

public class Rfiles implements java.io.Serializable {

	// Fields

	private String md5;
	private String alltitle;
	private String filepath;
	private Integer refcount;

	// Constructors

	/** default constructor */
	public Rfiles() {
	}

	/** minimal constructor */
	public Rfiles(String md5, String alltitle, Integer refcount) {
		this.md5 = md5;
		this.alltitle = alltitle;
		this.refcount = refcount;
	}

	/** full constructor */
	public Rfiles(String md5, String alltitle, String filepath,
			Integer refcount) {
		this.md5 = md5;
		this.alltitle = alltitle;
		this.filepath = filepath;
		this.refcount = refcount;
	}

	// Property accessors

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getAlltitle() {
		return this.alltitle;
	}

	public void setAlltitle(String alltitle) {
		this.alltitle = alltitle;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getRefcount() {
		return this.refcount;
	}

	public void setRefcount(Integer refcount) {
		this.refcount = refcount;
	}

}