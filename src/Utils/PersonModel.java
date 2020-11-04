package Utils;

/**
 *
 * @author Fred
 */
public class PersonModel {
    
    private int id;
    private String first_name;
    private String last_name;
    private String cargo;
    private String data;

    public PersonModel() {
    }
    
    //construtor
    public PersonModel(int id, String first_name, String last_name, String cargo, String data) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cargo = cargo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
