package Tema1;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

public class Comanda extends App{
    public int iD;
    ArrayList<String> arguments = new ArrayList<>();

    public Comanda() {}

    public void addArgs(String arguments) {
    }

    public Alegeri findAlegere(String iDalegere) {
        Alegeri aux = null;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            aux = oldAlegeri.get(i);
            if (aux.getIdAlegeri().equals(iDalegere)) {
                return aux;
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return null;
    }

}

class CreareAlegeri extends Comanda {
    private String iDAlegeri;
    private String numeAlegeri;

    public CreareAlegeri(String arguments) {
        iD = 0;
        String[] split = arguments.split(" ");
        this.arguments.add(split[0]);
        String temp = "";
        for (int i = 1; i < split.length; i++) {
            temp += split[i] + " ";
        }
        this.arguments.add(temp);

        iDAlegeri = this.arguments.get(0);
        numeAlegeri = this.arguments.get(1);

        Alegeri alegeri = new Alegeri(iDAlegeri, numeAlegeri);
//        oldAlegeri.add(alegeri);
    }
}

class StartAlegeri extends Comanda {

    public StartAlegeri() {
        iD = 1;
    }

    public StartAlegeri(String iDAlegeri) {
        iD = 1;
        Alegeri aux = findAlegere(iDAlegeri);
        if (aux == null) {
            return;
        }
        aux.startAlegeri();
    }
}

class AdaugareCircumscriptie extends Comanda {
    public String idAlegeri;

    public AdaugareCircumscriptie() {}

    public AdaugareCircumscriptie(String arguments) {
        iD = 2;
        String[] split = arguments.split(" ");
        this.arguments.add(split[0]);
//        String temp[] = null;
//        for (int i = 1; i < split.length; i++) {
//            temp += split[i] + " ";
//        }
//        this.arguments.add(temp);
        idAlegeri = this.arguments.get(0);

        int bec = -1;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                bec = i;
            }
        }
        if (bec == -1) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        } else if (oldAlegeri.get(bec).getStatus() != 1 ) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        Circumscriptie circumscriptie = new Circumscriptie(split[0], split[1], split[2]);
        oldAlegeri.get(bec).circumscriptii.add(circumscriptie);
    }
}

class EliminareCircumscriptie extends Comanda {
    public String idAlegeri;
    private String numeCircumscriptie;

    public EliminareCircumscriptie() {}

    public EliminareCircumscriptie(String arguments) {
        iD = 3;
        String[] split = arguments.split(" ");
//        this.arguments.add(split[0]);
        idAlegeri = split[0];
        numeCircumscriptie = split[1];

        int bec = -1;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                bec = i;
            }
        }
        if (bec == -1) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        } else if (oldAlegeri.get(bec).getStatus() != 1 ) {
            System.out.println("EROARE: Nu este perioada de votare");
        }

        for (int i = 0; i < oldAlegeri.get(bec).circumscriptii.size(); i++) {
            if (oldAlegeri.get(bec).circumscriptii.get(i).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                oldAlegeri.get(bec).circumscriptii.remove(i);
                System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
                return;
            }
        }
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);

    }
}

class AdaugareCandidat extends Comanda {
    public String idAlegeri;

    public AdaugareCandidat() {}
    public AdaugareCandidat(String arguments) {
        iD = 4;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        String nume = "";
        for (int i = 3; i < split.length; i++) {
            nume += split[i];
            if (i != split.length - 1) {
                nume += " ";
            }
        }
        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                oldAlegeri.get(i).adaugareCandidat(idAlegeri, split[1], Integer.parseInt(split[2]), nume);
                return;
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
}

class EliminareCandidat extends Comanda {
    public String idAlegeri;

    public EliminareCandidat() {}
    public EliminareCandidat(String arguments) {
        iD = 5;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        int indexAlegeri = -1;
        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                indexAlegeri = i;
            }
        }
        if (indexAlegeri == -1) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        } else if (oldAlegeri.get(indexAlegeri).getStatus() != 1 ) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        String CNP = split[1];

        oldAlegeri.get(indexAlegeri).eliminareCandidat(CNP);
    }
}

class AdaugareVotant extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    String CNP;
    int varsta;
    boolean indemanare;
    String nume;

    public AdaugareVotant() {}
    public AdaugareVotant(String arguments) {
        iD = 6;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];
        CNP = split[2];
        varsta = Integer.parseInt(split[3]);
        if (varsta < 18) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        if (split[4].equals("da")) {
            indemanare = true;
        } else if (split[4].equals("nu")) {
            indemanare = false;
        }
        nume = "";
        for (int i = 5; i < split.length; i++) {
            nume += split[i];
            if (i != split.length - 1) {
                nume += " ";
            }
        }

        for (int i = 0; i < oldAlegeri.size(); i++) {
            if (oldAlegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                oldAlegeri.get(i).adaugareVotant(numeCircumscriptie, CNP, varsta, indemanare, nume);
                return;
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");

    }
}

class ListareCandidati extends Comanda{
    String idAlegeri;

    public ListareCandidati() {}
    public ListareCandidati(String arguments) {
        iD = 7;
        idAlegeri = arguments;

        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (alegere.candidati.size() != 0) {
            System.out.println("Candidatii:");
            for (int i = 0; i < alegere.candidati.size(); i++) {
                System.out.println(alegere.candidati.get(i).toString());
            }
        } else {
            System.out.println("GOL: Nu sunt candidati");
            return;
        }
    }
}

class ListareVotanti extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;

    public ListareVotanti() {}
    public ListareVotanti(String arguments) {
        iD = 8;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];

        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        Circumscriptie circumscriptie = alegere.findCircumscriptie(numeCircumscriptie);
        if (circumscriptie == null) {
            return;
        }
        circumscriptie.listVotanti(alegere);

    }
}

class InregistrareVot extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    String CNPVotant;
    String CNPCandidat;

    public InregistrareVot() {}
    public InregistrareVot(String arguments) {
        iD = 9;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];
        CNPVotant = split[2];
        CNPCandidat = split[3];

        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        Circumscriptie circumscriptie = alegere.findCircumscriptie(numeCircumscriptie);
        if (circumscriptie == null) {
            return;
        }

        Candidat candidat = alegere.findCandidat(CNPCandidat);
        if (candidat == null) {
            return;
        }

        Votant votant = alegere.findVotant(CNPVotant);
        if (votant == null) {
            return;
        }
        if (votant.numeCircumscriptie.equals(numeCircumscriptie)) {
            votant.setVot(alegere, CNPCandidat);
        } else {
            Frauda frauda = new Frauda(votant);
        }


    }
}

class OprireAlegeri extends Comanda {
    String idAlegeri;

    public OprireAlegeri() {}
    public OprireAlegeri(String arguments) {
        iD = 10;

        idAlegeri = arguments;
        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }
        alegere.oprireAlegeri();
    }
}

class RaportCircumscriptie extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    Raport raport;

    public RaportCircumscriptie() {}
    public RaportCircumscriptie(String arguments) {
        iD = 11;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        if (split.length == 2) {
            numeCircumscriptie = split[1];
        }
        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        if (numeCircumscriptie != null) {
            raport = new Raport(alegere, numeCircumscriptie);
        } else {
            raport = new Raport(alegere);
        }
        System.out.println(raport.toString());
    }
}

class AnalizaCircumscriptie extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    Analiza analiza;

    public AnalizaCircumscriptie() {}
    public AnalizaCircumscriptie(String arguments) {
        iD = 13;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        if (split.length == 2) {
            numeCircumscriptie = split[1];
        }

        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        if (numeCircumscriptie != null) {
            analiza = new Analiza(alegere, numeCircumscriptie);
            System.out.println(analiza.toStringCirc("circumscriptiei"));
        } else {
            analiza = new Analiza(alegere);
        }



    }
}

class RaportFraude extends Comanda {
    String idAlegeri;

    public RaportFraude() {}
    public RaportFraude(String arguments) {
        iD = 15;
        idAlegeri = arguments;

        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        for (int i = fraude.size() - 1; i >= 0; i--) {
            Frauda frauda = fraude.get(i);
            boolean bec = false;
            if (frauda.getIdAlegeri().equals(idAlegeri)) {
                if (bec == false) {
                    System.out.println("Fraude comise:");
                }
                System.out.println(frauda);
            }
            return;
        }
        System.out.println("GOL: Romanii sunt cinstiti");

    }
}

class StergeAlegere extends Comanda {
    String idAlegeri;

    public StergeAlegere() {}
    public StergeAlegere(String arguments) {
        idAlegeri = arguments;

        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        } else {
            int index = oldAlegeri.indexOf(alegere);
            oldAlegeri.remove(index);
            System.out.println("S-au sters alegerile " + alegere.getNumeAlegeri());
        }

    }
}


class ListareAlegeri extends Comanda {

    public ListareAlegeri() {
        if (oldAlegeri.size() == 0) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        } else {
            System.out.println("Alegeri:");
            for (int i = 0; i < oldAlegeri.size(); i++) {
                System.out.println(oldAlegeri.get(i));
            }
        }
    }
}