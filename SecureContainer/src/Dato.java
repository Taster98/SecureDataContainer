import java.util.*;
public class Dato<E> {
    private String Owner;
    private E el;
    private List<String> AuthUsers;

    public Dato(String ow, E el){
        if(ow == null || el == null) throw new NullPointerException();
        if(ow.isEmpty()) throw new IllegalArgumentException();
        Owner = ow;
        this.el = el;
        AuthUsers = new Vector<>();
    }
    //Metodi setter e getter utilizzati
    public List<String> getAuth(){
        return AuthUsers;
    }
    public void setAuth(String name){
        AuthUsers.add(name);
    }

    public E getEl(){
        return el;
    }
    public String getOwner(){
        return Owner;
    }

    //metodo che svuota la lista degli autorizzati
    public void clearAuth(){
       AuthUsers.clear();
    }

    //metodo che verifica che l'utente sia autorizzato o il proprietario (true), false altrimenti
    public boolean auth(String ow){
        return (AuthUsers.contains(ow) || ow.equals(Owner));
    }

}
