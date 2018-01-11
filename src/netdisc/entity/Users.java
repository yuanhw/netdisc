package netdisc.entity;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String phone;
	private String password;
	private Double initcapacity;
	private Double usedcapacity;
	private Double account;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String username, String phone, String password) {
		this.username = username;
		this.phone = phone;
		this.password = password;
	}

	/** full constructor */
	public Users(String username, String phone, String password,
			Double initcapacity, Double usedcapacity, Double account) {
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.initcapacity = initcapacity;
		this.usedcapacity = usedcapacity;
		this.account = account;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getInitcapacity() {
		return this.initcapacity;
	}

	public void setInitcapacity(Double initcapacity) {
		this.initcapacity = initcapacity;
	}

	public Double getUsedcapacity() {
		return this.usedcapacity;
	}

	public void setUsedcapacity(Double usedcapacity) {
		this.usedcapacity = usedcapacity;
	}

	public Double getAccount() {
		return this.account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

}