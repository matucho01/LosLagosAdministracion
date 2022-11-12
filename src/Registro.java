import java.text.SimpleDateFormat;
import java.util.*;

public class Registro {

    private List gasolinaSuper;
    private List extra;
    private List diesel;
    private LectorDatos lectorDatos = new LectorDatos();
    private Inventario inventario = new Inventario();

    public Registro() {
        this.gasolinaSuper = new ArrayList<Float>();
        this.extra = new ArrayList<Float>();
        this.diesel = new ArrayList<Float>();
    }

    public void calcularVentasSemanales(int numDias) {
        this.gasolinaSuper.clear();
        this.extra.clear();
        this.diesel.clear();
        Calendar c = Calendar.getInstance();
        c.set(2022,10,24);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        sdf.format(c.getTime());
        for(int i=1; i<4; i++){
            c.add(Calendar.DATE, -7*i);
            float auxSemanaSuper = 0;
            float auxSemanaExtra = 0;
            float auxSemanaDiesel = 0;
            for (int j=0; j<numDias; j++){
                Calendar aux = c;
                aux.add(Calendar.DATE, j);
                List listaAux = lectorDatos.leerDatos(sdf.format(aux.getTime()));
                auxSemanaSuper += Float.parseFloat(listaAux.get(0).toString());
                auxSemanaExtra += Float.parseFloat(listaAux.get(1).toString());
                auxSemanaDiesel += Float.parseFloat(listaAux.get(2).toString());
            }
            this.gasolinaSuper.add(auxSemanaSuper);
            this.extra.add(auxSemanaExtra);
            this.diesel.add(auxSemanaDiesel);
            System.out.println(this.gasolinaSuper);
        }
    }

    public void proyectarVentas(){
        int numDias = 3;
        calcularVentasSemanales(numDias);
        float mayorSuper = (float) Collections.max(this.gasolinaSuper);
        float mayorExtra = (float) Collections.max(this.extra);
        float mayorDiesel = (float) Collections.max(this.diesel);

        int total = 0;
        int estimadorSuper = 0;
        int estimadorExtra = 0;
        int estimadorDiesel = 0;
        do{
            calcularVentasSemanales(numDias);
            estimadorSuper = (int) Math.floor((double)(mayorSuper - inventario.getGasolinaSuper()));
            estimadorExtra = (int) Math.floor((double)(mayorExtra - inventario.getExtra()));
            estimadorDiesel = (int) Math.floor((double)(mayorDiesel - inventario.getDiesel()));
            total = estimadorSuper + estimadorExtra + estimadorDiesel;
            numDias+=1;
            System.out.println(total);
        }while(total!=10000);
        System.out.println("El pedido es de: " + estimadorSuper + estimadorExtra + estimadorDiesel);
    }


}
