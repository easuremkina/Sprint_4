package scooter.model;

import scooter.page_object.enums.MetroStation;

public class TestUserPersData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final MetroStation metroStation;

    public TestUserPersData (String firstName, String lastName, String address, String phone, MetroStation metroStation) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.metroStation = metroStation;
    }

    public TestUserPersData() {
        this.firstName = "Галина";
        this.lastName = "Петрова";
        this.address = "г. Москва, ул. Фрунзе, д. 5";
        this.phone = "+79124425608";
        this.metroStation = MetroStation.CHERKIZOVSKAYA;
    }
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getAddress() { return address; }

    public String getPhone() { return phone; }

    public MetroStation getMetroStation() { return metroStation; }
}
