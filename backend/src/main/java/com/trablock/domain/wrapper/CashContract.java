package com.trablock.domain.wrapper;

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

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * TODO Sub PJT Ⅱ 과제 3 ERC-20 Token's Wrapper Class를 생성하여 코드를 대체한다.
 */
@SuppressWarnings("rawtypes")
public class CashContract extends Contract {
	public static final String BINARY = "60806040526040805190810160405280600c81526020017f547261426c636f6b436f696e0000000000000000000000000000000000000000815250600390805190602001906200005192919062000108565b506040805190810160405280600381526020017f5442430000000000000000000000000000000000000000000000000000000000815250600490805190602001906200009f92919062000108565b506012600560006101000a81548160ff021916908360ff160217905550612710600655336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620001b7565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200014b57805160ff19168380011785556200017c565b828001600101855582156200017c579182015b828111156200017b5782518255916020019190600101906200015e565b5b5090506200018b91906200018f565b5090565b620001b491905b80821115620001b057600081600090555060010162000196565b5090565b90565b6115a980620001c76000396000f3006080604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100e0578063095ea7b31461017057806318160ddd146101d557806323b872dd14610200578063313ce5671461028557806339509351146102b657806370a082311461031b5780638620410b146103725780638da5cb5b1461039d57806395d89b41146103f4578063a457c2d714610484578063a6f2ae3a146104e9578063a9059cbb14610533578063dd62ed3e14610598578063f2fde38b1461060f575b600080fd5b3480156100ec57600080fd5b506100f5610652565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561013557808201518184015260208101905061011a565b50505050905090810190601f1680156101625780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561017c57600080fd5b506101bb600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506106f4565b604051808215151515815260200191505060405180910390f35b3480156101e157600080fd5b506101ea61070b565b6040518082815260200191505060405180910390f35b34801561020c57600080fd5b5061026b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610715565b604051808215151515815260200191505060405180910390f35b34801561029157600080fd5b5061029a6107c6565b604051808260ff1660ff16815260200191505060405180910390f35b3480156102c257600080fd5b50610301600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506107dd565b604051808215151515815260200191505060405180910390f35b34801561032757600080fd5b5061035c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610882565b6040518082815260200191505060405180910390f35b34801561037e57600080fd5b506103876108cb565b6040518082815260200191505060405180910390f35b3480156103a957600080fd5b506103b26108d1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561040057600080fd5b506104096108f6565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561044957808201518184015260208101905061042e565b50505050905090810190601f1680156104765780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561049057600080fd5b506104cf600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610998565b604051808215151515815260200191505060405180910390f35b6104f1610a3d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561053f57600080fd5b5061057e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610af1565b604051808215151515815260200191505060405180910390f35b3480156105a457600080fd5b506105f9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b08565b6040518082815260200191505060405180910390f35b34801561061b57600080fd5b50610650600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b8f565b005b606060038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106ea5780601f106106bf576101008083540402835291602001916106ea565b820191906000526020600020905b8154815290600101906020018083116106cd57829003601f168201915b5050505050905090565b6000610701338484610ce4565b6001905092915050565b6000600754905090565b6000610722848484610f65565b6107bb84336107b685600260008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461128f90919063ffffffff16565b610ce4565b600190509392505050565b6000600560009054906101000a900460ff16905090565b6000610878338461087385600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546112d990919063ffffffff16565b610ce4565b6001905092915050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60065481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b606060048054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561098e5780601f106109635761010080835404028352916020019161098e565b820191906000526020600020905b81548152906001019060200180831161097157829003601f168201915b5050505050905090565b6000610a333384610a2e85600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461128f90919063ffffffff16565b610ce4565b6001905092915050565b6000806103e834029050600081111515610abf576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f596f75206e65656420746f2073656e6420736f6d65204574686572000000000081525060200191505060405180910390fd5b610ac93382611363565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691505090565b6000610afe338484610f65565b6001905092915050565b6000600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610bea57600080fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610c2657600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151515610daf576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260248152602001807f45524332303a20617070726f76652066726f6d20746865207a65726f2061646481526020017f726573730000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515610e7a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f45524332303a20617070726f766520746f20746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b80600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925836040518082815260200191505060405180910390a3505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151515611030576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001807f45524332303a207472616e736665722066726f6d20746865207a65726f20616481526020017f647265737300000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141515156110fb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f45524332303a207472616e7366657220746f20746865207a65726f206164647281526020017f657373000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b61114d81600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461128f90919063ffffffff16565b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506111e281600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546112d990919063ffffffff16565b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040518082815260200191505060405180910390a3505050565b60006112d183836040805190810160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f7700008152506114bc565b905092915050565b6000808284019050838110151515611359576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515611408576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f45524332303a206d696e7420746f20746865207a65726f20616464726573730081525060200191505060405180910390fd5b61141d816007546112d990919063ffffffff16565b60078190555061147581600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546112d990919063ffffffff16565b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b600080848411158390151561156c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b83811015611531578082015181840152602081019050611516565b50505050905090810190601f16801561155e5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5083850390508091505093925050505600a165627a7a72305820a412ac99237bc06ff22af3373ab776ac514732a5f3419d9d2e4e3cc72e41a2830029";

	public static final String FUNC__MINT = "_mint";

	public static final String FUNC_ALLOWANCE = "allowance";

	public static final String FUNC_APPROVE = "approve";

	public static final String FUNC_BALANCEOF = "balanceOf";

	public static final String FUNC_BUY = "buy";

	public static final String FUNC_BUYPRICE = "buyPrice";

	public static final String FUNC_DECIMALS = "decimals";

	public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

	public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

	public static final String FUNC_NAME = "name";

	public static final String FUNC_OWNER = "owner";

	public static final String FUNC_SYMBOL = "symbol";

	public static final String FUNC_TOTALSUPPLY = "totalSupply";

	public static final String FUNC_TRANSFER = "transfer";

	public static final String FUNC_TRANSFERFROM = "transferFrom";

	public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

	public static final Event APPROVAL_EVENT = new Event("Approval",
			Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
			}, new TypeReference<Address>(true) {
			}, new TypeReference<Uint256>() {
			}));;

	public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
			Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
			}, new TypeReference<Address>(true) {
			}));;

	public static final Event TRANSFER_EVENT = new Event("Transfer",
			Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
			}, new TypeReference<Address>(true) {
			}, new TypeReference<Uint256>() {
			}));;

	protected CashContract(String contractAddress, Web3j web3j, Credentials credentials,
			ContractGasProvider contractGasProvider) {
		super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
	}

	protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			ContractGasProvider contractGasProvider) {
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

	public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
		return approvalEventFlowable(filter);
	}

	public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
			TransactionReceipt transactionReceipt) {
		List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT,
				transactionReceipt);
		ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(
				valueList.size());
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
				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT,
						log);
				OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
				typedResponse.log = log;
				typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
				typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
				return typedResponse;
			}
		});
	}

	public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
			DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
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

	public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
		return transferEventFlowable(filter);
	}

	public RemoteFunctionCall<TransactionReceipt> _mint(String account, BigInteger amount) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__MINT,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account),
						new org.web3j.abi.datatypes.generated.Uint256(amount)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner),
						new org.web3j.abi.datatypes.Address(160, spender)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_APPROVE,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
						new org.web3j.abi.datatypes.generated.Uint256(value)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteFunctionCall<BigInteger> balanceOf(String account) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteFunctionCall<TransactionReceipt> buy(BigInteger weiValue) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BUY,
				Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function, weiValue);
	}

	public RemoteFunctionCall<BigInteger> buyPrice() {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BUYPRICE,
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteFunctionCall<BigInteger> decimals() {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS,
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECREASEALLOWANCE,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
						new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INCREASEALLOWANCE,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
						new org.web3j.abi.datatypes.generated.Uint256(addedValue)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteFunctionCall<String> name() {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME,
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteFunctionCall<String> owner() {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteFunctionCall<String> symbol() {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL,
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteFunctionCall<BigInteger> totalSupply() {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY,
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TRANSFER,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient),
						new org.web3j.abi.datatypes.generated.Uint256(amount)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteFunctionCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TRANSFERFROM,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender),
						new org.web3j.abi.datatypes.Address(160, recipient),
						new org.web3j.abi.datatypes.generated.Uint256(amount)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
		final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TRANSFEROWNERSHIP,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public static CashContract load(String contractAddress, Web3j web3j, Credentials credentials,
			ContractGasProvider contractGasProvider) {
		return new CashContract(contractAddress, web3j, credentials, contractGasProvider);
	}

	public static CashContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			ContractGasProvider contractGasProvider) {
		return new CashContract(contractAddress, web3j, transactionManager, contractGasProvider);
	}

	public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials,
			ContractGasProvider contractGasProvider) {
		return deployRemoteCall(CashContract.class, web3j, credentials, contractGasProvider, BINARY, "");
	}

	public static RemoteCall<CashContract> deploy(Web3j web3j, TransactionManager transactionManager,
			ContractGasProvider contractGasProvider) {
		return deployRemoteCall(CashContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
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
