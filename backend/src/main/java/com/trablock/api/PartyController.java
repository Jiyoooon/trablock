package com.trablock.api;

import com.trablock.application.IPartyService;
import com.trablock.domain.Party;
import com.trablock.domain.exception.EmptyListException;
import com.trablock.domain.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PartyController {
    public static final Logger logger = LoggerFactory.getLogger(PartyController.class);

    private IPartyService partyService;

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

    // 특정 id의 모임 검색
    @ApiOperation(value = "특정 id의 모임 검색")
    @GetMapping("/party/searchId/{id}")
    public Party get(@PathVariable int id) {

        Party party = partyService.get(id);
        if (party == null) {
            logger.error("NOT FOUND ID: ", id);
            throw new NotFoundException(id + " 모임 정보를 찾을 수 없습니다.");
        }

        return party;
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
        return partyService.add(party);
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