// SPDX-License-Identifier: MIT

pragma solidity ^0.7.2;

contract partyContract {

  uint public totalParties = 0;
  uint public totalExpenses = 0;

  struct Party{
    address beneficiary;
    uint id;
    uint noOfMembers;
    uint travelDate;
    uint payCycle;
    uint target;
    uint partyBalance;
    uint exitFee;
    address payable[] members;
  }

  struct UnverifiedExpense{
    uint id;
    address payable to;
    uint cost;
  //  address[] verifiedBy;
    uint totalSigns;
    string expHash;
  }

  Party[] public parties;
  UnverifiedExpense[] public unverifiedExpenses;

  mapping(address => mapping(uint => bool)) public toSignOrNot;
  
  mapping(address => mapping(uint  => bool)) public belongsToOrNot;
  mapping(address => uint[]) public belongsTo;

  event CreateParty(string name, uint indexed grpId, string venue, uint payCycle, uint target, uint partyBalance, uint exitFee, uint travelDate);
  event AddExpense(string name, uint indexed expId, uint indexed grpId, uint cost, address indexed to);
  event VerifiedExp(address indexed verifier, uint indexed expId, uint indexed grpId);

  function getNoOfTotalGroupsOf(address user) view public returns(uint){
    return belongsTo[user].length;
  }
  
     function _totalParties() public view returns (uint) {
        return totalParties;
    }

  function createParty(uint _travelDate, uint _payCycle, uint _target, uint _exitFee, string memory _name, string memory _venue) public returns(uint){
    Party memory newParty;
    
    newParty.id = totalParties;
    newParty.beneficiary = msg.sender;
    newParty.noOfMembers = 1;
    newParty.travelDate = _travelDate;
    newParty.payCycle = _payCycle;
    newParty.target = _target;
    newParty.partyBalance = 0;
    newParty.exitFee = _exitFee;

    emit CreateParty(_name, totalParties, _venue, _payCycle, _target, 0, _exitFee, _travelDate);

    totalParties++;
    parties.push(newParty);
  }

  function joinGroup(uint _partyId) public {
    require(totalParties >= _partyId && 0 <= _partyId);
    require(belongsToOrNot[msg.sender][_partyId]==false);

    parties[_partyId].members.push(msg.sender);
    parties[_partyId].noOfMembers++;
  }
}