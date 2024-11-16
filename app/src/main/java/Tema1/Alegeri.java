package Tema1;

import java.util.ArrayList;

public class Alegeri extends App {
    private String idAlegeri;
    private String numeAlegeri;
    private int status;
    public ArrayList<Candidat> candidati = new ArrayList<>();


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

}
