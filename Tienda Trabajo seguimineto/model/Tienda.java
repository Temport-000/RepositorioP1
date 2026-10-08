package model;

import java.util.*;

public class Tienda {

    private final String nombre;
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final List<Factura> listaFacturas = new LinkedList<>();
    private final ArrayList<Producto> listaProductos = new ArrayList<>();


    public Tienda(String nombre, String nit,String telefono){
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }

    // setters y getters

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String registrarCliente(Cliente cliente){
        Cliente clienteEncontrado = buscarCliente(cliente.getDocumentoIdentidad());
        if(clienteEncontrado == null){
            listaClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        } return "No se puede registrar, ya existe un cliente con esa informacion registrado anteriormente.";
    }

    public Cliente buscarCliente(String documentoIdentidad) {
        for (Cliente clienteMo : listaClientes) {
            if (clienteMo.getDocumentoIdentidad().equals(documentoIdentidad)) {
                return clienteMo;
            }
        }
        return null;
    }
    public String actualizarCliente(Cliente clienteNuevo) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getDocumentoIdentidad().equals(clienteNuevo.getDocumentoIdentidad())) {
                listaClientes.set(i, clienteNuevo);
                return "El cliente fue actualizado exitosamente";
            }
        }
        return "No se puede actualizar, no existe un cliente con ese documento.";
    }
    public String eliminarCliente(String documentoIdentidad) {
        Cliente cliente = buscarCliente(documentoIdentidad);
        if (cliente != null) {
            listaClientes.remove(cliente);
            return "El cliente fue eliminado exitosamente";
        }
        return "No se puede eliminar, no existe un cliente con ese documento.";
    }

    public String registrarProducto(Producto producto) {
        if (buscarProducto(producto.getCodigo()).isEmpty()) {
            listaProductos.add(producto);
            return "El producto fue registrado exitosamente";
        }
        return "No se puede registrar, ya existe un producto con ese codigo.";
    }
    public Optional<Producto> buscarProducto(String codigo) {
        return listaProductos.stream()
                .filter(producto -> producto.getCodigo().equals(codigo))
                .findFirst();
    }
    public String actualizarProducto(Producto productoNuevo) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equals(productoNuevo.getCodigo())) {
                listaProductos.set(i, productoNuevo);
                return "El producto fue actualizado exitosamente";
            }
        }
        return "No se puede actualizar, no existe un producto con ese codigo.";
    }
    public String eliminarProducto(String codigo) {
        Optional<Producto> producto = buscarProducto(codigo);
        if (producto.isPresent()) {
            listaProductos.remove(producto.get());
            return "El producto fue eliminado exitosamente";
        }
        return "No se puede eliminar, no existe un producto con ese codigo.";
    }
    public String registrarFactura(Factura factura) {
        if (buscarFactura(factura.codigo()).isEmpty()) {
            listaFacturas.add(factura);
            return "La factura fue registrada exitosamente";
        }
        return "No se puede registrar, ya existe una factura con ese codigo.";
    }
    public String actualizarFactura(Factura facturaNueva) {
        for (int i = 0; i < listaFacturas.size(); i++) {
            if (listaFacturas.get(i).codigo().equals(facturaNueva.codigo())) {
                listaFacturas.set(i, facturaNueva);
                return "La factura fue actualizada exitosamente";
            }
        }
        return "No se puede actualizar, no existe una factura con ese codigo.";
    }
    public String eliminarFactura(String codigo) {
        Optional<Factura> factura = buscarFactura(codigo);
        if (factura.isPresent()) {
            listaFacturas.remove(factura.get());
            return "La factura fue eliminada exitosamente";
        }
        return "No se puede eliminar, no existe una factura con ese codigo.";
    }

    public Optional<Factura> buscarFactura (String codigo){
        return listaFacturas.stream().filter(factura->factura.codigo().equals(codigo)).findFirst();
}

public ArrayList<Producto> buscarProductoMayor10 (){
        ArrayList<Producto> listaProducoMayor10= new ArrayList<>();
  for(Producto aux: listaProductos){
      if(aux.getCantidadDisponible()>= 10){
        listaProducoMayor10.add(aux);
      }
  }
   return listaProducoMayor10;
}





}