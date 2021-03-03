
package com.example.state;

import com.example.schema.IOUSchemaV1;
import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;
import net.corda.core.schemas.QueryableState;

import java.util.Arrays;
import java.util.List;

/**
 * The state object recording IOU agreements between two parties.
 *
 * A state must implement [ContractState] or one of its descendants.
 */
public class IOUState implements LinearState, QueryableState {
    private final Party hospital;
    private final Party patient;
    private final String name;
    private final Integer age;
    private final String gender;
    private final Integer height;
    private final Integer weight;
    private final String bloodGroup;
    private final String diagnosis;
    private final String medicine;
    private final UniqueIdentifier linearId;

    /**
     * @param hospital the party issuing the IOU.
     * @param patient the party receiving and approving the IOU.
     * @param name the name of the patient.
     * @param age the age of the patient.
     * @param gender the gender of the patient.
     * @param height the height of the patient.
     * @param weight the weight of the patient.
     * @param bloodGroup the blood group of the patient.
     * @param diagnosis the diagnosis done by the doctor.
     * @param medicine the medicine suggested by the doctor.
     */
    public IOUState(Party hospital, Party patient,
                    String name,Integer age, String gender, Integer height, Integer weight,
                    String bloodGroup, String diagnosis, String medicine,
                    UniqueIdentifier linearId)
    {
        this.hospital = hospital;
        this.patient = patient;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
        this.diagnosis = diagnosis;
        this.medicine = medicine;
        this.linearId = linearId;
    }


    public Party getHospital() { return hospital; }
    public Party getPatient() { return patient; }
    public String getName() { return name; }
    public Integer getAge() { return age; }
    public String getGender() { return gender; }
    public Integer getHeight() { return height; }
    public Integer getWeight() { return weight; }
    public String getBloodGroup() { return bloodGroup; }
    public String getDiagnosis() { return diagnosis; }
    public String getMedicine() { return medicine; }

    @Override public UniqueIdentifier getLinearId() { return linearId; }
    @Override public List<AbstractParty> getParticipants() {
        return Arrays.asList(hospital, patient);
    }