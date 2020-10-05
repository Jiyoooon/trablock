package com.trablock.domain.wrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class PartyContract extends Contract {
    public static final String BINARY = "{\r\n"
            + "\t\"linkReferences\": {},\r\n"
            + "\t\"object\": \"608060405234801561001057600080fd5b506040516106ef3803806106ef8339810180604052810190808051906020019092919080519060200190929190805190602001909291908051906020019092919080519060200190929190805182019291905050508560008190555084600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550670de0b6b3a764000084026003819055506201518083024201600681905550670de0b6b3a764000082026009819055506012600a819055506100fd81610108640100000000026401000000009004565b5050505050506101a5565b60008090505b81518110156101a1576001828281518110151561012757fe5b9060200190602002015190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050808060010191505061010e565b5050565b61053b806101b46000396000f3006080604052600436106100d0576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632064d9ca146100d557806329dcb0cf1461013b5780632d68bd2314610166578063313ce5671461019157806338af3eed146101bc5780635daf08ca146102135780636284ae4114610280578063722713f7146102ab5780637609ea4b146102d657806391c3897014610301578063a035b1fe1461032c578063a0dcf9da14610357578063e8b5e51f14610382578063f10684541461038c575b600080fd5b3480156100e157600080fd5b50610139600480360381019080803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192905050506103b7565b005b34801561014757600080fd5b50610150610454565b6040518082815260200191505060405180910390f35b34801561017257600080fd5b5061017b61045a565b6040518082815260200191505060405180910390f35b34801561019d57600080fd5b506101a6610460565b6040518082815260200191505060405180910390f35b3480156101c857600080fd5b506101d1610466565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561021f57600080fd5b5061023e6004803603810190808035906020019092919050505061048c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561028c57600080fd5b506102956104ca565b6040518082815260200191505060405180910390f35b3480156102b757600080fd5b506102c06104d0565b6040518082815260200191505060405180910390f35b3480156102e257600080fd5b506102eb6104ef565b6040518082815260200191505060405180910390f35b34801561030d57600080fd5b506103166104f5565b6040518082815260200191505060405180910390f35b34801561033857600080fd5b506103416104fb565b6040518082815260200191505060405180910390f35b34801561036357600080fd5b5061036c610501565b6040518082815260200191505060405180910390f35b61038a610507565b005b34801561039857600080fd5b506103a1610509565b6040518082815260200191505060405180910390f35b60008090505b815181101561045057600182828151811015156103d657fe5b9060200190602002015190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505080806001019150506103bd565b5050565b60065481565b60045481565b600a5481565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60018181548110151561049b57fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60085481565b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b60075481565b60055481565b60095481565b60035481565b565b600054815600a165627a7a723058209ca1ca5e37d5dfe7e70d68835cdba5f3bcb7d0a6ea201adf6c4c9085fbb39c5f0029\",\r\n"
            + "\t\"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x40 MLOAD PUSH2 0x6EF CODESIZE SUB DUP1 PUSH2 0x6EF DUP4 CODECOPY DUP2 ADD DUP1 PUSH1 0x40 MSTORE DUP2 ADD SWAP1 DUP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 MLOAD DUP3 ADD SWAP3 SWAP2 SWAP1 POP POP POP DUP6 PUSH1 0x0 DUP2 SWAP1 SSTORE POP DUP5 PUSH1 0x2 PUSH1 0x0 PUSH2 0x100 EXP DUP2 SLOAD DUP2 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF MUL NOT AND SWAP1 DUP4 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND MUL OR SWAP1 SSTORE POP PUSH8 0xDE0B6B3A7640000 DUP5 MUL PUSH1 0x3 DUP2 SWAP1 SSTORE POP PUSH3 0x15180 DUP4 MUL TIMESTAMP ADD PUSH1 0x6 DUP2 SWAP1 SSTORE POP PUSH8 0xDE0B6B3A7640000 DUP3 MUL PUSH1 0x9 DUP2 SWAP1 SSTORE POP PUSH1 0x12 PUSH1 0xA DUP2 SWAP1 SSTORE POP PUSH2 0xFD DUP2 PUSH2 0x108 PUSH5 0x100000000 MUL PUSH5 0x100000000 SWAP1 DIV JUMP JUMPDEST POP POP POP POP POP POP PUSH2 0x1A5 JUMP JUMPDEST PUSH1 0x0 DUP1 SWAP1 POP JUMPDEST DUP2 MLOAD DUP2 LT ISZERO PUSH2 0x1A1 JUMPI PUSH1 0x1 DUP3 DUP3 DUP2 MLOAD DUP2 LT ISZERO ISZERO PUSH2 0x127 JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x20 ADD SWAP1 PUSH1 0x20 MUL ADD MLOAD SWAP1 DUP1 PUSH1 0x1 DUP2 SLOAD ADD DUP1 DUP3 SSTORE DUP1 SWAP2 POP POP SWAP1 PUSH1 0x1 DUP3 SUB SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD PUSH1 0x0 SWAP1 SWAP2 SWAP3 SWAP1 SWAP2 SWAP1 SWAP2 PUSH2 0x100 EXP DUP2 SLOAD DUP2 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF MUL NOT AND SWAP1 DUP4 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND MUL OR SWAP1 SSTORE POP POP DUP1 DUP1 PUSH1 0x1 ADD SWAP2 POP POP PUSH2 0x10E JUMP JUMPDEST POP POP JUMP JUMPDEST PUSH2 0x53B DUP1 PUSH2 0x1B4 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x80 PUSH1 0x40 MSTORE PUSH1 0x4 CALLDATASIZE LT PUSH2 0xD0 JUMPI PUSH1 0x0 CALLDATALOAD PUSH29 0x100000000000000000000000000000000000000000000000000000000 SWAP1 DIV PUSH4 0xFFFFFFFF AND DUP1 PUSH4 0x2064D9CA EQ PUSH2 0xD5 JUMPI DUP1 PUSH4 0x29DCB0CF EQ PUSH2 0x13B JUMPI DUP1 PUSH4 0x2D68BD23 EQ PUSH2 0x166 JUMPI DUP1 PUSH4 0x313CE567 EQ PUSH2 0x191 JUMPI DUP1 PUSH4 0x38AF3EED EQ PUSH2 0x1BC JUMPI DUP1 PUSH4 0x5DAF08CA EQ PUSH2 0x213 JUMPI DUP1 PUSH4 0x6284AE41 EQ PUSH2 0x280 JUMPI DUP1 PUSH4 0x722713F7 EQ PUSH2 0x2AB JUMPI DUP1 PUSH4 0x7609EA4B EQ PUSH2 0x2D6 JUMPI DUP1 PUSH4 0x91C38970 EQ PUSH2 0x301 JUMPI DUP1 PUSH4 0xA035B1FE EQ PUSH2 0x32C JUMPI DUP1 PUSH4 0xA0DCF9DA EQ PUSH2 0x357 JUMPI DUP1 PUSH4 0xE8B5E51F EQ PUSH2 0x382 JUMPI DUP1 PUSH4 0xF1068454 EQ PUSH2 0x38C JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0xE1 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x139 PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP3 ADD DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP1 PUSH1 0x20 MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP4 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP4 DUP4 PUSH1 0x20 MUL DUP1 DUP3 DUP5 CALLDATACOPY DUP3 ADD SWAP2 POP POP POP POP POP POP SWAP2 SWAP3 SWAP2 SWAP3 SWAP1 POP POP POP PUSH2 0x3B7 JUMP JUMPDEST STOP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x147 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x150 PUSH2 0x454 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x172 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x17B PUSH2 0x45A JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x19D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x1A6 PUSH2 0x460 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x1C8 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x1D1 PUSH2 0x466 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x21F JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x23E PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 POP POP POP PUSH2 0x48C JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x28C JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x295 PUSH2 0x4CA JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x2B7 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x2C0 PUSH2 0x4D0 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x2E2 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x2EB PUSH2 0x4EF JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x30D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x316 PUSH2 0x4F5 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x338 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x341 PUSH2 0x4FB JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x363 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x36C PUSH2 0x501 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH2 0x38A PUSH2 0x507 JUMP JUMPDEST STOP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x398 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x3A1 PUSH2 0x509 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH1 0x0 DUP1 SWAP1 POP JUMPDEST DUP2 MLOAD DUP2 LT ISZERO PUSH2 0x450 JUMPI PUSH1 0x1 DUP3 DUP3 DUP2 MLOAD DUP2 LT ISZERO ISZERO PUSH2 0x3D6 JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x20 ADD SWAP1 PUSH1 0x20 MUL ADD MLOAD SWAP1 DUP1 PUSH1 0x1 DUP2 SLOAD ADD DUP1 DUP3 SSTORE DUP1 SWAP2 POP POP SWAP1 PUSH1 0x1 DUP3 SUB SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD PUSH1 0x0 SWAP1 SWAP2 SWAP3 SWAP1 SWAP2 SWAP1 SWAP2 PUSH2 0x100 EXP DUP2 SLOAD DUP2 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF MUL NOT AND SWAP1 DUP4 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND MUL OR SWAP1 SSTORE POP POP DUP1 DUP1 PUSH1 0x1 ADD SWAP2 POP POP PUSH2 0x3BD JUMP JUMPDEST POP POP JUMP JUMPDEST PUSH1 0x6 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x4 SLOAD DUP2 JUMP JUMPDEST PUSH1 0xA SLOAD DUP2 JUMP JUMPDEST PUSH1 0x2 PUSH1 0x0 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 JUMP JUMPDEST PUSH1 0x1 DUP2 DUP2 SLOAD DUP2 LT ISZERO ISZERO PUSH2 0x49B JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD PUSH1 0x0 SWAP2 POP SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 JUMP JUMPDEST PUSH1 0x8 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x0 ADDRESS PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND BALANCE SWAP1 POP SWAP1 JUMP JUMPDEST PUSH1 0x7 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x5 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x9 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x3 SLOAD DUP2 JUMP JUMPDEST JUMP JUMPDEST PUSH1 0x0 SLOAD DUP2 JUMP STOP LOG1 PUSH6 0x627A7A723058 KECCAK256 SWAP13 LOG1 0xca 0x5e CALLDATACOPY 0xd5 0xdf 0xe7 0xe7 0xd PUSH9 0x835CDBA5F3BCB7D0A6 0xea KECCAK256 BYTE 0xdf PUSH13 0x4C9085FBB39C5F002900000000 \",\r\n"
            + "\t\"sourceMap\": \"28:1764:0:-;;;942:515;8:9:-1;5:2;;;30:1;27;20:12;5:2;942:515:0;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;1182:7;1176:3;:13;;;;1214:18;1200:11;;:32;;;;;;;;;;;;;;;;;;1270:7;1255:12;:22;1243:9;:34;;;;1334:6;1317:14;:23;1299:15;:41;1288:8;:52;;;;1382:7;1359:20;:30;1351:5;:38;;;;1411:2;1400:8;:13;;;;1424:25;1434:14;1424:9;;;:25;;;:::i;:::-;942:515;;;;;;28:1764;;1463:168;1534:6;1543:1;1534:10;;1529:97;1550:14;:21;1546:1;:25;1529:97;;;1587:7;1600:14;1615:1;1600:17;;;;;;;;;;;;;;;;;;1587:31;;39:1:-1;33:3;27:10;23:18;57:10;52:3;45:23;79:10;72:17;;0:93;1587:31:0;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;1573:3;;;;;;;1529:97;;;1463:168;;:::o;28:1764::-;;;;;;;\"\r\n"
            + "}";

    public static final String FUNC_INVEST = "invest";

    public static final String FUNC_JOINGROUP = "joinGroup";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_DEADLINE = "deadline";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_EXITFEE = "exitFee";

    public static final String FUNC_MEMBERS = "members";

    public static final String FUNC_NOOFMEMBERS = "noOfMembers";

    public static final String FUNC_PARTYBALANCE = "partyBalance";

    public static final String FUNC_PARTYGOAL = "partyGoal";

    public static final String FUNC_PAYCYCLE = "payCycle";

    public static final String FUNC_PID = "pid";

    public static final String FUNC_PRICE = "price";

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

    public RemoteFunctionCall<TransactionReceipt> invest(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_INVEST,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> joinGroup(List<String> membersAddress) {
        final Function function = new Function(
                FUNC_JOINGROUP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(membersAddress, org.web3j.abi.datatypes.Address.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf() {
        final Function function = new Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> deadline() {
        final Function function = new Function(FUNC_DEADLINE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> exitFee() {
        final Function function = new Function(FUNC_EXITFEE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> members(BigInteger param0) {
        final Function function = new Function(FUNC_MEMBERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> noOfMembers() {
        final Function function = new Function(FUNC_NOOFMEMBERS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> partyBalance() {
        final Function function = new Function(FUNC_PARTYBALANCE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> partyGoal() {
        final Function function = new Function(FUNC_PARTYGOAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> payCycle() {
        final Function function = new Function(FUNC_PAYCYCLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> pid() {
        final Function function = new Function(FUNC_PID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> price() {
        final Function function = new Function(FUNC_PRICE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public static RemoteCall<PartyContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger partyId, String ifSuccessfulSendTo, BigInteger goalInEthers, BigInteger durationInDays, BigInteger etherCostOfEachToken, List<String> membersAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(partyId),
                new org.web3j.abi.datatypes.Address(160, ifSuccessfulSendTo),
                new org.web3j.abi.datatypes.generated.Uint256(goalInEthers),
                new org.web3j.abi.datatypes.generated.Uint256(durationInDays),
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken),
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(membersAddress, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(PartyContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PartyContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger partyId, String ifSuccessfulSendTo, BigInteger goalInEthers, BigInteger durationInDays, BigInteger etherCostOfEachToken, List<String> membersAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(partyId),
                new org.web3j.abi.datatypes.Address(160, ifSuccessfulSendTo),
                new org.web3j.abi.datatypes.generated.Uint256(goalInEthers),
                new org.web3j.abi.datatypes.generated.Uint256(durationInDays),
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken),
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(membersAddress, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(PartyContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PartyContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger partyId, String ifSuccessfulSendTo, BigInteger goalInEthers, BigInteger durationInDays, BigInteger etherCostOfEachToken, List<String> membersAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(partyId),
                new org.web3j.abi.datatypes.Address(160, ifSuccessfulSendTo),
                new org.web3j.abi.datatypes.generated.Uint256(goalInEthers),
                new org.web3j.abi.datatypes.generated.Uint256(durationInDays),
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken),
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(membersAddress, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(PartyContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PartyContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger partyId, String ifSuccessfulSendTo, BigInteger goalInEthers, BigInteger durationInDays, BigInteger etherCostOfEachToken, List<String> membersAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(partyId),
                new org.web3j.abi.datatypes.Address(160, ifSuccessfulSendTo),
                new org.web3j.abi.datatypes.generated.Uint256(goalInEthers),
                new org.web3j.abi.datatypes.generated.Uint256(durationInDays),
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken),
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(membersAddress, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(PartyContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
