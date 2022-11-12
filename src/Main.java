public class Main {
    public static void main(String[] args) {

        LectorDatos lectorDatos = new LectorDatos();
        System.out.println(lectorDatos.leerDatos("15-10-22").toString());
        Registro registro = new Registro();
        registro.proyectarVentas();
    }
}