package model;

public abstract class Empleado {
    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    //Asumo que estos son porcentajes
    protected float descuentoSalud;
    protected float descuentoPension;

    public Empleado(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoria = categoria;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;
    }
    public abstract double calcularSalarioBruto();
    public double calcularBonificacionCategoria(){
        double total=0;
        if(categoria==CategoriaEmpleado.JUNIOR){
            total= salarioBase*0.05;
        }
        if(categoria==CategoriaEmpleado.SEMI_SENIOR){
            total= salarioBase*0.1;
        }
        if(categoria==CategoriaEmpleado.SENIOR){
            total= salarioBase*0.15;
        }
        return total;
    }
    //Formula sacada de google en el documento no especifica
    public double calcularDescuentos(){
        double total=calcularSalarioBruto()*((descuentoSalud/100)+(descuentoPension/100));
        return total;
    }
    public double calcularSalarioNeto(){
        double total=calcularSalarioBruto()-calcularDescuentos();
        return total;
    }
    public void mostrarInformacion(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Documento: "+documento);
        System.out.println("Edad: "+edad);
        System.out.println("Salario Base: "+salarioBase);
        System.out.println("Categoria: "+categoria);
        System.out.println("Porcentaje de decuento en salud: "+descuentoSalud+'%');
        System.out.println("Porcentaje de decuento en pension: "+descuentoPension+'%');
        System.out.println("Total descuentos: "+calcularDescuentos());
        System.out.println("Salario Neto :"+calcularSalarioNeto());
        System.out.println("Salario bruto: "+calcularSalarioBruto());
        System.out.println("Total bonificacion: "+calcularBonificacionCategoria());
    }
    public ResumenPago generarResumenPago(){

        ResumenPago resumen=new ResumenPago(documento,nombre,categoria,calcularSalarioBruto(),calcularDescuentos(),calcularSalarioNeto());
        return resumen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }

    public float getDescuentoSalud() {
        return descuentoSalud;
    }

    public void setDescuentoSalud(float descuentoSalud) {
        this.descuentoSalud = descuentoSalud;
    }

    public float getDescuentoPension() {
        return descuentoPension;
    }

    public void setDescuentoPension(float descuentoPension) {
        this.descuentoPension = descuentoPension;
    }
}
