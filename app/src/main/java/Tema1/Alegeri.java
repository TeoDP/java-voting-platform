package Tema1;

import java.util.ArrayList;

public class Alegeri extends App {
    private String idAlegeri;
    private String numeAlegeri;
    private int status;


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

}
