package versioNova;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;


@Path("/json")
public class Rest {
    ProducManagerImpl pm = ProducManagerImpl.getInstance();

    public Rest() {
    }

    @GET
    @Path("/productes")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Producte> getProductos() {
        return pm.productosOrdenados();
    }

    @GET
    @Path("/ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Venda> listarVentas() {
        return pm.listarVentas();
    }

    @GET
    @Path("/servir")
    @Produces(MediaType.APPLICATION_JSON)
    public Comanda servir() {
        return pm.servirPedido();
    }

    @POST
    @Path("/usuaris/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public void añadirUsuario(Usuario u) {
        pm.añadirUsuario(u);
    }


    @GET
    @Path("/usuaris")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Usuario> getUsuarios() {
        return pm.listarUsuarios();
    }

    @POST
    @Path("/pedidos/new")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces({MediaType.APPLICATION_JSON})
    public Comanda crearPedido(String usuario) { /// internament fa un new
        Usuario usr = pm.usuarios.get(usuario);
        return pm.crearPedido(usr);
    }


}