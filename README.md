# java-grpc


import java.util.List;

import org.junit.jupiter.api.Test;

import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import static com.openpojo.validation.affirm.Affirm.affirmTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PojoTests {

    // Test specific pojo classes to avoid full classpath scan...
    private static final List<PojoClass> POJO_CLASSES = List.of(
    );

    @Test
    void pojoClassesTest() {
        assertDoesNotThrow(() -> ValidatorBuilder.create()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule(),
                        new EqualsAndHashCodeMatchRule(),
                        new NoStaticExceptFinalRule(),
                        new NoPublicFieldsExceptStaticFinalRule())
                .with(new SetterTester(),
                        new GetterTester(),
                        // hashCode, equals & toString tester (PITest line coverage)
                        pojoClass -> {
                            Object o = RandomFactory.getRandomValue(pojoClass.getClazz());
                            affirmTrue("", o.hashCode() != 0 && !o.equals(""));
                            String s = o.getClass().getSimpleName();
                            affirmTrue("toString value doesn't start with class name ".concat(s), o.toString().startsWith(s));
                        })
                .build()
                .validate(POJO_CLASSES));
    }
}
