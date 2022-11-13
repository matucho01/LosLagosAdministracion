import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Pedido {

    private float galonesSuper;
    private float galonesExtra;
    private float galonesDiesel;
    private String fechaPedido;

    public void crearPedido(){
        try{
            File archivo = new File("pedido.txt");
            if(archivo.createNewFile()){
                System.out.println("Archivo creado exitosamente.");
                FileWriter fw = new FileWriter("pedido.txt");
                List pedido =
                String pedido = "El pedido es para " + (numDias - 2) + " días: " +
                        "\nSuper: " + pedido.get(0).toString() + "00" +
                        "\nExtra: " + pedido.get(1).toString() + "00" +
                        "\nDiesel: "+ pedido.get(2).toString() + "00" );
                fw.write("hola");
                fw.close();
            }else{
                System.out.println("No se puede realizar el pedido.");
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
