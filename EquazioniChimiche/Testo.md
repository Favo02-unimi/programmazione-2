# Equazioni chimiche

## Descrizione

Scopo della prova è progettare e implementare una gerarchia di oggetti utili a
rappresentare alcune *molecole* data la loro *formula bruta*, in modo da poterne
determinare il *peso molecolare* e, facoltativamente, trattare *equazioni
chimiche*.

Per portare a termine il lavoro bisognerà decidere se e quali interfacce e
classi (concrete o astratte) implementare. Per ciascuna di esse si richiede di
**descrivere** (attraverso commenti presenti nel codice) le scelte relative alla
**rappresentazione** dello stato (con particolare riferimento all'*invariante di
rappresentazione* e alla *funzione di astrazione* che devono essere sempre
esplicitamente descritti) e ai **metodi** (con particolare riferimento a
*pre/post-condizioni*, *eccezioni* ed *effetti collaterali*). Si osservi che
*l'esito di questa prova si baserà tanto su questa documentazione quanto sul
codice sorgente*.

### Dettagli pratici

**La soluzione dovrà essere sviluppata in una serie di file `.java` che dovranno
essere salvati nella medesima cartella che contiene questo testo**, usando
esclusivamente il *default package* — altrimenti detto, **senza creare
sottocartelle e non introducendo alcuna dichiarazione `package` nei file
sorgenti**. Se queste indicazioni non saranno seguite esattamente, il materiale
non verrà raccolto dal sistema che gestisce la consegna e la prova d'esame non
potrà essere valutata.

Si presti particolare attenzione agli **errori di compilazione: l'intero
contenuto dei file che il compilatore si rifiuta di compilare non sarà affatto
esaminato**. Se fossero riscontrati errori di compilazione che non si è in grado
di correggere, si valuti la possibilità di racchiudere le porzioni di codice che
li causano all'interno di commenti; resta inteso che tale codice commentato non
sarà valutato, ma almeno verrà esaminato il resto del codice del file.

## Elementi chimici e molecole

Un **elemento chimico** è un atomo caratterizzato da un determinato numero di
protoni (detto *numero atomico*). Gli elementi chimici sono i costituenti
fondamentali delle sostanze e, fino al 2022, ne sono stati scoperti 118. Tali
elementi vengono ordinati, in base al numero atomico, nella **tavola periodica**
degli elementi. Ogni elemento ha un *nome*, un *simbolo* (costituito da una o
più lettere, di cui solo la prima è maiuscola) e un *peso* (espresso in unità di
massa atomica unificata, detta anche Dalton). Ad esempio, l'ossigeno è un
elemento chimico avente:

* *numero atomico*: 8
* *nome*: Ossigeno
* *simbolo*: O
* *peso*: 15,9994

Gli elementi chimici si possono aggregare in unità elementari chiamate
**molecole**, formate da atomi dello stesso elemento o di elementi diversi, che
caratterizzano molte sostanze; le molecole costituite da atomi dello stesso
elemento sono dette *semplici*, viceversa sono dette *composte*. Le molecole
possono essere descritte tramite una *formula* che elenca i simboli degli atomi
che la costituiscono seguiti dal numero di atomi di quell'elemento presenti
nella molecola; secondo la convenzione proposta da *Hill*, gli elementi sono
elencati nell'ordine: C, H, N e O seguiti dagli altri elementi in ordine
alfabetico del simbolo e, se la numerosità di un elemento è pari a 1, tale
numero viene omesso. Alcuni esempi di molecole sono il saccarosio (detto
comunemente zucchero) C12H22O11 o la clorofilla C55H72O5MgN4.

La somma dei pesi degli atomi che costituiscono una molecola ne determina il
*peso*. Ad esempio, per la clorofilla

* *formula*: C55H72N4O5Mg (secondo la convenzione di Hill)
* *peso*: 893.5026 Da

### Le equazioni chimiche (parte facoltativa)

*Implementate le entità descritte in questa sezione solo se il resto del lavoro
è stato terminato in modo completo e preciso*. La parte svolta facoltativamente
può essere valutata sia positivamente (in caso sia corretta), che negativamente
(andando a svantaggio della valutazione complessiva, anche abbassando la
valutazione ottenuta per la parte obbligatoria).

Una **equazione chimica** descrive una reazione (o trasformazione) chimica e
consiste di una lista di *reagenti* (le molecole di partenza) sul lato sinistro
di un segno di freccia `->` e di una lista di *prodotti* (le molecole di arrivo)
sul lato destro della medesima; se sono presenti più reagenti o prodotti, essi
sono separati (nei rispettivi lati dell'equazione) dal segno `+`. Ogni molecola
può essere preceduta da un numero intero chiamato *coefficiente stechiometrico*
che specifica quante molecole di tale tipo sono coinvolte nella reazione. 

Ad esempio la reazione dell'acido idrocloridrico con il sodio è descritta
dall'equazione

    2 HCl + 2 Na -> 2 NaCl + H2 

Una equazione chimica è *bilanciata* se e solo il se ciascun tipo di elementi è
presente nello stesso numero sia nei reagenti che nei prodotti. 

Nell'esempio precedente:

* tra i reagenti ci sono 2 elementi H e Cl (dalle due copie della prima
  molecola) e 2 elementi Na,
* tra i prodotti ci sono 2 elementi Na e Cl (dalle due copie della prima
  molecola) e 2 elementi H (dalla seconda molecola).

Quindi l'equazione è bilanciata.

## Cosa implementare

**Parte obbligatoria**: 

Implementare quanto necessario a rappresentare le entità descritte nella sezione
"Elementi chimici e molecole". 

In particolare, dovrà essere possibile costruire una tavola periodica coi
relativi elementi chimici a partire da un elenco (ordinato per peso atomico) dei
medesimi.

Deve essere inoltre possibile costruire delle molecole, (almeno) a partire dalla
loro formula (a patto che contenga solo gli elementi presenti nella tavola
periodica, seppur elencati in ordine diverso da quello stabilito dalla
convenzione di Hill); per ciascuna molecola dovrà essere poi possibile ottenere
sia la formula (secondo la convenzione di Hill) che il peso e l'indicazione se
sia semplice, o meno.

**Parte facoltativa**: in aggiunta, si implementi quanto necessario a
rappresentare le entità descritte nella sezione "Le equazioni chimiche".

In particolare, deve essere possibile costruire una equazione chimica (almeno a
partire da una rappresentazione testuale come quella mostrata nell'esempio della
sezione) e verificare se sia bilanciata o meno.

### La classe di test

La classe di test legge l'elenco degli elementi atomici dal flusso di ingresso
(ogni riga contiene nome, simbolo e peso, inoltre il numero della riga
corrisponde al numero atomico, supponendo le righe numerate da 1).

Se si è implementata solo la parte obbligatoria, la classe riceve una formula
come primo e unico argomento sulla linea di comando; dopo aver costruito le
molecole coinvolte deve emetterne nel flusso standard la formula (in forma di
Hill), il peso, l'indicazione se sia semplice, o composta.

Se si è implementata anche la parte facoltativa, la classe riceve una equazione
chimica come primo e unico argomento sulla linea di comando e, dopo aver
costruito le molecole coinvolte e l'equazione, deve emettere nel flusso standard
l'equazione (con le molecole in forma di Hill) e l'indicazione se l'equazione
sia, o meno, bilanciata.

#### Classe di utilità per formule ed espressioni

Per semplificare il vostro lavoro, potete usare i *metodi statici di utilità*
della classe `Helpers` fornita dal docente. Tale classe comprende i metodi
totali:

```java
public static boolean elementoValido(String simbolo);
public static String[] parseFormula(String formula);
public static String[] parseSommaStechiometrica(String somma);
```

la cui precisa specificazione è data all'interno del file sorgente e qui
brevemente richiamata:

* il primo metodo consente di sapere se una stringa ha il formato di un elemento
  (ossia se è composta da un carattere maiuscolo, eventualmente seguito da
  caratteri minuscoli);
* il secondo di suddividere una formula nelle sue "parti", ad esempio se
  invocata sulla stringa `"H2O"` restituisce l'array `["H", "2", "O", "1"]` (si
  noti l'`"1"` presente nell'array delle parti, anche se omesso nella formula);
* il terzo di suddividere l'elenco di reagenti o prodotti nelle sue "parti", ad
  esempio se invocata sulla stringa `"2 H2O + NaCl + 3O"` restituisce l'array
  `["2", "H2O", "1", "NaCl", "3", "O"]` (si noti l'`"1"` presente nell'array
  delle parti, anche se omesso nell'elenco).

### Esempio

Supponendo che sia stata implementata anche la parte facoltativa e la classe di
test si chiami Soluzione, eseguendo

    java Soluzione "2 HCl + 2 Na -> 2 NaCl + H2"

ed avendo

    Idrogeno H 1.0079
    Elio He 4.0026
    Litio Li 6.941
    Berillio Be 9.0122
    Boro B 10.81
    Carbonio C 12.011
    Azoto N 14.0067
    Ossigeno O 15.9994
    Fluoro F 18.9984
    Neon Ne 20.179
    Sodio Na 22.98977
    Magnesio Mg 24.305
    Alluminio Al 26.9815
    Silicio Si 28.0855
    Fosforo P 30.9738
    Zolfo S 32.06
    Cloro Cl 35.453
    Argon Ar 39.948
    Potassio K 39.0983
    Calcio Ca 40.08

nel flusso di ingresso, il programma emette

    Equazione:
            2HCl + 2Na -> 2ClNa + H2, bilanciata
    Reagenti:
            HCl, 36.4609, composta
            Na, 22.98977, semplice
    Prodotto:
            ClNa, 58.44277, composta
            H2, 2.0158, semplice

nel flusso d'uscita. Qualora non fosse stata svolta la parte facoltativa, avendo
`NaCl` come unico argomento sulla linea di comando, il programma emetterebbe

    ClNa, 58.44277, composta

nel flusso d'uscita.