package versioNova;

public class Productocantidad {
    int cantidad;
    String nombreproducto;
    public Productocantidad()
    {

    }
    public Productocantidad(String nombre, int cantidad)
    {
        this.cantidad = cantidad;
        this.nombreproducto = nombre;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }
}
