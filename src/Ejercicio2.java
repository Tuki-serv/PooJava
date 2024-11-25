enum TipoPlaneta {
    GASEOSO, TERRESTRE, ENANO
}

class Planeta {
    String nombre = null;
    int cantidadSatelite = 0;
    double masa = 0;
    double volumen = 0;
    int diámetro = 0;
    int distancia = 0;
    TipoPlaneta tipoPlaneta = null;
    boolean observable = false;
    double periodoOrbital = 0; // Nuevo atributo: en años
    double periodoRotacion = 0; // Nuevo atributo: en días

    // Constructor modificado
    Planeta(String nombre, int cantidadSatelite, double masa, double volumen, int diámetro, int distancia,
            TipoPlaneta tipoPlaneta, boolean observable, double periodoOrbital, double periodoRotacion) {
        this.nombre = nombre;
        this.cantidadSatelite = cantidadSatelite;
        this.masa = masa;
        this.volumen = volumen;
        this.diámetro = diámetro;
        this.distancia = distancia;
        this.tipoPlaneta = tipoPlaneta;
        this.observable = observable;
        this.periodoOrbital = periodoOrbital;
        this.periodoRotacion = periodoRotacion;
    }

    void imprimir() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad de satélites: " + cantidadSatelite);
        System.out.println("Masa: " + masa + " kg");
        System.out.println("Volumen: " + volumen + " km³");
        System.out.println("Diámetro: " + diámetro + " km");
        System.out.println("Distancia media al Sol: " + distancia + " millones de km");
        System.out.println("Tipo de planeta: " + tipoPlaneta);
        System.out.println("Observable a simple vista: " + (observable ? "Sí" : "No"));
        System.out.println("Periodo orbital: " + periodoOrbital + " años");
        System.out.println("Periodo de rotación: " + periodoRotacion + " días");
    }

    double calcularDensidad() {
        if (volumen == 0) {
            return 0.0; // Para evitar división entre cero
        }
        return masa / volumen;
    }

    public boolean esPlanetaExterior() {
        double distanciaUA = distancia * 1e6 / 149_597_870.0; // Convertir millones de km a UA
        return distanciaUA > 3.4;
    }
}

public class Ejercicio2 {
    public static void main(String[] args) {
        Planeta p1 = new Planeta(
                "Tierra",
                1,
                5.9736e24,
                1.08321e12,
                12742,
                150000000,
                TipoPlaneta.TERRESTRE,
                true,
                1.0, // Periodo orbital en años
                1.0  // Periodo de rotación en días
        );

        Planeta p2 = new Planeta(
                "Júpiter",
                79,
                1.899e27,
                1.431e15,
                139820,
                750000000,
                TipoPlaneta.GASEOSO,
                true,
                11.86, // Periodo orbital en años
                0.41   // Periodo de rotación en días
        );

        System.out.println("Planeta 1:");
        p1.imprimir();
        System.out.println("Densidad: " + p1.calcularDensidad() + " kg/km³");
        System.out.println("¿Es planeta exterior? " + (p1.esPlanetaExterior() ? "Sí" : "No"));

        System.out.println("\nPlaneta 2:");
        p2.imprimir();
        System.out.println("Densidad: " + p2.calcularDensidad() + " kg/km³");
        System.out.println("¿Es planeta exterior? " + (p2.esPlanetaExterior() ? "Sí" : "No"));
    }
}
