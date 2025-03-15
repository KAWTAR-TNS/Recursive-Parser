package RecursiveParser;
import java.util.*;

public class simpleFrParser {
    // Vocabulaire minimaliste
    private static final Set<String> ARTICLES = Set.of("le", "la", "les", "un", "une", "des" ,"mon","ma");
    private static final Set<String> VERBS = Set.of("mange", "mangent", "charge", "sonne");
    private static final Set<String> NOUNS = Set.of("souris", "fromage", "téléphone");
    private static final Set<String> PRONOUNS = Set.of("Je","je", "tu", "il", "elle", "nous", "vous", "ils", "elles");

    private List<String> tokens;
    private int currentIndex;

    public simpleFrParser(String sentence) {
        this.tokens = Arrays.asList(sentence.split(" "));
        this.currentIndex = 0;
    }

    public boolean parse() {
        return phrase() && currentIndex == tokens.size();
    }

    private boolean phrase() {
        int savedIndex = currentIndex;

        // <sujet> <verbe> <complement>
        if (sujet() && verbe() && complement()) return true;

        currentIndex = savedIndex;
        // <sujet> <verbe>
        if (sujet() && verbe()) return true;

        return false;
    }

    private boolean sujet() {
        int savedIndex = currentIndex;

        // <pronom>
        if (pronom()) return true;

        currentIndex = savedIndex;
        // <article> <nom>
        return article() && nom();
    }

    private boolean complement() {
        // <article> <nom>
        return article() && nom();
    }

    private boolean verbe() {
        return match(VERBS);
    }

    private boolean article() {
        return match(ARTICLES);
    }

    private boolean nom() {
        return match(NOUNS);
    }

    private boolean pronom() {
        return match(PRONOUNS);
    }

    private boolean match(Set<String> validWords) {
        if (currentIndex < tokens.size() && validWords.contains(tokens.get(currentIndex))) {
            currentIndex++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 System.out.println("Entrez une phrase :");

        while (true) {
            System.out.print("> ");
            String sentence = scanner.nextLine();
            if (sentence.equalsIgnoreCase("")) {
                System.out.println("Fin !");
                break;
            }

            simpleFrParser parser = new simpleFrParser(sentence);
            if (parser.parse()) {
                System.out.println("La phrase est valide.");
            } else {
                System.out.println("La phrase est invalide.");
            }
        }

        scanner.close();
    }
}
