# Development Log

> ### 2020/08/31 MON   
> - BlockChain Skeleton Code - git clone Complete.   
> - README.md Updated.   
> - Virtual Box 설치 완료   
> - Vagrant 설치 완료   
> - Virtual Box, Vagrant 연동 완료   
> - 2 Node (eth0, eth1) 생성 완료   
> - Virtual Box, Vagrant 구동 확인 완료   

> ### 2020/09/01 TUE   
> - eth0, eth1 노드 설정 완료   
> - Geth 설치 완료.   
> - 환경변수 설정 -> 오류로 인해 중단   
> - Geth 설정 완료.   
> - 마이닝 완료   
> - 마이닝 후 잔액 확인 -> 오류   
> - 노드 연결 -> 오류   

> ### 2020/09/02 WED   
> - skeleton code FrontEnd 실행 완료   
> - skeleton code BackEnd JRE 버전 동기화 완료   
> - BackEnd에 Getter, Setter 추가 완료.   

> ### 2020/09/03 THUR   
> - skeleton code FrontEnd config 수정 완료   
> - Geth 환경변수 설정 완료
> - 마이닝 후 잔액 증가 안되는 오류 해결
> - 노드 연결 완료
> - 트랜잭션 설정 진행중...


## 목차
- [개요](#개요)
- [기능](#기능)
- [유사 서비스](#유사-서비스) 
- [향후 전망](#향후-전망)
- [기술 스택](#기술-스택)
- [기술 설명](#기술-설명)
	- [ERD](#erd)
	- [디렉토리 구조도](#디렉토리-구조도)
	- [기타](#기타)
- [테스트 방법](#테스트-방법)

## 개요
> 가상 머신을 활용하여 개인적으로 실습할 수 있는 이더리움 네트워크 환경을 구축함.  
> 이더리움 프로토콜의 공식 구현체인 Go-Ethereum을 설치하고 이더리움 환경에서 수행할 수 있는 다양한 커맨드를 확인하며 블록체인의 구조를 익힘   
> 스마트 컨트랙트를 작성하는 방법을 학습함으로써 블록체인의 구성과 동작 원리, 활용 방법을 익힘   
> 제공된 스켈레톤 프로젝트를 통해 블록체인 환경을 웹과 어떻게 연동할 지 알 수 있음   

## 기능
> 프로젝트의 기능들을 설명해주세요  
> 스크린샷이나 gif등으로 한눈에 볼 수 있게 하면 더 좋습니다

## 유사 서비스
> #### [헥슬란트의 블록체인 서비스 '옥텟'](https://octet.hexlant.com/)   
> ![옥텟 이미지](https://plog-image.s3.ap-northeast-2.amazonaws.com/%EC%98%A5%ED%85%9F.PNG)    
> ![](https://plog-image.s3.ap-northeast-2.amazonaws.com/%EC%98%A5%ED%85%9F2.PNG)   
> ![](https://plog-image.s3.ap-northeast-2.amazonaws.com/%EC%98%A5%ED%85%9F3.PNG)   
> ![](https://plog-image.s3.ap-northeast-2.amazonaws.com/%EC%98%A5%ED%85%9F4.PNG)   
> 헥슬란트의 블록체인 서비스 '옥텟'은 개발자는 라이브러리 개발없이 블록체인 서비스 개발에 집중할 수 있고,    
> 기획자는 메인넷 제약 없이 블록체인 비즈니스를 연구할 수 있으며,   
> 운영자는 CS부터 고객지원을 최대의 품질로 대응할 수 있는 블록체인 개발자도구입니다.

## 향후 전망 
> 스마트 컨트랙트가 아직 불안정한 부분이 있습니다.   
> 노드끼리 연결이 되었으나 송금을 할때 pending에서 멈추는 경우가 가끔 생겨서 다음 프로젝트에는 좀더 꼼꼼히 공부한 후 해결할 예정입니다.   

## 기술 스택
> Oracle VM VirtualBox, Vagrant로 가상머신을 통해 Ubuntu 환경을 구성하였습니다.   
> Go-Ethereum, Solidity 등으로 블록체인 이더리움을 구현하였습니다.   
> BackEnd는 Spring STS로 구현하였습니다.   
> DB는 Mysql로 연결하였습니다.   
> FrontEnd는 Vue를 사용하였습니다.   


## 개발 단계
### 1. 개발 환경 구성
#### 1) 스켈레톤 프로젝트 내려받기   
``` 
* 원하는 위치에 스켈레톤 프로젝트 다운로드 
> git clone https://lab.ssafy.com/s03-blockchain-sub1/blockchain-skeleton.git
```
#### 2) 가상 머신 구성
#### - VirtualBox 설치
#### - Vagrant 설치
```
* 설치 여부 및 버전 확인
> vagrant version

* 호스트와 가상 머신 간 파일 전송 플러그인 설치
> vagrant plugin install vagrant-scp
```
#### - 가상 머신 생성 및 구동
```
* 원하는 작업 디렉토리에서 Vagrant 초기화 (설정 파일 생성)
> vagrant init


* 생성된 Vagrantfile의 내용 수정

VAGRANT_API_VERSION = "2"

vms = {
  'eth0' => '10',
  'eth1' => '11'
}

Vagrant.configure(VAGRANT_API_VERSION) do |config|
  config.vm.box = "ubuntu/bionic64"
  vms.each do |key, value|
    config.vm.define "#{key}" do |node|
      node.vm.network "private_network", ip: "192.168.50.#{value}"
      if "#{key}" == "eth0"
        node.vm.network "forwarded_port", guest: 8545, host: 8545
      end
      node.vm.hostname = "#{key}"
      node.vm.provider "virtualbox" do |nodev|
        nodev.memory = 2048
      end
    end
  end
end


* 가상 머신 구동 명령어 실행
> vagrant up


* 가상머신 구동 상태 확인
> vagrant status


* 가상 머신 접속(eth, eth1)
> vagrant ssh eth0
> vagrant ssh eth1
```
#### - eth0, eth1 노드별 Geth 설치
```
* ethereum 폴더 생성 후 해당 폴더로 이동
> mkdir ethereum && cd ethereum


* go-ethereum git에서 받아오기
> git clone https://github.com/ethereum/go-ethereum.git


* Geth 설치
> sudo apt-get update
> sudo apt-get install software-properties-common
> sudo add-apt-repository -y ppa:ethereum/ethereum
> sudo apt-get install ethereum
> geth version


* Go Language 설치
> wget https://dl.google.com/go/go1.14.7.linux-amd64.tar.gz
> tar xf go1.14.7.linux-amd64.tar.gz
> sudo mv go /usr/local/go-1.14


* 환경변수 설정
> export GOROOT=/usr/local/go-1.14
> export PATH=$GOROOT/bin:$PATH

* 빌드하기
# make all 에러 방지를 위한 build-essential 설치
> sudo apt-get install build-essential

# 폴더로 이동 후 빌드
> cd go-ethereum/
> make all


* Geth 환경변수 설정
> export GETH=/home/vagrant/ethereum/go-ethereum
> export PATH="$PATH:$GETH/build/bin"
>
```


