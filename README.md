# tracesource
基于区块链的牲畜养殖系统

技术栈：
    后端（中心化）：Java的SSM框架
    区块链（去中心化）：以太坊
    前端：bootstrap
    
区块链使用以太坊的第三方工具Gannache,通过区块链账户的私钥连接（见service/xxxContractService.java）,也可以通过自己搭建私链连接。

项目简介：
  该项目主要分为消费者和加工商两大模块，;加工商分为：养殖场、屠宰场及物流部门；消费者可以可直接进入系统查询肉品从养殖到运输的所有信息，以及验证数据是否篡改；
  每个加工商都有自己的区块链的账户，负责将牛只相关信息上链，以保证数据不可篡改。
  该系统为b/s架构
项目展示：

![首页](https://user-images.githubusercontent.com/58975392/157392742-26b4e093-9495-46e2-9f8e-4c5d14eb1d15.png)
![登录](https://user-images.githubusercontent.com/58975392/157392723-40f7f757-265a-4d1e-be03-09cec53db38d.png)
![信息录入1](https://user-images.githubusercontent.com/58975392/157395099-99071095-6067-4c59-a347-b1c53a14c9fc.png)
![信息显示](https://user-images.githubusercontent.com/58975392/157395148-229f94a2-f69e-4842-9fcf-9d8936ac2cdc.png)

![验证](https://user-images.githubusercontent.com/58975392/157394727-412ddabd-fcd4-49b0-b4bc-d4e35358c6da.png)

![验证1](https://user-images.githubusercontent.com/58975392/157394640-98842eb8-4c97-4796-bf33-3bf566ad6948.png)

  
  
