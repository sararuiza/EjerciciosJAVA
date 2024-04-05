package Entity;

public class Airplane {
    private int id;
    private String model;
    private String capacity;

    public Airplane(){}

    public Airplane(int id, String model, String capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
