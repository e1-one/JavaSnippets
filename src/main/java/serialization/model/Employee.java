package serialization.model;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable {
    private static final long serialVersionUID = 2647637072473877947L;

    public int salary;
    public SSN SSNObj;
    public List colleagues;
    public String name;

}
