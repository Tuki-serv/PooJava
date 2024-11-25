import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuento;
    private double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario, double descuento, double subtotal) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return codigoArticulo + " " + nombreArticulo + " " + cantidad + " " + precioUnitario + " " + descuento + " " + subtotal;
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private String cliente;
    private ArrayList<DetalleFactura> detallesFactura;
    private double totalCalculadoFactura;

    public Factura() {
        this.detallesFactura = new ArrayList<>();
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }

    @Override
    public String toString() {
        StringBuilder factura = new StringBuilder();
        factura.append("Fecha: ").append(fechaFactura).append("\n");
        factura.append("Número: ").append(numeroFactura).append("\n");
        factura.append("Cliente: ").append(cliente).append("\n");
        factura.append("Código Artículo  Nombre Artículo  Cantidad  Precio Unitario  Descuento  Subtotal\n");
        for (DetalleFactura detalle : detallesFactura) {
            factura.append(detalle).append("\n");
        }
        factura.append("Total: ").append(totalCalculadoFactura).append("\n");
        return factura.toString();
    }
}

public class Facturacion {
    private static final String[][] ARTICULOS = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        System.out.print("Ingrese la fecha de la factura: ");
        factura.setFechaFactura(scanner.nextLine());
        System.out.print("Ingrese el número de la factura: ");
        long numeroFactura = scanner.nextLong();
        while (numeroFactura <= 0) {
            System.out.print("El número debe ser mayor a 0. Intente nuevamente: ");
            numeroFactura = scanner.nextLong();
        }
        factura.setNumeroFactura(numeroFactura);
        scanner.nextLine(); // Consumir la línea restante
        System.out.print("Ingrese el cliente: ");
        String cliente = scanner.nextLine();
        while (cliente.isEmpty()) {
            System.out.print("El cliente no puede estar vacío. Intente nuevamente: ");
            cliente = scanner.nextLine();
        }
        factura.setCliente(cliente);

        boolean continuar = true;
        while (continuar) {
            System.out.print("Ingrese el código del artículo: ");
            String codigo = scanner.nextLine();
            String[] articulo = buscarArticulo(codigo);
            if (articulo == null) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
                continue;
            }
            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea restante

            double precioUnitario = Double.parseDouble(articulo[2]);
            double descuento = (cantidad > 5) ? precioUnitario * 0.1 : 0;
            double subtotal = (precioUnitario - descuento) * cantidad;

            DetalleFactura detalle = new DetalleFactura(codigo, articulo[1], cantidad, precioUnitario, descuento, subtotal);
            factura.agregarDetalle(detalle);

            System.out.print("¿Desea continuar agregando artículos? (true/false): ");
            continuar = scanner.nextBoolean();
            scanner.nextLine(); // Consumir la línea restante
        }

        factura.calcularMontoTotal();
        System.out.println("\nFactura generada:");
        System.out.println(factura);

        scanner.close();
    }

    private static String[] buscarArticulo(String codigo) {
        for (String[] articulo : ARTICULOS) {
            if (articulo[0].equals(codigo)) {
                return articulo;
            }
        }
        return null;
    }
}
