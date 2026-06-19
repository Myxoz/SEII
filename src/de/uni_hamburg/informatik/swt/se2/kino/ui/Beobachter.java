package de.uni_hamburg.informatik.swt.se2.kino.ui;

/**
 * Ein Interface, welches Beobachter markiert
 * 
 * @author Laurin
 * @version SoSe 2026
 */
public interface Beobachter 
{
	/**
	 * Diese Metode wird mit jeder Änderung in einer beobachtbaren Klasse aufgerufen.
	 * 
	 * @param beobachter Die beobachtete Klasse, in der die Aenderung stattfand
	 */
	void beachteAenderung(Beobachtbar beobachtbar);
}
