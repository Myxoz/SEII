package de.uni_hamburg.informatik.swt.se2.kino.ui;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.swt.se2.kino.model.wertobjekte.Geldbetrag;

public class BezahlView
{
    private JDialog _dialog;
    private JTextField _eingabeFeld;
    private JButton _okButton;
    private JButton _abbrechenButton;
    private JLabel _preisLabel;
    private JLabel _restbetragLabel;

    public BezahlView(Geldbetrag zuZahlen)
    {
        _dialog = new JDialog((Frame) null, "Zahlung durchführen", true);
        _dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Grid-Layout: 4 Zeilen, 2 Spalten, 10 Pixel Abstand
        JPanel hauptPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Widgets initialisieren und mit Werten füllen
        _preisLabel = new JLabel(zuZahlen.toString() + " €");

        _eingabeFeld = new JTextField(10);
        _restbetragLabel = new JLabel("0,00 €");

        _abbrechenButton = new JButton("Abbrechen");
        _okButton = new JButton("OK");
        _okButton.setEnabled(false);

        hauptPanel.add(new JLabel("Gesamtpreis:"));
        hauptPanel.add(_preisLabel);

        hauptPanel.add(new JLabel("Gegebenes Geld:"));
        hauptPanel.add(_eingabeFeld);

        hauptPanel.add(new JLabel("Restbetrag/Rückgeld:"));
        hauptPanel.add(_restbetragLabel);

        hauptPanel.add(_abbrechenButton);
        hauptPanel.add(_okButton);

        _dialog.add(hauptPanel);
        _dialog.setSize(350, 200);
        _dialog.setLocationRelativeTo(null);
    }

    public void zeigeFenster()
    {
        _dialog.setVisible(true);
    }

    public void schliesseFenster()
    {
        _dialog.dispose();
    }

    public JButton getOkButton()
    {
        return _okButton;
    }

    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    public JTextField getEingabeTextField()
    {
        return _eingabeFeld;
    }

    public JLabel getPreisLabel()
    {
        return _preisLabel;
    }

    public JLabel getRestbetragLabel()
    {
        return _restbetragLabel;
    }
}
