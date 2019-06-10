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

- URL: ```.../user/register```

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

- URL: ```.../user```

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

- URL: ```.../user/info```

- Headers: ```token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8```

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
- Method: **POST**

- URL: ```.../user/info```

- Headers: ```token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8```

    ```Content-Type:application/json```

- Body:
    ```json
    {
        "message": "个性签名",
        "name": "jiuyoung",
        "password": "123456789",
        "phone": "18729576978"
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

## 文章模块

### 1.新建文章

#### Request

- Method: **POST**

- URL: ```.../article```

- Headers: 

  * `Content-Type:application/json`
  * `token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8`

- Body:

  ```json
  {
      "title":"日记16日",
      "publishTime":1559347200000,
      "tag":1,
      "summary":"这是摘要",
      "content":"<p><h1>日记16日</h1></p>"
  }
  ```
  
  **注：作者使用用户ID，标签使用标签ID **

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "新建文章成功！",
      "object": {
          "data": {
              "articleId": 9,
              "author": {
                  "articleNum": 0,
                  "id": 1,
                  "name": "jiuyoung",
                  "starsNum": 0
              },
              "comments": 0,
              "summary": "这是摘要",
              "content": "<p><h1>日记18日</h1></p>",
              "publishTime": 1559347200000,
              "stars": 0,
              "tag": "音乐",
              "title": "日记18日"
          }
      }
  }
  ```
  
  

#### 说明

此接口需要认证

### 2.点赞文章

#### Request

- Method: **POST**

- URL: ```.../article/stars```

- Headers: 
  
  * `token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8`
  * `Content-Type:application/json`
- Params
	
	* `id:文章ID`
	* `up:点赞true|取消false`
- Body:

  ```json
  {
      "articleId":7,
      "starTime":1559347200000
  }
  ```
  
  **example: ?id=7&up=true**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": {
              "articleId": 7,
              "author": {
                  "articleNum": 1,
                  "id": 1,
                  "name": "jiuyoung",
                  "starsNum": 1
              },
              "comments": 1,
              "summary": "这是摘要",
              "content": "<p><h1>日记16日</h1></p>",
              "publishTime": 1559347200000,
              "stars": 1,
              "tag": "音乐",
              "title": "日记16日"
          }
      }
  }
  ```

#### 说明

此接口需要认证

### 3.查看作者所有文章

#### Request

- Method: **GET**

- URL: ```.../article/author```

- Headers: 
  
  * `token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8`
  
- Params
	
	* `pageNum:当前页数`可选
	* `pageSize:页面大小`可选
	
- Body:

  ```json
  
  ```
  
  **Example：`?pageNum=1&pageSize=2`**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": [
              {
                  "articleId": 7,
                  "author": {
                      "articleNum": 0,
                      "id": 1,
                      "name": "jiuyoung",
                      "starsNum": 0
                  },
                  "comments": 0,
                  "summary": "这是摘要",
                  "content": "<p><h1>日记16日</h1></p>",
                  "publishTime": 1559347200000,
                  "stars": 3,
                  "tag": "音乐",
                  "title": "日记16日"
              },
              {
                  "articleId": 8,
                  "author": {
                      "articleNum": 0,
                      "id": 1,
                      "name": "jiuyoung",
                      "starsNum": 0
                  },
                  "comments": 0,
                  "summary": "这是摘要",
                  "content": "<p><h1>日记17日</h1></p>",
                  "publishTime": 1559347200000,
                  "stars": 0,
                  "tag": "音乐",
                  "title": "日记17日"
              }
          ]
      }
  }
  ```

#### 说明

此接口需要认证

### 4.通过文章ID查找文章 

#### Request

- Method: **GET**
- URL: ```.../article/id```
- Headers: 



- Params
	
	* `id:文章ID`
	
- Body:

  ```json
  
  ```
  
  **Example：`?id=2`**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": {
              "articleId": 2,
              "author": {
                  "articleNum": 0,
                  "id": 4,
                  "name": "lzq",
                  "starsNum": 0
              },
              "comments": 1,
              "summary": "这是摘要",
              "content": "rt",
              "publishTime": 1555200000,
              "stars": 0,
              "tag": "讨论",
              "title": "你写代码像cxk"
          }
      }
  }
  ```

#### 说明

此接口不需要认证

### 5.获取推荐文章

#### Request

* Method:**GET**

* URL:`.../article/all`

* Headers:

* Params:

  * `pageNum:页数`
  * `pageSize:页面大小`

* Body:

  ```
  
  ```

  **Example:`?pageNum=1&pageSize=2`**

#### Response

* Body

  ```
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": [
              {
                  "articleId": 7,
                  "author": {
                      "articleNum": 1,
                      "id": 1,
                      "name": "jiuyoung",
                      "starsNum": 1
                  },
                  "comments": 1,
                  "content": "<p><h1>日记16日</h1></p>",
                  "publishTime": 1559347200000,
                  "stars": 1,
                  "summary": "这里是摘要",
                  "tag": "音乐",
                  "title": "日记16日"
              },
              {
                  "articleId": 3,
                  "author": {
                      "articleNum": 0,
                      "id": 4,
                      "name": "lzq",
                      "starsNum": 0
                  },
                  "comments": 4,
                  "content": "rt",
                  "publishTime": 1555200000,
                  "stars": 0,
                  "summary": "这里是摘要",
                  "tag": "讨论",
                  "title": "测试"
              }
          ]
      }
  }
  ```

  

## 评论模块

### 1.新增评论

#### Request

- Method: **POST**

- URL: ```.../comment```

- Headers: 

  * `Content-Type:application/json`
  * `token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8`

- Body:

  ```json
  {
      "articleId":7,
      "message":"文章一点都不好看",
      "time":1559347200000
  }
  ```
  

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "评论成功！",
      "object": {
          "data": {
              "articleId": 7,
              "id": 8,
              "message": "文章一点都不好看",
              "time": 1559347200000,
              "userId": 1
          }
      }
  }
  ```
  
  

#### 说明

此接口需要认证

### 2.删除评论

#### Request

- Method: **DELETE**

- URL: ```.../comment```

- Headers:

  * `token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJzaHVhaV96aGFuZ19tZUAxNjMuY29tIn0.3MdkxWMpNVys_xqYKPd_0F0DUcs-o47Tx95aPJCwm_8`
  
- Param:

  * `id:评论ID `

- Body:

  ```json
  
  ```

  **Example:`?id=7`**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "删除成功！",
      "object": {}
  }
  ```
  
  

#### 说明

此接口需要认证

### 3.通过评论ID查找评论

#### Request

- Method: **GET**

- URL: ```.../comment```

- Headers:

  * 
  
- Param:

  * `id:评论ID `

- Body:

  ```json
  
  ```

  **Example:`?id=7`**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": {
              "articleId": 7,
              "id": 8,
              "message": "文章一点都不好看",
              "time": 1559347200000,
              "userId": 1
          }
      }
  }
  ```
  
  

#### 说明

此接口不需要认证

### 4.查找文章对应的评论

#### Request

- Method: **GET**

- URL: ```.../comment/article```

- Headers:

  * 
  
- Param:

  * `id:文章ID `
  * `pageNum:当前页数`**可选**
  * `pageSize:页面大小`**可选**

- Body:

  ```json
  
  ```

  **Example:`?id=7&pageNum=1&pageSize=2`**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": [
              {
                  "articleId": 3,
                  "id": 3,
                  "message": "你测试代码也像cxk",
                  "time": 1555200000,
                  "userId": 4
              },
              {
                  "articleId": 3,
                  "id": 4,
                  "message": "你测试代码也像cxk",
                  "time": 1555200000,
                  "userId": 4
              }
          ]
      }
  }
  ```
  
  

#### 说明

此接口不需要认证

### 5.查找用户所有的评论

#### Request

- Method: **GET**

- URL: ```.../comment/user```

- Headers:

  * 
  
- Param:

  * `id:用户ID `
  * `pageNum:当前页数`**可选**
  * `pageSize:页面大小`**可选**

- Body:

  ```json
  
  ```

  **Example:`?id=7&pageNum=1&pageSize=2`**

#### Response

- Body

  ```json
  {
      "code": 200,
      "message": "sucess",
      "object": {
          "data": [
              {
                  "articleId": 2,
                  "id": 1,
                  "message": "鸡你太美",
                  "time": 1555200000,
                  "userId": 4
              },
              {
                  "articleId": 3,
                  "id": 3,
                  "message": "你测试代码也像cxk",
                  "time": 1555200000,
                  "userId": 4
              }
          ]
      }
  }
  ```
  
  

#### 说明

此接口不需要认证