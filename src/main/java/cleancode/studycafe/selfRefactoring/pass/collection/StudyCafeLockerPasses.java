package cleancode.studycafe.selfRefactoring.pass.collection;

import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> lockerPasses) {
        return new StudyCafeLockerPasses(lockerPasses);
    }


    public StudyCafeLockerPass findMatchingLockerFor(StudyCafePass selectedPass) {
        return lockerPasses.stream()
            .filter(locker -> locker.getPassType() == selectedPass.getPassType() &&
                locker.getDuration() == selectedPass.getDuration())
            .findFirst()
            .orElse(null);
    }
}
