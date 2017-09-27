package test.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring整合JUnit4 Test基类
 * 
 * @author sss
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/applicationContext-core.xml"})
//@TransactionConfiguration(transactionManager="transactionManager") //可选，默认就是这个
//@Transactional
public class BaseSpringTestCase {
	
}
