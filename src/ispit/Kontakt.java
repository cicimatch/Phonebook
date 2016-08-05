package ispit;

public class Kontakt {
	private int id;
	private String ime;
	private String prezime;
	private String nazivRadnogMesta;
	private String brojProstorije;
	private int brojLokala;
	//konstruktor bez parametera
	public Kontakt() {
	}
	//konstruktor sa parametrima
	public Kontakt(int id, String ime, String prezime, String nazivRadnogMesta, String brojProstorije, int brojLokala) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.nazivRadnogMesta = nazivRadnogMesta;
		this.brojProstorije = brojProstorije;
		this.brojLokala = brojLokala;
	}
	//geteri i seteri
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getNazivRadnogMesta() {
		return nazivRadnogMesta;
	}
	public void setNazivRadnogMesta(String nazivRadnogMesta) {
		this.nazivRadnogMesta = nazivRadnogMesta;
	}
	public String getBrojProstorije() {
		return brojProstorije;
	}
	public void setBrojProstorije(String brojProstorije) {
		this.brojProstorije = brojProstorije;
	}
	public int getBrojLokala() {
		return brojLokala;
	}
	public void setBrojLokala(int brojLokala) {
		this.brojLokala = brojLokala;
	}
	//toString metoda
	/*public String toString() {
		return "Kontakt [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", nazivRadnogMesta=" + nazivRadnogMesta
				+ ", brojProstorije=" + brojProstorije + ", brojLokala=" + brojLokala + "]";
	}*/
	public String toString() {
		return String.format("%15s %15s %15s %20s %15s %15s", id, ime, prezime, nazivRadnogMesta,brojProstorije, brojLokala);
	}
	public static void main(String[] args) {
		Kontakt kontakt = new Kontakt(1, "Ivan", "Vojnovic", "sluzbenik", "12A", 23);
		System.out.println(kontakt);
	}
	
	

}
