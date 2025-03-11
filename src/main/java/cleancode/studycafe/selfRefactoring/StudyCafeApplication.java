package cleancode.studycafe.selfRefactoring;

import cleancode.studycafe.selfRefactoring.io.*;
import cleancode.studycafe.selfRefactoring.io.file.FilePassFinder;
import cleancode.studycafe.selfRefactoring.pass.PassFinder;
import cleancode.studycafe.selfRefactoring.io.file.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeFileHandler fileHandler = new StudyCafeFileHandler();
        PassFinder passData = new FilePassFinder(fileHandler);

        StudyCafePassMachine studyCafePassMachine = StudyCafePassMachine.of(inputHandler, outputHandler, passData);

        studyCafePassMachine.run();
    }

}
