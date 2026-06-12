package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.wertobjekte.Kundennummer;
/**
 * Testklasse für den MedienComparator
 * 
 * @author Lino & Laurin
 * 
 */
public class VormerkKarteTest {
    @Test
    public void testVormerkenNurEinmalErfolgreich()
    {
    	Kunde kunde = new Kunde(new Kundennummer(111111), "Laurin", "Sperling");
    	VormerkKarte karte = new VormerkKarte();
    	karte.merkeVor(kunde);
    	assertFalse(karte.merkeVor(kunde));
    	assertTrue(karte.istVorgemerkt(kunde));
    	assertTrue(karte.entferneKunden(kunde));
    	assertFalse(karte.entferneKunden(kunde));
    }
}