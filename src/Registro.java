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

    public void calcularVentasSemanales(int numDias, String fecha) {


        List tokens = new ArrayList<String>();

        StringTokenizer tkzr = new StringTokenizer(fecha, "-");

        while(tkzr.hasMoreElements()) {
            tokens.add(tkzr.nextToken());
        }

        int dia = Integer.parseInt(tokens.get(0).toString());
        int mes = Integer.parseInt(tokens.get(1).toString())-1;
        int anio = Integer.parseInt(tokens.get(2).toString());

        this.gasolinaSuper.clear();
        this.extra.clear();
        this.diesel.clear();
        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        //sdf.format(c.getTime());
        for(int i = 1; i < 4; i++){
            c.set(anio,mes,dia);
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

    public void proyectarVentas(String fecha){
        int numDias = 3;
        //calcularVentasSemanales(numDias);

        float total = 0;
        float estimadorSuper = 0;
        float estimadorExtra = 0;
        float estimadorDiesel = 0;

        float mayorSuper = 0;
        float mayorExtra = 0;
        float mayorDiesel = 0;

        List pedido = new ArrayList<Float>();
        /*
        do {

        } while(total <= 10);
*/
        while(total <= 10){
            total = 0;
            pedido.clear();
            pedido.add(estimadorSuper);
            pedido.add(estimadorExtra);
            pedido.add(estimadorDiesel);
            calcularVentasSemanales(numDias, fecha);
            mayorSuper = (float) Collections.max(this.gasolinaSuper);
            mayorExtra = (float) Collections.max(this.extra);
            mayorDiesel = (float) Collections.max(this.diesel);

            System.out.println(mayorSuper);
            System.out.println(mayorExtra);
            System.out.println(mayorDiesel);
            System.out.println(numDias);

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
        }
        System.out.println("El pedido es para " + (numDias - 2) + " días: " +
                "\nSuper: " + pedido.get(0).toString() + "00" +
                "\nExtra: " + pedido.get(1).toString() + "00" +
                "\nDiesel: "+ pedido.get(2).toString() + "00" );
    }

}
