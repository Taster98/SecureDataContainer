import Exceptions.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class TestMain {
    //Creazione dei due oggetti
    private static SecureDataContainerHashMap hashTest = new SecureDataContainerHashMap<>();
    private static SecureDataContainerVectors vectTest = new SecureDataContainerVectors<>();
    //Password degli utenti testati
    private static final String LUIGGI_PSW = "ciao123";
    private static final String FULVIO_PSW = "cavallo12";
    private static final String GIOVANNI_PSW = "aquilone13";
    private static final String ALESSANDRO_PSW = "Peschereccio!";
    //Oggetti di tipo diverso usati per i test
    private static final Integer o1 = 4;
    private static final Double o2 = 3.56;
    private static Date o3 = new Date();//data odierna
    private static final String o4 = "Dato 4";
    //TEST PER LA CREATEUSER
    public static void testCreateOK(){
        try{
            System.out.println("Test creazione utente 'luigi' con HashMap:");
            hashTest.createUser("luigi",LUIGGI_PSW);
            System.out.println("- OK");
            System.out.println("Test creazione utente 'giovanni' con HashMap:");
            hashTest.createUser("giovanni",GIOVANNI_PSW);
            System.out.println("- OK");
            System.out.println("Test creazione utente 'fulvio' con Vectors:");
            vectTest.createUser("fulvio",FULVIO_PSW);
            System.out.println("- OK");
            System.out.println("Test creazione utente 'alessandro' con Vectors:");
            vectTest.createUser("alessandro",ALESSANDRO_PSW);
            System.out.println("- OK");
        }catch(DoubleUserException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked DoubleUserException
    public static void testCreateWRONG(){
        try{
            System.out.println("Test creazione utente doppione con HashMap:");
            hashTest.createUser("luigi",LUIGGI_PSW);
        }catch(DoubleUserException e){
           System.out.println(e);
        }
    }
    //TEST PER LA REMOVEUSER
    public static void testRemoveOK(){
        try{
            System.out.println("Test rimozione utente 'luigi' con HashMap:");
            hashTest.RemoveUser("luigi",LUIGGI_PSW);
            System.out.println("- OK");
            System.out.println("Test rimozione utente 'fulvio' con Vectors:");
            vectTest.RemoveUser("fulvio",FULVIO_PSW);
            System.out.println("- OK");
        }catch(NoUserException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked NoUserException
    public static void testRemoveWRONG(){
        try{
            System.out.println("Test rimozione utente inesistente con Vectors:");
            vectTest.RemoveUser("mario",LUIGGI_PSW);
        }catch(NoUserException e){
           System.out.println(e);
        }
    }
    //TEST PER LA DIMENSIONE
    public static void testGetSizeOK(){
        try{
            System.out.println("Test numero elementi utente 'luigi' con HashMap:");
            System.out.println(hashTest.getSize("luigi",LUIGGI_PSW));
            System.out.println("Test numero elementi utente 'fulvio' con Vectors:");
            System.out.println(vectTest.getSize("fulvio",FULVIO_PSW));
            System.out.println("Test numero elementi utente 'giovanni' con HashMap:");
            System.out.println(hashTest.getSize("giovanni",GIOVANNI_PSW));
            System.out.println("Test numero elementi utente 'alessandro' con Vectors:");
            System.out.println(vectTest.getSize("alessandro",ALESSANDRO_PSW));
        }catch(NoUserException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked NoUserException
    public static void testGetSizeWRONG(){
        try{
            System.out.println("Test numero elementi utente inesistente con HashMap:");
            System.out.println(hashTest.getSize("mario",LUIGGI_PSW));
        }catch(NoUserException e){
           System.out.println(e);
        }
    }
    //TEST PER L'INSERZIONE
    public static void testPutOK(){
        try{
            System.out.println("Test inserzione dato 1 nella collezione di 'luigi' con HashMap:");
            hashTest.put("luigi",LUIGGI_PSW,o1);
            System.out.println("- OK");
            System.out.println("Test inserzione dato 2 nella collezione di 'fulvio' con Vectors:");
            vectTest.put("fulvio",FULVIO_PSW,o2);
            System.out.println("- OK");
            System.out.println("Test inserzione dato 3 nella collezione di 'luigi' con HashMap:");
            hashTest.put("luigi",LUIGGI_PSW,o3);
            System.out.println("- OK");
            System.out.println("Test inserzione dato 4 nella collezione di 'fulvio' con Vectors:");
            vectTest.put("fulvio",FULVIO_PSW,o4);
            System.out.println("- OK");
        }catch(NoUserException e){
           System.out.println(e);
        }
    }

    //TEST PER L'ACQUISIZIONE
    public static void testGetOK(){
        try{
            System.out.println("Test acquisizione 4 dalla collezione di 'luigi' con HashMap:");
            hashTest.get("luigi",LUIGGI_PSW,o1);
            System.out.println("Test acquisizione 3.56 dalla collezione di 'fulvio' con Vectors:");
            vectTest.get("fulvio",FULVIO_PSW,o2);
        }catch(NoUserException | DataNotFoundException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked DataNotFoundException
    public static void testGetWRONG(){
        try{
            System.out.println("Test acquisizione dato non presente dalla collezione di 'fulvio' con Vectors:");
            vectTest.get("fulvio",FULVIO_PSW,o1);
        }catch(NoUserException | DataNotFoundException e){
           System.out.println(e);
        }
    }
    //TEST PER LA RIMOZIONE DI UN DATO
    public static void testRemoveElOK(){
        try{
            System.out.println("Test rimozione 4 dalla collezione di 'luigi' con HashMap:");
            hashTest.remove("luigi",LUIGGI_PSW,o1);
            System.out.println("- OK");
            System.out.println("Test rimozione 3.56 dalla collezione di 'fulvio' con Vectors:");
            vectTest.remove("fulvio",FULVIO_PSW,o2);
            System.out.println("- OK");
        }catch(NoUserException | DataNotFoundException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked DataNotFoundException
    public static void testRemoveElWRONG(){
        try{
            System.out.println("Test rimozione oggetto non presente dalla collezione di 'luigi' con HashMap:");
            hashTest.remove("luigi",LUIGGI_PSW,o2);
        }catch(NoUserException | DataNotFoundException e){
           System.out.println(e);
        }
    }
    //TEST PER LA COPIA DI UN DATO
    public static void testCopyOK(){
        try{
            System.out.println("Test copia 4 nella collezione di 'luigi' con HashMap:");
            hashTest.copy("luigi",LUIGGI_PSW,o1);
            System.out.println("- OK");
            System.out.println("Test copia 3.56 nella collezione di 'fulvio' con Vectors:");
            vectTest.copy("fulvio",FULVIO_PSW,o2);
            System.out.println("- OK");
        }catch(NoUserException | DataNotFoundException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked DataNotFoundException
    public static void testCopyWRONG(){
        try{
            System.out.println("Test copia elemento inesistente nella collezione di 'fulvio' con Vectors:");
            vectTest.copy("fulvio",FULVIO_PSW,o1);
        }catch(NoUserException | DataNotFoundException e){
           System.out.println(e);
        }
    }
    //TEST PER LA CONDIVISIONE DI UN DATO
    public static void testShareOK(){
        try{
            System.out.println("Test condivisione "+ o3 +" nella collezione di 'giovanni' da 'luigi' con HashMap:");
            hashTest.share("luigi",LUIGGI_PSW,"giovanni",o3);
            System.out.println("- OK");
            System.out.println("Test condivisione "+o4+" nella collezione di 'alessandro' da 'fulvio' con Vectors:");
            vectTest.share("fulvio",FULVIO_PSW,"alessandro",o4);
            System.out.println("- OK");
        }catch(NoUserException | DataNotFoundException | AlreadyAuthorizedException e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked AlreadyAuthorizedException
    public static void testShareWRONG(){
        try{
            System.out.println("Test condivisione con utente già autorizzato con Vectors:");
            vectTest.share("fulvio",FULVIO_PSW,"alessandro",o4);
        }catch(NoUserException | DataNotFoundException | AlreadyAuthorizedException e){
           System.out.println(e);
        }
    }
    //TEST PER L'ITERATORE
    //Metodo che stampa l'iterator
    private static void stampaIt(Iterator a){
        while (a.hasNext()){
            System.out.println("- "+a.next());
        }
    }
    public static void testGetIteratorOK(){
        try{
            System.out.println("Test stampa iteratore della collezione di 'luigi' con HashMap:");
            System.out.println("Elementi di luigi:");
            stampaIt(hashTest.getIterator("luigi",LUIGGI_PSW));
            System.out.println("Test stampa iteratore della collezione di 'fulvio' con Vectors:");
            System.out.println("Elementi di fulvio:");
            stampaIt(vectTest.getIterator("fulvio",FULVIO_PSW));
            System.out.println("Test stampa iteratore della collezione di 'giovanni' con HashMap:");
            System.out.println("Elementi di giovanni:");
            stampaIt(hashTest.getIterator("giovanni",GIOVANNI_PSW));
            System.out.println("Test stampa iteratore della collezione di 'alessandro' con Vectors:");
            System.out.println("Elementi di alessandro:");
            stampaIt(vectTest.getIterator("alessandro",ALESSANDRO_PSW));
        }catch(NoUserException  e){
           System.out.println(e);
        }
    }
    //Questo metodo genera l'eccezione checked NoUserException
    public static void testGetIteratorWRONG(){
        try{
            System.out.println("Test stampa iteratore della collezione di utente inesistente con HashMap:");
            System.out.println("Elementi di luigi:");
            stampaIt(hashTest.getIterator("mario",LUIGGI_PSW));
            System.out.println("Test stampa iteratore della collezione di utente inesistente con Vectors:");
            System.out.println("Elementi di fulvio:");
            stampaIt(vectTest.getIterator("pippo",FULVIO_PSW));
        }catch(NoUserException  e){
           System.out.println(e);
        }
    }
    //Qui lancio per ogni metodo prima quelli che non producono errori poi quelli che invece lanciano le eccezioni checked
    public static void main(String[] args){
        System.out.println("INIZIO BATTERIA TEST\n");
        System.out.println("Creazione utenti senza errori:");
        testCreateOK();
        System.out.println("\nCreazione utenti con errori:");
        testCreateWRONG();

        System.out.println("\nAggiunta elementi senza errori:");
        testPutOK();
        //1 sta per prima stampa effettuata
        System.out.println("\nStampa elementi 1 senza errori:");
        testGetIteratorOK();
        System.out.println("\nStampa elementi 1 con errori:");
        testGetIteratorWRONG();

        System.out.println("\nNumero degli elementi senza errori:");
        testGetSizeOK();
        System.out.println("\nNumero degli elementi con errori:");
        testGetSizeWRONG();

        System.out.println("\nOttiene elementi senza errori:");
        testGetOK();
        System.out.println("\nOttiene elementi con errori:");
        testGetWRONG();

        System.out.println("\nCopia elementi senza errori:");
        testCopyOK();
        System.out.println("\nCopia elementi con errori:");
        testCopyWRONG();
        //2 sta per seconda stampa effettuata
        System.out.println("\nStampa elementi 2 senza errori:");
        testGetIteratorOK();
        System.out.println("\nStampa elementi 2 con errori:");
        testGetIteratorWRONG();

        System.out.println("\nCondivisione elementi senza errori:");
        testShareOK();
        System.out.println("\nCondivisione elementi con errori:");
        testShareWRONG();
        //3 sta per terza stampa effettuata
        System.out.println("\nStampa elementi 3 senza errori:");
        testGetIteratorOK();
        System.out.println("\nStampa elementi 3 con errori:");
        testGetIteratorWRONG();

        System.out.println("\nRimozione elementi senza errori:");
        testRemoveElOK();
        System.out.println("\nRimozione elementi con errori:");
        testRemoveElWRONG();
        //4 sta per quarta stampa effettuata
        System.out.println("\nStampa elementi 4 senza errori:");
        testGetIteratorOK();
        System.out.println("\nStampa elementi 4 con errori:");
        testGetIteratorWRONG();

        System.out.println("\nRimozione utenti senza errori:");
        testRemoveOK();
        System.out.println("\nRimozione utenti con errori:");
        testRemoveWRONG();
        System.out.println("\nFINE BATTERIA TEST");
    }
}