package Entity;

public class Passenger {

    private int id;
    private String name;
    private String last_name;
    private String identity;


    public Passenger(){}

    public Passenger(int id, String name, String last_name, String identity) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
