package co.edu.uniquindio.parcial2.Decorator;

import co.edu.uniquindio.parcial2.InmuebleComponent;

public class DetalleDecorator extends InmuebleDecorator {

    public DetalleDecorator(InmuebleComponent inmuebleDecorado) {
        super(inmuebleDecorado);
    }

    @Override
    public String mostrarInformacion() {
        return "=== Detalle del Inmueble ===\n"
                + inmuebleDecorado.mostrarInformacion()
                + "\n============================";
    }
}

