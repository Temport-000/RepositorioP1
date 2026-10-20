package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Orden {
 private String id;
 private LocalDate date;
 private String obs;
 private EstadoOrden estado;
 private ArrayList<DetalleOrden> listaDetallesOrden = new ArrayList<>();
 public Orden(String id, LocalDate date, String obs, EstadoOrden estado) {
        this.id = id;
        this.date = date;
        this.obs = obs;
        this.estado = estado;
 }
    public String getId() {
        return id;
 }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    public EstadoOrden getEstado() {
        return estado;
    }
    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }
    public ArrayList<DetalleOrden> getListaDetallesOrden() {
        return listaDetallesOrden;
    }
    public void setListaDetallesOrden(ArrayList<DetalleOrden> listaDetallesOrden) {
        this.listaDetallesOrden = listaDetallesOrden;
    }

    public Optional<DetalleOrden> buscarDetalle(String idDetalle) {
        for (DetalleOrden aux : listaDetallesOrden) {
            if (aux.getId().equals(idDetalle)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    // Funcionalidad 6
    public String agregarDetalleOrden(DetalleOrden detalle) {
        if (estado == EstadoOrden.FINALIZADA || estado == EstadoOrden.ENTREGADA || estado == EstadoOrden.CANCELADA) {
            return "No se pueden agregar detalles a una orden " + estado;
        }
        if (buscarDetalle(detalle.getId()).isPresent()) {
            return "Ya existe el detalle " + detalle.getId() + " en la orden " + id;
        }
        listaDetallesOrden.add(detalle);
        return "Detalle " + detalle.getId() + " registrado en la orden " + id;
    }
    // Funcionalidad 10
    public int calcularTotal() {
        int total = 0;
        for (DetalleOrden d : listaDetallesOrden) {
            total += d.calcularValor();
        }
        return total;
    }
    // Funcionalidad 12
    public String finalizar() {
        if (estado == EstadoOrden.FINALIZADA || estado == EstadoOrden.ENTREGADA || estado == EstadoOrden.CANCELADA) {
            return "La orden ya está " + estado;
        }
        if (listaDetallesOrden.isEmpty()) {
            return "La orden no tiene detalles registrados";
        }
        for (DetalleOrden d : listaDetallesOrden) {
            if (!d.estaTerminado()) {
                return "No se puede finalizar: el detalle " + d.getId() + " está " + d.getEstado();
            }
        }
        estado = EstadoOrden.FINALIZADA;
        return "Orden " + id + " finalizada";
    }



}
