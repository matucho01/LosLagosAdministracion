import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private float gasolinaSuper = 400;
    private float extra = 5800;
    private float diesel = 3400;

    public void registrarDescarga(int volumenSuper, int volumenExtra, int volumenDiesel) {
        this.gasolinaSuper += volumenSuper;
        this.extra += volumenExtra;
        this.diesel += volumenDiesel;
        System.out.println("Descarga registrada exitosamente");
    }

    public void actualizarNivelTanque(float nivelSuper, float nivelExtra, float nivelDiesel) {

    }

    public float getGasolinaSuper() {
        return gasolinaSuper;
    }

    public float getExtra() {
        return extra;
    }

    public float getDiesel() {
        return diesel;
    }
}
