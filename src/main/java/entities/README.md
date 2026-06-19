Catalogo Libri Spiegazione strutturale del progetto

-SPIEGAZIONE DIAGRAMMA DRAWSQL

Per la realizzazione del progetto sono partita nel mettere su carta il mio ragionamento per poi andare a costruire il diagramma.
Ho istanziato come primo passaggio la classe madre astratta Catalogo che ha attributi comuni alle classi figlie Libro e Rivista che andranno ad estendere la madre apportando i loro specifici attributi aggiuntivi.
Ho creato un Enum per la periodicità delle riviste e ho completato lo schema iniziale del mio ragionamento.

Nel diagramma ho inserito la parte relazionale che riguarda la classe utente e il prestito; l'anello di congiunzione tra queste classi è l'elemento del catalogo. La relazione istanziata è un ManyToOne per quanto riguarda i prestiti e l'utente, in quanto più prestiti nel tempo possono essere associati ad un unico utente.

Questa relazione si traduce sul database nella generazione automatica di due chiavi esterne (FK) all'interno della tabella prestiti: utente_id e elemento_catalogo_id. Sfruttando, appunto, il polimorfismo, la chiave esterna del catalogo è in grado di puntare direttamente alla tabella madre astratta, permettendo al sistema di accettare in modo flessibile sia l'ID di un libro che quello di una rivista. In questo modo, l'entità Prestito fa da fulcro relazionale dell'intero sistema, collegando in modo pulito l'anagrafica degli utenti con l'inventario dei beni della biblioteca.