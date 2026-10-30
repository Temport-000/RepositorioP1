package co.edu.uniquindio.poo.taller.controller;

import co.edu.uniquindio.poo.taller.model.Empresa;
import co.edu.uniquindio.poo.taller.model.Producto;

import java.util.ArrayList;

public class ProductoController {
    private Empresa empresa;

    public ProductoController(Empresa empresa) {
        this.empresa = empresa;
    }
    public boolean crearProducto(Producto producto) {
        return empresa.agregarProducto(producto);
    }

    public ArrayList<Producto> obtenerListaProductos() {
        return empresa.getListaProductos();
    }

    public boolean eliminarProducto(String iD) {
        return empresa.eliminarProducto(iD);
    }

    public boolean actualizarProducto(String iDOriginal, Producto producto) {
        return empresa.actualizarProducto(iDOriginal, producto);
    }
}
