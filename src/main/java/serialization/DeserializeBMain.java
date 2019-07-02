package serialization;

import serialization.model.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static serialization.SerializeBMain.JOHN_SER_FILE_LOCATION;

public class DeserializeBMain {

    public static void main(String [] args) {
        Employee e;
        try {
            FileInputStream fileIn = new FileInputStream(JOHN_SER_FILE_LOCATION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee.class not found");
            c.printStackTrace();
            return;
        }

        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e.name);
        System.out.println("SSN: " + e.SSNObj.ssn);
        System.out.println("Colleagues: " + e.colleagues.size());
    }

}
