import java.util.ListResourceBundle;
import java.util.function.UnaryOperator;

public class Animals extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][]{
                {"Good morning!", "Good morning!"},
                {"Good afternoon!", "Good afternoon!"},
                {"Good evening!", "Good evening!"},
                {"Hi, Night Owl!", "Hi, Night Owl!"},
                {"Yes", "Yes"},
                {"No", "No"},
                {"Not sure", "Not sure"},
                {"confirmations", new String[]{
                        "y", "yes", "yeah", "yep", "sure", "right", "affirmative", "correct",
                        "indeed", "you bet", "exactly", "you said it"
                }},
                {"negations", new String[] {
                        "n", "no", "no way", "nah", "nope", "negative", "i don't think so",
                        "yeah no"
                }},
                {"clarifications", new String[] {
                        "I'm not sure I caught you: was it yes or no?",
                        "Funny, I still don't understand, is it yes or no?",
                        "Oh, it's too complicated for me: just tell me yes or no.",
                        "Could you please simply say yes or no?",
                        "Oh, no, don't try to confuse me: say yes or no."
                }},
                {"farewells", new String[] {
                        "Bye!", "Have a nice day!", "See you soon!",
                        "OK, Later!", "Hope to see you soon!", "Chao!"
                }},
                {"DefinedArticle", "The"},
                {"UndefinedArticleRegex", "an?"},
                {"getUndefinedArticle", (UnaryOperator<String>) name -> {
                    return name.matches("(?i)^\\s*[aeoui]\\w*.*") ? "an" : "a";
                }},
                {"factFormat", "(?i)it (can|has|is) ([\\w\\s]+)[?.!]?"},
                {"factFormatError", "The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal"},
                {"getQuestion", (UnaryOperator<String>) verb -> {
                    String questionVerb = verb + " it";
                    if ("has".equalsIgnoreCase(verb)) {
                        questionVerb = "does it have";
                    };
                    return questionVerb;
                }},
                {"getNegativeVerb", (UnaryOperator<String>) verb -> {
                    String negative = "";
                    if ("can".equalsIgnoreCase(verb)) {
                        negative = "can't";
                    } else if ("has".equalsIgnoreCase(verb)) {
                        negative = "doesn't have";
                    } else if ("is".equalsIgnoreCase(verb)) {
                        negative = "isn't";
                    }
                    return negative;
                }},
                {"Here are the animals I know:", "Here are the animals I know:"},
                {"No facts about %s.%n", "No facts about %s.%n"},
                {"It", "It"},
                {"Facts about %s:%n", "Facts about %s:%n"},
                {"The Knowledge Tree stats", "The Knowledge Tree stats"},
                {"root node", "root node"},
                {"total number of nodes", "total number of nodes"},
                {"total number of animals", "total number of animals"},
                {"total number of statements", "total number of statements"},
                {"height of the tree", "height of the tree"},
                {"minimum animal's depth", "minimum animal's depth"},
                {"average animal's depth", "average animal's depth"},
                {"ask animal", "I want to learn about animals.\n" +
                        "Which animal do you like most?"},
                {"Welcome", "Welcome to the animal expert system!"},
                {"menu", "What do you want to do:\n" +
                        "\n" +
                        "1. Play the guessing game\n" +
                        "2. List of all animals\n" +
                        "3. Search for an animal\n" +
                        "4. Calculate statistics\n" +
                        "5. Print the Knowledge Tree\n" +
                        "0. Exit"},
                {"Enter the animal:", "Enter the animal:"},
                {"game start", "You think of an animal, and I guess it.\n" +
                        "Press enter when you're ready."},
                {"Is it", "Is it"},
                {"win", "Nice! I know so much about animals!"},
                {"loss", "I give up. What animal do you have in mind?"},
                {"specifyDistinction", "Specify a fact that distinguishes %s from %s.\n" +
                        "The sentence should satisfy one of the following templates:\n" +
                        "- It can ...\n" +
                        "- It has ...\n" +
                        "- It is a/an ...%n"},
                {"isStatementCorrect", "Is the statement correct for %s?%n"},
                {"iLearned", "I have learned the following facts about animals:"},
                {"iCanDistinguish", "I can distinguish these animals by asking the question:"},
                {"todayWeLearned", "Nice! I've learned so much about animals!"},
                {"playAgainQuestion", "Would you like to play again?"}
        };
    }
}