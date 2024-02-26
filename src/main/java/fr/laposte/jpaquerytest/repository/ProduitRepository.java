package fr.laposte.jpaquerytest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.laposte.jpaquerytest.model.Categorie;
import fr.laposte.jpaquerytest.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	Produit findByLibelleEquals (String libelleExact);
	
	List<Produit> findByCategorieOrderByCategorie(Categorie categorie);
	
	List<Produit> findByPrixLessThanEqual (int prixMax);

}
