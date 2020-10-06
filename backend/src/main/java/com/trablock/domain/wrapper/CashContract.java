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
    public static final String BINARY = "60c0604052600c60808190526b2a3930a13637b1b5a1b7b4b760a11b60a090815261002d9160049190610090565b506040805180820190915260038082526254424360e81b602090920191825261005891600591610090565b506006805460ff1916601217905561271060075534801561007857600080fd5b50600080546001600160a01b03191633179055610123565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100d157805160ff19168380011785556100fe565b828001600101855582156100fe579182015b828111156100fe5782518255916020019190600101906100e3565b5061010a92915061010e565b5090565b5b8082111561010a576000815560010161010f565b61176d806101326000396000f3fe6080604052600436106101095760003560e01c80638da5cb5b11610095578063a9059cbb11610064578063a9059cbb146103d5578063d8b6e79a1461040e578063dd62ed3e1461043e578063ef48eee614610479578063f2fde38b146104a957610109565b80638da5cb5b1461036a57806395d89b411461037f578063a457c2d714610394578063a6f2ae3a146103cd57610109565b8063313ce567116100dc578063313ce5671461024f578063395093511461027a578063447fe289146102b357806370a08231146102f95780637ecc2af41461032c57610109565b806306fdde031461010e578063095ea7b31461019857806318160ddd146101e557806323b872dd1461020c575b600080fd5b34801561011a57600080fd5b506101236104dc565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561015d578181015183820152602001610145565b50505050905090810190601f16801561018a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101a457600080fd5b506101d1600480360360408110156101bb57600080fd5b506001600160a01b038135169060200135610572565b604080519115158252519081900360200190f35b3480156101f157600080fd5b506101fa610588565b60408051918252519081900360200190f35b34801561021857600080fd5b506101d16004803603606081101561022f57600080fd5b506001600160a01b0381358116916020810135909116906040013561058e565b34801561025b57600080fd5b506102646105df565b6040805160ff9092168252519081900360200190f35b34801561028657600080fd5b506101d16004803603604081101561029d57600080fd5b506001600160a01b0381351690602001356105e8565b3480156102bf57600080fd5b506102dd600480360360208110156102d657600080fd5b503561061e565b604080516001600160a01b039092168252519081900360200190f35b34801561030557600080fd5b506101fa6004803603602081101561031c57600080fd5b50356001600160a01b0316610639565b34801561033857600080fd5b506103686004803603608081101561034f57600080fd5b5080359060208101359060408101359060600135610654565b005b34801561037657600080fd5b506102dd6106f5565b34801561038b57600080fd5b50610123610704565b3480156103a057600080fd5b506101d1600480360360408110156103b757600080fd5b506001600160a01b038135169060200135610765565b61036861079b565b3480156103e157600080fd5b506101d1600480360360408110156103f857600080fd5b506001600160a01b0381351690602001356107ff565b34801561041a57600080fd5b506103686004803603604081101561043157600080fd5b508035906020013561080c565b34801561044a57600080fd5b506101fa6004803603604081101561046157600080fd5b506001600160a01b03813581169160200135166108b8565b34801561048557600080fd5b506103686004803603604081101561049c57600080fd5b50803590602001356108e3565b3480156104b557600080fd5b50610368600480360360208110156104cc57600080fd5b50356001600160a01b0316610987565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105685780601f1061053d57610100808354040283529160200191610568565b820191906000526020600020905b81548152906001019060200180831161054b57829003601f168201915b5050505050905090565b600061057f338484610a0c565b50600192915050565b60085490565b600061059b848484610af8565b6001600160a01b0384166000908152600360209081526040808320338085529252909120546105d59186916105d09086610c30565b610a0c565b5060019392505050565b60065460ff1690565b3360008181526003602090815260408083206001600160a01b0387168452909152812054909161057f9185906105d09086610c79565b6000908152600960205260409020546001600160a01b031690565b6001600160a01b031660009081526002602052604090205490565b6000848484843360405161066790610e18565b948552602085019390935260408085019290925260608401526001600160a01b039091166080830152519081900360a001906000f0801580156106ae573d6000803e3d6000fd5b506001600160a01b0316600081815260016020908152604080832080546001600160a01b031990811686179091559883526009909152902080549096161790945550505050565b6000546001600160a01b031681565b60058054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105685780601f1061053d57610100808354040283529160200191610568565b3360008181526003602090815260408083206001600160a01b0387168452909152812054909161057f9185906105d09086610c30565b6103e83402806107f2576040805162461bcd60e51b815260206004820152601b60248201527f596f75206e65656420746f2073656e6420736f6d652045746865720000000000604482015290519081900360640190fd5b6107fc3382610cd3565b50565b600061057f338484610af8565b6000828152600960209081526040808320546001600160a01b039081168085526001909352922054339216610842828486610af8565b806001600160a01b031663191e2e8384866040518363ffffffff1660e01b815260040180836001600160a01b0316815260200182815260200192505050600060405180830381600087803b15801561089957600080fd5b505af11580156108ad573d6000803e3d6000fd5b505050505050505050565b6001600160a01b03918216600090815260036020908152604080832093909416825291909152205490565b6000828152600960209081526040808320546001600160a01b0390811680855260019093529220543392168361091884610639565b111561098057610929838386610af8565b806001600160a01b031663ceec0e1c84866040518363ffffffff1660e01b815260040180836001600160a01b0316815260200182815260200192505050600060405180830381600087803b15801561089957600080fd5b5050505050565b6000546001600160a01b0316331461099e57600080fd5b6001600160a01b0381166109b157600080fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b6001600160a01b038316610a515760405162461bcd60e51b81526004018080602001828103825260248152602001806117146024913960400191505060405180910390fd5b6001600160a01b038216610a965760405162461bcd60e51b81526004018080602001828103825260228152602001806116cd6022913960400191505060405180910390fd5b6001600160a01b03808416600081815260036020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b6001600160a01b038316610b3d5760405162461bcd60e51b81526004018080602001828103825260258152602001806116ef6025913960400191505060405180910390fd5b6001600160a01b038216610b825760405162461bcd60e51b81526004018080602001828103825260238152602001806116aa6023913960400191505060405180910390fd5b6001600160a01b038316600090815260026020526040902054610ba59082610c30565b6001600160a01b038085166000908152600260205260408082209390935590841681522054610bd49082610c79565b6001600160a01b0380841660008181526002602090815260409182902094909455805185815290519193928716927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef92918290030190a3505050565b6000610c7283836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f770000815250610d81565b9392505050565b600082820183811015610c72576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b6001600160a01b038216610d2e576040805162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b600854610d3b9082610c79565b6008556001600160a01b038216600090815260026020526040902054610d619082610c79565b6001600160a01b0390921660009081526002602052604090209190915550565b60008184841115610e105760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610dd5578181015183820152602001610dbd565b50505050905090810190601f168015610e025780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b61088480610e268339019056fe608060405234801561001057600080fd5b50604051610884380380610884833981810160405260a081101561003357600080fd5b508051602080830151604080850151606080870151608097880151600397909755600480546001600160a01b039098166001600160a01b0319988916811790915560028054600181810183557f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace9091018054909a1683179099556005969096554262015180909402939093016007556009558251968701835260008088528786018181528885018881529289018281529382529587905292909220955186559251858501555193018054915115156101000261ff001994151560ff19909316929092179390931617909155600655610754806101306000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063a0dcf9da116100a2578063ceec0e1c11610071578063ceec0e1c14610340578063d6eef5be1461036c578063eef191cb14610374578063f10684541461037c578063fc9ffe021461038457610116565b8063a0dcf9da14610298578063a230c524146102a0578063c430d861146102da578063ca55753a1461031a57610116565b806338af3eed116100e957806338af3eed14610254578063565a2e2c146102785780636284ae41146102805780637609ea4b1461028857806391c389701461029057610116565b806308ae4b0c1461011b578063191e2e83146101695780632064d9ca1461019757806329dcb0cf1461023a575b600080fd5b6101416004803603602081101561013157600080fd5b50356001600160a01b03166103dc565b6040805194855260208501939093529015158383015215156060830152519081900360800190f35b6101956004803603604081101561017f57600080fd5b506001600160a01b038135169060200135610408565b005b610195600480360360208110156101ad57600080fd5b8101906020810181356401000000008111156101c857600080fd5b8201836020820111156101da57600080fd5b803590602001918460208302840111640100000000831117156101fc57600080fd5b919080806020026020016040519081016040528093929190818152602001838360200280828437600092019190915250929550610453945050505050565b61024261055d565b60408051918252519081900360200190f35b61025c610563565b604080516001600160a01b039092168252519081900360200190f35b61025c610572565b610242610581565b610242610587565b61024261058d565b610242610593565b6102c6600480360360208110156102b657600080fd5b50356001600160a01b0316610599565b604080519115158252519081900360200190f35b6102f7600480360360208110156102f057600080fd5b50356105ba565b604080516001600160a01b03909316835260208301919091528051918290030190f35b6102426004803603602081101561033057600080fd5b50356001600160a01b03166105df565b6101956004803603604081101561035657600080fd5b506001600160a01b0381351690602001356105fe565b610242610649565b61024261064f565b610242610655565b61038c61065b565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156103c85781810151838201526020016103b0565b505050509050019250505060405180910390f35b600160208190526000918252604090912080549181015460029091015460ff8082169161010090041684565b6040805180820182526001600160a01b03938416815260208082019384524260009081529081905291909120905181546001600160a01b031916931692909217825551600190910155565b60005b8151811015610559576040518060800160405280600654815260200160008152602001600115158152602001600015158152506001600084848151811061049957fe5b6020908102919091018101516001600160a01b03168252818101929092526040908101600020835181559183015160018301558201516002918201805460609094015115156101000261ff001992151560ff19909516949094179190911692909217909155825183908390811061050c57fe5b60209081029190910181015182546001808201855560009485529290932090920180546001600160a01b0319166001600160a01b0390931692909217909155600680548201905501610456565b5050565b60075481565b6004546001600160a01b031681565b6004546001600160a01b031690565b60095481565b60085481565b60065481565b60055481565b6001600160a01b031660009081526001602052604090206002015460ff1690565b600060208190529081526040902080546001909101546001600160a01b039091169082565b6001600160a01b03166000908152600160208190526040909120015490565b6001600160a01b0382166000908152600160208190526040909120015461062590826106bd565b6001600160a01b039092166000908152600160208190526040909120019190915550565b60065490565b60055490565b60035481565b606060028054806020026020016040519081016040528092919081815260200182805480156106b357602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610695575b5050505050905090565b600082820183811015610717576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b939250505056fea264697066735822122088568c76bf62be4f0bc43def652f9946bf9e965f6c6fdc5dc60d9b59b2270ffe64736f6c6343000702003345524332303a207472616e7366657220746f20746865207a65726f206164647265737345524332303a20617070726f766520746f20746865207a65726f206164647265737345524332303a207472616e736665722066726f6d20746865207a65726f206164647265737345524332303a20617070726f76652066726f6d20746865207a65726f2061646472657373a2646970667358221220efac16e97491f37b47cc814dde2c32dcd4fa072f289ade0c47cdb5a3bcaccbfa64736f6c63430007020033";

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

    public RemoteFunctionCall<TransactionReceipt> buy(BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, value);
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

    public RemoteFunctionCall<TransactionReceipt> pay(BigInteger pid, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(pid), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
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

    public RemoteFunctionCall<TransactionReceipt> withDraw(BigInteger pid, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(pid), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
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
