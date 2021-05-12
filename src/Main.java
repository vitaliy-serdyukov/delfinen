public class Main {
  public static void main(String[] args) {

    Medlem medlem = new Medlem();

    new Medlem(17, "Younes", "aktiv");
    new Medlem(37, "Vitaliy", "aktiv");

    Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();

    medlem.afgørAktivitetsform();
    konkurrencesvømmer.afgørÅrgang();
  }
}
