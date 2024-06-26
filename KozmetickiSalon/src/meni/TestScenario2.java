package meni;

public class TestScenario2 {

//	public static void main(String[] args) {
//
//		String fileSeparator = System.getProperty("file.separator");
//		
//		String klijentPath = "src" + fileSeparator + "podaci" + fileSeparator + "klijenti.csv";
//		String menadzerPath = "src" + fileSeparator + "podaci" + fileSeparator + "menadzer.csv";
//		String recepcionarPath = "src" + fileSeparator + "podaci" + fileSeparator + "recepcionar.csv";
//		String kozmeticarPath = "src" + fileSeparator + "podaci" + fileSeparator + "kozmeticar.csv";
//		String tretmanPath = "src" + fileSeparator + "podaci" + fileSeparator + "tretman.csv";
//		String tipTretmanaPath = "src" + fileSeparator + "podaci" + fileSeparator + "tiptretmana.csv";
//		String zakazaniTretmanPath = "src" + fileSeparator + "podaci" + fileSeparator + "zakazanitretmana.csv";
//		String kozmetickisalonPath = "src" + fileSeparator + "podaci" + fileSeparator + "kozmetickisalon.csv";
//		
//		appSettings app = new appSettings();
//		
//		app.setPathKlijent(klijentPath);
//		app.setPathKozmeticar(kozmeticarPath);
//		app.setPathMenadzer(menadzerPath);
//		app.setPathRecepcionar(recepcionarPath);
//		app.setPathKozmetickaUsluga(tretmanPath);
//		app.setPathTipKozmetickeUsluge(tipTretmanaPath);
//		app.setPathZakazanaKozmetickaUsluga(zakazaniTretmanPath);
//		app.setPathKozmetickiSalon(kozmetickisalonPath);
//		
//		System.out.println("");
//		System.out.println("======================================================================================================================================================");
//		System.out.println("UKOLIKO SE PROJEKAT POKRECE VISE PUTA POTREBNO JE IZBRISATI FAJLOVE SA PODACIMA IZ KOZMETICKISALON/SRC/PODACI, DA SE PODACI U FAJLOVIMA NE BI DUPLALI.");
//		System.out.println("======================================================================================================================================================");
//
//		app.addKozmetickiSalon("Moj salon", "10:00", "20:00");
//		
//		System.out.println("\n");
//		app.printKozmetickiSalon();
//		System.out.println("\n");
//				
//		app.addMenadzer( "Nikola", "Nikolic", "nikolan", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
//		app.addRecepcionar("Pera", "Peric", "perap", "lozinka", "muski", "65942318", "Bulevar cara Lazara 25", "STRUCNA", 4.00);
//		app.addKozmeticar("Sima", "Simic", "simas", "lozinka", "muski", "4641871", "Dr Sime Milosevica 25", "AKADEMSKA", 5.00, "");
//		app.addKozmeticar("Zika", "Zikic", "zikaz", "lozinka", "muski", "1564655", "Straziloska 28", "STRUKOVNA", 3.00, "");
//		app.addKozmeticar("Jovana", "Jovanovic", "jovanaj", "lozinka", "zenski", "8416544", "Brace Ribnikar 52", "STRUCNA", 4.00, "");
//		app.addKlijent("Milica", "Milic", "milicam", "lozinka", "zenski", "546544", "Zmaj Jovina 38", 0.00, false);
//		app.addKlijent("Mika", "Mikic", "mikam", "lozinka", "muski", "44338756", "Maksima Gorkog 47", 0.00, false);
//				
//		System.out.println("-----------------------Menadzeri-----------------------");
//		app.printMenadzeri();
//		System.out.println("\n");
//		
//		System.out.println("-----------------------Recepcionari-----------------------");
//		app.printRecepcionari();
//		System.out.println("\n");
//		
//		System.out.println("-----------------------Kozmeticari-----------------------");
//		app.printKozmeticari();
//		System.out.println("\n");
//		
//		System.out.println("-----------------------Klijenti-----------------------");
//		app.printKlijenti();
//		System.out.println("\n");
//		
//		app.addKozmetickaUsluga("masaza", "");
//		app.addKozmetickaUsluga("manikir", "");
//		app.addKozmetickaUsluga("pedikir", "");
//		
//		app.addTipKozmetickeUsluge("relaks masaza", "45 minuta", "masaza", 2000);
//		app.addTipKozmetickeUsluge("sportska masaza", "75 minuta", "masaza", 2500);
//		app.addTipKozmetickeUsluge("francuski manikir", "50 minuta", "manikir", 1500);
//		app.addTipKozmetickeUsluge("gel lak", "80 minuta", "manikir", 1600);
//		app.addTipKozmetickeUsluge("spa manikir", "90 minuta", "manikir", 2000);
//		app.addTipKozmetickeUsluge("spa pedikir", "45 minuta", "pedikir", 1600);
//		
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("relaks masaza", "masaza");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("sportska masaza", "masaza");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("francuski manikir", "manikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("gel lak", "manikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa manikir", "manikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa pedikir", "pedikir");
//
//		System.out.println("-----------------------Tipovi kozmetickih usluga-----------------------");
//		app.printTipoviKozmetickeUsluge();
//		System.out.println("\n");
//		
//		app.addKozmeticarListaUsluga("simas", "masaza,manikir");
//		app.addKozmeticarListaUsluga("zikaz", "masaza,manikir,pedikir");
//		app.addKozmeticarListaUsluga("jovanaj", "manikir");
//		
//		System.out.println("Kako nemam gui, ovo je jedini nacin za zakazivanje tretmana, sto cu kasnije rijesiti kroz gui. Pozivace se ista funkcija koja ce zahtijevati korisnicko ime kozmeticara i klijenta, nezavisno od toga da li klijent zakazuje online ili recepcionar zakazuje za njega u salonu.\n");
//		System.out.println("-----------------------Zakazane kozmeticke usluge-----------------------");
//		System.out.println("\n");
//		
//		app.addZakazanaKozmetickaUsluga1("ZAKAZAN", "10.06.2023", "18:00", "francuski manikir", "milicam", 3);
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("");
//
//		app.addZakazanaKozmetickaUsluga1("ZAKAZAN", "11.06.2023", "09:00", "spa pedikir", "milicam", 2);
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("");
//
//		app.addZakazanaKozmetickaUsluga1("ZAKAZAN", "12.06.2023", "08:00", "sportska masaza", "mikam", 1);
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("");
//
//		app.addZakazanaKozmetickaUsluga1("ZAKAZAN", "13.06.2023", "19:00", "relaks masaza", "mikam", 2);
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("");
//
//		app.addZakazanaKozmetickaUsluga1("ZAKAZAN", "10.06.2023", "18:00", "francuski manikir", "mikam", 3);
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("");
//		
//		System.out.println("\n");
//		System.out.println("-----------------------Zakazane kozmeticke usluge drugog kozmeticara/njegov raspored-----------------------");
//		System.out.println("\n");
//		app.printZakazanaKozmetickaUslugaZaKozmeticara(2);
//		
//		System.out.println("-----------------------Klijenti prije vrijednosti kartice-----------------------");
//		app.printKlijenti();
//		System.out.println("\n");
//		
//		app.setLoyaltyCardValue(3500);
//		
//		System.out.println("-----------------------Klijenti poslije vrijednosti kartice-----------------------");
//		app.printKlijenti();
//		System.out.println("\n");	
//		
//		System.out.print("\n");
//		System.out.println("-----------------------Mijenjanje stanja zakazane kozmeticke usluge-----------------------");
//		System.out.print("\n");
//		
//		app.changeZakazanaKozmetickaUslugaStatus(0, "IZVRŠEN");
//		app.printKlijenti();
//		app.printZakazaneKozmetickeUsluge();
//		app.setKozmetickiSalonBilans();
//		System.out.println("\n");
//		
//		app.changeZakazanaKozmetickaUslugaStatus(1, "OTKAZAO_KLIJENT");
//		app.printKlijenti();
//		app.printZakazaneKozmetickeUsluge();
//		app.setKozmetickiSalonBilans();
//		System.out.println("\n");
//
//		app.changeZakazanaKozmetickaUslugaStatus(2, "OTKAZAO_SALON");
//		app.printKlijenti();
//		app.printZakazaneKozmetickeUsluge();
//		app.setKozmetickiSalonBilans();
//		System.out.println("\n");
//
//		app.changeZakazanaKozmetickaUslugaStatus(3, "NIJE_SE_POJAVIO");
//		app.printKlijenti();
//		app.printZakazaneKozmetickeUsluge();
//		app.setKozmetickiSalonBilans();
//		System.out.println("\n");
//		
//		System.out.println("\n");
//		
//		app.addZakazanaKozmetickaUsluga1("ZAKAZAN", "14.06.2023", "09:00", "gel lak", "milicam", 1);
//		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "14.06.2023", "09:00", "spa manikir", "mikam", "");
//		
//		System.out.println("\n");
//		app.changeZakazanaKozmetickaUslugaStatus(5, "IZVRŠEN");
//		app.printZakazaneKozmetickeUsluge();
//		app.setKozmetickiSalonBilans();
//		
//		System.out.print("\n");
//		System.out.println("-----------------------Za Mika Mikića svi prethodni kozmetički tretmani i svi neophodni detalji-----------------------");
//		System.out.print("\n");
//		app.printZakazanaKozmetickaUslugaZaKlijenta("mikam");
//
//		app.setBonus(3000, 2);
//		
//		System.out.print("\n");
//		System.out.println("-----------------------Prihodi i rashodi, prihodi jednaki bilansu trenutno-----------------------");
//		System.out.print("\n");
//		app.setKozmetickiSalonBilans();
//		app.getRashodi();
//		
//		System.out.print("\n");
//		System.out.println("-----------------------Postavljen bonus kozmeticarima na osnovu broja zakazanih tretmana-----------------------");
//		System.out.print("\n");
//		app.printKozmeticari();
//		
//	}	
	
}
