package internet;


public class RechnungAddress {
    private String plz;
    private String ort;
    private String land;
    private String street;

    public void Rechnung(String plz, String ort, String land, String street) {
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlz() {
        return plz;
    }
    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setLand(String land) {
        this.land = land;
    }
}