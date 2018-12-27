## 1 Prima implementazione
### 1.1 SecureDataContainerHashMap
Come prima implementazione ho pensato di utilizzare un HashMap che contenga, come chiavi, il tipo User e, come
valori, un Vector<E>. Ho pensato a questa soluzione proprio perchè ogni utente è unico, e, a ogni utente, possono
appartenere uno o più dati di tipo generico E (o anche nessuno) e, data la struttura dell’HashMap secondo la quale
ogni chiave è unica, la gestione di ciò sarebbe stata più veloce. Ho inoltre ipotizzato che i vari nominativi e password
degli utenti debbano essere diversi da null e da stringa vuota, e che i dati siano diversi da null. Inoltre ho ammesso
l’inserzione di dati doppioni per via del metodo copy. Ho poi effettuato una shallow copy al posto di una deep copy,
dove occorreva, in primis perchè non era specificato che tipo di copia effettuare, e in secondo luogo perchè, avendo
a che fare con dei tipi generici, non è detto che al momento dell’utilizzo della classe venga passato un oggetto che
estenda la Cloneable (che ha il metodo clone), il che vorrebbe dire che in caso di deep copy avrei dovuto limitare il
generico, perdendo, cosı̀, i suoi vantaggi oppure implementare una classe E che implementasse Cloneable ma anche
in questo caso avrei perso il significato ”puro” del generico. La variabile d’istanza DBUsers è un oggetto di tipo
HashMap che contiene quindi tutti gli utenti registrati, a ognuno dei quali corrisponde un Vector contenente i dati
di tipo generico. Siccome ho ipotizzato che l’id di ogni utente fosse univoco mi sono creato un metodo di appoggio
doubleUser che, dato un User come input, controlli che non ne esista già uno nella collezione. Questo mi servirà in fase
di registrazione (createUser), ho inoltre creato un’eccezione checked DoubleUserException che viene lanciata quando
un utente prova a registrarsi con le stesse credenziali di un altro. Ho costruito un metodo per cifrare in modo banale
la password di ogni utente, al fine di non inserirla in chiaro. Per quanto riguarda la removeUser, ho creato un’altra
eccezione checked NoUserException che viene lanciata qualora si tenti di eliminare un utente che non esiste oppure
qualora le credenziali di quest’ultimo siano errate. Nella getSize, seguendo le regole della programmazione difensiva,
non restituisco direttamente la dimensione del Vector dell’utente selezionato, bensı̀ effettuo prima una copia shallow
di quest’ultimo, e restituisco aux.size(). Per quanto riguarda il metodo put, ho ipotizzato che, nonostante debba
restituire un boolean, fosse meglio comunque lanciare un’eccezione in caso di non riuscita dell’aggiunta del dato nella
collezione. Nel metodo get, in cui viene richiesta la copia di uno specifico dato di uno specifico utente, restituisco, se
esiste, una copia shallow del dato richiesto, altrimenti viene lanciata un’eccezione checked DataNotFoundException.
Ho ipotizzato, leggendo la testa del metodo remove, che dovesse restituire il dato dopo averlo eliminato. Ho anche
ipotizzato che se il dato venisse cancellato, dovrei cancellarlo anche per tutti quelli che sono stati autorizzati ad
accedervi, dunque mi sono aiutato scrivendo il metodo swap che si occupa di questo. Restituisco poi una copia del
dato. Dal metodo copy ho poi dedotto che i doppioni fossero ammessi e dunque l’ho implementato effettuando sempre
una copia shallow ed adottando sempre i paradigmi della defensive programming. Per quanto riguarda il metodo share
l’ho interpretato come il metodo mediante il quale un utente può autorizzare un altro ad accedere ad uno specifico dato
della propria collezione, ammettendo che entrambi gli utenti esistano. Per controllare quest’ultima proprietà mi sono
aiutato con dei metodi da me scritti: checkUser e isHere. Per come è fatta questa implementazione, ho pensato che il
mio metodo share altro non dovesse fare che copiare in modo shallow il dato specificato nella collezione dell’utente con
cui condividere, dato che poi quando viene effettuata la remove elimino tutti i riferimenti a quell’oggetto. Infine, per la
getIterator, restituisco l’iteratore che generi tutti i dati dell’utente richiesto, e, per togliere la possibilità di invocare su
di esso il metodo remove, ho pensato di restituire una lista non modificabile usando un metodo della classe Collections
’UnmodifiableList’.

#### 1.1.1 User
La classe User ha 2 variabili di istanza, idUser e passwUser. In questa classe sono definiti, inoltre, i metodi utilizzati
precedentemente quali isHere e sameUser, che servono sostanzialmente a indicare se l’utente esiste già nella collezione.
Ho inoltre dovuto sovrascrivere i metodi della classe Object equals e hashCode per via di alcuni problemi nel confronto
degli oggetti.

## 2 Seconda implementazione

### 2.1 SecureDataContainerVectors

Come seconda implementazione ho pensato invece di usare 2 variabili di istanza, ovvero DBUser, che è un Vector di
User, e DBElems, che è un Vector di Dato<E>. Molte eccezioni checked sono le solite della prima implementazione,
e anche qui ho ipotizzato di effettuare una shallow copy al posto di una deep copy per motivi analoghi. Anche qui i
nominativi degli utenti sono univoci e dunque utilizzo la stessa classe User. Per il metodo createUser, analogamente
alla precedente implementazione, devo controllare che l’utente da registrare non esista già, altrimenti viene lanciata
l’eccezione checked DoubleUserException. Mi aiuto con il metodo alreadyIn da me scritto. Per la removeUser mi sono
dovuto preoccupare di eliminare anche la lista di utenti da quest’ultimo autorizzato, visto che ho supposto di dover
eliminare anche tutti i suoi dati. La getSize restituisce un contatore che verifichi quanti dati ha un utente, compresi
quelli che gli sono stati condivisi esternamente. Per quanto riguarda la put è molto simile alla prima implementazione,
con la differenza che istanzio un nuovo oggetto Dato che prenda in input il dato da inserire nella collezione. Anche nella
get di questa implementazione restituisco poi una copia shallow del dato richiesto. Stessa cosa nel metodo remove, ho
ipotizzato che dovesse restituire una copia dell’elemento una volta rimosso dalla collezione. Invoco quindi il metodo dei
Vector ’remove’ e restituisco poi un generico di appoggio che contenga la copia del riferimento del dato. Nel metodo
copy creo, anche qui, una copia shallow dell’elemento e aggiungo un altro riferimento a un nuovo Dato uguale. Nel
metodo share, invece, l’ho pensato come un metodo che gestisce le autorizzazioni, dunque aggiunge l’utente con cui
condividere, se esistente, nella lista di utenti autorizzati per quel dato. Se l’utente fosse già presente in questa lista,
viene lanciata l’eccezione checked AlreadyAuthorizedException. Per questo controllo mi aiuto con il metodo auth da
me creato. Il metodo getIterator ho supposto che debba restituire un iteratore che generi tutti i dati dell’utente, sia
quelli originali sia quelli autorizzati da terzi. Per escludere il metodo remove sull’iteratore, mi sono servito, anche qui,
del metodo di Collections ’unmodifiableList’.

#### 2.1.1 Dato<E>

La classe Dato ha 3 variabili di istanza: Owner, che corrisponde all’id del proprietario; el, che corrisponde al dato
(Generico); AuthUsers, che è un Vector di String contenente tutti gli utenti autorizzati ad accedere a quel dato. Qui
ho scritto un metodo clearAuth, che svuota la lista di utenti autorizzati, e il metodo auth, che verifica che l’utente
ricevuto come parametro sia il proprietario o un utente autorizzato, restituendo true in caso positivo e false in caso
negativo.

## 3 Batteria di test

### 3.1 TestMain

Ho pensato di creare una classe di test con al suo interno costanti e metodi statici, ciascuno dei quali esegue tutti i
metodi delle due implementazioni sia nel modo corretto, per mostrare l’effettivo output corretto, sia nel modo ”errato”,
ovvero forzando il lancio delle opportune eccezioni checked che ho creato. Ho usato tipi di oggetti differenti per mostrare
che appunto entrambe le implementazioni sono generalizzate. Ho inoltre alternato la generazione delle eccezioni (nei
vari metodi con dicitura ’WRONG’) tra i due oggetti per mostrare che effettivamente vengono lanciate correttamente
in entrambi gli oggetti. Tramite una serie di stampe si possono vedere gli effettivi risultati di tutti i metodi delle due
classi.

#### 3.1.1 Come usarla:

Per testare le implementazioni, bisogna accedere in SecureContainer/src (cd SecureContainer/src), poi bisogna com-
pilare tutti i sorgenti java (rispettivamente Dato.java, SecureDataContainer.java, SecureDataContainerHashMap.java,
SecureDataContainerVectors.java, TestMain.java, User.java e le eccezioni checked nella cartella Exceptions) in byte-
code (javac *.java Exceptions/*.java) ed eseguire il file ’TestMain.class’ (java TestMain). Dopodichè si potrà vedere
l’output opportunamente descritto mediante delle stampe su schermo.

