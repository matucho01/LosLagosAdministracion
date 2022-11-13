import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorDatos {
    public List leerDatos(String fecha) {
        List consumoDiario = new ArrayList<String>();
        String line = "";
        String splitBy = ",";
        int posicion = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("Lecturas/lecturas_octubre.csv"));
            line = br.readLine();
            String[] fechas = line.split(splitBy);
            for(int i=0; i< fechas.length; i++) {
                if(fechas[i].equals(fecha)){
                    posicion = i;
                }
            }
            while ((line = br.readLine()) != null)
            {
                String[] datos = line.split(splitBy);
                consumoDiario.add(datos[posicion]);
            }
        }catch (IOException e){
            System.out.println("No se encuentra el archivo de lecturas: " + e);
        }
        return consumoDiario;
    }
}
