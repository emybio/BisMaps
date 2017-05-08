package com.bistekno.maps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tblilce")
public class TblIlce {
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
	
	@Column(name = "bolgeid")
	private int bolgeid = 0;
	public int getBolgeID() { return this.bolgeid; }
	public void setBolgeID(int bolgeid) { this.bolgeid = bolgeid; }	
	
	public TblIlce() { }
}
