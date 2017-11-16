import versioNova.ProducManagerImpl;
import versioNova.Producte;
import versioNova.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;

public class mitest {
    ProducManagerImpl singleton;
    private static final Logger logger = LogManager.getLogger(mitest.class.getName());
    @Before
        public void setUp()
        {
            singleton = singleton.getInstance();
        }
    @After
    public void tearDown()
    {
        singleton.getInstance().reiniciarSingleton();
    }

    @Test
    public void testProducteDuplicat()
    { logger.info("iniciem test1");
        Producte miProducte = new Producte("tasa", 20);
        singleton.añadirProducto(miProducte);
        singleton.añadirProducto(miProducte);
        assertEquals(false, singleton.añadirProducto(miProducte));

    }
    @Test
    public void testUsuariRepetit(){
        Usuario comprador = new Usuario("anna");
        singleton.añadirUsuario(comprador);
        singleton.añadirUsuario(comprador);
        assertEquals(1, singleton.listarUsuarios().size());

    }
    @Test
    public void testRealitzarComanda(){
        int pedidoactual;
        Producte p1 = new Producte("tasa", 5);
        Producte p2 = new Producte("lapiz", 25);
        Producte p3 = new Producte("casco", 35);
        Usuario miusuari = new Usuario("anna");
        singleton.añadirProducto(p1);
        singleton.añadirProducto(p2);
        singleton.añadirProducto(p3);
        assertEquals(false, singleton.añadirProducto(p1));

        singleton.añadirUsuario(miusuari);
        pedidoactual = singleton.crearPedido(miusuari).getId();
        singleton.añadirProductoaPedido(p3,pedidoactual);
        singleton.añadirProductoaPedido(p2,pedidoactual);
        singleton.añadirProductoaPedido(p2,pedidoactual);//pedido actual = 1 casco 2 lapices
        singleton.listarPedidos(miusuari);//veiem les comandes que ens han processat
        singleton.servirPedido();
        singleton.listarPedidos(miusuari);
    }
    @Test
    public void test()
{   int pedidoactual;
    int miotropedido;
        Producte miProducte = new Producte("tasa", 20);
        Producte miProducte2 = new Producte("lapiz", 50);
        Producte miProducte3 = new Producte("casco", 35);
        Usuario miusuari = new Usuario("marta");
        Usuario miusuari2 = new Usuario("anna");
        singleton.añadirProducto(miProducte);
        singleton.añadirProducto(miProducte2);
        singleton.añadirProducto(miProducte3);
        singleton.añadirUsuario(miusuari);
        singleton.añadirUsuario(miusuari2);
        pedidoactual = singleton.crearPedido(miusuari).getId();
        singleton.añadirProductoaPedido(miProducte3,pedidoactual);
        singleton.añadirProductoaPedido(miProducte3,pedidoactual);
        singleton.añadirProductoaPedido(miProducte2,pedidoactual);
        singleton.añadirProductoaPedido(miProducte2,pedidoactual);//pedido actual = 1 casco 2 lapices
        miotropedido = singleton.crearPedido(miusuari2).getId();
        singleton.añadirProductoaPedido(miProducte,miotropedido);
        singleton.añadirProductoaPedido(miProducte3,miotropedido);//2 taes
        singleton.servirPedido();
        logger.info("Hem servit la primera comanda");
        singleton.listarPedidos(miusuari);
        singleton.listarVentas();
        singleton.servirPedido();
        logger.info("Hem servit la segona comanda");
        singleton.listarPedidos(miusuari2);
        singleton.listarVentas();


    }
}

