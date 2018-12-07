function gereProduit(typeAction) {
	var formProduit =  document.getElementById("formProduit");
	
	if (formProduit != null) {
		formProduit.action = typeAction;
		formProduit.submit();
	}
}

function gereCategorie(typeAction) {
	var formProduit =  document.getElementById("formCategorie");
	
	if (formProduit != null) {
		formProduit.action = typeAction;
		formProduit.submit();
	}
}