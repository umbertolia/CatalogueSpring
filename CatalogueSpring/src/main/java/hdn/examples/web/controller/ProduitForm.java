package hdn.examples.web.controller;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.mysql.jdbc.StringUtils;


/**
 * Auteur HDN Crée le Dec 1, 2018
 * 
 * Cette classe permet de ...
 */
public class ProduitForm {
	
	boolean enCreation;

	@Size(min = 4, max = 12)
	String ref;

	@NotEmpty
	String libelle;

	@Pattern(regexp = "^[+]?\\d+(\\.{0,1}(\\d+?))?$")
	String prix;

	@Pattern(regexp = "\\d*")
	String qte;

	
	@NotEmpty
	String refCategorie;
	
	/**
	 * 
	 */
	public ProduitForm() {
		super();
	}

	/**
	 * @param ref
	 * @param libelle
	 * @param prix
	 * @param qte
	 */
	public ProduitForm(String ref, String libelle, String prix, String qte,
			String refCategorie) {
		super();
		this.ref = ref;
		this.libelle = libelle;
		this.prix = prix;
		this.qte = qte;
		this.refCategorie = refCategorie;
	}

	/**
	 * @return the ref
	 */
	public String getRef() {
		return this.ref;
	}

	/**
	 * @param ref
	 *           the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * @param libelle
	 *           the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the prix
	 */
	public String getPrix() {
		if (StringUtils.isNullOrEmpty(prix)) {
			return "0";
		}
		return this.prix;
	}

	/**
	 * @param prix
	 *           the prix to set
	 */
	public void setPrix(String prix) {
		this.prix = prix;
	}

	/**
	 * @return the qte
	 */
	public String getQte() {
		if (StringUtils.isNullOrEmpty(qte)) {
			return "0";
		}
		return this.qte;
	}

	/**
	 * @param qte
	 *           the qte to set
	 */
	public void setQte(String qte) {
		this.qte = qte;
	}

	/**
	 * @return the refCategorie
	 */
	public String getRefCategorie() {
		return this.refCategorie;
	}

	/**
	 * @param refCategorie
	 *           the refCategorie to set
	 */
	public void setRefCategorie(String refCategorie) {
		this.refCategorie = refCategorie;
	}

	/**
	 * @return the enCreation
	 */
	public boolean isEnCreation() {
		return this.enCreation;
	}

	/**
	 * @param enCreation the enCreation to set
	 */
	public void setEnCreation(boolean enCreation) {
		this.enCreation = enCreation;
	}


}
