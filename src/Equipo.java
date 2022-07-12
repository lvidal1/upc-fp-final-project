public class Equipo {

    private String codigoEquipo;
    private String categoria;
    private String marca;
    private String modelo;
    private String HDD;
    private String RAM;
    private String CPU;
    private String GPU;
    private String estado;
    private int antiguedad;
    private String comentario;

    public Equipo(String codigoEquipo, String categoria, String marca, String modelo, String HDD, String RAM, String CPU,
                       String GPU, String estado, int antiguedad, String comentario) {

        this.codigoEquipo = codigoEquipo;
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.HDD = HDD;
        this.RAM = RAM;
        this.CPU = CPU;
        this.GPU = GPU;
        this.estado = estado;
        this.antiguedad = antiguedad;
        this.comentario = comentario;
    }

    public String getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(String codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getHDD() {
        return HDD;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
