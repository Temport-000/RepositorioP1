package model;

import java.util.ArrayList;
import java.util.Optional;

public class Empresa {
    private String name;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Repuesto> listaRepuestos = new ArrayList<>();
    private ArrayList<Tecnico> listaTecnicos = new ArrayList<>();

    public Empresa(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    public ArrayList<Repuesto> getListaRepuesto() {
        return listaRepuestos;
    }
    public void setListaRepuesto(ArrayList<Repuesto> listaRepuesto) {
        this.listaRepuestos = listaRepuesto;
    }
    public ArrayList<Tecnico> getListaTecnico() {
        return listaTecnicos;
    }
    public void setListaTecnico(ArrayList<Tecnico> listaTecnico) {
        this.listaTecnicos = listaTecnico;
    }
    //Funcionalidad 1
    public String registrarCliente(Cliente cliente) {
        Optional<Cliente> clienteMo = buscarCliente(cliente.getId());
        if (clienteMo.isPresent()) {
            return "El cliente ya estaba registrado";
        }
        listaClientes.add(cliente);
        return "Cliente registrado con éxito";
    }

    public Optional<Cliente> buscarCliente(String docu) {
        for (Cliente aux : listaClientes) {
            if (aux.getId().equals(docu)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    //Funcionalidad 2
    public String registrarEquipo(Equipo equipo, String idCliente) {
        Optional<Cliente> cl = buscarCliente(idCliente);
        if (!cl.isPresent()) {
            return "Cliente no encontrado";
        }
        if (buscarEquipo(equipo.getId()).isPresent()) {
            return "Ya existe un equipo con el código " + equipo.getId();
        }
        if (buscarEquipoPorSerie(equipo.getsN()).isPresent()) {
            return "Ya existe un equipo con el número de serie " + equipo.getsN();
        }
        cl.get().agregarEquipo(equipo);
        return "El equipo " + equipo.getId() + " quedó registrado con el cliente " + cl.get().getName();
    }

    public Optional<Equipo> buscarEquipo(String codigo) {
        for (Cliente c : listaClientes) {
            Optional<Equipo> equipo = c.buscarEquipo(codigo);
            if (equipo.isPresent()) {
                return equipo;
            }
        }
        return Optional.empty();
    }

    public Optional<Equipo> buscarEquipoPorSerie(String sN) {
        for (Cliente c : listaClientes) {
            for (Equipo e : c.getListaEquipos()) {
                if (e.getsN().equals(sN)) {
                    return Optional.of(e);
                }
            }
        }
        return Optional.empty();
    }

    //Funcionalidad 3
    public String registrarTecnico(Tecnico tecnico) {
        Optional<Tecnico> tecnicoMo = buscarTecnico(tecnico.getId());
        if (tecnicoMo.isPresent()) {
            return "El técnico ya estaba registrado";
        }
        listaTecnicos.add(tecnico);
        return "Técnico registrado con éxito";
    }

    public Optional<Tecnico> buscarTecnico(String docu) {
        for (Tecnico aux : listaTecnicos) {
            if (aux.getId().equals(docu)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    //Funcionalidad 4
    public String agregarRepuesto(Repuesto repuesto) {
        if (repuesto.getPrecioU() <= 0 || repuesto.getStock() < 0) {
            return "El precio debe ser mayor a cero y las existencias no pueden ser negativas";
        }
        Optional<Repuesto> repMo = buscarRepuesto(repuesto.getCode());
        if (repMo.isPresent()) {
            return "Este repuesto ya está registrado";
        }
        listaRepuestos.add(repuesto);
        return "El repuesto fue registrado con éxito";
    }

    public Optional<Repuesto> buscarRepuesto(String code) {
        for (Repuesto aux : listaRepuestos) {
            if (aux.getCode().equals(code)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    //Funcionalidad 5
    public String agregarOrden(Orden orden, String idCliente) {
        Optional<Cliente> cl = buscarCliente(idCliente);
        if (!cl.isPresent()) {
            return "Cliente no encontrado";
        }
        if (buscarOrden(orden.getId()).isPresent()) {
            return "Esta orden ya existe";
        }
        orden.setEstado(EstadoOrden.ABIERTA); // toda orden nueva nace abierta
        cl.get().agregarOrden(orden);
        return "La orden " + orden.getId() + " se le asignó al cliente " + cl.get().getName();
    }

    public Optional<Orden> buscarOrden(String idOrden) {
        for (Cliente c : listaClientes) {
            Optional<Orden> orden = c.buscarOrden(idOrden);
            if (orden.isPresent()) {
                return orden;
            }
        }
        return Optional.empty();
    }

    public Optional<Cliente> buscarClientePorOrden(String idOrden) {
        for (Cliente c : listaClientes) {
            if (c.buscarOrden(idOrden).isPresent()) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    //Funcionalidad 6
    public String registrarDetalleOrden(DetalleOrden detalleOrden, String idOrden) {
        Optional<Orden> orden = buscarOrden(idOrden);
        Optional<Cliente> dueno = buscarClientePorOrden(idOrden);
        if (!orden.isPresent() || !dueno.isPresent()) {
            return "Orden no encontrada";
        }
        if (!dueno.get().buscarEquipo(detalleOrden.getEquipo().getId()).isPresent()) {
            return "El equipo no pertenece al cliente de la orden";
        }
        return orden.get().agregarDetalleOrden(detalleOrden);
    }

    public Optional<DetalleOrden> buscarDetalleOrden(String idOrden, String idDetalle) {
        Optional<Orden> orden = buscarOrden(idOrden);
        if (!orden.isPresent()) {
            return Optional.empty();
        }
        return orden.get().buscarDetalle(idDetalle);
    }

    //Funcionalidad 7
    public String asignarTecnico(String idOrden, String idDetalle, String idTecnico) {
        Optional<DetalleOrden> detalle = buscarDetalleOrden(idOrden, idDetalle);
        if (!detalle.isPresent()) {
            return "Detalle no encontrado";
        }
        Optional<Tecnico> tecnico = buscarTecnico(idTecnico);
        if (!tecnico.isPresent()) {
            return "Técnico no encontrado";
        }
        return detalle.get().asignarTecnico(tecnico.get());
    }

    //Funcionalidad 8
    public String registrarRepuestoUtilizado(String idOrden, String idDetalle, String codigoRepuesto, int cantidad) {
        Optional<DetalleOrden> detalle = buscarDetalleOrden(idOrden, idDetalle);
        if (!detalle.isPresent()) {
            return "Detalle no encontrado";
        }
        Optional<Repuesto> repuesto = buscarRepuesto(codigoRepuesto);
        if (!repuesto.isPresent()) {
            return "Repuesto no encontrado";
        }
        return detalle.get().registrarRepuesto(repuesto.get(), cantidad);
    }

    //Funcionalidad 9
    public int calcularValorDetalle(String idOrden, String idDetalle) {
        Optional<DetalleOrden> detalle = buscarDetalleOrden(idOrden, idDetalle);
        if (!detalle.isPresent()) {
            return -1;
        }
        return detalle.get().calcularValor();
    }

    //Funcionalidad 10
    public int calcularTotalOrden(String idOrden) {
        Optional<Orden> orden = buscarOrden(idOrden);
        if (!orden.isPresent()) {
            return -1;
        }
        return orden.get().calcularTotal();
    }

    //Funcionalidad 11
    public String actualizarEstadoDetalle(String idOrden, String idDetalle, EstadoOrden nuevoEstado) {
        Optional<DetalleOrden> detalle = buscarDetalleOrden(idOrden, idDetalle);
        if (!detalle.isPresent()) {
            return "Detalle no encontrado";
        }
        return detalle.get().actualizarEstado(nuevoEstado);
    }

    //Funcionalidad 12
    public String finalizarOrden(String idOrden) {
        Optional<Orden> orden = buscarOrden(idOrden);
        if (!orden.isPresent()) {
            return "Orden no encontrada";
        }
        return orden.get().finalizar();
    }

    //Funcionalidad 13
    public String consultarOrdenesCliente(String idCliente) {
        Optional<Cliente> cl = buscarCliente(idCliente);
        if (!cl.isPresent()) {
            return "Cliente no encontrado";
        }
        Cliente cliente = cl.get();
        if (cliente.getListaOrdenes().isEmpty()) {
            return "El cliente " + cliente.getName() + " no tiene órdenes";
        }
        String texto = "ordenes de " + cliente.getName() + ":\n";
        for (Orden o : cliente.getListaOrdenes()) {
            texto += "Orden " + o.getId() + " | " + o.getDate() + " | " + o.getEstado()
                    + " | Total: $" + o.calcularTotal() + "\n";
            for (DetalleOrden d : o.getListaDetallesOrden()) {
                texto += "   - " + d.resumen() + "\n";
            }
        }
        return texto;
    }

    //Funcionalidad 14
    public String consultarTrabajosTecnico(String idTecnico) {
        Optional<Tecnico> tec = buscarTecnico(idTecnico);
        if (!tec.isPresent()) {
            return "Técnico no encontrado";
        }
        String texto = "Trabajos de " + tec.get().getName() + ":\n";
        boolean hayTrabajos = false;
        for (EstadoOrden estado : EstadoOrden.values()) {
            String grupo = "";
            for (Cliente c : listaClientes) {
                for (Orden o : c.getListaOrdenes()) {
                    for (DetalleOrden d : o.getListaDetallesOrden()) {
                        if (d.getTecnico() != null && d.getTecnico().getId().equals(idTecnico)
                                && d.getEstado() == estado) {
                            grupo += "   - Orden " + o.getId() + " - " + d.resumen() + "\n";
                        }
                    }
                }
            }
            if (!grupo.equals("")) {
                texto += "[" + estado + "]\n" + grupo;
                hayTrabajos = true;
            }
        }
        if (!hayTrabajos) {
            return "El técnico " + tec.get().getName() + " no tiene trabajos asignados";
        }
        return texto;
    }

    //Funcionalidad 15
    public String consultarEquiposPendientes() {
        String texto = "";
        for (Cliente c : listaClientes) {
            ArrayList<Equipo> pendientes = new ArrayList<>();
            for (Orden o : c.getListaOrdenes()) {
                for (DetalleOrden d : o.getListaDetallesOrden()) {
                    if (d.estaPendiente() && !pendientes.contains(d.getEquipo())) {
                        pendientes.add(d.getEquipo());
                    }
                }
            }
            if (!pendientes.isEmpty()) {
                texto += "Cliente " + c.getName() + ":\n";
                for (Equipo e : pendientes) {
                    texto += "   - " + e + "\n";
                }
            }
        }
        if (texto.equals("")) {
            return "No hay equipos con intervenciones pendientes";
        }
        return texto;
    }

    //Funcionalidad 16
    public String generarReporteServicios() {
        int cantDiagnostico = 0;
        int totalDiagnostico = 0;
        int cantPreventivo = 0;
        int totalPreventivo = 0;
        int cantCorrectiva = 0;
        int totalCorrectiva = 0;
        for (Cliente c : listaClientes) {
            for (Orden o : c.getListaOrdenes()) {
                for (DetalleOrden d : o.getListaDetallesOrden()) {
                    if (d.estaTerminado()) {
                        if (d.getTipoServicio() instanceof Diagnostico) {
                            cantDiagnostico++;
                            totalDiagnostico += d.calcularValor();
                        } else if (d.getTipoServicio() instanceof MantenimientoPreventivo) {
                            cantPreventivo++;
                            totalPreventivo += d.calcularValor();
                        } else if (d.getTipoServicio() instanceof ReparacionCorrectiva) {
                            cantCorrectiva++;
                            totalCorrectiva += d.calcularValor();
                        }
                    }
                }
            }
        }
        int granTotal = totalDiagnostico + totalPreventivo + totalCorrectiva;
        return "Reporte de servicios:\n"
                + "Diagnóstico: " + cantDiagnostico + " servicio(s) | Facturado: $" + totalDiagnostico + "\n"
                + "Mantenimiento preventivo: " + cantPreventivo + " servicio(s) | Facturado: $" + totalPreventivo + "\n"
                + "Reparación correctiva: " + cantCorrectiva + " servicio(s) | Facturado: $" + totalCorrectiva + "\n"
                + "TOTAL FACTURADO: $" + granTotal;
    }

    //Funcionalidad 17
    public String generarReporteTecnicos() {
        if (listaTecnicos.isEmpty()) {
            return "No hay técnicos registrados";
        }
        String texto = "Reporte de técnicos:\n";
        for (Tecnico t : listaTecnicos) {
            int finalizadas = 0;
            int valor = 0;
            for (Cliente c : listaClientes) {
                for (Orden o : c.getListaOrdenes()) {
                    for (DetalleOrden d : o.getListaDetallesOrden()) {
                        if (d.estaTerminado() && d.getTecnico() != null && d.getTecnico().getId().equals(t.getId())) {
                            finalizadas++;
                            valor += d.calcularValor();
                        }
                    }
                }
            }
            texto += t.getName() + " (" + t.getId() + "): " + finalizadas
                    + " intervención(es) finalizada(s) | Valor atendido: $" + valor + "\n";
        }
        return texto;
    }

    //Funcionalidad 18
    public String consultarInventarioCritico() {
        String texto = "";
        for (Repuesto r : listaRepuestos) {
            if (r.getStock() < 5) {
                texto += "   - " + r + "\n";
            }
        }
        if (texto.equals("")) {
            return "No hay repuestos con existencias críticas";
        }
        return "Repuestos con menos de 5 unidades:\n" + texto;
    }
}





















