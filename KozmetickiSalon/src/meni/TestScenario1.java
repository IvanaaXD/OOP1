package meni;

public class TestScenario1 {

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
//		app.addKozmetickiSalon("Moj salon", "10:00", "20:00");
//		
//		System.out.println("\n");
//		app.printKozmetickiSalon();
//		System.out.println("\n");
//		
//		app.addMenadzer( "Nikola", "Nikolic", "nikolan", "lozinka", "muski", "8957496", "Hajduk Veljkova 13");
//		app.addRecepcionar("Pera", "Peric", "perap", "lozinka", "muski", "65942318", "Bulevar cara Lazara 25");
//		app.addKozmeticar("Sima", "Simic", "simas", "lozinka", "muski", "4641871", "Dr Sime Milosevica 25");
//		app.addKozmeticar("Zika", "Zikic", "zikaz", "lozinka", "muski", "1564655", "Straziloska 28");
//		app.addKozmeticar("Jadranka", "Jovanovic", "jadrankaj", "lozinka", "zenski", "8416544", "Brace Ribnikar 52");
//		app.addKlijent("Milica", "Milic", "milicam", "lozinka", "zenski", "546544", "Zmaj Jovina 38");
//		app.addKlijent("Mika", "Mikic", "mikam", "lozinka", "muski", "44338756", "Maksima Gorkog 47");
//		
//		app.changeKozmeticarName("jadrankaj", "Jovana");
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
//		app.deleteKozmeticar("zikaz", true);
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
//		//app.printKozmetickeUsluge();
//		
//		app.addTipKozmetickeUsluge("relaks masaza", "45 minuta", "masaza", 2000);
//		app.addTipKozmetickeUsluge("sportska masaza", "75 minuta", "masaza", 2500);
//		app.addTipKozmetickeUsluge("francuski manikir", "50 minuta", "manikir", 1500);
//		app.addTipKozmetickeUsluge("gel lak", "80 minuta", "manikir", 1600);
//		app.addTipKozmetickeUsluge("spa manikir", "90 minuta", "manikir", 2000);
//		app.addTipKozmetickeUsluge("spa pedikir", "45 minuta", "masaza", 1600);
//		
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("relaks masaza", "masaza");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("sportska masaza", "masaza");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("francuski manikir", "manikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("gel lak", "manikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa manikir", "manikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa pedikir", "masaza");
//
//		//app.printKozmetickeUsluge();
//
//		System.out.println("-----------------------Tipovi kozmetickih usluga-----------------------");
//		app.printTipoviKozmetickeUsluge();
//		System.out.println("\n");
//		
//		app.changeTipKozmetickeUslugeTrajanje("francuski manikir", "55 minuta");
//		
//		app.changeTipKozmetickeUslugeNaziv("spa pedikir", "pedikir");
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa pedikir", "pedikir");
//		app.deleteTipKozmetickeUslugeIzKozmetickeUsluge("spa pedikir", "masaza");
//		
//		//app.printKozmetickeUsluge();
//		
//		System.out.println("-----------------------Tipovi kozmetickih usluga-----------------------");
//		app.printTipoviKozmetickeUsluge();
//		System.out.println("\n");
//		
//		app.deleteKozmetickaUsluga("pedikir", true);
//		app.deleteTipKozmetickeUsluge2("pedikir");
//		
//		//app.printKozmetickeUsluge();
//		
//		System.out.println("-----------------------Tipovi kozmetickih usluga-----------------------");
//		app.printTipoviKozmetickeUsluge();
//		System.out.println("\n");
//		
//		/*app.addKozmetickaUsluga("pedikir", ""); //DODATO OPET KAKO BIH MOGLA RADITI S TIM KAKO JE TRAZENO U TEST SCENARIJU
//		app.addTipKozmetickeUsluge("spa pedikir", "45 minuta", "pedikir", 1600);		
//		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa pedikir", "pedikir");*/
//		
//		/*System.out.println("-----------------------Tipovi kozmetickih usluga-----------------------");
//		app.printTipoviKozmetickeUsluge();
//		System.out.println("\n");
//		
//		System.out.println("-----------------------Kozmeticke usluge-----------------------");
//		app.printKozmetickeUsluge();
//		System.out.println("\n");*/
//		
//		app.addKozmeticarListaUsluga("simas", "masaza,manikir");
//		app.addKozmeticarListaUsluga("jadrankaj", "manikir");
//
//		/*System.out.println("-----------------------Kozmeticari-----------------------");
//		app.printKozmeticari();
//		System.out.println("\n");*/
//		
//		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "23.05.2023.", "17:00", "relaks masaza", "milicam", "simas");
//		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "28.05.2023.", "13:00", "gel lak", "mikam", "simas");
//		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "20.05.2023.", "11:00", "spa manikir", "mikam", "jadrankaj");
//		
//		/*app.deleteKozmetickaUsluga("pedikir");
//		app.deleteTipKozmetickeUsluge2("pedikir");*/
//
//		System.out.println("-----------------------Zakazane kozmeticke usluge-----------------------");
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("\n");
//		
//		app.changeZakazanaKozmetickaUslugaTypeOfServiceAll(1, "francuski manikir");
//		
//		System.out.println("----------PRIKAZANI ZAJEDNO ZAKAZANI TRETMANI I NJIHOVE CIJENE----------");
//		System.out.print("\n");
//		System.out.println("-----------------------Zakazane kozmeticke usluge-----------------------");
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("\n");
//		
//		app.changeTipKozmetickeUslugeCijena("relaks masaza", 1700);
//		
//		//app.printTipoviKozmetickeUsluge();
//		
//		System.out.println("-----------------------Zakazane kozmeticke usluge-----------------------");
//		app.printZakazaneKozmetickeUsluge();
//		System.out.println("\n");
//	}
	


}
