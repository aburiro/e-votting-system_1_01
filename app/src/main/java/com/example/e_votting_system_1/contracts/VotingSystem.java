package com.example.e_votting_system_1.contracts;


import org.web3j.tx.Contract;
import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class VotingSystem extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610f65806100606000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c806359f784681161006657806359f784681461015d57806398c0793814610167578063a3ec138d14610185578063c94ca9e3146101b6578063f851a440146101d45761009e565b80630121b93f146100a35780632d35a8a2146100bf5780633477ee2e146100dd57806335b8e8201461010f578063462e91ec14610141575b600080fd5b6100bd60048036038101906100b891906109f4565b6101f2565b005b6100c761045a565b6040516100d49190610c88565b60405180910390f35b6100f760048036038101906100f291906109f4565b610460565b60405161010693929190610ca3565b60405180910390f35b610129600480360381019061012491906109f4565b610512565b60405161013893929190610ca3565b60405180910390f35b61015b600480360381019061015691906109b3565b6105f5565b005b610165610705565b005b61016f6107e7565b60405161017c9190610c88565b60405180910390f35b61019f600480360381019061019a919061098a565b6107ed565b6040516101ad929190610bdf565b60405180910390f35b6101be61081e565b6040516101cb9190610bc4565b60405180910390f35b6101dc610831565b6040516101e99190610ba9565b60405180910390f35b600560009054906101000a900460ff1615610242576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161023990610c08565b60405180910390fd5b600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16156102d2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102c990610c68565b60405180910390fd5b6000811180156102e457506003548111155b610323576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161031a90610c28565b60405180910390fd5b6001600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548160ff02191690831515021790555080600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055506001600082815260200190815260200160002060020160008154809291906103ec90610e1a565b91905055506004600081548092919061040490610e1a565b91905055503373ffffffffffffffffffffffffffffffffffffffff167fa36cc2bebb74db33e9f88110a07ef56e1b31b24b4c4f51b54b1664266e29f45b8260405161044f9190610c88565b60405180910390a250565b60035481565b600160205280600052604060002060009150905080600001549080600101805461048990610de8565b80601f01602080910402602001604051908101604052809291908181526020018280546104b590610de8565b80156105025780601f106104d757610100808354040283529160200191610502565b820191906000526020600020905b8154815290600101906020018083116104e557829003601f168201915b5050505050908060020154905083565b600060606000600160008581526020019081526020016000206000015460016000868152602001908152602001600020600101600160008781526020019081526020016000206002015481805461056890610de8565b80601f016020809104026020016040519081016040528092919081815260200182805461059490610de8565b80156105e15780601f106105b6576101008083540402835291602001916105e1565b820191906000526020600020905b8154815290600101906020018083116105c457829003601f168201915b505050505091509250925092509193909250565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610683576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161067a90610c48565b60405180910390fd5b6003600081548092919061069690610e1a565b91905055506040518060600160405280600354815260200182815260200160008152506001600060035481526020019081526020016000206000820151816000015560208201518160010190805190602001906106f4929190610855565b506040820151816002015590505050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610793576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161078a90610c48565b60405180910390fd5b6001600560006101000a81548160ff0219169083151502179055507f562cd572b2ba3b666cd989fc4ca98419ec81e75f04528df9d9d76fdb7807ee71426040516107dd9190610c88565b60405180910390a1565b60045481565b60026020528060005260406000206000915090508060000160009054906101000a900460ff16908060010154905082565b600560009054906101000a900460ff1681565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b82805461086190610de8565b90600052602060002090601f01602090048101928261088357600085556108ca565b82601f1061089c57805160ff19168380011785556108ca565b828001600101855582156108ca579182015b828111156108c95782518255916020019190600101906108ae565b5b5090506108d791906108db565b5090565b5b808211156108f45760008160009055506001016108dc565b5090565b600061090b61090684610d12565b610ce1565b90508281526020810184848401111561092357600080fd5b61092e848285610da6565b509392505050565b60008135905061094581610f01565b92915050565b600082601f83011261095c57600080fd5b813561096c8482602086016108f8565b91505092915050565b60008135905061098481610f18565b92915050565b60006020828403121561099c57600080fd5b60006109aa84828501610936565b91505092915050565b6000602082840312156109c557600080fd5b600082013567ffffffffffffffff8111156109df57600080fd5b6109eb8482850161094b565b91505092915050565b600060208284031215610a0657600080fd5b6000610a1484828501610975565b91505092915050565b610a2681610d5e565b82525050565b610a3581610d70565b82525050565b6000610a4682610d42565b610a508185610d4d565b9350610a60818560208601610db5565b610a6981610ef0565b840191505092915050565b6000610a81601283610d4d565b91507f456c656374696f6e2068617320656e64656400000000000000000000000000006000830152602082019050919050565b6000610ac1601183610d4d565b91507f496e76616c69642063616e6469646174650000000000000000000000000000006000830152602082019050919050565b6000610b01602283610d4d565b91507f4f6e6c792061646d696e2063616e20706572666f726d2074686973206163746960008301527f6f6e0000000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000610b67601683610d4d565b91507f596f75206861766520616c726561647920766f746564000000000000000000006000830152602082019050919050565b610ba381610d9c565b82525050565b6000602082019050610bbe6000830184610a1d565b92915050565b6000602082019050610bd96000830184610a2c565b92915050565b6000604082019050610bf46000830185610a2c565b610c016020830184610b9a565b9392505050565b60006020820190508181036000830152610c2181610a74565b9050919050565b60006020820190508181036000830152610c4181610ab4565b9050919050565b60006020820190508181036000830152610c6181610af4565b9050919050565b60006020820190508181036000830152610c8181610b5a565b9050919050565b6000602082019050610c9d6000830184610b9a565b92915050565b6000606082019050610cb86000830186610b9a565b8181036020830152610cca8185610a3b565b9050610cd96040830184610b9a565b949350505050565b6000604051905081810181811067ffffffffffffffff82111715610d0857610d07610ec1565b5b8060405250919050565b600067ffffffffffffffff821115610d2d57610d2c610ec1565b5b601f19601f8301169050602081019050919050565b600081519050919050565b600082825260208201905092915050565b6000610d6982610d7c565b9050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015610dd3578082015181840152602081019050610db8565b83811115610de2576000848401525b50505050565b60006002820490506001821680610e0057607f821691505b60208210811415610e1457610e13610e92565b5b50919050565b6000610e2582610d9c565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415610e5857610e57610e63565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000601f19601f8301169050919050565b610f0a81610d5e565b8114610f1557600080fd5b50565b610f2181610d9c565b8114610f2c57600080fd5b5056fea264697066735822122067596d865227c7bb44c77800120592b7bb5d4a12100b4eaccbf311f1c152a7c064736f6c63430008000033\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_ELECTIONENDED = "electionEnded";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_VOTERSCOUNT = "votersCount";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_ENDELECTION = "endElection";

    public static final String FUNC_GETCANDIDATE = "getCandidate";

    public static final Event ELECTIONENDED_EVENT = new Event("ElectionEnded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event VOTECAST_EVENT = new Event("VoteCast", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected VotingSystem(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VotingSystem(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VotingSystem(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VotingSystem(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ElectionEndedEventResponse> getElectionEndedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ELECTIONENDED_EVENT, transactionReceipt);
        ArrayList<ElectionEndedEventResponse> responses = new ArrayList<ElectionEndedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ElectionEndedEventResponse typedResponse = new ElectionEndedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ElectionEndedEventResponse getElectionEndedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ELECTIONENDED_EVENT, log);
        ElectionEndedEventResponse typedResponse = new ElectionEndedEventResponse();
        typedResponse.log = log;
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ElectionEndedEventResponse> electionEndedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getElectionEndedEventFromLog(log));
    }

    public Flowable<ElectionEndedEventResponse> electionEndedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ELECTIONENDED_EVENT));
        return electionEndedEventFlowable(filter);
    }

    public static List<VoteCastEventResponse> getVoteCastEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTECAST_EVENT, transactionReceipt);
        ArrayList<VoteCastEventResponse> responses = new ArrayList<VoteCastEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoteCastEventResponse typedResponse = new VoteCastEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VoteCastEventResponse getVoteCastEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTECAST_EVENT, log);
        VoteCastEventResponse typedResponse = new VoteCastEventResponse();
        typedResponse.log = log;
        typedResponse.voter = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.candidateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<VoteCastEventResponse> voteCastEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVoteCastEventFromLog(log));
    }

    public Flowable<VoteCastEventResponse> voteCastEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTECAST_EVENT));
        return voteCastEventFlowable(filter);
    }

    public RemoteFunctionCall<String> admin() {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>> candidates(
            BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> candidatesCount() {
        final Function function = new Function(FUNC_CANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> electionEnded() {
        final Function function = new Function(FUNC_ELECTIONENDED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple2<Boolean, BigInteger>> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<Boolean, BigInteger>>(function,
                new Callable<Tuple2<Boolean, BigInteger>>() {
                    @Override
                    public Tuple2<Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Boolean, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> votersCount() {
        final Function function = new Function(FUNC_VOTERSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String _name) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _candidateId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> endElection() {
        final Function function = new Function(
                FUNC_ENDELECTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>> getCandidate(
            BigInteger _candidateId) {
        final Function function = new Function(FUNC_GETCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    @Deprecated
    public static VotingSystem load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new VotingSystem(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VotingSystem load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotingSystem(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VotingSystem load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new VotingSystem(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VotingSystem load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VotingSystem(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VotingSystem> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VotingSystem.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<VotingSystem> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VotingSystem.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<VotingSystem> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VotingSystem.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<VotingSystem> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VotingSystem.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

   

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ElectionEndedEventResponse extends BaseEventResponse {
        public BigInteger timestamp;
    }

    public static class VoteCastEventResponse extends BaseEventResponse {
        public String voter;

        public BigInteger candidateId;
    }
}
