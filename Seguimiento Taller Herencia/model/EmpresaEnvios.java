package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EmpresaEnvios {

    private String nombre;
    private List<Envio> listaEnvios = new LinkedList<>();

    public EmpresaEnvios(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Envio> getListaEnvios() {
        return listaEnvios;
    }

    public void setListaEnvios(List<Envio> listaEnvios) {
        this.listaEnvios = listaEnvios;
    }
    public String registrarEnvio(Envio envio) {
        Optional<Envio> encontrado = buscarEnvio(envio.getCodigo());
        if (encontrado.isPresent()) {
            return "El envío ya fue registrado";
        }
        listaEnvios.add(envio);
        return "Envío registrado";
    }
    public Envio consultarEnvio(String codigo) {
        Optional<Envio> encontrado = buscarEnvio(codigo);
        if (encontrado.isPresent()) {
            return encontrado.get();
        }
        return null;
    }
    public String actualizarEnvio(String codigo, Envio nuevo) {
        Optional<Envio> encontrado = buscarEnvio(codigo);
        if (encontrado.isEmpty()) {
            return "El envío no existe";
        }
        listaEnvios.remove(encontrado.get());
        listaEnvios.add(nuevo);
        return "Envío actualizado";
    }
    public String eliminarEnvio(String codigo) {
        Optional<Envio> encontrado = buscarEnvio(codigo);
        if (encontrado.isEmpty()) {
            return "El envío no existe";
        }
        listaEnvios.remove(encontrado.get());
        return "Envío eliminado";
    }
    public Envio masCostoso() {
        Envio envioMayor = null;
        for (Envio e : listaEnvios) {
            if (envioMayor == null || e.calcularCostoTotal() > envioMayor.calcularCostoTotal()) {
                envioMayor = e;
            }
        }
        return envioMayor;
    }
    private Optional<Envio> buscarEnvio(String codigo) {
        return listaEnvios.stream().filter(e -> e.getCodigo().equals(codigo)).findFirst();
    }
    @Override
    public String toString() {
        return "EmpresaEnvios{nombre='" + nombre + "'}";
    }
}