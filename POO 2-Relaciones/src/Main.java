import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    @Override
    public String toString() {
        return "Cátedra: " + catedra + ", Nota: " + notaExamen;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return suma / notas.size();
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(nombreCompleto).append(", Legajo: ").append(legajo).append("\n");
        info.append("Notas:\n");
        for (Nota nota : notas) {
            info.append("  ").append(nota).append("\n");
        }
        info.append("Promedio: ").append(calcularPromedio());
        return info.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de alumnos a cargar: ");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea restante

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese los datos del alumno " + (i + 1) + ":");
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("Legajo: ");
            long legajo = scanner.nextLong();
            scanner.nextLine(); // Consumir la línea restante

            Alumno alumno = new Alumno(nombre, legajo);

            System.out.print("Ingrese la cantidad de notas para este alumno: ");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea restante

            while (cantidadNotas <= 0) {
                System.out.print("Debe ingresar al menos una nota. Intente nuevamente: ");
                cantidadNotas = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea restante
            }

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese los datos de la nota " + (j + 1) + ":");
                System.out.print("Cátedra: ");
                String catedra = scanner.nextLine();
                System.out.print("Nota del examen: ");
                double notaExamen = scanner.nextDouble();
                scanner.nextLine(); // Consumir la línea restante

                Nota nota = new Nota(catedra, notaExamen);
                alumno.agregarNota(nota);
            }

            alumnos.add(alumno);
        }

        System.out.println("\nInformación de los alumnos:");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
            System.out.println();
        }

        scanner.close();
    }
}
