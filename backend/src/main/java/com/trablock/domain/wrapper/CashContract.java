package com.trablock.domain.wrapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

	private static final String BINARY = "{\r\n"
            + "\t\"linkReferences\": {},\r\n"
            + "\t\"object\": \"6060604052341561000f57600080fd5b60405161085038038061085083398101604052808051919060200180518201919060200180519190602001805190910190505b60ff8216600a0a84026000818155600160a060020a03331681526004602052604090205560018380516100799291602001906100a7565b506002805460ff191660ff8416179055600381805161009c9291602001906100a7565b505b50505050610147565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100e857805160ff1916838001178555610115565b82800160010185558215610115579182015b828111156101155782518255916020019190600101906100fa565b5b50610122929150610126565b5090565b61014491905b80821115610122576000815560010161012c565b5090565b90565b6106fa806101566000396000f300606060405236156100965763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde03811461009b578063095ea7b31461012657806318160ddd1461015c57806323b872dd14610181578063313ce567146101bd57806370a08231146101e657806395d89b4114610217578063a9059cbb146102a2578063dd62ed3e146102d8575b600080fd5b34156100a657600080fd5b6100ae61030f565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100eb5780820151818401525b6020016100d2565b50505050905090810190601f1680156101185780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561013157600080fd5b610148600160a060020a03600435166024356103ad565b604051901515815260200160405180910390f35b341561016757600080fd5b61016f61041a565b60405190815260200160405180910390f35b341561018c57600080fd5b610148600160a060020a0360043581169060243516604435610420565b604051901515815260200160405180910390f35b34156101c857600080fd5b6101d061050a565b60405160ff909116815260200160405180910390f35b34156101f157600080fd5b61016f600160a060020a0360043516610513565b60405190815260200160405180910390f35b341561022257600080fd5b6100ae610532565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156100eb5780820151818401525b6020016100d2565b50505050905090810190601f1680156101185780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156102ad57600080fd5b610148600160a060020a03600435166024356105d0565b604051901515815260200160405180910390f35b34156102e357600080fd5b61016f600160a060020a03600435811690602435166106a1565b60405190815260200160405180910390f35b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103a55780601f1061037a576101008083540402835291602001916103a5565b820191906000526020600020905b81548152906001019060200180831161038857829003601f168201915b505050505081565b600160a060020a03338116600081815260056020908152604080832094871680845294909152808220859055909291907f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259085905190815260200160405180910390a35060015b92915050565b60005481565b600160a060020a0383166000908152600460205260408120548290108015906104705750600160a060020a0380851660009081526005602090815260408083203390941683529290522054829010155b151561047b57600080fd5b600160a060020a03808416600081815260046020908152604080832080548801905588851680845281842080548990039055600583528184203390961684529490915290819020805486900390559091907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9085905190815260200160405180910390a35060015b9392505050565b60025460ff1681565b600160a060020a0381166000908152600460205260409020545b919050565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103a55780601f1061037a576101008083540402835291602001916103a5565b820191906000526020600020905b81548152906001019060200180831161038857829003601f168201915b505050505081565b600160a060020a0333166000908152600460205260408120548290108015906106125750600160a060020a038316600090815260046020526040902054828101115b151561061d57600080fd5b600160a060020a038316151561063257600080fd5b600160a060020a033381166000818152600460205260408082208054879003905592861680825290839020805486019055917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9085905190815260200160405180910390a35060015b92915050565b600160a060020a038083166000908152600560209081526040808320938516835292905220545b929150505600a165627a7a7230582006c000f923fc211754c7238ff88691ea93d1b0a702ad2eb6cdc3c4dafa02bee90029\",\r\n"
            + "\t\"opcodes\": \"PUSH1 0x60 PUSH1 0x40 MSTORE CALLVALUE ISZERO PUSH2 0xF JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x40 MLOAD PUSH2 0x850 CODESIZE SUB DUP1 PUSH2 0x850 DUP4 CODECOPY DUP2 ADD PUSH1 0x40 MSTORE DUP1 DUP1 MLOAD SWAP2 SWAP1 PUSH1 0x20 ADD DUP1 MLOAD DUP3 ADD SWAP2 SWAP1 PUSH1 0x20 ADD DUP1 MLOAD SWAP2 SWAP1 PUSH1 0x20 ADD DUP1 MLOAD SWAP1 SWAP2 ADD SWAP1 POP JUMPDEST PUSH1 0xFF DUP3 AND PUSH1 0xA EXP DUP5 MUL PUSH1 0x0 DUP2 DUP2 SSTORE PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB CALLER AND DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SSTORE PUSH1 0x1 DUP4 DUP1 MLOAD PUSH2 0x79 SWAP3 SWAP2 PUSH1 0x20 ADD SWAP1 PUSH2 0xA7 JUMP JUMPDEST POP PUSH1 0x2 DUP1 SLOAD PUSH1 0xFF NOT AND PUSH1 0xFF DUP5 AND OR SWAP1 SSTORE PUSH1 0x3 DUP2 DUP1 MLOAD PUSH2 0x9C SWAP3 SWAP2 PUSH1 0x20 ADD SWAP1 PUSH2 0xA7 JUMP JUMPDEST POP JUMPDEST POP POP POP POP PUSH2 0x147 JUMP JUMPDEST DUP3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV DUP2 ADD SWAP3 DUP3 PUSH1 0x1F LT PUSH2 0xE8 JUMPI DUP1 MLOAD PUSH1 0xFF NOT AND DUP4 DUP1 ADD OR DUP6 SSTORE PUSH2 0x115 JUMP JUMPDEST DUP3 DUP1 ADD PUSH1 0x1 ADD DUP6 SSTORE DUP3 ISZERO PUSH2 0x115 JUMPI SWAP2 DUP3 ADD JUMPDEST DUP3 DUP2 GT ISZERO PUSH2 0x115 JUMPI DUP3 MLOAD DUP3 SSTORE SWAP2 PUSH1 0x20 ADD SWAP2 SWAP1 PUSH1 0x1 ADD SWAP1 PUSH2 0xFA JUMP JUMPDEST JUMPDEST POP PUSH2 0x122 SWAP3 SWAP2 POP PUSH2 0x126 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST PUSH2 0x144 SWAP2 SWAP1 JUMPDEST DUP1 DUP3 GT ISZERO PUSH2 0x122 JUMPI PUSH1 0x0 DUP2 SSTORE PUSH1 0x1 ADD PUSH2 0x12C JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST SWAP1 JUMP JUMPDEST PUSH2 0x6FA DUP1 PUSH2 0x156 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x60 PUSH1 0x40 MSTORE CALLDATASIZE ISZERO PUSH2 0x96 JUMPI PUSH4 0xFFFFFFFF PUSH29 0x100000000000000000000000000000000000000000000000000000000 PUSH1 0x0 CALLDATALOAD DIV AND PUSH4 0x6FDDE03 DUP2 EQ PUSH2 0x9B JUMPI DUP1 PUSH4 0x95EA7B3 EQ PUSH2 0x126 JUMPI DUP1 PUSH4 0x18160DDD EQ PUSH2 0x15C JUMPI DUP1 PUSH4 0x23B872DD EQ PUSH2 0x181 JUMPI DUP1 PUSH4 0x313CE567 EQ PUSH2 0x1BD JUMPI DUP1 PUSH4 0x70A08231 EQ PUSH2 0x1E6 JUMPI DUP1 PUSH4 0x95D89B41 EQ PUSH2 0x217 JUMPI DUP1 PUSH4 0xA9059CBB EQ PUSH2 0x2A2 JUMPI DUP1 PUSH4 0xDD62ED3E EQ PUSH2 0x2D8 JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE ISZERO PUSH2 0xA6 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0xAE PUSH2 0x30F JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0x20 DUP1 DUP3 MSTORE DUP2 SWAP1 DUP2 ADD DUP4 DUP2 DUP2 MLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0xEB JUMPI DUP1 DUP3 ADD MLOAD DUP2 DUP5 ADD MSTORE JUMPDEST PUSH1 0x20 ADD PUSH2 0xD2 JUMP JUMPDEST POP POP POP POP SWAP1 POP SWAP1 DUP2 ADD SWAP1 PUSH1 0x1F AND DUP1 ISZERO PUSH2 0x118 JUMPI DUP1 DUP3 SUB DUP1 MLOAD PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP JUMPDEST POP SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x131 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x148 PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD AND PUSH1 0x24 CALLDATALOAD PUSH2 0x3AD JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 ISZERO ISZERO DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x167 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x16F PUSH2 0x41A JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x18C JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x148 PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD DUP2 AND SWAP1 PUSH1 0x24 CALLDATALOAD AND PUSH1 0x44 CALLDATALOAD PUSH2 0x420 JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 ISZERO ISZERO DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x1C8 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x1D0 PUSH2 0x50A JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0xFF SWAP1 SWAP2 AND DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x1F1 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x16F PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD AND PUSH2 0x513 JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x222 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0xAE PUSH2 0x532 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH1 0x20 DUP1 DUP3 MSTORE DUP2 SWAP1 DUP2 ADD DUP4 DUP2 DUP2 MLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0xEB JUMPI DUP1 DUP3 ADD MLOAD DUP2 DUP5 ADD MSTORE JUMPDEST PUSH1 0x20 ADD PUSH2 0xD2 JUMP JUMPDEST POP POP POP POP SWAP1 POP SWAP1 DUP2 ADD SWAP1 PUSH1 0x1F AND DUP1 ISZERO PUSH2 0x118 JUMPI DUP1 DUP3 SUB DUP1 MLOAD PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP JUMPDEST POP SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x2AD JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x148 PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD AND PUSH1 0x24 CALLDATALOAD PUSH2 0x5D0 JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 ISZERO ISZERO DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE ISZERO PUSH2 0x2E3 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x16F PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD DUP2 AND SWAP1 PUSH1 0x24 CALLDATALOAD AND PUSH2 0x6A1 JUMP JUMPDEST PUSH1 0x40 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH1 0x1 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV DUP1 ISZERO PUSH2 0x3A5 JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x37A JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x3A5 JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x388 JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP DUP2 JUMP JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB CALLER DUP2 AND PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x5 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 SWAP5 DUP8 AND DUP1 DUP5 MSTORE SWAP5 SWAP1 SWAP2 MSTORE DUP1 DUP3 KECCAK256 DUP6 SWAP1 SSTORE SWAP1 SWAP3 SWAP2 SWAP1 PUSH32 0x8C5BE1E5EBEC7D5BD14F71427D1E84F3DD0314C0F7B2291E5B200AC8C7C3B925 SWAP1 DUP6 SWAP1 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG3 POP PUSH1 0x1 JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP4 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 SLOAD DUP3 SWAP1 LT DUP1 ISZERO SWAP1 PUSH2 0x470 JUMPI POP PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP1 DUP6 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x5 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 CALLER SWAP1 SWAP5 AND DUP4 MSTORE SWAP3 SWAP1 MSTORE KECCAK256 SLOAD DUP3 SWAP1 LT ISZERO JUMPDEST ISZERO ISZERO PUSH2 0x47B JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP1 DUP5 AND PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 DUP1 SLOAD DUP9 ADD SWAP1 SSTORE DUP9 DUP6 AND DUP1 DUP5 MSTORE DUP2 DUP5 KECCAK256 DUP1 SLOAD DUP10 SWAP1 SUB SWAP1 SSTORE PUSH1 0x5 DUP4 MSTORE DUP2 DUP5 KECCAK256 CALLER SWAP1 SWAP7 AND DUP5 MSTORE SWAP5 SWAP1 SWAP2 MSTORE SWAP1 DUP2 SWAP1 KECCAK256 DUP1 SLOAD DUP7 SWAP1 SUB SWAP1 SSTORE SWAP1 SWAP2 SWAP1 PUSH32 0xDDF252AD1BE2C89B69C2B068FC378DAA952BA7F163C4A11628F55A4DF523B3EF SWAP1 DUP6 SWAP1 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG3 POP PUSH1 0x1 JUMPDEST SWAP4 SWAP3 POP POP POP JUMP JUMPDEST PUSH1 0x2 SLOAD PUSH1 0xFF AND DUP2 JUMP JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP2 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD JUMPDEST SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV DUP1 ISZERO PUSH2 0x3A5 JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x37A JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x3A5 JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x388 JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP DUP2 JUMP JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB CALLER AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 DUP2 KECCAK256 SLOAD DUP3 SWAP1 LT DUP1 ISZERO SWAP1 PUSH2 0x612 JUMPI POP PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP4 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 SWAP1 KECCAK256 SLOAD DUP3 DUP2 ADD GT JUMPDEST ISZERO ISZERO PUSH2 0x61D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP4 AND ISZERO ISZERO PUSH2 0x632 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB CALLER DUP2 AND PUSH1 0x0 DUP2 DUP2 MSTORE PUSH1 0x4 PUSH1 0x20 MSTORE PUSH1 0x40 DUP1 DUP3 KECCAK256 DUP1 SLOAD DUP8 SWAP1 SUB SWAP1 SSTORE SWAP3 DUP7 AND DUP1 DUP3 MSTORE SWAP1 DUP4 SWAP1 KECCAK256 DUP1 SLOAD DUP7 ADD SWAP1 SSTORE SWAP2 PUSH32 0xDDF252AD1BE2C89B69C2B068FC378DAA952BA7F163C4A11628F55A4DF523B3EF SWAP1 DUP6 SWAP1 MLOAD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG3 POP PUSH1 0x1 JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP1 DUP4 AND PUSH1 0x0 SWAP1 DUP2 MSTORE PUSH1 0x5 PUSH1 0x20 SWAP1 DUP2 MSTORE PUSH1 0x40 DUP1 DUP4 KECCAK256 SWAP4 DUP6 AND DUP4 MSTORE SWAP3 SWAP1 MSTORE KECCAK256 SLOAD JUMPDEST SWAP3 SWAP2 POP POP JUMP STOP LOG1 PUSH6 0x627A7A723058 KECCAK256 MOD 0xc0 STOP 0xf9 0x23 0xfc 0x21 OR SLOAD 0xc7 0x23 DUP16 0xf8 DUP7 SWAP2 0xea SWAP4 0xd1 0xb0 0xa7 MUL 0xad 0x2e 0xb6 0xcd 0xc3 0xc4 0xda STATICCALL MUL 0xbe 0xe9 STOP 0x29 \",\r\n"
            + "\t\"sourceMap\": \"740:2690:0:-;;;1056:496;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;-1:-1:-1;1056:496:0;1215:22;;;1209:2;:28;1192:45;;1178:11;:59;;;-1:-1:-1;;;;;1287:10:0;1278:20;;;:8;:20;;;;;:34;1431:4;1438:10;;1431:17;;;;;;;;:::i;:::-;-1:-1:-1;1478:8:0;:24;;-1:-1:-1;;1478:24:0;;;;;;;1523:6;1532:12;;1523:21;;;;;;;;:::i;:::-;;1056:496;;;;;740:2690;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;-1:-1:-1;740:2690:0;;;-1:-1:-1;740:2690:0;:::i;:::-;;;:::o;:::-;;;;;;;;;;;;;;;;;;;;;;;;:::o;:::-;;;;;;;\"\r\n"
            + "}";
	// 생성자
	public CashContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
		super(BINARY, contractAddress, web3j, credentials, gasProvider);
	}
//	
//	protected CashContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
//			ContractGasProvider gasProvider) {
//		super(BINARY, contractAddress, web3j, transactionManager, gasProvider);
//	}
//
//	public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
//		final Event event = new Event("Transfer", 
//				Arrays.asList(new TypeReference<Address>(){}, 
//						new TypeReference<Address>(){}, 
//						new TypeReference<Uint256>(){}));
//		
//		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
//		ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
//		for (EventValues eventValues : valueList) {
//			TransferEventResponse typedResponse = new TransferEventResponse();
//			typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
//			typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
//			typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
//			responses.add(typedResponse);
//		}
//		return responses;
//	}
//
//	public Flowable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock,
//			DefaultBlockParameter endBlock) {
//		final Event event = new Event("Transfer", 
//				Arrays.asList(new TypeReference<Address>(){}, 
//						new TypeReference<Address>(){}, 
//						new TypeReference<Uint256>(){}));
//		
//		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//		filter.addSingleTopic(EventEncoder.encode(event));
//		
//		return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
//			@Override
//			public TransferEventResponse apply(Log log) throws Exception {
//				EventValues eventValues = extractEventParameters(event, log);
//				TransferEventResponse typedResponse = new TransferEventResponse();
//				typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
//				typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
//				typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
//				typedResponse._transactionHash = log.getTransactionHash();
//				return typedResponse;
//			}
//		});
//	}
//	
//	public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
//        final Event event = new Event("Approval", 
//        		Arrays.asList(new TypeReference<Address>(){}, 
//						new TypeReference<Address>(){}, 
//						new TypeReference<Uint256>(){}));
//        
//        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
//        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
//        for (EventValues eventValues : valueList) {
//            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
//            typedResponse._owner = (Address) eventValues.getIndexedValues().get(0);
//            typedResponse._spender = (Address) eventValues.getIndexedValues().get(1);
//            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Flowable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        final Event event = new Event("Approval", 
//        		Arrays.asList(new TypeReference<Address>(){}, 
//						new TypeReference<Address>(){}, 
//						new TypeReference<Uint256>(){}));
//        
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(event));
//        
//        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
//            @Override
//            public ApprovalEventResponse apply(Log log) throws Exception {
//                EventValues eventValues = extractEventParameters(event, log);
//                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
//                typedResponse._owner = (Address) eventValues.getIndexedValues().get(0);
//                typedResponse._spender = (Address) eventValues.getIndexedValues().get(1);
//                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
//                typedResponse._transactionHash = log.getTransactionHash();
//                return typedResponse;
//            }
//        });
//    }
//
//	// Function(String name, List<Type> inputParameters, List<TypeReference<?>>
//	// outputParameters)
//    
//    public Future<Utf8String> name() throws IOException {
//        Function function = new Function("name", 
//                Arrays.<Type>asList(), 
//                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//        return executeCallSingleValueReturn(function);
//    }
//
//	// approve(Address _spender, Uint256 _amount)
//	// _spender에게 _amount 만큼의 토큰을 인출할 권리를 부여한다.
//	public TransactionReceipt approve(Address _spender, Uint256 _amount) throws IOException, TransactionException {
//		Function function = new Function("approve", Arrays.<Type>asList(_spender, _amount),
//				Collections.<TypeReference<?>>emptyList());
//		return executeTransaction(function);
//	}
//
//	// transferFrom
//	// _from 의 계좌에서 _amount개의 토큰을 _to에게 보내라. 단, 이 함수는 approve 함수를 통해 인출할 권리를 받은
//	// _from만 실행할 수 있다.
//	public TransactionReceipt transferFrom(Address _from, Address _to, Uint256 _amount)
//			throws IOException, TransactionException {
//		Function function = new Function("transferFrom", Arrays.<Type>asList(_from, _to, _amount),
//				Collections.<TypeReference<?>>emptyList());
//		return executeTransaction(function);
//	}
//
//	public Uint8 decimals() throws IOException {
//		Function function = new Function("decimals", Arrays.<Type>asList(),
//				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
//				}));
//		return executeCallSingleValueReturn(function);
//	}
//
	// balanceOf
	// 잔액조회
	public RemoteFunctionCall<BigInteger> balanceOf(Address _owner) {
		Function function = new Function("balanceOf", Arrays.<Type>asList(_owner),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}
//	public Future<Address> owner() throws IOException {
//		Function function = new Function("owner", Arrays.<Type>asList(),
//				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
//				}));
//		return executeCallSingleValueReturn(function);
//	}
//
//	public Future<Utf8String> symbol() throws IOException {
//		Function function = new Function("symbol", Arrays.<Type>asList(),
//				Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
//				}));
//		return executeCallSingleValueReturn(function);
//	}
//
//	// transfer
//	public TransactionReceipt transfer(Address _to, Uint256 _amount) throws IOException, TransactionException {
//		Function function = new Function("transfer", Arrays.<Type>asList(_to, _amount),
//				Collections.<TypeReference<?>>emptyList());
//		return executeTransaction(function);
//	}
//
//	public Future<Uint256> allowance(Address _owner, Address _spender) throws IOException {
//		Function function = new Function("allowance", Arrays.<Type>asList(_owner, _spender),
//				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
//				}));
//		return executeCallSingleValueReturn(function);
//	}

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
    public static RemoteCall<CashContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
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
	public static CashContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
		return new CashContract(contractAddress, web3j, credentials, gasProvider);
	}

//	public static CashContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
//		return new CashContract(contractAddress, web3j, transactionManager, gasProvider);
//	}
//
//	public static class TransferEventResponse {
//		public Address _from;
//		public Address _to;
//		public Uint256 _value;
//		public String _transactionHash;
//	}
//
//	public static class ApprovalEventResponse {
//		public Address _owner;
//		public Address _spender;
//		public Uint256 _value;
//		public String _transactionHash;
//	}
}
