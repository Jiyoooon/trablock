# **TraBlock** (`Travel` + `Blockchain`)
 > 이더리움을 활용하여 투명하게 모임용 계좌를 생성하고 관리할 수 있는 DApp 서비스

<p>
  <a href="#" target="_blank">
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.1-blue.svg?cacheSeconds=2592000" />
  </a>

<br>

<br>

### 기술 스택

<div>
    <h3>frontend</h3>
    <a href="#"><img width="120" src="https://vuejs.org/images/logo.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://camo.githubusercontent.com/be384df72ff1748336f5927f7116e79a37cbe1639a5b7db162be2d7afe350f87/68747470733a2f2f63646e2e767565746966796a732e636f6d2f696d616765732f6c6f676f732f6c6f676f2e737667"/></a>&nbsp;&nbsp;
    <br>
    <h3>backend</h3>
	<a href="#"><img width="120" src="https://user-images.githubusercontent.com/55119239/74508917-976b0d80-4f43-11ea-9c97-f479de176bf3.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://miro.medium.com/max/3188/1*frpSc5LXx6_vvqQ29Muifg.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://mariadb.com/wp-content/uploads/2019/11/mariadb-logo-vert_blue-transparent.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://calmlake79.files.wordpress.com/2015/06/redis.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://d1.awsstatic.com/acs/characters/Logos/Docker-Logo_Horizontel_279x131.b8a5c41e56b77706656d61080f6a0217a3ba356d.png"/></a>&nbsp;&nbsp;
    <br>
    <h3>Ethereum Network</h3>
    <a href="#"><img width="120" src="https://upload.wikimedia.org/wikipedia/commons/b/b7/ETHEREUM-YOUTUBE-PROFILE-PIC.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://miro.medium.com/max/4000/0*yqbRInqX0ZRUlVS0"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://remix.ethereum.org/assets/img/icon.png"/></a>&nbsp;&nbsp;
    <br>
    <h3>Deploy Environment</h3>
    <a href="#"><img width="70" src="https://i.pinimg.com/originals/58/b5/48/58b5486d603986fa563abcfbf034a8c2.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Jenkins_logo_with_title.svg/1200px-Jenkins_logo_with_title.svg.png"/></a>&nbsp;&nbsp;
    <a href="#"><img width="120" src="https://www.nginx.com/wp-content/uploads/2018/08/NGINX-logo-rgb-large.png"/></a>&nbsp;&nbsp;
</div>

<br><br>

### 주요 기능

 - **메인 페이지**

   ![main_page](document/capture/mainpage.png)

   <br>

 - **모임 생성**

    - 정보 입력

      ![group_info](document/capture/add_group_info.png)

    - 모임 생성

      > 모임 등록 시 **모임주의 개인키** 입력 => 모임주 계정으로 모임 **스마트 컨트랙트** 배포를 위한 트랜잭션 발생시켜 CA(Contract Account) 생성

      ![add_group](document/capture/add_group.png)

      <br>

- **모임 상세 페이지**

  - 상세 페이지

    ![group_detail](document/capture/group_detail.png)

  - 납부하기

    > 모임원의 EOA(External Owned Account) => 모임의 CA(Contract Account) 로 송금 트랜잭션 발생

    ![deposit](document/capture/deposit.png)

  - 출금하기

    > 모임원 모두가 출금 가능하며, 출금신청 시 모든 모임원의 동의가 필요
    >
    > _**다중서명(Multi-signature) 기능 구현 실패 => 데이터베이스에 출금 동의 테이블을 만들어 임시로 기능을 구현해 놓은 상태**_

    ![withdraw](document/capture/withdraw.png)

    <br>

- **지갑 관리**

  - 충전하기

    > 시스템에서 임의로 **5이더** 씩 충전 => `가나슈(Ganache)`로 얻은 충전되어 있는 계정 사용
    >
    > **TBC** => 테스트넷에 발행해 둔 ERC 20 토큰으로, 이더 충전 후 송금 시에 자동 환전됨(`ETH` -> `TBC`) 

    ![charge](document/capture/charge_account.png)

<br><br>

### 결과

- `Ropsten` 테스트넷 환경에서 구현
- 모임 스마트 컨트랙트 작성
  - **모임원 등록**, **송금**, **출금** 기능 구현 성공
  - **다중서명**, **미납 2회 누적 시 퇴출** 기능 구현 실패 