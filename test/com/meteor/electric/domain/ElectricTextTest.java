package com.meteor.electric.domain;

import java.util.Date;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.meteor.electric.dao.start.ElectricTextDao;
import com.meteor.electric.domain.start.ElectricText;
import com.meteor.electric.service.start.ElectricTextService;
import com.meteor.electric.util.GenericTypeConversion;

public class ElectricTextTest {
	
	private SessionFactory sessionFactory;

	/*@Before
	public void before(){
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@After
	public void after(){
		this.sessionFactory.close();
	}*/
	
	@Test
	public void testEnvironment(){
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		ElectricText elec = new ElectricText();
		elec.setTextName("测试");
		elec.setTextDate(new Date());
		elec.setTextRemark("项目开始环境搭建");
		session.save(elec);
		
		tr.commit();
	}
	
	@Test
	public void electricTextDaoTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	
		ElectricTextDao dao = context.getBean(ElectricTextDao.DAO_NAME, ElectricTextDao.class);
		System.out.println(dao);
		ElectricText electricText = dao.findSingleObjectById("44288189504bbf5e01504bbf6c650000");
		System.out.println(electricText);
		
		ClassPathXmlApplicationContext path = (ClassPathXmlApplicationContext)context;
		path.close();
	}
	
	@Test
	public void electricTextServiceTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		ElectricTextService electricTextServiceImpl =  context.getBean(ElectricTextService.SERVICE_NAME,ElectricTextService.class);
		System.out.println(electricTextServiceImpl.getClass());
		
		ElectricText electricText = new ElectricText();
		electricText.setTextName("service层测试");
		electricText.setTextDate(new Date());
		electricText.setTextRemark("service层测试关于注解 ");
		
		electricTextServiceImpl.saveElectric(electricText);
		
		ClassPathXmlApplicationContext path = (ClassPathXmlApplicationContext)context;
		path.close();
	}
	
	@Test
	public void electricTextDelete(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ElectricTextDao dao = context.getBean(ElectricTextDao.DAO_NAME, ElectricTextDao.class);
		
		System.out.println(dao.findAllEntityObject());
		
		ClassPathXmlApplicationContext path = (ClassPathXmlApplicationContext)context;
		path.close();
	}
	
	@Test
	public void testLog4j2() throws Exception{
		//ReflectUtils.obtainClassDeclarsFields(ElectricText.class);
		//Object ob = ReflectUtils.invokeSpecificMethod("getTextName", new ElectricText(), new Class<?>[]{}, new Object[]{});
		//ReflectUtils.obtainMethodDeclare(ElectricText.class.getMethod("getTextName",new Class[]{}));
		ElectricText electricText = new ElectricText();
		/*electricText.setTextName("service层测试");
		electricText.setTextDate(new Date());
		electricText.setTextRemark("service层测试关于注解 ");*/
		//new ElectricTextServiceImpl().findCollectionCustomCondition(electricText);
	}
	
	@Test
	public void testCustomCondition(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ElectricTextService service = context.getBean(ElectricTextService.SERVICE_NAME, ElectricTextService.class);
		
		ElectricText electricText = new ElectricText();
		electricText.setTextName("service");
		electricText.setTextRemark("service");
		electricText.setTextDate(new Date());
		
		System.out.println(GenericTypeConversion.cunstomConditionFindAssemble(electricText, true));
		
		//service.findCollectionCustomCondition(electricText);
		
		ClassPathXmlApplicationContext path = (ClassPathXmlApplicationContext)context;
		path.close();
	}
	
	@Test
	public void testBasicDataSource(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		BasicDataSource dataSource = context.getBean("dataSource", BasicDataSource.class);
		
		System.out.println(dataSource.getNumIdle());
		System.out.println(dataSource.getNumActive());
		System.out.println(dataSource.getMaxTotal() - 3);
		
		ClassPathXmlApplicationContext path = (ClassPathXmlApplicationContext)context;
		path.close();
	}
}

class Log4jTest{
	
}
