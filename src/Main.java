import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        inventario.actualizarNivelTanque(0.265f,0.78f,0.56f);

        inventario.registrarDescarga(0,6000,3000);
        inventario.imprimirNivelTanques();

        System.out.print("Generando Pedido");
        Pedido p = new Pedido("24-10-2022",inventario);
        p.crearPedido();
    }
}