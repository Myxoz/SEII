package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.medien.Medium;

public class VormerkServiceImpl implements VormerkService {
    /**
     * Diese Map speichert für jedes Medium die Liste der Kunden, 
     * die dieses Medium vorgemerkt haben.
     * TODO kein Liste von Kunden sondern VormerkKarte
     * 
     */
    private Map<Medium, List<Kunde>> _vormerkungen;

    @Override
    public List<Kunde> getVormerker(Medium medium)
    {
        assert mediumImBestand(
                medium) : "Vorbedingung verletzt: mediumImBestand(medium)";

        List<Kunde> vormerker = _vormerkungen.get(medium);

        // Falls noch keine Vormerkungen für das Medium existieren, 
        // leere Liste zurückgeben.
        if (vormerker == null)
        {
            return new ArrayList<Kunde>();
        }

        return new ArrayList<Kunde>(vormerker);
    }

    @Override
    public void merkeVor(Kunde kunde, Medium medium)
    {

        List<Kunde> vormerker = _vormerkungen.get(medium);

        if (vormerker == null)
        {
            vormerker = new ArrayList<Kunde>();
            _vormerkungen.put(medium, vormerker);
        }

        vormerker.add(kunde);

        // 4. Benachrichtige die UI-Komponenten über die Änderung
        informiereUeberAenderung();

    }

}
