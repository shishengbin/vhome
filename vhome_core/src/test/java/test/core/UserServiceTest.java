package test.core;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.service.sys.UserService;

public class UserServiceTest extends BaseSpringTestCase {

	@Autowired
	private UserService userservice;

	@Test
	public void findByIdTest() {
		SysUser u = userservice.findById(1L);
		System.out.println("姓名" + u.getUsername() + "创建日期" + u.getCreateTime());
	}
	
	@Test
	public void count(){
		System.out.println("aaa");
	}

}
