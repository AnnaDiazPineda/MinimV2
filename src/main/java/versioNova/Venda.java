package versioNova;
public class Venda implements Comparable{
    public Venda(){}
    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public Integer getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    String prod;
    Integer unidadesVendidas;

    @Override
    public int compareTo(Object o) {
        Venda obj = (Venda)o;
        if( this.unidadesVendidas < obj.unidadesVendidas){
            return -1;
        }
        if( this.unidadesVendidas == obj.unidadesVendidas){
            return 0;
        }
        return 1;
    }
}
