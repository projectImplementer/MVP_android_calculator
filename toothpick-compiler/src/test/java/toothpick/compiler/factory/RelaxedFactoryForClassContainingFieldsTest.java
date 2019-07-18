package toothpick.compiler.factory;

import com.google.common.base.Joiner;
import com.google.testing.compile.JavaFileObjects;
import javax.tools.JavaFileObject;
import org.junit.Test;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class RelaxedFactoryForClassContainingFieldsTest extends BaseFactoryTest {
  @Test
  public void testRelaxedFactoryCreationForInjectedField() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject Foo foo;", //
        "}", //
        "  class Foo {}"));

    JavaFileObject expectedSource = JavaFileObjects.forSourceString("test/TestRelaxedFactoryCreationForInjectField__Factory", Joiner.on('\n').join(//
        "package test;", //
        "import java.lang.Override;", //
        "import toothpick.Factory;", //
        "import toothpick.MemberInjector;", //
        "import toothpick.Scope;", //
        "", //
        "public final class TestRelaxedFactoryCreationForInjectField__Factory implements Factory<TestRelaxedFactoryCreationForInjectField> {", //
        "  private MemberInjector<TestRelaxedFactoryCreationForInjectField> memberInjector = "
            + "new test.TestRelaxedFactoryCreationForInjectField__MemberInjector();",
        //
        "  @Override", //
        "  public TestRelaxedFactoryCreationForInjectField createInstance(Scope scope) {", //
        "    scope = getTargetScope(scope);", //
        "    TestRelaxedFactoryCreationForInjectField testRelaxedFactoryCreationForInjectField = new TestRelaxedFactoryCreationForInjectField();", //
        "    memberInjector.inject(testRelaxedFactoryCreationForInjectField, scope);", //
        "    return testRelaxedFactoryCreationForInjectField;", //
        "  }", //
        "  @Override", //
        "  public Scope getTargetScope(Scope scope) {", //
        "    return scope;", //
        "  }", //
        "  @Override", //
        "  public boolean hasScopeAnnotation() {", //
        "    return false;", //
        "  }", //
        "  @Override", //
        "  public boolean hasProvidesSingletonInScopeAnnotation() {", //
        "    return false;", //
        "  }", //
        "}" //
    ));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource);
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldFail_WhenFieldIsPrivate() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject private Foo foo;", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("@Inject annotated fields must be non private : test.TestRelaxedFactoryCreationForInjectField#foo");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldFail_WhenContainingClassIsPrivate() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  private static class InnerClass {", //
        "    @Inject Foo foo;", //
        "  }", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("@Injected fields in class InnerClass. The class must be non private.");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldFail_WhenFieldIsInvalidLazy() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "import toothpick.Lazy;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject Lazy foo;", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("Field test.TestRelaxedFactoryCreationForInjectField#foo is not a valid toothpick.Lazy.");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldFail_WhenFieldIsInvalidProvider() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "import javax.inject.Provider;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject Provider foo;", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("Field test.TestRelaxedFactoryCreationForInjectField#foo is not a valid javax.inject.Provider.");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldWorkButNoFactoryIsProduced_whenTypeIsAbstract() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public abstract class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject Foo foo;", //
        "}", //
        "  class Foo {}"));

    assertThatCompileWithoutErrorButNoFactoryIsCreated(source, "test", "TestRelaxedFactoryCreationForInjectField");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldWorkButNoFactoryIsProduced_whenTypeHasANonDefaultConstructor() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject Foo foo;", //
        "  public TestRelaxedFactoryCreationForInjectField(String s) {}", //
        "}", //
        "class Foo {}"));

    assertThatCompileWithoutErrorButNoFactoryIsCreated(source, "test", "TestRelaxedFactoryCreationForInjectField");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedField_shouldWorkButNoFactoryIsProduced_whenTypeHasAPrivateDefaultConstructor() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectField", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectField {", //
        "  @Inject Foo foo;", //
        "  private TestRelaxedFactoryCreationForInjectField() {}", //
        "}", //
        "class Foo {}"));

    assertThatCompileWithoutErrorButNoFactoryIsCreated(source, "test", "TestRelaxedFactoryCreationForInjectField");
  }
}
