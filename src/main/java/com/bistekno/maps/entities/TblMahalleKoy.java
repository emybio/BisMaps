package com.bistekno.maps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "tblmahallekoy")
public class TblMahalleKoy {
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
	
	@Column(name = "mucaviralan")
	private double mucaviralan = 0;
	public double getMucavirAlan() { return this.mucaviralan; }
	public void setMucavirAlan(double mucaviralan) { this.mucaviralan = mucaviralan; }
	
	@Column(name = "yuzolcum")
	private double yuzolcum = 0;
	public double getYuzOlcum() { return this.yuzolcum; }
	public void setYuzOlcum(double yuzolcum) { this.yuzolcum = yuzolcum; }
	
	@Column(name = "ilceid")
	private int ilceid = 0;
	public int getIlceID() { return this.ilceid; }
	public void setIlceID(int ilceid) { this.ilceid = ilceid; }	
	
	public TblMahalleKoy() { }
}
