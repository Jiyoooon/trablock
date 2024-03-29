package com.trablock.api;

import com.trablock.application.IPartyContractService;
import com.trablock.application.IPartyMemberService;
import com.trablock.application.IPartyService;
import com.trablock.domain.Party;
import com.trablock.domain.Withdraw;
import com.trablock.domain.exception.EmptyListException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PartyController {
	public static final Logger logger = LoggerFactory.getLogger(PartyController.class);

	private IPartyService partyService;

	@Autowired
	private IPartyContractService partyContractService;

	@Autowired
	private IPartyMemberService partyMemberService;

	@Autowired
	public PartyController(IPartyService partyService) {
		Assert.notNull(partyService, "partyService 개체가 반드시 필요!");
		this.partyService = partyService;
	}

	// 출금 동의 요청
	@ApiOperation(value = "출금 동의 요청")
	@GetMapping("/withdraw/agree")
	public void agreeWithdraw(long userId, long partyId, int isagree) {
		partyService.agreeWithdraw(userId, partyId, isagree);
	}

	// 출금 신청
	@ApiOperation(value = "모임 계좌 출금 신청")
	@PostMapping("/withdraw")
	public void withdraw(@RequestBody Withdraw withdraw) {
		// 파티 객체 가져와서 withdraw = true로 바꾸기
		// userId는 isagree = true로
		partyService.registerWithdraw(withdraw);
	}

	//송금
	@ApiOperation(value = "모임 계좌에 송금")
	@GetMapping("/party/pay")
	public void pay(@RequestParam(value = "userId") Long userId, @RequestParam(value = "partyId") Long partyId,
			@RequestParam(value = "privateKey") String privateKey, @RequestParam(value = "value") Long value) {
		partyContractService.pay(userId, partyId, privateKey, value);
	}

	// 전체 모임 리스트 검색
	@ApiOperation(value = "전체 모임 리스트 검색")
	@GetMapping("/party")
	public List<Party> list() {
		List<Party> partyList = partyService.list();

		if (partyList == null || partyList.isEmpty())
			throw new EmptyListException("NO DATA");

		return partyList;
	}

	// partyId로 특정 모임 검색
	@ApiOperation(value = "특정 모임 검색")
	@GetMapping("/party/searchByPartyId")
	public Party getParty(long partyId) {
		Party party = partyService.get(partyId);

		return party;
	}

	/**
	 *
	 * @param id User 테이블의 유저 아이디
	 * @return
	 */
	// 특정 id의 모임 검색
	@ApiOperation(value = "특정 id의 모임 검색")
	@GetMapping("/party/searchId")
	public List<Party> get(int id) {

		List<Party> partyList = new ArrayList<>();

		// 1. PartyMember에서 해당되는 모임 아이디들을 가져온다.
		List<Long> partyIdList = partyMemberService.getPartyIdListByUserId(id);

		// 2. for문을 돌려서 해당되는 party객체들을 찾아 partyList에 채운다.
		for (long partyId : partyIdList) {
			partyList.add(partyService.get(partyId));
		}

		return partyList;
	}

	// 특정이름(name)이 포함된 모임 리스트 검색
	@ApiOperation(value = "특정이름(name)이 포함된 모임 리스트 검색")
	@GetMapping("/party/searchName/{name}")
	public List<Party> list(@PathVariable String name) {
		List<Party> partyList = partyService.get(name);

		if (partyList == null || partyList.isEmpty())
			throw new EmptyListException("NO DATA");

		return partyList;
	}

	// 모임등록
	@ApiOperation(value = "모임 등록")
	@PostMapping("/party")
	public Party create(@RequestBody Party party) {

		partyService.add(party, party.getMembers());
		Party temp = partyService.get(party.getId());
		System.out.println(party.toString());

		// smart contract 배포
		partyContractService.setPartyContract(party, party.getPrivatekey());

		return temp;
	}

	// 모임정보수정
	@ApiOperation(value = "모임 정보 수정")
	@PutMapping("/party")
	public Party update(@RequestBody Party party) {
		return partyService.update(party);
	}

	// 모임삭제
	@ApiOperation(value = "모임 삭제")
	@DeleteMapping("/party/{id}")
	public void delete(@PathVariable int id) {
		partyService.delete(id);
	}

}
