package com.roberto.transactions.infra.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTypeEntityTest {

    @Test
    void shouldCreateOperationTypeEntityUsingNoArgsConstructor() {
        OperationTypeEntity entity = new OperationTypeEntity();

        assertNull(entity.getOperationTypeId());
        assertNull(entity.getDescription());
    }

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        OperationTypeEntity entity = new OperationTypeEntity();
        entity.setOperationTypeId(1L);
        entity.setDescription("Normal Purchase");

        assertEquals(1L, entity.getOperationTypeId());
        assertEquals("Normal Purchase", entity.getDescription());
    }

    @Test
    void shouldCreateOperationTypeEntityUsingBuilder() {
        OperationTypeEntity entity = OperationTypeEntity.builder()
                .operationTypeId(3L)
                .description("Withdrawal")
                .build();

        assertEquals(3L, entity.getOperationTypeId());
        assertEquals("Withdrawal", entity.getDescription());
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        OperationTypeEntity entity1 = OperationTypeEntity.builder()
                .operationTypeId(1L)
                .description("Normal Purchase")
                .build();

        OperationTypeEntity entity2 = OperationTypeEntity.builder()
                .operationTypeId(1L)
                .description("Other")
                .build();

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());

        entity2.setOperationTypeId(2L);
        assertNotEquals(entity1, entity2);
        assertNotEquals(entity1.hashCode(), entity2.hashCode());
    }
}

