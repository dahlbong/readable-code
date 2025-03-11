package cleancode.studycafe.selfRefactoring;

import cleancode.studycafe.selfRefactoring.exception.AppException;
import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.io.StudyCafeFileHandler;
import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler STUDY_CAFE_FILE_HANDLER = new StudyCafeFileHandler();


    public void run() {
        try {
            showInitialMessage();
            StudyCafePassType studyCafePassType = getPassTypeFromUser();
            selectPassByPassType(studyCafePassType);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void showInitialMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private StudyCafePassType getPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private void selectPassByPassType(StudyCafePassType passType) {
        if (passType == StudyCafePassType.HOURLY) {
            usingHourlyPass();
        }
        if (passType == StudyCafePassType.WEEKLY) {
            usingWeeklyPass();
        }
        if (passType == StudyCafePassType.FIXED) {
            usingFixedPass();
        }
    }

    private void usingHourlyPass() {
        List<StudyCafePass> studyCafePasses = STUDY_CAFE_FILE_HANDLER.readStudyCafePasses();
        List<StudyCafePass> hourlyPasses = filterPassesByPassType(studyCafePasses, StudyCafePassType.HOURLY);
        
        outputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void usingWeeklyPass() {
        List<StudyCafePass> studyCafePasses = STUDY_CAFE_FILE_HANDLER.readStudyCafePasses();
        List<StudyCafePass> weeklyPasses = filterPassesByPassType(studyCafePasses, StudyCafePassType.WEEKLY);
        
        outputHandler.showPassListForSelection(weeklyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }


    private void usingFixedPass() {
        List<StudyCafePass> studyCafePasses = STUDY_CAFE_FILE_HANDLER.readStudyCafePasses();
        List<StudyCafePass> fixedPasses = filterPassesByPassType(studyCafePasses, StudyCafePassType.FIXED);
        
        outputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

        List<StudyCafeLockerPass> lockerPasses = STUDY_CAFE_FILE_HANDLER.readLockerPasses();
        StudyCafeLockerPass lockerPass = getAvailableLockerPass(lockerPasses, selectedPass);

        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
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

    private static List<StudyCafePass> filterPassesByPassType(List<StudyCafePass> studyCafePasses, StudyCafePassType passType) {
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == passType)
            .toList();
        return fixedPasses;
    }

    


}
