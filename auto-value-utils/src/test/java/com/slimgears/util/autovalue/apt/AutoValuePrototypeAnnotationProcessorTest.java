package com.slimgears.util.autovalue.apt;

import com.google.auto.value.processor.AutoValueProcessor;
import com.slimgears.apt.util.AnnotationProcessingTester;
import com.slimgears.apt.util.StoreWrittenFilesRule;
import com.slimgears.util.generic.ScopedInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.event.Level;

public class AutoValuePrototypeAnnotationProcessorTest {
    private ScopedInstance.Closeable registrarScope;
    @ClassRule public final static StoreWrittenFilesRule storeWrittenFilesRule = StoreWrittenFilesRule.forPath("build/test");

    @Before
    public void setUp() {
        registrarScope = Registrar.scope();
    }

    @After
    public void tearDown() {
        registrarScope.close();
    }

    @Test
    public void testBasicPrototype() {
        tester()
                .inputFiles(
                        "SampleFieldAnnotation.java",
                        "SampleValuePrototype.java")
                .expectedSources(
                        "SampleValue.java",
                        "SampleValuePrototypeBuilder.java")
                .test();
    }

    @Test
    public void testGenericPrototype() {
        tester()
                .inputFiles(
                        "SampleInterface.java",
                        "SampleGenericInterface.java",
                        "SampleGenericPrototype.java")
                .expectedSources(
                        "SampleGenericPrototypeBuilder.java",
                        "SampleGeneric.java")
                .test();
    }

    @Test
    public void testComparableGenericPrototype() {
        tester()
                .inputFiles(
                        "SampleComparableGenericInterface.java",
                        "SampleComparableGenericPrototype.java")
                .expectedSources(
                        "SampleComparableGeneric.java",
                        "SampleComparableGenericPrototypeBuilder.java")
                .test();
    }

    @Test
    public void testGenericFieldPrototype() {
        tester()
                .inputFiles("SampleGenericFieldPrototype.java")
                .expectedSources(
                        "SampleGenericFieldPrototypeBuilder.java",
                        "SampleGenericField.java")
                .test();
    }

    @Test
    public void testPrototypeReferences() {
        tester()
                .inputFiles("SampleAPrototype.java", "SampleBPrototype.java")
                .expectedSources("SampleBPrototypeBuilder.java")
                .test();
    }

    @Test
    public void testGuavaValuePrototype() {
        tester()
                .inputFiles("SampleGuavaValuePrototype.java")
                .expectedSources("SampleGuavaValuePrototypeBuilder.java", "SampleGuavaValue.java")
                .test();
    }

    @Test
    public void testCustomBuilderValuePrototype() {
        tester()
                .inputFiles("SampleCustomBuilderValuePrototype.java")
                .expectedSources(
                        "SampleCustomBuilderValue.java",
                        "SampleCustomBuilderValuePrototypeBuilder.java")
                .test();
    }

    @Test
    public void testSpecializedGenericPrototype() {
        tester()
                .inputFiles(
                        "SampleSpecializedGenericPrototype.java",
                        "SampleGuavaInterface.java")
                .expectedSources(
                        "SampleSpecializedGeneric.java",
                        "SampleSpecializedGenericPrototypeBuilder.java")
                .test();
    }

    @Test
    public void testGenericListContainerPrototype() {
        tester()
                .inputFiles(
                        "GenericListItemPrototype.java",
                        "GenericListContainerPrototype.java")
                .expectedSources(
                        "GenericListItem.java",
                        "GenericListContainer.java")
                .test();
    }

    @Test
    public void testSampleWithKeyPrototype() {
        tester()
                .inputFiles("SampleWithKeyPrototype.java")
                .expectedSources("SampleWithKey.java")
                .test();
    }

    @Test
    public void testCircularDependencyPrototype() {
        tester()
                .inputFiles("SampleCircularDependencyPrototype.java")
                .expectedSources("SampleCircularDependency.java")
                .test();
    }

    @Test
    public void testNestedTypePrototype() {
        tester()
                .inputFiles("SampleNestedTypePrototype.java")
                .expectedSources("SampleNestedType.java")
                .test();
    }

    @Test
    public void testCustomAutoAnnotationWithBuilder() {
        tester()
                .inputFiles(
                        "CustomAutoAnnotationWithBuilder.java",
                        "SampleValueUsingCustomProto.java")
                .expectedSources("SampleValueUsingCustomConcreteWithBuilder.java")
                .test();
    }

    @Test
    public void testCustomAutoAnnotationWithCreator() {
        tester()
                .inputFiles(
                        "CustomAutoAnnotationWithCreator.java",
                        "SampleValueUsingCustomProto.java")
                .expectedSources("SampleValueUsingCustomConcreteWithCreator.java")
                .test();
    }

    private AnnotationProcessingTester tester() {
        return AnnotationProcessingTester.create()
                .verbosity(Level.TRACE)
                .processedWith(new AutoValuePrototypeAnnotationProcessor(), new AutoValueProcessor());
    }
}
