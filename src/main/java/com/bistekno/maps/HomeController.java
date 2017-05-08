package com.bistekno.maps;

import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bistekno.maps.entities.*;
import com.bistekno.maps.util.JPAUtil;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.WKTReader;

@Controller
public class HomeController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		JPAUtil util = new JPAUtil("com.bistekno.maps.jpa");
		EntityManager manager = util.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		TypedQuery<TblBolge> query = manager.createNamedQuery("List", TblBolge.class);
		TypedQuery<TblIlce> queryIlce = manager.createNamedQuery("ListIlce", TblIlce.class);
		TypedQuery<TblCaddeSokak> queryCaddeSokak = manager.createNamedQuery("ListCaddeSokak", TblCaddeSokak.class);
		TypedQuery<TblMahalleKoy> queryMahalleKoy = manager.createNamedQuery("ListMahalleKoy", TblMahalleKoy.class);
		TypedQuery<TblTur> queryTur= manager.createNamedQuery("ListTur", TblTur.class);
		try { model.addAttribute("bolgeler", query.getResultList()); } catch (Throwable e) { }	
		try { model.addAttribute("ilceler", queryIlce.getResultList()); } catch (Throwable e) { }
		try { model.addAttribute("turler", queryTur.getResultList()); } catch (Throwable e) { }
		try { model.addAttribute("mahkoyler", queryMahalleKoy.getResultList()); } catch (Throwable e) { }
		try { model.addAttribute("cadsoklar", queryCaddeSokak.getResultList()); } catch (Throwable e) { }
		transaction.commit();
				
		util.close();
		
		return "home";
	}
	
	@RequestMapping(value = "/ilceler/kaydet", method = RequestMethod.POST)
	@ResponseBody
	public String ilcekaydet(@RequestParam("bolgeId") int bolgeId, @RequestParam("ilceAdi") int ilceAdi) {
		
		
		return "{ \"success\": true }";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		JPAUtil util = new JPAUtil("com.bistekno.maps.jpa");
		EntityManager manager = util.createEntityManager();
		TypedQuery<TblHarita> query = manager.createQuery("SELECT T FROM TBLHARITA T", TblHarita.class);
		
		TblHarita harita = null;		
		try { harita = query.getSingleResult(); } catch (Throwable e) { }
		
		harita.setMesafe(50);
		harita.setBolge(1);
		harita.setIlce(2);
		harita.setMahalleKoy(3);
		harita.setCaddeSokak(4);
		harita.setTarih(new Date());
		harita.setTur(1);
		harita.setAciklama("");
		harita.setKullanici(1);
		
		WKTReader reader = new WKTReader();
		try {
			Geometry geometry = reader.read("LINESTRING (30 10, 10 30, 40 40)");
			
			if (geometry.getGeometryType().equals("LineString")) {
				logger.info("LineString length is {}.", ((LineString)geometry).getLength());
				harita.setGeometry((LineString)geometry);
			}
		} catch (Throwable e) { }
		
		manager.getTransaction().begin();
		try {
			manager.persist(harita);
			manager.getTransaction().commit();
		} catch (Throwable e) { manager.getTransaction().rollback(); }
		manager.close();
		
		util.close();
		
		model.addAttribute("harita", harita);
		
		return "home";
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String json(Locale locale, Model model) {		
		return "{ \"success\": true }";
	}
}
