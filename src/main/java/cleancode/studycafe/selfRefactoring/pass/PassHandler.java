package cleancode.studycafe.selfRefactoring.pass;

import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;
import cleancode.studycafe.selfRefactoring.exception.AppException;
import cleancode.studycafe.selfRefactoring.pass.collection.StudyCafeLockerPasses;
import cleancode.studycafe.selfRefactoring.pass.collection.StudyCafePasses;

import java.util.List;

public class PassHandler {
    private final PassFinder PASS_DATA;

    private PassHandler(PassFinder passData) {
        this.PASS_DATA = passData;
    }

    public static PassHandler of(PassFinder passData) {
        return new PassHandler(passData);
    }

    public StudyCafePass selectPass(StudyCafePassType passType, InputHandler inputHandler, OutputHandler outputHandler) {
        StudyCafePasses passes = StudyCafePasses.of(PASS_DATA.getAllPasses());
        List<StudyCafePass> availablePasses = passes.filterByPassType(passType);

        if (availablePasses.isEmpty()) {
            throw new AppException("해당 이용권을 찾을 수 없습니다.");
        }

        outputHandler.showPassListForSelection(availablePasses);
        return inputHandler.getSelectPass(availablePasses);
    }

    public StudyCafeLockerPass selectLockerPass(StudyCafePass selectedPass, InputHandler inputHandler, OutputHandler outputHandler) {
        if (!selectedPass.isPassTypeFixed()) {
            return null;
        }

        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(PASS_DATA.getAllLockerPasses());
        StudyCafeLockerPass availableLockerPass = lockerPasses.findMatchingLockerFor(selectedPass);

        if (availableLockerPass == null) {
            return null;
        }

        outputHandler.askLockerPass(availableLockerPass);
        boolean lockerSelection = inputHandler.getLockerSelection();

        return lockerSelection ? availableLockerPass : null;
    }
}
