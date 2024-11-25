// Clase abstracta MetodoPago
abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago(double monto);

    public abstract void cancelarPago();

    @Override
    public String toString() {
        return "Pago realizado por " + titular + " con número " + numero;
    }
}

// Subclase TarjetaCredito
class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago de " + monto + " realizado con Tarjeta de Crédito (" + numero + ").");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con Tarjeta de Crédito (" + numero + ") cancelado.");
    }

    @Override
    public String toString() {
        return super.toString() + " (Tarjeta de Crédito, Expiración: " + fechaExpiracion + ")";
    }
}

// Subclase PayPal
class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String correoElectronico) {
        super(titular, correoElectronico); // Usamos el correo como "número" en este caso
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago de " + monto + " realizado a través de PayPal (" + correoElectronico + ").");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con PayPal (" + correoElectronico + ") cancelado.");
    }

    @Override
    public String toString() {
        return super.toString() + " (PayPal, Correo: " + correoElectronico + ")";
    }
}

// Interfaz Cancelable
interface Cancelable {
    void cancelarPago();
}

// Clase Pagos que maneja los métodos de pago
class Pagos {
    private MetodoPago[] metodos;
    private int contadorMetodos;

    public Pagos(int capacidad) {
        metodos = new MetodoPago[capacidad];
        contadorMetodos = 0;
    }

    public void agregarMetodo(MetodoPago metodo) {
        if (contadorMetodos < metodos.length) {
            metodos[contadorMetodos++] = metodo;
        } else {
            System.out.println("No se pueden agregar más métodos de pago.");
        }
    }

    public void realizarPagos(double[] montos) {
        for (int i = 0; i < contadorMetodos && i < montos.length; i++) {
            metodos[i].realizarPago(montos[i]);
        }
    }

    public void cancelarPagos() {
        for (int i = 0; i < contadorMetodos; i++) {
            if (metodos[i] instanceof Cancelable) {
                ((Cancelable) metodos[i]).cancelarPago();
            }
        }
    }

    public void mostrarMetodos() {
        for (int i = 0; i < contadorMetodos; i++) {
            System.out.println(metodos[i]);
        }
    }
}

// Clase principal para probar el sistema de pagos
public class SistemaDePagos {
    public static void main(String[] args) {
        Pagos pagos = new Pagos(3);

        MetodoPago tarjetaCredito = new TarjetaCredito("Juan Pérez", "1234-5678-9876-5432", "12/25", "123");
        MetodoPago paypal = new PayPal("Ana Gómez", "ana@gmail.com");

        pagos.agregarMetodo(tarjetaCredito);
        pagos.agregarMetodo(paypal);

        System.out.println("Métodos de pago disponibles:");
        pagos.mostrarMetodos();

        double[] montos = {200.0, 150.0};
        System.out.println("\nRealizando pagos...");
        pagos.realizarPagos(montos);

        System.out.println("\nCancelando pagos...");
        pagos.cancelarPagos();
    }
}
