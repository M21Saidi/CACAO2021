package abstraction.fourni;

import java.util.List;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public interface IDistributeurChocolatDeMarque extends IActeur {
	
	/**
	 * @return La liste des chocolats de marque que le distributeur commercialise 
	 * (ce qui ne veut pas dire qu'il en a actuellement dans ses rayons).
	 * Il s'agit de la liste des chocolats que le distributeur s'engage a pouvoir fournir
	 * aux clients : les clients ne viendront acheter que des chocolats figurant dans
	 * cette liste (aucun chocolat ne figurant pas dans cette liste).
	 * Les clients pourront etre decus si il y a des chocolats dans cette liste
	 * pour lesquels la quantite en vente ne repond pas a leurs besoins.
	 * Cette methode n'est appelee qu'une seule fois en debut de simulation (pas de mise a jour/modification ulterieure)  
	 */
	public List<ChocolatDeMarque> getCatalogue();
	
	/**
	 * @param choco, choco!=null
	 * @return Le prix actuel d'un Kg de chocolat choco
	 * IMPORTANT : durant une meme etape, la fonction doit toujours retourner la meme valeur pour un chocolat donne.
	 */
	public double prix(ChocolatDeMarque choco);
	
	/**
	 * @param choco, choco!=null
	 * @return Retourne la quantite totale (rayon+tete de gondole) en Kg de chocolat de type choco 
	 * actuellement disponible a la vente (pour un achat immediat --> le distributeur a 
	 * au moins cette quantite en stock)
	 */
	public double quantiteEnVente(ChocolatDeMarque choco);

	/**
	 * @param choco, choco!=null
	 * @return Retourne la quantite en Kg de chocolat de type choco 
	 * actuellement disponible a la vente EN TETE DE GONDOLE (pour un
	 * achat immediat --> le distributeur a au moins cette quantite en stock)
	 * Remarque : La somme des quantites en vente en tete de gondole ne peut 
	 * pas depasser 10% de la somme totale des quantites mises en vente. 
	 */
	public double quantiteEnVenteTG(ChocolatDeMarque choco);
	
	
	/**
	 * Met a jour les donnees du distributeur (dont son stock de chocolat choco) suite
	 * a la vente d'une quantite quantite de chocolat choco.
	 * Lorsque le client appelle cette methode il a deja verse la somme montant correspondant a l'achat
	 * sur le compte du distributeur : le distributeur a recu une notification de la banque ce qui lui permet 
	 * de verifier que la transaction est bien effective.
	 * @param client, le client qui achete 
	 * @param choco, choco!=null
	 * @param quantite, quantite>0.0 et quantite<=quantiteEnVente(choco)
	 * @param montant, le montant correspondant a la transaction que le client a deja verse sur le compte du distributeur
	 */
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant);
	
	/**
	 * Methode appelee par le client final lorsque la quantite en rayon de chocolat choco 
	 * est inferieure a la quantite desiree
	 * @param choco, le chocolat de marque dont la quantite en rayon a ete integralement achetee
	 */
	public void notificationRayonVide(ChocolatDeMarque choco);

}
