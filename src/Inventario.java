public class Inventario {
    private float gasolinaSuper;
    private float extra;
    private float diesel;

    public void registrarDescarga(int volumenSuper, int volumenExtra, int volumenDiesel) {
        this.gasolinaSuper += volumenSuper;
        this.extra += volumenExtra;
        this.diesel += volumenDiesel;
        System.out.println("Descarga registrada exitosamente");
    }

    public void actualizarNivelTanque(float nivelSuper, float nivelExtra, float nivelDiesel) {

    }

}
