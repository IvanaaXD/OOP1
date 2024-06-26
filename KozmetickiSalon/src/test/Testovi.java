package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import korisnici.Klijent;
import korisnici.Kozmeticar;
import korisnici.Menadzer;
import korisnici.Recepcionar;
import meni.appSettings;
import osobineTretmana.KozmetickaUsluga;
import osobineTretmana.KozmetickiSalon;
import osobineTretmana.ZakazanaKozmetickaUsluga;

public class Testovi {

	public appSettings app;
	
	@Before
	public void setUp() {
		this.app = new appSettings();
		//app = new appSettings();
		String fileSeparator = System.getProperty("file.separator");
		
		String klijentPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "klijenti.csv";
		String menadzerPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "menadzer.csv";
		String recepcionarPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "recepcionar.csv";
		String kozmeticarPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "kozmeticar.csv";
		String tretmanPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "tretman.csv";
		String tipTretmanaPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "tiptretmana.csv";
		String zakazaniTretmanPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "zakazanitretmana.csv";
		String kozmetickisalonPath = "src" + fileSeparator + "testpodaci" + fileSeparator + "kozmetickisalon.csv";

		File klijentFile = new File(klijentPath);
        File menadzerFile = new File(menadzerPath);
        File recepcionarFile = new File(recepcionarPath);
        File kozmeticarFile = new File(kozmeticarPath);
        File tretmanFile = new File(tretmanPath);
        File tipTretmanaFile = new File(tipTretmanaPath);
        File zakazaniTretmanFile = new File(zakazaniTretmanPath);
        File kozmetickisalonFile = new File(kozmetickisalonPath);

        klijentFile.delete();
        menadzerFile.delete();
        recepcionarFile.delete();
        kozmeticarFile.delete();
        tretmanFile.delete();
        tipTretmanaFile.delete();
        zakazaniTretmanFile.delete();
        kozmetickisalonFile.delete();
		
		app.setPathKlijent(klijentPath);
		app.setPathKozmeticar(kozmeticarPath);
		app.setPathMenadzer(menadzerPath);
		app.setPathRecepcionar(recepcionarPath);
		app.setPathKozmetickaUsluga(tretmanPath);
		app.setPathTipKozmetickeUsluge(tipTretmanaPath);
		app.setPathZakazanaKozmetickaUsluga(zakazaniTretmanPath);
		app.setPathKozmetickiSalon(kozmetickisalonPath);
		
		app.addKozmetickiSalon("Moj salon", "10:00", "20:00");
		
		app.addMenadzer( "Nikola", "Nikolic", "nikolan", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
		app.addRecepcionar("Pera", "Peric", "perap", "lozinka", "muski", "65942318", "Bulevar cara Lazara 25", "STRUCNA", 4.00);
		app.addKozmeticar("Sima", "Simic", "simas", "lozinka", "muski", "4641871", "Dr Sime Milosevica 25", "AKADEMSKA", 5.00, "");
		app.addKozmeticar("Jovana", "Jovanovic", "jovanaj", "lozinka", "zenski", "8416544", "Brace Ribnikar 52", "STRUCNA", 4.00, "");
		app.addKlijent("Milica", "Milic", "milicam", "lozinka", "zenski", "546544", "Zmaj Jovina 38", 0.00, false);
		app.addKlijent("Mika", "Mikic", "mikam", "lozinka", "muski", "44338756", "Maksima Gorkog 47", 0.00, false);

		app.addKozmetickaUsluga("masaza", "");
		app.addKozmetickaUsluga("manikir", "");
		app.addKozmetickaUsluga("pedikir", "");
		
		app.addTipKozmetickeUsluge("relaks masaza", "45 minuta", "masaza", 2000);
		app.addTipKozmetickeUsluge("sportska masaza", "75 minuta", "masaza", 2500);
		app.addTipKozmetickeUsluge("francuski manikir", "50 minuta", "manikir", 1500);
		app.addTipKozmetickeUsluge("gel lak", "80 minuta", "manikir", 1600);
		app.addTipKozmetickeUsluge("spa manikir", "90 minuta", "manikir", 2000);
		app.addTipKozmetickeUsluge("spa pedikir", "45 minuta", "pedikir", 1600);
		
		app.addTipKozmetickeUslugeUKozmetickuUslugu("relaks masaza", "masaza");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("sportska masaza", "masaza");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("francuski manikir", "manikir");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("gel lak", "manikir");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa manikir", "manikir");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("spa pedikir", "pedikir");
		
		app.addKozmeticarListaUsluga("simas", "masaza,manikir");
		app.addKozmeticarListaUsluga("jovanaj", "manikir,pedikir");
		
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "10.06.2023", "18:00", "francuski manikir", "milicam", "jovanaj");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.06.2023", "09:00", "spa pedikir", "milicam", "jovanaj");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "12.06.2023", "08:00", "sportska masaza", "mikam", "simas");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "13.06.2023", "19:00", "relaks masaza", "mikam", "simas");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "10.06.2023", "18:00", "francuski manikir", "mikam", "simas");
		
	}
	
	@Test
	public void changeUsernameKlijent() {
		app.changeKlijentUsername("milicam", "milica");
		assertEquals("milica", app.getKfm().PronadjiKlijentaPoKorisnickomImenu("milica").getKorisnickoIme());
	}
	
	@Test
	public void changeImeKlijent() {
		app.changeKlijentName("mikam", "Maja");
		assertEquals("Maja", app.getKfm().PronadjiKlijentaPoKorisnickomImenu("mikam").getIme());
	}
	
	@Test
	public void changeTelefonKozmeticar() {
		app.changeKozmeticarPhone("simas", "0654985");
		assertEquals("0654985", app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu("simas").getTelefon());
	}
	
	@Test
	public void changePrezimeRecepcionar() {
		app.changeRecepcionarSurname("perap", "Perovic");
		assertEquals("Perovic", app.getrfm().PronadjiRecepcionaraPoKorisnickomImenu("perap").getPrezime());
	}
	
	@Test 
	public void changeAdresaMenadzer() {
		app.changeMenadzerAdress("nikolan", "Cara Dusana 6");
		assertEquals("Cara Dusana 6", app.getmfm().PronadjiMenadzeraPoKorisnickomImenu("nikolan").getAdresa());
	}

	@Test
	public void addLoyaltyCard() {
		app.setLoyaltyCardValue(3500);
		ArrayList<Klijent> klijenti = new ArrayList<Klijent>(app.getKfm().getKlijenti().values());
		
		int i = 0;
		for (Klijent k: klijenti) {
			if (k.getKartica()) {
				i += 1;
			}
		}
		
		assertEquals(i, 1);
	}
	
	@Test
	public void testSaveMenadzer() {
		app.addMenadzer( "Nikolina", "Nikolic", "nikolinan", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
		app.getmfm().loadData();
		assertEquals(app.getmfm().getMenadzeri().size(),2);
	}
	
	@Test
	public void testSaveRecepcioner() {
		app.addRecepcionar("Sara", "Saric", "saras", "lozinka", "5", "6", "Strazilovska 16", "STRUCNA", 0);
		app.getrfm().loadData();
		assertEquals(app.getrfm().getRecepcionar().size(), 2);
	}
	
	@Test
	public void testSaveKlijent() {
		app.addKlijent("Katarina", "Kitic", "katarinak", "lozinka", "5", "6", "Strazilovska 16", 0, false);
		app.getKfm().loadData();
		assertEquals(app.getKfm().getKlijenti().size(), 3);
	}
	
	@Test
	public void testSaveKozmeticari() {
		app.addKozmeticar("Violeta", "Vitic", "violetav", "lozinka", "5", "6", "Strazilovska 16", "AKADEMSKA", 0, "");
		app.getkkfm().loadData();
		assertEquals(app.getkkfm().getKozmeticar().size(), 3);
	}
	
	@Test
	public void testSave() {
		app.addKlijent("Katarina", "Kitic", "katarinak1", "lozinka", "5", "6", "Strazilovska 16", 0, false);
		app.addKlijent("Katarina", "Kitic", "katarinak2", "lozinka", "5", "6", "Strazilovska 16", 0, false);
		app.addKlijent("Katarina", "Kitic", "katarinak3", "lozinka", "5", "6", "Strazilovska 16", 0, false);
		app.addKlijent("Katarina", "Kitic", "katarinak4", "lozinka", "5", "6", "Strazilovska 16", 0, false);
		app.addKlijent("Katarina", "Kitic", "katarinak5", "lozinka", "5", "6", "Strazilovska 16", 0, false);

		app.getKfm().loadData();
		assertEquals(app.getKfm().getKlijenti().size(), 7);

		app.addKozmeticar("Violeta", "Vitic", "violetav1", "lozinka", "5", "6", "Strazilovska 16", "AKADEMSKA", 0, "");
		app.addKozmeticar("Violeta", "Vitic", "violetav2", "lozinka", "5", "6", "Strazilovska 16", "AKADEMSKA", 0, "");
		app.addKozmeticar("Violeta", "Vitic", "violetav3", "lozinka", "5", "6", "Strazilovska 16", "AKADEMSKA", 0, "");

		app.getkkfm().loadData();
		assertEquals(app.getkkfm().getKozmeticar().size(), 5);
		
		app.addRecepcionar("Sara", "Saric", "saras1", "lozinka", "5", "6", "Strazilovska 16", "STRUCNA", 0);
		app.addRecepcionar("Sara", "Saric", "saras2", "lozinka", "5", "6", "Strazilovska 16", "STRUCNA", 0);
		app.addRecepcionar("Sara", "Saric", "saras3", "lozinka", "5", "6", "Strazilovska 16", "STRUCNA", 0);
		app.addRecepcionar("Sara", "Saric", "saras4", "lozinka", "5", "6", "Strazilovska 16", "STRUCNA", 0);

		app.getrfm().loadData();
		assertEquals(app.getrfm().getRecepcionar().size(), 5);
		
		app.addMenadzer( "Nikolina", "Nikolic", "nikolinan1", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
		app.addMenadzer( "Nikolina", "Nikolic", "nikolinan2", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
		app.addMenadzer( "Nikolina", "Nikolic", "nikolinan3", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
		app.addMenadzer( "Nikolina", "Nikolic", "nikolinan4", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);

		app.getmfm().loadData();
		assertEquals(app.getmfm().getMenadzeri().size(), 5);
		
		app.addKozmetickaUsluga("epilacija", "");
		app.addKozmetickaUsluga("kosa", "");
		
		app.addKozmeticarListaUsluga("simas", "epilacija");
		app.addKozmeticarListaUsluga("jovanaj", "kosa");

		app.getkufm().loadData();
		assertEquals(app.getkufm().getKozmetickeUsluge().size(), 5);

		app.addTipKozmetickeUsluge("epilacija nogu", "85 minuta", "epilacija", 2500);
		app.addTipKozmetickeUsluge("sisanje", "30 minuta", "kosa", 1200);
		app.addTipKozmetickeUsluge("epilacija ruku", "75 minuta", "epilacija", 2000);
		app.addTipKozmetickeUsluge("farbanje", "150 minuta", "kosa", 5000);

		app.addTipKozmetickeUslugeUKozmetickuUslugu("epilacija nogu", "epilacija");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("epilacija ruku", "epilacija");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("sisanje", "kosa");
		app.addTipKozmetickeUslugeUKozmetickuUslugu("farbanje", "kosa");

		app.gettkufm().loadData();
		assertEquals(app.gettkufm().getTipKozmetickeUsluge().size(), 10);

		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.07.2023", "09:00", "sisanje", "mikam", "jovanaj");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.08.2023", "10:00", "epilacija ruku", "mikam", "simas");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.09.2023", "11:00", "farbanje", "milicam", "jovanaj");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.10.2023", "10:00", "epilacija nogu", "milicam", "simas");

		app.getzkufm().loadData();
		assertEquals(app.getzkufm().getZakazaneKozmetickeUsluge().size(), 9);
	}
	
	@Test
	public void testRemoveUsers() {
		Klijent k =app.getKfm().PronadjiKlijentaPoKorisnickomImenu("milicam");
		k.setObrisan(true);
		app.getKfm().saveData();
		app.getKfm().loadData();
		assertEquals(app.getKfm().PronadjiKlijentaPoKorisnickomImenu("milicam").getObrisan(), true);

		app.addKozmeticar("Violeta", "Vitic", "violetav2", "lozinka", "5", "6", "Strazilovska 16", "AKADEMSKA", 0, "");
		Kozmeticar kk =app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu("violetav2");
		kk.setObrisan(true);
		app.getkkfm().saveData();
		app.getkkfm().loadData();
		assertEquals(app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu("violetav2").getObrisan(), true);
		
		app.addMenadzer( "Nikolina", "Nikolic", "nikolinan2", "lozinka", "muski", "8957496", "Hajduk Veljkova 13", "AKADEMSKA", 5.00);
		Menadzer m =app.getmfm().PronadjiMenadzeraPoKorisnickomImenu("nikolinan2");
		m.setObrisan(true);
		app.getmfm().saveData();
		app.getmfm().loadData();
		assertEquals(app.getmfm().PronadjiMenadzeraPoKorisnickomImenu("nikolinan2").getObrisan(), true);
		
		app.addRecepcionar("Sara", "Saric", "saras3", "lozinka", "5", "6", "Strazilovska 16", "STRUCNA", 0);
		Recepcionar r =app.getrfm().PronadjiRecepcionaraPoKorisnickomImenu("saras3");
		r.setObrisan(true);
		app.getrfm().saveData();
		app.getrfm().loadData();
		assertEquals(app.getrfm().PronadjiRecepcionaraPoKorisnickomImenu("saras3").getObrisan(), true);
	}
	
	@Test
	public void testWorkingHours() {
		KozmetickiSalon ks = app.getksfm().PronadjiKozmetickiSalonPoId(0);
        LocalTime desiredTime = LocalTime.of(11, 0);
		ks.setPocetakRadnogVremena(desiredTime);
		app.getksfm().saveData();
		app.getksfm().loadData();
		assertEquals(app.getksfm().PronadjiKozmetickiSalonPoId(0).getPocetakRadnogVremena(), desiredTime);
	}
	
	@Test
	public void testBrojIzvrsenihTretmana() {

		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.08.2023", "10:00", "francuski manikir", "mikam", "simas");
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "12.08.2023", "10:00", "gel lak", "milicam", "simas");
		app.getzkufm().saveData();

		ZakazanaKozmetickaUsluga zku1 = app.getzkufm().PronadjiZakazanuKozmetickuUsluguPoId(app.getzkufm().getZakazaneKozmetickeUsluge().size()-2);
		ZakazanaKozmetickaUsluga zku2 = app.getzkufm().PronadjiZakazanuKozmetickuUsluguPoId(app.getzkufm().getZakazaneKozmetickeUsluge().size()-1);

		app.getzkufm().loadData();
        app.changeZakazanaKozmetickaUslugaStatus(zku1.getId(), "IZVRŠEN");
        app.changeZakazanaKozmetickaUslugaStatus(zku2.getId(), "IZVRŠEN");

		assertEquals(app.izvjestajJedanTest().size(), 2);   
	}
	
	@Test
	public void testSetLojalti() {
		app.setLoyaltyCardValue(3500);
		
		assertEquals(app.getKfm().PronadjiKlijentaPoKorisnickomImenu("milicam").getKartica(), false);   
		assertEquals(app.getKfm().PronadjiKlijentaPoKorisnickomImenu("mikam").getKartica(), true);   
	}
	
	@Test
	public void testListaKozmeticar() {
		app.addKozmetickaUsluga("epilacija", "");
		app.addKozmetickaUsluga("kosa", "");
		app.addKozmeticarListaUsluga("simas", "epilacija");
		app.addKozmeticarListaUsluga("jovanaj", "kosa");
		app.getkufm().saveData();
		app.getkkfm().saveData();
		
		app.getkufm().loadData();
		app.getkkfm().loadData();
		
		KozmetickaUsluga ku = app.getkufm().PronadjiKozmetickuUsluguPoNazivu("epilacija");
		int id = app.getkkfm().PronadjiKozmeticaraPoKorisnickomImenu("simas").getTretmani().get(0);
		assertEquals(id, ku.getId());   
	}
	
	
	@Test
	public void changeZakazanaUslugaTime() {
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.08.2023", "10:00", "francuski manikir", "mikam", "simas");
		ZakazanaKozmetickaUsluga zku1 = app.getzkufm().PronadjiZakazanuKozmetickuUsluguPoId(app.getzkufm().getZakazaneKozmetickeUsluge().size()-1);
		app.getzkufm().loadData();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		String timeString = "14:00";
        LocalTime localTime = LocalTime.parse(timeString, formatter);

		zku1.setVrijeme(localTime);
		app.getzkufm().loadData();
		
		assertEquals(zku1.getVrijeme(), localTime);   
	}
	
	@Test
	public void changeZakazanaUslugaDate() {
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.08.2023", "10:00", "francuski manikir", "mikam", "simas");
		ZakazanaKozmetickaUsluga zku1 = app.getzkufm().PronadjiZakazanuKozmetickuUsluguPoId(app.getzkufm().getZakazaneKozmetickeUsluge().size()-1);
		app.getzkufm().loadData();

		String dateString = "21.08.2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        
        // Parse the date string to create a LocalDate object
        LocalDate localDate = LocalDate.parse(dateString, formatter);

		zku1.setDatum(localDate);
		app.getzkufm().loadData();
		
		assertEquals(zku1.getDatum(), localDate);   
	}
	
	@Test
	public void changeZakazanaUslugaUsluga() {
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.08.2023", "10:00", "francuski manikir", "mikam", "simas");
		ZakazanaKozmetickaUsluga zku1 = app.getzkufm().PronadjiZakazanuKozmetickuUsluguPoId(app.getzkufm().getZakazaneKozmetickeUsluge().size()-1);
		app.getzkufm().loadData();

		String tekst = "gel lak";

		zku1.setTipUsluge("gel lak");
		app.getzkufm().loadData();
		
		assertEquals(zku1.getTipUsluge(), tekst);   
	}
	
	@Test
	public void changeZakazanaUslugaUser() {
		app.addZakazanaKozmetickaUsluga("ZAKAZAN", "11.08.2023", "10:00", "francuski manikir", "mikam", "simas");
		ZakazanaKozmetickaUsluga zku1 = app.getzkufm().PronadjiZakazanuKozmetickuUsluguPoId(app.getzkufm().getZakazaneKozmetickeUsluge().size()-1);
		app.getzkufm().loadData();

		String tekst = "milicam";
		Klijent k = app.getKfm().PronadjiKlijentaPoKorisnickomImenu(tekst);
		
		zku1.setIdKlijent(k.getId());
		app.getzkufm().loadData();
		
		assertEquals(zku1.getIdKlijent(), k.getId());   
	}

}
