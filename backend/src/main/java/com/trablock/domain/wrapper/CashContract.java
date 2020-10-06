package com.trablock.domain.wrapper;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class CashContract extends Contract {
    public static final String BINARY = "60c0604052600c60808190526b2a3930a13637b1b5a1b7b4b760a11b60a09081526200002f916004919062000096565b506040805180820190915260038082526254424360e81b60209092019182526200005c9160059162000096565b506006805460ff191660121790556127106007553480156200007d57600080fd5b50600080546001600160a01b0319163317905562000132565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000d957805160ff191683800117855562000109565b8280016001018555821562000109579182015b8281111562000109578251825591602001919060010190620000ec565b50620001179291506200011b565b5090565b5b808211156200011757600081556001016200011c565b6118d180620001426000396000f3fe6080604052600436106101095760003560e01c806370a0823111610095578063a457c2d711610064578063a457c2d7146103ed578063a6f2ae3a14610426578063a9059cbb1461042e578063dd62ed3e14610467578063f2fde38b146104a257610109565b806370a08231146103545780637ecc2af4146103875780638da5cb5b146103c357806395d89b41146103d857610109565b806318160ddd116100dc57806318160ddd1461024057806323b872dd14610267578063313ce567146102aa57806339509351146102d5578063447fe2891461030e57610109565b806306fdde031461010e578063095ea7b3146101985780630a67d2c7146101e55780630c11dedd1461021a575b600080fd5b34801561011a57600080fd5b506101236104d5565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561015d578181015183820152602001610145565b50505050905090810190601f16801561018a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101a457600080fd5b506101d1600480360360408110156101bb57600080fd5b506001600160a01b03813516906020013561056b565b604080519115158252519081900360200190f35b3480156101f157600080fd5b506102186004803603602081101561020857600080fd5b50356001600160a01b0316610581565b005b6102186004803603602081101561023057600080fd5b50356001600160a01b0316610830565b34801561024c57600080fd5b506102556108e0565b60408051918252519081900360200190f35b34801561027357600080fd5b506101d16004803603606081101561028a57600080fd5b506001600160a01b038135811691602081013590911690604001356108e6565b3480156102b657600080fd5b506102bf610937565b6040805160ff9092168252519081900360200190f35b3480156102e157600080fd5b506101d1600480360360408110156102f857600080fd5b506001600160a01b038135169060200135610940565b34801561031a57600080fd5b506103386004803603602081101561033157600080fd5b5035610976565b604080516001600160a01b039092168252519081900360200190f35b34801561036057600080fd5b506102556004803603602081101561037757600080fd5b50356001600160a01b0316610991565b34801561039357600080fd5b50610338600480360360808110156103aa57600080fd5b50803590602081013590604081013590606001356109ac565b3480156103cf57600080fd5b50610338610a53565b3480156103e457600080fd5b50610123610a62565b3480156103f957600080fd5b506101d16004803603604081101561041057600080fd5b506001600160a01b038135169060200135610ac3565b610218610af9565b34801561043a57600080fd5b506101d16004803603604081101561045157600080fd5b506001600160a01b038135169060200135610b5d565b34801561047357600080fd5b506102556004803603604081101561048a57600080fd5b506001600160a01b0381358116916020013516610b6a565b3480156104ae57600080fd5b50610218600480360360208110156104c557600080fd5b50356001600160a01b0316610b95565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105615780601f1061053657610100808354040283529160200191610561565b820191906000526020600020905b81548152906001019060200180831161054457829003601f168201915b5050505050905090565b6000610578338484610c1a565b50600192915050565b6001600160a01b03808216600090815260016020526040902054166105a582610991565b816001600160a01b031663eef191cb6040518163ffffffff1660e01b815260040160206040518083038186803b1580156105de57600080fd5b505afa1580156105f2573d6000803e3d6000fd5b505050506040513d602081101561060857600080fd5b505111156107ba576060816001600160a01b031663fc9ffe026040518163ffffffff1660e01b815260040160006040518083038186803b15801561064b57600080fd5b505afa15801561065f573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052602081101561068857600080fd5b81019080805160405193929190846401000000008211156106a857600080fd5b9083019060208201858111156106bd57600080fd5b82518660208202830111640100000000821117156106da57600080fd5b82525081516020918201928201910280838360005b838110156107075781810151838201526020016106ef565b50505050905001604052505050905060005b826001600160a01b031663d6eef5be6040518163ffffffff1660e01b815260040160206040518083038186803b15801561075257600080fd5b505afa158015610766573d6000803e3d6000fd5b505050506040513d602081101561077c57600080fd5b50518110156107b3576107ab8483838151811061079557fe5b60200260200101516107a687610991565b610d06565b600101610719565b505061082c565b61082c82826001600160a01b031663565a2e2c6040518163ffffffff1660e01b815260040160206040518083038186803b1580156107f757600080fd5b505afa15801561080b573d6000803e3d6000fd5b505050506040513d602081101561082157600080fd5b50516107a685610991565b5050565b6001600160a01b0380821660009081526001602052604090205433913491168161085984610991565b11156108da5761086a838584610d06565b806001600160a01b031663ceec0e1c84846040518363ffffffff1660e01b815260040180836001600160a01b0316815260200182815260200192505050600060405180830381600087803b1580156108c157600080fd5b505af11580156108d5573d6000803e3d6000fd5b505050505b50505050565b60085490565b60006108f3848484610d06565b6001600160a01b03841660009081526003602090815260408083203380855292529091205461092d9186916109289086610e3e565b610c1a565b5060019392505050565b60065460ff1690565b3360008181526003602090815260408083206001600160a01b038716845290915281205490916105789185906109289086610e87565b6000908152600960205260409020546001600160a01b031690565b6001600160a01b031660009081526002602052604090205490565b60008085858585336040516109c090611026565b948552602085019390935260408085019290925260608401526001600160a01b039091166080830152519081900360a001906000f080158015610a07573d6000803e3d6000fd5b506001600160a01b038116600081815260016020908152604080832080546001600160a01b031990811686179091559a8352600990915290208054909816179096555093949350505050565b6000546001600160a01b031681565b60058054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105615780601f1061053657610100808354040283529160200191610561565b3360008181526003602090815260408083206001600160a01b038716845290915281205490916105789185906109289086610e3e565b6103e8340280610b50576040805162461bcd60e51b815260206004820152601b60248201527f596f75206e65656420746f2073656e6420736f6d652045746865720000000000604482015290519081900360640190fd5b610b5a3382610ee1565b50565b6000610578338484610d06565b6001600160a01b03918216600090815260036020908152604080832093909416825291909152205490565b6000546001600160a01b03163314610bac57600080fd5b6001600160a01b038116610bbf57600080fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b6001600160a01b038316610c5f5760405162461bcd60e51b81526004018080602001828103825260248152602001806118786024913960400191505060405180910390fd5b6001600160a01b038216610ca45760405162461bcd60e51b81526004018080602001828103825260228152602001806118316022913960400191505060405180910390fd5b6001600160a01b03808416600081815260036020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b6001600160a01b038316610d4b5760405162461bcd60e51b81526004018080602001828103825260258152602001806118536025913960400191505060405180910390fd5b6001600160a01b038216610d905760405162461bcd60e51b815260040180806020018281038252602381526020018061180e6023913960400191505060405180910390fd5b6001600160a01b038316600090815260026020526040902054610db39082610e3e565b6001600160a01b038085166000908152600260205260408082209390935590841681522054610de29082610e87565b6001600160a01b0380841660008181526002602090815260409182902094909455805185815290519193928716927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef92918290030190a3505050565b6000610e8083836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f770000815250610f8f565b9392505050565b600082820183811015610e80576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b6001600160a01b038216610f3c576040805162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b600854610f499082610e87565b6008556001600160a01b038216600090815260026020526040902054610f6f9082610e87565b6001600160a01b0390921660009081526002602052604090209190915550565b6000818484111561101e5760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610fe3578181015183820152602001610fcb565b50505050905090810190601f1680156110105780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b6107da806110348339019056fe608060405234801561001057600080fd5b506040516107da3803806107da833981810160405260a081101561003357600080fd5b5080516020820151604083015160608401516080909401516002849055600380546001600160a01b0319166001600160a01b038316179055600180549495939492939282919060009061008257fe5b6000918252602080832090910180546001600160a01b039485166001600160a01b0319909116179055600496909655426201518090950294909401600655600892909255604080516080810182528481528086018581526001828401818152606084018881529590961687529686905291909420935184555183850155905160029092018054915115156101000261ff001993151560ff1990931692909217929092161790556005555061069f8061013b6000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c8063a0dcf9da11610097578063d6eef5be11610066578063d6eef5be146102ea578063eef191cb146102f2578063f1068454146102fa578063fc9ffe021461030257610100565b8063a0dcf9da14610256578063a230c5241461025e578063ca55753a14610298578063ceec0e1c146102be57610100565b8063565a2e2c116100d3578063565a2e2c146102365780636284ae411461023e5780637609ea4b1461024657806391c389701461024e57610100565b806308ae4b0c146101055780632064d9ca1461015357806329dcb0cf146101f857806338af3eed14610212575b600080fd5b61012b6004803603602081101561011b57600080fd5b50356001600160a01b031661035a565b6040805194855260208501939093529015158383015215156060830152519081900360800190f35b6101f66004803603602081101561016957600080fd5b81019060208101813564010000000081111561018457600080fd5b82018360208201111561019657600080fd5b803590602001918460208302840111640100000000831117156101b857600080fd5b919080806020026020016040519081016040528093929190818152602001838360200280828437600092019190915250929550610387945050505050565b005b6102006104a4565b60408051918252519081900360200190f35b61021a6104aa565b604080516001600160a01b039092168252519081900360200190f35b61021a6104b9565b6102006104c8565b6102006104ce565b6102006104d4565b6102006104da565b6102846004803603602081101561027457600080fd5b50356001600160a01b03166104e0565b604080519115158252519081900360200190f35b610200600480360360208110156102ae57600080fd5b50356001600160a01b0316610501565b6101f6600480360360408110156102d457600080fd5b506001600160a01b03813516906020013561051f565b610200610568565b61020061056e565b610200610574565b61030a61057a565b60408051602080825283518183015283519192839290830191858101910280838360005b8381101561034657818101518382015260200161032e565b505050509050019250505060405180910390f35b60006020819052908152604090208054600182015460029092015490919060ff8082169161010090041684565b60005b81518110156104a05761039b61063d565b6040518060800160405280600554815260200160008152602001600115158152602001600015158152509050806000808585815181106103d757fe5b6020908102919091018101516001600160a01b031682528181019290925260409081016000208351815591830151600180840191909155908301516002909201805460609094015115156101000261ff001993151560ff199095169490941792909216929092179055835184908490811061044e57fe5b60209081029190910181015182546001808201855560009485529290932090920180546001600160a01b0319166001600160a01b0390931692909217909155600580548201905591909101905061038a565b5050565b60065481565b6003546001600160a01b031681565b6003546001600160a01b031690565b60085481565b60075481565b60055481565b60045481565b6001600160a01b031660009081526020819052604090206002015460ff1690565b6001600160a01b031660009081526020819052604090206001015490565b6001600160a01b03821660009081526020819052604090206001015461054590826105dc565b6001600160a01b0390921660009081526020819052604090206001019190915550565b60055490565b60045490565b60025481565b606060018054806020026020016040519081016040528092919081815260200182805480156105d257602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116105b4575b5050505050905090565b600082820183811015610636576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b60405180608001604052806000815260200160008152602001600015158152602001600015158152509056fea2646970667358221220e36d28be2aa69f044e674e62b773a2960c0f90df22cbfe74d0634e74e2c5612a64736f6c6343000702003345524332303a207472616e7366657220746f20746865207a65726f206164647265737345524332303a20617070726f766520746f20746865207a65726f206164647265737345524332303a207472616e736665722066726f6d20746865207a65726f206164647265737345524332303a20617070726f76652066726f6d20746865207a65726f2061646472657373a26469706673582212205db000bf47be05ead4f755eb982eb70de7fe2fd7a8def7df8b6eae8f90c3654164736f6c63430007020033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_CREATEPARTIES = "createParties";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_GETPARTIES = "getParties";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_WITHDRAW = "withDraw";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected CashContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CashContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(BigInteger bigInteger) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, bigInteger);
    }

    public RemoteFunctionCall<TransactionReceipt> createParties(BigInteger partyId, BigInteger partyGoal, BigInteger durationInDays, BigInteger exitFee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEPARTIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(partyId), 
                new org.web3j.abi.datatypes.generated.Uint256(partyGoal), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInDays), 
                new org.web3j.abi.datatypes.generated.Uint256(exitFee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getParties(BigInteger partyId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPARTIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(partyId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(String adr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, adr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withDraw(String adr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, adr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CashContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CashContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CashContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CashContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CashContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CashContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CashContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CashContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CashContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CashContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CashContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CashContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
