# MagnaApp
OOP_project

## Realizzazione del progetto

L'applicazione si apre con una fase di login/signup in cui l'utente deve accedere

### LoginActivity

<img src="/images/LoginActivity.png" alt="drawing" width="200"/>

Le due tab di login e signup sono state messe all'interno di un *tabLayout*.

Una volta eseguito il login si accede a quella che è l'activity principale dell'applicazione.

### MenuActivity

<img src="/images/MenuActivity.png" alt="drawing" width="200"/>

Tale activity è composta da una *BottomNavigationView* con 4 pulsanti a cui è collegato un *NavHostFragment* che cambia il fragment da visualizzare in base 
alla voce che si sceglie nella *BottomNavigationView*

Nel primo *Fragment* è stata inserita una *RecyclerView* che mostra la suddivisione per categoria dei prodotti all'interno del magazzino
NB: Questo fragment è disponibile solo per i dipendenti del ristorante.

Nel secondo *Fragment* è stata inserita una *RecyclerView* che mostra la suddivisione per categoria delle cose ordinabili dal menu

Nel terzo *Fragment* verrà inserito (forse tramite una recyclerView) lo storico degli ordini effettuati dall'utente
come nell'immagine riportata sotto

<img src="/images/esempio_ordini.jpeg" alt="drawing" width="400"/>

