package com.trablock.domain.wrapper;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
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
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class PartyContract extends Contract {
    public static final String BINARY = "608060405260008055600060015534801561001957600080fd5b50610b0d806100296000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c80637bd35af8116100715780637bd35af814610218578063c4ee2a1714610220578063da0225db14610260578063dc2b12151461028c578063eed02e4b146102b8578063fa983998146102d7576100a9565b806301d3be05146100ae57806329e27dc7146100c8578063415c2bef146100ee57806370651a18146100f65780637b08b1b01461015e575b600080fd5b6100b661041c565b60408051918252519081900360200190f35b6100b6600480360360208110156100de57600080fd5b50356001600160a01b0316610422565b6100b661043d565b6101136004803603602081101561010c57600080fd5b5035610443565b604080516001600160a01b0390991689526020890197909752878701959095526060870193909352608086019190915260a085015260c084015260e083015251908190036101000190f35b61017b6004803603602081101561017457600080fd5b50356104a0565b60405180868152602001856001600160a01b0316815260200184815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156101d95781810151838201526020016101c1565b50505050905090810190601f1680156102065780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b6100b6610578565b61024c6004803603604081101561023657600080fd5b506001600160a01b03813516906020013561057e565b604080519115158252519081900360200190f35b6100b66004803603604081101561027657600080fd5b506001600160a01b03813516906020013561059e565b61024c600480360360408110156102a257600080fd5b506001600160a01b0381351690602001356105cc565b6102d5600480360360208110156102ce57600080fd5b50356105ec565b005b6100b6600480360360c08110156102ed57600080fd5b81359160208101359160408201359160608101359181019060a08101608082013564010000000081111561032057600080fd5b82018360208201111561033257600080fd5b8035906020019184600183028401116401000000008311171561035457600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092959493602081019350359150506401000000008111156103a757600080fd5b8201836020820111156103b957600080fd5b803590602001918460018302840111640100000000831117156103db57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610699945050505050565b60005481565b6001600160a01b031660009081526006602052604090205490565b60005490565b6002818154811061045057fe5b6000918252602090912060099091020180546001820154600283015460038401546004850154600586015460068701546007909701546001600160a01b0390961697509395929491939092909188565b600381815481106104ad57fe5b600091825260209182902060059190910201805460018083015460028085015460038601546004870180546040805161010098831615989098026000190190911694909404601f81018a90048a0287018a019094528386529598506001600160a01b039093169690959294929392919083018282801561056e5780601f106105435761010080835404028352916020019161056e565b820191906000526020600020905b81548152906001019060200180831161055157829003601f168201915b5050505050905085565b60015481565b600560209081526000928352604080842090915290825290205460ff1681565b600660205281600052604060002081815481106105b757fe5b90600052602060002001600091509150505481565b600460209081526000928352604080842090915290825290205460ff1681565b80600054101580156105fc575060015b61060557600080fd5b33600090815260056020908152604080832084845290915290205460ff161561062d57600080fd5b6002818154811061063a57fe5b60009182526020808320600860099093020191909101805460018101825590835291200180546001600160a01b03191633179055600280548290811061067c57fe5b600091825260209091206002600990920201018054600101905550565b60006106a36109fe565b6000548160200181815250503381600001906001600160a01b031690816001600160a01b03168152505060018160400181815250508781606001818152505086816080018181525050858160a001818152505060008160c0018181525050848160e00181815250506000547fc0868b320b548a68fac5493579398770c7bb7bcb88795d87b555dd41fdca4fa085858a8a60008b8f60405180806020018060200188815260200187815260200186815260200185815260200184815260200183810383528a818151815260200191508051906020019080838360005b8381101561079657818101518382015260200161077e565b50505050905090810190601f1680156107c35780820380516001836020036101000a031916815260200191505b5083810382528951815289516020918201918b019080838360005b838110156107f65781810151838201526020016107de565b50505050905090810190601f1680156108235780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390a2600080546001908101825560028054918201815590915281517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace600990920291820180546001600160a01b0319166001600160a01b039092169190911781556020808401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5acf84015560408401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad084015560608401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad184015560808401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad284015560a08401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad384015560c08401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad484015560e08401517f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad5840155610100840151805185946109f0937f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ad6909101920190610a53565b505050509695505050505050565b60405180610120016040528060006001600160a01b0316815260200160008152602001600081526020016000815260200160008152602001600081526020016000815260200160008152602001606081525090565b828054828255906000526020600020908101928215610aa8579160200282015b82811115610aa857825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610a73565b50610ab4929150610ab8565b5090565b5b80821115610ab45780546001600160a01b0319168155600101610ab956fea2646970667358221220aa44d8f7e48d5b41d003525f0dddde3d78dcdcca79473b01045964d0c3134e0a64736f6c63430007020033";

    public static final String FUNC__TOTALPARTIES = "_totalParties";

    public static final String FUNC_BELONGSTO = "belongsTo";

    public static final String FUNC_BELONGSTOORNOT = "belongsToOrNot";

    public static final String FUNC_CREATEPARTY = "createParty";

    public static final String FUNC_GETNOOFTOTALGROUPSOF = "getNoOfTotalGroupsOf";

    public static final String FUNC_JOINGROUP = "joinGroup";

    public static final String FUNC_PARTIES = "parties";

    public static final String FUNC_TOSIGNORNOT = "toSignOrNot";

    public static final String FUNC_TOTALEXPENSES = "totalExpenses";

    public static final String FUNC_TOTALPARTIES = "totalParties";

    public static final String FUNC_UNVERIFIEDEXPENSES = "unverifiedExpenses";

    public static final Event ADDEXPENSE_EVENT = new Event("AddExpense", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event CREATEPARTY_EVENT = new Event("CreateParty", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VERIFIEDEXP_EVENT = new Event("VerifiedExp", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected PartyContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PartyContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PartyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PartyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AddExpenseEventResponse> getAddExpenseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDEXPENSE_EVENT, transactionReceipt);
        ArrayList<AddExpenseEventResponse> responses = new ArrayList<AddExpenseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddExpenseEventResponse typedResponse = new AddExpenseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.expId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.grpId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cost = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddExpenseEventResponse> addExpenseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddExpenseEventResponse>() {
            @Override
            public AddExpenseEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDEXPENSE_EVENT, log);
                AddExpenseEventResponse typedResponse = new AddExpenseEventResponse();
                typedResponse.log = log;
                typedResponse.expId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.grpId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cost = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddExpenseEventResponse> addExpenseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDEXPENSE_EVENT));
        return addExpenseEventFlowable(filter);
    }

    public List<CreatePartyEventResponse> getCreatePartyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEPARTY_EVENT, transactionReceipt);
        ArrayList<CreatePartyEventResponse> responses = new ArrayList<CreatePartyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreatePartyEventResponse typedResponse = new CreatePartyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.grpId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.venue = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.payCycle = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.target = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.partyBalance = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.exitFee = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.travelDate = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreatePartyEventResponse> createPartyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CreatePartyEventResponse>() {
            @Override
            public CreatePartyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATEPARTY_EVENT, log);
                CreatePartyEventResponse typedResponse = new CreatePartyEventResponse();
                typedResponse.log = log;
                typedResponse.grpId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.venue = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.payCycle = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.target = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.partyBalance = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.exitFee = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.travelDate = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreatePartyEventResponse> createPartyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEPARTY_EVENT));
        return createPartyEventFlowable(filter);
    }

    public List<VerifiedExpEventResponse> getVerifiedExpEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VERIFIEDEXP_EVENT, transactionReceipt);
        ArrayList<VerifiedExpEventResponse> responses = new ArrayList<VerifiedExpEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VerifiedExpEventResponse typedResponse = new VerifiedExpEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.verifier = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.expId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.grpId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VerifiedExpEventResponse> verifiedExpEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VerifiedExpEventResponse>() {
            @Override
            public VerifiedExpEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VERIFIEDEXP_EVENT, log);
                VerifiedExpEventResponse typedResponse = new VerifiedExpEventResponse();
                typedResponse.log = log;
                typedResponse.verifier = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.expId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.grpId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VerifiedExpEventResponse> verifiedExpEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VERIFIEDEXP_EVENT));
        return verifiedExpEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> _totalParties() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__TOTALPARTIES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> belongsTo(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BELONGSTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> belongsToOrNot(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BELONGSTOORNOT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> createParty(BigInteger _travelDate, BigInteger _payCycle, BigInteger _target, BigInteger _exitFee, String _name, String _venue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEPARTY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_travelDate), 
                new org.web3j.abi.datatypes.generated.Uint256(_payCycle), 
                new org.web3j.abi.datatypes.generated.Uint256(_target), 
                new org.web3j.abi.datatypes.generated.Uint256(_exitFee), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_venue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getNoOfTotalGroupsOf(String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNOOFTOTALGROUPSOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> joinGroup(BigInteger _partyId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_JOINGROUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_partyId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple8<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> parties(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PARTIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple8<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple8<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple8<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> toSignOrNot(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOSIGNORNOT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> totalExpenses() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALEXPENSES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalParties() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALPARTIES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple5<BigInteger, String, BigInteger, BigInteger, String>> unverifiedExpenses(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_UNVERIFIEDEXPENSES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<BigInteger, String, BigInteger, BigInteger, String>>(function,
                new Callable<Tuple5<BigInteger, String, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple5<BigInteger, String, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, String, BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    @Deprecated
    public static PartyContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PartyContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PartyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PartyContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PartyContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PartyContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PartyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PartyContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PartyContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PartyContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PartyContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PartyContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<PartyContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PartyContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PartyContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PartyContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AddExpenseEventResponse extends BaseEventResponse {
        public BigInteger expId;

        public BigInteger grpId;

        public String to;

        public String name;

        public BigInteger cost;
    }

    public static class CreatePartyEventResponse extends BaseEventResponse {
        public BigInteger grpId;

        public String name;

        public String venue;

        public BigInteger payCycle;

        public BigInteger target;

        public BigInteger partyBalance;

        public BigInteger exitFee;

        public BigInteger travelDate;
    }

    public static class VerifiedExpEventResponse extends BaseEventResponse {
        public String verifier;

        public BigInteger expId;

        public BigInteger grpId;
    }
}
