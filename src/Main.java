import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Supermercado supermercado = new Supermercado();
        supermercado.cargarDatos();
        supermercado.mostrarResultados(supermercado.resultado());
    }
}