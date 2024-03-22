package scooter.page_object.enums;

public enum ScooterColor {
    BLACK("black"),
    GREY("grey");
    private final String color;

    ScooterColor(String color) {this.color = color;}

    public String getMode() { return color;}
}
