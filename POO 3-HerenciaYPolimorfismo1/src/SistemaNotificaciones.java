// Clase abstracta CanalNotificacion
abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();

    @Override
    public String toString() {
        return "Notificación para " + usuario + ": " + mensaje;
    }
}

// Subclase CorreoElectronico
class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo electrónico a " + direccionCorreo + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }

    @Override
    public String toString() {
        return super.toString() + " (Correo electrónico: " + direccionCorreo + ")";
    }
}

// Subclase MensajeTexto
class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto al número " + numeroTelefono + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }

    @Override
    public String toString() {
        return super.toString() + " (Mensaje de texto: " + numeroTelefono + ")";
    }
}

// Interfaz Personalizable
interface Personalizable {
    void personalizarMensaje(String nuevoMensaje);
}

// Clase Notificaciones que maneja los canales de notificación
class Notificaciones {
    private CanalNotificacion[] canales;
    private int contadorCanales;

    public Notificaciones(int capacidad) {
        canales = new CanalNotificacion[capacidad];
        contadorCanales = 0;
    }

    public void agregarCanal(CanalNotificacion canal) {
        if (contadorCanales < canales.length) {
            canales[contadorCanales++] = canal;
        } else {
            System.out.println("No se pueden agregar más canales de notificación.");
        }
    }

    public void enviarNotificaciones() {
        for (int i = 0; i < contadorCanales; i++) {
            canales[i].enviarNotificacion();
        }
    }

    public void personalizarMensajes(String[] nuevosMensajes) {
        for (int i = 0; i < contadorCanales && i < nuevosMensajes.length; i++) {
            if (canales[i] instanceof Personalizable) {
                ((Personalizable) canales[i]).personalizarMensaje(nuevosMensajes[i]);
            }
        }
    }

    public void mostrarCanales() {
        for (int i = 0; i < contadorCanales; i++) {
            System.out.println(canales[i]);
        }
    }
}

// Clase principal para probar el sistema de notificaciones
public class SistemaNotificaciones {
    public static void main(String[] args) {
        Notificaciones notificaciones = new Notificaciones(3);

        CanalNotificacion correo = new CorreoElectronico("Juan Pérez", "Su pedido ha sido enviado.", "juan@correo.com");
        CanalNotificacion sms = new MensajeTexto("Ana Gómez", "Su paquete está en camino.", "555-1234");

        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(sms);

        System.out.println("Canales de notificación:");
        notificaciones.mostrarCanales();

        System.out.println("\nEnviando notificaciones...");
        notificaciones.enviarNotificaciones();

        System.out.println("\nPersonalizando mensajes...");
        String[] nuevosMensajes = {"Su pedido llegará mañana.", "Su paquete llegará esta tarde."};
        notificaciones.personalizarMensajes(nuevosMensajes);

        System.out.println("\nEnviando notificaciones personalizadas...");
        notificaciones.enviarNotificaciones();
    }
}
