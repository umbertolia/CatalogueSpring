package hdn.examples.web.metier.impl;

import hdn.examples.web.beans.Categorie;
import hdn.examples.web.beans.Produit;
import hdn.examples.web.exception.CatalogueException;
import hdn.examples.web.metier.interfaces.ICatalogueDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


/**
 * Auteur HDN Cr�e le Nov 23, 2018
 *
 * Cette classe permet de ...
 * 
 */

@Repository
public class CatalogueDAOImpl implements ICatalogueDAO {

	@PersistenceContext
	private EntityManager em;
	
	private Logger logger = Logger.getLogger(CatalogueDAOImpl.class);

	
	public void init() {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CATALOG");
		em = entityManagerFactory.createEntityManager();
	}
	
	public boolean  addCategorie(Categorie categorie) {
		boolean ajout = false;
		List<Categorie> listCateg = listCategories();
		if (!listCateg.contains(categorie)) {
			em.persist(categorie);
			ajout = true;
		}
		return ajout;
	}

	public void addProduit(Produit produit, Long codeCategorie) {
		Produit prodDB = getProduit(produit.getReference());
		if (prodDB == null) {
			// ajout
			Categorie categorie = em.find(Categorie.class, codeCategorie);
			if (categorie != null) {
				produit.setCategorie(categorie);
				em.persist(produit);
			}
		}
	}

	public List<Categorie> listCategories() {
		return em.createQuery("select c from Categorie c").getResultList();
	}
	

	public List<Produit> produitsParCat(Long codeCat) {
		Query req = em.createQuery("select p from Produit p where p.categorie.codeCategorie = :code");
		req.setParameter("code", codeCat);
		return req.getResultList();
	}

	public Produit getProduitParDesignation(String nom) {
		Produit prod = null;
		try {
			Query req = em.createQuery("select p from Produit p where p.designation = :nomProd");
			req.setParameter("nomProd", nom);
			prod = (Produit) req.getSingleResult();
		}
		catch (NoResultException noResultException) {
		}
		return prod;
	}

	public List<Produit> produitsParDesignation(String designation) {
		Query req = em.createQuery("select p from Produit p where p.designation like :label");
		req.setParameter("label", "%"+designation+"%");
		return req.getResultList();
	}
	
	public Categorie getCategorie(Long codeCat) {
		return em.find(Categorie.class, codeCat);
	}

	public Produit getProduit(String ref) {
		return em.find(Produit.class, ref);
	}

	public void updateProduit(Produit produit) {
		em.merge(produit);
	}
	
	public void updateCategorie(Categorie cat) {
		em.merge(cat);		
	}

	public void deleteProduit(String ref) {
		Produit produit = getProduit(ref);		
		em.remove(produit);
	}
	
	
	public Categorie getCategorie(String nomCat) {
		Query req = em.createQuery("select c from Categorie c where c.nomCategorie like :x");
		req.setParameter("x", nomCat);
		return (Categorie) req.getSingleResult();		
	}

	
	public void deleteCategorie(Long codeCat) throws CatalogueException {
		Categorie cat = getCategorie(codeCat);
		if (cat != null) {
			logger.info(cat);
			if (cat.getProduits() != null && !cat.getProduits().isEmpty()) {
				throw new CatalogueException("deleteCategorie", "exception.supprimer.categorie", null);
			}
			else {
				em.remove(cat);
			}
		}
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return this.em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	
	
}
