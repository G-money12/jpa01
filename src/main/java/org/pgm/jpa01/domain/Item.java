package org.pgm.jpa01.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;        // 상품 코드

    @Column(unique = true, nullable=false, length=50)
    private String itemNm;      // 상품명

    @Column(name = "price", nullable = false)
    @ColumnDefault(value = "1000")
    private int price;      // 가격

    @Column(columnDefinition = "int default 10 not null")
    private int stockNumber;        // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;      // 상품 상세 설명

    @Enumerated(EnumType.ORDINAL)
    private ItemSellStatus itemSellStatus;       // 상품 판매 상태
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regTime;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    //@Transient
    private String memo;
    @Transient
    private String remark;
}
