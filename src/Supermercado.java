import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Supermercado {

    private int ventasConsecutivas;
    private int cantidadVendedoras;
    private LinkedList<Vendedora> vendedoras = new LinkedList<>();

    public void cargarDatos() throws IOException
    {
        try(BufferedReader in = new BufferedReader(new FileReader("src/Input.txt")))
        {
            String line;
            LinkedList<Integer> datosInput = new LinkedList<>();
            while ((line = in.readLine()) != null) {
                datosInput.add(Integer.parseInt(line));
            }

            cantidadVendedoras = datosInput.getFirst();
            datosInput.remove(0);
            ventasConsecutivas = datosInput.getLast();
            datosInput.remove(datosInput.indexOf(datosInput.getLast()));

            int indice=0;
            for(int vendedora = 0; vendedora < cantidadVendedoras; vendedora++)
            {
                int cantidadVentas = datosInput.get(indice);
                LinkedList<Integer> montoVentas = new LinkedList<>();
                for(int venta = 1; venta <= cantidadVentas; venta++)
                {
                    montoVentas.add(datosInput.get(indice+venta));
                }
                indice= indice +cantidadVentas+1;
                montoVentas.sort((o1, o2) -> o2.compareTo(o1));
                vendedoras.add(new Vendedora(cantidadVentas, montoVentas));
            }
            //vendedoras.forEach(vendedora -> System.out.println(vendedora.getMontoVentas()));
        }
    }

    public void resultado()
    {
        int opcionGanador=0;
        int valorConsecutivoMod = this.ventasConsecutivas;
        int valorGanador=0;
        int indiceGanador=0;
        int finValidacion=0;

        while(opcionGanador!=1 && finValidacion!=this.cantidadVendedoras)
        {
            for(Vendedora vendedora : vendedoras)
            {
                if(vendedora.validacionConValorConsecutivo(valorConsecutivoMod))
                {
                    if(vendedora.getMontoTotalVentasConsecutivas(valorConsecutivoMod) > valorGanador)
                    {
                        valorGanador = vendedora.getMontoTotalVentasConsecutivas(valorConsecutivoMod);
                        indiceGanador = vendedoras.indexOf(vendedora);
                        opcionGanador = 1;
                        //System.out.println(valorGanador);
                        //System.out.println(indiceGanador);
                    } else if (vendedora.getMontoTotalVentasConsecutivas(valorConsecutivoMod) == valorGanador) {
                        opcionGanador++;
                        //System.out.println(valorGanador);
                        //System.out.println(indiceGanador);
                    }

                }
                else{
                    finValidacion++;
                }
            }
            valorConsecutivoMod++;
        }

        if(opcionGanador==1)
        {
            System.out.println(indiceGanador);
        } else if (opcionGanador==0) {
            System.out.println("No hay ganadores");
        }
        else {
            System.out.println("No se puede desempatar");
        }
    }

}
