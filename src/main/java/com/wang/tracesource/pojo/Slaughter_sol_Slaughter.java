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
public class Slaughter_sol_Slaughter extends Contract {
    private static final String BINARY = "60806040523480156100115760006000fd5b505b6000600160005081909090555033600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b610067565b6106be806100766000396000f3fe60806040523480156100115760006000fd5b506004361061003b5760003560e01c806319181fc5146100415780638da5cb5b146103075761003b565b60006000fd5b61030560048036036101008110156100595760006000fd5b8101908080359060200190929190803590602001909291908035906020019064010000000081111561008b5760006000fd5b82018360208201111561009e5760006000fd5b803590602001918460018302840111640100000000831117156100c15760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290803590602001906401000000008111156101295760006000fd5b82018360208201111561013c5760006000fd5b8035906020019184600183028401116401000000008311171561015f5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505090909192909091929080359060200190929190803590602001906401000000008111156101d15760006000fd5b8201836020820111156101e45760006000fd5b803590602001918460018302840111640100000000831117156102075760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505090909192909091929080359060200190929190803590602001906401000000008111156102795760006000fd5b82018360208201111561028c5760006000fd5b803590602001918460018302840111640100000000831117156102af5760006000fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050909091929090919290505050610351565b005b61030f610572565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b60016001600082828250540192505081909090555061036e610598565b6040518060e001604052808a81526020018981526020018881526020018781526020018681526020018581526020018481526020015090508060026000506000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008b8152602001908152602001600020600050600082015181600001600050909055602082015181600101600050909055604082015181600201600050908051906020019061045f9291906105d8565b50606082015181600301600050908051906020019061047f9291906105d8565b5060808201518160040160005090905560a08201518160050160005090805190602001906104ae9291906105d8565b5060c0820151816006016000509090559050507f17fdc5482098bdc764015450f8bd8431e33983722e44376fae3f8069b09f160a89836040518083815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561052b5780820151818401525b60208101905061050f565b50505050905090810190601f1680156105585780820380516001836020036101000a031916815260200191505b50935050505060405180910390a1505b5050505050505050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6040518060e00160405280600081526020016000815260200160608152602001606081526020016000815260200160608152602001600081526020015090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061061957805160ff191683800117855561064c565b8280016001018555821561064c579182015b8281111561064b578251826000509090559160200191906001019061062b565b5b509050610659919061065d565b5090565b6106859190610667565b808211156106815760008181506000905550600101610667565b5090565b9056fea264697066735822122019786cb95485a1700272b92a8c029a2fe34741c0b6021662f6128fac15d87aba64736f6c63430006010033";

    public static final String FUNC_ADDSLAUGHTERRECORD = "addSlaughterRecord";

    public static final String FUNC_OWNER = "owner";

    public static final Event HASH_EVENT = new Event("Hash", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected Slaughter_sol_Slaughter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Slaughter_sol_Slaughter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<Slaughter_sol_Slaughter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Slaughter_sol_Slaughter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Slaughter_sol_Slaughter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Slaughter_sol_Slaughter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public RemoteCall<TransactionReceipt> addSlaughterRecord(BigInteger recordid, BigInteger cattleid, String cattlehealth, String quarantinedate, BigInteger quarantinerid, String slaughterdate, BigInteger butcherid, String recordhash) {
        final Function function = new Function(
                FUNC_ADDSLAUGHTERRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(recordid), 
                new org.web3j.abi.datatypes.generated.Uint256(cattleid), 
                new org.web3j.abi.datatypes.Utf8String(cattlehealth), 
                new org.web3j.abi.datatypes.Utf8String(quarantinedate), 
                new org.web3j.abi.datatypes.generated.Uint256(quarantinerid), 
                new org.web3j.abi.datatypes.Utf8String(slaughterdate), 
                new org.web3j.abi.datatypes.generated.Uint256(butcherid), 
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

    public static Slaughter_sol_Slaughter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Slaughter_sol_Slaughter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Slaughter_sol_Slaughter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Slaughter_sol_Slaughter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class HashEventResponse {
        public Log log;

        public BigInteger recordid;

        public String recordHash;
    }
}
