import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventario inventario = new Inventario();
        System.out.println("Digite el nivel de los tanques: ");
        System.out.print("Nivel tanque Super: ");
        float nivelSuper = Float.parseFloat(sc.nextLine());
        System.out.print("Nivel tanque Extra: ");
        float nivelExtra = Float.parseFloat(sc.nextLine());
        System.out.print("Nivel tanque Diesel: ");
        float nivelDiesel = Float.parseFloat(sc.nextLine());
        inventario.actualizarNivelTanque(nivelSuper,nivelExtra,nivelDiesel);

        System.out.println("Ingresa las descargas realizadas en los tanques: ");
        System.out.print("Descarga en el tanque Super: ");
        int descargaSuper = Integer.parseInt(sc.nextLine());
        System.out.print("Descarga en el tanque Extra: ");
        int descargaExtra = Integer.parseInt(sc.nextLine());
        System.out.print("Descarga en el tanque Diesel: ");
        int descargaDiesel = Integer.parseInt(sc.nextLine());
        inventario.registrarDescarga(descargaSuper,descargaExtra,descargaDiesel);

        System.out.print("Generando Pedido");
        Pedido p = new Pedido("24-10-2022",inventario);
        p.crearPedido();
    }
}