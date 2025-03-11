package cleancode.studycafe.selfRefactoring.pass.collection;

import cleancode.studycafe.selfRefactoring.model.StudyCafePass;
import cleancode.studycafe.selfRefactoring.model.StudyCafePassType;

import java.util.List;
import java.util.stream.Collectors;

public class StudyCafePasses {
    private final List<StudyCafePass> passes;

    private StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes) {
        return new StudyCafePasses(passes);
    }

    public List<StudyCafePass> filterByPassType(StudyCafePassType passType) {
        return passes.stream()
            .filter(pass -> pass.getPassType() == passType)
            .collect(Collectors.toList());
    }

}
