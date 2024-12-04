### Panait Teodor Dimitrie - 323CB
# Tema 1 - Programare Orientata pe Obiecte (POO)


Obiectivul acestei teme este de a crea o platforma de votare care sa poata sa ofere
o gama variata de comenzi posibile.

- Comenzile in sine pot fi gasite in fisierul [Tema 1 - Documentatie Comenzi](./Tema1-DocumentațieComenzi-2024.pdf).    
- Cerinta temei in full poate fi gasita in fisierul [Tema 1 - 2024](./Tema1-2024.pdf).    

Metoda de baza prin care am implementat comenzile a fost prin creearea unei clase 
generale de "Comenzi", care este mostenita de o serie de clase diferite, fiecare
ocupandu-se de una sau maxim 2 comenzi acceptate. Aceste clase se gasesc
in fisierul [Comanda.java](./app/src/main/java/Tema1/Comanda.java).

Programul primeste inputul printr-un scanner buffer, si apoi folosind un switch
case identifica care dintre clasele de comanda trebuie apelata. Executia comenzii
in sine se realizeaza cu ajutorul constructorului clasei.

### Programul foloseste o suita de 28 de clase, organizate in felul urmator:    

- 17 clase in [Comanda.java](./app/src/main/java/Tema1/Comanda.java)
- 2 clase in [Raport.java](./app/src/main/java/Tema1/Raport.java)
- cate o clasa in [Alegeri.java](./app/src/main/java/Tema1/Alegeri.java),
  [Analiza.java](./app/src/main/java/Tema1/Analiza.java), 
  [App.java](./app/src/main/java/Tema1/App.java), 
  [Candidat.java](./app/src/main/java/Tema1/Candidat.java), 
  [Circumscriptie.java](./app/src/main/java/Tema1/Circumscriptie.java), 
  [Frauda.java](./app/src/main/java/Tema1/Frauda.java), 
  [Persoana.java](./app/src/main/java/Tema1/Persoana.java), 
  [Votant.java](./app/src/main/java/Tema1/Votant.java), 
  [Vot.java](./app/src/main/java/Tema1/Vot.java).


La nivel mai specific de implementare, in clasa App pastram 3 obiecte de tip
ArrayList: o lista publica de comenzi, o lista statica de alegeri si o lista
statica de fraude. Motivul utilizarii listelor statice este pentru a oferi acces
catre aceste liste in constructorii si metodele claselor. Mare parte din clase
mostenesc clasa App, ceea ce, impreuna cu proprietatea de variabilelor de a fi
statice, inseamna ca putem lucra cu aceeasi lista de fiecare data, ca si cum ar fi
un fel de variabile accesibile semi global.    
O implementare similara, dar pentru a evita mostenirea clasei App ar fi putut fi 
apelarea listelor statice prin sintagme de tip ```App().lista```.

In clasa Alegeri, pentru obiecte de tip alegeri, avem 3 liste publice: Candidati, 
Circumscriptie si Votanti. Astfel, pentru fiecare obiect Alegeri, avem o lista 
individuala de informatii legate de alegerile respective. 

Clasa Persoana nu este niciodata folosita individual, ci are scopul de a fi
folosita ca un punct de pornire pentru clasele Candidat si Votant.

Clasele Analiza si Raport se diferentiaza oarecum, deoarece ele reprezinta intr-un 
fel cate 2 sau 3 tipuri diferite de clase. Folosind constructori cu numar diferit 
de parametrii, putem crea obiecte de tip Analiza de exemplu, dar care au proprietati
destul de diferite. Mai concret, clasa Analiza poate crea obiecte care, la nivel 
intern, sunt de tipul AnalizaNationala, AnalizaRegiune si AnalizaCircumscriptie.
Probabil ca o implementare mai "corecta" din punctul de vedere al modului de gandire
cu obiecte ar fi facut clasa Analiza o clasa abstracta si ar fi creat clase 
separate pentru aceste 3 tipuri diferite de analiza. O situatie similara se aplica
si pentru Raport, cu tipurile imaginare RaportNational, RaportRegiune si
RaportCircumscripetie. 

### La nivelul discutiei de ce as refactoriza pentru comenzile si raspunsurile din aplicatie, cu presupunerea ca putem modifica intr-o oarecare masura si cerinta temei, as veni cu urmatoarele idei:
- O comanda de tipul "Configurare alegeri", care in urma rularii va primi comenzi
specifice pentru alegerea selectata. Aceasta ar veni ca un beneficiu pentru 
utilizator, deoarece nu ar mai fi necesara adaugarea ID-ului alegerii la absolut
fiecare comanda. Aceasta ar veni in paralel si cu o comanda de tup "Finalizare
configurare". Aceasta idee poate fi extinsa pentru mai multe comenzi repetitive, si
ar eficientiza programul prin limitarea numarului de verificari (daca selectam deja
o circumscriptie la care vrem sa inseram un numar arbitrar de votanti, unul dupa
altul, atunci nu mai este necesara verificarea existentei circumscriptiei pentru
fiecare votant in parte).
- Erorile (de exemplu "CNP-ul nu este valid") ar putea fi procesate prin exceptii,
lucru care ar putea pe urma facilita si crearea unui istoric de erori, salvate
impreuna cu comanda care le-a generat si eventual si un timestamp. Altfel, as 
redirectiona macar erorile catre /dev/stderr.
- As muta proprietatea de indemanare de la votant catre vot. Acest lucru ar fi mai
de folos pentru reciclarea bazelor de date de votanti, si in acelasi timp
indemanarea ar putea fi reprocesata sa o posibila renumarare


### Pentru discutia cazurilor limita pe care le-as trata in plus:
- nu pot sa le descriu in mod specific, dar ar fi in mare parte legate de actiunea
de vot. Fie ca e vorba de tipuri aditionale de frauda, cu verificarile aferente lor,
fie ca sunt anumite comenzi care s-ar putea calca pe picioare, mai ales in 
implementarea scrisa de mine. Cazuri limita cu siguranta exista, doar ca in momentul
de fata nu reusesc sa le identific