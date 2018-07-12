package com.ltp.memo;

import com.ltp.memo.dao.MemoGroupDao;
import com.ltp.memo.dao.impl.MemoGroupDaoImpl;
import com.ltp.memo.entity.MemoGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MemoApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoApplication.class);
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        DaoCompoment daoCompoment = context.getBean(DaoCompoment.class);

        MemoGroupDao memoGroupDao = context.getBean(MemoGroupDaoImpl.class);

        List<MemoGroup> list = memoGroupDao.queryMemoGroup(2);
        LOGGER.info("MemoGroup List {}", list);

        memoGroupDao.deleteAll();

        MemoGroup m = (MemoGroup) context.getBean("memoGroup");
        memoGroupDao.insetMemoGroup(m);

        list = memoGroupDao.queryMemoGroup(3);
        LOGGER.info("MemoGroup List {}", list);
    }

}
