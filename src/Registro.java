import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Registro {

    private List consumoSuper;
    private List consumoExtra;
    private List consumoDiesel;
    private LectorDatos lectorDatos = new LectorDatos();
    private int numDias;

    DecimalFormat formato = new DecimalFormat("#.#");

    public Registro() {
        this.consumoSuper = new ArrayList<Float>();
        this.consumoExtra = new ArrayList<Float>();
        this.consumoDiesel = new ArrayList<Float>();
        this.numDias = 3;
    }

    private void calcularVentasSemanales(String fecha) {
        ArrayList<Integer> elementosFecha = tokenizarFecha(fecha);
        reiniciarConsumos();
        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        for(int i = 1; i < 4; i++){
            c.set(elementosFecha.get(2),elementosFecha.get(1),elementosFecha.get(0));
            c.add(Calendar.DAY_OF_MONTH, -7*i);
            float auxSemanaSuper = 0;
            float auxSemanaExtra = 0;
            float auxSemanaDiesel = 0;
            for (int j = 0; j < this.numDias; j++){
                Calendar aux = (Calendar) c.clone();
                aux.add(Calendar.DAY_OF_MONTH, j);
                List listaAux = lectorDatos.leerDatos(sdf.format(aux.getTime()));
                auxSemanaSuper += Float.parseFloat(listaAux.get(0).toString());
                auxSemanaExtra += Float.parseFloat(listaAux.get(1).toString());
                auxSemanaDiesel += Float.parseFloat(listaAux.get(2).toString());
            }
            auxSemanaSuper = formatearGalones(auxSemanaSuper);
            auxSemanaExtra = formatearGalones(auxSemanaExtra);
            auxSemanaDiesel = formatearGalones(auxSemanaDiesel);

            this.consumoSuper.add(auxSemanaSuper);
            this.consumoExtra.add(auxSemanaExtra);
            this.consumoDiesel.add(auxSemanaDiesel);
        }
    }

    private void reiniciarConsumos() {
        this.consumoSuper.clear();
        this.consumoExtra.clear();
        this.consumoDiesel.clear();
    }

    private ArrayList<Integer> tokenizarFecha(String fecha) {
        List tokens = new ArrayList<String>();

        StringTokenizer tkzr = new StringTokenizer(fecha, "-");

        while(tkzr.hasMoreElements()) {
            tokens.add(tkzr.nextToken());
        }

        ArrayList elementosFecha = new ArrayList<Integer>();

        elementosFecha.add(Integer.parseInt(tokens.get(0).toString()));
        elementosFecha.add(Integer.parseInt(tokens.get(1).toString())-1);
        elementosFecha.add(Integer.parseInt(tokens.get(2).toString()));
        return elementosFecha;
    }

    private float formatearGalones(float galones) {
        float galonesNormalizados = galones/1000;
        return Float.parseFloat(formato.format((galonesNormalizados)));
    }

    public List proyectarVentas(String fecha,float galonesSuper,float galonesExtra,float galonesDiesel){

        float total = 0;
        float estimadorSuper = 0;
        float estimadorExtra = 0;
        float estimadorDiesel = 0;

        List pedido = new ArrayList<Float>();

        while(total <= 10){
            total = 0;
            pedido.clear();
            pedido.add(estimadorSuper);
            pedido.add(estimadorExtra);
            pedido.add(estimadorDiesel);
            calcularVentasSemanales(fecha);

            estimadorSuper = Math.round(obtenerMayor().get(0) - formatearGalones(galonesSuper));
            estimadorExtra = Math.round(obtenerMayor().get(1) - formatearGalones(galonesExtra));;
            estimadorDiesel = Math.round(obtenerMayor().get(2) - formatearGalones(galonesDiesel));;

            if(estimadorSuper < 0) {
                estimadorSuper = 0;
            }

            total = estimadorSuper + estimadorExtra + estimadorDiesel;
            this.numDias += 1;
        }
        return pedido;
    }

    private ArrayList<Float> obtenerMayor() {
        ArrayList<Float> mayores = new ArrayList<>();
        mayores.add((float) Collections.max(this.consumoSuper));
        mayores.add((float) Collections.max(this.consumoExtra));
        mayores.add((float) Collections.max(this.consumoDiesel));
        return mayores;
    }

    public int getNumDias() {
        return numDias;
    }
}
