package versioNova;

public class Producte {
    String nombre;
    int precio;
    public Producte()
    {
        this.precio =0;

    }
    public Producte(String nombre, int precio)
    {
        this.precio = precio;
        this.nombre = nombre;
    }
    public String getNombre(){return nombre;}
    public int getPrecio(){return precio;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPrecio(int precio){this.precio = precio;}
}
