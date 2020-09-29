package com.trablock.domain.wrapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
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
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import io.reactivex.Flowable;

/**
 * TODO Sub PJT Ⅱ 과제 3 ERC-20 Token's Wrapper Class를 생성하여 코드를 대체한다.
 */

public class CashContract extends Contract {
	
	private static final String BINARY = "60806040526040805190810160405280600c81526020017f547261426c636f6b436f696e0000000000000000000000000000000000000000815250600390805190602001906200005192919062000108565b506040805190810160405280600381526020017f5442430000000000000000000000000000000000000000000000000000000000815250600490805190602001906200009f92919062000108565b506012600560006101000a81548160ff021916908360ff160217905550612710600655336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620001b7565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200014b57805160ff19168380011785556200017c565b828001600101855582156200017c579182015b828111156200017b5782518255916020019190600101906200015e565b5b5090506200018b91906200018f565b5090565b620001b491905b80821115620001b057600081600090555060010162000196565b5090565b90565b61173a80620001c76000396000f3006080604052600436106100e6576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100eb578063095ea7b31461017b57806318160ddd146101e057806323b872dd1461020b578063313ce5671461029057806339509351146102c15780634e6ec2471461032657806370a08231146103735780638620410b146103ca5780638da5cb5b146103f557806395d89b411461044c578063a457c2d7146104dc578063a6f2ae3a14610541578063a9059cbb1461058b578063dd62ed3e146105f0578063f2fde38b14610667575b600080fd5b3480156100f757600080fd5b506101006106aa565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610140578082015181840152602081019050610125565b50505050905090810190601f16801561016d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561018757600080fd5b506101c6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061074c565b604051808215151515815260200191505060405180910390f35b3480156101ec57600080fd5b506101f5610763565b6040518082815260200191505060405180910390f35b34801561021757600080fd5b50610276600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061076d565b604051808215151515815260200191505060405180910390f35b34801561029c57600080fd5b506102a561081e565b604051808260ff1660ff16815260200191505060405180910390f35b3480156102cd57600080fd5b5061030c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610835565b604051808215151515815260200191505060405180910390f35b34801561033257600080fd5b50610371600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506108da565b005b34801561037f57600080fd5b506103b4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a99565b6040518082815260200191505060405180910390f35b3480156103d657600080fd5b506103df610ae2565b6040518082815260200191505060405180910390f35b34801561040157600080fd5b5061040a610ae8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561045857600080fd5b50610461610b0d565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156104a1578082015181840152602081019050610486565b50505050905090810190601f1680156104ce5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156104e857600080fd5b50610527600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610baf565b604051808215151515815260200191505060405180910390f35b610549610c54565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561059757600080fd5b506105d6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610ddb565b604051808215151515815260200191505060405180910390f35b3480156105fc57600080fd5b50610651600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610df2565b6040518082815260200191505060405180910390f35b34801561067357600080fd5b506106a8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e79565b005b606060038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107425780601f1061071757610100808354040283529160200191610742565b820191906000526020600020905b81548152906001019060200180831161072557829003601f168201915b5050505050905090565b6000610759338484610fce565b6001905092915050565b6000600754905090565b600061077a84848461124f565b610813843361080e85600260008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461157990919063ffffffff16565b610fce565b600190509392505050565b6000600560009054906101000a900460ff16905090565b60006108d033846108cb85600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546115c390919063ffffffff16565b610fce565b6001905092915050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415151561097f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f45524332303a206d696e7420746f20746865207a65726f20616464726573730081525060200191505060405180910390fd5b610994816007546115c390919063ffffffff16565b6007819055506109ec81600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546115c390919063ffffffff16565b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040518082815260200191505060405180910390a35050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60065481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b606060048054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ba55780601f10610b7a57610100808354040283529160200191610ba5565b820191906000526020600020905b815481529060010190602001808311610b8857829003601f168201915b5050505050905090565b6000610c4a3384610c4585600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461157990919063ffffffff16565b610fce565b6001905092915050565b6000806000670de0b6b3a76400006103e83402029150610c946000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff16610a99565b9050600082111515610d0e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f596f75206e65656420746f2073656e6420736f6d65204574686572000000000081525060200191505060405180910390fd5b808211151515610d86576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4e6f7420656e6f75676820746f6b656e7320696e20746865207265736572766581525060200191505060405180910390fd5b610db26000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff16338461124f565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff169250505090565b6000610de833848461124f565b6001905092915050565b6000600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610ed457600080fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610f1057600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151515611099576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260248152602001807f45524332303a20617070726f76652066726f6d20746865207a65726f2061646481526020017f726573730000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515611164576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f45524332303a20617070726f766520746f20746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b80600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925836040518082815260200191505060405180910390a3505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415151561131a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001807f45524332303a207472616e736665722066726f6d20746865207a65726f20616481526020017f647265737300000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141515156113e5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f45524332303a207472616e7366657220746f20746865207a65726f206164647281526020017f657373000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b61143781600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461157990919063ffffffff16565b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506114cc81600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546115c390919063ffffffff16565b600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040518082815260200191505060405180910390a3505050565b60006115bb83836040805190810160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f77000081525061164d565b905092915050565b6000808284019050838110151515611643576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b60008084841115839015156116fd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b838110156116c25780820151818401526020810190506116a7565b50505050905090810190601f1680156116ef5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5083850390508091505093925050505600a165627a7a72305820a5f12fe82b63c7745b28c47ee9bed95498881e9e0e65ad3557be6b166310c0b30029";

	// 생성자
	public CashContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
		super(BINARY, contractAddress, web3j, credentials, gasProvider);
	}

	protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			ContractGasProvider gasProvider) {
		super(BINARY, contractAddress, web3j, transactionManager, gasProvider);
	}

	public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
		final Event event = new Event("Transfer", 
				Arrays.asList(new TypeReference<Address>(){}, 
						new TypeReference<Address>(){}, 
						new TypeReference<Uint256>(){}));
		
		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
		ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
		for (EventValues eventValues : valueList) {
			TransferEventResponse typedResponse = new TransferEventResponse();
			typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
			typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
			typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
			responses.add(typedResponse);
		}
		return responses;
	}

	public Flowable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		final Event event = new Event("Transfer", 
				Arrays.asList(new TypeReference<Address>(){}, 
						new TypeReference<Address>(){}, 
						new TypeReference<Uint256>(){}));
		
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(event));
		
		return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
			@Override
			public TransferEventResponse apply(Log log) throws Exception {
				EventValues eventValues = extractEventParameters(event, log);
				TransferEventResponse typedResponse = new TransferEventResponse();
				typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
				typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
				typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
				typedResponse._transactionHash = log.getTransactionHash();
				return typedResponse;
			}
		});
	}
	
	public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval", 
        		Arrays.asList(new TypeReference<Address>(){}, 
						new TypeReference<Address>(){}, 
						new TypeReference<Uint256>(){}));
        
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse._owner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._spender = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Approval", 
        		Arrays.asList(new TypeReference<Address>(){}, 
						new TypeReference<Address>(){}, 
						new TypeReference<Uint256>(){}));
        
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) throws Exception {
                EventValues eventValues = extractEventParameters(event, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse._owner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._spender = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._transactionHash = log.getTransactionHash();
                return typedResponse;
            }
        });
    }

    
    public Future<Utf8String> name() throws IOException {
        Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturn(function);
    }
    
//	public RemoteFunctionCall<String> name() {
//		final Function function = new Function("name", Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String("d")),
//				Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
//				}));
//		return executeRemoteCallSingleValueReturn(function, String.class);
//	}

	// approve(Address _spender, Uint256 _amount)
	// _spender에게 _amount 만큼의 토큰을 인출할 권리를 부여한다.
	public TransactionReceipt approve(Address _spender, Uint256 _amount) throws IOException, TransactionException {
		Function function = new Function("approve", Arrays.<Type>asList(_spender, _amount),
				Collections.<TypeReference<?>>emptyList());
		return executeTransaction(function);
	}

	// transferFrom
	// _from 의 계좌에서 _amount개의 토큰을 _to에게 보내라. 단, 이 함수는 approve 함수를 통해 인출할 권리를 받은
	// _from만 실행할 수 있다.
	public TransactionReceipt transferFrom(Address _from, Address _to, Uint256 _amount)
			throws IOException, TransactionException {
		Function function = new Function("transferFrom", Arrays.<Type>asList(_from, _to, _amount),
				Collections.<TypeReference<?>>emptyList());
		return executeTransaction(function);
	}



	public RemoteCall<Uint8> totalSupply() throws IOException {
		Function function = new Function("totalSupply", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}));
		return executeRemoteCallSingleValueReturn(function, Uint8.class);
	}

	public Uint8 decimals() throws IOException {
		Function function = new Function("decimals", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}));
		return executeCallSingleValueReturn(function);
	}

//
	// balanceOf
	// 잔액조회
	public RemoteCall<BigInteger> balanceOf(Address account) {
		Function function = new Function("balanceOf", Arrays.<Type>asList(account),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}
	
	// transfer
	public TransactionReceipt transfer(Address _to, Uint256 _amount) throws IOException, TransactionException {
		Function function = new Function("transfer", Arrays.<Type>asList(_to, _amount),
				Collections.<TypeReference<?>>emptyList());
		return executeTransaction(function);
	}

	public Future<Uint256> allowance(Address _owner, Address _spender) throws IOException {
		Function function = new Function("allowance", Arrays.<Type>asList(_owner, _spender),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeCallSingleValueReturn(function);
	}
	
	public TransactionReceipt buy(Address _owner, Uint256 _amount) throws IOException, TransactionException {
		Function function = new Function("buy", Arrays.<Type>asList(_owner, _amount),
				Collections.<TypeReference<?>>emptyList());
		return executeTransaction(function);
	}

	public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit, BigInteger initialWeiValue, Uint256 totalSupply, Utf8String tokenName,
			Uint8 decimalUnits, Utf8String tokenSymbol) {
		String encodedConstructor = FunctionEncoder
				.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName, decimalUnits, tokenSymbol));
		return deployRemoteCall(CashContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor,
				initialWeiValue);
	}

//
//	public static RemoteCall<CashContract> deploy(Web3j web3j, TransactionManager transactionManager,
//			BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Uint256 totalSupply,
//			Utf8String tokenName, Uint8 decimalUnits, Utf8String tokenSymbol) {
//		String encodedConstructor = FunctionEncoder
//				.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName, decimalUnits, tokenSymbol));
//		return deployRemoteCall(CashContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY,
//				encodedConstructor, initialWeiValue);
//	}
//	
	public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials,
			ContractGasProvider contractGasProvider) {
		return deployRemoteCall(CashContract.class, web3j, credentials, contractGasProvider, BINARY, "");
	}

//
//    public static RemoteCall<CashContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(CashContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }
//	
//	public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider gasProvider, Uint256 totalSupply, Utf8String tokenName,
//			Uint8 decimalUnits, Utf8String tokenSymbol) {
//		String encodedConstructor = FunctionEncoder
//				.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName, decimalUnits, tokenSymbol));
//		System.out.println(encodedConstructor);
//		return deployRemoteCall(CashContract.class, web3j, credentials, gasProvider, BINARY, encodedConstructor);
//	}
//
	public static CashContract load(String contractAddress, Web3j web3j, Credentials credentials,
			ContractGasProvider gasProvider) {
		return new CashContract(contractAddress, web3j, credentials, gasProvider);
	}

	public static CashContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			ContractGasProvider gasProvider) {
		return new CashContract(contractAddress, web3j, transactionManager, gasProvider);
	}

	public static class TransferEventResponse {
		public Address _from;
		public Address _to;
		public Uint256 _value;
		public String _transactionHash;
	}

	public static class ApprovalEventResponse {
		public Address _owner;
		public Address _spender;
		public Uint256 _value;
		public String _transactionHash;
	}
}
