package step2.domain;

import static step2.domain.var.LottoConstant.*;

import java.util.List;
import java.util.Objects;

public class Lotto {
	private final List<LottoNumber> lottoNumbers;

	public Lotto(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_COUNT) {
			throw new IllegalArgumentException("로또 번호는 " + LOTTO_COUNT + "개여야 합니다.");
		}
		this.lottoNumbers = lottoNumbers;
	}

	public static int getPossibleAmountByMoney(Money money) {
		return money.getMoney() / LOTTO_PRICE;
	}

	public boolean contains(LottoNumber winNumber) {
		return lottoNumbers.contains(winNumber);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < lottoNumbers.size(); i++) {
			LottoNumber number = lottoNumbers.get(i);
			sb.append(number.getNumber());
			if (i < lottoNumbers.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Lotto lotto = (Lotto)o;
		return Objects.equals(lottoNumbers, lotto.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(lottoNumbers);
	}

}
