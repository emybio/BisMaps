package com.bistekno.maps.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import com.vividsolutions.jts.geom.LineString;

@Entity
@Table(name = "tblharita")
public class TblHarita {
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private int id = 0;
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	@Column(name = "geometry", columnDefinition="Geometry")
	private LineString geometry = null;
	public LineString getGeometry() { return geometry; }
	public void setGeometry(LineString geometry) { this.geometry = geometry; }
	
	@Column(name = "mesafe")
	private double mesafe = 0;
	public double getMesafe() { return this.mesafe; }
	public void setMesafe(double mesafe) { this.mesafe = mesafe; }
	
	@Column(name = "bolge")
	private int bolge = 0;
	public int getBolge() { return this.bolge; }
	public void setBolge(int bolge) { this.bolge = bolge; }	
	
	@Column(name = "ilce")
	private int ilce = 0;
	public int getIlce() { return this.ilce; }
	public void setIlce(int ilce) { this.ilce = ilce; }
	
	@Column(name = "mahallekoy")
	private int mahalleKoy = 0;
	public int getMahalleKoy() { return this.mahalleKoy; }
	public void setMahalleKoy(int mahallekoy) { this.mahalleKoy = mahallekoy; }
	
	@Column(name = "caddesokak")
	private int caddeSokak = 0;
	public int getCaddeSokak() { return this.caddeSokak; }
	public void setCaddeSokak(int caddesokak) { this.caddeSokak = caddesokak; }
	
	@Column(name = "tur")
	private int tur = 0;
	public int getTur() { return this.tur; }
	public void setTur(int tur) { this.tur = tur; }
	
	@Column(name = "tarih")
	private Date tarih = new Date();
	public Date getTarih() { return this.tarih; }
	public void setTarih(Date tarih) { this.tarih = tarih; }
	
	@Column(name = "aciklama")
	private String aciklama = "";
	public String getAciklama() { return this.aciklama; }
	public void setAciklama(String aciklama) { this.aciklama = aciklama; }
	
	@Column(name = "kullanici")
	private int kullanici = 0;
	public int getKullanici() { return this.kullanici; }
	public void setKullanici(int kullanici) { this.kullanici = kullanici; }
	
	public TblHarita() { }
}
