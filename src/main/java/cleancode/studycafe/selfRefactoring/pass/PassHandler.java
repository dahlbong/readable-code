package cleancode.studycafe.selfRefactoring.pass;

import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.io.StudyCafeFileHandler;
import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;

import java.util.List;

public class PassHandler {

    private final StudyCafeFileHandler STUDY_CAFE_FILE_HANDLER = new StudyCafeFileHandler();

    public List<StudyCafePass> getPassesByPassType(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = STUDY_CAFE_FILE_HANDLER.readStudyCafePasses();
        return studyCafePasses.stream()
            .filter(pass -> pass.getPassType() == passType)
            .toList();
    }

    public StudyCafePass selectPass(StudyCafePassType passType, InputHandler inputHandler, OutputHandler outputHandler) {
        List<StudyCafePass> availablePasses = getPassesByPassType(passType);
        outputHandler.showPassListForSelection(availablePasses);
        return inputHandler.getSelectPass(availablePasses);
    }

    public StudyCafeLockerPass selectLockerPass(StudyCafePass selectedPass, InputHandler inputHandler, OutputHandler outputHandler) {
        if (!selectedPass.ifPassTypeFixed()) {
            return null;
        }

        List<StudyCafeLockerPass> lockerPasses = STUDY_CAFE_FILE_HANDLER.readLockerPasses();
        StudyCafeLockerPass availableLockerPass = getAvailableLockerPass(lockerPasses, selectedPass);

        if (availableLockerPass == null) {
            return null;
        }

        outputHandler.askLockerPass(availableLockerPass);
        boolean lockerSelection = inputHandler.getLockerSelection();

        return lockerSelection ? availableLockerPass : null;
    }

    private static StudyCafeLockerPass getAvailableLockerPass(List<StudyCafeLockerPass> lockerPasses, StudyCafePass selectedPass) {
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);
        return lockerPass;
    }

}
