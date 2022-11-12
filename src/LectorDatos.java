import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectorDatos {
    public List leerDatos(String fecha) {
        List consumoDiario = new ArrayList<String>();
        String line = "";
        String splitBy = ",";
        try{
            BufferedReader br = new BufferedReader(new FileReader("/Lecturas/lecturas.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] datos = line.split(splitBy);    // use comma as separator
                //System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2] + ", Contact=" + employee[3] + ", Salary= " + employee[4] + ", City= " + employee[5] +"]");
                if(datos[0].equals(fecha)){
                    consumoDiario.add(datos[1]);
                    consumoDiario.add(datos[2]);
                    consumoDiario.add(datos[3]);
                }
            }
        }catch (IOException e){
            System.out.println("No se encuentra el archivo de lecturas: " + e);
        }
        return consumoDiario;
    }
}
