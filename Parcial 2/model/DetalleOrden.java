package model;

import java.util.ArrayList;

public class DetalleOrden {
    private String id;
    private int hours;
    private String desc;
    private String obs;
    private EstadoOrden estado;
    private Tecnico tecnico;
    private Servicio tipoServicio;
    private Equipo equipo;
    private ArrayList<RepuestoUtilizado> listaRepuestoUtilizados = new ArrayList<>();

    public DetalleOrden(String id, int hours, String desc, String obs, EstadoOrden estado,
                        Tecnico tecnico, Servicio tipoServicio, Equipo equipo) {
        this.id = id;
        this.hours = hours;
        this.desc = desc;
        this.obs = obs;
        this.estado = estado;
        this.tecnico = tecnico;
        this.tipoServicio = tipoServicio;
        this.equipo = equipo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }
    public EstadoOrden getEstado() { return estado; }
    public void setEstado(EstadoOrden estado) { this.estado = estado; }
    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }
    public Servicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(Servicio tipoServicio) { this.tipoServicio = tipoServicio; }
    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
    public ArrayList<RepuestoUtilizado> getListaRepuestoUtilizados() { return listaRepuestoUtilizados; }
    public void setListaRepuestoUtilizados(ArrayList<RepuestoUtilizado> lista) { this.listaRepuestoUtilizados = lista; }

    public boolean estaTerminado() {
        return estado == EstadoOrden.FINALIZADA || estado == EstadoOrden.ENTREGADA;
    }

    public boolean estaPendiente() {
        return estado == EstadoOrden.ABIERTA || estado == EstadoOrden.EN_PROCESO;
    }

    // Funcionalidad 7
    public String asignarTecnico(Tecnico nuevo) {
        if (!estaPendiente()) {
            return "No se puede asignar técnico a un detalle en estado " + estado;
        }
        if (nuevo.getEstado() != EstadoLaboral.ACTIVO) {
            return "El técnico " + nuevo.getName() + " está inactivo";
        }
        if (!nuevo.estaAutorizadoPara(equipo)) {
            return "El técnico " + nuevo.getName() + " (" + nuevo.getEspecialidad()
                    + ") no está autorizado para intervenir un equipo " + equipo.getTipo();
        }
        this.tecnico = nuevo;
        return "Técnico " + nuevo.getName() + " asignado al detalle " + id;
    }

    // Funcionalidad 8
    public String registrarRepuesto(Repuesto repuesto, int cantidad) {
        if (!(tipoServicio instanceof ReparacionCorrectiva)) {
            return "Solo las reparaciones correctivas admiten repuestos";
        }
        if (!estaPendiente()) {
            return "No se pueden agregar repuestos a un detalle en estado " + estado;
        }
        if (cantidad <= 0) {
            return "La cantidad debe ser mayor a cero";
        }
        if (!repuesto.descontar(cantidad)) {
            return "Existencias insuficientes de " + repuesto.getName() + " (disponibles: " + repuesto.getStock() + ")";
        }
        for (RepuestoUtilizado ru : listaRepuestoUtilizados) {
            if (ru.getRepuesto().getCode().equals(repuesto.getCode())
                    && ru.getPrecioAplicado() == repuesto.getPrecioU()) {
                ru.setCantidad(ru.getCantidad() + cantidad);
                return cantidad + " unidad(es) más de " + repuesto.getName() + " registradas en el detalle " + id;
            }
        }
        listaRepuestoUtilizados.add(new RepuestoUtilizado(repuesto, cantidad));
        return cantidad + " unidad(es) de " + repuesto.getName() + " registradas en el detalle " + id;
    }

    public int calcularCostoRepuestos() {
        int total = 0;
        for (RepuestoUtilizado ru : listaRepuestoUtilizados) {
            total += ru.getSubtotal();
        }
        return total;
    }

    // Funcionalidad 9
    public int calcularValor() {
        return tipoServicio.calcularCosto(this);
    }

    // Funcionalidad 11
    public String actualizarEstado(EstadoOrden nuevo) {
        if (estado == EstadoOrden.ENTREGADA || estado == EstadoOrden.CANCELADA) {
            return "El detalle está " + estado + " y ya no se puede modificar";
        }
        if (nuevo == estado) {
            return "El detalle ya estaba en estado " + estado;
        }
        if (nuevo != EstadoOrden.CANCELADA && nuevo != EstadoOrden.ABIERTA && tecnico == null) {
            return "Debe asignar un técnico antes de cambiar el estado a " + nuevo;
        }
        if (nuevo == EstadoOrden.ENTREGADA && estado != EstadoOrden.FINALIZADA) {
            return "Solo se puede entregar un detalle finalizado";
        }
        this.estado = nuevo;
        return "Detalle " + id + " actualizado a " + nuevo;
    }

    public String resumen() {
        String nombreTecnico = (tecnico == null) ? "sin técnico" : tecnico.getName();
        return "Detalle " + id + " | Equipo: " + equipo.getId() + " (" + equipo.getTipo() + ") | Servicio: "
                + tipoServicio.getTipo() + " | " + estado + " | " + nombreTecnico
                + " | Valor: $" + calcularValor();
    }
}