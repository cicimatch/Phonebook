package ispit;

import java.util.List;


import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class Imenik {
	
	private ArrayList<Kontakt> listaKontakta;
	//konstruktor bez parametara
	public Imenik() {
		this.listaKontakta = new ArrayList<Kontakt>();
	}
	//geteri i seteri
	public ArrayList<Kontakt> getListaKontakta() {
		return listaKontakta;
	}
	public void setListaKontakta(ArrayList<Kontakt> listaKontakta) {
		this.listaKontakta = listaKontakta;
	}
	//metoda dodaje u listu prosledjeni objekat tipa kontakt
	public boolean dodavanjeKontakta(Kontakt kontakt) {
		for(int i = 0; i < listaKontakta.size(); i++) {
			Kontakt kontaktIzListe = this.listaKontakta.get(i);
			int idKontaktaIzListe = kontaktIzListe.getId();
			int idProsledjenogKontakta = kontakt.getId();
			if(idKontaktaIzListe == idProsledjenogKontakta) {
				return false;
			}
			if(this.listaKontakta.get(i).getId() == kontakt.getId()) {
				return false;
			}
		}
		Kontakt noviKontakt = new Kontakt(kontakt.getId(), kontakt.getIme(), kontakt.getPrezime(), kontakt.getNazivRadnogMesta(), kontakt.getBrojProstorije(),kontakt.getBrojLokala());
		boolean daLiJeDodat = this.listaKontakta.add(noviKontakt);
		return daLiJeDodat;
	}
	//metoda za ispis kontakata
	public void ispisKontakata() {
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		System.out.println("-----------------------------------------------------------------------------------------------");
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			Kontakt kontakt = listaKontakta.get(i);
			System.out.println(kontakt);
		}
	}
	//metoda brisanja kontakta
	public Kontakt brisanjeKontakta(int id) {
		int index = -1;
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			Kontakt kontaktIzListe = this.listaKontakta.get(i);
			int idKontaktaIzListe = kontaktIzListe.getId();
			if(idKontaktaIzListe == id) {
				index = i;
			}
		}
		if(index != -1) {
			Kontakt kontaktKojiSeBrise = this.listaKontakta.remove(index);
			return kontaktKojiSeBrise;
		}
	return null;
	}
	//metoda menja kontakt sa prosledjenim identifikacionim brojem
	public Kontakt izmenaKontakta(int id, Kontakt kontakt) {
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			Kontakt kontaktIzListe = this.listaKontakta.get(i);
			int idKontaktaIzListe = kontaktIzListe.getId();
			if(idKontaktaIzListe == id) {
				Kontakt kontaktKojiSeMenja = this.listaKontakta.set(i, kontakt);
				return kontaktKojiSeMenja;
			}
		}
		return null;
	}
	//metoda save
	public void save(String path) {
		ArrayList<String> lines = new ArrayList<String>();
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			Kontakt kontakt = this.listaKontakta.get(i);
			int identifikacioniBroj = kontakt.getId();
			String imeKontakta = kontakt.getIme();
			String prezimeKontakta = kontakt.getPrezime();
			String nazivRadnogMesta = kontakt.getNazivRadnogMesta();
			String brojProstorije = kontakt.getBrojProstorije();
			int brojLokala = kontakt.getBrojLokala();
			String line = identifikacioniBroj + ";" + imeKontakta + ";" + prezimeKontakta + ";" + nazivRadnogMesta + ";" + brojProstorije + ";" + brojLokala;
			lines.add(line);
		}
		try {
			if(!Files.exists(Paths.get(path))){
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW);
			} else {
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
			e.printStackTrace();
		}
	}
	//metoda load
	public void load(String path) {
		
		this.listaKontakta = new ArrayList<Kontakt>();
		
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			for (String line: lines) {
				String[] attributes = line.split(";");
				int id = Integer.parseInt(attributes[0]); 
				String ime = attributes[1];
				String prezime = attributes[2]; 
				String radnoMesto = attributes[3]; 
				String brojProstorije = attributes[4]; 
				int brojLokala = Integer.parseInt(attributes[5]);
				Kontakt kontakt = new Kontakt(id, ime, prezime, radnoMesto, brojProstorije, brojLokala);
				this.listaKontakta.add(kontakt);
				
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
			e.printStackTrace();
		}
	}
	//metoda za ispis svih kontakata sa istim lokalom
	public void ispisKontakataSaIstimLokalom(int lokal) {
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		for (int i = 0; i < this.listaKontakta.size(); i++) {
			Kontakt kontaktIzListe = this.listaKontakta.get(i); 
			int lokalKontaktaIzListe = kontaktIzListe.getBrojLokala();
			if(lokalKontaktaIzListe == lokal) {
				Kontakt kontaktKojiSeNalaziUProsledjenomLokalu = this.listaKontakta.get(i);
				System.out.println(kontaktKojiSeNalaziUProsledjenomLokalu);
			}
		}
	}
	//metoda za prosledjeni naziv radnog mesta, ispisuje sve podatke o svakom kontaktu koji ima to radno mesto
	public void ispisKontaktaSaIstimRadnimMestom(String nazivRadnogMesta) {
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			if ((this.listaKontakta.get(i)).getNazivRadnogMesta().equalsIgnoreCase(nazivRadnogMesta)) {
				System.out.println(this.listaKontakta.get(i));
			}
		}
	}
	//Metoda za prosledjeno ime kontakta ispisuje sve podatke o svakom kontaktu koji ima to ime
	public ArrayList<Kontakt> ispisKontakataSaIstimImenom(String ime) {
		ArrayList<Kontakt> rezultat = new ArrayList<Kontakt>();
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			if(this.listaKontakta.get(i).getIme().equals(ime)) {
				rezultat.add(this.listaKontakta.get(i));
			}
		}
		return rezultat;
	}
	//metoda za prosledjeno ime i prezime i pocetkom naziva radnog mesta, radi ako ispunjava sva 3 uslova
	public void ispisKontakataSaZadatimUslovima(String ime, String prezime, String nazivRadnogMesta) {
		System.out.printf("%15s %15s %15s %25s %15s %15s \n", "Id", "Ime kontakta", "Prezime kontakta", "Naziv radnog mesta", "Broj prostorije", "Broj lokala");
		for(int i = 0; i < this.listaKontakta.size(); i++) {
			if(this.listaKontakta.get(i).getIme().equalsIgnoreCase(ime) && this.listaKontakta.get(i).getPrezime().equalsIgnoreCase(prezime) && this.listaKontakta.get(i).getNazivRadnogMesta().startsWith(nazivRadnogMesta)) {
				System.out.println(this.listaKontakta.get(i));
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
