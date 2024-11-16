package Tema1;

import java.util.ArrayList;

public class Circumscriptie extends Alegeri{
    private String numeCircumscriptie;
    private String regiuneCircumscriptie;
    public ArrayList<Votant> votanti = new ArrayList<>();

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public Circumscriptie(){}

    public Circumscriptie(String idAlegeri, String numeCircumscriptie, String regiuneCircumscriptie){
//        Circumscriptie aux = null;
        Alegeri aux = null;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                aux = oldAlegeri.get(i);
            }
        }
        if (aux == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        for (int i = 0; i < aux.circumscriptii.size(); i++){
            if (aux.circumscriptii.get(i).numeCircumscriptie.equals(numeCircumscriptie)){
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie);
                return;
            }
        }

        this.numeCircumscriptie = numeCircumscriptie;
        this.regiuneCircumscriptie = regiuneCircumscriptie;
        System.out.println("S-a adaugat circumscriptia " + numeCircumscriptie);
        aux.circumscriptii.add(this);
    }

    public void adaugareVotant(String CNP, int varsta, boolean indemanare, String nume) {
        for (int i = 0; i < votanti.size(); i++) {
            if (votanti.get(i).getCNP().equals(CNP)) {
                System.out.println("EROARE: Votantul " + votanti.get(i).getNume() + " are deja acelasi CNP");
                return;
            }
        }
        Votant retval = new Votant(this.getNumeCircumscriptie(), CNP, varsta, indemanare, nume);
        votanti.add(retval);
    }

    public void listVotanti() {
        if (votanti.size() > 0) {
            System.out.println("Votantii din " + numeCircumscriptie + ":");
            for (int i = 0; i < votanti.size(); i++) {
                System.out.println(votanti.get(i).toString());
            }
        } else {
            System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
        }
    }
}
