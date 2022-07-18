package classes;

public class Cliente implements Comparable<Cliente>{
    private int id;
    private int id_empresa;
    private String nome;
    private String endereco;
    private String longitude;
    private String latitude;

    public Cliente() {
    }
    
    public Cliente(int id_emp, String n, String ender) {
        this.id_empresa = id_emp;
        this.nome = n;
        this.endereco = ender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
        
    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }        

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", id_empresa=" + id_empresa + ", nome=" + nome + '}';
    }

    @Override
    public int compareTo(Cliente o) {
        if(this.getNome().compareTo(o.getNome()) < 0){
            return -1;
        }else if(this.getNome().compareTo(o.getNome()) > 0){
            return 1;
        }
        return 0;
    }

}
