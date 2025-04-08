package org.pgm.jpa01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;       // 현재 페이지
    private int size;       //블록 크기
    private int total;      // 전체 레크드 수
    private int start;      // 시작 페이지 버호
    private int end;        // 끝 페이지 번호
    private boolean prev;   // 이전 페이지 존재 여부
    private boolean next;   // 다음 페이지 존재 여부
    private List<E> dtoList;    // 페이징할 dto List     E를 적는이유는 여러가지 정보가 들어 있을수 있다.// E 를 적어놓으면 어떤 리스트가 와도 된다. 제네릭을 이때 쓴다.
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList, int total) {
        if(total <=0){
            return;
        }
        this.page=pageRequestDTO.getPage();
        this.size=pageRequestDTO.getSize();
        this.total=total;
        this.dtoList=dtoList;
        this.end=(int)(Math.ceil(this.page/(double)size))*size;     // 10
        this.start=this.end-this.size+1;                    //
        int last=(int)(Math.ceil(total/(double)size));
        this.end= end>last ? last:end;       // 마지막 블록은 last가 end가 되도록 설정
        this.prev=this.start > 1 ;
        this.next=total > this.end * this.size;

    }
}
