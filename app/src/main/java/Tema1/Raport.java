package Tema1;

import java.util.ArrayList;
import java.util.Collections;

// clasa care se ocupa de rapoartele care trebuie generate
// poate genera si raporturi per circumscriptie, dar si la nivel national
public class Raport extends App {
    public ArrayList<RaportCandidat> rapoarte = new ArrayList<>();
    String numeCircumscriptie;
    String numeRegiune;

    // constructor fara parametrii pentru clasele care mostenesc aceasta clasa
    public Raport() {}

    // constructor cu un singur argument, pentru rapoartele nationale
    public Raport(Alegeri alegere) {
        Votant votant = null;

        for (int i = 0; i < alegere.votanti.size(); i++) {
            // cautam votantii cu vot valid
            votant = alegere.votanti.get(i);
            if (votant.getVot() == null) {
                continue;
            }

            // gasim circumscriptia in care se regasesc
            for (int j = 0; j < alegere.candidati.size(); j++) {
                // gasim candidatii din circumscriptii
                Candidat candidat = alegere.candidati.get(j);
                RaportCandidat aux = null;

                for (int k = 0; k < rapoarte.size(); k++) {
                    // cautam raportul candidatilor in lista de rapoarte
                    if (rapoarte.get(k).getCNP().equals(candidat.getCNP())) {
                        aux = rapoarte.get(k);
                        break;
                    }
                }

                // daca e nevoie, creem un raport nou pentru candidatul nou gasit
                if (aux == null) {
                    aux = new RaportCandidat(candidat.getCNP(), candidat.getVarsta(), candidat.getNume());
                    rapoarte.add(aux);
                }

                // verificam ca votantul curent sa fi si votat pentru candidatul respectiv
                if (votant.getVot().CNPCandidat.equals(aux.getCNP())) {
                    aux.voturi += 1;
                }
            }
        }
    }

    // constructor cu 2 parametrii pentru rapoarte generate intr-o singura circumscriptie
    public Raport(Alegeri alegere, String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
        // gasim circumscriptia respectiva
        if (alegere.findCircumscriptie(numeCircumscriptie) == null) {
            return;
        }
        Votant votant = null;

        for (int i = 0; i < alegere.votanti.size(); i++) {
            // cautam votantii cu vot valid
            votant = alegere.votanti.get(i);
            if (votant.getVot() == null) {
                continue;
            }
            // gasim circumscriptia in care se regasesc
            if (votant.numeCircumscriptie.equals(numeCircumscriptie)) {
                for (int j = 0; j < alegere.candidati.size(); j++) {
                    // gasim candidatii din circumscriptii
                    Candidat candidat = alegere.candidati.get(j);
                    RaportCandidat aux = null;

                    for (int k = 0; k < rapoarte.size(); k++) {
                        if (rapoarte.get(k).getCNP().equals(candidat.getCNP())) {
                            // cautam raportul candidatilor in lista de rapoarte
                            aux = rapoarte.get(k);
                            break;
                        }
                    }

                    if (aux == null) {
                        // daca e nevoie, creem un raport nou pentru candidatul nou gasit
                        aux = new RaportCandidat(candidat.getCNP(), candidat.getVarsta(), candidat.getNume());
                        rapoarte.add(aux);
                    }

                    // verificam ca votantul curent sa fi si votat pentru candidatul respectiv
                    if (votant.getVot().CNPCandidat.equals(candidat.getCNP())) {
                        aux.voturi += 1;
                    }
                }
            }
        }
    }

    // constructor cu 3 parametrii, folosit pentru a genera rapoarte pe regiuni
    // al 3lea parametru are scopul de a schimba semnatura metodei intr-un mod semi-fortat
    public Raport(Alegeri alegere, String numeRegiune, int nothing) {
        this.numeRegiune = numeRegiune;

        Votant votant = null;

        for (int i = 0; i < alegere.votanti.size(); i++) {
            // cautam votantii cu vot valid
            votant = alegere.votanti.get(i);
            if (votant.getVot() == null) {
                continue;
            }
            // gasim candidatii din regiune
            if (votant.numeRegiune.equals(numeRegiune)) {
                for (int j = 0; j < alegere.candidati.size(); j++) {
                    Candidat candidat = alegere.candidati.get(j);
                    RaportCandidat aux = null;

                    for (int k = 0; k < rapoarte.size(); k++) {
                        // cautam raportul candidatilor in lista de rapoarte
                        if (rapoarte.get(k).getCNP().equals(candidat.getCNP())) {
                            aux = rapoarte.get(k);
                            break;
                        }
                    }

                    if (aux == null) {
                        // daca e nevoie, creem un raport nou pentru candidatul nou gasit
                        aux = new RaportCandidat(candidat.getCNP(), candidat.getVarsta(), candidat.getNume());
                        rapoarte.add(aux);
                    }

                    // verificam ca votantul curent sa fi si votat pentru candidatul respectiv
                    if (votant.getVot().CNPCandidat.equals(candidat.getCNP())) {
                        aux.voturi += 1;
                    }
                }
            }
        }
    }

    // modificam metoda toString ca sa faciliteze printarea raportului
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

// clasa pentru raportul individual al fiecarui candidat
class RaportCandidat extends Persoana implements Comparable {
    int voturi;

    // constructor ce apeleaza constructorul mostenit cu parametrii al clasei Persoana
    public RaportCandidat(String CNP, int varsta, String nume) {
        super(CNP, varsta, nume);
    }

    // suprascriem metoda toString ca sa facilitam printarea rapoartelor
    public String toString() {
        return getNume() + " " + getCNP() + " - " + voturi;
    }

    // implementam metoda clasei comparable ca sa putem sa sortam rapoartele candidatilor
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