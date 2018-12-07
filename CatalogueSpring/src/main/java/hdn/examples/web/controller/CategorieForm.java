/**
 * 
 */
package hdn.examples.web.controller;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Auteur HDN
 * Crée le Dec 1, 2018
 *
 * Cette classe permet de ...

 */
public class CategorieForm {
	
	String code;
	
	boolean enCreation;

	@NotEmpty
	String nom;

	/**
	 * 
	 */
	public CategorieForm() {
		super();
	}

	/**
	 * @param code
	 * @param nom
	 */
	public CategorieForm(String code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	

}
