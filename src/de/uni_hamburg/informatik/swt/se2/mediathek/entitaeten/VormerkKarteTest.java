package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

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
public class VormerkKarteTest
{
    @Test
    public void testVormerkenNurEinmalErfolgreich()
    {
        Kunde kunde = new Kunde(new Kundennummer(111111), "Laurin", "Sperling");
        Kunde kunde1 = new Kunde(new Kundennummer(222222), "Lino", "Paal");
        Kunde kunde2 = new Kunde(new Kundennummer(333333), "Manfred",
                "Dodenhoff");
        VormerkKarte karte = new VormerkKarte();
        karte.merkeVor(kunde);
        assertTrue(karte.istVormerkenMöglich());
        assertTrue(karte.darfAusleihen(kunde));
        assertFalse(karte.merkeVor(kunde));
        assertTrue(karte.istVorgemerkt(kunde));
        karte.merkeVor(kunde1);
        karte.merkeVor(kunde2);
        assertTrue(karte.entferneKunden(kunde));
        assertFalse(karte.entferneKunden(kunde));
    }
}