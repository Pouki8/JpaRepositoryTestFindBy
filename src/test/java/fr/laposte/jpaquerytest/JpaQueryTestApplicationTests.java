package fr.laposte.jpaquerytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.hibernate.dialect.function.array.ArrayAggFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.laposte.jpaquerytest.model.Categorie;
import fr.laposte.jpaquerytest.model.Produit;
import fr.laposte.jpaquerytest.repository.ProduitRepository;

@SpringBootTest
class JpaQueryTestApplicationTests {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@BeforeEach
	void cleanAndPopulate() {
		
		produitRepository.deleteAll();
		
		Produit chips = new Produit("Chips", 2.5f, Categorie.ALIMENTAIRE);
		Produit poulet = new Produit("Poulet", 10f, Categorie.ALIMENTAIRE);
		Produit pile = new Produit("Pile", 5.5f, Categorie.BAZAR);
		Produit assiettes = new Produit("Assiettes", 3f, Categorie.BAZAR);
		Produit magazine = new Produit("Magazine", 10.8f, Categorie.LIBRAIRIE);
		Produit bd = new Produit("BD", 15f, Categorie.LIBRAIRIE);
		Produit shampoing = new Produit("Shampoing", 4f, Categorie.HYGIENE);
		Produit deo = new Produit("Deo", 3f, Categorie.HYGIENE);
		
		produitRepository.saveAll(Arrays.asList(chips, poulet, pile, assiettes, magazine, bd, shampoing, deo));
	}

	@Test
	void testFindByLibelleEquals() {
		//soit :
		assertEquals("Chips", produitRepository.findByLibelleEquals("chips").getLibelle());
		//ou :
		assertNotNull(produitRepository.findByLibelleEquals("poulet"));
		// l'inverse :
		assertNull(produitRepository.findByLibelleEquals("cacahuete"));
	}
	
	@Test
	void testFindByCategorieOrderByCategorie() {
		assertEquals(2, produitRepository.findByCategorieOrderByCategorie(Categorie.ALIMENTAIRE).size());
	}
	
	@Test
	void testFindByPrixLessThanEqual() {
		assertEquals(4, produitRepository.findByPrixLessThanEqual(5).size());
	}

}
