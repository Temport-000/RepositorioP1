package model;
public abstract class Servicio{
    protected int code;
    protected int costoFijo;
    protected String name;
    protected String desc;


    public Servicio(int code, int costoFijo, String name, String desc) {
        this.code = code;
        this.costoFijo = costoFijo;
        this.name = name;
        this.desc = desc;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCostoFijo() {
        return costoFijo;
    }

    public void setCostoFijo(int costoFijo) {
        this.costoFijo = costoFijo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTipo() {
        return getClass().getSimpleName();
    }

    public abstract int calcularCosto(DetalleOrden detalle);
}