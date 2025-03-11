package cleancode.studycafe.selfRefactoring.io;

import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;

import java.util.List;

public class StudyCafeFileData implements StudyCafeData {
    private final StudyCafeFileHandler fileHandler;

    public StudyCafeFileData(StudyCafeFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public static StudyCafeFileData of(StudyCafeFileHandler fileHandler) {
        return new StudyCafeFileData(fileHandler);
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
