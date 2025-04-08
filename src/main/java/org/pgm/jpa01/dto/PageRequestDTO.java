package org.pgm.jpa01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import java.net.URLEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default            // 만들어질때 이값이 디폴트 값
    private int page=1;
    @Builder.Default            // 아무값도 주지않으면 이값을 사용한다.
    private int size=3;
    private String link;
    private String type;
    private String keyword;

    public String[] getTypes(){
        if(type==null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    // pageable
    public Pageable getPageable(String...props) {          // props 스티링형 배열
        return PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
    }
    public String getLink(){
        if(link==null) {
            StringBuilder builder = new StringBuilder();            // 필요한 링크값을 더할것이다.
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(type!=null && type.length()>0){
                builder.append("&type=" + type);
            }
            if(keyword!=null){
                builder.append("&keyword=" + URLEncoder.encode(keyword));
            }
            link = builder.toString();
        }
        return link;
    }

}
