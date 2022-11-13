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
                String pedido = "El pedido es para " + (registro.getNumDias() - 2) + " días: " +
                        "\nSuper: " + volumenesPedido.get(0).toString() + "00" +
                        "\nExtra: " + volumenesPedido.get(1).toString() + "00" +
                        "\nDiesel: "+ volumenesPedido.get(2).toString() + "00" +
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
