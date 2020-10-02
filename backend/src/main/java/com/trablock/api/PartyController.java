package com.trablock.api;

import com.trablock.application.IPartyMemberService;
import com.trablock.application.IPartyService;
import com.trablock.domain.Party;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;
import com.trablock.domain.wrapper.PartyContract;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PartyController {
    public static final Logger logger = LoggerFactory.getLogger(PartyController.class);

    private IPartyService partyService;

    @Autowired
    private IPartyMemberService partyMemberService;

    @Autowired
    public PartyController(IPartyService partyService) {
        Assert.notNull(partyService, "partyService 개체가 반드시 필요!");
        this.partyService = partyService;
    }

    // 전체 모임 리스트 검색
    @ApiOperation(value = "전체 모임 리스트 검색")
    @GetMapping("/party")
    public List<Party> list() {
        List<Party> partyList = partyService.list();

        if (partyList == null || partyList.isEmpty() )
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
        for (long partyId:partyIdList) {
            partyList.add(partyService.get(partyId));
        }

        return partyList;
    }

    // 특정이름(name)이 포함된 모임 리스트 검색
    @ApiOperation(value = "특정이름(name)이 포함된 모임 리스트 검색")
    @GetMapping("/party/searchName/{name}")
    public List<Party> list(@PathVariable String name) {
        List<Party> partyList = partyService.get(name);

        if (partyList == null || partyList.isEmpty() )
            throw new EmptyListException("NO DATA");

        return partyList;
    }

    // 모임등록
    @ApiOperation(value = "모임 등록")
    @PostMapping("/party")
    public Party create(@RequestBody Party party) {
    	System.out.println(party.getMembers().size());

        Party temp = partyService.add(party, party.getMembers());

        // smart contract 배포

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
