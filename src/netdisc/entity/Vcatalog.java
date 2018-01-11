package netdisc.entity;

import java.util.Date;

import netdisc.util.PlaySupport;

/**
 * Vcatalog entity. @author MyEclipse Persistence Tools
 */

public class Vcatalog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String md5;
	private Cltype cltype;
	private Integer uid;
	private String title;
	private Double size;
	private Date altertime;
	private Integer preid;
	private Integer layer;

	// Constructors

	/** default constructor */
	public Vcatalog() {
	}

	/** minimal constructor */
	public Vcatalog(String md5, Cltype cltype, Integer uid, String title,
			Date altertime, Integer layer) {
		this.md5=md5;
		this.cltype = cltype;
		this.uid=uid;
		this.title = title;
		this.altertime = altertime;
		this.layer = layer;
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cltype getCltype() {
		return this.cltype;
	}

	public void setCltype(Cltype cltype) {
		this.cltype = cltype;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getSize() {
		return this.size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getAltertime() {
		return this.altertime;
	}

	public void setAltertime(Date altertime) {
		this.altertime = altertime;
	}

	public Integer getPreid() {
		return this.preid;
	}

	public void setPreid(Integer preid) {
		this.preid = preid;
	}

	public Integer getLayer() {
		return this.layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Vcatalog [id=" + id + ", md5=" + md5 + ", cltype=" + cltype
				+ ", uid=" + uid + ", title=" + title + ", size=" + size
				+ ", altertime=" + altertime + ", preid=" + preid + ", layer="
				+ layer + "]";
	}
	
	public String getTrans(){
		String str="";
		if(this.size!=null){
			if(this.size<1024){
				str=this.size+"B";
			}else if(this.size<1024*1024){
				str=Math.floor(this.size/1024)+"KB";
			}else if(this.size<1024*1024*1024){
				str=Math.floor(this.size/1024/1024)+"MB";
			}else{
				str=Math.floor(this.size/1024/1024/1024)+"GB";
			}
		}
		return str;
	}
	
	public boolean getSupport(){
		return PlaySupport.isSupport(this.title);
	}
}