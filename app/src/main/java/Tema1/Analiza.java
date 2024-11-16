package Tema1;

public class Analiza extends App {
    String numeCircumscriptie;
    int nrVoturiCircumscriptie;
    int nrVoturiNational;
    Raport raportCircumscriptie;
    Raport raportNational;

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
        System.out.println("in Romania au fost " + nrVoturiNational + " voturi.");

        for (int i = 0; i < alegere.circumscriptii.size(); i++) {
            String regiune = alegere.circumscriptii.get(i).getRegiuneCircumscriptie();
            new Analiza(alegere, regiune);
        }

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

        System.out.println(this.toStringCirc());

    }

    public String toStringCirc() {
        String retval = "";
        if (nrVoturiCircumscriptie == 0) {
            retval = "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
            return retval;
        }
        retval = "in " + numeCircumscriptie + " au fost ";
        retval += nrVoturiCircumscriptie + " voturi din ";
        retval += nrVoturiNational + ". Adica ";
        int procent = nrVoturiCircumscriptie * 100 / nrVoturiNational;
        retval += procent + "%. Cele mai multe voturi au fost stranse de ";
        int length = raportCircumscriptie.rapoarte.size() - 1;
        String CNP = raportCircumscriptie.rapoarte.get(length).getCNP();
        String nume = raportCircumscriptie.rapoarte.get(length).getNume();
        retval += CNP + " " + nume + ". Acestea constituie ";
        procent = raportCircumscriptie.rapoarte.get(length).voturi * 100 / nrVoturiCircumscriptie;
        retval += procent + "% din voturile circumscriptiei.";
        return retval;
    }
}
