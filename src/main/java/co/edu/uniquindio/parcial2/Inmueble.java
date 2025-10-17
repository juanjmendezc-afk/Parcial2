package co.edu.uniquindio.parcial2;

public class Inmueble {
    private TipoInmueble tipoInmueble;
    private int pisos, habitaciones, precio;
    private String ciudad;

    public Inmueble(TipoInmueble tipoInmueble, String ciudad, int habitaciones, int pisos, int precio) {
        this.tipoInmueble = tipoInmueble;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
        this.pisos = pisos;
        this.precio = precio;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public int getPisos() {
        return pisos;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public int getPrecio() {
        return precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public void setPisos(int pisos) {
        this.pisos = pisos;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Inmueble [tipoInmueble=" + tipoInmueble + ", pisos=" + pisos + ", habitaciones=" + habitaciones
                + ", precio=" + precio + ", ciudad=" + ciudad + "]";
    }
}