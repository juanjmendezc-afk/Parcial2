package co.edu.uniquindio.parcial2;

public class InmuebleAdapter extends Inmueble {

    private Inmueble inmueble;

    public InmuebleAdapter(Inmueble inmueble) {
        super(Inmueble.getTipoInmueble(),
                Inmueble.getCiudad(),
                0,
                0,
                inmueble.getPrecio());
        this.Inmueble = inmueble;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
