> @author Luluy233

# fabric环境搭建及合约部署

> 操作系统：Ubuntu20.04
>
> 虚拟机：VMware 16 Pro

## 一、环境搭建

### 1. 先决条件

#### docker

```bash
sudo apt-get install git curl docker-compose -y

# 开启docker
sudo systemctl start docker

# 添加用户名到docker应用组
sudo usermod -a -G docker <username>

# 检查版本是否正确
docker --version
docker-compose --version

# 系统启动同时开启docker
sudo systemctl enable docker
```

#### git

```bash
sudo apt update
sudo apt install git
git --version
```

#### cURL

```bash
sudo apt install curl
curl --version
```



### 2. 安装fabric

1. 新建工作目录为`/home/fabric`，获取远程脚本`install-fabric.sh`

   ```bash
   curl -sSLO https://raw.githubusercontent.com/hyperledger/fabric/main/scripts/install-fabric.sh && chmod +x install-fabric.sh
   ```

2. 运行`install-fabric.sh`

   ```bash
   sudo ./install-fabric.sh docker samples binary
   ```

   需要等待一段时间，完成后可以看到`/home/fabric`目录下出现`fabric-samples`目录

   

### 3. 使用fabric测试网络

1. 打开`test-network`目录，执行`network.sh`

   ```bash
   cd fabric-samples/test-network
   #开启测试网络
   ./network.sh up
   #关闭测试网络
   ./network.sh down
   ```

   ![](/docs/img/image1-1.png)

2. 使用docker产看是否运行

   ```bash
   docker ps -a
   ```



## 二、合约部署到fabric网络

### 1. 创建通道

```bash
#fabric-samples/test-network
./network.sh up
#创建channel：默认名为myChannel
./network.sh createChannel -c <channel-name>
```

![img](/docs/img/imag2-1.png)

```bash
cd ../../test-network
```

### 2.  打包智能合约

```bash
git clone git@github.com:Luluy233/maotai-traceability-blockchain.git
```

下载 maotai-tracibility-blockchain源码到本地机器，将其中的`maotai-contract`文件夹复制到`fabric/fabric-samples/chaincode`目录下

### 3.  返回到test-network所在目录

返回到test-network所在目录，以便可以将链码与其他网络部件打包在一起。

```plain
cd ../../test-network
```

### 4.  环境变量配置

所需格式的链码包可以使用peer CLI创建，使用以下命令将这些二进制文件添加到你的CLI路径。

```plain
export PATH=${PWD}/../bin:$PATH
```

设置FABRIC_CFG_PATH为指向fabric-samples中的core.yaml文件

```plain
export FABRIC_CFG_PATH=$PWD/../config/
```

### 5. 创建链码包

> 注意文件名和路径的修改！！

```bash
peer lifecycle chaincode package maotai-contract.tar.gz --path ../chaincode/maotai-contract/ --lang java --label maotai-contract_1
```

![img](/docs/img/image2-2.png)

命令解释：此命令将在当前目录中创建一个名为`maotai-contract.tar.gz`的软件包。`–lang`标签用于指定链码语言，`–path`标签提供智能合约代码的位置，该路径必须是标准路径或相对于当前工作目录的路径，`–label`标签用于指定一个链码标签，该标签将在安装链码后对其进行标识。建议标签包含链码名称和版本。

### 6. 安装链码包

打包 hyperledger-fabric-contract-java-demo 智能合约后，我们可以在peer节点上安装链码。需要在将认可交易的每个peer节点上安装链码。因为我们将设置背书策略以要求来自Org1和Org2的背书，所以我们需要在两个组织的peer节点上安装链码：peer0.org1.example.com和peer0.org2.example.com

#### 6.1 Org1 peer节点安装链码

1. 设置以下环境变量，以Org1管理员的身份操作peer CLI。

   ```bash
   export CORE_PEER_TLS_ENABLED=true
   export CORE_PEER_LOCALMSPID="Org1MSP"
   export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
   export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
   export CORE_PEER_ADDRESS=localhost:7051
   ```

2. 使用 peer lifecycle chaincode install 命令在peer节点上安装链码。

   ```bash
   peer lifecycle chaincode install maotai-contract.tar.gz
   ```

   **需要等等一段时间！！**

   看到如下信息说明链码安装成功:

   ![img](/docs/img/image2-3.png)

#### 6.2 Org2 peer节点安装链码

设置以下环境变量，以Org2管理员的身份操作peer CLI。

```bash
export CORE_PEER_LOCALMSPID="Org2MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp
export CORE_PEER_ADDRESS=localhost:9051
```

使用 peer lifecycle chaincode install 命令在peer节点上安装链码。

```bash
peer lifecycle chaincode install maotai-contract.tar.gz
```

 **注意：安装链码时，链码由peer节点构建。如果智能合约代码有问题，install命令将从链码中返回所有构建错误。 因为安装 java 链码的时候需要经过 maven 构建以及下载依赖包的过程这个过程有可能会较慢，所以 install 命令有可能会返回一个超时错误:。但是其实链码的 docker 容器内此时还在执行构建任务没有完成。等到构建成功了链码包也就安装成功了。** 

**重复执行，可以确定链码是否成功安装：**

![img](/docs/img/image2-4.png)



### 7. 通过链码定义

安装链码包后，需要通过组织的链码定义。该定义包括链码管理的重要参数，例如名称，版本和链码认可策略。

如果组织已在其peer节点上安装了链码，则他们需要在其组织通过的链码定义中包括包ID。包ID用于将peer节点上安装的链码与通过的链码定义相关联，并允许组织使用链码来认可交易。

#### 7.1 查询包ID

```plain
peer lifecycle chaincode queryinstalled
```

包ID是链码标签和链码二进制文件的哈希值的组合。每个peer节点将生成相同的包ID。你应该看到类似于以下内容的输出：

```bash
Installed chaincodes on peer:
Package ID: maotai-contract_1:cb9e575b305ac6cd6cfef6e0ae02a28b51ea739332c3b64ba48da17df20be425, Label: maotai-contract_1
```

通过链码时，我们将使用包ID，因此，将包ID保存为环境变量。将返回的包ID粘贴到下面的命令中。

**注意：包ID对于所有用户而言都不相同，因此需要使用上一步中从命令窗口返回的包ID来完成此步骤。而不是直接复制命令！！！**

```bash
vim ~/.bashrc
#编辑如下内容
#这里的ID必须是上面得到的！！！
export CC_PACKAGE_ID=maotai-contract_1:cb9e575b305ac6cd6cfef6e0ae02a28b51ea739332c3b64ba48da17df20be425
source ~/.bashrc
```

#### 7.2 Org2 通过链码定义

因为已经设置了环境变量为peer CLI作为Orig2管理员进行操作，所以我们可以以Org2组织级别将 `maotai-contract` 的链码定义通过。使用 `peer lifecycle chaincode approveformyorg`命令通过链码定义：

```plain
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name maotai-contract --version 1.0 --package-id $CC_PACKAGE_ID --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem
```

#### 7.3 Org1 通过链码定义

设置以下环境变量以Org1管理员身份运行：

```plain
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_ADDRESS=localhost:7051
```

用 peer lifecycle chaincode approveformyorg命令通过链码定义

```plain
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name maotai-contract --version 1.0 --package-id $CC_PACKAGE_ID --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem
```

![img](/docs/img/image2-5.png) 

### 8. 将链码定义提交给通道

使用peer lifecycle chaincode checkcommitreadiness命令来检查通道成员是否已批准相同的链码定义：

```plain
peer lifecycle chaincode checkcommitreadiness --channelID mychannel --name maotai-contract --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --output json
```

该命令将生成一个JSON映射，该映射显示通道成员是否批准了checkcommitreadiness命令中指定的参数：

```plain
{
	"approvals": {
		"Org1MSP": true,
		"Org2MSP": true
	}
}
```

 **由于作为通道成员的两个组织都同意了相同的参数，因此链码定义已准备好提交给通道。你可以使用peer lifecycle chaincode commit命令将链码定义提交到通道。commit命令还需要由组织管理员提交。** 

```plain
peer lifecycle chaincode commit -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name maotai-contract --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
```

![img](/docs/img/image2-6.png)

可以使用peer lifecycle chaincode querycommitted命令来确认链码定义已提交给通道。

```plain
peer lifecycle chaincode querycommitted --channelID mychannel --name maotai-contract --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem
```

如果将链码成功提交给通道，该querycommitted命令将返回链码定义的顺序和版本:

![img](/docs/img/image2-7.png)

```plain
Version: 1.0, Sequence: 1, Endorsement Plugin: escc, Validation Plugin: vscc, Approvals: [Org1MSP: true, Org2MSP: true]
```



### 9. 调用链码

```plain
peer chaincode query -C mychannel -n maotai-contract -c '{"Args":["getMaotaiWineById" , "43423"]}'
```

![img](https://cdn.nlark.com/yuque/0/2024/png/40938745/1714980681329-e23f9ed9-4c92-47da-aa3f-fbd5e5529a82.png)

由于还没有创建任何茅台，因此返回status: 500
