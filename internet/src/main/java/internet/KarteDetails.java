package internet;


public class KarteDetails {
    private String karteName;
    private String karteNummer;
    private String cvc;

    public KarteDetails(String karteName, String karteNummer, String cvc) {
        this.karteName = karteName;
        this.karteNummer = karteNummer;
        this.cvc = cvc;
    }

    public KarteDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getKarteName() {
        return karteName;
    }
    public String getKarteNummer() {
        return karteNummer;
    }
    public String getCvc() {
        return cvc;
    }
    public void setKarteNummer(String karteNummer) {
        this.karteNummer = karteNummer;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    public void setKarteName(String karteName) {
        this.karteName = karteName;
    }
    
}