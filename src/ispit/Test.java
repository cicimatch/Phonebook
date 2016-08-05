package ispit;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;



public class Test {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Imenik imenik = new Imenik();
		if(Files.exists(Paths.get("kontakti.txt"))) {
			imenik.load("kontakti.txt");
		}
		
		String answer = null;
		
		do {

			System.out.println("Meni:");
			System.out.println("1. Unesi kontakt");
			System.out.println("2. Ispis svih kontakata");
			System.out.println("3. Brisanje kontakta");
			System.out.println("4. Ispis kontakata u istom lokalu");
			System.out.println("5. Ispis kontakata sa istim radnim mestom");
			System.out.println("6. Ispis kontakata sa istim imenom");
			System.out.println("7. Ispis sa istim imenom, prezimenom i nazivom radnog mesta koje pocinje sa prosledjenim stringom");
			System.out.println("8. Izmena kontakta");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
				case "1":
					unesiKontakt(imenik);
					break;
				case "2":
					ispisKontakata(imenik);
					break;
				case "3":
					brisanjeKontakta(imenik);
					break;
				case "4":
					kontaktiULokalu(imenik);
					break;
				case "5":
					kontaktiNaIstomRadnomMestu(imenik);
					break;
				case "6":
					kontaktiSaIstimImenom(imenik);
					break;
				case "7":
					kontaktiPoUslovima8(imenik);
					break;
				case "8":
					izmenaKontakta(imenik);
					break;
				case "x":
					break;
				default:
					System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}

		} while (!answer.equals("x"));

		imenik.save("kontakti.txt");
		scanner.close();

	}
	//metoda da li je uneti string sa tastature ceo broj
	public static boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	//unos kontakta
	public static void unesiKontakt(Imenik imenik) {
		int id;
		String ime;
		String prezime;
		String nazivRadnogMesta;
		String brojProstorije;
		int brojLokala;
		String idKontakta = null;
		do {
			System.out.println("Identifikacioni broj: ");
			idKontakta = scanner.nextLine();
		} while (!isNumber(idKontakta));
		id = Integer.parseInt(idKontakta);
		System.out.println("Ime kontakta: ");
		ime = scanner.nextLine();
		System.out.println("Prezime kontakta: ");
		prezime = scanner.nextLine();
		System.out.println("Radno mesto: ");
		nazivRadnogMesta = scanner.nextLine();
		System.out.println("Broj prostorije: ");
		brojProstorije = scanner.nextLine();
		String brLokala = null;
		do {
			System.out.println("Broj lokala: ");
			brLokala = scanner.nextLine();
		} while (!isNumber(brLokala));
		brojLokala = Integer.parseInt(brLokala);
		
		Kontakt kontakt = new Kontakt(id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala);
		boolean provera = imenik.dodavanjeKontakta(kontakt);
		if(provera == true){
			System.out.println("Kontakt je uspesno dodat!");
		} else {
			System.out.println("Kontakt nije dodat!");
		}
	}
	//ispis kontakata
	public static void ispisKontakata(Imenik imenik) {

		imenik.ispisKontakata();
	}
	//brisanje kontakata
	public static void  brisanjeKontakta(Imenik imenik) {
		int id;
		String idKontakta = null;
		do {
			System.out.println("Identifikacioni broj za brisanje");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		id = Integer.valueOf(idKontakta);
		Kontakt provera = imenik.brisanjeKontakta(id);
		if(provera == null) {
			System.out.println("Kontakt sa zadatim brojem ne postoji!");
		}
	}
	//kontakti u lokalu
	public static void kontaktiULokalu(Imenik imenik) {
		String brLokala = null;
		do {
			System.out.println("Unesite broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		int brojLokala = Integer.valueOf(brLokala);
		imenik.ispisKontakataSaIstimLokalom(brojLokala);
	}
	public static void kontaktiNaIstomRadnomMestu(Imenik imenik) {
		System.out.println("Unesite radno mesto: ");
		String radnoMesto = scanner.nextLine();
		imenik.ispisKontaktaSaIstimRadnimMestom(radnoMesto);
	}
	public static void kontaktiSaIstimImenom(Imenik imenik) {
		System.out.println("Unesite ime: ");
		String ime = scanner.nextLine();
		imenik.ispisKontakataSaIstimImenom(ime);
	}
	public static void kontaktiPoUslovima8(Imenik imenik) {
		System.out.println("Unesite ime: ");
		String ime = scanner.nextLine();
		System.out.println("Unesite prezime: ");
		String prezime = scanner.nextLine();
		System.out.println("Unesite pocetno slovo radnog mesta: ");
		String radnoMesto = scanner.nextLine();
		imenik.ispisKontakataSaZadatimUslovima(ime, prezime, radnoMesto);
	}
	public static void izmenaKontakta(Imenik imenik) {
		String idKontakta = null;
		do {
			System.out.println("Unesite identifikacioni broj kontakta: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		int id = Integer.valueOf(idKontakta);
		String ime;
		String prezime;
		String nazivRadnogMesta;
		String brojProstorije;
		int brojLokala;
		System.out.println("Ime kontakta: ");
		ime = scanner.nextLine();
		System.out.println("Prezime kontakta: ");
		prezime = scanner.nextLine();
		System.out.println("Naziv radnog mesta: ");
		nazivRadnogMesta = scanner.nextLine();
		System.out.println("Broj prostorije: ");
		brojProstorije = scanner.nextLine();
		String brLokala = null;
		do {
			System.out.println("Broj lokala: ");
		} while(!isNumber(brLokala));
		brojLokala = Integer.valueOf(brLokala);
		Kontakt kontakt = new Kontakt(id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala);
		Kontakt provera = imenik.izmenaKontakta(id, kontakt);
		if(provera != null) {
			System.out.println("Izmena uspesno izvrsena");
		}
		else {
			System.out.println("Izmena nije izvrsena");
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
