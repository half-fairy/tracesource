package com.wang.tracesource.pojo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Logistics_sol_Logistics extends Contract {
    private static final String BINARY = "60806040523480156100115760006000fd5b505b6000600160005081909090555033600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b610067565b610831806100766000396000f3fe60806040523480156100115760006000fd5b506004361061003b5760003560e01c80638da5cb5b14610041578063b6133a791461008b5761003b565b60006000fd5b610049610483565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61048160048036036101208110156100a35760006000fd5b81019080803590602001909291908035906020019092919080359060200190929190803590602001906401000000008111156100df5760006000fd5b8201836020820111156100f25760006000fd5b803590602001918460018302840111640100000000831117156101155760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509090919290909192908035906020019064010000000081111561017d5760006000fd5b8201836020820111156101905760006000fd5b803590602001918460018302840111640100000000831117156101b35760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509090919290909192908035906020019064010000000081111561021b5760006000fd5b82018360208201111561022e5760006000fd5b803590602001918460018302840111640100000000831117156102515760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156102b95760006000fd5b8201836020820111156102cc5760006000fd5b803590602001918460018302840111640100000000831117156102ef5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156103575760006000fd5b82018360208201111561036a5760006000fd5b8035906020019184600183028401116401000000008311171561038d5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156103f55760006000fd5b8201836020820111156104085760006000fd5b8035906020019184600183028401116401000000008311171561042b5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509090919290909192905050506104a9565b005b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6001600160008282825054019250508190909055506104c6610703565b6040518061010001604052808b81526020018a81526020018981526020018881526020018781526020018681526020018581526020018481526020015090508060026000506000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008c815260200190815260200160002060005060008201518160000160005090905560208201518160010160005090905560408201518160020160005090905560608201518160030160005090805190602001906105cd92919061074b565b5060808201518160040160005090805190602001906105ed92919061074b565b5060a082015181600501600050908051906020019061060d92919061074b565b5060c082015181600601600050908051906020019061062d92919061074b565b5060e082015181600701600050908051906020019061064d92919061074b565b509050507f17fdc5482098bdc764015450f8bd8431e33983722e44376fae3f8069b09f160a8a836040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156106bb5780820151818401525b60208101905061069f565b50505050905090810190601f1680156106e85780820380516001836020036101000a031916815260200191505b50935050505060405180910390a1505b505050505050505050565b60405180610100016040528060008152602001600081526020016000815260200160608152602001606081526020016060815260200160608152602001606081526020015090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061078c57805160ff19168380011785556107bf565b828001600101855582156107bf579182015b828111156107be578251826000509090559160200191906001019061079e565b5b5090506107cc91906107d0565b5090565b6107f891906107da565b808211156107f457600081815060009055506001016107da565b5090565b9056fea264697066735822122024e51b85b42ceeba13117b9f9dfa8be3971912254d16731dab4b974660a037ce64736f6c63430006010033";

    public static final String FUNC_ADDLOGISTICSRECORD = "addLogisticsRecord";

    public static final String FUNC_OWNER = "owner";

    public static final Event HASH_EVENT = new Event("Hash", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected Logistics_sol_Logistics(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Logistics_sol_Logistics(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<Logistics_sol_Logistics> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Logistics_sol_Logistics.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Logistics_sol_Logistics> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Logistics_sol_Logistics.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<HashEventResponse> getHashEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(HASH_EVENT, transactionReceipt);
        ArrayList<HashEventResponse> responses = new ArrayList<HashEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            HashEventResponse typedResponse = new HashEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.recordid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.recordHash = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<HashEventResponse> hashEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, HashEventResponse>() {
            @Override
            public HashEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(HASH_EVENT, log);
                HashEventResponse typedResponse = new HashEventResponse();
                typedResponse.log = log;
                typedResponse.recordid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.recordHash = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<HashEventResponse> hashEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(HASH_EVENT));
        return hashEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> addLogisticsRecord(BigInteger recordid, BigInteger beefid, BigInteger driverId, String driverName, String departureTime, String transitionTime, String carTemperature, String destination, String recordhash) {
        final Function function = new Function(
                FUNC_ADDLOGISTICSRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(recordid), 
                new org.web3j.abi.datatypes.generated.Uint256(beefid), 
                new org.web3j.abi.datatypes.generated.Uint256(driverId), 
                new org.web3j.abi.datatypes.Utf8String(driverName), 
                new org.web3j.abi.datatypes.Utf8String(departureTime), 
                new org.web3j.abi.datatypes.Utf8String(transitionTime), 
                new org.web3j.abi.datatypes.Utf8String(carTemperature), 
                new org.web3j.abi.datatypes.Utf8String(destination), 
                new org.web3j.abi.datatypes.Utf8String(recordhash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> owner() {
        final Function function = new Function(
                FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static Logistics_sol_Logistics load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Logistics_sol_Logistics(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Logistics_sol_Logistics load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Logistics_sol_Logistics(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class HashEventResponse {
        public Log log;

        public BigInteger recordid;

        public String recordHash;
    }
}
