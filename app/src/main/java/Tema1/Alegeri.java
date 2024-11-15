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

    public Alegeri(String idAlegeri, String numeAlegeri) {
        for (int i = 0;  i < oldAlegeriIDs.size(); i++) {
            if (oldAlegeriIDs.get(i).equals(idAlegeri)) {
                System.out.println("EROARE: Deja exista alegeri cu id " + idAlegeri);
                return;
            }
        }
        oldAlegeriIDs.add(idAlegeri);
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        System.out.println("S-au creat alegerile " + numeAlegeri);
    }

}
