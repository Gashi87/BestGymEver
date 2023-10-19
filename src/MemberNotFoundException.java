public class MemberNotFoundException extends Exception {
    public MemberNotFoundException(String nameOrId) {
        super("Ingen medlem hittades med namn eller personnummer: " + nameOrId);
    }
}