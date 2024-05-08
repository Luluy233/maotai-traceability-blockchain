---
typora-root-url: docs\img
---

# MaotaiTraceability说明文档

## 1. 设计思路

### 1.1. 业务流程设计

使用状态转移来表示茅台酒的生命周期，如下图所示：

![img](/image3-1.png)

MaotaiTraceability应用程序调用茅台智能合约来提交茅台交易及溯源请求。

![img](/image3-2.png)

### 1.2. 项目架构设计

![img](/image3-3.png)

1. 前端：Vue+ElementPlus+Axios（`maotai-traceability-blockchain/maotai-frontend`）
2. 智能合约：使用Springboot+maven+hyperledger fabric（`maotai-traceability-blockchaiin/maotai-contract`），智能合约fabric使用虚拟机VMware16PRO+Ubuntu20.04+fabric v2.5.7进行部署（部署文档见文件夹`maotai-traceability-blockchain/docs/fabric_deployment_v1.md`）。
3. 后端应用逻辑：Springboot+maven+hyperledger fabric（`maotai-traceability-blockchain/maotai-backend`）



### 1.3. Fabric区块链设计

#### 1.3.1. 区块链设计

Fabric系统主要由两个应用构成：Orderer和Peer，其中Orderer负责交易共识并生成区块，Peer节点负责模拟执行交易和记账，这种划分可以使整个平台拥有更好的弹性和扩展性。搭建Fabric网络首先需要生成公私钥和证书，通过fabric-ca服务来生成相关证书，Fabric网络中的成员通过提供基于数字证书的信息来表明自己的身份。然后需要创建一个通道（myChannel）并生成创世区块，再把一个orderer排序节点和2个peer节点加入通道中，2个peer记账节点对应五个组织Org1、Org2，并作为锚节点，负责代表组织和其他组织进行信息交换。最后给每个peer节点部署安装智能合约并实例化。

#### 1.3.2. 智能合约设计

智能合约的实现，本质是通过赋予对象数字特性，把对象程序化并部署在区块链上，成为网上共享的资源，使单一用户无法对其直接进行修改，只能通过外部事件触发合约的生成、发布、执行来接受、存储、执行数据，进而改变区块链网络中的数字对象的数值和状态。

根据功能模块分析，需要将农产品的生产信息、零售信息、消费信息上传至区块链网络上，由于只是一个demo系统，因此只使用一个智能合约来实现设计功能，以下是智能合约的部分设计字段。

| 字段名称         | 数据类型 | 说明                               |
| ---------------- | -------- | ---------------------------------- |
| bottleId         | String   | 茅台酒编号                         |
| status           | String   | 茅台酒状态（PRODUCED/ONSALE/SOLD） |
| producerId       | String   | 生产商编号                         |
| producerName     | String   | 生产商名称                         |
| producerTel      | String   | 生产商电话                         |
| rawMaterials     | String   | 原料                               |
| producerLocation | String   | 生产地址                           |
| produceTime      | long     | 生产时间                           |
| retailerId       | String   | 零售商编号                         |
| retailerName     | String   | 零售商名称                         |
| retailerTel      | String   | 零售商电话                         |
| retailerLocation | String   | 零售地址                           |
| retailTime       | String   | 上架时间                           |
| consumerId       | String   | 消费者编号                         |
| consumerName     | String   | 消费者名称                         |
| consumeTime      | String   | 消费时间                           |



## 2. 程序界面说明

由于只是个简单的demo系统，因此并没有实现角色管理系统、不同角色通过测试时手动输入ID来区分。

### 2.1. 首页![img](/image4-1.png)

### 2.2. 生产茅台

生产商用户选择“生产茅台”菜单，可以进入对应界面输入生产信息表单，包括生产商编号（以PRD-开头的字符串）、生产商名称、生产商电话、生产商地址、原材料描述等基本信息，提交表单后会自动生成对应的生产时间以及本瓶茅台酒编号，并将该茅台酒生产信息提交到区块链中。

![img](/image4-2.png)

错误提示：

![img](/image4-3.png)

### 2.3. 上架茅台

#### 2.3.1. 分页查看所有已生产茅台

零售商用户选择“上架茅台”菜单，可以进入对应界面分页查看所有已生产的茅台列表，包括生产时间、茅台编号、生产商编号（以PRD-开头的字符串）、生产商名称、生产商地址、原材料描述等基本信息。

![img](/image4-4.png)

#### 2.3.2. 选择已生产茅台进行上架

零售商用户可以选择指定茅台的对应的“上架”操作按钮填写上架信息，包括零售商编号（以RTL-开头的字符串）、零售商商名称、零售商电话、零售商地址等基本信息，提交表单后会自动生成对应的上架时间，并将生产信息及上架信息提交到区块链中。

![img](/image4-5.png)

### 2.4. 购买茅台

#### 2.4.1. 分页查看所有出售中的茅台

消费者用户选择“购买茅台”菜单，可以进入对应界面分页查看所有已上架的茅台列表，包括上架时间、茅台编号、零售商商编号（以RTL-开头的字符串）、零售商名称、零售商地址等基本信息。

![img](/image4-6.png)

#### 2.4.2. 购买茅台

消费者用户可以选择指定茅台的对应的“购买”操作按钮填写消费信息，包括消费者编号（以CUS-开头的字符串）、消费者昵称等基本信息，提交表单后会自动生成对应的消费时间，并将该瓶茅台及相应的生产、上架、消费信息提交到区块链中。

![img](/image4-7.png)

### 2.5. 茅台溯源

用户选择“茅台溯源”菜单，可以进入对应界面输入对应的茅台酒编号，点击搜索按钮会从区块链中追溯该茅台的基本信息并显示在界面中。

![img](/image4-8.png)

## 3. 附录

1. **API设计文档**：`maotai-traceability-blockchain/docs/API_v1.md` []()
2. **Fabric部署文档**：`maotai-traceability-blockchain/docs/fabric_deployment_v1.md`

   