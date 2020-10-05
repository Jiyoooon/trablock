// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.7.2;

import "./SafeMath.sol";
import "./Ownable.sol";

interface IERC20 {

    function totalSupply() external view returns (uint256);
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

contract Cash is Ownable, IERC20{
    
    using SafeMath for uint256;
    
    mapping (address => uint256) private _balances;
    mapping (address => mapping (address => uint256)) private _allowances;
    
    string private _name = "TraBlcokCoin";
    string private _symbol = "TBC";
    uint8 private _decimals  = 18;
    uint public buyPrice = 10000;
    
    uint256 private _totalSupply;

    function name() public view returns (string memory) {
        return _name;
    }

    /**
     * @dev 주로 이름을 줄여서 표현한 토큰 심볼을
     * 반환합니다.
     */
    function symbol() public view returns (string memory) {
        return _symbol;
    }

    /**
     * @dev 사용자 표현을 위한 소수 자릿수를 반환합니다.
     * 예를 들어, `decimals`이  `2`인 경우, 505` 토큰은
     * 사용자에게 `5,05` (`505 / 10 ** 2`)와 같이 표시되어야 합니다.
     *
     * 토큰은 보통 18의 값을 취하며, 이는 Ether와 Wei의 관계를
     * 모방한 것입니다.
     *
     * > 이 정보는 디스플레이 목적으로만 사용됩니다.
     * `IERC20.balanceOf`와 `IERC20.transfer`를 포함해
     * 컨트랙트의 산술 연산에 어떠한 영향을 주지 않습니다.
     */
    function decimals() public view returns (uint8) {
        return _decimals;
    }
    // https://github.com/OpenZeppelin/openzeppelin-solidity/blob/v2.3.0/contracts/token/ERC20/ERC20Detailed.sol 끝 부분을 참고



    /**
     * @dev `IERC20.totalSupply`를 참조하세요.
     */
    function totalSupply() override public view returns (uint256) {
        return _totalSupply;
    }

    /**
     * @dev `IERC20.balanceOf`를 참조하세요.
     */
    function balanceOf(address account) public view returns (uint256) {
        return _balances[account];
    }

    /**
     * @dev `IERC20.transfer`를 참조하세요.
     *
     * 요구사항 :
     *
     * - `recipient`는 영 주소(0x0000...0)가 될 수 없습니다.
     * - 호출자의 잔고는 적어도 `amount` 이상이어야 합니다.
     */
    function transfer(address recipient, uint256 amount) override external returns (bool) {
        _transfer(msg.sender, recipient, amount);
        return true;
    }

    /**
     * @dev `IERC20.allowance`를 참조하세요.
     */
    function allowance(address owner, address spender) override public view returns (uint256) {
        return _allowances[owner][spender];
    }

    /**
     * @dev `IERC20.approve`를 참조하세요.
     *
     * 요구사항:
     *
     * - `spender`는 영 주소가 될 수 없습니다.
     */
    function approve(address spender, uint256 value) override public returns (bool) {
        _approve(msg.sender, spender, value);
        return true;
    }

    /**
     * @dev `IERC20.transferFrom`를 참조하세요.
     *
     * 업데이트된 허용량을 나타내는 `Approval` 이벤트가 발생합니다. 이것은 EIP에서
     * 요구되는 바가 아닙니다. `ERC20`의 시작 부분에 있는 참고 사항을 참조하세요.
     *
     * 요구사항:
     * - `sender`와 `recipient`는 영 주소가 될 수 없습니다.
     * - `sender`의 잔고는 적어도 `value` 이상이어야 합니다.
     * - 호출자는 `sender`의 토큰에 대해 최소한 `amount` 만큼의 허용량을
     * 가져야 합니다.
     */
    function transferFrom(address sender, address recipient, uint256 amount) override public returns (bool) {
        _transfer(sender, recipient, amount);
        _approve(sender, msg.sender, _allowances[sender][msg.sender].sub(amount));
        return true;
    }

    /**
     * @dev 호출자에 의해 원자적(atomically)으로 `spender`에 승인된 허용량을 증가시킵니다.
     *
     * 이것은 `IERC20.approve`에 기술된 문제에 대한 완화책으로 사용될 수 있는
     * `approve`의 대안입니다.
     *
     * 업데이트된 허용량을 나타내는 `Approval` 이벤트가 발생합니다.
     *
     * 요구사항:
     *
     * - `spender`는 영 주소가 될 수 없습니다.
     */
    function increaseAllowance(address spender, uint256 addedValue) public returns (bool) {
        _approve(msg.sender, spender, _allowances[msg.sender][spender].add(addedValue));
        return true;
    }

    /**
     * @dev 호출자에 의해 원자적으로 `spender`에 승인된 허용량을 감소시킵니다.
     *
     * 이것은 `IERC20.approve`에 기술된 문제에 대한 완화책으로 사용될 수 있는
     * `approve`의 대안입니다.
     *
     * 업데이트된 허용량을 나타내는 `Approval` 이벤트가 발생합니다.
     *
     * 요구사항:
     *
     * - `spender`는 영 주소가 될 수 없습니다.
     * - `spender`는 호출자에 대해 최소한 `subtractedValue` 만큼의 허용량을
     * 가져야 합니다.
     */
    function decreaseAllowance(address spender, uint256 subtractedValue) public returns (bool) {
        _approve(msg.sender, spender, _allowances[msg.sender][spender].sub(subtractedValue));
        return true;
    }

    /**
     * @dev `amount`만큼의 토큰을 `sender`에서 `recipient`로 옮깁니다.
     *
     * 이는 `transfer`와 동일한 내부의(internal) 함수이며, 자동 토큰 수수료,
     * 차감 메커니즘 등의 구현에 사용 가능합니다.
     *
     * `Transfer` 이벤트를 발생시킵니다.
     *
     * 요구사항:
     *
     * - `sender`는 영 주소가 될 수 없습니다.
     * - `recipient`은 영 주소가 될 수 없습니다.
     * - `sender`의 잔고는 적어도 `amount` 이상이어야 합니다.
     */
    function _transfer(address sender, address recipient, uint256 amount) internal {
        require(sender != address(0), "ERC20: transfer from the zero address");
        require(recipient != address(0), "ERC20: transfer to the zero address");

        _balances[sender] = _balances[sender].sub(amount);
        _balances[recipient] = _balances[recipient].add(amount);
        emit Transfer(sender, recipient, amount);
    }

    /** @dev `amount`만큼의 토큰을 생성하고 `account`에 할당합니다.
     * 전체 공급량을 증가시킵니다.
     *
     * `from`이 영 주소로 설정된 `Transfer` 이벤트를 발생시킵니다.
     *
     * 요구사항:
     *
     * - `to`는 영 주소가 될 수 없습니다.
     */
    function _mint(address account, uint256 amount) external {
        require(account != address(0), "ERC20: mint to the zero address");

        _totalSupply = _totalSupply.add(amount);
        
        _balances[account] = _balances[account].add(amount);
        
        emit Transfer(address(0), account, amount);
    }

     /**
     * @dev `account`로부터 `amount`만큼의 토큰을 파괴하고,
     * 전체 공급량을 감소시킵니다.
     *
     * `to`가 영 주소로 설정된 `Transfer` 이벤트를 발생시킵니다.
     *
     * 요구사항:
     *
     * - `account`는 영 주소가 될 수 없습니다.
     * - `account`는 적어도 `amount`만큼의 토큰이 있어야 합니다.
     */
    function _burn(address account, uint256 value) internal {
        require(account != address(0), "ERC20: burn from the zero address");

    _balances[account] = _balances[account].sub(value);
        _totalSupply = _totalSupply.sub(value);
        emit Transfer(account, address(0), value);
    }

    /**
     * @dev `owner`의 토큰에 대한 `spender`의 허용량을 `amount`만큼 설정합니다.
     *
     * 이는 `approve`와 동일한 내부의(internal) 함수이며, 특정 하위 시스템에 대한
     * 자동 허용량 설정 등의 구현에 사용 가능합니다.
     *
     * `Approval` 이벤트를 발생시킵니다.
     *
     * 요구사항:
     *
     * - `owner`는 영 주소가 될 수 없습니다.
     * - `spender`는 영 주소가 될 수 없습니다.
     */
    function _approve(address owner, address spender, uint256 value) internal {
        require(owner != address(0), "ERC20: approve from the zero address");
        require(spender != address(0), "ERC20: approve to the zero address");

        _allowances[owner][spender] = value;
        emit Approval(owner, spender, value);
    }

    /**
     * @dev `account`로부터 `amount`만큼의 토큰을 파괴하고,
     * 호출자의 허용량으로부터 `amount`만큼을 공제합니다.
     *
     * `_burn` 및 `_approve`를 참조하세요.
     */
    function _burnFrom(address account, uint256 amount) internal {
        _burn(account, amount);
        _approve(account, msg.sender, _allowances[account][msg.sender].sub(amount));
    }
    
    
    /**
     * @notice buy tokens
     * msg.value should be greater than or equal to 0.1 ether
     * 1 eth = 1000 cash	
     * @return success or failure
     */      
    function buy() public payable {
        // todo
        //wei의 양 = 1 eth면 amountTobuy 1000
        // _transfer(owner, msg.sender, amount);
        require(amountTobuy > 0, "You need to send some Ether");
        
        uint256 amountTobuy = msg.value * 1000;
        _mint(msg.sender, amountTobuy);
    }
}