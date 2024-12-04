package Tema1;

import java.util.ArrayList;

public class Alegeri extends Comanda {
    private String idAlegeri;
    private String numeAlegeri;
    private int status;
    public ArrayList<Candidat> candidati = new ArrayList<>();
    public ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
    public ArrayList<Votant> votanti = new ArrayList<>();

    // constructor gol pentru clasele care mostenesc clasa alegeri
    public Alegeri() {}

    // gettere si settere pentru vafriabilele private ale clasei
    public String getIdAlegeri() {
        return this.idAlegeri;
    }

    public void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }

    public String getNumeAlegeri() {
        return this.numeAlegeri;
    }

    public void setNumeAlegeri(String numeAlegeri) {
        this.numeAlegeri = numeAlegeri;
    }

    public int getStatus() {
        return this.status;
    }

    // functie care ne spune daca exista deja o circumscriptie cu numele dat in lista de circumscriptii
    public Circumscriptie findCircumscriptie(String numeCircumscriptie) {
        for (int i = 0; i < circumscriptii.size(); i++) {
            if (circumscriptii.get(i).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                return circumscriptii.get(i);
            }
        }
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        return null;
    }

    // functie care ne gaseste daca exista deja candidatul cu CNP-ul dat in lista de candidati
    public Candidat findCandidat(String CNP) {
        for (int i = 0; i < candidati.size(); i++) {
            if (candidati.get(i).getCNP().equals(CNP)) {
                return candidati.get(i);
            }
        }
        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);
        return null;
    }

    // funcite care ne spune daca exista deja un votant cu CNP-ul dat in lista de votanti
    public Votant findVotant(String CNP) {
        for (int i = 0; i < votanti.size(); i++) {
            if (votanti.get(i).getCNP().equals(CNP)) {
                return votanti.get(i);
            }
        }
        System.out.println("EROARE: Nu exista un votant cu CNP-ul " + CNP);
        return null;
    }

    // constructor cu parametri
    public Alegeri(String idAlegeri, String numeAlegeri) {
        // verificam daca nu cumva exista deja alegeri cu ID-ul dat
        Alegeri alegere = new Comanda().findAlegere(idAlegeri);
        if (alegere != null) {
            System.out.println("EROARE: Deja exista alegeri cu id " + idAlegeri);
            return;
        }
        // daca nu, putem crea in continuare alegerea respectiva
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        System.out.println("S-au creat alegerile " + numeAlegeri);
        // si putem adauga alegerile in lista de alegeri
        oldAlegeri.add(this);
    }

    // metoda care seteaza alegerea ca fiind pornita
    public void startAlegeri() {
        // avem o variabila care are ca scop doar sa retina starea in care se afla alegerile
        if (status == 0) {
            status = 1;
            System.out.println("Au pornit alegerile " + numeAlegeri);
        } else {
            System.out.println("EROARE: Alegerile deja au inceput.");
        }
    }

    // metoda care seteaza alegerea ca fiind oprita
    public void oprireAlegeri() {
        if (status == 1) {
            status = 0;
            System.out.println("S-au terminat alegerile " + numeAlegeri);
        } else {
            System.out.println("EROARE: Nu este perioada de votare");
        }
    }

    // metoda care adauga un candidat la alegerea curenta
    public void adaugareCandidat(String idAlegeri, String CNP, int varsta, String nume) {
        // verificam ca alegerile sa fie pornite
        if (this.status != 1) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        // verificam ca CNP-ul candidatului sa fie legal
        if (CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        // verificam ca varsta canidatului sa fie legala
        if (varsta < 35) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        // verificam sa nu existe deja un candidat cu CNP-ul dat
        for (int i = 0; i < candidati.size(); i++) {
            if (candidati.get(i).getCNP().equals(CNP)) {
                System.out.println("EROARE: Candidatul " + candidati.get(i).getNume() + " are deja acelasi CNP");
                return;
            }
        }
        // daca totul e bine, creem un nou obiect de tip candidat, cu datele primite
        // si il adaugam in lista de candidati
        Candidat aux = new Candidat(idAlegeri, CNP, varsta, nume);
        candidati.add(aux);
    }

    // functie care elimina un candidat din alegerea curenta
    public void eliminareCandidat(String CNP) {
        // il gasim in lista de candidati si il stergem
        for (int i = 0; i < candidati.size(); i++) {
            if (candidati.get(i).getCNP().equals(CNP)) {
                String numeCandidat = candidati.get(i).getNume();
                candidati.remove(i);
                System.out.println("S-a sters candidatul " + numeCandidat);
                return;
            }
        }
        // daca nu exista in lista de candidati, mentionam asta
        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);

    }

    // metoda care adauga un votant la alegerea curenta
    public void adaugareVotant(String numeCircumscriptie, String CNP, int varsta, boolean indemanare, String nume) {
        // cautam circumscriptia sa
        for (int i = 0; i < circumscriptii.size(); i++) {
            if (circumscriptii.get(i).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                for (int j = 0; j < votanti.size(); j++) {
                    // verificam sa nu fie deja inregistrat pe circumscriptia respectiva
                    if (votanti.get(j).getCNP().equals(CNP)) {
                        System.out.println("EROARE: Votantul " + votanti.get(j).getNume() + " are deja acelasi CNP");
                        return;
                    }
                }
                String numeRegiune = circumscriptii.get(i).getRegiuneCircumscriptie();
                // creem votantul si il adaugam listei de votanti
                Votant retval = new Votant(idAlegeri, numeCircumscriptie, numeRegiune, CNP, varsta, indemanare, nume);
                votanti.add(retval);
                return;
            }
        }
        // aici putem ajunge doar daca nu exista in sesiunea noastra de alegeri o circumscriptie cu numele dat
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        return;

    }

    // suprascriem functia toString ca sa putem printa mai usor alegerile
    public String toString() {
        return idAlegeri + " " + numeAlegeri;
    }

}
