package versioNova;

import java.util.ArrayList;

public class Comanda {

    public  ArrayList<Producte> productes;
    Usuario usuario;
    int id;

    public Comanda(int id, Usuario usuario){
        productes = new ArrayList<Producte>();
        this.setId(id);
        this.setUsuario(usuario);
    }

    public Comanda(){

    }

    public void nuevoProducto(Producte nuevo){
        this.productes.add(nuevo);
    }
    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public void setProductes(ArrayList<Producte> productes) {
        this.productes = productes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}

