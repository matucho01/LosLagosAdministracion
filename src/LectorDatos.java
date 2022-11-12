import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectorDatos {
    public List leerDatos(String fecha) {
        List consumoDiario = new ArrayList<String>();
        String line = "";
        String splitBy = ",";
        int posicion = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("/Lecturas/lecturas.csv"));
            line = br.readLine();
            String[] fechas = line.split(splitBy);
            for(int i=0; i< fechas.length; i++) {
                if(fechas[i].equals(fecha)){
                    posicion = i;
                }
            }
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] datos = line.split(splitBy);    // use comma as separator
                //System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2] + ", Contact=" + employee[3] + ", Salary= " + employee[4] + ", City= " + employee[5] +"]");
                consumoDiario.add(datos[posicion]);
            }
        }catch (IOException e){
            System.out.println("No se encuentra el archivo de lecturas: " + e);
        }
        return consumoDiario;
    }
}
