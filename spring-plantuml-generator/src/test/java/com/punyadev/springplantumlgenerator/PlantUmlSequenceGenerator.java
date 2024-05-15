package com.punyadev.springplantumlgenerator;

import de.elnarion.test.domain.t0002.CallerA;
import de.elnarion.util.plantuml.generator.sequencediagram.PlantUMLSequenceDiagramGenerator;
import de.elnarion.util.plantuml.generator.sequencediagram.config.PlantUMLSequenceDiagramConfigBuilder;
import de.elnarion.util.plantuml.generator.sequencediagram.exception.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
public class PlantUmlSequenceGenerator {

    @Test
    public void testGenSequence() throws NotFoundException, IOException {
        ClassLoader classLoader = PlantUmlSequenceGenerator.class.getClassLoader();
// ARRANGE
        PlantUMLSequenceDiagramConfigBuilder builder = new PlantUMLSequenceDiagramConfigBuilder(CallerA.class.getName(), //(1)
                "callSomething"); //(2)
        PlantUMLSequenceDiagramGenerator generator = new PlantUMLSequenceDiagramGenerator(builder.build()); //(3)
        String expectedDiagramText = IOUtils.toString(Objects.requireNonNull(classLoader.getResource("sequence/0001_basic_caller_test.txt")),
                StandardCharsets.UTF_8);

// ACT
        String generatedDiagram = generator.generateDiagramText(); //(4)
        System.out.println(generatedDiagram);

// ASSERT
        assertAll(() -> assertNotNull(generatedDiagram), () -> assertEquals(expectedDiagramText.replaceAll("\\s+", ""),
                generatedDiagram.replaceAll("\\s+", "")));

    }
}
