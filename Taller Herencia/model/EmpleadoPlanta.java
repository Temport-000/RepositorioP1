package model;

public class EmpleadoPlanta extends Empleado{
    private String cargo;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte;

    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,String cargo,int horasExtra,float valorHoraExtra,
    float auxilioTransporte){
        super(nombre,documento,edad,salarioBase,categoria,descuentoSalud,descuentoPension);
        this.cargo=cargo;
        this.horasExtra=horasExtra;
        this.valorHoraExtra=valorHoraExtra;
        this.auxilioTransporte=auxilioTransporte;
    }

    @Override
    public double calcularSalarioBruto() {
        double total=Double.valueOf(salarioBase)+calcularBonificacionCategoria()+Double.valueOf(horasExtra)*Double.valueOf(valorHoraExtra)+Double.valueOf(auxilioTransporte);
        return total;
    }
}
