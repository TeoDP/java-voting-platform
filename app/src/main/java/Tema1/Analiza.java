package Tema1;

import java.util.ArrayList;

// clasa folosita in mod special pentru a crea obiecte de tip analiza
public class Analiza extends App {
    String numeCircumscriptie;
    String numeRegiune;
    int nrVoturiCircumscriptie;
    int nrVoturiRegiune;
    int nrVoturiNational;
    Raport raportCircumscriptie;
    Raport raportNational;
    Raport raportRegiune;

    // constructor pentru a genera o analiza nationala
    public Analiza(Alegeri alegere) {
        // numaram numarul total de voturi
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

        // creem o lista de string-uri cu toate regiunile din alegerile curente
        ArrayList<String> regiuni = new ArrayList<>();
        for (int i = 0; i < alegere.circumscriptii.size(); i++) {
            Circumscriptie circumscriptie = alegere.circumscriptii.get(i);
            if ( !regiuni.contains(circumscriptie.getRegiuneCircumscriptie()) ) {
                regiuni.add(circumscriptie.getRegiuneCircumscriptie());
            }
        }
        // generam analize pentru fiecare regiune in parte, si le printam
        ArrayList<Analiza> rapoarteRegiuni = new ArrayList<>();
        for (int j = 0; j < regiuni.size(); j++) {
            Analiza aux = new Analiza(alegere, regiuni.get(j), 0);
            System.out.println(aux.toStringCirc("regiunii"));
        }

    }

    // constructor pentru a genera o analiza pe regiune
    // se foloseste de al 3lea parametru pentru a diferentia semnatura metodei
    public Analiza(Alegeri alegere, String numeRegiune, int nothing) {
        // creem raport pe regiune si apelam functia toString pentru a-i sorta continutul
        raportRegiune = new Raport(alegere, numeRegiune, nothing);
        raportRegiune.toString();

        // numaram numarul de voturi din regiunea curenta
        nrVoturiRegiune = 0;
        for (int i = 0; i < raportRegiune.rapoarte.size(); i++) {
            nrVoturiRegiune += raportRegiune.rapoarte.get(i).voturi;
        }

        // creem un raport national si ii sortam continutul
        raportNational = new Raport(alegere);
        raportNational.toString();

        // numaram numarul de voturi la nivel national
        nrVoturiNational = 0;
        for (int i = 0; i < raportNational.rapoarte.size(); i++) {
            nrVoturiNational += raportNational.rapoarte.get(i).voturi;
        }
        numeCircumscriptie = numeRegiune;
        nrVoturiCircumscriptie = nrVoturiRegiune;
        raportCircumscriptie = raportRegiune;

    }

    // constructor pentru analize la nivel de circumscriptie
    public Analiza(Alegeri alegere, String numeCircumscriptie) {
        // gasim circumscriptia data
        this.numeCircumscriptie = numeCircumscriptie;
        Circumscriptie circumscriptie = alegere.findCircumscriptie(numeCircumscriptie);
        if (circumscriptie == null) {
            return;
        }

        // generam un raport pentru circumscriptie si ii sortam continutul
        raportCircumscriptie = new Raport(alegere, numeCircumscriptie);
        raportCircumscriptie.toString();

        // numaram numarul de voturi din circumscriptie
        nrVoturiCircumscriptie = 0;
        for (int i = 0; i < raportCircumscriptie.rapoarte.size(); i++) {
            nrVoturiCircumscriptie += raportCircumscriptie.rapoarte.get(i).voturi;
        }

        // creem un raport national si ii sortam continutul
        raportNational = new Raport(alegere);
        raportNational.toString();

        // numaram numarul de voturi la nivel national
        nrVoturiNational = 0;
        for (int i = 0; i < raportNational.rapoarte.size(); i++) {
            nrVoturiNational += raportNational.rapoarte.get(i).voturi;
        }
        // printam analiza circumscriptiei
        System.out.println(this.toStringCirc("circumscriptiei"));
    }

    // o functie foarte similara cu toString-ul normal, dar modificata astfel in cat sa primeasca un parametru
    // scopul parametrului este de a face functia "mai universala", putand fi folosita atat pentru regiuni cat
    // si pentru circumscriptii
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

}
