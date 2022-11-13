import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Registro {

    private List gasolinaSuper;
    private List extra;
    private List diesel;
    private LectorDatos lectorDatos = new LectorDatos();
    private Inventario inventario = new Inventario();

    DecimalFormat formato = new DecimalFormat("#.#");

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        //sdf.format(c.getTime());
        for(int i = 1; i < 4; i++){
            c.set(2022,9,24);
            c.add(Calendar.DAY_OF_MONTH, -7*i);
            float auxSemanaSuper = 0;
            float auxSemanaExtra = 0;
            float auxSemanaDiesel = 0;
            for (int j = 0; j < numDias; j++){
                Calendar aux = (Calendar) c.clone();
                aux.add(Calendar.DAY_OF_MONTH, j);
                System.out.println(sdf.format(aux.getTime()));
                List listaAux = lectorDatos.leerDatos(sdf.format(aux.getTime()));
                auxSemanaSuper += Float.parseFloat(listaAux.get(0).toString());
                auxSemanaExtra += Float.parseFloat(listaAux.get(1).toString());
                auxSemanaDiesel += Float.parseFloat(listaAux.get(2).toString());
            }
            auxSemanaSuper = Float.parseFloat(formato.format(auxSemanaSuper/1000));
            auxSemanaExtra = Float.parseFloat(formato.format(auxSemanaExtra/1000));
            auxSemanaDiesel = Float.parseFloat(formato.format(auxSemanaDiesel/1000));

            this.gasolinaSuper.add(auxSemanaSuper);
            this.extra.add(auxSemanaExtra);
            this.diesel.add(auxSemanaDiesel);
            //System.out.println(this.gasolinaSuper);
        }
        //System.out.println(this.gasolinaSuper);
    }

    public void proyectarVentas(){
        int numDias = 3;
        //calcularVentasSemanales(numDias);


        float total = 0;
        float estimadorSuper = 0;
        float estimadorExtra = 0;
        float estimadorDiesel = 0;
        do {
            total = 0;
            calcularVentasSemanales(numDias);
            float mayorSuper = (float) Collections.max(this.gasolinaSuper);
            float mayorExtra = (float) Collections.max(this.extra);
            float mayorDiesel = (float) Collections.max(this.diesel);
            System.out.println(mayorSuper);
            System.out.println(mayorExtra);
            System.out.println(mayorDiesel);

            //estimadorSuper = (int) Math.floor((double)(mayorSuper - inventario.getGasolinaSuper()));
            //estimadorExtra = (int) Math.floor((double)(mayorExtra - inventario.getExtra()));
            //estimadorDiesel = (int) Math.floor((double)(mayorDiesel - inventario.getDiesel()));
            estimadorSuper = Math.round(mayorSuper - Float.parseFloat(formato.format((inventario.getGasolinaSuper()/1000))));
            estimadorExtra = Math.round(mayorExtra - Float.parseFloat(formato.format((inventario.getExtra()/1000))));;
            estimadorDiesel = Math.round(mayorDiesel - Float.parseFloat(formato.format((inventario.getDiesel()/1000))));;

            if(estimadorSuper < 0) {
                estimadorSuper = 0;
            }

            total = estimadorSuper + estimadorExtra + estimadorDiesel;
            numDias += 1;
            System.out.println(total);
        } while(total <= 10);
        System.out.println("El pedido es de: " +
                "\nSuper: " + estimadorSuper + "00" +
                "\nExtra: " + estimadorExtra + "00" +
                "\nDiesel: "+ estimadorDiesel + "00");
        System.out.println("Ola bebeese");
        System.out.println("Dejen de dañar");
    }

}
