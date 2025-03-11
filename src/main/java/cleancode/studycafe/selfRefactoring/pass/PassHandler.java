package cleancode.studycafe.selfRefactoring.pass;

import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.io.StudyCafeFileHandler;
import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;
import cleancode.studycafe.tobe.exception.AppException;

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
        StudyCafePasses passes = new StudyCafePasses(STUDY_CAFE_FILE_HANDLER.readStudyCafePasses());
        List<StudyCafePass> availablePasses = passes.of(passType);

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

        StudyCafeLockerPasses lockerPasses = new StudyCafeLockerPasses(STUDY_CAFE_FILE_HANDLER.readLockerPasses());
        StudyCafeLockerPass availableLockerPass = lockerPasses.of(selectedPass);

        if (availableLockerPass == null) {
            return null;
        }

        outputHandler.askLockerPass(availableLockerPass);
        boolean lockerSelection = inputHandler.getLockerSelection();

        return lockerSelection ? availableLockerPass : null;
    }
}
