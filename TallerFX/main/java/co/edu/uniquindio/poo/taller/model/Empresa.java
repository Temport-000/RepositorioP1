package co.edu.uniquindio.poo.taller.model;

import java.util.*;

public class Empresa {
    private String nombre;
    private Collection<Cliente> clientes;
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
        clientes = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean centinela = false;
        if (!verificarCliente(cliente.getCedula())) {
            clientes.add(cliente);
            centinela = true;
        }
        return centinela;
    }

    public boolean eliminarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                clientes.remove(cliente);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean actualizarCliente(String cedula, Cliente actualizado) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                cliente.setCedula(actualizado.getCedula());
                cliente.setNombre(actualizado.getNombre());
                cliente.setApellido(actualizado.getApellido());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

    public boolean agregarProducto (Producto producto){
        Optional<Producto> productoVerificado =buscarProducto(producto.getiD());
        boolean isPresent=false;
        if(!productoVerificado.isPresent()){
            listaProductos.add(producto);
            isPresent=true;
        }
        return isPresent;
    }

    public Optional<Producto> buscarProducto (String iD){
        return listaProductos.stream().filter(a-> a.getiD().equals(iD)).findFirst();
    }

    public boolean eliminarProducto (String iD){
        Optional<Producto> productoBuscado= buscarProducto(iD);
        boolean isPresent=false;
        if(productoBuscado.isPresent()){
            listaProductos.remove(productoBuscado.get());
            isPresent=true;
        }
        return isPresent;
    }

    public boolean actualizarProducto(String iDOriginal, Producto actualizado) {
        Optional<Producto> buscado = buscarProducto(iDOriginal);
        if (!buscado.isPresent()) {
            return false;
        }
        boolean idCambio = !iDOriginal.equals(actualizado.getiD());
        if (idCambio && buscarProducto(actualizado.getiD()).isPresent()) {
            return false;
        }
        Producto producto = buscado.get();
        producto.setiD(actualizado.getiD());
        producto.setName(actualizado.getName());
        producto.setStock(actualizado.getStock());
        return true;
    }
}
