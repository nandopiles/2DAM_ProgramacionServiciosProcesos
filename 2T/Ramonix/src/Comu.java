public interface Comu {

    //LLETRES
    String ANSI_RESET = "\u001B[0m";

    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_LIGHT_BROWN = "\u001B[1;33m";

    // DADES DE LES CONNEXIONS
    String HOST = "localhost";
    int PORT = 5000;

    // LLISTAT DE HACKERS
    Object[][] HACKERS = {
            {"Neo", -20, 2},
            {"P4q1T0", -10, 1},
            {"PaU3T", -10, 1},
            {"Ab4$t0$", 10, 1}
    };
}
