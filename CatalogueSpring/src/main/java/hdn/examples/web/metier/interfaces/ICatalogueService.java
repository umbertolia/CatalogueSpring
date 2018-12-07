package hdn.examples.web.metier.interfaces;

import hdn.examples.web.beans.Categorie;
import hdn.examples.web.beans.Produit;
import hdn.examples.web.exception.CatalogueException;

import java.util.List;

/**
 * Auteur HDN
 * Crée le Dec 1, 2018
 *
 * Cette classe permet de ...

 */

public interface ICatalogueService {

	public boolean addCategorie(Categorie categorie);
	
	public void addProduit(Produit produit, Long codeCategorie);
	
	public List<Categorie> listCategories();
	
	public List<Produit> produitsParCat(Long codeCat);
	
	public List<Produit> produitsParDesignation(String designation);
	
	public Categorie getCategorie(Long codeCat);
	
	public Produit getProduit(String ref);
	
	public Produit getProduitParDesignation(String nom);
	
	public void updateProduit(Produit p, Long codeCat);
	
	public void updateCategorie(Categorie cat);
	
	public void deleteProduit(String ref);
	
	public void deleteCategorie(Long codeCat) throws CatalogueException;
	
	public Categorie getCategorie(String nomCat);

	public List<Produit> recupererTousProduits();
}
