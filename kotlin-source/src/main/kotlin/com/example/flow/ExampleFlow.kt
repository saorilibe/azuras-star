package com.example.flow

import co.paralleluniverse.fibers.Suspendable
import com.example.contract.IOUContract
import com.example.contract.IOUContract.Companion.IOU_CONTRACT_ID
import com.example.flow.ExampleFlow.Acceptor
import com.example.flow.ExampleFlow.Initiator
import com.example.state.IOUState
import net.corda.core.contracts.Command
import net.corda.core.contracts.requireThat
import net.corda.core.flows.*
import net.corda.core.identity.Party
import net.corda.core.transactions.SignedTransaction
import net.corda.core.transactions.TransactionBuilder
import net.corda.core.utilities.ProgressTracker
import net.corda.core.utilities.ProgressTracker.Step

/**
 * This flow allows two parties (the [Initiator] and the [Acceptor]) to come to an agreement about the IOU encapsul