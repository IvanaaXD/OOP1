package meni;

public class Meni {

	//public static void main(String[] args) {
		
		/*String fileSeparator = System.getProperty("file.separator");
				
		String klijentPath = "src" + fileSeparator + "podaci" + fileSeparator + "klijenti.csv";
		String menadzerPath = "src" + fileSeparator + "podaci" + fileSeparator + "menadzer.csv";
		String recepcionarPath = "src" + fileSeparator + "podaci" + fileSeparator + "recepcionar.csv";
		String kozmeticarPath = "src" + fileSeparator + "podaci" + fileSeparator + "kozmeticar.csv";
		String tretmanPath = "src" + fileSeparator + "podaci" + fileSeparator + "tretman.csv";
		String tipTretmanaPath = "src" + fileSeparator + "podaci" + fileSeparator + "tiptretmana.csv";
		String zakazaniTretmanPath = "src" + fileSeparator + "podaci" + fileSeparator + "zakazanitretmana.csv";
		String kozmetickisalonPath = "src" + fileSeparator + "podaci" + fileSeparator + "kozmetickisalon.csv";*/
		
		/*appSettings app = new appSettings();
		
		app.setPathKlijent(klijentPath);
		app.setPathKozmeticar(kozmeticarPath);
		app.setPathMenadzer(menadzerPath);
		app.setPathRecepcionar(recepcionarPath);
		app.setPathKozmetickaUsluga(tretmanPath);
		app.setPathTipKozmetickeUsluge(tipTretmanaPath);
		app.setPathZakazanaKozmetickaUsluga(zakazaniTretmanPath);
		app.setPathKozmetickiSalon(kozmetickisalonPath);*/
		
		/*-----------Testiranje klijenta-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje klijenta-----------");

		app.addKlijent("Marija", "Maric", "mp", "lozinka", "zenski", "654685454", "Arilje");
		app.addKlijent("Kristina", "Kukuric", "kk", "lozinka", "zenski", "654135165", "Nis");
		app.addKlijent("Andrej", "Andric", "aa", "lozinka", "muski", "51658463", "Novi Sad");
		app.addKlijent("Marko", "Markovic", "mm", "lozinka", "muski", "654654", "Beograd");
		
		app.printKlijenti();
		System.out.println("------------------------------------------------------------------");
		
		app.changeKlijentName("mp", "Milica");
		app.changeKlijentPassword("kk", "password");
		app.changeKlijentUsername("aa", "aaa");
		
		app.addKlijentLoyaltyCard("mm");
		app.changeKlijentAmount("mm", 3500);
		
		app.deleteKlijent("kk");
		
		app.printKlijenti();   //print u konzoli se vrsi bez lozinke*/
		
		/*-----------Testiranje menadzera-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje menadzera-----------");
		
		app.addMenadzer("Petar", "Petrovic", "pp", "lozinka", "muski", "654685454", "Zrenjanin");
		app.addMenadzer("Milan", "Milanovic", "mmm", "lozinka", "muski", "61441634", "Kraljevo");
		app.addMenadzer("Lana", "Lakic", "ll", "lozinka", "zenski", "6146541", "Pancevo");
		app.addMenadzer("Andjela", "Andjelic", "aa", "lozinka", "zenksi", "864133654", "Subotica");
		
		app.printMenadzeri();
		System.out.println("------------------------------------------------------------------");
		
		app.changeMenadzerSurname("pp", "Perovic");
		app.changeMenadzerAdress("aa", "Kikinda");
		
		app.addMenadzerNivoRS("mmm", 5);
		app.addMenadzerPlata("pp", 20000);
		app.addMenadzerBonus("aa");
		
		app.deleteMenadzer("ll");
		
		app.printMenadzeri(); //print u konzoli se vrsi bez lozinke*/
		
		/*-----------Testiranje recepcionara-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje recepcionara-----------");
		
		app.addRecepcionar("Jana", "Janjic", "jj", "lozinka", "zenski", "654685454", "Kragujevac");
		app.addRecepcionar("Kristina", "Kitic", "kk", "lozinka", "zenski", "46841635", "Beograd");
		app.addRecepcionar("Zoran", "Zoric", "zz", "lozinka", "muski", "78564165", "Pozarevac");
		app.addRecepcionar("Petar", "Petrovic", "pp", "lozinka", "muski", "654546354", "Obrenovac");
		
		app.printRecepcionari();
		System.out.println("------------------------------------------------------------------");
		
		app.changeRecepcionarSurname("zz", "Zecevic");
		app.changeRecepcionarName("jj", "Jelena");
		
		app.addRecepcionarBonus("kk");
		app.addRecepcionarNivoRS("jj", 4);
		
		app.deleteRecepcionar("pp");
		
		app.printRecepcionari();*/
		
		/*-----------Testiranje kozmeticara-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje kozmeticara-----------");
		
		app.addKozmeticar("Helena", "Horvat", "hh", "lozinka", "zenski", "1654165", "Krusevac", "1,3");
		app.addKozmeticar("Kosta", "Kostic", "kk", "lozinka", "muski", "1651653", "Nis", "2,3");
		app.addKozmeticar("Goran", "Gordic", "gg", "lozinka", "muski", "6151564", "Uzice", "4,6");
		app.addKozmeticar("Sara", "Saric", "ss", "lozinka", "zenski", "16546556", "Cacak", "5");
		
		app.printKozmeticari();
		System.out.println("------------------------------------------------------------------");
		
		app.changeKozmeticarSurname("ss", "Savovic");
		app.changeKozmeticarPassword("gg", "password");
		
		app.addKozmeticarBonus("gg");
		app.addKozmeticarPlata("hh", 3);
		
		app.deleteKozmeticar("kk");
		
		app.printKozmeticari();*/
		
		/*-----------Testiranje kozmeticke usluge-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje kozmeticke usluge-----------");
		
		app.addKozmetickaUsluga("masaza", "1,3,5");
		app.addKozmetickaUsluga("nokti", "0,2");
		app.addKozmetickaUsluga("kosa", "4,6");
		
		app.printKozmetickeUsluge();
		System.out.println("------------------------------------------------------------------");
		
		app.changeKozmetickaUslugaNaziv("kosa", "sminka");
		app.changeKozmetickaUslugaListaTipova("nokti", "0,7");
		
		app.deleteKozmetickaUsluga("masaza");
		
		app.printKozmetickeUsluge();*/
		
		/*-----------Testiranje tipa kozmeticke usluge-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje tipa kozmeticke usluge-----------");
		
		app.addTipKozmetickeUsluge("french nokti", 1200);
		app.addTipKozmetickeUsluge("brazilska masaza", 3000);
		app.addTipKozmetickeUsluge("sisanje", 1000);
		
		app.printTipoviKozmetickeUsluge();
		System.out.println("------------------------------------------------------------------");
		
		app.changeTipKozmetickeUslugeTip("brazilska masaza", "havajska masaza");
		app.changeTipKozmetickeUslugeCijena("sisanje", 1500);
		
		app.deleteTipKozmetickeUsluge("french nokti");
		
		app.printTipoviKozmetickeUsluge();*/
		
		/*-----------Testiranje zakazane kozmeticke usluge-----------*/
		
		/*System.out.println("\n");
		System.out.println("-----------Testiranje zakazane kozmeticke usluge-----------");
		
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "22.05.2023.", "17:00", "brazilska masaza", 2000, 2, 1);
		app.addZakazanaKozmetickaUsluga("OTKAZAO_KLIJENT", "14.06.2023.", "18:00", "havajska masaza", 1700, 0, 3);
		app.addZakazanaKozmetickaUsluga("IZVRŠEN", "13.07.2023.", "14:00", "french nokti", 1200, 2, 3);
		
		app.printZakazaneKozmetickeUsluge();
		System.out.println("------------------------------------------------------------------");
		
		app.changeZakazanaKozmetickaUslugaDate(0, "23.05.2023.");
		app.changeZakazanaKozmetickaUslugaPrice(1, 1900);
		
		app.deleteZakazanaKozmetickaUsluga(2);
		
		app.printZakazaneKozmetickeUsluge();*/
	//}

}
