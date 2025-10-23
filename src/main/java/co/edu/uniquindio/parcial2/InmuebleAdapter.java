package co.edu.uniquindio.parcial2;
// Adapter para Inmueble
public class InmuebleAdapter extends Inmueble {

    private Inmueble inmueble;

    public InmuebleAdapter(Inmueble inmueble) {
        super(inmueble.getTipo(),
                inmueble.getCiudad(),
                inmueble.getHabitaciones(),
                inmueble.getPisos(),
                inmueble.getPrecio());

        this.inmueble = inmueble;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
