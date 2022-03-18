package animals;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(ImportExport.getFormat(args));
        game.menu();
    }
}
