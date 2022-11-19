import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Pedido {

    private float galonesSuper;
    private float galonesExtra;
    private float galonesDiesel;
    private String fechaPedido;
    private Inventario inventario;

    public Pedido(String fecha,Inventario inventario){
        this.fechaPedido = fecha;
        this.inventario = inventario;
    }

    public void crearPedido() {
        if (crearArchivo() != null) {
            try {
                FileWriter fw = new FileWriter(crearArchivo());
                Registro registro = new Registro();
                List volumenesPedido = registro.proyectarVentas(this.fechaPedido, this.inventario.getGalonesSuper(), this.inventario.getGalonesExtra(), this.inventario.getGalonesDiesel());
                this.galonesSuper = (float) volumenesPedido.get(0);
                this.galonesExtra = (float) volumenesPedido.get(1);
                this.galonesDiesel = (float) volumenesPedido.get(2);

                String pedido = "El pedido es para " + (registro.getNumDias() - 2) + " días: " +
                        "\nSuper: " + this.galonesSuper + "00" +
                        "\nExtra: " + this.galonesExtra + "00" +
                        "\nDiesel: " + this.galonesDiesel + "00" +
                        "\nRevisado por: Gerente";

                fw.write(pedido);
                fw.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se puede crear pedido");
        }
    }

    public String crearArchivo() {
        try {
            String nombreArchivo = "pedido"+fechaPedido+".txt";
            File archivo = new File(nombreArchivo);
            if(archivo.createNewFile()) {
                System.out.println("Archivo creado exitosamente");
                return nombreArchivo;
            }else{
                System.out.println("No se puede realizar el pedido");
                return null;
            }
        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
