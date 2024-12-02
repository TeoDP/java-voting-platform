package Tema1;

import java.util.ArrayList;

public class Analiza extends App {
    String numeCircumscriptie;
    String numeRegiune;
    int nrVoturiCircumscriptie;
    int nrVoturiRegiune;
    int nrVoturiNational;
    Raport raportCircumscriptie;
    Raport raportNational;
    Raport raportRegiune;

    public Analiza() {}

    public Analiza(Alegeri alegere) {
        for (int i = 0; i < alegere.votanti.size(); i++) {
            if (alegere.votanti.get(i).getVot() != null) {
                nrVoturiNational += 1;
            }
        }
        if (nrVoturiNational == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }
        System.out.println("In Romania au fost " + nrVoturiNational + " voturi.");

        ArrayList<String> regiuni = new ArrayList<>();
        for (int i = 0; i < alegere.circumscriptii.size(); i++) {
            Circumscriptie circumscriptie = alegere.circumscriptii.get(i);
            if ( !regiuni.contains(circumscriptie.getRegiuneCircumscriptie()) ) {
                regiuni.add(circumscriptie.getRegiuneCircumscriptie());
            }
        }
        ArrayList<Analiza> rapoarteRegiuni = new ArrayList<>();
        for (int j = 0; j < regiuni.size(); j++) {
            Analiza aux = new Analiza(alegere, regiuni.get(j), 0);
            System.out.println(aux.toStringCirc("regiunii"));
        }

    }

    public Analiza(Alegeri alegere, String numeRegiune, int nothing) {

        raportRegiune = new Raport(alegere, numeRegiune, nothing);
        raportRegiune.toString();

        nrVoturiRegiune = 0;
        for (int i = 0; i < raportRegiune.rapoarte.size(); i++) {
            nrVoturiRegiune += raportRegiune.rapoarte.get(i).voturi;
        }

        raportNational = new Raport(alegere);
        raportNational.toString();

        nrVoturiNational = 0;
        for (int i = 0; i < raportNational.rapoarte.size(); i++) {
            nrVoturiNational += raportNational.rapoarte.get(i).voturi;
        }
        numeCircumscriptie = numeRegiune;
        nrVoturiCircumscriptie = nrVoturiRegiune;
        raportCircumscriptie = raportRegiune;

    }

    public Analiza(Alegeri alegere, String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
        Circumscriptie circumscriptie = alegere.findCircumscriptie(numeCircumscriptie);
        if (circumscriptie == null) {
            return;
        }

        raportCircumscriptie = new Raport(alegere, numeCircumscriptie);
        raportCircumscriptie.toString();

        nrVoturiCircumscriptie = 0;
        for (int i = 0; i < raportCircumscriptie.rapoarte.size(); i++) {
            nrVoturiCircumscriptie += raportCircumscriptie.rapoarte.get(i).voturi;
        }

        raportNational = new Raport(alegere);
        raportNational.toString();

        nrVoturiNational = 0;
        for (int i = 0; i < raportNational.rapoarte.size(); i++) {
            nrVoturiNational += raportNational.rapoarte.get(i).voturi;
        }

    }

    public String toStringCirc(String type) {
        String retval = "";
        if (nrVoturiCircumscriptie == 0) {
            retval = "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
            return retval;
        }
        retval = "In " + numeCircumscriptie + " au fost ";
        retval += nrVoturiCircumscriptie + " voturi din ";
        retval += nrVoturiNational + ". Adica ";
        int procent = nrVoturiCircumscriptie * 100 / nrVoturiNational;
        retval += procent + "%. Cele mai multe voturi au fost stranse de ";
        int length = raportCircumscriptie.rapoarte.size() - 1;
        String CNP = raportCircumscriptie.rapoarte.get(length).getCNP();
        String nume = raportCircumscriptie.rapoarte.get(length).getNume();
        retval += CNP + " " + nume + ". Acestea constituie ";
        procent = raportCircumscriptie.rapoarte.get(length).voturi * 100 / nrVoturiCircumscriptie;
        retval += procent + "% din voturile " + type + ".";
        return retval;
    }
//
//    public String toStringReg() {
//        String retval = "";
//        if (nrVoturiRegiune == 0) {
//            retval = "GOL: Lumea nu isi exercita dreptul de vot in Romania";
//            return retval;
//        }
//        retval = "in " + numeRegiune + " au fost ";
//        retval += nrVoturiRegiune + " voturi din ";
//        retval += nrVoturiNational + ". Adica ";
//        int procent =nrVoturiRegiune * 100 / nrVoturiNational;
//        retval += procent + "%. Cele mai multe voturi au
//    }
}
