package de.uni_hamburg.informatik.swt.se2.kino.model.wertobjekte;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Testklasse für die Klasse Geldbetrag.
 */
public class GeldbetragTest {

    @Test
    public void testKonstruktorInt() 
    {
        Geldbetrag positiv = new Geldbetrag(100);
        assertEquals("1,00", positiv.toString());

        Geldbetrag nullBetrag = new Geldbetrag(0);
        assertEquals("0,00", nullBetrag.toString());

        Geldbetrag negativ = new Geldbetrag(-550);
        assertEquals("-5,50", negativ.toString());
    }

    @Test
    public void testKonstruktorString() 
    {
        Geldbetrag g1 = new Geldbetrag("10,50");
        assertEquals("10,50", g1.toString());

        Geldbetrag g2 = new Geldbetrag("0,05");
        assertEquals("0,05", g2.toString());

        Geldbetrag g3 = new Geldbetrag("-2,00");
        assertEquals("-2,00", g3.toString());

        // Testet die Validierung des Regex (falls Assertions aktiviert sind)
        assertThrows(AssertionError.class, () -> new Geldbetrag("10"));
        assertThrows(AssertionError.class, () -> new Geldbetrag("10,5"));
        assertThrows(AssertionError.class, () -> new Geldbetrag("abc"));
    }

    @Test
    public void testMul() 
    {
        Geldbetrag basis = new Geldbetrag(100); // 1,00 €
        
        Geldbetrag ergebnisPositiv = basis.mul(5);
        assertEquals("5,00", ergebnisPositiv.toString());

        Geldbetrag ergebnisNull = basis.mul(0);
        assertEquals("0,00", ergebnisNull.toString());

        Geldbetrag ergebnisNegativ = basis.mul(-2);
        assertEquals("-2,00", ergebnisNegativ.toString());

        // Testet den Überlaufsschutz (Overflow) via Assertion
        Geldbetrag grosserBetrag = new Geldbetrag(Integer.MAX_VALUE / 2 + 10);
        assertThrows(AssertionError.class, () -> grosserBetrag.mul(2));
    }

    @Test
    public void testAdd() 
    {
        Geldbetrag g1 = new Geldbetrag(150); // 1,50 €
        Geldbetrag g2 = new Geldbetrag(250); // 2,50 €
        
        Geldbetrag summe = g1.add(g2);
        assertEquals("4,00", summe.toString());

        Geldbetrag gNegativ = new Geldbetrag(-50);
        Geldbetrag summeMitNegativ = g1.add(gNegativ);
        assertEquals("1,00", summeMitNegativ.toString());

        // Testet den Überlaufsschutz (Overflow) via Assertion
        Geldbetrag max = new Geldbetrag(Integer.MAX_VALUE);
        Geldbetrag eins = new Geldbetrag(1);
        assertThrows(AssertionError.class, () -> max.add(eins));
    }

    @Test
    public void testSub() 
    {
        Geldbetrag g1 = new Geldbetrag(500); // 5,00 €
        Geldbetrag g2 = new Geldbetrag(200); // 2,00 €

        Geldbetrag differenz = g1.sub(g2);
        assertEquals("3,00", differenz.toString());

        Geldbetrag differenzNegativ = g2.sub(g1);
        assertEquals("-3,00", differenzNegativ.toString());
    }

    @Test
    public void testCompareTo() 
    {
        Geldbetrag kleiner = new Geldbetrag(100);
        Geldbetrag grosser = new Geldbetrag(200);
        Geldbetrag gleich = new Geldbetrag(100);

        assertTrue(kleiner.compareTo(grosser) < 0);
        assertTrue(grosser.compareTo(kleiner) > 0);
        assertEquals(0, kleiner.compareTo(gleich));
    }

    @Test
    public void testToString() 
    {
        Geldbetrag g1 = new Geldbetrag(12345);
        assertEquals("123,45", g1.toString());

        Geldbetrag g2 = new Geldbetrag(7);
        assertEquals("0,07", g2.toString());

        Geldbetrag g3 = new Geldbetrag(-10);
        assertEquals("-0,10", g3.toString());
    }
}
