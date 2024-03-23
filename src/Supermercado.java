import java.io.*;
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

    public int[] resultado()
    {
        int resultado[]=new int[3];
        int opcionGanador=0;
        int valorConsecutivoMod = this.ventasConsecutivas;
        int valorGanador=0;
        int indiceGanador=0;
        int valorConsecutivoGanador=0;
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
                        valorConsecutivoGanador = valorConsecutivoMod;
                    } else if (vendedora.getMontoTotalVentasConsecutivas(valorConsecutivoMod) == valorGanador) {
                        opcionGanador++;
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
            resultado = new int[]{opcionGanador,indiceGanador, valorConsecutivoGanador};
        } else if (opcionGanador==0) {
            resultado = new int[]{opcionGanador,0, 0};
        }
        else {
            resultado= new int[]{opcionGanador,0,0};
        }
        return resultado;
    }

    public void mostrarResultados(int[] resultado) throws IOException
    {
        try(BufferedWriter out = new BufferedWriter(new FileWriter("src/Output.txt")))
        {
            if(resultado[0]==1)
            {
                int vendedoraGanadora = resultado[1]+1;
                String line2 = resultado[2] +" " +vendedoras.get(resultado[1]).getMontoTotalVentasConsecutivas(resultado[2]);
                out.write(vendedoraGanadora +"\n");
                out.write(line2);
            } else if (resultado[0]==0) {
                out.write("No hay ganadores");
            }
            else {
                out.write("No se puede desempatar");
            }
        }
    }

}
