package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

/**
 * Eine Vormerkkarte ist eine Liste, die alle Vormerkungen von einem bestimmten Medium
 * durch Kunden dokumentiert und kontrolliert.
 * 
 * @author Lino & Laurin
 * @version 06.2026
 */
public class VormerkKarte
{
    private Kunde[] _vorgemerkte;

    /**
     * Erzeugt Vormerkkarte ohne vorgemerkte Kunden.
     */
    public VormerkKarte()
    {
        _vorgemerkte = new Kunde[3];
    }

    /**
     * Prüft ob Vormerken möglich ist.
     * 
     * @return Ob Vormerken möglich ist
     */
    public boolean istVormerkenMöglich()
    {
        return _vorgemerkte[2] == null;
    }

    /**
     * Prüft ob von einem Kunden ausgeliehen werden darf.
     * 
     * @param kunde Der Kunde
     * @return Ob ausgeliehen werden darf
     */
    public boolean darfAusleihen(Kunde kunde)
    {
        return _vorgemerkte[0] == null || _vorgemerkte[0] == kunde;
    }

    private void nachrucken()
    {
        Kunde[] copy = new Kunde[3];
        int index = 0;

        for (int i = 0; i < 3; i++)
        {
            if (_vorgemerkte[i] != null)
            {
                copy[index] = _vorgemerkte[i];
                index++;
            }
        }

        _vorgemerkte = copy;
    }

    /**
     * Prüft ob das Medium von einem Kunden vorgemerkt ist.
     * 
     * @param kunde Der Kunde
     * @return Ob vorgemerkt ist
     */
    public boolean istVorgemerkt(Kunde kunde)
    {
        for (Kunde k : _vorgemerkte)
        {
            if (kunde.equals(k))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Entfernt die Vormerkung vom Kunden fürs Medium.
     * 
     * @param kunde Der Kunde
     * @return Ob ein Kunde entfernt wurde
     */
    public boolean entferneKunden(Kunde kunde)
    {
        for (int i = 0; i < 3; i++)
        {
            if (kunde.equals(_vorgemerkte[i]))
            {
                _vorgemerkte[i] = null;
                nachrucken();
                return true;
            }
        }
        return false;
    }

    /**
     * Merkt ein Medium für einen Kunden vor.
     * 
     * @param kunde Der Kunde
     * @return Ob für den Kunden vorgemerkt wurde
     */
    public boolean merkeVor(Kunde kunde)
    {
        for (int i = 0; i < 3; i++)
        {
            if (_vorgemerkte[i] == null)
            {
                _vorgemerkte[i] = kunde;
                nachrucken();
                return true;
            }
        }
        return false;
    }
}