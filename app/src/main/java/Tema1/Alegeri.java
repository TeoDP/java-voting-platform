package Tema1;

import java.util.ArrayList;

public class Alegeri extends Comanda {
    private String idAlegeri;
    private String numeAlegeri;
    private int status;
    public ArrayList<Candidat> candidati = new ArrayList<>();
    public ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
    public ArrayList<Votant> votanti = new ArrayList<>();


    public Alegeri() {}

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

    public Circumscriptie findCircumscriptie(String numeCircumscriptie) {
        for (int i = 0; i < circumscriptii.size(); i++) {
            if (circumscriptii.get(i).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                return circumscriptii.get(i);
            }
        }
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        return null;
    }

    public Candidat findCandidat(String CNP) {
        for (int i = 0; i < candidati.size(); i++) {
            if (candidati.get(i).getCNP().equals(CNP)) {
                return candidati.get(i);
            }
        }
        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);
        return null;
    }

    public Votant findVotant(String CNP) {
        for (int i = 0; i < votanti.size(); i++) {
            if (votanti.get(i).getCNP().equals(CNP)) {
                return votanti.get(i);
            }
        }
        System.out.println("EROARE: Nu exista un votant cu CNP-ul " + CNP);
        return null;
    }

    public Alegeri(String idAlegeri, String numeAlegeri) {
        for (int i = 0;  i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).idAlegeri.equals(idAlegeri)) {
                System.out.println("EROARE: Deja exista alegeri cu id " + idAlegeri);
                return;
            }
        }
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        System.out.println("S-au creat alegerile " + numeAlegeri);
        oldAlegeri.add(this);
    }

    public void startAlegeri() {
        if (status == 0) {
            status = 1;
            System.out.println("Au pornit alegerile " + numeAlegeri);
        } else {
            System.out.println("EROARE: Alegerile deja au inceput.");
        }
    }

    public void oprireAlegeri() {
        if (status == 1) {
            status = 0;
            System.out.println("S-au terminat alegerile " + numeAlegeri);
            return;
        } else {
            System.out.println("EROARE: Nu este perioada de votare");
        }
    }

    public void adaugareCandidat(String idAlegeri, String CNP, int varsta, String nume) {
        if (this.status != 1) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        if (CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        if (varsta < 35) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        for (int i = 0; i < candidati.size(); i++) {
            if (candidati.get(i).getCNP().equals(CNP)) {
                System.out.println("EROARE: Candidatul " + candidati.get(i).getNume() + " are deja acelasi CNP");
                return;
            }
        }
        Candidat aux = new Candidat(idAlegeri, CNP, varsta, nume);
        candidati.add(aux);
    }

    public void eliminareCandidat(String CNP) {
        for (int i = 0; i < candidati.size(); i++) {
            if (candidati.get(i).getCNP().equals(CNP)) {
                String numeCandidat = candidati.get(i).getNume();
                candidati.remove(i);
                System.out.println("S-a sters candidatul " + numeCandidat);
                return;
            }
        }
        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);

    }

    public void adaugareVotant(String numeCircumscriptie, String CNP, int varsta, boolean indemanare, String nume) {
        for (int i = 0; i < circumscriptii.size(); i++) {
            if (circumscriptii.get(i).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                for (int j = 0; j < votanti.size(); j++) {
                    if (votanti.get(j).getCNP().equals(CNP)) {
                        System.out.println("EROARE: Votantul " + votanti.get(j).getNume() + " are deja acelasi CNP");
                        return;
                    }
                }
                Votant retval = new Votant(idAlegeri, numeCircumscriptie, CNP, varsta, indemanare, nume);
                votanti.add(retval);
//                circumscriptii.get(i).adaugareVotant(CNP, varsta, indemanare, nume);
                return;
            }
        }
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        return;

    }

    public String toString() {
        return idAlegeri + " " + numeAlegeri;
    }

}
