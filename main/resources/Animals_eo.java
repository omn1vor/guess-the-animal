import java.util.ListResourceBundle;
import java.util.function.UnaryOperator;

public class Animals_eo extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][]{
                {"Good morning!", "Bonan matenon!"},
                {"Good afternoon!", "Bonan tagon!"},
                {"Good evening!", "Bonan vesperon!"},
                {"Hi, Night Owl!", "Saluton Nokta Strigo!"},
                {"Yes", "Jes"},
                {"No", "Ne"},
                {"Not sure", "Ne certas"},
                {"confirmations", new String[]{
                        "y", "jes", "certe"
                }},
                {"negations", new String[] {
                        "n", "ne"
                }},
                {"clarifications", new String[] {
                        "Bonvolu enigi jes aŭ ne.",
                        "Amuze, mi ankoraŭ ne komprenas, ĉu jes aŭ ne?",
                        "Pardonu, devas esti jes aŭ ne.",
                        "Ni provu denove: ĉu jes aŭ ne?",
                        "Pardonu, ĉu mi rajtas demandi vin denove: ĉu tio estis jes aŭ ne?"
                }},
                {"farewells", new String[] {
                        "Ĝis!", "Ĝis revido!", "Estis agrable vidi vin!"
                }},
                {"DefinedArticle", "La"},
                {"UndefinedArticleRegex", ""},
                {"getUndefinedArticle", (UnaryOperator<String>) name -> ""},
                {"factFormat", "(?i)Ĝi (povas|havas|estas) ([\\w\\s]+)[?.!]?"},
                {"factFormatError", "La ekzemploj de aserto:\n" +
                        " - Ĝi povas flugi\n" +
                        " - Ĝi havas kornojn\n" +
                        " - Ĝi estas mamulo"},
                {"getQuestion", (UnaryOperator<String>) verb -> "Ĉu ĝi " + verb},
                {"getNegativeVerb", (UnaryOperator<String>) verb -> "ne " + verb},
                {"Here are the animals I know:", "Jen la bestoj, kiujn mi konas:"},
                {"No facts about %s.%n", "Neniuj faktoj pri %s.%n"},
                {"It", "Ĝi"},
                {"Facts about %s:%n", "Faktoj pri %s:%n"},
                {"The Knowledge Tree stats", "La statistiko de la Scio-Arbo"},
                {"root node", "radika nodo"},
                {"total number of nodes", "totala nombro de nodoj"},
                {"total number of animals", "totala nombro de bestoj"},
                {"total number of statements", "totala nombro de deklaroj"},
                {"height of the tree", "alteco de la arbo"},
                {"minimum animal's depth", "minimuma profundo"},
                {"average animal's depth", "averaĝa profundo"},
                {"ask animal", "Mi volas lerni pri bestoj.\n" +
                        "Kiun beston vi plej ŝatas?"},
                {"Welcome", "Bonvenon al la sperta sistemo de la besto!"},
                {"menu", "Kion vi volas fari:\n" +
                        "\n" +
                        "1. Ludi la divenludon\n" +
                        "2. Listo de ĉiuj bestoj\n" +
                        "3. SSerĉi beston\n" +
                        "4. Kalkuli statistikojn\n" +
                        "5. Printi la Sciarbon\n" +
                        "0. Eliri"},
                {"Enter the animal:", "Eniru la beston:"},
                {"game start", "Vi pensu pri besto, kaj mi divenos ĝin.\n" +
                        "Premu enen kiam vi pretas."},
                {"Is it", "Ĉu ĝi estas"},
                {"win", "Bela! Mi scias tiom multe pri bestoj!"},
                {"loss", "Mi rezignas. Kiun beston vi havas en la kapo?"},
                {"specifyDistinction", "Indiku fakton, kiu distingas %s de %s.\n" +
                        "La frazo devas esti de la formato: 'Ĝi ...'.%n"},
                {"isStatementCorrect", "Ĉu la aserto ĝustas por la %s?%n"},
                {"iLearned", "Mi lernis la jenajn faktojn pri bestoj:"},
                {"iCanDistinguish", "Mi povas distingi ĉi tiujn bestojn per la demando:"},
                {"todayWeLearned", "Bela! Mi multe lernis pri bestoj!"},
                {"playAgainQuestion", "Ĉu vi volas provi denove?"}
        };
    }
}