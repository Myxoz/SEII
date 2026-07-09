package de.uni_hamburg.informatik.swt.se2.kino.ui.platzverkauf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.model.wertobjekte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.kino.ui.BezahlView;

/**
 * Der Controller für den Barzahlvorgang.
 */
class BezahlController
{
    private BezahlView _view;
    private Geldbetrag _zuZahlenderBetrag;
    private Geldbetrag _eingezahlterBetrag;

    /**
     * Initialisiert den BezahlController.
     * 
     * @param gesamtBetrag Der für die Tickets zu zahlende Gesamtbetrag.
     */
    public BezahlController(Geldbetrag gesamtBetrag)
    {
        _zuZahlenderBetrag = gesamtBetrag;
        _view = new BezahlView(_zuZahlenderBetrag);

        registriereUIAktionen();
    }

    /**
     * Macht das Bezahlfenster sichtbar und wartet auf die Eingabe des Users.
     * 
     * @return boolean Ob bezahlt wurde.
     */
    public boolean fuehreZahlungDurch()
    {
        _view.zeigeFenster();

        return _eingezahlterBetrag != null;
    }

    /**
     * Registriert die Listener und leitet sie weiter.
     */
    private void registriereUIAktionen()
    {
        // Klick auf Abbrechen leitet weiter
        _view.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    reagiereAufAbbrechenButton();
                }
            });

        // Klick auf OK leitet weiter
        _view.getOkButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Der eingezahlte Betrag entspricht dem, was im Textfeld steht
                    String eingabeText = _view.getEingabeTextField()
                        .getText()
                        .trim();
                    if (!eingabeText.contains(","))
                    {
                        eingabeText += ",00";
                    }
                    _eingezahlterBetrag = new Geldbetrag(eingabeText);
                    _view.schliesseFenster();
                }
            });

        // Jede Tastatureingabe leitet an "reagiereAufEingabe()" weiter
        _view.getEingabeTextField()
            .getDocument()
            .addDocumentListener(new DocumentListener()
            {
                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    reagiereAufEingabe();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    reagiereAufEingabe();
                }

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                    reagiereAufEingabe();
                }
            });
    }

    /**
     * Reagiert auf die Betragseingabe im Textfeld.
     */
    private void reagiereAufEingabe()
    {
        String eingabeText = _view.getEingabeTextField()
            .getText()
            .trim();

        if (eingabeText.isEmpty())
        {
            _view.getRestbetragLabel()
                .setText("0,00 €");
            _view.getOkButton()
                .setEnabled(false);
            return;
        }

        if (!eingabeText.contains(","))
        {
            eingabeText = eingabeText + ",00";
        }

        try
        {
            Geldbetrag gegebenesGeld = new Geldbetrag(eingabeText);

            if (gegebenesGeld.compareTo(_zuZahlenderBetrag) >= 0)
            {
                Geldbetrag rueckgeld = gegebenesGeld.sub(_zuZahlenderBetrag);
                _view.getRestbetragLabel()
                    .setText(rueckgeld.toString() + " €");
                _view.getOkButton()
                    .setEnabled(true);
            }
            else
            {
                Geldbetrag nochZuZahlen = _zuZahlenderBetrag.sub(gegebenesGeld);
                _view.getRestbetragLabel()
                    .setText("Es fehlen noch: " + nochZuZahlen.toString()
                            + " €");
                _view.getOkButton()
                    .setEnabled(false);
            }
        }
        catch (NumberFormatException | AssertionError e)
        {
            _view.getRestbetragLabel()
                .setText("Ungültiger Betrag!");
            _view.getOkButton()
                .setEnabled(false);
        }
    }

    /**
     * Reagiert auf das Drücken des Abbrechen Buttons.
     */
    private void reagiereAufAbbrechenButton()
    {
        _eingezahlterBetrag = null;
        _view.schliesseFenster();
    }
}