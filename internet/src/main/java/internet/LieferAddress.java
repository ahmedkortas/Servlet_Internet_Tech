package internet;


public class LieferAddress{
    private String plz;
    private String ort;
    private String street;
    private String land;

    public LieferAddress(String plz, String ort, String street,String land) {
        this.plz = plz;
        this.ort = ort;
        this.street = street;
    }

    public LieferAddress() {
		// TODO Auto-generated constructor stub
	}

	public String getLand() {
        return land;
    }

    public String getPlz() {
        return plz;
    }
    public String getOrt() {
        return ort;
    }

    public String getStreet() {
        return street;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public void setLand(String land) {
        this.land = land;
    }


    public String addressToString() {
        return "LieferAddress: " + this.plz + " " + this.ort + " " + this.street + " " + this.land;
    }
}