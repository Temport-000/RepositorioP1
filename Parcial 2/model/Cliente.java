package model;

import java.util.ArrayList;
import java.util.Optional;

public class Cliente {
    private String id;
    private String name;
    private String tel;
    private String eMail;
    private String add;
    private ArrayList<Orden> listaOrdenes = new ArrayList<>();
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();

    public Cliente(String id, String name, String tel, String eMail, String add) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.eMail = eMail;
        this.add = add;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public String getAdd() {
        return add;
    }
    public void setAdd(String add) {
        this.add = add;
    }
    public ArrayList<Orden> getListaOrdenes() {
        return listaOrdenes;
    }
    public void setListaOrdenes(ArrayList<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }
    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }


    public void agregarEquipo(Equipo equipo) {
        this.listaEquipos.add(equipo);
    }

    public void agregarOrden(Orden orden) {
        this.listaOrdenes.add(orden);
    }

    public Optional<Equipo> buscarEquipo(String codigo) {
        for (Equipo aux : listaEquipos) {
            if (aux.getId().equals(codigo)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Optional<Orden> buscarOrden(String idOrden) {
        for (Orden aux : listaOrdenes) {
            if (aux.getId().equals(idOrden)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

}