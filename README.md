# memo-jdbc-spring-app
一个Spring的JDBC案例

## 一个项目变成Spring项目的步骤 

###  XML配置方式依赖注入
1. 调整包和目录结构，将其变成一个maven项目
2. 编写pom文件。导入spring依赖。依赖配置见spring.io官网
3. 在resource目录下创建application-context.xml文件，用来配置bean
4. 创建一个ClassPathXMLApplication容器。使用getBean方法获取容器中的对象

### 注解方式依赖注入
1. 调整包和目录结构，将其变成一个maven项目
2. 编写pom文件。导入spring依赖。依赖配置见spring.io官网
3. 在resource目录下创建application-context.xml文件，在其中写入<contex:component-scan base-package = "packageName" />
4. 给相应的类加上@Component注解让组件，给类中的成员变量加上@Autowired注解。用来装配成员变量，成员变量的装配可以使用XML方式，也可以使用注解方式，视具体情况，如果是，自定义类就可以用注解方式，其他的用XML方式装配。当成员变量有多个对象时，必须加上@Qualified注解，用来表示注入那个Bean。
5. 创建一个ClassPathXMLApplication容器。使用getBean方法获取容器中的对象