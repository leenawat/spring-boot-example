package com.punyadev.springplantumlgenerator;

import de.elnarion.util.plantuml.generator.classdiagram.PlantUMLClassDiagramGenerator;
import de.elnarion.util.plantuml.generator.classdiagram.config.PlantUMLClassDiagramConfigBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlantUmlClassGenerator {
    @Test
    public  void testGenClassDiagram() throws IOException {
        ClassLoader classLoader = PlantUmlSequenceGenerator.class.getClassLoader();
        List<String> scanPackages = new ArrayList<>();
        scanPackages.add("de.elnarion.test.domain.t0001");
        List<String> hideClasses = new ArrayList<>(); // (1)
        hideClasses.add("de.elnarion.test.domain.ChildB");
        PlantUMLClassDiagramConfigBuilder configBuilder = new PlantUMLClassDiagramConfigBuilder(scanPackages) //(2)
                .withHideClasses(hideClasses); //(3)
        PlantUMLClassDiagramGenerator generator = new PlantUMLClassDiagramGenerator(configBuilder.build()); //(4)
        String result = generator.generateDiagramText(); //(5)
        System.out.println(result);
        String expectedDiagramText = IOUtils.toString(Objects.requireNonNull(classLoader.getResource("class/0001_general_diagram.txt")),
                StandardCharsets.UTF_8);
        assertNotNull(result);
        assertNotNull(expectedDiagramText);
        assertEquals(expectedDiagramText.replaceAll("\\s+", ""), result.replaceAll("\\s+", ""));





    }
}
