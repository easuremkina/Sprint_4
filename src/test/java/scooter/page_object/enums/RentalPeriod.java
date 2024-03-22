package scooter.page_object.enums;

public enum RentalPeriod {
    SUTKI("сутки"),
    DVOE_SUTOK("двое суток"),
    TROE_SUTOK("трое суток"),
    CHETVERO_SUTOK("четверо суток"),
    PYATERO_SUTOK("пятеро суток"),
    SHESTERO_SUTOK("шестеро суток"),
    SEMERO_SUTOK("семеро суток");
    private final String rentalPeriod;

    RentalPeriod(String rentalPeriod) { this.rentalPeriod = rentalPeriod;}

    public String getMode() { return rentalPeriod;}
}
