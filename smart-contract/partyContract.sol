pragma solidity ^0.4.2;

import "./SafeMath.sol";

contract partyContract {
    
    using SafeMath for uint256;
  
   struct Member {
        uint index;
        uint balance;
        bool exists;
        bool warning;
   }
   
   struct Record {
       address member;
       uint amount;
   }
  
  mapping(uint => Record) public withDrawList;
  mapping(address => Member) public members;             // 파티 멤버들의 지갑주소
  address[] internal walletList;
  uint public pid;                      // 모임계좌 id
  address public beneficiary;           // 펀딩을 실행한 사람의 계좌 주소
  uint public partyGoal;                // 목표 금액
  uint public noOfMembers;              // 파티 인원수
  uint public deadline;                 // 여행 금액 모금 마감 날짜
  uint public payCycle;                 // 여행 금액 모금하는 주기(front쪽에서 정해져서 넘어옴)
  uint public exitFee;                  // 퇴출할 때 수수료(파티 생성때 front쪽에서 정해져서 넘어옴)
  
  
  constructor (
        uint partyId,
        uint _partyGoal,
        uint durationInDays,
        uint _exitFee,
        address ownerAddress
    ) public {
        pid = partyId;
        beneficiary = ownerAddress;
        walletList.push(ownerAddress);
        partyGoal = _partyGoal;
        deadline = block.timestamp + durationInDays * 1 days;
        exitFee = _exitFee;
        members[ownerAddress] = Member(0, 0, true, false);
        noOfMembers = 1;
    }

  function joinGroup(address[] memory membersAddress) public {
    for (uint i = 0; i < membersAddress.length; i++) {
        members[membersAddress[i]] = Member(noOfMembers, 0, true, false);
        walletList.push(membersAddress[i]);
        noOfMembers++;
    }
  }
 
 
  
  function isMember(address adr) public view returns (bool) {
      return members[adr].exists;
  }

    function getPartyGoal() public view returns (uint) {
        return partyGoal;
    }
    
    function getBeneficiary() public view returns (address) {
        return beneficiary;
    }
    
    function getNoOfMembers() public view returns (uint) {
        return noOfMembers;
    }
    
    function getWalletList() public view returns (address[] memory){
        return walletList;
    }
    
    function getMemberBalance(address adr) public view returns (uint) {
        return members[adr].balance;
    }

    function addBalacne(address adr, uint256 amount) public {
        members[adr].balance = members[adr].balance.add(amount);
    }
    
    function withDraw(address withdrawer, uint256 amount) public {
        withDrawList[block.timestamp] = Record(withdrawer, amount);
    }
}