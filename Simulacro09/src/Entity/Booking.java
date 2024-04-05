package Entity;

public class Booking {
    private int id;
    private String booking_date;
    private String seat;
    private int id_passenger;
    private int id_flight;


    public Booking(){}

    public Booking(int id, String booking_date, String seat, int id_passenger, int id_flight) {
        this.id = id;
        this.booking_date = booking_date;
        this.seat = seat;
        this.id_passenger = id_passenger;
        this.id_flight = id_flight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(int id_passenger) {
        this.id_passenger = id_passenger;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", booking_date='" + booking_date + '\'' +
                ", seat='" + seat + '\'' +
                ", id_passenger=" + id_passenger +
                ", id_flight=" + id_flight +
                '}';
    }


}
