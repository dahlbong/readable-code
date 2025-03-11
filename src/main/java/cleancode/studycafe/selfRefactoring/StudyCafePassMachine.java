package cleancode.studycafe.selfRefactoring;

import cleancode.studycafe.selfRefactoring.exception.AppException;
import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;
import cleancode.studycafe.selfRefactoring.pass.PassHandler;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final PassHandler PASS_HANDLER = new PassHandler();


    public void run() {
        try {
            showInitialMessage();

            StudyCafePassType studyCafePassType = getPassTypeFromUser();
            StudyCafePass selectedPass = PASS_HANDLER.selectPass(studyCafePassType, inputHandler, outputHandler);
            StudyCafeLockerPass lockerPass = PASS_HANDLER.selectLockerPass(selectedPass, inputHandler, outputHandler);

            outputHandler.showPassOrderSummary(selectedPass, lockerPass);

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

}
