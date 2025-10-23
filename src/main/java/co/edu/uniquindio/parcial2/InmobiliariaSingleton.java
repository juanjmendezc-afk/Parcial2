package co.edu.uniquindio.parcial2;

import java.util.ArrayList;
//Singleton para gestionar los inmuebles :)
public class InmobiliariaSingleton {

    private static InmobiliariaSingleton instancia;
    private ArrayList<Inmueble> listaInmuebles;

    private InmobiliariaSingleton() {
        listaInmuebles = new ArrayList<>();
    }

    public static InmobiliariaSingleton getInstancia() {
        if (instancia == null) {
            instancia = new InmobiliariaSingleton();
        }
        return instancia;
    }

    public void agregarInmueble(Inmueble inmueble) {
        listaInmuebles.add(inmueble);
    }

    public ArrayList<Inmueble> getListaInmuebles() {
        return listaInmuebles;
    }
}

