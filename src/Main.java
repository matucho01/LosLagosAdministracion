import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        System.out.println("Nivel tanque Super: 0.265");
        System.out.println("Nivel tanque Extra: 0.78");
        System.out.println("Nivel tanque Diesel: 0.56");
        inventario.actualizarNivelTanque(0.265f,0.78f,0.56f);

        System.out.println("Descarga en el tanque Super: 0");
        System.out.println("Descarga en el tanque Extra: 6000");
        System.out.println("Descarga en el tanque Diesel: 3000");
        inventario.registrarDescarga(0,6000,3000);
        System.out.println("La cantidad de combustible en los tanques en la siguiente:");
        System.out.println("Tanque Super: "+inventario.getGalonesSuper()+"\nTanque Extra: "+inventario.getGalonesExtra()
        +"\nTanque Diesel: "+inventario.getGalonesDiesel());

        System.out.print("Generando Pedido");
        Pedido p = new Pedido("24-10-2022",inventario);
        p.crearPedido();
    }
}