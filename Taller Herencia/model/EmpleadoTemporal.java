package model;

public class EmpleadoTemporal extends Empleado {
private int diasTrabajados;
private float valorDia;

    public EmpleadoTemporal(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension, int diasTrabajados, float valorDia){
        super(nombre,documento,edad,salarioBase,categoria,descuentoSalud,descuentoPension);
        this.diasTrabajados=diasTrabajados;
        this.valorDia=valorDia;
    }

    @Override
    public double calcularSalarioBruto() {
        double total=Double.valueOf(diasTrabajados)*Double.valueOf(valorDia)+calcularBonificacionCategoria();
        return total;
    }
}
