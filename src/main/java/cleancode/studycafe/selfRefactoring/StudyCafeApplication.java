package cleancode.studycafe.selfRefactoring;

import cleancode.studycafe.selfRefactoring.io.*;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeFileHandler fileHandler = new StudyCafeFileHandler();
        StudyCafeData passData = new StudyCafeFileData(fileHandler);

        StudyCafePassMachine studyCafePassMachine = StudyCafePassMachine.of(inputHandler, outputHandler, passData);

        studyCafePassMachine.run();
    }

}
