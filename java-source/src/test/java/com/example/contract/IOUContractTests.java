
package com.example.contract;

import com.example.state.IOUState;
import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.CordaX500Name;
import net.corda.testing.core.TestIdentity;
import net.corda.testing.node.MockServices;
import org.junit.Test;

import static com.example.contract.IssueContract.IOU_CONTRACT_ID;
import static net.corda.testing.node.NodeTestUtils.ledger;

public class IOUContractTests {
    static private final MockServices ledgerServices = new MockServices();
    static private TestIdentity megaCorp = new TestIdentity(new CordaX500Name("MegaCorp", "London", "GB"));
    static private TestIdentity miniCorp = new TestIdentity(new CordaX500Name("MiniCorp", "London", "GB"));
//    static private int iouValue = 1;
    static private String iouName = "F";
