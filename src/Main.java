import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Digite la fecha en el formato dd-MM-yyyy para iniciar la proyección del pedido: ");
        String respuesta = sc.nextLine();
        Pedido p = new Pedido(respuesta);
        p.crearPedido();
    }
}