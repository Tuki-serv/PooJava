import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadDeMedida;

    public Ingrediente(String nombre, double cantidad, String unidadDeMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadDeMedida = unidadDeMedida;
    }

    @Override
    public String toString() {
        return nombre + " " + cantidad + " " + unidadDeMedida;
    }
}

class Plato {
    private String nombre;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombre, double precio, boolean esBebida) {
        this.nombre = nombre;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!esBebida) {
            ingredientes.add(ingrediente);
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(nombre).append("\n");
        info.append("Precio: $").append(precio).append("\n");
        if (!esBebida) {
            info.append("Ingredientes:\n");
            for (Ingrediente ingrediente : ingredientes) {
                info.append("  ").append(ingrediente).append("\n");
            }
        }
        return info.toString();
    }
}

public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.print("Ingrese la cantidad de platos a cargar: ");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea restante

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("Ingrese los datos del plato " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            System.out.print("¿Es una bebida? (true/false): ");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine(); // Consumir la línea restante

            Plato plato = new Plato(nombre, precio, esBebida);

            if (!esBebida) {
                System.out.print("Ingrese la cantidad de ingredientes para este plato: ");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea restante

                while (cantidadIngredientes <= 0) {
                    System.out.print("Debe ingresar al menos un ingrediente. Intente nuevamente: ");
                    cantidadIngredientes = scanner.nextInt();
                    scanner.nextLine(); // Consumir la línea restante
                }

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.println("Ingrese los datos del ingrediente " + (j + 1) + ":");
                    System.out.print("Nombre: ");
                    String nombreIngrediente = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    double cantidad = scanner.nextDouble();
                    System.out.print("Unidad de medida: ");
                    scanner.nextLine(); // Consumir la línea restante
                    String unidadDeMedida = scanner.nextLine();

                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidad, unidadDeMedida);
                    plato.agregarIngrediente(ingrediente);
                }
            }

            platosMenu.add(plato);
        }

        System.out.println("\nMENÚ:");
        for (Plato plato : platosMenu) {
            System.out.println(plato);
        }

        scanner.close();
    }
}
