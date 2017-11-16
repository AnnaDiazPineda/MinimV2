package versioNova;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ProducManagerImpl implements ProductManager {

    HashMap<String,Integer > productosVendidos; // per contar total nom producte, numero unitats venudes
    HashMap<Integer, Comanda> todosLosPedidosEver; // per recuperar pedidos i afegir-hi productes
    HashMap<String, Usuario> usuarios; // per recuperar usuaios a partir del nom
    Listapedidos listaDePedidos; // cua de pedidos
    //no cal hash NomUsario->Array de pedidos pq Usuari tindra attribut pedidos
    HashMap<String, Producte> productosenventa;//productes disponibles
    int idsdepedido = 0;

    private static final Logger logger = LogManager.getLogger(ProductManager.class.getName());
    private static ProducManagerImpl mimundo;
    private ProducManagerImpl() {
        this.usuarios = new HashMap<String, Usuario>();
        this.productosVendidos = new HashMap<String, Integer>();
        this.listaDePedidos = new Listapedidos();
        this.todosLosPedidosEver = new HashMap<Integer, Comanda>();
        this.productosenventa = new HashMap<String, Producte>();
        Usuario inicial = new Usuario("Juan");
        this.añadirUsuario(inicial);
        Producte miProducte = new Producte("lampara", 20);
        Producte miProducte2 = new Producte("lapiz", 50);
        Producte miProducte3 = new Producte("casco", 35);
        Usuario miusuari = new Usuario("marta");
        Usuario miusuari2 = new Usuario("anna");
        this.añadirProducto(miProducte);
        this.añadirProducto(miProducte2);
        this.añadirProducto(miProducte3);
        this.añadirUsuario(miusuari);
        this.añadirUsuario(miusuari2);
        int pedidoactual = this.crearPedido(miusuari).getId();
        this.añadirProductoaPedido(miProducte3,pedidoactual);
        this.añadirProductoaPedido(miProducte3,pedidoactual);
        this.añadirProductoaPedido(miProducte2,pedidoactual);
        this.añadirProductoaPedido(miProducte2,pedidoactual);//pedido actual = 1 casco 2 lapices
        int miotropedido = this.crearPedido(miusuari2).getId();
        this.añadirProductoaPedido(miProducte,miotropedido);
        this.añadirProductoaPedido(miProducte3,miotropedido);//2 lamparas
    }
    public static ProducManagerImpl getInstance() {
        if (mimundo == null)
            mimundo = new ProducManagerImpl();
        return mimundo;
    }

    public void añadirUsuario(Usuario u) {
        if (u == null) {
            logger.error("l'usuari està buit");

        }
        Usuario t = usuarios.put(u.getNombre(), u);
        if (t != null) {
            logger.error("l'usuari ja existeix");

        } else {
            logger.info("usuari afegit");

        }
    }

    public Collection listarUsuarios(){
        return usuarios.values();
    }

    public ArrayList<Producte> productosOrdenados() {
        if(productosenventa == null)
        {
            logger.error("no hi han objectes disponibles");
            return null;
        }
        ArrayList<Producte> ordenados = new ArrayList<Producte>(productosenventa.values());
        Collections.sort(ordenados, new Comparator<Producte>() {
            public int compare(Producte product1, Producte product2) {
                Integer idea1 = new Integer(product1.getPrecio());
                Integer idea2 = new Integer(product2.getPrecio());
                return idea1.compareTo(idea2);

            }
        });
        for (int x = 0; x < ordenados.size(); x++) {
            logger.info(ordenados.get(x).getNombre());
            logger.info(ordenados.get(x).getPrecio());
        }

        return ordenados;
    }

    public Comanda crearPedido(Usuario usuari) {//una comanda pot estar buida s'omplirà mès endavant
        logger.info("numero actual de comandes = "+idsdepedido);
        Comanda nuevo = new Comanda(this.idsdepedido,usuari);
        int actual = this.idsdepedido;
        this.todosLosPedidosEver.put(nuevo.id,nuevo);
        this.idsdepedido++;
        listaDePedidos.push(nuevo);
        logger.info("numero actual de comandes = "+idsdepedido);
        return nuevo;
    }

    //@Override
    public void añadirProductoaPedido(Producte producte, int id) {
        Comanda mipedido;
        mipedido = this.todosLosPedidosEver.get(id);//recupera el pedido
        mipedido.nuevoProducto(producte);
    }
    @Override
    public boolean añadirProducto(Producte nuevo)
    {
        if (productosenventa.containsKey(nuevo.getNombre())){
            logger.error("ja hi ha ningun article amb aquest nom a la venta");
            return false;
        }
        productosenventa.put(nuevo.getNombre(),nuevo);//añadimos producto
        logger.info("el producte s'ha afegit a la venta");
        productosVendidos.put(nuevo.getNombre(),0);//inicialment ninguna venuda
        return true;
    }

    public void unidadesvendidas(Comanda p) {
        ArrayList<Producte> milista = p.getProductes();
        for (Producte nuevo:milista)
        {
            int unidadesActuales = productosVendidos.get(nuevo.getNombre());
            unidadesActuales  = unidadesActuales+1;
            productosVendidos.put(nuevo.getNombre(),unidadesActuales);

        }

    }


    public Comanda servirPedido() {
        Comanda nuevo = this.listaDePedidos.desencuaFirstPedido();
        Usuario usuari = nuevo.getUsuario();
        usuarios.get(usuari.getNombre()).mispedidos.add(nuevo);//afgim la comanda al propietari
        unidadesvendidas(nuevo);
        return nuevo;
    }

    @Override
    public ArrayList<Comanda> listarPedidos(Usuario u) {
        ArrayList<Comanda> mispedidos = u.getMispedidos();
        if(u.getMispedidos()==null)
        {
            logger.info("no s'han processat encara cap comanda");
        }
        ArrayList<Producte> productes = new ArrayList<Producte>();
        int numeroDelPedido;
        for (int x = 0; x < mispedidos.size(); x++) {
            productes = mispedidos.get(x).getProductes();
            numeroDelPedido=mispedidos.get(x).getId();
            for (Producte p: productes) {
                logger.info("numero de comanda " +numeroDelPedido+" nom del objecte venut "+p.getNombre());

            }

        }
        return u.getMispedidos();


    }



    public void productosDisponibles() {
        ArrayList<Producte> disponibles = new ArrayList<Producte>(productosenventa.values());
        for (int x = 0; x < disponibles.size(); x++) {
            logger.info(disponibles.get(x).getNombre());
        }
    }


    @Override
    public ArrayList<Venda> listarVentas()
    {
        ArrayList<Venda> vendas = new ArrayList<>();

        Iterator it = productosVendidos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,Integer> e = (Map.Entry)it.next();
            Venda v = new Venda();
            v.prod = e.getKey();
            v.unidadesVendidas =  e.getValue();
            vendas.add(v);
        }
        Collections.sort(vendas);
        for (int i = vendas.size() - 1; i >= 0; i--) {
            logger.info(vendas.get(i).prod + " " + vendas.get(i).unidadesVendidas);
        }
        return vendas;
    }

    public void reiniciarSingleton() {
        mimundo = null;
    }



}






