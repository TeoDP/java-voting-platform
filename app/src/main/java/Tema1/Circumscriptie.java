package Tema1;

import java.util.ArrayList;

public class Circumscriptie extends AdaugareCircumscriptie{
    private String numeCircumscriptie;
    private String regiuneCircumscriptie;

    public Circumscriptie(){}

    public Circumscriptie(String idAlegeri, String numeCircumscriptie, String regiuneCircumscriptie){
//        Circumscriptie aux = null;
        for (int i = 0; i < circumscriptii.size(); i++){
            if (circumscriptii.get(i).numeCircumscriptie.equals(numeCircumscriptie)){
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie);
                return;
            }
        }

        this.numeCircumscriptie = numeCircumscriptie;
        this.regiuneCircumscriptie = regiuneCircumscriptie;
        System.out.println("S-a adaugat circumscriptia " + numeCircumscriptie);
        circumscriptii.add(this);
    }
}
