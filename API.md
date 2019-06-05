[TOC]

# 文档说明

## 服务器API地址

前缀：

```http:// 120.79.138.162:9090```



完整的API地址为：```前缀```+```具体接口路径```

比如，用户注册的接口为：

```http:// 120.79.138.162:9090```+```/user/register/```

->

```http:// 120.79.138.162:9090/user/register/```

## 调用接口说明
- 如果参数格式是**JSON**的话：提交request请求时必须添加header头：**Content-Type:application/json**

- 请求中是否要包含头信息：**token:{accesstoken}**
    - 需要登录的接口会在文档中进行说明
    
    - 当access token无效或者已过期时，返回：
    
        ```json
        {
          "code": 401,
          "message": "无效token，请重新登录!"
        }
        ```

- 所有的接口的返回形式都是统一为：
  
    - 正常返回
    
      ``````json
      {
        "code": 200,
        "message": "success",
        "object": 某种类型的数据，比如字符串，数字，字典等等
      }
      ``````
    
    - 错误返回
    
      ```json
      {
        "code": 500,
        "message": "具体的错误信息字符串",
        "object": 相关数据
      }
      ```
    
      
# API文档

## 用户模块

### 1.用户注册

#### Request

- Method: **POST**

- URL: ```.../user/register/```

- Headers: Content-Type:application/json

- Body:

  ```json
  {
      "account":"shuai_zhang_me@163.com",
      "password":"123456789",
      "message": "个性签名",
      "name": "jiuyoung",
      "phone": "18729576978",
      "registTime": 1559347200000
  }
  ```

#### Response

- Body

  ```json
  {
      "code": "200",
      "message": "sucess",
      "object": {
          "data": {
              "account": "shuai_zhang_me@gmain.com",
              "articleNum": 0,
              "id": 3,
              "message": "个性签名",
              "name": "jiuyoung",
              "password": "123456789",
              "phone": "18729576978",
              "registTime": 1559347200000,
              "starsNum": 0
          }
      }
  }
  ```

#### 说明

### 2.用户登录

#### Request
- Method: **POST**

- URL: ```.../user/```

- Headers: Content-Type:application/json

- Body:
    ```json
    {
    	"account":"shuai_zhang_me@163.com",
    	"password":"123456789"
    }
    ```

#### Response

- Body

  ```json
  {
      "code": "200",
      "message": "登录成功",
      "object": {
          "data": {
              "account": "shuai_zhang_me@163.com",
              "articleNum": 0,
              "id": 1,
              "message": "个性签名",
              "name": "jiuyoung",
              "password": "123456789",
              "phone": "18729576978",
              "registTime": 1559347200000,
              "starsNum": 0
          },
          "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8"
      }
  }
  ```

#### 说明

Token作为识别用户是否登录的凭证，在请求需要认证的接口时需要在Headers中加入```"token":token```

### 3.获取用户信息

#### Request
- Method: **GET**

- URL: ```.../user/```

- Headers: ```"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8"```

- Body:
    ```json
    null
    ```

#### Response

- Body

  ```json
  {
      "code": "200",
      "message": "sucess",
      "object": {
          "data": {
              "account": "shuai_zhang_me@163.com",
              "articleNum": 0,
              "id": 1,
              "message": "个性签名",
              "name": "jiuyoung",
              "password": "123456789",
              "phone": "18729576978",
              "registTime": 1559347200000,
              "starsNum": 0
          }
      }
  }
  ```

#### 说明

此接口需要认证

### 4.修改用户信息

#### Request
- Method: **GET**

- URL: ```.../info```

- Headers: ```"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8"```

    ```Content-Type:application/json```

- Body:
    ```json
    {
    	"account": "shuai_zhang_me@163.com",
        "articleNum": 0,
        "id": 1,
        "message": "个性签名",
        "name": "jiuyoung",
        "password": "123456789",
        "phone": "18729576978",
        "registTime": 1559347200000,
        "starsNum": 0
    }
    ```

#### Response

- Body

  ```json
  {
      "code": "200",
      "message": "sucess",
      "object": {
          "data": {
              "account": "shuai_zhang_me@163.com",
              "articleNum": 0,
              "id": 1,
              "message": "个性签名",
              "name": "jiuyoung",
              "password": "123456789",
              "phone": "18729576978",
              "registTime": 1559347200000,
              "starsNum": 0
          }
      }
  }
  ```

#### 说明

此接口需要认证

