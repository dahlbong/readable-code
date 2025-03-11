package cleancode.studycafe.selfRefactoring.pass;

import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;

import java.util.List;

public interface PassFinder {
    List<StudyCafePass> getAllPasses();
    List<StudyCafeLockerPass> getAllLockerPasses();
}
