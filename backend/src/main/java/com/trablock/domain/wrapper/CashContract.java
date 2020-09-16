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

	private static final String BINARY = "contract binary key";
	
	// 생성자
	protected CashContract(String contractAddress, Web3j web3j, Credentials credentials,
			ContractGasProvider gasProvider) {
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

	// Function(String name, List<Type> inputParameters, List<TypeReference<?>>
	// outputParameters)
    
    public Future<Utf8String> name() throws IOException {
        Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturn(function);
    }

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

	public Uint8 decimals() throws IOException {
		Function function = new Function("decimals", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}));
		return executeCallSingleValueReturn(function);
	}

	// balanceOf
	// 잔액조회
	public Uint256 balanceOf(Address _owner) throws IOException {
		Function function = new Function("balanceOf", Arrays.<Type>asList(_owner),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeCallSingleValueReturn(function);
	}

	public Future<Address> owner() throws IOException {
		Function function = new Function("owner", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		return executeCallSingleValueReturn(function);
	}

	public Future<Utf8String> symbol() throws IOException {
		Function function = new Function("symbol", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
				}));
		return executeCallSingleValueReturn(function);
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

	public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit, BigInteger initialWeiValue, Uint256 totalSupply, Utf8String tokenName,
			Uint8 decimalUnits, Utf8String tokenSymbol) {
		String encodedConstructor = FunctionEncoder
				.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName, decimalUnits, tokenSymbol));
		return deployRemoteCall(CashContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor,
				initialWeiValue);
	}

	public static RemoteCall<CashContract> deploy(Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Uint256 totalSupply,
			Utf8String tokenName, Uint8 decimalUnits, Utf8String tokenSymbol) {
		String encodedConstructor = FunctionEncoder
				.encodeConstructor(Arrays.<Type>asList(totalSupply, tokenName, decimalUnits, tokenSymbol));
		return deployRemoteCall(CashContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY,
				encodedConstructor, initialWeiValue);
	}

	public static CashContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
		
		return new CashContract(contractAddress, web3j, credentials, gasProvider);
	}

	public static CashContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
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
