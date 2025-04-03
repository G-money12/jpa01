package org.pgm.jpa01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.pgm.jpa01.domain.Item;
import org.pgm.jpa01.domain.ItemSellStatus;
import org.pgm.jpa01.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Log4j2
public class TestRepository {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testItemInsert() throws SQLException {
        Item item = Item.builder()
                .itemNm("수박")
                .price(1000)
                .stockNumber(10)
                .itemSellStatus(ItemSellStatus.판매중)
                .itemDetail("부산 수박")
                .build();
        itemRepository.save(item);
    }
    // findAll
    @Test
    public void testItemFindAll() throws SQLException {
        List<Item> items = itemRepository.findAll();
        log.info(items);
    }
    // findById
    public void testItemFindById() throws SQLException {
        Member member = memberRepository.findById(1L).get();
        log.info(member);
    }
    // update(save)
    public void testItemUpdate() throws SQLException {
        Item item = itemRepository.findById(1L).get();
        item.setPrice(2000);
        item.setStockNumber(20);
        item.setMemo("eeee");
        log.info(item);

    }
    // deleteById
    public void testItemDeleteById() throws SQLException {
        itemRepository.deleteById(1L);
    }









    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.info(connection);
    }
    @Test
    public void testInsert() throws SQLException {
        Member member = new Member();
        member.setName("test2");
        member.setPassword("123456");
        member.setEmail("test2@gmail.com");
        member.setAddr("부산");
        memberRepository.save(member);
    }
    @Test
    public void testFindAll() throws SQLException {
        List<Member> members = memberRepository.findAll();
        log.info(members);
        for(int i=0; i<members.size(); i++){
            log.info(members.get(i));
        }
    }
    @Test
    public void testFindById() throws SQLException {
        Member member = memberRepository.findById(1L).get();
        log.info(member);
    }
    @Test
    public void testUpdate() throws SQLException {
        Member member = memberRepository.findById(1L).get();
        member.setAddr("울산");
        member.setName("몽돌이");
        member.setId(1L);
        memberRepository.save(member);
    }
    @Test
    public void testDelete() throws SQLException {
        memberRepository.deleteById(1L);
    }
}
