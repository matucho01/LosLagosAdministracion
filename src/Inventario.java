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
        if(nivelSuper<=2.7){
            gasolinaSuper = (float) (-761.40*Math.pow(nivelSuper,3)+3104*(nivelSuper*nivelSuper)+2066.1*nivelSuper-179.72);
        }
        if(nivelExtra<=2.7){
            extra = (float) (-703.56*Math.pow(nivelExtra,3)+2868.1*(nivelExtra*nivelExtra)+1908.5*nivelExtra-166.02);
        }
        if(nivelDiesel<=2.7){
            diesel = (float) (-596.37*Math.pow(nivelDiesel,3)+2431.2*(nivelDiesel*nivelDiesel)+1617.8*nivelDiesel-140.79);
        }
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
