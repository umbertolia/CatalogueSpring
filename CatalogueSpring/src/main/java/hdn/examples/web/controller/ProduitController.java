package hdn.examples.web.controller;

import hdn.examples.web.beans.Categorie;
import hdn.examples.web.beans.Produit;
import hdn.examples.web.exception.CatalogueException;
import hdn.examples.web.metier.interfaces.ICatalogueService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProduitController {

	@Autowired
	private ICatalogueService service;
	

	@RequestMapping(method = RequestMethod.GET, value = "/produits")
	public String afficher(ModelMap pModel)  {
		final List<Produit> listeProduits = service.recupererTousProduits();
		pModel.addAttribute("listeProduits", listeProduits);
		
		try {
			service.deleteCategorie(1L);
		} catch (Exception exception) {
		}
		
		return "listeProduits";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/ajouterProduit")
	public String ajouterProduit(final ModelMap pModel) {
		final List<Produit> listeProduits = service.recupererTousProduits();
		final List<Categorie> listeCategories = service.listCategories();

		pModel.addAttribute("listeProduits", listeProduits);
		pModel.addAttribute("listeCategories", listeCategories);
		
		ProduitForm produitForm = (ProduitForm) pModel.get("produitFormModel");
		if (produitForm == null) {
			produitForm = new ProduitForm();
			produitForm.setEnCreation(true);
			pModel.addAttribute("produitFormModel", produitForm);
		}
		
		return "ajoutProduit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/creerProduit")
	public String creerProduit(
			@Valid @ModelAttribute(value = "produitFormModel") final ProduitForm produitForm,
			final BindingResult pBindingResult, final ModelMap pModel) {

		if (!pBindingResult.hasErrors()) {
			Produit produit = new Produit(produitForm.getRef(),
					produitForm.getLibelle(), Double.valueOf(produitForm.getPrix()),
					Integer.valueOf(produitForm.getQte()));
			service.addProduit(produit,
					Long.valueOf(produitForm.getRefCategorie()));

		}
		return ajouterProduit(pModel);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/supprimerProduit")
   public String supprimerProduit(@RequestParam(value="refProduit") final String pRefProduit, final ModelMap pModel) {

		service.deleteProduit(pRefProduit);
		return ajouterProduit(pModel);
   }
	
	@RequestMapping(method = RequestMethod.GET, value = "/recupererProduit")
   public String recupererInfosProduit(@RequestParam(value="refProduit") final String pRefProduit, final ModelMap pModel) {

		Produit produit = service.getProduit(pRefProduit);
		
		ProduitForm produitForm = new ProduitForm(produit.getReference(), produit.getDesignation(), String.valueOf(produit.getPrix()), String.valueOf(produit.getQuantite()), String.valueOf(produit.getCategorie().getCodeCategorie()));
		pModel.addAttribute("produitFormModel", produitForm);
		return ajouterProduit(pModel);
   }
	
	@RequestMapping(method = RequestMethod.POST, value = "/modifierProduit")
   public String modifierProduit(
			@Valid @ModelAttribute(value = "produitFormModel") final ProduitForm produitForm,
			final BindingResult pBindingResult, final ModelMap pModel) {

		if (!pBindingResult.hasErrors()) {
			Produit produit = new Produit();
			produit.setDesignation(produitForm.getLibelle());
			produit.setPrix(Double.valueOf(produitForm.getPrix()));
			produit.setQuantite(Integer.valueOf(produitForm.getQte()));
			produit.setReference(produitForm.getRef());
			service.updateProduit(produit, Long.valueOf(produitForm.getRefCategorie()));
			return annuler(pModel);
		}
		else {
			return ajouterProduit(pModel);
		}
   }
	
	
	
	@RequestMapping(value = "/annulerOperation")
   public String annuler(final ModelMap pModel) {

		pModel.addAttribute("produitFormModel", null);
		return ajouterProduit(pModel);
   }
	
	
	
}