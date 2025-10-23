package co.edu.uniquindio.parcial2;

// Patrón Builder + co.edu.uniquindio.parcial2.Decorator
public class Inmueble implements InmuebleComponent {

    private String tipo;
    private String ciudad;
    private int habitaciones;
    private int pisos;
    private double precio;

    private Inmueble(Builder builder) {
        this.tipo = builder.tipo;
        this.ciudad = builder.ciudad;
        this.habitaciones = builder.habitaciones;
        this.pisos = builder.pisos;
        this.precio = builder.precio;
    }

    public Inmueble(Object tipoInmueble, String ciudad, int i, int i1, double precio) {}

    public String getTipo() { return tipo; }
    public String getCiudad() { return ciudad; }
    public int getHabitaciones() { return habitaciones; }
    public int getPisos() { return pisos; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return tipo + " en " + ciudad + " (" + habitaciones + " hab, " + pisos + " pisos, $" + precio + ")";
    }

    @Override
    public String mostrarInformacion() {
        return toString();
    }

    public static class Builder {
        private String tipo;
        private String ciudad;
        private int habitaciones;
        private int pisos;
        private double precio;

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder ciudad(String ciudad) {
            this.ciudad = ciudad;
            return this;
        }

        public Builder habitaciones(int habitaciones) {
            this.habitaciones = habitaciones;
            return this;
        }

        public Builder pisos(int pisos) {
            this.pisos = pisos;
            return this;
        }

        public Builder precio(double precio) {
            this.precio = precio;
            return this;
        }

        public Inmueble build() {
            return new Inmueble(this);
        }
    }
}

