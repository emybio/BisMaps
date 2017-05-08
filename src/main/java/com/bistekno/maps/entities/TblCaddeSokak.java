package com.bistekno.maps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tblcaddesokak")
public class TblCaddeSokak {
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private int id = 0;
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	@Column(name = "ad")
	private String ad = "";
	public String getAd() { return this.ad; }
	public void setAd(String ad) { this.ad = ad; }
	
	@Column(name = "yuzolcum")
	private double yuzolcum = 0;
	public double getYuzOlcum() { return this.yuzolcum; }
	public void setYuzOlcum(double yuzolcum) { this.yuzolcum = yuzolcum; }
	
	@Column(name = "mahallekoyid")
	private int mahallekoyid = 0;
	public int getMahalleKoyID() { return this.mahallekoyid; }
	public void setMahalleKoyID(int mahallekoyid) { this.mahallekoyid = mahallekoyid; }	
	
	public TblCaddeSokak() { }
}
