package br.com.felixgilioli.alunoservice.arquitetura;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "br.com.felixgilioli.alunoservice")
public class ArquiteturaTest {

    @ArchTest
    public final ArchRule controllerTest = ArchRuleDefinition.classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..controller..")
            .andShould().haveSimpleNameEndingWith("Controller")
            .andShould().haveSimpleNameNotEndingWith("RestController");

    @ArchTest
    public final ArchRule controllerNaoEstaChamandoRepositoryTest = ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..controller..")
            .should().dependOnClassesThat().resideInAPackage("..repository..")
            .as("Controller não pode chamar diretamente o repository.");

    @ArchTest
    public final ArchRule serviceTest = ArchRuleDefinition.classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    @ArchTest
    public final ArchRule entityTest = ArchRuleDefinition.classes()
            .that().areAnnotatedWith(Entity.class)
            .should().resideInAPackage("..entity..");

    @ArchTest
    public final ArchRule controllerMethodsTest = ArchRuleDefinition.noMethods()
            .that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
            .should().beAnnotatedWith(RequestMapping.class);

    @ArchTest
    public final ArchRule logTest = ArchRuleDefinition.fields()
            .that().haveRawType(Logger.class)
            .should().bePrivate()
            .andShould().beStatic()
            .andShould().beFinal()
            .allowEmptyShould(true);

    @ArchTest
    public final ArchRule log2Test = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    public final ArchRule injecaoDependenciaTest = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}
