package co.edu.uniquindio.parcial2.Decorator;

import co.edu.uniquindio.parcial2.InmuebleComponent;

public abstract class InmuebleDecorator implements InmuebleComponent {
    protected InmuebleComponent inmuebleDecorado;

    public InmuebleDecorator(InmuebleComponent inmuebleDecorado) {
        this.inmuebleDecorado = inmuebleDecorado;
    }

    @Override
    public String mostrarInformacion() {
        return inmuebleDecorado.mostrarInformacion();
    }
}

