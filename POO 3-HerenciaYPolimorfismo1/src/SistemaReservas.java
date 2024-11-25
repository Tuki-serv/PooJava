// Clase abstracta Vuelo
abstract class Vuelo {
    protected String numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();

    public String toString() {
        return "Vuelo " + numeroVuelo + " de " + origen + " a " + destino + " el " + fecha;
    }
}

// Subclase VueloRegular
class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioPorAsiento;

    public VueloRegular(String numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioPorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioPorAsiento = precioPorAsiento;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioPorAsiento;
    }

    @Override
    public void aplicarPromocion() {
        // Aplicar una promoción reduciendo el precio por asiento
        this.precioPorAsiento *= 0.9; // 10% de descuento
    }

    @Override
    public String toString() {
        return super.toString() + " (Vuelo Regular, " + numeroAsientos + " asientos, Precio total: " + calcularPrecio() + ")";
    }
}

// Subclase VueloCharter
class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(String numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion() {
        // Aplicar una promoción reduciendo el precio total
        this.precioTotal *= 0.85; // 15% de descuento
    }

    @Override
    public String toString() {
        return super.toString() + " (Vuelo Charter, Precio total: " + calcularPrecio() + ")";
    }
}

// Interfaz Promocionable
interface Promocionable {
    void aplicarPromocion();
}

// Clase Reservas que maneja los vuelos
class Reservas {
    private Vuelo[] vuelos;
    private int contadorVuelos;

    public Reservas(int capacidad) {
        vuelos = new Vuelo[capacidad];
        contadorVuelos = 0;
    }

    public void agregarVuelo(Vuelo vuelo) {
        if (contadorVuelos < vuelos.length) {
            vuelos[contadorVuelos++] = vuelo;
        } else {
            System.out.println("No se pueden agregar más vuelos");
        }
    }

    public double calcularPrecioTotal() {
        double total = 0;
        for (int i = 0; i < contadorVuelos; i++) {
            total += vuelos[i].calcularPrecio();
        }
        return total;
    }

    public void aplicarPromociones() {
        for (int i = 0; i < contadorVuelos; i++) {
            if (vuelos[i] instanceof Promocionable) {
                ((Promocionable) vuelos[i]).aplicarPromocion();
            }
        }
    }

    public void mostrarVuelos() {
        for (int i = 0; i < contadorVuelos; i++) {
            System.out.println(vuelos[i]);
        }
    }
}

// Clase principal para probar el sistema
public class SistemaReservas {
    public static void main(String[] args) {
        Reservas reservas = new Reservas(5);

        Vuelo vuelo1 = new VueloRegular("A123", "Madrid", "Paris", "2024-12-01", 150, 200.0);
        Vuelo vuelo2 = new VueloCharter("B456", "Barcelona", "Roma", "2024-12-05", 5000.0);

        reservas.agregarVuelo(vuelo1);
        reservas.agregarVuelo(vuelo2);

        System.out.println("Vuelos antes de la promoción:");
        reservas.mostrarVuelos();

        System.out.println("\nAplicando promociones...");
        reservas.aplicarPromociones();

        System.out.println("\nVuelos después de la promoción:");
        reservas.mostrarVuelos();

        System.out.println("\nPrecio total de las reservas: " + reservas.calcularPrecioTotal());
    }
}
