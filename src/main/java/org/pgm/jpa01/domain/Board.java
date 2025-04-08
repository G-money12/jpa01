package org.pgm.jpa01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)             // IDENTITY : mysql 에서 ai 설정
    private Long bno;
    @Column(nullable = false, length = 500)
    private String title;
    @Column(nullable = false, length = 3000)
    private String content;
    @Column(nullable = false, length = 50)
    private String author;
    private int readcount;

    public void updateReadcount() {            // sql 이 없어서 여기다가 적어뿐다.
        readcount = readcount + 1;
    }
    public void change(String title, String content){               // sql 이 없어서 여기다가 적어뿐다.
        this.title = title;
        this.content = content;
    }

}
