package entity;

public class doctor {
    private int id;
    private String name;
    private String last_name;

    private int id_specialty;


    public doctor(){

    }

    public doctor(int id, String name, String last_name, int id_specialty) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.id_specialty = id_specialty;
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

    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    @Override
    public String toString() {
        return "doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", id_specialty=" + id_specialty +
                '}';
    }
}
