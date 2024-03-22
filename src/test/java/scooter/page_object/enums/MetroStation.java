package scooter.page_object.enums;

public enum MetroStation {
    BULVAR_ROKOSSOVSKOGO(1),
    CHERKIZOVSKAYA(2),
    PREOBRAZHENSKATYA_PLOSHAD(3);
    private final Integer metroStation;

    MetroStation(Integer metroStation) {this.metroStation = metroStation;}

    public Integer getMode() {return metroStation;}

}
