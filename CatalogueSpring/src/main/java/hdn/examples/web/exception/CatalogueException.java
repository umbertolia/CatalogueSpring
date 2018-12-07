/**
 * 
 */
package hdn.examples.web.exception;



/**
 * Auteur HDN
 * Crée le Dec 5, 2018
 *
 * Cette classe permet de ...

 */
public class CatalogueException extends Exception {

	private String nomService;
	
	private String code;
	
	private String message;
	
	
	
	/**
	 * @param nomService
	 * @param code
	 * @param message
	 */
	public CatalogueException(String nomService, String code, String message) {
		super();
		this.nomService = nomService;
		this.code = code;
		this.message = message;
	}
	
	
	public CatalogueException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2012490428250531589L;

	/**
	 * @return the nomService
	 */
	public String getNomService() {
		return this.nomService;
	}

	/**
	 * @param nomService the nomService to set
	 */
	public void setNomService(String nomService) {
		this.nomService = nomService;
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
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
