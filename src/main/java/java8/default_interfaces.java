package java8;

public class default_interfaces {
    public interface Vehicle {

        default String turnAlarmOn() {
            return "Turning the vehicle alarm on.";
        }

        default String turnAlarmOff() {
            return "Turning the vehicle alarm off.";
        }
    }

    public interface Alarm {

        default String turnAlarmOn() {
            return "Turning the alarm on.";
        }

        default String turnAlarmOff() {
            return "Turning the alarm off.";
        }
    }

    public class Car implements Vehicle, Alarm {

        @Override
        public String turnAlarmOn() {
            return Vehicle.super.turnAlarmOn();
        }

        @Override
        public String turnAlarmOff() {
            return Vehicle.super.turnAlarmOff();
        }
    }
}
