package com.example

import net.corda.core.identity.CordaX500Name
import net.corda.core.utilities.getOrThrow
import net.corda.testing.core.TestIdentity
import net.corda.testing.driver.DriverParameters
import net.corda.testing.driver.driver
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test
import kotlin.test.assertEquals

class DriverBasedTests {
    val bankA = TestIdentity(CordaX500Name("BankA", "", "GB"))
    val bankB = TestIdentity(CordaX500Name("BankB", "", "US"))

    @Test
    fun `node test`() {
        driver(DriverParameters(isDebug = true, startNodesInProcess = true)) {
            // This starts two nodes simultaneously with startNode, which returns a future that completes when the node
            // has completed startup. Then these are all resolved with getOrThrow which returns the NodeHandle list.
            val (partyAHandle, partyBHandle) = listOf(
                    startNode(providedName = bankA.name),
     