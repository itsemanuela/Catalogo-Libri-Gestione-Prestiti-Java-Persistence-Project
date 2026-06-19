Catalogo Libri Spiegazione strutturale del progetto

-SPIEGAZIONE DIAGRAMMA DRAWSQL

Per la realizzazione del progetto sono partita nel mettere su carta il mio ragionamento per poi andare a costruire il diagramma.
Ho istanziato come primo passaggio la classe madre astratta Catalogo che ha attributi comuni alle classi figlie Libro e Rivista che andranno ad estendere la madre apportando i loro specifici attributi aggiuntivi.
Ho creato un Enum per la periodicità delle riviste e ho completato lo schema iniziale del mio ragionamento.

Nel diagramma ho inserito la parte relazionale che riguarda la classe utente e il prestito; l'anello di congiunzione tra queste classi è l'elemento del catalogo. La relazione istanziata è un ManyToOne per quanto riguarda i prestiti e l'utente, in quanto più prestiti nel tempo possono essere associati ad un unico utente.

Questa relazione si traduce sul database nella generazione automatica di due chiavi esterne (FK) all'interno della tabella prestiti: utente_id e elemento_catalogo_id. Sfruttando, appunto, il polimorfismo, la chiave esterna del catalogo è in grado di puntare direttamente alla tabella madre astratta, permettendo al sistema di accettare in modo flessibile sia l'ID di un libro che quello di una rivista. In questo modo, l'entità Prestito fa da fulcro relazionale dell'intero sistema, collegando in modo pulito l'anagrafica degli utenti con l'inventario dei beni della biblioteca.


-SCELTA DELLA JOINED TABLE APPLICATION

La strategia JOINED mi crea tre tabelle separate nel database: una tabella madre (elementi_catalogo) per i dati comuni come titolo e ISBN, e due tabelle figlie (libri e riviste) che contengono solo i loro dati specifici. Sono collegate tra loro tramite lo stesso ID (UUID).

Ho scelto questa strada per tre motivi principali:

Database pulito: Ogni tabella contiene solo quello che serve davvero. Non ci sono righe duplicate o spazi vuoti duplicati nel database.

Obbligo di inserire i dati importanti (Vincolo NOT NULL): Se avessi usato un'unica grande tabella, i campi come "autore" o "periodicità" avrebbero dovuto accettare valori vuoti (null), perché una rivista non ha un autore e un libro non ha una periodicità. Separando le tabelle, ho potuto imporre al database che un libro deve avere sempre un autore e una rivista deve avere una periodicità, bloccando gli errori all'origine.

Flessibilità: Se in futuro la biblioteca decidesse di aggiungere i DVD, mi basterà creare una nuova classe Java e una nuova tabella dvd. Le tabelle esistenti di libri e riviste non verranno toccate o modificate in alcun modo.


-STRATEGIA DI PERSISTENZA E TRANSACTION

Per assicurarmi che il salvataggio dei dati non crei mai conflitti o errori, ho gestito tutte le operazioni di persistenza usando le EntityTransaction di JPA.

Nel concreto, ho implementato un flusso di lavoro che segue dei passaggi ben precisi: ogni salvataggio (come la creazione di un nuovo prestito) è racchiuso tra un begin(), il persist() e il commit(). Questo mi permette di avere la certezza che, se dovesse capitare un qualsiasi intoppo durante l'inserimento dei dati (ad esempio un'informazione mancante o un errore di connessione), il database non rimanga con dati parziali o 'sporchi'.

La struttura generale delle entità è ben distribuita attraverso packages dedicati.

CUSTOM EXCEPTION

Oltre all'integrità garantita dalle transazioni, ho implementato un sistema di gestione degli errori basato su eccezioni custom:
ho creato classi specifiche che definiscono esattamente il tipo di fallimento riscontrato, come nel caso di ricerche di prestiti inesistenti o tentativi di operare su entità non valide.

Nel Main, il blocco try-catch intercetta tempestivamente queste anomalie, permettendo al programma di gestire il fallimento con messaggi informativi e precisi, evitando crash improvvisi e garantendo che l'utente capisca sempre cosa è andato storto nella sua richiesta.

Infine mi sono dedicata alla creazione, prova e modifica di tutti i metodi che ho implementato e risolto numerosi bug (impegnativi) che mi si sono presentati per la duplicazione degli elementi e la loro univocità.


Generando il diagramma sql automatico del DB possiamo notare 5 tabelle, lo schema automatico inserisce le tabelle in quanto gestisce l'architettura del database.
Parallelamente, la struttura del catalogo segue il pattern di ereditarietà JOINED: la tabella Catalogo centralizza le informazioni comuni, mentre Libro e Riviste estendono il modello con attributi specifici, quindi li ho inseriti nel diagramma per visualizzazione complessiva.
Per cui visualizzo il rapporto tra Libro e Catalogo 1:1 come per Rivista e Catalogo,
ma non è da intendere su base relazionale bensì di ereditarieta.