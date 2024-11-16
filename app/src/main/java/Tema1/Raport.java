package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Raport extends App {
    public ArrayList<RaportCandidat> rapoarte = new ArrayList<>();
    String numeCircumscriptie;

    public Raport() {}

    public Raport(Alegeri alegere) {
        Votant votant = null;

        for (int i = 0; i < alegere.votanti.size(); i++) {
            votant = alegere.votanti.get(i);
            if (votant.getVot() == null) {
                continue;
            }

            for (int j = 0; j < alegere.candidati.size(); j++) {
                Candidat candidat = alegere.candidati.get(j);
                RaportCandidat aux = null;

                for (int k = 0; k < rapoarte.size(); k++) {
                    if (rapoarte.get(k).getCNP().equals(candidat.getCNP())) {
                        aux = rapoarte.get(k);
                        break;
                    }
                }

                if (aux == null) {
                    aux = new RaportCandidat(candidat.getCNP(), candidat.getVarsta(), candidat.getNume());
                    rapoarte.add(aux);
                }

                if (votant.getVot().CNPCandidat.equals(aux.getCNP())) {
                    aux.voturi += 1;
                }
            }
        }
    }

    public Raport(Alegeri alegere, String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
        if (alegere.findCircumscriptie(numeCircumscriptie) == null) {
            return;
        }
        Votant votant = null;

        for (int i = 0; i < alegere.votanti.size(); i++) {
            votant = alegere.votanti.get(i);
            if (votant.getVot() == null) {
                continue;
            }

            if (votant.numeCircumscriptie.equals(numeCircumscriptie)) {
                for (int j = 0; j < alegere.candidati.size(); j++) {
                    Candidat candidat = alegere.candidati.get(j);
                    RaportCandidat aux = null;

                    for (int k = 0; k < rapoarte.size(); k++) {
                        if (rapoarte.get(k).getCNP().equals(candidat.getCNP())) {
                            aux = rapoarte.get(k);
                            break;
                        }
                    }

                    if (aux == null) {
                        aux = new RaportCandidat(candidat.getCNP(), candidat.getVarsta(), candidat.getNume());
                        rapoarte.add(aux);
                    }

                    if (votant.getVot().CNPCandidat.equals(candidat.getCNP())) {
                        aux.voturi += 1;
                    }
                }
            }
        }
    }

    public String toString() {
        if (this.numeCircumscriptie == null) {
            numeCircumscriptie = "Romania";
        }
        if (rapoarte.size() == 0) {
            return "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
        } else {
            Collections.sort(rapoarte);
            String retval = "Raport voturi " + numeCircumscriptie + ":\n";
            for (int i = 0; i < rapoarte.size(); i++) {
                retval += rapoarte.get(i).toString() + "\n";
            }
            return retval;
        }
    }

}

class RaportCandidat extends Persoana implements Comparable {
    int voturi;

    public RaportCandidat(String CNP, int varsta, String nume) {
        super(CNP, varsta, nume);
    }

    public String toString() {
        return getNume() + " " + getCNP() + " - " + voturi;
    }

    public int compareTo(Object o) {
        RaportCandidat c = (RaportCandidat) o;
        if (this.voturi > c.voturi) {
            return 1;
        } else if (this.voturi < c.voturi) {
            return -1;
        } else if (Long.parseLong(this.getCNP()) > Long.parseLong(c.getCNP())) {
            return 1;
        } else if (Long.parseLong(this.getCNP()) < Long.parseLong(c.getCNP())) {
            return -1;
        }
        return 0;
    }
}

class RaportRegiune extends Raport {
    public String numeRegiune;

    public RaportRegiune() {}
    public RaportRegiune(Alegeri alegere, String numeRegiune) {

    }
}