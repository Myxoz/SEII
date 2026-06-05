package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerken;

import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.medien.Medium;

public interface VormerkService {
    /**
     * Liefert eine Liste der Kunden, die das Medium vorgemerkt haben, 
     * in der Reihenfolge ihrer Vormerkung.
     * 
     * @param medium Das Medium, dessen Vormerker gesucht werden.
     * @return Eine Liste von Kunden. Die Liste ist leer, wenn keine Vormerkungen vorliegen.
     * 
     * @require medium != null
     * @ensure result != null
     */
    List<Kunde> getVormerker(Medium medium);

    /**
     * Merkt ein Medium für einen Kunden vor.
     * 
     * @param kunde Der Kunde, der das Medium vormerken möchte.
     * @param medium Das vorzumerkende Medium.
     * 
     * @require kunde != null
     * @require medium != null
     */
    void merkeVor(Kunde kunde, Medium medium);
}
