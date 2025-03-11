package cleancode.studycafe.selfRefactoring;

import cleancode.studycafe.selfRefactoring.config.StudyCafeConfig;

public class StudyCafeApplication {

    public static void main(String[] args) {

        StudyCafePassMachine studyCafePassMachine = StudyCafeConfig.setPassMachine();
        studyCafePassMachine.run();
    }

}
