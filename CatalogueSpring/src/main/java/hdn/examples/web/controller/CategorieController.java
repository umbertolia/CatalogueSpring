/**
 * 
 */
package hdn.examples.web.controller;

import hdn.examples.web.beans.Categorie;
import hdn.examples.web.exception.CatalogueException;
import hdn.examples.web.metier.interfaces.ICatalogueService;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Auteur HDN
 * Crée le Dec 5, 2018
 *
 * Cette classe permet de ...

 */
@Controller
public class CategorieController {

	@Autowired
	private ICatalogueService service;
	

	@RequestMapping(method = RequestMethod.GET, value = "/categories")
	public String afficher(ModelMap pModel)  {
		final List<Categorie> listeCategories = service.listCategories();
		pModel.addAttribute("listeCategories", listeCategories);
		

		return "listeCategories";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/ajouterCategorie")
	public String ajouterCategorie(final ModelMap pModel) {
		final List<Categorie> listeCategories = service.listCategories();

		pModel.addAttribute("listeCategories", listeCategories);
		
		CategorieForm categorieForm = (CategorieForm) pModel.get("categorieFormModel");
		if (categorieForm == null) {
			categorieForm = new CategorieForm();
			categorieForm.setEnCreation(true);
			pModel.addAttribute("categorieFormModel", categorieForm);
		}
		
		return "ajoutCategorie";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/creerCategorie")
	public String creerCategorie(
			@Valid @ModelAttribute(value = "categorieFormModel") final CategorieForm categorieForm,
			final BindingResult pBindingResult, final ModelMap pModel) {

		if (!pBindingResult.hasErrors()) {
			Categorie categorie = new Categorie();
			categorie.setNomCategorie(categorieForm.getNom());
			service.addCategorie(categorie);
		}
		return ajouterCategorie(pModel);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supprimerCategorie")
   public String supprimerCategorie(@Valid @ModelAttribute(value = "categorieFormModel") final CategorieForm categorieForm, final ModelMap pModel) throws  CatalogueException {

		service.deleteCategorie(Long.valueOf(categorieForm.getCode()));
		pModel.addAttribute("categorieFormModel", null);
		return ajouterCategorie(pModel);
   }
	
	@RequestMapping(method = RequestMethod.GET, value = "/recupererCategorie")
   public String recupererInfosCategorie(@RequestParam(value="idCat") final String pCodeCategorie, final ModelMap pModel) {

		Categorie categorie = service.getCategorie(pCodeCategorie);
		
		CategorieForm categorieForm = new CategorieForm();
		categorieForm.setCode(String.valueOf(categorie.getCodeCategorie()));
		categorieForm.setNom(categorie.getNomCategorie());
		pModel.addAttribute("categorieFormModel", categorieForm);
		return ajouterCategorie(pModel);
   }
	
	@RequestMapping(method = RequestMethod.POST, value = "/modifierCategorie")
   public String modifierCategorie(
			@Valid @ModelAttribute(value = "categorieFormModel") final CategorieForm categorieForm,
			final BindingResult pBindingResult, final ModelMap pModel) {

		if (!pBindingResult.hasErrors()) {
			Categorie categorie = new Categorie();
			categorie.setCodeCategorie(Long.valueOf(categorieForm.getCode()));
			categorie.setNomCategorie(categorieForm.getNom());
			service.updateCategorie(categorie);
			return annuler(pModel);
		}
		else {
			return ajouterCategorie(pModel);
		}
   }
	
	@RequestMapping(value = "/annulerOperationCategorie")
   public String annuler(final ModelMap pModel) {

		pModel.addAttribute("categorieFormModel", null);
		return ajouterCategorie(pModel);
   }
	
	
	@ExceptionHandler(CatalogueException.class)
	public ModelAndView  handleCatalogueException(CatalogueException catalogueException) {

		String messageErreur = null;
		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("locale.xml");
		}
		catch (BeansException beansException) {
			messageErreur = "Impossible de récupérer locale.xml";
		}
		try {
			messageErreur = context.getMessage(catalogueException.getCode(), null,
					Locale.FRENCH);
			
		} catch (NoSuchMessageException noSuchMessageException) {
			messageErreur = "Erreur inconnue";

		} catch (Exception exception) {
			messageErreur = "Erreur inconnue";
		}
	
		catalogueException.setMessage(messageErreur);
		
		ModelAndView model = new ModelAndView();     
		    model.addObject("exception", catalogueException);
		    model.setViewName("error_pages/error");
		    return model;
	}

	
	
}
