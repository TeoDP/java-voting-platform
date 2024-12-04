package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    // scanner-ul primit in constructor
    private Scanner scan;

    // comenzile
    public ArrayList<Comanda> comenzi = new ArrayList<>();
    // folosim cate un vector static pentru listele de alegeri si fraude
    // pentru ca majoritatea, daca nu chiar toate clasele folosite mostenesc clasa App
    // inseamna ca in toate aceste clase o sa avem acces la aceste 2 liste
    static ArrayList<Alegeri> oldAlegeri = new ArrayList<>();
    static ArrayList<Frauda> fraude = new ArrayList<>();

    public App(){}
    public App(InputStream input) {
        this.scan = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.

        // citim prima linie
        String temp = scan.nextLine();

        // intram in bucla de procesare a liniilor citite din scanner
        while (!temp.isBlank()) {
            // vedem care este codificarea comenzii primite
            int iD = Integer.parseInt(temp);
            // intram pe cazurile in care comanda nu are o linie aditionala de argumente
            // mai exact pentru comenzile 18 si 17
            if (iD == 18) {
                // este indeajuns sa eliberam vectorii statici si sa iesim din executie
                oldAlegeri.clear();
                fraude.clear();
                return;
            } else if (iD == 17) {
                // comanda de listare a alegerilor
                Comanda aux = new ListareAlegeri();
                comenzi.add(aux);

            } else {
                // aici procesam comenzile care au si o linie care contine argumentele lor
                // asa ce citim linia respectiva
                temp = scan.nextLine();
                // cream o variabila de clasa Comanda ca sa putem sa folosim constructori
                // de la clase de comenzi care mostenesc clasa Comanda
                // operatiile specifice fiecarei comenzi incep din constructorul comenzii respective
                Comanda aux = null;
                // pentru fiecare dintre aceste comenzi, o sa le adaugam in lista de elemente de tip Comanda
                // in caz ca in viitor vom avea nevoie de un istoric al comenzilor rulate
                switch (iD) {
                    case 0:
                        // Comanda pentru Crearea unei Alegeri
                        aux = new CreareAlegeri(temp);
                        comenzi.add(aux);
                        break;

                    case 1:
                        // Comanda pentru Deschiderea Alegerilor
                        aux = new StartAlegeri(temp);
                        comenzi.add(aux);
                        break;

                    case 2:
                        // Comanda pentru Adaugarea Circumscriptiilor la o alegere
                        aux = new AdaugareCircumscriptie(temp);
                        comenzi.add(aux);
                        break;

                    case 3:
                        // Comanda pentru Elimnarea unei Circumscriptii de la o alegere
                        aux = new EliminareCircumscriptie(temp);
                        comenzi.add(aux);
                        break;

                    case 4:
                        // Comanda pentru adaugarea unui candidat la o alegere
                        aux = new AdaugareCandidat(temp);
                        comenzi.add(aux);
                        break;

                    case 5:
                        // Comanda pentru eliminarea unui candidat de la o alegere
                        aux = new EliminareCandidat(temp);
                        comenzi.add(aux);
                        break;

                    case 6:
                        // Comanda pentru adaugarea unui votant la o circumscriptie de la o alegere
                        aux = new AdaugareVotant(temp);
                        comenzi.add(aux);
                        break;

                    case 7:
                        // Listarea candidatilor de la o alegere
                        aux = new ListareCandidati(temp);
                        comenzi.add(aux);
                        break;

                    case 8:
                        // Comanda pentru listarea votantilor de la o alegere
                        aux = new ListareVotanti(temp);
                        comenzi.add(aux);
                        break;

                    case 9:
                        // COmanda pentru inregistrarea votului unui votant (daca e valid) la o circumscriptie
                        // in cazul in care nu e valid in mod fraudulos, o sa se reporteze frauda catre lista de fraude
                        aux = new InregistrareVot(temp);
                        comenzi.add(aux);
                        break;

                    case 10:
                        // Comanda pentru oprirea unei alegeri
                        aux = new OprireAlegeri(temp);
                        comenzi.add(aux);
                        break;

                    case 11:
                        // Comanda oentreu generarea unui raport per circumscriptie pentru o alegere
                        aux = new RaportCircumscriptie(temp);
                        comenzi.add(aux);
                        break;

                    case 12:
                        // Comanda pentru generarea unui raport la nivelul intregii tari (si structurata pe regiuni)
                        // pentru o alegere
                        aux = new RaportCircumscriptie(temp);
                        comenzi.add(aux);
                        break;

                    case 13:
                        // Comanda pentru generarea unei analize per circumscriptie pentru o alegere
                        aux = new AnalizaCircumscriptie(temp);
                        comenzi.add(aux);
                        break;

                    case 14:
                        // Comanda pentru generaea unei analize la nivel national pentru o alegere
                        aux = new AnalizaCircumscriptie(temp);
                        comenzi.add(aux);
                        break;

                    case 15:
                        // Comanda pentru generarea unui raport de fraude
                        aux = new RaportFraude(temp);
                        comenzi.add(aux);
                        break;

                    case 16:
                        // Comanda pentru stergerea unei alegeri
                        aux = new StergeAlegere(temp);
                        comenzi.add(aux);
                        break;

                }
            }
            // scanam inca o linie pentru a pregati urmatoarea bucla
            temp = scan.nextLine();

        }
        return;
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}