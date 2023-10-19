import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gym {

    private List<Member> allMembers = new ArrayList<>();

    private File checkInFile;

    //denna metod ger två saker: en lista med medlemmar och en plats där det "checkas in" när folk kommer till gymmet.
    public Gym(String membersFilePath, String checkInFilePath) throws IOException {
        loadAllMembers(membersFilePath);
        checkInFile = loadCheckInFile(checkInFilePath);
    }
     // Finns checkInFile då är det bra, annars skapas en checkInFile.
    private File loadCheckInFile(String checkInFilePath) throws IOException {
        File file = new File(checkInFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    // laddar filen med alla medlemmar och delar upp texten i namn, personummer och senaste betalning.
    private void loadAllMembers(String pathname) throws FileNotFoundException {
        var file = new File(pathname);
        var fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            var firstRow = fileReader.nextLine();
            var splitFirstRow = firstRow.split(", ");
            var personummer = splitFirstRow[0];
            var namn = splitFirstRow[1];
            if (fileReader.hasNextLine()) {
                var secondRow = fileReader.nextLine();
                LocalDate sistaBetalningsDatum = LocalDate.parse(secondRow);
                Member member = new Member(namn, personummer, sistaBetalningsDatum);
                allMembers.add(member);
            }
        }
    }

    public List<Member> getAllMembers() {
        return allMembers;


    }
        // checka in medlemmar när du söker på de om de är aktiva eller ej.
    public void checkIn(Member member) throws IOException {
        if (member.isActive()) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);
            String log = formattedNow + " - " + member.getNamn() + " (" + member.getPersonnummer() + ")\n";
            FileWriter fileWriter = new FileWriter(checkInFile, true);
            fileWriter.write(log);
            fileWriter.close();
        } else {
            System.out.println("Medlemmen är inte aktiv och kan inte checka in.");
        }
    }
            // Sök metoden för att hitta medlemmar om de är aktiva eller icke men också om de inte finns.
    public Member findMember(String nameOrId) throws MemberNotFoundException {
        return allMembers.stream()
                .filter(member -> member.getNamn().equalsIgnoreCase(nameOrId.trim()) ||
                        member.getPersonnummer().equals(nameOrId.trim()))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException(nameOrId));
    }


}