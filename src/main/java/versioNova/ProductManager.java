package versioNova;

import java.util.ArrayList;

public interface ProductManager {
    public ArrayList<Producte> productosOrdenados();

    public boolean añadirProducto(Producte miproducto);

    public void productosDisponibles();

    public Comanda crearPedido(Usuario usuari);

    public void añadirProductoaPedido(Producte miproducto, int id);

    public Comanda servirPedido();

    public ArrayList<Comanda> listarPedidos(Usuario u);

    public ArrayList<Venda> listarVentas();
}

