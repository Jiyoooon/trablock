// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.7.0;

import "./SafeMath.sol";

interface IERC20 {

    function totalSupply() external view returns (uint256);
    function balanceOf(address account) external view returns (uint256);
    function transfer(address recipient, uint256 amount) external returns (bool);
    function allowance(address owner, address spender) external view returns (uint256);
    function approve(address spender, uint256 amount) external returns (bool);
    function transferFrom(address sender, address recipient, uint256 amount) external returns (bool);

    event Transfer(address indexed from, address indexed to, uint256 value);
    event Approval(address indexed owner, address indexed spender, uint256 value);
}

/** 
 * @title Cash
 * @notice Cash follows the erc20 standard and is used in the ecommerce service.
 */
contract Cash is IERC20{
    
    using SafeMath for uint256;
    
    mapping (address => uint256) private _balances;
    
    mapping (address => mapping (address => uint256)) private _allowances;
    
    string private _name;
    string private _symbol;
    unit8 private _decimals;
    
    /**
     * @notice constructor
     * totalSupply(inital supply), minter(owner)
     */
    constructor(string memory name, string memory symbol, uint8 decimals) public {
       _name = name;
       _symbol = symbol;
       _decimals = decimals;
       
       
    }

    /**
     * optional function example
     * @notice mint
     * @param _receiver the receiver's address
     * @param _amount the amount of tokens
     */
    function mint(address _receiver, uint256 _amount) external 
    {
        // todo 
    }
    
    /**
     * @notice retrieves the balance that the account has
     * @param _account address
     * @return balance 
     */
    function balanceOf(address _account) external view returns (uint)		
    {
        // todo 
        return _balanceOf[_account];
    }
    
    /**
     * @notice transfers the amount of token to the recipient
     * @param _recipient the receiver's address
     * @param _amount the amount of tokens
     * @return success or failure
     */
    function transfer(address _recipient, uint _amount) external returns (bool)
    {  
        // todo 
        //_transfer(msg.sender, recipient, amount);
        require(msg.sender != address(0), "ERC20: transfer from the zero address");
        require(_recipient != address(0), "ERC20: transfer to the zero address");
        
        _balances[msg.sender] = _balances[msg.sender].sub(_amount);
        _balances[_recipient] = _balances[_recipient].add(_amount);
        
        return true;
    }

    /**
     * @notice retrieves the delegated balance 
     * @param _owner the onwer's address
     * @param _spender the delegator's address
     * @return the amount of allownace
     * _owner가 _spender에게 인출을 허락한 토큰의 개수는 몇개인가?
     */
    function allowance(address _owner, address _spender) external view returns (uint)
    {
        // todo 
        return _allowance[_owner][_spender];	
    }  

    /**
     * @notice delegate the transfer
     * @param _spender the delegator's address
     * @param _amount the allowed amount of tokens
     * @return success or failure
     * _spender에게  _amount 만큼의 토큰을 인출할 권리를 부여한다. 
     */    
    function approve(address _spender, uint _amount) external returns (bool)
    {
        // todo
        //_approve(msg.sender, _spender, _amount)
        require(msg.sender != address(0), "ERC20: approve from the zero address");
        require(_spender != address(0), "ERC20: approve to the zero address");
        
        _allowances[msg.sender][_spender] = _amount;
        emit Approval(msg.sender, _spender, _amount);
        return true;
    }
    
    /**
     * @notice transfers the amount of token on behalf of the owner
     * @param _sender the sender's address
     * @param _recipient the receiver's address
     * @param _amount the amount of tokens
     * @return success of failure
     * 남아있는 토큰을 누군가에게 보내는 과정
     * _sender 의 계좌에서 _amount개의 토큰을 _recipient에게 보내라. 단, 이 함수는 approve 함수를 통해 인출할 권리를 받은 spender 만 실행할 수 있다.
     */     
    function transferFrom(address _sender, address _recipient, uint _amount) external returns (bool)
    {
        // todo
        //_transfer(_sender, _recipient, _amount)
        //_approve(_sender, msg.sender, _allowances[_sender][msg.sender].sub(_amount))
 
        require(_sender != address(0), "ERC20: transfer from the zero address");
        require(_recipient != address(0), "ERC20: transfer to the zero address");
        
        _balances[_sender] = _balances[_sender].sub(_amount);
        _balances[_recipient] = _balances[_recipient].add(_amount);
        emit Transfer(_sender, _recipient, _amount);
        
        
        require(_sender != address(0), "ERC20: approve from the zero address");
        require(msg.sender != address(0), "ERC20: approve to the zero address");
        
        _allowances[_sender][msg.sender] = _allowances[_sender][msg.sender].sub(_amount);
        emit Approval(_sender, msg.sender, _allowances[_sender][msg.sender].sub(_amount));

        return true;
    }

    /**
     * @notice buy tokens
     * msg.value should be greater than or equal to 0.1 ether
     * 1 eth = 100,000 cash	
     * @return success or failure
     */      
    function buy() public payable returns(bool){
        // todo
        return true;
    }
    
}