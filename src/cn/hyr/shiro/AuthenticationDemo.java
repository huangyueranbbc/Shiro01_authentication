package cn.hyr.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @category 完成用户认证功能
 * @author huangyueran
 *
 */
public class AuthenticationDemo {

	public static void main(String[] args) {
		// 1.创建SecurityManager工厂 安全管理器 读取相应的配置文件 也可以读取数据库
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

		// 2.通过SecurityManager工厂获取SecurityManager的实例。
		SecurityManager securityManager = factory.getInstance();

		// 3.将SecurityManager对象设置到运行环境中。
		SecurityUtils.setSecurityManager(securityManager);

		// 4.通过SecurityUtils获取主体Subject
		Subject subject = SecurityUtils.getSubject();

		// 5.输入用户身份和凭证。这里是用户输入的信息，shiro.ini相当于数据库中的信息。
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1112");

		try {
			// 6.进行用户身份认证 登录
			subject.login(token);

			// 7.通过subject判断用户是否通过验证
			if (subject.isAuthenticated()) { // 如果用户认证通过
				System.out.println("用户登录成功");
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("用户名或密码错误!!!!!!");
		}

	}
}
