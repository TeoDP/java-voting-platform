package Tema1;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

// clasa asta este clasa de baza pentru clasele de tip comanda
// ea extinde clasa App pentru a avea acces la listele declarate static
public class Comanda extends App{
    // ID-ul comnezii
    public int iD;
    // argumentele pe care le primeste comanda
    // existenta acestei liste nu e necesara in mod direct pentru functionare, dar este de folos
    // deoarece stocam toate comenzile care sunt rulate
    ArrayList<String> arguments = new ArrayList<>();

    // constructor gol
    public Comanda() {}

    // functie care este de folos la majoritatea comenzilor;
    // scopul ei este sa returneze obiectul alegere cu indexul primit
    // de asemenea afiseaza si eroarea aferenta daca nu e gasita sesiunea de alegeri
    public Alegeri findAlegere(String iDalegere) {
        Alegeri aux = null;
        // verificam in vectorul de alegeri, declarat static in clasa App
        for (int i = 0; i < oldAlegeri.size(); i++) {
            aux = oldAlegeri.get(i);
            if (aux.getIdAlegeri().equals(iDalegere)) {
                return aux;
            }
        }
        // daca nu exista alegeri cu acest id, dam eroare
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return null;
    }

}

// clasa aferenta comenzii de Creare a unor Alegeri
class CreareAlegeri extends Comanda {
    // are ca variabile argumentele pe care le primeste in scanner buffer
    private String iDAlegeri;
    private String numeAlegeri;

    public CreareAlegeri(String arguments) {
        // ID-ul comenzii
        iD = 0;

        // citim argumentele  si le adaugam in lista de argumente
        String[] split = arguments.split(" ");
        this.arguments.add(split[0]);
        String temp = "";
        for (int i = 1; i < split.length; i++) {
            temp += split[i] + " ";
        }
        this.arguments.add(temp);

        // asignam si variabilelor argumentele primite
        iDAlegeri = this.arguments.get(0);
        numeAlegeri = this.arguments.get(1);

        // cream un obiect de tip alegeri nou
//        Alegeri alegeri =
        new Alegeri(iDAlegeri, numeAlegeri);
    }
}

// clasa aferenta comenzii de Start Alegeri
class StartAlegeri extends Comanda {

//    public StartAlegeri() {
//        iD = 1;
//    }

    public StartAlegeri(String iDAlegeri) {
        // comanda are codificare 1
        iD = 1;
        // gasim alegerea primita ca parametru unic
        Alegeri aux = findAlegere(iDAlegeri);
        if (aux == null) {
            return;
        }
        // apelam functia de start alegeri din cadrul obiectului de tip alegeri
        aux.startAlegeri();
    }
}

// clasa aferenta comenzii de adaugare circumscriptie
class AdaugareCircumscriptie extends Comanda {
    // primeste ca parametru
    public String idAlegeri;

    public AdaugareCircumscriptie(String arguments) {
        // ID-ul comenzii
        iD = 2;
        // extragem argumentele pe care le primeste comanda
        String[] split = arguments.split(" ");
        this.arguments.add(split[0]);
        idAlegeri = this.arguments.get(0);

        // identificam sesiunea de alegeri mentionata in argumentele comenzii, daca exista
        Alegeri alegeri = findAlegere(idAlegeri);
        if (alegeri == null) {
            return;
            // verificam daca sesiunea de alegeri este si deschisa
        } else if (alegeri.getStatus() != 1) {
            System.out.println("EROARE: Nu este perioada de votare");
        }
        // creeam o circumscriptie pentru alegerea noastra
        // apelam constructorul clasei circumscriptie cu paramentrii primiti ca argument in scanner buffer
        Circumscriptie circumscriptie = new Circumscriptie(split[0], split[1], split[2]);
        // adaugam circumscriptia la alegerea noastra
        alegeri.circumscriptii.add(circumscriptie);
    }
}

// clasa aferenta comenzii de Eliminare Circumscriptie
class EliminareCircumscriptie extends Comanda {
    // argumentele primite de comanda
    public String idAlegeri;
    private String numeCircumscriptie;

    public EliminareCircumscriptie(String arguments) {
        // ID-ul comenzii
        iD = 3;
        // obtinem in sine argumentele primite
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];

        // gasim alegerea ceruta
        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        } else if (alegere.getStatus() != 1 ) {
            // verificam daca aceasta este si deschisa
            System.out.println("EROARE: Nu este perioada de votare");
        }

        // gasim circumscriptia ceruta si o stergem
        for (int i = 0; i < alegere.circumscriptii.size(); i++) {
            if (alegere.circumscriptii.get(i).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                alegere.circumscriptii.remove(i);
                System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
                return;
            }
        }
        // daca nu exista circumscriptia ceruta, mentionam printr-o eroare
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);

    }
}

// clasa aferenta comenzii de adaugare candidat
class AdaugareCandidat extends Comanda {
    public String idAlegeri;

    public AdaugareCandidat(String arguments) {
        iD = 4;
        // extragem argumentele comenzii
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        // concatenam stringuri ca sa aflam numele candidatului
        String nume = "";
        for (int i = 3; i < split.length; i++) {
            nume += split[i];
            if (i != split.length - 1) {
                nume += " ";
            }
        }
        // identificam alegerea
        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        } else {
            // adaugam votantul la alegere
            alegere.adaugareCandidat(idAlegeri, split[1], Integer.parseInt(split[2]), nume);
        }
    }
}

// clasa aferenta comenzii de eliminare candidat
class EliminareCandidat extends Comanda {
    public String idAlegeri;

    public EliminareCandidat(String arguments) {
        iD = 5;
        // identificam argumentele primite
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        } else if (alegere.getStatus() != 1 ) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        String CNP = split[1];

        // apelam metoda de eliminare candidat din cadrul obiectului de tip alegeri
        alegere.eliminareCandidat(CNP);
    }
}

// clasa aferenta comenzii de adaugare votant
class AdaugareVotant extends Comanda {
    // setam variabilele setate ca argumente
    String idAlegeri;
    String numeCircumscriptie;
    String CNP;
    int varsta;
    boolean indemanare;
    String nume;

    public AdaugareVotant(String arguments) {
        iD = 6;
        // obtinem argumentele primite in scanner buffer
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];
        CNP = split[2];
        varsta = Integer.parseInt(split[3]);
        // verificam sa aiba varsta legala de vot
        if (varsta < 18) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        // setam indemanarea
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

        // identificam alegerea
        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        } else {
            // adaugam votantul la alegere
            alegere.adaugareVotant(numeCircumscriptie, CNP, varsta, indemanare, nume);
        }
    }
}

// comanda aferenta comenzii de listare candidati
class ListareCandidati extends Comanda{
    String idAlegeri;

    public ListareCandidati(String arguments) {
        iD = 7;
        idAlegeri = arguments;

        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        // citim candidatii din lista din alegere
        if (!alegere.candidati.isEmpty()) {
            System.out.println("Candidatii:");
            for (int i = 0; i < alegere.candidati.size(); i++) {
                // folosim functia suprascrisa toString ca sa afisam candidatii
                System.out.println(alegere.candidati.get(i).toString());
            }
        } else {
            System.out.println("GOL: Nu sunt candidati");
            return;
        }
    }
}

// clasa aferenta comenzii de listare votanti
class ListareVotanti extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;

    public ListareVotanti(String arguments) {
        iD = 8;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];

        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }
        // gasim circumscriptia data ca argument in scanner buffer
        Circumscriptie circumscriptie = alegere.findCircumscriptie(numeCircumscriptie);
        if (circumscriptie == null) {
            return;
        }
        // apelam functia de listare votanti implementata in obiectul de tip circumscriptie
        circumscriptie.listVotanti(alegere);

    }
}

// clasa aferenta comenzii de inregistrare vot
class InregistrareVot extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    String CNPVotant;
    String CNPCandidat;

    public InregistrareVot(String arguments) {
        iD = 9;
        // separam argumentele comenzii
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        numeCircumscriptie = split[1];
        CNPVotant = split[2];
        CNPCandidat = split[3];

        // gasim alegerea
        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        // gasim circumscriptia
        Circumscriptie circumscriptie = alegere.findCircumscriptie(numeCircumscriptie);
        if (circumscriptie == null) {
            return;
        }

        // gasim candidatul
        Candidat candidat = alegere.findCandidat(CNPCandidat);
        if (candidat == null) {
            return;
        }

        // gasim votantul
        Votant votant = alegere.findVotant(CNPVotant);
        if (votant == null) {
            return;
        }
        // setam votul
        if (votant.numeCircumscriptie.equals(numeCircumscriptie)) {
            votant.setVot(alegere, CNPCandidat);
        } else {
            // frauda de vot
            Frauda frauda = new Frauda(votant);
        }


    }
}

// clasa aferenta comenzii de oprire alegeri
class OprireAlegeri extends Comanda {
    String idAlegeri;

    public OprireAlegeri(String arguments) {
        iD = 10;

        idAlegeri = arguments;
        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }
        // oprim alegerile din cadrul obiectului in sine
        alegere.oprireAlegeri();
    }
}

// clasa aferenta comenzii de generare raport circumscriptie
// si comenzii de generare raport national
class RaportCircumscriptie extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    Raport raport;

    public RaportCircumscriptie(String arguments) {
        iD = 11;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        // trebuie sa verificam numarul de argumente primit
        if (split.length == 2) {
            // daca exista, setam numele circumscriptiei
            numeCircumscriptie = split[1];
        }
        // gasim alegerea
        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        // folosim constructori cu numar de parametri diferiti, in functie de tipul raportului
        if (numeCircumscriptie != null) {
            // daca avem nume pentru circumscriptie, atunci raportul va fi generat pentru circumscriptie
            raport = new Raport(alegere, numeCircumscriptie);
        } else {
            // altfel, va fi generat la nivel national
            raport = new Raport(alegere);
        }
        // printam raportul in sine, folosind functia toString
        System.out.println(raport.toString());
    }
}

// clasa aferenta comenzii de generare analiza circumscriptie
// si comenzii de generare analiza nationala
class AnalizaCircumscriptie extends Comanda {
    String idAlegeri;
    String numeCircumscriptie;
    Analiza analiza;

    public AnalizaCircumscriptie() {}
    public AnalizaCircumscriptie(String arguments) {
        iD = 13;
        String[] split = arguments.split(" ");
        idAlegeri = split[0];
        // verificam daca am primit ca argumente pentru comanda si numele circumscriptiei
        if (split.length == 2) {
            numeCircumscriptie = split[1];
        }

        // gasim alegerea in sine
        Alegeri alegere = this.findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        // apelam constructorul necesar in functie de tipul de analiza cerut
        if (numeCircumscriptie != null) {
            analiza = new Analiza(alegere, numeCircumscriptie);
        } else {
            analiza = new Analiza(alegere);
        }
    }
}

// clasa aferenta comenzii de generare raport fraude
class RaportFraude extends Comanda {
    String idAlegeri;

    public RaportFraude(String arguments) {
        iD = 15;
        idAlegeri = arguments;

        // identificam alegerea
        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        }

        // gasim fraudele care sunt aferente alegerii
        boolean bec = false;
        for (int i = fraude.size() - 1; i >= 0; i--) {
            Frauda frauda = fraude.get(i);
            if (frauda.getIdAlegeri().equals(idAlegeri)) {
                if (bec == false) {
                    // printam un "header" inainte de a incepe sa scriem fraudele
                    // dar doar daca avem minim o frauda identificata
                    System.out.println("Fraude comise:");
                    bec = true;
                }
                System.out.println(frauda);
            }
            return;
        }
        System.out.println("GOL: Romanii sunt cinstiti");

    }
}

// clasa aferenta comenzii de stergere a unei alegeri
class StergeAlegere extends Comanda {
    String idAlegeri;

    public StergeAlegere(String arguments) {
        idAlegeri = arguments;

        // gasim alegerea
        Alegeri alegere = findAlegere(idAlegeri);
        if (alegere == null) {
            return;
        } else {
            // stergem alegerea
            int index = oldAlegeri.indexOf(alegere);
            oldAlegeri.remove(index);
            System.out.println("S-au sters alegerile " + alegere.getNumeAlegeri());
        }

    }
}

// clasa aferenta comenzii de listare a tuturor alegerilor
class ListareAlegeri extends Comanda {

    public ListareAlegeri() {
        if (oldAlegeri.size() == 0) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        } else {
            System.out.println("Alegeri:");
            // practic, ne folosim in mod implicit
            for (int i = 0; i < oldAlegeri.size(); i++) {
                System.out.println(oldAlegeri.get(i));
            }
        }
    }
}