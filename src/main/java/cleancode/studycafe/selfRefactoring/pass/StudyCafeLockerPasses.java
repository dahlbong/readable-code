package cleancode.studycafe.selfRefactoring.pass;

import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }


    public StudyCafeLockerPass of(StudyCafePass selectedPass) {
        return lockerPasses.stream()
            .filter(locker -> locker.getPassType() == selectedPass.getPassType() &&
                locker.getDuration() == selectedPass.getDuration())
            .findFirst()
            .orElse(null);
    }
}
