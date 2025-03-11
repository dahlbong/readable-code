package cleancode.studycafe.selfRefactoring.io.file;

import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.pass.PassFinder;

import java.util.List;

public class FilePassFinder implements PassFinder {
    private final StudyCafeFileHandler fileHandler;

    public FilePassFinder(StudyCafeFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public static FilePassFinder of(StudyCafeFileHandler fileHandler) {
        return new FilePassFinder(fileHandler);
    }

    @Override
    public List<StudyCafePass> getAllPasses() {
        return fileHandler.readStudyCafePasses();
    }

    @Override
    public List<StudyCafeLockerPass> getAllLockerPasses() {
        return fileHandler.readLockerPasses();
    }
}
