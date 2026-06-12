package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

import static org.junit.Assert.assertArrayEquals;
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
public class VormerkKarteTest
{
	Kunde kunde1;
	Kunde kunde2;
	Kunde kunde3;
	Kunde kunde4;
	public VormerkKarteTest() 
	{
        kunde1 = new Kunde(new Kundennummer(111111), "Laurin", "Sperling");
        kunde2 = new Kunde(new Kundennummer(222222), "Lino", "Paal");
        kunde3 = new Kunde(new Kundennummer(333333), "Manfred", "Dodenhoff");
        kunde4 = new Kunde(new Kundennummer(444444), "Ich", "Bin Dumm");
	}

	@Test
    public void testIstVormerkenMöglich()
    {
		VormerkKarte vormerkKarte = new VormerkKarte();

        assertTrue(vormerkKarte.merkeVor(kunde1));
        assertTrue(vormerkKarte.merkeVor(kunde2));
        assertTrue(vormerkKarte.merkeVor(kunde3));
        assertFalse(vormerkKarte.merkeVor(kunde4));
        assertFalse(vormerkKarte.merkeVor(kunde3));
        assertFalse(vormerkKarte.merkeVor(kunde2));
        assertFalse(vormerkKarte.merkeVor(kunde1));
    }

	@Test
    public void testDarfAusleihen()
    {
		VormerkKarte vormerkKarte = new VormerkKarte();
        assertTrue(vormerkKarte.darfAusleihen(kunde4));
        assertTrue(vormerkKarte.darfAusleihen(kunde1));

        assertTrue(vormerkKarte.merkeVor(kunde1));
        assertTrue(vormerkKarte.merkeVor(kunde2));
        assertTrue(vormerkKarte.merkeVor(kunde3));

        assertTrue(vormerkKarte.darfAusleihen(kunde1));
        assertFalse(vormerkKarte.darfAusleihen(kunde2));
        assertFalse(vormerkKarte.darfAusleihen(kunde3));
    }

	@Test
    public void testIstVorgemerkt()
    {
		VormerkKarte vormerkKarte = new VormerkKarte();
        assertTrue(vormerkKarte.merkeVor(kunde1));
        assertTrue(vormerkKarte.merkeVor(kunde2));
        assertTrue(vormerkKarte.merkeVor(kunde3));
        
        assertTrue(vormerkKarte.istVorgemerkt(kunde1));
        assertTrue(vormerkKarte.istVorgemerkt(kunde2));
        assertTrue(vormerkKarte.istVorgemerkt(kunde3));
        assertFalse(vormerkKarte.istVorgemerkt(kunde4));
    }

	@Test
    public void entferneKunden()
    {
		VormerkKarte vormerkKarte = new VormerkKarte();
        assertTrue(vormerkKarte.merkeVor(kunde1));
        assertTrue(vormerkKarte.merkeVor(kunde2));
        assertTrue(vormerkKarte.merkeVor(kunde3));
        assertTrue(vormerkKarte.entferneKunden(kunde2));
        
        assertTrue(vormerkKarte.istVorgemerkt(kunde1));
        assertFalse(vormerkKarte.istVorgemerkt(kunde2));
        assertTrue(vormerkKarte.istVorgemerkt(kunde3));

        assertTrue(vormerkKarte.entferneKunden(kunde3));
        assertFalse(vormerkKarte.istVorgemerkt(kunde3));
        assertTrue(vormerkKarte.istVorgemerkt(kunde1));
    }

	@Test
    public void testMerkeVor()
    {
		VormerkKarte vormerkKarte = new VormerkKarte();

        assertTrue(vormerkKarte.merkeVor(kunde1));
        assertTrue(vormerkKarte.merkeVor(kunde2));
        assertTrue(vormerkKarte.merkeVor(kunde3));
        
        assertFalse(vormerkKarte.merkeVor(kunde1));
        assertFalse(vormerkKarte.merkeVor(kunde2));
        assertFalse(vormerkKarte.merkeVor(kunde3));
        assertFalse(vormerkKarte.merkeVor(kunde4));
    }

	@Test
    public void testGetVorgemerkte() {
		VormerkKarte vormerkKarte = new VormerkKarte();

        assertTrue(vormerkKarte.merkeVor(kunde1));
        assertTrue(vormerkKarte.merkeVor(kunde2));
        assertTrue(vormerkKarte.merkeVor(kunde3));
        
        assertArrayEquals(new Kunde[]{kunde1, kunde2, kunde3}, vormerkKarte.getVorgemerkte());
        vormerkKarte.entferneKunden(kunde2);
        assertArrayEquals(new Kunde[]{kunde1, kunde3, null}, vormerkKarte.getVorgemerkte());
	}
}