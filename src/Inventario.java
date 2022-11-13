public class Inventario {
    private float galonesSuper;
    private float galonesExtra;
    private float galonesDiesel;

    public void registrarDescarga(int volumenSuper, int volumenExtra, int volumenDiesel) {
        this.galonesSuper += volumenSuper;
        this.galonesExtra += volumenExtra;
        this.galonesDiesel += volumenDiesel;
        System.out.println("Descarga registrada exitosamente");
    }

    public void actualizarNivelTanque(float nivelSuper, float nivelExtra, float nivelDiesel) {
        if(nivelSuper<=2.7){
            galonesSuper = (float) (-761.40*Math.pow(nivelSuper,3)+3104*(nivelSuper*nivelSuper)+2066.1*nivelSuper-179.72);
        }
        if(nivelExtra<=2.7){
            galonesExtra = (float) (-703.56*Math.pow(nivelExtra,3)+2868.1*(nivelExtra*nivelExtra)+1908.5*nivelExtra-166.02);
        }
        if(nivelDiesel<=2.7){
            galonesDiesel = (float) (-596.37*Math.pow(nivelDiesel,3)+2431.2*(nivelDiesel*nivelDiesel)+1617.8*nivelDiesel-140.79);
        }
    }

    public float getGalonesSuper() {
        return galonesSuper;
    }

    public float getGalonesExtra() {
        return galonesExtra;
    }

    public float getGalonesDiesel() {
        return galonesDiesel;
    }
}
