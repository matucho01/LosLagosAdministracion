import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Pedido {

    private float galonesSuper;
    private float galonesExtra;
    private float galonesDiesel;
    private String fechaPedido;

    public Pedido(String fecha){
        this.fechaPedido = fecha;
    }

    public void crearPedido(){
        try{
            File archivo = new File("pedido.txt");
            if(archivo.createNewFile()){
                System.out.println("Archivo creado exitosamente.");
                FileWriter fw = new FileWriter("pedido.txt");
                Registro registro = new Registro();
                List volumenesPedido = registro.proyectarVentas(this.fechaPedido);
                this.galonesSuper = (float)volumenesPedido.get(0);
                this.galonesExtra = (float)volumenesPedido.get(1);
                this.galonesDiesel = (float)volumenesPedido.get(2);

                String pedido = "El pedido es para " + (registro.getNumDias() - 2) + " días: " +
                        "\nSuper: " + this.galonesSuper + "00" +
                        "\nExtra: " + this.galonesExtra + "00" +
                        "\nDiesel: "+ this.galonesDiesel + "00" +
                        "\nRevisado por: Gerente";
                fw.write(pedido);
                fw.close();
            }else{
                System.out.println("No se puede realizar el pedido.");
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
