package model;

import java.util.ArrayList;
import java.util.Optional;

public class Empresa {
    private ArrayList <Empleado> listaEmpleados= new ArrayList<>();

    public Empresa(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String registrarEmpleado(Empleado empleado){
        for(Empleado aux:listaEmpleados) {
            if (aux.getDocumento().equals(empleado.getDocumento())) {
                return "Empleado ya existente";
            }
        }
        listaEmpleados.add(empleado);
        return "Empleado registrado exitosamente";
    }
    public void mostrarEmpleados(){
        if(listaEmpleados.isEmpty()){
            System.out.println("No hay empleados Registrados");
        }else{
        int numeroEmpleado=1;
        for(Empleado aux:listaEmpleados){
            System.out.println("-------- Empleado numero: "+numeroEmpleado+"  --------");
            aux.mostrarInformacion();
            numeroEmpleado+=1;
        }}

    }
    public Empleado buscarEmpleado(String documento){
        for(Empleado aux:listaEmpleados) {
            if (aux.getDocumento().equals(documento)) {
                return aux;
            }
        }
        return null;
    }
    public Optional<Empleado> buscarEmpleadoMasGana(){
        Empleado masGana=null;
        for(Empleado aux:listaEmpleados){
            if(masGana==null || aux.calcularSalarioNeto() > masGana.calcularSalarioNeto()){
                masGana=aux;
            }
        }
        return Optional.ofNullable(masGana);
    }
    //En google dice que la nomina es el total de todos los pagos netos a sus trabajadores
    public double calcularNomina(){
        double total=0;
        for(Empleado aux:listaEmpleados){
            total+=aux.calcularSalarioNeto();
        }
        return total;
    }
    public void mostrarResumenesPago(){
        int numeroEmpleado=1;
        for(Empleado aux:listaEmpleados){
            System.out.println("-------- Empleado numero: "+numeroEmpleado+"  --------");
            System.out.println(aux.calcularSalarioNeto());
            numeroEmpleado+=1;
        }
    }



}
