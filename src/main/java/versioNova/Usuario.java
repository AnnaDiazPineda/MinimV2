package versioNova;

import java.util.ArrayList;

public class Usuario {
    String nombre;
    public ArrayList<Comanda> mispedidos;
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}



    public Usuario(String nombre) {
        this.nombre = nombre;
        this.mispedidos = new ArrayList<Comanda>();

    }
    public Usuario() {

    }

    public ArrayList<Comanda> getMispedidos() {return mispedidos;}
    public void setMispedidos(ArrayList<Comanda> mispedidos) {this.mispedidos = mispedidos;}
}
