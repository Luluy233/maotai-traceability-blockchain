> @author Luluy233

# MaotaiTraceability_V1

## POST 上架茅台

POST /maotai/shelf

> Body 请求参数

```json
{
  "bottleId": "string",
  "retailerId": "string",
  "retailerName": "string",
  "retailerTel": "string",
  "retailerLocation": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» bottleId|body|string| 是 |none|
|» retailerId|body|string| 是 |none|
|» retailerName|body|string| 是 |none|
|» retailerTel|body|string| 是 |none|
|» retailerLocation|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|

## POST 生产茅台

POST /maotai/produce

> Body 请求参数

```json
{
  "producerId": "string",
  "producerName": "string",
  "producerTel": "string",
  "rawMaterials": "string",
  "produceLocation": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» producerId|body|string| 是 |none|
|» producerName|body|string| 是 |none|
|» producerTel|body|string| 是 |none|
|» rawMaterials|body|string| 是 |none|
|» produceLocation|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "bottleId": "string"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» bottleId|string|true|none|新生成的茅台编号|none|

## POST 购买茅台

POST /maotai/buy

> Body 请求参数

```json
{
  "bottleId": "string",
  "cosumerId": "string",
  "consumerName": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» bottleId|body|string| 是 |none|
|» cosumerId|body|string| 是 |none|
|» consumerName|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|

## GET 单瓶茅台溯源

GET /maotai/trace

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|bottleId|query|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "bottleId": "string",
    "producer": {
      "producerId": "string",
      "producerName": "string",
      "producerTel": "string",
      "rawMaterials": "string",
      "produceLocation": "string",
      "produceTime": "string"
    },
    "retailer": {
      "retailerId": "string",
      "retailerName": "string",
      "retailerTel": "string",
      "retailerLocation": "string",
      "retailTime": "string"
    },
    "consumer": {
      "consumerId": "string",
      "consumerName": "string",
      "consumeTime": "string"
    }
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[MaotaiWine](#schemamaotaiwine)|true|none||none|
|»» bottleId|string|true|none||none|
|»» producer|object|true|none||none|
|»»» producerId|string|true|none||none|
|»»» producerName|string|true|none||none|
|»»» producerTel|string|true|none||none|
|»»» rawMaterials|string|true|none||none|
|»»» produceLocation|string|true|none||none|
|»»» produceTime|string|true|none||none|
|»» retailer|object|true|none||none|
|»»» retailerId|string|true|none||none|
|»»» retailerName|string|true|none||none|
|»»» retailerTel|string|true|none||none|
|»»» retailerLocation|string|true|none||none|
|»»» retailTime|string|true|none||none|
|»» consumer|object|true|none||none|
|»»» consumerId|string|true|none||none|
|»»» consumerName|string|true|none||none|
|»»» consumeTime|string|true|none||none|

## GET 获得指定状态茅台列表

GET /maotai/status-wines

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|status|query|string| 否 |PRODUCED/ONSALE/SOLD|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "bottleId": "string",
      "status": "string",
      "producer": {
        "producerId": "string",
        "producerName": "string",
        "producerTel": "string",
        "rawMaterials": "string",
        "produceTime": "string",
        "produceLocation": "string"
      },
      "retailer": {
        "retailerId": "string",
        "retailerName": "string",
        "retailerTel": "string",
        "retailerLocation": "string",
        "retailTime": "string"
      },
      "consumer": {
        "consumerId": "string",
        "consumerName": "string",
        "consumeTime": "string"
      }
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» bottleId|string|true|none||none|
|»» status|string|true|none||none|
|»» producer|object|true|none||none|
|»»» producerId|string|true|none||none|
|»»» producerName|string|true|none||none|
|»»» producerTel|string|true|none||none|
|»»» rawMaterials|string|true|none||none|
|»»» produceTime|string|true|none||none|
|»»» produceLocation|string|true|none||none|
|»» retailer|object|true|none||none|
|»»» retailerId|string|true|none||none|
|»»» retailerName|string|true|none||none|
|»»» retailerTel|string|true|none||none|
|»»» retailerLocation|string|true|none||none|
|»»» retailTime|string|true|none||none|
|»» consumer|object|true|none||none|
|»»» consumerId|string|true|none||none|
|»»» consumerName|string|true|none||none|
|»»» consumeTime|string|true|none||none|

## GET 获取指定用户购买的茅台列表

GET /maotai/user-wines

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|query|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "bottleId": "string",
      "status": "string",
      "producer": {
        "producerId": "string",
        "producerName": "string",
        "producerTel": "string",
        "rawMaterials": "string",
        "produceTime": "string",
        "produceLocation": "string"
      },
      "retailer": {
        "retailerId": "string",
        "retailerName": "string",
        "retailerTel": "string",
        "retailerLocation": "string",
        "retailTime": "string"
      },
      "consumer": {
        "consumerId": "string",
        "consumerName": "string",
        "consumeTime": "string"
      }
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» bottleId|string|true|none||none|
|»» status|string|true|none||none|
|»» producer|object|true|none||none|
|»»» producerId|string|true|none||none|
|»»» producerName|string|true|none||none|
|»»» producerTel|string|true|none||none|
|»»» rawMaterials|string|true|none||none|
|»»» produceTime|string|true|none||none|
|»»» produceLocation|string|true|none||none|
|»» retailer|object|true|none||none|
|»»» retailerId|string|true|none||none|
|»»» retailerName|string|true|none||none|
|»»» retailerTel|string|true|none||none|
|»»» retailerLocation|string|true|none||none|
|»»» retailTime|string|true|none||none|
|»» consumer|object|true|none||none|
|»»» consumerId|string|true|none||none|
|»»» consumerName|string|true|none||none|
|»»» consumeTime|string|true|none||none|

# 数据模型

<h2 id="tocS_MaotaiWine">MaotaiWine</h2>

<a id="schemamaotaiwine"></a>
<a id="schema_MaotaiWine"></a>
<a id="tocSmaotaiwine"></a>
<a id="tocsmaotaiwine"></a>

```json
{
  "bottleId": "string",
  "status": "string",
  "producer": {
    "producerId": "string",
    "producerName": "string",
    "producerTel": "string",
    "rawMaterials": "string",
    "produceTime": "string",
    "produceLocation": "string"
  },
  "retailer": {
    "retailerId": "string",
    "retailerName": "string",
    "retailerTel": "string",
    "retailerLocation": "string",
    "retailTime": "string"
  },
  "consumer": {
    "consumerId": "string",
    "consumerName": "string",
    "consumeTime": "string"
  }
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|bottleId|string|true|none||none|
|status|string|true|none||none|
|producer|object|true|none||none|
|» producerId|string|true|none||none|
|» producerName|string|true|none||none|
|» producerTel|string|true|none||none|
|» rawMaterials|string|true|none||none|
|» produceTime|string|true|none||none|
|» produceLocation|string|true|none||none|
|retailer|object|true|none||none|
|» retailerId|string|true|none||none|
|» retailerName|string|true|none||none|
|» retailerTel|string|true|none||none|
|» retailerLocation|string|true|none||none|
|» retailTime|string|true|none||none|
|consumer|object|true|none||none|
|» consumerId|string|true|none||none|
|» consumerName|string|true|none||none|
|» consumeTime|string|true|none||none|

