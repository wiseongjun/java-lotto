package lotto.util;

import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {
    private List<Integer> numbers;
    private int index;

    public ManualLottoGenerator(List<Integer> numbers) {
        this.numbers = numbers;
        this.index = 0;
    }

    @Override
    public int generatorNumber() {
        try {
            return numbers.get(index++);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("수동번호 생성 에러");
        }

    }
}
