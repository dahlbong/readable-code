package cleancode.studycafe.selfRefactoring.io;

import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;

import java.util.List;

public interface StudyCafeData {
    List<StudyCafePass> getAllPasses();
    List<StudyCafeLockerPass> getAllLockerPasses();
}
