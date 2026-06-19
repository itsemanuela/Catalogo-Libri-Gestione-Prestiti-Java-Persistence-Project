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