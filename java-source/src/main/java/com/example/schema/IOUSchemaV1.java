package com.example.schema;

import com.google.common.collect.ImmutableList;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * An IOUState schema.
 */
public class IOUSchemaV1 extends MappedSchema {
    public IOUSchemaV1() {
        super(IOUSchema.class, 1, ImmutableList.of(PersistentIOU.class));
    }

    @Entity
    @Table(name = "iou_states")
    public static class PersistentIOU extends PersistentState {
        @Column(name = "hospital") private final String hospital;
        @Column(name = "patient") private final String patient;
        @Column(name = "name") private final String name;
        @Column(name = "age") private final int age;
        @Column(name = "gender") private final String gender;
        @Column(name = "height") private final int height;
        @Column(name = "weight") privat