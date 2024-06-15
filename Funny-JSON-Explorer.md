# Funny-JSON-Explorer

### 设计要求

对已有FJE实现进行设计重构，改用迭代器+策略模式。

#### 设计模式

使用**工厂方法模式**、**迭代器模式**、**策略模式**，完成功能的同时，使得程序易于扩展和维护。

### 类图

![uml.drawio](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje2\uml.drawio.png)

### 设计模式说明

#### 工厂方法模式

抽象产品接口为JSONExplorer，抽象工厂接口为JSONStyleFactory，具体产品类为TreeJSONExplorer和RectangleJSONExplorer，具体工厂类为TreeJSONStyleFactory和RectangleJSONStyleFactory。由于这里只涉及单一产品，所以没有使用抽象工厂模式。

#### 迭代器模式

将JSON数据用JSONObject和JSONArray存储后，使用迭代器进行访问，抽象迭代器为JSONIterator，具体迭代器有JSONObjectIterator和JSONArrayIterator。

#### 策略模式

在这个FJE中采用策略模式来选择使用哪个explorer的display()方法，抽象策略(Strategy)类为JSONExplorer，具体策略(Concrete Strategy)类包括TreeJSONExplorer和RectangleJSONExplorer，环境(Context)类为JSONContext。

在这个设计中，添加新的抽象工厂并实现对应的explorer即可添加新的Style。对于图标族的添加，只需要修改项目中配置文件config.json即可：

```json
[
  {
    "name": "circle",
    "nodeIcon": "○",
    "leafIcon": "●"
  },
  {
    "name": "square",
    "nodeIcon": "□",
    "leafIcon": "■"
  },
  {
    "name": "math",
    "nodeIcon": "+",
    "leafIcon": "*"
  }
]
```

### 运行截图

![1](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje2\1.png)

![2](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje2\2.png)

![3](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje2\3.png)

![4](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje2\4.png)

### 源代码库

