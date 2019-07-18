package toothpick.compiler.factory;

import com.google.common.base.Joiner;
import com.google.testing.compile.JavaFileObjects;
import javax.tools.JavaFileObject;
import org.junit.Test;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class RelaxedFactoryForClassContainingMethodsTest extends BaseFactoryTest {
  @Test
  public void testRelaxedFactoryCreationForInjectedMethod() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject void m(Foo foo) {}", //
        "}", //
        "  class Foo {}"));

    JavaFileObject expectedSource = JavaFileObjects.forSourceString("test/TestRelaxedFactoryCreationForInjectMethod__Factory", Joiner.on('\n').join(//
        "package test;", //
        "import java.lang.Override;", //
        "import toothpick.Factory;", //
        "import toothpick.MemberInjector;", //
        "import toothpick.Scope;", //
        "", //
        "public final class TestRelaxedFactoryCreationForInjectMethod__Factory implements Factory<TestRelaxedFactoryCreationForInjectMethod> {", //
        "  private MemberInjector<TestRelaxedFactoryCreationForInjectMethod> memberInjector"
            + " = new test.TestRelaxedFactoryCreationForInjectMethod__MemberInjector();", //
        "  @Override", //
        "  public TestRelaxedFactoryCreationForInjectMethod createInstance(Scope scope) {", //
        "    scope = getTargetScope(scope);", //
        "    TestRelaxedFactoryCreationForInjectMethod testRelaxedFactoryCreationForInjectMethod"
            + " = new TestRelaxedFactoryCreationForInjectMethod();", //
        "    memberInjector.inject(testRelaxedFactoryCreationForInjectMethod, scope);", //
        "    return testRelaxedFactoryCreationForInjectMethod;", //
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
  public void testRelaxedFactoryCreationForInjectedMethod_shouldFail_WhenMethodIsPrivate() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject private void m(Foo foo) {}", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("@Inject annotated methods must not be private : test.TestRelaxedFactoryCreationForInjectMethod#m");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedMethod_shouldFail_WhenContainingClassIsInvalid() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  private static class InnerClass {", //
        "    @Inject void m(Foo foo) {}", //
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
  public void testRelaxedFactoryCreationForInjectedMethod_shouldFail_WhenMethodParameterIsInvalidLazy() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "import toothpick.Lazy;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject void m(Lazy foo) {}", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("Parameter foo in method/constructor test.TestRelaxedFactoryCreationForInjectMethod#m is not a valid toothpick.Lazy.");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedMethod_shouldFail_WhenMethodParameterIsInvalidProvider() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "import javax.inject.Provider;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject void m(Provider foo) {}", //
        "}", //
        "  class Foo {}"));

    assert_().about(javaSource())
        .that(source)
        .processedWith(ProcessorTestUtilities.factoryAndMemberInjectorProcessors())
        .failsToCompile()
        .withErrorContaining("Parameter foo in method/constructor test.TestRelaxedFactoryCreationForInjectMethod#m is not a valid javax.inject.Provider.");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedMethod_shouldWorkButNoFactoryIsProduced_whenTypeIsAbstract() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public abstract class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject void m(Foo foo) {}", //
        "}", //
        "  class Foo {}"));

    assertThatCompileWithoutErrorButNoFactoryIsCreated(source, "test", "TestRelaxedFactoryCreationForInjectMethod");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedMethod_shouldWorkButNoFactoryIsProduced_whenTypeHasANonDefaultConstructor() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject void m(Foo foo) {}", //
        "  public TestRelaxedFactoryCreationForInjectMethod(String s) {}", //
        "}", //
        "class Foo {}"));

    assertThatCompileWithoutErrorButNoFactoryIsCreated(source, "test", "TestRelaxedFactoryCreationForInjectMethod");
  }

  @Test
  public void testRelaxedFactoryCreationForInjectedMethod_shouldWorkButNoFactoryIsProduced_whenTypeHasAPrivateDefaultConstructor() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.TestRelaxedFactoryCreationForInjectMethod", Joiner.on('\n').join(//
        "package test;", //
        "import javax.inject.Inject;", //
        "public class TestRelaxedFactoryCreationForInjectMethod {", //
        "  @Inject void m(Foo foo) {}", //
        "  private TestRelaxedFactoryCreationForInjectMethod() {}", //
        "}", //
        "class Foo {}"));

    assertThatCompileWithoutErrorButNoFactoryIsCreated(source, "test", "TestRelaxedFactoryCreationForInjectMethod");
  }
}
