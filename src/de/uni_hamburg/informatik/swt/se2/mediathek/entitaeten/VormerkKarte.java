package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

public class VormerkKarte {
	private Kunde[] _vorgemerkte;
	public VormerkKarte() {
		_vorgemerkte = new Kunde[3];
	}
	
	public boolean istVormerkenMöglich() {
		return _vorgemerkte[2] == null;
	}

	public boolean darfVormerken(Kunde kunde) {
		return _vorgemerkte[0] == null || _vorgemerkte[0] == kunde;
	}
	

	private void nachrucken() {
		Kunde[] copy = new Kunde[3];
		int index = 0;
    
		for (int i = 0; i < 3; i++) {
			if (_vorgemerkte[i] != null) {
				copy[index] = _vorgemerkte[i];
				index++;
			}
		}
    
		_vorgemerkte = copy;
	}

	public boolean istVorgemerkt(Kunde kunde) {
		for(Kunde k: _vorgemerkte) {
			if(kunde.equals(k)) {
				return true;
			}
		}
		return false;
	}

	public boolean entferneKunden(Kunde kunde) {
		for(int i = 0; i < 3; i++) {
			if(kunde.equals(_vorgemerkte[i])) {
				_vorgemerkte[i] = null;
				nachrucken();
				return true;
			}
		}
		return false;
	}

	public boolean merkeVor(Kunde kunde) {
		for(int i = 0; i < 3; i++) {
			if(_vorgemerkte[i] == null) {
				_vorgemerkte[i] = kunde;
				nachrucken();
				return true;
			}
		}
		return false;
	}
}
