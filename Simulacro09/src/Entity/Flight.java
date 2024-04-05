package Entity;

public class Flight {

    private int id;
    private String destination;
    private String departure_date;
    private String departure_time;
    private int id_airplane;

    public Flight(){}
    public Flight(int id, String destination, String departure_date, String departure_time, int id_airplane) {
        this.id = id;
        this.destination = destination;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.id_airplane = id_airplane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public int getId_airplane() {
        return id_airplane;
    }

    public void setId_airplane(int id_airplane) {
        this.id_airplane = id_airplane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", id_airplane=" + id_airplane +
                '}';
    }
}
