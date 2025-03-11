package cleancode.studycafe.selfRefactoring.pass;

import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;

import java.util.List;
import java.util.stream.Collectors;

public class StudyCafePasses {
    private final List<StudyCafePass> passes;

    public StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public List<StudyCafePass> of(StudyCafePassType passType) {
        return passes.stream()
            .filter(pass -> pass.getPassType() == passType)
            .collect(Collectors.toList());
    }

}
