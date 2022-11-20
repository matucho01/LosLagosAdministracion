import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        inventario.actualizarNivelTanque(0.265f,0.78f,0.56f);

        inventario.registrarDescarga(0,6000,3000);
        System.out.println("La cantidad de combustible en los tanques en la siguiente:");
        inventario.imprimirNivelTanques();

        System.out.print("Generando Pedido");
        Pedido p = new Pedido("24-10-2022",inventario);
        p.crearPedido();
    }
}