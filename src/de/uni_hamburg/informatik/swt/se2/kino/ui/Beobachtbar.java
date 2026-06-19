package de.uni_hamburg.informatik.swt.se2.kino.ui;

import java.util.HashSet;
import java.util.Set;

/**
 * Beobachter. Mit dieser Klasse können Klassen, die die Klasse implimentieren, beobachtet werden.
 * 
 * @author Laurin
 * @version SoSe 2026
 */
public abstract class Beobachtbar 
{
	private Set<Beobachter> _beobachter;

	public Beobachtbar() {
		_beobachter = new HashSet<Beobachter>();
	}
	/**
	 * Fügt einen Beobachter für diese Klasse hinzu
	 * 
	 * @param beobachter Der Beobachter, der hinzugefügt werden soll
	 * 
	 * @require beobachter != null
	 */
	public void fuegeBeobachterHinzu(Beobachter beobachter) 
	{
		assert beobachter != null: "Beobachter ist null";
		_beobachter.add(beobachter);
	}
	/**
	 * Informiert jeden Beobachter, dass es eine Aenderung gab
	 */
	protected void meldeAenderung() 
	{
		for(Beobachter beobachter: _beobachter)
		{
			beobachter.beachteAenderung(this);
		}
	}
}