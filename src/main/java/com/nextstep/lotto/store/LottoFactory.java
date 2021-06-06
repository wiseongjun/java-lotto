package com.nextstep.lotto.store;

import java.util.ArrayList;
import java.util.List;

import com.nextstep.lotto.lotto.Lotto;
import com.nextstep.lotto.lotto.LottoNumberGenerator;
import com.nextstep.lotto.lotto.Lottos;

public class LottoFactory {

	private Lottos lottos;

	public LottoFactory(int lottoCount) {
		List<Lotto> lottoList = new ArrayList<>();
		LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

		for (int i = 0; i < lottoCount; i++) {
			Lotto lotto = new Lotto(lottoNumberGenerator.makeRandomNumbers());
			lottoList.add(lotto);
		}

		lottos = new Lottos(lottoList);
	}

	public Lottos lottos() {
		return this.lottos;
	}
}
