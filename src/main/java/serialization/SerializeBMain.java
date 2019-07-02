package serialization;

import serialization.model.Employee;
import serialization.model.SSN;

import java.io.*;
import java.util.Collections;

public class SerializeBMain {

    static final String JOHN_SER_FILE_LOCATION = "tmp/employeeJohn.ser";

    public static void main(String[] args) {
        SSN SSN = new SSN();
        SSN.ssn = 1000050000;
        Employee employeeJohn = new Employee();
        employeeJohn.SSNObj = SSN;
        employeeJohn.salary = 4000;
        employeeJohn.name = "John";

        Employee employeeBob = new Employee();
        employeeBob.name = "Bob";

        employeeJohn.colleagues = Collections.singletonList(employeeBob);

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(JOHN_SER_FILE_LOCATION);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employeeJohn);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved to "+JOHN_SER_FILE_LOCATION);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
