package com.wang.tracesource.pojo;

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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Farm_sol_farm extends Contract {
    private static final String BINARY = "60806040523480156100115760006000fd5b505b600060016000508190909055506000600260005081909090555033600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b610074565b610c05806100836000396000f3fe60806040523480156100115760006000fd5b506004361061005c5760003560e01c80636b726805146100625780638345b6a8146100805780638da5cb5b1461009e578063b0bc4862146100e8578063f617122d146103ae5761005c565b60006000fd5b61006a61065f565b6040518082815260200191505060405180910390f35b610088610668565b6040518082815260200191505060405180910390f35b6100a6610671565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6103ac60048036036101008110156101005760006000fd5b8101908080359060200190929190803590602001909291908035906020019092919080359060200190929190803590602001906401000000008111156101465760006000fd5b8201836020820111156101595760006000fd5b8035906020019184600183028401116401000000008311171561017c5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156101e45760006000fd5b8201836020820111156101f75760006000fd5b8035906020019184600183028401116401000000008311171561021a5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156102825760006000fd5b8201836020820111156102955760006000fd5b803590602001918460018302840111640100000000831117156102b85760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156103205760006000fd5b8201836020820111156103335760006000fd5b803590602001918460018302840111640100000000831117156103565760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290505050610697565b005b61065d600480360360c08110156103c55760006000fd5b810190808035906020019092919080359060200190929190803590602001906401000000008111156103f75760006000fd5b82018360208201111561040a5760006000fd5b8035906020019184600183028401116401000000008311171561042d5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156104955760006000fd5b8201836020820111156104a85760006000fd5b803590602001918460018302840111640100000000831117156104cb5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156105335760006000fd5b8201836020820111156105465760006000fd5b803590602001918460018302840111640100000000831117156105695760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156105d15760006000fd5b8201836020820111156105e45760006000fd5b803590602001918460018302840111640100000000831117156106075760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509090919290909192905050506108b8565b005b60026000505481565b60016000505481565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6001600260008282825054019250508190909055506106b4610aad565b6040518060e001604052808a81526020018981526020018881526020018781526020018681526020018581526020018481526020015090508060036000506000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008b815260200190815260200160002060005060008201518160000160005090905560208201518160010160005090905560408201518160020160005090905560608201518160030160005090905560808201518160040160005090805190602001906107c3929190610aed565b5060a08201518160050160005090805190602001906107e3929190610aed565b5060c0820151816006016000509080519060200190610803929190610aed565b509050507f17fdc5482098bdc764015450f8bd8431e33983722e44376fae3f8069b09f160a89836040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156108715780820151818401525b602081019050610855565b50505050905090810190601f16801561089e5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a1505b5050505050505050565b6001600160008282825054019250508190909055506108d5610b72565b6040518060a001604052808881526020018781526020018681526020018581526020018481526020015090508060046000506000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050600089815260200190815260200160002060005060008201518160000160005090905560208201518160010160005090905560408201518160020160005090805190602001906109ba929190610aed565b5060608201518160030160005090805190602001906109da929190610aed565b5060808201518160040160005090805190602001906109fa929190610aed565b509050507f17fdc5482098bdc764015450f8bd8431e33983722e44376fae3f8069b09f160a87836040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610a685780820151818401525b602081019050610a4c565b50505050905090810190601f168015610a955780820380516001836020036101000a031916815260200191505b50935050505060405180910390a1505b505050505050565b6040518060e00160405280600081526020016000815260200160008152602001600081526020016060815260200160608152602001606081526020015090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b2e57805160ff1916838001178555610b61565b82800160010185558215610b61579182015b82811115610b605782518260005090905591602001919060010190610b40565b5b509050610b6e9190610ba4565b5090565b6040518060a0016040528060008152602001600081526020016060815260200160608152602001606081526020015090565b610bcc9190610bae565b80821115610bc85760008181506000905550600101610bae565b5090565b9056fea26469706673582212201f6067b2cc8df3f02ce184a0f558d72c3ea3298e15233e574b775a3a7f99469b64736f6c63430006010033";

    public static final String FUNC_ADDCATTLE = "addCattle";

    public static final String FUNC_ADDFEEDRECORD = "addFeedRecord";

    public static final String FUNC_CATTLEINDEX = "cattleIndex";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RECORDINDEX = "recordIndex";

    public static final Event HASH_EVENT = new Event("Hash", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected Farm_sol_farm(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Farm_sol_farm(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<Farm_sol_farm> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Farm_sol_farm.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Farm_sol_farm> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Farm_sol_farm.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public RemoteCall<TransactionReceipt> addCattle(BigInteger cattleid, BigInteger cattleoperator, String cattletype, String cattlegender, String cattledate, String cattlehash) {
        final Function function = new Function(
                FUNC_ADDCATTLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(cattleid), 
                new org.web3j.abi.datatypes.generated.Uint256(cattleoperator), 
                new org.web3j.abi.datatypes.Utf8String(cattletype), 
                new org.web3j.abi.datatypes.Utf8String(cattlegender), 
                new org.web3j.abi.datatypes.Utf8String(cattledate), 
                new org.web3j.abi.datatypes.Utf8String(cattlehash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addFeedRecord(BigInteger farmid, BigInteger cattleid, BigInteger plantid, BigInteger employeeid, String feedmed, String operaterdate, String foodtype, String recordhash) {
        final Function function = new Function(
                FUNC_ADDFEEDRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(farmid), 
                new org.web3j.abi.datatypes.generated.Uint256(cattleid), 
                new org.web3j.abi.datatypes.generated.Uint256(plantid), 
                new org.web3j.abi.datatypes.generated.Uint256(employeeid), 
                new org.web3j.abi.datatypes.Utf8String(feedmed), 
                new org.web3j.abi.datatypes.Utf8String(operaterdate), 
                new org.web3j.abi.datatypes.Utf8String(foodtype), 
                new org.web3j.abi.datatypes.Utf8String(recordhash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cattleIndex() {
        final Function function = new Function(
                FUNC_CATTLEINDEX, 
                Arrays.<Type>asList(), 
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

    public RemoteCall<TransactionReceipt> recordIndex() {
        final Function function = new Function(
                FUNC_RECORDINDEX, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static Farm_sol_farm load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Farm_sol_farm(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Farm_sol_farm load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Farm_sol_farm(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class HashEventResponse {
        public Log log;

        public BigInteger recordid;

        public String recordHash;
    }
}
