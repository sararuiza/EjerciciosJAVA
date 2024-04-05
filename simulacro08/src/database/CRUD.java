package database;

import java.util.ArrayList;
import java.util.List;

public interface CRUD {

    public Object create(Object object);

    public ArrayList<Object> read ();
    public boolean update (Object object);
    public boolean delete (Object object);
    public Object find(int id);

}
