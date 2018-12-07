package hdn.examples.web.metier.impl;

import hdn.examples.web.beans.Categorie;
import hdn.examples.web.beans.Produit;
import hdn.examples.web.exception.CatalogueException;
import hdn.examples.web.metier.interfaces.ICatalogueDAO;
import hdn.examples.web.metier.interfaces.ICatalogueService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auteur HDN
 * Crée le Dec 1, 2018
 *
 * Cette classe permet de ...

 */


@Service
public class CatalogueServiceImpl implements ICatalogueService {
	
	@Autowired
	private ICatalogueDAO catalogueDAO;
	
	private Logger logger = Logger.getLogger(CatalogueServiceImpl.class);

	@Transactional
	public boolean addCategorie(Categorie categorie) {
		return catalogueDAO.addCategorie(categorie);
	}

	@Transactional
	public void addProduit(Produit produit, Long codeCategorie) {
		catalogueDAO.addProduit(produit, codeCategorie);
	}

	@Transactional(readOnly = true)
	public List<Categorie> listCategories() {
		return catalogueDAO.listCategories();
	}

	@Transactional(readOnly = true)
	public List<Produit> produitsParCat(Long codeCat) {
		return catalogueDAO.produitsParCat(codeCat);
	}

	@Transactional
	public List<Produit> produitsParDesignation(String designation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Categorie getCategorie(Long codeCat) {
		return catalogueDAO.getCategorie(codeCat);
	}

	@Transactional(readOnly = true)
	public Produit getProduit(String ref) {
		return catalogueDAO.getProduit(ref);
	}

	@Transactional
	public Produit getProduitParDesignation(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void updateProduit(Produit p, Long codeCat) {
		Categorie categorie = this.getCategorie(codeCat);
		p.setCategorie(categorie);
		catalogueDAO.updateProduit(p);

	}

	@Transactional
	public void updateCategorie(Categorie cat) {
		catalogueDAO.updateCategorie(cat);
	}

	@Transactional
	public void deleteProduit(String ref) {
		this.catalogueDAO.deleteProduit(ref);
	}

	@Transactional
	public void deleteCategorie(Long codeCat) throws CatalogueException {
		logger.info("appel du service deleteCategorie <DEBUT>");
		catalogueDAO.deleteCategorie(codeCat);
		logger.info("appel du service deleteCategorie <FIN>");
	}

	@Transactional
	public Categorie getCategorie(String nomCat) {
		try {
			Long refCat = Long.valueOf(nomCat);
			return catalogueDAO.getCategorie(refCat);
		}
		catch (NumberFormatException exception) {
			return null;
		}
		
	}
	
	public List<Produit> recupererTousProduits() {
		List<Produit> produits = new ArrayList<Produit>();
		List<Categorie> listeCategories = this.listCategories();
		for (Categorie categorie : listeCategories) {
			if (!categorie.getProduits().isEmpty()) {
				produits.addAll(categorie.getProduits());
			}
		}
		return produits;
	}

}
