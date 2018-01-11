package netdisc.entity;

/**
 * Cltype entity. @author MyEclipse Persistence Tools
 */

public class Cltype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String imgPath;

	// Constructors

	/** default constructor 
	 * @param string */

	/** minimal constructor */
	public Cltype(String title) {
		this.title = title;
	}
	// Property accessors

	public Cltype() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}