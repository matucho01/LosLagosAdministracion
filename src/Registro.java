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
        this.numDias =3;
    }

    public void calcularVentasSemanales(String fecha) {
        ArrayList<Integer> elementosFecha = tokenizarFecha(fecha);
        this.consumoSuper.clear();
        this.consumoExtra.clear();
        this.consumoDiesel.clear();
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
            auxSemanaSuper = Float.parseFloat(formato.format(auxSemanaSuper/1000));
            auxSemanaExtra = Float.parseFloat(formato.format(auxSemanaExtra/1000));
            auxSemanaDiesel = Float.parseFloat(formato.format(auxSemanaDiesel/1000));

            this.consumoSuper.add(auxSemanaSuper);
            this.consumoExtra.add(auxSemanaExtra);
            this.consumoDiesel.add(auxSemanaDiesel);
        }
    }

    public ArrayList<Integer> tokenizarFecha(String fecha) {
        List tokens = new ArrayList<String>();

        StringTokenizer tkzr = new StringTokenizer(fecha, "-");

        while(tkzr.hasMoreElements()) {
            tokens.add(tkzr.nextToken());
        }

        int dia = Integer.parseInt(tokens.get(0).toString());
        int mes = Integer.parseInt(tokens.get(1).toString())-1;
        int anio = Integer.parseInt(tokens.get(2).toString());

        ArrayList elementosFecha = new ArrayList<Integer>();
        elementosFecha.add(dia);
        elementosFecha.add(mes);
        elementosFecha.add(anio);
        return elementosFecha;
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

            estimadorSuper = Math.round(obtenerMayor().get(0) - Float.parseFloat(formato.format((galonesSuper/1000))));
            estimadorExtra = Math.round(obtenerMayor().get(1) - Float.parseFloat(formato.format((galonesExtra/1000))));;
            estimadorDiesel = Math.round(obtenerMayor().get(2) - Float.parseFloat(formato.format((galonesDiesel/1000))));;

            if(estimadorSuper < 0) {
                estimadorSuper = 0;
            }

            total = estimadorSuper + estimadorExtra + estimadorDiesel;
            this.numDias += 1;
        }
        return pedido;

    }

    public ArrayList<Float> obtenerMayor() {
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
