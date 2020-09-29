pragma solidity ^0.5.0;

contract partyContract {

  uint public totalParties = 0;
  uint public totalExpenses = 0;

  struct Party{
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

  event CreateGroup(string name, uint indexed grpId, string venue,uint date, uint payCycle, uint target, uint partyBalance, uint exitFee, uint disburseDate);
  event AddExpense(string name, uint indexed expId, uint indexed grpId, uint cost, address indexed to);
  event VerifiedExp(address indexed verifier, uint indexed expId, uint indexed grpId);

  function getNoOfTotalGroupsOf(address user) view public returns(uint){
    return belongsTo[user].length;
  }

  function createGroup( uint _disburseDate, uint _payCycle, uint _target, uint _exitFee, string memory _name, string memory _venue, uint _date) public returns(uint){
    Party memory newParty;

    newParty.id = totalParties;
    newParty.disburseDate = _disburseDate;
    newParty.payCycle = _payCycle;
    newParty.target = _target;
    newParty.partyBalance = 0;
    newParty.exitFee = _exitFee;

    emit CreateGroup(_name, totalParties, _venue, _date, _payCycle, _target, 0, _exitFee, _disburseDate);

    totalParties++;
    parties.push(newParty);
  }

  function joinGroup(uint _partyId) public payable {
    require(totalParties >= _partyId && 0 <= _partyId);
    require(msg.value == parties[_partyId].payCycle);
    require(belongsToOrNot[msg.sender][_partyId]==false);

    parties[_partyId].members.push(msg.sender);
    parties[_partyId].noOfMembers++;
    parties[_partyId].partyBalance += msg.value;
    belongsToOrNot[msg.sender][_partyId] = true;
    belongsTo[msg.sender].push(_partyId);

  }

  function addExpense(string memory _name, uint _partyId, address payable _to, uint _cost, string memory _exphash) public {
      require(parties[_partyId].partyBalance >= _cost);

      UnverifiedExpense memory newUnverifiedExp;

      newUnverifiedExp.id = totalExpenses;
      newUnverifiedExp.cost = _cost;
      newUnverifiedExp.to = _to;
      newUnverifiedExp.expHash = _exphash;
      newUnverifiedExp.totalSigns = 0;


      for(uint i=0; i<parties[_partyId].noOfMembers ;i++){
          toSignOrNot[parties[_partyId].members[i]][totalExpenses] = true;
      }

      totalExpenses++;

      unverifiedExpenses.push(newUnverifiedExp);

      emit AddExpense(_name, newUnverifiedExp.id, _partyId, _cost, _to);
  }

  function verifyExpense(uint _expId, uint _partyId) public payable{
      if(unverifiedExpenses[_expId].totalSigns >= parties[_partyId].noOfMembers){
          revert();
      }
      else if(unverifiedExpenses[_expId].totalSigns == parties[_partyId].noOfMembers-1){

          unverifiedExpenses[_expId].totalSigns++;

          unverifiedExpenses[_expId].to.transfer(unverifiedExpenses[_expId].cost);

          parties[_partyId].partyBalance -= unverifiedExpenses[_expId].cost;


          delete unverifiedExpenses[_expId];

      }
      else{
          unverifiedExpenses[_expId].totalSigns++;
      }

      toSignOrNot[msg.sender][_expId] = false;


      emit VerifiedExp(msg.sender, _expId, _partyId);
  }

   function disburse(uint _partyId) public payable{
       require(now > parties[_partyId].disburseDate );
       uint dividedBal = parties[_partyId].partyBalance/parties[_partyId].noOfMembers;
       for(uint i=0;i<parties[_partyId].noOfMembers;i++){
           parties[_partyId].members[i].transfer(dividedBal);
       }
       parties[_partyId].partyBalance = 0;

   }
}