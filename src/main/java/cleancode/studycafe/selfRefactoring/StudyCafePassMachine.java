package cleancode.studycafe.selfRefactoring;

import cleancode.studycafe.selfRefactoring.exception.AppException;
import cleancode.studycafe.selfRefactoring.io.InputHandler;
import cleancode.studycafe.selfRefactoring.io.OutputHandler;
import cleancode.studycafe.selfRefactoring.io.StudyCafeData;
import cleancode.studycafe.selfRefactoring.model.StudyCafeLockerPass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;
import cleancode.studycafe.selfRefactoring.pass.PassHandler;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final PassHandler passHandler;

    private StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeData passData) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.passHandler = PassHandler.of(passData);
    }

    public static StudyCafePassMachine of(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeData passData) {
        return new StudyCafePassMachine(inputHandler, outputHandler, passData);
    }

    public void run() {
        try {
            showInitialMessage();

            StudyCafePassType studyCafePassType = getPassTypeFromUser();
            StudyCafePass selectedPass = passHandler.selectPass(studyCafePassType, inputHandler, outputHandler);
            StudyCafeLockerPass lockerPass = passHandler.selectLockerPass(selectedPass, inputHandler, outputHandler);

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
