package co.edu.uniquindio.parcial2;

public class InmuebleFactory {

    public Inmueble crearInmueble(String tipo, String ciudad, int habitaciones, int pisos, double precio) {
        if (tipo.equalsIgnoreCase("casa")) {
            return new Inmueble(TipoInmueble.CASA, ciudad, habitaciones, pisos, (int) precio);
        } else if (tipo.equalsIgnoreCase("apartamento")) {
            return new Inmueble(TipoInmueble.APARTAMENTO, ciudad, habitaciones, pisos, (int) precio);
        } else if (tipo.equalsIgnoreCase("local")) {
            return new Inmueble(TipoInmueble.LOCAL, ciudad, habitaciones, pisos, (int) precio);
        } else if (tipo.equalsIgnoreCase("finca")) {
            return new Inmueble(TipoInmueble.FINCA, ciudad, habitaciones, pisos, (int) precio);
        }
        return null;
    }
}