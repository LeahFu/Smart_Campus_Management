package ca.access.annotation.generation;

import ca.access.annotation.EnableAccessCreateBy;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class CreationCreateByGeneration implements AnnotationValueGeneration<EnableAccessCreateBy> {
    private ValueGenerator<?> generator;

    public CreationCreateByGeneration() {
    }

    @Override
    public void initialize(EnableAccessCreateBy enableAccessUpdateBy, Class<?> propertyType) {
        this.generator = CreateByGenerators.get(propertyType);
    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return this.generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }
}
