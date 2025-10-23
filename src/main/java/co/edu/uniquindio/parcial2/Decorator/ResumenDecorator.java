package co.edu.uniquindio.parcial2.Decorator;

import co.edu.uniquindio.parcial2.InmuebleComponent;

public class ResumenDecorator extends InmuebleDecorator {

    public ResumenDecorator(InmuebleComponent inmuebleDecorado) {
        super(inmuebleDecorado);
    }

    @Override
    public String mostrarInformacion() {
        String base = inmuebleDecorado.mostrarInformacion();
        // Simplificamos la vista
        String[] partes = base.split(" ");
        return "Resumen: " + partes[0] + " - " + partes[2];
    }
}
