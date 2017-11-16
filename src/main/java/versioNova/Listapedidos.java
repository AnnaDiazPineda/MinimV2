package versioNova;

public class Listapedidos {
    public class Nodo{
        Nodo siguiente;
        Comanda contenido;
    }
int numeropedidos;
    Nodo first;
    public Listapedidos(){}

    public void push(Comanda comanda)
    {
        Nodo nuevo = new Nodo();
        nuevo.contenido = comanda;
        Nodo ultimo = getUltim();
        if(ultimo == null){
            first = nuevo;
        }else {
            ultimo.siguiente = nuevo;
        }
    }

    public Comanda desencuaFirstPedido(){
        Nodo aretornar = this.first;
        if(aretornar == null){
            return null;
        }else {
            Nodo futuroprimero = aretornar.siguiente;
            this.first = futuroprimero;
            return aretornar.contenido;
        }
    }

    public Nodo getUltim()
    {
        Nodo actual = this.first;
        if(this.first == null){
            return null;
        }
        int i =0;
        while(actual.siguiente != null)
        {
            i++;
            actual = actual.siguiente;
        }
        return actual;
    }
}
