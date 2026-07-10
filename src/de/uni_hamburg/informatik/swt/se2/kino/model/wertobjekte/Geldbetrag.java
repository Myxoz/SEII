package de.uni_hamburg.informatik.swt.se2.kino.model.wertobjekte;

import java.util.regex.Pattern;

/**
 * Geldbetrag
 * @author Laurin
 * @version 1.0
 */

public class Geldbetrag implements Comparable<Geldbetrag> 
{
	private final int _euroCent;
	private static Pattern CHECK_REGEX = Pattern.compile("^-?\\d{1,9},\\d{2}$");


	/**
	 * Erstellt einen neuen Geldbetrag
	 * @param str Der Geldbetrag in Cent
	 */
	public Geldbetrag(String str)
	{
		assert CHECK_REGEX.matcher(str).matches(): "Geldbetrag ist nicht valide";
		this._euroCent = Integer.parseInt(str.replaceAll("\\D", "")) * (str.startsWith("-") ? -1 : 1);
	}

	/**
	 * Erstellt einen neuen Geldbetrag
	 * @param geld Der Geldbetrag in Cent
	 */
	public Geldbetrag(int geld)
	{
		this._euroCent = geld;
	}
	
	/**
	 * Gibt diesen Geldbetrag mal den anderen Geldbetrag zurück
	 * @param other Der andere Geldbetrag
	 * @return Den neuen Geldbetrag
	 */
	public Geldbetrag mul(int other) 
	{
		assert ((long)_euroCent) * other == _euroCent * other: "Das Multiplizieren mit diesem Faktor ist nicht möglich";
		return new Geldbetrag(_euroCent * other);
	}
	
	
	/**
	 * Gibt diesen Geldbetrag plus den anderen Geldbetrag zurück
	 * @param other Der andere Geldbetrag
	 * @return Den neuen Geldbetrag
	 */
	public Geldbetrag add(Geldbetrag other) 
	{
		assert ((long) _euroCent) + other._euroCent == _euroCent + other._euroCent: "Das addieren dieser Beträge ist nicht möglich";
		return new Geldbetrag(_euroCent + other._euroCent);
	}
	
	/**
	 * Gibt diesen Geldbetrag minus den anderen Geldbetrag zurück
	 * @param other Der andere Geldbetrag
	 * @return Den neuen Geldbetrag
	 */
	public Geldbetrag sub(Geldbetrag other) 
	{
		return add(other.mul(-1));
	}

	@Override
	public int compareTo(Geldbetrag o) 
	{
		return _euroCent - o._euroCent;
	}
	
	@Override
	public String toString() 
	{
		return String.format("%.2f", _euroCent / 100.0).replace(".", ",");
	}
	
	@Override
	public boolean equals(Object other)
	{
		return other instanceof Geldbetrag o ? o._euroCent == _euroCent : false;
	}
}