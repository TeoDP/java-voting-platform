package Tema1;

import java.util.ArrayList;

public class Circumscriptie extends Alegeri{
    private String numeCircumscriptie;
    private String regiuneCircumscriptie;

    // gettere pentru variabilele private
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public String getRegiuneCircumscriptie() {
        return regiuneCircumscriptie;
    }

    // constructor cu parametrii pentru toate informatiile relevante pentru obiectul nostru
    public Circumscriptie(String idAlegeri, String numeCircumscriptie, String regiuneCircumscriptie){
        // gasim alegerae data prin ID
        Alegeri aux = findAlegere(idAlegeri);
        if (aux == null) {
            return;
        }
        // cautam sa vedem daca exista deja o circumscriptie cu numele dat in alegerea data
        for (int i = 0; i < aux.circumscriptii.size(); i++){
            if (aux.circumscriptii.get(i).numeCircumscriptie.equals(numeCircumscriptie)){
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie);
                return;
            }
        }
        // setam variabilele circumscriptiei
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiuneCircumscriptie = regiuneCircumscriptie;
        System.out.println("S-a adaugat circumscriptia " + numeCircumscriptie);
        // adaugam circumscriptia la lista din cadrul alegerilor date
        aux.circumscriptii.add(this);
    }

    // metoda prin care listam toti votantii din circumscriptie
    public void listVotanti(Alegeri alegere) {
        if (alegere.votanti.size() > 0) {
            // printam un "header" pentru lista noastra
            System.out.println("Votantii din " + numeCircumscriptie + ":");
            for (int i = 0; i < alegere.votanti.size(); i++) {
                // ne folosim de functia toString ca sa printam informatiile care ne intereseaza
                System.out.println(alegere.votanti.get(i).toString());
            }
        } else {
            // daca nu a votat nimeni, anuntam asat
            System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
        }
    }

    // functie prin care gasim daca exista un votant cu un CNP anume la circumscriptia curenta
    public Votant findVotant(String CNP) {
        // trecem prin toata lista de votanti
        for (int i = 0; i < votanti.size(); i++) {
            if (votanti.get(i).getCNP().equals(CNP)) {
                return votanti.get(i);
            }
        }
        // dacanu il gasim, nu l-am gasit si anuntam asta
        System.out.println("EROARE: Nu exista un votant cu CNP-ul " + CNP);
        return null;
    }
}
