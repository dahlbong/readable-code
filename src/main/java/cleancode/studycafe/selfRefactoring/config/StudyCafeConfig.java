package cleancode.studycafe.selfRefactoring.config;

import cleancode.studycafe.selfRefactoring.StudyCafePassMachine;
import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.io.file.FilePassFinder;
import cleancode.studycafe.selfRefactoring.io.file.StudyCafeFileHandler;
import cleancode.studycafe.selfRefactoring.pass.PassFinder;

public class StudyCafeConfig {

    public static StudyCafePassMachine setPassMachine() {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeFileHandler fileHandler = new StudyCafeFileHandler();
        PassFinder passData = new FilePassFinder(fileHandler);

        return StudyCafePassMachine.of(inputHandler, outputHandler, passData);
    }
}
