import java.time.LocalDate;

public class Member {

    // Här deklareras tre privata instansvariabler. Dessa variabler används för att lagra info om medlemskap.
    private String namn;
    private String Personnummer;
    private LocalDate sistaBetalningsDatum;

    public Member(String namn, String Personnummer, LocalDate sistaBetalningsDatum) {
        this.namn = namn;
        this.Personnummer = Personnummer;
        this.sistaBetalningsDatum = sistaBetalningsDatum;
    }

    public String getNamn() {
        return namn;
    }


    // Detta är en metod som kontrollerar om medlemskapet är aktivt.
    public boolean isActive() {
        var dateOneYearAgo = LocalDate.now().minusYears(1);
        return sistaBetalningsDatum.isAfter(dateOneYearAgo);
    }

    public String getPersonnummer() {
        return Personnummer;
    }


    //Denna metod returnerar information om medlemskapet som en sträng. Den inkluderar medlemmens namn,
    // personnummer och om medlemskapet är aktivt eller inte (baserat på isActive-metoden)
    public String getInfo() {
        String isMemberActive = isActive() ? "ja" : "nej";
        return "Namn: " + namn + "\n"
                + "Personnr: " + Personnummer + "\n"
                + "Aktivt medlemskap: " + isMemberActive;
    }

    public LocalDate getSistaBetalningsDatum() {
        return sistaBetalningsDatum;
    }
}

