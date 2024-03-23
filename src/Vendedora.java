import java.util.LinkedList;

public class Vendedora {
    private int cantidadVentas;
    private LinkedList<Integer> montoVentas;

    public Vendedora(int cantidadVentas, LinkedList<Integer> montoVentas) {
        this.cantidadVentas = cantidadVentas;
        this.montoVentas = montoVentas;
    }

    public int getMontoTotalVentasConsecutivas(int ventasConsecutivas) {
        int total = 0;
        for(int i = 0; i < ventasConsecutivas; i++) {
            total += montoVentas.get(i);
        }
        return total;
    }

    public boolean validacionConValorConsecutivo(int valorConsecutivoMod) {
        boolean esValido = true;
        if (valorConsecutivoMod > this.cantidadVentas) {
            esValido=false;
        }
        return esValido;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }
    public LinkedList<Integer> getMontoVentas() {
        return montoVentas;
    }
}

